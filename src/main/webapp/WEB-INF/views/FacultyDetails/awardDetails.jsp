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
									href="${pageContext.request.contextPath}/showAwardDetailsList"><button
										type="button" class="btn btn-info">Back</button></a>
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertAwardDetail"
										method="post" name="form_sample_2" id="form_sample_2"
										onsubmit="return confirm('Do you really want to submit the form?');">

										<ul class="nav nav-tabs">
											<li class="active"><a href="#home" data-toggle="tab">
													<i class="fa fa-home"></i> Register
											</a></li>

										</ul>

										<div class="tab-content">
											<div class="tab-pane fade in active" id="home">

												<div>

<%-- 
													<input type="hidden" id="award_id" name="awardId"
														value="${award.awardId}"> --%>

														<div class="form-group">
														<label class="control-label col-sm-2" for="name">Name
															of Award/Recognition <span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<input type="text" class="form-control" id="name"
																autocomplete="off" name="name"
																placeholder="Name of Award/Recognition"
																value="${award.awardName}" required>
														</div>
													</div>
												<div class="form-group">

														<label class="control-label col-sm-2" for="agency">Awarding
															Agency/Authority <span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<input type="text" class="form-control" id="agency"
																name="agency" placeholder="Awarding Agency" autocomplete="off"
																value="${award.awardAuthority}" required>
														</div>

													</div>


													<div class="form-group">
														<label class="control-label col-sm-2" for="nature">Nature
															of Award/Recognition <span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<input type="text" class="form-control" id="nature" autocomplete="off"
																name="nature" placeholder="Nature of Award/Recognition"
																value="${award.awardNature}" required>
														</div>
													</div>


													<div class="form-group">

														<label class="control-label col-sm-2" for="date">Date
															<span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<input type="text" class="form-control datepicker"
																id="date" name="date" placeholder="Date" autocomplete="off"
																value="${award.awardDate}" required>
														</div>

													</div>
													<div class="form-group">
														<label class="control-label col-sm-2" for="validity">Validity
															<span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															Duration <input type="radio" name="validity" 
																id="validity" checked value="0" onclick="check1()">
															Lifetime<input onclick="check()" type="radio"
																name="validity" id="validity" value="1">
														</div>
													</div>
													<input type="hidden" id="is_view" name="is_view" value="0">
													<div id="abc">
														<div class="form-group">


															<label class="control-label col-sm-2" id="fromDate"
																for="validitydate">From <span
																class="text-danger"></span>
															</label>
															<div class="col-sm-6">
																<input type="text" class="form-control datepicker" autocomplete="off"
																	placeholder="From Date" id="fromDate" name="fromDate"
																	value="${award.awardValidityFrom}" required>
															</div>
														</div>

														<div class="form-group">
															<label class="control-label col-sm-2" id="toDate"
																for="toDate">To <span class="text-danger"></span>
															</label>
															<div class="col-sm-6">
																<input type="text" class="form-control datepicker" autocomplete="off"
																	id="toDate" name="toDate" placeholder="To Date"
																	value="${award.awardValidityTo}" required>
															</div>

														</div>
													</div>
													<div class="form-group">
														<div class="col-sm-offset-2 col-sm-10">
															<input type="submit" class="btn btn-primary"
																onclick="submit_f(1)" value="Save"> <input
																type="submit" class="btn btn-primary"
																onclick="submit_f(0)"
																value="Save & Next">
															<button type="reset" class="btn btn-default">Reset</button>
														</div>
													</div>



													<div class="clearfix"></div>

												</div>
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

	<script type="text/javascript">
		function check() {
			//alert("hii");

			/* document.getElementById("fromDate").style = "display:none"
			document.getElementById("toDate").style = "display:none" */
			document.getElementById("abc").style = "display:none"

			/* 	document.getElementById("label4").style = "display:none" */
			//	document.getElementById("hide_div").style = "visible"
		}
		function check1() {
			//alert("hii");

			/* document.getElementById("fromDate").style = "visible"
			document.getElementById("toDate").style = "visible" */
			document.getElementById("abc").style = "visible"

			/* document.getElementById("label4").style = "visible" */
			//	document.getElementById("hide_div").style = "visible"
		}
	</script>
	<script type="text/javascript">
function submit_f(view){
	//alert(view);
		document.getElementById("is_view").value=view; 
		
	}
 
</script>
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

</body>
</html>