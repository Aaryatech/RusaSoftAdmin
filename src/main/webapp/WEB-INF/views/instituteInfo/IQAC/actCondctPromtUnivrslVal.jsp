<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="java.util.UUID"%>
<%@ page import="java.security.MessageDigest"%>
<%@ page import="java.math.BigInteger"%>



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
				<div class="col-lg-12">
					<section class="box ">

						<header class="panel_header">
							<h2 class="title pull-left">${title}</h2>

							<div class="actions panel_actions pull-right">
								<c:if test="${addAccess == 0}">
									<a
										href="${pageContext.request.contextPath}/addActCndctPromtUnivrslVal"><button
											type="submit" class="btn btn-success">
											<i class="${sessionScope.addIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Add
										</button> </a>
								</c:if>
							</div>

						</header>


<%
												UUID uuid = UUID.randomUUID();
													MessageDigest md = MessageDigest.getInstance("MD5");
													byte[] messageDigest = md.digest(String.valueOf(uuid).getBytes());
													BigInteger number = new BigInteger(1, messageDigest);
													String hashtext = number.toString(16);
													session = request.getSession();
													session.setAttribute("generatedKey", hashtext);
											%>
						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/delActivitiesCndctPrmotUnvrslVal/0/<%out.println(hashtext);%>"
										method="get" name="form_sample_2" id="form_sample_2">




										<div class="col-xs-12">
											<div class="col-xs-12"></div>
											<div id="22"
												class="dataTables_wrapper form-inline">

												<table id="example-1" class="table table-striped dt-responsive display">

													<thead>
														<tr>
															<th class="check" style="text-align: center; width: 5%;"><input
																type="checkbox" name="selAll" id="selAll"
																onClick="selectedInst(this)" /> Select All</th>
															<th>Sr No</th>
															<th>Title of Programme/ Activity</th>
															<th>From Date</th>
															<th>To Date</th>
															<th>No. of Participants</th>
															<th>Action</th>

														</tr>
														 
													</thead>
													<tbody>
														<c:forEach items="${actCndctPrgList}" var="actCndctPrgList"
															varStatus="count">
															<tr>
																<td align="center"><input type="checkbox"
																	class="chk" name="activityId"
																	id="activityId${count.index+1}"
																	value="${actCndctPrgList.actCndctId}" /></td>
																<td align="center">${count.index+1}</td>
																<td>${actCndctPrgList.ttleProgrmAct}</td>
																<td align="center">${actCndctPrgList.fromDate}</td>
																<td align="center">${actCndctPrgList.toDate}</td>
																<td>${actCndctPrgList.noOfParticipant}</td>

																<td align="center"><c:if test="${editAccess==0}">
																		<a
																			href="${pageContext.request.contextPath}/editActCndctPromtUnivrsalValu/${actCndctPrgList.actCndctId}">
																			<span class="glyphicon glyphicon-edit" title="Edit"
																			data-animate=" animated fadeIn " rel="tooltip"></span>
																		</a>&nbsp;&nbsp;&nbsp;&nbsp;
														 </c:if> <c:if test="${deleteAccess==0}">
																		<a
																			href="${pageContext.request.contextPath}/deleteActCndctPromtUnivrsalValu/${actCndctPrgList.actCndctId}/<%out.println(hashtext);%>"
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
														style="align-content: center; width: 113px; margin-left: 40px; margin-top: 10px;">
														<i class="${sessionScope.deleteIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Delete
													</button>
												</c:if>
												<input type="hidden" id="edit_accOff_id"
													name="edit_accOff_id" value="0">


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
	<script type="text/javascript">
		function selectedInst(source) {

			checkboxes = document.getElementsByName('activityId');

			for (var i = 0, n = checkboxes.length; i < n; i++) {
				checkboxes[i].checked = source.checked;

			}

		}
	</script>


</body>
</html>