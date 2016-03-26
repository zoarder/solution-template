package com.tigerit.exam;

import java.util.ArrayList;
import java.util.Arrays;

public class TableData {
	private String tableName;
	private String tableShortName;
	private int nRow;
	private int nColumn;
	private ArrayList<String> columnNames;
	private int data[][];
	
	public TableData() {
		tableName = "";
		tableShortName = "";
		nRow = 0;
		nColumn = 0;
		columnNames = new ArrayList<String>();
		data = new int[101][101];
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableShortName() {
		return tableShortName;
	}

	public void setTableShortName(String tableShortName) {
		this.tableShortName = tableShortName;
	}

	public int getnRow() {
		return nRow;
	}

	public void setnRow(int nRow) {
		this.nRow = nRow;
	}

	public int getnColumn() {
		return nColumn;
	}

	public void setnColumn(int nColumn) {
		this.nColumn = nColumn;
	}

	public ArrayList<String> getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(ArrayList<String> columnNames) {
		this.columnNames = columnNames;
	}

	public int[][] getData() {
		return data;
	}

	public void setData(int[][] data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "TableData [tableName=" + tableName + ", tableShortName=" + tableShortName + ", nRow=" + nRow
				+ ", nColumn=" + nColumn + ", columnNames=" + columnNames + ", data=" + Arrays.toString(data) + "]";
	}
}
