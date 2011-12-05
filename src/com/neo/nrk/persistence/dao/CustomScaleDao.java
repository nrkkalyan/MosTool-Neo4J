package com.neo.nrk.persistence.dao;

import com.neo.nrk.persistence.entity.CustomScale;

public interface CustomScaleDao extends BaseEntityDao<CustomScale> {

	CustomScale findBySurveyId(int surveyId) throws Exception;
}
