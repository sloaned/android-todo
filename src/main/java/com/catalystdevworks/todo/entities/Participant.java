package com.catalystdevworks.todo.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by dsloane on 3/15/2016.
 */
@Entity
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @Column(name = "name")
    private String participantName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getParticipantName() {
        return participantName;
    }

    public void setParticipantName(String name) {
        participantName = name;
    }



}
