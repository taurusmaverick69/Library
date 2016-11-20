package com.maverick.domain;

import org.bson.types.ObjectId;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Author extends EntityClass {

    @Column
    private String fullName;

    @Column
    private String yearsOfLife;

    public Author() {
    }

    public Author(int id, String fullName, String yearsOfLife) {
        this.id = id;
        this.fullName = fullName;
        this.yearsOfLife = yearsOfLife;
    }

    public Author(ObjectId _id, String fullName, String yearsOfLife) {
        this._id = _id;
        this.fullName = fullName;
        this.yearsOfLife = yearsOfLife;
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
