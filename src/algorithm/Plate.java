/**
 * 
 */
package algorithm;

/**
 * @author Carlo Cena
 *
 */
public class Plate {
	
	private Population myPopulation;
	private int equal_iteration;
	private int max_weight;
	
	public Plate(Population myPopulation, int equal_iteration, int max_weight) {
		this.myPopulation = myPopulation;
		this.equal_iteration = equal_iteration;
		this.max_weight = max_weight;
	}
	
	public void StartStudy() {
		int cont=0;
		double previous_value = 0.0;
		double current_value = 0.0;
		
		this.myPopulation.computeFit(max_weight);
	
		while(cont < this.equal_iteration) {
			this.myPopulation.merging();
			this.myPopulation.computeFit(max_weight);
			current_value = this.myPopulation.getBestValue();
			if(previous_value == current_value)
				cont++;
			else {
				cont=0;
				previous_value = current_value;
			}
			//Debug
			//System.out.println(current_value+ " "+cont);
		}
		System.out.println("Value of best solution: "+this.myPopulation.getBestValue()+
							" best solution: "+this.myPopulation.getBestIds());
	}
}
