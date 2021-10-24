package com.wotos.wotosplayerservice.dto;

import javax.persistence.*;

@Entity(name = "tank_statistics")
@Table(name = "tank_statistics")
public class PlayerTankStatistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stats_id", nullable = false, unique = true)
    private Integer stats_id;
    private Integer player_id;
    private Integer tank_id;
    private Float wn8;
    private Integer battles;
    private Float average_wn8;
    private Float average_xp;
    private Float hit_ratio;
    private Float kill_ratio;
    private Float win_rate;
    private Float damage_per_game;
    private Float average_kills_per_game;
    private Integer total_battles;

    public Integer getStats_id() {
        return stats_id;
    }

    public void setStats_id(Integer stats_id) {
        this.stats_id = stats_id;
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

    public Float getWn8() {
        return wn8;
    }

    public void setWn8(Float wn8) {
        this.wn8 = wn8;
    }

    public Integer getBattles() {
        return battles;
    }

    public void setBattles(Integer battles) {
        this.battles = battles;
    }

    public Float getAverage_wn8() {
        return average_wn8;
    }

    public void setAverage_wn8(Float average_wn8) {
        this.average_wn8 = average_wn8;
    }

    public Float getAverage_xp() {
        return average_xp;
    }

    public void setAverage_xp(Float average_xp) {
        this.average_xp = average_xp;
    }

    public Float getHit_ratio() {
        return hit_ratio;
    }

    public void setHit_ratio(Float hit_ratio) {
        this.hit_ratio = hit_ratio;
    }

    public Float getKill_ratio() {
        return kill_ratio;
    }

    public void setKill_ratio(Float kill_ratio) {
        this.kill_ratio = kill_ratio;
    }

    public Float getWin_rate() {
        return win_rate;
    }

    public void setWin_rate(Float win_rate) {
        this.win_rate = win_rate;
    }

    public Float getDamage_per_game() {
        return damage_per_game;
    }

    public void setDamage_per_game(Float damage_per_game) {
        this.damage_per_game = damage_per_game;
    }

    public Float getAverage_kills_per_game() {
        return average_kills_per_game;
    }

    public void setAverage_kills_per_game(Float average_kills_per_game) {
        this.average_kills_per_game = average_kills_per_game;
    }

    public Integer getTotal_battles() {
        return total_battles;
    }

    public void setTotal_battles(Integer total_battles) {
        this.total_battles = total_battles;
    }

}
