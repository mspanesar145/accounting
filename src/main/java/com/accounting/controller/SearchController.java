package com.accounting.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accounting.UserDocument;
import com.accounting.bo.Banner;
import com.accounting.bo.Banner.BannerScreen;
import com.accounting.bo.DocumentComment;
import com.accounting.bo.MyAccount;
import com.accounting.bo.ProfileCategory;
import com.accounting.dao.DocumentDao;
import com.accounting.service.BannerService;
import com.accounting.service.DocumentService;
import com.accounting.service.ProfileService;
import com.accounting.service.UserService;

@RestController
public class SearchController {
	
	@Autowired
	ProfileService profileService;
	
	@Autowired
	UserService userService;

	@Autowired
	DocumentService documentService;
	
	@Autowired
	DocumentDao documentDao;
	
	@Autowired
	BannerService bannerService;
	
	@RequestMapping(value="/find/topTenDocuments",produces="application/json")
	public Map<String,List<UserDocument>> findTopTenDocuments(Long userId,String title) {
		
		Map<Long,List<ProfileCategory>> categoriesMap = profileService.generateCatSubCategoryMap();
		
		Map<Long,List<ProfileCategory>> myAccountCategoiresMap = new HashMap<>();
		
		Map<String,List<UserDocument>> topTenDocMap = new HashMap<>();
		
		MyAccount myAccount = profileService.findMyAccountByCreatedById(userId);
		if (myAccount != null && myAccount.getMainCourseId() != null && myAccount.getSecondryCourseId() != null) {
			
			List<String> mainCourseIds = Arrays.asList(myAccount.getMainCourseId().split(","));
			
			for (String mainCourseIdStr : mainCourseIds) {
				
				Long mainCourseId = Long.parseLong(mainCourseIdStr);
				List<ProfileCategory> secondryCategories = null;
				List<ProfileCategory> secondryCategoriesList = categoriesMap.get(mainCourseId);
				for (ProfileCategory secondryCategory : secondryCategoriesList) {
					if (myAccount.getSecondryCourseId().indexOf(secondryCategory.getProfileCategoryId().toString()) != -1) {
						
						secondryCategories = new ArrayList<>();
						if (myAccountCategoiresMap.containsKey(mainCourseId)) {
							secondryCategories = myAccountCategoiresMap.get(mainCourseId);
						}
						secondryCategories.add(secondryCategory);
						myAccountCategoiresMap.put(mainCourseId, secondryCategories);
					}
				}
			}
			
			topTenDocMap.put("image",documentDao.findTop10ImageDocuments(myAccountCategoiresMap,title));
			topTenDocMap.put("video", documentDao.findTop10VideoDocuments(myAccountCategoiresMap,title));
			topTenDocMap.put("content", documentDao.findTop10ContentDocuments(myAccountCategoiresMap,title));
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
			return null;
			//return profileService.findUserDocumentsByCategoryIdAndSubCategoryIdAndContentLinkIsNull(myAccount.getMainCourseId(),myAccount.getSecondryCourseId());
		} else {
			return new ArrayList<>();
		}
	}
	
	@RequestMapping(value="/find/allDocumentsByCategotyIdSubCategoryIdContainsVideo",produces="application/json")
	public List<UserDocument> findAllDocumentsByContainsVideoYesNo(String categoryId,String subCategoryId,Boolean containsVideo,String title) {
		
		/*if (title == null || title.length() == 0) {
			if (containsVideo == null) {
				return profileService.findAllContentDocumentsByCategoryIdAndSubCtaeogryId(categoryId, subCategoryId);
			}
			return profileService.findAllContentDocumentsByCategoryIdAndSubCtaeogryIdAndContainsVideo(categoryId,subCategoryId,containsVideo);
		} else {
			if (containsVideo == null) {
				return profileService.findAllContentDocumentsByCategoryIdAndSubCtaeogryIdAndTitle(categoryId, subCategoryId,title);
			}
			return profileService.findAllContentDocumentsByCategoryIdAndSubCtaeogryIdAndContainsVideoAndTitle(categoryId,subCategoryId,containsVideo,title);
		}*/
		if (containsVideo == null) {
			containsVideo = false;
		}
		Map<Long,List<ProfileCategory>> categoriesMap = profileService.generateCatSubCategoryMap();
		Map<Long,List<ProfileCategory>> myAccountCategoiresMap = new HashMap<>();

		List<String> mainCourseIds = Arrays.asList(categoryId.split(","));
		
		for (String mainCourseIdStr : mainCourseIds) {
			
			Long mainCourseId = Long.parseLong(mainCourseIdStr);
			List<ProfileCategory> secondryCategories = null;
			List<ProfileCategory> secondryCategoriesList = categoriesMap.get(mainCourseId);
			for (ProfileCategory secondryCategory : secondryCategoriesList) {
				if (subCategoryId.indexOf(secondryCategory.getProfileCategoryId().toString()) != -1) {
					
					secondryCategories = new ArrayList<>();
					if (myAccountCategoiresMap.containsKey(mainCourseId)) {
						secondryCategories = myAccountCategoiresMap.get(mainCourseId);
					}
					secondryCategories.add(secondryCategory);
					myAccountCategoiresMap.put(mainCourseId, secondryCategories);
				}
			}
		}
		
		return documentDao.findUserDocumentsListByCategoryIdSubCategoryIdContainsVideoTitle(myAccountCategoiresMap, containsVideo, title);
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
	
	@RequestMapping(value="/find/documentsByTitle",produces="application/json")
	public List<UserDocument> findUserDocumentsByTitle(String title) {
		return documentService.findUserDocumentByTitle(title);
	}
	
	@RequestMapping(value="/find/documentsCommentsByDocumentId",produces="application/json")
	public List<DocumentComment> findCommentsByDocumentId(Long documentId) {
		return documentService.findDocumentCommentsByDocumentId(documentId);
	}
	
	@RequestMapping(value="/find/bannersByScreen",produces="application/json")
	public List<Banner> findBannersByScreen(BannerScreen screen) {
		return bannerService.findActiveBannersByScreen(screen);
	}
}


