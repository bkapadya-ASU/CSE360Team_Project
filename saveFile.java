import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class saveFile {

	private String grades = "";
	/**
	 * This method will allow user to save current grades into their desired location
	 * @param stage gets a new stage that it will be displayed
	 * @param list gets the size of the list to iterate through a loop
	 * @param listView gets the values from the list
	 */
	public saveFile(Stage stage, ScoreList score, List<Score> list, ListView listView) {

		//get grades from the list view and it to the string
		for(int i = 0;i<list.size();i++) 
			grades += listView.getItems().get(i) + "\n";
		
        FileChooser fileChooser = new FileChooser();
        
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
         
        //Show save file dialog
        File file = fileChooser.showSaveDialog(stage);
         
        if(file != null){
        	try {
    			FileWriter fileWriter;
    			
    			fileWriter = new FileWriter(file);
    			fileWriter.write(grades);
    			fileWriter.close();
        	}
        	catch (IOException ex) {
    			//ex.printStackTrace();
        		ex.getMessage();
    		}
        }
	}	
}
