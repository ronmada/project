package catalog;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import catalog.ProductFormController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import client.*; 

public class ProductFrameController implements Initializable
{
	private ProductFormController pfc;	
	private static int itemIndex;
	ObservableList<String> list;
	@FXML
	private Label lblProductID;	
	@FXML
	private Label lblProductName;
	@FXML
	private Label lblProductType;
	@FXML
	private Button btnExit = null;
	@FXML
	private ComboBox<String> cmbProductList = new ComboBox<String>();
		
	public void ProductInfo(ActionEvent event) throws Exception {
		((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/catalog/ProductForm.fxml").openStream());
		ProductFormController productFormController = loader.getController();		
		productFormController.loadProduct(CatalogControler.getProducts().get(itemIndex));
		Scene scene = new Scene(root);			
		primaryStage.setScene(scene);		
		primaryStage.show();
	}
	
	public void start(Stage primaryStage) throws Exception {	
		Parent root = FXMLLoader.load(getClass().getResource("/catalog/ProductFrame.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();		
	}
	
	public void getExitBtn(ActionEvent event) throws Exception {
		System.out.println("exit Academic Tool");
		//System.exit(0);			
	}
	
	public void loadProduct(Product p1) {
		this.pfc.loadProduct(p1);
		}
	
	public void cmbSetProductList(ArrayList<Product> products)
	{
		System.out.println(products);
		ArrayList<String> al = new ArrayList<String>();	
		
		for(Product product:products){
		
			al.add(product.get_product_ID()+" "+product.get_product_Name());
		}
		list = FXCollections.observableArrayList(al);
		cmbProductList.setItems(list);

	}
	public void chooseProduct(ActionEvent e) throws Exception
	{
		itemIndex=cmbProductList.getSelectionModel().getSelectedIndex();
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		cmbSetProductList(CatalogControler.getProducts());
	}
	
	public void exitBtnListenner(ActionEvent e) throws Exception
	{
		System.exit(0);
	}
	
}
