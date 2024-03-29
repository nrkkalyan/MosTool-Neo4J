package com.neo.nrk.main.install;

public final class DBConstants {

	public static final String SQLiteDriver = "org.sqlite.JDBC";
	public static final String DB_FILE = "mosdatabase.db";
	public static final String DB_CONNECTION = "jdbc:sqlite:" + DB_FILE;
	//
	public static String ADMIN_TABLE = "CREATE TABLE IF NOT EXISTS " + //
			"ADMIN_TABLE " + //
			"( ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + //
			"USERNAME VARCHAR NOT NULL UNIQUE, " + //
			"PASSWORD VARCHAR NOT NULL );";
	//
	public static String VIDEO_TABLE = "CREATE TABLE IF NOT EXISTS " + //
			"VIDEO_TABLE " + //
			"( ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + //
			"FILENAME VARCHAR NOT NULL, " + //
			"URL VARCHAR NOT NULL," + "FK_SURVEY_ID INTEGER NOT NULL," + "CONSTRAINT VIDEO_UNQ UNIQUE (URL, FK_SURVEY_ID) );";
	//
	public static String USER_TABLE = "CREATE TABLE IF NOT EXISTS " + //
			"USER_TABLE " + //
			"(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + //
			"NAME VARCHAR NOT NULL, " + //
			"AGE INTEGER NOT NULL, " + //
			"GENDER VARCHAR NOT NULL," + //
			"SKILL VARCHAR NOT NULL );";
	//
	public static String VIDEO_GRADING_TABLE = "CREATE TABLE IF NOT EXISTS " + //
			"VIDEO_GRADING_TABLE " + //
			"( ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + //
			"FK_USER_ID INTEGER NOT NULL, " + //
			"FK_VIDEO_ID INTEGER NOT NULL, " + //
			"FK_SURVEY_ID INTEGER NOT NULL, " + //
			"GRADE INTEGER NOT NULL, " + //
			"FOREIGN KEY(FK_USER_ID) REFERENCES USER_TABLE(ID), " + //
			"FOREIGN KEY(FK_VIDEO_ID) REFERENCES VIDEO_TABLE(ID), " + //
			"FOREIGN KEY(FK_SURVEY_ID) REFERENCES SURVEY_TABLE(ID), " + //
			"CONSTRAINT VIDEO_GRADING_UNQ UNIQUE (FK_USER_ID, FK_VIDEO_ID, FK_SURVEY_ID) );";
	//
	public static String CUSTOM_SCALE_TABLE = "CREATE TABLE IF NOT EXISTS " + //
			"CUSTOM_SCALE_TABLE (" + //
			"ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , " + //
			"FK_SURVEY_ID INTEGER NOT NULL, " + //
			"MINSCALE VARCHAR NOT NULL , " + //
			"MIDSCALE VARCHAR NOT NULL , " + //
			"MAXSCALE VARCHAR NOT NULL , " + //
			"FOREIGN KEY(FK_SURVEY_ID) REFERENCES SURVEY_TABLE(ID) )";
	//
	public static String BINARY_SCALE_TABLE = "CREATE TABLE IF NOT EXISTS " + //
			"BINARY_SCALE_TABLE (" + //
			"ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , " + //
			"FK_SURVEY_ID INTEGER NOT NULL, " + //
			"POSITIVE_SCALE VARCHAR NOT NULL , " + //
			"NEGATIVE_SCALE VARCHAR NOT NULL," + //
			"FOREIGN KEY(FK_SURVEY_ID) REFERENCES SURVEY_TABLE(ID))";
	//
	public static String GLOBAL_PROPERTIES_TABLE = "CREATE TABLE IF NOT EXISTS " + //
			"GLOBAL_PROPERTIES_TABLE (" + //
			"ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," + //
			"KEY VARCHAR NOT NULL UNIQUE , " + //
			"VALUE VARCHAR NOT NULL)";

	//
	public static String SURVEY_TABLE = "CREATE TABLE IF NOT EXISTS " + //
			"SURVEY_TABLE (" + //
			"ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," + //
			"SURVEYNAME VARCHAR NOT NULL UNIQUE , " + //
			"SCALETYPE VARCHAR NOT NULL , " + //
			"FK_TIMER_ID INTEGER NOT NULL UNIQUE, " + //
			"VIDEOLOCATION VARCHAR NOT NULL," + //
			"FOREIGN KEY(FK_TIMER_ID) REFERENCES TIMER_SETTINGS_TABLE(ID))";
	//
	public static String TIMER_SETTINGS_TABLE = "CREATE TABLE IF NOT EXISTS " + //
			"TIMER_SETTINGS_TABLE  (" + //
			"ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + // \
			"TIMERTYPE VARCHAR NOT NULL," + //
			"T1 INTEGER, " + //
			"T2 INTEGER NOT NULL, " + //
			"T3 INTEGER NOT NULL)"; //

}
