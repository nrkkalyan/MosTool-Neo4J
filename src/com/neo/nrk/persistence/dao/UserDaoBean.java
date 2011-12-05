package com.neo.nrk.persistence.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.neo.nrk.persistence.entity.User;

public class UserDaoBean extends BaseEntityDaoBean<User> implements UserDao {

	private final String TABLE_NAME = " USER_TABLE ";
	private final String COLUMNS = " ID ,NAME , AGE  , GENDER, SKILL  ";
	private final String SELECT_QUERY = " SELECT " + COLUMNS + " FROM " + TABLE_NAME;
	private final String INSERT_QUERY = " INSERT INTO " + TABLE_NAME + "(NAME , AGE  , GENDER, SKILL ) VALUES (?,?,?,?)";

	@Override
	public User createEntity(User entity) throws Exception {
		return super.createEntity(entity);
	}

	@Override
	public User findById(int id) throws Exception {
		String findQuery = SELECT_QUERY + " WHERE ID = ?";
		return super.executeQuery(findQuery, id);
	}

	@Override
	protected User createManagedEntity() {
		return new User();
	}

	@Override
	protected int assign(User baseEntity, PreparedStatement preparedStatement) throws Exception {
		int i = 1;
		preparedStatement.setString(i++, baseEntity.getName());
		preparedStatement.setInt(i++, baseEntity.getAge());
		preparedStatement.setString(i++, baseEntity.getGender().name());
		preparedStatement.setString(i++, baseEntity.getSkill().name());
		return i;
	}

	@Override
	protected User assign(User baseEntity, ResultSet resultSet) throws SQLException {
		baseEntity.setName(resultSet.getString("NAME"));
		baseEntity.setAge(resultSet.getInt("AGE"));
		baseEntity.setGender(User.Gender.valueOf(resultSet.getString("GENDER")));
		baseEntity.setSkill(User.Skill.valueOf(resultSet.getString("SKILL")));
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
	public List<User> findAllEntries() throws Exception {
		String findQuery = SELECT_QUERY;
		return super.findMultiple(findQuery);
	}

	@Override
	protected String getTableName() {
		return TABLE_NAME;
	}

	@Override
	public User updateEntity(User entity) throws Exception {
		return super.updateEntity(entity);
	}

	@Override
	public void deleteEntityById(int id) throws Exception {
		String statement = "DELETE FROM " + TABLE_NAME + " WHERE ID = ?";
		super.delete(statement, id);
	}

	@Override
	public String getNextUserName() throws Exception {
		int nextId = super.getMaxId() + 1;
		return "User-" + nextId;
	}
}
