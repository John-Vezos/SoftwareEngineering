package MinusXL.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;

import MinusXL.data.SpreadSheet;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class SpreadInitializer extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNameSpSh;
	private JSpinner spinnerCols;
	private JSpinner spinnerRows;
	private SpreadSheet spreadSheet;
	
	/**
	 * Create the frame.
	 */
	public SpreadInitializer(BasicFrame window) {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(SpreadInitializer.class.getResource("/MinusXL/icons16x16/Create New-16.png")));
		setTitle("Settings: Spreadsheet");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(680, 330, 500, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Data", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancel.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		JButton btnOk = new JButton("OK");
		btnOk.setSelected(true);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PutOk(window);
			}
		});
		btnOk.setFont(new Font("Calibri", Font.PLAIN, 14));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnOk, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 475, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnOk))
					.addContainerGap())
		);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setToolTipText("Name for Spreadsheet.");
		lblName.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		textNameSpSh = new JTextField();
		textNameSpSh.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					PutOk(window);
			}
		});
		textNameSpSh.setFont(new Font("Calibri", Font.PLAIN, 14));
		textNameSpSh.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Cols:");
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		spinnerCols = new JSpinner();
		spinnerCols.setFont(new Font("Calibri", Font.PLAIN, 14));
		spinnerCols.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		
		JLabel lblRows = new JLabel("Rows:");
		lblRows.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		spinnerRows = new JSpinner();
		spinnerRows.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinnerRows.setFont(new Font("Calibri", Font.PLAIN, 14));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel)
								.addComponent(lblName))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(textNameSpSh, GroupLayout.PREFERRED_SIZE, 366, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(spinnerRows, Alignment.LEADING)
									.addComponent(spinnerCols, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))))
						.addComponent(lblRows))
					.addContainerGap(45, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textNameSpSh, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblName))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(spinnerCols, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRows)
						.addComponent(spinnerRows, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
	/**
	 * 
	 * @param window. This code called from Enter and click on OK.
	 */
	public void PutOk(BasicFrame window) {
		if (window.getWorkBook() == null || window.getWorkBook().isClosed()) {				/*mporei otan emfanistei to frame WorkInit na kleisei to workBook.*/
			window.getPromptMessage().setEditorPromt("Please open one workBook first.");
			window.getPromptMessage().setVisible(true);
			dispose();
			return ;
		}
		boolean promtCall = false;
		String str = "";
		if (textNameSpSh.getText().equals("") == true)	{
			str = "Name, ";
			promtCall = true;
			textNameSpSh.setText("Default_Spreadsheet_#"+window.getWorkBook().getDefaultSpSh());
			window.getWorkBook().setDefaultSpSh();
		}
		if ((int) spinnerCols.getValue() == 0) {
			str += "Cols, ";
			promtCall = true;
			spinnerCols.setModel(new SpinnerNumberModel(new Integer(5), new Integer(0), null, new Integer(1)));
		}
		if ((int) spinnerRows.getValue() == 0) {
			str += "Rows, ";
			promtCall = true;
			spinnerRows.setModel(new SpinnerNumberModel(new Integer(5), new Integer(0), null, new Integer(1)));
		}
		if (promtCall == true) {
			window.getPromptMessage().setEditorPromt(str+"get default value(s).");
			window.getPromptMessage().setVisible(true);
		}
		
		// Ψάχνει αν υπάρχει στο ίδιο workBook spreadSheet με το ίδιο όνομα. 
		for (int i = 0; i < window.getWorkBook().getArrayNamesSpSh().size(); i++) {
			if ( window.getWorkBook().getArrayNamesSpSh().get(i).equals(textNameSpSh.getText())) {
				window.getPromptMessage().setEditorPromt("Spreadsheet with this name int the some Workbook is already exists.");
				window.getPromptMessage().setVisible(true);
				return ;
			}
		}
		spreadSheet = new SpreadSheet(window);
		window.getWorkBook().getWrkBkPanel().add(spreadSheet);
		spreadSheet.show();
		dispose();
		window.getArrayFrames().clear();
		window.getWorkBook().getArrayNamesSpSh().add(textNameSpSh.getText());
		window.getWorkBook().getArraySpSh().add(spreadSheet);
	}
	
	public String getTextNameSpSh() {
		return textNameSpSh.getText();
	}
	
	public JSpinner getSpinnerCols() {
		return spinnerCols;
	}
	
	public JSpinner getSpinnerRows() {
		return spinnerRows;
	}
}
