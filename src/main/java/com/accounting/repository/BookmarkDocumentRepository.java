package com.accounting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accounting.bo.BookmarkDocument;

@Repository
public interface BookmarkDocumentRepository extends JpaRepository<BookmarkDocument, Long>{

	public BookmarkDocument findByBookmarkedById(Long bookmarkedById);
	
	public BookmarkDocument findByUserDocumentId(Long documentId);
}
