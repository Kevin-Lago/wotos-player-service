package com.wotos.wotosplayerservice.util.model.wot.achievements;

import com.fasterxml.jackson.annotation.JsonProperty;


public class WotPlayerAchievements {

    private final WotAchievements achievements;
    private final WotFragsAchievements frags;
    @JsonProperty("max_series")
    private final WotMaxSeriesAcheivements maxSeries;

    public WotPlayerAchievements(
            WotAchievements achievements, WotFragsAchievements frags, WotMaxSeriesAcheivements maxSeries
    ) {
        this.achievements = achievements;
        this.frags = frags;
        this.maxSeries = maxSeries;
    }

    public WotAchievements getAchievements() {
        return achievements;
    }

    public WotFragsAchievements getFrags() {
        return frags;
    }

    public WotMaxSeriesAcheivements getMaxSeries() {
        return maxSeries;
    }
}
