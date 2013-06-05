package antsystem;

import setcovering.ColumnSet;


public class AntSystemSCP {
	
	private Double alfa;
	
	private Double beta;
	
	private Double ro;
	
	private Integer maxIter;
	
	private Double q;
	
	private Integer antPopulation = 50;

	public AntSystemSCP(Double alfa,
						Double beta, 
						Double ro, 
						Integer maxIter, 
						Double q) {
		this.alfa = alfa;
		this.beta = beta;
		this.ro = ro;//entre 0 e 1 - evaporacao 0,7 ~ 0,8
		this.maxIter = maxIter;
		this.q = q;
	}
	
	public ColumnSet execute(ColumnSet totalColumns) {
		
		ColumnSet bestSolution = totalColumns;
		
		for (int i = 0; i < maxIter; i++) {
			for (int f = 0; f < antPopulation; f++) {
				Ant ant  = new Ant(totalColumns);
				ColumnSet partialSolution = ant.run(alfa, beta, ro, q);
				
				if (partialSolution.getCost() < bestSolution.getCost()) {
					bestSolution = partialSolution;
				}
				
			}
		}
		
		return bestSolution;
	}
	
	

}
