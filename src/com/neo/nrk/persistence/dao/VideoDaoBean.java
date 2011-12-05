package com.neo.nrk.persistence.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.neo.nrk.persistence.entity.Video;

public class VideoDaoBean extends BaseEntityDaoBean<Video> implements VideoDao {

	private final String TABLE_NAME = " VIDEO_TABLE ";
	private final String COLUMNS = " ID ,FILENAME , URL , FK_SURVEY_ID ";
	private final String SELECT_QUERY = " SELECT " + COLUMNS + " FROM " + TABLE_NAME;
	private final String INSERT_QUERY = " INSERT INTO " + TABLE_NAME + "(FILENAME , URL, FK_SURVEY_ID) VALUES (?,?,?)";

	@Override
	public Video createEntity(Video entity) throws Exception {
		return super.createEntity(entity);
	}

	@Override
	public Video findById(int id) throws Exception {
		String findQuery = SELECT_QUERY + " WHERE ID = ?";
		return super.executeQuery(findQuery, id);
	}

	@Override
	protected Video createManagedEntity() {
		return new Video();
	}

	@Override
	protected int assign(Video baseEntity, PreparedStatement preparedStatement) throws Exception {
		int i = 1;
		preparedStatement.setString(i++, baseEntity.getFileName());
		preparedStatement.setString(i++, baseEntity.getUrl());
		preparedStatement.setInt(i++, baseEntity.getFk_survey_id());
		return i;
	}

	@Override
	protected Video assign(Video baseEntity, ResultSet resultSet) throws SQLException {
		baseEntity.setFileName(resultSet.getString("FILENAME"));
		baseEntity.setUrl(resultSet.getString("URL"));
		baseEntity.setFk_survey_id(resultSet.getInt("FK_SURVEY_ID"));
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
	public List<Video> findAllEntries() throws Exception {
		String findQuery = SELECT_QUERY;
		return super.findMultiple(findQuery);
	}

	@Override
	protected String getTableName() {
		return TABLE_NAME;
	}

	@Override
	public Video updateEntity(Video entity) throws Exception {
		return super.updateEntity(entity);
	}

	@Override
	public void deleteEntityById(int id) throws Exception {
		String statement = "DELETE FROM " + TABLE_NAME + " WHERE ID = ?";
		super.delete(statement, id);
	}

	@Override
	public List<Video> findVideosBySurveyId(int fk_survey_id) throws Exception {
		String findQuery = SELECT_QUERY  + " WHERE FK_SURVEY_ID = ?"; ;
		return super.findMultiple(findQuery, fk_survey_id);
	}
}
