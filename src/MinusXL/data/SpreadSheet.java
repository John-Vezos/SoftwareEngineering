package MinusXL.data;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import MinusXL.view.BasicFrame;

import javax.swing.border.LineBorder;

import java.awt.Color;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Cursor;
import javax.swing.JFrame;
public class SpreadSheet extends JInternalFrame {
	
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTable table_1;
	private final int yOffset = 25;
	private int xOffset = 20;
	private CellTable cellTable;

	/**
	 * Create the frame. call it the StartFrame. 
	 * @wbp.parser.constructor
	 */
	public SpreadSheet(BasicFrame window) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setClosable(true);
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		setTitle(window.getSpreadInit().getTextNameSpSh());
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setFrameIcon(new ImageIcon(SpreadSheet.class.getResource("/MinusXL/icons16x16/Create New-16.png")));
		setBounds(0, 0, 450, 300);
		int posSpSh = window.getWorkBook().getPosSpSh();
		if(xOffset*posSpSh > 720 || yOffset*posSpSh > 720){
			posSpSh -= 30;
		}
		setLocation(xOffset*posSpSh, yOffset*posSpSh);
		window.getWorkBook().setPosSpSh();		
		
		JSplitPane splitPane = new JSplitPane();
		getContentPane().add(splitPane, BorderLayout.CENTER);
		splitPane.setResizeWeight(0.05);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setLeftComponent(scrollPane);
		scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		
		table = new JTable();
		table.setFont(new Font("Calibri", Font.PLAIN, 14));
		table.setFillsViewportHeight(true);
		table.setEnabled(false);
		table.setRowSelectionAllowed(false);
		table.setBorder(new LineBorder(new Color(0,0,0)));
		table.setModel(new DefaultTableModel(
			new Object[(int) window.getSpreadInit().getSpinnerRows().getValue()][],
			new String[] {
				" "
			}
		));
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		splitPane.setRightComponent(scrollPane_1);
		scrollPane.getVerticalScrollBar().setModel(
                scrollPane_1.getVerticalScrollBar().getModel());
		
		cellTable = new CellTable((int) window.getSpreadInit().getSpinnerRows().getValue(),(int) window.getSpreadInit().getSpinnerCols().getValue());
		table_1 = new JTable(cellTable);
		table_1.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		table_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		table_1.setRowSelectionAllowed(false);
		table_1.setCellSelectionEnabled(true);
		table_1.setBorder(new LineBorder(new Color(0,0,0)));		

		scrollPane_1.setViewportView(table_1);
		
		/**
		 * the table left  get numbers for rows.
		 */
		for (int i = 0; i < (int) window.getSpreadInit().getSpinnerRows().getValue(); i++) {
			table.setValueAt(i+1, i, 0);
		}
		
		/**
		 * 	user get Attention for Table dimension
		 */
		boolean promtCall = false;
		String str = "The visual isnt so good because you add too many:";
		if ((int) window.getSpreadInit().getSpinnerCols().getValue() > 50) {
			str += "\n-COLS.";
			promtCall = true;
		}
		if ((int) window.getSpreadInit().getSpinnerRows().getValue() > 50) {
			str += "\n-ROWS.";
			promtCall = true;
		}
		if (promtCall == true) {
			window.getPromptMessage().setEditorPromt(str);
			window.getPromptMessage().setVisible(true);
		}
	}
	
	/**
	 * call it the Load to make one SpreadSheet with correct params.
	 * @param name
	 * @param cols
	 * @param rows
	 * @param window
	 */
	public SpreadSheet(String name, int cols, int rows, BasicFrame window) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setClosable(true);
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		setTitle(name);
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setFrameIcon(new ImageIcon(SpreadSheet.class.getResource("/MinusXL/icons16x16/Create New-16.png")));
		setBounds(0, 0, 450, 300);
		int posSpSh = window.getWorkBook().getPosSpSh();
		if(xOffset*posSpSh > 720 || yOffset*posSpSh > 720){
			posSpSh -= 30;
		}
		setLocation(xOffset*posSpSh, yOffset*posSpSh);
		window.getWorkBook().setPosSpSh();		
		
		JSplitPane splitPane = new JSplitPane();
		getContentPane().add(splitPane, BorderLayout.CENTER);
		splitPane.setResizeWeight(0.05);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setLeftComponent(scrollPane);
		scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		
		table = new JTable();
		table.setFont(new Font("Calibri", Font.PLAIN, 14));
		table.setFillsViewportHeight(true);
		table.setEnabled(false);
		table.setRowSelectionAllowed(false);
		table.setBorder(new LineBorder(new Color(0,0,0)));
		table.setModel(new DefaultTableModel(
			new Object[rows][],
			new String[] {
				" "
			}
		));
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		splitPane.setRightComponent(scrollPane_1);
		scrollPane.getVerticalScrollBar().setModel(
                scrollPane_1.getVerticalScrollBar().getModel());
		
		cellTable = new CellTable(rows, cols);
		table_1 = new JTable(cellTable);
		table_1.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		table_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		table_1.setRowSelectionAllowed(false);
		table_1.setCellSelectionEnabled(true);
		table_1.setBorder(new LineBorder(new Color(0,0,0)));		

		scrollPane_1.setViewportView(table_1);
		
		/**
		 * the table left  get numbers for rows.
		 */
		for (int i = 0; i < rows; i++) {
			table.setValueAt(i+1, i, 0);
		}
		
		/**
		 * 	user get Attention for Table dimension
		 */
		boolean promtCall = false;
		String str = "The visual isnt so good because you add too many:";
		if (cols > 50) {
			str += "\n-COLS.";
			promtCall = true;
		}
		if (rows > 50) {
			str += "\n-ROWS.";
			promtCall = true;
		}
		if (promtCall == true) {
			window.getPromptMessage().setEditorPromt(str);
			window.getPromptMessage().setVisible(true);
		}
	
	}
	
	public JTable getTable() {
		return table_1;
	}
	
	public CellTable getCellTable() {
		return cellTable;
	}

}
