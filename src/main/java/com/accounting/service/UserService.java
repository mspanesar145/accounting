package com.accounting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accounting.repository.UserRepository;
import com.accounting.user.bo.User;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	/***********  USER BLOCK STARTS *****************/
	public User findUserById(Long userId) {
		return userRepository.findOne(userId);
	}
	
	public List<User> findAllUsers() {
		return (List<User>) userRepository.findAll();
	}
	/***********  USER BLOCK ENDS *****************/
	
	public User findUserByFacebookId(String facebookId) {
		return userRepository.findUserByFacebookID(facebookId);
	}
	
	public User findUserByEmail(String email) {
		return userRepository.findUserByEmail(email);
	}
	
	public User findUserByResetToken(String resetToken) {
		return userRepository.findByResetTokenAndResetTokenCreatedAt(resetToken);
	}
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}
}
