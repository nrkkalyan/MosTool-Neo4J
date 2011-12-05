package com.neo.nrk.mos.manager;

import com.neo.nrk.mos.MosConstants;
import com.neo.nrk.persistence.dao.BinaryScaleDao;
import com.neo.nrk.persistence.dao.BinaryScaleDaoBean;
import com.neo.nrk.persistence.dao.CustomScaleDao;
import com.neo.nrk.persistence.dao.CustomScaleDaoBean;
import com.neo.nrk.persistence.dao.GlobalPropertiesDao;
import com.neo.nrk.persistence.dao.GlobalPropertiesDaoBean;
import com.neo.nrk.persistence.dao.SurveyDao;
import com.neo.nrk.persistence.dao.SurveyDaoBean;
import com.neo.nrk.persistence.dao.TimerDao;
import com.neo.nrk.persistence.dao.TimerDaoBean;
import com.neo.nrk.persistence.entity.BinaryScale;
import com.neo.nrk.persistence.entity.CustomScale;
import com.neo.nrk.persistence.entity.GlobalProperties;
import com.neo.nrk.persistence.entity.Survey;
import com.neo.nrk.persistence.entity.Timer;
import com.neo.nrk.persistence.entity.Survey.ScaleType;

public class SurveyManager {

	private final SurveyDao mSurveyDao = new SurveyDaoBean();
	private final BinaryScaleDao mBinaryScaleDao = new BinaryScaleDaoBean();
	private final TimerDao mTimerDao = new TimerDaoBean();
	private final CustomScaleDao mCustomScaleDao = new CustomScaleDaoBean();
	private final GlobalPropertiesDao mGlobalPropertiesDao = new GlobalPropertiesDaoBean();

	public SurveyDetails findSurveyByName(String surveyName) {
		SurveyDetails surveyDetails = new SurveyDetails();
		try {

			Survey survey = mSurveyDao.findSurveyByName(surveyName);
			if (survey == null) {
				throw new RuntimeException(
						"No Survey found. Please contact Administrator");
			}
			surveyDetails.setSurvey(survey);
			//
			Timer timer = mTimerDao.findById(survey.getFk_timer_id());
			surveyDetails.setTimer(timer);
			//
			if (survey.getScaleType() == ScaleType.BINARY_SCALE) {
				BinaryScale binaryScale = mBinaryScaleDao.findBySurveyId(survey
						.getId());
				surveyDetails.setBinaryScale(binaryScale);
			}
			//
			if (survey.getScaleType() == ScaleType.CUSTOM_SCALE) {
				CustomScale customScale = mCustomScaleDao.findBySurveyId(survey
						.getId());
				surveyDetails.setCustomScale(customScale);
			}

		} catch (Exception e) {
			throw new RuntimeException("Could not find survey by that name : "
					+ surveyName);
		}

		return surveyDetails;
	}

	public String getCurrentSurveyName() throws Exception {
		GlobalProperties globalProperties = mGlobalPropertiesDao
				.findByKey(MosConstants.CURRENT_SURVEY_NAME);
		if (globalProperties == null || globalProperties.getValue() == null
				|| globalProperties.getValue().isEmpty()) {
			throw new UnsupportedOperationException(
					"Survey not set. Please contact your administrator");
		}
		//
		String currentSurveyName = globalProperties.getValue();
		return currentSurveyName;
	}

}
