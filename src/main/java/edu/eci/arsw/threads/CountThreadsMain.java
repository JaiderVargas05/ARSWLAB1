/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.threads;

//import sun.jvm.hotspot.runtime.Thread;

/**
 *
 * @author hcadavid
 */
public class CountThreadsMain {
    
    /**
     * Creates three threads and starts them to print the numbers between 0 and 200
     */
    public static void main(String a[]){
        CountThread thread1 = new CountThread(0,99);
        CountThread thread2 = new CountThread(99,199);
        CountThread thread3 = new CountThread(200,299);
        thread1.start();
        thread2.start();
        thread3.start();
    }
    
}
