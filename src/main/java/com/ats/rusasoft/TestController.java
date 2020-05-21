/*
 * package com.ats.rusasoft;
 * 
 * import javax.servlet.http.HttpServletRequest; import
 * javax.servlet.http.HttpServletResponse;
 * 
 * import org.apache.commons.text.StringEscapeUtils; import org.jsoup.Jsoup;
 * import org.jsoup.safety.Whitelist; import
 * org.springframework.context.annotation.Scope; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RequestMethod; import
 * org.springframework.web.bind.annotation.RequestParam; import
 * org.springframework.web.bind.annotation.ResponseBody; import
 * org.springframework.web.servlet.ModelAndView; import
 * org.springframework.web.util.HtmlUtils; import
 * org.springframework.web.util.JavaScriptUtils;
 * 
 * @Controller
 * 
 * @Scope("session") public class TestController {
 * 
 * // JavaScriptUtils ju=new JavaScriptUtils();
 * 
 * @RequestMapping(value = { "/kishore" }, method = RequestMethod.GET) public
 * ModelAndView kishore(HttpServletRequest request, HttpServletResponse
 * response) { ModelAndView model=new ModelAndView("abc"); return model; }
 * 
 * @RequestMapping(value = { "/testJS" }, method = RequestMethod.POST) public
 * ModelAndView testJS(HttpServletRequest request, HttpServletResponse response)
 * {
 * 
 * String inputStr=null; inputStr=request.getParameter("inputStr"); ModelAndView
 * model=new ModelAndView("testJsp");
 * 
 * String str=XssEscapeUtils.jsoupParse(inputStr);
 * 
 * System.err.println("inputStr =" +inputStr);
 * 
 * System.err.println("XssEscapeUtils.jsoupParse(inputStr)=" +str);
 * 
 * String str2=XssEscapeUtils.jsoupParseOutput(str);
 * 
 * System.err.println("XssEscapeUtils.jsoupParseOutput(str)=" +str2);
 * 
 * //String escapedHTML = StringEscapeUtils.escapeHtml4(inputStr);
 * 
 * // System.out.println(escapedHTML); //Browser can now parse this and print
 * 
 * return model; }
 * 
 * public String sendMe(String str) {
 * System.err.println("In sendMe for "+str+"is "
 * +JavaScriptUtils.javaScriptEscape(str)); return
 * JavaScriptUtils.javaScriptEscape(str); }
 * 
 * 
 * public static String html2text(String html) { return
 * Jsoup.parse(html).text(); }
 * 
 * 
 * //a) in all input type text and textarea withoput ckeditor or any html editor
 * in both project public String jsoupParse(String str) { return
 * Jsoup.parse(str).text(); }
 * 
 * //b in all textarea with ckeditor and eny html editor in both project public
 * String jsoupParseClean(String str) { return Jsoup.clean(str,
 * Whitelist.relaxed()); }
 * 
 * package com.ats.rusasoft;
 * 
 * import java.net.URLDecoder; import java.net.URLEncoder;
 * 
 * import org.apache.commons.text.StringEscapeUtils; import org.jsoup.Jsoup;
 * import org.jsoup.safety.Whitelist;
 * 
 * <dependency> <groupId>org.jsoup</groupId> <artifactId>jsoup</artifactId>
 * <version>1.8.3</version> </dependency> <!--
 * https://mvnrepository.com/artifact/org.apache.commons/commons-lang3 -->
 * <dependency> <groupId>org.apache.commons</groupId>
 * <artifactId>commons-text</artifactId> <version>1.4</version> </dependency>
 * public class XssEscapeUtils { // a) in all input type text and textarea
 * without ckeditor or any html editor // in both project public static String
 * jsoupParse(String str) { str = str.replaceAll("\\<.*?\\>", "");
 * str=Jsoup.parse(str).text(); System.err.println("str1 in jsoupParse " +str);
 * 
 * str =URLDecoder.decode(str);
 * 
 * str=StringEscapeUtils.escapeHtml4(str);
 * str=StringEscapeUtils.escapeJava(str);
 * 
 * str=StringEscapeUtils.unescapeHtml4(str);
 * 
 * str=StringEscapeUtils.escapeJava(str);
 * 
 * return str; }
 * 
 * // b) in all textarea with ckeditor or any html editor in both project public
 * static String jsoupParseClean(String str) { return Jsoup.clean(str,
 * Whitelist.relaxed()); }
 * 
 * public static String jsoupParseOutput(String str) {
 * System.err.println("str0 " +str);
 * 
 * // str = URLEncoder.encode(str); // System.err.println("str1 encode " +str);
 * 
 * 
 * 
 * str=StringEscapeUtils.unescapeHtml4(str);
 * System.err.println("str2  unescapeHtml4 " +str);
 * 
 * str=StringEscapeUtils.escapeJava(str);
 * 
 * System.err.println("str3 unescapeJava " +str); return str; }
 * 
 * 
 * public static String jsoupParse(String str) { return Jsoup.parse(str).text();
 * }
 * 
 * public static String escapeHtml4(String html) { return
 * StringEscapeUtils.escapeHtml4(html); }
 * 
 * public static String unescapeHtml4(String html) { return
 * StringEscapeUtils.unescapeHtml4(html); }
 * 
 * }
 * 
 * 
 * 
 * }
 */