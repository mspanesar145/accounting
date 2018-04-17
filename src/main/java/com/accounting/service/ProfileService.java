package com.accounting.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.accounting.DocumentRating;
import com.accounting.UserDocument;
import com.accounting.bo.MyAccount;
import com.accounting.repository.DocumentRatingRepository;
import com.accounting.repository.MyAccountRepository;
import com.accounting.repository.UserDocumentRepository;

@Service
public class ProfileService {
	
	@Autowired
	private UserDocumentRepository userDocumentRepository;
	
	@Autowired
	private MyAccountRepository myAccountRepository;
	
	@Autowired
	private DocumentRatingRepository documentRatingRepository;
	
	@Value("${accounting.domain}")
	private String domain;
	
	public UserDocument saveUserDocument(UserDocument userDocument) {
		if (userDocument.getUploadFile() != null) {
			String coverPhotoUrl = uploadUserDocument(userDocument);
			if (coverPhotoUrl != null) {
				userDocument.setCoverImageUrl(coverPhotoUrl);
			}
		}
		return userDocumentRepository.save(userDocument);
	}
	
	public List<UserDocument> findAllUserDocuments() {
		return userDocumentRepository.findAll();
	}
	
	public MyAccount saveMyAccount(MyAccount myAccount) {
		return myAccountRepository.save(myAccount);
	}
	
	public DocumentRating saveDocumentRating(DocumentRating documentRating) {
		return documentRatingRepository.save(documentRating);
	}
	
	public String uploadUserDocument(UserDocument userDocument) {
		String storagePathDir = null;//imageUploadPath.replaceAll("\\[userId\\]", userId);
		
		String fileName = uploadFile(userDocument.getUploadFile(),storagePathDir);
		if (fileName != null) {
	        return "http://"+domain+"/assets/uploads/"+userDocument.getCreatedById()+"/"+fileName;
		}
		return null;
	}
	
    public String uploadFile(MultipartFile uploadfile,String storagePathDir) {
    	
		File file = new File(storagePathDir);
		if (file != null) {
			File[] files = file.listFiles(); 
	        if (files != null && files.length > 0) {
	            for (File f:files) {
	            	if (f.isFile() && f.exists()) { 
	            		f.delete();
	            		System.out.println("successfully deleted");
	                } else {
	                	System.out.println("cant delete a file due to open or error");
	                } 
	            }
	        }
		}
		InputStream inputStream = null;
        OutputStream outputStream = null;
        String extension = uploadfile.getOriginalFilename().substring(uploadfile.getOriginalFilename().trim().lastIndexOf("."),uploadfile.getOriginalFilename().length());
        
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