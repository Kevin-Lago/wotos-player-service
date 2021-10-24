package com.wotos.wotosplayerservice.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class PlayerTankStatisticsSnapshot {

    @Id
    @GeneratedValue
    private Integer player_tank_statistics_snapshot_id;
    @Column(name = "player_id", nullable = false)
    private Integer player_id;
    @Column(name = "tank_id", nullable = false)
    private Integer tank_id;
    @Column(name = "total_battles", nullable = false)
    private Integer total_battles;
    @Column(name = "average_wn8", nullable = false)
    private Float average_wn8;
    @Column(name = "hit_ratio", nullable = false)
    private Float hit_ratio;
    @Column(name = "average_experience", nullable = false)
    private Float average_experience;
    @Column(name = "personal_rating", nullable = false)
    private Integer personal_rating;

    public Integer getPlayer_tank_statistics_snapshot_id() {
        return player_tank_statistics_snapshot_id;
    }

    public void setPlayer_tank_statistics_snapshot_id(Integer player_tank_statistics_snapshot_id) {
        this.player_tank_statistics_snapshot_id = player_tank_statistics_snapshot_id;
    }

    public Integer getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(Integer player_id) {
        this.player_id = player_id;
    }

    public Integer getTank_id() {
        return tank_id;
    }

    public void setTank_id(Integer tank_id) {
        this.tank_id = tank_id;
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

    public Integer getPersonal_rating() {
        return personal_rating;
    }

    public void setPersonal_rating(Integer personal_rating) {
        this.personal_rating = personal_rating;
    }
}
