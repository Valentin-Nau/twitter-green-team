package com.m2i.poec.twittergreen.check;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.m2i.poec.twittergreen.exception.ConfirmPasswordNotValidException;
import com.m2i.poec.twittergreen.exception.EmailNotValidException;
import com.m2i.poec.twittergreen.exception.PasswordNotValidException;
import com.m2i.poec.twittergreen.exception.PictureNotValidException;
import com.m2i.poec.twittergreen.exception.UsernameNotValidException;

public class Validator {
	
	private static Pattern pattern;
	private static Matcher matcher;
	private static final String REGEX_PASSWORD = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])";
	private static final String REGEX_USERNAME = "[a-z]|[A-Z]|[0-9]|[_]";
	private static final Logger LOGGER = Logger.getLogger(Validator.class.getName());
	
	public void check(String username, String password, String confirmPassword, String email, String picture) throws UsernameNotValidException, PasswordNotValidException, EmailNotValidException, ConfirmPasswordNotValidException, PictureNotValidException {
		
		int i = 0;
		
		pattern = Pattern.compile(REGEX_USERNAME);
		matcher = pattern.matcher(username);
		while(matcher.find()){
			++i;
		}
		
		if(username == null || i == 0 || i != username.length()){
			throw new UsernameNotValidException();
		}
		
		
		pattern = Pattern.compile(REGEX_PASSWORD);
		matcher = pattern.matcher(password);
		if(null == password || !matcher.find() || password.length() <= 7){
			throw new PasswordNotValidException();
		}
		
		if (null == confirmPassword || !password.equals(confirmPassword)) {
			throw new ConfirmPasswordNotValidException();
		}
		
		if(null == email || (email.indexOf("@") < 1 || email.indexOf("@") == email.length() - 1)){
			throw new EmailNotValidException();
		}
		
		if(picture.length() == 0 || null == picture){
			throw new PictureNotValidException();
		}
	
	}

}
