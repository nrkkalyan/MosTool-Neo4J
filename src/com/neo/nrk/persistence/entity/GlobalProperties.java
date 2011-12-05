package com.neo.nrk.persistence.entity;

public class GlobalProperties extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String key;
	private String value;

	public void setKey(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public void validate() {
		if (key == null || key.isEmpty()) {
			throw new IllegalArgumentException("key is required");
		}

		if (value == null || value.isEmpty()) {
			throw new IllegalArgumentException("value is required");
		}
	}
}
