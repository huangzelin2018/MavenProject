package com.easyframework.base;

import java.util.ArrayList;
import java.util.List;

/**
 * 封装一个统一对象用于返回前台
 */
public class ResultObj<T> {

	private List<T> collection = new ArrayList<T>();

	private String msg;

	private boolean success = true;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public ResultObj() {
	}

	public List<T> getCollection() {
		return collection;
	}

	public void setCollection(List<T> collection) {
		this.collection = collection;
	}

}
