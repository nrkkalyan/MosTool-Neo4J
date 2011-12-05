package com.neo.nrk.persistence.dao;

import com.neo.nrk.persistence.entity.GlobalProperties;

public interface GlobalPropertiesDao extends BaseEntityDao<GlobalProperties> {

	GlobalProperties findByKey(String key)
			throws Exception;

	@Override
	GlobalProperties updateEntity(GlobalProperties entity)
			throws Exception;
}
