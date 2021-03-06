package com.accounting.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accounting.UserDocument;
import com.accounting.bo.BookmarkDocument;
import com.accounting.bo.DocumentComment;
import com.accounting.bo.DocumentStats;
import com.accounting.bo.DocumentStats.DocumentStatsSource;
import com.accounting.bo.Rating;
import com.accounting.repository.BookmarkDocumentRepository;
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
	
	@Autowired
	BookmarkDocumentRepository bookmarkDocumentRepository;
	
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
		
		UserDocument document = userDocumentRepository.findOne(userDocumentId);
		DocumentStats documentStats = documentStatsRepository.findByUserId(document.getCreatedById());
		if (documentStats == null) {
			documentStats = new DocumentStats();
			documentStats.setAttachmentCounts(0l);
			documentStats.setContentCounts(0l);
			documentStats.setUserDocumentId(userDocumentId);
			documentStats.setUserId(document.getCreatedById());
			documentStats.setCreatedAt(new Date());
		}
		System.out.println("Document Found : "+documentStats.getUserDocumentId());
		if (source.equals(DocumentStatsSource.content)) {
			long contentStats = 1;
			documentStats.setContentCounts(contentStats);
		} else  if (source.equals(DocumentStatsSource.attachment)) {
			long attachmentStats = 1;
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
	
	public List<UserDocument> findBookmarkedDocumentsByUserId(Long userId) {
		return userDocumentRepository.findByBookmarkedById(userId);
	}
	
	public UserDocument findUserDocumentById(Long userId) {
		return userDocumentRepository.findOne(userId);
	}
	
	public Map<String,String> updateBookMarkedDocumentStatus(BookmarkDocument bookmarkDocument) {
		Map<String,String> responseMap = new HashMap<>();
		responseMap.put("status","success");
		if (bookmarkDocument.getBookmarkDocumentId() != null) {
			bookmarkDocumentRepository.delete(bookmarkDocument.getBookmarkDocumentId());
			responseMap.put("message","");
		} else {
			bookmarkDocumentRepository.save(bookmarkDocument);
			responseMap.put("message","Bookmarked..!!");
		}
		return responseMap;
	}
	
	public BookmarkDocument findBookmarkDocumentByUserDocumentId(Long userDocumentId){
		
		return bookmarkDocumentRepository.findByUserDocumentId(userDocumentId);
		
	}
}
