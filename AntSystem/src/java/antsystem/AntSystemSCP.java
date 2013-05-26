package antsystem;

import setcovering.ColumnSet;


public class AntSystemSCP {
	
	private Double alfa;
	
	private Double beta;
	
	private Double ro;
	
	private Integer maxIter;
	
	private Long q;
	
	private Integer antPopulation = 50;

	public AntSystemSCP(Double alfa,
						Double beta, 
						Double ro, 
						Integer maxIter, 
						Long q) {
		this.alfa = alfa;
		this.beta = beta;
		this.ro = ro;
		this.maxIter = maxIter;
		this.q = q;
	}
	
	public ColumnSet execute(ColumnSet totalColumns) {
		
		ColumnSet bestSolution = totalColumns;
		
		for (int i = 0; i < maxIter; i++) {
			for (int f = 0; f < antPopulation; f++) {
				Ant ant  = new Ant(totalColumns);
				ColumnSet partialSolution = ant.run();
				
				if (partialSolution.getCost() < bestSolution.getCost()) {
					bestSolution = partialSolution;
				}
			}
		}
		
		return bestSolution;
	}
	
	

}
