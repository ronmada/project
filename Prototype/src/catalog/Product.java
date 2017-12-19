package catalog;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Product implements Serializable{
	private String _product_ID;
	private String _product_Name;
	private String _product_Type;

public Product(String id,String name,String type )
{
	_product_ID=id;
	_product_Name=name;
	_product_Type=type;
}

	public String get_product_ID() {
		return _product_ID;
	}
	
	public void set_product_ID(String _product_ID) {
		this._product_ID = _product_ID;
	}
	
	public String get_product_Name() {
		return _product_Name;
	}
	
	public void set_product_Name(String _product_Name) {
		this._product_Name = _product_Name;
	}
	
	public String get_product_Type() {
		return _product_Type;
	}
	
	public void set_product_Type(String _product_Type) {
		this._product_Type = _product_Type;
	}
	public String toString()
	{
		return(this.get_product_Name());
	}

}
