package com.saga.innerclasses;

/**
 * 
 * @author Sathish
 * 
 *         If we are declaring any named class directly inside a class without
 *         static modifier, such type of class is called normal/ regular inner
 *         class.
 */

public class RegularInnerClassDemo {
	// Accessing inner class code from out side of outer class
	public static void main(String[] args) {
		// Accessing outer class method
		Outer o = new Outer();
		o.m2();
		Outer.m3();
		
		// Accessing inner class method
		Outer.Inner i = o.new Inner();
		i.m1();
		
		// Accessing nested inner class method
		Outer.Inner.NestedInner ni = i.new NestedInner();
		ni.m();
	}
}

// The only applicable modifiers for outer classes are public, default, final,
// abstract and strictfp.
class Outer {

	int x = 10;
	static int y = 20;

	// The applicable modifiers for inner classes are public, default, final,
	// abstract and strictfp + private, protected and static.
	class Inner {
		int x = 100;

		void m1() {
			System.out.println("Inner class instance method m1 called");
		}

		// Inside inner class we can't declare any static members. Hence we
		// can't declare main method and we can't run inner class directly from
		// command prompt.
		// static m4() {}
		// static int i = 10;

		// We can't declare static members in inner class but we can access
		// static members or non-static members of outer class directly.
		void m5() {
			System.out.println(x);
			System.out.println(y);
		}

		// With in the inner class 'this' always refers to current inner class
		// object. If we want to refer current outer class object we have to use
		// 'outerclassname.this'.
		void m6() {
			int x = 1000;
			System.out.println(x);
			System.out.println(this.x);
			System.out.println(Inner.this.x);
			System.out.println(Outer.Inner.this.x);
			System.out.println(Outer.this.x);
		}
		
		// Inside inner class we can declare another inner class. That is nesting of inner classes is possible.
		class NestedInner {
			void m() {
				System.out.println("NestedInner class instance method m called");
			}
		}
	}

	// Accessing inner class code from instance area of outer class
	void m2() {
		System.out.println("Outer class instance method m2 called");
		Inner i = new Inner();
		i.m1();
	}

	// Accessing inner class code from static area of outer class
	static void m3() {
		System.out.println("Outer class static method m3 called");
		Outer o = new Outer();
		Outer.Inner i = o.new Inner();
		i.m1();
	}
}
