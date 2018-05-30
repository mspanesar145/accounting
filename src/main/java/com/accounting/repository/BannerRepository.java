package com.accounting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accounting.bo.Banner;
import com.accounting.bo.Banner.BannerScreen;
import com.accounting.bo.Banner.BannerStatus;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Long>{
	
	public List<Banner> findByBannerAtScreen(BannerScreen bannerAtScreen);
	public List<Banner> findByBannerAtScreenAndBannerStatus(BannerScreen bannerAtScreen,BannerStatus bannerStatus);

}