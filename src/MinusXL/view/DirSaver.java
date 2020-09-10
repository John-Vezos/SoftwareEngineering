package MinusXL.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JEditorPane;
import javax.swing.border.TitledBorder;

import MinusXL.io.FileSaver;

import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class DirSaver extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textSaveDir;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public DirSaver(JFrame frame, PromptMessage promptMessage, BasicFrame window) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(DirSaver.class.getResource("/MinusXL/icons16x16/Wolf-16.png")));
		setTitle("MinusXL Launcher");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(650, 300, 620, 280);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("CheckBoxMenuItem.border"), "Select a workspace", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		
		JEditorPane dtrpnEclipseStoresYour = new JEditorPane();
		dtrpnEclipseStoresYour.setEditable(false);
		dtrpnEclipseStoresYour.setFont(new Font("Calibri", Font.PLAIN, 14));
		dtrpnEclipseStoresYour.setText("MinusXL stores your projects in a folder called a workspace.\r\nChoose a workspace folder to use for this session.");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(dtrpnEclipseStoresYour, GroupLayout.PREFERRED_SIZE, 508, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(106, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(dtrpnEclipseStoresYour, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
		);
		panel.setLayout(gl_panel);
		
		JLabel lblWorkspace = new JLabel("Workspace:");
		lblWorkspace.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		textSaveDir = new JTextField();
		textSaveDir.setFont(new Font("Calibri", Font.PLAIN, 14));
		textSaveDir.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					putOk(frame, promptMessage, window);
			}
		});
		textSaveDir.setColumns(10);
		textSaveDir.setText("C:\\");
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnCancel.setFont(new Font("Calibri", Font.PLAIN, 14));
		JButton btnOk = new JButton("OK");
		btnOk.setSelected(true);
		btnOk.setMnemonic(KeyEvent.VK_ENTER);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				putOk(frame, promptMessage, window);
			}
		});
		btnOk.setFont(new Font("Calibri", Font.PLAIN, 14));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblWorkspace)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textSaveDir, GroupLayout.PREFERRED_SIZE, 434, GroupLayout.PREFERRED_SIZE)
							.addGap(101))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnOk, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
							.addGap(29))))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 614, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(20, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblWorkspace)
						.addComponent(textSaveDir, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnOk))
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
	}
	/**
	 * 
	 * @param frame
	 * @param promtMessage 
	 * when you have got open workBook warning for save your Spreadsheets.
	 */
	public void putOk(JFrame frame, PromptMessage promptMessage, BasicFrame window) {
		FileSaver fileSaver = new FileSaver(textSaveDir, promptMessage);
		window.setTextSaveDir(textSaveDir.getText()+"\\MinusXL");
		if (fileSaver.getSaveDone() == true) {
			dispose();
			frame.setVisible(true);
		} else {
			promptMessage.setVisible(true);
		}
	}

	
}
