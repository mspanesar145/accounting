package com.accounting.service;

import java.util.Date;
import java.util.List;

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
		if (documentStats == null) {
			documentStats = new DocumentStats();
			documentStats.setAttachmentCounts(0l);
			documentStats.setContentCounts(0l);
			documentStats.setUserDocumentId(userDocumentId);
			documentStats.setCreatedAt(new Date());
		}
		System.out.println("Document Found : "+documentStats.getUserDocumentId());
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
