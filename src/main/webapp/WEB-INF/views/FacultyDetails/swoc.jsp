<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<!DOCTYPE html>
<html class=" ">
<head>

<c:url value="/saveSWOC" var="saveSWOC"></c:url>
<c:url value="/deleteSwoc" var="deleteSwoc"></c:url>
<c:url value="/editSwoc" var="editSwoc"></c:url>

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
				<!-- MAIN CONTENT AREA STARTS -->

				<div class="col-lg-12"></div>



				<div class="col-lg-12">
					<section class="box ">

						<header class="panel_header">
							<h2 class="title pull-left">${title}</h2>

							<div class="actions panel_actions pull-right"></div>
					<%-- 		
						<div class="col-lg-12" id="sucess_msg" style="display: none;">
								<!-- onclick="location.reload();" -->
								<div class="alert alert-success alert-dismissible fade in">
									<button type="button" class="close" onclick="hideAlert()">
										<span aria-hidden="true">×</span>
									</button>
									<strong>Success : </strong>${msg}</div>
							</div>
									
									
									<div class="col-lg-12" id="fail_msg" style="display: none;">
								<div class="alert alert-success alert-dismissible fade in">
									<button type="button" class="close" data-dismiss="alert"
										aria-label="Close">
										<span aria-hidden="true">×</span>
									</button>
									<strong>Fail : </strong>${failMsg}</div>


							</div> --%>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertPublicationDetail"
										method="post" enctype="multipart/form-data"
										name="form_sample_2" id="form_sample_2"
										onsubmit="return confirm('Do you really want to submit the form?');">

										<ul class="nav nav-tabs">
											<li class="active"><a href="#strength" data-toggle="tab">
													<i class="fa fa-home"></i> Strength
											</a></li>
											<li><a href="#weak" data-toggle="tab"> <i
													class="fa fa-home"></i> Weakness
											</a></li>
											<li><a href="#oppt" data-toggle="tab"> <i
													class="fa fa-home"></i> Opportunity
											</a></li>
											<li><a href="#challenge" data-toggle="tab"> <i
													class="fa fa-home"></i> Challenges
											</a></li>


										</ul>
										<input type="hidden" value="0" name="swocId" id="swocId">

										<div class="tab-content">
											<!-- 1 -->
											<div class="tab-pane fade in active " id="strength">

												<div class="row">

													<label class="col-sm-2 text-left" for="hh">
														Strength <span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="text" maxlength="200" class="form-control"
															id="swocText" name="swocText" placeholder="Strength"
															required>
													</div>

													<div class="col-sm-4">



														<button type="submit" id="sub_button"
															class="btn btn-primary" onclick="saveSWOCList(1)">
															<i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save
														</button>
													</div>
												</div>

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
																		<th style="text-align: center" width="10%">Sr No</th>
																		<th style="text-align: center">Strength</th>

																		<th style="text-align: center" width="10%">Action</th>

																	</tr>

																</thead>
																<tbody>
																	<c:forEach items="${strengthList}" var="list"
																		varStatus="count">
																		<tr>

																			<td style="text-align: center;">${count.index+1}</td>
																			<td>${list.swocText}</td>



																			<td align="center"><a href="#"
																				onclick="editSwoc(${list.swocId})"><span
																					class="glyphicon glyphicon-edit" title="Edit"
																					data-animate=" animated fadeIn " rel="tooltip"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;

																				<a href="#"
																				onclick="deleteSwoc(${list.swocId},${list.swocType})"
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
											<div class="tab-pane fade in " id="weak">

												<div class="row">
													<label class="col-sm-2 text-left" for="weak">
														Weakness <span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="text" maxlength="200" class="form-control"
															id="swocTextWeak" name="swocTextWeak"
															placeholder="Weakness" required>
													</div>
													<div class="col-sm-4">


														<button type="submit" id="sub_button"
															class="btn btn-primary" onclick="saveSWOCList(2)">
															<i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save
														</button>
													</div>
												</div>
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
																		<th style="text-align: center" width="10%">Sr No</th>
																		<th style="text-align: center">Weakness</th>
																		<th style="text-align: center" width="10%">Action</th>

																	</tr>

																</thead>
																<tbody>
																	<c:forEach items="${weakNessList}" var="list"
																		varStatus="count">
																		<tr>

																			<td style="text-align: center;">${count.index+1}</td>
																			<td>${list.swocText}</td>

																			<td align="center"><a href="#"
																				onclick="editSwoc(${list.swocId})"><span
																					class="glyphicon glyphicon-edit" title="Edit"
																					data-animate=" animated fadeIn " rel="tooltip"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;

																				<a href="#"
																				onclick="deleteSwoc(${list.swocId},${list.swocType})"
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


											<div class="tab-pane " id="oppt">

	<div align="center" id="loader3" style="display: none;">
													<img
														src="${pageContext.request.contextPath}/resources/assets/images/loader.gif"
														style="width: 50px; height: 50px;">
												</div>



												<div class="row">
													<label class="col-sm-2 text-left" for="Opportunity">
														Opportunity <span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="text" maxlength="200" class="form-control"
															id="swocTextOpp" name="swocTextOpp"
															placeholder="Opportunity" required>
													</div>

													<div class="col-sm-4">


														<button type="submit" id="sub_button"
															class="btn btn-primary" onclick="saveSWOCList(3)">
															<i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save
														</button>
													</div>
												</div>


												<div class="row">


													<div class="col-xs-12">
														<div class="table-responsive">
															<table class="table table-bordered" id="table3">
																<thead>

																	<tr>
																		<th style="text-align: center" width="10%">Sr No</th>
																		<th style="text-align: center">Opportunity</th>
																		<th style="text-align: center" width="10%">Action</th>

																	</tr>

																</thead>
																<tbody>
																	<c:forEach items="${oppList}" var="list"
																		varStatus="count">
																		<tr>

																			<td style="text-align: center;">${count.index+1}</td>
																			<td>${list.swocText}</td>

																			<td align="center"><a href="#"
																				onclick="editSwoc(${list.swocId})"><span
																					class="glyphicon glyphicon-edit" title="Edit"
																					data-animate=" animated fadeIn " rel="tooltip"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;

																				<a href="#"
																				onclick="deleteSwoc(${list.swocId},${list.swocType})"
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

											<div class="tab-pane " id="challenge">

												<!-- 	<input type="hidden" id="swocType" name="swocType" value="4"> -->
												<label class="col-sm-2 text-left" for="hh">
													Challenge <span class="text-danger">*</span>
												</label>
												<div class="col-sm-6">
													<input type="text" maxlength="200" class="form-control"
														id="swocTextCha" name="swocTextCha"
														placeholder="Challenge" required>
												</div>

												<div class="col-sm-4">


													<button type="submit" id="sub_button"
														class="btn btn-primary" onclick="saveSWOCList(4)">
														<i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save
													</button>
												</div>


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
																		<th style="text-align: center" width="10%">Sr No</th>
																		<th style="text-align: center">Challenges</th>
																		<th style="text-align: center" width="10%">Action</th>

																	</tr>

																</thead>
																<tbody>
																	<c:forEach items="${challengelist}" var="list"
																		varStatus="count">
																		<tr>

																			<td style="text-align: center;">${count.index+1}</td>
																			<td>${list.swocText}</td>

																			<td align="center"><a href="#"
																				onclick="editSwoc(${list.swocId})"><span
																					class="glyphicon glyphicon-edit" title="Edit"
																					data-animate=" animated fadeIn " rel="tooltip"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;

																				<a href="#"
																				onclick="deleteSwoc(${list.swocId},${list.swocType})"
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



										<!--  -->
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
		function saveSWOCList(swocType) {
			//alert(swocType);
			
			if(swocType==1)
				{
			 
				var swocText = document.getElementById("swocText").value;
				}
			else if(swocType==2)
				{
			 
				var swocText = document.getElementById("swocTextWeak").value;
				}
			
			else if(swocType==3)
			{
				 
			var swocText = document.getElementById("swocTextOpp").value;
			}
			else if(swocType==4)
			{ 
			var swocText = document.getElementById("swocTextCha").value;
			}

		//	var swocType = document.getElementById("swocType").value;
			//var swocText = document.getElementById("swocText").value;
			var swocId = document.getElementById("swocId").value;
			
		 
			
			//alert(swocType);

			if (swocText != "") {
				 
				
				$("#loader1").show();
				$("#loader2").show();
				$("#loader3").show();
				$("#loader4").show();
				$
						.getJSON(
								'${saveSWOC}',

								{
									swocType : swocType,
									swocText : swocText,
									swocId : swocId,

									ajax : 'true'

								},
								function(data) {
									
									if(swocType==1)
									{
									$('#table1 td').remove();
									 
									}
								else if(swocType==2)
									{
									$('#table2 td').remove();
									 
									}
								
								else if(swocType==3)
								{
									$('#table3 td').remove();
								 
								}
								else if(swocType==4)
								{
									$('#table4 td').remove();
								 
								}

									$("#loader1").hide();
									$("#loader2").hide();
									$("#loader3").hide();
									$("#loader4").hide();
									//var dataTable = $('#example-1').DataTable();

									for (var i = 0; i < data.length; i++) {

										var acButton = '<a href="#"><span onclick="editSwoc('
												+ data[i].swocId
												+ ')" class="glyphicon glyphicon-edit" title="Edit" '
												+ 'data-animate=" animated fadeIn " rel="tooltip"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;'
												+ ' <a href="#" onclick="deleteSwoc('+ data[i].swocId+ ' ,'+ data[i].swocType+')"'
												+ ' rel="tooltip" data-color-class="danger" title="Delete" data-animate=" animated fadeIn " data-toggle="tooltip"'
												+ 'data-original-title="Delete  record"><span class="glyphicon glyphicon-remove"></span></a>'

										/*  dataTable.row.add(
												[ i + 1, data.programVissionList[i].visionText, data.programVissionList[i].visionRemark, acButton ])
												.draw(); */
												
												if(data[i].swocType==1)
													{

										var tr = $('<tr></tr>');
										tr.append($('<td ></td>').html(i + 1));

										tr.append($('<td ></td>').html(
												data[i].swocText));

										tr.append($('<td  ></td>').html(
												acButton));
										$('#table1 tbody').append(tr);
										
										if(data!=null){
											 $("#sucess_msg").show();
										 } else{
											 $("#fail_msg").show();
										 }
										
													}else if(data[i].swocType==2){
													
														var tr = $('<tr></tr>');
														tr.append($('<td ></td>').html(i + 1));

														tr.append($('<td ></td>').html(
																data[i].swocText));

														tr.append($('<td  ></td>').html(
																acButton));
														$('#table2 tbody').append(tr);
														
														if(data!=null){
															 $("#sucess_msg").show();
															 
														 } else{
															 $("#fail_msg").show();
														 }
														
																	}
													else if(data[i].swocType==3){

														var tr = $('<tr></tr>');
														tr.append($('<td ></td>').html(i + 1));

														tr.append($('<td ></td>').html(
																data[i].swocText));

														tr.append($('<td  ></td>').html(
																acButton));
														$('#table3 tbody').append(tr);
													
														if(data!=null){
															 $("#sucess_msg").show();
														 } else{
															 $("#fail_msg").show();
														 }
													
													}
													else if(data[i].swocType==4){

											var tr = $('<tr></tr>');
											tr.append($('<td ></td>').html(i + 1));

											tr.append($('<td ></td>').html(
													data[i].swocText));

											tr.append($('<td  ></td>').html(
													acButton));
											$('#table4 tbody').append(tr);
														
											if(data!=null){
												 $("#sucess_msg").show();
											 } else{
												 $("#fail_msg").show();
											 }
													
													}
									}
									document.getElementById("swocId").value = 0;
									document.getElementById("swocText").value = "";
									document.getElementById("swocTextWeak").value = "";
									document.getElementById("swocTextOpp").value = "";
									document.getElementById("swocTextCha").value = "";

								});

			} else {
				alert("Enter Text ");
			}

		}

		function deleteSwoc(swocId,swocType) {
		//	alert("hii");
		if(swocType==1)
			{
			$('#table1 td').remove();
			$("#loader1").show();
			}
		else if(swocType==2)
			{
			$('#table2 td').remove();
			$("#loader1").show();
			}
		else if(swocType==3)
		{
		$('#table3 td').remove();
		$("#loader1").show();
		}
		else if(swocType==4)
		{
		$('#table4 td').remove();
		$("#loader1").show();
		}

			$
					.getJSON(
							'${deleteSwoc}',

							{
								swocId : swocId,
								swocType:swocType,
								ajax : 'true'

							},

							function(data) {

								$("#loader1").hide();

								//var dataTable = $('#example-1').DataTable();

								for (var i = 0; i < data.length; i++) {

									var acButton = '<a href="#"><span onclick="editSwoc('
											+ data[i].swocId
											+ ')" class="glyphicon glyphicon-edit" title="Edit" '
											+ 'data-animate=" animated fadeIn " rel="tooltip"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;'
											+ ' <a href="#" onclick="deleteSwoc('+ data[i].swocId+ ' ,'+ data[i].swocType+')"'
											+ ' rel="tooltip" data-color-class="danger" title="Delete" data-animate=" animated fadeIn " data-toggle="tooltip"'
											+ 'data-original-title="Delete  record"><span class="glyphicon glyphicon-remove"></span></a>'

									 

											if(data[i].swocType==1)
											{

								var tr = $('<tr></tr>');
								tr.append($('<td ></td>').html(i + 1));

								tr.append($('<td ></td>').html(
										data[i].swocText));

								tr.append($('<td  ></td>').html(
										acButton));
								$('#table1 tbody').append(tr);
								
								
											}else if(data[i].swocType==2){

												var tr = $('<tr></tr>');
												tr.append($('<td ></td>').html(i + 1));

												tr.append($('<td ></td>').html(
														data[i].swocText));

												tr.append($('<td  ></td>').html(
														acButton));
												$('#table2 tbody').append(tr);
															}
											else if(data[i].swocType==3){

												var tr = $('<tr></tr>');
												tr.append($('<td ></td>').html(i + 1));

												tr.append($('<td ></td>').html(
														data[i].swocText));

												tr.append($('<td  ></td>').html(
														acButton));
												$('#table3 tbody').append(tr);
															}
											else if(data[i].swocType==4){

									var tr = $('<tr></tr>');
									tr.append($('<td ></td>').html(i + 1));

									tr.append($('<td ></td>').html(
											data[i].swocText));

									tr.append($('<td  ></td>').html(
											acButton));
									$('#table4 tbody').append(tr);
												}
								}
								document.getElementById("swocId").value = 0;
								document.getElementById("swocText").value = "";

							});

		}

		function editSwoc(swocId) {
			$("#loader1").show();
			//alert("hii");
			//alert(swocId);
			$.getJSON('${editSwoc}',

			{
				swocId : swocId,
				ajax : 'true'

			}, function(data) {
				//alert(data);
				$("#loader1").hide();
				
				
				if(data.swocType==1)
				{
				 
				document.getElementById("swocText").value = data.swocText;
				}
				else if(data.swocType==2)
				{ 
				 
					document.getElementById("swocTextWeak").value = data.swocText;
					}
				else if(data.swocType==3)
				{ 
					 
					document.getElementById("swocTextOpp").value = data.swocText;
					}
				else if(data.swocType==4)
				{ 
				 
					document.getElementById("swocTextCha").value = data.swocText;
					}
				
				document.getElementById("swocId").value = data.swocId;

			});

		}
		
		function hideAlert(){
			$("#sucess_msg").hide();
			$("#fail_msg").hide();
		}
	</script>



</body>
</html>