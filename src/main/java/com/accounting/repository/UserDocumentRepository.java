package com.accounting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.accounting.UserDocument;

@Repository
public interface UserDocumentRepository extends JpaRepository<UserDocument, Long>{
	
	@Query("select ud from UserDocument ud where ud.categoryId =:categoryId and ud.subCategoryId =:ud.subCategoryId and ud.contentLinkUrl is null")
	public List<UserDocument> findByNullContentLinkAndCategoryIdAndSubcategoryId(Long categoryId,Long subCategoryId);
}
