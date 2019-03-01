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
<body class=" " onload="hideText()" >
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
								<%-- <a href="${pageContext.request.contextPath}/publicationList"><button
										type="button" class="btn btn-info">Back</button></a>  --%>
										<a
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


								<div class="col-xs-12">

														<h4>Subject Details:</h4>

<div class="form-group">
									<table class="table table-striped dt-responsive display" id="example-1">
										<thead>
											<tr>
												<th width="5%">Sr No</th>
												<th width="10%">Year</th>
												<th width="10%" >Name of Subject</th>
												<th  width="5%">Name of Faculty</th>
												<th  width="10%">No of Affiliated Guides</th>
													<th width="20%">No of Students Registered</th>
												 <th width="20%" style="text-align: center;" colspan="2">No.
													of Students Admitted</th> 
													
											
											</tr>
</thead>
<tbody>
											<tr>
												<td width="5%"></td>
												<td width="10%"></td>
												<td width="10%"></td>
												<td width="5%"></td>
												<td width="10%"></td>
												<td width="20%"></td>
												<td width="10%">Sanctioned Intake</td>
												<td width="10%">Admitted Student</td>
											</tr> 
										</tbody>
</table>


									
								</div>
</div>
								
							
														<div class="form-group">
															<div class="col-sm-offset-2 col-sm-10">
																<button type="submit" class="btn btn-primary">Submit</button>
																<button type="reset" class="btn btn-default">Reset</button>
															</div>
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
						<label class="control-label col-sm-6" for="academicYear">Academic
							Year</label> <select id="academicYear" name="academicYear"
							class="form-control" required>
							<option value="2018-2019">2018-2019</option>
							<option value="2017-2018">2017-2018</option>
							<option value="2016-2017">2016-2017</option>
								<option value="2015-2016">2015-2016</option>

						</select>
					</div>
						
						<div class="form-group">
						
							<label class="control-label col-sm-6" for="page_name">Name of Subject
							</label>
							
								<input type="text" class="form-control" id="subName" required
									name="subName" placeholder="Name of Subject" value="${page.pageName}"
									required>
						
	</div>
	<div class="form-group">
							<label class="control-label col-sm-6" for="page_name">Name of Faculty
							</label>
							
								<input type="text" class="form-control"
								id="facultyName" name="facultyName" value="" placeholder="Name of Faculty" required>
							

</div>


<div class="form-group">
							<label class="control-label col-sm-8" for="page_name">No of affiliated Guides
							</label>
							
								<input type="text" class="form-control"
								id="affGuide" name="affGuide" value="" placeholder="No of affiliated Guides" required>
							

</div>


<div class="form-group">
							<label class="control-label col-sm-8" for="page_name">No of Students Registered
							</label>
							
								<input type="text" class="form-control"
								id="studNum" name="studNum" value="" placeholder="No of Students Registered" required>
							

</div>
<div class="form-group">
							<label class="control-label col-sm-3" for="page_name">From Date
							</label>
							
								<input type="date" class="form-control"
								id="fromDate" name="fromDate" value="" placeholder="Total Student Strength" required>
							

</div>
<div class="form-group">
							<label class="control-label col-sm-3" for="page_name">To Date
							</label>
							
								<input type="date" class="form-control"
								id="toDate" name="toDate" value="" placeholder="Total Student Strength" required>
							

</div>
						<button type="submit" class="btn btn-primary" onclick="subData()">Submit</button>
				<!-- 	</form> -->
				</div>
			</div>
		</div>
	</div>
	
	
	


	
	
	<script type="text/javascript">
	
	
	function subData() {
	alert("hii");
		var i = parseInt(document.getElementById("index").value);
		var academicYear=document.getElementById("academicYear").value
		
		var subName=document.getElementById("subName").value
		var facultyName=document.getElementById("facultyName").value
		var affGuide=document.getElementById("affGuide").value
		var studNum=document.getElementById("studNum").value
		var fromDate=document.getElementById("fromDate").value
		var toDate=document.getElementById("toDate").value
		
		
        alert("hello");
		var dataTable = $('#example-1')
		.DataTable();
		
					i+1,
					academicYear,
					subName,
					facultyName,
					affGuide,
					studNum,
					fromDate,
					toDate

					 ])
	.draw();
	
	
	document.getElementById("index").value = i + 1;
}

	

	</script>
	
	

</body>
</html>



