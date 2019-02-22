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
							<h1 class="title">${title}</h1>
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
								<%-- <a href="${pageContext.request.contextPath}/publicationList"><button
										type="button" class="btn btn-info">Back</button></a> --%> <a
									class="box_toggle fa fa-chevron-down"></a>
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


													<div class="col-xs-12">

			

															<h5 class="title pull-left">
																<strong>Ph.D Guidance :</strong>
															</h5>
															<div class="col-sm-12"></div>

															<div class="form-group">

																<label class="control-label col-sm-2" for="smallheading">Ph.D.
																	Guide : <span class="text-danger">*</span>
																</label>


																<div class="col-sm-4">
																	Yes <input type="radio" name="phdGuide" id="phdGuide"
																		checked value="0"> No<input type="radio"
																		name="phdGuide" id="phdGuide" value="1">
																</div>
</div>

															<div class="form-group">
																<label class="control-label col-sm-2" for="smallheading">Co-Guide
																	: <span class="text-danger">*</span>
																</label>


																<div class="col-sm-4">
																	Yes <input type="radio" name="coGuide" id="coGuide"
																		checked value="0"> No<input type="radio"
																		name="coGuide" id="coGuide" value="1">
																</div>

																<label class="control-label col-sm-2" for="smallheading">Name
																	of Co-Guide: <span class="text-danger">*</span>
																</label>
																<div class="col-sm-4">
																	<input type="text" class="form-control" id="dob"
																		name="Grant" placeholder="Name of Co_Guide" value=""
																		required>
																</div>


															</div>

															<div class="form-group">



																<label class="control-label col-sm-2" for="smallheading">Name
																	of Ph.D. Scholar: <span class="text-danger">*</span>
																</label>
																<div class="col-sm-4">
																	<input type="text" class="form-control" id="dob"
																		name="Grant" placeholder="Name of Ph.D Scholar"
																		value="" required>
																</div>
																<label class="control-label col-sm-2" for="smallheading">Year
																	of Registration: <span class="text-danger">*</span>
																</label>
																<div class="col-sm-1">
																	<input type="text" class="form-control" id="dob"
																		name="Grant" placeholder="Year" value="" required>
																</div>
																<label class="control-label col-sm-2" for="smallheading">Year
																	of Awarded Ph.D: <span class="text-danger">*</span>
																</label>
																<div class="col-sm-1">
																	<input type="text" class="form-control" id="dob"
																		name="Grant" placeholder="Year" value="" required>
																</div>


															</div>

															<div class="form-group">

																<label class="control-label col-sm-2" for="smallheading">Topic/
																	Area of Research : <span class="text-danger">*</span>
																</label>
																<div class="col-sm-10">
																	<textarea id="off_add" name="off_add"
																		class="form-control" style="width: 100%;" required></textarea>
																</div>
															</div>

															<div class="form-group">



																<label class="control-label col-sm-2" for="smallheading">Date
																	of Registration: <span class="text-danger">*</span>
																</label>
																<div class="col-sm-2">
																	<input type="date" class="form-control" id="dob"
																		name="Grant" placeholder="Name of Ph.D Scholar"
																		value="" required>
																</div>

																<label class="control-label col-sm-2" for="smallheading">Valid
																	up : <span class="text-danger">*</span>
																</label>
																<div class="col-sm-2">
																	<input type="date" class="form-control" id="validup"
																		name="validup" placeholder="Valid up" value=""
																		required>
																</div>


																<label class="control-label col-sm-2" for="smallheading">Awarded
																	: <span class="text-danger">*</span>
																</label>


																<div class="col-sm-2">
																	Yes <input type="radio" name="awarded" id="awarded"
																		checked value="0"> No<input type="radio"
																		name="awarded" id="awarded" value="1">
																</div>

															</div>

														

														<div class="form-group">
															<div class="col-sm-offset-2 col-sm-10">
																<button type="submit" class="btn btn-primary">Submit</button>
																<button type="reset" class="btn btn-default">Reset</button>
															</div>
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


</body>
</html>