package com.atm.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * descrption:
 * </p>
 * 抽象数据库持久化定义
 * 
 * @author xxxx
 * @date 2015年6月25日
 * @Copyright 2015 Soft, Inc. All rights reserved.
 */
public abstract class AbstractDao<T> {

	public static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/atm";
	public static final String DB_USERNAME = "root";
	public static final String DB_PWD = "splatform_admin";//数据库密码123

	/**
	 * 获取连接，并打开
	 * 
	 * @return
	 */
	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(DB_DRIVER);
			conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PWD);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 关闭连接
	 * 
	 * @param conn
	 */
	public void closeConnection(Connection conn) {
		try {
			if (!conn.isClosed()) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询
	 * 
	 * @param sql
	 * @return
	 */
	public List<T> select(String sql) {
		return null;
	}

	/**
	 * 添加一个对象
	 */
	public void add(T t) {

	}

	/**
	 * 更新一个对象
	 */
	public void update(T t) {

	}

	/**
	 * 删除一个对象
	 */
	public void delete(T t) {

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Map> resultSetToList(ResultSet rs)
			throws java.sql.SQLException {
		if (rs == null)
			return Collections.EMPTY_LIST;
		ResultSetMetaData md = rs.getMetaData(); // 得到结果集(rs)的结构信息，比如字段数、字段名等
		int columnCount = md.getColumnCount(); // 返回此 ResultSet 对象中的列数
		List<Map> list = new ArrayList<Map>();
		Map rowData = new HashMap();
		while (rs.next()) {
			rowData = new HashMap(columnCount);
			for (int i = 1; i <= columnCount; i++) {
				rowData.put(md.getColumnName(i), rs.getObject(i));
			}
			list.add(rowData);
			System.out.println("list:" + list.toString());
		}
		return list;
	}
	
	/**
	 * 通过sql更新
	 * @param sql
	 * @return
	 */
	public int updateBySql(String sql) {
		return 0;
	}
	// public static void main(String[] args){
	// Connection conn=null;
	// try{
	// Class.forName(DB_DRIVER);
	// }catch (ClassNotFoundException e){
	// e.printStackTrace();
	// }
	// try{
	// conn=DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PWD);
	// }catch(SQLException e){
	// e.printStackTrace();
	// }
	// System.out.print(conn);
	// try{
	// conn.close();
	// }catch(SQLException e){
	// e.printStackTrace();
	// }
	// }
}