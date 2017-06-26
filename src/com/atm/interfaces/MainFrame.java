package com.atm.interfaces;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.atm.model.LimitedCard;

/**
 * <p>
 * descrption:
 * </p>
 * MainFrame主程序界面
 * 
 * @author xxxx
 * @date 2015年6月19日
 * @Copyright 2015 Soft, Inc. All rights reserved.
 */
public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6193114597460837061L;


	private JLabel mLabel,kLabel,yLabel;

	/* 构造方法 */
	public MainFrame(LimitedCard card) {
		this.setTitle("欢迎使用ATM终端机");
		mainInit(card);
		// 设置布局方式为绝对定位
        this.setLayout(null);
        this.setBounds(0, 0, 355, 265);
        // 居中显示
        this.setLocationRelativeTo(null);
        // 窗体可见
        this.setVisible(true);
		
	}

	/**
	 * 初始化
	 */
	public void mainInit(final LimitedCard card) {
		// 创建一个容器
        Container con = this.getContentPane();
        JLabel jl1 = new JLabel();
        jl1.setBounds(0, 0, 355, 265);
        
		mLabel = new JLabel("欢迎使用ATM终端机");
		mLabel.setBounds(100, 0, 120, 20);
		kLabel = new JLabel("您的卡号:"+card.getCardNo());
		kLabel.setBounds(60, 50, 120, 20);
		yLabel = new JLabel("您的余额"+card.getMoney());
		yLabel.setBounds(60, 90, 120, 20);
		

		JButton subBtn = new JButton("存款");
		subBtn.setBounds(40, 140, 70, 20);
		subBtn.addActionListener(new ActionListener()// 登录监听器
		{
			public void actionPerformed(ActionEvent e) {
				close(MainFrame.this);//关闭当前frame
				new DepositFrame(card);//打开取款frame
			}
		});

		JButton exitBtn = new JButton("取款");
		exitBtn.setBounds(120, 140, 70, 20);
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close(MainFrame.this);//关闭当前frame
				new DrawFrame(card);//打开取款frame
			}
		});
		
		JButton transBtn = new JButton("转账");
		transBtn.setBounds(40, 180, 70, 20);
		transBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close(MainFrame.this);//关闭当前frame
				new TransferFrame(card);//打开取款frame
			}
		});
		
		JButton setPwdBtn = new JButton("修改密码");
		setPwdBtn.setBounds(120, 180, 100, 20);
		setPwdBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close(MainFrame.this);//关闭当前frame
				new SetPwdFrame(card);
			}
		});
		
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jl1.add(kLabel);
		jl1.add(yLabel);
		jl1.add(subBtn);
		jl1.add(exitBtn);
		jl1.add(setPwdBtn);
		jl1.add(transBtn);
		
		con.add(jl1);
	}
	
	/**
	 * 关闭frame
	 * @param frame
	 */
	public static void close(JFrame frame){
		frame.setVisible(false);
	}
	/**
	 * 打开frame
	 * @param frame
	 */
	public static void open(JFrame frame){
		frame.setVisible(true);
	}
//	/**
//	 * main方法，程序入口
//	 */
	public static void main(String[] args) {
		/* 创建匿名对象 */
		//new MainFrame("62222");
	}
}
