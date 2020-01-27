<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.UUID"%>
<%@ page import="java.security.MessageDigest"%>
<%@ page import="java.math.BigInteger"%>

<!DOCTYPE html>
<html class=" ">
<head>

<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<!-- CORE CSS TEMPLATE - END -->
<c:url var="clearSessionAttribute" value="/clearSessionAttribute" />
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class=" " onload="clearSessionAttribute()">
	<!-- START TOPBAR -->
	<jsp:include page="/WEB-INF/views/include/topbar.jsp"></jsp:include>
	<!-- END TOPBAR -->
	<!-- START CONTAINER -->
	<div class="page-container row-fluid container-fluid">

		<!-- SIDEBAR - START -->

		<jsp:include page="/WEB-INF/views/include/left.jsp"></jsp:include>
		<!--  SIDEBAR - END -->
		<!-- START CONTENT -->
		<!-- START CONTENT -->
		<section id="main-content" class=" ">
			<section class="wrapper main-wrapper row" style=''>

				<%-- <div class='col-xs-12'>
					<div class="page-title">

						<div class="pull-left">
							<!-- PAGE HEADING TAG - START -->
							<h1 class="title">${title}</h1>
							<!-- PAGE HEADING TAG - END -->
						</div>


					</div>
				</div> --%>
				<div class="clearfix"></div>
				<!-- MAIN CONTENT AREA STARTS -->



				<div class="col-lg-12">
					<section class="box ">
						<header class="panel_header">
							<h2 class="title pull-left">${title}</h2>
							<div class="actions panel_actions pull-right">
								<c:if test="${addAccess==0}">
									<a href="${pageContext.request.contextPath}/addNewDifAbldStudent"><button
											type="button" class="btn btn-success"><i class="${sessionScope.addIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Add</button></a>
								</c:if>
							</div>
						</header>
						<form
							action="${pageContext.request.contextPath}/deleteTranPlace/0"
							method="get" id="insListForm">
								<%
		UUID uuid = UUID.randomUUID();
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] messageDigest = md.digest(String.valueOf(uuid).getBytes());
		BigInteger number = new BigInteger(1, messageDigest);
		String hashtext = number.toString(16);
		session = request.getSession();
		session.setAttribute("generatedKey", hashtext);
	%>
							<div class="content-body">
								<div class="row">
									<div class="col-xs-12"></div>
									<div class="col-xs-12">
										<table class="table table-striped dt-responsive display"
											id="example-1">
											<thead>
												<tr>
													<th>Sr.No.</th>
													<th>Program Enrolled</th>
													<th>Name of Student</th>
													<th>Gender</th>
													<th>UDID Card No.</th>
													<th>Type of Disability</th>
													<th>% of Disability</th>
													<th>Year of Enrollment</th>
													<th>Action</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${difStudList}" var="studList"
													varStatus="count">
													<tr>
														<td align="center">${count.index+1}</td>
														<td align="left">${studList.programName}-${studList.nameOfProgram}</td>
														<td align="right">${studList.nameOfStud}</td>
														<td align="left">${studList.gender==1 ? 'Male' : studList.gender==0 ? 'Female' : studList.gender==2 ? 'Transgender' : ''}</td>
														<td align="left">${studList.udidCardNo}</td>
														<td align="right">${studList.typeOfDisability}</td>
														<td align="right">${studList.percntOfDisability}</td>
														<td align="right">${studList.yearOfEnrollement}</td>

														<td align="center"><c:if test="${editAccess==0}">
														<a href="${pageContext.request.contextPath}/editDisableStudInfo/${studList.difAbleStudId}">
															<span class="glyphicon glyphicon-edit" title="Edit"
																	data-animate=" animated fadeIn " rel="tooltip"></span></a>&nbsp;&nbsp;&nbsp;&nbsp; </c:if>
															<c:if test="${deleteAccess==0}">
																<a
																	href="${pageContext.request.contextPath}/deleteDisableStudInfo/${studList.difAbleStudId}/<%out.println(hashtext);%>"
																	onClick="return confirm('Are you sure want to delete this record');"
																	rel="tooltip" data-color-class="danger" title="Delete"
																	data-animate=" animated fadeIn " data-toggle="tooltip"
																	data-original-title="Delete  record"><span
																	class="glyphicon glyphicon-remove"></span></a>
															</c:if></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</form>
					</section>
				</div>

				<!-- MAIN CONTENT AREA ENDS -->
			</section>
		</section>
		<!-- END CONTENT -->
	</div>
	
	<!-- END CONTAINER -->
	<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->

	<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
	<script>
		function clearSessionAttribute() {

			$.getJSON('${clearSessionAttribute}', {

				ajax : 'true',

			}, function(data) {

			});

		}
		
	</script>
</body>
</html>
