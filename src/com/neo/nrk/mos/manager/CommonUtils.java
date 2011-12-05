package com.neo.nrk.mos.manager;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CommonUtils {

	public static String NATIVE_LIBRARY_SEARCH_PATH = "C:\\VideoLAN\\VLC";
	public static List<String> supportedVideoFormatsArray = new ArrayList<String>();
	public static String reportDir = "c:/temp";
	public static String itutimerT1 = "10";
	public static String itutimerT2 = "10";
	public static String itutimerT3 = "5";

	public static void loadSettings() throws Exception {

		try {
			Properties settingsProperties = new Properties();
			FileInputStream fis = new FileInputStream("settings.properties");
			settingsProperties.load(fis);
			fis.close();
			String vlcplayerLocation = settingsProperties.getProperty("vlcplayerlocation");
			if (vlcplayerLocation == null || vlcplayerLocation.isEmpty() || vlcplayerLocation.endsWith("/")) {
				throw new IllegalArgumentException("vlcplayerlocation missing in the settings file. Note it must not end with /");
			}
			NATIVE_LIBRARY_SEARCH_PATH = vlcplayerLocation;
			if (!new File(NATIVE_LIBRARY_SEARCH_PATH + "/libvlc.dll").exists() || !new File(NATIVE_LIBRARY_SEARCH_PATH + "/libvlccore.dll").exists()) {
				throw new IllegalArgumentException("Vlc Player Not installed properly.");
			}
			// reportDir
			reportDir = settingsProperties.getProperty("reportdir");
			if (reportDir == null || !reportDir.endsWith("/")) {
				throw new IllegalArgumentException("reportDir missing in the settings file and it should end with a /. i.e c:/temp/");
			}
			// itutimerT1
			itutimerT1 = settingsProperties.getProperty("itutimer.T1");
			if (itutimerT1 == null || itutimerT1.equals("0")) {
				throw new IllegalArgumentException("itutimer.T1 missing in the settings file and should be greater than 0");
			}
			// itutimerT2
			itutimerT2 = settingsProperties.getProperty("itutimer.T2");
			if (itutimerT2 == null || itutimerT2.equals("0")) {
				throw new IllegalArgumentException("itutimer.T2 missing in the settings file and should be greater than 0");
			}
			// itutimerT3
			itutimerT3 = settingsProperties.getProperty("itutimer.T3");
			if (itutimerT3 == null || itutimerT3.equals("0")) {
				throw new IllegalArgumentException("itutimer.T3 missing in the settings file and should be greater than 0");
			}
			//
			String supportedVideoFormats = settingsProperties.getProperty("supportedvideoformats");
			if (supportedVideoFormats == null || supportedVideoFormats.isEmpty()) {
				throw new IllegalArgumentException("supportedVideoFormats missing in the settings file.");
			}
			for (String fileFormat : supportedVideoFormats.split(";")) {
				if (fileFormat.contains(".") || fileFormat.isEmpty()) {
					throw new IllegalArgumentException(fileFormat + " is not valid. Note it must not contain a dot(.)");
				}
				supportedVideoFormatsArray.add("." + fileFormat.toLowerCase());
			}

		} catch (Exception e) {
			throw e;
		}
	}
}
