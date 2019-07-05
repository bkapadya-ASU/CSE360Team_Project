package application;

import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.Scene;

public class SelectionPopUp {
	
	public SelectionPopUp(Stage newStage, Stage sameStage, String message, ScoreList score, String command, ListView list, int numberDeleted)
	{
		
		GUImain gui = new GUImain();
        HBox hBox = new HBox();
        HBox hBox2 = new HBox();
        VBox vBox = new VBox();
		
		Label enterGrade = new Label(message + ": ");
	    enterGrade.setTranslateY(15);
	    enterGrade.setTranslateX(5);
	    enterGrade.setStyle("-fx-font: 16 arial;");
	    
	    double number = 0;
	    
	    if(command.equals("Delete"))
	    {
	    	number = (double) list.getItems().get(numberDeleted); //get the value of the list and print it out
	    	
	    	Label showGrade = new Label(Double.toString(number));
	        showGrade.setTranslateX(40);
	        showGrade.setTranslateY(5);
	        showGrade.setStyle("-fx-font: 30 arial;");
	        
	        Button ok = new Button("OKAY");
	        ok.setOnAction(e->{
        		score.delete(numberDeleted);
        		
	        	Stage cancleStage = (Stage) ok.getScene().getWindow();
	        	cancleStage.close();
	        	
	        	gui.start(sameStage,score.getList(),score);     
        	});
	        ok.setTranslateX(110);
	        ok.setTranslateY(20);
	        ok.setMinWidth(50);
	        
	        Button cancle = new Button("CANCEL");
			cancle.setOnAction(e ->{
	        	Stage cancleStage = (Stage) cancle.getScene().getWindow();
	        	cancleStage.close();
		     });
	        cancle.setTranslateX(130);
	        cancle.setTranslateY(20);
	        cancle.setMinWidth(50);
	        
	        hBox.getChildren().addAll(enterGrade, showGrade);
	        hBox2.getChildren().addAll(ok, cancle);
	
	        vBox.getChildren().addAll(hBox, hBox2);
	                
	        Scene scene = new Scene(vBox, 400, 100);
	        newStage.setScene(scene);
	        newStage.show();
	    }
	    else
	    {
	    	TextField grade = new TextField();
	        grade.setTranslateX(30);
	        grade.setTranslateY(10);
	        grade.setMaxSize(200,20);
	        
	        Button ok = new Button("OKAY");
	        ok.setOnAction(e->{
        		score.setScore(Double.parseDouble(grade.getText()), numberDeleted);
        		
	        	Stage cancleStage = (Stage) ok.getScene().getWindow();
	        	cancleStage.close();
	        	
	        	gui.start(sameStage,score.getList(),score);     
        	});
	        ok.setTranslateX(110);
	        ok.setTranslateY(20);
	        ok.setMinWidth(50);
	        
	        Button cancle = new Button("CANCEL");
			cancle.setOnAction(e ->{
	        	Stage cancleStage = (Stage) cancle.getScene().getWindow();
	        	cancleStage.close();
		     });
	        cancle.setTranslateX(130);
	        cancle.setTranslateY(20);
	        cancle.setMinWidth(50);
	        
	        hBox.getChildren().addAll(enterGrade, grade);
	        hBox2.getChildren().addAll(ok, cancle);
	        
	        vBox.getChildren().addAll(hBox, hBox2);
	        
	        Scene scene = new Scene(vBox, 400, 100);
	        newStage.setScene(scene);
	        newStage.show();
	    }
	     
	}

}
