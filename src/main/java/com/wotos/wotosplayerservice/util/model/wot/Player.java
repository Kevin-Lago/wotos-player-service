package com.wotos.wotosplayerservice.util.model.wot;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Player {

    private String nickname;
    @JsonProperty("account_id")
    private String accountId;

    public Player(String nickname) {
        this.nickname = nickname;
    }

    public Player(String nickname, String accountId) {
        this.nickname = nickname;
        this.accountId = accountId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

}
