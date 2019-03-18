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

						<div class="pull-left">
							<!-- PAGE HEADING TAG - START -->
							<%-- 	<h1 class="title">${title}</h1> --%>
							<!-- PAGE HEADING TAG - END -->
						</div>


					</div>
				</div>
				<div class="clearfix"></div>
				<!-- MAIN CONTENT AREA STARTS -->

				<div class="col-lg-12"></div>



				<div class="col-lg-12">
					<section class="box ">

						<header class="panel_header">
							<h2 class="title pull-left">${title}</h2>

							<div class="actions panel_actions pull-right">
								<a href="${pageContext.request.contextPath}/showSubDetailsList"><button
										type="button" class="btn btn-info">Back</button></a>

							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertSubject"
										method="post" name="form_sample_2" id="form_sample_2"
										onsubmit="return confirm('Do you really want to submit the form?');">

										<ul class="nav nav-tabs">
											<li class="active"><a href="#home" data-toggle="tab">
													<i class="fa fa-home"></i> Register
											</a></li>

										</ul>

										<div class="tab-content">
											<div class="tab-pane fade in active" id="home">
												<div class="form-group">

													<label class="control-label col-sm-2" for="subCode">Subject
														Code <span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="subCode"
															placeholder="Subject Code" name="subCode"
															value="${editSubject.subCode}" required>
													</div>
												</div>

												<div class="form-group">
													<label class="control-label col-sm-2" for="Semester">Semester<span
														class="text-danger">*</span></label>
													<div class="col-sm-6">
														<select id="sem" name="sem" class="form-control" required>


															<c:choose>
																<c:when test="${editSubject.subSem==1}">
																	<option value="1" Selected>I</option>
																	<option value="2">II</option>
																	<option value="3">III</option>
																	<option value="4">IV</option>
																	<option value="5">V</option>
																	<option value="6">VI</option>
																	<option value="7">VII</option>
																	<option value="8">VIII</option>
																	<option value="9">IX</option>
																	<option value="10">X</option>
																</c:when>

																<c:when test="${editSubject.subSem==2}">
																	<option value="1">I</option>
																	<option value="2" Selected>II</option>
																	<option value="3">III</option>
																	<option value="4">IV</option>
																	<option value="5">V</option>
																	<option value="6">VI</option>
																	<option value="7">VII</option>
																	<option value="8">VIII</option>
																	<option value="9">IX</option>
																	<option value="10">X</option>
																</c:when>
																<c:when test="${editSubject.subSem==3}">
																	<option value="1">I</option>
																	<option value="2">II</option>
																	<option value="3" Selected>III</option>
																	<option value="4">IV</option>
																	<option value="5">V</option>
																	<option value="6">VI</option>
																	<option value="7">VII</option>
																	<option value="8">VIII</option>
																	<option value="9">IX</option>
																	<option value="10">X</option>
																</c:when>
																<c:when test="${editSubject.subSem==4}">
																	<option value="1">I</option>
																	<option value="2">II</option>
																	<option value="3">III</option>
																	<option value="4" Selected>IV</option>
																	<option value="5">V</option>
																	<option value="6">VI</option>
																	<option value="7">VII</option>
																	<option value="8">VIII</option>
																	<option value="9">IX</option>
																	<option value="10">X</option>
																</c:when>
																<c:when test="${editSubject.subSem==5}">
																	<option value="1">I</option>
																	<option value="2">II</option>
																	<option value="3">III</option>
																	<option value="4">IV</option>
																	<option value="5" Selected>V</option>
																	<option value="6">VI</option>
																	<option value="7">VII</option>
																	<option value="8">VIII</option>
																	<option value="9">IX</option>
																	<option value="10">X</option>
																</c:when>
																<c:when test="${editSubject.subSem==6}">
																	<option value="1">I</option>
																	<option value="2">II</option>
																	<option value="3">III</option>
																	<option value="4">IV</option>
																	<option value="5">V</option>
																	<option value="6" Selected>VI</option>
																	<option value="7">VII</option>
																	<option value="8">VIII</option>
																	<option value="9">IX</option>
																	<option value="10">X</option>
																</c:when>
																<c:when test="${editSubject.subSem==7}">
																	<option value="1">I</option>
																	<option value="2">II</option>
																	<option value="3">III</option>
																	<option value="4">IV</option>
																	<option value="5">V</option>
																	<option value="6">VI</option>
																	<option value="7" Selected>VII</option>
																	<option value="8">VIII</option>
																	<option value="9">IX</option>
																	<option value="10">X</option>
																</c:when>
																<c:when test="${editSubject.subSem==8}">
																	<option value="1">I</option>
																	<option value="2">II</option>
																	<option value="3">III</option>
																	<option value="4">IV</option>
																	<option value="5">V</option>
																	<option value="6">VI</option>
																	<option value="7">VII</option>
																	<option value="8" Selected>VIII</option>
																	<option value="9">IX</option>
																	<option value="10">X</option>
																</c:when>
																<c:when test="${editSubject.subSem==9}">
																	<option value="1">I</option>
																	<option value="2">II</option>
																	<option value="3">III</option>
																	<option value="4">IV</option>
																	<option value="5">V</option>
																	<option value="6">VI</option>
																	<option value="7">VII</option>
																	<option value="8">VIII</option>
																	<option value="9" Selected>IX</option>
																	<option value="10">X</option>
																</c:when>
																<c:when test="${editSubject.subSem==10}">
																	<option value="1">I</option>
																	<option value="2">II</option>
																	<option value="3">III</option>
																	<option value="4">IV</option>
																	<option value="5">V</option>
																	<option value="6">VI</option>
																	<option value="7">VII</option>
																	<option value="8">VIII</option>
																	<option value="9">IX</option>
																	<option value="10" Selected>X</option>
																</c:when>

																<c:otherwise>
																	<option value="1">I</option>
																	<option value="2">II</option>
																	<option value="3">III</option>
																	<option value="4">IV</option>
																	<option value="5">V</option>
																	<option value="6">VI</option>
																	<option value="7">VII</option>
																	<option value="8">VIII</option>
																	<option value="9">IX</option>
																	<option value="10">X</option>
																</c:otherwise>
															</c:choose>


														</select>
													</div>
												</div>


												<div class="form-group">

													<label class="control-label col-sm-2" for="subName">Subject
														Name <span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="subName"
															placeholder="Subject Name" name="subName"
															value="${editSubject.subName}" required>
													</div>
												</div>


												<div class="form-group">
													<label class="control-label col-sm-2" for="page_name">Subject
														Type<span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">


														<select id="subType" name="subType" class="form-control"
															onchange="showForm()" required>
															<c:choose>
																<c:when test="${editSubject.subType==0}">

																	<option value="0" Selected>Regular</option>
																	<option value="1">Elective</option>
																	<option value="2">Other</option>
																</c:when>

																<c:when test="${editSubject.subType==1}">
																	<option value="0">Regular</option>
																	<option value="1" Selected>Elective</option>
																	<option value="2">Other</option>
																</c:when>
																<c:otherwise>

																	<option value="0">Regular</option>
																	<option value="1">Elective</option>
																	<option value="2">Other</option>
																</c:otherwise>
															</c:choose>

														</select>
													</div>
												</div>


												<div class="form-group">
													<label class="control-label col-sm-2" for="page_name">Select
														Program <span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<select id="programId" name="programId"
															class="form-control" required>
															<c:forEach items="${proList}" var="program">


																<c:choose>
																	<c:when test="${editSubject.progId==program.programId}">
																		<option value="${program.programId}" selected>${program.nameOfProgram}</option>
																	</c:when>
																	<c:otherwise>
																		<option value="${program.programId}">${program.nameOfProgram}</option>
																	</c:otherwise>
																</c:choose>



															</c:forEach>

														</select>
													</div>
												</div>




												<div class="form-group">
													<label class="control-label col-sm-2" for="isCbse">Is
														CBSE<span class="text-danger">*</span>
													</label>


													<c:choose>
														<c:when test="${editSubject.subIsCbse==0}">
															<div class="col-md-2">

																<input type="radio" name="isCbse" id="isCbse" value="0"
																	checked>Yes

															</div>

															<div class="col-md-2">
																<input type="radio" name="isCbse" value="1"> No
															</div>
														</c:when>
														<c:when test="${editSubject.subIsCbse==1}">
															<div class="col-md-2">

																<input type="radio" name="isCbse" id="isCbse" value="0">Yes

															</div>

															<div class="col-md-2">
																<input type="radio" name="isCbse" value="1" checked>
																No
															</div>
														</c:when>
														<c:otherwise>

															<div class="col-md-2">

																<input type="radio" name="isCbse" id="isCbse" checked
																	value="0">Yes

															</div>

															<div class="col-md-2">
																<input type="radio" name="isCbse" value="1"> No
															</div>


														</c:otherwise>
													</c:choose>


													<!-- <div class="col-sm-2">
														Yes <input type="radio" name="isCbse" id="isCbse" checked
															value="0"> No<input type="radio" name="isCbse"
															id="isCbse" value="1">
													</div> -->
												</div>
												<div class="form-group">

													<label class="control-label col-sm-2" for="noStudApp">No.
														of Student Appeared <span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="noStudApp"
															placeholder="No. of Student Appeared" name="noStudApp"
															value="${editSubject.subStuAppear}" required>
													</div>
												</div>
												<div class="form-group">

													<label class="control-label col-sm-2" for="pass">Passed
														<span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="pass"
															placeholder="Passed" onchange=" calResult()" name="pass"
															value="${editSubject.subStuPassed}" required>
													</div>
												</div>
												<input type="hidden" id="is_view" name="is_view" value="0">
												<input type="hidden" value="${editSubject.subId}"
													name="subId" id="subId">

												<div class="form-group">

													<label class="control-label col-sm-2" for="page_name">%
														of Result <span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="rslt"
															placeholder="% of Result" name="rslt"
															value="${editSubject.subPassPer}" required>
													</div>
												</div>

												<div class="form-group">
													<div class="col-sm-offset-2 col-sm-10">
														<input type="submit" class="btn btn-primary"
															onclick="submit_f(1)" value="Save"> <input
															type="submit" class="btn btn-primary"
															onclick="submit_f(0)"
															value="Save &
																		Next">
														<button type="reset" class="btn btn-default">Reset</button>
													</div>
												</div>

												<div class="clearfix"></div>
											</div>


										</div>
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

	<div aria-hidden="true" role="dialog" tabindex="-1" id="myModal"
		class="modal fade" style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button aria-hidden="true" data-dismiss="modal" class="close"
						type="button">×</button>
					<h4 class="modal-title">Subject Details</h4>
				</div>
				<div class="modal-body">
					<%-- <form role="form"
						action="${pageContext.request.contextPath}/showModuleForm"
						method="get"> --%>
					<input type="hidden" class="form-control" id="pageId" name="pageId">
					<input type="hidden" class="form-control" id="index" name="index"
						value="0">









					<button type="submit" class="btn btn-primary" onclick="getData()">Submit</button>
					<!-- </form> -->
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		function getData() {
			//alert("hii");
			var i = parseInt(document.getElementById("index").value);

			var sem = document.getElementById("sem").value
			var code = document.getElementById("code").value
			var academicYear = document.getElementById("academicYear").value

			var subTaut = document.getElementById("subTaut").value
			var subType = document.getElementById("subType").value

			var noStud = document.getElementById("noStud").value

			var pass = document.getElementById("pass").value
			var rslt = document.getElementById("rslt").value
			var course = document.getElementById("course").value
			var isCBCS = document.getElementById("consultancy").value
			var t = "-";

			var dataTable = $('#example-1').DataTable();

			dataTable.row.add([ i + 1, academicYear,

			sem, code, subTaut, subType, isCBCS, course, noStud, pass, rslt,

			t ]).draw();

			document.getElementById("index").value = i + 1;

		}

		function calResult() {
			var noStudApp = parseFloat(document.getElementById("noStudApp").value);

			var pass = parseFloat(document.getElementById("pass").value);

			var x = (pass / noStudApp) * 100;
			document.getElementById("rslt").value = x.toFixed(2);

		}
	</script>

	<script type="text/javascript">
		function submit_f(view) {
			//alert(view);
			document.getElementById("is_view").value = view;

		}
	</script>
</body>
</html>
