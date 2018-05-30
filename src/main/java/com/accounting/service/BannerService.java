package com.accounting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accounting.bo.Banner;
import com.accounting.bo.Banner.BannerScreen;
import com.accounting.bo.Banner.BannerStatus;
import com.accounting.repository.BannerRepository;

@Service
public class BannerService {

	@Autowired
	private BannerRepository bannerReporsitory;
	
	public List<Banner> findActiveBannersByScreen(BannerScreen bannerAtScreen) {
		return bannerReporsitory.findByBannerAtScreenAndBannerStatus(bannerAtScreen, BannerStatus.Active);
	}
	
	public Banner saveBanner(Banner banner) {
		return bannerReporsitory.save(banner);
	}
}
