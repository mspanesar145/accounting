package com.accounting.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="my_accounts")
public class MyAccount extends AccountingGeneral{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="my_account_id")
	private Long myAccountId;
	
	@Column(name="main_course_ids")
	private String mainCourseId;
	
	@Column(name="secondry_course_ids")
	private String secondryCourseId;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="secondry_cource_id",insertable=false, updatable=false)
	private ProfileCategory secondryCourse;
	
	@Column(name="news_letter_subscribed")
	private Boolean newsLetterSubscribed = false;
	
	@Column(name="is_notification_on")
	private Boolean isNotificationOn = false;

	@Column(name="created_by_id")
	private Long createdById;
	
	public Long getMyAccountId() {
		return myAccountId;
	}

	public void setMyAccountId(Long myAccountId) {
		this.myAccountId = myAccountId;
	}

	public Boolean getNewsLetterSubscribed() {
		return newsLetterSubscribed;
	}

	public void setNewsLetterSubscribed(Boolean newsLetterSubscribed) {
		this.newsLetterSubscribed = newsLetterSubscribed;
	}

	public Boolean getIsNotificationOn() {
		return isNotificationOn;
	}

	public void setIsNotificationOn(Boolean isNotificationOn) {
		this.isNotificationOn = isNotificationOn;
	}

	public Long getCreatedById() {
		return createdById;
	}

	public void setCreatedById(Long createdById) {
		this.createdById = createdById;
	}

	public ProfileCategory getSecondryCource() {
		return secondryCourse;
	}

	public void setSecondryCource(ProfileCategory secondryCourse) {
		this.secondryCourse = secondryCourse;
	}

	public String getMainCourseId() {
		return mainCourseId;
	}

	public void setMainCourseId(String mainCourseId) {
		this.mainCourseId = mainCourseId;
	}

	public String getSecondryCourseId() {
		return secondryCourseId;
	}

	public void setSecondryCourseId(String secondryCourseId) {
		this.secondryCourseId = secondryCourseId;
	}
}
