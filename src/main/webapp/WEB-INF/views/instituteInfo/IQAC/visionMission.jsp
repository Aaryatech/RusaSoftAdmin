<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>



<!DOCTYPE html>
<html class=" ">
<head>
<c:url value="/saveInstituteVission" var="saveInstituteVission"></c:url>
<c:url value="/deleteInstituteVission" var="deleteInstituteVission"></c:url>
<c:url value="/editInstituteVission" var="editInstituteVission"></c:url>

<c:url value="/saveInstituteMission" var="saveInstituteMission"></c:url>
<c:url value="/deleteInstituteMission" var="deleteInstituteMission"></c:url>
<c:url value="/editInstituteMission" var="editInstituteMission"></c:url>

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


								<%-- <a href="${pageContext.request.contextPath}/showProgramList"><button
										type="button" class="btn btn-success">Back</button></a> --%>

							</div>
							
							 <%-- <c:if test="${sessionScope.successMsg!=null}"> --%>
           						 <div class="col-lg-12" id="sucess_msg" style="display: none;">
    						          <div class="alert alert-success alert-dismissible fade in">
            							    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
             						   <strong>Success : </strong>${msgSucss}</div>
        	                     </div> 
        	                     
        	                     <div class="col-lg-12" id="fail_msg" style="display: none;">
    						          <div class="alert alert-success alert-dismissible fade in">
            							    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
             						   <strong>Fail : </strong>${msgFail}</div>
        	                     </div> 
        	                   
        	                   
        	                   
            			<%-- </c:if> --%>
						</header>
						

						<div class="content-body">
							<div class="row">

								<div class="col-md-12">

									<ul class="nav nav-tabs">

										<li class="active"><a href="#Vision" data-toggle="tab">
												<i class="fa fa-home"></i> <span style="color: #1F85DE; font-weight: 900;">Vision</span>
										</a></li>
										<li><a href="#Mission" data-toggle="tab"> <i
												class="fa fa-home"></i><span style="color: #ee730a; font-weight: 900;">Mission</span>
										</a></li>
									</ul>

									<div class="tab-content">	



										<!-- 		Vision -->
										<div class="tab-pane fade in active " id="Vision">

											<!-- 	<form action="" method="post">		 -->
											<form class="form-horizontal"
												action="#" method="post"
												name="submitProgramVission" id="submitProgramVission"
												onsubmit="return confirm('Do you really want to add Institute Vission?');">

												<div class="row">

													<input type="hidden" id="inst_vision_id"
														name="inst_vision_id" value="0"> <label
														class="col-sm-2 text-left" for="inst_vision_text">
														Vision<span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<textarea rows="4" cols="50" class="form-control" onchange="trim(this)"
															id="inst_vision_text" name="inst_vision_text" maxlength="500"
															placeholder="Institute Vision" autocomplete="off" ></textarea>
															<span
																class="error_form text-danger" id="vision_error_field"
																style="display: none;">Please enter institute vision</span>
													</div>

													<div class="col-sm-4">
														<input type="hidden" class="form-control"
															name="instVisionId" id="instVisionId" value="0"><input
															type="button" class="btn btn-info"
															onclick="saveInstituteVission()" value="Add"
															id="saveVision">
													</div>
												</div>

												<br />
												<!-- <div class="row" style="display: none;">
													<label class="col-sm-2 text-left"
														for="programVissionRemark"> Remark(If Any) </label>
													<div class="col-sm-6">
														<input type="text" class="form-control"
															id="inst_vision_remark" name="inst_vision_remark"
															value="-" placeholder="Institute Vission Remark">
													</div>
 
												</div> -->



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
																<!-- 	<th width="10%">Sr No</th> -->
																	<th>Vision</th>

																	<th width="10%">Action</th>

																</tr>

															</thead>
														<tbody>
																<c:forEach items="${institueVisionList}" var="list"
																	varStatus="count">
																	<tr>

																		<%-- <td style="text-align: center; ">${count.index+1}</td> --%>
																		
																		<c:set var="tempVision" value="${list.instVisionText}"></c:set>
										<c:set var="visionName" value="${fn:substring(tempVision,0,130)}"></c:set>
					
																		<td title="${list.instVisionText}" style="word-wrap:break-word; max-width: 200" >${list.instVisionText}</td>
																		<td style="text-align: center; "><a href="#"
																			onclick="editInstituteVission(${list.instVisionId})"><span
																				class="glyphicon glyphicon-edit" title="Edit"
																				data-animate=" animated fadeIn " rel="tooltip"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;

																			<a href="#"
																			onclick="deleteInstituteVission(${list.instVisionId})"
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

													<input type="hidden" id="inst_mission_id"
														name="inst_mission_id" value="0"> <label
														class="col-sm-2 text-left" for="inst_mission_text">
														Mission<span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<textarea rows="4" cols="" class="form-control" maxlength="200"
															id="inst_mission_text" name="inst_mission_text"
															placeholder="Institute Mission " required autocomplete="off"></textarea> <input
															type="hidden"   id="instMissionId"
															name="instMissionId" value="0">
													</div>
													<div class="col-sm-4">

														<input type="button" onclick="saveInstituteMission()"
															class="btn btn-info" value="Add" id="saveMission">
													</div>
												</div>

												<br />
												<div class="row" style="display: none;">
													<label class="col-sm-2 text-left"
														for="programMissionRemark"> Remark(If Any) </label>
													<div class="col-sm-6">
														<input type="text" class="form-control"
															id="programMissionRemark" name="programMissionRemark"
															value="-" placeholder="Program Mission Remark">
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
																	<!-- <th width="10%">Sr No</th> -->
																	<th>Mission</th>

																	<th width="10%">Action</th>

																</tr>
															</thead>

															<tbody>
																<c:forEach items="${institueMissionList}" var="list"
																	varStatus="count">
																	<tr>

																		<%-- <td style="text-align: center; ">${count.index+1}</td> --%>
																		<td style="word-wrap:break-word; max-width: 200">${list.instMissionText}</td>
																		<td style="text-align: center; "><a href="#"
																			onclick="editInstituteMission(${list.instMissionId})"><span
																				class="glyphicon glyphicon-edit" title="Edit"
																				data-animate=" animated fadeIn " rel="tooltip"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;

																			<a href="#"
																			onclick="deleteInstituteMission(${list.instMissionId})"
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
		function saveInstituteVission() {

			var instVisionText = document.getElementById("inst_vision_text").value; 
			var instituteVissionId = document.getElementById("instVisionId").value;
			
			if (instVisionText.trim().length>0) {
				//$('#example-1 td').remove(); 
				document.getElementById("saveVision").disabled=true;
				
				$("#loader1").show(); 
				$.getJSON('${saveInstituteVission}',

				{
					instVisionText : instVisionText, 
					instituteVissionId : instituteVissionId,
					ajax : 'true'

				}, function(data) {
				 
					$('#table1 td').remove();
					$("#loader1").hide();
					
					document.getElementById("saveVision").disabled=false;
					//var dataTable = $('#example-1').DataTable();
					 
					for(var i=0 ; i<data.institueVisionList.length ;i++){
						
						 
						 var acButton = '<a href="#"><span onclick="editInstituteVission('+data.institueVisionList[i].instVisionId+')" class="glyphicon glyphicon-edit" title="Edit" '
						+'data-animate=" animated fadeIn " rel="tooltip"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;'
						+' <a href="#" onclick="deleteInstituteVission('+data.institueVisionList[i].instVisionId+')"'+
						' rel="tooltip" data-color-class="danger" title="Delete" data-animate=" animated fadeIn " data-toggle="tooltip"'
						+'data-original-title="Delete  record"><span class="glyphicon glyphicon-remove"></span></a>'
						 
						/*  dataTable.row.add(
								[ i + 1, data.programVissionList[i].visionText, data.programVissionList[i].visionRemark, acButton ])
								.draw(); */  
						
						var tr = $('<tr></tr>');
						/* tr.append($('<td ></td>').html(i + 1)); */
					  	tr.append($('<td ></td>').html(data.institueVisionList[i].instVisionText)); 
					  	tr.append($('<td  ></td>').html(acButton)); 
						$('#table1 tbody').append(tr); 
					}
					
					document.getElementById("instVisionId").value=0; 
					 document.getElementById("inst_vision_text").value="";
					 
					 if(data!=null){
						 $("#sucess_msg").show();
					 } else{
						 $("#fail_msg").show();
					 }
					 
				});

			} else {
				//alert("Enter Institute Vission ");
				 //document.getElementById("inst_vision_text").value="";
				 var isError = false;
				var errMsg = "";
				 if (!$("#inst_vision_text").val()) {
					isError = true;

					$("#inst_vision_text").addClass(
							"has-error")
					$("#vision_error_field")
							.show()
				} else {
					$("#vision_error_field")
							.hide()
				}
				 
				 if (!isError) {
						var x = confirm("Do you really want to submit the form?");
						if (x == true) {
							document.getElementById("sub1").disabled = true;
							document.getElementById("sub2").disabled = true;
							return  true;
						}	
					}
				 
				
			}

		}
		
		function deleteInstituteVission(instVisionId) {
 
				//$('#example-1 td').remove();
				$("#loader1").show();
				
				$.getJSON('${deleteInstituteVission}',

				{
					instVisionId : instVisionId,  
					ajax : 'true'

				}, function(data) {
				 
					$("#loader1").hide();
					$('#table1 td').remove();
					
					//var dataTable = $('#example-1').DataTable();
					
					for(var i=0 ; i<data.institueVisionList.length ;i++){
						 
						 var acButton = '<a href="#"><span onclick="editInstituteVission('+data.institueVisionList[i].instVisionId+')" class="glyphicon glyphicon-edit" title="Edit" '
						+'data-animate=" animated fadeIn " rel="tooltip"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;'
						+' <a href="#" onclick="deleteInstituteVission('+data.institueVisionList[i].instVisionId+')"'+
						' rel="tooltip" data-color-class="danger" title="Delete" data-animate=" animated fadeIn " data-toggle="tooltip"'
						+'data-original-title="Delete  record"><span class="glyphicon glyphicon-remove"></span></a>'
						 
						/*  dataTable.row.add(
								[ i + 1, data.programVissionList[i].visionText, data.programVissionList[i].visionRemark, acButton ])
								.draw(); */  
						var tr = $('<tr></tr>');
						/* tr.append($('<td ></td>').html(i + 1)); */
					  	tr.append($('<td ></td>').html(data.institueVisionList[i].instVisionText)); 
					  	tr.append($('<td  ></td>').html(acButton)); 
						$('#table1 tbody').append(tr); 
					}
					 
				});

			 

		}
		
		function editInstituteVission(instVisionId) {
			$("#loader1").show();
				$.getJSON('${editInstituteVission}',

				{
					instVisionId : instVisionId, 
					ajax : 'true'

				}, function(data) {
					$("#loader1").hide();
					document.getElementById("instVisionId").value=data.instVisionId;
					 document.getElementById("inst_vision_text").value=data.instVisionText; 
					 
				});

			 

		}
		 
	</script>

	<script type="text/javascript">
		function saveInstituteMission() {

			var instMissionText = document.getElementById("inst_mission_text").value; 
			var instMissionId = document.getElementById("instMissionId").value;
			
			if (instMissionText.trim().length>0) {
				//$('#example-1 td').remove(); 
				document.getElementById("saveMission").disabled=true;
				
				$("#loader2").show(); 
				$.getJSON('${saveInstituteMission}',

				{
					instMissionText : instMissionText, 
					instMissionId : instMissionId,
					ajax : 'true'

				}, function(data) {
				 
					$('#table2 td').remove();
					$("#loader2").hide();
					document.getElementById("saveMission").disabled=false;
					//var dataTable = $('#example-1').DataTable();
					 
					for(var i=0 ; i<data.institueMissionList.length ;i++){
						
						 
						 var acButton = '<a href="#"><span onclick="editInstituteMission('+data.institueMissionList[i].instMissionId+')" class="glyphicon glyphicon-edit" title="Edit" '
						+'data-animate=" animated fadeIn " rel="tooltip"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;'
						+' <a href="#" onclick="deleteInstituteMission('+data.institueMissionList[i].instMissionId+')"'+
						' rel="tooltip" data-color-class="danger" title="Delete" data-animate=" animated fadeIn " data-toggle="tooltip"'
						+'data-original-title="Delete  record"><span class="glyphicon glyphicon-remove"></span></a>'
						 
						/*  dataTable.row.add(
								[ i + 1, data.programVissionList[i].visionText, data.programVissionList[i].visionRemark, acButton ])
								.draw(); */  
						
						var tr = $('<tr></tr>');
						/* tr.append($('<td ></td>').html(i + 1)); */
					  	tr.append($('<td ></td>').html(data.institueMissionList[i].instMissionText)); 
					  	tr.append($('<td  ></td>').html(acButton)); 
						$('#table2 tbody').append(tr); 
					}
					
					document.getElementById("instMissionId").value=0; 
					 document.getElementById("inst_mission_text").value="";
					 
					 if(data!=null){
						 $("#sucess_msg").show();
					 }else{
						 $("#fail_msg").show();
					 }
					 
				});
				 

			} else {
				alert("Enter Institute Mission ");
			}

		}
		
		function deleteInstituteMission(instMissionId) {
			 
			//$('#example-1 td').remove();
			$("#loader2").show();
			
			$.getJSON('${deleteInstituteMission}',

			{
				instMissionId : instMissionId,  
				ajax : 'true'

			}, function(data) {
			 
				$("#loader2").hide();
				$('#table2 td').remove();
				
				//var dataTable = $('#example-1').DataTable();
				
				for(var i=0 ; i<data.institueMissionList.length ;i++){
					
					 
					 var acButton = '<a href="#"><span onclick="editInstituteMission('+data.institueMissionList[i].instMissionId+')" class="glyphicon glyphicon-edit" title="Edit" '
					+'data-animate=" animated fadeIn " rel="tooltip"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;'
					+' <a href="#" onclick="deleteInstituteMission('+data.institueMissionList[i].instMissionId+')"'+
					' rel="tooltip" data-color-class="danger" title="Delete" data-animate=" animated fadeIn " data-toggle="tooltip"'
					+'data-original-title="Delete  record"><span class="glyphicon glyphicon-remove"></span></a>'
					 
					/*  dataTable.row.add(
							[ i + 1, data.programVissionList[i].visionText, data.programVissionList[i].visionRemark, acButton ])
							.draw(); */  
					
					var tr = $('<tr></tr>');
					/* tr.append($('<td ></td>').html(i + 1)); */
				  	tr.append($('<td ></td>').html(data.institueMissionList[i].instMissionText)); 
				  	tr.append($('<td  ></td>').html(acButton)); 
					$('#table2 tbody').append(tr); 
				}
				 
			});

		 

	}
		
		function editInstituteMission(instMissionId) {
			$("#loader2").show();
				$.getJSON('${editInstituteMission}',

				{
					instMissionId : instMissionId, 
					ajax : 'true'

				}, function(data) {
					$("#loader2").hide();
					document.getElementById("instMissionId").value=data.instMissionId;
					 document.getElementById("inst_mission_text").value=data.instMissionText; 
					 
				});

			 

		}
		 
	</script>

	<script type="text/javascript">
		$(function() {
			$('#submitForm').submit(
					function() {
						$("input[type='submit']", this).val("Please Wait...")
								.attr('disabled', 'disabled');

						return true;
					});
		});

		function trim(el) {
			el.value = el.value.replace(/(^\s*)|(\s*$)/gi, ""). // removes leading and trailing spaces
			replace(/[ ]{2,}/gi, " "). // replaces multiple spaces with one space 
			replace(/\n +/, "\n"); // Removes spaces after newlines
			return;
		}
		
		function hideText() {
			var isOther = document.getElementById("isOther").value;

			if (isOther == 1) {
				document.getElementById("abc").style = "visible";
			} else {
				document.getElementById("abc").style = "display:none"
			}

		}
		function showText(showValue){
			//alert("fdhsg")
		}
	</script>




</body>
</html>