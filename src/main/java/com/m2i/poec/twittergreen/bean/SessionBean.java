package com.m2i.poec.twittergreen.bean;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.m2i.poec.twittergreen.entity.User;
import java.io.Serializable;

@Named
@SessionScoped
public class SessionBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
