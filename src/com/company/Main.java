package com.company;

import java.io.IOException;

public class Main extends NbDecoder {

    public static void main(String[] args) throws IOException {



        NbDecoder nb = new NbDecoder();
        nb.decoder("02040004A30B00F60800F802202203101144220003000220");
        //nb.printPayload();

        NbPayloadGenerator nn = new NbPayloadGenerator();
        //nn.printGenerator();
        //nn.generatedHexStr();



        // UDP Client
        UdpClient client = new UdpClient();
        client.EchoClient();
        client.sendEcho(nn.generatedHexStr());
        client.close();


        //String str-old-v2-6 = "02060004A30B00F6B5690800F80003000220060305E661";

        /*
        System.out.println("\n");
        System.out.println("---LORA---- TYPE 2 / 6 -----LORA----");
        System.out.println("Received at: " + java.time.LocalDateTime.now());
        System.out.println("Device payload type: " + ans[0]);
        System.out.println("Device type variant: "+ans[1]);
        System.out.println("Device ID: " + sub);
        System.out.println("Device Status: " +ans[10]);
        System.out.println("Battery voltage: " + twoBitsBattery(ans[11],ans[12]) / 100);
        System.out.println("Counter A: " + twoBitsBig(ans[13],ans[14]));
        System.out.println("Counter B: " + twoBitsBig(ans[15], ans[16]));
        System.out.println("Sensor status: " + ans[17]);
        System.out.println("Total Counter A: " + twoBitsBig(ans[18], ans[19]));
        System.out.println("Total Counter B: " + twoBitsBig(ans[20], ans[21]));
        System.out.println("Payload Counter: " + ans[22]);

 */

    }
}

