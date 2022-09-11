package com.wotos.wotosplayerservice.util.model.wot.player;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WotPlayer {

    private final String nickname;
    @JsonProperty("account_id")
    private final Integer accountId;

    public WotPlayer(String nickname, Integer accountId) {
        this.nickname = nickname;
        this.accountId = accountId;
    }

    public String getNickname() {
        return nickname;
    }

    public Integer getAccountId() {
        return accountId;
    }
}
