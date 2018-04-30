package com.accounting.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="profile_categories")
public class ProfileCategory {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="profile_category_id")
	private Long profileCategoryId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="parent_category_id")
	private Long parentCategoryId;

	public Long getProfileCategoryId() {
		return profileCategoryId;
	}

	public void setProfileCategoryId(Long profileCategoryId) {
		this.profileCategoryId = profileCategoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParentCategoryId() {
		return parentCategoryId;
	}

	public void setParentCategoryId(Long parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}
}
