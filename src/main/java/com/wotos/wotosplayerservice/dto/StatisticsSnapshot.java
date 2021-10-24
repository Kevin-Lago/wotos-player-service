package com.wotos.wotosplayerservice.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "statistics_snapshot")
@Table(name = "statistics_snapshot")
public class StatisticsSnapshot {

    @Column(name = "player_id", nullable = false)
    private Integer player_id;
    @Column(name = "player_id", nullable = false)
    private Integer total_battles;
    @Column(name = "player_id", nullable = false)
    private Float average_wn8;
    @Column(name = "player_id", nullable = false)
    private Float hit_ratio;
    @Column(name = "player_id", nullable = false)
    private Float average_experience;
    @Column(name = "player_id", nullable = false)
    private int personal_rating;

    public Integer getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(Integer player_id) {
        this.player_id = player_id;
    }

    public Integer getTotal_battles() {
        return total_battles;
    }

    public void setTotal_battles(Integer total_battles) {
        this.total_battles = total_battles;
    }

    public Float getAverage_wn8() {
        return average_wn8;
    }

    public void setAverage_wn8(Float average_wn8) {
        this.average_wn8 = average_wn8;
    }

    public Float getHit_ratio() {
        return hit_ratio;
    }

    public void setHit_ratio(Float hit_ratio) {
        this.hit_ratio = hit_ratio;
    }

    public Float getAverage_experience() {
        return average_experience;
    }

    public void setAverage_experience(Float average_experience) {
        this.average_experience = average_experience;
    }

    public int getPersonal_rating() {
        return personal_rating;
    }

    public void setPersonal_rating(int personal_rating) {
        this.personal_rating = personal_rating;
    }
}
