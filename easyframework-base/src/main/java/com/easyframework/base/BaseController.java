package com.easyframework.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * 控制层基类 ，所有业务类都应该继承此类
 */
public abstract class BaseController{

	public static Logger logger = Logger.getLogger(BaseController.class);

	protected ResultObj resultObj = new ResultObj();// 封装一个统一对象用于返回前台

	protected HttpServletRequest request;  
    protected HttpServletResponse response;  
    protected HttpSession session;  
    protected Model model;  
    
    /**
     * 获取request/response/session
     */
    @ModelAttribute  
    public void setReqAndRes(Model model,HttpServletRequest request, HttpServletResponse response){  
        this.request = request;  
        this.response = response;  
        this.model=model;
        this.session = request.getSession(true);  //false:如果有回话就返回回话，否则返回null
    }  
	
}
