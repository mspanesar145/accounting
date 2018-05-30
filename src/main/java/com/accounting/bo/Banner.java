package com.accounting.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="banners")
public class Banner extends AccountingGeneral {

	public enum BannerScreen {login,home}; 
	public enum BannerStatus {InActive,Active}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="banner_id")
	private Long bannerId;
	
	@Column(name="path")
	private String path;
	
	@Enumerated(EnumType.STRING)
	@Column(name="banner_at_screen")
	private BannerScreen bannerAtScreen;

	@Enumerated(EnumType.STRING)
	@Column(name="banner_status")
	private BannerStatus bannerStatus = BannerStatus.Active;
	
	@Column(name="display_order")
	private Integer displayOrder;
	
	public Long getBannerId() {
		return bannerId;
	}

	public void setBannerId(Long bannerId) {
		this.bannerId = bannerId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public BannerScreen getBannerAtScreen() {
		return bannerAtScreen;
	}

	public void setBannerAtScreen(BannerScreen bannerAtScreen) {
		this.bannerAtScreen = bannerAtScreen;
	}

	public BannerStatus getBannerStatus() {
		return bannerStatus;
	}

	public void setBannerStatus(BannerStatus bannerStatus) {
		this.bannerStatus = bannerStatus;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}
}
