package com.accounting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accounting.bo.ProfileCategory;

@Repository
public interface ProfileCategoryRepository extends JpaRepository<ProfileCategory, Long>{

	List<ProfileCategory> findByParentCategoryId(Long parentCategoryId);
	
}
