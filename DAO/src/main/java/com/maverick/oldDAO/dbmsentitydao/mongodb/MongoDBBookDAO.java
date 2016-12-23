package com.maverick.oldDAO.dbmsentitydao.mongodb;

import com.maverick.domain.Book;
import com.maverick.oldDAO.dbmsdaofactory.MongoDBDAOFactory;
import com.maverick.oldDAO.entitydao.BookDAO;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MongoDBBookDAO implements BookDAO {

    private MongoCollection<Document> bookCollection = MongoDBDAOFactory.createConnection().getCollection("book");

    @Override
    public boolean save(Book book) {
        try {
            bookCollection.insertOne(MongoDBDAOFactory.toDocument(book));
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
                    bookCollection.insertOne(MongoDBDAOFactory.toDocument(book));
                    break;
                } catch (Exception e1) {
                    System.out.println("Reconnect" + i + "failed");
                }
            }
        }
        return true;
    }

    @Override
    public void delete(int id) {

            bookCollection.deleteOne(new Document("_id", id));

    }

    @Override
    public List<Book> findAll() {
        List<Book> bookList = new ArrayList<>();

        for (Document document : bookCollection.find())
            bookList.add((Book) MongoDBDAOFactory.fromDocument(document, "Book"));
        return bookList;
    }

    @Override
    public Book findById(int id) {
        return null;
    }

    @Override
    public boolean update(Book book) {
        bookCollection.updateOne(new Document("_id", book.get_id()), new Document("$set", MongoDBDAOFactory.toDocument(book)));
        return true;
    }

    @Override
    public int selectAmount(Book book) {
        return 0;
    }
}
