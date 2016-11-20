package com.maverick.domain;

import org.bson.types.ObjectId;

import javax.persistence.*;

@Entity
public class Book extends EntityClass {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Author_id")
    private Author author = new Author();

    @Column
    private String title;

    @Column
    private Integer publishingYear;

    @ManyToOne
    @JoinColumn(name = "Genre_id")
    private Genre genre = new Genre();

    @ManyToOne
    @JoinColumn(name = "Publisher_id")
    private Publisher publisher = new Publisher();

    @Column
    private int amount;

    public Book() {
    }

    public Book(int id, Author author, String title, int publishingYear, Genre genre, Publisher publisher, int amount) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.publishingYear = publishingYear;
        this.genre = genre;
        this.publisher = publisher;
        this.amount = amount;
    }

    public Book(ObjectId id, Author author, String title, int publishingYear, Genre genre, Publisher publisher, int amount) {
        this._id = id;
        this.author = author;
        this.title = title;
        this.publishingYear = publishingYear;
        this.genre = genre;
        this.publisher = publisher;
        this.amount = amount;
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    @Override
    public ObjectId get_id() {
        return super.get_id();
    }

    @Override
    public void set_id(ObjectId _id) {
        super.set_id(_id);
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPublishingYear() {
        return publishingYear;
    }

    public void setPublishingYear(Integer publishingYear) {
        this.publishingYear = publishingYear;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
