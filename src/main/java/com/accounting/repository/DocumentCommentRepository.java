package com.accounting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accounting.bo.DocumentComment;

public interface DocumentCommentRepository extends JpaRepository<DocumentComment, Long>{
	
	public List<DocumentComment> findByUserDocumentId(Long userDocumentId);
}
