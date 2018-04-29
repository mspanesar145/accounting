package com.accounting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accounting.bo.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long>{
	
	List<Rating> findByUserDocumentId(Long userDocumentId);
}
