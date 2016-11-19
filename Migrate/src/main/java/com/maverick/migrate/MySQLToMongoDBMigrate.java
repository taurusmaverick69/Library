package com.maverick.migrate;

public class MySQLToMongoDBMigrate implements IMigrate {

//    @Override
//    public int migrateAuthors() {
//        int unsuccessfulCount = 0;
//
//        for (Author author : IMySQL_DAO.selectAuthors()) {
//            if (IMySQL_DAO.deleteAuthor(author)) {
//                unsuccessfulCount++;
//                continue;
//            }
//            IMongoDB_DAO.insertAuthor(author);
//        }
//        return unsuccessfulCount;
//    }
//
//    @Override
//    public int migrateBooks() {
//        int unsuccessfulCount = 0;
//
//        for (Book book : IMySQL_DAO.selectBooks()) {
//            if (IMySQL_DAO.deleteBook(book)) {
//                unsuccessfulCount++;
//                continue;
//            }
//            IMongoDB_DAO.insertBook(book);
//        }
//        return unsuccessfulCount;
//    }
//
//    @Override
//    public int migrateOrders(Librarian librarian) {
//        int unsuccessfulCount = 0;
//
//        for (Order order : IMySQL_DAO.selectOrdersByLibrarian(librarian)) {
//
//            if (IMySQL_DAO.deleteOrder(order)) {
//                unsuccessfulCount++;
//                continue;
//            }
//            IMongoDB_DAO.insertOrder(order);
//        }
//        return unsuccessfulCount;
//    }
//
//    @Override
//    public int migrateStudents() {
//        int unsuccessfulCount = 0;
//
//        for (Student student : IMySQL_DAO.selectStudents()) {
//            if(IMySQL_DAO.deleteStudent(student)){
//                unsuccessfulCount++;
//                continue;
//            }
//            IMongoDB_DAO.insertStudent(student);
//        }
//        return unsuccessfulCount;
//    }
}
