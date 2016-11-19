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
    public boolean insertOrder(Order order) {
        orderCollection.insertOne(MongoDBDAOFactory.toDocument(order));
        return true;
    }

    @Override
    public boolean deleteOrder(Order order) {
        try {
            orderCollection.deleteOne(new Document("_id", order.get_id()));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Order> selectOrders() {
        return null;
    }

    @Override
    public boolean updateOrder(Order order) {
        return false;
    }

    @Override
    public List<Order> searchOrder(Order order) {
        return null;
    }
}
