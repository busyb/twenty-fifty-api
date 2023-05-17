package com.example.sustainability.api.dto;

public class ItemResponseDto {

    private String email;
    private Long id;
    private String firstName;
    private String lastName;
    private String description;
    private String itemName;
    private String type;
    private Boolean isExchanged;
    private String image;


    public ItemResponseDto(String email, long id, String firstName, String lastName, String description, String itemName, String type, Boolean isExchanged, String image) {
        this.email = email;
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.itemName = itemName;
        this.type = type.toUpperCase();
        this.isExchanged = isExchanged;
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
