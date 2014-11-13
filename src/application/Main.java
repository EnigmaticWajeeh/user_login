package application;
	
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane page = (AnchorPane) FXMLLoader.load(Main.class.getResource("user_Login_Page.fxml"));
			Scene scene = new Scene(page,900,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
			//count max player
			Connection conn=global.getConnection();
			
			
			String maxUserQuery="select * from FBM_USER_TABLE";
			
			System.out.println(maxUserQuery);
			
			try {
				Statement st1=conn.createStatement();
				ResultSet rs1=st1.executeQuery(maxUserQuery);
				
				while(rs1.next()){
					global.increaseMaxUser();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			System.out.println(global.getMaxUser());
			System.out.println("Now checking user, max player counted");
			
			
			
			
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}

	
}
