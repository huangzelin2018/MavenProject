package com.easyframework.admin.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.easyframework.admin.service.UserService;
import com.easyframework.model.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/admin/user")
public class UserController extends AdminController {

	@Resource
	private UserService userService;

	@RequestMapping("/list")
	public String list(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size) {
		PageHelper.startPage(page, size);
		List<User> list = userService.findAll();
		PageInfo<User> pageInfo = new PageInfo<User>(list);
		request.setAttribute("pageInfo", pageInfo);
		return view("list");
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
