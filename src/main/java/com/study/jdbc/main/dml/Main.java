package main.java.com.study.jdbc.main.dml;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import main.java.com.study.jdbc.entity.User;
import main.java.com.study.jdbc.repository.UserDao;

public class Main {

	public static void main(String[] args) {
		
		UserDao dao = new UserDao();
		
		User user = User.builder()
				.username("abcd")
				.build();
		
		int result = dao.insertUser(user);
		System.out.println(result > 0 ? "user_id [" + user.getUser_id() + "] 등록완료!" : "등록실패!");
		
	}
}
