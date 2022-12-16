package main.java.com.study.jdbc.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import lombok.RequiredArgsConstructor;
import main.java.com.study.jdbc.entity.User;
import main.java.com.study.jdbc.util.DBConnectionMgr;

@RequiredArgsConstructor // 이 Constructor은 Args가 필수이다.
public class UserDao {

//	private final DBConnectionMgr pool; // 상수는 항상 값이 초기화 되어야한다.

//	public UserDao(DBConnectionMgr pool) { // pool : DI이다. 
//		this.pool = pool; // 생성될 때 외부에서 pool을 받아서 DBC pool을 생성한다.
//	} 

//	  싱글톤이기 때문에 DI 를 하지 않아도 된다.  외부에서 가지고 올 수 있기 때문에

	private DBConnectionMgr pool;

//	public UserDao() {
//		pool = DBConnectionMgr.getInstance(); // 싱글톤
//	} @RequiredArgsConstructor.....????????????????????????????????????????????????????????????????????????????????

	public int insertUser(User user) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int successCount = 0;

		try {
			con = pool.getConnection();
			sql = "insert into user_mst values(0, ?)"; // auto increment 값으로 insert한다. 무슨 값인지 모른다.
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			successCount = pstmt.executeUpdate();

			rs = pstmt.getGeneratedKeys(); // auto increment된 값을 찾아준다.
			user.setUser_id(rs.getInt(1)); // 그 값을 user에 set해주는 것이다.
		}

		catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs); // freeConnection : 객체 소멸
		}
		return successCount;
	}
}
