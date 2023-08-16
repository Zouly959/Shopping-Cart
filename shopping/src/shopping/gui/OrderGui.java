package shopping.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import shopping.model.Order;
import shopping.model.Product;

public class OrderGui {
	JTable orderTable;
	JFrame emptyorderGui = new JFrame("订单");
	JFrame orderGui = new JFrame("订单");
	Vector<Vector<Object>> vDate = new Vector<Vector<Object>>();//表格内容
	Vector<String> vName = new Vector<String>();//表头
	static CardLayout orderCard = new CardLayout();
	static JPanel centerPanel = new JPanel(orderCard);

	public OrderGui() {
		initorderGui();
	}

	public void initorderGui() {

		orderGui.setLayout(null); // 清除布局函数
		orderGui.setResizable(false); // 设置窗体大小不可变
		// orderGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		orderGui.setLayout(new BorderLayout()); // 新建BorderLayout布局

		// 标题
		JPanel panel1 = new JPanel(new FlowLayout());
		JLabel jl1 = new JLabel("我的订单");
		jl1.setFont(new Font("微软雅黑", Font.BOLD, 30)); // 设置字体、样式、大小
		panel1.add(jl1);
		panel1.setBorder(new EmptyBorder(10, 10, 10, 10));
		orderGui.add(panel1, BorderLayout.NORTH);

		// 中间
		//卡片一  空订单
		JPanel panel0 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		ImageIcon icon = new ImageIcon("image/emptyOrder.png");
		JLabel label = new JLabel(icon);
		panel0.add(label);
						
		//卡片二 非空订单
		JPanel panel2 = new JPanel(new FlowLayout());
		JPanel p0 = new JPanel(new FlowLayout());
	

		vName.add("商品编号");vName.add("商品名");vName.add("单价(元)");vName.add("数量");vName.add("总价");

		DefaultTableModel model = new DefaultTableModel() { // 表格数据不可改

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		model.setDataVector(vDate,vName);
		orderTable = new JTable(model);// 创建一个表格，指定 所有行数据 和 表头
		SettingWindow.setTable(orderTable); // 设置表格
		
		JScrollPane orderScrollPane = new JScrollPane(orderTable);
		orderScrollPane.setBorder(null);


		panel2.add(p0);
		panel2.add(orderScrollPane);
		panel2.setBorder(new EmptyBorder(10, 10, 40, 10));
		
		centerPanel.add(panel0,"emptyOrder");
		centerPanel.add(panel2,"Order");
		if( getvDate().isEmpty() ) {
			setOrderCenterPanel(0);
		}else {
			setOrderCenterPanel(1);
		}
		orderGui.add(centerPanel);

		// 版权归属
		JPanel panel3 = new JPanel(new FlowLayout());
		JLabel jl3 = new JLabel("©2023 sylu沥沥樱");
		jl3.setFont(new Font("微软雅黑", Font.PLAIN, 15)); // 设置字体、样式、大小
		jl3.setHorizontalAlignment(SwingConstants.CENTER); // 设置控件左右居中对齐

		panel3.add(jl3);
		orderGui.add(panel3, BorderLayout.SOUTH);

		// Display the window.
		orderGui.setSize(450, 750);
		orderGui.setVisible(false);
		// 设置窗口显示位置
		SettingWindow.setFrameNear(orderGui);
	}

	
	public JTable getOrder() {
		return orderTable;
	}

	public void setOrder(JTable order) {
		this.orderTable = order;
	}

	public JFrame getOrderGui() {
		return orderGui;
	}

	public void setOrderGui(JFrame orderGui) {
		this.orderGui = orderGui;
	}
	

	public Vector<Vector<Object>> getvDate() {
		return vDate;
	}

	public void setvDate(Vector<Vector<Object>> vDate) {
		this.vDate = vDate;
	}

	public void importList(Order orderlist) {//将订单导入表格
		// TODO Auto-generated method stub	
		for(Product e:orderlist.getList()){
			Vector<Object> v=new Vector<Object>();
			v.add(e.getId());
			v.add(e.getName());
			v.add(e.getPrice());
			v.add(e.getNum());
			double n=e.getPrice()*e.getNum();
			v.add(n);
			vDate.add(v);
		}
		Vector<Object> row=new Vector<Object>();
		row.add(null);
		row.add(null);
		row.add(null);
		row.add("总计:");
		row.add(orderlist.getTotal());
		vDate.add(row);
		row=new Vector<Object>();row.add(null);row.add(null);row.add(null);row.add(null);row.add(null);
		vDate.add(row);
		orderTable.updateUI();
	}
	
	public static void setOrderCenterPanel(int x) {
		if( x == 0 ) {
			orderCard.show(centerPanel,"emptyOrder");
		}else {
			orderCard.show(centerPanel,"Order");
		}
	}
}

