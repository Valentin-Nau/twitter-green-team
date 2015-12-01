package com.m2i.poec.twittergreen.check;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
	
	private static Pattern pattern;
	private static Matcher matcher;
	private static final String REGEX_PASSWORD = "(?=.​*[a-z])(?=.*​[A-Z])(?=.*[0-9])";
	private static final String REGEX_USERNAME = "([a-z]|​[A-Z]|[0-9]|[_])";
	
	public void check(String username, String password, String confirmPassword, String email, String picture) throws Exception{
		
		int i = 0;
		
		pattern = Pattern.compile(REGEX_USERNAME);
		matcher = pattern.matcher(username);
		while(matcher.find()){
			++i;
		}
		if(username == null || i == 0 || i != username.length()){
			throw new Exception();
		}
		
		
		pattern = Pattern.compile(REGEX_PASSWORD);
		matcher = pattern.matcher(password);
		if(null == password || !matcher.find() || password.length() <= 7){
			throw new Exception();
		}
		
		if(null == email || email.indexOf("@") < 1){
			throw new Exception();
		}
		if (null == confirmPassword || !password.equals(confirmPassword)) {
			throw new Exception();
		}
		if(picture.length() == 0 || null == picture){
			throw new Exception();
		}
	
	}

}
