package MinusXL.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;

import MinusXL.data.SpreadSheet;
import MinusXL.io.SpShLoader;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoadInitializer extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textLoadSpSh;
	private String str;
	private	String data;
	private	char charData[] = null;
	private	int rows = 0;
	private	int cols = 0;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public LoadInitializer(BasicFrame window) {
		setResizable(false);
		setTitle("Settings: Load Spreadsheet");
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoadInitializer.class.getResource("/MinusXL/icons32x32/Add List-32.png")));
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
				window.getArrayFrames().clear();
			}
		});
		btnCancel.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		JButton btnOk = new JButton("OK");
		btnOk.setSelected(true);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				putOk(window);
			}
		});
		btnOk.setFont(new Font("Calibri", Font.PLAIN, 14));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 463, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(11, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(290, Short.MAX_VALUE)
					.addComponent(btnOk, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnOk))
					.addContainerGap())
		);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblName.setToolTipText("Spreadsheet");
		
		textLoadSpSh = new JTextField();
		textLoadSpSh.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					putOk(window);
				}
			}
		});
		textLoadSpSh.setFont(new Font("Calibri", Font.PLAIN, 14));
		textLoadSpSh.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblName)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textLoadSpSh, GroupLayout.PREFERRED_SIZE, 321, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(78, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(26)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(textLoadSpSh, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(44, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}

	public void putOk(BasicFrame window) {
		if (window.getWorkBook() == null || window.getWorkBook().isClosed()) {
			window.getPromptMessage().setEditorPromt("You need to open one WorkBook first.\r\nTry again!");
			window.getPromptMessage().setVisible(true);
			dispose();
			return ;
		}
		dispose();
		
		boolean checkDir;
		Path xPath = Paths.get(window.getTextSaveDir());
		checkDir = Files.exists(xPath);
		
		if (!checkDir) {
			window.getPromptMessage().setEditorPromt("Load fail.You have delete the directory to save all your projects.");
			window.getPromptMessage().setVisible(true);
			return ;
		}	
		
		findFile(textLoadSpSh.getText()+".csv", new File(window.getTextSaveDir()));
		File file = new File(str);
		window.getPromptMessage().setEditorPromt("Found it ->"+str);
		window.getPromptMessage().setVisible(true);
		
		findFileSize(file); 
		
		SpreadSheet spreadSheet = new SpreadSheet(textLoadSpSh.getText(), cols, rows, window);
		window.getWorkBook().getWrkBkPanel().add(spreadSheet);
		window.getWorkBook().getArraySpSh().add(spreadSheet);
		window.getWorkBook().getArrayNamesSpSh().add(textLoadSpSh.getText());
		new SpShLoader(window, str, cols, rows, window.getWorkBook().getArraySpSh().size()-1);
		spreadSheet.show();
		window.getArrayFrames().clear();		
	}
	/**
	 * 
	 * @param Search for name. 
	 * @param file search in there.
	 */
	public void findFile(String name, File file)
    {
        File[] list = file.listFiles();
        if(list != null)
        for (File fil : list)
        {
            if (fil.isDirectory())
            {
                findFile(name, fil);
            }
            else if (name.equalsIgnoreCase(fil.getName()))
            {
                 str = fil.getPath();
                 
            }
        }
    }
	
	public void findFileSize(File file) {
		try {
			Scanner inputStream = new Scanner(file);
			inputStream.useDelimiter("\n");
			while (inputStream.hasNextLine()) {
				rows++;
				data = inputStream.nextLine()+";";
				charData = data.toCharArray();
			}
			for (int i = 0; i < charData.length; i++) {
				if (charData[i] == ';') {
					cols++;
				}
			}
			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public String  getTextLoadSpSh(String str) {
		return textLoadSpSh.getText();
	}
}
