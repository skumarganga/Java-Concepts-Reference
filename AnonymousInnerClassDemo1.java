package com.saga.innerclasses;

/**
 * 
 * @author Sathish K Ganga
 * 
 *         This is an example of anonymous inner class that extends a class
 * 
 */

public class AnonymousInnerClassDemo1 {
	public static void main(String[] args) {

		// Declaring a class that extends BingoChips class without name
		// (anonymous inner class)
		BingoChips bc1 = new BingoChips() {
			void flavor() {
				System.out.println("Deliver barbecue bingo chips");
			}
		};
		bc1.flavor(); // deliver barbecue bingo chips for one time.

		// Creating BingoChips object
		BingoChips bc2 = new BingoChips();
		bc2.flavor();
		bc2.flavorMasala();
		bc2.flavorSpicy();

		// Declaring another class that extends BingoChips class without name
		// (anonymous inner class)
		BingoChips bc3 = new BingoChips() {
			void flavor() {
				System.out.println("Deliver cheddar bingo chips");
			}
		};
		bc3.flavor(); // deliver cheddar bingo chips for one time.

		// Generated class file names for reference
		System.out.println(bc1.getClass().getName()); // AnonymousInnerClassDemo1$1
		System.out.println(bc2.getClass().getName()); // BingoChips
		System.out.println(bc3.getClass().getName()); // AnonymousInnerClassDemo1$1
	}
}

class BingoChips {
	// Regular flavor chips for example
	void flavor() {
		System.out.println("Deliver salty bingo chips by default");
	}

	void flavorMasala() {
		System.out.println("Deliver Masala bingo chips");
	}

	void flavorSpicy() {
		System.out.println("Deliver spicy bingo chips");
	}
}
