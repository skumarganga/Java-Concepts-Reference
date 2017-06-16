package com.saga.innerclasses;

/**
 * 
 * @author Sathish K Ganga
 * 
 *         Some times we can declare inner class with static modifier. Such type
 *         of inner classes are called static nested classes.
 * 
 *         In the case of normal / regular inner class without existing outer
 *         class object there is no chance of existing inner class object. That
 *         is, inner class object is strongly associated with outer class
 *         object. But in the case of static nested classes without existing
 *         outer class object there may be a chance if existing nested class
 *         object. Hence static nested class object is not strongly associated
 *         with outer class object.
 * 
 *         In normal / regular inner classes we can't declare any static
 *         members. But in static nested classes we can declare static members
 *         including main method. Hence we can invoke static nested class
 *         directly from command prompt. Example: java ClassOuter$ClassNested
 * 
 */

public class StaticNestedClassDemo {
	public static void main(String[] args) {
		// To create nested class object from outside of outer class
		ClassOuter.ClassNested n = new ClassOuter.ClassNested();
		n.m1();
	}
}

class ClassOuter {

	int x = 10;
	static int y = 20;

	static class ClassNested {
		public void m1() {
			System.out.println("Static nested class method");
		}

		public void m2() {
			// From normal / regular inner classes we can access both static &
			// non-static members of outer class directly. But from static
			// nested classes we can access static members of outer class
			// directly and we CANNOT access non-static members.
			// System.out.println(x); Compile time error: Non-static variable x
			// can not be referenced from a static context.
			System.out.println(y);
		}

		public static void main(String[] args) {
			System.out.println("Static nested class main method");
		}
	}

	public static void main(String[] args) {
		// To create nested class object inside outer class
		ClassNested n = new ClassNested();
		n.m1();

		System.out.println("Outer class main method");
	}
}