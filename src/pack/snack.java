package pack;

public class snack {
	
	private double weight;
	private String id;
	private double value;
	
	public snack(double weight, double value, String id) {
		this.weight = weight;
		this.value = value;
		this.id = id;
	}

	public double getWeight() {
		return weight;
	}
	
	public double getValue() {
		return value;
	}

	public String getId() {
		return id;
	}
	
	

}
