package com.accounting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accounting.UserDocument;

@Repository
public interface UserDocumentRepository extends JpaRepository<UserDocument, Long>{

}
