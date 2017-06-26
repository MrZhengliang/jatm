package com.atm.service;

import java.util.ArrayList;
import java.util.List;

import com.atm.dao.AbstractDao;
import com.atm.dao.BankDao;
import com.atm.model.LimitedCard;

/**
 * <p>descrption: </p>
 * 
 * @author xxxx
 * @date   2015年6月26日
 * @Copyright 2015 Snail Soft, Inc. All rights reserved.
 */
public class BankService {
	
	private AbstractDao<LimitedCard> bankDao = new BankDao<LimitedCard>();
	
	/*获取卡号信息*/
	public List<LimitedCard> getBank(LimitedCard card){
		List<LimitedCard> tList = null;
		StringBuffer sbf = new StringBuffer();
		sbf.setLength(0);
		sbf.append("select t.* from t_bank_card t where t.card_no = "+ card.getCardNo());
		tList = bankDao.select(sbf.toString());
		if(null == tList){
			return new ArrayList<LimitedCard>();
		}
		return tList;
	}

	/*更改存款余额*/
	public int updateMoney(LimitedCard card) {
		int result = 0;
		StringBuffer sbf = new StringBuffer();
		sbf.setLength(0);
		sbf.append("update t_bank_card t set t.money = " + card.getMoney() + " where t.card_no = "+ card.getCardNo());
		result = bankDao.updateBySql(sbf.toString());
		return result;
	}

	/*转账操作*/
	public int transMoney(LimitedCard ownCard, LimitedCard dbCard) {

		int oresult = 0;
		int dresult = 0;
		StringBuffer osbf = new StringBuffer();
		osbf.setLength(0);
		osbf.append("update t_bank_card t set t.money = " + ownCard.getMoney() + " where t.card_no = "+ ownCard.getCardNo());
		oresult = bankDao.updateBySql(osbf.toString());
		
		StringBuffer dsbf = new StringBuffer();
		dsbf.setLength(0);
		dsbf.append("update t_bank_card t set t.money = " + dbCard.getMoney() + " where t.card_no = "+ dbCard.getCardNo());
		dresult = bankDao.updateBySql(dsbf.toString());
		
		return oresult + dresult;
	}
	
	
	
	/*更改卡信息*/
	public int updateCardInfo(LimitedCard card) {
		int result = 0;
		StringBuffer sbf = new StringBuffer();
		sbf.setLength(0);
		sbf.append("update t_bank_card t set t.password = " + card.getPassword() + " where t.card_no = "+ card.getCardNo());
		result = bankDao.updateBySql(sbf.toString());
		return result;
	}
}
