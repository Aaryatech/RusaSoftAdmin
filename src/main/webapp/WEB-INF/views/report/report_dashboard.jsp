<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Institute Reports</title>
</head>
<body>
<form method="post" id="reportForm">

<a href="#" onclick="getProgReport()"><button
										type="button" class="btn btn-info">Programs Report</button></a>
										</form>

<script type="text/javascript">
function getProgReport() {
	var form = document.getElementById("reportForm");
	form.setAttribute("target", "_blank");
	form.setAttribute("method", "post");
	form.action = ("${pageContext.request.contextPath}/showProgReport/");

	form.submit();
}
</script>
</body>
</html>