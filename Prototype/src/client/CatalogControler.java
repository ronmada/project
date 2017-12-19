package client;

import java.util.ArrayList;

import gui.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class CatalogControler extends Application 
{
	public static  String [] parameters=null; 
	public ProductFrameController Frame;
	public static Client client;
	public CatalogControler(){}
	public static void init_launch(Client c) 
	{
		client=c;
	    launch(parameters);
	}
	public void start(Stage primaryStage) throws Exception 
	{
		Frame = new ProductFrameController(); // create ProductFrame
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
