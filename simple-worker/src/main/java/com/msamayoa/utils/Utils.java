package com.msamayoa.utils;

import org.apache.commons.lang3.Validate;

/**
 * 
 * @author msamayoa
 * Utility class
 */
public final class Utils {
	
	private final static int DEFAULT_PORT = 8080;
	
	public static int getIntParameter(String arg){
		Validate.notNull(arg);
		int port = DEFAULT_PORT;
		String vals[] = arg.split("=");
		if(vals.length>=2){
			port = Integer.parseInt(vals[1]);
		}
		return port;
	}
	
	public static int fromStringToInt(String arg){
		Validate.notNull(arg);
		int port = DEFAULT_PORT;		
		if(arg!=null){
			port = Integer.parseInt(arg);
		}
		return port;
	}
	
}