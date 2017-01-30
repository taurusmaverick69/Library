package com.maverick.domain;

import org.bson.types.ObjectId;

import javax.persistence.*;

@MappedSuperclass
public abstract class EntityClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    int id;

    @Transient
    private ObjectId _id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }
}
