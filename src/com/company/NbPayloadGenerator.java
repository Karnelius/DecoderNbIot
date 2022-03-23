package com.company;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class NbPayloadGenerator {

    //"02040004A30B00F60800F802202203101144220003000220"
    
    String typTwoFour = "02040004A30B00F60800F802202203101144220003000220";

    LocalDate datez = LocalDate.now();
    LocalTime timez = LocalTime.now();

    //TODO fixa så randomizern (?) returnerar alltid 2 bytes. Så vid >10 att det blir ex 02 iso 2 ... fel format annars.
        int min = 10;
        int max = 30;

        
         String payload_type = "02";                                                 //02
         String type_variant = "04";                                                 //04
         String device_id = "0004A30B00F6";                                                      //00 04 A3 0B 00 F6
         String device_status = "08";                                                     //08
         String battery_voltage = "00F8";                                                //00 F8
         String rss_level = "02";                                                         //02
         String date = datez.format(DateTimeFormatter.ofPattern("yyyyMMdd"));                                                           //20 22 03 10
         String time = timez.format(DateTimeFormatter.ofPattern("HHmmss"));                                                           //11 44 22
         int counter_a = randomize();                                       //00 03 // gör om till hex -> 2 byte // problem då fel format
         int counter_b= randomize();                                        //00 02 // gör om till hex -> 2 byte // problem då fel format
         String sensor_status = "20";                                                 //20


    public int randomize(){
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }

    public String generatedHexStr(){
        String hexStr = (payload_type+ type_variant+
                        device_id+device_status+battery_voltage+
                        rss_level+date+time+
                        "00" + counter_a + "00" +  counter_b + sensor_status);
        //System.out.println("Generator hexStr " + hexStr);
        return hexStr;
    }


    public void printGenerator(){
        System.out.println("\n");
        System.out.println("Payload Type: " + payload_type);
        System.out.println("Type Variant: " + type_variant);
        System.out.println("Device ID: " + device_id);
        System.out.println("Device Status: " + device_status);
        //System.out.println("Battery Voltage: " + getBattery_voltage());
        System.out.println("RSSI Level: " + rss_level);
        System.out.println("Date (YYYYMMDD) : " + date);
        System.out.println("Time (HHMMSS) : " + time);
        System.out.println("Counter A: " + counter_a);
        System.out.println("Counter B: " + counter_b);
        System.out.println("Sensor Status: " + sensor_status);
        System.out.println("\n");
        System.out.println("Original hexStr  02040004A30B00F60800F802202203101144220003000220");
    }





}