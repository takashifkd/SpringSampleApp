package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.repositry.UserDao;

@SpringBootTest
@Transactional
public class UserDaoTest {

	@Autowired
	@Qualifier("UserDaoJdbcImpl")
	UserDao dao;

	// カウントメソッドのテスト１
	@Test
	public void countTest1() {
		// カウントメソッドの結果が２件である
		assertEquals(dao.count(), 2);
	}

	@Test
	@Sql("/testdata.sql")
	public void countTest2() {
		assertEquals(dao.count(), 3);
	}

}
