package com.maverick.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Publisher extends EntityClass {

    @Column
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
