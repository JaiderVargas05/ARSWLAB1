package edu.eci.arsw.math;

/*
 * PiThread class
 */
public class PiThread extends Thread {

    private int subRangeStart;
    private int subRangeCount;
    private int indexOfSubRange;
    private static int start;
    private static int count;
    private static int DigitsPerSum = 8;
    private static double Epsilon = 1e-17;
    public static byte[] digits;

    /**
     * Initializes the digits array to store the hexadecimal digits of pi in the
     * specified range.
     *
     * @param startRange The start index of the range.
     * @param countRange The number of elements in the range.
     */
    public static void createByteArrayRange(int startRange, int countRange) {
        start = startRange;
        count = countRange;
        digits = new byte[count];
    }

    /**
     * Initializes a new instance of the PiThread class.
     * A specific PiThread object calculates the hexadecimal digits of pi in a
     * specific sub range.
     *
     * @param subRangeStart The start index of the sub range.
     * @param subRangeCount The number of elements in the sub range.
     */
    public PiThread(int subRangeStart, int subRangeCount) {
        this.subRangeStart = subRangeStart;
        this.subRangeCount = subRangeCount;
        this.indexOfSubRange = subRangeStart;

    }

    /**
     * Modifies the digits array to store the hexadecimal digits of pi in the
     * specified sub range.
     */
    @Override
    public void run() {
        if (subRangeStart < 0 || subRangeCount < 0) {
            throw new RuntimeException("Invalid Interval");
        }
        double sum = 0;
        for (int i = 0; i < subRangeCount; i++) {
            if (i % DigitsPerSum == 0) {
                sum = 4 * sum(1, subRangeStart)
                        - 2 * sum(4, subRangeStart)
                        - sum(5, subRangeStart)
                        - sum(6, subRangeStart);
                subRangeStart += DigitsPerSum;
            }
            sum = 16 * (sum - Math.floor(sum));
            digits[indexOfSubRange + i - start] = (byte) sum;
        }
    }

    /// <summary>
    /// Returns the sum of 16^(n - k)/(8 * k + m) from 0 to k.
    /// </summary>
    /// <param name="m"></param>
    /// <param name="n"></param>
    /// <returns></returns>
    private static double sum(int m, int n) {
        double sum = 0;
        int d = m;
        int power = n;
        while (true) {
            double term;
            if (power > 0) {
                term = (double) hexExponentModulo(power, d) / d;
            } else {
                term = Math.pow(16, power) / d;
                if (term < Epsilon) {
                    break;
                }
            }
            sum += term;
            power--;
            d += 8;
        }
        return sum;
    }

    /// <summary>
    /// Return 16^p mod m.
    /// </summary>
    /// <param name="p"></param>
    /// <param name="m"></param>
    /// <returns></returns>
    private static int hexExponentModulo(int p, int m) {
        int power = 1;
        while (power * 2 <= p) {
            power *= 2;
        }
        int result = 1;
        while (power > 0) {
            if (p >= power) {
                result *= 16;
                result %= m;
                p -= power;
            }
            power /= 2;
            if (power > 0) {
                result *= result;
                result %= m;
            }
        }
        return result;
    }

}
