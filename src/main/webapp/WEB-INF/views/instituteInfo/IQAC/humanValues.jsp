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
								<a href="${pageContext.request.contextPath}/showAddHumanValues"><button
										type="button" class="btn btn-success">Add</button></a>
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertPublicationDetail"
										method="post" name="form_sample_2" id="form_sample_2"
										onsubmit="return confirm('Do you really want to submit the form?');">


										<div id="example-4_wrapper"
											class="dataTables_wrapper form-inline">

											<table id="example-4" class="display dataTable" role="grid"
												aria-describedby="example-4_info" style="width: 100%;">
												<thead>

													<tr>
														<th rowspan="2">Sr No</th>
														<th rowspan="2">Title of Program/Activity</th>
														<th style="text-align: center;" colspan="2">Duration</th>
														<th rowspan="2">No. of Participants</th>
														<th rowspan="2">Action</th>

													</tr>
													<tr>

														<th>From Date</th>
														<th>To Date</th>


													</tr>

												</thead>

												<tbody>
													<c:forEach items="${valuelist}" var="value"
														varStatus="count">
														<tr>

															<td style="text-align: center">${count.index+1}</td>

															<td style="text-align: left"><c:out
																	value="${value.activityName}" /></td>



															<td style="text-align: left"><c:out
																	value="${value.activityFromdt}" /></td>


															<td style="text-align: left"><c:out
																	value="${value.activityTodt}" /></td>


															<td style="text-align: left"><c:out
																	value="${value.activityPcount}" /></td>

															<td align="center"><c:if test="${editAccess == 0}">
																	<a
																		href="${pageContext.request.contextPath}/editValue/${value.valueId}"
																		title="Edit" rel="tooltip" data-color-class="detail"
																		data-animate=" animated fadeIn " data-toggle="tooltip"
																		data-original-title="Edit"><span
																		class="glyphicon glyphicon-edit"></span></a> |</c:if> <c:if
																	test="${deleteAccess == 0}">
																	<a
																		href="${pageContext.request.contextPath}/deleteHumanValues/${value.valueId}"
																		onClick="return confirm('Are you sure want to delete this record');"
																		rel="tooltip" data-color-class="danger" title="Delete"
																		data-animate=" animated fadeIn " data-toggle="tooltip"
																		data-original-title="Delete  record"><span
																		class="glyphicon glyphicon-remove"></span></a>

																</c:if></td>




														</tr>
													</c:forEach>
												</tbody>


											</table>
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
					<h4 class="modal-title">Human Values and Professional Ethics</h4>
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
						<label class="control-label col-sm-3" for="finantialYear">Title
							of Programme/Activity </label> <select id="title" name="title"
							class="form-control" required>

							<option value="Truth">Truth</option>
							<option value="Righteous Conduct">Righteous Conduct</option>
							<option value="Non violence">Non violence</option>
							<option value="Peace">Peace</option>
							<option value=">National values">National values</option>
							<option value="National Integration ">National
								Integration</option>
							<option value="Communal harmony">Communal harmony</option>
							<option value="Fundamental Duties">Fundamental Duties</option>

						</select>
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

						<label class="control-label col-sm-3" for="participant">No.
							of Participants </label> <input type="text" class="form-control"
							id="participant" name="participant"
							placeholder="No. of Participants" value="${page.pageName}"
							required>
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
			var year = document.getElementById("academicYear").value;
			var i = parseInt(document.getElementById("index").value);
			var title = document.getElementById("title").value;
			var participant = document.getElementById("participant").value;
			var fromDate = document.getElementById("fromDate").value;
			var toDate = document.getElementById("toDate").value;

			//alert("noStud"+noStud);
			var dataTable = $('#example-1').DataTable();

			dataTable.row.add(
					[ i + 1, year, title, fromDate, toDate, participant ])
					.draw();
			document.getElementById("index").value = i + 1;
		}
	</script>






</body>
</html>