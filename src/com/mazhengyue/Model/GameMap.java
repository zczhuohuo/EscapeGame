package com.mazhengyue.Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashMap;

import com.mazhengyue.Controller.Utilities;

public class GameMap {
	private final static int ColLength = 23;
	private final static int RowLength = 28;
	
	private final static int upperBound = 0;
	private final static int lowerBound = 27;
	private final static int leftBound = 0;
	private final static int rightBound = 22;

	
	public Sector humanSector;		// For convenient to use TeleportCard
	public Sector alienSector;		
	
	private Sector sectors[][] = new Sector[ColLength][RowLength];
	
	public GameMap(int arr[][]) {
		for (int i = 0; i < RowLength; i++) {
			for (int j = 0; j < ColLength; j++) {
				sectors[i][j] = new Sector(i, j, arr[i][j]);
				if (arr[i][j] == Sector.human) 
					humanSector = sectors[i][j];
				if (arr[i][j] == Sector.alien) 
					alienSector = sectors[i][j];
				if (arr[i][j] == Sector.escapeOne ||
					arr[i][j] == Sector.escapeTwo ||
					arr[i][j] == Sector.escapeThree ||
					arr[i][j] == Sector.escapeFour) {
					this.sectors[i][j].setType(Sector.escape);
					this.sectors[i][j].setHatchNumber(arr[i][j] - 50);
				}
			}
		}
	}
	
	private void init() {
		for (int i = 0; i < ColLength; i++) {
			for (int j = 0; j < RowLength; j++) {
				this.sectors[i][j] = new Sector(i, j, 0);
			}
		}
	}
	
	public Sector[][] getSectors() {
		return this.sectors;
	}
	
	public Sector getSingleSector(String sec) {
		int x = Utilities.getX(sec);
		int y = Utilities.getY(sec);
		return this.sectors[x][y];
	}
	
	public GameMap(String filename) throws IOException {
		this.init();
		File file = new File(filename);
		String line;
		BufferedReader reader = new BufferedReader(new FileReader(file));
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		while ((line = reader.readLine()) != null) {
			String tmpStr[] = line.split(" ");
			hm.put(tmpStr[0], Integer.parseInt(tmpStr[1]));
		}
		this.constructMap(hm);
		//printMap();
		reader.close();
	}
	
	private void constructMap(HashMap<String, Integer> gMap) {
		for (String key : gMap.keySet()) {
			Sector sec = new Sector(key, gMap.get(key));
			//System.out.println("x = " + sec.x + " y = " + sec.y + " " + sec.getStr()); 
			this.sectors[sec.x][sec.y] = sec;
			if (gMap.get(key) == Sector.human)
				this.humanSector = sec;
			if (gMap.get(key) == Sector.alien) 
				this.alienSector = sec;
			if (gMap.get(key) == Sector.escapeOne ||
				gMap.get(key) == Sector.escapeTwo ||
				gMap.get(key) == Sector.escapeThree ||
				gMap.get(key) == Sector.escapeFour) {
				this.sectors[sec.x][sec.y].setType(Sector.escape);
				this.sectors[sec.x][sec.y].setHatchNumber(gMap.get(key) - 50);
			}
		}
	}
	
	public LinkedList<Sector> nearbySectors(Sector sec) {
		LinkedList<Sector> nbSectors = new LinkedList<Sector>();
		for (int i = sec.x - 1; i <= sec.x + 1; i++) {
			for (int j = sec.y - 2; j <= sec.y + 2; j += 1) {
				if (i == sec.x && j == sec.y)
					continue;
				if (i >= GameMap.leftBound && i <= GameMap.rightBound && j >= GameMap.upperBound && j <= GameMap.lowerBound) {
					nbSectors.add(this.sectors[i][j]);
				}
			}
		}

		return nbSectors;
	}
	
	public void legalSectors(Sector sec, int steps, Set<Sector> rstSet, Set<Sector> visitedSet){
		if (steps == 0) { // no more steps
			if (this.sectors[sec.x][sec.y].getType() != Sector.notLegal) {
				rstSet.add(this.sectors[sec.x][sec.y]);  
			}
		} 
		else {
			for (Sector nbSector : nearbySectors(sec)) {
				if (! visitedSet.contains(nbSector) && nbSector.getType() != Sector.notLegal) {
					legalSectors(nbSector, steps - 1, rstSet, visitedSet);
					rstSet.add(nbSector);
				}
			}
		}
		visitedSet.add(sec);
	}
}
