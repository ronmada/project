package server;

import javafx.application.Application;
import javafx.stage.Stage;

public class GuiOpener extends Application
{
	public static String [] parameters=null; 
	public ServerLoginFrameGUI Frame;
	
	public GuiOpener() {};
	
	public static void init_launch() 
	{
		System.out.println("DSadas");
	    Application.launch(parameters);
	}
	
	public void start(Stage primaryStage) throws Exception 
	{
		Frame = new ServerLoginFrameGUI(); // create ProductFrame
		Frame.start(primaryStage);
	}
				
}