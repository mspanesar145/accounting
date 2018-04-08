package com.accounting;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.accounting.bo.AccountingGeneral;
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
	
	@Column(name="video_link")
	private String videoLink;
	
	@Column(name="content")
	private String content;

	@Column(name="created_by_id")
	private Long createdById;
	
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
}
