package com.maverick.domain;

import org.bson.types.ObjectId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Student extends EntityClass {

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "libraryCard")
    private String libraryCard;

    @ManyToOne
    @JoinColumn(name = "Group_id")
    private Group group;

    public Student() {
    }

    public Student(int id, String fullName, String libraryCard, Group group) {
        this.id = id;
        this.fullName = fullName;
        this.libraryCard = libraryCard;
        this.group = group;
    }

    public Student(ObjectId _id, String fullName, String libraryCard, Group group) {
        this._id = _id;
        this.fullName = fullName;
        this.libraryCard = libraryCard;
        this.group = group;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLibraryCard() {
        return libraryCard;
    }

    public void setLibraryCard(String libraryCard) {
        this.libraryCard = libraryCard;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

}
