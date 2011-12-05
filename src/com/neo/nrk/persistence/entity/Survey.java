package com.neo.nrk.persistence.entity;

import java.io.File;

public class Survey extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum ScaleType {
		HUNDRED_POINTSCALE, FIVE_POINTSCALE, BINARY_SCALE, CUSTOM_SCALE;
	}

	private String surveyName;
	private ScaleType scaleType = ScaleType.HUNDRED_POINTSCALE;
	private String videoLocation;
	private int fk_timer_id;

	public String getSurveyName() {
		return surveyName;
	}

	public void setSurveyName(String surveyName) {
		this.surveyName = surveyName;
	}

	public ScaleType getScaleType() {
		return scaleType;
	}

	public void setScaleType(ScaleType scaleType) {
		this.scaleType = scaleType;
	}

	public String getVideoLocation() {
		return videoLocation;
	}

	public void setVideoLocation(String videoLocation) {
		this.videoLocation = videoLocation;
	}

	public void setFk_timer_id(int fk_timer_id) {
		this.fk_timer_id = fk_timer_id;
	}

	public int getFk_timer_id() {
		return fk_timer_id;
	}

	@Override
	public void validate() {
		// Validate if the videolocation is provided
		if (videoLocation == null || videoLocation.isEmpty()) {
			throw new IllegalArgumentException("VideoLocation name is required");
		}

		if (fk_timer_id <= 0) {
			throw new IllegalArgumentException("Timer is not set.");
		}

		// Validate if the video location exists or not.
		File videoLocationDir = new File(videoLocation);
		if (!videoLocationDir.exists() || !videoLocationDir.isDirectory()) {
			throw new IllegalArgumentException("Please select a valid directory.");
		}

		// Validate if the survey name exists
		if (surveyName == null || surveyName.isEmpty()) {
			throw new IllegalArgumentException("Survey name is required");
		}
	}

	@Override
	public String toString() {
		return "Survey [surveyName=" + surveyName + ", scaleType=" + scaleType + ", videoLocation=" + videoLocation + ", fk_timer_id=" + fk_timer_id + "]";
	}

}
