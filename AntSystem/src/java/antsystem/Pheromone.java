package antsystem;

import setcovering.Column;

public class Pheromone {
	
	public Pheromone(Column column, Ant ant) {
//		column.setPheromone(this);
	}
	
	public Double value;
	
	public void increment() {
		value++;
	}
	
	public void evapore() {
		value--;
	}

}
