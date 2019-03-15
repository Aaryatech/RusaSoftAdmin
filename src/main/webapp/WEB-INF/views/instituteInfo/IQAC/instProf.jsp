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
								<a href="${pageContext.request.contextPath}/showInstituteList"><button
										type="button" class="btn btn-info">Back</button></a>
								<!-- <a
									class="box_toggle fa fa-chevron-down"></a> -->
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertCmsForm"
										method="post" enctype="multipart/form-data"
										name="form_sample_2" id="form_sample_2"
										onsubmit="return confirm('Do you really want to submit the form?');">

										<ul class="nav nav-tabs">
											<li class="active"><a href="#home" data-toggle="tab">
													<i class="fa fa-home"></i> Register Form
											</a></li>


										</ul>

										<div class="tab-content">
											<div class="tab-pane fade in active" id="home">

												<div>




													<div class="form-group">
														<label class="control-label col-sm-2" for="page_name">Date
															of Establishment of IQAC <span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<input type="text" class="form-control datepicker"
																id="page_name" name="page_name"
																placeholder="Date of Establishment of IQAC "
																value="${page.pageName}">
														</div>
													</div>



													<div class="form-group">
														<label class="control-label col-sm-2" for="page_order">Name
															of alternate facility with IQAC <span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<input type="text" class="form-control" id="page_order"
																name="page_order"
																placeholder="Name of alternate facility with IQAC"
																value="" required>
														</div>
													</div>

													<div class="form-group">
														<label class="control-label col-sm-2" for="page_order">Contact
															No of Alternate Faculty Associated <span
															class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<input type="text" class="form-control" id="page_order"
																name="page_order" placeholder="Contact No" value=""
																required>
														</div>
													</div>

													<div class="form-group">
														<label class="control-label col-sm-2" for="page_order">Phone
															No.<span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<input type="text" class="form-control" id="page_order"
																name="page_order" placeholder="Phone No." value=""
																required>
														</div>
													</div>

													<div class="form-group">
														<label class="control-label col-sm-2" for="page_order">Fax
															No.<span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<input type="text" pattern="^[1-9]{1}[0-9]{9}$"
																maxlength="10" class="form-control" id="page_order"
																name="page_order" placeholder="Fax No." required>
														</div>
													</div>

													<div class="form-group">
														<label class="control-label col-sm-2" for="page_order">
															Registered Email <span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<input type="text" class="form-control" id="page_order"
																name="page_order" placeholder="abc@xyz.com" required>
														</div>
													</div>

													<div class="form-group">
														<label class="control-label col-sm-2" for="page_order">Alternate
															Email :<span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<input type="email" class="form-control" id="page_order"
																name="page_order" placeholder="abc@xyz.com " required>
														</div>
													</div>




													<div class="form-group">
														<div class="col-sm-offset-2 col-sm-9">
															<input type="submit" id="sub1" class="btn btn-primary"
																onclick="submit_f(1)" value="Save"> <input
																type="submit" id="sub2" class="btn btn-primary"
																onclick="submit_f(0)"
																value="Save &
																		Next">
															<button type="reset" class="btn btn-default">Reset</button>
														</div>
													</div>



												</div>
											</div>


											<div class="clearfix"></div>


										</div>
									</form>
								</div>

							</div>

						</div>
					</section>
				</div>

			</section>
		</section>

	</div>
	<!-- MAIN CONTENT AREA ENDS -->

	<!-- END CONTENT -->

	<div aria-hidden="true" role="dialog" tabindex="-1" id="myModal"
		class="modal fade" style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button aria-hidden="true" data-dismiss="modal" class="close"
						type="button">×</button>
					<h4 class="modal-title">Academic Details</h4>
				</div>
				<div class="modal-body">
					<%-- <form role="form"
						action="${pageContext.request.contextPath}/showModuleForm"
						method="get"> --%>
					<input type="hidden" class="form-control" id="pageId" name="pageId">

					<div class="form-group">
						<label class="control-label col-sm-3" for="page_name">Qualification</label>
						<select id="qualType" name="qualType" class="form-control"
							onchange="showForm()" required>
							<option value="Diploma">Diploma</option>
							<option value="Bachelors">Bachelors</option>
							<option value="Masters">Masters</option>
							<option value="Doctorates">Doctorates</option>
							<option value="Post Doctorates">Post Doctorates</option>
							<option value="M.Phill/Ph.D. Guide">M.Phill/Ph.D. Guide</option>

							<option value="7">Any Other</option>



						</select>
					</div>


					<div class="form-group">

						<label class="control-label col-sm-3" for="page_name">Other
							Qualification </label>
						<!-- <div class="col-sm-3"> -->
						<input type="text" class="form-control" id="qualName"
							name="qualName" placeholder="" value="${page.pageName}">
						<!-- </div> -->
					</div>

					<div class="form-group">

						<label class="control-label col-sm-1" for="page_name">Class
						</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" id="className"
								name="className" placeholder="" value="${page.pageName}"
								required>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="page_name">Year
							of Passing </label>
						<div class="col-sm-3">
							<input type="date" class="form-control" id="year" name="year"
								value="" required>
						</div>

					</div>

					<button type="submit" class="btn btn-primary" onclick="getData()">Submit</button>
					<!-- 	</form> -->
				</div>
			</div>
		</div>
	</div>


	<script type="text/javascript">
		$(function() {

			$('.datepicker').datepicker({
				autoclose : true,
				format : "dd-mm-yyyy",
				changeYear : true,
				changeMonth : true

			});
		});
	</script>





	<!-- END CONTAINER -->
	<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->

	<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>



</body>
</html>