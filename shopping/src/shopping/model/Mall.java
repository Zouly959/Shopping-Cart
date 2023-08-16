package shopping.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Mall {
	ArrayList<Product> prolist=new ArrayList<Product>();
	
	public ArrayList<Product> getProlist() {
		return prolist;
	}

	public void setProlist(ArrayList<Product> prolist) {
		this.prolist = prolist;
	}


	public ArrayList<Product> searchProduct(String pro) {//搜索商品
		ArrayList<Product> list=new ArrayList<Product>();
		for(Product a: prolist) {
			if(a.getName().indexOf(pro)!=-1) {
				list.add(a);
			}
		}
		return list;
	}



}
