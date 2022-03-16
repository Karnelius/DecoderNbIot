package com.company;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;


public class NbDecoder {

    private int payload_type;
    private int type_variant;
    private int device_status;
    private double battery_voltage;
    private int rss_level;
    private String date;
    private String time;
    private int counter_a;
    private int counter_b;
    private int sensor_status;
    private String device_id;


    public void decoder(String str){
         //String sub = str.substring(4,16);
         byte[] ans = new byte[str.length() / 2];
         for(int i=0; i< ans.length; i++){
             int index = i * 2;
             int val = Integer.parseInt(str.substring(index, index +2),16);
             ans[i] = (byte) val;
         }
         varMethod(ans);
    }


    private void varMethod(byte[] ans) {
         this.payload_type = ans[0];
         this.type_variant = ans[1];
         this.device_id = deviceIdfromByte(ans[2], ans[3], ans[4], ans[5], ans[6], ans[7]);
         this.device_status = ans[8];
         this.battery_voltage = twoBitsBattery(ans[9],ans[10])/100;
         this.rss_level = ans[11];
         this.date = decToHexDate(ans[12],ans[13],ans[14],ans[15]);
         this.time = decToHexTime(ans[16],ans[17],ans[18]);
         this.counter_a = twoBitsBig(ans[19],ans[20]);
         this.counter_b = twoBitsBig(ans[21], ans[22]);
         this.sensor_status = ans[23];

    }

    public void printPayload(){
        System.out.println("\n");
        System.out.println("Payload Type: " + getPayload_type());
        System.out.println("Type Variant: " + getType_variant());
        System.out.println("Device ID: " + getDevice_id());
        System.out.println("Device Status: " + getDevice_status());
        System.out.println("Battery Voltage: " + getBattery_voltage());
        System.out.println("RSS Level: " + getRss_level());
        System.out.println("Date (YYYYMMDD) : " + getDate());
        System.out.println("Time (HHMMSS) : " + getTime());
        System.out.println("Counter A: " + getCounter_a());
        System.out.println("Counter B: " + getCounter_b());
        System.out.println("Sensor Status: " + getSensor_status());
        System.out.println("\n");

    }
/*
        System.out.println("---NB-IOT---- TYPE 2 / 4 -----NB-IOT------");
        System.out.println("Payload type: " + ans[0]);
        System.out.println("Type Variant: " + ans[1]);
        System.out.println("Device ID: " + sub2);
        System.out.println("Device Status: " + ans[8]);
        System.out.println("Battery Voltage: " + twoBitsBattery(ans[9],ans[10])/100);
        System.out.println("RSSI Level: " + ans[11]);
        System.out.println("Date (UTC) (YYMMDD) " + decToHexDate(ans[12],ans[13], ans[14], ans[15]));// BCD Binary Coded Decimal // 4 bytes
        System.out.println("Time (UTC) (HHMMSS) " + decToHexTime(ans[16],ans[17], ans[18])); // BCD Binary Coded Decimal // 3 bytes
        System.out.println("Counter A: " + twoBitsBig(ans[19],ans[20]));
        System.out.println("Counter B: " + twoBitsBig(ans[21], ans[22]));
        System.out.println("Sensor Status: " + ans[23]);
         */


    static String decToHexTime(byte a, byte b, byte c) {
        return Integer.toHexString(a)+Integer.toHexString(b)+Integer.toHexString(c);
    }

    //Not finished ...
    static String deviceIdfromByte(byte a, byte b, byte c, byte d, byte e, byte f) {
        //return String.valueOf(a) + String.valueOf(b) + String.valueOf(c)+ String.valueOf(d)+ String.valueOf(e) + String.valueOf(f);
        return "00"+Integer.toHexString(a)+Integer.toHexString(b)+Integer.toHexString(c)+Integer.toHexString(d)+Integer.toHexString(e)+Integer.toHexString(f);
           //byte[] bytes = {a , b , c , d , e , f};
            //return Arrays.toString(bytes);
        //return String.format((new String(bytes)) + bytes + Arrays.toString(bytes) + Arrays.hashCode(bytes));
        //("0xF0 = FFF")


    }


    static String decToHexDate(byte a, byte b, byte c, byte d){
        return Integer.toHexString(a)+Integer.toHexString(b)+Integer.toHexString(c)+Integer.toHexString(d);
    }

    static short twoBitsBig(byte a, byte b){
        ByteBuffer bb = ByteBuffer.allocate(2);
        bb.order(ByteOrder.BIG_ENDIAN);
        bb.put(a);
        bb.put(b);
        short shortVal = bb.getShort(0);
        return shortVal;
    }

    static double twoBitsBattery(byte a, byte b){
        ByteBuffer bb = ByteBuffer.allocate(2);
        bb.order(ByteOrder.BIG_ENDIAN);
        bb.put(a);
        bb.put(b);
        short doubleVal = bb.getShort(0);
        return doubleVal;
    }

    public int getPayload_type(){
        return this.payload_type;
    }
    public int getType_variant(){
        return this.type_variant;
    }
    public String getDevice_id(){
        return this.device_id;
    }
    public int getDevice_status(){
        return this.device_status;
    }
    public double getBattery_voltage(){
        return this.battery_voltage;
    }
    public int getRss_level(){
        return this.rss_level;
    }
    public String getDate(){
        return this.date;
    }
    public String getTime(){
        return this.time;
    }
    public int getCounter_a(){
        return this.counter_a;
    }
    public int getCounter_b(){
        return this.counter_b;
    }
    public int getSensor_status(){
        return this.sensor_status;
    }


}
