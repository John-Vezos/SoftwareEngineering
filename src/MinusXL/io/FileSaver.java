package MinusXL.io;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JTextField;

import MinusXL.view.PromptMessage;


public class FileSaver {
	private boolean saveDone = false;
	
	public FileSaver (JTextField textSaveDir, PromptMessage promptMessage) {
		boolean checkDir;
		if (promptMessage != null) {
			promptMessage.dispose();
		}
		
		if (textSaveDir.getText().equals("") == true) {
			promptMessage.setEditorPromt("Give your path you want to create folder to save your porjects.");
			return ;
		}
		if (textSaveDir.getText().charAt(1) != ':'){
			promptMessage.setEditorPromt("Give your Disk (e.g. C:\\ or D:\\ or ..).");
			return ;
		}
		
		Path xPath = Paths.get(textSaveDir.getText()+"\\MinusXL");
		checkDir = Files.exists(xPath);

		if (checkDir) {
			setSaveDone();
			return ;
		}
		try {
			Files.createDirectory(xPath);
			setSaveDone();
		} catch (Exception e) {
			promptMessage.setEditorPromt("Could not create directory. Give correct path e.x. C:\\Users\\George");
			return ;
		}
	}
	/**
	 * 
	 * @return if directory is created. to exit from Workspace Path.
	 */
	public boolean getSaveDone() {
		return saveDone;
	}
	
	public void setSaveDone() {
		saveDone = true;
	}
	
}
