package antsystem;

import setcovering.ColumnSet;

public final class BestSolution {
	
	private static BestSolution instance = new BestSolution();

	private BestSolution(){}

	private boolean avaible = false;
	
	private ColumnSet columnSet;

	public boolean isAvaible() {
		return avaible;
	}

	public void setAvaible(boolean avaible) {
		this.avaible = avaible;
	}

	public ColumnSet getColumnSet() {
		return columnSet;
	}

	public void setColumnSet(ColumnSet columnSet) {
		avaible = columnSet != null;
		this.columnSet = columnSet;
	}

	public static BestSolution getInstance() {
		return instance;
	}
}
