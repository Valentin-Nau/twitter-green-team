package com.m2i.poec.twittergreen.check;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.m2i.poec.twittergreen.bean.UserCreateBean;

public class Validator {
	
	private static Pattern pattern;
	private static Matcher matcher;
	private static final String REGEX_PASSWORD = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])";
	private static final String REGEX_USERNAME = "[a-z]|[A-Z]|[0-9]|[_]";
	private static final Logger LOGGER = Logger.getLogger(Validator.class.getName());
	
	public void check(String username, String password, String confirmPassword, String email, String picture) throws Exception{
		
		int i = 0;
		
		pattern = Pattern.compile(REGEX_USERNAME);
		matcher = pattern.matcher(username);
		while(matcher.find()){
			++i;
			LOGGER.log(Level.INFO,"dans le while");
		}
		
		if(username == null || i == 0 || i != username.length()){
			LOGGER.log(Level.INFO,"exception username");
			throw new Exception();
		}
		
		
		pattern = Pattern.compile(REGEX_PASSWORD);
		matcher = pattern.matcher(password);
		if(null == password || !matcher.find() || password.length() <= 7){
			LOGGER.log(Level.INFO,"Exception password");
			throw new Exception();
		}
		
		if(null == email || email.indexOf("@") < 1){
			LOGGER.log(Level.INFO,"exception email");
			throw new Exception();
		}
		if (null == confirmPassword || !password.equals(confirmPassword)) {
			LOGGER.log(Level.INFO,"exception confirm");
			throw new Exception();
		}
		if(picture.length() == 0 || null == picture){
			LOGGER.log(Level.INFO,"exception picture");
			throw new Exception();
		}
	
	}

}
