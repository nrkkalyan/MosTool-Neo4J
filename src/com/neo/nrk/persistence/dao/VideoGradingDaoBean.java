package com.neo.nrk.persistence.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.neo.nrk.persistence.entity.VideoGrading;

public class VideoGradingDaoBean extends BaseEntityDaoBean<VideoGrading> implements VideoGradingDao {

	private final String TABLE_NAME = " VIDEO_GRADING_TABLE ";
	private final String COLUMNS = " ID , FK_USER_ID, FK_VIDEO_ID ,FK_SURVEY_ID , GRADE ";
	private final String SELECT_QUERY = " SELECT " + COLUMNS + " FROM " + TABLE_NAME;
	private final String INSERT_QUERY = " INSERT INTO " + TABLE_NAME + "(FK_USER_ID, FK_VIDEO_ID ,FK_SURVEY_ID , GRADE) VALUES (?,?,?,?)";
	private final String UPDATE_QUERY = " UPDATE " + TABLE_NAME + "SET GRADE =? WHERE ID = ?";

	@Override
	public VideoGrading createEntity(VideoGrading entity) throws Exception {
		return super.createEntity(entity);
	}

	@Override
	public VideoGrading updateEntity(VideoGrading entity) throws Exception {
		return super.updateEntity(entity);
	}

	@Override
	public VideoGrading findById(int id) throws Exception {
		String findQuery = SELECT_QUERY + " WHERE ID = ? order by FK_SURVEY_ID";
		return super.executeQuery(findQuery, id);
	}

	@Override
	protected VideoGrading createManagedEntity() {
		return new VideoGrading();
	}

	@Override
	protected int assign(VideoGrading baseEntity, PreparedStatement preparedStatement) throws Exception {
		int i = 1;
		preparedStatement.setInt(i++, baseEntity.getFk_user_id());
		preparedStatement.setInt(i++, baseEntity.getFk_video_id());
		preparedStatement.setInt(i++, baseEntity.getFk_survey_id());
		preparedStatement.setInt(i++, baseEntity.getGrade());
		return i;
	}

	@Override
	protected VideoGrading assign(VideoGrading baseEntity, ResultSet resultSet) throws Exception {
		//
		baseEntity.setFk_user_id(resultSet.getInt("FK_USER_ID"));
		//
		baseEntity.setFk_video_id(resultSet.getInt("FK_VIDEO_ID"));
		//
		baseEntity.setFk_survey_id(resultSet.getInt("FK_SURVEY_ID"));
		//
		baseEntity.setGrade(resultSet.getInt("GRADE"));
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
	public List<VideoGrading> findAllEntries() throws Exception {
		String findQuery = SELECT_QUERY;
		return super.findMultiple(findQuery);
	}

	@Override
	public List<VideoGrading> findAllByUserId(int fk_userId) throws Exception {
		String findQuery = SELECT_QUERY + " where FK_USER_ID = ?";
		return super.findMultiple(findQuery, fk_userId);
	}

	@Override
	public List<VideoGrading> findAllBySurveyId(int fk_surveyId) throws Exception {
		String findQuery = SELECT_QUERY + " where FK_SURVEY_ID = ?";
		return super.findMultiple(findQuery, fk_surveyId);
	}

	@Override
	public List<VideoGrading> findAllByVideoId(int fk_videoId) throws Exception {
		String findQuery = SELECT_QUERY + " where FK_VIDEO_ID = ?";
		return super.findMultiple(findQuery, fk_videoId);
	}

	@Override
	protected String getTableName() {
		return TABLE_NAME;
	}

	@Override
	public void deleteEntityById(int id) throws Exception {
		String statement = "DELETE FROM " + TABLE_NAME + " WHERE ID = ?";
		super.delete(statement, id);
	}
}
