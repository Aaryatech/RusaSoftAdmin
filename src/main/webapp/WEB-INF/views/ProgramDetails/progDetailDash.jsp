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

													<input type="hidden" id="programVissionId" name="programVissionId"
														value="0"> <label
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
												action="${pageContext.request.contextPath}/submitProgramMission"
												method="post" name="submitProgramMission"
												id="submitProgramMission"
												onsubmit="return confirm('Do you really want to add Program Mission?');">
												<div class="row">


													<label class="col-sm-2 text-left" for="hh"> Mission<span
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

													<table id="example-2"
														class="table table-striped dt-responsive display">
														<thead>
															<tr>
																<th width="10%">Sr No</th>
																<th>Mission</th>
																<th width="10%">Action</th>

															</tr>
														</thead>



													</table>

												</div>

											</div>


										</div>

										<!-- 	PEO -->
										<div class="tab-pane fade in " id="PEO">

											<!-- 	<form action="" method="post">		 -->
											<form class="form-horizontal"
												action="${pageContext.request.contextPath}/submitProgramObjective"
												method="post" name="submitProgramMission"
												id="submitProgramMission"
												onsubmit="return confirm('Do you really want to add Program Objective?');">
												<div class="row">


													<label class="col-sm-2 text-left" for="hh"> PEO<span
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

													<table id="example-5"
														class="table table-striped dt-responsive display">
														<thead>
															<tr>
																<th width="10%">Sr No</th>
																<th>PEO</th>
																<th width="10%">Action</th>

															</tr>
														</thead>



													</table>

												</div>

											</div>


										</div>


										<!-- 	PO -->
										<div class="tab-pane fade in " id="PO">

											<!-- 	<form action="" method="post">		 -->
											<form class="form-horizontal"
												action="${pageContext.request.contextPath}/submitProgramOutcome"
												method="post" name="submitProgramMission"
												id="submitProgramMission"
												onsubmit="return confirm('Do you really want to add Program Outcome?');">
												<div class="row">


													<label class="col-sm-2 text-left" for="hh"> PO<span
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
												<!-- 		</form>			 -->
											</form>
											<div class="row">

												<div class="col-xs-12">

													<table id="example-3"
														class="table table-striped dt-responsive display">
														<thead>
															<tr>
																<th width="10%">Sr No</th>
																<th>PO</th>
																<th width="10%">Action</th>

															</tr>
														</thead>



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
		
		/* function exportExcel() {

			var catId = $("#ind_cat").val();
			var typeId = $("#indent_type").val();
			//alert(catId);
			$
					.getJSON(
							'${exportExcelforIndent}',

							{
								catId : catId,
								typeId : typeId,
								ajax : 'true'

							},
							function(data) {
								//alert(data);
								if (data == "") {
									alert("No records found !!");

								}

								$('#table1 td').remove();
								$
										.each(
												data,
												function(key, trans) {
													//alert(itemList.indDetailId);

													 

														var tr = $('<tr></tr>');
														tr
																.append($(
																		'<td class="col-sm-1" ></td>')
																		.html(
																				key + 1));
														tr
																.append($(
																		'<td class="col-md-1" ></td>')
																		.html(
																				trans.itemCode));
														tr
																.append($(
																		'<td class="col-md-4" ></td>')
																		.html(
																				trans.itemName));
														tr
																.append($(
																		'<td class="col-md-1" ></td>')
																		.html(
																				trans.uom));
														tr
																.append($(
																		'<td class="col-md-1" ></td>')
																		.html(
																				trans.curStock));

														tr
																.append($(
																		'<td class="col-md-1" ></td>')
																		.html(
																				trans.qty));
														tr
																.append($(
																		'<td class="col-md-1" ></td>')
																		.html(
																				trans.date));

														tr
																.append($(
																		'<td class="col-md-1" style="text-align: center;"></td>')
																		.html(
																				"<a href='#' class='action_btn'onclick=deleteIndentItem("
																						+ trans.itemId
																						+ ","
																						+ key
																						+ ")><abbr title='Delete'><i class='fa fa-trash-o  fa-lg'></i></abbr></a>"));

														$('#table1 tbody')
																.append(tr);
														$('#ind_cat')
																.prop(
																		'disabled',
																		true)
																.trigger(
																		"chosen:updated");

														document
																.getElementById("catId").value = catId;
														document
																.getElementById("submitt").disabled = false;
													 

												})

							});
		} */
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