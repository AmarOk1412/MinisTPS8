package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class TP12 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		B b1 = new B();
		B b2 = new B();
		B b3 = new B();
		Z z1 = new Z();
		Z z2 = new Z();
		
		z1.b = new HashSet<B>();
		z2.b = new HashSet<B>();
		
		z1.b.add(b1);
		z2.b.add(b2);
		z2.b.add(b3);
		
	}

}
