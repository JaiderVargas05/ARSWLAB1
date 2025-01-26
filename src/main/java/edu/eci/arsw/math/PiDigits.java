package edu.eci.arsw.math;

import java.util.ArrayList;
import java.util.List;

///  <summary>
///  An implementation of the Bailey-Borwein-Plouffe formula for calculating hexadecimal
///  digits of pi.
///  https://en.wikipedia.org/wiki/Bailey%E2%80%93Borwein%E2%80%93Plouffe_formula
///  *** Translated from C# code: https://github.com/mmoroney/DigitsOfPi ***
///  </summary>
public class PiDigits {

    private static byte[] digits;
    private static int s;
    /**
     * Returns a range of hexadecimal digits of pi.
     * 
     * @param start The starting location of the range.
     * @param count The number of digits to return
     * @return An array containing the hexadecimal digits.
     */
    public static byte[] getDigits(int start, int count, int N) {
        s = start;
        List<PiThread> threads = new ArrayList<>();
        digits = new byte[count];
        try {
            int pendingDigits = count;
            int countxThread = count / N;
            PiThread piThread;
            for (int n = 0; n < N; n++) {
                
                if (n == N - 1) {
                    /*System.out.println("start: " + start + " pendingDigits: " + pendingDigits);*/
                    piThread = new PiThread(start, pendingDigits);
                } else {
                    //System.out.println("start: " + start + " countxThread: " + countxThread);
                    piThread = new PiThread(start, countxThread);
                }

                //
                // piThread.join();
                piThread.start();
                threads.add(piThread);

                // piThread.join();
                // Add digits to the list

                start += countxThread;
                pendingDigits -= countxThread;
            }
        } catch (Exception e) {

        }

        try {
            for (PiThread thread : threads) {
                thread.join();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Convert List<Byte> to byte[]

        /*  Example output
        for (byte digit : digits) {
            System.out.print((byte)digit + " ");
        }*/
        return digits;

    }

    public static void putADigit(int i, byte value) {
        digits[i-s] = value;
    }

}
