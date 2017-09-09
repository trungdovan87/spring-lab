package com.exoty.chat;

import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;

import java.util.Objects;

public class HistoryChat {
    private String from;
    private String msg;

    public HistoryChat(String from, String msg) {
        Objects.requireNonNull(from);
        Objects.requireNonNull(msg);
        this.from = from;
        this.msg = msg;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ISFSObject toSFSObject() {
        ISFSObject obj = new SFSObject();
        obj.putUtfString("from", from);
        obj.putUtfString("msg", msg);
        return obj;
    }
}
