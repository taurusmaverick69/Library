package com.maverick.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Librarian")
public class Librarian extends EntityClass {

    @Column(name = "full_name")
    private String fullName;

    @Column
    private String password;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "librarian")
    private List<Order> orders = new ArrayList<>();

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
