package testApp;

import java.util.ArrayList;
import java.util.List;

import algorithm.Plate;
import algorithm.Population;
import pack.snack;

public class TestApp {
	
	public static void main(String[] args) {
		
		List<snack> snacks = new ArrayList<>();
		snacks.add(new snack(2.0, 10.0, "Uno"));
		snacks.add(new snack(1.0, 13.2, "Due"));
		snacks.add(new snack(3.0, 15.1, "Tre"));
		snacks.add(new snack(4.0, 1.0, "Quattro"));
		snacks.add(new snack(0.5, 0.5, "Cinque"));
		snacks.add(new snack(10.0, 40.0, "Sei"));
		
		Population myPopulation = new Population(500, 0.70, 0.30, snacks);
		Plate myPlate = new Plate(myPopulation, 50, 42);
		myPlate.StartStudy();
	}
}
