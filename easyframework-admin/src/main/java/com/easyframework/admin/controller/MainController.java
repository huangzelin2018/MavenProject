package com.easyframework.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/main")
public class MainController extends AdminController {

	@RequestMapping("/index")
	public String index() {
		
		
		
		return view("index");
	}
}
