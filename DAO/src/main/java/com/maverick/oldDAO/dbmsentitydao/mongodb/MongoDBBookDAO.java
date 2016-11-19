package com.maverick.oldDAO.dbmsentitydao.mongodb;

import com.maverick.domain.Book;
import com.maverick.oldDAO.entitydao.BookDAO;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.maverick.oldDAO.dbmsdaofactory.MongoDBDAOFactory.*;

public class MongoDBBookDAO implements BookDAO {

    private MongoCollection<Document> bookCollection = createConnection().getCollection("book");

    @Override
    public boolean insertBook(Book book) {
        try {
            bookCollection.insertOne(toDocument(book));
        } catch (Exception e) {

            System.out.println("Lost Connection...Retrying");

            for (int i = 1; i < 4; i++) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                System.out.println("Reconnect" + i);
                try {
                    bookCollection.insertOne(toDocument(book));
                    break;
                } catch (Exception e1) {
                    System.out.println("Reconnect" + i + "failed");
                }
            }
        }
        return true;
    }

    @Override
    public boolean deleteBook(Book book) {
        try {
            bookCollection.deleteOne(new Document("_id", book.get_id()));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Book> selectBooks() {
        List<Book> bookList = new ArrayList<>();

        for (Document document : bookCollection.find())
            bookList.add((Book) fromDocument(document, "Book"));
        return bookList;
    }

    @Override
    public boolean updateBook(Book book) {
        bookCollection.updateOne(new Document("_id", book.get_id()), new Document("$set", toDocument(book)));
        return true;
    }

    @Override
    public List<Book> searchBook(Book book) {
//
        List books = new ArrayList<>();
//        MongoCursor<Document> iterator = bookCollection.find(new Document(type, Pattern.compile(request, Pattern.CASE_INSENSITIVE))).iterator();
//        iterator = bookCollection.find(new Document(type, java.util.regex.Pattern.compile(request, Pattern.CASE_INSENSITIVE))).iterator();
//
//        while (iterator.hasNext()) {
//            Document document = iterator.next();
//            books.add(fromDocument(document, "Book"));
//        }
//
        return books;
    }

    @Override
    public int selectAmount(Book book) {
        return 0;
    }

    @Override
    public boolean isOrdered(Book book) {
        return false;
    }
}
