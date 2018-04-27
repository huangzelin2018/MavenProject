package com.easyframework.admin.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.easyframework.admin.service.UserService;
import com.easyframework.base.ResultObj;
import com.easyframework.model.User;

@Controller
@RequestMapping("/admin/user")
public class UserController extends AdminController {

	@Resource
	private UserService userService;

	@RequestMapping("/")
	public String toList() {
		return view("list");
	}

	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> list(@RequestParam Map<String, Object> paramsMap) {
		
		User user = userService.findById(1);
		System.out.println(user);
		return userService.getListByMap(paramsMap);
	}

	@GetMapping("/add")
	public String add() {
		return view("add");
	}

	@PostMapping("/add")
	@ResponseBody
	public ResultObj save(User user) {
		try {
			System.out.println(user);
//			userService.save(user);
			return resultObj.ajaxOk();
		} catch (Exception e) {
			return resultObj.ajaxError();
		}

	}

	@GetMapping("/edit")
	public String edit(Integer id) {
		User user = userService.findById(id);
		request.setAttribute("model", user);
		return view("edit");
	}

	@PostMapping("/edit")
	@ResponseBody
	public ResultObj update(User user) {
		try {
			userService.update(user);
			return resultObj.ajaxOk();
		} catch (Exception e) {
			return resultObj.ajaxError();
		}
	}

	@RequestMapping("/delete")
	@ResponseBody
	public ResultObj delete(Integer id) {
		try {
			userService.deleteById(id);
			return resultObj.ajaxOk();
		} catch (Exception e) {
			return resultObj.ajaxError();
		}
	}
}
