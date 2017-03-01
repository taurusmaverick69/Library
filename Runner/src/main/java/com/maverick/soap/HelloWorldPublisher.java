package com.maverick.soap;

import javax.xml.ws.Endpoint;

/**
 * Created by Vladislav on 28.02.2017.
 */
public class HelloWorldPublisher {

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/ws/hello", new HelloWorldImpl());
    }

}
