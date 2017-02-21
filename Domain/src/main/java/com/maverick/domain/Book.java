package com.maverick.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlType;

@Entity
@XmlType(propOrder = {"author", "title", "publishingYear", "genre", "publisher", "amount"}, name = "book")
public class Book extends EntityClass {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Author_id")
    private Author author = new Author();

    @Column
    private String title;

    @Column(name = "publishing_year")
    private Integer publishingYear;

    @ManyToOne
    @JoinColumn(name = "Genre_id")
    private Genre genre = new Genre();

    @ManyToOne
    @JoinColumn(name = "Publisher_id")
    private Publisher publisher = new Publisher();

    @Column
    private int amount;

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

    @Override
    public String toString() {
        return title;
    }
}