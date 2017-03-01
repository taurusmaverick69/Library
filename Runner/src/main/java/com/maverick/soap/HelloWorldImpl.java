package com.maverick.soap;

import javax.jws.WebService;

/**
 * Created by Vladislav on 28.02.2017.
 */

@WebService(endpointInterface = "com.maverick.soap.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

    @Override
    public String getHelloWorldAsString(String name) {
        return "Hello World JAX-WS " + name;
    }
}