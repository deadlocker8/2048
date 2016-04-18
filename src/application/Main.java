package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try 
		{
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("application/GameGUI.fxml"));
			Parent root = (Parent)loader.load();				
				    
			Scene scene = new Scene(root,600,750);			
			
			stage.setResizable(false);			
			
			stage.getIcons().add(new Image("/application/2048.png"));
			
			stage.setTitle("2048");
			stage.setScene(scene);
			stage.show();				
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
