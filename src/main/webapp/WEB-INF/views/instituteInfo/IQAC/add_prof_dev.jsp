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
						<%-- 
						<div class="pull-left">
							<!-- PAGE HEADING TAG - START -->
							<h1 class="title">${title}</h1>
							<!-- PAGE HEADING TAG - END -->
						</div> --%>


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
								<a href="${pageContext.request.contextPath}/showProfDevelopment"
									data-toggle="modal"><button type="submit"
										class="btn btn-info">Back</button></a>
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertTeachTraing"
										method="post"
										name="form_sample_2" id="form_sample_2"
										onsubmit="return confirm('Do you really want to submit the form?');">

										<%-- <ul class="nav nav-tabs">
											<li class="active"><a href="#home" data-toggle="tab">
													<i class="fa fa-home"></i> ${title}
											</a></li>

										</ul>
 --%>
										<!-- <div class="tab-content">
											<div class="tab-pane fade in active" id="home"> -->

										<div class="form-group">
											<label class="control-label col-sm-2" for="dev_Prog_title">
												Title<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control" id="dev_Prog_title"
													name="dev_Prog_title" autocomplete="off" onchange="trim(this)"
													placeholder="Title of Professional Development Program for Teaching Staff"
													value="${page.pageName}" required>
											</div>
										</div>

										<div class="form-group">
											<label class="control-label col-sm-2" for="no_of_participant">No.
												of Participants <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="number" min="0" class="form-control" id="no_of_participant"
													autocomplete="off" name="no_of_participant"
													placeholder="Enter No. of Participants" value="${page.pageName}"
													required>
											</div>
										</div>


										<div class="form-group">

											<label class="control-label col-sm-2" for="fromDate">From
												Date <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control datepicker" placeholder="Select From Date"
													autocomplete="off" id="fromDate" name="fromDate"
													value="${page.pageName}" required>
											</div>
										</div>
										<div class="form-group">

											<label class="control-label col-sm-2" for="toDate">To
												Date <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text"  class="form-control datepicker"
													autocomplete="off" id="toDate" name="toDate" placeholder="Select To Date"
													value="${page.pageName}" required>
											</div>
										</div>

										<div class="form-group">
											<div class="col-sm-offset-2 col-sm-10">
												<input type="submit" class="btn btn-primary"
													onclick="submit_f(1)" value="Save"> <input
													type="submit" class="btn btn-primary" onclick="submit_f(0)"
													value="Save &
																		Next">
												<button type="reset" class="btn btn-default">Reset</button>
												<input type="hidden" name="is_view" id="is_view" value="0">
											</div>

											<div class="clearfix"></div>
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
	<script type="text/javascript">
		$(function() {
			$('#submitForm').submit(
					function() {
						$("input[type='submit']", this).val("Please Wait...")
								.attr('disabled', 'disabled');

						return true;
					});
		});

		function trim(el) {
			el.value = el.value.replace(/(^\s*)|(\s*$)/gi, ""). // removes leading and trailing spaces
			replace(/[ ]{2,}/gi, " "). // replaces multiple spaces with one space 
			replace(/\n +/, "\n"); // Removes spaces after newlines
			return;
		}
		
		function submit_f(view){
			document.getElementById("is_view").value=view;
		}
	</script>


</body>
</html>


<!-- Title of administrative training program organized for non-teaching staff -->