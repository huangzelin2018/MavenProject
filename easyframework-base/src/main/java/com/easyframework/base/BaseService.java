package com.easyframework.base;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;

import tk.mybatis.mapper.entity.Condition;

/**
 * 基于通用MyBatis Mapper插件的Service接口的实现
 */
public abstract class BaseService<T> implements Service<T> {

	@Autowired
	protected Mapper<T> mapper;

	private Class<T> modelClass; // 当前泛型真实类型的Class

	public BaseService() {
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		modelClass = (Class<T>) pt.getActualTypeArguments()[0];
	}

	public int save(T model) {
		return mapper.insertSelective(model);
	}

	public int save(List<T> models) {
		return mapper.insertList(models);
	}

	public int deleteById(Integer id) {
		return mapper.deleteByPrimaryKey(id);
	}

	public int deleteByIds(String ids) {
		return mapper.deleteByIds(ids);
	}

	public int update(T model) {
		return mapper.updateByPrimaryKeySelective(model);
	}

	public T findById(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public T findBy(String fieldName, Object value) throws TooManyResultsException {
		try {
			T model = modelClass.newInstance();
			Field field = modelClass.getDeclaredField(fieldName);
			field.setAccessible(true);
			field.set(model, value);
			return mapper.selectOne(model);
		} catch (ReflectiveOperationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<T> findByIds(String ids) {
		return mapper.selectByIds(ids);
	}

	public List<T> findByCondition(Condition condition) {
		return mapper.selectByCondition(condition);
	}

	public List<T> findAll() {
		return mapper.selectAll();
	}

	public Map<String, Object> getListByMap(Map<String, Object> paramsMap) {

		int pageSize = Integer.valueOf(paramsMap.get("limit").toString());
		int startNum = (Integer.valueOf(paramsMap.get("page").toString()) - 1) * pageSize;
		paramsMap.put("startNum", startNum);
		paramsMap.put("pageSize", pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", mapper.findCountByMap(paramsMap));
		map.put("data", mapper.findListByMap(paramsMap));
		return map;
	}

}
