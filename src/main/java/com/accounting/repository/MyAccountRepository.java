package com.accounting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accounting.bo.MyAccount;

@Repository
public interface MyAccountRepository extends JpaRepository<MyAccount, Long>{
	public MyAccount findByCreatedById(Long createdById);
}
