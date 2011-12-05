package com.neo.nrk.persistence.dao;

import com.neo.nrk.persistence.entity.Survey;

public interface SurveyDao extends BaseEntityDao<Survey> {

	Survey findSurveyByName(String surveyName) throws Exception;

}
