package catalog;

import java.util.ArrayList;

import catalog.*;
import client.Client;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CatalogControler extends Application
{
	public static  String [] parameters=null; 
	public static ProductFrameController Frame;
	public static Client client;
	public CatalogControler(){} 

	public static void setClient(Client c)
	{
		client =c;
	}
	public static void changeWindowToProductFrame(Pane root)
	{
		Stage primaryStage = new Stage();
		Scene scene = new Scene(root);		 	
		primaryStage.setScene(scene);		
		primaryStage.show();
	}
	public void start(Stage primaryStage) throws Exception 
	{ 
		Frame = new ProductFrameController(); // create ProductFrame fd fds
		Frame.start(primaryStage);
	}
	public static void updateName(Product p)
	{
		client.sendUpdateNameToServer(p);
	}
	
	public static ArrayList<Product> getProducts( )
	{
		return(client.getProducts());
	}
}
