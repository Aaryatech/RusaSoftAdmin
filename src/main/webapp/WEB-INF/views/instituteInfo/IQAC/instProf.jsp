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
										action="${pageContext.request.contextPath}/insertIqacBasicInfo"
										method="post" name="form_sample_2" id="form_sample_2"
										onsubmit="return checkBeforeSubmit()">

										<ul class="nav nav-tabs">
											<li class="active"><a href="#home" data-toggle="tab">
													<i class="fa fa-home"></i> Register Form
											</a></li>


										</ul>

										<div class="tab-content">
											<div class="tab-pane fade in active" id="home">

												<div>




													<div class="form-group">
														<label class="control-label col-sm-2" for="page_name">
															IQAC Establishment Date <span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<input type="text" class="form-control datepicker"
																id="estb_date" name="estb_date" placeholder="dd-MM-YYYY"
																placeholder="Date of Establishment of IQAC "
																value="${date}">
														</div>
													</div>



													<div class="form-group">
														<label class="control-label col-sm-2" for="page_order">
															Alternate Faculty Name <span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<input type="text" class="form-control"
																id="alt_faculty_name" name="alt_faculty_name"
																placeholder="Name of alternate Faculty with IQAC"
																pattern="^(?!\s*$).+" value="${instRes.iqacAltName}"
																required>
														</div>
													</div>

													<div class="form-group">
														<label class="control-label col-sm-2" for="page_order">Mobile
															No <span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<input type="text" class="form-control"
																id="alt_fac_contact" pattern="^[1-9]{1}[0-9]{9}$"
																maxlength="10" name="alt_fac_contact"
																placeholder="Mobile No of Alternate Faculty Associated"
																value="${instRes.iqacAltMobile}" required>
														</div>
													</div>

													<div class="form-group">
														<label class="control-label col-sm-2" for="page_order">Phone
															No<span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<input type="text" class="form-control" id="phone_no"
																pattern="^[1-9]{1}[0-9]{9}$" maxlength="10"
																name="phone_no"
																placeholder="Phone No of Alternate Faculty"
																value="${instRes.iqacAltPhone}" required>
														</div>
													</div>

													<div class="form-group">
														<label class="control-label col-sm-2" for="page_order">FAX
															No.<span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<input type="text" pattern="^[1-9]{1}[0-9]{9}$"
																maxlength="10" maxlength="10" class="form-control"
																id="fax_no" value="${instRes.iqacAltFax}" name="fax_no"
																placeholder="FAX No of Alternate Faculty Associated"
																required>
														</div>
													</div>

													<div class="form-group">
														<label class="control-label col-sm-2" for="page_order">
															Registered Email <span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<input type="email" class="form-control"
																id="registered_email" value="${instRes.iqacAltEmail1}"
																name="registered_email" placeholder="abc@xyz.com"
																required>
														</div>
													</div>

													<div class="form-group">
														<label class="control-label col-sm-2" for="page_order">Alternate
															Email <span class="text-danger"></span>
														</label>
														<div class="col-sm-6">
															<input type="email" class="form-control" id="alt_email"
																value="${instRes.iqacAltEmail2}" name="alt_email"
																placeholder="abc@xyz.com " required>
														</div>
													</div>



													<c:choose>
														<c:when test="${instRes.iqacInfoId!=0}">
															<input type="hidden" id="iqac_info_id"
																name="iqac_info_id" value="${instRes.iqacInfoId}">
														</c:when>
														<c:otherwise>
															<input type="hidden" id="iqac_info_id"
																name="iqac_info_id" value="0">
														</c:otherwise>

													</c:choose>


													<div class="form-group">
														<div class="col-sm-offset-2 col-sm-9">
															<input type="submit" id="sub1" class="btn btn-primary"
																value="Save & Next">

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
		function submit_f(view) {
			document.getElementById("is_view").value = view;//create this 
			/* var form=document.getElementById("form_sample_2");
			form.setAttribute("method", "post");

			form.action=("insertLibrarian");
			var x =confirm();
			if(x==true)
			form.submit(); */

		}

		var wasSubmitted = false;
		function checkBeforeSubmit() {
			if (!wasSubmitted) {
				var x = confirm("Do you really want to submit the form?");
				if (x == true) {
					wasSubmitted = true;
					document.getElementById("sub1").disabled = true;
					//  document.getElementById("sub2").disabled=true;

					return wasSubmitted;
				}
			}
			return false;
		}
	</script>



	<!-- END CONTAINER -->
	<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->

	<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>



</body>
</html>