package com.wotos.wotosplayerservice.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "player_details")
@Table(name = "player_details")
public class PlayerDetails {

    @Id
    @Column(name = "account_id", nullable = false, unique = true)
    private Integer accountID;
    @Column(name = "nickname", nullable = false)
    private String nickname;
    @Column(name = "last_battle_time", nullable = false)
    private Integer lastBattleTime;
    @Column(name = "created_at", nullable = false)
    private Integer createdAt;
    @Column(name = "updated_at", nullable = false)
    private Integer updatedAt;
    @Column(name = "logout_at", nullable = false)
    private Integer logoutAt;
    @Column(name = "clan_id", nullable = false)
    private Integer clanId;
    @Column(name = "client_language", nullable = false)
    private String clientLanguage;
    @Column(name = "is_private_account", nullable = false)
    private Boolean isPrivateAccount;
    @Column(name = "global_rating", nullable = false)
    private Integer globalRating;
    @Column(name = "statistics", nullable = false)
    private PlayerStatisticsSnapshot[] statistics;

    public Integer getAccountID() {
        return accountID;
    }

    public void setAccountID(Integer accountID) {
        this.accountID = accountID;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getLastBattleTime() {
        return lastBattleTime;
    }

    public void setLastBattleTime(Integer lastBattleTime) {
        this.lastBattleTime = lastBattleTime;
    }

    public Integer getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Integer updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getLogoutAt() {
        return logoutAt;
    }

    public void setLogoutAt(Integer logoutAt) {
        this.logoutAt = logoutAt;
    }

    public Integer getClanId() {
        return clanId;
    }

    public void setClanId(Integer clanId) {
        this.clanId = clanId;
    }

    public String getClientLanguage() {
        return clientLanguage;
    }

    public void setClientLanguage(String clientLanguage) {
        this.clientLanguage = clientLanguage;
    }

    public Boolean getPrivateAccount() {
        return isPrivateAccount;
    }

    public void setPrivateAccount(Boolean privateAccount) {
        isPrivateAccount = privateAccount;
    }

    public Integer getGlobalRating() {
        return globalRating;
    }

    public void setGlobalRating(Integer globalRating) {
        this.globalRating = globalRating;
    }

    public PlayerStatisticsSnapshot[] getStatistics() {
        return statistics;
    }

    public void setStatistics(PlayerStatisticsSnapshot[] statistics) {
        this.statistics = statistics;
    }
}
