package antsystem;

import java.util.logging.Logger;

import javax.swing.JProgressBar;

import setcovering.ColumnSet;

public class AntSystemSCP implements Runnable {

	private static final Logger LOG = Logger.getLogger(AntSystemSCP.class
			.getPackage().getName());

	private Double alfa;

	private Double beta;

	private Double ro;

	private Integer maxIter;

	private Double q;

	private Integer antPopulation = 50;

	private ColumnSet totalColumns;

	private JProgressBar progressBar;

	/**
	 * 
	 * @param alfa
	 * @param beta
	 * @param ro
	 * @param maxIter
	 * @param q
	 */
	public AntSystemSCP(Double alfa, Double beta, Double ro, Integer maxIter,
			Double q) {
		this.alfa = alfa;
		this.beta = beta;
		this.ro = ro;// entre 0 e 1 - evaporacao 0,7 ~ 0,8
		this.maxIter = maxIter;
		this.q = q;
	}

	public void execute(ColumnSet totalColumns, JProgressBar p) {
		this.totalColumns = totalColumns;
		this.progressBar = p;
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		ColumnSet bestSolution = totalColumns;

//		for (int i = 0; i < maxIter; i++) {
		int i = 0;
		while (i++<maxIter) {
			for (int f = 0; f < antPopulation; f++) {

				Ant ant = new Ant(totalColumns);
				ColumnSet partialSolution = ant.run(alfa, beta, q);
				LOG.fine("Partial solution: " + partialSolution);
				totalColumns.evaporePheromone(ro);
				if (partialSolution.getCost() < bestSolution.getCost()) {
					bestSolution = partialSolution;
					i=0;
				}
				System.out.println(partialSolution.getCost().intValue()+"|min:"+bestSolution.getCost().intValue());
			}
			progressBar.setValue(i+1);
//			LOG.info("Iteração "+i+": "+bestSolution);
		}

		LOG.info(bestSolution.toString());
		BestSolution.getInstance().setColumnSet(bestSolution);
	}
}
