package MinusXL.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import MinusXL.view.BasicFrame;

public class SpShLoader {

	public SpShLoader(BasicFrame window, String fileName, int cols, int rows, int possition) {
		
		System.out.println(possition);
		File file = new File(fileName);
		try {
			Scanner inputStream = new Scanner(file);
			String lastData;
			String data;
			
			int i = 0;
			inputStream.useDelimiter("\n");
			while (inputStream.hasNextLine()) {
				data = "\b"+inputStream.nextLine(); 			
				Scanner input = new Scanner(data);
				input.useDelimiter(";");
				
				int j = 0;
				while (input.hasNext()) {
					lastData = input.next();
					lastData = lastData.replace("\b", "");
					window.getWorkBook().getArraySpSh().get(possition).getCellTable().setValueAt(lastData, i, j);
					j++;
				}
				i++;
				input.close();
			}	
			
			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
