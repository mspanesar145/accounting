package com.accounting.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="my_accounts")
public class MyAccount extends AccountingGeneral{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="my_account_id")
	private Long myAccountId;
	
	@Column(name="main_cource_id")
	private Long mainCourseId;
	
	@Column(name="secondry_cource_id")
	private Long secondryCourseId;
	
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

	public Long getMainCourseId() {
		return mainCourseId;
	}

	public void setMainCourseId(Long mainCourseId) {
		this.mainCourseId = mainCourseId;
	}

	public Long getSecondryCourseId() {
		return secondryCourseId;
	}

	public void setSecondryCourseId(Long secondryCourseId) {
		this.secondryCourseId = secondryCourseId;
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
}
