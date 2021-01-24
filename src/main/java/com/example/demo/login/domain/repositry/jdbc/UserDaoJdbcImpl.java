package com.example.demo.login.domain.repositry.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repositry.UserDao;

@Repository("UserDaoJdbcImpl")
public class UserDaoJdbcImpl implements UserDao {

	@Autowired
	JdbcTemplate jdbc;

	@Autowired
	PasswordEncoder passwordEncoder;

	// Userテーブルの件数を取得
	@Override
	public int count() throws DataAccessException {
		// 全件取得してカウント
		int count = jdbc.queryForObject("SELECT COUNT(*) FROM m_user",
				Integer.class);
		return count;
	}

	// エンジニア領域件数取得
	@Override
	public Map<String, Object> count2() {
		Map<String, Object> countMap = new HashMap<String, Object>();
		countMap.put("front", jdbc.queryForObject("SELECT COUNT(*) FROM m_user"
				+ " WHERE type_of_engineer = 0", Integer.class));
		countMap.put("back", jdbc.queryForObject("SELECT COUNT(*) FROM m_user"
				+ " WHERE type_of_engineer = 1", Integer.class));
		countMap.put("infra", jdbc.queryForObject("SELECT COUNT(*) FROM m_user"
				+ " WHERE type_of_engineer = 2", Integer.class));
		return countMap;
	}

	// 使用言語件数取得
	@Override
	public Map<String, Object> count3() {
		Map<String, Object> countMap = new HashMap<String, Object>();
		countMap.put("data0", jdbc.queryForObject("SELECT COUNT(*) FROM m_user"
				+ " WHERE using_language = 0", Integer.class));
		countMap.put("data1", jdbc.queryForObject("SELECT COUNT(*) FROM m_user"
				+ " WHERE using_language = 1", Integer.class));
		countMap.put("data2", jdbc.queryForObject("SELECT COUNT(*) FROM m_user"
				+ " WHERE using_language = 2", Integer.class));
		countMap.put("data3", jdbc.queryForObject("SELECT COUNT(*) FROM m_user"
				+ " WHERE using_language = 3", Integer.class));
		countMap.put("data4", jdbc.queryForObject("SELECT COUNT(*) FROM m_user"
				+ " WHERE using_language = 4", Integer.class));
		countMap.put("data5", jdbc.queryForObject("SELECT COUNT(*) FROM m_user"
				+ " WHERE using_language = 5", Integer.class));
		return countMap;
	}

	// Userテーブルに１件insert
	@Override
	public int insertOne(User user) throws DataAccessException {

		// パスワード暗号化
		String password = passwordEncoder.encode(user.getPassword());

		// １件登録
		int rowNumber = jdbc.update("INSERT INTO m_user(user_id,"
				+ " password,"
				+ " user_name,"
				+ " birthday,"
				+ " age,"
				+ " marriage,"
				+ " type_of_engineer,"
				+ " using_language,"
				+ "role)"
				+ " VALUES(?,?,?,?,?,?,?,?,?)"
				, user.getUserId()
				, password
				, user.getUserName()
				, user.getBirthday()
				, user.getAge()
				, user.isMarriage()
				, user.getTypeOfEngineer()
				, user.getUsingLanguage()
				, user.getRole());

		return rowNumber;
	}

	@Override
	public User selectOne(String userId) throws DataAccessException {

		Map<String, Object> map = jdbc.queryForMap("SELECT * FROM m_user "
				+ "WHERE user_id = ?", userId);

		User user = new User();
		user.setUserId((String) map.get("user_id"));
		user.setPassword((String) map.get("password"));
		user.setUserName((String) map.get("user_name"));
		user.setBirthday((Date) map.get("birthday"));
		user.setAge((int) map.get("age"));
		user.setMarriage((boolean) map.get("marriage"));
		user.setTypeOfEngineer((int) map.get("type_of_engineer"));
		user.setUsingLanguage((int) map.get("using_language"));
		user.setRole((String) map.get("role"));
		return user;
	}

	// M_USERテーブルの全データを取得
	@Override
	public List<User> selectMany() throws DataAccessException {

		// M_USERテーブルのデータを全件取得
		List<Map<String, Object>> getList = jdbc.queryForList("SELECT * FROM m_user");

		// 結果返却用の変数
		List<User> userList = new ArrayList<>();

		// 取得したデータを結果返却賞のListに格納
		for(Map<String, Object> map: getList) {
			User user = new User();

			// 取得したデータを格納
			user.setUserId((String) map.get("user_id"));
			user.setPassword((String) map.get("password"));
			user.setUserName((String) map.get("user_name"));
			user.setBirthday((Date) map.get("birthday"));
			user.setAge((int) map.get("age"));
			user.setMarriage((boolean) map.get("marriage"));
			user.setTypeOfEngineer((int) map.get("type_of_engineer"));
			user.setUsingLanguage((int) map.get("using_language"));
			user.setRole((String) map.get("role"));

			// 取得したレコードを追加
			userList.add(user);
		}

		return userList;
	}

	@Override
	public int updateOne(User user) throws DataAccessException {

		// パスワード暗号化
		String password = passwordEncoder.encode(user.getPassword());

		int rowNumber = jdbc.update("UPDATE m_user"
				+ " SET"
				+ " password = ?,"
				+ " user_name = ?,"
				+ " birthday = ?,"
				+ " age = ?,"
				+ " marriage = ?"
				+ " type_of_engineer = ?"
				+ " using_language = ?"
				+ " WHERE user_id = ?"
				, password
				, user.getUserName()
				, user.getBirthday()
				, user.getAge()
				, user.isMarriage()
				, user.getTypeOfEngineer()
				, user.getUsingLanguage()
				, user.getUserId());

		 // トランザクション確認
//		if (rowNumber > 0) {
//			throw new DataAccessException("トランザクションテスト") {};
//		}

		return rowNumber;
	}

	@Override
	public int deleteOne(String userId) throws DataAccessException {
		int rowNumber = jdbc.update("DELETE FROM m_user WHERE user_id = ?", userId);
		return rowNumber;
	}

	@Override
	public void userCsvOut() throws DataAccessException {

		// M_USERテーブルのデータを全件取得するSQL
		String sql = "SELECT * FROM m_user";

		// UserRowCallbackHandlerの生成
		UserRowCallbackHandler handler = new UserRowCallbackHandler();

		// SQL実行＆CSV出力
		jdbc.query(sql, handler);

	}

}
