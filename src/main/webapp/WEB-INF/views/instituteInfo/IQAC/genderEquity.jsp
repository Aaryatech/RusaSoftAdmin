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

				<div class="col-lg-12">
					<section class="box ">

						<header class="panel_header">
							<h2 class="title pull-left">${title}</h2>

							<div class="actions panel_actions pull-right">
								<c:if test="${addAccess == 0}">
									<a
										href="${pageContext.request.contextPath}/showAddGenderEquity"><button
											type="button" class="btn btn-success">
											<i class="${sessionScope.addIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Add
										</button></a>
								</c:if>
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/delSelGenderEqty/0"
										method="get" name="form_sample_2" id="form_sample_2">

										<div id="example-4_wrapper"
												class="dataTables_wrapper form-inline">

												<table id="example-4" class="display dataTable" role="grid"
													aria-describedby="example-4_info" style="width: 100%;">
													<thead>
														<tr>
															<th rowspan="2" class="check"
																style="text-align: center; width: 5%;"><input
																type="checkbox" name="selAll" id="selAll"
																onClick="selectedInst(this)" /> Select All</th>
															<th rowspan="2">Sr No</th>
															<th rowspan="2">Title of Program</th>
															<!-- <th style="text-align: center;" colspan="2">Date of
																Conduction</th> -->
															<th>From Date</th>
															<th>To Date</th>
															<th rowspan="2">No. of Participants</th>
															<th rowspan="2">Action</th>
														</tr>
														<!-- <tr>
															<th>From Date</th>
															<th>To Date</th>

														</tr> -->
													</thead>
													<tbody>
														<c:forEach items="${gndrList}" var="gndrList"
															varStatus="count">
															<tr>
																<td align="center"><input type="checkbox"
																	class="chk" name="gndreqtyId"
																	id="gndreqtyIdIds${count.index+1}"
																	value="${gndrList.gprogId}" /></td>
																<td style="text-align: center;">${count.index+1}</td>
																<td>${gndrList.gprogName}</td>
																<td align="center">${gndrList.gprogFromdt}</td>
																<td align="center">${gndrList.gprogTodt}</td>
																<td style="text-align: right;">${gndrList.gprogPcount}</td>
																<td align="center"><c:if test="${editAccess==0}">
																		<a
																			href="${pageContext.request.contextPath}/editGenderEquality/${gndrList.gprogId}"><span
																			class="glyphicon glyphicon-edit" title="Edit"
																			data-animate=" animated fadeIn " rel="tooltip"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;
																	
																	 </c:if> <c:if test="${deleteAccess==0}">
																		<a
																			href="${pageContext.request.contextPath}/deleteGenderEquality/${gndrList.gprogId}"
																			onClick="return confirm('Are you sure want to delete this record');"
																			rel="tooltip" data-color-class="danger"
																			title="Delete" data-animate=" animated fadeIn "
																			data-toggle="tooltip"
																			data-original-title="Delete  record"><span
																			class="glyphicon glyphicon-remove"></span></a>
																	</c:if></td>
															</tr>
														</c:forEach>

													</tbody>
												</table>
												<c:if test="${deleteAccess==0}">
													<button class="btn btn-primary" id="deleteId"
														onClick="var checkedVals = $('.chk:checkbox:checked').map(function() { return this.value;}).get();checkedVals=checkedVals.join(',');if(checkedVals==''){alert('No Rows Selected');return false;	}else{   return confirm('Are you sure want to delete record');}"
														style="align-content: center; width: 113px; margin-left: 40px;">
														<i class="${sessionScope.deleteIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Delete
													</button>
												</c:if>
												<input type="hidden" id="edit_accOff_id"
													name="edit_accOff_id" value="0">
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
	<script>
		function selectedInst(source) {

			checkboxes = document.getElementsByName('gndreqtyId');

			for (var i = 0, n = checkboxes.length; i < n; i++) {
				checkboxes[i].checked = source.checked;

			}

		}
	</script>
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
					[ i + 1, year, title, participant, fromDate, toDate ])
					.draw();
			document.getElementById("index").value = i + 1;
		}
	</script>






</body>
</html>