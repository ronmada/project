package server;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import catalog.Product;
import catalog.ProductFrameController;
import client.Client;
import javafx.application.Application;
import javafx.stage.Stage;
import ocsf.server.*;



public class Server extends AbstractServer
{
	final public static int DEFAULT_PORT = 5555;
	private static String path;
	private static String username;
	private static String password;
	
	public Server(int port) 
	{
		super(port);
		
	}
	/*input : msg (string/product) , client when client send message this function 
	this function check whether the msg is a string or a products that needs to be update */
	  public void handleMessageFromClient(Object msg, ConnectionToClient client)
	  {
		 if(msg instanceof String) //in case of string from client
		 {  
			String command=(String) msg;
			if(command.equals("recive")) 
		    {
				try 
				{
					client.sendToClient(getProducts());
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			    System.out.println("Recived");
		    }
		 }
		 else if(msg instanceof Product) //in case update product was pressed
		 {
			 updateProduct(msg);
		 }
	  }
	
		private ArrayList<Product> getProducts()// get products from data base
		{
			ArrayList<Product> to_ret=new ArrayList<Product>();
			Connection conn=null;
			try 
		    {
				conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sys","root","231184mo");
		        System.out.println("SQL connection succeed");		        
		 	} 
			catch (SQLException ex) 
		 	{/* handle any errors*/
		        System.out.println("SQLException: " + ex.getMessage());
		        System.out.println("SQLState: " + ex.getSQLState());
		        System.out.println("VendorError: " + ex.getErrorCode());
		    }
			  Statement stmt;
			  String productId,productName,productType;
			  try 
			  {
				  stmt = conn.createStatement();
				  ResultSet rs = stmt.executeQuery("Select * From sys.product");
				  while (rs.next())// rs has all the columns from the specify index
				  {
					  productId = rs.getNString(1);
					  productName = rs.getNString(2);
					  productType = rs.getNString(3);
					  to_ret.add(new Product(productId,productName,productType));
				  }	
			  }
			  catch(SQLException e)
			  {
				  e.printStackTrace();
			  }
			  try 
			  {
				conn.close();
			  } 
			  catch (SQLException e)
			  {
				// TODO Auto-generated catch block
				e.printStackTrace();
			  }
			  return to_ret;
		}
		public synchronized void updateProduct(Object msg) 
		{
			Connection conn=null;
			Product p= (Product) msg;
			System.out.println(p);
			try 
			{
				conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sys","root","231184mo");
			} 
			catch (SQLException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//Product ms;
			ArrayList<String> castedMSG=new ArrayList<String>();
			castedMSG.add(0, p.get_product_Name());
			castedMSG.add(1, p.get_product_ID());
			try
			{
				PreparedStatement ps = conn.prepareStatement("UPDATE product SET Product_Name=? WHERE Product_ID=?;");
				ps.setString(1, castedMSG.get(0));
				ps.setString(2, castedMSG.get(1));
				ps.executeUpdate();
			}
			 catch (SQLException e) 
			 {	
				 e.printStackTrace();
			 }
			 try 
			 {
				 conn.close();
			 }
			 catch (SQLException e) 
			 {
				 e.printStackTrace();
			 }
		}
		public static void main(String[] args) 
		  {
		    int port = 0; //Port to listen on
		    Connection conn;
		    try
		    {
		      port = Integer.parseInt(args[0]); //Get port from command line
		    }
		    catch(Throwable t)
		    {
		      port = DEFAULT_PORT; //Set port to 5555
		    }
		   // GuiOpener guiOpener = new GuiOpener();
		    GuiOpener.init_launch();
		    System.out.println(path+' '+username+' '+password);
		    
		    Server sv = new Server(port);
		    
		    try {
				conn = DriverManager.getConnection(path,username,password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		    try 
			 {
				 conn.close();
			 }
			 catch (SQLException e) 
			 {
				 e.printStackTrace();
			 }
		    try 
		    {
		      sv.listen(); //Start listening for connections
		    } 
		    catch (Exception ex) 
		    {
		      System.out.println("ERROR - Could not listen for clients!");
		    }
		  }
		public static void setDBAtributers(String pathiy,String user,String pass)
		{
			path=pathiy;
			username=user;
			password=pass;
			
		}

	
}
