package com.accounting.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accounting.UserDocument;
import com.accounting.bo.MyAccount;
import com.accounting.bo.ProfileCategory;
import com.accounting.service.ProfileService;
import com.accounting.service.UserService;

@RestController
public class SearchController {
	
	@Autowired
	ProfileService profileService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/find/topTenDocuments",produces="application/json")
	public Map<String,List<UserDocument>> findTopTenDocuments(Long userId) {
		Map<String,List<UserDocument>> topTenDocMap = new HashMap<>();
		
		MyAccount myAccount = profileService.findMyAccountByCreatedById(userId);
		if (myAccount != null) {
			topTenDocMap.put("image", profileService.findTopTenImageDate(myAccount.getMainCourseId(), myAccount.getSecondryCourseId()));
			topTenDocMap.put("video", profileService.findTop10VideoData(myAccount.getMainCourseId(), myAccount.getSecondryCourseId()));
			topTenDocMap.put("content", profileService.findTop10ContentData(myAccount.getMainCourseId(), myAccount.getSecondryCourseId()));
		} else {
			topTenDocMap.put("image", new ArrayList<>());
			topTenDocMap.put("video",  new ArrayList<>());
			topTenDocMap.put("content", new ArrayList<>());
		}
		return topTenDocMap;
	}
	
	@RequestMapping(value="/find/allUserDocuments",produces="application/json")
	public List<UserDocument> findAllUserDocument() {
		return profileService.findAllUserDocuments();
	}
	
	@RequestMapping(value="/find/myAccountByCreatedById",produces="application/json")
	public MyAccount findMyAccountByCreatedById(Long createdById) {
		return profileService.findMyAccountByCreatedById(createdById);
	}
	
	//UnUsed
	@RequestMapping(value="/find/allContentUserDocumentsForNullPdfAndCategoryIdSubCategoryId",produces="application/json")
	public List<UserDocument> findAllContentUserDocumentsForNullPdfAndCategoryIdSubCategoryId(Long userId) {
		MyAccount myAccount = profileService.findMyAccountByCreatedById(userId);
		if (myAccount != null) {
			return profileService.findUserDocumentsByCategoryIdAndSubCategoryIdAndContentLinkIsNull(myAccount.getMainCourseId(),myAccount.getSecondryCourseId());
		} else {
			return new ArrayList<>();
		}
	}
	
	@RequestMapping(value="/find/allDocumentsByCategotyIdSubCategoryIdContainsVideo",produces="application/json")
	public List<UserDocument> findAllDocumentsByContainsVideoYesNo(Long categoryId,Long subCategoryId,String type) {
			Boolean containsVideo = false;
			if ("video".equals(type)) {
				containsVideo = true;
			}
			return profileService.findAllContentDocumentsByCategoryIdAndSubCtaeogryIdAndContainsVideo(categoryId,subCategoryId,containsVideo);
	}
	
	@RequestMapping(value="/find/categories",produces="application/json")
	public List<ProfileCategory> findMainCategories() {
		return profileService.findProfileCategoryParentCategoryIdNull();
	}
	
	@RequestMapping(value="/find/subCategoriesByParentId",produces="application/json")
	public List<ProfileCategory> findSubCategories(Long parentCategoryId) {
		return profileService.findProfileCategoryParentCategoryId(parentCategoryId);
	}
	
	@RequestMapping(value="/find/subCategories",produces="application/json")
	public List<ProfileCategory> findSubCategories() {
		return profileService.findProfileCategoryParentCategoryIdNotNull();
	}
	
}
