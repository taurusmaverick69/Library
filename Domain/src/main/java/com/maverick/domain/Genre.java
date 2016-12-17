package com.maverick.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Genre extends EntityClass {

    @Column
    private String name;

    public Genre() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
