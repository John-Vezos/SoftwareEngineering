package MinusXL.io;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import MinusXL.data.CellTable;
import MinusXL.view.BasicFrame;

public class SpShSaver {
	
	public SpShSaver(BasicFrame window) {
		
		if ( window.getWorkBook() == null || window.getWorkBook().isClosed()) {
			window.getPromptMessage().setEditorPromt("No workBook open.");
			window.getPromptMessage().setVisible(true);
			return ;
		}
		if (window.getWorkBook().getArraySpSh().size() == 0) {
			window.getPromptMessage().setEditorPromt("No spreadSheet in this workBook."+window.getWorkInit().getTextNameWrkBk().getText());
			window.getPromptMessage().setVisible(true);
			return ;
		}
		for (int i = 0; i < window.getWorkBook().getArraySpSh().size(); i++) {
			if (window.getWorkBook().getArraySpSh().get(i).isSelected() == true){
				savior(window, i);
				return ;
			}
		}
		
		

	}
	
	public void savior(BasicFrame window, int posSpreadSheet) {
		boolean checkDupliCate;
		FileWriter writer = null;
		
		Path xPath = Paths.get(window.getTextSaveDir()+"\\"+window.getWorkInit().getTextNameWrkBk().getText());
		checkDupliCate = Files.exists(xPath);
				
		if (!checkDupliCate) {
			try {
				Files.createDirectory(xPath);
			} catch (Exception e) {
				window.getPromptMessage().setEditorPromt("Could not create Directory");
				window.getPromptMessage().setVisible(true);
				return ;
			}
		} 
		try {
			writer = new FileWriter(xPath+"\\"+window.getWorkBook().getArrayNamesSpSh().get(posSpreadSheet)+".csv");
		} catch (IOException e) {
			window.getPromptMessage().setEditorPromt("Something going wrong with save.\r\nTry again.");
			e.printStackTrace();
		}
		
		CellTable table = window.getWorkBook().getArraySpSh().get(posSpreadSheet).getCellTable();
		try {
			for (int i = 0; i < table.getRowCount(); i++) {
				for (int j = 0; j < table.getColumnCount(); j++) {
					if (table.getValueAt(i, j) != null && !table.getValueAt(i, j).equals("")) {
						writer.append(String.valueOf(table.getValueAt(i, j)));
					}
					if (j != table.getColumnCount() - 1) {
						writer.append(";");
					} 
				}
				if (i != table.getRowCount() - 1) {
					writer.append("\r\n");
				} else {
					writer.append("\r");
				}
			}
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}



