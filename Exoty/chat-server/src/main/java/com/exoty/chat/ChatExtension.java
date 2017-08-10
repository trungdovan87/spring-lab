package com.exoty.chat;

import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.core.SFSEventType;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;
import com.smartfoxserver.v2.extensions.BaseServerEventHandler;
import com.smartfoxserver.v2.extensions.SFSExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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

    public void init() {
        trace("Init Chat Extension");
//        addEventHandler(SFSEventType.USER_JOIN_ZONE, ZoneEventHandler.class);
        users = new ArrayList<>();
        addEventHandler(SFSEventType.USER_JOIN_ZONE, new ZoneEventHandler());
        addEventHandler(SFSEventType.USER_DISCONNECT, new ZoneEventHandler());
        addRequestHandler("chat-to", new ChatReqHandler());
        addRequestHandler("chat-private-to", new ChatPrivateHandler());
    }


    public class ChatReqHandler extends BaseClientRequestHandler {
        @Override
        public void handleClientRequest(User sender, ISFSObject params) {
            String msg = params.getUtfString("msg");

            ISFSObject cmd = new SFSObject();
            cmd.putUtfString("msg", filterChat(msg));
            cmd.putUtfString("from", sender.getName());

            trace("request: ", "{ chat: ", msg, "}");
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
            } else {
                ISFSObject cmd = new SFSObject();
                cmd.putUtfString("msg", filterChat(msg));
                cmd.putUtfString("from", user.getName());
                send("chat-private-from", cmd, u);
            }
        }
    }

    public class ZoneEventHandler extends BaseServerEventHandler {
        public ZoneEventHandler() {
            super();
        }

        @Override
        public void handleServerEvent(ISFSEvent event) throws SFSException {
            User user = (User) event.getParameter(SFSEventParam.USER);
            trace("handleServerEvent: " + event.getType());
            switch (event.getType()) {
                case USER_JOIN_ZONE:
                    users.add(user);
                    trace("Welcome new user: " + user.getName());
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
