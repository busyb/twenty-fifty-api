package com.example.sustainability.api.data;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "item")
public class Item {

    public Item() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "workers_id")
    private Long userId;
    @Column(name = "description")
    private String description;

    @Column(name = "itemName")
    private String itemName;
    @Column(name = "type")
    private String type;
    @Column(name = "exchanged")
    private Boolean isExchanged = Boolean.FALSE;
    @Lob
    @Column(name = "image")
    private byte[] image;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "worker_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private User user;


    public Item(Long userId, String description, String itemName, String type) {
        this.userId = userId;
        this.description = description;
        this.itemName = itemName;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public Boolean getExchanged() {
        return isExchanged;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
