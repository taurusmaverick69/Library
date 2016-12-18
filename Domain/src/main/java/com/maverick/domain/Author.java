package com.maverick.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        return fullName.equals(author.fullName);

    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

    @Override
    public String toString() {
        return fullName;
    }
}