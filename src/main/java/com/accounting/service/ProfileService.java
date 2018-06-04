package com.accounting.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.accounting.UserDocument;
import com.accounting.bo.MyAccount;
import com.accounting.bo.ProfileCategory;
import com.accounting.repository.MyAccountRepository;
import com.accounting.repository.ProfileCategoryRepository;
import com.accounting.repository.UserDocumentRepository;

@Service
public class ProfileService {
	
	@Autowired
	private UserDocumentRepository userDocumentRepository;
	
	@Autowired
	private MyAccountRepository myAccountRepository;
	
	@Autowired
	private ProfileCategoryRepository profileCategoryRepository;
	
	@Value("${accounting.domain}")
	private String domain;
	
	@Value("${accounting.imageUploadPath}")
	private String imageUploadPath;
	
	public UserDocument saveUserDocument(UserDocument userDocument) {
		/*if (userDocument.getUploadFile() != null) {
			String coverPhotoUrl = uploadUserDocument(userDocument);
			if (coverPhotoUrl != null) {
				userDocument.setCoverImageUrl(coverPhotoUrl);
			}
		}*/
		return userDocumentRepository.save(userDocument);
	}
	
	public List<UserDocument> findAllUserDocuments() {
		return userDocumentRepository.findAll();
	}
	
	public List<UserDocument> findTopTenImageDate(Long categoryId, Long subCategoryId) {
		Pageable pageable = new PageRequest(0, 10, new Sort(Sort.Direction.DESC,"userDocumentId"));
		return userDocumentRepository.findTop10ImageData(pageable,categoryId, subCategoryId);
	}
	public List<UserDocument> findTop10VideoData(Long categoryId,Long subCategoryId) {
		Pageable pageable = new PageRequest(0, 10, new Sort(Sort.Direction.DESC,"userDocumentId"));

		return userDocumentRepository.findTo10VideoData(pageable,categoryId, subCategoryId);
	}
	public List<UserDocument> findTop10ContentData(Long categoryId,Long subCategoryId) {
		Pageable pageable = new PageRequest(0, 10, new Sort(Sort.Direction.DESC,"userDocumentId"));

		return userDocumentRepository.findTo10ContentData(pageable,categoryId, subCategoryId);
	}
	
	public List<UserDocument> findUserDocumentsByCategoryIdAndSubCategoryIdAndContentLinkIsNull(Long categoryId,Long subCategoryId) {
		return userDocumentRepository.findByNullContentLinkAndCategoryIdAndSubcategoryId(categoryId, subCategoryId);
	}
	
	public List<UserDocument> findAllContentDocumentsByCategoryIdAndSubCtaeogryId(Long categoryId,Long subCategoryId) {
		return userDocumentRepository.findByCategoryIdAndSubcategoryId(categoryId, subCategoryId);
	}
	public List<UserDocument> findAllContentDocumentsByCategoryIdAndSubCtaeogryIdAndContainsVideo(Long categoryId,Long subCategoryId,Boolean containsVideo) {
		return userDocumentRepository.findByCategoryIdAndSubcategoryIdAndContainsVideo(categoryId, subCategoryId,containsVideo);
	}
	
	//Contains title
	public List<UserDocument> findAllContentDocumentsByCategoryIdAndSubCtaeogryIdAndTitle(Long categoryId,Long subCategoryId,String title) {
		return userDocumentRepository.findByCategoryIdAndSubcategoryIdAndTitleContaining(categoryId, subCategoryId,title);
	}
	public List<UserDocument> findAllContentDocumentsByCategoryIdAndSubCtaeogryIdAndContainsVideoAndTitle(Long categoryId,Long subCategoryId,Boolean containsVideo,String title) {
		return userDocumentRepository.findByCategoryIdAndSubcategoryIdAndContainsVideoAndTitleContaining(categoryId, subCategoryId,containsVideo,title);
	}
	
	public MyAccount saveMyAccount(MyAccount myAccount) {
		return myAccountRepository.save(myAccount);
	}
	
	public List<MyAccount> saveMyAccounts(List<MyAccount> myAccounts) {
		return myAccountRepository.save(myAccounts);
	}
	
	public MyAccount findMyAccountByCreatedById(Long createdById) {
		return myAccountRepository.findByCreatedById(createdById);
	}
	
	public Map<Long,List<ProfileCategory>> generateCatSubCategoryMap() {
		
		Map<Long,List<ProfileCategory>> catSubCatMap = new HashMap<>();
		
		List<ProfileCategory> profileCategories =  profileCategoryRepository.findAll();
		
		List<ProfileCategory> categories = new ArrayList<>();
		for (ProfileCategory profileCategory : profileCategories) {
			if (profileCategory.getParentCategoryId() == null) {
				categories.add(profileCategory);
			}
		}	
		
		for (ProfileCategory mainCategory : categories) {
			
			for (ProfileCategory profileCategory : profileCategories) {
				
				List<ProfileCategory> profileCategoryList = null;
				if (!catSubCatMap.containsKey(mainCategory.getProfileCategoryId())) {
					profileCategoryList = new ArrayList<>();
				} else {
					profileCategoryList = catSubCatMap.get(mainCategory.getProfileCategoryId());
				}
				
				if (mainCategory.getProfileCategoryId().equals(profileCategory.getParentCategoryId())) {
					profileCategoryList.add(profileCategory);
				}
				catSubCatMap.put(mainCategory.getProfileCategoryId(), profileCategoryList);
			}
			
		}
		return catSubCatMap;
	}
	
	public List<ProfileCategory> findProfileCategoryParentCategoryIdNull() {
		Long parentCateogryId = null;
		return profileCategoryRepository.findByParentCategoryId(parentCateogryId);
	}
	
	public List<ProfileCategory> findProfileCategoryParentCategoryId(Long parentCategoryId) {
		return profileCategoryRepository.findByParentCategoryId(parentCategoryId);
	}
	
	public List<ProfileCategory> findProfileCategoryParentCategoryIdNotNull() {
		return profileCategoryRepository.findByParentCategoryIdNotNull();
	}
	
	public String uploadUserDocument(MultipartFile uploadedFile,Long userId) {
		String storagePathDir = imageUploadPath.replaceAll("\\[userId\\]", userId+"");
		
		String fileName = uploadFile(uploadedFile,storagePathDir);
		if (fileName != null) {
	        return "http://159.203.95.8:8181/assets/uploads/"+userId+"/"+fileName;
		}
		return null;
	}
	
    public String uploadFile(MultipartFile uploadfile,String storagePathDir) {
    	
		File file = new File(storagePathDir);
		if (file != null) {
			File[] files = file.listFiles(); 
	        /*if (files != null && files.length > 0) {
	            for (File f:files) {
	            	if (f.isFile() && f.exists()) { 
	            		f.delete();
	            		System.out.println("successfully deleted");
	                } else {
	                	System.out.println("cant delete a file due to open or error");
	                } 
	            }
	        }*/
		}
		InputStream inputStream = null;
        OutputStream outputStream = null;
        String extension = uploadfile.getOriginalFilename().substring(uploadfile.getOriginalFilename().trim().lastIndexOf("."),uploadfile.getOriginalFilename().length());
        System.out.println("File Name : "+extension);
        String fileName = UUID.randomUUID().toString()+extension;

        File f = new File(storagePathDir);
        if(!f.exists()) { 
        	try {
				f.mkdirs();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        System.out.println(storagePathDir+fileName);
        File newFile = new File(storagePathDir+fileName);
        try {
            inputStream = uploadfile.getInputStream();

            if (!newFile.exists()) {
                newFile.createNewFile();
            }
            outputStream = new FileOutputStream(newFile);
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        return fileName;
    }
}
