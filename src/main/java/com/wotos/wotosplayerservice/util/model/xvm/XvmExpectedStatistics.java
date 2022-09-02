package com.wotos.wotosplayerservice.util.model.xvm;

import com.fasterxml.jackson.annotation.JsonProperty;

public class XvmExpectedStatistics {

    @JsonProperty("IDNum")
    private final Integer tankId;
    @JsonProperty("expDef")
    private final Float expectedDefense;
    @JsonProperty("expFrag")
    private final Float expectedFrag;
    @JsonProperty("expSpot")
    private final Float expectedSpot;
    @JsonProperty("expDamage")
    private final Float expectedDamage;
    @JsonProperty("expWinRate")
    private final Float expectedWinRate;

    public XvmExpectedStatistics(Integer tankId, Float expectedDefense, Float expectedFrag, Float expectedSpot, Float expectedDamage, Float expectedWinRate) {
        this.tankId = tankId;
        this.expectedDefense = expectedDefense;
        this.expectedFrag = expectedFrag;
        this.expectedSpot = expectedSpot;
        this.expectedDamage = expectedDamage;
        this.expectedWinRate = expectedWinRate;
    }

    public Integer getTankId() {
        return tankId;
    }

    public Float getExpectedDefense() {
        return expectedDefense;
    }

    public Float getExpectedFrag() {
        return expectedFrag;
    }

    public Float getExpectedSpot() {
        return expectedSpot;
    }

    public Float getExpectedDamage() {
        return expectedDamage;
    }

    public Float getExpectedWinRate() {
        return expectedWinRate;
    }
}
