package com.acat.qq.db;

import java.sql.Connection;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mysql.jdbc.SQLError;

/**
 * ������ݿ����������
 * @author wujinfan
 *
 */
public class DbManager {
	private static final String DRIVER_NAME="com.mysql.jdbc.Driver";
	private static final String USERNAME="root";
	private static final String PASSWORD="123456";
	private static final String URL="jdbc:mysql://localhost:3306/acat_qq";
	
	public static DataSource dataSource = null;
	
	//׼���������Դ
	static {
		try{
			ComboPooledDataSource pool = new ComboPooledDataSource();
			pool.setDriverClass(DRIVER_NAME);
			pool.setUser(USERNAME);
			pool.setPassword(PASSWORD);
			pool.setJdbcUrl(URL);
			
			pool.setMaxPoolSize(30);//��ݿ�������Ӹ���
			pool.setMinPoolSize(5);
			dataSource = pool;
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("数据连接池加载失败");
		}
	}
	
	//ͨ��˷���������е�Connection���Ӷ���
	public static Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}
	
	
	
}
