/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neo.nrk.main;

import java.io.File;

import javax.swing.JOptionPane;

import com.neo.nrk.main.install.DBConstants;
import com.neo.nrk.main.install.InitialDBSetup;
import com.neo.nrk.mos.manager.CommonUtils;
import com.neo.nrk.ui.admin.AdminLoginScreen;

/**
 * 
 * @author nrkkalyan
 */
public class Main {

	public static void main(String... args) {

		try {
			if (!new File(DBConstants.DB_FILE).exists()) {
				System.out.println("Database file not found. Installing database.");
				InitialDBSetup.main(args);
			}
			CommonUtils.loadSettings();
			AdminLoginScreen mainScreen = new AdminLoginScreen();
			mainScreen.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
			System.exit(0);
		}
	}

}
