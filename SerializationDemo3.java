package com.saga.serialization;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 
 * @author Sathish K Ganga
 * 
 *         This demonstrates CUSTOMIZED SERIALIZATION and EXTERNALIZATION
 * 
 */

public class SerializationDemo3 {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws FileNotFoundException,
			IOException, ClassNotFoundException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
				"Test.ps"));
		oos.writeObject(new SerializingOne());
		oos.writeObject(new ExternalizingOne(10, 1000, "AAA"));

		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
				"Test.ps"));
		SerializingOne sObj = (SerializingOne) ois.readObject();
		ExternalizingOne eObj = (ExternalizingOne) ois.readObject();

		System.out.println("User: " + sObj.user + ", Password: "
				+ sObj.password);
		System.out.println(eObj.i1 + "..." + eObj.i2 + "..." + eObj.i3 + "..."
				+ eObj.i4 + "..." + eObj.str);
	}
}

/**
 * 
 * CUSTOMIZED SERIALIZATION: In default serialization, there may be a chance of
 * loss of information because of transient variables. To recover this loss of
 * information we should go for customized serialization concept. Example:
 * Suppose an object holds a password of a user, then due to security reasons if
 * the password variable is made transient, then the variable value will be
 * saved/transferred as its default value. In order to recover the password, we
 * can use encryption at sender side and decryption at receiver side. Hence some
 * extra work has to be done at sender and receiver side (means during
 * serialization and deserialization). Define the extra work under the following
 * methods: During serialization: private void writeObject(ObjectOutputSteam
 * oos) throws Exception During deserialization: private void
 * readObject(ObjectInputStream ois) throws Exception
 * 
 * NOTE: If any methods are executed automatically by JVM, they are called
 * 'callback' methods. defaultWriteObject(), defaultReadObject() are meant for
 * default serialization.
 * 
 */

class SerializingOne implements Serializable {
	private static final long serialVersionUID = 3264058670260898182L;
	String user = "admin";
	transient String password = "jun20jun";

	private void writeObject(ObjectOutputStream oos) throws Exception {
		oos.defaultWriteObject();
		String ePassword = "123" + password;
		oos.writeObject(ePassword);
	}

	private void readObject(ObjectInputStream ois) throws Exception {
		ois.defaultReadObject();
		String ePassword = (String) ois.readObject();
		password = ePassword.substring(3);
	}
}

/**
 * 
 * EXTERNALIZATION > In serialization everything will be taken care by JVM,
 * programmer will not have any control. > In serialization it is always
 * possible to save total object to the file and it is not possible to save part
 * of the object. To overcome above problems, we should go for externalization.
 * 
 * > In externalization, everything is controlled by programmer, JVM won't have
 * control. Note that relatively performance will be increased. > To provide
 * externalizability to any class, it compulsory should implement
 * 'Externalizable' interface. > As programmer is responsible to provide ability
 * in externalization, the interface has two methods. 1) writeExternal() 2)
 * readExternal() > Externalizable is the child interface of Serializable only.
 * Both were introdued in Java 1.1 version. In externalization, at the time of
 * deserialization file doesn't contain total object. Thats why JVM will create
 * a seperate new object and on that object JVM will call readExternal() method.
 * To create this new object, JVM will always call public no-arg constructor. So
 * compulsory externalizable object should have no-arg constructor which is
 * either programmer provided or compiler generated, otherwise run time
 * exception 'invalidClassException' will appear. Transient keyword is not
 * required in externalization. It won't play any role.
 * 
 */

class ExternalizingOne implements Externalizable {

	int i1, i2, i3, i4;
	String str;

	public ExternalizingOne() {
	}

	public ExternalizingOne(int i1, int i3, String str) {
		this.i1 = i1;
		this.i3 = i3;
		this.str = str;
	}

	@Override
	public void readExternal(ObjectInput arg0) throws IOException,
			ClassNotFoundException {
		this.i1 = arg0.readInt();
		this.i3 = arg0.readInt();
		this.str = (String) arg0.readObject();
	}

	@Override
	public void writeExternal(ObjectOutput arg0) throws IOException {
		arg0.writeInt(i1);
		arg0.writeInt(i3);
		arg0.writeObject(str);
	}
}
