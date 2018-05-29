package com.accounting.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accounting.bo.DocumentComment;

public interface DocumentCommentRepository extends JpaRepository<DocumentComment, Long>{
	
}
