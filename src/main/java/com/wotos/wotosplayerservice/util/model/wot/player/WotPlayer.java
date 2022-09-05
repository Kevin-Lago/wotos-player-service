package com.wotos.wotosplayerservice.util.model.wot.player;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WotPlayer {

    private final String nickname;
    @JsonProperty("account_id")
    private final String accountId;

    public WotPlayer(String nickname, String accountId) {
        this.nickname = nickname;
        this.accountId = accountId;
    }

    public String getNickname() {
        return nickname;
    }

    public String getAccountId() {
        return accountId;
    }
}