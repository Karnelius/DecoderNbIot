package com.company;

import java.io.IOException;
import java.net.*;

public class UdpClient {
    private DatagramSocket socket;
    private InetAddress adress;

    private byte[] buf;

    public void EchoClient() throws SocketException, UnknownHostException {
        socket = new DatagramSocket();
        adress = InetAddress.getByName("localhost");
    }

    public String sendEcho(String msg) throws IOException {
        buf = msg.getBytes();
        DatagramPacket packet = new DatagramPacket(buf, buf.length, adress, 4445);
        socket.send(packet);
        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        String recieved = new String(packet.getData(), 0, packet.getLength());
        System.out.println(recieved);
        return recieved;
    }
    public void close(){
        socket.close();

    }
}
