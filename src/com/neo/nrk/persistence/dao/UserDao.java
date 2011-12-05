package com.neo.nrk.persistence.dao;

import com.neo.nrk.persistence.entity.User;

public interface UserDao extends BaseEntityDao<User> {

	String getNextUserName() throws Exception;

}
