package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try 
		{
			Parent root=FXMLLoader.load(getClass().getResource("Giris.fxml"));
			Scene scene = new Scene(root,800,700);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
