package com.wotos.wotosplayerservice.jsonao;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Id;

public class ExpectedStatisticsJSONAO {

    private Integer IDNum;
    private Float expDef;
    private Float expFrag;
    private Float expSpot;
    private Float expDamage;
    private Float expWinRate;

    public Integer getIDNum() {
        return IDNum;
    }

    public void setIDNum(Integer IDNum) {
        this.IDNum = IDNum;
    }

    public Float getExpDef() {
        return expDef;
    }

    public void setExpDef(Float expDef) {
        this.expDef = expDef;
    }

    public Float getExpFrag() {
        return expFrag;
    }

    public void setExpFrag(Float expFrag) {
        this.expFrag = expFrag;
    }

    public Float getExpSpot() {
        return expSpot;
    }

    public void setExpSpot(Float expSpot) {
        this.expSpot = expSpot;
    }

    public Float getExpDamage() {
        return expDamage;
    }

    public void setExpDamage(Float expDamage) {
        this.expDamage = expDamage;
    }

    public Float getExpWinRate() {
        return expWinRate;
    }

    public void setExpWinRate(Float expWinRate) {
        this.expWinRate = expWinRate;
    }
}
