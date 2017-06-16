package com.saga.innerclasses;

/**
 * 
 * @author Sathish
 * 
 *         Some times we can declare a class inside a method. Such type of inner
 *         classes are called method local inner classes.
 * 
 *         The main purpose of method local inner class is to define method
 *         specific repeatedly required functionality. Method local inner
 *         classes are best suitable to meet nested method requirements.
 * 
 *         We can access method local inner classes only with in a method where
 *         we declared. Outside of the method we can't access. Because of its
 *         less scope method local inner classes are most rare types of inner
 *         classes.
 * 
 *         Note: We can declare method local inner class inside both instance
 *         and static methods.
 * 
 */

public class MethodLocalInnerClassDemo {
	public static void main(String[] args) {
		OuterClass o = new OuterClass();
		o.m();
		o.m1();
	}
}

class OuterClass {

	int x = 10;
	static int y = 20;

	void m() {
		class MethodLocalInnerClass {
			void sum(int x, int y) {
				System.out.println("The sum is: " + (x + y));
			}
		}

		MethodLocalInnerClass mlic = new MethodLocalInnerClass();
		mlic.sum(10, 20);
		mlic.sum(100, 200);
		mlic.sum(1000, 2000);
	}

	// If we declare inner class inside instance method then from that method
	// local inner class we can access both static and non-static members of
	// outer class directly.
	void m1() {
		class MethodLocalInnerClass {
			void display() {
				System.out
						.println("OuterClass instance variable x value: " + x);
				System.out.println("OuterClass static variable y value: " + y);
			}
		}

		MethodLocalInnerClass mlic = new MethodLocalInnerClass();
		mlic.display();
	}

	// If we declare inner class inside static method then we can access only
	// static members of outer class directly from that method local inner
	// class. If we declare the method static then at the line where we access
	// static member, compilation error saying Cannot make a static reference to
	// the non-static field <member-name>.
	static void m2() {
		class MethodLocalInnerClass {
			void display() {
				// Cannot make a static reference to the non-static field x
				/*
				 * System.out.println("OuterClass instance variable x value: " +
				 * x);
				 */
				System.out.println("OuterClass static variable y value: " + y);
			}
		}

		MethodLocalInnerClass mlic = new MethodLocalInnerClass();
		mlic.display();
	}

	// From method local inner class we can't access local variables of the
	// method in which we declare inner class. If the local variable declared as
	// final then we can access.
	// Clue: final variables are assigned during compile time only
	void m3() {
		// int x = 10;
		final int y = 20;
		class MethodLocalInnerClass {
			void display() {
				// Cannot refer to a non-final variable x inside an inner class
				// defined in a different method.
				/*
				 * System.out.println("OuterClass method variable x value: " +
				 * x);
				 */
				System.out.println("OuterClass method final variable y value: "
						+ y);
			}
		}

		MethodLocalInnerClass mlic = new MethodLocalInnerClass();
		mlic.display();
	}
}
