package com.maverick.migrate;

public class MongoDBToMySQLMigrate implements IMigrate {

//    @Override
//    public int migrateAuthors() {
//
//        int successfulCount = 0;
//        for (Author author : IMongoDB_DAO.selectAuthors()) {
//            if (IMySQL_DAO.insertAuthor(author)) {
//                IMongoDB_DAO.deleteAuthor(author);
//
//                successfulCount++;
//            }
//        }
//        return successfulCount;
//    }
//
//    @Override
//    public int migrateBooks() {
//        int successfulCount = 0;
//        for (Book book : IMongoDB_DAO.selectBooks()) {
//            try {
//                if (IMySQL_DAO.insertBook(book)) {
//                    IMongoDB_DAO.deleteBook(book);
//                    successfulCount++;
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return successfulCount;
//
//    }
//
//    @Override
//    public int migrateOrders(Librarian librarian) {
//        int successfulCount = 0;
//        for (Order order : IMongoDB_DAO.selectOrdersByLibrarian(librarian)) {
//            if (IMySQL_DAO.insertOrder(order)) {
//
//                IMongoDB_DAO.deleteOrder(order);
//
//                successfulCount++;
//            }
//        }
//        return successfulCount;
//    }
//
//    @Override
//    public int migrateStudents() {
//        int successfulCount = 0;
//        for (Student student : IMongoDB_DAO.selectStudents()) {
//            if (IMySQL_DAO.insertStudent(student)) {
//
//                IMongoDB_DAO.deleteStudent(student);
//                successfulCount++;
//            }
//        }
//        return successfulCount;
//    }
}
