package com.accounting.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accounting.UserDocument;
import com.accounting.bo.MyAccount;
import com.accounting.bo.ProfileCategory;
import com.accounting.repository.MyAccountRepository;
import com.accounting.service.ProfileService;
import com.accounting.service.UserService;
import com.accounting.user.bo.User;

@RestController
public class SearchController {
	
	@Autowired
	ProfileService profileService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/find/allUserDocuments",produces="application/json")
	public List<UserDocument> findAllUserDocument() {
		return profileService.findAllUserDocuments();
	}
	
	@RequestMapping(value="/find/myAccountByCreatedById",produces="application/json")
	public MyAccount findMyAccountByCreatedById(Long createdById) {
		return profileService.findMyAccountByCreatedById(createdById);
	}
	
	@RequestMapping(value="/find/allContentUserDocumentsForNullPdfAndCategoryIdSubCategoryId",produces="application/json")
	public List<UserDocument> findAllContentUserDocumentsForNullPdfAndCategoryIdSubCategoryId(Long userId) {
		MyAccount myAccount = profileService.findMyAccountByCreatedById(userId);
		if (myAccount != null) {
			return profileService.findUserDocumentsByCategoryIdAndSubCategoryIdAndContentLinkIsNull(myAccount.getMainCourseId(),myAccount.getSecondryCourseId());
		} else {
			return new ArrayList<>();
		}
	}
	
	@RequestMapping(value="/find/categories",produces="application/json")
	public List<ProfileCategory> findMainCategories() {
		return profileService.findProfileCategoryParentCategoryIdNull();
	}
	
	@RequestMapping(value="/find/subCategories",produces="application/json")
	public List<ProfileCategory> findSubCategories(Long parentCategoryId) {
		return profileService.findProfileCategoryParentCategoryId(parentCategoryId);
	}
	
	
}
