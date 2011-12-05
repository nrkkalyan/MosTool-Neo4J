package com.neo.nrk.persistence.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.neo.nrk.persistence.entity.Timer;

public class TimerDaoBean extends BaseEntityDaoBean<Timer> implements TimerDao {

	private final String TABLE_NAME = " TIMER_SETTINGS_TABLE ";
	private final String COLUMNS = " ID ,T1 ,T2 ,T3, TIMERTYPE ";
	private final String SELECT_QUERY = " SELECT " + COLUMNS + " FROM " + TABLE_NAME;
	private final String INSERT_QUERY = " INSERT INTO " + TABLE_NAME + " (T1 ,T2 ,T3, TIMERTYPE ) VALUES (?,?,?,?) ";
	private final String UPDATE_QUERY = " UPDATE " + TABLE_NAME + " SET T1 = ?, T2 = ?, T3 = ?, TIMERTYPE =? WHERE ID = ? ";

	@Override
	public Timer createEntity(Timer entity) throws Exception {
		return super.createEntity(entity);
	}

	@Override
	public Timer findById(int id) throws Exception {
		String findQuery = SELECT_QUERY + " WHERE ID = ?";
		return super.executeQuery(findQuery, id);
	}

	@Override
	protected Timer createManagedEntity() {
		return new Timer();
	}

	@Override
	protected int assign(Timer baseEntity, PreparedStatement preparedStatement) throws Exception {
		int i = 1;
		preparedStatement.setInt(i++, baseEntity.getT1());
		preparedStatement.setInt(i++, baseEntity.getT2());
		preparedStatement.setInt(i++, baseEntity.getT3());
		preparedStatement.setString(i++, baseEntity.getTimerType().name());
		return i;
	}

	@Override
	protected Timer assign(Timer baseEntity, ResultSet resultSet) throws Exception {
		baseEntity.setT1(resultSet.getInt("T1"));
		baseEntity.setT2(resultSet.getInt("T2"));
		baseEntity.setT3(resultSet.getInt("T3"));
		baseEntity.setTimerType(Timer.TimerType.valueOf(resultSet.getString("TIMERTYPE")));
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
	public List<Timer> findAllEntries() throws Exception {
		String findQuery = SELECT_QUERY;
		return super.findMultiple(findQuery);
	}

	@Override
	protected String getTableName() {
		return TABLE_NAME;
	}

	@Override
	public Timer updateEntity(Timer entity) throws Exception {
		return super.updateEntity(entity);
	}

	@Override
	public void deleteEntityById(int id) throws Exception {
		String statement = "DELETE FROM " + TABLE_NAME + " WHERE ID = ?";
		super.delete(statement, id);
	}
}
