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
<body class=" " >
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
								<a href="${pageContext.request.contextPath}/publicationList"><button
										type="button" class="btn btn-info">Back</button></a> <a
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

										
														



														<label class="control-label col-sm-3" for="page_name">Mentoring
															to the Students :<span class="text-danger">*</span>
														</label>


														<div class="col-xs-12">




															<table id="example-2"
																class="table table-striped dt-responsive display">
																<thead>
																	<tr>
																		<th width="10%">Sr No</th>
																		<th width="45%">Year</th>
																		<th width="45%">No of Students<a href="#myModal1"
														data-toggle="modal"><button type="submit"
																class="btn btn-primary">Add</button></a></th>
																	</tr>
																</thead>



																<tbody>

																	<tr>
																		<td>1</td>
																		<td>2018-2019</td>

																		<td><input type="text" class="form-control"
																			id="curExp" name="curExp" value="" required></td>

																	</tr>
																	<!-- <tr>
																		<td>2</td>
																		<td>2019-2020</td>

																		<td><input type="text" class="form-control"
																			id="curExp" name="curExp" value="" required></td>

																	</tr>
																	<tr>
																		<td>3</td>
																		<td>2020-2021</td>

																		<td><input type="text" class="form-control"
																			id="curExp" name="curExp" value="" required></td>

																	</tr>
 -->

																</tbody>
															</table>
														
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
	
	
	

	
	<div aria-hidden="true" role="dialog" tabindex="-1" id="myModal1"
		class="modal fade" style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button aria-hidden="true" data-dismiss="modal" class="close"
						type="button">×</button>
					<h4 class="modal-title">Mentoring to Student</h4>
				</div>
				<div class="modal-body">
					<form role="form"
						action="${pageContext.request.contextPath}/showModuleForm"
						method="get">
						<input type="hidden" class="form-control" id="pageId"
							name="pageId">
						
								
						
	<div class="form-group">
							<label class="control-label col-sm-3" for="page_name">Year of Passing</label> <select
								id="qualType" name="qualType" class="form-control" onchange="showForm()" required>
								<option value="0">2018-2019</option>
								<option value="1">2017-2018</option>
								<option value="3">2016-2017</option>
								
							</select>
						</div>
						<div class="form-group">
						
							<label class="control-label col-sm-3" for="page_name">No of Student
							</label>
						
								<input type="text" class="form-control" id="hodName"
									name="hodName" placeholder="No." value="${page.pageName}"
									required>
							</div>
	
						

						<button type="submit" class="btn btn-primary">Submit</button>
					</form>
				</div>
			</div>
		</div>
	</div>


	<script>
	function showForm() {
	document.getElementById("abc").style = "display:none"
		var index=document.getElementById("qualType").value
		

		if (index == 6) {

			document.getElementById("abc").style = "visible"

		} 
	
	}
	function showForm1() {
		document.getElementById("abc").style = "display:none"
			
		
		}
	
	</script>
	
	
	

</body>
</html>