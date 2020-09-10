package MinusXL.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import MinusXL.view.BasicFrame;

public class SpShDeleter {

	public SpShDeleter(BasicFrame window) {
		
		if (window.getWorkBook() == null || window.getWorkBook().isClosed()) {
			window.getPromptMessage().setEditorPromt("You havent got open workBook."+window.getWorkInit().getTextNameWrkBk().getText());
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
				DeleteSpSh(window, i);
				return ;
			}
		}
	}
	
	public void DeleteSpSh(BasicFrame window, int i) {
		
		Path xPath = Paths.get(window.getTextSaveDir()+"\\"+window.getWorkInit().getTextNameWrkBk().getText()+"\\"+window.getWorkBook().getArrayNamesSpSh().get(i)+".csv");
		try {
			Files.deleteIfExists(xPath);
			window.getPromptMessage().setEditorPromt("Delete complete.");
			window.getWorkBook().getWrkBkPanel().remove(window.getWorkBook().getArraySpSh().get(i));
			window.getPromptMessage().setVisible(true);
			window.getWorkBook().getArraySpSh().get(i).dispose();
			window.getWorkBook().getArrayNamesSpSh().remove(i);
			window.getWorkBook().getArraySpSh().remove(i);
			window.getWorkBook().getWrkBkPanel().repaint();
		} catch (IOException e) {
			window.getPromptMessage().setEditorPromt("Delete failed.");
			window.getPromptMessage().setVisible(true);
			e.printStackTrace();
		}
	}
}
