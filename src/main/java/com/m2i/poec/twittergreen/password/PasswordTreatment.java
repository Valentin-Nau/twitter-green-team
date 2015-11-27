package com.m2i.poec.twittergreen.password;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordTreatment {

	private BCrypt bCrypt;
	private static int keyValue = 26;

	public String crypPassWord(String password){
		return bCrypt.hashpw(password, BCrypt.gensalt(keyValue));
	}

	public Boolean decryptPassword(String passwordEnter, String passwordHashStored){
		return bCrypt.checkpw(passwordEnter, passwordHashStored);
	}

}
