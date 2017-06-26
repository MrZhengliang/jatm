package com.atm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * <p>
 * descrption:
 * </p>
 * 银行数据持久类
 * 
 * @author xxxx
 * @param <T>
 * @date 2015年6月25日
 * @Copyright 2015 Soft, Inc. All rights reserved.
 */
public class BankDao<T> extends AbstractDao<T> {

	@Override
	public Connection getConnection() {
		return super.getConnection();
	}

	@Override
	public void closeConnection(Connection conn) {
		super.closeConnection(conn);
	}

	/**
	 * 通过sql查询
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List select(String sql) {
		List tList = null;
		Connection conn = null;
		try {
			conn = this.getConnection();
			PreparedStatement stat = conn.prepareStatement(sql);
			ResultSet rs = stat.executeQuery();
			tList = super.resultSetToList(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != conn) {
				this.closeConnection(conn);
			}
		}
		return tList;
	}

	@Override
	public void add(T t) {

	}

	@Override
	public void update(T t) {

	}

	@Override
	public void delete(T t) {

	}

	/**
	 * 通过sql更新
	 * 
	 * @param sql
	 * @return
	 */
	@Override
	public int updateBySql(String sql) {
		int result = 0;
		Connection conn = null;
		try {
			conn = this.getConnection();
			PreparedStatement stat = conn.prepareStatement(sql);
			result = stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != conn) {
				this.closeConnection(conn);
			}
		}
		return result;
	}

}
