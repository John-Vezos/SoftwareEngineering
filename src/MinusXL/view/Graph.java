package MinusXL.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;

import javax.swing.JScrollPane;

public class Graph extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPane;

	/**
	 * Create the frame.
	 */
	public Graph() {
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 627, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
	}
	
	public void graphLine(CategoryDataset ds, String textNameGraph, String textAxisX, String textAxisY) {
		JFreeChart chart = ChartFactory.createLineChart(textNameGraph, textAxisX, textAxisY, ds, PlotOrientation.VERTICAL, true, true, false);
		
		ChartPanel cp = new ChartPanel(chart);
		scrollPane.setViewportView(cp);

	}
	public void graphBar(CategoryDataset ds, String textNameGraph, String textAxisX, String textAxisY) {
		JFreeChart chart = ChartFactory.createBarChart(textNameGraph, textAxisX, textAxisY, ds, PlotOrientation.VERTICAL, true, true, false);
		
		ChartPanel cp = new ChartPanel(chart);
		scrollPane.setViewportView(cp);
	}
	
}

