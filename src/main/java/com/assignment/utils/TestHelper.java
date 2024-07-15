package com.assignment.utils;

import com.assignment.constants.Constants;

public class TestHelper {

	/**
	 * This method Check for the latitude and longitude between values specified in Constant
	 * @param latitude
	 * @param longitude
	 * @return
	 */
	public static boolean LatituteLongitudeCheckerForUser(double latitude, double longitude) 
	{
		if (latitude >= Constants.LAT_START && latitude <= Constants.LAT_END && longitude >= Constants.LNG_START
				&& longitude <= Constants.LNG_END) 
		{
			return true;
		}
		return false;
	}
	
	
	/**
	 * This method calculates the percentage
	 * @param completedTask
	 * @param totalTask
	 * @return
	 */
	public static double CalculateTaskCompletedPercentage (double completedTask, double totalTask) {
		return (completedTask/totalTask) * 100;
	}
}
