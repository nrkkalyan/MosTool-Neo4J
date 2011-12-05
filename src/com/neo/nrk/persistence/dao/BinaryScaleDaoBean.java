package com.neo.nrk.persistence.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.neo.nrk.persistence.entity.BinaryScale;

public class BinaryScaleDaoBean extends BaseEntityDaoBean<BinaryScale> implements BinaryScaleDao {
	private final String TABLE_NAME = " BINARY_SCALE_TABLE ";
	private final String COLUMNS = " ID ,POSITIVE_SCALE,NEGATIVE_SCALE,FK_SURVEY_ID  ";
	private final String SELECT_QUERY = " SELECT " + COLUMNS + " FROM " + TABLE_NAME;
	private final String INSERT_QUERY = " INSERT INTO " + TABLE_NAME + "(POSITIVE_SCALE,NEGATIVE_SCALE, FK_SURVEY_ID) VALUES (?,?,?)";
	private final String UPDATE_QUERY = " UPDATE " + TABLE_NAME + "SET POSITIVE_SCALE = ?, NEGATIVE_SCALE = ?, FK_SURVEY_ID = ? WHERE ID = ?";

	@Override
	public BinaryScale createEntity(BinaryScale entity) throws Exception {
		return super.createEntity(entity);
	}

	@Override
	public BinaryScale findById(int id) throws Exception {
		String findQuery = SELECT_QUERY + " WHERE ID = ?";
		return super.executeQuery(findQuery, id);
	}

	@Override
	protected BinaryScale createManagedEntity() {
		return new BinaryScale();
	}

	@Override
	protected int assign(BinaryScale baseEntity, PreparedStatement preparedStatement) throws Exception {
		int i = 1;
		preparedStatement.setString(i++, baseEntity.getPositiveScale());
		preparedStatement.setString(i++, baseEntity.getNegativeScale());
		preparedStatement.setInt(i++, baseEntity.getFk_survey_id());
		return i;
	}

	@Override
	protected BinaryScale assign(BinaryScale baseEntity, ResultSet resultSet) throws Exception {
		baseEntity.setPositiveScale(resultSet.getString("POSITIVE_SCALE"));
		baseEntity.setNegativeScale(resultSet.getString("NEGATIVE_SCALE"));
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
	public List<BinaryScale> findAllEntries() throws Exception {
		String findQuery = SELECT_QUERY;
		return super.findMultiple(findQuery);
	}

	@Override
	protected String getTableName() {
		return TABLE_NAME;
	}

	@Override
	public BinaryScale updateEntity(BinaryScale entity) throws Exception {
		return super.updateEntity(entity);
	}

	@Override
	public BinaryScale findBySurveyId(int surveyId) throws Exception {
		String findQuery = SELECT_QUERY + " WHERE FK_SURVEY_ID = ?";
		return super.executeQuery(findQuery, surveyId);
	}

	@Override
	public void deleteEntityById(int id) throws Exception {
		String statement = "DELETE FROM " + TABLE_NAME + " WHERE ID = ?";
		super.delete(statement, id);
	}
}
