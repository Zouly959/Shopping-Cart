package shopping.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import shopping.model.Order;
import shopping.model.Product;
import shopping.model.ShoppingCart;

public class ShoppingCartFrame {
	JTable shoppingCartTable;
	JFrame emptyShoppingCarGui = new JFrame("购物车");
	JFrame ShoppingCarGui = new JFrame("购物车");
	Vector<Vector<Object>> date = new Vector<Vector<Object>>();
	Vector<String> ColumnNames = new Vector<String>();
	JLabel jl2;//表示购物车宝贝数量
	JLabel jl0;//表示购物车总价
	ShoppingCart shoppingCart=new ShoppingCart();
	static CardLayout card = new CardLayout();
	static JPanel centerPanel=new JPanel(card);
	
	public ShoppingCartFrame(OrderGui order){
		 initShoppingCarGui(order);
	}
	
	public void initShoppingCarGui(OrderGui order) {
		

		ShoppingCarGui.setLayout(null); // 清除布局函数
		ShoppingCarGui.setResizable(false); // 设置窗体大小不可变

		ShoppingCarGui.setLayout(new BorderLayout()); // 新建BorderLayout布局
		
		// 中间
		//卡片一  空购物车
		JPanel panel0 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		ImageIcon icon = new ImageIcon("image/emptycar.png");
		JLabel label = new JLabel(icon);
		panel0.add(label);
		
		//卡片二  有东西的购物车
		JPanel panel2 = new JPanel(new FlowLayout(2));
		DefaultTableModel model = new DefaultTableModel() { // 表格数据不可改

			public boolean isCellEditable(int row, int column) {
				if( column == 3 ){
                    return true;//返回true则表明单元格可编辑
                }
				else return false;
			}
		};
		ColumnNames.add("商品编号");ColumnNames.add("商品名");ColumnNames.add("单价(元)");ColumnNames.add("数量");ColumnNames.add("总价");
		model.setDataVector(date,ColumnNames);
		shoppingCartTable = new JTable(model);// 创建一个表格，指定 所有行数据 和 表头
		SettingWindow.setTable(shoppingCartTable); // 设置表格

		JScrollPane shoppingCarScrollPane = new JScrollPane(shoppingCartTable);
		shoppingCarScrollPane.setBorder(null);
		
		//共计
		JPanel p0 = new JPanel(new GridLayout(2, 0));
		JLabel jl00 = new JLabel(" ");
		jl0 = new JLabel("共计 " + shoppingCartTable.getRowCount() + " 元");
		jl0.setFont(new Font("微软雅黑", Font.PLAIN, 19)); // 设置字体、样式、大小
		
		JButton jb0 = new JButton("删除商品");
		jb0.setFont(new java.awt.Font("黑体", 1, 20));
		JButton jb1 = new JButton("结算");
		jb1.setFont(new java.awt.Font("黑体", 1, 20));
		
		p0.add(jl00);
		p0.add(jl0);
		p0.add(jb0);
		p0.add(jb1);
		
		panel2.add(shoppingCarScrollPane);
		panel2.add(p0);
		
		panel2.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		centerPanel.add(panel0,"emptyShoppingCart");
		centerPanel.add(panel2,"ShoppingCart");
		if( shoppingCartTable.getRowCount() == 0 ) {
			setShoppingCartCenterPanel(0);
		}else {
			setShoppingCartCenterPanel(1);
		}
		ShoppingCarGui.add(centerPanel, BorderLayout.CENTER);

		
		// 标题
		JPanel panel1 = new JPanel(new GridLayout(2, 0));
		JLabel jl1 = new JLabel("购物车");
		jl1.setFont(new Font("微软雅黑", Font.BOLD, 25)); // 设置字体、样式、大小
		jl2 = new JLabel("共 " + shoppingCartTable.getRowCount() + " 件宝贝");
		jl2.setFont(new Font("微软雅黑", Font.PLAIN, 19)); // 设置字体、样式、大小
		panel1.add(jl1);
		panel1.add(jl2);
		panel1.setBorder(new EmptyBorder(10, 10, 10, 10));
		ShoppingCarGui.add(panel1, BorderLayout.NORTH);
		
		// 版权归属
		JPanel panel3 = new JPanel(new GridLayout(1, 0));
		JLabel jl3 = new JLabel("©2023 sylu沥沥樱");
		jl3.setFont(new Font("微软雅黑", Font.PLAIN, 15)); // 设置字体、样式、大小
		jl3.setHorizontalAlignment(SwingConstants.CENTER); // 设置控件左右居中对齐
		panel3.add(jl3);
		ShoppingCarGui.add(panel3, BorderLayout.SOUTH);
		// Display the window.
		ShoppingCarGui.setSize(450, 750);
		ShoppingCarGui.setVisible(false);
		// 设置窗口居中显示
		SettingWindow.setFrameNear(ShoppingCarGui);
		
		jb0.addActionListener(new java.awt.event.ActionListener() {//删除购物车商品
            public void actionPerformed(ActionEvent evt) {
            	DefaultTableModel dtm=(DefaultTableModel)shoppingCartTable.getModel();
                int row=shoppingCartTable.getSelectedRow();
                String s=(String)dtm.getValueAt(row, 0);
                double n=Double.parseDouble(dtm.getValueAt(row, 4).toString());
                JOptionPane.showMessageDialog(null, "商品 "+ s + " 删除成功！", "提醒", JOptionPane.WARNING_MESSAGE);
                dtm.removeRow(row);
                shoppingCart.getProlist().remove(row);
                jl2.setText("共 " + shoppingCartTable.getRowCount() + " 件宝贝");
                ShoppingCart.setTotal(ShoppingCart.getTotal()-n);
        		jl0.setText("共计 " + ShoppingCart.getTotal() + " 元");
        		if( shoppingCartTable.getRowCount() == 0 ) {
        			setShoppingCartCenterPanel(0);
        		}
            }
        });
		jb1.addActionListener(new ActionListener() {//结算购物车商品
			
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "结算成功", "提醒", JOptionPane.WARNING_MESSAGE);
				if(date.isEmpty()) {
					return;
				}
				Order orderlist=new Order(shoppingCart.getProlist(),ShoppingCart.getTotal());
				order.importList(orderlist);
				 ShoppingCart.setTotal(0.0);
				jl2.setText("共 " + 0 + " 件宝贝");
				jl0.setText("共计 " + ShoppingCart.getTotal() + " 元");
				shoppingCart.getProlist().clear();
				date.clear();
				shoppingCartTable.updateUI();
				setShoppingCartCenterPanel(0);
				OrderGui.setOrderCenterPanel(1);
			}
		});
		shoppingCartTable.getModel().addTableModelListener(new TableModelListener() {//商品数量修改
            public void tableChanged(TableModelEvent e) {
                int col = e.getColumn();
                int row = e.getFirstRow(); // 可能会发生多行同时修改，但这里只考虑一行
                
                if (col == 3 ) { // 因为列数从0开始算，所以要减一
                	double v4 = Double.valueOf(String.valueOf(shoppingCartTable.getValueAt(row, 4)));
                	ShoppingCart.setTotal(ShoppingCart.getTotal()-v4);
                    double v3 = 0;
                    try {
                        v3 = Double.valueOf(String.valueOf(shoppingCartTable.getValueAt(row, 3)));
                    } catch (NumberFormatException ex) { // 主要是防止输入的不是数值
                    }
                    double v2 = Double.valueOf(String.valueOf(shoppingCartTable.getValueAt(row, 2)));
                    shoppingCartTable.setValueAt(v2 * v3, row, 4); // 设置值，如果规则没做好，可能会循环触发更新事件而导致调用栈溢出。
                    v4 = Double.valueOf(String.valueOf(shoppingCartTable.getValueAt(row, 4)));
                    ShoppingCart.setTotal(ShoppingCart.getTotal()+v4);
                    jl0.setText("共计 " + ShoppingCart.getTotal() + " 元");
                    
                    shoppingCart.getProlist().get(row).setNum((int)v3);
                }
            }
        });
	}

	public JFrame getShoppingCarGui() {
		return ShoppingCarGui;
	}

	public void setShoppingCarGui(JFrame shoppingCarGui) {
		ShoppingCarGui = shoppingCarGui;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public JTable getShoppingCartTable() {
		return shoppingCartTable;
	}

	public void setShoppingCartTable(JTable shoppingCartTable) {
		this.shoppingCartTable = shoppingCartTable;
	}

	public JLabel getJl2() {
		return jl2;
	}

	public void setJl2(JLabel jl2) {
		this.jl2 = jl2;
	}

	public JLabel getJl0() {
		return jl0;
	}

	public void setJl0(JLabel jl0) {
		this.jl0 = jl0;
	}
	
	public static void setShoppingCartCenterPanel(int x) {
		if( x == 0 ) {
			card.show(centerPanel,"emptyShoppingCart");
		}else {
			card.show(centerPanel,"ShoppingCart");
		}
	}

	public Vector<Vector<Object>> getDate() {
		return date;
	}

	public void setDate(Vector<Vector<Object>> date) {
		this.date = date;
	}
	
}
