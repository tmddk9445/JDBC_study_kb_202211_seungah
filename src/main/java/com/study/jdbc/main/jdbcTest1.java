package main.java.com.study.jdbc.main;

import java.sql.Connection;

import main.java.com.study.jdbc.util.DBConnection;

public class jdbcTest1 {

	public static void main(String[] args) {
		Connection connection = DBConnection.getInstance().getConnection();
		
		System.out.println(connection);
	}

}
