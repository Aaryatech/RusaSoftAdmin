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
								<a href="${pageContext.request.contextPath}/showSubDetails"><button
										type="button" class="btn btn-info">Back</button></a>

							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertPublicationDetail"
										method="post" enctype="multipart/form-data"
										name="form_sample_2" id="form_sample_2"
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
														Code Taught <span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="subCode"
															placeholder="Subject Code Taught" name="subCode"
															value="${page.pageName}" required>
													</div>
												</div>

												<div class="form-group">
													<label class="control-label col-sm-2" for="Semester">Semester<span
														class="text-danger">*</span></label>
													<div class="col-sm-6">
														<select id="sem" name="sem" class="form-control"
															onchange="showForm()" required>
															<option value="0">I</option>
															<option value="1">II</option>
															<option value="3">III</option>
															<option value="4">IV</option>

														</select>
													</div>
												</div>


												<div class="form-group">

													<label class="control-label col-sm-2" for="page_name">Subject
														Taught <span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="subTaut"
															placeholder="Subject Taught" name="subTaut"
															value="${page.pageName}" required>
													</div>
												</div>


												<div class="form-group">
													<label class="control-label col-sm-2" for="page_name">Subject
														Type<span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<select id="subType" name="subType" class="form-control"
															onchange="showForm()" required>
															<option value="0">Regular</option>
															<option value="1">Elective</option>
															<option value="3">Other</option>

														</select>
													</div>
												</div>



												<div class="form-group">
													<label class="control-label col-sm-2" for="isCbsc">Is
														CBSE<span class="text-danger">*</span>
													</label>
													<div class="col-sm-2">
														Yes <input type="radio" name="isCbsc" id="isCbsc" checked
															value="0"> No<input type="radio" name="isCbsc"
															id="isCbsc" value="1">
													</div>
												</div>
												<div class="form-group">

													<label class="control-label col-sm-2" for="noStud">No.
														of Student Appeared <span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="noStud"
															placeholder="No. of Student Appeared" name="noStud"
															value="${page.pageName}" required>
													</div>
												</div>
												<div class="form-group">

													<label class="control-label col-sm-2" for="pass">Passed
														<span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="pass"
															placeholder="Passed" onchange=" calResult()" name="pass"
															value="${page.pageName}" required>
													</div>
												</div>


												<div class="form-group">

													<label class="control-label col-sm-2" for="page_name">%
														of Result <span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="rslt"
															placeholder="% of Result" name="rslt"
															value="${page.pageName}" required>
													</div>
												</div>



												<div class="form-group">

													<label class="control-label col-sm-2" for="course">Course
														Outcome <span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="course"
															placeholder="Course Outcome" name="course"
															value="${page.pageName}" required>
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
			var noStud = parseFloat(document.getElementById("noStud").value);

			var pass = parseFloat(document.getElementById("pass").value);

			var x = (pass / noStud) * 100;
			document.getElementById("rslt").value = x;

		}
	</script>





</body>
</html>