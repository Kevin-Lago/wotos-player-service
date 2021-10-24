package com.wotos.wotosplayerservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "expected_statistics")
@Table(name = "expected_statistics")
public class ExpectedStatistics {

    @Id
    @Column(name = "tank_id", nullable = false, unique = true)
    @JsonProperty("IDNum")
    private Integer tankID;
    @Column(name = "expected_defense", nullable = false)
    @JsonProperty("expDef")
    private Float expectedDefense;
    @Column(name = "expected_frag", nullable = false)
    @JsonProperty("expFrag")
    private Float expectedFrag;
    @Column(name = "expected_spot", nullable = false)
    @JsonProperty("expSpot")
    private Float expectedSpot;
    @Column(name = "expected_damage", nullable = false)
    @JsonProperty("expDamage")
    private Float expectedDamage;
    @Column(name = "expected_win_rate", nullable = false)
    @JsonProperty("expWinRate")
    private Float expectedWinRate;

    public Integer getTankID() {
        return tankID;
    }

    public void setTankID(Integer tankID) {
        this.tankID = tankID;
    }

    public Float getExpectedDefense() {
        return expectedDefense;
    }

    public void setExpectedDefense(Float expectedDefense) {
        this.expectedDefense = expectedDefense;
    }

    public Float getExpectedFrag() {
        return expectedFrag;
    }

    public void setExpectedFrag(Float expectedFrag) {
        this.expectedFrag = expectedFrag;
    }

    public Float getExpectedSpot() {
        return expectedSpot;
    }

    public void setExpectedSpot(Float expectedSpot) {
        this.expectedSpot = expectedSpot;
    }

    public Float getExpectedDamage() {
        return expectedDamage;
    }

    public void setExpectedDamage(Float expectedDamage) {
        this.expectedDamage = expectedDamage;
    }

    public Float getExpectedWinRate() {
        return expectedWinRate;
    }

    public void setExpectedWinRate(Float expectedWinRate) {
        this.expectedWinRate = expectedWinRate;
    }
}
