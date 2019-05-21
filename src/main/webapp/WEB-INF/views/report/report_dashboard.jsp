<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<!DOCTYPE html>
<html class=" ">
<head>

<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<!-- CORE CSS TEMPLATE - END -->
</head>
<!-- END HEAD -->

<style>
.image-preview-input {
	position: relative;
	overflow: hidden;
	margin: 0px;
	color: #333;
	background-color: #fff;
	border-color: #ccc;
}

.image-preview-input input[type=file] {
	position: absolute;
	top: 0;
	right: 0;
	margin: 0;
	padding: 0;
	font-size: 20px;
	cursor: pointer;
	opacity: 0;
	filter: alpha(opacity = 0);
}

.image-preview-input-title {
	margin-left: 2px;
}
</style>


<!-- BEGIN BODY -->
<body class=" ">
	<!-- START TOPBAR -->
	<c:url value="/getProgramTypeByProgram" var="getProgramTypeByProgram"></c:url>

	<jsp:include page="/WEB-INF/views/include/topbar.jsp"></jsp:include>
	<!-- END TOPBAR -->
	<!-- START CONTAINER -->
	<div class="page-container row-fluid container-fluid">

		<!-- SIDEBAR - START -->

		<jsp:include page="/WEB-INF/views/include/left.jsp"></jsp:include>
		<!--  SIDEBAR - END -->
		<!-- START CONTENT -->
		<section id="main-content" class=" ">
			<section class="wrapper main-wrapper row" style="">

				<div class="col-xs-12">
					<div class="page-title">

						<!-- 	<div class="pull-left">
							PAGE HEADING TAG - START
							<h1 class="title">Add Department</h1>
							PAGE HEADING TAG - END
						</div> -->
					</div>
				</div>
				<div class="clearfix"></div>
				<!-- MAIN CONTENT AREA STARTS -->
				<div class="col-lg-12">
					<section class="box ">
						<header class="panel_header">
							<h2 class="title pull-left">${title}</h2>
							<div class="actions panel_actions pull-right">
								<%-- 	<a href="${pageContext.request.contextPath}/showDeptList"><button
										type="button" class="btn btn-info">Back</button></a> --%>
							</div>
						</header>
						<div class="content-body">

							<div class="row" style="padding-bottom: 0px;">

								<div class="form-group">
									<label class="control-label col-sm-1" for="status">Academic
										Year <span class="text-danger"></span>
									</label>
									<div class="col-sm-2">
										<select id="ac_year" name="ac_year" class="form-control">

											<c:forEach items="${acaYearList}" var="acYear">
												<option value="${acYear.yearId}">${acYear.academicYear}</option>
											</c:forEach>

										</select> <span class="error_form text-danger" id="prog_type_field"
											style="display: none;">Please select program type</span>
									</div>

									<label class="control-label col-sm-1" for="status">Program
										<span class="text-danger"></span>
									</label>
									<div class="col-sm-3">
										<select id="prog_type" name="prog_type" class="form-control"
											onchange="getProgramTypeByProgram()">

											<c:forEach items="${progTypeList}" var="progType">
												<option value="${progType.programId}">${progType.programName}</option>
											</c:forEach>

										</select> <span class="error_form text-danger" id="prog_type_field"
											style="display: none;">Please select program type</span>
									</div>

									<label class="control-label col-sm-1" for="page_order">
										Program Type<span class="text-danger">*</span>
									</label>
									<div class="col-sm-3">

										<select id="prog_name" name="prog_name" class="form-control"
											required>

										</select>

										<%-- <input type="text" class="form-control" id="prog_name"
															value="${trainPlace.programName}" onchange="trim(this)" name="prog_name"
															placeholder="Name of Program" maxlength="100"> --%>
										<span class="error_form text-danger" id="prog_name_field"
											style="display: none;">Please enter program name</span>
									</div>
								</div>
							</div>
							<br />
							<div class="row" style="padding-bottom: 0px;">
								<div class="col-lg-12">
									<form id="reportForm">

										<div class="panel-group primary" id="accordion-2"
											role="tablist" aria-multiselectable="true">

											<!-- Criteria 1 -->
											<div class="panel panel-default">
												<div class="panel-heading" role="tab" id="headingOne2">
													<h4 class="panel-title">
														<a data-toggle="collapse" data-parent="#accordion-2"
															href="#collapseOne-2" aria-expanded="true"
															aria-controls="collapseOne-2"> <i class='fa fa-check'></i>
															Criteria 1 : Curricular Aspects
														</a>
													</h4>
												</div>
												<div id="collapseOne-2" class="panel-collapse collapse "
													role="tabpanel" aria-labelledby="headingOne2">
													<div class="panel-body">
														<div class="col-lg-10">1] No. of Certificate/Diploma
															Programs</div>
														<div class="col-lg-2">
															<a href="#" onclick="getProgReport(0)"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="getProgReport(1)"><i
																class="fa fa-file-pdf-o" style="color: red;"
																aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">2] Percentage(%) of
															Participation in various University Bodies</div>
														<div class="col-lg-2">
															<a href="#" onclick="getReport2(0)"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="getReport2(1)"><i
																class="fa fa-file-pdf-o" style="color: red;"
																aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>

													<div class="panel-body">
														<div class="col-lg-10">3] Percentage(%) of New
															Courses Introduced</div>
														<div class="col-lg-2">
															<a href="#" onclick="getProgReport(0)"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="getProgReport(1)"><i
																class="fa fa-file-pdf-o" style="color: red;"
																aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">4] Percentage(%) of Programs
															with CBCS/ Elective courses</div>
														<div class="col-lg-2">
															<a href="#" onclick="getProgReport(0)"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="getProgReport(1)"><i
																class="fa fa-file-pdf-o" style="color: red;"
																aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">5] Students Enrolled in
															Certi., Diploma or Add-On Programs</div>
														<div class="col-lg-2">
															<a href="#" onclick="getProgReport(0)"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="getProgReport(1)"><i
																class="fa fa-file-pdf-o" style="color: red;"
																aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">6] Value Added Courses</div>
														<div class="col-lg-2">
															<a href="#" onclick="getProgReport(0)"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="getProgReport(1)"><i
																class="fa fa-file-pdf-o" style="color: red;"
																aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">7] Fields Project/Internships
														</div>
														<div class="col-lg-2">
															<a href="#" onclick="getProgReport(0)"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="getProgReport(1)"><i
																class="fa fa-file-pdf-o" style="color: red;"
																aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">8] Feedback Processed</div>
														<div class="col-lg-2">
															<a href="#" onclick="getProgReport(0)"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="getProgReport(1)"><i
																class="fa fa-file-pdf-o" style="color: red;"
																aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">9] Feedback Received from
															Stakeholders</div>
														<div class="col-lg-2">
															<a href="#" onclick="getProgReport(0)"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="getProgReport(1)"><i
																class="fa fa-file-pdf-o" style="color: red;"
																aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
												</div>

											</div>

											<!-- Criteria 1 Ends-->
											<!-- Criteria 2 -->
											<div class="panel panel-default">
												<div class="panel-heading" role="tab" id="headingTwo2">
													<h4 class="panel-title">
														<a class="collapsed" data-toggle="collapse"
															data-parent="#accordion-2" href="#collapseTwo-2"
															aria-expanded="false" aria-controls="collapseTwo-2">
															<i class='fa fa-check'></i>Criteria 2 : Teaching,Learning
															and Evaluation
														</a>
													</h4>
												</div>
												<div id="collapseTwo-2" class="panel-collapse collapse"
													role="tabpanel" aria-labelledby="headingTwo2">
													<div class="panel-body">
														<div class="col-lg-10">1] Average % of Students from
															other States/Countries – Yearwise</div>
														<div class="col-lg-2">
															<a href="#" onclick="hi()"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="hi()"><i class="fa fa-file-pdf-o"
																style="color: red;" aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">2] Average Enrollment
															Percentage</div>
														<div class="col-lg-2">
															<a href="#" onclick="hi()"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="hi()"><i class="fa fa-file-pdf-o"
																style="color: red;" aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">3] Admissions feeds against
															reservation category</div>
														<div class="col-lg-2">
															<a href="#" onclick="hi()"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="hi()"><i class="fa fa-file-pdf-o"
																style="color: red;" aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">4]Student Teacher Ratio</div>
														<div class="col-lg-2">
															<a href="#" onclick="hi()"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="hi()"><i class="fa fa-file-pdf-o"
																style="color: red;" aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">5] Differently abled students
															(Divyanjan)</div>
														<div class="col-lg-2">
															<a href="#" onclick="hi()"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="hi()"><i class="fa fa-file-pdf-o"
																style="color: red;" aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">6] Teachers Using ICT</div>
														<div class="col-lg-2">
															<a href="#" onclick="hi()"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="hi()"><i class="fa fa-file-pdf-o"
																style="color: red;" aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">7] Total no. of Mentors No.
															of Students Assigned</div>
														<div class="col-lg-2">
															<a href="#" onclick="hi()"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="hi()"><i class="fa fa-file-pdf-o"
																style="color: red;" aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">8] Faculty Available against
															sanctioned post</div>
														<div class="col-lg-2">
															<a href="#" onclick="hi()"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="hi()"><i class="fa fa-file-pdf-o"
																style="color: red;" aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">9]Full time teachers from
															other states against sanctioned post</div>
														<div class="col-lg-2">
															<a href="#" onclick="hi()"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="hi()"><i class="fa fa-file-pdf-o"
																style="color: red;" aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>

													<div class="panel-body">
														<div class="col-lg-10">10] Teaching Experience of
															Full Time Teachers (Current Year Data)</div>
														<div class="col-lg-2">
															<a href="#" onclick="hi()"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="hi()"><i class="fa fa-file-pdf-o"
																style="color: red;" aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">11] Students Performance &
															Learning Outcomes</div>
														<div class="col-lg-2">
															<a href="#" onclick="hi()"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="hi()"><i class="fa fa-file-pdf-o"
																style="color: red;" aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">12] Final Year Students
															Successfully Completed the Programme</div>
														<div class="col-lg-2">
															<a href="#" onclick="hi()"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="hi()"><i class="fa fa-file-pdf-o"
																style="color: red;" aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>


												</div>
											</div>

											<!-- Criteria 2 Ends-->
											
											<!-- Criteria 3  -->
											<div class="panel panel-default">
												<div class="panel-heading" role="tab" id="headingOne3">
													<h4 class="panel-title">
														<a data-toggle="collapse" data-parent="#accordion-2"
															href="#collapseOne-3" aria-expanded="true"
															aria-controls="collapseOne-3"> <i class='fa fa-check'></i>
															Criteria 3 : Research, Innovation and Extension
														</a>
													</h4>
												</div>
												<div id="collapseOne-3" class="panel-collapse collapse "
													role="tabpanel" aria-labelledby="headingOne3">
													<div class="panel-body">
														<div class="col-lg-10">1] No of Recognition/Awards</div>
														<div class="col-lg-2">
															<a href="#" onclick="hi()"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="hi()"><i class="fa fa-file-pdf-o"
																style="color: red;" aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">2] Teachers
															Recognition/Awards and Incentives Information</div>
														<div class="col-lg-2">
															<a href="#" onclick="hi()"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="hi()"><i class="fa fa-file-pdf-o"
																style="color: red;" aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">3] Teacher Research Paper/Journal Information</div>
														<div class="col-lg-2">
															<a href="#" onclick="hi()"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="hi()"><i class="fa fa-file-pdf-o"
																style="color: red;" aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">4] Teacher Research Paper/Journal Ratio</div>
														<div class="col-lg-2">
															<a href="#" onclick="hi()"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="hi()"><i class="fa fa-file-pdf-o"
																style="color: red;" aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">5] Research Project Grants</div>
														<div class="col-lg-2">
															<a href="#" onclick="hi()"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="hi()"><i class="fa fa-file-pdf-o"
																style="color: red;" aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">6]No. of Full Time Teachers in the Institute as Research Guide</div>
														<div class="col-lg-2">
															<a href="#" onclick="hi()"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="hi()"><i class="fa fa-file-pdf-o"
																style="color: red;" aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">7] No of Research Project per Teacher</div>
														<div class="col-lg-2">
															<a href="#" onclick="hi()"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="hi()"><i class="fa fa-file-pdf-o"
																style="color: red;" aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">8] Intellectual Property Rights and Industry Institute Initiatives</div>
														<div class="col-lg-2">
															<a href="#" onclick="hi()"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="hi()"><i class="fa fa-file-pdf-o"
																style="color: red;" aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">9] Plagarism and Code of Ethics</div>
														<div class="col-lg-2">
															<a href="#" onclick="hi()"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="hi()"><i class="fa fa-file-pdf-o"
																style="color: red;" aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">10] Ph. D. Awarded Information</div>
														<div class="col-lg-2">
															<a href="#" onclick="hi()"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="hi()"><i class="fa fa-file-pdf-o"
																style="color: red;" aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">11] Book and Chapter Publication by Teachers and Papers in Conference Proceedings</div>
														<div class="col-lg-2">
															<a href="#" onclick="hi()"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="hi()"><i class="fa fa-file-pdf-o"
																style="color: red;" aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">12] No Book and Chapter Publication by Teachers and Papers in Conference Proceedings</div>
														<div class="col-lg-2">
															<a href="#" onclick="hi()"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="hi()"><i class="fa fa-file-pdf-o"
																style="color: red;" aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">13] Award/Recognition for Extension Activity</div>
														<div class="col-lg-2">
															<a href="#" onclick="hi()"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="hi()"><i class="fa fa-file-pdf-o"
																style="color: red;" aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">14] No of Student/Teachers Participation in Extension Activity</div>
														<div class="col-lg-2">
															<a href="#" onclick="hi()"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="hi()"><i class="fa fa-file-pdf-o"
																style="color: red;" aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">15] Linkages Information</div>
														<div class="col-lg-2">
															<a href="#" onclick="hi()"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="hi()"><i class="fa fa-file-pdf-o"
																style="color: red;" aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">16] No of Linkages</div>
														<div class="col-lg-2">
															<a href="#" onclick="hi()"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="hi()"><i class="fa fa-file-pdf-o"
																style="color: red;" aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
														<div class="panel-body">
														<div class="col-lg-10">17] Functional MoU's R&D</div>
														<div class="col-lg-2">
															<a href="#" onclick="hi()"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="hi()"><i class="fa fa-file-pdf-o"
																style="color: red;" aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
												</div>
											</div>
											<!-- Criteria 3 Ends-->
											<!-- Criteria 4  -->

											<div class="panel panel-default">
												<div class="panel-heading" role="tab" id="headingOne4">
													<h4 class="panel-title">
														<a data-toggle="collapse" data-parent="#accordion-2"
															href="#collapseOne-4" aria-expanded="true"
															aria-controls="collapseOne-4"> <i class='fa fa-check'></i>
															Criteria 4 : Infrastructure and Learning Resources
														</a>
													</h4>
												</div>
												<div id="collapseOne-4" class="panel-collapse collapse "
													role="tabpanel" aria-labelledby="headingOne4">
													<div class="panel-body">
														<div class="col-lg-10">1]ICT Enabled Facilities
															 </div>
														<div class="col-lg-2">
															<a href="#" onclick="hi()"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="hi()"><i class="fa fa-file-pdf-o"
																style="color: red;" aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">2]Budget on Infrastructure augmentation</div>
														<div class="col-lg-2">
															<a href="#" onclick="getProgReport(0)"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="getProgReport(1)"><i
																class="fa fa-file-pdf-o" style="color: red;"
																aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">3] Library Automation and ILMS Information</div>
														<div class="col-lg-2">
															<a href="#" onclick="getProgReport(0)"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="getProgReport(1)"><i
																class="fa fa-file-pdf-o" style="color: red;"
																aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">4] Rare Book – manuscripts – special report</div>
														<div class="col-lg-2">
															<a href="#" onclick="getProgReport(0)"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="getProgReport(1)"><i
																class="fa fa-file-pdf-o" style="color: red;"
																aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">5] Availability of Special Facilities in Library</div>
														<div class="col-lg-2">
															<a href="#" onclick="getProgReport(0)"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="getProgReport(1)"><i
																class="fa fa-file-pdf-o" style="color: red;"
																aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">6] Expenditure on Purchase of Books and Journals</div>
														<div class="col-lg-2">
															<a href="#" onclick="getProgReport(0)"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="getProgReport(1)"><i
																class="fa fa-file-pdf-o" style="color: red;"
																aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">7] No of Students and Teachers using Library Per Day</div>
														<div class="col-lg-2">
															<a href="#" onclick="getProgReport(0)"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="getProgReport(1)"><i
																class="fa fa-file-pdf-o" style="color: red;"
																aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">8] Student-Computer Ratio</div>
														<div class="col-lg-2">
															<a href="#" onclick="getProgReport(0)"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="getProgReport(1)"><i
																class="fa fa-file-pdf-o" style="color: red;"
																aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">9] Internet connection Information</div>
														<div class="col-lg-2">
															<a href="#" onclick="getProgReport(0)"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="getProgReport(1)"><i
																class="fa fa-file-pdf-o" style="color: red;"
																aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">10] E-Content Development Facilities</div>
														<div class="col-lg-2">
															<a href="#" onclick="getProgReport(0)"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="getProgReport(1)"><i
																class="fa fa-file-pdf-o" style="color: red;"
																aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">11] Expenditure on Physical & Academic Support Facilities</div>
														<div class="col-lg-2">
															<a href="#" onclick="getProgReport(0)"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="getProgReport(1)"><i
																class="fa fa-file-pdf-o" style="color: red;"
																aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
												</div>
											</div>
								<!-- Criteria 4 Ends-->			
									<!-- Criteria 5  -->			
								
											<div class="panel panel-default">
												<div class="panel-heading" role="tab" id="headingOne5">
													<h4 class="panel-title">
														<a data-toggle="collapse" data-parent="#accordion-2"
															href="#collapseOne-5" aria-expanded="true"
															aria-controls="collapseOne-5"> <i class='fa fa-check'></i>
															Criteria 5 : Student Support and Progression
														</a>
													</h4>
												</div>
												<div id="collapseOne-5" class="panel-collapse collapse "
													role="tabpanel" aria-labelledby="headingOne5">
													
													
													<div class="panel-body">
														<div class="col-lg-10">1] Govt Scholership Scheme</div>
														<div class="col-lg-2">
															<a href="#" onclick="hi()"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="hi()"><i class="fa fa-file-pdf-o"
																style="color: red;" aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">2] Institutional Financial Support besides Govt</div>
														<div class="col-lg-2">
															<a href="#" onclick="getProgReport(0)"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="getProgReport(1)"><i
																class="fa fa-file-pdf-o" style="color: red;"
																aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">3] Capability Enhancement & Development Schemes </div>
														<div class="col-lg-2">
															<a href="#" onclick="getProgReport(0)"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="getProgReport(1)"><i
																class="fa fa-file-pdf-o" style="color: red;"
																aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">4] Students Benifited from VET</div>
														<div class="col-lg-2">
															<a href="#" onclick="getProgReport(0)"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="getProgReport(1)"><i
																class="fa fa-file-pdf-o" style="color: red;"
																aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">5] Redressal of Stud Grievances</div>
														<div class="col-lg-2">
															<a href="#" onclick="getProgReport(0)"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="getProgReport(1)"><i
																class="fa fa-file-pdf-o" style="color: red;"
																aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">6] EStudents Outstanding Performance (Sport and Cultural)</div>
														<div class="col-lg-2">
															<a href="#" onclick="getProgReport(0)"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="getProgReport(1)"><i
																class="fa fa-file-pdf-o" style="color: red;"
																aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">7] Organization of Sports & Cultural Activities - Competitions</div>
														<div class="col-lg-2">
															<a href="#" onclick="getProgReport(0)"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="getProgReport(1)"><i
																class="fa fa-file-pdf-o" style="color: red;"
																aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">8] Avg % of Placement:  (Last Five Years)</div>
														<div class="col-lg-2">
															<a href="#" onclick="getProgReport(0)"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="getProgReport(1)"><i
																class="fa fa-file-pdf-o" style="color: red;"
																aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">9] % of Students Progression ( Higher Education ): (Current YearData)</div>
														<div class="col-lg-2">
															<a href="#" onclick="getProgReport(0)"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="getProgReport(1)"><i
																class="fa fa-file-pdf-o" style="color: red;"
																aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">10] Students qualifying State/National/Internationa Exams  per year</div>
														<div class="col-lg-2">
															<a href="#" onclick="getProgReport(0)"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="getProgReport(1)"><i
																class="fa fa-file-pdf-o" style="color: red;"
																aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">11] Alumni Engagement </div>
														<div class="col-lg-2">
															<a href="#" onclick="getProgReport(0)"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="getProgReport(1)"><i
																class="fa fa-file-pdf-o" style="color: red;"
																aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													
														<div class="panel-body">
														<div class="col-lg-10">12] List of Distinguished Alumni </div>
														<div class="col-lg-2">
															<a href="#" onclick="getProgReport(0)"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="getProgReport(1)"><i
																class="fa fa-file-pdf-o" style="color: red;"
																aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													
													<div class="panel-body">
														<div class="col-lg-10">13] Alumni Association Meeting Details </div>
														<div class="col-lg-2">
															<a href="#" onclick="getProgReport(0)"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="getProgReport(1)"><i
																class="fa fa-file-pdf-o" style="color: red;"
																aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
												</div>
											</div>											
							<!-- Criteria 5 Ends-->		
							<!-- Criteria 6  -->			
								
											<div class="panel panel-default">
												<div class="panel-heading" role="tab" id="headingOne6">
													<h4 class="panel-title">
														<a data-toggle="collapse" data-parent="#accordion-2"
															href="#collapseOne-6" aria-expanded="true"
															aria-controls="collapseOne-6"> <i class='fa fa-check'></i>
															Criteria 6 : Governance,Leadership and Management
														</a>
													</h4>
												</div>
												<div id="collapseOne-6" class="panel-collapse collapse "
													role="tabpanel" aria-labelledby="headingOne6">
													
													
													<div class="panel-body">
														<div class="col-lg-10">1] Institutional Vision & Mission </div>
														<div class="col-lg-2">
															<a href="#" onclick="hi()"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="hi()"><i class="fa fa-file-pdf-o"
																style="color: red;" aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">2] E-Governance & Areas of Operation</div>
														<div class="col-lg-2">
															<a href="#" onclick="getProgReport(0)"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="getProgReport(1)"><i
																class="fa fa-file-pdf-o" style="color: red;"
																aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">3] Financial support to Professional membership/Conference/Workshop </div>
														<div class="col-lg-2">
															<a href="#" onclick="getProgReport(0)"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="getProgReport(1)"><i
																class="fa fa-file-pdf-o" style="color: red;"
																aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">4] No of Faculty Financial support to Professional membership/Conference/Workshop</div>
														<div class="col-lg-2">
															<a href="#" onclick="getProgReport(0)"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="getProgReport(1)"><i
																class="fa fa-file-pdf-o" style="color: red;"
																aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">5] Average No of Training programmes organized for Teachers and non teaching staff (Professional Development, Administrative))</div>
														<div class="col-lg-2">
															<a href="#" onclick="getProgReport(0)"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="getProgReport(1)"><i
																class="fa fa-file-pdf-o" style="color: red;"
																aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">6] Training programmes organized for Teachers (Professional Development,)</div>
														<div class="col-lg-2">
															<a href="#" onclick="getProgReport(0)"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="getProgReport(1)"><i
																class="fa fa-file-pdf-o" style="color: red;"
																aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">7] Training programmes organized for Non Teaching (Administrative)</div>
														<div class="col-lg-2">
															<a href="#" onclick="getProgReport(0)"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="getProgReport(1)"><i
																class="fa fa-file-pdf-o" style="color: red;"
																aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">8] Avg % of Training Program attended by Teachers</div>
														<div class="col-lg-2">
															<a href="#" onclick="getProgReport(0)"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="getProgReport(1)"><i
																class="fa fa-file-pdf-o" style="color: red;"
																aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">9] Funds/Grants Received from Non-Government Organisation, Individuals, Other Agencies (in Cr.)</div>
														<div class="col-lg-2">
															<a href="#" onclick="getProgReport(0)"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="getProgReport(1)"><i
																class="fa fa-file-pdf-o" style="color: red;"
																aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">10] Quality Initiative by IQAC</div>
														<div class="col-lg-2">
															<a href="#" onclick="getProgReport(0)"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="getProgReport(1)"><i
																class="fa fa-file-pdf-o" style="color: red;"
																aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">11] Quality Assurance Initiatives </div>
														<div class="col-lg-2">
															<a href="#" onclick="getProgReport(0)"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="getProgReport(1)"><i
																class="fa fa-file-pdf-o" style="color: red;"
																aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													
													 
												</div>
											</div>											
							<!-- Criteria 6 Ends-->		
							
							<!-- Criteria 7 -->			
								
											<div class="panel panel-default">
												<div class="panel-heading" role="tab" id="headingOne7">
													<h4 class="panel-title">
														<a data-toggle="collapse" data-parent="#accordion-2"
															href="#collapseOne-7" aria-expanded="true"
															aria-controls="collapseOne-7"> <i class='fa fa-check'></i>
															Criteria 7 : Institutional Values and Best Practices
														</a>
													</h4>
												</div>
												<div id="collapseOne-7" class="panel-collapse collapse "
													role="tabpanel" aria-labelledby="headingOne7">
													
													
													<div class="panel-body">
														<div class="col-lg-10">1] Gender Equality Programmes </div>
														<div class="col-lg-2">
															<a href="#" onclick="hi()"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="hi()"><i class="fa fa-file-pdf-o"
																style="color: red;" aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">2] No of Gender Equality Program</div>
														<div class="col-lg-2">
															<a href="#" onclick="getProgReport(0)"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="getProgReport(1)"><i
																class="fa fa-file-pdf-o" style="color: red;"
																aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">3] Gender sensitivity in Providing Facility </div>
														<div class="col-lg-2">
															<a href="#" onclick="getProgReport(0)"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="getProgReport(1)"><i
																class="fa fa-file-pdf-o" style="color: red;"
																aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">4] Alternative Energy Initiative ( Current Data )</div>
														<div class="col-lg-2">
															<a href="#" onclick="getProgReport(0)"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="getProgReport(1)"><i
																class="fa fa-file-pdf-o" style="color: red;"
																aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">5] Power Requirement met through LED Bulbs for Lighting</div>
														<div class="col-lg-2">
															<a href="#" onclick="getProgReport(0)"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="getProgReport(1)"><i
																class="fa fa-file-pdf-o" style="color: red;"
																aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">6] Expenditure on Green Initiatives & Waste Management</div>
														<div class="col-lg-2">
															<a href="#" onclick="getProgReport(0)"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="getProgReport(1)"><i
																class="fa fa-file-pdf-o" style="color: red;"
																aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">7] Initiative to Address Locational Advantages & Disadvantages</div>
														<div class="col-lg-2">
															<a href="#" onclick="getProgReport(0)"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="getProgReport(1)"><i
																class="fa fa-file-pdf-o" style="color: red;"
																aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">8] No of Initiative to Address Locational Advantages & Disadvantages</div>
														<div class="col-lg-2">
															<a href="#" onclick="getProgReport(0)"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="getProgReport(1)"><i
																class="fa fa-file-pdf-o" style="color: red;"
																aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">9] Initiative to Engage & Contribute Local Community</div>
														<div class="col-lg-2">
															<a href="#" onclick="getProgReport(0)"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="getProgReport(1)"><i
																class="fa fa-file-pdf-o" style="color: red;"
																aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">10] Human Values & Professional Ethics</div>
														<div class="col-lg-2">
															<a href="#" onclick="getProgReport(0)"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="getProgReport(1)"><i
																class="fa fa-file-pdf-o" style="color: red;"
																aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">11] Promotion of Universal Values</div>
														<div class="col-lg-2">
															<a href="#" onclick="getProgReport(0)"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="getProgReport(1)"><i
																class="fa fa-file-pdf-o" style="color: red;"
																aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													<div class="panel-body">
														<div class="col-lg-10">12] No of Promotion of Universal Values</div>
														<div class="col-lg-2">
															<a href="#" onclick="getProgReport(0)"><i
																class="fa fa-file-excel-o" style="color: green;"
																aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="#" onclick="getProgReport(1)"><i
																class="fa fa-file-pdf-o" style="color: red;"
																aria-hidden="true"></i>&nbsp;PDF</a>
														</div>
													</div>
													
													 
												</div>
											</div>											
							<!-- Criteria 7 Ends-->			
											
										</div>
										<input type="hidden" id="p" name="p" value="0">
									</form>
								</div>
							</div>
						</div>
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
	<script type="text/javascript">
		//use this function for all reports just get mapping form action name dynamically as like of prm from every report pdf,excel function	
		function getProgReport(prm) {
			if (prm == 1) {
				document.getElementById("p").value = "1";
			}
			var form = document.getElementById("reportForm");

			form.setAttribute("target", "_blank");
			form.setAttribute("method", "post");

			form.action = ("${pageContext.request.contextPath}/showProgReport/");

			form.submit();
			document.getElementById("p").value = "0";
		}

		function getReport2(prm) {
			if (prm == 1) {
				document.getElementById("p").value = "1";
			}
			var form = document.getElementById("reportForm");

			form.setAttribute("target", "_blank");
			form.setAttribute("method", "post");

			form.action = ("${pageContext.request.contextPath}/showFacPartiVarBodies/");

			form.submit();
		}
	</script>

	<script type="text/javascript">
		function getProgramTypeByProgram() {

			var programType = document.getElementById("prog_type").value;
			//alert("programType" + programType);

			var valid = true;

			/* if (programType == null || programType == "") {
				valid = false;
				alert("Please Select Program");
			} */

			$.getJSON('${getProgramTypeByProgram}', {
				programType : programType,
				ajax : 'true',
			},

			function(data) {
				//alert(data);

				var html;
				var len = data.length;
				for (var i = 0; i < len; i++) {

					html += '<option value="' + data[i].programId + '">'
							+ data[i].nameOfProgram + '</option>';

				}
				html += '</option>';

				$('#prog_name').html(html);
				$("#prog_name").trigger("chosen:updated");

			});
			//alert("Hi")
		}
	</script>

</body>
</html>