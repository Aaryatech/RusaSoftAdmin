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
<body class=" " onload="hideText()">
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
							<%-- <h1 class="title">${title}</h1> --%>
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
									href="${pageContext.request.contextPath}/showOutReachDetailsList"><button
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

													<div class="col-sm-12"></div>


													<div class="form-group">
														<label class="control-label col-sm-2" for="status">Select
															Attended Activity <span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<select id="salutation" onchange="showForm()"
																name="salutation" class="form-control" required>
																<option value="STTP">STTP</option>
																<option value="Seminar">Seminar</option>
																<option value="Workshop">Workshop</option>
																<option value="Conference">Conference</option>
																<option value="FDP">FDP</option>
																<option value="Refresher Course">Refresher Course</option>
																<option value="6">Any Other</option>

															</select>
														</div>
															</div>
														<div class="form-group" id="abc">
															<label class="control-label col-sm-2" for="smallheading">Other
																Activity <span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="text" class="form-control" id="desn"
																	name="desn" placeholder="Other Designation" value=""
																	required>
															</div>
														</div>


													</div>

													<div class="form-group">

														<label class="control-label col-sm-2" for="smallheading">Name
															of Activity <span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<input type="text" class="form-control" id="dob"
																name="Grant" placeholder="Name of Activity" value=""
																required>
														</div>

													</div>



											<div class="form-group">

														<label class="control-label col-sm-2" for="status">Activity Type
															 <span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<select id="" name="activity_type" class="form-control" required>
															
																	
																		<c:forEach items="${facultyOutreachTypeList}" var="outtype">
																	
																			<option value="${outtype.typeId}">${outtype.typeName}</option>

																			

																	</c:forEach>
																</select>
														</div>
													</div>


													<div class="form-group">

														<label class="control-label col-sm-2" for="status">Select
															Level <span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<select id="salutation" name="salutation"
																class="form-control" required>
																<option value="International">International</option>
																<option value="National">National</option>
																<option value="State">State</option>
																<option value="Regional">Regional</option>


															</select>
														</div>
													</div>

												<div class="form-group">
															<label class="control-label col-sm-2" for="page_order">
																Date of Activity <span class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<input type="text" class="form-control datepicker" id="lib_joiningDate" 	value="${editInst.joiningDate}"
																	name="lib_joiningDate" placeholder="dd/MM/YYYY " required>
															</div>
														</div>
														
														
														          <input type="hidden" id="librarian_id" name="outreach_id" value="${editInst.librarianId}">
                                             	<input type="hidden" id="is_view" name="is_view" value="0">
												
												
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
	<script type="text/javascript">
		function showForm() {
			//document.getElementById("abc").style = "display:none"
			var qualType = document.getElementById("salutation").value
			//alert("qualType::"+qualType);

			if (qualType == 6) {

				document.getElementById("abc").style = "visible"

			} else {
				document.getElementById("abc").style = "display:none"
			}

		}
		function hideText() {
			//alert("hii");
			document.getElementById("abc").style = "display:none"

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