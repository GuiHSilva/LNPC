package me.guiihsilva.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class debug {

	private static Logger logger;

	public static void log(String msg) {
		logger.log(Level.INFO, msg);
	}

}
