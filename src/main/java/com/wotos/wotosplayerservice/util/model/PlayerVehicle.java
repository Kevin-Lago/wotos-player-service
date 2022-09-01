package com.wotos.wotosplayerservice.util.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class PlayerVehicle {

    Map<String, Integer> statistics;
    @JsonProperty("marks_of_mastery")
    Integer MarksOfMastery;
    @JsonProperty("tank_id")
    Integer tankId;

    public Map<String, Integer> getStatistics() {
        return statistics;
    }

    public void setStatistics(Map<String, Integer> statistics) {
        this.statistics = statistics;
    }

    public Integer getMarksOfMastery() {
        return MarksOfMastery;
    }

    public void setMarksOfMastery(Integer marksOfMastery) {
        MarksOfMastery = marksOfMastery;
    }

    public Integer getTankId() {
        return tankId;
    }

    public void setTankId(Integer tankId) {
        this.tankId = tankId;
    }
}
