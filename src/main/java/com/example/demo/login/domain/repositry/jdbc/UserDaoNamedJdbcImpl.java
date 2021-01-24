package com.example.demo.login.domain.repositry.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repositry.UserDao;

@Repository("UserDaoNamedJdbcImpl")
public class UserDaoNamedJdbcImpl implements UserDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbc;

	// Userテーブルの件数を取得
	@Override
	public int count() throws DataAccessException {

		// SQL文
		String sql = "SELECT COUNT(*) FROM m_user";

		// パラメータ生成
		SqlParameterSource params = new MapSqlParameterSource();

		// 全件取得してカウント
		return jdbc.queryForObject(sql, params, Integer.class);
	}

	// Userテーブルにデータを１件insert
	@Override
	public int insertOne(User user) throws DataAccessException {
		// SQL文
		String sql = "INSERT INTO m_user(user_id,"
				+ " password,"
				+ " user_name,"
				+ " birthday,"
				+ " age,"
				+ " marriage,"
				+ " role)"
				+ " VALUES(:userId,"
				+ " :password,"
				+ " :userName,"
				+ " :birthday,"
				+ " :age,"
				+ " :marriage,"
				+ " :role)";

		// パラメータの設定
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("userId", user.getUserId())
				.addValue("password", user.getPassword())
				.addValue("userName", user.getUserName())
				.addValue("birthday", user.getBirthday())
				.addValue("age", user.getAge())
				.addValue("marriage", user.isMarriage())
				.addValue("role", user.getRole());

		// SQL実行
		return jdbc.update(sql, params);
	}

	// Userテーブルのデータを１件取得
	@Override
	public User selectOne(String userId) throws DataAccessException {

		// SQL文
		String sql = "SELECT * FROM m_user WHERE user_id = :userId";

		// パラメータ
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("userId", userId);

		// SQL実行
		Map<String, Object> map = jdbc.queryForMap(sql, params);

		// 結果返却用のインスタンスを生成
		User user = new User();

		// 取得したデータをインスタンスにセットしていく
		user.setUserId((String) map.get("user_id"));
		user.setPassword((String) map.get("password"));
		user.setUserName((String) map.get("user_name"));
		user.setBirthday((Date) map.get("birthday"));
		user.setAge((int) map.get("age"));
		user.setMarriage((boolean) map.get("marriage"));
		user.setRole((String) map.get("role"));

		return user;
	}

	// Userテーブルの全データを取得
	@Override
	public List<User> selectMany() throws DataAccessException {

		// SQL文
		String sql = "SELECT * FROM m_user";

		// パラメータ
		SqlParameterSource params = new MapSqlParameterSource();

		// SQL実行
		List<Map<String, Object>> getList = jdbc.queryForList(sql, params);

		// 結果返却用List
		List<User> userList = new ArrayList<>();

		// 取得データ分Loop
		for (Map<String, Object> map: getList) {

			// Userインスタンスの生成
			User user = new User();

			// Userインスタンスに取得したデータをセット
			user.setUserId((String) map.get("user_id"));
			user.setPassword((String) map.get("password"));
			user.setUserName((String) map.get("user_name"));
			user.setBirthday((Date) map.get("birthday"));
			user.setAge((int) map.get("age"));
			user.setMarriage((boolean) map.get("marriage"));
			user.setRole((String) map.get("role"));

			// Listに追加
			userList.add(user);
		}
		return userList;
	}

	// Userテーブルを１件更新
	@Override
	public int updateOne(User user) throws DataAccessException {

		// SQL文
		String sql = "UPDATE m_user"
				+ " SET"
				+ " password = :password,"
				+ " user_name = :userName,"
				+ " birthday = :birthday,"
				+ " age = :age,"
				+ " marriage = :marriage"
				+ " WHERE user_id = :userId";

		// パラメータ
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("userId", user.getUserId())
				.addValue("password", user.getPassword())
				.addValue("userName", user.getUserName())
				.addValue("birthday", user.getBirthday())
				.addValue("age", user.getAge())
				.addValue("marriage", user.isMarriage());

		// SQL実行
		return jdbc.update(sql, params);
	}

	// Userテーブルを１件削除
	@Override
	public int deleteOne(String userId) throws DataAccessException {
		// SQL文
		String sql = "DELETE FROM m_user WHERE user_id = :userId";

		// パラメータ
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("userId", userId);

		// SQL実行
		int rowNumber = jdbc.update(sql, params);

		return rowNumber;
	}

	// SQL取得結果をサーバーにCSVで保存する
	@Override
	public void userCsvOut() throws DataAccessException {

		// m_userテーブルのデータを全件取得するSQL
		String sql = "SELECT * FROM m_user";

		// ResultSetExtractorの生成
		UserRowCallbackHandler handler = new UserRowCallbackHandler();

		// クエリー実行＆CSV出力
		jdbc.query(sql, handler);

	}

	@Override
	public Map<String, Object> count2() throws DataAccessException {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Map<String, Object> count3() throws DataAccessException {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
