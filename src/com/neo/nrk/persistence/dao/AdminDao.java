package com.neo.nrk.persistence.dao;

import com.neo.nrk.persistence.entity.Admin;

public interface AdminDao extends BaseEntityDao<Admin> {

	Admin authenticate(String userName, char[] password) throws Exception;

}
