package testApp;

import java.util.ArrayList;
import java.util.List;
import algorithm.Plate;
import algorithm.Population;
import pack.snack;


public class TestApp {
	
	public static void main(String[] args) throws InterruptedException {
		
		List<snack> snacks = new ArrayList<>();
		snacks.add(new snack(2.0, 10.0, "Uno"));
		snacks.add(new snack(1.0, 13.2, "Due"));
		snacks.add(new snack(3.0, 15.1, "Tre"));
		snacks.add(new snack(4.0, 1.0, "Quattro"));
		snacks.add(new snack(0.5, 0.5, "Cinque"));
		snacks.add(new snack(10.0, 40.0, "Sei"));
		
		//BestSolution
		//Thread t1 = new Thread(new bestSolution(snacks, 42, 2));
		Thread t2 = new Thread(new solution(snacks));
		//t1.start();
		t2.start();
		t2.join();
		//t1.join();
	}
	
	public static class solution implements Runnable{
		
		List<snack> snacks;
		
		public solution(List<snack> snacks) {
			this.snacks = snacks;
		}

		@Override
		public void run() {
			Population myPopulation = new Population(1000, 0.80, 0.20, snacks, 2);
			Plate myPlate = new Plate(myPopulation, 10000, 42);
			myPlate.StartStudy();
		}
		
	}
	
	public static class bestSolution implements Runnable{

		private List<snack> snacks;
		private int maxWeight;
		private double bestVal;
		private int dimension;
		
		public bestSolution(List<snack> snacks, int maxWeight, int dimension) {
			this.snacks = snacks;
			this.maxWeight = maxWeight;
			this.dimension = dimension;
		}

		@Override
		public void run() {
			
			findBest(0, 0.0, 0.0);
			System.out.println("Value of optimal solution: "+this.bestVal);
		}
		
		private void findBest(int pos, double val, double weight) {
			if(pos >= this.snacks.size()*dimension) {
				if(val > this.bestVal) 
					this.bestVal = val;
				return;
			}
			for(int i=0;i<this.snacks.size();++i) 
				if(this.snacks.get(i).getWeight() + weight < maxWeight)
					findBest(pos+1, val+this.snacks.get(i).getValue(), weight+this.snacks.get(i).getWeight());
		}
	}
	
}
