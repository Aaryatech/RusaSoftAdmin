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
								<%-- <a href="${pageContext.request.contextPath}/hodList"><button
										type="button" class="btn btn-info">< Back</button></a> --%> <a
									class="box_toggle fa fa-chevron-down"></a>
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




										<div class="col-xs-12">

	

											<h5 class="title pull-left">
												<strong>Gender Issue</strong>
											</h5>
											<div class="col-xs-12"></div>
											<div class="col-xs-12">


											<div class="form-group">
												<label class="control-label col-sm-3" for="page_name">Title
													of Gender Equity Program<span class="text-danger">*</span>
												</label>
												<div class="col-sm-6">
													<input type="text" class="form-control" id="hodName"
														name="hodName" placeholder="Title of Linkage"
														value="${page.pageName}" required>
												</div>
											</div>


										
											<div class="form-group">
												<label class="control-label col-sm-3" for="page_name">No. of Participant
												<span class="text-danger">*</span>
												</label>
												<div class="col-sm-6">
													<input type="text" class="form-control" id="hodName"
														name="hodName" placeholder="No. of Participant"
														value="${page.pageName}" required>
												</div>
											</div>
										
										
										<div class="form-group">
																<label class="control-label col-sm-2" for="smallheading">Duration
																	<span class="text-danger">*</span>
																</label>
																
																
																<label class="control-label col-sm-1" id="label1" for="smallheading">From
																	  <span class="text-danger"></span>
																</label>
																<div class="col-sm-2">
																	<input type="date" class="form-control" id="label2"
																		name="Grant" value="" required>
																</div>

																
																<label class="control-label col-sm-2" id="sp" for="smallheading">To
																	  <span class="text-danger" ></span>
																</label>
																<div class="col-sm-2">
																	<input type="date" class="form-control" id="label4"
																		name="Grant" value="" required>
																</div>
																
															</div>
															
															
															<div class="form-group">
															
															<label class="control-label col-sm-2" for="smallheading">    
																	 <span class="text-danger"></span>
																</label>
																
												<label class="control-label col-sm-1" for="status">
													Year  <span class="text-danger">*</span>
												</label>
												<div class="col-sm-2">
													<select id="salutation" name="salutation"
														class="form-control" required>
														<option value="0">2018-2019</option>
														<option value="1">2017-2018</option>
														<option value="2">2016-2017</option>
														<option value="3">2015-2016</option>


													</select>
													</div>
													<label class="control-label col-sm-2" id="label1" for="smallheading">Number
																	  <span class="text-danger"></span>
																</label>
																<div class="col-sm-2">
																	<input type="text" class="form-control" id="label2"
																		name="Grant" value=""  placeholder="Number" required>
																</div>
													
												
										
															
												</div>			
												<!-- <table class="table table-striped dt-responsive display">
													<thead>
														<tr>

														</tr>

													</thead>



													<tbody>
														<tr>
															<td>Year</td>
															<td><input type="text" class="form-control"
																id="curExp" name="curExp" value="" required></td>
														</tr>

														<tr>
															<td>Number</td>
															<td><input type="text" class="form-control"
																id="curExp" name="curExp" value="" required></td>
														</tr>


													</tbody>
												</table> -->
											</div>

													<div class="form-group">
													
													<label class="control-label col-sm-3" for="page_order">Gender Equity in Safety/Security
													<span class="text-danger">*</span>
												</label>
												<div class="col-sm-2">
													Yes <input type="radio" name="stu" id="stu" checked
														value="0"> No<input type="radio" name="stu"
														id="stu" value="1">
												</div>
													
													</div>
													
													<div class="form-group">
													
													<label class="control-label col-sm-3" for="page_order">Gender Equity in Counseling
													<span class="text-danger">*</span>
												</label>
												<div class="col-sm-2">
													Yes <input type="radio" name="stu" id="stu" checked
														value="0"> No<input type="radio" name="stu"
														id="stu" value="1">
												</div>
													
													</div>
													
													<div class="form-group">
													
													<label class="control-label col-sm-3" for="page_order">Gender Equity in Common Room
													<span class="text-danger">*</span>
												</label>
												<div class="col-sm-2">
													Yes <input type="radio" name="stu" id="stu" checked
														value="0"> No<input type="radio" name="stu"
														id="stu" value="1">
												</div>
													
													</div>



											<div class="form-group">
												<div class="col-sm-offset-2 col-sm-10">
													<button type="submit" class="btn btn-primary">Submit</button>
													<button type="reset" class="btn btn-default">Reset</button>
												</div>
											</div>

										</div>
									</form>
								</div>

							</div>
						</div>

					</section>

				</div>


<!-- <label class="control-label col-sm-1" for="page_order">Students
													:<span class="text-danger">*</span>
												</label>
												<div class="col-sm-2">
													Yes <input type="radio" name="stu" id="stu" checked
														value="0"> No<input type="radio" name="stu"
														id="stu" value="1">
												</div>


 -->














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