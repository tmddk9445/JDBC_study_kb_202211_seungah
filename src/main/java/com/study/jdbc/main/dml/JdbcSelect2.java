package main.java.com.study.jdbc.main.dml;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import main.java.com.study.jdbc.util.DBConnection;

public class JdbcSelect2 {

	public static void main(String[] args) {
		Connection connection = DBConnection.getInstance().getConnection();
		Scanner scanner = new Scanner(System.in);
		System.out.println("작성자 id : ");
		int writerId = scanner.nextInt();		

		String sql = " select * from board_mst where writer_id = ?";

		try {
			PreparedStatement pstmt = connection.prepareStatement(sql); // connection객체안에 prepareStatement 메소드를 만들 때 sql쿼리문도 같이 준다.
			pstmt.setInt(1, writerId);

			ResultSet rs = pstmt.executeQuery();

			System.out.println("id\t title\t\t content\t\t read_count\t writer_id");

			while (rs.next()) {
				System.out.println("id : " + rs.getInt(1) + "\t title : " + rs.getString(2) + "\t content : "
						+ rs.getString(3) + "\t read_count : " + rs.getInt(4) + "\t writer_id : " + rs.getInt(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
