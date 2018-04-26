package ${basePackage}.controller;
import ${basePackage}.${modelNameUpperCamel};
import ${basePackage}.${modelNameUpperCamel}Service;
import ${basePackage}.core.Result;
import ${basePackage}.core.ResultGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import javax.servlet.http.HttpServletRequest;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by ${author} on ${date}.
*/
@Controller
@RequestMapping("/admin/${modelNameLowerCamel}")
public class ${modelNameUpperCamel}Controller extends AdminController{

    @Resource
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

    @RequestMapping("/list")
    public String list(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size, HttpServletRequest request) {
        PageHelper.startPage(page, size);
        List<${modelNameUpperCamel}> list = ${modelNameLowerCamel}Service.findAll();
        PageInfo<${modelNameUpperCamel}> pageInfo = new PageInfo<${modelNameUpperCamel}>(list);
        request.setAttribute("pageInfo", pageInfo);
        return view("list");
    }
    
    @RequestMapping("/add")
    public String add() {
        return view("add");
    }
    
    @RequestMapping("/save")
    public Result save(${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.save(${modelNameLowerCamel});
        return ResultGenerator.genSuccessResult();
    }
    
    @RequestMapping("/edit")
    public String edit(Integer id, HttpServletRequest request) {
        ${modelNameUpperCamel} ${modelNameLowerCamel} = ${modelNameLowerCamel}Service.findById(id);
        request.setAttribute("${modelNameLowerCamel}", ${modelNameLowerCamel});
        return view("edit");
    }

    @RequestMapping("/update")
    public Result update(${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.update(${modelNameLowerCamel});
        return ResultGenerator.genSuccessResult();
    }
    
    @RequestMapping("/delete")
    public Result delete(Integer id) {
        ${modelNameLowerCamel}Service.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }
}
