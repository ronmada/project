package server;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
public class ServerLoginFrameGUI implements Initializable 
{
	
	Stage s;
	    @FXML
	    private Button btnLogin;

	    @FXML
	    private Button btnExit;

	    @FXML
	    private TextField txtPath;

	    @FXML
	    private TextField txtUsername;

	    @FXML
	    private TextField txtPassword;
	  
	    public void loginListener(ActionEvent event) {
	    	Server.setDBAtributers(txtPath.getText(), txtUsername.getText(), txtPassword.getText());
	    	((Node)event.getSource()).getScene().getWindow().hide();
	    }
	    
		public void exitBtnListenner(ActionEvent e) throws Exception
		{
			System.exit(0);
		}
		
		public void start(Stage primaryStage) throws Exception {	
			Parent root = FXMLLoader.load(getClass().getResource("ServerLoginFrame.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			s=primaryStage;
			primaryStage.show();		
		}
	    
	

	  
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			
		}

}
