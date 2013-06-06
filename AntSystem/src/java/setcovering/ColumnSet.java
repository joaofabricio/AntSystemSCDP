package setcovering;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ColumnSet {
	
	private Long totalLines;

	public ColumnSet(Long totalLines) {
		this.totalLines = totalLines;
	}
	
	private Set<Column> columns = new HashSet<Column>();
	
	public void addColumn(Column column) {
		columns.add(column);
	}
	
	public Set<Column> getColumns() {
		return columns;
	}
	
	public Double getCost() {
		Double cost = 0d;
		for (Column column : columns) {
			cost += column.getCost();
		}
		return cost;
	}

	public Long getTotalLines() {
		return totalLines;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.getCost());
		sb.append("[");
		Iterator<Column> i = columns.iterator();
		while(i.hasNext()) {
			sb.append(i.next()).append(",");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append("]");
		return sb.toString();
	}
	
}
