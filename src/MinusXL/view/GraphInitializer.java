package MinusXL.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;

import org.jfree.data.category.DefaultCategoryDataset;

import MinusXL.data.Cell;
import MinusXL.data.CellTable;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;

public class GraphInitializer extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNameGraph;
	private JTextField textAxisX;
	private JTextField textAxisY;
	private JTextField textCells;
	private int countCellInPut;
	private int cols_1[];
	private int rows_1[];
	private CellTable cellTable;
	private DefaultCategoryDataset dataset;
	private boolean blockCell = false;


	/**
	 * Create the frame.
	 */
	public GraphInitializer(BasicFrame window) {
		setResizable(false);
		setAlwaysOnTop(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(GraphInitializer.class.getResource("/MinusXL/icons16x16/Combo Chart-16.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(680, 330, 500, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Data", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JButton btnBarGraph = new JButton("BarGraph");
		btnBarGraph.setToolTipText("BarGraph");
		btnBarGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.getArrayFrames().clear();
				dispose();
				selectedItem(window);
				
				dataset = new DefaultCategoryDataset();
				changeToDouble();				
		        
				Graph graph = new Graph();
				graph.graphBar(dataset, textNameGraph.getText(), textAxisX.getText(), textAxisY.getText());
				graph.setVisible(true);
				String str = "";
				if (textNameGraph.getText().equals("")) {
					str += "The next fields is empty: Name Graph";
				}
				if (textAxisX.getText().equals("")) {
					str += "\nName axis X";
				}
				if (textAxisY.getText().equals("")) {
					str += "\nName axix Y";
				}
				window.getPromptMessage().setEditorPromt(str);
				window.getPromptMessage().setVisible(true);
			}
		});
		btnBarGraph.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		JButton btnLineGraph = new JButton("LineGraph");
		btnLineGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.getArrayFrames().clear();
				dispose();
				selectedItem(window);
				
				dataset = new DefaultCategoryDataset();
				changeToDouble();				
		        
				Graph graph = new Graph();
				graph.graphLine(dataset, textNameGraph.getText(), textAxisX.getText(), textAxisY.getText());
				graph.setVisible(true);
				String str = "";
				if (textNameGraph.getText().equals("")) {
					str += "The next fields is empty: Name Graph";
				}
				if (textAxisX.getText().equals("")) {
					str += "\nName axis X";
				}
				if (textAxisY.getText().equals("")) {
					str += "\nName axix Y";
				}
				window.getPromptMessage().setEditorPromt(str);
				window.getPromptMessage().setVisible(true);
				
			}
		});
		btnLineGraph.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(60)
					.addComponent(btnBarGraph, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
					.addComponent(btnLineGraph)
					.addGap(107))
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 165, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnBarGraph)
						.addComponent(btnLineGraph)))
		);
		
		JLabel lblNewLabel = new JLabel("Name Graph:");
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		JLabel lblNameX = new JLabel("Name axis X:");
		lblNameX.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		JLabel lblNameY = new JLabel("Name axis Y:");
		lblNameY.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		JLabel lblCells = new JLabel("Cells:");
		lblCells.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		textNameGraph = new JTextField();
		textNameGraph.setFont(new Font("Calibri", Font.PLAIN, 14));
		textNameGraph.setColumns(10);
		
		textAxisX = new JTextField();
		textAxisX.setFont(new Font("Calibri", Font.PLAIN, 14));
		textAxisX.setColumns(10);
		
		textAxisY = new JTextField();
		textAxisY.setFont(new Font("Calibri", Font.PLAIN, 14));
		textAxisY.setColumns(10);
		
		textCells = new JTextField();
		textCells.setFont(new Font("Calibri", Font.PLAIN, 14));
		textCells.setColumns(10);
		
		JEditorPane dtrpnForBlockcellPres = new JEditorPane();
		dtrpnForBlockcellPres.setEnabled(false);
		dtrpnForBlockcellPres.setEditable(false);
		dtrpnForBlockcellPres.setText("For blockCell pres this -->");
		dtrpnForBlockcellPres.setFont(new Font("Calibri", Font.BOLD, 14));
		
		JButton btnBlockcell = new JButton("BlockCell");
		btnBlockcell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				blockCell = !blockCell;
				window.getPromptMessage().setEditorPromt("blockCell is "+blockCell);
				window.getPromptMessage().setVisible(true);
			}
		});
		btnBlockcell.setFont(new Font("Calibri", Font.PLAIN, 14));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNameX)
						.addComponent(lblNameY)
						.addComponent(lblCells)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(textAxisY, Alignment.LEADING)
							.addComponent(textAxisX, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE))
						.addComponent(textCells, GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
						.addComponent(textNameGraph, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(dtrpnForBlockcellPres, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnBlockcell)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textNameGraph, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNameX)
						.addComponent(textAxisX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNameY)
						.addComponent(textAxisY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textCells, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCells))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(dtrpnForBlockcellPres, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBlockcell))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
	
	public void changeToDouble() {
		
		for (int i = 0; i < countCellInPut; i++) {		
			if (!cellTable.getCell(rows_1[i], cols_1[i]).getMorphCell().equals("number")) {
				dataset.setValue(0.0, "values", ""+i);
			} else {
				dataset.setValue(Double.parseDouble(cellTable.getValueAt(rows_1[i], cols_1[i]).toString()), "values",  ""+i);
			}
		}
		
		
	}
	
	
	public void selectedItem(BasicFrame window) {
		int i;
		if (window.getWorkBook().getArraySpSh().size() == 0) {
			return ;
		}
		for (i = 0; i < window.getWorkBook().getArraySpSh().size(); i++) {
			if (window.getWorkBook().getArraySpSh().get(i).isSelected() == true){
				break;
			}
		}
		cellTable = window.getWorkBook().getArraySpSh().get(i).getCellTable();
		
		if (window.getTextFunction() != null || !window.getTextFunction().equals("")) {
			splitTextFunction(textCells.getText());
		}
		
		
		
	}
	
	public void splitTextFunction(String textFunction) {
		cols_1 = new int[100];
		rows_1 = new int[100];
		countCellInPut = 0;
		boolean splitCellComa = false;
		char[] parts = textFunction.toCharArray();
		for (int q = 0; q < parts.length; q++) {
			if (parts[q] == ')') {
				parts[q] = ' ';
			} else if (parts[q] == '(') {
				parts[q] = ' ';
			} else if (parts[q] == ',' && splitCellComa) {
				parts[q] = ';';
				splitCellComa = false;
			} else if (parts[q] == ',' && !splitCellComa) {
				parts[q] = '~';
				splitCellComa = true;
			}
		}
		String str = "";
		for (int q = 0; q < parts.length; q++) {
			str += parts[q];
		}
		str = str.replace(" ", "");
		Scanner splitter = new Scanner(str);
		splitter.useDelimiter(";");
		String[] cell = new String[textFunction.length()];
		int cellLength = 0;
		while (splitter.hasNext()) {
			cell[cellLength] = splitter.next();
			cellLength++;
		}
		splitter.close();
		takeColsAndRows(cell, cellLength);
	}
	public void takeColsAndRows(String[] cell, int cellLength) {
		Scanner splitter;
		String helper = "";
		char[] helpCols = null;
		int[] intHelpCols;

		for (int i1 = 0; i1 < cellLength; i1++) {
			splitter = new Scanner(cell[i1]);
			splitter.useDelimiter("~");
			helper = splitter.next();
			helper = helper.toUpperCase();
			helpCols = helper.toCharArray();
			intHelpCols = new int[helpCols.length];
			for (int i2 = 0; i2 < helpCols.length; i2++) {
				if (helpCols[i2] <= 'Z' && helpCols[i2] >= 'A') {
					intHelpCols[i2] = (int) (helpCols[i2] - 'A') + 1 ;
				} else {
					fail() ;
					splitter.close();
					return ;
				}
			}
			
			for (int i2 = 0; i2 < helpCols.length; i2++) {
				if (i2 == helpCols.length-1) {
					cols_1[i1] += intHelpCols[i2];
				} else {
					cols_1[i1] += ((int) Math.pow(26, helpCols.length-1 - i2)) * (intHelpCols[i2]);
				}
			}
			cols_1[i1] -= 1;
			if (!splitter.hasNext()) {
				fail();
				splitter.close();
				return ;
			}
			helper = splitter.next();
			if (!Cell.isInt(helper.toString())) {
				fail(); 
				splitter.close();
				return ;
			}
			rows_1[i1] = Integer.parseInt(helper) - 1;
			countCellInPut++;
		}
		
		if (countCellInPut == 2 && blockCell) {
			
			int rowsBackUp[] = {rows_1[0], rows_1[1]};
			int colsBackUp[] = {cols_1[0], cols_1[1]};
			if (cols_1[0] <= cols_1[1] && rows_1[0] <= rows_1[1]) {
				int pivot = 0;
				countCellInPut -= 2;
				for (int i = colsBackUp[0]; i <= colsBackUp[1]; i++) {
					for (int c = rowsBackUp[0]; c <= rowsBackUp[1]; c++) {
						cols_1[pivot] = i;
						rows_1[pivot] = c;
						pivot++;
						countCellInPut++;
					}
				}	
			}
		}
		checkCells();
		
	}
	public void checkCells() {

		for (int c = 0; c < countCellInPut; c++) {
			if (rows_1[c] < 0 || rows_1[c] >= cellTable.getRowCount() || cols_1[c] < 0 || cols_1[c] >= cellTable.getColumnCount()) {
				fail();
				return ;
			}
		}
		
		// check for dublicate cell
		int row, col;
		for (int c = 0; c < countCellInPut; c++) {
			row = rows_1[c];
			col = cols_1[c];
			for (int i = c+1; i < countCellInPut; i++) {
				if (rows_1[i] == row && cols_1[i] == col) {
					fail();
					return;
				}
			}
		}
	}
	public void fail() {
		dispose();
		PromptMessage msg = new PromptMessage();
		msg.setEditorPromt("Care your input in Cells");
		msg.setVisible(true);
		countCellInPut = -1;
	}

}
