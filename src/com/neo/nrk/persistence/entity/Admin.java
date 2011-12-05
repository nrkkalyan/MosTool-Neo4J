package com.neo.nrk.persistence.entity;

public class Admin extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 652561170569818739L;
	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void validate() {

		if (username == null || username.isEmpty()) {
			throw new IllegalArgumentException("username is required");
		}

		if (password == null || password.isEmpty()) {
			throw new IllegalArgumentException("password is required");
		}
	}

}
