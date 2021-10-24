package com.wotos.wotosplayerservice.jsonao;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayerDetailsJSONAO {

    @JsonProperty("client_language")
    private String clientLanguage;
    @JsonProperty("last_battle_time")
    private long lastBattleTime;
    @JsonProperty("account_id")
    private long accountID;
    @JsonProperty("created_at")
    private long createdAt;
    @JsonProperty("updated_at")
    private long updatedAt;
    @JsonProperty("private")
    private boolean isPrivateAccount;
    @JsonProperty("global_rating")
    private long globalRating;
    @JsonProperty("clan_id")
    private long clanID;
    private String nickname;
    @JsonProperty("logout_at")
    private long logoutAt;
    private StatisticsByModeJSONAO statistics;

    public String getClientLanguage() {
        return clientLanguage;
    }

    public void setClientLanguage(String clientLanguage) {
        this.clientLanguage = clientLanguage;
    }

    public long getLastBattleTime() {
        return lastBattleTime;
    }

    public void setLastBattleTime(long lastBattleTime) {
        this.lastBattleTime = lastBattleTime;
    }

    public long getAccountID() {
        return accountID;
    }

    public void setAccountID(long accountID) {
        this.accountID = accountID;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isPrivateAccount() {
        return isPrivateAccount;
    }

    public void setPrivateAccount(boolean privateAccount) {
        isPrivateAccount = privateAccount;
    }

    public long getGlobalRating() {
        return globalRating;
    }

    public void setGlobalRating(long globalRating) {
        this.globalRating = globalRating;
    }

    public long getClanID() {
        return clanID;
    }

    public void setClanID(long clanID) {
        this.clanID = clanID;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public long getLogoutAt() {
        return logoutAt;
    }

    public void setLogoutAt(long logoutAt) {
        this.logoutAt = logoutAt;
    }

    public StatisticsByModeJSONAO getStatistics() {
        return statistics;
    }

    public void setStatistics(StatisticsByModeJSONAO statistics) {
        this.statistics = statistics;
    }
}
