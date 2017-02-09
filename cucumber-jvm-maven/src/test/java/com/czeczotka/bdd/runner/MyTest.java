package com.czeczotka.bdd.runner;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by trungdovan on 12/8/16.
 */
@RunWith(JUnit4.class)
public class MyTest {
	static public class P {
	}

	static public class A extends P{
		public String getValue(){
			return "A";
		}
	}


	static public class B extends A {
		public String getValue(){
			return "B";
		}
	}
	static public class C extends A {
		public String getValue(){
			return "C";
		}
	}

	static public void processElements(List<A> elements) {
		for (A o : elements) {
			System.out.println(o.getValue());
		}
	}

	static public void processElements1(List<?> elements) {
		for (Object o : elements) {
			System.out.println(o.toString());
		}
	}

	public void processElements2(List<? extends A> elements) {
		for (A o : elements) {
			System.out.println(o.getValue());
		}
	}

	public static void insertElements(List<? super A> list){
		list.add(new A());
		list.add(new B());
		list.add(new C());
	}

	@Test
	public void testAbc() {
		List<Object> listO = new ArrayList<>();
		List<P> listP = new ArrayList<>();
		List<A> listA = new ArrayList<>();
		List<B> listB = new ArrayList<>();
		List<C> listC = new ArrayList<>();
		listA.add(new A());
		//processElements2(listA);

		insertElements(listA);

		processElements2(listA);
	}

}
