package com.wotos.wotosplayerservice.util.model.wot.player;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class WotPlayerVehicle {

    private final Map<String, Integer> statistics;
    @JsonProperty("marks_of_mastery")
    private final Integer MarksOfMastery;
    @JsonProperty("tank_id")
    private final Integer tankId;

    public WotPlayerVehicle(Map<String, Integer> statistics, Integer marksOfMastery, Integer tankId) {
        this.statistics = statistics;
        MarksOfMastery = marksOfMastery;
        this.tankId = tankId;
    }

    public Map<String, Integer> getStatistics() {
        return statistics;
    }

    public Integer getMarksOfMastery() {
        return MarksOfMastery;
    }

    public Integer getTankId() {
        return tankId;
    }
}
