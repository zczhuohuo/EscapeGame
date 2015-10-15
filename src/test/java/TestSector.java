package test.java;
import org.junit.Test;

import com.mazhengyue.Model.Sector;

import junit.framework.TestCase;


public class TestSector extends TestCase {

	@Test
	public void testGetStr() {
		Sector st = new Sector(2, 4, 0);
		String rt = st.getStr();
		assertEquals("just a test", "C03", rt);
	}

	
	@Test
	public void testNew() {
		Sector st = new Sector("K02", 0);
		assertEquals(10, st.x);
		assertEquals(2, st.y);
		
		Sector st2 = new Sector("I06", 0);
		assertEquals(8, st2.x);
		assertEquals(10, st2.y);
	}
}
