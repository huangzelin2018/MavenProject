package com.easyframework.admin.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.easyframework.base.util.ArrayUtils;


public class AdminController {

	private static final String BUSINESS_NAME = "/admin";
	private static final char SP = '/';// File.separatorChar;
	protected Map<String, Object> m = new HashMap<String, Object>();// 用来设置给前台显示map
	private static String[] excludeSearchArr = new String[] { "page", "pageSize", "dir", "sort" };// 搜索排除值

	/**
	 * 获取业务名称
	 */
	public String getClassModelName() {
		String n = this.getClass().getSimpleName().replace("Controller", "");
		return n.substring(0, 1).toLowerCase() + n.substring(1);
	}

	public String view(String pageName) {
		String className = getClassModelName();
		String url = BUSINESS_NAME + SP + className + SP + pageName;
		return url;
	}

	/**
	 * 把搜索值转为map，供后台及界面使用
	 * 
	 * @param request
	 * @return
	 */
	public Map<String, Object> getSearchMap(HttpServletRequest request) {
		Map<?, ?> map = request.getParameterMap();
		for (Object key : map.keySet()) {
			String keyStr = key.toString();
			if (keyStr != "")
				if (!ArrayUtils.isInArray(excludeSearchArr, keyStr)) {
					try {
						m.put(keyStr, java.net.URLDecoder.decode(request.getParameter(key.toString()), "UTF-8"));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
		}
		return m;
	}

}
