package com.accounting.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bookmark_documents")
public class BookmarkDocument extends AccountingGeneral {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="bookmark_document_id")
	private Long bookmarkDocumentId;
	
	@Column(name="user_document_id")
	private Long userDocumentId;
	
	@Column(name="bookmarked_by_id")
	private Long bookmarkedById;

	public Long getBookmarkDocumentId() {
		return bookmarkDocumentId;
	}

	public void setBookmarkDocumentId(Long bookmarkDocumentId) {
		this.bookmarkDocumentId = bookmarkDocumentId;
	}

	public Long getUserDocumentId() {
		return userDocumentId;
	}

	public void setUserDocumentId(Long userDocumentId) {
		this.userDocumentId = userDocumentId;
	}

	public Long getBookmarkedById() {
		return bookmarkedById;
	}

	public void setBookmarkedById(Long bookmarkedById) {
		this.bookmarkedById = bookmarkedById;
	}
}
