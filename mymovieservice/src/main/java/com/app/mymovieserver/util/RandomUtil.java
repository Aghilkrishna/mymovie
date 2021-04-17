package com.app.mymovieserver.util;

import java.util.Random;

import org.springframework.stereotype.Component;

/**
 * @author aghil
 *
 */
@Component
public class RandomUtil {

	private static RandomUtil randomUtil = new RandomUtil();

	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	static Random rnd = new Random();
	private RandomUtil() {}

	public static RandomUtil createInstance() {
		return randomUtil;
	}
	public String getTrackId( ) 
	{
		StringBuilder sb = new StringBuilder( 8 );
		for( int i = 0; i < 8; i++ ) 
			sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
		return sb.toString();
	}

}
