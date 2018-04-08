package com.accounting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accounting.UserDocument;
import com.accounting.service.ProfileService;

@RestController
public class SaveController {

	@Autowired
	private ProfileService profileService;
	
	@RequestMapping(value="/save/userDocument",produces="application/json")
	public UserDocument saveUserDocument(@RequestBody UserDocument userDocument) {
		return profileService.saveUserDocument(userDocument);
	}
}
