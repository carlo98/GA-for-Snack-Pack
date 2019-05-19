/**
 * 
 */
package algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Carlo Cena
 *
 */
class Chromosome implements Comparable<Chromosome> {
	
	private List<Integer> ids;
	private double fit;
	
	Chromosome(List<Integer> ids, double fit) {
		this.ids = new ArrayList<>();
		this.ids.addAll(ids);
		this.fit = fit;
	}

	List<Integer> getIds() {
		return ids;
	}

	double getFit() {
		return fit;
	}
	
	void setFit(double fit) {
		this.fit = fit;
	}
	
	void setIds(List<Integer> ids) {
		this.ids.clear();
		this.ids.addAll(ids);
	}

	@Override
	public int compareTo(Chromosome o) {
		if(o.getFit() == this.getFit())
			return 0;
		if(o.getFit() > this.getFit())
			return 1;
		return -1;
	}
	
}
