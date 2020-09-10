package MinusXL.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;

import MinusXL.io.WrkBkSaver;

import javax.swing.JEditorPane;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Savewarner extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public Savewarner(BasicFrame window) {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Savewarner.class.getResource("/MinusXL/icons16x16/Save-16.png")));
		setTitle("Save");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(680, 330, 500, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Message!", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				window.arrayFrames.clear();
			}
		});
		btnCancel.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.getLogInPanel().removeAll();
				window.setWorkBook(null);
				window.getLogInPanel().repaint();
				dispose();
				window.arrayFrames.clear();
				window.createWB();
			}
		});
		btnOk.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		JButton btnSaveNow = new JButton("Save now");
		btnSaveNow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new WrkBkSaver(window);
				dispose();
				window.arrayFrames.clear();
			}
		});
		btnSaveNow.setSelected(true);
		btnSaveNow.setFont(new Font("Calibri", Font.PLAIN, 14));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(203)
							.addComponent(btnSaveNow)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnOk, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 485, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnOk)
						.addComponent(btnSaveNow)))
		);
		
		JEditorPane dtrpnYourChangeData = new JEditorPane();
		dtrpnYourChangeData.setForeground(SystemColor.windowText);
		dtrpnYourChangeData.setEnabled(false);
		dtrpnYourChangeData.setEditable(false);
		dtrpnYourChangeData.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 14));
		dtrpnYourChangeData.setText("Your change data will be lost.Save now.");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(109)
					.addComponent(dtrpnYourChangeData, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(124, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(51)
					.addComponent(dtrpnYourChangeData, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(54, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
}
