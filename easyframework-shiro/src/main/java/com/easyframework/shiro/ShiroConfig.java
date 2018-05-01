package com.easyframework.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.easyframework.vrifycode.VrifyCodeAuthenticationFilter;

/**
 * shiro配置类
 */
@Configuration
public class ShiroConfig {

	@Bean
	public ShiroFilterFactoryBean shiroFilter(org.apache.shiro.mgt.SecurityManager manager) {
		System.err.println("ShiroConfiguration.shiroFilter()");
		ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
		bean.setSecurityManager(manager);
		
		Map<String, Filter> filters = bean.getFilters();//获取filters
		filters.put("authc", new VrifyCodeAuthenticationFilter());//将自定义 的FormAuthenticationFilter注入shiroFilter中  
		
		// 配置登录的url和登录成功的url
		bean.setLoginUrl("/login");
		bean.setSuccessUrl("/home");
		bean.setUnauthorizedUrl("/public/403");
		// 配置访问权限
		LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		filterChainDefinitionMap.put("/loginUser", "anon");
		filterChainDefinitionMap.put("/logout*", "anon");
		filterChainDefinitionMap.put("/public/*", "anon");
		filterChainDefinitionMap.put("/admin/*", "authc");
		filterChainDefinitionMap.put("/*", "authc");// 表示需要认证才可以访问
		filterChainDefinitionMap.put("/**", "authc");// 表示需要认证才可以访问
		filterChainDefinitionMap.put("/*.*", "authc");
		bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return bean;
	}

	// 配置核心安全事务管理器
	@Bean(name = "securityManager")
	public org.apache.shiro.mgt.SecurityManager securityManager() {
		System.err.println("--------------shiro已经加载----------------");
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
		manager.setRealm(authRealm());
		return manager;
	}

	// 配置自定义的权限登录器
	@Bean
	public AuthRealm authRealm() {
		AuthRealm authRealm = new AuthRealm();
		authRealm.setCredentialsMatcher(new CredentialsMatcher());
		return authRealm;
	}
}
