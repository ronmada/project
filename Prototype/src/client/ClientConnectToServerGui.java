package client;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import catalog.CatalogControler;
import catalog.ProductFormController;
import catalog.ProductFrameController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ClientConnectToServerGui implements Initializable
{
    @FXML
    private Button btnLogin;

    @FXML
    private Text lblIP;

    @FXML
    private TextField txtIP;

    @FXML
    void txtListener(ActionEvent event) {

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//  TODO Auto-generated method stub	
	}
	public void start(Stage primaryStage) throws Exception {	
		Parent root = FXMLLoader.load(getClass().getResource("ClientConnectToServer.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	@FXML////
	   public void btnListener(ActionEvent event)  throws Exception
	    {
			Client.connectToServer(txtIP.getText());
	    	((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/catalog/ProductFrame.fxml").openStream());
	    	CatalogControler.changeWindowToProductFrame(root);
	    }
}
