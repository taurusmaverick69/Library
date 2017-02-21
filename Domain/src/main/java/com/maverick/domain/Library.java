package com.maverick.domain;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "library")
@XmlType(propOrder = {"order"})
public class Library {

    private Order order;

    public Library() {
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Library{" +
                "order=" + order +
                '}';
    }
}