package com.atm.interfaces;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.atm.model.LimitedCard;
import com.atm.service.BankService;

/**
 * <p>
 * descrption:
 * </p>
 * 存款程序界面
 * 
 * @author xxxx
 * @date 2015年6月19日
 * @Copyright 2015 Soft, Inc. All rights reserved.
 */
public class DepositFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6193114597460837061L;

	/*BankService服务*/
	private BankService bankService = new BankService();
	

	private JLabel mLabel,kLabel;
	private JTextField amountField;


	
	/* 构造方法 */
	public DepositFrame(LimitedCard card) {
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
		mLabel.setBounds(100, 30, 120, 20);
		//真实操作为放入纸钞,这里模拟存款
		kLabel = new JLabel("请输入存款金额:");
		kLabel.setBounds(60, 90, 120, 20);
		
		amountField = new JTextField(15);
		amountField.setBounds(160, 90, 100, 20);
		

		JButton backBtn = new JButton("返回");
		backBtn.setBounds(80, 160, 60, 20);
		backBtn.addActionListener(new ActionListener()// 登录监听器
		{
			public void actionPerformed(ActionEvent e) {
				//返回主页
				close(DepositFrame.this);
				new MainFrame(card);
			}
		});

		JButton okBtn = new JButton("确认");
		okBtn.setBounds(150, 160, 60, 20);
		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Integer.parseInt(amountField.getText())%100!=0 ){
					JOptionPane.showMessageDialog(DepositFrame.this, "存款金额不是100整数倍,请重新输入!");
					return;
				}
				try {
					//确认存款
					card.depositMoney(Integer.parseInt(amountField.getText()));
					//更改db存款金额 
					int result = bankService.updateMoney(card);
					if(result > 0){
						//存款完成,跳转主页
						close(DepositFrame.this);
						new MainFrame(card);
					}else{
						JOptionPane.showMessageDialog(DepositFrame.this, "系统忙,存款失败,请稍等!");
						return;
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(DepositFrame.this, e2.getMessage()+",存款失败,请稍等!");
					return;
				}
				
			}
		});
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jl1.add(mLabel);
		jl1.add(kLabel);
		jl1.add(amountField);
		jl1.add(backBtn);
		jl1.add(okBtn);
		
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
	public static void open(JFrame frame,Map<String,Object> params){
		frame.setVisible(true);
		
	}
	
}
