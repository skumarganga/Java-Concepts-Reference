package com.saga.serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 
 * @author Sathish K Ganga
 * 
 *         SERIALIZATION WITH RESPECT TO INHERITANCE
 * 
 *         Case#1: Parent implements Serializable but not child: If the parent
 *         is serializable then automatically all child classes will implement
 *         serializable by default eventhough child classes don't implement
 *         serializable explicitly. Example: GenericServlet implements
 *         Serializable, hence all servlets implemets Serializable.
 * 
 *         Case#2: Parent doesn't implement Serializable but child implements: >
 *         To serialize a child object, parent class need not be serializable.
 *         At the time of serialization, JVM checks is there exists any variable
 *         coming from non serializable object, if any instance variable
 *         inheriting from non-serializable parent then JVM ignores original
 *         value and saves default value to the file. > At the time of
 *         deserialization, JVM checks is any parent class is non-serializable
 *         or not. If parent class non-serializable then JVM will execute
 *         instance control flow and share its instance variable values to
 *         current object. > Instance control flow: > Identification of instance
 *         variables/members. > Execution of instance variable assignments and
 *         instance blocks. > Execution of constructor. As part of instance flow
 *         execution JVM will always call default constructor or no-arg
 *         constructor of non-serializable parent. Hence every non-serializable
 *         parent should compulsory contain no-arg constructor which may be a
 *         programmer provided or compiler generated default constructor,
 *         otherwise we will get runtime exception saying
 *         'InvalidClassException'.
 * 
 */

public class SerializationDemo2 {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException,
			ClassNotFoundException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
				"Test.ser"));

		// Case#1 - Serialization
		ChildOne c1 = new ChildOne();

		oos.writeObject(c1);

		// Case#2: Serialization
		ChildTwo c2 = new ChildTwo();

		oos.writeObject(c2);

		System.out.println();

		// Serialization and deserialization of multiple objects to a single
		// file SHOULD BE IN SAME ORDER.
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
				"Test.ser"));

		// Case#1: Deserialization
		ChildOne c11 = (ChildOne) ois.readObject();

		System.out.println("Name of c11-Parent: " + c11.parentName);
		System.out.println("Name of c11: " + c11.name);

		System.out.println("Name of c11-Other: " + c11.o1.name);

		// Case#2: Deserialization (Observe instance control flow)
		ChildTwo c22 = (ChildTwo) ois.readObject();

		System.out.println("Name of c22-Parent: " + c22.parentName);
		System.out.println("Name of c22: " + c22.name);
	}
}

class ParentOne implements Serializable {
	private static final long serialVersionUID = 1L;
	String parentName = "ParentOne";

	ParentOne() {
		System.out.println("ParentOne constructor called");
	}
}

class ChildOne extends ParentOne {
	private static final long serialVersionUID = 2L;
	String name = "ChildOne";
	OtherOne o1 = new OtherOne();

	public ChildOne() {
		System.out.println("ChildOne constructor called");
	}
}

// OBJECT GRAPH IN SERIALIZATION: When ever we are serializing an object, the
// set of all objects which are reachable from that object will be serialized
// automatically. This group of objects that will be serialized is object graph
// in serialization. NOTE: All the classes in graph should implement
// Serializable. (OtherOne is reachable from ChildOne)
class OtherOne implements Serializable {
	private static final long serialVersionUID = 3L;
	String name = "OtherOne";

	OtherOne() {
		System.out.println("OtherOne constructor called");
	}
}

class ParentTwo {
	String parentName = "ParentTwo";

	ParentTwo() {
		System.out.println("ParentTwo constructor called");
	}
}

class ChildTwo extends ParentTwo implements Serializable {
	private static final long serialVersionUID = 4L;
	String name = "ChildTwo";

	ChildTwo() {
		System.out.println("ChildTwo constructor called");
	}
}
