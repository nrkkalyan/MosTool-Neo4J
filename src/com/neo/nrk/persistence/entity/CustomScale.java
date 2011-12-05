package com.neo.nrk.persistence.entity;

public class CustomScale extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String minScale;
	private String midScale;
	private String maxScale;
	private int fk_survey_id;

	public void setMinScale(String minScale) {
		this.minScale = minScale;
	}

	public String getMinScale() {
		return minScale;
	}

	public void setMidScale(String midScale) {
		this.midScale = midScale;
	}

	public String getMidScale() {
		return midScale;
	}

	public void setMaxScale(String maxScale) {
		this.maxScale = maxScale;
	}

	public String getMaxScale() {
		return maxScale;
	}

	public void setFk_survey_id(int fk_survey_id) {
		this.fk_survey_id = fk_survey_id;
	}

	public int getFk_survey_id() {
		return fk_survey_id;
	}

	@Override
	public void validate() {
		if (fk_survey_id <= 0) {
			throw new IllegalArgumentException("Survey is required");
		}

		if (minScale == null || minScale.isEmpty()) {
			throw new IllegalArgumentException("minScale is required");
		}

		if (midScale == null || midScale.isEmpty()) {
			throw new IllegalArgumentException("midScale is required");
		}

		if (maxScale == null || maxScale.isEmpty()) {
			throw new IllegalArgumentException("maxScale is required");
		}
	}

}
