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
							<%-- 	<!-- PAGE HEADING TAG - START -->
							<h1 class="title">${title}</h1>
							<!-- PAGE HEADING TAG - END --> --%>
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
								 <c:if test="${isAdd==1}"> 
										<a
											href="${pageContext.request.contextPath}/addFacultyEmpower"
											data-toggle="modal"><button type="submit"
												class="btn btn-success">
												<i class="${sessionScope.addIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Add
											</button></a>
									 </c:if> 
								<%-- <a
									href="${pageContext.request.contextPath}/publicationList"><button
										type="button" class="btn btn-info">Back</button></a> <a
									class="box_toggle fa fa-chevron-down"></a> --%>
							</div>
						</header>
						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/deleteInstTraining/0"
										method="get" name="form_sample_2" id="form_sample_2">
										<div id="example-4_wrapper"
											class="dataTables_wrapper form-inline">
											<table id="example-4" class="display dataTable" role="grid"
												aria-describedby="example-4_info" style="width: 100%;">
												<thead>
													<tr>
														<!-- <th rowspan="2" class="check"
															style="text-align: center; width: 5%;"><input
															type="checkbox" name="selAll" id="selAll"
															onClick="selectedInst(this)" /> Select All</th> -->
														<th rowspan="2">Sr No</th>
														<th rowspan="2">Name of Activity</th>
														<th rowspan="2">Title</th>
														<!-- <th rowspan="2">Financial Support</th> -->
														<th rowspan="2">Amount Received From</th>
														<th style="text-align: center;" colspan="2">Duration</th>
														<th rowspan="2">Action</th>
													</tr>
													<tr>
														<th>From Date</th>
														<th>To Date</th>
												</thead>
												<tbody>
													<c:forEach items="${facEmpowrList}" var="facEmpwr"
														varStatus="count">
														<tr>
															<%-- <td align="center"><input type="checkbox"
																class="chk" name="trainingId"
																id="trainingIds${count.index+1}"
																value="${facEmpwr.facultyEmpwrmntId}" /></td> --%>
															<td align="center">${count.index+1}</td>
															<td align="left">${facEmpwr.nameOfAcitvity}</td>
															<td align="left">${facEmpwr.title}</td>
															<%-- <td align="left">${facEmpwr.financialSupport}</td> --%>
															<td align="left">${facEmpwr.amt_recvd_from}</td>
															<td align="center">${facEmpwr.fromDate}</td>
															<td align="center">${facEmpwr.toDate}</td>
														
															<td align="center"><c:if test="${isEdit==1}">
																	<a href="editFacultyEmpower/${facEmpwr.facultyEmpwrmntId}"><span class="glyphicon glyphicon-edit"
																		title="Edit" data-animate=" animated fadeIn "
																		rel="tooltip"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;
														</c:if> 
															<c:if test="${isDelete==1}"> 
																	<a
																		href="${pageContext.request.contextPath}/deleteFacultyEmpower/${facEmpwr.facultyEmpwrmntId}"
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
											<c:if test="${isDelete==1}">
												
												 <input type="submit" class="btn btn-primary" value="Delete"
												id="deleteId"
												onClick="var checkedVals = $('.chk:checkbox:checked').map(function() { return this.value;}).get();checkedVals=checkedVals.join(',');if(checkedVals==''){alert('No Rows Selected');return false;	}else{   return confirm('Are you sure want to delete record');}"
												style="align-content: center; width: 113px; margin-left: 40px;"> 


													

												</c:if> 
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


	<script type="text/javascript">

function showEditInstTrain(trainId){
	document.getElementById("training_id").value=trainId;
	
	var form=document.getElementById("form_sample_2");
    form.setAttribute("method", "post");

	form.action=("showEditInstTraining");
	form.submit();
}

</script>
	<script>
function selectedInst(source) {

	checkboxes = document.getElementsByName('trainingId');

	for (var i = 0, n = checkboxes.length; i < n; i++) {
		checkboxes[i].checked = source.checked;

	}

}
</script>


</body>
</html>


<!-- Title of administrative training program organized for non-teaching staff -->