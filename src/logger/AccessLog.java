package logger;

import java.io.*;
import java.util.Date;

/**
 * Created by IntelliJ IDEA. User: prasannas Date: Jun 6, 2003 Time: 1:30:50 PM
 * To change this template use Options | File Templates.
 */

public class AccessLog {
	private FileOutputStream fp = null;
	private static AccessLog singleton = null;
	public static String appRealPath;
	private static String VERSION = " :ver1: ";

	public static void Log(Exception e) {
		if (singleton == null) {
			singleton = new AccessLog();
		}
		if (singleton == null || singleton.fp == null) {
			System.err.println("Could not write to Log: ");
			e.printStackTrace();
			return;
		}
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(bos));
			Date dt = new Date();
			DataOutputStream dos = new DataOutputStream(singleton.fp);
			String tempStr = dt + VERSION + bos.toString() + "\r\n";
			dos.writeBytes(tempStr);
		} catch (IOException ioe) {
			System.err.println("Could not write to Log: ");
			e.printStackTrace();
		}
	}// method ends

	public static void Log(String str) {
		System.out.println("ABM: " + str);
		if (singleton == null) {
			singleton = new AccessLog();
		}
		if (singleton == null || singleton.fp == null) {
			System.err.println("Could not write to Log: " + str);
			return;
		}
		try {
			Date dt = new Date();
			DataOutputStream dos = new DataOutputStream(singleton.fp);
			String tempStr = dt + VERSION + str + "\r\n";
			dos.writeBytes(tempStr);
			dos.flush();

		} catch (IOException ioe) {
			System.err.println("Could not write to Log: " + str);
		}

	}// method ends

	private AccessLog() {
		// String filename = appRealPath+"log/error_log.log";
		String filename = appRealPath + "log/error_log.txt";
		try {
			fp = new FileOutputStream(filename, true);
		} catch (IOException ioe) {
			System.err.println("Could not create log file: " + filename);
			ioe.printStackTrace();
		}
	}
}
