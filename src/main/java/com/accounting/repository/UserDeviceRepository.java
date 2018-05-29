package com.accounting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accounting.user.bo.UserDevice;

@Repository
public interface UserDeviceRepository extends JpaRepository<UserDevice,Long>{
	
	public List<UserDevice> findByUserId(Long userId);
}
