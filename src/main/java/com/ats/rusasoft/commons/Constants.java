package com.ats.rusasoft.commons;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;

public class Constants {

	//Role Constants to get role Ids
	public static String Princ_Role = "Principal";
	public static String IQAC_Role = "IQAC";
	public static String HOD_Role = "HOD";
	public static String Faculty_Role = "Faculty";
	public static  String Account_Officer_Role="Account Officer";
	public static String Dean_Role = "Dean";
	public static String Librarian_Role = "Librarian";
	public static String Student_Role = "Student";
	public static String Rusa_Role = "Rusa";
	public static final String TPO_Role = "TPO";
	public static final String EAO_Role = "Extension Activity Officer";

	public static Font headFontData = new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);
	public static Font tableHeaderFont = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
	public static final BaseColor tableHeaderFontBaseColor=BaseColor.WHITE;
	public static final BaseColor baseColorTableHeader=BaseColor.BLUE;

	public static final Font reportNameFont = new Font(FontFamily.TIMES_ROMAN, 14.0f, Font.UNDERLINE, BaseColor.BLACK);

	
	public static float marginLeft=50;
	public static float marginRight=45;
	public static float marginTop=50;
	public static float marginBottom=80;
	

	//Web Api Path url
	public static final String url = "http://localhost:8099/";
	//public static final String url = "http://132.148.143.124:8080/rusasoftwebapi/";
	//public static final String url="http://exhibition.aaryatechindia.in:3209/rusasoftwebapi2/";
	
	//Report Save Url
	//public static final String REPORT_SAVE = "/home/tomcataaryatechi/exhibition.aaryatechindia.in/tomcat-8.0.18/webapps/tempadmin/rusa_report2019.pdf";
	public static final String REPORT_SAVE = "/home/ats-11/rusa_report2019.pdf";



	
}
