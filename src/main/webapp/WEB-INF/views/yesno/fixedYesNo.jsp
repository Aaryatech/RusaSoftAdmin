<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<!DOCTYPE html>
<html class=" ">
<head>
<c:url value="/addTransperent" var="addTransperent"></c:url>
<c:url value="/deleteTranspernt" var="deleteTranspernt"></c:url>
<c:url value="/editTranspernt" var="editTranspernt"></c:url>

<c:url value="/addTimeboundAddDive" var="addTimeboundAddDive"></c:url>
<c:url value="/deleteTimebound" var="deleteTimebound"></c:url>
<c:url value="/editTranspernt" var="editTimebound"></c:url>

<c:url value="/addEfficient" var="addEfficient"></c:url>
<c:url value="/deleteEfficient" var="deleteEfficient"></c:url>
<c:url value="/editTranspernt" var="editEfficient"></c:url>


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

							<%-- <div class="actions panel_actions pull-right">


								<a href="${pageContext.request.contextPath}/showProgramList"><button
										type="button" class="btn btn-success">Back</button></a>

							</div>
 --%>
							<%-- <div class="col-lg-12" id="sucess_msg" style="display: none;">
								<!-- onclick="location.reload();" -->
								<div class="alert alert-success alert-dismissible fade in">
									<button type="button" class="close" onclick="hideAlert()">
										<span aria-hidden="true">×</span>
									</button>
									<strong>Success : </strong>${msg}</div>
							</div> --%>
											

							<%-- <div class="col-lg-12" id="fail_msg" style="display: none;">
								<div class="alert alert-success alert-dismissible fade in">
									<button type="button" class="close" data-dismiss="alert"
										aria-label="Close">
										<span aria-hidden="true">×</span>
									</button>
									<strong>Fail : </strong>${msg}</div>
							</div> --%>
							
						</header>

						<div class="content-body">
							<div class="row">

								<div class="col-md-12">

									<ul class="nav nav-tabs">

										<li class="active"><a href="#Vision" data-toggle="tab">
												<i class="fa fa-home"></i> Transparent
										</a></li>
										<li><a href="#Mission" data-toggle="tab"> <i
												class="fa fa-home"></i> Time Bound
										</a></li>
										<li><a href="#PEO" data-toggle="tab"> <i
												class="fa fa-home"></i> Efficient
										</a></li>
										<!-- <li><a href="#PO" data-toggle="tab"> <i
												class="fa fa-home"></i> Program Outcome
										</a></li>
										<li><a href="#PSO" data-toggle="tab"> <i
												class="fa fa-home"></i> Program Specific Outcome
										</a></li> -->

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

												<c:if test="${instituteYesNoTab1List.size()==0}">
													<div class="row">
														<label class="col-sm-2 text-left" for="programVission">
															Transparent<span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<input type="radio" name="transperntyesno" value="1"
																id="transperntyesno"
																onchange="vissiblediveTrasnpernt(1)"> YES <input
																type="radio" id="transperntyesno" name="transperntyesno"
																value="0" onchange="vissiblediveTrasnpernt(0)" checked>
															NO
														</div>
													</div>
												</c:if>

												<c:choose>
													<c:when test="${instituteYesNoTab1List.size()>0}">
														<br />
														<div class="row" id="transperentAddDive">

															<label class="col-sm-2 text-left"
																for="programVissionRemark"> Specify </label>
															<div class="col-sm-6">
																<input type="text" class="form-control"
																	id="transperentspeficytext"
																	name="transperentspeficytext"
																	placeholder="Please Specify Here">
															</div>

															<div class="col-sm-4">

																<input type="button" class="btn btn-info"
																	onclick="addTransperent()" value="Add"> <input
																	type="hidden" id="transId" name="transId" value="0">

															</div>
														</div>
													</c:when>
													<c:otherwise>

														<br />
														<div class="row" id="transperentAddDive"
															style="display: none;">

															<label class="col-sm-2 text-left"
																for="transperentspeficy"> Specify </label>
															<div class="col-sm-6">
																<input type="text" class="form-control"
																	id="transperentspeficytext"
																	name="transperentspeficytext"
																	placeholder="Please Specify Here">
															</div>

															<div class="col-sm-4">

																<input type="button" class="btn btn-info"
																	onclick="addTransperent()" value="Add"> <input
																	type="hidden" id="transId" name="transId" value="0">

															</div>
														</div>
													</c:otherwise>
												</c:choose>



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
																	<th>Value</th>
																	<th width="10%">Action</th>

																</tr>

															</thead>
															<tbody>
																<c:forEach items="${instituteYesNoTab1List}" var="list"
																	varStatus="count">
																	<tr>

																		<td>${count.index+1}</td>
																		<td>${list.instYesnoResponse}</td>
																		<td><a href="#"
																			onclick="editTranspernt(${list.instYesnoId})"
																			rel="tooltip" data-color-class="danger" title="Edit"
																			data-animate=" animated fadeIn "
																			data-toggle="tooltip"
																			data-original-title="Edit  record"><span
																				class="glyphicon glyphicon-edit"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																			href="#"
																			onclick="deleteTranspernt(${list.instYesnoId})"
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

											<form class="form-horizontal"
												action="${pageContext.request.contextPath}/#" method="post"
												name="submitProgramVission" id="submitProgramVission"
												onsubmit="return confirm('Do you really want to add Program Vission?');">

												<c:if test="${instituteYesNoTab2List.size()==0}">
													<div class="row">
														<label class="col-sm-2 text-left" for="programVission">
															Time Bound<span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<input type="radio" name="timeboundyesno" value="1"
																id="timeboundyesno"
																onchange="vissiblediveTimeboundyesno(1)"> YES <input
																type="radio" id="timeboundyesno" name="timeboundyesno"
																value="0" onchange="vissiblediveTimeboundyesno(0)"
																checked> NO
														</div>
													</div>
												</c:if>

												<c:choose>
													<c:when test="${instituteYesNoTab2List.size()>0}">
														<br />
														<div class="row" id="timeboundAddDive">

															<label class="col-sm-2 text-left"
																for="timeboundspeficytext"> Specify </label>
															<div class="col-sm-6">
																<input type="text" class="form-control"
																	id="timeboundspeficytext" name="timeboundspeficytext"
																	placeholder="Please Specify Here">
															</div>

															<div class="col-sm-4">

																<input type="button" class="btn btn-info"
																	onclick="addTimeboundAddDive()" value="Add"> <input
																	type="hidden" id="timeBoundId" name="timeBoundId"
																	value="0">

															</div>
														</div>
													</c:when>
													<c:otherwise>

														<br />
														<div class="row" id="timeboundAddDive"
															style="display: none;">

															<label class="col-sm-2 text-left"
																for="timeboundspeficytext"> Specify </label>
															<div class="col-sm-6">
																<input type="text" class="form-control"
																	id="timeboundspeficytext" name="timeboundspeficytext"
																	placeholder="Please Specify Here">
															</div>

															<div class="col-sm-4">

																<input type="button" class="btn btn-info"
																	onclick="addTimeboundAddDive()" value="Add"> <input
																	type="hidden" id="timeBoundId" name="timeBoundId"
																	value="0">

															</div>
														</div>
													</c:otherwise>
												</c:choose>



												<br>


											</form>

											<!-- 		</form>			 -->
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
																	<th>Value</th>
																	<th width="10%">Action</th>

																</tr>

															</thead>
															<tbody>
																<c:forEach items="${instituteYesNoTab2List}" var="list"
																	varStatus="count">
																	<tr>

																		<td align="center">${count.index+1}</td>
																		<td>${list.instYesnoResponse}</td>
																		<td align="center"><a href="#"
																			onclick="editTimebound(${list.instYesnoId})"
																			rel="tooltip" data-color-class="danger" title="Edit"
																			data-animate=" animated fadeIn "
																			data-toggle="tooltip"
																			data-original-title="Edit  record"><span
																				class="glyphicon glyphicon-edit"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																			href="#"
																			onclick="deleteTimebound(${list.instYesnoId})"
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

										<!-- 	PEO -->
										<div class="tab-pane fade in " id="PEO">

											<!-- 	<form action="" method="post">		 -->
											<form class="form-horizontal"
												action="${pageContext.request.contextPath}/#" method="post"
												name="submitProgramVission" id="submitProgramVission"
												onsubmit="return confirm('Do you really want to add Program Vission?');">

												<c:if test="${instituteYesNoTab3List.size()==0}">
													<div class="row">
														<label class="col-sm-2 text-left" for="efficientyesno">
															Efficient<span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<input type="radio" name="efficientyesno" value="1"
																id="efficientyesno"
																onchange="vissiblediveEfficientyesno(1)"> YES <input
																type="radio" id="efficientyesno" name="efficientyesno"
																value="0" onchange="vissiblediveEfficientyesno(0)"
																checked> NO
														</div>
													</div>
												</c:if>

												<c:choose>
													<c:when test="${instituteYesNoTab3List.size()>0}">
														<br />
														<div class="row" id="efficientAddDive">

															<label class="col-sm-2 text-left"
																for="programVissionRemark"> Specify </label>
															<div class="col-sm-6">
																<input type="text" class="form-control"
																	id="efficientspeficytext" name="efficientspeficytext"
																	placeholder="Please Specify Here">
															</div>

															<div class="col-sm-4">

																<input type="button" class="btn btn-info"
																	onclick="addEfficient()" value="Add"> <input
																	type="hidden" id="efficientId" name="efficientId"
																	value="0">
															</div>
														</div>
													</c:when>
													<c:otherwise>

														<br />
														<div class="row" id="efficientAddDive"
															style="display: none;">

															<label class="col-sm-2 text-left"
																for="efficientspeficytext"> Specify </label>
															<div class="col-sm-6">
																<input type="text" class="form-control"
																	id="efficientspeficytext" name="efficientspeficytext"
																	placeholder="Please Specify Here">
															</div>

															<div class="col-sm-4">

																<input type="button" class="btn btn-info"
																	onclick="addEfficient()" value="Add"> <input
																	type="hidden" id="efficientId" name="efficientId"
																	value="0">
															</div>
														</div>
													</c:otherwise>
												</c:choose>



												<br>


											</form>

											<!-- 		</form>			 -->
											<div align="center" id="loader3" style="display: none;">
												<img
													src="${pageContext.request.contextPath}/resources/assets/images/loader.gif"
													style="width: 50px; height: 50px;">
											</div>


											<div class="row">


												<div class="col-xs-12">
													<div class="table-responsive">
														<table class="table table-bordered" id="table3">
															<thead>

																<tr>
																	<th width="10%">Sr No</th>
																	<th>Value</th>
																	<th width="10%">Action</th>

																</tr>

															</thead>
															<tbody>
																<c:forEach items="${instituteYesNoTab3List}" var="list"
																	varStatus="count">
																	<tr>

																		<td>${count.index+1}</td>
																		<td>${list.instYesnoResponse}</td>
																		<td><a href="#"
																			onclick="editEfficient(${list.instYesnoId})"
																			rel="tooltip" data-color-class="danger" title="Edit"
																			data-animate=" animated fadeIn "
																			data-toggle="tooltip"
																			data-original-title="Edit  record"><span
																				class="glyphicon glyphicon-edit"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;<a
																			href="#"
																			onclick="deleteEfficient(${list.instYesnoId})"
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


										<!-- 	PO -->
										<div class="tab-pane fade in " id="PO">

											<!-- 	<form action="" method="post">		 -->
											<form class="form-horizontal"
												action="${pageContext.request.contextPath}/#" method="post"
												name="submitProgramMission" id="submitProgramMission"
												onsubmit="return confirm('Do you really want to add Program Outcome?');">
												<div class="row">
													<input type="hidden" id="poId" name="poId" value="0">
													<label class="col-sm-2 text-left" for="poText"> PO<span
														class="text-danger">*</span>
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
													<div class="table-responsive">
														<table class="table table-bordered" id="table4">
															<thead>
																<tr>
																	<th width="10%">Sr No</th>
																	<th>PO</th>
																	<th>Remark</th>
																	<th width="10%">Action</th>

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



										<!-- 	PSO -->
										<div class="tab-pane fade in " id="PSO">

											<!-- 	<form action="" method="post">		 -->
											<form class="form-horizontal"
												action="${pageContext.request.contextPath}/#" method="post"
												name="submitProgramMission" id="submitProgramMission"
												onsubmit="return confirm('Do you really want to add Program Outcome?');">
												<div class="row">

													<input type="hidden" id="psoId" name="psoId" value="0">
													<label class="col-sm-2 text-left" for="psoText">
														PSO<span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="psoText"
															name="psoText" placeholder="Program Specific Outcome"
															required>
													</div>
												</div>

												<br />
												<div class="row">

													<label class="col-sm-2 text-left" for="psoRemark">
														Remark(If Any) </label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="psoRemark"
															name="psoRemark"
															placeholder="Program Specific Outcome Remark" required>
													</div>

													<div class="col-sm-4">

														<input type="button" onclick="saveProgramPso()"
															class="btn btn-info" value="Add">
													</div>
												</div>
											</form>
											<!-- 		</form>			 -->
											<br>
											<div align="center" id="loader5" style="display: none;">
												<img
													src="${pageContext.request.contextPath}/resources/assets/images/loader.gif"
													style="width: 50px; height: 50px;">
											</div>

											<div class="row">

												<%-- <div class="col-xs-12">

													<table id="example-4"
														class="table table-striped dt-responsive display">
														<thead>
															<tr>
																<th width="60%">Sr No</th>
																<th>PSO</th>
																<th>Remark</th>
																<th width="60%">Action</th>

															</tr>
														</thead>
														<tbody>
															<c:forEach items="${programSpeceficOutcomeList}"
																var="list" varStatus="count">
																<tr>

																	<td>${count.index+1}</td>
																	<td>${list.psoText}</td>
																	<td>${list.psoRemark}</td>
																	<td><a href="#"
																		onclick="editProgramPso(${list.psoId})"><span
																			class="glyphicon glyphicon-edit" title="Edit"
																			data-animate=" animated fadeIn " rel="tooltip"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;

																		<a href="#" onclick="deleteProgramPso(${list.psoId})"
																		rel="tooltip" data-color-class="danger" title="Delete"
																		data-animate=" animated fadeIn " data-toggle="tooltip"
																		data-original-title="Delete  record"><span
																			class="glyphicon glyphicon-remove"></span></a></td>
																</tr>
															</c:forEach>

														</tbody>


													</table>

												</div> --%>

												<div class="col-xs-12">
													<div class="table-responsive">
														<table class="table table-bordered" id="table5">
															<thead>
																<tr>
																	<th width="10%">Sr No</th>
																	<th>PSO</th>
																	<th>Remark</th>
																	<th width="10%">Action</th>

																</tr>
															</thead>
															<tbody>
																<c:forEach items="${programSpeceficOutcomeList}"
																	var="list" varStatus="count">
																	<tr>

																		<td>${count.index+1}</td>
																		<td>${list.psoText}</td>
																		<td>${list.psoRemark}</td>
																		<td><a href="#"
																			onclick="editProgramPso(${list.psoId})"><span
																				class="glyphicon glyphicon-edit" title="Edit"
																				data-animate=" animated fadeIn " rel="tooltip"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;

																			<a href="#" onclick="deleteProgramPso(${list.psoId})"
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
		function addTransperent() {
			transId=document.getElementById("transId").value
			var id = document.getElementById("transId").value; 
			
			var transperentspeficytext = document.getElementById("transperentspeficytext").value; 
			 
			if (transperentspeficytext != "") {
				 
				
				 document.getElementById("transperentspeficytext").value=""; 
				 
				 $("#loader1").show();
				 
				$.getJSON('${addTransperent}',

				{
					transperentspeficytext : transperentspeficytext, 
					transId : transId,
					ajax : 'true'

				}, function(data) {
				 
					$('#table1 td').remove();
					 
					$("#loader1").hide();
					  
					//var dataTable = $('#example-1').DataTable();
					  document.getElementById("transId").value="0";
					for(var i=0 ; i<data.length ;i++){
						
						 
						 
						 var acButton='<a href="#" onclick="editTranspernt('+data[i].instYesnoId+')"'+
							' rel="tooltip" data-color-class="danger" title="Edit" data-animate=" animated fadeIn " data-toggle="tooltip"'
							+'data-original-title="Delete  record"><span class="glyphicon glyphicon-edit"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="deleteTranspernt('+data[i].instYesnoId+')"'+
						' rel="tooltip" data-color-class="danger" title="Delete" data-animate=" animated fadeIn " data-toggle="tooltip"'
						+'data-original-title="Delete  record"><span class="glyphicon glyphicon-remove"></span></a>'
						/*  dataTable.row.add(
								[ i + 1, data.programVissionList[i].visionText, data.programVissionList[i].visionRemark, acButton ])
								.draw(); */  
						
						var tr = $('<tr></tr>');
						tr.append($('<td ></td>').html(i + 1));
					  	tr.append($('<td ></td>').html(data[i].instYesnoResponse)); 
					  	tr.append($('<td  ></td>').html(acButton)); 
						$('#table1 tbody').append(tr); 
					}
					
					 if(data!=null){
						  $("#sucess_msg").show();
						 						
					 } else{
						 $("#fail_msg").show();
					 }
					 
				});

			} else {
				alert("Specify Value");
			}

		}
		
function editTranspernt(instYesnoId){
			
			
			$.getJSON('${editTranspernt}',

					{
						instYesnoId : instYesnoId,  
						ajax : 'true'

					}, function(data) {
						//alert(JSON.stringify(data));
						document.getElementById("transperentspeficytext").value=data.instYesnoResponse; 
						document.getElementById("transId").value=data.instYesnoId; 
						
					});
			
		}

		function deleteTranspernt(instYesnoId) {
 
				//$('#example-1 td').remove();
				$("#loader1").show();
				
				$.getJSON('${deleteTranspernt}',

				{
					instYesnoId : instYesnoId,  
					ajax : 'true'

				}, function(data) {
				 
					$("#loader1").hide();
					$('#table1 td').remove();
					
					//var dataTable = $('#example-1').DataTable();
			 document.getElementById("transId").value="0";

					for(var i=0 ; i<data.length ;i++){
						 
						 
						 var acButton='<a href="#" onclick="editTranspernt('+data[i].instYesnoId+')"'+
							' rel="tooltip" data-color-class="danger" title="Edit" data-animate=" animated fadeIn " data-toggle="tooltip"'
							+'data-original-title="Delete  record"><span class="glyphicon glyphicon-edit"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="deleteTranspernt('+data[i].instYesnoId+')"'+
						' rel="tooltip" data-color-class="danger" title="Delete" data-animate=" animated fadeIn " data-toggle="tooltip"'
						+'data-original-title="Delete  record"><span class="glyphicon glyphicon-remove"></span></a>'
						 
						/*  dataTable.row.add(
								[ i + 1, data.programVissionList[i].visionText, data.programVissionList[i].visionRemark, acButton ])
								.draw(); */  
						
						var tr = $('<tr></tr>');
						tr.append($('<td ></td>').html(i + 1));
					  	tr.append($('<td ></td>').html(data[i].instYesnoResponse)); 
					  	tr.append($('<td  ></td>').html(acButton)); 
						$('#table1 tbody').append(tr); 
					}
					 
				});

			 

		} 
	</script>

	<script type="text/javascript">
		function addTimeboundAddDive() {
			var timeBoundId=document.getElementById("timeBoundId").value;

			var transperentspeficytext = document.getElementById("timeboundspeficytext").value; 
			 
			if (transperentspeficytext != "") {
				 
				
				 document.getElementById("timeboundspeficytext").value=""; 
				 
				 $("#loader2").show();
				 
				$.getJSON('${addTimeboundAddDive}',

				{
					transperentspeficytext : transperentspeficytext, 
					timeBoundId : timeBoundId,
					ajax : 'true'

				}, function(data) {
				 
				 
					$('#table2 td').remove();
					 
					$("#loader2").hide();
					  
					//var dataTable = $('#example-1').DataTable();
					document.getElementById("timeBoundId").value="0";

					for(var i=0 ; i<data.length ;i++){
						 
						 
						 var acButton = '<a href="#" onclick="editTimebound('+data[i].instYesnoId+')"'+
							' rel="tooltip" data-color-class="danger" title="Edit" data-animate=" animated fadeIn " data-toggle="tooltip"'
							+'data-original-title="Edit  record"><span class="glyphicon glyphicon-edit"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="deleteTimebound('+data[i].instYesnoId+')"'+
						' rel="tooltip" data-color-class="danger" title="Delete" data-animate=" animated fadeIn " data-toggle="tooltip"'
						+'data-original-title="Delete  record"><span class="glyphicon glyphicon-remove"></span></a>'
						 
						/*  dataTable.row.add(
								[ i + 1, data.programVissionList[i].visionText, data.programVissionList[i].visionRemark, acButton ])
								.draw(); */  
						
						var tr = $('<tr></tr>');
						tr.append($('<td ></td>').html(i + 1)); 
					  	tr.append($('<td ></td>').html(data[i].instYesnoResponse));  
					  	tr.append($('<td  ></td>').html(acButton));  
						$('#table2 tbody').append(tr); 
					}
					
					 if(data!=null){
						 $("#sucess_msg").show();
					 } else{
						 $("#fail_msg").show();
					 }
					 
				});

			} else {
				alert("Specify Value");
			}
			

		}
		
function editTimebound(instYesnoId){
			
			$.getJSON('${editTimebound}',

					{
						instYesnoId : instYesnoId,  
						ajax : 'true'

					}, function(data) {
						//alert(JSON.stringify(data));
						document.getElementById("timeboundspeficytext").value=data.instYesnoResponse; 
						document.getElementById("timeBoundId").value=data.instYesnoId; 
						
					});
			
		}
		
		function deleteTimebound(instYesnoId) {
			 
			//$('#example-1 td').remove();
			$("#loader2").show();
			
			$.getJSON('${deleteTimebound}',

			{
				instYesnoId : instYesnoId,  
				ajax : 'true'

			}, function(data) {
			 
				$("#loader2").hide();
				$('#table2 td').remove();
				
				//var dataTable = $('#example-1').DataTable();
				
				document.getElementById("timeBoundId").value="0";

					for(var i=0 ; i<data.length ;i++){
						 
						 
						 var acButton = '<a href="#" onclick="editTimebound('+data[i].instYesnoId+')"'+
							' rel="tooltip" data-color-class="danger" title="Edit" data-animate=" animated fadeIn " data-toggle="tooltip"'
							+'data-original-title="Edit  record"><span class="glyphicon glyphicon-edit"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="deleteTimebound('+data[i].instYesnoId+')"'+
						' rel="tooltip" data-color-class="danger" title="Delete" data-animate=" animated fadeIn " data-toggle="tooltip"'
						+'data-original-title="Delete  record"><span class="glyphicon glyphicon-remove"></span></a>'
						 
					 
					/*  dataTable.row.add(
							[ i + 1, data.programVissionList[i].visionText, data.programVissionList[i].visionRemark, acButton ])
							.draw(); */  
					
					var tr = $('<tr></tr>');
					tr.append($('<td ></td>').html(i + 1));
				  	tr.append($('<td ></td>').html(data[i].instYesnoResponse)); 
				  	tr.append($('<td  ></td>').html(acButton)); 
					$('#table2 tbody').append(tr); 
				}
				 
			});

		 

	} 
		 
		 
	</script>

	<script type="text/javascript">
		function addEfficient() {
			var efficientId=document.getElementById("efficientId").value;

			var transperentspeficytext = document.getElementById("efficientspeficytext").value; 
			 
			if (transperentspeficytext != "") {
				 
				
				 document.getElementById("efficientspeficytext").value=""; 
				 
				 $("#loader3").show();
				 
				$.getJSON('${addEfficient}',

				{
					transperentspeficytext : transperentspeficytext, 
					efficientId : efficientId,

					ajax : 'true'

				}, function(data) {
				 
				 
					$('#table3 td').remove();
					 
					$("#loader3").hide();
					  
					//var dataTable = $('#example-1').DataTable();
					 document.getElementById("efficientId").value="0";

					for(var i=0 ; i<data.length ;i++){
						
						 var acButton = '<a href="#" onclick="editEfficient('+data[i].instYesnoId+')"'+
							' rel="tooltip" data-color-class="danger" title="Edit" data-animate=" animated fadeIn " data-toggle="tooltip"'
							+'data-original-title="Delete  record"><span class="glyphicon glyphicon-edit"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="deleteEfficient('+data[i].instYesnoId+')"'+
						' rel="tooltip" data-color-class="danger" title="Delete" data-animate=" animated fadeIn " data-toggle="tooltip"'
						+'data-original-title="Delete  record"><span class="glyphicon glyphicon-remove"></span></a>'
						 
						/*  dataTable.row.add(
								[ i + 1, data.programVissionList[i].visionText, data.programVissionList[i].visionRemark, acButton ])
								.draw(); */  
						
						var tr = $('<tr></tr>');
						tr.append($('<td ></td>').html(i + 1)); 
					  	tr.append($('<td ></td>').html(data[i].instYesnoResponse));  
					  	tr.append($('<td  ></td>').html(acButton));  
						$('#table3 tbody').append(tr); 
					}
					
					 if(data!=null){
						 $("#sucess_msg").show();
					 } else{
						 $("#fail_msg").show();
					 }
					 
				});

			} else {
				alert("Specify Value");
			}
			

		}
		
function editEfficient(instYesnoId){
			
			$.getJSON('${editEfficient}',

					{
						instYesnoId : instYesnoId,  
						ajax : 'true'

					}, function(data) {
						//alert(JSON.stringify(data));
						document.getElementById("efficientspeficytext").value=data.instYesnoResponse; 
						document.getElementById("efficientId").value=data.instYesnoId; 
						
					});
			
		}
		
		function deleteEfficient(instYesnoId) {
			 
			//$('#example-1 td').remove();
			$("#loader3").show();
			
			$.getJSON('${deleteEfficient}',

			{
				instYesnoId : instYesnoId,  
				ajax : 'true'

			}, function(data) {
			 
				$("#loader3").hide();
				$('#table3 td').remove();
				
				 document.getElementById("efficientId").value="0";

					for(var i=0 ; i<data.length ;i++){
						
						 var acButton = '<a href="#" onclick="editEfficient('+data[i].instYesnoId+')"'+
							' rel="tooltip" data-color-class="danger" title="Edit" data-animate=" animated fadeIn " data-toggle="tooltip"'
							+'data-original-title="Delete  record"><span class="glyphicon glyphicon-edit"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="deleteEfficient('+data[i].instYesnoId+')"'+
						' rel="tooltip" data-color-class="danger" title="Delete" data-animate=" animated fadeIn " data-toggle="tooltip"'
						+'data-original-title="Delete  record"><span class="glyphicon glyphicon-remove"></span></a>'
						 
					/*  dataTable.row.add(
							[ i + 1, data.programVissionList[i].visionText, data.programVissionList[i].visionRemark, acButton ])
							.draw(); */  
					
					var tr = $('<tr></tr>');
					tr.append($('<td ></td>').html(i + 1));
				  	tr.append($('<td ></td>').html(data[i].instYesnoResponse)); 
				  	tr.append($('<td  ></td>').html(acButton)); 
					$('#table3 tbody').append(tr); 
				}
				 
			});

		 

	} 
		 
		 
	</script>
	<script type="text/javascript">
	function vissiblediveTrasnpernt(value) {
		 
		if(value==1){
			
			document.getElementById("transperentAddDive").style = "visible";
			
		}else{
			document.getElementById("transperentAddDive").style = "display:none";
		}

	}function vissiblediveTimeboundyesno(value) {
		 
		if(value==1){
			
			document.getElementById("timeboundAddDive").style = "visible";
			
		}else{
			document.getElementById("timeboundAddDive").style = "display:none";
		}

	}function vissiblediveEfficientyesno(value) {
		 
		if(value==1){
			
			document.getElementById("efficientAddDive").style = "visible";
			
		}else{
			document.getElementById("efficientAddDive").style = "display:none";
		}

	}
	
	function hideAlert(){
		$("#sucess_msg").hide();
		$("#fail_msg").hide();
	}
	
	
	</script>





</body>
</html>