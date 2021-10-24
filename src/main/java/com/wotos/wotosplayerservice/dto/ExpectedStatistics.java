package com.wotos.wotosplayerservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "expected_statistics")
public class ExpectedStatistics {

    @Id
    @Column(name = "tank_id", nullable = false, unique = true)
    @JsonProperty("IDNum")
    private Integer tank_id;
    @Column(name = "expected_defense", nullable = false)
    @JsonProperty("expDef")
    private Float expected_defense;
    @Column(name = "expected_frag", nullable = false)
    @JsonProperty("expFrag")
    private Float expected_frag;
    @Column(name = "expected_spot", nullable = false)
    @JsonProperty("expSpot")
    private Float expected_spot;
    @Column(name = "expected_damage", nullable = false)
    @JsonProperty("expDamage")
    private Float expected_damage;
    @Column(name = "expected_win_rate", nullable = false)
    @JsonProperty("expWinRate")
    private Float expected_win_rate;

    public Integer getTank_id() {
        return tank_id;
    }

    public void setTank_id(Integer tank_id) {
        this.tank_id = tank_id;
    }

    public Float getExpected_defense() {
        return expected_defense;
    }

    public void setExpected_defense(Float expected_defense) {
        this.expected_defense = expected_defense;
    }

    public Float getExpected_frag() {
        return expected_frag;
    }

    public void setExpected_frag(Float expected_frag) {
        this.expected_frag = expected_frag;
    }

    public Float getExpected_spot() {
        return expected_spot;
    }

    public void setExpected_spot(Float expected_spot) {
        this.expected_spot = expected_spot;
    }

    public Float getExpected_damage() {
        return expected_damage;
    }

    public void setExpected_damage(Float expected_damage) {
        this.expected_damage = expected_damage;
    }

    public Float getExpected_win_rate() {
        return expected_win_rate;
    }

    public void setExpected_win_rate(Float expected_win_rate) {
        this.expected_win_rate = expected_win_rate;
    }
}
