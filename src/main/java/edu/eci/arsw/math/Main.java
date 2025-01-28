/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.math;

/**
 *
 * @author hcadavid
 */
public class Main {

    private static int start = 10;
    private static int count = 1;
    private static int numbersOfThreads = 3;

    public static void main(String a[]) {
        Runtime runtime = Runtime.getRuntime();
        System.out.println("Available processors (cores): " + runtime.availableProcessors());

        long startTime = System.nanoTime();
        byte[] digits = PiDigits.getDigits(start, count, numbersOfThreads);
        long endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1000000000.0;
        System.out.println("Duration calculating the digits of pi from "
                + start + " to " + (start + count) + " with "
                + numbersOfThreads + " threads: " + duration + " seconds");
        System.out.println("Digits of pi from " + start + " to " + (start + count)
                + ": " + bytesToHex(digits));
    }

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    /**
     * Convert a byte array to a string of hexadecimal characters.
     * 
     * @param bytes Byte array to convert.
     * @return String with hexadecimal characters.
     */
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hexChars.length; i = i + 2) {
            // sb.append(hexChars[i]);
            sb.append(hexChars[i + 1]);
        }
        return sb.toString();
    }

}
