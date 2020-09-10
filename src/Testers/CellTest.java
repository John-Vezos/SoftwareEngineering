package Testers;

import org.junit.Test;

import MinusXL.data.Cell;

public class CellTest {

	@Test
	public void test() {
		Object value = "500";
	
		
		Cell cell = new Cell(value);
		System.out.println("The value = "+cell.getValue()+"\nThe type Cell = "+cell.getMorphCell());
		cell.setValue("a");
		System.out.println("The value = "+cell.getValue()+"\nThe type Cell = "+cell.getMorphCell());
		cell.setValue("FALSE");
		System.out.println("The value = "+cell.getValue()+"\nThe type Cell = "+cell.getMorphCell());
		cell.setValue("");
		System.out.println("The value = "+cell.getValue()+"\nThe type Cell = "+cell.getMorphCell());
	}

}
