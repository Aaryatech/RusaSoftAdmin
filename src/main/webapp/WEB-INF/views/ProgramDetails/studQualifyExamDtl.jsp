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
</head>
<!-- END HEAD -->

<!-- BEGIN BODY -->
<body class=" " onload="hideText()">
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
				<%-- 
				<div class='col-xs-12'>
					<div class="page-title">

						<div class="pull-left">
							<!-- PAGE HEADING TAG - START -->
							<h1 class="title">${title}</h
							<!-- PAGE HEADING TAG - END -->
						</div>


					</div>
				</div> --%>
				<div class="clearfix"></div>
				<!-- MAIN CONTENT AREA STARTS -->



				<div class="col-lg-12">
					<section class="box ">
						<header class="panel_header">
							<h2 class="title pull-left">${title}</h2>
							<div class="actions panel_actions pull-right">
								<%-- <a
									href="${pageContext.request.contextPath}/showAddStudentAttendActivity"><button
										type="submit" class="btn btn-success">Add</button></a> --%>
										
										<c:if test="${isAdd==1}">
 											 <a title="Add"
													href="${pageContext.request.contextPath}/addStudQualifyExmDtl"><button
														type="button" class="btn btn-success"><i class="${sessionScope.addIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Add</button></a>
										</c:if>
							</div>
						</header>
						<div class="content-body">
							<div class="row">
								<div class="col-xs-12">
								<form class="form-horizontal"
										action="${pageContext.request.contextPath}/delSlectedStudQlifExmDtl/0"
										method="get" name="form_sample_2" id="form_sample_2">
									<table class="table table-striped dt-responsive display"
										id="example-1">
										<thead>
											<tr>
											<th class="check" style="text-align: center; width: 5%;"><input
														type="checkbox" name="selAll" id="selAll"
														onClick="selectedInst(this)" /> Select All</th>
												<th>Sr.No.</th>
												<th>Name of Qualifying Exam</th>
												<th>Level of Exam</th>
												<th>No. of Students Appeared</th>
												<th> No. of Students Qualified</th>
												<th>Action</th>
											</tr>

										</thead>
										<tbody>
											<c:forEach items="${studQlifExamList}" var="list" varStatus="count">
												<tr>
													<td align="center"><input type="checkbox" class="chk" name="studQlfExmId"
															id="studQlfExmIds${count.index+1}" value="${list.studExmId}" /></td>
													<td style="text-align: center">${count.index+1}</td>
													<td>${list.nameQualifExam}</td>
													<td >${list.levelExam}</td>
													<td>${list.noStudAppeared}</td>
													<td >${list.noStudQualified}</td>
													<td style="text-align: center; "><c:if test="${isEdit==1}">
															<a
																href="${pageContext.request.contextPath}/editStudQualifyExam/${list.studExmId}"><span
																class="glyphicon glyphicon-edit" title="Edit"
																data-animate=" animated fadeIn " rel="tooltip"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;
															</c:if> <c:if test="${isDelete==1}">
															<a
																href="${pageContext.request.contextPath}/deleteStudQualifyExam/${list.studExmId}"
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
											<button class="btn btn-primary" id="deleteId"
												onClick="var checkedVals = $('.chk:checkbox:checked').map(function() { return this.value;}).get();checkedVals=checkedVals.join(',');if(checkedVals==''){alert('No Rows Selected');return false;	}else{   return confirm('Are you sure want to delete record');}"
												style="align-content: center; width: 113px; margin-left: 40px;">
												<i class="${sessionScope.deleteIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Delete
											</button>
										</c:if>
									</form>
								</div>




								<!-- <div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										<button type="submit" class="btn btn-primary">Submit</button>
										<button type="reset" class="btn btn-default">Reset</button>
									</div>
								</div> -->







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
	function selectedInst(source) {

		checkboxes = document.getElementsByName('studQlfExmId');

		for (var i = 0, n = checkboxes.length; i < n; i++) {
			checkboxes[i].checked = source.checked;

		}

	}
	</script>	
	
	<script>
		function clearSessionAttribute() {

			$.getJSON('${clearSessionAttribute}', {

				ajax : 'true',

			}, function(data) {

			});

		}
	</script>
</body>
</html>
