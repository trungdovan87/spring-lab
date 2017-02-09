package com.czeczotka.bdd.runner;

import org.junit.Test;
import org.junit.runner.JUnitCore;

/**
 * Created by trungdovan on 12/8/16.
 */
public class MainTest {
	@Test
	public void test(){
		JUnitCore.main("com.czeczotka.bdd.runner.MyTest");
	}
}
