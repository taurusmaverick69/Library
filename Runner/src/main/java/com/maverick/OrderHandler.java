package com.maverick;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class OrderHandler extends DefaultHandler {

    boolean isStudent;
    boolean isBook;
    boolean isLibrarian;
    boolean isStartDate;
    boolean isFinishDate;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        isFinishDate = qName.equals("order");
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {


        if (isFinishDate) {
            String s = new String(ch, start, length);

            System.out.println(s);
        }


    }
}