package com.easyframework.admin.controller;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.easyframework.base.ResultObj;
import com.easyframework.model.User;
import com.easyframework.service.UserService;

@Controller
@RequestMapping("/admin/user")
public class UserController extends AdminController {

	@Autowired
	private UserService userService;

	@RequestMapping("/")
	public String toList() {
		return view("list");
	}

	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> list(@RequestParam Map<String, Object> paramsMap) {
		return userService.getListByMap(paramsMap);
	}

	@GetMapping("/add")
	@RequiresPermissions("user:add")
	public String add() {
		return view("add");
	}

	@PostMapping("/add")
	@RequiresPermissions("user:add")
	@ResponseBody
	public ResultObj save(User user) {
		try {
			userService.save(user);
			return resultObj.ajaxOk();
		} catch (Exception e) {
			return resultObj.ajaxError();
		}

	}

	@GetMapping("/edit")
	public String edit(Integer uid) {
		User user = userService.findById(uid);
		System.out.println(user);
		model.addAttribute("model", user);
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
	public ResultObj delete(Integer uid) {
		try {
			userService.deleteById(uid);
			return resultObj.ajaxOk();
		} catch (Exception e) {
			return resultObj.ajaxError();
		}
	}
}
