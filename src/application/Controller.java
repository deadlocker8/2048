package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Controller implements Initializable
{
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private Label label;
	@FXML
	private Button neuButton;
	@FXML
	private Button anleitungButton;
	@FXML
	private Pane pane;
	
	private int[] spielfeld;
	private boolean running;
	
	
	@FXML
	public void neuButton(ActionEvent e)
	{
		Game.newGame();
		spielfeld = Game.getSpielfeld();		
		setSpielfeld();
		running = true;
	}
	
	@FXML
	public void anleitungButton(ActionEvent e)
	{
		Alert alert = new Alert(AlertType.INFORMATION);	
		alert.setTitle("Anleitung");
		alert.setHeaderText("Anleitung");	
		alert.setResizable(true);
		alert.getDialogPane().setPrefSize(350, 230);
		alert.setContentText("Bewege mit den Pfeiltasten die Blöcke nach \noben, unten, links oder rechts \nund versuche gleiche Zahlen zu kombinieren.\nZiel ist es einen 2048 Block zu erstellen.");
		alert.showAndWait();	
	}
	
	public void setSpielfeld()
	{
		FlowPane flow = new FlowPane();
	    flow.setPadding(new Insets(5, 0, 5, 0));
	    flow.setVgap(10);
	    flow.setHgap(10);
	    flow.setPrefWrapLength(430); // preferred width allows for two columns	    

	    Label labels[] = new Label[16];
	    for (int i = 0; i < 16; i++) 
	    {
	    	switch(spielfeld[i])
	    	{
	    		case 0: 	labels[i] = new Label("");
	    					labels[i].setStyle("-fx-background-color: #CDC0B4; fx-border-top-radius: 15; -fx-background-radius: 15; ");
	    					break;
	    		case 2:		labels[i] = new Label("2");
							labels[i].setStyle("-fx-background-color: #EEE4DA; fx-border-top-radius: 15; -fx-background-radius: 15; ");
							break;
	    		case 4:		labels[i] = new Label("4");
							labels[i].setStyle("-fx-background-color: #EDE0C8; fx-border-top-radius: 15; -fx-background-radius: 15; ");
							break;
	    		case 8: 	labels[i] = new Label("8");
	    					labels[i].setStyle("-fx-background-color: #F2B179; fx-border-top-radius: 15; -fx-background-radius: 15; ");
							break;
	    		case 16:	labels[i] = new Label("16");
							labels[i].setStyle("-fx-background-color: #F59563; fx-border-top-radius: 15; -fx-background-radius: 15; ");
							break;
	    		case 32:	labels[i] = new Label("32");
							labels[i].setStyle("-fx-background-color: #F67C5F; fx-border-top-radius: 15; -fx-background-radius: 15; ");
							break;
	    		case 64: 	labels[i] = new Label("64");
							labels[i].setStyle("-fx-background-color: #F65E3B; fx-border-top-radius: 15; -fx-background-radius: 15; ");
							break;
	    		case 128:	labels[i] = new Label("128");
							labels[i].setStyle("-fx-background-color: #EDCF72; fx-border-top-radius: 15; -fx-background-radius: 15; ");
							break;
	    		case 256:	labels[i] = new Label("256");
							labels[i].setStyle("-fx-background-color: #EDCC61; fx-border-top-radius: 15; -fx-background-radius: 15; ");
							break;
	    		case 512: 	labels[i] = new Label("512");
							labels[i].setStyle("-fx-background-color: #EDC850; fx-border-top-radius: 15; -fx-background-radius: 15; ");
							break;
	    		case 1024:	labels[i] = new Label("1024");
							labels[i].setStyle("-fx-background-color: #EDC53F; fx-border-top-radius: 15; -fx-background-radius: 15; ");
							break;
	    		case 2048:	labels[i] = new Label("2048");
							labels[i].setStyle("-fx-background-color: #ECC400; fx-border-top-radius: 15; -fx-background-radius: 15; ");
							break;	    	
	    	}
	        
	        
	        labels[i].setFont(Font.font ("System",FontWeight.BOLD , 36));
	        labels[i].setAlignment(Pos.CENTER);
	        
	        labels[i].setPrefHeight(100);
	        labels[i].setPrefWidth(100);	       
	         
	        flow.getChildren().add(labels[i]);
	    }		
		
	   pane.getChildren().add(flow); 	   
	}	


	@Override	
	public void initialize(URL arg0, ResourceBundle arg1)
	{	
		running = false;
		
		anchorPane.setStyle("-fx-background-color: #FFFFFF;");	            
	
		anchorPane.setOnKeyPressed(
			      new EventHandler<KeyEvent>()
			      {
			         @Override
			         public void handle(KeyEvent keyEvent)
			         {
			        	 if(running)
			        	 {			        	
				        	 String keyCode = keyEvent.getCode().toString();
				        
				        	 switch(keyCode)
				        	 {
				        	 	case "UP": 		Game.nachOben();       	 					
				        	 					break;
				        		case "DOWN": 	Game.nachUnten();				        								        						
		        	 							break;
				        		case "RIGHT": 	Game.nachRechts();				        					        						
		        	 							break;
				        		case "LEFT": 	Game.nachLinks();				        						     						
		        	 							break;
				        		case "W":		Game.cheat();				
		        	 			default:		
		        	 							break;			        	 				
				        	 }
				        	 
				        	setSpielfeld(); 
				        	setLabel(Game.getPoints());
	 	 					boolean win = Game.checkWinning();
	 	 					if(win)
	 	 					{
	 	 						running = false;
	 	 						
	 	 						Alert alert = new Alert(AlertType.INFORMATION);	
	 	 						alert.setTitle("Gewonnen!");
	 	 						alert.setHeaderText("Du hast den 2048 Block erschaffen!");
	 	 						alert.setContentText("Du hast dabei " + Game.getPoints() + " Punkte erreicht.");
	 	 						alert.showAndWait();
	 	 						
	 	 					}
	 	 					boolean lose = Game.checkLosing();
	 	 					if(lose)
	 	 					{
	 	 						running = false;
	 	 						Alert alert = new Alert(AlertType.INFORMATION);	
	 	 						alert.setTitle("Verloren!");
	 	 						alert.setHeaderText("Kein Züge mehr übrig!");
	 	 						alert.setContentText("Du hast " + Game.getPoints() + " Punkte erreicht.");
	 	 						alert.showAndWait();	
	 	 					} 
			        	 }
			         }
			      }
			);
	}
	
	public void setLabel(int punkte)
	{
		label.setText(String.valueOf(punkte));
	}
}