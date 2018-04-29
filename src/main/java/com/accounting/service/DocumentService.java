package com.accounting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accounting.UserDocument;
import com.accounting.bo.Rating;
import com.accounting.repository.RatingRepository;
import com.accounting.repository.UserDocumentRepository;

@Service
public class DocumentService {
	
	@Autowired
	RatingRepository ratingRepository;
	
	@Autowired
	UserDocumentRepository userDocumentRepository;
	
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
		return userDocumentRepository.save(userDocument);
	}
}
