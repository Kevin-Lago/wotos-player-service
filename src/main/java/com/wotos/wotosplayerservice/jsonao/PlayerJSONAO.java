package com.wotos.wotosplayerservice.jsonao;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayerJSONAO {

    private String nickname;
    @JsonProperty("account_id")
    private String accountID;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }
}
