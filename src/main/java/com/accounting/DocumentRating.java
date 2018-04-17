package com.accounting;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.accounting.bo.AccountingGeneral;

@Entity
@Table(name="document_ratings")
public class DocumentRating extends AccountingGeneral{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="document_rating_id")
	private Long documentRatingId;
	
	@Column(name="user_document_id")
	private Long userDocumentId;
	
	@Column(name="user_id")
	private Long userId;
	
	@Column(name="score")
	private Integer ratingScore;

	public Long getDocumentRatingId() {
		return documentRatingId;
	}

	public void setDocumentRatingId(Long documentRatingId) {
		this.documentRatingId = documentRatingId;
	}

	public Long getUserDocumentId() {
		return userDocumentId;
	}

	public void setUserDocumentId(Long userDocumentId) {
		this.userDocumentId = userDocumentId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getRatingScore() {
		return ratingScore;
	}

	public void setRatingScore(Integer ratingScore) {
		this.ratingScore = ratingScore;
	}
}
