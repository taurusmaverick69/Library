package com.maverick.oldDAO.dbmsentitydao.mongodb;

import com.maverick.domain.Student;
import com.maverick.oldDAO.dbmsdaofactory.MongoDBDAOFactory;
import com.maverick.oldDAO.entitydao.StudentDAO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MongoDBStudentDAO implements StudentDAO {

    private MongoCollection<Document> studentCollection = MongoDBDAOFactory.createConnection().getCollection("student");


    @Override
    public boolean insertStudent(Student student) {
        studentCollection.insertOne(MongoDBDAOFactory.toDocument(student));
        return true;
    }

    @Override
    public boolean deleteStudent(Student student) {
        try {
            studentCollection.deleteOne(new Document("_id", student.get_id()));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Student> selectStudents() {
        List<Student> students = new ArrayList<>();
        MongoCursor<Document> iterator = studentCollection.find().iterator();
        while (iterator.hasNext()) {
            Document document = iterator.next();
            students.add((Student) MongoDBDAOFactory.fromDocument(document, "Student"));
        }
        return students;
    }


    @Override
    public boolean updateStudent(Student student) {
        return false;
    }
}
