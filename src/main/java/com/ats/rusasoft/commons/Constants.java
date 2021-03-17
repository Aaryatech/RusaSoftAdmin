package com.ats.rusasoft.commons;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;

public class Constants {

	//public static final String url = "http://localhost:8094/"; //10.9.63.3;http://
public static final String url = "http://10.9.63.3:8080/rusasoftapitest/";
	public static final String REPORT_SAVE = "/opt/tomcat/webapps/report/rusa_report.pdf";

	//public static final String REPORT_SAVE = "/home/ubuntu/Downloads/saii.pdf";

	
	//Role Constants to get role Ids
	public static String Princ_Role = "Principal";
	public static String IQAC_Role = "IQAC";
	public static String HOD_Role = "HOD";
	public static String Faculty_Role = "Faculty";
	public static  String Account_Officer_Role="Account Officer";
	public static String Dean_Role = "Dean R & D";
	public static String Librarian_Role = "Director Resource Center (Librarian)";
	public static String Student_Role = "Specific Student Registration and Info";
	public static String Rusa_Role = "Admin-RUSA";
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
	
	
	public static String facHOD = "1";
	public static String facDean = "2";
	public static String facProfessor = "3";
	public static String facAssProf = "4";
	public static String facAssistProf = "5";
	public static String facFaculty = "4,5";
	public static String facAccountant = "6";
	public static String facLibrarian = "7";
	public static String facReader = "8";
	public static String facPrincipal = "9";
	public static String facTrainPlace = "10";
	public static String facExternlAct = "11";
	public static String facIQAC = "12";
	public static String sucess_msg="Record Saved Successfully";
	public static Object updt_msg="Record Updated Successfully";
	public static Object fail_msg="Record Failed to Save";


	

	//Web Api Path url
	
	//public static final String url = "http://132.148.143.124:8080/rusasoftwebapi/";
	//public static final String url="http://exhibition.aaryatechindia.in:3209/rusasoftwebapi/";
	
	//Report Save Url
	//public static final String REPORT_SAVE = "/home/tomcataaryatechi/exhibition.aaryatechindia.in/tomcat-8.0.18/webapps/tempadmin/rusa_report2019.pdf";

	
	


	
}
