package Testers;

import org.junit.Test;

import MinusXL.data.CellTable;

public class CellTableTest {

	@Test
	public void test() {
		
		CellTable cellTable = new CellTable();
		System.out.println("The Rows = "+cellTable.getRowCount()+"\nThe columns = "+cellTable.getColumnCount());
		CellTable cellTable_1 = new CellTable(20, 30);
		System.out.println("The Rows = "+cellTable_1.getRowCount()+"\nThe columns = "+cellTable_1.getColumnCount());
		
	}

}
