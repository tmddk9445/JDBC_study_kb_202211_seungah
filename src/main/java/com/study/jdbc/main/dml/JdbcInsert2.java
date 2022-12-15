package main.java.com.study.jdbc.main.dml;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.java.com.study.jdbc.util.DBConnection;

public class JdbcInsert2 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		List<String> usernameList = new ArrayList<>();

		while (true) {
			System.out.println("등록할 아이디 입력 : ");
			usernameList.add(scanner.nextLine()); // 입력받은 값 바로 add시키기
			System.out.println("아이디를 추가로 등록하시겠습니까? (Y/y, 취소하려면 아무키나 입력하세요.)");
			String selected = scanner.nextLine();
			if (!"yY".contains(selected.isBlank() ? "n" : selected)) { // 0번인덱스에서 1번전까지 : 0번인덱스 한 글자
				// (scanner.nextLine().substring(0, 1)) 입력받은 값이 포함되어있는지 확인한다.
				// selected값이 비어져있으면 "n"이라는 문자열이 있는지 확인하고
				// 비어있지 않으면 selected값을 받아서 출력한다.
				break; // 포함되어있지 않다면 >> break
			}
		}
		Connection con = DBConnection.getInstance().getConnection();
		String prefixSql = "insert into user_mst values"; // 머리말
		String valuesBody = ""; // 꼬리말
		String suffixSql = ";";

		for (int i = 0; i < usernameList.size(); i++) {
			valuesBody += "(0, ?)";
			if (i < usernameList.size() - 1) { // 제일 마지막에 쉼표 찍히지 않도록 하는 - 1
				valuesBody += ", ";
			}
		}
		System.out.println(valuesBody); // ?들어간 상태로 출력된다. >> pstmt에 값을 넣어주어야한다.

		try {
			PreparedStatement pstmt = con.prepareStatement(prefixSql + valuesBody + suffixSql); // prefixSql +
																								// valuesBody +
																								// suffixSql : sql
			for (int i = 0; i < usernameList.size(); i++) {
				pstmt.setString(i + 1, usernameList.get(i)); // (i + 1) : 1번 컬럼부터 값을 입력해준다. // List의 값은 0번 인덱스 부터 시작한다.
			}
			int successCount = pstmt.executeUpdate();
			System.out.println(successCount + "건 등록완료!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(usernameList);
	}
}

