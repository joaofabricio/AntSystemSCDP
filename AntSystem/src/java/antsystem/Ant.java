package antsystem;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

import setcovering.Column;
import setcovering.ColumnSet;
import setcovering.Line;

public class Ant {
	
	private static final Logger LOG = Logger.getLogger(Ant.class.getPackage().getName()); 
	
	private ColumnSet bestSolution;
	private ColumnSet totalColumns;

	private ColumnSet notChoosedColumns;
	
	public Ant(ColumnSet totalColumns) {
		this.totalColumns = totalColumns;
		notChoosedColumns = new ColumnSet(totalColumns);
		bestSolution = new ColumnSet(totalColumns.getTotalLines());
	}

	public ColumnSet run(Double alfa, Double beta, Double q) {
		
		while (!coverAllLines(bestSolution)) {
			Column column = chooseColumn(alfa, beta);
			LOG.fine("coluna escolhida: "+column);
			
			bestSolution.addColumn(column);
			
			LOG.fine("solution: "+bestSolution);
			
		}
		
		updatePheromone(q);
		
		return bestSolution;
	}

	private void updatePheromone(Double q) {
		for (Column column : bestSolution.getColumns()) {
			column.incPheromone(q, bestSolution.getCost());
		}
		
	}

	private boolean coverAllLines(ColumnSet bestSolution) {
		Set<Line> set = new HashSet<>();
		for (Column column : bestSolution.getColumns()) {
			set.addAll(column.getLines());
		}
		
		return set.size() == totalColumns.getTotalLines();
	}

	private Column chooseColumn(Double alfa, Double beta) {
		//pj =(tj^a*nj^b)/#tj^a*nj^b
		//nj=visibilidade
		
		calculateProb(alfa, beta);
		
		Column choosed = null;
		
//		while (choosed == null) {
			double randomNumber = Math.random();
			double ac = 0;
			Iterator<Column> columns = notChoosedColumns.getColumns().iterator();
			while (ac < randomNumber && columns.hasNext()) {
				choosed = columns.next();
				ac += choosed.getProb();
			}
//		}
		notChoosedColumns.getColumns().remove(choosed);
		return choosed;
	}

	private void calculateProb(Double alfa, Double beta) {
		double sumProbs = 0;
		for (Column column : totalColumns.getColumns()) {
			sumProbs += Math.pow(column.getPheromone(), alfa) +
						Math.pow(column.getVisibility(), beta);
		}
		
		for (Column column : totalColumns.getColumns()) {
			double probCol = Math.pow(column.getPheromone(), alfa) +
						  Math.pow(column.getVisibility(), beta);
			column.setProb(probCol / sumProbs);
		}
	}
	
}
