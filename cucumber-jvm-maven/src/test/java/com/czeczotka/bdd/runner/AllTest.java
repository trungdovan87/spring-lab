package com.czeczotka.bdd.runner;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by trungdovan on 12/8/16.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({RunCalculatorTest.class, MyTest.class})
public class AllTest {
}
