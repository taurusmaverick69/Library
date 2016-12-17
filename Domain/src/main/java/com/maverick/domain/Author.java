package com.maverick.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "AUTHOR")
//@NamedQuery(name = "Author.findAll", query = "select a.fullName from Author a")
public class Author extends EntityClass {

    @Column(name = "full_name")
    private String fullName;

    @Column
    private String yearsOfLife;

    public Author() {
    }

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
        return "Author{" +
                "fullName='" + fullName + '\'' +
                ", yearsOfLife='" + yearsOfLife + '\'' +
                '}';
    }
}