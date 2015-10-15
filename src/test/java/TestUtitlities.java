package test.java;

import junit.framework.TestCase;

import org.junit.Test;

import com.mazhengyue.Controller.Utilities;
import com.mazhengyue.Model.Sector;

public class TestUtitlities extends TestCase{

	@Test
	public void testGetXY() {
		assertEquals(0, Utilities.getX("A01"));
		assertEquals(0, Utilities.getY("A01"));
		assertEquals(12, Utilities.getX("M03"));
		assertEquals(4, Utilities.getY("M03"));
		assertEquals(11, Utilities.getX("L05"));
		assertEquals(9, Utilities.getY("L05"));
	}
	
	@Test 
	public void testInputOk() {
		assertEquals(true, Utilities.inputOk("L01"));
		assertEquals(false, Utilities.inputOk("O010"));
	}

	@Test 
	public void testValidSector() {
		assertEquals(true, Utilities.validSector(new Sector(0, 1, 1)));
		assertEquals(false, Utilities.validSector(new Sector(0, 1, 0)));
	}
	
	@Test
	public void testOutOfRange() {
		assertEquals(true, Utilities.outOfRange(10, 1, 9));
		assertEquals(false, Utilities.outOfRange(10, 1, 10));
		assertEquals(false, Utilities.outOfRange(1, 1, 10));
	}
}
