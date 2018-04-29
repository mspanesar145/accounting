package com.accounting.job;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.accounting.UserDocument;
import com.accounting.bo.Rating;
import com.accounting.service.DocumentService;

@Service
public class RatingCalculationJob {
	
	@Autowired
	DocumentService documentService;
	
	@Scheduled(cron="0 0/15 * * * *")
	public void runProvidersRatingCalculation() {
		System.out.println(new Date()+" RatingCalculationJob STARTS");
		List<UserDocument> userDocuments  = this.documentService.findAllUserDocuments();
		for (UserDocument userDocument : userDocuments) {
			List<Rating> ratings = this.documentService.findRatingByUserDocumentId(userDocument.getUserDocumentId());
			if (ratings != null && ratings.size() > 0) {
				double ratingsByUser = 0;
				for (Rating rating :ratings) {
					ratingsByUser += rating.getScore();
				}
				
				double ratingPercentage = (ratingsByUser / (ratings.size()*5)*100);
				double avgRating = (ratingPercentage * 5)/100;
				userDocument.setOverallRating((int)avgRating);
				
			}else{
				userDocument.setOverallRating(0);
			}
			this.documentService.saveUserDocument(userDocument);
 		}
		System.out.println(new Date()+" RatingCalculationJob ENDS");

	}
}
