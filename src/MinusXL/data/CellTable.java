package MinusXL.data;

import javax.swing.table.AbstractTableModel;

public class CellTable extends AbstractTableModel {
		/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private Cell spreadsheetData[][];
		
		public CellTable() {
			spreadsheetData = new Cell[5][5];
			startCell(5, 5);
		}
		
		public CellTable(int rows, int columns) {
			spreadsheetData = new Cell[rows][columns];
			startCell(rows, columns);
		}
		
		public void startCell(int rows, int cols) {
			for (int q = 0; q < rows; q++) {
				for (int c = 0; c < cols; c++) {
					firstTimeSetValue("", q, c);
				}
			}
		}
		
		public int getRowCount() {
			return spreadsheetData.length;  
		}
		public int getColumnCount() {    
			return spreadsheetData[0].length;
		}
		public Object getValueAt(int row, int column) {
			return spreadsheetData[row][column];
		}
		public boolean isCellEditable(int row, int col) {
			return true; 
		}
		public void setValueAt(Object value, int row, int col) {
			spreadsheetData[row][col].setValue(value);
			fireTableCellUpdated(row, col);
		}
		  
		public void firstTimeSetValue(Object value, int row, int col) {
			spreadsheetData[row][col] = new Cell(value);
			fireTableCellUpdated(row, col);
		}
		
		public Cell getCell(int row, int col) {
			return spreadsheetData[row][col];
		}
		
	}

