<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html class=" ">
<head>

<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<!-- CORE CSS TEMPLATE - END -->
<c:url var="clearSessionAttribute" value="/clearSessionAttribute" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
.btn1 {
	background-color: #ffffff; /* Blue background */
	border: none; /* Remove borders */
	color: white; /* White text */
	padding: 12px 16px; /* Some padding */
	font-size: 16px; /* Set a font size */
	cursor: pointer; /* Mouse pointer on hover */
}

/* Darker background on mouse-over */
.btn:hover {
	background-color: blue;
}
</style>
</head>
<!-- END HEAD -->

<!-- BEGIN BODY -->
<body class=" " onload="clearSessionAttribute()">
	<!-- START TOPBAR -->
	<jsp:include page="/WEB-INF/views/include/topbar.jsp"></jsp:include>
	<!-- END TOPBAR -->
	<!-- START CONTAINER -->
	<div class="page-container row-fluid container-fluid">

		<!-- SIDEBAR - START -->

		<jsp:include page="/WEB-INF/views/include/left.jsp"></jsp:include>
		<!--  SIDEBAR - END -->
		<!-- START CONTENT -->
		<!-- START CONTENT -->
		<section id="main-content" class=" ">
			<section class="wrapper main-wrapper row" style=''>


				<div class="clearfix"></div>
				<!-- MAIN CONTENT AREA STARTS -->



				<div class="col-lg-12">
					<section class="box ">
						<header class="panel_header">
							<h2 class="title pull-left">${title}</h2>
							<div class="actions panel_actions pull-right">
								<c:if test="${addAccess==0}">
									<a href="${pageContext.request.contextPath}/hodRegistration"><button
											type="button" class="btn btn-success">
											<i class="${sessionScope.addIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Add
										</button></a>
								</c:if>

							</div>

						</header>
						<form action="${pageContext.request.contextPath}/deleteHod/0"
							method="get" id="insListForm">
							<div class="content-body">
								<div class="row">
									<c:if test="${sessionScope.successMsg!=null}">
										<div class="col-lg-12">
											<div class="alert alert-success alert-dismissible fade in">
												<button type="button" class="close" data-dismiss="alert"
													aria-label="Close">
													<span aria-hidden="true">×</span>
												</button>
												<strong>Success : </strong> ${sessionScope.successMsg}
											</div>
										</div>
									</c:if>

									<div class="col-xs-12">


										<table id="example-1"
											class="table table-striped dt-responsive display">
											<thead>
												<tr>
													<th class="check" style="text-align: center; width: 5%;"><input
														type="checkbox" name="selAll" id="selAll"
														onClick="selectedInst(this)" /> Select All</th>
													<th width="5%">Sr No</th>
													<th>HOD Name</th>
													<th>Department</th>
													<th>Contact No.</th>
													<th>Email ID</th>
													<th width="20%">Action</th>
												</tr>
											</thead>


											<tbody>
												<c:forEach items="${hodList}" var="hod" varStatus="count">
													<tr>
														<td align="center"><input type="checkbox" class="chk"
															name="hodIds" id="hodIds${count.index+1}"
															value="${hod.facultyId}" /></td>
														<td align="center">${count.index+1}</td>
														<td align="left">${hod.facultyFirstName}</td>
														<td align="left">${hod.deptName}</td>
														<td align="center">${hod.contactNo}</td>
														<td align="left">${hod.email}</td>

														<td align="center"><a
															href="${pageContext.request.contextPath}/changeHod/${hod.facultyId}"
															title="Change HOD" rel="tooltip"
															data-color-class="detail"
															data-animate=" animated fadeIn " data-toggle="tooltip"
															data-original-title="Block"><span
																class="glyphicon glyphicon-list"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;
															<c:if test="${editAccess==0}">

																<a
																	href="${pageContext.request.contextPath}/showEditHod/${hod.facultyId}"
																	rel="tooltip" data-color-class="danger" title="Edit"
																	data-animate=" animated fadeIn " data-toggle="tooltip"><span
																	class="glyphicon glyphicon-edit"></span></a>

															</c:if>&nbsp;&nbsp;&nbsp;&nbsp;<c:if test="${deleteAccess==0}">
																<a
																	href="${pageContext.request.contextPath}/deleteHod/${hod.facultyId}"
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
										<c:if test="${deleteAccess==0}">

											<div class="form-group">

												<div class="col-sm-5">
													<div class="col-sm-1">
														<button type="submit" title="delete checked records"
															id="deleteId" class="btn btn-primary"
															onClick="var checkedVals = $('.chk:checkbox:checked').map(function() { return this.value;}).get();checkedVals=checkedVals.join(',');if(checkedVals==''){alert('No Rows Selected');return false;	}else{   return confirm('Are you sure want to delete record');}">
															<i class="${sessionScope.deleteIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Delete
														</button>


													</div>

												</div>
											</div>
										</c:if>
										<input type="hidden" id="edit_hod_id" name="edit_hod_id"
											value="0">

									</div>
								</div>
							</div>
						</form>
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
		function clearSessionAttribute() {

			$.getJSON('${clearSessionAttribute}', {

				ajax : 'true',

			}, function(data) {

			});

		}

		function selectedInst(source) {

			checkboxes = document.getElementsByName('hodIds');

			for (var i = 0, n = checkboxes.length; i < n; i++) {
				checkboxes[i].checked = source.checked;

			}

		}

		function showEditHod(hodId) {
			document.getElementById("edit_hod_id").value = hodId;//create this 
			var form = document.getElementById("insListForm");
			form.setAttribute("method", "post");

			form.action = ("showEditHod");
			form.submit();

		}
	</script>


</body>
</html>
