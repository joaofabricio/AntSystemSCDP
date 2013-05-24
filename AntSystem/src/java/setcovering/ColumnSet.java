package setcovering;

import java.util.ArrayList;
import java.util.List;

public class ColumnSet {
	
	private List<Column> columns = new ArrayList<Column>();
	
	private int amountColumns = 0;
	private int amountLines = 0;

	public void setAmountColumns(int amountColumns) {
		this.amountColumns = amountColumns;
	}

	public void setAmountLines(int amountLines) {
		this.amountLines = amountLines;
	}

	public int getAmountColumns() {
		return amountColumns;
	}

	public int getAmountLines() {
		return amountLines;
	}

	public void addColumn(Column column) {
		columns.add(column);
	}
	
	public List<Column> getColumns() {
		return columns;
	}
}
