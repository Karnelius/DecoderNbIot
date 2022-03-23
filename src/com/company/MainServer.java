package com.company;

import java.io.IOException;


// UDP Server Main.
public class MainServer {
    public static void main(String[] args) throws IOException {
        UdpServer server = new UdpServer();
        server.EchoServer();
        server.run();


    }
}

