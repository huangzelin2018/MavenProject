package com.easyframework.exception;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * web异常统一处理
 */
@ControllerAdvice
public class WebExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(WebExceptionHandler.class);
	public static final String DEFAULT_ERROR_VIEW = "public/error";

	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) {
		ModelAndView mav = new ModelAndView();
		if (e instanceof UnauthorizedException) {
			ModelAndView mv = new ModelAndView("public/403");
			return mv;
		}
		System.err.println(e.getMessage());
		LOGGER.error(e.getMessage());
		mav.addObject("exception", e);
		mav.addObject("url", req.getRequestURL());
		mav.setViewName(DEFAULT_ERROR_VIEW);
		return mav;
	}

}
