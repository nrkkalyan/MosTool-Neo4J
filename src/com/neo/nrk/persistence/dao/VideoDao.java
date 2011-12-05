package com.neo.nrk.persistence.dao;

import java.util.List;

import com.neo.nrk.persistence.entity.Video;


public interface VideoDao  extends BaseEntityDao<Video> {
	List<Video> findVideosBySurveyId(int fk_survey_id) throws Exception;
}
