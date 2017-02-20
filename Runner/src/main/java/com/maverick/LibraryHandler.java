package com.maverick;

import com.maverick.domain.Group;
import com.maverick.domain.Order;
import com.maverick.domain.Student;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class LibraryHandler extends DefaultHandler {

    private boolean isOrder;
    private boolean isStudent;
    private boolean isFullName;
    private boolean isLibraryCard;
    private boolean isGroup;
    private boolean isName;
    private boolean isStartDate;
    private boolean isFinishDate;
    private boolean isStatus;

    private static final String ATTRIBUTE_ID = "id";

    private Order order;
    private Student student;
    private Group group;

    private SimpleDateFormat format;

    @Override
    public void startDocument() throws SAXException {
        format = new SimpleDateFormat("yyyy-MM-dd");
        order = new Order();
        student = new Student();
        group = new Group();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        isOrder = qName.equals("order");
        if (isOrder)
            order.setId(Integer.parseInt(attributes.getValue(ATTRIBUTE_ID)));

        isStudent = qName.equals("student");
        if (isStudent) {
            student.setId(Integer.parseInt(attributes.getValue(ATTRIBUTE_ID)));
        }
        isFullName = qName.equals("full_name");
        isLibraryCard = qName.equals("library_card");

        isGroup = qName.equals("group");
        if (isGroup)
            group.setId(Integer.parseInt(attributes.getValue(ATTRIBUTE_ID)));
        isName = qName.equals("name");
        isStartDate = qName.equals("start_date");
        isFinishDate = qName.equals("finish_date");
        isStatus = qName.equals("status");
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        try {
            String content = new String(ch, start, length);
            if (isFullName)
                student.setFullName(content);
            if (isLibraryCard)
                student.setLibraryCard(content);
            if (isName)
                group.setName(content);
            if (isStartDate)
                order.setStartDate(format.parse(content));
            if (isFinishDate)
                order.setFinishDate(format.parse(content));
            if (isStatus)
                order.setStatus(content);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("group"))
            student.setGroup(group);
        if (qName.equals("student"))
            order.setStudent(student);
        setAllToFalse();
    }

    @Override
    public void endDocument() throws SAXException {
        System.err.println(order);
    }

    private void setAllToFalse() {
        isOrder = isStudent = isFullName = isLibraryCard = isGroup = isName = isStartDate = isFinishDate = isStatus = false;
    }
}