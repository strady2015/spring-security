package com.strady.springsecurity.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Author: strady
 * @Date: 2019-08-19
 * @Time: 13:27:17
 * @Description: User实体类
 */
@Data
@Entity
public class User {

    @Id
    private String id;
    private String userName;
    private String nickName;
    private String password;
    private String photoUrl;
    private String email;
    private String mobile;
    private String gender;
    private String salt;
    private Integer status;
    private Boolean isDeleted;
    private Long createTime;
    private Long updateTime;
    private Long lastPasswordChange;
    private String remark;

}
