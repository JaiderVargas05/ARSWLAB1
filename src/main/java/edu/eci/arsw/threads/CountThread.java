package edu.eci.arsw.threads;

/**
 *
 * @author hcadavid
 */
public class CountThread extends Thread {
    public int a;
    public int b;

    /*
     * Run method that prints the numbers between a and b
     */
    @Override
    public void run() {
        for (int i = a; i <= b; i++) {
            System.out.println(i);
        }
    }


    /*
     * Constructor of the class
     * @param a The start of the interval
     * @param b The end of the interval
     */
    public CountThread(int a, int b) {
        this.a = a;
        this.b = b;
    }

}
