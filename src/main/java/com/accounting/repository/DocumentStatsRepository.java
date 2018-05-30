package com.accounting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accounting.bo.DocumentStats;

@Repository
public interface DocumentStatsRepository extends JpaRepository<DocumentStats, Long>{
	
	public DocumentStats findByUserDocumentId(Long userDocumentId);
}
