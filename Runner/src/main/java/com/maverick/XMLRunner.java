package com.maverick;

import com.maverick.domain.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
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

public class XMLRunner {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, ParseException, JAXBException {

        File file = new File("xsd_xml", "library.xml");
        validate(file);
        //  Order order = parseDOM(file);


        //  System.err.println(order);
        //  SAXParserFactory.newInstance().newSAXParser().parse(file, new LibraryHandler());
        parseJaxb(file);
    }

    private static void validate(File file) throws SAXException, IOException {
        Source xmlFile = new StreamSource(file);
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new File("xsd_xml", "library.xsd"));
        Validator validator = schema.newValidator();
        try {
            validator.validate(xmlFile);
            System.out.println(xmlFile.getSystemId() + " is valid");
        } catch (SAXException e) {
            System.out.println(xmlFile.getSystemId() + " is NOT valid reason:" + e);
        }
    }

    private static Order parseDOM(File file) throws IOException, SAXException, ParserConfigurationException, ParseException {

        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);

        Element rootElement = doc.getDocumentElement();
        rootElement.normalize();

        Order order = new Order();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        List<Element> libraryElements = filterElementNodes(rootElement.getChildNodes());
        libraryElements.forEach(libraryElement -> {

            order.setId(Integer.parseInt(libraryElement.getAttribute("id")));
            List<Element> orderElements = filterElementNodes(libraryElement.getChildNodes());
            orderElements.forEach(orderElement -> {
                try {
                    switch (orderElement.getTagName()) {
                        case "student":
                            Student student = new Student();
                            student.setId(Integer.parseInt(orderElement.getAttribute("id")));
                            List<Element> studentElements = filterElementNodes(orderElement.getChildNodes());
                            studentElements.forEach(studentElement -> {
                                switch (studentElement.getTagName()) {
                                    case "full_name":
                                        student.setFullName(studentElement.getTextContent());
                                        break;
                                    case "library_card":
                                        student.setLibraryCard(studentElement.getTextContent());
                                        break;
                                    case "group":
                                        Group group = new Group();
                                        group.setId(Integer.parseInt(studentElement.getAttribute("id")));
                                        List<Element> groupElements = filterElementNodes(studentElement.getChildNodes());
                                        groupElements.forEach(groupElement -> {
                                            switch (groupElement.getTagName()) {
                                                case "name":
                                                    group.setName(groupElement.getTextContent());
                                                    break;
                                            }
                                        });
                                        student.setGroup(group);
                                        break;
                                }
                            });
                            order.setStudent(student);
                            break;
                        case "book":
                            Book book = new Book();
                            book.setId(Integer.parseInt(orderElement.getAttribute("id")));
                            List<Element> bookElements = filterElementNodes(orderElement.getChildNodes());
                            bookElements.forEach(bookElement -> {
                                switch (bookElement.getTagName()) {
                                    case "author":
                                        Author author = new Author();
                                        author.setId(Integer.parseInt(bookElement.getAttribute("id")));
                                        List<Element> authorElements = filterElementNodes(bookElement.getChildNodes());
                                        authorElements.forEach(authorElement -> {
                                            switch (authorElement.getTagName()) {
                                                case "full_name":
                                                    author.setFullName(authorElement.getTextContent());
                                                    break;
                                                case "years_of_life":
                                                    author.setYearsOfLife(authorElement.getTextContent());
                                                    break;
                                            }
                                        });
                                        book.setAuthor(author);
                                        break;
                                    case "title":
                                        book.setTitle(bookElement.getTextContent());
                                        break;
                                    case "publishing_year":
                                        book.setPublishingYear(Integer.parseInt(bookElement.getTextContent()));
                                        break;
                                    case "genre":
                                        Genre genre = new Genre();
                                        genre.setId(Integer.parseInt(bookElement.getAttribute("id")));
                                        List<Element> genreElements = filterElementNodes(bookElement.getChildNodes());
                                        genreElements.forEach(genreElement -> {
                                            switch (genreElement.getTagName()) {
                                                case "name":
                                                    genre.setName(genreElement.getTextContent());
                                            }
                                        });
                                        book.setGenre(genre);
                                        break;
                                    case "publisher":
                                        Publisher publisher = new Publisher();
                                        publisher.setId(Integer.parseInt(bookElement.getAttribute("id")));
                                        List<Element> publisherElements = filterElementNodes(bookElement.getChildNodes());
                                        publisherElements.forEach(publisherElement -> {
                                            switch (publisherElement.getTagName()) {
                                                case "name":
                                                    publisher.setName(publisherElement.getTextContent());
                                            }
                                        });
                                        book.setPublisher(publisher);
                                        break;
                                    case "amount":
                                        book.setAmount(Integer.parseInt(bookElement.getTextContent()));
                                        break;
                                }
                            });
                            order.setBook(book);
                            break;
                        case "librarian":
                            Librarian librarian = new Librarian();
                            librarian.setId(Integer.parseInt(orderElement.getAttribute("id")));
                            List<Element> librarianElements = filterElementNodes(orderElement.getChildNodes());
                            librarianElements.forEach(librarianElement -> {
                                switch (librarianElement.getTagName()) {
                                    case "full_name":
                                        librarian.setFullName(librarianElement.getTextContent());
                                        break;
                                    case "password":
                                        librarian.setPassword(librarianElement.getTextContent());
                                        break;
                                }
                            });
                            order.setLibrarian(librarian);
                            break;
                        case "start_date":
                            order.setStartDate(format.parse(orderElement.getTextContent()));
                            break;
                        case "finish_date":
                            order.setFinishDate(format.parse(orderElement.getTextContent()));
                            break;
                        case "status":
                            order.setStatus(orderElement.getTextContent());
                            break;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            });
        });
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

    private static void parseJaxb(File file) throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(Library.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Library library = (Library) unmarshaller.unmarshal(file);
        System.out.println(library);


//        Order order = new Order();
//        order.setId(1);
//        order.setStatus("non-returned");
//
//        Marshaller marshaller = jaxbContext.createMarshaller();
//        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//        marshaller.marshal(order, System.out);
    }
}