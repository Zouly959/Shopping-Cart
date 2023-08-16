package shopping.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


public class SettingWindow {
	public static void setFrameCenter(JFrame frame) {
		int windowWidth = frame.getWidth(); // 获取窗体的宽
		int windowHeight = frame.getHeight();// 获取窗体的高
		Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
		Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
		int screenWidth = screenSize.width; // 获取屏幕的宽
		int screenHeight = screenSize.height; // 获取屏幕的高
		frame.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);// 设置窗体出现的坐标
	}
		
	public static void setTable (JTable table){
		// 设置表头
        table.getTableHeader().setFont(new Font(null, Font.BOLD, 14));  // 设置表头名称字体样式
        table.getTableHeader().setResizingAllowed(false);               // 设置不允许手动改变列宽
        table.getTableHeader().setReorderingAllowed(false);             // 设置不允许拖动重新排序各列
        table.getTableHeader().setBackground(Color.CYAN);
        
        table.setShowVerticalLines(false); 	//不显示竖直方向的网格线
        
        //设置数据居中
        DefaultTableCellRenderer r   = new DefaultTableCellRenderer();   
        r.setHorizontalAlignment(JLabel.CENTER);   
        table.setDefaultRenderer(Object.class, r);
        
        table.setRowHeight(30);		//设置行高
        table.setSelectionBackground(Color.LIGHT_GRAY);     // 选中后字体背景
        //table.setShowGrid(false);
        table.setPreferredScrollableViewportSize(new Dimension(400, 490));
        
        
	}

	public static void setFrameNear(JFrame frame) {
		// TODO Auto-generated method stub
		int windowWidth = frame.getWidth(); // 获取窗体的宽
		int windowHeight = frame.getHeight();// 获取窗体的高
		Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
		Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
		int screenWidth = screenSize.width; // 获取屏幕的宽
		int screenHeight = screenSize.height; // 获取屏幕的高
		frame.setLocation(screenWidth / 2 + 192, screenHeight / 2 - windowHeight / 2);// 设置窗体出现的坐标
		
	}



}

