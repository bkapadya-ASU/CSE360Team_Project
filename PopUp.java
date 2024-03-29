import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.*; 


public class PopUp {
	
	GridPane gp1 = new GridPane();
	/**
	 * This method will make a pop up that will display the threshold to change and the statistics of the list
	 * @param newStage gets the new stage to be display
	 * @param sameStage gets the same stage that it current is
	 * @param message gets the message that it will be displayed in the pop up
	 * @param score is the score list that it will work on
	 * @param command gets the command on which pop to be displayed
	 */
	public PopUp(Stage newStage, Stage sameStage, String message, ScoreList score, String command,ListView listView)
	{
		
		GUImain gui = new GUImain();
		
        GridPane gp = new GridPane();
        
        Label enterGrade = new Label(message + ": ");
        TextArea gradesText = new TextArea();
        
        enterGrade.setTranslateY(15);
        enterGrade.setTranslateX(5);
        enterGrade.setStyle("-fx-font: 16 arial;");
            
        if(command.equals("Threshold"))
        {
        	Label letterA = new Label("A: ");
        	Label letterB = new Label("B: ");
        	Label letterC = new Label("C: ");
        	Label letterD = new Label("D: ");
        	
        	TextField aTF = new TextField();
        	TextField bTF = new TextField();
        	TextField cTF = new TextField();
        	TextField dTF = new TextField();
        	
        	Button saveBt = new Button("SAVE");
        	saveBt.setOnAction(e->{
        		Double newA = Double.parseDouble(aTF.getText());
        		Double newB = Double.parseDouble(bTF.getText());
        		Double newC = Double.parseDouble(cTF.getText());
        		Double newD = Double.parseDouble(dTF.getText());
        		
        		score.changeGradeThresholds(newA, newB, newC, newD);
        	
        		Stage cancleStage = (Stage) saveBt.getScene().getWindow();
        		cancleStage.close();
        	
        		gui.start(sameStage, score.getList(), score);
        		});
        
        	saveBt.setTranslateX(110);
        	saveBt.setTranslateY(20);
        	saveBt.setMinWidth(50);
        
        	Button cancle = new Button("CANCEL");
        	cancle.setOnAction(e ->{
        		Stage cancleStage = (Stage) cancle.getScene().getWindow();
        		cancleStage.close();
        	});
        	cancle.setTranslateX(130);
        	cancle.setTranslateY(20);
        	cancle.setMinWidth(50);
        
        	gp.add(enterGrade, 0, 0);
        	gp.add(letterA, 0, 1);
        	gp.add(letterB, 0, 2);
        	gp.add(letterC, 0, 3);
        	gp.add(letterD, 0, 4);
        	gp.add(aTF, 1, 1);
        	gp.add(bTF, 1, 2);
        	gp.add(cTF, 1, 3);
        	gp.add(dTF, 1, 4);
        	gp.add(saveBt, 0, 5);
        	
        	gp.setVgap(10.00);
        	
        	ScrollPane sp = new ScrollPane(gp);
        	sp.setFitToWidth(true);
        	
        	Scene scene = new Scene(sp, 400, 220);
         	newStage.setScene(scene);
         	newStage.show();
        }
        else
        {
		score.findMinScore();
        	score.findMaxScore();
		
		String mean = "Mean:       " + Double.toString(score.getMeanScore());
        	String median = "Median:     " + Double.toString(score.getMedianScore());
        	String max = "Maximum: " + Double.toString(score.getMaxScore());
        	String min = "Minimum:  " + Double.toString(score.getMinScore());
        	String meanPer = "Mean:       " + Double.toString(score.getMeanPercentScore());
        	String medianPer = "Median:     " + Double.toString(score.getMedianPercentScore());
        	String maxPer = "Maximum " + Double.toString(score.getMaxScorePercentage());
        	String minPer = "Minimum:  " + Double.toString(score.getMinScorePercentage());
        	String meanHigh = "Mean:       " + Double.toString(score.getMeanPercentScoreBasedOnHighestScore());
        	String medianHigh = "Median:     " + Double.toString(score.getMedianPercentScoreBasedOnHighestScore());
        	String maxHigh = "Maximum: " + Double.toString(score.getMaxScorePercentageBasedOnHighestScore());
        	String minHigh = "Minimum:  " + Double.toString(score.getMinScorePercentageBasedOnHighestScore());
        	   
    		//highScore will get the absolute value of the difference between the max score and threshold
        	Double highScore = Math.abs(score.getMaxThreshold() - score.getMaxScore());
        	
        	for(int i = 0; i<=0;i++) 
        	{
        		
        		String letterGrade = ("Number of A's: " + score.getNumberofA() + "\n"
        							+ "Number of B's: " + score.getNumberofB() + "\n"
        							+ "Number of C's: " + score.getNumberofC() + "\n"
        							+ "Number of D's: " + score.getNumberofD() + "\n"
        							+ "Number of E's: " + score.getNumberofE() + "\n");
        		String letterGradHighest = ("Number of A's: " + score.getNumberofABasedOnHighestScore() + "\n"
						+ "Number of B's: " + score.getNumberofBBasedOnHighestScore() + "\n"
						+ "Number of C's: " + score.getNumberofCBasedOnHighestScore() + "\n"
						+ "Number of D's: " + score.getNumberofDBasedOnHighestScore() + "\n"
						+ "Number of E's: " + score.getNumberofEBasedOnHighestScore() + "\n");
        		      	
	        	Label rawLabel = new Label("Raw Score");
	        	Label meanLabel = new Label(mean);
	        	Label medianLabel = new Label(median);
	        	Label maxLabel = new Label(max);
	        	Label minLabel = new Label(min);
	        	Label percentileLabel = new Label("Percentile Score");
	        	Label highestLabel = new Label("Percentile Score with Highest Score");
	        	Label gradeLabel = new Label("Letter Grade");
	        	Label highestGradeLabel = new Label("Letter Grade with Highest Score");
	        	Label meanPercentileLabel = new Label(meanPer);
	        	Label medianPercentileLabel = new Label(medianPer);
	        	Label maxPercentileLabel = new Label(maxPer);
	        	Label minPercentileLabel = new Label(minPer);
	        	Label meanHighestLabel = new Label(meanHigh);
	        	Label medianHighestLabel = new Label(medianHigh);
	        	Label maxHighestLabel = new Label(maxHigh);
	        	Label minHighestLabel = new Label(minHigh);

	        	Label letterGrad = new Label(letterGrade);
	        	Label letterHigh = new Label(letterGradHighest);

	        	gp1.add(rawLabel, 0, 0);
	        	gp1.add(meanLabel, 0, 1);
	        	gp1.add(medianLabel, 0, 2);
	        	gp1.add(maxLabel, 0, 3);
	        	gp1.add(minLabel, 0, 4);
	        	gp1.add(percentileLabel, 1, 0);
	        	gp1.add(meanPercentileLabel, 1, 1);
	        	gp1.add(medianPercentileLabel, 1, 2);
	        	gp1.add(maxPercentileLabel, 1, 3);
	        	gp1.add(minPercentileLabel, 1, 4);
	        	gp1.add(highestLabel, 2, 0);
	        	gp1.add(meanHighestLabel, 2, 1);
	        	gp1.add(medianHighestLabel, 2, 2);
	        	gp1.add(maxHighestLabel, 2, 3);
	        	gp1.add(minHighestLabel, 2, 4);
	        	gp1.add(gradeLabel, 3, 0);
	        	gp1.add(highestGradeLabel, 4, 0);

		        gp1.add(letterGrad, 3, i+1);
		        gp1.add(letterHigh, 4, i+1);
	        	gp1.setHgap(30.00);
	        	gp1.setVgap(10.00);
	        }
        		
        	    ScrollPane sp = new ScrollPane(gp1);
        	    sp.setFitToWidth(true);
        	    
	        	Scene sceneS = new Scene(sp, 1050, 300);
	        	newStage.setScene(sceneS);
	        	newStage.show();
		}
		}
	}
