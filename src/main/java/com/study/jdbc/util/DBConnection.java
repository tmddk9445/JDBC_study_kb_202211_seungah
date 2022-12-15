package main.java.com.study.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

public class DBConnection {

	private static DBConnection instance = null;

	private DBConnection() {
	} // 생성자

	public static DBConnection getInstance() { // 싱글톤형식??
		if (instance == null) {
			instance = new DBConnection();

		}
		return instance;
	}

	public Connection getConnection() { // 오류 중에 surround catch
		Connection connection = null;
		String url = null;
		String username = null;
		String password = null;

		try {
			Class.forName(Driver.class.getName()); // Class.forName >> 드라이브 객체 생성
			System.out.println("데이터베이스 드라이브 로딩 성공!");

			url = "jdbc:mysql://localhost:3306/subquery_study";
			// >> 리턴을 하면 subquery_study에 연결
			username = "root";
			password = "root";

			connection = DriverManager.getConnection(url, username, password);
			// >> getConnection을 사용해 connection객체 생성(1)
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패!");
		} // 객체 node(생성)을 시작한다.
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("데이터베이스 연결 실패!");
		}
		return connection; // >> 생성된 객체를 return(2)
	}

}
