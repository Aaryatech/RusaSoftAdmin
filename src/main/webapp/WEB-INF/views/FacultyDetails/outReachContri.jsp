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
								<a
									href="${pageContext.request.contextPath}/showOutReachContriList"><button
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

												<div>







													<div class="form-group">

														<label class="control-label col-sm-2" for="status">Select
															Level <span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<select id="salutation" name="salutation"
																class="form-control" required>
																<option value="0">BOS</option>
																<option value="1">Faculty</option>
																<option value="2">Academic Council</option>
															</select>
														</div>



													</div>
													<div class="form-group">
														<label class="control-label col-sm-2" for="smallheading">Name
															<span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<input type="text" class="form-control" id="dob"
																name="Grant" placeholder="Name " value="" required>
														</div>


													</div>

													<div class="form-group">

														<label class="control-label col-sm-2" for="smallheading">University
															<span class="text-danger">*</span>
														</label>

														<div class="col-sm-6">
															<input type="text" class="form-control" id="dob"
																name="Grant" placeholder="University" value="" required>
														</div>

													</div>

													<div class="form-group">


														<label class="control-label col-sm-2" for="smallheading">From
															<span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<input type="text" class="form-control" id="dob"
																name="Grant" placeholder="From" value="" required>
														</div>
													</div>

													<div class="form-group">

														<label class="control-label col-sm-2" for="smallheading">To
															<span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<input type="text" class="form-control" id="to" name="to"
																placeholder="To" value="" required>
														</div>



													</div>




													<div class="form-group">


														<label class="control-label col-sm-2" for="smallheading">Examination
															Paper Setting <span class="text-danger">*</span>
														</label>


														<div class="col-sm-2">
															Yes <input type="radio" name="examSetting"
																id="examSetting" checked value="0"> No<input
																type="radio" name="examSetting" id="examSetting"
																value="1">
														</div>
													</div>

													<div class="form-group">

														<label class="control-label col-sm-2" for="smallheading">Answer
															Sheet Evaluation <span class="text-danger">*</span>
														</label>


														<div class="col-sm-2">
															Yes <input type="radio" name="ansEvaluation"
																id="ansEvaluation" checked value="0"> No<input
																type="radio" name="ansEvaluation" id="ansEvaluation"
																value="1">
														</div>
													</div>

													<div class="form-group">

														<label class="control-label col-sm-2" for="smallheading">Answer
															Sheet Moderation <span class="text-danger">*</span>
														</label>


														<div class="col-sm-2">
															Yes <input type="radio" name="ansmod" id="ansmod" checked
																value="0"> No<input type="radio" name="ansmod"
																id="ansmod" value="1">
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


</body>
</html>