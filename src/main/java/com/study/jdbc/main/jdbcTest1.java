package main.java.com.study.jdbc.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.com.study.jdbc.util.DBConnection;

public class jdbcTest1 {

	public static void main(String[] args) {
		Connection connection = DBConnection.getInstance().getConnection();
		// >> 싱글톤 객체를 가져와서 우리가 연결했던 Connection return값을 가지고 와서 Connction타입에 넣었다.

		// 1215 수업시작
		String sql = "select * from score_mst";
		// >> select 쿼리문을 우리가 지정해주고
		// 쿼리문 작성

		// prepareStatement메소드를 호출하면서 생성 (실행준비)
		// sql에 "select * from score_mst" 작성

		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			// >> 쿼리를 받아들일 수 있는 공간(입력하는 공간) connection공간에 prepareStatement 메소드를 만들어서 pstmt에 넣어라
			// >> (실행이전 쿼리문을 담고만 있다.)
			ResultSet rs = pstmt.executeQuery();
			// >> pstmt안에 있는 쿼리문을 실행해라
			// >> executeQuery는 ResultSet 결과를 가지고 있는 Set을 불러온다.
			// >> ResultSet : 한번 소진하면 다시 불러올 수 없다. (한번쓰면 비워진다.)

			System.out.println("id\t name\t\t score");

			while (rs.next()) { // >> rs.next 가 false가 뜰 때까지 반복한다. 
				// >> 이터레이터..?처럼 한개씩 값을 가지고와서 비어질 때까지 반복한다.
				// >> 꺼냈을 때의 데이터는 rs
				System.out.println("id : " + rs.getInt(1) // >> rs.getInt : id값이 int이다. rs >> 첫번째 행이다.
				// >> rs.getInt(1)의 숫자는 Column번호이다. Column번호는 1부터 시작이다. (0부터 시작X) // id(Int)
						+ "\t name : " + rs.getString(2) // name(String)
						+ "\t score : " + rs.getInt(3)); // score(Int)
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
