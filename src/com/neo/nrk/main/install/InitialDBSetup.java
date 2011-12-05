package com.neo.nrk.main.install;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.neo.nrk.mos.MosConstants;
import com.neo.nrk.persistence.dao.AdminDao;
import com.neo.nrk.persistence.dao.AdminDaoBean;
import com.neo.nrk.persistence.dao.GlobalPropertiesDao;
import com.neo.nrk.persistence.dao.GlobalPropertiesDaoBean;
import com.neo.nrk.persistence.entity.Admin;
import com.neo.nrk.persistence.entity.GlobalProperties;

public class InitialDBSetup {

	private static Connection mConnection;
	private final AdminDao adminDao = new AdminDaoBean();
	private final GlobalPropertiesDao globalPropertiesDao = new GlobalPropertiesDaoBean();

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) {
		InitialDBSetup initialDBSetup = new InitialDBSetup();
		try {
			initialDBSetup.testCreateTable();
			System.out.println("Succussfully created the tables..");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void testCreateTable() throws Exception {
		dropAndCreateTables();
		closeConnection();
		insertDefaultValuesToAdminTable();
		insertDefaultValuesToGlobalPropertiesTable();
	}

	private void closeConnection() throws SQLException {
		mConnection.close();
	}

	private Connection getConnection() throws Exception {
		if (mConnection == null) {
			Class.forName(DBConstants.SQLiteDriver);
			mConnection = DriverManager.getConnection(DBConstants.DB_CONNECTION);
		}
		return mConnection;
	}

	private void dropAndCreateTables() throws Exception {
		Statement stat = getConnection().createStatement();
		stat.executeUpdate(DBConstants.ADMIN_TABLE);
		stat.executeUpdate(DBConstants.USER_TABLE);
		stat.executeUpdate(DBConstants.VIDEO_TABLE);
		stat.executeUpdate(DBConstants.VIDEO_GRADING_TABLE);
		stat.executeUpdate(DBConstants.CUSTOM_SCALE_TABLE);
		stat.executeUpdate(DBConstants.BINARY_SCALE_TABLE);
		stat.executeUpdate(DBConstants.GLOBAL_PROPERTIES_TABLE);
		stat.executeUpdate(DBConstants.TIMER_SETTINGS_TABLE);
		stat.executeUpdate(DBConstants.SURVEY_TABLE);

	}

	private void insertDefaultValuesToAdminTable() throws Exception {
		Admin entity = new Admin();
		entity.setUsername("admin");
		entity.setPassword("admin");
		adminDao.createEntity(entity);
	}

	private void insertDefaultValuesToGlobalPropertiesTable() throws Exception {
		GlobalProperties entity1 = new GlobalProperties();
		entity1.setKey(MosConstants.CURRENT_SURVEY_NAME);
		entity1.setValue("Not Present");
		globalPropertiesDao.createEntity(entity1);
	}

}
