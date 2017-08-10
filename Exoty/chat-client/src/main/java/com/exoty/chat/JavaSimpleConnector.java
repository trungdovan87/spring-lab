/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exoty.chat;

import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.exceptions.SFSException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sfs2x.client.SmartFox;
import sfs2x.client.core.BaseEvent;
import sfs2x.client.core.IEventListener;
import sfs2x.client.core.SFSEvent;
import sfs2x.client.requests.ExtensionRequest;
import sfs2x.client.requests.LoginRequest;
import sfs2x.client.requests.LogoutRequest;
import sfs2x.client.util.ConfigData;

import java.util.Map;
import java.util.stream.IntStream;

/**
 * Basic SFS2X client, performing connection and login to a 'localhost' server
 */
public class JavaSimpleConnector implements IEventListener {
    private final SmartFox sfs;
    private final ConfigData cfg;
    private final Logger log = LoggerFactory.getLogger(getClass());

    public JavaSimpleConnector() {
        /**
         * Setup the main API object and add all the events we
         * want to listen to.
         */
        sfs = new SmartFox();
        sfs.addEventListener(SFSEvent.CONNECTION, this);
        sfs.addEventListener(SFSEvent.CONNECTION_LOST, this);
        sfs.addEventListener(SFSEvent.LOGIN, this);
        sfs.addEventListener(SFSEvent.LOGIN_ERROR, this);
        sfs.addEventListener(SFSEvent.LOGOUT, this);
        sfs.addEventListener(SFSEvent.ROOM_JOIN, this);
        sfs.addEventListener(SFSEvent.ROOM_JOIN_ERROR, this);
        sfs.addEventListener(SFSEvent.EXTENSION_RESPONSE, this);
        sfs.addEventListener("test", this);

        /**
         * Create a configuration for the connection passing
         * the basic parameters such as host, TCP port number and Zone name
         * that will be used for logging in.
         */
        cfg = new ConfigData();
        cfg.setHost("localhost");
        cfg.setPort(9933);
        cfg.setZone("Chat");
    }

    public SmartFox getSfs() {
        return sfs;
    }

    /**
     * This handles the events coming from the server
     */
    @Override
    public void dispatch(BaseEvent evt) throws SFSException {
        switch (evt.getType()) {
            case SFSEvent.CONNECTION:
                boolean success = (Boolean) evt.getArguments().get("success");

                if (!success)
                    System.err.println("Connection failed!");
                else
                    System.err.println("Connection success!");
                break;
            case SFSEvent.CONNECTION_LOST:
                //log.info("Connection was closed");
                System.err.println("Disconnect!!! please, type: /connect to reconnect...");
                break;
            case SFSEvent.LOGIN:
                //log.info("Logged in as: " + sfs.getMySelf().getName());
                System.err.println("Logged in as: " + sfs.getMySelf().getName());
                break;
            case SFSEvent.LOGIN_ERROR:
                //log.warn("Login error:  " + evt.getArguments().get("errorMessage"));
                System.err.println("Login error:  " + evt.getArguments().get("errorMessage"));
                System.err.println("please: login again with command: '/login'");
                break;
            case SFSEvent.LOGOUT:
                System.err.println("Logout!!");
                break;
            case SFSEvent.ROOM_JOIN:
                System.err.println("Join Room Success!!");
                break;
            case SFSEvent.ROOM_JOIN_ERROR:
                log.warn("Room Join error:  " + evt.getArguments().get("errorMessage"));
                System.err.println("Join Room Error!!");
                break;
            case SFSEvent.EXTENSION_RESPONSE:
                Map<String, Object> args = evt.getArguments();
                String cmd = (String) args.get("cmd");
                ISFSObject p = (ISFSObject) args.get("params");
                processExtensionResponse(cmd, p);
                break;
            default:
                break;
        }
    }

    private void processExtensionResponse(String cmd, ISFSObject params) {
        switch (cmd) {
            case "chat-from":
                String msg = params.getUtfString("msg");
                String from = params.getUtfString("from");
                if (!from.equals(sfs.getMySelf().getName()))
                    System.out.println(String.format("from '%s': %s", from, msg));
                break;
            case "chat-private-from":
                msg = params.getUtfString("msg");
                from = params.getUtfString("from");
                System.out.println(String.format("from '%s' [private]: %s", from, msg));
                break;
            case "chat-private-from-exception":
                System.err.println("Exception when send Private msg: " + params.getUtfString("error"));
                break;
            case "user-list":
                System.out.println("--- List user: ");
                params.getUtfStringArray("users").stream().forEach(System.out::println);
                System.out.println("------");
                break;
            case "history-chat":
                historyChat(params);
                break;
            default:
                System.out.println("Receive msg with UNKNOWN cmd = " + cmd);
                break;
        }
    }

    private void historyChat(ISFSObject params) {
        int size = params.getInt("size");
        if (size == 0) {
            System.err.println("--- NO chat in history!!!");
            return;
        }

        System.err.println("--- History Chat:");
        IntStream.range(0, size).forEach(i -> {
            String from = params.getUtfString("from" + i);
            String msg = params.getUtfString("msg" + i);
            System.err.println(String.format("from '%s' [history]: %s", from, msg));

        });
    }

    public void connect() {
        sfs.connect(cfg);
    }

    public void disconnect() {
        sfs.disconnect();
    }

    public boolean isConnected() {
        return sfs.isConnected();
    }

    public void login(String username, String password) {
        sfs.send(new LoginRequest(username, password));
    }

    public void logout() {
        sfs.send(new LogoutRequest());
    }

    public void sendMsg(String msg) {
        SFSObject params = new SFSObject();
        params.putUtfString("msg", msg);
        sfs.send(new ExtensionRequest("chat-to", params));
    }

    public void sendPrivateMsg(String username, String msg) {
        SFSObject params = new SFSObject();
        params.putUtfString("to", username);
        params.putUtfString("msg", msg);
        sfs.send(new ExtensionRequest("chat-private-to", params));
    }

    public void sendUserList() {
        SFSObject params = new SFSObject();
        sfs.send(new ExtensionRequest("user-list", params));
    }

}

