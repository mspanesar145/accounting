package com.accounting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accounting.UserDocument;
import com.accounting.bo.MyAccount;
import com.accounting.service.ProfileService;

@RestController
public class SearchController {
	
	@Autowired
	ProfileService profileService;
	
	@RequestMapping(value="/find/allUserDocuments",produces="application/json")
	public List<UserDocument> findAllUserDocument() {
		return profileService.findAllUserDocuments();
	}
	
	@RequestMapping(value="/find/myAccountByCreatedById",produces="application/json")
	public MyAccount findMyAccountByCreatedById(Long createdById) {
		return profileService.findMyAccountByCreatedById(createdById);
	}
}
