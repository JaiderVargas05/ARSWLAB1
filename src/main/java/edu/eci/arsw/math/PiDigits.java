package edu.eci.arsw.math;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

///  <summary>
///  An implementation of the Bailey-Borwein-Plouffe formula for calculating hexadecimal
///  digits of pi.
///  https://en.wikipedia.org/wiki/Bailey%E2%80%93Borwein%E2%80%93Plouffe_formula
///  *** Translated from C# code: https://github.com/mmoroney/DigitsOfPi ***
///  </summary>
public class PiDigits {

    private static int DigitsPerSum = 8;
    private static double Epsilon = 1e-17;

    
    /**
     * Returns a range of hexadecimal digits of pi.
     * @param start The starting location of the range.
     * @param count The number of digits to return
     * @return An array containing the hexadecimal digits.
     */
    public static byte[] getDigits(int start, int count, int N) {
        List<Byte> digitsList = new ArrayList<>();
        List<PiThread> threads = new ArrayList<>();
        byte[] digitsx = new byte[count];
        try {
            int pendingDigits = count;
            int countxThread = Math.round(count/N);
            PiThread piThread;
            for (int n = 0; n < N; n++) {



                if(n == N-1){
                    piThread = new PiThread(start, pendingDigits);

                }
                else{
                    piThread = new PiThread(start, countxThread);
                }

//
//                piThread.join();
                piThread.start();
                threads.add(piThread);

                //piThread.join();
                // Add digits to the list

                start += countxThread;
                pendingDigits -= countxThread;
            }
        } catch (Exception e) {

        }

        try{
            for (PiThread thread:threads){
                int index=0;

                thread.join();
                for (int i=0;i<thread.digits.length; i++) {

                    digitsx[i+index] = thread.digits[i];

                }
                index+=thread.digits.length;
                //System.out.println(threads.size());
                //System.out.println(thread.digits.length);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Convert List<Byte> to byte[]


        // Example output
//        for (byte digit : digitsx) {
//            System.out.print(digit + " ");
//        }
        return digitsx;

    }



}
