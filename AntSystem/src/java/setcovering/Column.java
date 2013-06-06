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

	private double pheromone = 0.00001d;//TODO feromonio inicial
	
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
	
	public void incPheromone(Double q, Double solutionCost) {
		double antPheromone = q / solutionCost;
//		BigDecimal evaporation = new BigDecimal(ro * pheromone);
		pheromone += antPheromone;
	}
	
	public void evaporePheromone(double ro) {
		pheromone *= ro;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cost == null) ? 0 : cost.hashCode());
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + ((lines == null) ? 0 : lines.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Column other = (Column) obj;
		if (label == null) {
			if (other.label != null) {
				return false;
			}
		} else if (!label.equals(other.label)) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		return label;
	}
}
