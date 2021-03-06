package com.company;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UdpServer {
    private DatagramSocket socket;
    private boolean running;
    private byte[] buf = new byte[256];

    public void EchoServer() throws SocketException {
        socket = new DatagramSocket(4445);
    }

    public void run() throws IOException {
        running = true;

        while (running) {
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);

            InetAddress adress = packet.getAddress();
            int port = packet.getPort();
            packet = new DatagramPacket(buf, buf.length, adress, port);

            String received = new String(packet.getData(), 0, packet.getLength());

            if (received.equals("end")) {
                running = false;
                continue;
            }
            socket.send(packet);
        }
        socket.close();
    }
}
