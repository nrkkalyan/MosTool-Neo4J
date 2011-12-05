package com.neo.nrk.persistence.entity;

public class BinaryScale extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String positiveScale;
	private String negativeScale;
	private int fk_survey_id;

	public String getPositiveScale() {
		return positiveScale;
	}

	public void setPositiveScale(String positiveScale) {
		this.positiveScale = positiveScale;
	}

	public String getNegativeScale() {
		return negativeScale;
	}

	public void setNegativeScale(String negativeScale) {
		this.negativeScale = negativeScale;
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

		if (negativeScale == null || negativeScale.isEmpty()) {
			throw new IllegalArgumentException("negativeScale is required");
		}

		if (positiveScale == null || positiveScale.isEmpty()) {
			throw new IllegalArgumentException("positiveScale is required");
		}
	}
}
