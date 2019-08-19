package com.strady.springsecurity.core;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @Author: strady
 * @Date: 2019-08-19
 * @Time: 13:24:27
 * @Description: Repository基类
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
}
