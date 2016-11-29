package com.maverick.oldDAO.dbmsentitydao.mongodb;

import com.maverick.oldDAO.dbmsdaofactory.MongoDBDAOFactory;
import com.maverick.oldDAO.entitydao.AuthorDAO;
import com.maverick.domain.Author;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MongoDBAuthorDAO implements AuthorDAO {

    private MongoCollection<Document> authorCollection = MongoDBDAOFactory.createConnection().getCollection("author");


    @Override
    public boolean insertAuthor(Author author) {
        authorCollection.insertOne(MongoDBDAOFactory.toDocument(author));
        return true;
    }

    @Override
    public boolean deleteAuthor(Author author) {
        try {
            authorCollection.deleteOne(new Document("_id", author.get_id()));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Author> selectAuthors() {
        List<Author> authors = new ArrayList<>();
        for (Document document : authorCollection.find()) {
            authors.add((Author) MongoDBDAOFactory.fromDocument(document, "Author"));
        }
        return authors;
    }

    @Override
    public boolean updateAuthor(Author author) {
        return false;
    }
}
