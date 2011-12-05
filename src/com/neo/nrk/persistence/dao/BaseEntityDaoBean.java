package com.neo.nrk.persistence.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.neo.nrk.main.install.DBConstants;
import com.neo.nrk.persistence.entity.BaseEntity;

public abstract class BaseEntityDaoBean<Entity extends BaseEntity> {

	// Abstract methods to be implemented by the SubClasses
	protected abstract Entity createManagedEntity();

	protected abstract int assign(Entity baseEntity, PreparedStatement preparedStatement) throws Exception;

	protected abstract Entity assign(Entity baseEntity, ResultSet resultSet) throws Exception;

	protected abstract String getInsertStatement();

	protected abstract String getUpdateStatement();

	protected abstract String getTableName();

	/**
	 * @Description :
	 * */
	protected Entity createEntity(Entity entity) throws Exception {
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		try {
			entity.validate();
			String insertQuery = getInsertStatement();
			connection = getConnection();
			preparedStatement = connection.prepareStatement(insertQuery);
			assign(entity, preparedStatement);
			preparedStatement.executeUpdate();
			int id = getMaxId();
			entity.setId(id);
		} catch (Exception e) {
			throw e;
		} finally {
			closeConnection(connection, preparedStatement, null);
		}
		return entity;
	}

	public int getMaxId() throws Exception {
		final String query = "SELECT MAX(ID) FROM " + getTableName();
		Statement statement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		try {
			connection = getConnection();
			statement = connection.createStatement();

			resultSet = statement.executeQuery(query);
			if (resultSet.next()) {
				return resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			closeConnection(connection, statement, resultSet);
		}
		throw new Exception("Could not able to get the maxId");
	}

	protected Entity updateEntity(Entity entity) throws Exception {
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		try {
			entity.validate();
			String updateQuery = getUpdateStatement();
			connection = getConnection();
			preparedStatement = connection.prepareStatement(updateQuery);
			assign(entity, preparedStatement);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			closeConnection(connection, preparedStatement, null);
		}
		return entity;
	}

	protected Entity executeQuery(String statement, Object... parameters) throws Exception {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Entity result = null;
		Connection connection = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(statement);
			assignParameters(preparedStatement, parameters);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				Entity baseEntity = createManagedEntity();
				baseEntity.setId(resultSet.getInt("ID"));
				assign(baseEntity, resultSet);
				result = baseEntity;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			closeConnection(connection, preparedStatement, resultSet);
		}
		return result;
	}

	protected List<Entity> findMultiple(String statement, Object... parameters) throws Exception {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Entity> result = new LinkedList<Entity>();
		Connection connection = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(statement);
			assignParameters(preparedStatement, parameters);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Entity baseEntity = createManagedEntity();
				baseEntity.setId(resultSet.getInt("ID"));
				assign(baseEntity, resultSet);
				result.add(baseEntity);
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			closeConnection(connection, preparedStatement, resultSet);
		}
		return result;
	}

	private void closeConnection(Connection connection, Statement preparedStatement, ResultSet resultSet) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException e) {
			// Ignore
		}
		try {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (SQLException e) {
			// Ignore
		}
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			// Ignore
		}
	}

	protected int delete(String statement, Object... parameters) throws Exception {
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		int result;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(statement);
			assignParameters(preparedStatement, parameters);
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			closeConnection(connection, preparedStatement, null);
		}
		return result;
	}

	private Connection getConnection() throws Exception {
		Class.forName(DBConstants.SQLiteDriver);
		return DriverManager.getConnection(DBConstants.DB_CONNECTION);
	}

	private void assignParameters(PreparedStatement preparedStatement, Object... parameters) throws SQLException {
		int i = 1;
		for (Object parameter : parameters) {
			if (parameter instanceof Long) {
				preparedStatement.setLong(i++, (Long) parameter);
			} else if (parameter instanceof Integer) {
				preparedStatement.setInt(i++, (Integer) parameter);
			} else if (parameter instanceof String) {
				preparedStatement.setString(i++, (String) parameter);
			} else if (parameter instanceof Timestamp) {
				preparedStatement.setTimestamp(i++, (Timestamp) parameter);
			} else if (parameter instanceof Enum<?>) {
				preparedStatement.setInt(i++, ((Enum<?>) parameter).ordinal());
			} else if (parameter instanceof byte[]) {
				preparedStatement.setBytes(i++, (byte[]) parameter);
			} else if (parameter instanceof Date) {
				preparedStatement.setTimestamp(i++, new Timestamp(((Date) parameter).getTime()));
			} else if (parameter == null) {
				preparedStatement.setNull(i++, Types.NULL);
			} else {
				throw new UnsupportedOperationException();
			}
		}
	}

}
