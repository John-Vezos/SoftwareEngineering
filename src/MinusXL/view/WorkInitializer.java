package MinusXL.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;

import MinusXL.data.WorkBook;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class WorkInitializer extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNameWrkBk;
	private WorkBook workBook;
	
	/**
	 * Create the frame.
	 */
	public WorkInitializer(BasicFrame window) {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(WorkInitializer.class.getResource("/MinusXL/icons16x16/Word-16.png")));
		setTitle("Settings: Workbook");
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
				dispose();
			}
		});
		btnCancel.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		JButton btnOk = new JButton("OK");
		btnOk.setSelected(true);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				putOk(window);
			}
		});
		btnOk.setFont(new Font("Calibri", Font.PLAIN, 14));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 472, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(12, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(335, Short.MAX_VALUE)
					.addComponent(btnOk, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCancel)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnOk))
					.addContainerGap())
		);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblName.setToolTipText("Give name to your Workbook.");
		
		textNameWrkBk = new JTextField();
		textNameWrkBk.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					putOk(window);
			}
		});
		textNameWrkBk.setFont(new Font("Calibri", Font.PLAIN, 14));
		textNameWrkBk.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblName)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textNameWrkBk, GroupLayout.PREFERRED_SIZE, 370, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(45, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(42)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(textNameWrkBk, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(62, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
	
	/**
	 * 
	 * @param window. Press enter or click on OK JButton do some code. its the some.
	 */
	public void putOk(BasicFrame window) {
		if (textNameWrkBk.getText().equals("") == true) {
			textNameWrkBk.setText("Default_Workbook_#"+window.getDefaultWrkBk());
			window.setDefaultWrkBk();
			window.getPromptMessage().setEditorPromt("The Workbook get defautl name.\nYou didnt give name."+textNameWrkBk.getText());
			window.getPromptMessage().setVisible(true);
		}
		workBook = new WorkBook(window);
		window.getLogInPanel().add(workBook);
		workBook.show();
		window.setWorkBook(workBook);
		dispose();
		window.getArrayFrames().clear();
	}
	
	/**
	 * 
	 * @return workbook need this for get the name.
	 */
	public JTextField getTextNameWrkBk() {
		return textNameWrkBk;
	}
}
