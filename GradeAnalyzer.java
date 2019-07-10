import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;  
import javafx.stage.FileChooser;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class GradeAnalyzer extends Application {
	private HBox hBox;
	private Button yesButton,noButton;
	private BorderPane bp, bp2;
	
	private double maxScore;
	ScoreList scoreList;
	
    @Override
        public void start(Stage stage) throws Exception {
    	
    	GradeError_window error = new GradeError_window();
    	GUImain screen = new GUImain();
    	
    	TextInputDialog dialog = new TextInputDialog();
    	dialog.setTitle("Max Score Indentifier");
    	dialog.setHeaderText("This dialog determines how the assessment was scored.");
    	dialog.setContentText("Please enter the maximum possible (denominator) score on this assessment:");

    	Optional<String> result = dialog.showAndWait();
    	if (result.isPresent()){
    	    maxScore = Double.parseDouble(result.get());
    	}
    	
    	scoreList = new ScoreList(maxScore);
    	
        hBox = new HBox();
        hBox.setTranslateX(40);
        hBox.setTranslateY(10);
       
        bp  = new BorderPane();
        bp2 = new BorderPane();
   
        yesButton = new Button("Yes");
        noButton = new Button("No");
        
        yesButton.setMinWidth(50); //set width of the buttons
        noButton.setMinWidth(50);
        noButton.setTranslateX(20);
               
        yesButton.setOnAction((e)->
        {
        	FileChooser chooseFile = new FileChooser();
        	        	
        	chooseFile.setTitle("Select .txt file");
        	FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        	
        	chooseFile.getExtensionFilters().add(extFilter);
        	File file = chooseFile.showOpenDialog(stage);
        	
        	try {
				Scanner scan = new Scanner(file);
				while(scan.hasNext() && scan.hasNextDouble())
				{
					scoreList.add(Double.parseDouble(scan.nextLine()));
				}
				
				if(scoreList.getList().isEmpty()) { //This will open a new window if the file selected doesn't contain numbers
					try {
						error.start(stage);

					} catch (Exception e1) {
						e1.printStackTrace();
					}					
				}
				else {
					screen.start(stage, scoreList.getList(), scoreList);
				}
				
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
        	
        });
        
        noButton.setOnAction(e->{
        	screen.start(stage, scoreList.getList(), scoreList);
        });
        
        Label label = new Label("Would you like to load a file?");
        
        hBox.getChildren().addAll(yesButton,noButton);
             
        bp2.setCenter(label);
        bp.setTop(bp2);
        bp.setCenter(hBox);
      
        stage.setTitle("Grade Analyzer");
        Scene scene = new Scene(bp);
        stage.setScene(scene);
        stage.setWidth(200);
        stage.setHeight(90);
        stage.show();
        
        stage.setMinWidth(stage.getWidth());
        stage.setMinHeight(stage.getHeight());
    }
  
    public static void main(String[] args) {
        Application.launch(args);
    }
}