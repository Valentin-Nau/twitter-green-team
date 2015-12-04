package com.m2i.poec.twittergreen.bean;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.m2i.poec.twittergreen.entity.Users;
import java.io.Serializable;

@Named
@SessionScoped
public class SessionBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Users user;

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

}
