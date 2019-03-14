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
<body onload="abc()" class=" ">
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

							<div class="actions panel_actions pull-right"></div>

						</header>
						<!-- 
											<div class="col-xs-12">
														
																		<div class="form-group">
															<label class="control-label col-sm-2" for="status">Select Program
															 <span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
						       	<select id="approveValue" name="approveValue"class="form-control"  onchange="showExtraField()"  required>
				                         	</select>
																
																
															</div>
														</div>
														
											</div>
 -->

						<div class="col-xs-12">
							&nbsp;&nbsp;&nbsp;
							<div class="row">
								<label class="control-label col-sm-2" for="status">
									Program Name:</label> <label class="control-label col-sm-2"
									for="status"> </label>
							</div>

							<div class="row">
								<label class="control-label col-sm-2" for="status">
									Duration:</label> <label class="control-label col-sm-2" for="status">
								</label>
							</div>
							<div class="row">
								<label class="control-label col-sm-2" for="status">
									Sanctioned Intake:</label> <label class="control-label col-sm-2"
									for="status"> </label>

							</div>
						</div>


						<br />
						<br />
						<br />
						<div class="content-body">
							<div class="row">
								<div class="col-md-12">

									<ul class="nav nav-tabs">

										<li class="active"><a href="#Vision" data-toggle="tab">
												<i class="fa fa-home"></i> Vision
										</a></li>
										<li><a href="#Mission" data-toggle="tab"> <i
												class="fa fa-home"></i> Mission
										</a></li>
										<li><a href="#PEO" data-toggle="tab"> <i
												class="fa fa-home"></i> Program Educational Objective
										</a></li>
										<li><a href="#PO" data-toggle="tab"> <i
												class="fa fa-home"></i> Program Outcome
										</a></li>
										<li><a href="#PSO" data-toggle="tab"> <i
												class="fa fa-home"></i> Program Specific Outcome
										</a></li>

										<!-- 	<li><a href="#PO-PSO" data-toggle="tab"> <i
													class="fa fa-home"></i>PO-PSO Mapping
											</a></li>
											 -->


									</ul>

									<div class="tab-content">



										<!-- 		Vision -->
										<div class="tab-pane fade in active " id="Vision">

											<!-- 	<form action="" method="post">		 -->




											<div class="row">


												<label class="col-sm-2 text-left" for="hh"> Vision<span
													class="text-danger">*</span>
												</label>
												<div class="col-sm-6">
													<input type="text" maxlength="10" class="form-control"
														id="hh" name="hh" placeholder="Another Scheme Name"
														required>
												</div>
											</div>

											<br />
											<div class="row">

												<label class="col-sm-2 text-left" for="hh">
													Remark(If Any) </label>
												<div class="col-sm-6">
													<input type="text" maxlength="10" class="form-control"
														id="hh" name="hh" placeholder="Another Scheme Name"
														required>
												</div>

												<div class="col-sm-4">

													<input type="submit" class="btn btn-info" value="Add">
												</div>
											</div>




											<!-- 		</form>			 -->
											<div class="row">

												<div class="col-xs-12">

													<table id="example7"
														class="table table-striped dt-responsive display">
														<thead>
															<tr>
																<th>Sr No</th>
																<th>Vision</th>
																<th>Action</th>

															</tr>
														</thead>



													</table>

												</div>

											</div>


										</div>




										<!-- 	Mission -->
										<div class="tab-pane fade in " id="Mission">

											<!-- 	<form action="" method="post">		 -->

											<div class="row">


												<label class="col-sm-2 text-left" for="hh"> Mission<span
													class="text-danger">*</span>
												</label>
												<div class="col-sm-6">
													<input type="text" maxlength="10" class="form-control"
														id="hh" name="hh" placeholder="Another Scheme Name"
														required>
												</div>
											</div>

											<br />
											<div class="row">

												<label class="col-sm-2 text-left" for="hh">
													Remark(If Any) </label>
												<div class="col-sm-6">
													<input type="text" maxlength="10" class="form-control"
														id="hh" name="hh" placeholder="Another Scheme Name"
														required>
												</div>

												<div class="col-sm-4">

													<input type="submit" class="btn btn-info" value="Add">
												</div>
											</div>
											<!-- 		</form>			 -->
											<div class="row">

												<div class="col-xs-12">

													<table id="example7"
														class="table table-striped dt-responsive display">
														<thead>
															<tr>
																<th>Sr No</th>
																<th>Mission</th>
																<th>Action</th>

															</tr>
														</thead>



													</table>

												</div>

											</div>


										</div>


										<!-- 	PO -->
										<div class="tab-pane fade in " id="PO">

											<!-- 	<form action="" method="post">		 -->

											<div class="row">


												<label class="col-sm-2 text-left" for="hh"> PO<span
													class="text-danger">*</span>
												</label>
												<div class="col-sm-6">
													<input type="text" maxlength="10" class="form-control"
														id="hh" name="hh" placeholder="Another Scheme Name"
														required>
												</div>
											</div>

											<br />
											<div class="row">

												<label class="col-sm-2 text-left" for="hh">
													Remark(If Any) </label>
												<div class="col-sm-6">
													<input type="text" maxlength="10" class="form-control"
														id="hh" name="hh" placeholder="Another Scheme Name"
														required>
												</div>

												<div class="col-sm-4">

													<input type="submit" class="btn btn-info" value="Add">
												</div>
											</div>
											<!-- 		</form>			 -->
											<div class="row">

												<div class="col-xs-12">

													<table id="example7"
														class="table table-striped dt-responsive display">
														<thead>
															<tr>
																<th>Sr No</th>
																<th>PO</th>
																<th>Action</th>

															</tr>
														</thead>



													</table>

												</div>

											</div>


										</div>



										<!-- 	PSO -->
										<div class="tab-pane fade in " id="PSO">

											<!-- 	<form action="" method="post">		 -->
											<div class="row">


												<label class="col-sm-2 text-left" for="hh"> PSO<span
													class="text-danger">*</span>
												</label>
												<div class="col-sm-6">
													<input type="text" maxlength="10" class="form-control"
														id="hh" name="hh" placeholder="Another Scheme Name"
														required>
												</div>
											</div>

											<br />
											<div class="row">

												<label class="col-sm-2 text-left" for="hh">
													Remark(If Any) </label>
												<div class="col-sm-6">
													<input type="text" maxlength="10" class="form-control"
														id="hh" name="hh" placeholder="Another Scheme Name"
														required>
												</div>

												<div class="col-sm-4">

													<input type="submit" class="btn btn-info" value="Add">
												</div>
											</div>
											<!-- 		</form>			 -->
											<div class="row">

												<div class="col-xs-12">

													<table id="example7"
														class="table table-striped dt-responsive display">
														<thead>
															<tr>
																<th>Sr No</th>
																<th>PSO</th>
																<th>Action</th>

															</tr>
														</thead>



													</table>

												</div>

											</div>


										</div>



										<!-- 	PEO -->
										<div class="tab-pane fade in " id="PEO">

											<!-- 	<form action="" method="post">		 -->

											<div class="row">


												<label class="col-sm-2 text-left" for="hh"> PEO<span
													class="text-danger">*</span>
												</label>
												<div class="col-sm-6">
													<input type="text" maxlength="10" class="form-control"
														id="hh" name="hh" placeholder="Another Scheme Name"
														required>
												</div>
											</div>

											<br />
											<div class="row">

												<label class="col-sm-2 text-left" for="hh">
													Remark(If Any) </label>
												<div class="col-sm-6">
													<input type="text" maxlength="10" class="form-control"
														id="hh" name="hh" placeholder="Another Scheme Name"
														required>
												</div>

												<div class="col-sm-4">

													<input type="submit" class="btn btn-info" value="Add">
												</div>
											</div>
											<!-- 		</form>			 -->
											<div class="row">

												<div class="col-xs-12">

													<table id="example7"
														class="table table-striped dt-responsive display">
														<thead>
															<tr>
																<th>Sr No</th>
																<th>PEO</th>
																<th>Action</th>

															</tr>
														</thead>



													</table>

												</div>

											</div>


										</div>






										<!-- 	poo-ps -->
										<div class="tab-pane fade in " id="PO-PSO">

											<!-- 	<form action="" method="post">		 -->

											<div class="row">




												<label class="col-sm-2 text-left" for="hh"> Program
													Outcome: </label> <label class="col-sm-2 text-left" for="hh">

												</label>




											</div>
											<!-- 		</form>			 -->
											<div class="row">

												<div class="col-xs-12">

													<table id="example7"
														class="table table-striped dt-responsive display">
														<thead>
															<tr>
																<th>Sr No</th>
																<th>PSO</th>
																<th>Satisfying Value</th>


															</tr>
														</thead>



													</table>
													<br />

													<div class="col-sm-4">

														<input type="submit" class="btn btn-info" value="Submit">
													</div>

												</div>

											</div>


										</div>



									</div>



									<!--  -->

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
		function abc() {

		}
	</script>





</body>
</html>