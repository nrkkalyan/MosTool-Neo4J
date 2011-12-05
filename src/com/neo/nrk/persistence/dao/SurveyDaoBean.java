package com.neo.nrk.persistence.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.neo.nrk.persistence.entity.Survey;

public class SurveyDaoBean extends BaseEntityDaoBean<Survey> implements SurveyDao {

	private final String TABLE_NAME = " SURVEY_TABLE ";
	private final String COLUMNS = " ID, SURVEYNAME , VIDEOLOCATION ,SCALETYPE ,FK_TIMER_ID ";
	private final String SELECT_QUERY = " SELECT " + COLUMNS + " FROM " + TABLE_NAME;
	private final String INSERT_QUERY = " INSERT INTO " + TABLE_NAME + "(SURVEYNAME , VIDEOLOCATION, SCALETYPE ,FK_TIMER_ID) VALUES (?,?,?,?)";
	private final String UPDATE_QUERY = " UPDATE " + TABLE_NAME + "SET SURVEYNAME = ?, VIDEOLOCATION = ?, SCALETYPE = ? , FK_TIMER_ID = ? WHERE ID = ?";

	@Override
	public Survey createEntity(Survey entity) throws Exception {
		return super.createEntity(entity);
	}

	@Override
	public Survey findById(int id) throws Exception {
		String findQuery = SELECT_QUERY + " WHERE ID = ?";
		return super.executeQuery(findQuery, id);
	}

	@Override
	protected Survey createManagedEntity() {
		return new Survey();
	}

	@Override
	protected int assign(Survey baseEntity, PreparedStatement preparedStatement) throws Exception {
		int i = 1;
		preparedStatement.setString(i++, baseEntity.getSurveyName());
		preparedStatement.setString(i++, baseEntity.getVideoLocation());
		preparedStatement.setString(i++, baseEntity.getScaleType().name());
		preparedStatement.setInt(i++, baseEntity.getFk_timer_id());
		return i;
	}

	@Override
	protected Survey assign(Survey baseEntity, ResultSet resultSet) throws Exception {
		baseEntity.setSurveyName(resultSet.getString("SURVEYNAME"));
		baseEntity.setVideoLocation(resultSet.getString("VIDEOLOCATION"));
		baseEntity.setScaleType(Survey.ScaleType.valueOf(resultSet.getString("SCALETYPE")));
		baseEntity.setFk_timer_id(resultSet.getInt("FK_TIMER_ID"));
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
	public List<Survey> findAllEntries() throws Exception {
		String findQuery = SELECT_QUERY;
		return super.findMultiple(findQuery);
	}

	@Override
	protected String getTableName() {
		return TABLE_NAME;
	}

	@Override
	public Survey findSurveyByName(String surveyName) throws Exception {
		String findQuery = SELECT_QUERY + " WHERE SURVEYNAME = ?";
		return super.executeQuery(findQuery, surveyName);
	}

	@Override
	public Survey updateEntity(Survey entity) throws Exception {
		return super.updateEntity(entity);
	}

	@Override
	public void deleteEntityById(int id) throws Exception {
		String statement = "DELETE FROM " + TABLE_NAME + " WHERE ID = ?";
		super.delete(statement, id);
	}
}
