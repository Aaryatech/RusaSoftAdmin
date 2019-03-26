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

								<a href="${pageContext.request.contextPath}/showAwardDetails"><button
										type="button" class="btn btn-success">Add</button></a>

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

										<!-- <ul class="nav nav-tabs">
											<li class="active"><a href="#home" data-toggle="tab">
													<i class="fa fa-home"></i> Register
											</a></li>

										</ul> -->
<!-- 
										<div class="tab-content">
											<div class="tab-pane fade in active" id="home"> -->

												<div>

													<div class="col-xs-12">


														<div class="col-xs-12">


															<table id="example-1"
																class="table table-striped dt-responsive display">
																<thead>
																	<tr>
																		<th>Sr No</th>

																		<th>Name of Award/Recognition</th>
																		<th>Awarding Agency</th>
																		<th>Nature of Award/Recognition</th>
																		<th>Date</th>

																		<th>Action</th>
																	</tr>
																</thead>


																<tbody>
																	<c:forEach items="${facultyAwardList}" var="awardList"
																		varStatus="count">
																		<tr>
																			<%-- <td><input type="checkbox" class="chk" name="iqacIds"
															id="iqacIds${count.index+1}" value="${QList.iqacId}" /></td> --%>
																			<td style="text-align: center">${count.index+1}</td>

																			<td style="text-align: left"><c:out
																					value="${awardList.awardName}" /></td>

																			<td style="text-align: left"><c:out
																					value="${awardList.awardAuthority}" /></td>

																			<td style="text-align: left"><c:out
																					value="${awardList.awardNature}" /></td>



																			<td style="text-align: center"><c:out
																					value="${awardList.awardDate}" /></td>

																			<td align="center"><c:if
																					test="${editAccess == 0}">
																					<a
																						href="${pageContext.request.contextPath}/editAward/${awardList.awardId}"
																						title="Edit" rel="tooltip"
																						data-color-class="detail"
																						data-animate=" animated fadeIn "
																						data-toggle="tooltip"
																						data-original-title="Edit"><span
																						class="glyphicon glyphicon-edit"></span></a> </c:if> &nbsp; &nbsp; &nbsp; &nbsp;<c:if
																					test="${deleteAccess == 0}">
																					<a
																						href="${pageContext.request.contextPath}/deleteFacultyAward/${awardList.awardId}"
																						onClick="return confirm('Are you sure want to delete this record');"
																						rel="tooltip" data-color-class="danger"
																						title="Delete" data-animate=" animated fadeIn "
																						data-toggle="tooltip"
																						data-original-title="Delete  record"><span
																						class="glyphicon glyphicon-remove"></span></a>

																				</c:if> <%-- <a
											href="${pageContext.request.contextPath}/showFacultyDetails" title="Add Librarian"
											 rel="tooltip" data-color-class = "detail" data-animate=" animated fadeIn " data-toggle="tooltip" data-original-title="Block"><span
												class="glyphicon glyphicon-list"></span></a>
												
												<a
											href="${pageContext.request.contextPath}/showFacultyDetails" title="Add Dean R&D"
											 rel="tooltip" data-color-class = "detail" data-animate=" animated fadeIn " data-toggle="tooltip" data-original-title="Block"><span
												class="glyphicon glyphicon-List"></span></a>
											
												
												<a
											href="${pageContext.request.contextPath}/showFacultyDetails" title="Add Librarian"
											 rel="tooltip" data-color-class = "detail" data-animate=" animated fadeIn " data-toggle="tooltip" data-original-title="Block"><span
												class="glyphicon glyphicon-edit"></span></a> --%></td>




																		</tr>
																	</c:forEach>
																</tbody>

															</table>

														</div>

													</div>


													<div class="clearfix"></div>

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


