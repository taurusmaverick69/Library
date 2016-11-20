package com.maverick.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Librarian extends EntityClass {

    @Column
    private String fullName;

    @Column
    private String password;

    @Transient
    private List<Order> orders = new ArrayList<>();

    public Librarian() {
    }

    public Librarian(int id, String fullName, String password, List<Order> orders) {
        this.orders = new ArrayList<>();
        this.id = id;
        this.fullName = fullName;
        this.password = password;
        this.orders = orders;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return fullName;
    }
}
