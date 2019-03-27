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
								<c:if test="${addAccess == 0}">
								<a
									href="${pageContext.request.contextPath}/showAddActivityOrganized"><button
										type="submit" class="btn btn-success">Add</button> </a>
							</c:if>
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


										<div class="col-xs-12">
											<h5 class="title pull-left">
												Sports/Cultural Activity/Competition <Strong>Orgnaized</Strong>
												at Institute

											</h5>
											<div class="col-xs-12"></div>
											<div id="example-4_wrapper"
												class="dataTables_wrapper form-inline">

												<table id="example-4" class="display dataTable" role="grid"
													aria-describedby="example-4_info" style="width: 100%;">
													<thead>
														<tr>
															<th rowspan="2">Sr No</th>

															<th rowspan="2">Type of Activity</th>
															<th rowspan="2">Level of Activity</th>
															<th rowspan="2">Name of Activity</th>
															<th>From Date</th>
															<th>To Date</th>
															<!-- <th style="text-align: center;" colspan="2">Duration</th> -->
															<th rowspan="2">Total Participants</th>
															<th rowspan="2">Action</th>

														</tr>
														<!-- <tr>
															<th>From Date</th>
															<th>To Date</th>
														</tr> -->
													</thead>
													<tbody>
												<c:forEach items="${instActList}" var="activList"
													varStatus="count">
													<tr>
														<%-- <td><input type="checkbox" class="chk"
															name="bookIds" id="bookIds${count.index+1}"
															value="${bList.bookId}" /></td> --%>
														<td align="center">${count.index+1}</td>
														<td>${activList.instActivityType}</td>
														<td>${activList.instActivityLevel}</td>
														<td>${activList.instActivityName}</td>
														<td  align="center">${activList.instActivityFromdt}</td>
														<td  align="center">${activList.instActivityTodt}</td>
														<td>${activList.instActivityParticipation}</td>
														
														<td align="center"> <c:if test="${editAccess==0}"> 
																<a 
																	href="${pageContext.request.contextPath}/editActivity/${activList.instActivityId}">
																	<span class="glyphicon glyphicon-edit"  title="Edit"
																	data-animate=" animated fadeIn " rel="tooltip"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;
														 </c:if><c:if test="${deleteAccess==0}">
																<a
																	href="${pageContext.request.contextPath}/deleteActivity/${activList.instActivityId}"
																	onClick="return confirm('Are you sure want to delete this record');"
																	rel="tooltip" data-color-class="danger"  title="Delete"
																	data-animate=" animated fadeIn " data-toggle="tooltip"
																	data-original-title="Delete  record"><span
																	class="glyphicon glyphicon-remove"></span></a>
															</c:if></td>
													</tr>
												</c:forEach>

											</tbody>
												</table>

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
					<h4 class="modal-title">Sports / Cultural Activity /
						Competition organized at Institute</h4>
				</div>
				<div class="modal-body">

					<div class="form-group">
						<label class="control-label col-sm-3" for="academicYear">Academic
							Year</label> <select id="academicYear" name="academicYear"
							class="form-control" required>
							<option value="2018-2019">2018-2019</option>
							<option value="2017-2018">2017-2018</option>
							<option value="2016-2017">2016-2017</option>
							<option value="2015-2016">2015-2016</option>

						</select>
					</div>


					<div class="form-group">

						<label class="control-label col-sm-3" for="activityName">Name
							of Activity </label> <input type="text" class="form-control"
							id="activityName" name="activityName"
							placeholder="Name of Activity" value="${page.pageName}" required>
					</div>



					<div class="form-group">

						<label class="control-label col-sm-3" for="fromDate">From
							Date </label> <input type="date" class="form-control" id="fromDate"
							name="fromDate" value="${page.pageName}" required> <label
							class="control-label col-sm-3" for="toDate">To Date </label> <input
							type="date" class="form-control" id="toDate" name="toDate"
							value="${page.pageName}" required>
					</div>
					<div class="form-group">

						<label class="control-label col-sm-3" for="totalParticipants">Total
							Participants </label> <input type="text" class="form-control"
							id="totalParticipants" name="totalParticipants"
							placeholder="Total Participants" value="${page.pageName}"
							required>
					</div>




					<div class="form-group">
						<label class="control-label col-sm-3" for="activityLevel">Level
							of Activity Year</label> <select id="activityLevel" name="activityLevel"
							class="form-control" required>
							<option value="State">State</option>
							<option value="National">National</option>
							<option value="International">International</option>

						</select>
					</div>


					<div class="form-group">
						<label class="control-label col-sm-3" for="activityType">Type
							of Activity Year</label> <select id="activityType" name="activityType"
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

			var totalParticipants = document
					.getElementById("totalParticipants").value;
			var activityLevel = document.getElementById("activityLevel").value;
			var fromDate = document.getElementById("fromDate").value;
			var toDate = document.getElementById("toDate").value;
			var activityType = document.getElementById("activityType").value;
			//alert("noStud"+noStud);
			var dataTable = $('#example-1').DataTable();

			dataTable.row.add(
					[ i + 1, activityName, academicYear, fromDate, toDate,
							totalParticipants, activityLevel, activityType ])
					.draw();
			document.getElementById("index").value = i + 1;
		}
	</script>






</body>
</html>