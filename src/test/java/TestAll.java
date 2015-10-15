package test.java;


import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class TestAll extends TestSuite {

	public static Test suite() {
		TestSuite suite = new TestSuite("My First Test");
		suite.addTestSuite(TestSector.class);
		suite.addTestSuite(TestGameMap.class);
		suite.addTestSuite(TestUtitlities.class);
		return suite;
	}
	
	public static void main(String args[]) {
		TestRunner.run(suite());
	}
}
