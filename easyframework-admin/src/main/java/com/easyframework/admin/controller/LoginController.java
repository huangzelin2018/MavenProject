package com.easyframework.admin.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.easyframework.model.User;
import com.easyframework.shiro.AuthRealm;

/**
 * 登录控制器
 */
@Controller
public class LoginController {

	private final static Logger logger = LoggerFactory.getLogger(AuthRealm.class);
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request, Map<String, Object> map) throws Exception {

		Subject currentUser = SecurityUtils.getSubject();
		User user = (User) currentUser.getPrincipal();
		if (user != null) {
			return this.home();
		}

		// 登录失败从request中获取shiro处理的异常信息。
		// shiroLoginFailure:就是shiro异常类的全类名.
		String exception = (String) request.getAttribute("shiroLoginFailure");
		logger.info("exception=" + exception);
		String msg = "";
		if (exception != null) {
			if (UnknownAccountException.class.getName().equals(exception)) {
				logger.info("UnknownAccountException -- > 账号不存在：");
				msg = "UnknownAccountException -- > 账号不存在：";
			} else if (IncorrectCredentialsException.class.getName().equals(exception)) {
				logger.info("IncorrectCredentialsException -- > 密码不正确：");
				msg = "IncorrectCredentialsException -- > 密码不正确：";
			} else if ("kaptchaValidateFailed".equals(exception)) {
				logger.info("kaptchaValidateFailed -- > 验证码错误");
				msg = "kaptchaValidateFailed -- > 验证码错误";
			} else {
				msg = "else >> " + exception;
				logger.info("else -- >" + exception);
			}
		}
		map.put("msg", msg);
		// 此方法不处理登录成功,由shiro进行处理
		return "/login";
	}

	@RequestMapping("/home")
	public String home() {
		return "redirect:admin/main/index";
	}

	@RequestMapping("/logOut")
	public String logOut() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "/login";
	}

}