package com.example.demo.login.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.login.domain.model.GroupOrder;
import com.example.demo.login.domain.model.SignupForm;
import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.service.UserService;

@Controller
public class SignupController {

	@Autowired
	private UserService userService;

	// ラジオボタン
	private Map<String, String> radioMarriage;

	// ラジオボタンの初期化
	private Map<String, String> initRadioMarriage(){
		Map<String, String> radio = new LinkedHashMap<>();

		// 既婚、未婚をMapに格納
		radio.put("既婚", "true");
		radio.put("未婚", "false");

		return radio;
	}

	// ユーザー登録画面のGET用コントローラー
	@GetMapping("/signup")
	public String getSignup(@ModelAttribute SignupForm form, Model model) {

		// ラジオボタンの初期化
		radioMarriage = initRadioMarriage();

		// ラジオボタン用MapをModelへ登録
		model.addAttribute("radioMarriage", radioMarriage);

		return "login/signup";
	}

	// ユーザー登録画面POST用コントローラー
	@PostMapping("/signup")
	public String postSignUp(@ModelAttribute @Validated(GroupOrder.class) SignupForm form,
			BindingResult bindingResult, Model model) {

		if(bindingResult.hasErrors()) {
			return getSignup(form, model);
		}

		System.out.println(form);

		// insert用変数
		User user = new User();

		// 登録内容格納
		user.setUserId(form.getUserId());
		user.setPassword(form.getPassword());
		user.setUserName(form.getUserName());
		user.setBirthday(form.getBirthday());
		user.setAge(form.getAge());
		user.setMarriage(form.isMarriage());
		user.setRole("ROLE_GENERAL");

		// ユーザー登録処理
		boolean result = userService.insert(user);

		// ユーザー登録結果の判定
		if(result == true) {
			System.out.println("insert成功");
		} else {
			System.out.println("insert失敗");
		}

		// login.htmlへリダイレクト
		return "redirect:/login";
	}

	// ExceptionHandlerを使用
	@ExceptionHandler(DataAccessException.class)
	public String dataAccessExceptionHandler(DataAccessException e, Model model) {

		// 例外クラスのメッセージをModelに登録
		model.addAttribute("error", "内部サーバーエラー（DB）：ExceptionHandler");

		// 例外クラスのメッセージをModelに登録
		model.addAttribute("message", "SignupControllerでDataAccessExceptionが発生しました");

		// HTTPのエラーコード（500）をModelに登録
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

		return "error";

	}
	
	// 
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e, Model model) {
		
		// 例外クラスのメッセージをModelに登録
		model.addAttribute("error", "内部サーバーエラー：ExceptionHandler");
		
		// 例外クラスのメッセージをModelに登録
		model.addAttribute("message", "SignupControllerでExceptionが発生しました");
		
		// HTTPのエラーコード（500）をModelに登録
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
		
		return "error";
	}


}