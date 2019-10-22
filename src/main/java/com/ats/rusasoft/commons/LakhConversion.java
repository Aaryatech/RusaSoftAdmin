package com.ats.rusasoft.commons;

import java.text.DecimalFormat;

public class LakhConversion {

	public static String convertToLakh(String amt) {

		double convertedAmt = 0;
		String a = null;
		DecimalFormat decimalFormat = new DecimalFormat("0.0000");
		try {

			convertedAmt = Float.parseFloat(amt) / 100000;

			a = String.valueOf(decimalFormat.format(convertedAmt));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return a;

	}

}
