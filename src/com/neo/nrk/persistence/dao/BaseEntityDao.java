package com.neo.nrk.persistence.dao;

import java.util.List;

import com.neo.nrk.persistence.entity.BaseEntity;

public interface BaseEntityDao<Entity extends BaseEntity> {

	Entity createEntity(Entity entity) throws Exception;

	Entity findById(int id) throws Exception;

	List<Entity> findAllEntries() throws Exception;

	Entity updateEntity(Entity entity) throws Exception;

	void deleteEntityById(int id) throws Exception;
}
