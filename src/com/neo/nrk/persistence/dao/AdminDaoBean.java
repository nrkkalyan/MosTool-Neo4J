package com.neo.nrk.persistence.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.neo.nrk.persistence.entity.Admin;

public class AdminDaoBean extends BaseEntityDaoBean<Admin> implements AdminDao {

	private final String TABLE_NAME = " ADMIN_TABLE ";
	private final String COLUMNS = " ID , USERNAME , PASSWORD  ";
	private final String SELECT_QUERY = " SELECT " + COLUMNS + " FROM " + TABLE_NAME;
	private final String INSERT_QUERY = " INSERT INTO " + TABLE_NAME + "(USERNAME , PASSWORD) VALUES (?,?)";

	@Override
	public Admin createEntity(Admin entity) throws Exception {
		return super.createEntity(entity);
	}

	@Override
	public Admin findById(int id) throws Exception {
		String findQuery = SELECT_QUERY + " WHERE ID = ?";
		return super.executeQuery(findQuery, id);
	}

	@Override
	public Admin authenticate(String userName, char[] password) throws Exception {
		String statement = SELECT_QUERY + " WHERE USERNAME = ? AND PASSWORD =?";
		Admin admin = super.executeQuery(statement, userName, String.valueOf(password));
		if (admin == null) {
			throw new RuntimeException("Invalid userid or password");
		}
		return admin;
	}

	@Override
	protected Admin createManagedEntity() {
		return new Admin();
	}

	@Override
	protected int assign(Admin baseEntity, PreparedStatement preparedStatement) throws Exception {
		int i = 1;
		preparedStatement.setString(i++, baseEntity.getUsername());
		preparedStatement.setString(i++, baseEntity.getPassword());
		return i;
	}

	@Override
	protected Admin assign(Admin baseEntity, ResultSet resultSet) throws SQLException {
		baseEntity.setUsername(resultSet.getString("USERNAME"));
		baseEntity.setPassword(resultSet.getString("PASSWORD"));
		return baseEntity;
	}

	@Override
	protected String getInsertStatement() {
		return INSERT_QUERY;
	}

	@Override
	protected String getUpdateStatement() {
		throw new UnsupportedOperationException("Update is not allowed");
	}

	@Override
	public List<Admin> findAllEntries() throws Exception {
		String findQuery = SELECT_QUERY;
		return super.findMultiple(findQuery);
	}

	@Override
	protected String getTableName() {
		return TABLE_NAME;
	}

	@Override
	public Admin updateEntity(Admin entity) throws Exception {
		return super.updateEntity(entity);
	}

	@Override
	public void deleteEntityById(int id) throws Exception {
		String statement = "DELETE FROM " + TABLE_NAME + " WHERE ID = ?";
		super.delete(statement, id);
	}
}
