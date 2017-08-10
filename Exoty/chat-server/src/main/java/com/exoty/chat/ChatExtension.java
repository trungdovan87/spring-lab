package com.exoty.chat;

import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.core.SFSEventType;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.*;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;
import com.smartfoxserver.v2.extensions.BaseServerEventHandler;
import com.smartfoxserver.v2.extensions.SFSExtension;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.stream.Collectors;

public class ChatExtension extends SFSExtension {

    public static final String[] BAD_WORDS = {
            "fuck", "bitch", "cock", "dick", "shit", "dm", "vkl",
    };

    public static final String filterChat(String s) {
        Objects.requireNonNull(s);
        for (String bad : BAD_WORDS) {
            s = s.replaceAll(bad, "***");
        }
        return s;
    }

    private List<User> users;

    private ConcurrentLinkedDeque<HistoryChat> history;

    public void init() {
        trace("Init Chat Extension");
//        addEventHandler(SFSEventType.USER_JOIN_ZONE, ZoneEventHandler.class);
        users = Collections.synchronizedList(new ArrayList<>());
        history = new ConcurrentLinkedDeque<>();

        ZoneEventHandler zoneEventHandler = new ZoneEventHandler();
        addEventHandler(SFSEventType.USER_JOIN_ZONE, zoneEventHandler);
        addEventHandler(SFSEventType.USER_DISCONNECT, zoneEventHandler);
        addEventHandler(SFSEventType.USER_LOGOUT, zoneEventHandler);
        addEventHandler(SFSEventType.USER_LOGIN, zoneEventHandler);

        addRequestHandler("chat-to", new ChatReqHandler());
        addRequestHandler("chat-private-to", new ChatPrivateHandler());
        addRequestHandler("user-list", new UserListReqHandler());
    }

    private void addChatHistory(String from, String msg) {
        history.addLast(new HistoryChat(from, msg));
        if (history.size() > 100) {
            history.removeFirst();
        }
    }

    private void sendHistoryChat(User user) {
        ISFSObject cmd = new SFSObject();
        cmd.putInt("size", history.size());
        int i = 0;
        for (HistoryChat chat : history) {
            cmd.putUtfString("from" + i, chat.getFrom());
            cmd.putUtfString("msg" + i, chat.getMsg());
            i++;
        }

        send("history-chat", cmd, user);
    }


    public class ChatReqHandler extends BaseClientRequestHandler {
        @Override
        public void handleClientRequest(User sender, ISFSObject params) {
            String msg = filterChat(params.getUtfString("msg"));
            trace("request: ", "{ chat: ", msg, "}");
            addChatHistory(sender.getName(), msg);

            ISFSObject cmd = new SFSObject();
            cmd.putUtfString("msg", msg);
            cmd.putUtfString("from", sender.getName());

            send("chat-from", cmd, users);
        }
    }

    public class ChatPrivateHandler extends BaseClientRequestHandler {

        @Override
        public void handleClientRequest(User user, ISFSObject params) {
            String to = params.getUtfString("to");
            String msg = params.getUtfString("msg");
            Objects.requireNonNull(to);
            Objects.requireNonNull(msg);
            User u = this.getApi().getUserByName(to);
            if (u == null) {
                ChatExtension.this.trace(String.format("user '%s' is null", to));
                ISFSObject cmd = new SFSObject();
                cmd.putUtfString("error", String.format("User '%s' NOT online", to));
                send("chat-private-from-exception", cmd, user);
            } else {
                ISFSObject cmd = new SFSObject();
                cmd.putUtfString("msg", filterChat(msg));
                cmd.putUtfString("from", user.getName());
                send("chat-private-from", cmd, u);
            }
        }
    }

    public class UserListReqHandler extends BaseClientRequestHandler {
        @Override
        public void handleClientRequest(User sender, ISFSObject params) {
            ISFSObject cmd = new SFSObject();
            List<String> userList = users.stream().map(u -> u.getName()).collect(Collectors.toList());
            cmd.putUtfStringArray("users", userList);
            send("user-list", cmd, users);
        }
    }


    public class ZoneEventHandler extends BaseServerEventHandler {

        @Override
        public void handleServerEvent(ISFSEvent event) throws SFSException {
            User user = (User) event.getParameter(SFSEventParam.USER);
            trace("handleServerEvent: " + event.getType());
            switch (event.getType()) {
                case USER_JOIN_ZONE:
                    users.add(user);
                    trace("Welcome new user JOIN_ZONE: " + user.getName());
                    sendHistoryChat(user);
//                    sendHistoryChat(user);
                    break;
                case USER_LOGIN:
                    users.add(user);
                    trace("Welcome new user LOGIN: " + user.getName());

                    break;

                case USER_LOGOUT:
                    users.remove(user);
                    trace("Logout user: " + user.getName());
                    break;

                case USER_DISCONNECT:
                    users.remove(user);
                    trace("Disconnect user: " + user.getName());
                    break;
            }
        }

    }
}
