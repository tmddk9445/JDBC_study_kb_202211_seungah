package main.java.com.study.jdbc.main.dml;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import main.java.com.study.jdbc.util.DBConnection;

public class JdbcInsert1 {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("등록할 아이디 입력 : ");
		String username = scanner.nextLine();
		
		Connection con = DBConnection.getInstance().getConnection();
		String sql = "insert into user_mst values(0,?)";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			
			int successCount = pstmt.executeUpdate();
			System.out.println("데이터 " + successCount + "건 등록완료!");
		} catch (SQLException e) {

			e.printStackTrace();
		}

		
	}
}
