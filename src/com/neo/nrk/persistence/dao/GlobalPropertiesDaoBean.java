package com.neo.nrk.persistence.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.neo.nrk.persistence.entity.GlobalProperties;

public class GlobalPropertiesDaoBean extends BaseEntityDaoBean<GlobalProperties> implements GlobalPropertiesDao {

	private final String TABLE_NAME = " GLOBAL_PROPERTIES_TABLE ";
	private final String COLUMNS = " ID ,KEY , VALUE  ";
	private final String SELECT_QUERY = " SELECT " + COLUMNS + " FROM " + TABLE_NAME;
	private final String INSERT_QUERY = " INSERT INTO " + TABLE_NAME + "(KEY , VALUE) VALUES (?,?)";
	private final String UPDATE_QUERY = " UPDATE " + TABLE_NAME + "SET VALUE = ?, KEY = ? WHERE ID = ?";

	@Override
	public GlobalProperties createEntity(GlobalProperties entity) throws Exception {
		return super.createEntity(entity);
	}

	@Override
	public GlobalProperties findById(int id) throws Exception {
		String findQuery = SELECT_QUERY + " WHERE ID = ?";
		return super.executeQuery(findQuery, id);
	}

	@Override
	protected GlobalProperties createManagedEntity() {
		return new GlobalProperties();
	}

	@Override
	protected int assign(GlobalProperties baseEntity, PreparedStatement preparedStatement) throws Exception {
		int i = 1;
		preparedStatement.setString(i++, baseEntity.getKey());
		preparedStatement.setString(i++, baseEntity.getValue());
		return i;
	}

	@Override
	protected GlobalProperties assign(GlobalProperties baseEntity, ResultSet resultSet) throws SQLException {
		baseEntity.setKey(resultSet.getString("KEY"));
		baseEntity.setValue(resultSet.getString("VALUE"));
		return baseEntity;
	}

	@Override
	protected String getInsertStatement() {
		return INSERT_QUERY;
	}

	@Override
	protected String getUpdateStatement() {
		return UPDATE_QUERY;
	}

	@Override
	public List<GlobalProperties> findAllEntries() throws Exception {
		String findQuery = SELECT_QUERY;
		return super.findMultiple(findQuery);
	}

	@Override
	protected String getTableName() {
		return TABLE_NAME;
	}

	@Override
	public GlobalProperties findByKey(String key) throws Exception {
		String findQuery = SELECT_QUERY + " WHERE KEY = ?";
		return super.executeQuery(findQuery, key);
	}

	@Override
	public GlobalProperties updateEntity(GlobalProperties entity) throws Exception {
		return super.updateEntity(entity);
	}

	@Override
	public void deleteEntityById(int id) throws Exception {
		String statement = "DELETE FROM " + TABLE_NAME + " WHERE ID = ?";
		super.delete(statement, id);
	}
}
