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
 * 修改密码程序界面
 * 
 * @author xxxx
 * @date 2015年6月19日
 * @Copyright 2015 Soft, Inc. All rights reserved.
 */
public class SetPwdFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5017071947207012394L;
	/*BankService服务*/
	private BankService bankService = new BankService();

	private JLabel mLabel,sLabel,nLabel;
	private JTextField sourceField,newField;


	
	/* 构造方法 */
	public SetPwdFrame(LimitedCard card) {
		this.setTitle("欢迎使用ATM终端机");
		transferInit(card);
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
	public void transferInit(final LimitedCard card) {
		// 创建一个容器
        Container con = this.getContentPane();
        JLabel jl1 = new JLabel();
        jl1.setBounds(0, 0, 355, 265);
        
		mLabel = new JLabel("欢迎使用ATM终端机");
		mLabel.setBounds(100, 30, 120, 20);
		
		sLabel = new JLabel("请输入原密码:");
		sLabel.setBounds(60, 80, 120, 20);
		sourceField = new JTextField(15);
		sourceField.setBounds(160, 80, 120, 20);
		
		
		nLabel = new JLabel("请输入新密码:");
		nLabel.setBounds(60, 110, 120, 20);
		
		newField = new JTextField(15);
		newField.setBounds(160, 110, 100, 20);
		

		JButton backBtn = new JButton("返回");
		backBtn.setBounds(80, 160, 60, 20);
		backBtn.addActionListener(new ActionListener()// 登录监听器
		{
			public void actionPerformed(ActionEvent e) {
				//返回主页
				close(SetPwdFrame.this);
				new MainFrame(card);
			}
		});

		JButton okBtn = new JButton("确认");
		okBtn.setBounds(150, 160, 60, 20);
		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//确认转账,保证事务唯一
					//1.判断转入账号是否有效
					//2.进行本方扣款和对方存款操作
					
					if(!sourceField.getText().equals(card.getPassword())){
						JOptionPane.showMessageDialog(SetPwdFrame.this, "原密码输入不正确!");
						return;
					}
					
					LimitedCard newCard = new LimitedCard("", card.getCardNo(),newField.getText(), 0);
					
					int result = 0 ;
					result = bankService.updateCardInfo(newCard);
					
					if(result > 0){
						//转账完成,跳转主页
						JOptionPane.showMessageDialog(SetPwdFrame.this, "密码修改成功!");
						close(SetPwdFrame.this);
						new LoginFrame();//重新登录
					}else{
						JOptionPane.showMessageDialog(SetPwdFrame.this, "密码修改失败,请稍等!");
						return;
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(SetPwdFrame.this, e2.getMessage()+",转账失败,请稍等!");
					return;
				}
			}
		});
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jl1.add(mLabel);
		jl1.add(sLabel);
		jl1.add(nLabel);
		jl1.add(sourceField);
		jl1.add(newField);
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
	
//	/**
//	 * main方法，程序入口
//	 */
	/*模拟银行卡信息*/
	private static LimitedCard card = new LimitedCard("小米", "62015855","123123", 10000);

	
//	/**
//	 * main方法，程序入口
//	 */
	public static void main(String[] args) {
		/* 创建匿名对象 */
		new SetPwdFrame(card);
	}
}
