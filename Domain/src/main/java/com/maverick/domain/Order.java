package com.maverick.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "`order`")
public class Order extends EntityClass {

    @ManyToOne
    @JoinColumn(name = "Student_id")
    private Student student = new Student();

    @ManyToOne
    @JoinColumn(name = "Book_id")
    private Book book = new Book();

    @ManyToOne
    @JoinColumn(name = "Librarian_id")
    private Librarian librarian = new Librarian();

    @Column
    private Date startDate;

    @Column
    private Date finishDate;

    @Column
    private String status;

    public Order() {
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Librarian getLibrarian() {
        return librarian;
    }

    public void setLibrarian(Librarian librarian) {
        this.librarian = librarian;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
