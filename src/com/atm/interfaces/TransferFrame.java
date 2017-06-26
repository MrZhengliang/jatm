package com.atm.interfaces;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import net.sf.json.JSONObject;

import com.atm.model.LimitedCard;
import com.atm.service.BankService;

/**
 * <p>
 * descrption:
 * </p>
 * 转账程序界面
 * 
 * @author xxxx
 * @date 2015年6月19日
 * @Copyright 2015 Soft, Inc. All rights reserved.
 */
public class TransferFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6193114597460837061L;
	/*BankService服务*/
	private BankService bankService = new BankService();

	private JLabel mLabel,kLabel,aLabel;
	private JTextField amountField,accountField;


	
	/* 构造方法 */
	public TransferFrame(LimitedCard card) {
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
		
		aLabel = new JLabel("请输入转入账户:");
		aLabel.setBounds(60, 80, 120, 20);
		accountField = new JTextField(15);
		accountField.setBounds(160, 80, 120, 20);
		
		
		kLabel = new JLabel("请输入转账金额:");
		kLabel.setBounds(60, 110, 120, 20);
		
		amountField = new JTextField(15);
		amountField.setBounds(160, 110, 100, 20);
		

		JButton backBtn = new JButton("返回");
		backBtn.setBounds(80, 160, 60, 20);
		backBtn.addActionListener(new ActionListener()// 登录监听器
		{
			public void actionPerformed(ActionEvent e) {
				//返回主页
				close(TransferFrame.this);
				new MainFrame(card);
			}
		});

		JButton okBtn = new JButton("确认");
		okBtn.setBounds(150, 160, 60, 20);
		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Integer.parseInt(amountField.getText())%100!=0 ){
					JOptionPane.showMessageDialog(TransferFrame.this, "输入金额不是100整数倍,请重新输入!");
					return;
				}
				if(Integer.parseInt(amountField.getText()) > card.getMoney() ){
					JOptionPane.showMessageDialog(TransferFrame.this, "输入金额超出余额,当前余额["+card.getMoney()+"],请重新输入!");
					return;
				}
				try {
					//确认转账,保证事务唯一
					//1.判断转入账号是否有效
					//2.进行本方扣款和对方存款操作
					LimitedCard otherCard = new LimitedCard("", accountField.getText(),"", 0);
					List<LimitedCard> bankCardList = bankService.getBank(otherCard);
					
					int transResult = 0 ;
					if(bankCardList.size()>0){
						//转入卡号有效，进行本方扣款和对方存款操作
						JSONObject jsonObj = JSONObject.fromObject(bankCardList.get(0));
						LimitedCard dbCard = new LimitedCard();
						dbCard.setMoney(Double.parseDouble(jsonObj.getString("money")));
						dbCard.setName(jsonObj.getString("name"));
						dbCard.setPassword(jsonObj.getString("password"));
						dbCard.setCardNo(jsonObj.getString("card_no"));

						//本方扣款
						card.drawMoney(Integer.parseInt(amountField.getText()));
						//对方存款
						dbCard.depositMoney(Integer.parseInt(amountField.getText()));
						transResult = bankService.transMoney(card,dbCard);
					}
					
					if(transResult > 1){
						//转账完成,跳转主页
						JOptionPane.showMessageDialog(TransferFrame.this, "转账成功!");
						close(TransferFrame.this);
						new MainFrame(card);
					}else{
						JOptionPane.showMessageDialog(TransferFrame.this, "转账失败,请稍等!");
						return;
					}
//					
//					card.drawMoney(Integer.parseInt(amountField.getText()));
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(TransferFrame.this, e2.getMessage()+",转账失败,请稍等!");
					return;
				}
			}
		});
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jl1.add(mLabel);
		jl1.add(kLabel);
		jl1.add(aLabel);
		jl1.add(accountField);
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
		new TransferFrame(card);
	}
}
