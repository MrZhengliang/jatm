package com.atm.main;


import com.atm.interfaces.LoginFrame;
import com.atm.model.BankCard;
import com.atm.model.LimitedCard;

/**
 * <p>descrption: </p>
 * 入口程序
 * @author xxxx
 * @date   2015年6月19日
 * @Copyright 2015 Snail Soft, Inc. All rights reserved.
 */
public class AtmDemo {

	/*模拟银行卡信息*/
	private BankCard card = new LimitedCard("小米", "62015855","123123", 10000);
	

	/*程序主入口*/
	public static void main(String[] args) {
		/* 创建匿名对象,启动窗体 */
		new LoginFrame();
	}

	
	/*get / set*/
	public BankCard getCard() {
		return card;
	}

	public void setCard(BankCard card) {
		this.card = card;
	}

	
}
