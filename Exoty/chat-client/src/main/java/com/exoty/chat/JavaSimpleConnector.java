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
import sfs2x.client.requests.CreateRoomRequest;
import sfs2x.client.requests.ExtensionRequest;
import sfs2x.client.requests.LoginRequest;
import sfs2x.client.requests.LogoutRequest;
import sfs2x.client.util.ConfigData;

import java.util.Map;

/**
 * Basic SFS2X client, performing connection and login to a 'localhost' server
 */
public class JavaSimpleConnector implements IEventListener
{
    public SmartFox getSfs() {
        return sfs;
    }

    private final SmartFox sfs;
    private final ConfigData cfg;
    
    private final Logger log = LoggerFactory.getLogger(getClass());

    public JavaSimpleConnector() 
    {
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



    /**
     * This handles the events coming from the server
     */
    @Override
    public void dispatch(BaseEvent evt) throws SFSException {
        switch (evt.getType()) {
            case SFSEvent.CONNECTION:
                boolean success = (Boolean) evt.getArguments().get("success");

                if (!success) {
                    log.warn("Connection failed!");
                    return;
                }
                //log.info("Connection success: " + sfs.getConnectionMode());
                break;
            case SFSEvent.CONNECTION_LOST:
                //log.info("Connection was closed");
                break;
            case SFSEvent.LOGIN:
                //log.info("Logged in as: " + sfs.getMySelf().getName());
                System.err.println("Logged in as: " + sfs.getMySelf().getName());
                break;
            case SFSEvent.LOGIN_ERROR:
                //log.warn("Login error:  " + evt.getArguments().get("errorMessage"));
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
        }
    }

    private void processExtensionResponse(String cmd, ISFSObject params) {
        switch (cmd) {
            case "chat-from":
                String msg =params.getUtfString("msg");
                String from = params.getUtfString("from");
                if (!from.equals(sfs.getMySelf().getName()))
                    System.out.println(String.format("from '%s': %s", from, msg));
                break;
        }
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

    public void login(String username) {
        sfs.send(new LoginRequest(username, ""));
    }

    public void logout() {
        sfs.send(new LogoutRequest());
    }

    public void sendMsg(String msg) {
        SFSObject params = new SFSObject();
        params.putUtfString("msg", msg);
        sfs.send(new ExtensionRequest("chat-to", params));
    }


}

