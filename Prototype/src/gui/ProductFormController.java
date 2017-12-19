package gui;

import java.net.URL;
import java.util.ResourceBundle;
import client.Product;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import client.*;
public class ProductFormController implements Initializable {
	private Product p;
		
	@FXML
	private Label lblProductID;
	@FXML
	private Label lblProductName;
	@FXML
	private Label lblProductType;
	@FXML
	private Button btnUpdateInfo;
	@FXML
	private TextField txtProductID;
	@FXML
	private TextField txtProductName;
	
	@FXML
	private Button btnClose;
	
	@FXML
	private TextField txtProductType;
	
//	@FXML
//	private ComboBox cmbProductType;	
	
	ObservableList<String> list;
		
	public void loadProduct(Product p1){
		this.p=p1;
		this.txtProductID.setText(p.get_product_ID());
		this.txtProductName.setText(p.get_product_Name());
		this.txtProductType.setText(p.get_product_Type());
	}
	
	public void closeProductInfo(ActionEvent event) throws Exception {
		((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/gui/ProductFrame.fxml").openStream());
		Scene scene = new Scene(root);			
		primaryStage.setScene(scene);		
		primaryStage.show();
	}
	
	public void changeProductInfo(ActionEvent event) throws Exception 
	{
		p.set_product_Name(txtProductName.getText());
		CatalogControler.updateName(p);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{}
	
}

