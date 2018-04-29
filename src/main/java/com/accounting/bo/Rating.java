package com.accounting.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ratings")
public class Rating extends AccountingGeneral {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="rating_id")
	private Long ratingId;
	
	@Column(name="score")
	private Integer score;
	
	@Column(name="rated_by_id")
	private Long ratedById;
	
	@Column(name="user_document_id")
	private Long userDocumentId;

	public Long getRatingId() {
		return ratingId;
	}

	public void setRatingId(Long ratingId) {
		this.ratingId = ratingId;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Long getUserDocumentId() {
		return userDocumentId;
	}

	public void setUserDocumentId(Long userDocumentId) {
		this.userDocumentId = userDocumentId;
	}

	public Long getRatedById() {
		return ratedById;
	}

	public void setRatedById(Long ratedById) {
		this.ratedById = ratedById;
	}
}
