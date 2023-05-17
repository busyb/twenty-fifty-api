package com.example.sustainability.api.data;

import javax.persistence.*;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "user_task")
public class UserTask {

    public UserTask() {
    }

    public UserTask(User user, Instant registerDate, Integer pointTotal, ActionType actionType, LocalDate entryDate) {
        this.user = user;
        this.registerDate = registerDate;
        this.points = pointTotal;
        this.actionType = actionType;
        this.entryDate = entryDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "registered_date")
    private Instant registerDate;
    @Column(name = "points")
    private Integer points;

    @Column(name = "action_type")
    private ActionType actionType;

    @Column(name = "local_date", columnDefinition = "DATE")
    private LocalDate entryDate;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    public Instant getRegisterDate() {
        return registerDate;
    }

    public Integer getPoints() {
        return points;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
