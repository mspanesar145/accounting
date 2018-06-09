package com.accounting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accounting.bo.BookmarkDocument;

@Repository
public interface BookmarkDocumentRepository extends JpaRepository<BookmarkDocument, Long>{

	public List<BookmarkDocument> findByBookmarkedById(Long bookmarkedById);
}
