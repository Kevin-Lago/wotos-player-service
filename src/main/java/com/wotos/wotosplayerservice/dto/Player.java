package com.wotos.wotosplayerservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Player {

    private String nickname;
    @JsonProperty("account_id")
    private Integer accountId;

    public Player(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

}
