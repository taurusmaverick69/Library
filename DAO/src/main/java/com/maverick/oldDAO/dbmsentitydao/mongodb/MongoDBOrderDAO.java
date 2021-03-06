package com.maverick.oldDAO.dbmsentitydao.mongodb;

import com.maverick.domain.Order;
import com.maverick.oldDAO.dbmsdaofactory.MongoDBDAOFactory;
import com.maverick.oldDAO.entitydao.OrderDAO;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.List;

public class MongoDBOrderDAO implements OrderDAO {

    private MongoCollection<Document> orderCollection = MongoDBDAOFactory.createConnection().getCollection("order");


    @Override
    public boolean save(Order order) {
        orderCollection.insertOne(MongoDBDAOFactory.toDocument(order));
        return true;
    }

    @Override
    public void delete(int id) {
        try {
            orderCollection.deleteOne(new Document("_id", id));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public List<Order> findByLibrarianId(int librarianId) {
        return null;
    }

    @Override
    public boolean update(Order order) {
        return false;
    }
}
