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
								<a href="#myModal1" data-toggle="modal"><button
										type="submit" class="btn btn-info">Add</button></a> <a
									href="${pageContext.request.contextPath}/publicationList"><button
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
													<i class="fa fa-home"></i> ${title}
											</a></li>

										</ul>

										<div class="tab-content">
											<div class="tab-pane fade in active" id="home">

												<div>


													<div class="col-xs-12">
													
															<div class="col-xs-12">

															<table id="example-1"
																class="table table-striped dt-responsive display">
																<thead>
																	<tr>
																		<th width="10%">Sr No</th>
																		
																		<th>Academic Year</th>
																		<th>Name of Activity</th>
																		<th>From Date</th>
																		<th >To Date</th>
																		<th >Total Participants</th>
																		<th >Level of Activity/Competition</th>
																	<th >Type of Activity/Competition</th>
																		
																	</tr>
																
																</thead>



																<tbody>


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




	<div class="modal fade col-xs-12" id="myModal1" tabindex="-1"
		role="dialog" aria-hidden="true">
		<div class="modal-dialog" style="width: 65%">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Sports / Cultural Activity / Competition organized at Institute

</h4>
				</div>
				<div class="modal-body">
				
				<div class="form-group">
						<label class="control-label col-sm-3" for="academicYear">Academic
							Year</label> <select id="academicYear" name="academicYear"
							class="form-control" required>
							<option value="2018-2019">2018-2019</option>
							<option value="2017-2018">2017-2018</option>
							<option value="2016-2017">2016-2017</option>

						</select>
					</div>
					
				
				<div class="form-group">

						<label class="control-label col-sm-3" for="activityName">Name of Activity
						</label> <input type="text" class="form-control"
							id="activityName" name="activityName"
							placeholder="Name of Activity"
							value="${page.pageName}" required>
					</div>

				
	
					<div class="form-group">

						<label class="control-label col-sm-3" for="fromDate">From Date
					 </label> 
							
							<input type="date" class="form-control"
							id="fromDate" name="fromDate"
							
							value="${page.pageName}" required>
							
							
							<label class="control-label col-sm-3" for="toDate">To Date
					 </label> 
							
							<input type="date" class="form-control"
							id="toDate" name="toDate"
							
							value="${page.pageName}" required>
					</div>
<div class="form-group">

						<label class="control-label col-sm-3" for="totalParticipants">Total Participants
						</label> <input type="text" class="form-control"
							id="totalParticipants" name="totalParticipants"
							placeholder="Total Participants"
							value="${page.pageName}" required>
					</div>

				

					
				<div class="form-group">
						<label class="control-label col-sm-3" for="activityLevel">Level of Activity
							Year</label> <select id="activityLevel" name="activityLevel"
							class="form-control" required>
							<option value="State">State</option>
							<option value="National">National</option>
							<option value="International">International</option>

						</select>
					</div>
					
							
				<div class="form-group">
						<label class="control-label col-sm-3" for="activityType">Type of Activity
							Year</label> <select id="activityType" name="activityType"
							class="form-control" required>
							<option value="Sports">Sports</option>
							<option value="Cultural">Cultural</option>
							

						</select>
					</div>
					
					<!-- Link on Website for Activity Report -->

					<div class="form-group" style="text-align: center;">

						<button type="submit" class="btn btn-primary" onclick="getData()">Submit</button>
					</div>


				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
					<input type="hidden" id="index" name="index" value="0">
				</div>
			</div>
		</div>
	</div>


	<script type="text/javascript">
		function getData() {
			//alert("hii");
			var i = parseInt(document.getElementById("index").value);
			var activityName = document.getElementById("activityName").value;
			var academicYear = document.getElementById("academicYear").value;
			
			var totalParticipants = document.getElementById("totalParticipants").value;
			var activityLevel = document.getElementById("activityLevel").value;
			var fromDate = document.getElementById("fromDate").value;
			var toDate = document.getElementById("toDate").value;
			var activityType = document.getElementById("activityType").value;
			//alert("noStud"+noStud);
			var dataTable = $('#example-1').DataTable();

			dataTable.row.add(
					[ i + 1,activityName, academicYear, fromDate, toDate,totalParticipants ,activityLevel,activityType])
					.draw();
			document.getElementById("index").value = i + 1;
		}
	</script>






</body>
</html>