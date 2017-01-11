package com.msamayoa.utils;

public final class Utils {

	public static int getIntParameter(String arg){
		System.out.println("*********************** args:========>" + arg);
				
		int port = 8080; //default port
		String vals[] = arg.split("=");
		if(vals.length>=2){
			port = Integer.parseInt(vals[1]);
		}
		return port;
	}
	
	public static int fromStringToInt(String arg){
		System.out.println("*********************** args:========>" + arg);				
		int port = 8080; //default port		
		if(arg!=null){
			port = Integer.parseInt(arg);
		}
		return port;
	}
	
}
