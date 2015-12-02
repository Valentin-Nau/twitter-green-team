package com.m2i.poec.twittergreen.security;

import org.mindrot.jbcrypt.BCrypt;


/**
 * @author Xezaluri
 */
public class PasswordBCrypt {

	private static int keyValue = 12;

	/**
	 * Permit to crypt the password.
	 * @param passwordEnterByUser
	 * @return String
	 */
	public static String cryptPassWord(String passwordEnterByUser){
		return BCrypt.hashpw(passwordEnterByUser, BCrypt.gensalt(keyValue));
	}

	/**
	 * permit to decrypt the password.
	 * @param passwordEnterByUser
	 * @param passwordCryptedStored
	 * @return Boolean
	 */
	public static Boolean verifyPassword(String passwordEnterByUser, String passwordCryptedStored){
		return BCrypt.checkpw(passwordEnterByUser, passwordCryptedStored);
	}

}
