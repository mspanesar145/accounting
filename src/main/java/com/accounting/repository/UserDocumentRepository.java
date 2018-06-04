package com.accounting.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.accounting.UserDocument;

@Repository
public interface UserDocumentRepository extends JpaRepository<UserDocument, Long>{
	
	@Query("select ud from UserDocument ud where ud.categoryId =:categoryId and ud.subCategoryId =:subCategoryId and ud.containsVideo = false")
	public List<UserDocument> findByNullContentLinkAndCategoryIdAndSubcategoryId(@Param("categoryId") Long categoryId,@Param("subCategoryId") Long subCategoryId);

	@Query("select ud from UserDocument ud where ud.categoryId =:categoryId and ud.subCategoryId =:subCategoryId and ud.containsVideo = :containsVideo")
	public List<UserDocument> findByCategoryIdAndSubcategoryIdAndContainsVideo(@Param("categoryId") Long categoryId,@Param("subCategoryId") Long subCategoryId,@Param("containsVideo") Boolean containsVideo);

	//Qry for tirle
	@Query("select ud from UserDocument ud where ud.categoryId =:categoryId and ud.subCategoryId =:subCategoryId and ud.containsVideo = :containsVideo and title like CONCAT(:title,'%')")
	public List<UserDocument> findByCategoryIdAndSubcategoryIdAndContainsVideoAndTitleContaining(@Param("categoryId") Long categoryId,@Param("subCategoryId") Long subCategoryId,@Param("containsVideo") Boolean containsVideo,@Param("title") String title);
	
	@Query("select ud from UserDocument ud where ud.categoryId =:categoryId and ud.subCategoryId =:subCategoryId")
	public List<UserDocument> findByCategoryIdAndSubcategoryId(@Param("categoryId") Long categoryId,@Param("subCategoryId") Long subCategoryId);

	//Query for title filter.
	@Query("select ud from UserDocument ud where ud.categoryId =:categoryId and ud.subCategoryId =:subCategoryId and title like CONCAT(:title,'%')")
	public List<UserDocument> findByCategoryIdAndSubcategoryIdAndTitleContaining(@Param("categoryId") Long categoryId,@Param("subCategoryId") Long subCategoryId,@Param("title") String title);
	
	@Query("select ud from UserDocument ud where ud.categoryId =:categoryId and ud.subCategoryId =:subCategoryId and ud.containsVideo = true")
	public List<UserDocument> findTo10VideoData(Pageable page,@Param("categoryId") Long categoryId,@Param("subCategoryId") Long subCategoryId);

	@Query("select ud from UserDocument ud where ud.categoryId =:categoryId and ud.subCategoryId =:subCategoryId and ud.containsVideo = false and ud.contentLinkUrl is null")
	public List<UserDocument> findTo10ContentData(Pageable page,@Param("categoryId") Long categoryId,@Param("subCategoryId") Long subCategoryId);

	@Query("select ud from UserDocument ud where ud.categoryId =:categoryId and ud.subCategoryId =:subCategoryId and ud.contentLinkUrl is not null")
	public List<UserDocument> findTop10ImageData(Pageable page,@Param("categoryId") Long categoryId,@Param("subCategoryId") Long subCategoryId);

	@Query("select ud from UserDocument ud where ud.title like CONCAT(:title,'%')")
	public List<UserDocument> findByTitleContainig(@Param("title") String title);

}
