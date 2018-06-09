package com.accounting.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.accounting.UserDocument;
import com.accounting.bo.ProfileCategory;

@Service
public class DocumentDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<UserDocument> findTop10ImageDocuments(Map<Long,List<ProfileCategory>> myAccountCategories,String title) {
		
		StringBuffer sb = new StringBuffer();
		
		for (Entry<Long,List<ProfileCategory>> myAccountCatSet : myAccountCategories.entrySet()) {
			for (ProfileCategory subCategory : myAccountCatSet.getValue()) {
				sb.append("(category_id = "+myAccountCatSet.getKey()+" and sub_category_id = "+subCategory.getProfileCategoryId()+")");
				sb.append(" or ");
			}
		}
		
		String catCustomQry = sb.toString().substring(0,sb.toString().lastIndexOf(" or "));
		
		String titleQry = "";
		if (title != null && title.length() > 0) {
			titleQry = " and title like '"+title+"%' ";
		}
		
		String sql = "SELECT * from user_documents where content_link_url is not null and "+catCustomQry+" "+titleQry+" order by user_document_id desc limit 10";
		System.out.println("image sql : "+sql);

		return jdbcTemplate.query(sql, new RowMapper<UserDocument>() {
			@Override
			public UserDocument mapRow(ResultSet rs, int rownumber) throws SQLException {
				UserDocument userDocument = new UserDocument();
				userDocument.setUserDocumentId(rs.getLong("user_document_id"));
				userDocument.setCategoryId(rs.getLong("category_id"));
				userDocument.setSubCategoryId(rs.getLong("sub_category_id"));
				userDocument.setContainsVideo(rs.getBoolean("contains_video"));
				userDocument.setContent(rs.getString("content"));
				userDocument.setContentLinkUrl(rs.getString("content_link_url"));
				userDocument.setCoverImageUrl(rs.getString("cover_image_url"));
				userDocument.setCreatedAt(rs.getDate("created_at"));
				userDocument.setCreatedById(rs.getLong("created_by_id"));
				userDocument.setOverallRating(rs.getInt("overall_rating"));
				userDocument.setTitle(rs.getString("title"));
				userDocument.setVideoLink(rs.getString("video_link"));
				
				return userDocument;
			}
		});
	}	
	
	public List<UserDocument> findTop10ContentDocuments(Map<Long,List<ProfileCategory>> myAccountCategories,String title) {
		
		StringBuffer sb = new StringBuffer();
		
		for (Entry<Long,List<ProfileCategory>> myAccountCatSet : myAccountCategories.entrySet()) {
			for (ProfileCategory subCategory : myAccountCatSet.getValue()) {
				sb.append("(category_id = "+myAccountCatSet.getKey()+" and sub_category_id = "+subCategory.getProfileCategoryId()+")");
				sb.append(" or ");
			}
		}
		
		String catCustomQry = sb.toString().substring(0,sb.toString().lastIndexOf(" or "));
		
		String titleQry = "";
		if (title != null && title.length() > 0) {
			titleQry = " and title like '"+title+"%' ";
		}
		
		String sql = "SELECT * from user_documents where contains_video = false and "+catCustomQry+" "+titleQry+" order by user_document_id desc limit 10";
		System.out.println("vide sql : "+sql);
		return jdbcTemplate.query(sql, new RowMapper<UserDocument>() {
			@Override
			public UserDocument mapRow(ResultSet rs, int rownumber) throws SQLException {
				UserDocument userDocument = new UserDocument();
				userDocument.setUserDocumentId(rs.getLong("user_document_id"));
				userDocument.setCategoryId(rs.getLong("category_id"));
				userDocument.setSubCategoryId(rs.getLong("sub_category_id"));
				userDocument.setContainsVideo(rs.getBoolean("contains_video"));
				userDocument.setContent(rs.getString("content"));
				userDocument.setContentLinkUrl(rs.getString("content_link_url"));
				userDocument.setCoverImageUrl(rs.getString("cover_image_url"));
				userDocument.setCreatedAt(rs.getDate("created_at"));
				userDocument.setCreatedById(rs.getLong("created_by_id"));
				userDocument.setOverallRating(rs.getInt("overall_rating"));
				userDocument.setTitle(rs.getString("title"));
				userDocument.setVideoLink(rs.getString("video_link"));
				
				return userDocument;
			}
		});
	}	

	public List<UserDocument> findTop10VideoDocuments(Map<Long,List<ProfileCategory>> myAccountCategories,String title) {
	
		StringBuffer sb = new StringBuffer();
		
		for (Entry<Long,List<ProfileCategory>> myAccountCatSet : myAccountCategories.entrySet()) {
			for (ProfileCategory subCategory : myAccountCatSet.getValue()) {
				sb.append("(category_id = "+myAccountCatSet.getKey()+" and sub_category_id = "+subCategory.getProfileCategoryId()+")");
				sb.append(" or ");
			}
		}
		
		String catCustomQry = sb.toString().substring(0,sb.toString().lastIndexOf(" or "));
		
		String titleQry = "";
		if (title != null && title.length() > 0) {
			titleQry = " and title like '"+title+"%' ";
		}
		
		String sql = "SELECT * from user_documents where contains_video = true and content_link_url is null and "+catCustomQry+" "+titleQry+" order by user_document_id desc limit 10";
		System.out.println("Content sql : "+sql);
		return jdbcTemplate.query(sql, new RowMapper<UserDocument>() {
			@Override
			public UserDocument mapRow(ResultSet rs, int rownumber) throws SQLException {
				UserDocument userDocument = new UserDocument();
				userDocument.setUserDocumentId(rs.getLong("user_document_id"));
				userDocument.setCategoryId(rs.getLong("category_id"));
				userDocument.setSubCategoryId(rs.getLong("sub_category_id"));
				userDocument.setContainsVideo(rs.getBoolean("contains_video"));
				userDocument.setContent(rs.getString("content"));
				userDocument.setContentLinkUrl(rs.getString("content_link_url"));
				userDocument.setCoverImageUrl(rs.getString("cover_image_url"));
				userDocument.setCreatedAt(rs.getDate("created_at"));
				userDocument.setCreatedById(rs.getLong("created_by_id"));
				userDocument.setOverallRating(rs.getInt("overall_rating"));
				userDocument.setTitle(rs.getString("title"));
				userDocument.setVideoLink(rs.getString("video_link"));
				
				return userDocument;
			}
		});
	}
	
	public List<UserDocument> findUserDocumentsListByCategoryIdSubCategoryIdContainsVideoTitle(Map<Long,List<ProfileCategory>> myAccountCategories,Boolean containsVideo,String title) {
		
		StringBuffer sb = new StringBuffer();
		
		for (Entry<Long,List<ProfileCategory>> myAccountCatSet : myAccountCategories.entrySet()) {
			for (ProfileCategory subCategory : myAccountCatSet.getValue()) {
				sb.append("(category_id = "+myAccountCatSet.getKey()+" and sub_category_id = "+subCategory.getProfileCategoryId()+")");
				sb.append(" or ");
			}
		}
		
		String catCustomQry = sb.toString().substring(0,sb.toString().lastIndexOf(" or "));
		
		String titleQry = "";
		if (title != null && title.length() > 0) {
			titleQry = " and title like '"+title+"%' ";
		}
		
		String sql = "SELECT * from user_documents where contains_video = "+containsVideo+" and  "+catCustomQry+" "+titleQry+" order by user_document_id desc";
		System.out.println("Content sql : "+sql);
		return jdbcTemplate.query(sql, new RowMapper<UserDocument>() {
			@Override
			public UserDocument mapRow(ResultSet rs, int rownumber) throws SQLException {
				UserDocument userDocument = new UserDocument();
				userDocument.setUserDocumentId(rs.getLong("user_document_id"));
				userDocument.setCategoryId(rs.getLong("category_id"));
				userDocument.setSubCategoryId(rs.getLong("sub_category_id"));
				userDocument.setContainsVideo(rs.getBoolean("contains_video"));
				userDocument.setContent(rs.getString("content"));
				userDocument.setContentLinkUrl(rs.getString("content_link_url"));
				userDocument.setCoverImageUrl(rs.getString("cover_image_url"));
				userDocument.setCreatedAt(rs.getDate("created_at"));
				userDocument.setCreatedById(rs.getLong("created_by_id"));
				userDocument.setOverallRating(rs.getInt("overall_rating"));
				userDocument.setTitle(rs.getString("title"));
				userDocument.setVideoLink(rs.getString("video_link"));
				
				return userDocument;
			}
		});
	}
	
}
