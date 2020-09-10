package MinusXL.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JEditorPane;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Toolkit;

public class Helper extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public Helper(BasicFrame window) {
		setResizable(false);
		setAutoRequestFocus(false);
		setAlwaysOnTop(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Helper.class.getResource("/MinusXL/icons16x16/Info-16.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(680, 330, 552, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JEditorPane dtrpnWorkbookGive = new JEditorPane();
		dtrpnWorkbookGive.setEditable(false);
		dtrpnWorkbookGive.setEnabled(false);
		dtrpnWorkbookGive.setText("WorkBook : Give the name.\r\nSpreadSheet : You must have workbook open.\r\n- Give name and number of rows and columns.\r\nFunction : Fill the field with cells in the form (column, row) to choose the cells that will take part in the function.\r\n- Select the function.\r\nGraphs : Fill all the fields and fill the field for cells with the same way like you did it with the function.");
		dtrpnWorkbookGive.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		JButton btnDone = new JButton("Done");
		btnDone.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				window.getArrayFrames().clear();
				dispose();
				
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(dtrpnWorkbookGive, GroupLayout.PREFERRED_SIZE, 454, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(btnDone, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
							.addGap(134))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(dtrpnWorkbookGive, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnDone)
					.addContainerGap(15, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
