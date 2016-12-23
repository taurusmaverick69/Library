package com.maverick.oldDAO.dbmsentitydao.mongodb;

import com.maverick.domain.Author;
import com.maverick.oldDAO.dbmsdaofactory.MongoDBDAOFactory;
import com.maverick.oldDAO.entitydao.AuthorDAO;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MongoDBAuthorDAO implements AuthorDAO {

    private MongoCollection<Document> authorCollection = MongoDBDAOFactory.createConnection().getCollection("author");

    @Override
    public boolean save(Author author) {
        authorCollection.insertOne(MongoDBDAOFactory.toDocument(author));
        return true;
    }

    @Override
    public void delete(int id) {
        try {
            authorCollection.deleteOne(new Document("_id", id));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Author> findAll() {
        List<Author> authors = new ArrayList<>();
        for (Document document : authorCollection.find()) {
            authors.add((Author) MongoDBDAOFactory.fromDocument(document, "Author"));
        }
        return authors;
    }

    @Override
    public Author findById(int id) {
        return null;
    }

    @Override
    public boolean update(Author author) {
        return false;
    }
}
