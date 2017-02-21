package com.maverick.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@Entity
@XmlType(propOrder = {"fullName", "libraryCard", "group"}, name = "student")
public class Student extends EntityClass {

    @Column(name = "full_name")
    private String fullName;

    @OneToMany(mappedBy = "student", cascade = {CascadeType.REMOVE, CascadeType.REFRESH})
    private List<Order> orders;

    @Column(name = "library_card")
    private String libraryCard;

    @ManyToOne
    @JoinColumn(name = "Group_id")
    private Group group;

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

    @Override
    public String toString() {
        return "Student{" +
                "fullName='" + fullName + '\'' +
                ", orders=" + orders +
                ", libraryCard='" + libraryCard + '\'' +
                ", group=" + group +
                '}';
    }
}