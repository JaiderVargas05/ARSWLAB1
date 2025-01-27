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

    /**
     * Returns a range of hexadecimal digits of pi.
     * @param start The starting location of the range.
     * @param count The number of digits to return
     * @return An array containing the hexadecimal digits.
     */
    public static byte[] getDigits(int start, int count, int N) {
        if (start < 0 || count <= 0 || N <= 0) {
            return new byte[0];
        }
        List<PiThread> threads = new ArrayList<>();
        PiThread.createByteArrayRange(start, count);
        try {
            int countxThread = N > count ? 1 : count / N;
            int pendingDigits = N > count ? 0 : ((count % N) + countxThread);
            PiThread piThread;
            for (int n = 0; n < N && !(N > count && n * countxThread > N - 1); n++) {
                int startRange = n * countxThread + start;
                int pending = (n != N - 1 && startRange < start + count) ? countxThread : pendingDigits;
                piThread = new PiThread(startRange, pending);
                piThread.start();
                threads.add(piThread);
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
        return PiThread.digits;
    }

}
