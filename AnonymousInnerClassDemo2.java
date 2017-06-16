package com.saga.innerclasses;

/**
 * 
 * @author Sathish K Ganga
 * 
 *         This is an example of anonymous inner class that extends a Thread
 *         class
 * 
 */

public class AnonymousInnerClassDemo2 {
	public static void main(String[] args) {

		// If in case this thread process is many times requirement
		// Normal class approach
		MyThread t1 = new MyThread();
		t1.start();

		// If the thread process is just one time requirement, can do without
		// MyThread class
		// Anonymous inner class approach
		Thread t2 = new Thread() {
			public void run() {
				for (int i = 0; i < 10; i++) {
					System.out.println("Child thread");
				}
			}
		};
		t2.start();

		// Example main thread process for test
		for (int i = 0; i < 10; i++) {
			System.out.println("Main thread");
		}
	}
}

class MyThread extends Thread {
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("Child thread");
		}
	}
}
