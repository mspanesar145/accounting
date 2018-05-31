package com.accounting.controller;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.accounting.constant.AccountingConstants;
import com.accounting.constant.AccountingConstants.ErrorCodes;
import com.accounting.enums.AccountingEnums.AuthenticateType;
import com.accounting.repository.UserDeviceRepository;
import com.accounting.repository.UserRepository;
import com.accounting.stateless.security.TokenAuthenticationService;
import com.accounting.stateless.security.UserService;
import com.accounting.user.bo.User;
import com.accounting.user.bo.UserDevice;
import com.google.common.collect.Sets;

@RestController
public class UserDetailController {

	// @Autowired
	UserService userService;

	@Autowired
	UserRepository userRepository;
	

	@Autowired
	UserDeviceRepository userDeviceRepository;
	
	@Autowired
	TokenAuthenticationService tokenAuthenticationService;

	@Value("${accounting.salt}")
	private String salt;
	/*
	 * { "username":"Blue", "password":200, "name":"1234" }
	 */

	
	@RequestMapping(value = "/auth/user/authenticate", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<User> createToken(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestBody User user) {
		
		System.out.println("Feting Device Token");
		UserDevice userDevice = null;
		if (user.getDeviceToken() != null) {
			System.out.println("Feting Device Tokken ");
			System.out.println("Device Token : "+user.getDeviceToken());
			userDevice = new UserDevice();
			userDevice.setDeviceToken(user.getDeviceToken());
			userDevice.setDeviceType(user.getDeviceType());
		}
		
		System.out.println("[ Date : "+new Date()+", UserType : Email, Message : Creating new user");
		if (user.getAuthType() == AuthenticateType.email && user.getEmail() != null) {
			String password = new Md5PasswordEncoder().encodePassword(user.getPassword(), salt);
			user = userRepository.findUserByEmailAndPassword(user.getEmail(),password);
		}
		
		if (user == null) {
			user = new User();
			user.setErrorCode(ErrorCodes.IncorrectEmailOrPassword.getErrorCode());
			user.setErrorDetail(AccountingConstants.errorMessages.get(ErrorCodes.IncorrectEmailOrPassword));
		} else if (user.getUserId() == null) {
			user = new User();
			user.setErrorCode(HttpStatus.BAD_REQUEST.ordinal());
			user.setErrorDetail(HttpStatus.BAD_REQUEST.name());
		}
		System.out.println("[ Date : "+new Date()+", UserType : Email, Message : Creating new user "+user.toString());	
		user.setPassword(null);
		
		
		if (userDevice != null) {
			userDevice.setUserId(user.getUserId());
			userDeviceRepository.save(userDevice);
		}
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/api/users/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<User> getUserDetail(@PathVariable("userId") Long userId) {
		User user = new User();
		try {
			return new ResponseEntity<User>(userRepository.findOne(userId),
					HttpStatus.OK);
		} catch (Exception ex) {

		}
		user.setErrorCode(ErrorCodes.RowNotFound.getErrorCode());
		user.setErrorDetail(ErrorCodes.RowNotFound.toString());
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	

	@RequestMapping(value = "/api/users/me", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<User> getUserDetail() {
		User user = new User();
		try {
			
			 user = (User) SecurityContextHolder.getContext()
					.getAuthentication().getPrincipal();
			 user = userRepository.findOne(user.getUserId());
			
			return new ResponseEntity<User>(user,
					HttpStatus.OK);
		} catch (Exception ex) {

		}
		user.setErrorCode(ErrorCodes.RowNotFound.getErrorCode());
		user.setErrorDetail(ErrorCodes.RowNotFound.toString());
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	private String establishUserAndLogin(HttpServletResponse response,
			User email) {
		// Find user, create if necessary
		org.springframework.security.core.userdetails.User user;

		user = new org.springframework.security.core.userdetails.User(
				email.getUsername(), UUID.randomUUID().toString(),
				Sets.<GrantedAuthority> newHashSet());
		// userService.addUser(user);
		com.accounting.stateless.security.UserAuthentication authentication = new com.accounting.stateless.security.UserAuthentication(
				user, email);
		String token = tokenAuthenticationService.addAuthentication(response,
				authentication);
		return token;
	}

}
