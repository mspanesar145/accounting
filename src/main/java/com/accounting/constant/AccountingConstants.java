package com.accounting.constant;

import java.util.HashMap;
import java.util.Map;

public class AccountingConstants {
	public enum ErrorCodes {
		UserNotFound(99),
		IncorrectEmailOrPassword(100), 
		IncorrectPassword(101), 
		UserNameOrEmailAlreadyTaken(102), 
		EmailAlreadyTaken(103), 
		EmailLenghtMustBeSixCharater(104),
		NameLenghtMustBeSixCharater(105),
		PasswordLenghtMustBeSixCharater(106), 
		UserNameLenghtMustBeSixCharater(107),
		RowNotFound(108),
		UserDoestNotHavePermission(109),
		FriendAlreadyNotYourFriend(110),
		FriendAlreadyYourFriend(111),
		CantFollowYourSelf(112),
		FacebookLoginNeedAcessToekn(113),
		FacebookAcessTokenExpires(114),
		PasswordNotPresent(115),
		EmailNotPresent(116),
		FacebookIdNotPresent(117),
		FacebookTokenNotPresent(118),
		NameNotPresent(119),
		AuthTypeNotPresent(120),
		InternalServerError(121);
		int errorCode;

		private ErrorCodes(int i) {
			errorCode = i;
		}

		public int getErrorCode() {
			return errorCode;
		}
	}
	
	public static Map<Object, String> errorMessages = new HashMap<>();
	static {
		errorMessages.put(ErrorCodes.UserNotFound, "User Not Found");
		errorMessages.put(ErrorCodes.IncorrectPassword, "Incorrect Password");
		errorMessages.put(ErrorCodes.UserNameOrEmailAlreadyTaken, "User Name Or Email Already Exist");
		errorMessages.put(ErrorCodes.EmailLenghtMustBeSixCharater, "Email Length Must Be Six Character");
		errorMessages.put(ErrorCodes.NameLenghtMustBeSixCharater, "Name Length Must Be Six Charater");
		errorMessages.put(ErrorCodes.PasswordLenghtMustBeSixCharater, "Password Length Must Be Six Character");
		errorMessages.put(ErrorCodes.UserNameLenghtMustBeSixCharater, "User Name Length Must Be Six Character");
		errorMessages.put(ErrorCodes.RowNotFound, "Row Not Found"); 
		errorMessages.put(ErrorCodes.FriendAlreadyNotYourFriend, "Friend Already Not Your Friend");
		errorMessages.put(ErrorCodes.FriendAlreadyYourFriend, "Friend Already Your Friend");
		errorMessages.put(ErrorCodes.CantFollowYourSelf, "Cant Follow Your Self");
		errorMessages.put(ErrorCodes.FacebookLoginNeedAcessToekn, "Facebook Login Need Acess Toekn");
		errorMessages.put(ErrorCodes.AuthTypeNotPresent, "Auth Type Not Present");
		errorMessages.put(ErrorCodes.FacebookAcessTokenExpires, "Facebook Acess Token Expires");
		errorMessages.put(ErrorCodes.EmailNotPresent, "Email Not Found");
		errorMessages.put(ErrorCodes.IncorrectEmailOrPassword, "Incorrect Email Or Mobile Number");
		errorMessages.put(ErrorCodes.EmailAlreadyTaken, "Email Already Exists");


	}
}
