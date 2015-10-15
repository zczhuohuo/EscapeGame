package test.java;

import org.junit.Test;

import com.mazhengyue.Model.GameMap;
import com.mazhengyue.Model.Sector;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

import junit.framework.TestCase;


public class TestGameMap extends TestCase{
	public boolean setEquals(HashSet<Sector>setOne, HashSet<Sector>setTwo) {
		for (Sector s : setOne) {
			boolean flag = false;
			for (Sector eqlS : setTwo) {
				if (s.equals(eqlS))
					flag = true;
			}
			if (!flag)
				return false;
		}
		return true;
	}
	
	@Test
	public void testLegalSectors() throws IOException {
		GameMap gm = new GameMap("E:/code/Java/EscapeGame/src/com/mazhengyue/mapOne.txt");
		HashSet<Sector> rstSet = new HashSet<Sector>();
		HashSet<Sector> visitedSet = new HashSet<Sector>();
		HashSet<Sector> eqlSet = new HashSet<Sector>();
		eqlSet.add(new Sector("K02", 1));
		gm.legalSectors(new Sector("J01", 5), 1, rstSet, visitedSet);
		boolean flag = setEquals(eqlSet, rstSet);
		assertEquals(true, flag);
		
		eqlSet.clear();
		rstSet.clear();
		visitedSet.clear();
		gm.legalSectors(new Sector("L03", 2), 1, rstSet, visitedSet);
		eqlSet.add(new Sector("K03", 1));
		eqlSet.add(new Sector("K04", 2));
		eqlSet.add(new Sector("L04", 2));
		eqlSet.add(new Sector("M04", 2));
		eqlSet.add(new Sector("M03", 1));
		flag = setEquals(eqlSet, rstSet);
		assertEquals(true, flag);
	}

	@Test
	public void testContructGameMap() throws IOException {
		String mapFile = "E:/code/Java/EscapeGame/src/com/mazhengyue/mapOne.txt";
		GameMap gm = new GameMap(mapFile);
		File file = new File(mapFile);
		String line;
		BufferedReader reader = new BufferedReader(new FileReader(file));
		while ((line = reader.readLine()) != null) {
			String tmpStr[] = line.split(" ");
			Sector sec = new Sector(tmpStr[0], Integer.parseInt(tmpStr[1]));
			if (sec.getType() > 50)
				sec.setType(5);
			assertEquals(sec.getType(), gm.getSectors()[sec.x][sec.y].getType());
		}
		reader.close();
	}
	
}
