/**
 * 
 */
package algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import pack.snack;

/**
 * @author Carlo Cena
 *
 */
public class Population {
	
	private List<Chromosome> inhabitants;
	//S is used for weighted probability
	private double S;
	private int size;
	private double pc;
	private double pm;
	private List<snack> snacks;
	List<Integer> tmp;
	
	public Population(int size, double pc, double pm, List<snack> snacks) {
		this.size = size;
		this.pc = pc;
		this.pm = pm;
		this.S = 0.0;
		this.inhabitants = new ArrayList<>();
		this.tmp = new ArrayList<>();
		this.snacks = snacks;
		//Building list
		List<Integer> ids = new ArrayList<>();
		for(int i = 0;i < this.size;++i) {
			//Adding random snacks 
			for(int j = 0;j < snacks.size()*5;++j) {
				ids.add((int) (Math.random()*snacks.size())) ;
			}
			this.inhabitants.add(new Chromosome(ids, 0.0));
			ids.clear();
		}
	}
	
	//Computes fitness of each chromosome 
	void computeFit(int maxWeight) {
		double value;
		double weight;
		int i;
		for(Chromosome c: this.inhabitants) {
			value = 0.0;
			weight = 0.0;
			i = 0;
			while(weight < maxWeight && i < c.getIds().size()) {
				if(weight + this.snacks.get(c.getIds().get(i)).getWeight() < maxWeight) {
					weight += this.snacks.get(c.getIds().get(i)).getWeight();
					value += this.snacks.get(c.getIds().get(i)).getValue();
				}
				i++;
			}
			c.setFit(value);
			this.S += value;
		}
		//Sorting list of chromosomes in descending order
		this.inhabitants.sort(new Comparator<Chromosome>() {

			@Override
			public int compare(Chromosome o1, Chromosome o2) {
				return o1.compareTo(o2);
			}
		});
	}
	
	/**
	 * Descending order of the list and return the value of the best solution
	 * @return value of the best solution
	 */
	double getBestValue() {
		this.inhabitants.sort(new Comparator<Chromosome>() {

			@Override
			public int compare(Chromosome o1, Chromosome o2) {
				return o1.compareTo(o2);
			}
			
		});
		return this.inhabitants.get(0).getFit();
	}
	
	/**
	 * It is supposed that we have an ordered list of inhabitants
	 * This method should be called after getBestValue
	 * @return Best solution found
	 */
	List<Integer> getBestIds() {
		return this.inhabitants.get(0).getIds();
	}
	
	
	void merging() {
		int half_index = this.size/2;
		int half_size = this.snacks.size()/2;
		int first_parent = 0;
		int second_parent = 0;
		int j;
		double sum;
		double random_number;
		
		this.inhabitants.sort(new Comparator<Chromosome>() {

			@Override
			public int compare(Chromosome o1, Chromosome o2) {
				return o1.compareTo(o2);
			}
			
		});
		for(int i = half_index;i < this.inhabitants.size();++i) {
			//Randomly taking two parents
			sum = 0;
			random_number = Math.random()*this.S;
			while(sum < random_number) {
				first_parent = (first_parent+1)%this.inhabitants.size();
				sum += this.inhabitants.get((first_parent+1)%this.inhabitants.size()).getFit();
			}
			sum = 0;
			random_number = Math.random()*this.S;
			while(sum < random_number) {
				second_parent = (second_parent+1)%this.inhabitants.size();
				sum += this.inhabitants.get((second_parent+1)%this.inhabitants.size()).getFit();
			}
			
			/**
			 * No crossover 00-11 22-33 -> 00-33
			 * Crossover 00-11 22-33 -> 22-11
			 * Mutation 00-11 -> 11-00
			 */
			tmp.clear();
			tmp.addAll(this.inhabitants.get(i).getIds());
			
			//NO CROSSOVER
			if(this.pc >= Math.random()) {
				for(j = 0;j < half_size;++j) {
					//From first_parent
					tmp.set(j, this.inhabitants.get(first_parent).getIds().get(j));
					//From second_parent
					tmp.set(half_size+j, this.inhabitants.get(second_parent).getIds().get(half_size+j));
				}
			}
			//CROSSOVER
			else
				for(j = 0;j < half_size;++j) {
					//From first_parent
					tmp.set(half_size+j, this.inhabitants.get(first_parent).getIds().get(half_size+j));
					//From second_parent
					tmp.set(j, this.inhabitants.get(second_parent).getIds().get(j));
				}
			this.inhabitants.get(i).setIds(tmp);
			
			//MUTATION
			if(this.pm >= Math.random()) {
				for(j = 0;j < half_size;++j) {
					tmp.set(j, this.inhabitants.get(i).getIds().get(half_size+j));
				}
				for(j = 0;j < half_size;++j) {
					tmp.set(half_size+j, this.inhabitants.get(i).getIds().get(j));
				}
				this.inhabitants.get(i).setIds(tmp);
			}
		}
	}

	public List<Chromosome> getInhabitants() {
		return inhabitants;
	}
	
	public List<snack> getObjects() {
		return snacks;
	}

	int getSize() {
		return size;
	}

	double getPc() {
		return pc;
	}

	double getPm() {
		return pm;
	}
	
}
