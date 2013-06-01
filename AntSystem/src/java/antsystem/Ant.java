package antsystem;

import setcovering.Column;
import setcovering.ColumnSet;

public class Ant {
	
	private ColumnSet bestSolution = new ColumnSet();
	
	public Ant(ColumnSet totalColumns) {
	}

	public ColumnSet run() {
		
		while (!coverAllLines(bestSolution)) {
			Column column = chooseColumn();
			
			bestSolution.addColumn(column);
			
			//TODO instanciar feromonio aqui??
		}
		
		return bestSolution;
	}

	private boolean coverAllLines(ColumnSet bestSolution) {
		// TODO Auto-generated method stub
		return false;
	}

	private Column chooseColumn() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
