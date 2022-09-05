package com.wotos.wotosplayerservice.util.model.wot.achievements;

public class WotMaxSeriesAcheivements {

    private final Integer armorPiercer;
    private final Integer aimer;
    private final Integer titleSniper;
    private final Integer deathTrack;
    private final Integer invincible;
    private final Integer victoryMarch;
    private final Integer EFC2016;
    private final Integer diehard;
    private final Integer WFC2014;
    private final Integer tacticalBreakthrough;
    private final Integer handOfDeath;

    public WotMaxSeriesAcheivements(
            Integer armorPiercer, Integer aimer, Integer titleSniper, Integer deathTrack,
            Integer invincible, Integer victoryMarch, Integer EFC2016, Integer diehard,
            Integer WFC2014, Integer tacticalBreakthrough, Integer handOfDeath
    ) {
        this.armorPiercer = armorPiercer;
        this.aimer = aimer;
        this.titleSniper = titleSniper;
        this.deathTrack = deathTrack;
        this.invincible = invincible;
        this.victoryMarch = victoryMarch;
        this.EFC2016 = EFC2016;
        this.diehard = diehard;
        this.WFC2014 = WFC2014;
        this.tacticalBreakthrough = tacticalBreakthrough;
        this.handOfDeath = handOfDeath;
    }

    public Integer getArmorPiercer() {
        return armorPiercer;
    }

    public Integer getAimer() {
        return aimer;
    }

    public Integer getTitleSniper() {
        return titleSniper;
    }

    public Integer getDeathTrack() {
        return deathTrack;
    }

    public Integer getInvincible() {
        return invincible;
    }

    public Integer getVictoryMarch() {
        return victoryMarch;
    }

    public Integer getEFC2016() {
        return EFC2016;
    }

    public Integer getDiehard() {
        return diehard;
    }

    public Integer getWFC2014() {
        return WFC2014;
    }

    public Integer getTacticalBreakthrough() {
        return tacticalBreakthrough;
    }

    public Integer getHandOfDeath() {
        return handOfDeath;
    }
}
