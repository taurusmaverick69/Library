package com.maverick.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
//@NamedQuery(name = "Author.findAll", query = "select a.fullName from Author a")
public class Author extends EntityClass {

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "years_of_life")
    private String yearsOfLife;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getYearsOfLife() {
        return yearsOfLife;
    }

    public void setYearsOfLife(String yearsOfLife) {
        this.yearsOfLife = yearsOfLife;
    }

    @Override
    public String toString() {
        return fullName;
    }
}