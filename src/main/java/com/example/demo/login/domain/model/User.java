package com.example.demo.login.domain.model;

import java.util.Date;

import lombok.Data;

@Data
public class User {

	// ユーザーID
	private String userId;

	// パスワード
	private String password;

	// ユーザー名
	private String userName;

	// 誕生日
	private Date birthday;

	// 年齢
	private int age;

	// 結婚ステータス
	private boolean marriage;

	// エンジニア領域
	private int typeOfEngineer;

	// 使用言語
	private int usingLanguage;

	// ロール
	private String role;

}
