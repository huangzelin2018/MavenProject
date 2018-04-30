package ${basePackage}.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ${basePackage}.${modelNameUpperCamel}Service;
import com.easyframework.base.ResultObj;
import com.easyframework.model.${modelNameUpperCamel};

@Controller
@RequestMapping("/admin/${modelNameLowerCamel}")
public class ${modelNameUpperCamel}Controller extends AdminController {

	@Resource
	private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;
	

	@RequestMapping("/")
	public String toList() {
		return view("list");
	}

	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> list(@RequestParam Map<String, Object> paramsMap) {
		return ${modelNameLowerCamel}Service.getListByMap(paramsMap);
	}

	@GetMapping("/add")
	public String add() {
		return view("add");
	}

	@PostMapping("/add")
	@ResponseBody
	public ResultObj save(${modelNameUpperCamel} ${modelNameLowerCamel}) {
		try {
			${modelNameLowerCamel}Service.save(${modelNameLowerCamel});
			return resultObj.ajaxOk();
		} catch (Exception e) {
			return resultObj.ajaxError();
		}

	}

	@GetMapping("/edit")
	public String edit(Integer id) {
		${modelNameUpperCamel} ${modelNameLowerCamel} = ${modelNameLowerCamel}Service.findById(id);
		model.addAttribute("model", ${modelNameLowerCamel});
		return view("edit");
	}

	@PostMapping("/edit")
	@ResponseBody
	public ResultObj update(${modelNameUpperCamel} ${modelNameLowerCamel}) {
		try {
			${modelNameLowerCamel}Service.update(${modelNameLowerCamel});
			return resultObj.ajaxOk();
		} catch (Exception e) {
			return resultObj.ajaxError();
		}
	}

	@RequestMapping("/delete")
	@ResponseBody
	public ResultObj delete(Integer id) {
		try {
			${modelNameLowerCamel}Service.deleteById(id);
			return resultObj.ajaxOk();
		} catch (Exception e) {
			return resultObj.ajaxError();
		}
	}
}
