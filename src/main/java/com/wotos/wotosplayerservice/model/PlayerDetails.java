package com.wotos.wotosplayerservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(schema = "players")
public class PlayerDetails {

    private String client_language;
    private long last_battle_time;
    @Id
    private long account_id;
    private long created_at;
    private long updated_at;
    @JsonProperty("private")
    private boolean isPrivate;
    private long global_rating;
    private long clan_id;
    @JsonInclude
    @Transient
    private Statistics statistics;
    private String nickname;
    private long logout_at;

    public String getClient_language() {
        return client_language;
    }

    public void setClient_language(String client_language) {
        this.client_language = client_language;
    }

    public long getLast_battle_time() {
        return last_battle_time;
    }

    public void setLast_battle_time(long last_battle_time) {
        this.last_battle_time = last_battle_time;
    }

    public long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(long account_id) {
        this.account_id = account_id;
    }

    public long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }

    public long getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(long updated_at) {
        this.updated_at = updated_at;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public long getGlobal_rating() {
        return global_rating;
    }

    public void setGlobal_rating(long global_rating) {
        this.global_rating = global_rating;
    }

    public long getClan_id() {
        return clan_id;
    }

    public void setClan_id(long clan_id) {
        this.clan_id = clan_id;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public long getLogout_at() {
        return logout_at;
    }

    public void setLogout_at(long logout_at) {
        this.logout_at = logout_at;
    }
}
