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
							<a href="#myModal"	data-toggle="modal"><button type="submit"
																class="btn btn-info">Add</button></a>
								<%-- <a href="${pageContexts.request.contextPath}/publicationList"><button
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
																<strong>Subject Details :</strong>
															</h5>
															<div class="col-sm-12"></div>


														


																<table id="example1"
																	class="table table-striped dt-responsive display">
																	<thead>
																		<tr>
																			<th width="5%">Sr No</th>
																			
																			<th width="10%">Academic Year</th>
																			<th width="10%">Semester</th>
																			<th width="10%">Subject Code Taught</th>
																			<th width="10%">Subject Taught</th>
																			<th width="15%">Subject Type
																				</th>
																				<th width="15%">Is CBCS
																				</th>
																				<th width="10%">Course Outcome</th>
																			<th width="15%">No. of Students Appeared</th>
																			<th width="10%">Passed</th>
																			<th width="10%">% of Result</th>
																			
																			<th width="30%">Action</th>
																		</tr>
																	</thead>



																	<tbody>

																		
																	</tbody>
																</table>

															</div>

															<hr>

															
														

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

<div aria-hidden="true" role="dialog" tabindex="-1" id="myModal"
		class="modal fade" style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button aria-hidden="true" data-dismiss="modal" class="close"
						type="button">×</button>
					<h4 class="modal-title">Subject Details</h4>
				</div>
				<div class="modal-body">
					<%-- <form role="form"
						action="${pageContext.request.contextPath}/showModuleForm"
						method="get"> --%>
						<input type="hidden" class="form-control" id="pageId"
							name="pageId" >
							<input type="hidden" class="form-control" id="index"
							name="index" value="0">
						<div class="form-group">
							<label class="control-label col-sm-6" for="page_name">Academic Year</label> <select
								id="academicYear" name="qualType" class="form-control" onchange="showForm()" required>
								<option value="2018-2019">2018-2019</option>
								<option value="2017-2018">2017-2018</option>
								<option value="2016-2017">2016-2017</option>
									<option value="2016-2017">2015-2016</option>
								
							</select>
						</div>
						
								
						<div class="form-group">
						
							<label class="control-label col-sm-6" for="page_name">Subject Code Taught
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="code" placeholder="Subject Code Taught"
									name="code" value="${page.pageName}"
									required>
							<!-- </div> -->
	</div>
						
				
									<div class="form-group">
						<label class="control-label col-sm-6" for="page_name">Semester</label> <select
								id="sem" name="sem" class="form-control" onchange="showForm()" required>
								<option value="0">I</option>
								<option value="1">II</option>
								<option value="3">III</option>
								<option value="4">IV</option>
							
							


							</select>
						</div>
						
						
						<div class="form-group">
						
							<label class="control-label col-sm-6" for="page_name">Subject Taught
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="subTaut" placeholder="Subject Taught"
									name="subTaut" value="${page.pageName}"
									required>
							<!-- </div> -->
	</div>
						
		
									<div class="form-group">
						<label class="control-label col-sm-6" for="page_name">Subject Type</label> <select
								id="subType" name="subType" class="form-control" onchange="showForm()" required>
								<option value="0">Regular</option>
								<option value="1">Elective</option>
								<option value="3">Other</option>
								
							</select>
						</div>			
						
						
						
									<div class="form-group">
						<label class="control-label col-sm-6" for="page_name">Is CBCS</label> 
						<div class="col-sm-2">
																Yes <input type="radio" name="consultancy"
																	id="consultancy" checked value="0"> No<input
																	type="radio" name="consultancy" id="consultancy"
																	value="1">
															</div> 
						</div>				
					<div class="form-group">
						
							<label class="control-label col-sm-6" for="page_name">No. of Student Appeared
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="noStud" placeholder="No. of Student Appeared"
									name="noStud"  value="${page.pageName}"
									required>
							<!-- </div> -->
	</div>
	<div class="form-group">
						
							<label class="control-label col-sm-6" for="page_name">Passed
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="pass" placeholder="Passed" onchange=" calResult()"
									name="pass"  value="${page.pageName}"
									required>
							<!-- </div> -->
	</div>
	
	
	<div class="form-group">
						
							<label class="control-label col-sm-6" for="page_name">% of Result
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="rslt" placeholder="% of Result"
									name="rslt"  value="${page.pageName}"
									required>
							<!-- </div> -->
	</div>
	
	
						
<div class="form-group">
						
							<label class="control-label col-sm-6" for="page_name">Course Outcome
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="course" placeholder="Course Outcome"
									name="course"value="${page.pageName}"
									required>
							<!-- </div> -->
	</div>
		
						
						

						<button type="submit" class="btn btn-primary" onclick="getData()">Submit</button>
					<!-- </form> -->
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
	function getData() {
	//alert("hii");
		var i = parseInt(document.getElementById("index").value);
	
		var sem=document.getElementById("sem").value
		var code=document.getElementById("code").value
		var academicYear=document.getElementById("academicYear").value
		
		var subTaut=document.getElementById("subTaut").value
		var subType=document.getElementById("subType").value
		
		var noStud=document.getElementById("noStud").value
	
		var pass=document.getElementById("pass").value
		var rslt=document.getElementById("rslt").value
		var course=document.getElementById("course").value
		var isCBCS=document.getElementById("consultancy").value
		var t="-";
		

		var dataTable = $('#example1')
		.DataTable();
		
		dataTable.row
		.add(
				[
					i+1,
					academicYear,
				
					sem,
					code,
					subTaut,
					subType,
					isCBCS,
					course,
					noStud,
					pass,
					rslt,
					
				    t
						 ])
		.draw();
		
		document.getElementById("index").value = i + 1;
		
	}
	
	function calResult(){
		var noStud=parseFloat(document.getElementById("noStud").value);
		
		var pass=parseFloat(document.getElementById("pass").value);
		
		var x=(pass/noStud)*100;
		document.getElementById("rslt").value=x;
		
		
	}

	</script>
	
	
	
	
	
</body>
</html>