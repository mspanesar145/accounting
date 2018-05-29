package com.accounting.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="document_comments")
public class DocumentComment extends AccountingGeneral {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="document_comment_id")
	private Long documentCommentId;
	
	@Column(name="user_document_id")
	private Long userDocumentId;

	@Column(name="comment")
	private String comment;
	
	@Column(name="commented_by_id")
	private Long commentedById;

	public Long getDocumentCommentId() {
		return documentCommentId;
	}

	public void setDocumentCommentId(Long documentCommentId) {
		this.documentCommentId = documentCommentId;
	}

	public Long getUserDocumentId() {
		return userDocumentId;
	}

	public void setUserDocumentId(Long userDocumentId) {
		this.userDocumentId = userDocumentId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Long getCommentedById() {
		return commentedById;
	}

	public void setCommentedById(Long commentedById) {
		this.commentedById = commentedById;
	}
	
}
