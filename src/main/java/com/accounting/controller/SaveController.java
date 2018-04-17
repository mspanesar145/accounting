package com.accounting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accounting.DocumentRating;
import com.accounting.UserDocument;
import com.accounting.bo.MyAccount;
import com.accounting.service.ProfileService;

@RestController
public class SaveController {

	@Autowired
	private ProfileService profileService;
	
	@RequestMapping(value="/save/userDocument",produces="application/json")
	public UserDocument saveUserDocument(@RequestBody UserDocument userDocument) {
		return profileService.saveUserDocument(userDocument);
	}
	
	@RequestMapping(value="/save/myAccount",produces="application/json")
	public MyAccount saveUserDocument(@RequestBody MyAccount myAccount) {
		return profileService.saveMyAccount(myAccount);
	}
	
	@RequestMapping(value="/save/documentRating",produces="application/json")
	public DocumentRating saveUserDocument(@RequestBody DocumentRating documentRating) {
		return profileService.saveDocumentRating(documentRating);
	}
}
