package setcovering;

import java.util.ArrayList;
import java.util.List;

import antsystem.Pheromone;

public class Column {
	
	private String label;
	
	private Double cost;
	
	private List<Line> lines = new ArrayList<Line>();

	private Pheromone pheromone;
	
	public Column(String rotulo, Double custo) {
		this.label = rotulo;
		this.cost = custo;
	}

	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public Double getCost() {
		return cost;
	}
	
	public void setCost(Double cost) {
		this.cost = cost;
	}
	
	public void addLine(Line l) {
		lines.add(l);
	}

	public void setPheromone(Pheromone pheromone) {
		this.pheromone = pheromone;
	}
	
	public Pheromone getPheromone() {
		return pheromone;
	}

}
