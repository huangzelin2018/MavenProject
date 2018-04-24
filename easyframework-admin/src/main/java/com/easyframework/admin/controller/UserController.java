package com.easyframework.admin.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.easyframework.admin.service.UserService;

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
		System.out.println(paramsMap);
//		return userService.getPageList(pageIndex, pageSize);
		return userService.getUserList(paramsMap);
	}

	@RequestMapping("/add")
	public String add() {
		return view("add");
	}

	// @RequestMapping("/save")
	// public Result save(User user) {
	// userService.save(user);
	// return ResultGenerator.genSuccessResult();
	// }
	//
	// @RequestMapping("/edit")
	// public String edit(Integer id, HttpServletRequest request) {
	// User user = userService.findById(id);
	// request.setAttribute("user", user);
	// return view("edit");
	// }
	//
	// @RequestMapping("/update")
	// public Result update(User user) {
	// userService.update(user);
	// return ResultGenerator.genSuccessResult();
	// }
	//
	// @RequestMapping("/delete")
	// public Result delete(Integer id) {
	// userService.deleteById(id);
	// return ResultGenerator.genSuccessResult();
	// }
}
