import javafx.application.Application;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.text.*;


public class GUImain {
	
	private int deleteNumber = 0;
	private int check = 0;
	
	/**
	 * This displays the options for the user to choose either to add, delete, set/change, change threshold, save and exit the program.
	 * @param primaryStage is the stage that it will display the stage
	 * @param list is the list that has a copy of the text file being the input
	 * @param scoreList gets the student score list
	 */
	public void start(Stage primaryStage,List<Score> list, ScoreList scoreList) {
		Stage newStage = new Stage();
		DecimalFormat df = new DecimalFormat("#.00");
		
		try {
			BorderPane root = new BorderPane();
			
			
			ListView listView = new ListView();
			
			for(int i = 0;i<list.size();i++)
				listView.getItems().add(df.format(list.get(i).getRawScore()));
			
			listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
			listView.getSelectionModel().selectedItemProperty().addListener(e->{
				deleteNumber = listView.getSelectionModel().getSelectedIndex();
				check++;
			});
			
			Text t = new Text();
			
			t.setFont(new Font(30));
			t.setText("Grade Analyzer");
			t.setTranslateX(200);
			
			Button statisticsB = new Button("STATISTICS");
			statisticsB.setMinWidth(180);
			statisticsB.setMinHeight(50);
			statisticsB.setOnAction(e->{
				PopUp pop = new PopUp(newStage, primaryStage, "STATISTICS", scoreList,"Statistcs",listView);
			});
			
			Button addB = new Button("ADD");
			addB.setMinWidth(200);
			addB.setMinHeight(50);
			addB.setOnAction(e->{
				addGradePopUp gragePop = new addGradePopUp(newStage, primaryStage,"Enter grade: ", scoreList,"Add",null,0); 
				System.out.print("Hello");
			});
			
			Button deleteB = new Button("DELETE");
			deleteB.setMinWidth(200);
			deleteB.setMinHeight(50);		
			deleteB.setOnAction(e->{
				if(deleteNumber>=0 && check!=0){	
					addGradePopUp gragePop = new addGradePopUp(newStage, primaryStage,"Are you sure you want to Delete: ", scoreList,"Delete",listView,deleteNumber); 
				}
				else { 
					errorWindow showError = new errorWindow(newStage, primaryStage, scoreList, "PLEASE SELECT A GRADE TO MODIFY",20);
				}
			});		
			
			Button changeB = new Button("SET/CHANGE");
			changeB.setMinWidth(200);
			changeB.setMinHeight(50);
			changeB.setOnAction(e->{
				if(deleteNumber>=0 && check!=0){	
					addGradePopUp gragePop = new addGradePopUp(newStage, primaryStage,"Enter new grade: ", scoreList,"SET/CHANGE",listView,deleteNumber); 
				}
				else { 
					errorWindow showError = new errorWindow(newStage, primaryStage, scoreList, "PLEASE SELECT A GRADE TO MODIFY",20);
				}
			});

				
			Button saveB = new Button("SAVE");
			saveB.setMinWidth(200);
			saveB.setMinHeight(50);
			saveB.setOnAction(e->{
				saveFile sf = new saveFile(newStage, scoreList, list, listView);
			});

			Button thresholdB = new Button("CHANGE THRESHOLDS");
			thresholdB.setMinWidth(200);
			thresholdB.setMinHeight(50);
			thresholdB.setOnAction(e->{
				PopUp pop = new PopUp(newStage, primaryStage, "Change Threshold", scoreList,"Threshold",listView);
			});

			Button exitB = new Button("EXIT");
			exitB.setMinWidth(200);
			exitB.setMinHeight(50);
			exitB.setOnAction(e ->{
				Stage stage = (Stage) exitB.getScene().getWindow();
				stage.close();
			});		
				
			GridPane gp = new GridPane();
			gp.add(statisticsB, 0, 0);
			gp.add(addB, 0, 2);
			gp.add(deleteB, 1, 2);
			gp.add(saveB, 1, 13);
			gp.add(exitB, 0, 13);
			gp.add(changeB, 1, 4);
			gp.add(thresholdB, 0, 4);			
			
			gp.setPadding(new Insets(20, 12, 15, 20));
			gp.setVgap(10.0);
			
			root.setLeft(gp);
			root.setRight(listView);
			root.setTop(t);
			
			Scene scene = new Scene(root,350,350);
			primaryStage.setWidth(600);
			primaryStage.setHeight(500);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Grade Analyzer");
			primaryStage.setScene(scene);
	        primaryStage.setMinWidth(primaryStage.getWidth());
	        primaryStage.setMinHeight(primaryStage.getHeight());
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
