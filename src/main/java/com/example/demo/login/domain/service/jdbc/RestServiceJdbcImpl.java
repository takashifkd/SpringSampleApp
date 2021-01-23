package com.example.demo.login.domain.service.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repositry.UserDao;
import com.example.demo.login.domain.service.RestService;

@Transactional
@Service
public class RestServiceJdbcImpl implements RestService {

	@Autowired
	@Qualifier("UserDaoJdbcImpl")
	UserDao dao;

	// １件登録用
	@Override
	public boolean insert(User user) {

		int result = dao.insertOne(user);

		if(result == 0) {
			return false;
		} else {
			return true;
		}

	}

	// １件検索用
	@Override
	public User selectOne(String userId) {

		return dao.selectOne(userId);
	}

	// 全件検索用
	@Override
	public List<User> selectMany() {

		return dao.selectMany();
	}

	@Override
	public boolean update(User user) {

		int result = dao.updateOne(user);

		if (result == 0) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean delete(String userId) {

		int result = dao.deleteOne(userId);

		if (result == 0) {
			return false;
		} else {
			return true;
		}
	}

}
