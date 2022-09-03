package com.wotos.wotosplayerservice.util.model.wot.player;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wotos.wotosplayerservice.util.model.wot.statistics.PlayerStatistics;

import java.util.Map;

public class PlayerDetails {

    @JsonProperty("client_language")
    private final String clientLanguage;
    @JsonProperty("last_battle_time")
    private final Integer lastBattleTime;
    @JsonProperty("account_id")
    private final Integer accountId;
    @JsonProperty("created_at")
    private final Integer createdAt;
    @JsonProperty("updated_at")
    private final Integer updatedAt;
    @JsonProperty("private")
    private final Boolean isPrivateAccount;
    @JsonProperty("global_rating")
    private final Integer globalRating;
    @JsonProperty("clan_id")
    private final Integer clanId;
    @JsonProperty("statistics")
    private final PlayerStatistics statistics;
    @JsonProperty("nickname")
    private final String nickname;
    @JsonProperty("logout_at")
    private final Integer logoutAt;

    public PlayerDetails(
            String clientLanguage, Integer lastBattleTime, Integer accountId,
            Integer createdAt, Integer updatedAt, Boolean isPrivateAccount,
            Integer globalRating, Integer clanId, PlayerStatistics statistics,
            String nickname, Integer logoutAt
    ) {
        this.clientLanguage = clientLanguage;
        this.lastBattleTime = lastBattleTime;
        this.accountId = accountId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isPrivateAccount = isPrivateAccount;
        this.globalRating = globalRating;
        this.clanId = clanId;
        this.statistics = statistics;
        this.nickname = nickname;
        this.logoutAt = logoutAt;
    }

    public String getClientLanguage() {
        return clientLanguage;
    }

    public Integer getLastBattleTime() {
        return lastBattleTime;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public Integer getCreatedAt() {
        return createdAt;
    }

    public Integer getUpdatedAt() {
        return updatedAt;
    }

    public Boolean getPrivateAccount() {
        return isPrivateAccount;
    }

    public Integer getGlobalRating() {
        return globalRating;
    }

    public Integer getClanId() {
        return clanId;
    }

    public PlayerStatistics getStatistics() {
        return statistics;
    }

    public String getNickname() {
        return nickname;
    }

    public Integer getLogoutAt() {
        return logoutAt;
    }
}
