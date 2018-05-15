package com.accounting.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.accounting.UserDocument;
import com.accounting.bo.MyAccount;
import com.accounting.bo.Rating;
import com.accounting.service.DocumentService;
import com.accounting.service.ProfileService;

@RestController
public class SaveController {

	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private DocumentService documentService;
	
	@RequestMapping(value="/save/userDocument",produces="application/json")
	public UserDocument saveUserDocument(@RequestBody UserDocument userDocument) {
		return profileService.saveUserDocument(userDocument);
	}
	
	@RequestMapping(value="/save/myAccount",produces="application/json")
	public MyAccount saveUserDocument(@RequestBody MyAccount myAccount) {
		
		MyAccount myAccntFromDatabase = profileService.findMyAccountByCreatedById(myAccount.getCreatedById());
		if (myAccntFromDatabase != null) {
			myAccount.setMyAccountId(myAccntFromDatabase.getMyAccountId());
		}
		
		return profileService.saveMyAccount(myAccount);
	}
	
	@RequestMapping(value="/save/documentRating",produces="application/json")
	public Rating saveUserDocument(@RequestBody Rating rating) {
		return documentService.saveRating(rating);
	}
	
	@RequestMapping(value="/save/coverimage",produces="application/json")
	public String saveUserDocument(@RequestParam("mediaFile") MultipartFile uploadfile) {
		JSONObject response = new JSONObject();
		try {
			response.put("coverImageUrl",profileService.uploadUserDocument(uploadfile));
			return response.toString();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
}
