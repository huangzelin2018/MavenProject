package ${basePackage}.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easyframework.base.BaseService;
import com.easyframework.model.${modelNameUpperCamel};
import com.easyframework.mybatis.${modelNameUpperCamel}Mapper;

@Service
@Transactional
public class ${modelNameUpperCamel}Service extends BaseService<${modelNameUpperCamel}> {

	@Autowired
	private ${modelNameUpperCamel}Mapper ${modelNameLowerCamel}Mapper;

	
}
