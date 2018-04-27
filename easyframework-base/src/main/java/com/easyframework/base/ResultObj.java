package com.easyframework.base;

import java.util.ArrayList;
import java.util.List;

/**
 * 封装一个统一对象用于返回前台
 */
public class ResultObj {

	private List<Object> collection = new ArrayList<Object>();

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

	public List<Object> getCollection() {
		return collection;
	}

	public void setCollection(List<Object> collection) {
		this.collection = collection;
	}

	public ResultObj ajaxOk() {
		this.setSuccess(true);
		this.setMsg("操作成功");
		return this;
	}

	public ResultObj ajaxError() {
		this.setSuccess(false);
		this.setMsg("操作失败");
		return this;
	}

}
