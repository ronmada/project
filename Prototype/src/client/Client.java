package client;
import java.io.IOException;
import java.util.ArrayList;

import catalog.CatalogControler;
import catalog.Product;
import ocsf.client.AbstractClient;

public class Client extends AbstractClient
{
	final public static int DEFAULT_PORT = 5555;
	int port;
	static Client client;
	static boolean mail_box=false;
	public ArrayList<Product> products;
	
	public Client(String host, int port) 
	{
		    super(host, port); //Call the superclass constructor
		    try
		    {
				openConnection();
			} 
		    catch (IOException e)
		    {
				e.printStackTrace();
			}
	}
	  public void handleMessageFromServer(Object msg) 
	  {
		mail_box=true;
	    if(msg instanceof ArrayList<?> )
	    {
	    	productsRecived(msg);
	    }
	  }
	  public void handleMessageFromClientUI(Object message)  
	  {  
	    try
	    {
	    	sendToServer(message);
	    }
	    catch(IOException e)
	    {
	      System.out.println("Could not send message to server.  Terminating client.");
	      quit();
	    }
	  }
	  public void quit()
	  {
	    try
	    {
	      closeConnection();
	    }
	    catch(IOException e) 
	    {
	    	e.printStackTrace();
	    }
	    System.exit(0);
	  }
	  public static void main(String[] args) 
	  {
	    String host = "";
	    try
	    {
	      host = args[0];
	    }
	    catch(ArrayIndexOutOfBoundsException e)
	    {
	      host = "localhost";
	    }
	   client= new Client(host, DEFAULT_PORT);
	   CatalogControler.init_launch(client);	    

	  }
	  @SuppressWarnings("unchecked")
	  public void productsRecived(Object o) 
	  {
	  	 products = (ArrayList<Product>) o;
	  }
	  
	  public void sendUpdateNameToServer(Product product)
	  {
	    handleMessageFromClientUI(product);
	  }
	  
	  public  ArrayList<Product> getProducts()
	  /*this function activate when someone asks for product arraylist
	   * we send recive to the function handleMessageFromClientUI that sends 
	   * this signal to the function sendToServer which leads to the function
	   * handleMessageFromClient , then it return the updated arraylist 
	   * which will return to the ProductFrameController
	   */
	  {
		  client.handleMessageFromClientUI("recive");
		  while(!mail_box)
			try
		  	{
				Thread.sleep(10);
			} catch (InterruptedException e) 
		    {
				e.printStackTrace();
			}
	        mail_box=false;
	        return products;
	  }
}
