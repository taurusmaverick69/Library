package com.maverick.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table(name = "`group`")
@XmlType(propOrder = {"name"}, name = "group")
public class Group extends EntityClass {

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
        return "Group{" +
                "id='" + id + '\'' +
                "name='" + name + '\'' +
                '}';
    }
}
