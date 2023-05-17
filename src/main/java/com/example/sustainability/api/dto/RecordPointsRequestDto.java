package com.example.sustainability.api.dto;

import java.util.List;

public class RecordPointsRequestDto {

    private String email;

    private List<PointsDto> list;

    public RecordPointsRequestDto() {
    }

    public RecordPointsRequestDto(String email, List<PointsDto> list) {
        this.email = email;
        this.list = list;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<PointsDto> getList() {
        return list;
    }

    public void setList(List<PointsDto> list) {
        this.list = list;
    }
}
