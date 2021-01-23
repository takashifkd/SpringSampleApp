package com.example.demo.login.domain.repositry.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo.login.domain.model.User;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {

		// 戻り値用Userインスタンス
		User user = new User();

		// ResultSetの取得結果をUserインスタンスにセット
		user.setUserId(rs.getString("user_id"));
		user.setPassword(rs.getString("password"));
		user.setUserName(rs.getString("user_name"));
		user.setBirthday(rs.getDate("birthday"));
		user.setAge(rs.getInt("age"));
		user.setMarriage(rs.getBoolean("marriage"));
		user.setRole(rs.getString("role"));

		return user;
	}

}