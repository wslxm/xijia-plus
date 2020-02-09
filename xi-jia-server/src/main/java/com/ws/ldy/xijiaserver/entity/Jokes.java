package com.ws.ldy.xijiaserver.entity;

import javax.xml.crypto.Data;

@lombok.Data
public class Jokes {
    private Data updateTime;
    private String content;

    public Jokes(Data updateTime, String content) {
        this.updateTime = updateTime;
        this.content = content;
    }
}
