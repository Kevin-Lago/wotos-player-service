package com.wotos.wotosplayerservice.util.model.wot.player;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Player {

    private final String nickname;
    @JsonProperty("account_id")
    private final String accountId;

    public Player(String nickname, String accountId) {
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
