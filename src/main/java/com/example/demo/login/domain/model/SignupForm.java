package com.example.demo.login.domain.model;

import java.util.Date;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SignupForm {

	// ユーザーID：入力必須、メールアドレス形式
	@NotBlank (groups = ValidGroup1.class)
	@Email (groups = ValidGroup2.class)
	private String userId;

	// パスワード：入力必須、長さが4-100桁、半角英数字のみ
	@NotBlank(groups = ValidGroup1.class)
	@Length(min=4, max=100, groups = ValidGroup2.class)
	@Pattern(regexp="^[a-zA-Z0-9]+$", groups = ValidGroup3.class)
	private String password;

	// ユーザー名：入力必須
	@NotBlank(groups = ValidGroup1.class)
	private String userName;

	// 誕生日：入力必須
	@NotNull(groups = ValidGroup1.class)
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date birthday;

	// 年齢：20-100歳
	@Min(value = 20, groups = ValidGroup2.class)
	@Max(value = 100, groups = ValidGroup2.class)
	private int age;

	// 結婚ステータス：falseのみ
	@AssertFalse(groups = ValidGroup2.class)
	private boolean marriage;

}
