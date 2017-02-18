package com.maverick;

import com.maverick.domain.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sednor-7 on 2/15/17.
 */
public class XMLRunner {


    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, ParseException {

        File file = new File("xsd_xml", "order.xml");

        File schemaFile = new File("xsd_xml/xsd", "order.xsd");
        Source xmlFile = new StreamSource(file);
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(schemaFile);
        Validator validator = schema.newValidator();
        try {
            validator.validate(xmlFile);
            System.out.println(xmlFile.getSystemId() + " is valid");
        } catch (SAXException e) {
            System.out.println(xmlFile.getSystemId() + " is NOT valid reason:" + e);
        }

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(file);

        doc.getDocumentElement().normalize();

        Order order = parseDOM(doc);
        System.out.println(order);
    }

    private static Order parseDOM(Document doc) throws ParseException {

        Order order = new Order();

        String id = doc.getElementsByTagName("id").item(0).getTextContent();
        NodeList studentChildNodes = doc.getElementsByTagName("student").item(0).getChildNodes();
        NodeList bookChildNodes = doc.getElementsByTagName("book").item(0).getChildNodes();
        NodeList librarianChildNodes = doc.getElementsByTagName("librarian").item(0).getChildNodes();
        String startDate = doc.getElementsByTagName("start_date").item(0).getTextContent();
        String finishDate = doc.getElementsByTagName("finish_date").item(0).getTextContent();
        String status = doc.getElementsByTagName("status").item(0).getTextContent();

        List<Element> studentElements = filterElementNodes(studentChildNodes);
        String studentId = studentElements.get(0).getTextContent();
        String fullName = studentElements.get(1).getTextContent();
        String libraryCard = studentElements.get(2).getTextContent();

        List<Element> groupElements = filterElementNodes(studentElements.get(3).getChildNodes());
        String groupId = groupElements.get(0).getTextContent();
        String groupName = groupElements.get(1).getTextContent();

        Student student = new Student();
        student.setId(Integer.parseInt(studentId));
        student.setFullName(fullName);
        student.setLibraryCard(libraryCard);
        Group group = new Group();
        group.setId(Integer.parseInt(groupId));
        group.setName(groupName);
        student.setGroup(group);

        List<Element> bookElements = filterElementNodes(bookChildNodes);
        String bookId = bookElements.get(0).getTextContent();
        List<Element> authorElements = filterElementNodes(bookElements.get(1).getChildNodes());
        String authorId = authorElements.get(0).getTextContent();
        String authorFullName = authorElements.get(1).getTextContent();
        String authorYearsOfLife = authorElements.get(2).getTextContent();

        String title = bookElements.get(2).getTextContent();
        String publishingYear = bookElements.get(3).getTextContent();
        List<Element> genreElements = filterElementNodes(bookElements.get(4).getChildNodes());
        String genreId = genreElements.get(0).getTextContent();
        String genreName = genreElements.get(1).getTextContent();

        List<Element> publisherElements = filterElementNodes(bookElements.get(5).getChildNodes());
        String publisherId = publisherElements.get(0).getTextContent();
        String publisherName = publisherElements.get(1).getTextContent();
        String amount = bookElements.get(6).getTextContent();

        Book book = new Book();
        book.setId(Integer.parseInt(bookId));
        Author author = new Author();
        author.setId(Integer.parseInt(authorId));
        author.setFullName(authorFullName);
        author.setYearsOfLife(authorYearsOfLife);
        book.setAuthor(author);
        book.setTitle(title);
        book.setPublishingYear(Integer.parseInt(publishingYear));
        Genre genre = new Genre();
        genre.setId(Integer.parseInt(genreId));
        genre.setName(genreName);
        book.setGenre(genre);
        Publisher publisher = new Publisher();
        publisher.setId(Integer.parseInt(publisherId));
        publisher.setName(publisherName);
        book.setPublisher(publisher);
        book.setAmount(Integer.parseInt(amount));

        List<Element> librarianElements = filterElementNodes(librarianChildNodes);


        String libId = librarianElements.get(0).getTextContent();
        String libName = librarianElements.get(1).getTextContent();
        String libPass = librarianElements.get(2).getTextContent();


        Librarian librarian = new Librarian();
        librarian.setId(Integer.parseInt(libId));
        librarian.setFullName(libName);
        librarian.setPassword(libPass);

        order.setId(Integer.parseInt(id));
        order.setStudent(student);
        order.setBook(book);
        order.setLibrarian(librarian);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        order.setStartDate(format.parse(startDate));
        order.setFinishDate(format.parse(finishDate));
        order.setStatus(status);

        return order;
    }

    private static List<Element> filterElementNodes(NodeList nodeList) {
        List<Element> elements = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++)
            if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                elements.add((Element) nodeList.item(i));
            }
        return elements;
    }
}