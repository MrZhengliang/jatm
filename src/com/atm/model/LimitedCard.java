package com.atm.model;

/**
 * <p>descrption: </p>
 * 银行卡限制
 * @author xxxx
 * @date   2015年6月19日
 * @Copyright 2015 Snail Soft, Inc. All rights reserved.
 */
public class LimitedCard extends BankCard {

	public LimitedCard(){
		
	}
	
	public LimitedCard(String name,String cardNo,String password,  double money) {
		super(name,cardNo, password, money);
	}

	/*	每次取款金额为100的倍数，总额不超过5000元，不允许透支。	*/
	@Override
	public void drawMoney(int someMoney) {
		if(someMoney > 5000) {
			throw new RuntimeException("取款不能超过5000");
		}
		if(getMoney() < someMoney) {
			throw new RuntimeException("余额不足");
		}
		super.money -= someMoney;
	}

	@Override
	public String toString() {
		return "LimitedCard [name=" + name + ", password=" + password
				+ ", cardNo=" + cardNo + ", money=" + money + "]";
	}

}
