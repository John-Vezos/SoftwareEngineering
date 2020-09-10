package MinusXL.data;

import javax.swing.JInternalFrame;
import javax.swing.border.EmptyBorder;

import MinusXL.view.BasicFrame;

import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JDesktopPane;
import java.awt.SystemColor;
import java.util.ArrayList;

public class WorkBook extends JInternalFrame {
	
	private static final long serialVersionUID = 1L;
	private int defaultSpSh;
	private JDesktopPane wrkBkPanel;
	private int posSpSh;
	private ArrayList<SpreadSheet> arraySpSh = new ArrayList<SpreadSheet>();
	private ArrayList<String> arrayNameSpSh = new ArrayList<String>();

	/**
	 * Create the frame.
	 */
	public WorkBook(BasicFrame window) {
		setClosable(true);
		setFrameIcon(new ImageIcon(WorkBook.class.getResource("/MinusXL/icons16x16/Word-16.png")));
		setMaximizable(true);
		setIconifiable(true);
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setTitle(window.getWorkInit().getTextNameWrkBk().getText());
		setBounds(0, 0, 1280, 698);
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		wrkBkPanel = new JDesktopPane();
		wrkBkPanel.setBackground(SystemColor.textHighlight);
		scrollPane.setViewportView(wrkBkPanel);
	}
	
	/**
	 * 
	 * @return there i will add the spreadsheets.
	 */
	public JDesktopPane getWrkBkPanel() {
		return wrkBkPanel;
	}
	
	/**
	 * @return spreadSheet with out name in this workBook.
	 */
	public void setDefaultSpSh() {
		++defaultSpSh;
	}
	public int getDefaultSpSh() {
		return defaultSpSh;
	}
	
	/**
	 * 
	 */
	public ArrayList<SpreadSheet> getArraySpSh() {
		return arraySpSh;
	}
	public ArrayList<String> getArrayNamesSpSh() {
		return arrayNameSpSh;
	}
	/**
	 * 
	 * @return PosSpSh save the number of Spreadsheets in each Workbook for do not stack the SpreadSheets on review.
	 */
	public int getPosSpSh() {
		return posSpSh;
	}
	public void setPosSpSh() {
		++posSpSh;
	}
}
