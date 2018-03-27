package com.accounting.repository;
import org.springframework.data.repository.CrudRepository;

import com.accounting.bo.UserToken;

public interface UserTokenRepository extends CrudRepository<UserToken,Long>{

}
