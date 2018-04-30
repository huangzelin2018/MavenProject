package com.easyframework.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.easyframework.base.BaseModel;

public class Role extends BaseModel{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer rid;
	private String rname;
	private Set<User> users = new HashSet<User>();	
	private Set<Module> modules = new HashSet<Module>();
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	public Set<Module> getModules() {
		return modules;
	}
	public void setModules(Set<Module> modules) {
		this.modules = modules;
	}
}
