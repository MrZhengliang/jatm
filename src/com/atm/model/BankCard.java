package com.atm.model;

/**
 * <p>
 * descrption:
 * </p>
 * 抽象银行卡类
 * ForeignCard取款时应缴纳1%的手续费。
 * LimitedCard取款时不能超过1000元的限额。
 * @author xxxx
 * @date 2015年6月19日
 * @Copyright 2015 , Inc. All rights reserved.
 */
public abstract class BankCard {

	protected String name;
	protected String password;
	protected String cardNo;
	protected double money;

	public BankCard(){
	}
	
	public BankCard(String name, String cardNo, String password,double money) {
		super();
		this.password = password;
		this.name = name;
		this.cardNo = cardNo;
		this.money = money;
	}

	public String getName() {
		return name;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String getCardNo() {
		return cardNo;
	}

	public double getMoney() {
		return money;
	}
	
	/*取款*/
	public abstract void drawMoney(int someMoney);
	
	/*存款*/
	public void depositMoney(int someMoney) {
		this.money += someMoney;
	}
}
