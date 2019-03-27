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

				<%-- <div class="col-xs-12">
					<div class="page-title">

						<div class="pull-left">
							<!-- PAGE HEADING TAG - START -->
							<h1 class="title">${title}</h1>
							<!-- PAGE HEADING TAG - END -->
						</div>


					</div>
				</div> --%>
				<div class="clearfix"></div>
				<!-- MAIN CONTENT AREA STARTS -->

				<div class="col-lg-12"></div>



				<div class="col-lg-12">
					<section class="box ">

						<header class="panel_header">
							<h2 class="title pull-left">${title}</h2>

							<div class="actions panel_actions pull-right">

								<c:if test="${isAdd==1}">
									<a href="${pageContext.request.contextPath}/showAddConsultancy"><button
											type="button" class="btn btn-success">Add</button></a>
								</c:if>


							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertPublicationDetail"
										method="post" name="form_sample_2" id="form_sample_2"
										onsubmit="return confirm('Do you really want to submit the form?');">

										<!-- <ul class="nav nav-tabs">
											<li class="active"><a href="#home" data-toggle="tab">
													<i class="fa fa-home"></i> Consultancy
											</a></li>

										</ul> -->

										<!-- 	<div class="tab-content">
											<div class="tab-pane fade in active" id="home"> -->

										<div>
											<table id="example-1"
												class="table table-striped dt-responsive display">
												<thead>
													<tr>
														<th>Sr No</th>
														<th>Nature of Consultancy</th>
														<th>Sponsoring Industry</th>
														<th>Consultancy Amount</th>
														<th>Consultancy Period</th>
														<th>Project Completed</th>
														<th>Action</th>
													</tr>
												</thead>

												<tbody>
													<c:forEach items="${list}" var="list" varStatus="count">
														<tr>

															<td style="text-align: center; ">${count.index+1}</td>
															<td>${list.consNature}</td>
															<td>${list.consSponsor}</td>
															<td style="text-align: right; ">${list.consAmount}</td>
															<td style="text-align: right; ">${list.consPeriod}</td>
															<td><c:set value="-" var="isCompleted"></c:set> <c:choose>
																	<c:when test="${list.isConsCompleted==1}">
																		<c:set value="Yes" var="isCompleted"></c:set>
																	</c:when>
																	<c:otherwise>
																		<c:set value="No" var="isCompleted"></c:set>
																	</c:otherwise>
																</c:choose>${isCompleted}</td>


															<td style="text-align: center; "><c:if test="${isEdit==1}">
																	<a
																		href="${pageContext.request.contextPath}/editConsultancy/${list.consId}"><span
																		class="glyphicon glyphicon-edit" title="Edit"
																		data-animate=" animated fadeIn " rel="tooltip"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;
															</c:if> <c:if test="${isDelete==1}">
																	<a
																		href="${pageContext.request.contextPath}/deleteConsultancy/${list.consId}"
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

										<!-- </div>


										</div> -->
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
					<h4 class="modal-title">Consultancy Details</h4>
				</div>
				<div class="modal-body">
					<%-- <form role="form"
						action="${pageContext.request.contextPath}/showModuleForm"
						method="get"> --%>



					<div class="form-group">
						<label class="control-label col-sm-6" for="page_name">Academic
							Year</label> <select id="academicYear" name="qualType"
							class="form-control" onchange="showForm()" required>
							<option value="2018-2019">2018-2019</option>
							<option value="2017-2018">2017-2018</option>
							<option value="2016-2017">2016-2017</option>
							<option value="2016-2017">2015-2016</option>

						</select>
					</div>

					<div class="form-group">

						<label class="control-label col-sm-6" for="page_name">Nature
							of Consultancy </label>
						<!-- <div class="col-sm-3"> -->
						<input type="text" class="form-control" id="nature"
							placeholder="Nature of Consultancy" name="nature" placeholder=""
							value="${page.pageName}">
						<!-- </div> -->
					</div>

					<div class="form-group">

						<label class="control-label col-sm-6" for="page_name">Sponsoring
							Agency/Industry </label>
						<!-- <div class="col-sm-3"> -->
						<input type="text" class="form-control" id="sponser"
							placeholder="Sponsoring Agency/Industry" name="sponser"
							placeholder="" value="${page.pageName}">
						<!-- </div> -->
					</div>

					<div class="form-group">

						<label class="control-label col-sm-6" for="page_name">Amount
							of Consultancy </label>
						<!-- <div class="col-sm-3"> -->
						<input type="text" class="form-control" id="amount"
							placeholder="Amount of Consultancy" name="amount" placeholder=""
							value="${page.pageName}">
						<!-- </div> -->
					</div>
					<div class="form-group">

						<label class="control-label col-sm-6" for="page_name">Consultancy
							Period </label>
						<!-- <div class="col-sm-3"> -->
						<input type="text" class="form-control" id="conPeriod"
							placeholder="Consultancy Period" name="conPeriod" placeholder=""
							value="${page.pageName}">
						<!-- </div> -->
					</div>

					<label class="control-label col-sm-3" for="page_name">Project
						Completed </label>
					<div class="col-sm-1"></div>
					<div class="col-sm-2">
						Yes <input type="radio" name="consultancy" id="consultancy"
							checked value="1"> No<input type="radio"
							name="consultancy" id="consultancy" value="0">
					</div>
					<button type="submit" class="btn btn-primary" onclick="getData()">Submit</button>
					<!-- 	</form> -->
				</div>
			</div>
		</div>
	</div>





	<script type="text/javascript">
		function getData() {
			//alert("hii");
			var i = parseInt(document.getElementById("index").value);
			var nature = document.getElementById("nature").value
			var sponser = document.getElementById("sponser").value
			//alert(qualName);
			var amount = document.getElementById("amount").value
			var academicYear = document.getElementById("academicYear").value
			var conPeriod = document.getElementById("conPeriod").value
			var consultancy = document.getElementById("consultancy").value
			var temp;
			if (consultancy == 1) {
				temp = "Yes";
			} else {
				temp = "No";
			}
			var dataTable = $('#example-1').DataTable();

			dataTable.row.add(
					[ i + 1, academicYear, nature, sponser, amount, conPeriod,
							temp

					]).draw();

			document.getElementById("index").value = i + 1;

		}
	</script>



</body>
</html>



