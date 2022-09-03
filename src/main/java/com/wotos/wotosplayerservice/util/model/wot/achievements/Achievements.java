package com.wotos.wotosplayerservice.util.model.wot.achievements;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class Achievements {

    private Map<String, Map<String, Integer>> achievements;
    private Map<String, Map<String, Integer>> series;
    private Map<String, Map<String, Integer>> frags;
    @JsonProperty("max_series")
    private Map<String, Map<String, Integer>> maxSeries;

    public Map<String, Map<String, Integer>> getAchievements() {
        return achievements;
    }

    public void setAchievements(Map<String, Map<String, Integer>> achievements) {
        this.achievements = achievements;
    }

    public Map<String, Map<String, Integer>> getSeries() {
        return series;
    }

    public void setSeries(Map<String, Map<String, Integer>> series) {
        this.series = series;
    }

    public Map<String, Map<String, Integer>> getFrags() {
        return frags;
    }

    public void setFrags(Map<String, Map<String, Integer>> frags) {
        this.frags = frags;
    }

    public Map<String, Map<String, Integer>> getMaxSeries() {
        return maxSeries;
    }

    public void setMaxSeries(Map<String, Map<String, Integer>> maxSeries) {
        this.maxSeries = maxSeries;
    }
}
