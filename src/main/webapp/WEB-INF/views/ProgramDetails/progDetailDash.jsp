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
										<li><a href="#PEO" data-toggle="tab"> <i
												class="fa fa-home"></i> Program Educational Objective
										</a></li>
										<li><a href="#PO" data-toggle="tab"> <i
												class="fa fa-home"></i> Program Outcome
										</a></li>
										<li><a href="#PSO" data-toggle="tab"> <i
												class="fa fa-home"></i> Program Specific Outcome
										</a></li>

										<!-- 	<li><a href="#PO-PSO" data-toggle="tab"> <i
													class="fa fa-home"></i>PO-PSO Mapping
											</a></li>
											 -->


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

													<table id="example-1"
														class="table table-striped dt-responsive display">
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
																		rel="tooltip" data-color-class="danger" title="Delete"
																		data-animate=" animated fadeIn " data-toggle="tooltip"
																		data-original-title="Delete  record"><span
																			class="glyphicon glyphicon-remove"></span></a></td>
																</tr>
															</c:forEach>

														</tbody>



													</table>

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

											<div align="center" id="loader2" style="display: none;">
												<img
													src="${pageContext.request.contextPath}/resources/assets/images/loader.gif"
													style="width: 50px; height: 50px;">
											</div>

											<div class="row">

												<div class="col-xs-12">

													<table id="example-2"
														class="table table-striped dt-responsive display">
														<thead>
															<tr>
																<th width="60%">Sr No</th>
																<th>Mission</th>
																<th>Remark</th>
																<th width="60%">Action</th>

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
																		rel="tooltip" data-color-class="danger" title="Delete"
																		data-animate=" animated fadeIn " data-toggle="tooltip"
																		data-original-title="Delete  record"><span
																			class="glyphicon glyphicon-remove"></span></a></td>
																</tr>
															</c:forEach>

														</tbody>



													</table>

												</div>

											</div>


										</div>

										<!-- 	PEO -->
										<div class="tab-pane fade in " id="PEO">

											<!-- 	<form action="" method="post">		 -->
											<form class="form-horizontal"
												action="${pageContext.request.contextPath}/#" method="post"
												name="submitProgramMission" id="submitProgramMission"
												onsubmit="return confirm('Do you really want to add Program Objective?');">
												<div class="row">

													<input type="hidden" id="programPeoId" name="programPeoId"
														value="0"> <label class="col-sm-2 text-left"
														for="peoText"> PEO<span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="peoText"
															name="peoText" placeholder="Program Education Objective"
															required>
													</div>
												</div>

												<br />
												<div class="row">

													<label class="col-sm-2 text-left" for="peoRemark">
														Remark(If Any) </label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="peoRemark"
															name="peoRemark"
															placeholder="Program Education Objective Remark">
													</div>

													<div class="col-sm-4">

														<input type="button" onclick="saveProgramPeo()"
															class="btn btn-info" value="Add">
													</div>
												</div>
											</form>
											<br>
											<!-- 		</form>			 -->
											<div align="center" id="loader3" style="display: none;">
												<img
													src="${pageContext.request.contextPath}/resources/assets/images/loader.gif"
													style="width: 50px; height: 50px;">
											</div>
											<div class="row">

												<div class="col-xs-12">

													<table id="example-5"
														class="table table-striped dt-responsive display">
														<thead>
															<tr>
																<th width="60%">Sr No</th>
																<th>PEO</th>
																<th>Remark</th>
																<th width="60%">Action</th>

															</tr>
														</thead>

														<tbody>
															<c:forEach items="${programEducationObjectiveList}"
																var="list" varStatus="count">
																<tr>

																	<td>${count.index+1}</td>
																	<td>${list.peoText}</td>
																	<td>${list.peoRemark}</td>
																	<td><a href="#"
																		onclick="editProgramPeo(${list.peoId})"><span
																			class="glyphicon glyphicon-edit" title="Edit"
																			data-animate=" animated fadeIn " rel="tooltip"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;

																		<a href="#" onclick="deleteProgramPeo(${list.peoId})"
																		rel="tooltip" data-color-class="danger" title="Delete"
																		data-animate=" animated fadeIn " data-toggle="tooltip"
																		data-original-title="Delete  record"><span
																			class="glyphicon glyphicon-remove"></span></a></td>
																</tr>
															</c:forEach>

														</tbody>



													</table>

												</div>

											</div>


										</div>


										<!-- 	PO -->
										<div class="tab-pane fade in " id="PO">

											<!-- 	<form action="" method="post">		 -->
											<form class="form-horizontal"
												action="${pageContext.request.contextPath}/#" method="post"
												name="submitProgramMission" id="submitProgramMission"
												onsubmit="return confirm('Do you really want to add Program Outcome?');">
												<div class="row">
													<input type="hidden" id="poId" name="poId"
														value="0"> <label class="col-sm-2 text-left"
														for="poText"> PO<span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="poText"
															name="poText" placeholder="Program Outcome Text" required>
													</div>
												</div>

												<br />
												<div class="row">

													<label class="col-sm-2 text-left" for="poRemark">
														Remark(If Any) </label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="poRemark"
															name="poRemark" placeholder="Program Outcome Remark">
													</div>

													<div class="col-sm-4">

														<input type="button" onclick="saveProgramPo()"
															class="btn btn-info" value="Add">
													</div>
												</div>
												<!-- 		</form>			 -->
											</form>
											<br>

											<div align="center" id="loader4" style="display: none;">
												<img
													src="${pageContext.request.contextPath}/resources/assets/images/loader.gif"
													style="width: 50px; height: 50px;">
											</div>

											<div class="row">

												<div class="col-xs-12">

													<table id="example-3"
														class="table table-striped dt-responsive display">
														<thead>
															<tr>
																<th width="60%">Sr No</th>
																<th>PO</th>
																<th>Remark</th>
																<th width="60%">Action</th>

															</tr>
														</thead>

														<tbody>
															<c:forEach items="${programOutcomeList}" var="list"
																varStatus="count">
																<tr>

																	<td>${count.index+1}</td>
																	<td>${list.poText}</td>
																	<td>${list.poRemark}</td>
																	<td><a href="#"
																		onclick="editProgramPo(${list.poId})"><span
																			class="glyphicon glyphicon-edit" title="Edit"
																			data-animate=" animated fadeIn " rel="tooltip"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;

																		<a href="#" onclick="deleteProgramPo(${list.poId})"
																		rel="tooltip" data-color-class="danger" title="Delete"
																		data-animate=" animated fadeIn " data-toggle="tooltip"
																		data-original-title="Delete  record"><span
																			class="glyphicon glyphicon-remove"></span></a></td>
																</tr>
															</c:forEach>

														</tbody>
													</table>

												</div>

											</div>


										</div>



										<!-- 	PSO -->
										<div class="tab-pane fade in " id="PSO">

											<!-- 	<form action="" method="post">		 -->
											<form class="form-horizontal"
												action="${pageContext.request.contextPath}/submitProgramOutcome"
												method="post" name="submitProgramMission"
												id="submitProgramMission"
												onsubmit="return confirm('Do you really want to add Program Outcome?');">
												<div class="row">


													<label class="col-sm-2 text-left" for="hh"> PSO<span
														class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="text" maxlength="10" class="form-control"
															id="hh" name="hh" placeholder="Another Scheme Name"
															required>
													</div>
												</div>

												<br />
												<div class="row">

													<label class="col-sm-2 text-left" for="hh">
														Remark(If Any) </label>
													<div class="col-sm-6">
														<input type="text" maxlength="10" class="form-control"
															id="hh" name="hh" placeholder="Another Scheme Name"
															required>
													</div>

													<div class="col-sm-4">

														<input type="submit" class="btn btn-info" value="Add">
													</div>
												</div>
											</form>
											<!-- 		</form>			 -->
											<div class="row">

												<div class="col-xs-12">

													<table id="example-4"
														class="table table-striped dt-responsive display">
														<thead>
															<tr>
																<th width="10%">Sr No</th>
																<th>PSO</th>
																<th width="10%">Action</th>

															</tr>
														</thead>



													</table>

												</div>

											</div>


										</div>

										<!-- 	poo-ps -->
										<div class="tab-pane fade in " id="PO-PSO">

											<!-- 	<form action="" method="post">		 -->

											<div class="row">




												<label class="col-sm-2 text-left" for="hh"> Program
													Outcome: </label> <label class="col-sm-2 text-left" for="hh">

												</label>




											</div>
											<!-- 		</form>			 -->
											<div class="row">

												<div class="col-xs-12">

													<table id="example-6"
														class="table table-striped dt-responsive display">
														<thead>
															<tr>
																<th width="10%">Sr No</th>
																<th>PSO</th>
																<th width="10%">Satisfying Value</th>


															</tr>
														</thead>



													</table>
													<br />

													<div class="col-sm-4">

														<input type="submit" class="btn btn-info" value="Submit">
													</div>

												</div>

											</div>


										</div>



									</div>



									<!--  -->

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
		function saveProgramVission() {

			var programVission = document.getElementById("programVission").value;
			var programVissionRemark = document
					.getElementById("programVissionRemark").value;
			var programId = document.getElementById("programId").value;
			var programVissionId = document.getElementById("programVissionId").value;
			
			if (programVission != "") {
				$('#example-1 td').remove();
				$("#loader1").show();
				$.getJSON('${saveProgramVission}',

				{
					programVission : programVission,
					programVissionRemark : programVissionRemark,
					programId : programId,
					programVissionId : programVissionId,
					ajax : 'true'

				}, function(data) {
				 
					$("#loader1").hide();
					  
					var dataTable = $('#example-1').DataTable();
					
					for(var i=0 ; i<data.programVissionList.length ;i++){
						
						 
						var acButton = '<a href="#"><span onclick="editProgramVission('+data.programVissionList[i].visionId+')" class="glyphicon glyphicon-edit" title="Edit" '
						+'data-animate=" animated fadeIn " rel="tooltip"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;'
						+' <a href="#" onclick="deleteProgramVission('+data.programVissionList[i].visionId+')"'+
						' rel="tooltip" data-color-class="danger" title="Delete" data-animate=" animated fadeIn " data-toggle="tooltip"'
						+'data-original-title="Delete  record"><span class="glyphicon glyphicon-remove"></span></a>'
						 
						dataTable.row.add(
								[ i + 1, data.programVissionList[i].visionText, data.programVissionList[i].visionRemark, acButton ])
								.draw();
					}
					 document.getElementById("programVissionId").value=0;
					 document.getElementById("programVissionRemark").value="";
					 document.getElementById("programVission").value="";
				});

			} else {
				alert("Enter Program Vission ");
			}

		}
		
		function deleteProgramVission(visionId) {

			var programId = document.getElementById("programId").value;
				$('#example-1 td').remove();
				$("#loader1").show();
				
				$.getJSON('${deleteProgramVission}',

				{
					visionId : visionId, 
					programId : programId,
					ajax : 'true'

				}, function(data) {
				 
					$("#loader1").hide();
					  
					var dataTable = $('#example-1').DataTable();
					
					for(var i=0 ; i<data.programVissionList.length ;i++){
						
						 
						var acButton = '<a href="#"><span onclick="editProgramVission('+data.programVissionList[i].visionId+')" class="glyphicon glyphicon-edit" title="Edit" '
						+'data-animate=" animated fadeIn " rel="tooltip"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;'
						+' <a href="#" onclick="deleteProgramVission('+data.programVissionList[i].visionId+')"'+
						' rel="tooltip" data-color-class="danger" title="Delete" data-animate=" animated fadeIn " data-toggle="tooltip"'
						+'data-original-title="Delete  record"><span class="glyphicon glyphicon-remove"></span></a>'
						 
						dataTable.row.add(
								[ i + 1, data.programVissionList[i].visionText, data.programVissionList[i].visionRemark, acButton ])
								.draw();
					}
					 
				});

			 

		}
		
		function editProgramVission(visionId) {
			$("#loader1").show();
				$.getJSON('${editProgramVission}',

				{
					visionId : visionId, 
					ajax : 'true'

				}, function(data) {
					$("#loader1").hide();
					document.getElementById("programVissionId").value=data.visionId;
					 document.getElementById("programVission").value=data.visionText;
					 document.getElementById("programVissionRemark").value=data.visionRemark;
					 
				});

			 

		}
		 
	</script>

	<script type="text/javascript">
		function saveProgramMission() {

			var programMission = document.getElementById("programMission").value;
			var programMissionRemark = document
					.getElementById("programMissionRemark").value;
			var programId = document.getElementById("programId").value;
			var programMissionId = document.getElementById("programMissionId").value;
			
			if (programMission != "") {
				$('#example-2 td').remove();
				$("#loader2").show();
				$.getJSON('${saveProgramMission}',

				{
					programMission : programMission,
					programMissionRemark : programMissionRemark,
					programId : programId,
					programMissionId : programMissionId,
					ajax : 'true'

				}, function(data) {
				 
					$("#loader2").hide();
					  
					var dataTable = $('#example-2').DataTable();
					
					for(var i=0 ; i<data.programMissionList.length ;i++){
						
						 
						var acButton = '<a href="#"><span onclick="editProgramMission('+data.programMissionList[i].missionId+')" class="glyphicon glyphicon-edit" title="Edit" '
						+'data-animate=" animated fadeIn " rel="tooltip"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;'
						+' <a href="#" onclick="deleteProgramMission('+data.programMissionList[i].missionId+')"'+
						' rel="tooltip" data-color-class="danger" title="Delete" data-animate=" animated fadeIn " data-toggle="tooltip"'
						+'data-original-title="Delete  record"><span class="glyphicon glyphicon-remove"></span></a>'
						 
						dataTable.row.add(
								[ i + 1, data.programMissionList[i].missionText, data.programMissionList[i].missionRemark, acButton ])
								.draw();
					}
					 document.getElementById("programMissionId").value=0;
					 document.getElementById("programMissionRemark").value="";
					 document.getElementById("programMission").value="";
				});

			} else {
				alert("Enter Program Mission ");
			}

		}
		
		function deleteProgramMission(missionId) {

			var programId = document.getElementById("programId").value;
				$('#example-2 td').remove();
				$("#loader2").show();
				
				$.getJSON('${deleteProgramMission}',

				{
					missionId : missionId, 
					programId : programId,
					ajax : 'true'

				}, function(data) {
				 
					$("#loader2").hide();
					  
					var dataTable = $('#example-2').DataTable();
					
					for(var i=0 ; i<data.programMissionList.length ;i++){
						
						 
						var acButton = '<a href="#"><span onclick="editProgramMission('+data.programMissionList[i].missionId+')" class="glyphicon glyphicon-edit" title="Edit" '
						+'data-animate=" animated fadeIn " rel="tooltip"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;'
						+' <a href="#" onclick="deleteProgramMission('+data.programMissionList[i].missionId+')"'+
						' rel="tooltip" data-color-class="danger" title="Delete" data-animate=" animated fadeIn " data-toggle="tooltip"'
						+'data-original-title="Delete  record"><span class="glyphicon glyphicon-remove"></span></a>'
						 
						dataTable.row.add(
								[ i + 1, data.programMissionList[i].missionText, data.programMissionList[i].missionRemark, acButton ])
								.draw();
					}
					 
				});

			 

		}
		
		function editProgramMission(missionId) {
			$("#loader2").show();
				$.getJSON('${editProgramMission}',

				{
					missionId : missionId, 
					ajax : 'true'

				}, function(data) {
					$("#loader2").hide();
					document.getElementById("programMissionId").value=data.missionId;
					 document.getElementById("programMission").value=data.missionText;
					 document.getElementById("programMissionRemark").value=data.missionRemark;
					 
				});

			 

		}
		 
		 
	</script>

	<script type="text/javascript">
		function saveProgramPeo() {

			var peoText = document.getElementById("peoText").value;
			var peoRemark = document
					.getElementById("peoRemark").value;
			var programId = document.getElementById("programId").value;
			var programPeoId = document.getElementById("programPeoId").value;
			
			if (peoText != "") {
				$('#example-5 td').remove();
				$("#loader3").show();
				$.getJSON('${saveProgramPeo}',

				{
					peoText : peoText,
					peoRemark : peoRemark,
					programId : programId,
					programPeoId : programPeoId,
					ajax : 'true'

				}, function(data) {
				 
					$("#loader3").hide();
					  
					var dataTable = $('#example-5').DataTable();
					
					for(var i=0 ; i<data.programEducationObjectiveList.length ;i++){
						
						 
						var acButton = '<a href="#"><span onclick="editProgramPeo('+data.programEducationObjectiveList[i].peoId+')" class="glyphicon glyphicon-edit" title="Edit" '
						+'data-animate=" animated fadeIn " rel="tooltip"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;'
						+' <a href="#" onclick="deleteProgramPeo('+data.programEducationObjectiveList[i].peoId+')"'+
						' rel="tooltip" data-color-class="danger" title="Delete" data-animate=" animated fadeIn " data-toggle="tooltip"'
						+'data-original-title="Delete  record"><span class="glyphicon glyphicon-remove"></span></a>'
						 
						dataTable.row.add(
								[ i + 1, data.programEducationObjectiveList[i].peoText, data.programEducationObjectiveList[i].peoRemark, acButton ])
								.draw();
					}
					 document.getElementById("programPeoId").value=0;
					 document.getElementById("peoText").value="";
					 document.getElementById("peoRemark").value="";
				});

			} else {
				alert("Enter Program Education Object ");
			}

		}
		
		function deleteProgramPeo(peoId) {

			var programId = document.getElementById("programId").value;
				$('#example-5 td').remove();
				$("#loader3").show();
				
				$.getJSON('${deleteProgramPeo}',

				{
					peoId : peoId, 
					programId : programId,
					ajax : 'true'

				}, function(data) {
				 
					$("#loader3").hide();
					  
					var dataTable = $('#example-5').DataTable();
					
					for(var i=0 ; i<data.programEducationObjectiveList.length ;i++){
						
						 
						var acButton = '<a href="#"><span onclick="editProgramPeo('+data.programEducationObjectiveList[i].peoId+')" class="glyphicon glyphicon-edit" title="Edit" '
						+'data-animate=" animated fadeIn " rel="tooltip"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;'
						+' <a href="#" onclick="deleteProgramPeo('+data.programEducationObjectiveList[i].peoId+')"'+
						' rel="tooltip" data-color-class="danger" title="Delete" data-animate=" animated fadeIn " data-toggle="tooltip"'
						+'data-original-title="Delete  record"><span class="glyphicon glyphicon-remove"></span></a>'
						 
						dataTable.row.add(
								[ i + 1, data.programEducationObjectiveList[i].peoText, data.programEducationObjectiveList[i].peoRemark, acButton ])
								.draw();
					}
					 
				});

			 

		}
		
		function editProgramPeo(peoId) {
			$("#loader3").show();
				$.getJSON('${editProgramPeo}',

				{
					peoId : peoId, 
					ajax : 'true'

				}, function(data) {
					$("#loader3").hide();
					document.getElementById("programPeoId").value=data.peoId;
					 document.getElementById("peoText").value=data.peoText;
					 document.getElementById("peoRemark").value=data.peoRemark;
					 
				});

			 

		}
		
  
	</script>

	<script type="text/javascript">
		function saveProgramPo() {

			var poText = document.getElementById("poText").value;
			var poRemark = document
					.getElementById("poRemark").value;
			var programId = document.getElementById("programId").value;
			var poId = document.getElementById("poId").value;
			
			if (poText != "") {
				$('#example-3 td').remove();
				$("#loader4").show();
				$.getJSON('${saveProgramPo}',

				{
					poText : poText,
					poRemark : poRemark,
					programId : programId,
					poId : poId,
					ajax : 'true'

				}, function(data) {
				 
					$("#loader4").hide();
					  
					var dataTable = $('#example-3').DataTable();
					
					for(var i=0 ; i<data.programOutcomeList.length ;i++){
						
						 
						var acButton = '<a href="#"><span onclick="editProgramPo('+data.programOutcomeList[i].poId+')" class="glyphicon glyphicon-edit" title="Edit" '
						+'data-animate=" animated fadeIn " rel="tooltip"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;'
						+' <a href="#" onclick="deleteProgramPo('+data.programOutcomeList[i].poId+')"'+
						' rel="tooltip" data-color-class="danger" title="Delete" data-animate=" animated fadeIn " data-toggle="tooltip"'
						+'data-original-title="Delete  record"><span class="glyphicon glyphicon-remove"></span></a>'
						 
						dataTable.row.add(
								[ i + 1, data.programOutcomeList[i].poText, data.programOutcomeList[i].poRemark, acButton ])
								.draw();
					}
					 document.getElementById("poId").value=0;
					 document.getElementById("poText").value="";
					 document.getElementById("poRemark").value="";
				});

			} else {
				alert("Enter Program Outcome ");
			}

		}
 
		function deleteProgramPo(poId) {

			var programId = document.getElementById("programId").value;
				$('#example-3 td').remove();
				$("#loader4").show();
				
				$.getJSON('${deleteProgramPo}',

				{
					poId : poId, 
					programId : programId,
					ajax : 'true'

				}, function(data) {
				 
					$("#loader4").hide();
					  
					var dataTable = $('#example-3').DataTable();
					
					for(var i=0 ; i<data.programOutcomeList.length ;i++){
						
						 
						var acButton = '<a href="#"><span onclick="editProgramPo('+data.programOutcomeList[i].poId+')" class="glyphicon glyphicon-edit" title="Edit" '
						+'data-animate=" animated fadeIn " rel="tooltip"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;'
						+' <a href="#" onclick="deleteProgramPo('+data.programOutcomeList[i].poId+')"'+
						' rel="tooltip" data-color-class="danger" title="Delete" data-animate=" animated fadeIn " data-toggle="tooltip"'
						+'data-original-title="Delete  record"><span class="glyphicon glyphicon-remove"></span></a>'
						 
						dataTable.row.add(
								[ i + 1, data.programOutcomeList[i].poText, data.programOutcomeList[i].poRemark, acButton ])
								.draw();
					}
					 
				});

			 

		}
		
		function editProgramPo(poId) {
			$("#loader4").show();
				$.getJSON('${editProgramPo}',

				{
					poId : poId, 
					ajax : 'true'

				}, function(data) {
					$("#loader4").hide();
					document.getElementById("poId").value=data.poId;
					 document.getElementById("poText").value=data.poText;
					 document.getElementById("poRemark").value=data.poRemark;
					 
				});

			 

		}
  
	</script>

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