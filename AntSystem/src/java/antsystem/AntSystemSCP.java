package antsystem;

import java.util.logging.Logger;

import setcovering.ColumnSet;


public class AntSystemSCP {
	
	private static final Logger LOG = Logger.getLogger(AntSystemSCP.class.getPackage().getName());

	private Double alfa;
	
	private Double beta;
	
	private Double ro;
	
	private Integer maxIter;
	
	private Double q;
	
	private Integer antPopulation = 10;

	/**
	 * 
	 * @param alfa
	 * @param beta
	 * @param ro
	 * @param maxIter
	 * @param q
	 */
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
				ColumnSet partialSolution = ant.run(alfa, beta, q);
				LOG.fine("Partial solution: "+partialSolution);
				totalColumns.evaporePheromone(ro);
				if (partialSolution.getCost() < bestSolution.getCost()) {
					bestSolution = partialSolution;
				}
				
			}
		}
		
		return bestSolution;
	}
	
	

}
