package shopping.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import shopping.db.CartDao;
import shopping.db.ProductDao;
import shopping.model.DateTransform;
import shopping.model.Mall;
import shopping.model.Product;
import shopping.model.ShoppingCart;
import shopping.model.User;

public class ProductListFrame {

	JTable productListTable;
	JFrame productListGui = new JFrame("商城");

	public ProductListFrame(ShoppingCartFrame shoppingCart, Mall mall) {
		initComponents(shoppingCart, mall);
	}
// 商品列表组件初始化
	private void initComponents(ShoppingCartFrame shoppingCart, Mall mall) {

		productListGui.setLayout(null); // 清除布局函数
		productListGui.setResizable(false); // 设置窗体大小不可变

		productListGui.setLayout(new BorderLayout()); // 新建BorderLayout布局

		// 中间
		JPanel panel2 = new JPanel(new FlowLayout());

		DefaultTableModel model = new DefaultTableModel() { // 表格数据不可改
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		Vector<Vector<Object>> date = new Vector<Vector<Object>>(getSelect(ProductDao.selecProduct()));// 数据 date 定为二维可变数组
		Vector<String> names = new Vector<String>();
		names.add("商品编号");
		names.add("商品名");
		names.add("单价(元)");
		DateTransform.changeList(mall.getProlist(),date); // 将商品信息从ArrayList转化为Vector，用于表格显示
		model.setDataVector(date, names); // 用新的行date替换当前的date实例变量

		JTable productListTable = new JTable(model);// 创建一个表格，指定 所有行数据 和 表头
		SettingWindow.setTable(productListTable); // 设置表格

		JScrollPane ProductListScrollPane = new JScrollPane(productListTable);

		panel2.add(ProductListScrollPane);
		panel2.setBorder(new EmptyBorder(10, 10, 10, 10));
		productListGui.add(panel2, BorderLayout.CENTER);

		// 标题
		JPanel panel1 = new JPanel(new GridLayout(2, 0));
		JLabel jl1 = new JLabel("商品列表");
		jl1.setFont(new Font("微软雅黑", Font.BOLD, 25)); // 设置字体、样式、大小
		JLabel jl2 = new JLabel("共 " + productListTable.getRowCount() + " 件宝贝"); // getRowCount()获取table中的行数并返回
		jl2.setFont(new Font("微软雅黑", Font.PLAIN, 19)); // 设置字体、样式、大小
		panel1.add(jl1);
		panel1.add(jl2);
		panel1.setBorder(new EmptyBorder(10, 10, 10, 10));// 设置内边框间距，四个方向均为10
		productListGui.add(panel1, BorderLayout.NORTH);

		// 版权归属
		JPanel panel3 = new JPanel(new GridLayout(2, 0));
		JButton jb1 = new JButton("添加到购物车");
		jb1.setFont(new java.awt.Font("黑体", 1, 20));
		JLabel jl3 = new JLabel("©2023 sylu沥沥樱");
		jl3.setFont(new Font("微软雅黑", Font.PLAIN, 15)); // 设置字体、样式、大小
		jl3.setHorizontalAlignment(SwingConstants.CENTER); // 设置控件左右居中对齐
		//panel3.add(jb1, JPanel.RIGHT_ALIGNMENT);
		panel3.add(jb1, JPanel.CENTER_ALIGNMENT);
		panel3.add(jl3);
		productListGui.add(panel3, BorderLayout.SOUTH);

		// Display the window.
		productListGui.setSize(450, 750);
		productListGui.setVisible(false);
		// 设置窗口居中显示
		SettingWindow.setFrameNear(productListGui);

		jb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String inputValue = JOptionPane.showInputDialog("请输入添加的商品数量");// 显示输入框
				int a = Integer.parseInt(inputValue);
				DefaultTableModel dtm = (DefaultTableModel) productListTable.getModel();
				// 将选择的单行表格商品数据转化为一个商品信息
				int row = productListTable.getSelectedRow();
				Vector<Object> v = new Vector<Object>();
				v.add(dtm.getValueAt(row, 0));
				v.add(dtm.getValueAt(row, 1));
				v.add(dtm.getValueAt(row, 2));
				v.add(a);
				double b = (double) dtm.getValueAt(row, 2);
				double vSum = a * b;
				v.add(vSum);
				DateTransform pro = new DateTransform();
				ShoppingCart cart = shoppingCart.getShoppingCart();
				int index = cart.addProduct(pro.productTransform(v));
				ShoppingCart c=new ShoppingCart();
				if (index != -1) {// 添加购物车已有商品
					DefaultTableModel dd = (DefaultTableModel) shoppingCart.shoppingCartTable.getModel();
					dd.setValueAt(cart.getProlist().get(index).getNum(), index, 3); // 将 columnIndex 和 rowIndex 位置的单元格中的值设置为 aValue即更新数量
					double total = cart.getProlist().get(index).getNum()* cart.getProlist().get(index).getPrice();// 更新总价
					dd.setValueAt(total, index, 4);
				} else {// 添加一行新商品
  				    shoppingCart.getDate().add(v);// 先获取购物车表格数据，在后面一列加入商品信息
					shoppingCart.getShoppingCartTable().updateUI();
					shoppingCart.getJl2().setText("共 " + shoppingCart.getShoppingCartTable().getRowCount() + " 件宝贝");
					shoppingCart.getJl0().setText("共计 " + cart.getTotal() + " 元");
					c.setPid(dtm.getValueAt(row, 0).toString());
					c.setName(dtm.getValueAt(row, 1).toString());
					c.setPrice(Double.parseDouble(dtm.getValueAt(row, 2).toString()));
					c.setNum(a);
					c.setSum((double) dtm.getValueAt(row, 2)*a);
					User u=new User();
					String uname=u.getUserName();
					int count=CartDao.insertCar(uname,c);
					if (count==1) {
						JOptionPane.showMessageDialog(null, "添加成功");
					}
				}
				
				
				ShoppingCartFrame.setShoppingCartCenterPanel(1);
			}
		});
	}
	 public Vector<Vector<Object>> getSelect( List<Product> list){
		    Vector<Vector<Object>> result=new Vector<Vector<Object>>();
			for(int i=0;i<list.size();i++) {
				Vector<Object> results=new Vector<Object>();
				Product product=list.get(i);
				results.add(product.getId());
				results.add(product.getName());
				results.add(product.getPrice());
				result.add(results);
			}	
			return result;	
		}

	public JTable getProductList() {
		return productListTable;
	}

	public void setProductList(JTable productList) {
		productListTable = productList;
	}

	public JFrame getShoppingCarGui() {
		return productListGui;
	}

	public void setShoppingCarGui(JFrame shoppingCarGui) {
		productListGui = shoppingCarGui;
	}

}
