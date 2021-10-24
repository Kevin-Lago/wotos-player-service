package com.wotos.wotosplayerservice.jsonao;

public class TankStatisticsJSONAO {

    private Integer tank_id;
    private Boolean in_garage;
    private Integer frags;
    private Integer mark_of_mastery;
    private Integer max_frags;
    private Integer max_xp;
    private Integer account_id;
    private StatisticsByModeJSONAO statistics;

    public Integer getTank_id() {
        return tank_id;
    }

    public void setTank_id(Integer tank_id) {
        this.tank_id = tank_id;
    }

    public Boolean getIn_garage() {
        return in_garage;
    }

    public void setIn_garage(Boolean in_garage) {
        this.in_garage = in_garage;
    }

    public Integer getFrags() {
        return frags;
    }

    public void setFrags(Integer frags) {
        this.frags = frags;
    }

    public Integer getMark_of_mastery() {
        return mark_of_mastery;
    }

    public void setMark_of_mastery(Integer mark_of_mastery) {
        this.mark_of_mastery = mark_of_mastery;
    }

    public Integer getMax_frags() {
        return max_frags;
    }

    public void setMax_frags(Integer max_frags) {
        this.max_frags = max_frags;
    }

    public Integer getMax_xp() {
        return max_xp;
    }

    public void setMax_xp(Integer max_xp) {
        this.max_xp = max_xp;
    }

    public Integer getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Integer account_id) {
        this.account_id = account_id;
    }

    public StatisticsByModeJSONAO getStatistics() {
        return statistics;
    }

    public void setStatistics(StatisticsByModeJSONAO statistics) {
        this.statistics = statistics;
    }
}
