package com.accounting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accounting.DocumentRating;

@Repository
public interface DocumentRatingRepository extends JpaRepository<DocumentRating, Long>{

}
