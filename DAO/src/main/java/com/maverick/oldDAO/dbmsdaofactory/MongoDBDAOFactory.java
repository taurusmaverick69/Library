package com.maverick.oldDAO.dbmsdaofactory;

import com.maverick.domain.*;
import com.maverick.oldDAO.DAOFactory;
import com.maverick.oldDAO.dbmsentitydao.mongodb.*;
import com.maverick.oldDAO.entitydao.*;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Arrays;
import java.util.List;

public class MongoDBDAOFactory extends DAOFactory {

    private static final String DATABASE_NAME = "booksDB";
    private static MongoDatabase db;

    public static MongoDatabase createConnection() {

        List<ServerAddress> serverAddresses = Arrays.asList(
                new ServerAddress("localhost", 27001),
                new ServerAddress("localhost", 27002),
                new ServerAddress("localhost", 27003));
        if (db == null)
            db = new MongoClient().getDatabase(DATABASE_NAME);
        return db;
    }

    public static Document toDocument(EntityClass entityClass) {

        Document document = new Document();

        if (entityClass instanceof Book) {
            Book book = (Book) entityClass;
            document.put("author", book.getAuthor().getFullName());
            document.put("title", book.getTitle());
            document.put("publishingYear", book.getPublishingYear());
            document.put("genre", book.getGenre().getName());
            document.put("publisher", book.getPublisher().getName());
            document.put("amount", book.getAmount());
        }

        if (entityClass instanceof Order) {
            Order order = (Order) entityClass;
            document.put("student", order.getStudent().getFullName());
            document.put("book", order.getBook().getTitle());
            document.put("librarian", order.getLibrarian().getFullName());
            document.put("startDate", order.getStartDate());
            document.put("finishDate", order.getFinishDate());
            document.put("status", order.getStatus());
        }

        if (entityClass instanceof Student) {
            Student student = (Student) entityClass;
            document.put("group", student.getGroup().getName());
            document.put("fullName", student.getFullName());
            document.put("libraryCard", student.getLibraryCard());
        }

        if (entityClass instanceof Author) {
            Author author = (Author) entityClass;
            document.put("fullName", author.getFullName());
            document.put("yearsOfLife", author.getYearsOfLife());
        }

        return document;
    }

    public static EntityClass fromDocument(Document document, String typeEntity) {

        switch (typeEntity) {
            case "Book":
                Author author = new Author();
                author.setFullName(document.getString("author"));

                Genre genre = new Genre();
                genre.setName(document.getString("genre"));

                Publisher publisher = new Publisher();
                publisher.setName(document.getString("publisher"));

                return new Book(
                        document.getObjectId("_id"),
                        author,
                        document.getString("title"),
                        document.getInteger("publishingYear"),
                        genre,
                        publisher,
                        document.getInteger("amount")
                );


            case "Author":
                return new Author(
                        document.getObjectId("_id"),
                        document.getString("fullName"),
                        document.getString("yearsOfLife")
                );

            case "Order":


                Student student = new Student();
                student.setFullName(document.getString("student"));

                Book book = new Book();
                book.setTitle(document.getString("book"));

                return new Order(
                        document.getObjectId("_id"),
                        student,
                        book,
                        document.getDate("startDate"),
                        document.getDate("finishDate"),
                        document.getString("status"));


            case "Student":

                Group group = new Group();
                group.setName(document.getString("group"));

                return new Student(
                        document.getObjectId("_id"),
                        document.getString("fullName"),
                        document.getString("libraryCard"),
                        group);
        }
        return null;
    }

    @Override
    public AuthorDAO getAuthorDAO() {
        return new MongoDBAuthorDAO();
    }

    @Override
    public BookDAO getBookDAO() {
        return new MongoDBBookDAO();
    }

    @Override
    public GenreDAO getGenreDAO() {
        return new MongoDBGenreDAO();
    }

    @Override
    public GroupDAO getGroupDAO() {
        return new MongoDBGroupDAO();
    }

    @Override
    public LibrarianDAO getLibrarianDAO() {
        return new MongoDBLibrarianDAO();
    }

    @Override
    public OrderDAO getOrderDAO() {
        return new MongoDBOrderDAO();
    }

    @Override
    public PublisherDAO getPublisherDAO() {
        return new MongoDBPublisherDAO();
    }

    @Override
    public StudentDAO getStudentDAO() {
        return new MongoDBStudentDAO();
    }

}
