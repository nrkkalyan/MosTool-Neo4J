package com.neo.nrk.persistence.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.neo.nrk.persistence.entity.CustomScale;

public class CustomScaleDaoBean extends BaseEntityDaoBean<CustomScale> implements CustomScaleDao {

	private final String TABLE_NAME = " CUSTOM_SCALE_TABLE ";
	private final String COLUMNS = " ID ,MINSCALE , MIDSCALE, MAXSCALE , FK_SURVEY_ID ";
	private final String SELECT_QUERY = " SELECT " + COLUMNS + " FROM " + TABLE_NAME;
	private final String INSERT_QUERY = " INSERT INTO " + TABLE_NAME + "(MINSCALE , MIDSCALE, MAXSCALE, FK_SURVEY_ID) VALUES (?,?,?,?)";
	private final String UPDATE_QUERY = " UPDATE " + TABLE_NAME + "SET MINSCALE = ?, MIDSCALE = ?, MAXSCALE = ?, FK_SURVEY_ID = ? WHERE ID = ?";

	@Override
	public CustomScale createEntity(CustomScale entity) throws Exception {
		return super.createEntity(entity);
	}

	@Override
	public CustomScale findById(int id) throws Exception {
		String findQuery = SELECT_QUERY + " WHERE ID = ?";
		return super.executeQuery(findQuery, id);
	}

	@Override
	protected CustomScale createManagedEntity() {
		return new CustomScale();
	}

	@Override
	protected int assign(CustomScale baseEntity, PreparedStatement preparedStatement) throws Exception {
		int i = 1;
		preparedStatement.setString(i++, baseEntity.getMinScale());
		preparedStatement.setString(i++, baseEntity.getMidScale());
		preparedStatement.setString(i++, baseEntity.getMaxScale());
		preparedStatement.setInt(i++, baseEntity.getFk_survey_id());
		return i;
	}

	@Override
	protected CustomScale assign(CustomScale baseEntity, ResultSet resultSet) throws Exception {
		baseEntity.setMinScale(resultSet.getString("MINSCALE"));
		baseEntity.setMidScale(resultSet.getString("MIDSCALE"));
		baseEntity.setMaxScale(resultSet.getString("MAXSCALE"));
		baseEntity.setFk_survey_id(resultSet.getInt("FK_SURVEY_ID"));
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
	public List<CustomScale> findAllEntries() throws Exception {
		String findQuery = SELECT_QUERY;
		return super.findMultiple(findQuery);
	}

	@Override
	protected String getTableName() {
		return TABLE_NAME;
	}

	@Override
	public CustomScale updateEntity(CustomScale entity) throws Exception {
		return super.updateEntity(entity);
	}

	@Override
	public CustomScale findBySurveyId(int surveyId) throws Exception {
		String findQuery = SELECT_QUERY + " WHERE FK_SURVEY_ID = ?";
		return super.executeQuery(findQuery, surveyId);
	}

	@Override
	public void deleteEntityById(int id) throws Exception {
		String statement = "DELETE FROM " + TABLE_NAME + " WHERE ID = ?";
		super.delete(statement, id);
	}
}
