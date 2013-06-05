package setcovering;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Column {
	
	private String label;
	
	private Double cost;
	
	private List<Line> lines = new ArrayList<Line>();

	private Long totalLines;
	
	private double prob;

	private double pheromone = 1;//TODO feromonio inicial
	
	public Column(String rotulo, Double custo, Long totalLines) {
		this.label = rotulo;
		this.cost = custo;
		this.totalLines = totalLines;
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
	
	public void incPheromone(Double q, Double solutionCost, double ro) {
		double antPheromone = q / solutionCost;
		//ro*pheromone = evaporação
		pheromone = (ro * pheromone) + antPheromone;
	}
	
	public double getPheromone() {
		return pheromone;
	}

	public Double getVisibility() {
		return new Double(lines.size() / totalLines);
	}

	public double getProb() {
		return prob;
	}
	
	public void setProb(double prob) {
		this.prob = prob;
	}

	public Collection<? extends Line> getLines() {
		return lines;
	}
}
