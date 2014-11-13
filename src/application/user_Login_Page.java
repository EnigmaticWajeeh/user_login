package application;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class user_Login_Page {
	


	@FXML
	private TextField userName;
	
	@FXML
	private TextField passWord;
	
	@FXML
	private Button loginSignup;
	
	@FXML
	private Button createAccount;
	
	@FXML
	private Label message;
	
	private String obtUser=null;
	private String obtPass=null;
	private String username=null;
	private String password=null;
	private boolean noAccount=false;
	private boolean userExist=false;
	
	public void ButtonClicked(javafx.event.ActionEvent action){
		System.out.println("button clicked");
		
		username=userName.getText().trim();
		password=passWord.getText().trim();
	
		Connection conn=global.getConnection();
		
		

		String query= "SELECT * from FBM_USER_TABLE";
		
		System.out.println("query is : " + query);
		
		try {
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery(query);
			
			System.out.println("here");


			while(rs.next()){
				
				obtUser=rs.getString("USER_NAME").toLowerCase().trim();
				
				obtPass=rs.getString("PASSWORD").toLowerCase().trim();
				System.out.println(obtUser + " " + obtPass + " " + username + " " + password );
				if(obtUser.equals(username.toLowerCase())){
					
					userExist=true;
					System.out.println("user matched");
					if(obtPass.equals(password.toLowerCase())){
						System.out.println("working user");
						userExist=true;
						//Go to another page and do further stuff, here user and password
						
						
						break;
					}else{
						System.out.println("password dont match");
						break;
					}
				
				}else if (obtUser.toLowerCase()!=username.toLowerCase()&&obtPass.toLowerCase()!=password.toLowerCase()){
					
					noAccount=true;
				// Almost done check here
						
				}
				
			}
			rs.close();
			st.close();
			
			if(noAccount&&!userExist){

				message.setText("No such account wanna create?");
				createAccount.setDisable(false);
		
			}
			
			System.out.println(global.getMaxUser());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	// Create New User
	public void createNewUser(javafx.event.ActionEvent action){
		Connection conn=global.getConnection();
		
		if(!userExist){
		int newId=1+global.getMaxUser();
		String createUserQuery="INSERT INTO FBM_USER_TABLE (USER_ID, USER_NAME, PASSWORD, MATCH_LOST, MATCH_DRAW, MATCH_WON) VALUES ('"+ newId +"','"+ username + "','" + password + "','0','0','0')" ;
		//complete query here
		System.out.println(createUserQuery);

		try {
			Statement st=conn.createStatement();
			st.executeUpdate(createUserQuery);
			System.out.println("Query executed");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		//Go to another page and do further stuff, here user created and logged in
		
		
		}else{
			message.setText("User Already Exist, change it");
		}
	}
	
	
}
