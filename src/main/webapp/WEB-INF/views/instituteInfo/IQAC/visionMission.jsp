<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<!DOCTYPE html>
<html class=" ">
<head>
<c:url value="/saveProgramVission" var="saveProgramVission"></c:url>
<c:url value="/deleteProgramVission" var="deleteProgramVission"></c:url>
<c:url value="/editProgramVission" var="editProgramVission"></c:url>

<c:url value="/saveProgramMission" var="saveProgramMission"></c:url>
<c:url value="/deleteProgramMission" var="deleteProgramMission"></c:url>
<c:url value="/editProgramMission" var="editProgramMission"></c:url>

<c:url value="/saveProgramPeo" var="saveProgramPeo"></c:url>
<c:url value="/deleteProgramPeo" var="deleteProgramPeo"></c:url>
<c:url value="/editProgramPeo" var="editProgramPeo"></c:url>

<c:url value="/saveProgramPo" var="saveProgramPo"></c:url>
<c:url value="/deleteProgramPo" var="deleteProgramPo"></c:url>
<c:url value="/editProgramPo" var="editProgramPo"></c:url>

<c:url value="/saveProgramPso" var="saveProgramPso"></c:url>
<c:url value="/deleteProgramPso" var="deleteProgramPso"></c:url>
<c:url value="/editProgramPso" var="editProgramPso"></c:url>

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
<body onload="abc()" class=" ">
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


								<a href="${pageContext.request.contextPath}/showProgramList"><button
										type="button" class="btn btn-success">Back</button></a>

							</div>

						</header>

						<div class="content-body">
							<div class="row">
								<div class="col-xs-12">

									<div class="form-group">
										<label class="control-label col-sm-2" for="status">
											Program Name:</label> <label class="control-label col-sm-10"
											for="status"><strong>
												${programDetail.nameOfProgram} </strong></label>
									</div>

									<div class="form-group">
										<label class="control-label col-sm-2" for="status">
											Duration:</label> <label class="control-label col-sm-10" for="status">
											<strong>${programDetail.monthDuration}</strong>
										</label>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2" for="status">
											Sanctioned Intake:</label> <label class="control-label col-sm-10"
											for="status"><strong>${programDetail.sanctionalIntake}</strong></label>
										<input type="hidden" id="programId" name="programId"
											value="${programDetail.programId}">

									</div>
									<div class="form-group"></div>
								</div>
								<div class="col-md-12">

									<ul class="nav nav-tabs">

										<li class="active"><a href="#Vision" data-toggle="tab">
												<i class="fa fa-home"></i> Vision
										</a></li>
										<li><a href="#Mission" data-toggle="tab"> <i
												class="fa fa-home"></i> Mission
										</a></li>
									</ul>

									<div class="tab-content">



										<!-- 		Vision -->
										<div class="tab-pane fade in active " id="Vision">

											<!-- 	<form action="" method="post">		 -->
											<form class="form-horizontal"
												action="${pageContext.request.contextPath}/#" method="post"
												name="submitProgramVission" id="submitProgramVission"
												onsubmit="return confirm('Do you really want to add Program Vission?');">

												<div class="row">

													<input type="hidden" id="programVissionId"
														name="programVissionId" value="0"> <label
														class="col-sm-2 text-left" for="programVission">
														Vision<span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="text" class="form-control"
															id="programVission" name="programVission"
															placeholder="Program Vission" required>
													</div>
												</div>

												<br />
												<div class="row">

													<label class="col-sm-2 text-left"
														for="programVissionRemark"> Remark(If Any) </label>
													<div class="col-sm-6">
														<input type="text" class="form-control"
															id="programVissionRemark" name="programVissionRemark"
															placeholder="Program Vission Remark">
													</div>

													<div class="col-sm-4">

														<input type="button" class="btn btn-info"
															onclick="saveProgramVission()" value="Add">
													</div>
												</div>
												<br>


											</form>

											<!-- 		</form>			 -->
											<div align="center" id="loader1" style="display: none;">
												<img
													src="${pageContext.request.contextPath}/resources/assets/images/loader.gif"
													style="width: 50px; height: 50px;">
											</div>


											<div class="row">


												<div class="col-xs-12">
													<div class="table-responsive">
														<table class="table table-bordered" id="table1">
															<thead>

																<tr>
																	<th width="10%">Sr No</th>
																	<th>Vision</th>
																	<th>Remark</th>
																	<th width="10%">Action</th>

																</tr>

															</thead>
															<tbody>
																<c:forEach items="${programVisionList}" var="list"
																	varStatus="count">
																	<tr>

																		<td>${count.index+1}</td>
																		<td>${list.visionText}</td>
																		<td>${list.visionRemark}</td>
																		<td><a href="#"
																			onclick="editProgramVission(${list.visionId})"><span
																				class="glyphicon glyphicon-edit" title="Edit"
																				data-animate=" animated fadeIn " rel="tooltip"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;

																			<a href="#"
																			onclick="deleteProgramVission(${list.visionId})"
																			rel="tooltip" data-color-class="danger"
																			title="Delete" data-animate=" animated fadeIn "
																			data-toggle="tooltip"
																			data-original-title="Delete  record"><span
																				class="glyphicon glyphicon-remove"></span></a></td>
																	</tr>
																</c:forEach>
															</tbody>
														</table>
													</div>
												</div>

											</div>


										</div>




										<!-- 	Mission -->
										<div class="tab-pane fade in " id="Mission">

											<!-- 	<form action="" method="post">		 -->
											<form class="form-horizontal"
												action="${pageContext.request.contextPath}/#" method="post"
												name="submitProgramMission" id="submitProgramMission"
												onsubmit="return confirm('Do you really want to add Program Mission?');">
												<div class="row">

													<input type="hidden" id="programMissionId"
														name="programMissionId" value="0"> <label
														class="col-sm-2 text-left" for="programMission">
														Mission<span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="text" class="form-control"
															id="programMission" name="programMission"
															placeholder="Program Mission " required>
													</div>
												</div>

												<br />
												<div class="row">

													<label class="col-sm-2 text-left"
														for="programMissionRemark"> Remark(If Any) </label>
													<div class="col-sm-6">
														<input type="text" class="form-control"
															id="programMissionRemark" name="programMissionRemark"
															placeholder="Program Mission Remark">
													</div>

													<div class="col-sm-4">

														<input type="button" onclick="saveProgramMission()"
															class="btn btn-info" value="Add">
													</div>
												</div>
											</form>
											<!-- 		</form>			 -->
											<br>

											<div align="center" id="loader2" style="display: none;">
												<img
													src="${pageContext.request.contextPath}/resources/assets/images/loader.gif"
													style="width: 50px; height: 50px;">
											</div>

											<div class="row">


												<div class="col-xs-12">
													<div class="table-responsive">
														<table class="table table-bordered" id="table2">
															<thead>
																<tr>
																	<th width="10%">Sr No</th>
																	<th>Mission</th>
																	<th>Remark</th>
																	<th width="10%">Action</th>

																</tr>
															</thead>

															<tbody>
																<c:forEach items="${programMissionList}" var="list"
																	varStatus="count">
																	<tr>

																		<td>${count.index+1}</td>
																		<td>${list.missionText}</td>
																		<td>${list.missionRemark}</td>
																		<td><a href="#"
																			onclick="editProgramMission(${list.missionId})"><span
																				class="glyphicon glyphicon-edit" title="Edit"
																				data-animate=" animated fadeIn " rel="tooltip"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;

																			<a href="#"
																			onclick="deleteProgramMission(${list.missionId})"
																			rel="tooltip" data-color-class="danger"
																			title="Delete" data-animate=" animated fadeIn "
																			data-toggle="tooltip"
																			data-original-title="Delete  record"><span
																				class="glyphicon glyphicon-remove"></span></a></td>
																	</tr>
																</c:forEach>

															</tbody>
														</table>
													</div>
												</div>

											</div>


										</div>


									</div>

									<!-- 	poo-ps -->

									<!-- 		</form>			 -->



								</div>



							</div>



							<!--  -->


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
		$("#example-2").dataTable(
				{
					responsive : true,
					aLengthMenu : [ [ 10, 25, 50, 100, -1 ],
							[ 10, 25, 50, 100, "All" ] ]
				});
		$("#example-3").dataTable(
				{
					responsive : true,
					aLengthMenu : [ [ 10, 25, 50, 100, -1 ],
							[ 10, 25, 50, 100, "All" ] ]
				});
		$("#example-5").dataTable(
				{
					responsive : true,
					aLengthMenu : [ [ 10, 25, 50, 100, -1 ],
							[ 10, 25, 50, 100, "All" ] ]
				});
	</script>





</body>
</html>