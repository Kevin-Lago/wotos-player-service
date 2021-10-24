package com.wotos.wotosplayerservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wotos.wotosplayerservice.jsonao.StatisticsJSONAO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "player_details")
@Table(name = "player_details")
public class PlayerDetails {

    @Id
    @Column(name = "account_id", nullable = false, unique = true)
    private Integer account_id;
    private String nickname;
    private Integer last_battle_time;
    private Integer created_at;
    private Integer updated_at;
    private Integer logout_at;
    private Integer clan_id;
    private String client_language;
    private Boolean is_private_account;
    private Integer global_rating;
    private StatisticsSnapshot[] statistics;

    public Integer getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Integer account_id) {
        this.account_id = account_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getLast_battle_time() {
        return last_battle_time;
    }

    public void setLast_battle_time(Integer last_battle_time) {
        this.last_battle_time = last_battle_time;
    }

    public Integer getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Integer created_at) {
        this.created_at = created_at;
    }

    public Integer getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Integer updated_at) {
        this.updated_at = updated_at;
    }

    public Integer getLogout_at() {
        return logout_at;
    }

    public void setLogout_at(Integer logout_at) {
        this.logout_at = logout_at;
    }

    public Integer getClan_id() {
        return clan_id;
    }

    public void setClan_id(Integer clan_id) {
        this.clan_id = clan_id;
    }

    public String getClient_language() {
        return client_language;
    }

    public void setClient_language(String client_language) {
        this.client_language = client_language;
    }

    public Boolean getIs_private_account() {
        return is_private_account;
    }

    public void setIs_private_account(Boolean is_private_account) {
        this.is_private_account = is_private_account;
    }

    public Integer getGlobal_rating() {
        return global_rating;
    }

    public void setGlobal_rating(Integer global_rating) {
        this.global_rating = global_rating;
    }

    public StatisticsSnapshot[] getStatistics() {
        return statistics;
    }

    public void setStatistics(StatisticsSnapshot[] statistics) {
        this.statistics = statistics;
    }
}