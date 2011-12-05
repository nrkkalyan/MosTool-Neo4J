package com.neo.nrk.mos.manager;

import java.util.List;

import com.neo.nrk.persistence.dao.UserDao;
import com.neo.nrk.persistence.dao.UserDaoBean;
import com.neo.nrk.persistence.dao.VideoDao;
import com.neo.nrk.persistence.dao.VideoDaoBean;
import com.neo.nrk.persistence.dao.VideoGradingDao;
import com.neo.nrk.persistence.dao.VideoGradingDaoBean;
import com.neo.nrk.persistence.entity.User;
import com.neo.nrk.persistence.entity.Video;
import com.neo.nrk.persistence.entity.VideoGrading;

public class UserDetailsManager {

	private final UserDao mUserDao = new UserDaoBean();
	private final SurveyManager mSurveyManager = new SurveyManager();
	private final VideoDao mVideoDao = new VideoDaoBean();
	private final VideoGradingDao mVideoGradingDao = new VideoGradingDaoBean();

	public void saveUserDetails(User entity) throws Exception {
		entity.setName(mUserDao.getNextUserName());
		mUserDao.createEntity(entity);
	}

	public SurveyDetails getCurrentSurveyDetails() throws Exception {
		String currentSurveyName = mSurveyManager.getCurrentSurveyName();
		return mSurveyManager.findSurveyByName(currentSurveyName);
	}

	public List<Video> getVideoFiles(int surveyId) throws Exception {
		List<Video> videoList = mVideoDao.findVideosBySurveyId(surveyId);
		if (videoList.isEmpty()) {
			throw new IllegalArgumentException("No Videos are available for survey. Please contact Administrator.");
		}
		return videoList;
	}

	public void gradeVideo(Video currentVideo, SurveyDetails currentSurveyDetails, User user, int grade) throws Exception {
		VideoGrading entity = new VideoGrading();
		entity.setFk_survey_id(currentSurveyDetails.getSurvey().getId());
		entity.setFk_user_id(user.getId());
		entity.setFk_video_id(currentVideo.getId());
		entity.setGrade(grade);

		System.out.println("******************************");
		System.out.println("Saving Survey Grading");
		System.out.println("Video " + currentVideo.getFileName());
		System.out.println("User " + user.getId());
		mVideoGradingDao.createEntity(entity);
		System.out.println("VideoGrading Done :" + entity);
		System.out.println("******************************");

	}

}
