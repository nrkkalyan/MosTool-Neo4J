package com.neo.nrk.persistence.dao;

import com.neo.nrk.persistence.entity.BinaryScale;

public interface BinaryScaleDao extends BaseEntityDao<BinaryScale> {
	
	BinaryScale findBySurveyId(int surveyId) throws Exception;

}
