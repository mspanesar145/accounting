package com.accounting.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accounting.UserDocument;
import com.accounting.bo.DocumentComment;
import com.accounting.bo.DocumentStats;
import com.accounting.bo.DocumentStats.DocumentStatsSource;
import com.accounting.bo.Rating;
import com.accounting.repository.DocumentCommentRepository;
import com.accounting.repository.DocumentStatsRepository;
import com.accounting.repository.RatingRepository;
import com.accounting.repository.UserDocumentRepository;
import com.accounting.user.bo.User;
import com.accounting.user.bo.UserDevice;

@Service
public class DocumentService {
	
	@Autowired
	RatingRepository ratingRepository;
	
	@Autowired
	UserDocumentRepository userDocumentRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	NotificationService notificationService;

	@Autowired
	DocumentCommentRepository documentCommentRepository;
	
	@Autowired
	DocumentStatsRepository documentStatsRepository;
	
	public Rating saveRating(Rating rating) {		
		return ratingRepository.save(rating);
	}
	
	public List<Rating> findRatingByUserDocumentId(Long userDocumentId) {
		return ratingRepository.findByUserDocumentId(userDocumentId);
	}
	
	public List<UserDocument> findAllUserDocuments() {
		return userDocumentRepository.findAll();
	}
	
	public UserDocument saveUserDocument(UserDocument userDocument) {
		
		userDocument = userDocumentRepository.save(userDocument);
		
		User documentOwner = userService.findUserById(userDocument.getCreatedById());
		
		List<User> users = userService.findUsersByMainCourseIdsAndSecondryCourseIds(userDocument.getCategoryId()+"",userDocument.getSubCategoryId()+"");
		if (users != null && users.size() > 0) {
			List<String> deviceTokens = new ArrayList<>();
			for (User user : users) {
				List<UserDevice> userDevices = user.getUserDevices();
				if (userDevices != null && userDevices.size() > 0) {
					for (UserDevice userDevice : userDevices) {
						deviceTokens.add(userDevice.getDeviceToken());
					}
				}
			}
			
			if (deviceTokens.size() > 0) {
				Map<String,Object> payload = new HashMap<>();
				payload.put("message", "New Document Uploaded By "+documentOwner.getFirstName());
				notificationService.sendPushNotification(deviceTokens, payload);
				
			}
 		}
		return userDocument;
	}
	
	public List<UserDocument> findUserDocumentByTitle(String title) {
		return userDocumentRepository.findByTitleContainig(title);
	}
	
	public UserDocument saveDocumentComment(DocumentComment documentComment) {
		documentComment = documentCommentRepository.save(documentComment);
		return userDocumentRepository.findOne(documentComment.getUserDocumentId());
	}
	
	public UserDocument updateDocumentStats(Long userDocumentId,DocumentStatsSource source) {
		
		DocumentStats documentStats = documentStatsRepository.findByUserDocumentId(userDocumentId);
		
		if (source.equals(DocumentStatsSource.content)) {
			long contentStats = documentStats.getContentCounts() + 1;
			documentStats.setContentCounts(contentStats);
		} else  if (source.equals(DocumentStatsSource.attachment)) {
			long attachmentStats = documentStats.getAttachmentCounts() + 1;
			documentStats.setAttachmentCounts(attachmentStats);
		}
		
		long totalCounts = documentStats.getContentCounts() + documentStats.getAttachmentCounts();
		documentStats.setTotalCounts(totalCounts);
		documentStatsRepository.save(documentStats);
		
		return userDocumentRepository.findOne(userDocumentId);
	}
	
	public List<DocumentComment> findDocumentCommentsByDocumentId(Long documentId) {
		return documentCommentRepository.findByUserDocumentId(documentId);
	}
}
