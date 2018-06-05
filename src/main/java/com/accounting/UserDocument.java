package com.accounting;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.accounting.bo.AccountingGeneral;
import com.accounting.bo.DocumentComment;
import com.accounting.bo.DocumentStats;
import com.accounting.enums.AccountingEnums.FileType;

@Entity
@Table(name="user_documents")
public class UserDocument extends AccountingGeneral {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_document_id")
	private Long userDocumentId;

	@Enumerated(EnumType.STRING)
	@Column(name="file_type")
	private FileType fileType;
	
	@Column(name="category_id")
	private Long categoryId;
	
	@Column(name="sub_category_id")
	private Long subCategoryId;
	
	@Column(name="title")
	private String title;

	@Column(name="cover_image_url")
	private String coverImageUrl;
	
	@Column(name="content_link_url")
	private String contentLinkUrl;
	
	@Column(name="video_link")
	private String videoLink;
	
	@Column(name="content")
	private String content;

	@Column(name="created_by_id")
	private Long createdById;
	
	@Column(name="contains_video")
	private Boolean containsVideo = false;
	
	@Column(name="overall_rating")
	private Integer overallRating;
	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="user_document_id",insertable=false,updatable=false)
	private List<DocumentComment> documentComments;
	
	@OneToOne(fetch=FetchType.EAGER,mappedBy = "userDocument", cascade = CascadeType.ALL)
	private DocumentStats documentStats;
	
	@Transient
	private MultipartFile uploadFile;

	public Long getUserDocumentId() {
		return userDocumentId;
	}

	public void setUserDocumentId(Long userDocumentId) {
		this.userDocumentId = userDocumentId;
	}

	public FileType getFileType() {
		return fileType;
	}

	public void setFileType(FileType fileType) {
		this.fileType = fileType;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(Long subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCoverImageUrl() {
		return coverImageUrl;
	}

	public void setCoverImageUrl(String coverImageUrl) {
		this.coverImageUrl = coverImageUrl;
	}

	public String getVideoLink() {
		return videoLink;
	}

	public void setVideoLink(String videoLink) {
		this.videoLink = videoLink;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getCreatedById() {
		return createdById;
	}

	public void setCreatedById(Long createdById) {
		this.createdById = createdById;
	}

	public MultipartFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	public Boolean getContainsVideo() {
		return containsVideo;
	}

	public void setContainsVideo(Boolean containsVideo) {
		this.containsVideo = containsVideo;
	}

	public String getContentLinkUrl() {
		return contentLinkUrl;
	}

	public void setContentLinkUrl(String contentLinkUrl) {
		this.contentLinkUrl = contentLinkUrl;
	}

	public Integer getOverallRating() {
		return overallRating;
	}

	public void setOverallRating(Integer overallRating) {
		this.overallRating = overallRating;
	}

	public List<DocumentComment> getDocumentComments() {
		return documentComments;
	}

	public void setDocumentComments(List<DocumentComment> documentComments) {
		this.documentComments = documentComments;
	}

	public DocumentStats getDocumentStats() {
		return documentStats;
	}

	public void setDocumentStats(DocumentStats documentStats) {
		this.documentStats = documentStats;
	}
}
