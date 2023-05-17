package com.example.sustainability.api.dto;

public class PointsDto {
    private String actionType;
    private Integer pointTotal;

    public PointsDto() {
    }

    public PointsDto( String actionType, Integer pointTotal) {
        this.actionType = actionType;
        this.pointTotal = pointTotal;
    }


    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public Integer getPointTotal() {
        return pointTotal;
    }

    public void setPointTotal(Integer pointTotal) {
        this.pointTotal = pointTotal;
    }
}
