package com.accounting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accounting.UserDocument;
import com.accounting.bo.DocumentComment;
import com.accounting.repository.DocumentCommentRepository;

@Service
public class DocumentCommentService {

	@Autowired
	DocumentCommentRepository documentCommentRepository;
	
	public DocumentComment saveDocumentComment(DocumentComment documentComment) {
		DocumentComment savedDocumentComment = documentCommentRepository.save(documentComment);
		return savedDocumentComment;
	}
	
}
