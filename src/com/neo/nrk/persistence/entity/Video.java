package com.neo.nrk.persistence.entity;

import java.io.File;

public class Video extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String fileName;
	private String url;
	private int fk_survey_id;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Video [fileName=" + fileName + ", url=" + url
				+ ", fk_survey_id=" + fk_survey_id + "]";
	}

	@Override
	public void validate() {
		// Validate if the filename is provided
		if (fileName == null || fileName.isEmpty()) {
			throw new IllegalArgumentException("fileName is required");
		}

		if (url == null || url.isEmpty()) {
			throw new IllegalArgumentException("URL is required");
		}

		// Validate if the video exists or not.
		File videoFile = new File(url);
		if (!videoFile.exists() || !videoFile.isFile()) {
			throw new IllegalArgumentException("File doesnot exist: " + url);
		}
	}

	public void setFk_survey_id(int fk_survey_id) {
		this.fk_survey_id = fk_survey_id;
	}

	public int getFk_survey_id() {
		return fk_survey_id;
	}
	
	

}
