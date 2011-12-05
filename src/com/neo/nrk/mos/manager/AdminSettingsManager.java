package com.neo.nrk.mos.manager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
import com.neo.nrk.persistence.dao.VideoDao;
import com.neo.nrk.persistence.dao.VideoDaoBean;
import com.neo.nrk.persistence.entity.BinaryScale;
import com.neo.nrk.persistence.entity.CustomScale;
import com.neo.nrk.persistence.entity.GlobalProperties;
import com.neo.nrk.persistence.entity.Survey;
import com.neo.nrk.persistence.entity.Timer;
import com.neo.nrk.persistence.entity.Video;

public class AdminSettingsManager {

	private final GlobalPropertiesDao mGlobalPropertiesDao = new GlobalPropertiesDaoBean();
	private final SurveyDao mSurveyDao = new SurveyDaoBean();
	private final BinaryScaleDao mBinaryScaleDao = new BinaryScaleDaoBean();
	private final TimerDao mTimerDao = new TimerDaoBean();
	private final VideoDao mVideoDao = new VideoDaoBean();
	private final CustomScaleDao mCustomScaleDao = new CustomScaleDaoBean();
	private final SurveyManager mSurveyManager = new SurveyManager();

	public void saveSettings(Survey survey, Timer timer, BinaryScale binaryScale, CustomScale customScale) throws Exception {

		Survey surveyDetails = mSurveyDao.findSurveyByName(survey.getSurveyName());

		if (surveyDetails == null) {
			mTimerDao.createEntity(timer);
			survey.setFk_timer_id(timer.getId());
			// Create the Survey Entity
			mSurveyDao.createEntity(survey);
			// Saving the Binary scale
			if (binaryScale != null && Survey.ScaleType.BINARY_SCALE == survey.getScaleType()) {
				binaryScale.setFk_survey_id(survey.getId());
				mBinaryScaleDao.createEntity(binaryScale);
			}
			// Saving the Custom Scale
			if (customScale != null && Survey.ScaleType.CUSTOM_SCALE == survey.getScaleType()) {
				customScale.setFk_survey_id(survey.getId());
				mCustomScaleDao.createEntity(customScale);
			}

			final File videoDir = new File(survey.getVideoLocation());

			for (File videoFile : videoDir.listFiles()) {
				if (!videoFile.isDirectory()) {
					String fileName = videoFile.getName();
					int dotPos = fileName.lastIndexOf(".");
					if (dotPos >= 0) {
						String extension = fileName.substring(dotPos);
						if (CommonUtils.supportedVideoFormatsArray.contains(extension.toLowerCase())) {
							Video entity = new Video();
							entity.setFileName(fileName);
							entity.setUrl(videoFile.getAbsolutePath());
							entity.setFk_survey_id(survey.getId());
							mVideoDao.createEntity(entity);
						}
					}
				}
			}

		} else {
			throw new IllegalArgumentException("Survey Name already exists. Please choose a new survey name.");
		}

		GlobalProperties globalProperty = mGlobalPropertiesDao.findByKey(MosConstants.CURRENT_SURVEY_NAME);
		if (globalProperty != null) {
			mGlobalPropertiesDao.deleteEntityById(globalProperty.getId());
		}
		globalProperty = new GlobalProperties();
		globalProperty.setKey(MosConstants.CURRENT_SURVEY_NAME);
		globalProperty.setValue(survey.getSurveyName());
		mGlobalPropertiesDao.createEntity(globalProperty);

	}

	public List<String> findAllSurveys() {
		List<String> surveyNames = new ArrayList<String>();
		try {
			List<Survey> surveyList = mSurveyDao.findAllEntries();
			if (surveyList.isEmpty()) {
				throw new RuntimeException("Could not find survey list");
			}
			for (Survey survey : surveyList) {
				surveyNames.add(survey.getSurveyName());
			}
		} catch (Exception e) {
			throw new RuntimeException("Could not find survey list");
		}

		return surveyNames;
	}

	public void setAsCurrentSurvey(final String surveyName) {

		GlobalProperties globalProperty;
		try {
			globalProperty = mGlobalPropertiesDao.findByKey(MosConstants.CURRENT_SURVEY_NAME);

			if (globalProperty != null) {
				mGlobalPropertiesDao.deleteEntityById(globalProperty.getId());
			}
			globalProperty = new GlobalProperties();
			globalProperty.setKey(MosConstants.CURRENT_SURVEY_NAME);
			globalProperty.setValue(surveyName);
			mGlobalPropertiesDao.createEntity(globalProperty);
		} catch (Exception e) {
			throw new RuntimeException("Could not set survey as current survey");
		}
	}

	public SurveyDetails findSurveyByName(String surveyName) {
		return mSurveyManager.findSurveyByName(surveyName);
	}

	public void deleteSurveyByName(String surveyName) throws Exception {
		Survey survey = mSurveyDao.findSurveyByName(surveyName);
		if (survey != null) {
			mSurveyDao.deleteEntityById(survey.getId());
		}
	}
}
