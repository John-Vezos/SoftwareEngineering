package MinusXL.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;

import MinusXL.data.WorkBook;
import MinusXL.io.SpShDeleter;
import MinusXL.io.SpShSaver;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;

import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JDesktopPane;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import java.awt.Font;

public class BasicFrame {

	private static BasicFrame window;
	private JFrame frmMinusXL;
	
	private JTextField textFunction;
	
	private PromptMessage promptMessage;
	private WorkInitializer workInit;
	private JDesktopPane logInPanel;
	private SpreadInitializer spreadInit;
	private WorkBook workBook;
	private Savewarner savewarner;
	private Exiter exit;
	private FunctionInitializer functionInit;
	private GraphInitializer graphInit;
	private Helper helper;
	
	ArrayList<JFrame> arrayFrames = new ArrayList<JFrame>();
	private String textSaveDir;
	private int defaultWrkBk = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new BasicFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		EventQueue.invokeLater(new Runnable() {
			public void run () {
				try {
					DirSaver save = new DirSaver(window.frmMinusXL, window.promptMessage, BasicFrame.window);
					save.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}


	
	/**
	 * Create the application.
	 */
	public BasicFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		promptMessage = new PromptMessage();
		
		frmMinusXL = new JFrame();
		frmMinusXL.setIconImage(Toolkit.getDefaultToolkit().getImage(BasicFrame.class.getResource("/MinusXL/icons16x16/Wolf-16.png")));
		frmMinusXL.setTitle("MinusXL");
		frmMinusXL.setBounds(300, 150, 1297, 795);
		frmMinusXL.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JEditorPane dtrpnFx = new JEditorPane();
		dtrpnFx.setFont(new Font("Calibri", Font.PLAIN, 14));
		dtrpnFx.setEnabled(false);
		dtrpnFx.setEditable(false);
		dtrpnFx.setText("F(x) =");
		
		textFunction = new JTextField();
		textFunction.setToolTipText("give the cells for function e.g.  (a,1),(a,2) the last Cell is for the result.");
		textFunction.setFont(new Font("Calibri", Font.PLAIN, 14));
		textFunction.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(frmMinusXL.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1281, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(dtrpnFx, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textFunction, GroupLayout.PREFERRED_SIZE, 342, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(652, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(textFunction, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(dtrpnFx, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE))
		);
		
		logInPanel = new JDesktopPane();
		logInPanel.setBackground(SystemColor.inactiveCaption);
		scrollPane.setViewportView(logInPanel);
		
		JButton btnNewSpSh = new JButton("");
		btnNewSpSh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createSpSh();
			}
		});
		btnNewSpSh.setIcon(new ImageIcon(BasicFrame.class.getResource("/MinusXL/icons32x32/Create New-32.png")));
		btnNewSpSh.setToolTipText("New Spreadsheet");
		toolBar.add(btnNewSpSh);
		
		JButton btnSave = new JButton("");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new SpShSaver(window);
			}
		});
		btnSave.setIcon(new ImageIcon(BasicFrame.class.getResource("/MinusXL/icons32x32/Save-32.png")));
		btnSave.setToolTipText("Select Spreadsheet.");
		toolBar.add(btnSave);
		
		JButton btnLoad = new JButton("");
		btnLoad.setToolTipText("Load Spreadsheet");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame helpFrame = isOpen();
				if (helpFrame != null) {
					helpFrame.setVisible(false);
					helpFrame.setVisible(true);
					return ;
				}
				if (workBook == null || workBook.isClosed()) {
					promptMessage.setEditorPromt("You need to open one WorkBook first.\r\nTry again!");
					promptMessage.setVisible(true);
					return ;
				}
				LoadInitializer loadInit = new LoadInitializer(window);
				arrayFrames.add(loadInit);
				loadInit.setVisible(true);
				
			}
		});
		btnLoad.setIcon(new ImageIcon(BasicFrame.class.getResource("/MinusXL/icons32x32/Add List-32.png")));
		toolBar.add(btnLoad);
		
		JButton btnDeleteSpSh = new JButton("");
		btnDeleteSpSh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (workBook == null || workBook.isClosed()) {
					return ;
				}
				new SpShDeleter(window);
			}
		});
		btnDeleteSpSh.setToolTipText("Delete");
		btnDeleteSpSh.setIcon(new ImageIcon(BasicFrame.class.getResource("/MinusXL/icons32x32/Delete Property-32.png")));
		toolBar.add(btnDeleteSpSh);
		
		JButton btnGraphs = new JButton("");
		btnGraphs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame helpFrame = isOpen();
				if (helpFrame != null) {
					helpFrame.setVisible(false);
					helpFrame.setVisible(true);
					return ;
				}
				if (workBook == null || workBook.isClosed()) {
					return ;
				}
				if (workBook.getArraySpSh().size() == 0) {
					return ;
				}
				graphInit = new GraphInitializer(window);
				arrayFrames.add(graphInit);
				graphInit.setVisible(true);
				
			}
		});
		btnGraphs.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnGraphs.setIcon(new ImageIcon(BasicFrame.class.getResource("/MinusXL/icons32x32/Combo Chart-32.png")));
		btnGraphs.setToolTipText("Graphs.");
		toolBar.add(btnGraphs);
		
		JButton btnFunctionInit = new JButton("");
		btnFunctionInit.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnFunctionInit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame helpFrame = isOpen();
				if (helpFrame != null) {
					helpFrame.setVisible(false);
					helpFrame.setVisible(true);
					return ;
				}
				if (workBook == null || workBook.isClosed()) {
					return ;
				}
				if (workBook.getArraySpSh().size() == 0) {
					return ;
				}
				functionInit = new FunctionInitializer(window);
				arrayFrames.add(functionInit);
				functionInit.setVisible(true);
			}
		});
		btnFunctionInit.setIcon(new ImageIcon(BasicFrame.class.getResource("/MinusXL/icons32x32/Square Root 2-32.png")));
		btnFunctionInit.setToolTipText("Function");
		toolBar.add(btnFunctionInit);
		frmMinusXL.getContentPane().setLayout(groupLayout);
		
		JMenuBar menuBar = new JMenuBar();
		frmMinusXL.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewWorkbook = new JMenuItem("New Workbook");		
		mntmNewWorkbook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createWB();
			}
		});
		mntmNewWorkbook.setIcon(new ImageIcon(BasicFrame.class.getResource("/MinusXL/icons16x16/Word-16.png")));
		mnNewMenu.add(mntmNewWorkbook);
		
		JMenuItem mntmExit = new JMenuItem("exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame testFrame;
				if ((testFrame = isOpen()) != null) {
					testFrame.dispose();
					arrayFrames.clear();	
				}
				if (exit == null || !exit.isDisplayable()) {
					exit = new Exiter(window);
					arrayFrames.add(exit);
					exit.setVisible(true);
				}
			}
		});
		mntmExit.setIcon(new ImageIcon(BasicFrame.class.getResource("/MinusXL/icons16x16/Shutdown-16.png")));
		mnNewMenu.add(mntmExit);
		
		JMenu mnHelper = new JMenu("Help");
		menuBar.add(mnHelper);
		
		JMenuItem mntmHelp = new JMenuItem("Help");
		mntmHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame helpFrame = isOpen();
				if (helpFrame != null) {
					helpFrame.setVisible(false);
					helpFrame.setVisible(true);
					return;
				}
				helper = new Helper(window);
				arrayFrames.add(helper);
				helper.setVisible(true);
			}
		});
		mntmHelp.setIcon(new ImageIcon(BasicFrame.class.getResource("/MinusXL/icons16x16/Info-16.png")));
		mnHelper.add(mntmHelp);
	}
	
	public PromptMessage getPromptMessage() {
		return promptMessage;
	}
	
	/**
	 * create workInit its the settings for Workbook
	 *
	 */
	public void createWB() {
		if (workBook == null || workBook.isClosed()) {
			JFrame helpFrame = isOpen();
			if (helpFrame != null) {
				helpFrame.setVisible(false);
				helpFrame.setVisible(true);
				return;
			}
			workInit = new WorkInitializer(window);
			arrayFrames.add(workInit);
			workInit.setVisible(true);
		} else {
			JFrame helpFrame = isOpen();
			if (helpFrame != null) {
				helpFrame.setVisible(false);
				helpFrame.setVisible(true);
				return;
			}
			savewarner = new Savewarner(window);
			arrayFrames.add(savewarner);
			savewarner.setVisible(true);
		}
	}
	
	/**
	 *  create spreadsheet its the settings for Spreadsheets.
	 */
	public void createSpSh() {
		if (workBook == null || workBook.isClosed()) {
			return ;
		}
		JFrame helpFrame = isOpen();
		if (helpFrame != null) {
			helpFrame.setVisible(false);
			helpFrame.setVisible(true);
			return;
		}
		spreadInit = new SpreadInitializer(window);
		arrayFrames.add(spreadInit);
		spreadInit.setVisible(true);
		
	}
	
	/**
	 * 
	 * @return who frame is open currently.
	 */
	public JFrame isOpen() {
		for (int i = 0; i < getArrayFrames().size(); i++) {
			if (arrayFrames.get(i).isDisplayable()) {
				return arrayFrames.get(i);
			}
		}
		return null;
	}
	
	/**
	 * 
	 *@return sumWorkBooks with no-name from user.
	 */

	public int getDefaultWrkBk() {
		return defaultWrkBk ;
	}
	public void setDefaultWrkBk() {
		++defaultWrkBk;		
	}
	
	/**
	 * 
	 * @return DesktopPanel form window to add WorkInit, workBook
	 */
	public JDesktopPane getLogInPanel() {
		return logInPanel;
	}
	
	/**
	 * 
	 * @param workBook in window StartFrame
	 */
	public void setWorkBook(WorkBook workBook) {
		this.workBook = workBook;
	}
	
	public WorkBook getWorkBook() {
		return workBook;
	}
	/**
	 * 
	 * @return workInit to get access in workInit methods and fields.
	 */
	public WorkInitializer getWorkInit() {
		return workInit;
	}
	
	/**
	 * 
	 * @return the list with frames.
	 */
	public ArrayList<JFrame> getArrayFrames() {
		return arrayFrames;
	}
	
	/**
	 * 
	 * @return sreadInit  cause it have the name of spreadSheets
	 */
	public SpreadInitializer getSpreadInit() {
		return spreadInit;
	}
	/**
	 * 
	 * @return the path to save workspace\\Name_workBook\\ spreadSheets.
	 */
	public String getTextSaveDir() {
		return textSaveDir;
	}
	public void setTextSaveDir(String str) {
		textSaveDir = str;
	}
	public String getTextFunction() {
		return textFunction.getText();
	}
	public FunctionInitializer getFunctionInit() {
		return functionInit;
	}
}

