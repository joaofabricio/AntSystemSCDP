package antsystem;

import setcovering.Column;

public class Pheromone {
	
	private Column column;

	public Pheromone(Column column) {
		this.column = column;
	}
	
	public Double value;
	
	public void increment() {
		value++;
	}
	
	public void evapore() {
		value--;
	}

	public Column getColumn() {
		return column;
	}
	
}
