package com.neo.nrk.persistence.entity;

public class User extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum Gender {
		M, F;

	}

	public enum Skill {
		NonTechnical, Technical;

	}

	private String name;
	private int age;
	private Gender gender;
	private Skill skill;

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return age;
	}

	@Override
	public void validate() {
		if (age <= 0) {
			throw new IllegalArgumentException("Invalid Age");
		}
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public Skill getSkill() {
		return skill;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + ", gender=" + gender + ", skill=" + skill + "]";
	}

}
