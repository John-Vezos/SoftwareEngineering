package MinusXL.data;

import java.util.ArrayList;

public class Cell extends Object{
	private Object value;
	private String morphCell;
	ArrayList<Function> functionArray = new ArrayList<Function>();
	private Function resultCellFunction;
	private boolean resultFromFunction;
	

	public Cell(Object value) {
		this.value = value;
		resultFromFunction = false;
		resultCellFunction = null;
		setMorphCell("str");
	}
	
	public void setValue(Object value) {
		this.value = value;
		if (value == null) {
			return;
		}
		if (isInt(value.toString()) || isDouble(value.toString())) {
			setMorphCell("number");
		} else if (value.toString().toUpperCase().equals("FALSE") || value.toString().toUpperCase().equals("TRUE")) {
			setMorphCell("bool");
			this.value = value.toString().toUpperCase();
		} else {
			setMorphCell("str");
		}
		removeFunctionFromCell();
		cellUpdate();
	}
	
	public void removeFunctionFromCell() {
		if (resultCellFunction != null && !resultFromFunction) {
			for (int c = 0; c < resultCellFunction.getCountCellInPut() - 1; c++) {
				resultCellFunction.getCellTable().getCell(resultCellFunction.getRows()[c], resultCellFunction.getCols()[c]).getFunctionArray().remove(resultCellFunction);
			}
			resultCellFunction = null;
		}
		return;
	}
	
	public void cellUpdate() {
		if (!functionArray.isEmpty()) {
			for (int c = 0; c < functionArray.size(); c++) {
				if (functionArray.get(c) != null) {
					Function editResult = new Function(functionArray.get(c).getStr(), functionArray.get(c).getRows(), functionArray.get(c).getCols(), functionArray.get(c).getCellTable(), functionArray.get(c).getCountCellInPut());
					functionArray.get(c).getCellTable().getCell( functionArray.get(c).getRows()[functionArray.get(c).getCountCellInPut()-1], functionArray.get(c).getCols()[functionArray.get(c).getCountCellInPut()-1]).setResultFromFunction(true);
					functionArray.get(c).getCellTable().setValueAt(editResult.getResult().toString(), functionArray.get(c).getRows()[functionArray.get(c).getCountCellInPut()-1], functionArray.get(c).getCols()[functionArray.get(c).getCountCellInPut()-1]);
					functionArray.get(c).getCellTable().getCell( functionArray.get(c).getRows()[functionArray.get(c).getCountCellInPut()-1], functionArray.get(c).getCols()[functionArray.get(c).getCountCellInPut()-1]).setResultFromFunction(false);
				}
			}
		}
		return;
	}
	
	public ArrayList<Function> getFunctionArray() {
		return functionArray;
	}
	
	
	public static boolean isDouble(String str)  
	{  
		try {  
			Double.parseDouble(str);
			} catch (NumberFormatException nfe) {  
				return false;  
			}  
		return true;  
	}
	
	public static boolean isInt(String str) {  
		try {  
			Integer.parseInt(str);
		} catch (NumberFormatException nfe) {  
			return false;  
		}  
		return true;  
	}
	
	
	public void setMorphCell(String str) {
		morphCell = str;
	}
	
	public String getMorphCell() {
		return morphCell;
	}
	
	public String toString() {
		return (String) value;
	}
	
	public void setResultCellFunction(Function fun) {
		resultCellFunction = fun;
	}
	public void setResultFromFunction(boolean bool) {
		resultFromFunction = bool;
	}
	public Object getValue() {
		return value;
	}
	
}