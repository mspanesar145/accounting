package com.accounting.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.accounting.UserDocument;

@Entity
@Table(name="document_stats")
public class DocumentStats extends AccountingGeneral {

	public enum DocumentStatsSource {content,attachment}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="document_stats_id")
	private Long documentStatsId;
	
	@Column(name="user_document_id")
	private Long userDocumentId;
	
	@Column(name="content_counts",nullable = false, columnDefinition = "int default 0")
	private Long contentCounts;
	
	@Column(name="attachment_counts",nullable = false, columnDefinition = "int default 0")
	private Long attachmentCounts;
	
	@Column(name="total_counts",nullable = false, columnDefinition = "int default 0")
	private Long totalCounts;
	
	public Long getDocumentStatsId() {
		return documentStatsId;
	}

	public void setDocumentStatsId(Long documentStatsId) {
		this.documentStatsId = documentStatsId;
	}
	
	public Long getUserDocumentId() {
		return userDocumentId;
	}

	public void setUserDocumentId(Long userDocumentId) {
		this.userDocumentId = userDocumentId;
	}

	public Long getContentCounts() {
		return contentCounts;
	}

	public void setContentCounts(Long contentCounts) {
		this.contentCounts = contentCounts;
	}

	public Long getAttachmentCounts() {
		return attachmentCounts;
	}

	public void setAttachmentCounts(Long attachmentCounts) {
		this.attachmentCounts = attachmentCounts;
	}

	public Long getTotalCounts() {
		return totalCounts;
	}

	public void setTotalCounts(Long totalCounts) {
		this.totalCounts = totalCounts;
	}

}
