package com.saga.innerclasses;

/**
 * 
 * @author Sathish K Ganga
 * 
 *         This is an example of anonymous inner class that implements a
 *         Runnable interface
 * 
 */

public class AnonymousInnerClassDemo3 {
	public static void main(String[] args) {

		// If in case this thread process is many times requirement
		// Normal class approach
		MyRunnable r1 = new MyRunnable();
		Thread t1 = new Thread(r1);
		t1.start();

		// If the thread process is just one time requirement, can do without
		// MyRunnable class
		// Anonymous inner class approach (Its an implemented class)
		Runnable r2 = new Runnable() {
			public void run() {
				for (int i = 0; i < 10; i++) {
					System.out.println("Child thread");
				}
			}
		};
		Thread t2 = new Thread(r2);
		t2.start();
		
		// Same above t2 - different way
		new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 10; i++) {
					System.out.println("Child thread");
				}
			}
		}).start();

		// Example main thread process for test
		for (int i = 0; i < 10; i++) {
			System.out.println("Main thread");
		}
	}
}

class MyRunnable implements Runnable {
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("Child thread");
		}
	}
}
