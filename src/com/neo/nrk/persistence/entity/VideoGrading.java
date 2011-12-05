package com.neo.nrk.persistence.entity;

public class VideoGrading extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6199995706566737062L;
	private int fk_user_id;
	private int fk_video_id;
	private int fk_survey_id;
	private int grade = -1;

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getGrade() {
		return grade;
	}

	public void setFk_user_id(int fk_user_id) {
		this.fk_user_id = fk_user_id;
	}

	public int getFk_user_id() {
		return fk_user_id;
	}

	public void setFk_video_id(int fk_video_id) {
		this.fk_video_id = fk_video_id;
	}

	public int getFk_video_id() {
		return fk_video_id;
	}

	@Override
	public void validate() {

		if (fk_user_id <= 0) {
			throw new IllegalArgumentException("user is not set.");
		}

		if (fk_video_id <= 0) {
			throw new IllegalArgumentException("Video is not set.");
		}

		if (fk_survey_id <= 0) {
			throw new IllegalArgumentException("Survey is not set.");
		}

	}

	public void setFk_survey_id(int fk_survey_id) {
		this.fk_survey_id = fk_survey_id;
	}

	public int getFk_survey_id() {
		return fk_survey_id;
	}

	@Override
	public String toString() {
		return "VideoGrading [fk_user_id=" + fk_user_id + ", fk_video_id=" + fk_video_id + ", fk_survey_id=" + fk_survey_id + ", grade=" + grade + "]";
	}

}
