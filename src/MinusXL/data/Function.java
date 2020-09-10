package MinusXL.data;

import MinusXL.view.PromptMessage;

public class Function {
	private Object result = "";
	private String str;
	private int rows_1[];
	private int cols_1[];
	private CellTable cellTable;
	private int countCellInPut;
	
	public Function(String str, int rows_1[], int cols_1[], CellTable cellTable, int countCellInPut) {
		this.str = str;
		this.rows_1 = rows_1;
		this.cols_1 = cols_1;
		this.cellTable = cellTable;
		this.countCellInPut = countCellInPut;
		functionManager();
	}
		
	public void functionManager() {
			
			if (countCellInPut == -1) {
				return;
			}
			
			switch (str) {
			case ("abs"): 
				if (countCellInPut != 2) {
					fail();
					return;
				}
				result = abs();
				break;
			case ("cos"): 
				if (countCellInPut != 2) {
					fail();
					return;
				}
				result = cos();
				break;
			case ("sin"): 
				if (countCellInPut != 2) {
					fail();
					return;
				}
				result = sin();
				break;
			case ("tan"): 
				if (countCellInPut != 2) {
					fail();
					return;
				}
				result = tan();
				break;
			case ("pow"): 
				if (countCellInPut != 3) {
					fail();
					return;
				}
				result = pow();
				break;
			case ("sum"):
				if (countCellInPut < 3) {
					fail();
					return;
				}
				result = sum();
				break;
			case ("mult"): 
				if (countCellInPut < 3) {
					fail();
					return;
				}
				result = mult();
				break;
			case ("log"): 
				if (countCellInPut != 2) {
					fail();
					return;
				}
				result = log();
				break;
			case ("log10"): 
				if (countCellInPut != 2) {
					fail();
					return;
				}
				result = log10();
				break;
			case ("and"):
				if (countCellInPut != 3) {
					fail();
					return;
				}
				result = and();
				break;
			case ("or"):
				if (countCellInPut != 3) {
					fail();
					return;
				}
				result = or();
				break;
			case("not"):
				if (countCellInPut != 3) {
					fail();
					return;
				}
				result = not();
				break;
			case ("xor"): 
				if (countCellInPut != 3) {
					fail();
					return;
				}
				result = xor();
				break;
			case ("max"): 
				if (countCellInPut < 3) {
					fail();
					return;
				}
				result = max();
				break;
			case ("Min"): 
				if (countCellInPut < 3) {
					fail();
					return;
				}
				result = min();
				break;
			case ("Mean"): 
				if (countCellInPut < 3) {
					fail();
					return;
				}
				result = mean();
				break;
			case ("Median"): 
				if (countCellInPut < 3) {
					fail();
					return;
				}
				result = median();
				break;
			case ("Stddev"): 
				if (countCellInPut < 3) {
					fail();
					return;
				}
				result = stddev();
				break;
			case ("Concat"): 
				if (countCellInPut != 3) {
					fail();
					return;
				}
				result = concat();
				break;
			case ("includes"):
				if (countCellInPut != 3) {
					fail();
					return;
				}
				result = includes();
				break;
			case ("Trim"):
				if (countCellInPut != 2) {
					fail();
					return;
				}
				result = trim();
				break;
		case ("Remove"):
			if (countCellInPut != 3) {
				fail();
				return;
				
			}
			result = remove();
			break;
		default:
			break;
		}
	}
	
	
	
	public Object abs() {
		double resultAbs;
		
		if (cellTable.getCell(rows_1[0], cols_1[0]).getMorphCell().equals("number")) {
			resultAbs = Math.abs(Double.parseDouble(cellTable.getValueAt(rows_1[0], cols_1[0]).toString()));
			
			if (isInteger(resultAbs)) {
				return (int) resultAbs;
			} else {
				return resultAbs;
			}
		} else {
			return "#VALUE("+str+")";
		}
	}
	
	public Object cos() {
		double resultCos;
		
		if (cellTable.getCell(rows_1[0], cols_1[0]).getMorphCell().equals("number")) {
			resultCos = Math.cos(Math.toRadians(Double.parseDouble(cellTable.getValueAt(rows_1[0], cols_1[0]).toString())));
			if (isInteger(resultCos)) {
				return (int) resultCos;
			} else {
				return resultCos;
			}
		} else return "#VALUE("+str+")";
	}

	public Object sin() {
		double resultSin;
		
		if (cellTable.getCell(rows_1[0], cols_1[0]).getMorphCell().equals("number")) {
			resultSin = Math.sin(Math.toRadians(Double.parseDouble(cellTable.getValueAt(rows_1[0], cols_1[0]).toString())));
			if (isInteger(resultSin)) {
				return (int) resultSin;
			} else {
				return resultSin;
			}
		} else {
			return "#VALUE("+str+")";
		}
	}

	public Object tan() {
		double resultTan;
		
		if (cellTable.getCell(rows_1[0], cols_1[0]).getMorphCell().equals("number")) {
			resultTan = Math.tan(Math.toRadians(Double.parseDouble(cellTable.getValueAt(rows_1[0], cols_1[0]).toString())));
			if (isInteger(resultTan)) {
				return (int) resultTan;
			} else {
				return resultTan;
			}
		} else {
			return "#VALUE("+str+")";
		}
	}
	
	public Object pow() {
		double resultPow;
		
		if (checkCellNumber() < 2) {
			return "#VALUE("+str+")";
		}
		if (cellTable.getCell(rows_1[0], cols_1[0]).getMorphCell().equals("number") && cellTable.getCell(rows_1[1], cols_1[1]).getMorphCell().equals("number")) {
			resultPow = Math.pow(Double.parseDouble(cellTable.getValueAt(rows_1[0], cols_1[0]).toString()), Double.parseDouble(cellTable.getValueAt(rows_1[1], cols_1[1]).toString()));
			if(isInteger(resultPow)) {
				return (int) resultPow;
			}
			return resultPow;
		}
		
		return "#VALUE("+str+")";
		
	}
	
	public Object sum() {
		double resultSum = 0;
		
		if (checkCellNumber() < 1) {
			return "#VALUE("+str+")";
		}
		
		for (int i = 0; i < countCellInPut - 1; i++) {
			if (cellTable.getCell(rows_1[i], cols_1[i]).getMorphCell().equals("number")) {
				resultSum += (Double.parseDouble(cellTable.getValueAt(rows_1[i], cols_1[i]).toString()));
			}
		}
		if (isInteger(resultSum)) {
			return (int) resultSum; 
		} else {
			return  resultSum;
		}
	}
	
	public Object mult() {
		double resultMult = 1;
		
		if (checkCellNumber() < 2) {
			return "#VALUE("+str+")";
		}
		
		for (int i = 0; i < countCellInPut - 1; i++) {
			if (cellTable.getCell(rows_1[i], cols_1[i]).getMorphCell().equals("number")) {
				resultMult *= (Double.parseDouble(cellTable.getValueAt(rows_1[i], cols_1[i]).toString()));
			}
		}
		if (isInteger(resultMult)) {
			return (int) resultMult;
		} else {
			return resultMult;
		}
	}
	
	public Object log() {
		double log ;
				
		if (cellTable.getCell(rows_1[0], cols_1[0]).getMorphCell().equals("number")) {
			if (!(Double.parseDouble(cellTable.getValueAt(rows_1[0], cols_1[0]).toString()) > 0)) {
				return "#VALUE("+str+")";
			}
			log = Math.log(Double.parseDouble(cellTable.getValueAt(rows_1[0], cols_1[0]).toString()));
			if (isInteger(log)) {
				return (int) log;
			} else {
				return log;
			}
		} else {
			return "#VALUE("+str+")";
		}
	}
	
	public Object log10() {
		double resultLog10 = 0;
		
		if (cellTable.getCell(rows_1[0], cols_1[0]).getMorphCell().equals("number")) {
			if (!(Double.parseDouble(cellTable.getValueAt(rows_1[0], cols_1[0]).toString()) > 0)) {
				return "#VALUE("+str+")"; /*negative number*/
			}
			resultLog10 = Math.log10(Double.parseDouble(cellTable.getValueAt(rows_1[0], cols_1[0]).toString()));
			
			if (isInteger(resultLog10)) {
				return (int) resultLog10;
			} else {
				return resultLog10;
			}
		} else {
			return "#VALUE("+str+")";
		}
	}
	
	public Object and() {
		boolean bool_1;
		boolean bool_2;
		
		if (cellTable.getCell(rows_1[0], cols_1[0]).getMorphCell().equals("bool") && cellTable.getCell(rows_1[1], cols_1[1]).getMorphCell().equals("bool")) {
			bool_1 = Boolean.valueOf(cellTable.getValueAt(rows_1[0], cols_1[0]).toString());
			bool_2 = Boolean.valueOf(cellTable.getValueAt(rows_1[1], cols_1[1]).toString());
			return (bool_1 && bool_2);
		} else {
			return "#VALUE("+str+")";
		}
	}
	
	public Object or() {
		boolean bool_1;
		boolean bool_2;
		
		if (cellTable.getCell(rows_1[0], cols_1[0]).getMorphCell().equals("bool") && cellTable.getCell(rows_1[1], cols_1[1]).getMorphCell().equals("bool")) {
			bool_1 = Boolean.valueOf(cellTable.getValueAt(rows_1[0], cols_1[0]).toString());
			bool_2 = Boolean.valueOf(cellTable.getValueAt(rows_1[1], cols_1[1]).toString());
			return (bool_1 || bool_2);
		} else {
			return "#VALUE("+str+")";
		}
	}
	
	public Object not() {
		boolean bool_1;
		
		if (cellTable.getCell(rows_1[0], cols_1[0]).getMorphCell().equals("bool")) {
			bool_1 = Boolean.valueOf(cellTable.getValueAt(rows_1[0], cols_1[0]).toString());
			return !bool_1;
		} else {
			return "#VALUE("+str+")";
		}
	}
	
	public Object xor() {
		boolean bool_1 ;
		boolean bool_2;
		
		if (cellTable.getCell(rows_1[0], cols_1[0]).getMorphCell().equals("bool") && cellTable.getCell(rows_1[1], cols_1[1]).getMorphCell().equals("bool")) {
			bool_1 = Boolean.valueOf(cellTable.getValueAt(rows_1[0], cols_1[0]).toString());
			bool_2 = Boolean.valueOf(cellTable.getValueAt(rows_1[1], cols_1[1]).toString());
			return (!bool_1 && bool_2) || (bool_1 && !bool_2);
		} else {
			return "#VALUE("+str+")";
		}
	}
	
	public Object max() {
		double resultMax = -999999999;
		
		if (checkCellNumber() < 2) {
			return "#VALUE("+str+")";
		}
		
		for (int i = 0; i < countCellInPut - 1; i++) {
			if (cellTable.getCell(rows_1[i], cols_1[i]).getMorphCell().equals("number")) {
				if ((Double.parseDouble(cellTable.getValueAt(rows_1[i], cols_1[i]).toString()) > resultMax)) {
					resultMax = Double.parseDouble(cellTable.getValueAt(rows_1[i], cols_1[i]).toString());
				}
			}
		}
		
		if (isInteger(resultMax)) {
			return (int) resultMax;
		} else {
			return resultMax;
		}
	}
	
	public Object min() {
		double resultMin = 999999999;
		
		if (checkCellNumber() < 2) {
			return "#VALUE("+str+")";
		}
		
		for (int i = 0; i < countCellInPut - 1; i++) {
			if (cellTable.getCell(rows_1[i], cols_1[i]).getMorphCell().equals("number")) {
				if ((Double.parseDouble(cellTable.getValueAt(rows_1[i], cols_1[i]).toString()) < resultMin)) {
					resultMin = Double.parseDouble(cellTable.getValueAt(rows_1[i], cols_1[i]).toString());
				}
			}
		}
		
		if (isInteger(resultMin)) {
			return (int) resultMin;
		} else {
			return resultMin;
		}
	}
	
	public Object mean() {
		double resultMean = 0;
		int countResultMean = 0;
		
		if (checkCellNumber() < 2) {
			return "#VALUE("+str+")";
		}
		
		for (int i = 0; i < countCellInPut - 1; i++) {
			if (cellTable.getCell(rows_1[i], cols_1[i]).getMorphCell().equals("number")) {
				resultMean += Double.parseDouble(cellTable.getValueAt(rows_1[i], cols_1[i]).toString());
				countResultMean++;
			}
		}
		
		if (countResultMean == 0) {
			return "";
		} else if (isInteger(resultMean/countResultMean)) {
			return (int) resultMean/countResultMean;
		} else {
			return resultMean/countResultMean;
		}
	}
	
	public Object median() {
		double resultMedian = 0;
		int countMedian = 0;
		double median[] = new double[100];
		
		if (checkCellNumber() < 2) {
			return "#VALUE("+str+")";
		}
			
		for (int i = 0; i < countCellInPut - 1; i++) {
			if (cellTable.getCell(rows_1[i], cols_1[i]).getMorphCell().equals("number")) {
				median[countMedian] = Double.parseDouble(cellTable.getValueAt(rows_1[i], cols_1[i]).toString());
				countMedian++;
			}
		}
		
		quickSort(median, 0, countMedian-1);
		
		if (countMedian % 2 == 0) {
			resultMedian = (median[(countMedian / 2) - 1] + median[countMedian / 2]) / 2;
		} else {
			resultMedian = median[(countMedian / 2) ];
		}
		
		if (isInteger(resultMedian)) {
			return (int) resultMedian;
		} else {
			return resultMedian;
		}
	}
	
	public Object stddev() {
		int n; 			
		double x, curentlySum = 0, resultStddev;
		if ((n = checkCellNumber()) < 2) {
			return "#VALUE("+str+")";
		}
		x = Double.parseDouble((mean().toString()));
		for (int c = 0; c < countCellInPut - 1; c++) {
			if (cellTable.getCell(rows_1[c], cols_1[c]).getMorphCell().equals("number")) {
				curentlySum += Math.pow((Double.parseDouble(cellTable.getValueAt(rows_1[c], cols_1[c]).toString()) - x), 2);
			}
		}
		resultStddev = Math.sqrt(Math.pow((double) n, -1) * curentlySum);
		if (isInteger(resultStddev)) {
			return (int) resultStddev;
		} else {
			return resultStddev;
		}
	}
	
	public Object concat() {
		
		if (cellTable.getCell(rows_1[0], cols_1[0]).getMorphCell().equals("str") && cellTable.getCell(rows_1[1], cols_1[1]).getMorphCell().equals("str")) {
			return cellTable.getValueAt(rows_1[0], cols_1[0]).toString() + cellTable.getValueAt(rows_1[1], cols_1[1]).toString();
		} else {
			return "#VALUE("+str+")";
		}
	}
	
	public Object includes() {
		
		if (cellTable.getCell(rows_1[0], cols_1[0]).getMorphCell().equals("str") && cellTable.getCell(rows_1[1], cols_1[1]).getMorphCell().equals("str")) {
			return cellTable.getValueAt(rows_1[0], cols_1[0]).toString().contains(cellTable.getValueAt(rows_1[1], cols_1[1]).toString());
		} else {
			return "#VALUE("+str+")";
		}
	}
	
	public Object trim() {
		
		if (cellTable.getCell(rows_1[0], cols_1[0]).getMorphCell().equals("str")) {
			return cellTable.getValueAt(rows_1[0], cols_1[0]).toString().replaceAll(" ", "");
		} else {
			return "#VALUE("+str+")";
		}
	}
	
	public Object remove() {
		String x, y;
		if (cellTable.getCell(rows_1[0], cols_1[0]).getMorphCell().equals("str") && cellTable.getCell(rows_1[1], cols_1[1]).getMorphCell().equals("str")) {
			x = cellTable.getValueAt(rows_1[0], cols_1[0]).toString().toUpperCase();
			y = cellTable.getValueAt(rows_1[1], cols_1[1]).toString().toUpperCase();
			return x.replaceAll(y, "");
		} else {
			return "#VALUE("+str+")";
		}
	}
	
	
	
	
	public int checkCellNumber() {
		int sizeCellNumbers = 0;
		
		for (int i = 0; i < countCellInPut - 1; i++) {
			if (cellTable.getCell(rows_1[i], cols_1[i]).getMorphCell().equals("number")) {
				sizeCellNumbers++;
			}
		}
		return sizeCellNumbers;
	}
	
	
	
	public void quickSort(double arr[], int low, int high) {
		if (arr == null || arr.length == 0)
			return;
 
		if (low >= high)
			return;
 
		// pick the pivot
		int middle = low + (high - low) / 2;
		double pivot = arr[middle];
 
		// make left < pivot and right > pivot
		int i = low, j = high;
		while (i <= j) {
			while (arr[i] < pivot) {
				i++;
			}
 
			while (arr[j] > pivot) {
				j--;
			}
 
			if (i <= j) {
				double temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				i++;
				j--;
			}
		}
 
		// recursively sort two sub parts
		if (low < j)
			quickSort(arr, low, j);
 
		if (high > i)
			quickSort(arr, i, high);
	}
	
	
	public boolean isInteger(double tester) {
		if ((int) tester - tester == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	// Frame to wrong with input
	public void fail() {
		PromptMessage msg = new PromptMessage();
		msg.setEditorPromt("Care your input in Function");
		msg.setVisible(true);
	}
	
	/**
	 *    All gets
	 */
	public Object getResult() {
		return result;
	}
	public String getStr() {
		return str;
	}
	public int[] getRows() {
		return rows_1;
	}
	public int[] getCols() {
		return cols_1;
	}
	public CellTable getCellTable() {
		return cellTable;
	}
	public int getCountCellInPut() {
		return countCellInPut;
	}
	
}

