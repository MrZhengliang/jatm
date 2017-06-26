package com.atm.test;

import javax.swing.JFrame;

/**
 * <p>descrption: </p>
 * 
 * @author fuzl
 * @date   2015年6月19日
 * @Copyright 2015  Soft, Inc. All rights reserved.
 */
public class SwingDemo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2449057393375386229L;

	/*构造方法*/
	public SwingDemo(){
		this.setTitle("我的第一个GUI程序");
		this.setSize(300,200);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * main方法，程序入口
	 */
	public static void main(String[] args){
		/*创建匿名对象*/
		new SwingDemo();
	}
}
