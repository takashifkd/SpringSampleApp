package com.example.demo.login.domain.service;

import java.util.List;

import com.example.demo.login.domain.model.User;

public interface RestService {

	// １件登録用
	public boolean insert(User user);

	// １件検索用
	public User selectOne(String userId);

	// 全件検索用
	public List<User> selectMany();

	// １件更新用
	public boolean update(User user);

	// １件削除用
	public boolean delete(String userId);

}
