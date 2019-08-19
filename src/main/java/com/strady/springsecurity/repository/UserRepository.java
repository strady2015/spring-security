package com.strady.springsecurity.repository;

import com.strady.springsecurity.core.BaseRepository;
import com.strady.springsecurity.domain.User;
import org.springframework.stereotype.Repository;

/**
 * @Author: strady
 * @Date: 2019-08-19
 * @Time: 13:32:50
 * @Description:
 */
@Repository
public interface UserRepository extends BaseRepository<User, String> {

    User findByUserNameAndIsDeletedIsFalse(String userName);
}
