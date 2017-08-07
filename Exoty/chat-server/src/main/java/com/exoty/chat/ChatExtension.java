package com.exoty.chat;

import com.smartfoxserver.v2.api.CreateRoomSettings;
import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.core.SFSEventType;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.exceptions.SFSCreateRoomException;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;
import com.smartfoxserver.v2.extensions.BaseServerEventHandler;
import com.smartfoxserver.v2.extensions.SFSExtension;

import java.util.ArrayList;
import java.util.List;

public class ChatExtension extends SFSExtension{
    private List<User> users;

    public void init() {
        trace("Init Chat Extension");
//        addEventHandler(SFSEventType.USER_JOIN_ZONE, ZoneEventHandler.class);
        users = new ArrayList<>();
        addEventHandler(SFSEventType.USER_JOIN_ZONE, new ZoneEventHandler());
        addEventHandler(SFSEventType.USER_DISCONNECT, new ZoneEventHandler());
        addRequestHandler("chat-to", new ChatReqHandler());
        addRequestHandler("create-room", new BaseClientRequestHandler() {
            @Override
            public void handleClientRequest(User user, ISFSObject params) {
                String name = params.getUtfString("name");
                makeRoom(user, name);
            }
        });
    }

    void makeRoom(User user, String nameRoom)
    {
        CreateRoomSettings settings = new CreateRoomSettings();
        settings.setName("room");
        settings.setMaxUsers(20);
        try {
            getApi().createRoom(getParentZone(), settings, user);
        } catch (SFSCreateRoomException e) {
            trace(e);
        }
    }


    public class ChatReqHandler extends BaseClientRequestHandler
    {
        @Override
        public void handleClientRequest(User sender, ISFSObject params)
        {
            String str = params.getUtfString("msg");
            params.putUtfString("from", sender.getName());
            trace("request: ", "{ chat: ", str, "}");
            send("chat-from", params, users);
        }
    }



    public class ZoneEventHandler extends BaseServerEventHandler
    {
        public ZoneEventHandler() {
            super();
        }

        @Override
        public void handleServerEvent(ISFSEvent event) throws SFSException
        {
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
