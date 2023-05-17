package com.example.sustainability.api.dto;


public class ItemDto {
    private String userToken;
    private String description;
    private String itemName;
    private String type;
    private Boolean isExchanged;

    public ItemDto() {


    }

    public ItemDto(String userId, String description, String itemName, String type, Boolean isExchanged) {
        this.userToken = userId;
        this.description = description;
        this.itemName = itemName;
        this.type = type;
        this.isExchanged = isExchanged;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getExchanged() {
        return isExchanged;
    }

    public void setExchanged(Boolean exchanged) {
        isExchanged = exchanged;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
