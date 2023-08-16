package shopping.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import shopping.db.ProductDao;
import shopping.model.DateTransform;
import shopping.model.Mall;
import shopping.model.Product;

public class SearchGui {
	JFrame searchGui = new JFrame("搜索");

	
	public SearchGui(ShoppingCartFrame shoppingcar, Mall mall) {
		initSearchGui(shoppingcar,mall);
	}

	public void initSearchGui(ShoppingCartFrame shoppingcar, Mall mall) {
		
		
		searchGui.setLayout(null); // 清除布局函数
		searchGui.setResizable(false); // 设置窗体大小不可变

		searchGui.setLayout(new BorderLayout()); // 新建BorderLayout布局

		// 标题
		JPanel panel1 = new JPanel(new FlowLayout());
		JLabel jl1 = new JLabel("商品搜索");
		jl1.setFont(new Font("微软雅黑", Font.BOLD, 35)); // 设置字体、样式、大小
		panel1.add(jl1);
		searchGui.add(panel1, BorderLayout.NORTH);

		// 中间
		JPanel panel2 = new JPanel(new FlowLayout());
		JPanel p0 = new JPanel(new FlowLayout());
		JLabel jl2 = new JLabel("请输入商品名称： ");
		jl2.setFont(new Font("微软雅黑", Font.BOLD, 15)); // 设置字体、样式、大小
		JTextField jt1 = new JTextField(12); // 创建文本框
		JButton jb1 = new JButton("搜索");
		
		p0.add(jl2);
		p0.add(jt1);
		p0.add(jb1);
		JPanel p1 = new JPanel(new FlowLayout());
		JButton jb2= new JButton("添加到购物车");
		p1.add(jb2);
		
		Vector<Vector<Object>> vDate = new Vector<Vector<Object>>(getSelect(ProductDao.selecProduct()));
		Vector<String> vName = new Vector<String>();
		vName.addElement("商品编号");vName.addElement("商品名");vName.addElement("单价(元)");
		DefaultTableModel model = new DefaultTableModel() { // 表格数据不可改

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		model.setDataVector(vDate,vName);
		
		JTable searchList = new JTable(model);// 创建一个表格，指定 所有行数据 和 表头
		SettingWindow.setTable(searchList); // 设置表格
		searchList.setPreferredScrollableViewportSize(new Dimension(400, 210));
		JScrollPane searchtListScrollPane = new JScrollPane(searchList);

		panel2.add(searchtListScrollPane);
		
		panel2.add(p0);
		panel2.add(searchtListScrollPane);
		panel2.add(p1);
		searchGui.add(panel2);
		
		// 版权归属
		JPanel panel3 = new JPanel(new FlowLayout());
		JLabel jl3 = new JLabel("©2023 sylu沥沥樱");
		jl3.setFont(new Font("微软雅黑", Font.PLAIN, 15)); // 设置字体、样式、大小
		jl3.setHorizontalAlignment(SwingConstants.CENTER); // 设置控件左右居中对齐

		panel3.add(jl3);
		searchGui.add(panel3, BorderLayout.SOUTH);

		// Display the window.
		searchGui.setSize(450, 450);
		searchGui.setVisible(false);
		// 设置窗口居中显示
		SettingWindow.setFrameNear(searchGui);

		jb1.addActionListener(new ActionListener() {//搜索商品

			public void actionPerformed(ActionEvent e) {
				vDate.clear();
				String pro=jt1.getText();
				ArrayList<Product> prolist=new ArrayList<Product>(ProductDao.selectName(pro));
				DateTransform.changeList(prolist,vDate);
				searchList.updateUI();
			}
		});
		jb2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {//添加商品到购物车
				String inputValue = JOptionPane.showInputDialog("请输入添加的商品数量"); 
				int a=Integer.parseInt(inputValue);
				DefaultTableModel dtm=(javax.swing.table.DefaultTableModel)searchList.getModel();
				int row=searchList.getSelectedRow();
				 Vector<Object> v=new Vector<Object>();
		            v.add(dtm.getValueAt(row, 0));
		            v.add(dtm.getValueAt(row, 1));
		            v.add(dtm.getValueAt(row, 2));
		            v.add(a);
		            double b=(double) dtm.getValueAt(row, 2);
		            double sum=a*b;
		            v.add(sum);
		            DateTransform pro = new DateTransform();
		            
		            int i=shoppingcar.getShoppingCart().addProduct(pro.productTransform(v));
		            if(i!=-1) {//添加购物车已有商品
		            	DefaultTableModel dd=(DefaultTableModel)shoppingcar.shoppingCartTable.getModel();
		            	dd.setValueAt(shoppingcar.getShoppingCart().getProlist().get(i).getNum(), i, 3);
		            	double total=shoppingcar.getShoppingCart().getProlist().get(i).getNum()*shoppingcar.getShoppingCart().getProlist().get(i).getPrice();
		            	dd.setValueAt(total, i, 4);
		            }
		            else {//添加一行新商品
		            	shoppingcar.getDate().add(v);
		            	shoppingcar.getShoppingCartTable().updateUI();
		            	shoppingcar.getJl2().setText("共 " + shoppingcar.getShoppingCartTable().getRowCount() + " 件宝贝");
		        		double price=Double.parseDouble(v.get(2).toString());
		        		shoppingcar.getJl0().setText("共计 " + shoppingcar.getShoppingCart().getTotal() + " 元");
		            }
		          
		            shoppingcar.setShoppingCartCenterPanel(1);
			}
		});
	}
	 public Vector<Vector<Object>> getSelect( List<Product> list){
		 
			//Object[][] results=new Object[list.size()][3];
		    Vector<Vector<Object>> result=new Vector<Vector<Object>>();
			for(int i=0;i<list.size();i++) {
				Vector<Object> results=new Vector<Object>();
				Product product=list.get(i);
				results.add(product.getId());
				results.add(product.getName());
				results.add(product.getPrice());
				result.add(results);
//				results[i][0]=product.getId();
//				results[i][1]=product.getName();
//				results[i][2]=product.getPrice();
//				results[i][3]=product.getNum();
			}
			
			return result;
			
		}
//	 public Object[][] getSelectName( List<Product> list){
//			Object[][] results=new Object[list.size()][3];
//			for(int i=0;i<list.size();i++) {
//				Product product=list.get(i);
//				results[i][0]=product.getId();
//				results[i][1]=product.getName();
//				results[i][2]=product.getPrice();
//			}
//			return results;
//			
//		}

	public JFrame getSearchGui() {
		return searchGui;
	}

	public void setSearchGui(JFrame searchGui) {
		this.searchGui = searchGui;
	}
	
}
