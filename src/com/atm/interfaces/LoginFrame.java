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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import net.sf.json.JSONObject;

import com.atm.model.LimitedCard;
import com.atm.service.BankService;

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
public class LoginFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6193114597460837061L;

	/*BankService服务*/
	private BankService bankService = new BankService();

	private JLabel uLabel,pLabel;
	private JTextField user;
	private JPasswordField password;

	/* 构造方法 */
	public LoginFrame() {
		this.setTitle("欢迎使用ATM终端机");
		loginInit();
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
	public void loginInit() {
		// 创建一个容器
        Container con = this.getContentPane();
        JLabel jl1 = new JLabel();
        jl1.setBounds(0, 0, 355, 265);
        
		uLabel = new JLabel("卡号:");
		uLabel.setBounds(10, 60, 70, 20);
		pLabel = new JLabel("密码:");
		pLabel.setBounds(10, 100, 70, 20);
		user = new JTextField(15);
		user.setBounds(100, 60, 150, 20);
		password = new JPasswordField(15);
		password.setBounds(100, 100, 150, 20);
		password.setEchoChar('*');

		JButton subBtn = new JButton("OK");
		subBtn.setBounds(100, 160, 70, 20);
		subBtn.addActionListener(new ActionListener()// 登录监听器
		{
			@SuppressWarnings({ "deprecation"})
			public void actionPerformed(ActionEvent e) {
				LimitedCard card = new LimitedCard("小米", user.getText(),password.getText(), 0);
				//1.查询数据库是否存在该卡号
				//2.密码确认
				List<LimitedCard> bankCardList = bankService.getBank(card);
				
				if(bankCardList.size()>0){
					//卡号存在
					JSONObject jsonObj = JSONObject.fromObject(bankCardList.get(0));
					
					LimitedCard dbCard = new LimitedCard();
					dbCard.setMoney(Double.parseDouble(jsonObj.getString("money")));
					dbCard.setName(jsonObj.getString("name"));
					dbCard.setPassword(jsonObj.getString("password"));
					dbCard.setCardNo(jsonObj.getString("card_no"));
					//判断密码
					if (password.getText().equals(dbCard.getPassword())) {
						close(LoginFrame.this);
						new MainFrame(dbCard);
					} else {
						JOptionPane
								.showMessageDialog(LoginFrame.this, "密码错误,请重新输入!");
					}
				}else{
					JOptionPane.showMessageDialog(LoginFrame.this, "账号错误,请重新输入!");
				}
				
				
				//3.结果返回
//				if (user.getText().equals(card.getCardNo())
//						&& password.getText().equals(card.getPassword())) {
//					//LoginFrame.this.setVisible(false);//隐藏当前
//					close(LoginFrame.this);
//					new MainFrame(card);
//				} else {
//					JOptionPane
//							.showMessageDialog(LoginFrame.this, "账号或密码错误,请重新输入!");
//				}
			}
		});

		JButton exitBtn = new JButton("Exit");
		exitBtn.setBounds(180, 160, 70, 20);
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jl1.add(uLabel);
		jl1.add(user);
		jl1.add(pLabel);
		jl1.add(password);
		jl1.add(subBtn);
		jl1.add(exitBtn);
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
