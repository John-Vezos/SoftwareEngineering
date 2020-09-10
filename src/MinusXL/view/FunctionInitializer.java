package MinusXL.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionListener;

import MinusXL.data.Cell;
import MinusXL.data.Function;
import MinusXL.data.CellTable;

import java.util.Scanner;
import javax.swing.JEditorPane;

public class FunctionInitializer extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JList<Object> list;
	private JScrollPane scrollPane;
	private DefaultListModel<Object> listModel;
	private JPanel panel_1;
	private int rows_1[];
	private int cols_1[];
	private int countCellInPut;
	private CellTable cellTable;
	private String msg;
	private Function fun;
	private boolean blockCell = false;
	private JEditorPane dtrpnBlockCell;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	
	public FunctionInitializer(BasicFrame window) {
		setResizable(false);
		setTitle("Function");
		setAlwaysOnTop(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(FunctionInitializer.class.getResource("/MinusXL/icons16x16/Square Root 2-16.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(680, 330, 488, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Data", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				window.getArrayFrames().clear();
			}
		});
		btnCancel.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				putOk(window);
			}
		});
		btnOk.setFont(new Font("Calibri", Font.PLAIN, 14));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(296)
							.addComponent(btnOk, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(138)
							.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 470, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(14, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnOk))
					.addContainerGap())
		);
		
		JLabel lblSelectFunction = new JLabel("Select Function:");
		lblSelectFunction.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		listModel = new DefaultListModel<>();
		String[] messageStrings = {"Math and Trigonometric", "Logic", "Statistical", "Alphanumeric"};
		JComboBox<Object> comboBox = new JComboBox<Object>(messageStrings);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (arg0.getSource() == comboBox) {
					list.setValueIsAdjusting(true);
					JComboBox<?> cb = (JComboBox<?>)arg0.getSource();
					msg = (String)cb.getSelectedItem();
					addList(msg);					
				}
			}
		});
		comboBox.setFont(new Font("Calibri", Font.PLAIN, 14));
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JButton btnBlockcell = new JButton("BlockCell");
		btnBlockcell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				blockCell = !blockCell;
				if (blockCell) {
					dtrpnBlockCell.setText("BlockCell is :: ON");
				} else {
					dtrpnBlockCell.setText("BlockCell is :: OFF");
				}
			}
		});
		btnBlockcell.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		dtrpnBlockCell = new JEditorPane();
		dtrpnBlockCell.setFont(new Font("Calibri", Font.BOLD, 14));
		dtrpnBlockCell.setEnabled(false);
		dtrpnBlockCell.setEditable(false);
		dtrpnBlockCell.setText("BlockCell is :: OFF");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSelectFunction)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(dtrpnBlockCell, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE))
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnBlockcell)
					.addGap(8)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
					.addGap(19))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblSelectFunction)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnBlockcell))
							.addGap(11)
							.addComponent(dtrpnBlockCell, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(2))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		panel_1 = new JPanel();
		scrollPane.setViewportView(panel_1);
		
		list = new JList<Object>();
		list.setValueIsAdjusting(true);
		
		list.setVisibleRowCount(4);
		list.setLayoutOrientation(JList.VERTICAL_WRAP);
		list.setModel(listModel);
		/**
		 * Listener for items in list.
		 */
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg1) {
				if (!arg1.getValueIsAdjusting()) {	
					selectedItem(window, list.getSelectedValue().toString());
				}
			}
		});
		
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setFont(new Font("Calibri", Font.PLAIN, 14));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(list, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(223, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(list, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(61, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
	
	
	public void putOk(BasicFrame window) {
		window.getArrayFrames().clear();
		dispose();
		
		if (fun == null) {
			return;
		} else if (!fun.getResult().toString().equals("")) {
			cellTable.setValueAt(fun.getResult().toString(), rows_1[countCellInPut - 1], cols_1[countCellInPut - 1]);
			cellTable.getCell(rows_1[countCellInPut-1], cols_1[countCellInPut-1]).setResultCellFunction(fun);
		} else {
			return;
		}
		
		for (int c = 0; c < countCellInPut-1; c++) {
			cellTable.getCell(rows_1[c], cols_1[c]).getFunctionArray().add(fun);
		}
		
	}
	
	public void addList(String msg) {
		if (msg.equals("Math and Trigonometric")) {
			listModel.removeAllElements();
			listModel.addElement("abs");
			listModel.addElement("cos");
			listModel.addElement("sin");
			listModel.addElement("tan");
			listModel.addElement("pow");
			listModel.addElement("sum");
			listModel.addElement("mult");
			listModel.addElement("log");
			listModel.addElement("log10");
		} else if (msg.equals("Logic")) {
			listModel.removeAllElements();
			listModel.addElement("and");
			listModel.addElement("or");
			listModel.addElement("not");
			listModel.addElement("xor");			
		} else if (msg.equals("Statistical")) {
			listModel.removeAllElements();
			listModel.addElement("max");
			listModel.addElement("Min");
			listModel.addElement("Mean");
			listModel.addElement("Median");
			listModel.addElement("Stddev");
		} else if (msg.equals("Alphanumeric")) {
			listModel.removeAllElements();
			listModel.addElement("Concat");
			listModel.addElement("includes");
			listModel.addElement("Trim");
			listModel.addElement("Remove");
		} else {
			listModel.removeAllElements();
		}
		
	}
	
	public void selectedItem(BasicFrame window, String str) {
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
			splitTextFunction(window.getTextFunction());
		}
		
		
		fun = new Function(str, rows_1, cols_1, cellTable, countCellInPut);
		
		
		
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
			rows_1[i1] = Integer.parseInt(helper) -1;
			countCellInPut++;
		}
		
		if (countCellInPut == 3 && blockCell) {
			
			int rowsBackUp[] = {rows_1[0], rows_1[1], rows_1[2]};
			int colsBackUp[] = {cols_1[0], cols_1[1], cols_1[2]};
			if (cols_1[0] <= cols_1[1] && rows_1[0] <= rows_1[1]) {
				int pivot = 0;
				countCellInPut -= 2;
				for (int i = colsBackUp[0]; i <= colsBackUp[1]; i++ ){
					for (int c = rowsBackUp[0]; c <= rowsBackUp[1]; c++) {
						cols_1[pivot] = i;
						rows_1[pivot] = c;
						pivot++;
						countCellInPut++;
					}
				}

				rows_1[pivot] = rowsBackUp[2];
				cols_1[pivot] = colsBackUp[2];
	
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
		msg.setEditorPromt("Care your input in Function");
		msg.setVisible(true);
		countCellInPut = -1;
	}
}
