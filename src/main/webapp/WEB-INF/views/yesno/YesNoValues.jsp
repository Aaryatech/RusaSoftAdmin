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
	table, th, td {
		padding: 5px !important;
	}
	
	.act_tr:hover {
		background: #F1F1F2;
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
	
					<%-- <div class="col-xs-12">
						<div class="page-title">
	
							<div class="pull-left">
								<!-- PAGE HEADING TAG - START -->
								<h1 class="title">${title}</h1>
								<!-- PAGE HEADING TAG - END -->
							</div>
	
	
						</div>
					</div> --%>
					<div class="clearfix"></div>
					<!-- MAIN CONTENT AREA STARTS -->
	
					<div class="col-lg-12"></div>
	
	
	
					<div class="col-lg-12">
						<section class="box ">
	
							<header class="panel_header">
								<h2 class="title pull-left">${title}</h2>
	
								<div class="actions panel_actions pull-right">
	
									<c:if test="${isAdd==1}">
										<a href="${pageContext.request.contextPath}/showAddConsultancy"><button
												type="button" class="btn btn-info">Add</button></a>
	
	
									</c:if>
	
	
								</div>

							</header>
						 	<c:if test="${sessionScope.successMsg!=null}">
           						 <div class="col-lg-12">
    						          <div class="alert alert-success alert-dismissible fade in">
            							    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
             						   <strong>Success : </strong> ${sessionScope.successMsg}</div>
        	                     </div> 
        	                     <%session=request.getSession();
        	                    
        	                     session.removeAttribute("successMsg");
        	                     %>
            			</c:if>
	
	
							<div class="content-body">
								<div class="row">
									<div class="col-md-12">
										<form class="form-horizontal"
											action="${pageContext.request.contextPath}/submitYesNo"
											method="post" name="form_sample_2" id="form_sample_2"
											onsubmit="return confirm('Do you really want to submit the form?');">
	
											<!-- <ul class="nav nav-tabs">
												<li class="active"><a href="#home" data-toggle="tab">
														<i class="fa fa-home"></i> Consultancy
												</a></li>
	
											</ul> -->
	
											<!-- 	<div class="tab-content">
												<div class="tab-pane fade in active" id="home"> -->
											<!-- class="table table-bordered" -->
											<div class="table-responsive">
												<table id="table1" style="width: 100%; padding-bottom: 50px;">
													<thead>
														<tr>
															<th width="40%"></th>
															<th></th>
															<th></th>
															<th width="40%"></th>
														</tr>
													</thead>
	
													<tbody>
														<c:forEach items="${sectionList}" var="sectionList">
	
															<tr>
	
	
																<td><strong>${sectionList.yesnoSecname}</strong></td>
																<td></td>
																<td></td>
																<td></td>
															</tr>
	
															<c:forEach items="${yesNoMasterList}"
																var="yesNoMasterList">
	
																<c:if
																	test="${yesNoMasterList.yesnoSeccode==sectionList.yesnoSeccode}">
	
																	<c:set var="find" value="0"></c:set>
	
	
																	<c:forEach items="${instituteYesNoList}"
																		var="instituteYesNoList">
	
																		<c:if
																			test="${yesNoMasterList.yesnoSeccode==instituteYesNoList.sectionCode && yesNoMasterList.yesnoId==instituteYesNoList.yesnoId}">
																			<c:set var="find" value="1"></c:set>
																			<tr class="act_tr">
																				<td>&nbsp;&nbsp;&nbsp;&nbsp;${yesNoMasterList.yesnoTitle}</td>
																				<td></td>
																				<td><input type="radio"
																					name="yesNo${yesNoMasterList.yesnoId}" value="1"
																					onchange="vissibledive(${yesNoMasterList.yesnoId},1)"
																					checked> YES <input type="radio"
																					id="yesNo${yesNoMasterList.yesnoId}"
																					name="yesNo${yesNoMasterList.yesnoId}" value="0"
																					onchange="vissibledive(${yesNoMasterList.yesnoId},0)">
																					NO</td>
	
																				<td>
	
																					<div id="resps${yesNoMasterList.yesnoId}">
																						<div class="col-sm-3">
																							${yesNoMasterList.yesnoResponseTitle}</div>
																						<div class="col-sm-5">
																							<c:choose>
																								<c:when
																									test="${yesNoMasterList.yesnoResponseType==0}">
																									<input type="text" maxlength="50"
																										class="form-control"
																										id="respnsevalue${yesNoMasterList.yesnoId}"
																										name="respnsevalue${yesNoMasterList.yesnoId}"
																										placeholder="Enter value"
																										value="${instituteYesNoList.instYesnoResponse}">
																								</c:when>
																								<c:when
																									test="${yesNoMasterList.yesnoResponseType==1}">
																									<select
																										id="respnsevalue${yesNoMasterList.yesnoId}"
																										name="respnsevalue${yesNoMasterList.yesnoId}"
																										class="form-control choosen">
	
																										<c:forEach items="${yesNoMasterList.jsonArray}"
																											var="jsonArray">
																											<c:choose>
																												<c:when
																													test="${instituteYesNoList.instYesnoResponse==jsonArray}">
																													<option value="${jsonArray}" selected>${jsonArray}</option>
																												</c:when>
																												<c:otherwise>
																													<option value="${jsonArray}">${jsonArray}</option>
																												</c:otherwise>
																											</c:choose>
	
																										</c:forEach>
																									</select>
																								</c:when>
																								<c:otherwise>
																									<input type="hidden"
																										id="respnsevalue${yesNoMasterList.yesnoId}"
																										name="respnsevalue${yesNoMasterList.yesnoId}"
																										value="${instituteYesNoList.instYesnoResponse}">
																								</c:otherwise>
	
																							</c:choose>
																						</div>
																					</div>
																				</td>
																			</tr>
																		</c:if>
	
																	</c:forEach>
	
																	<c:if test="${find==0}">
	
																		<tr class="act_tr">
																			<td>&nbsp;&nbsp;&nbsp;&nbsp;${yesNoMasterList.yesnoTitle}</td>
																			<td></td>
																			<td><input type="radio"
																				name="yesNo${yesNoMasterList.yesnoId}"
																				onchange="vissibledive(${yesNoMasterList.yesnoId},1)"
																				value="1"> YES <input type="radio"
																				id="yesNo${yesNoMasterList.yesnoId}"
																				name="yesNo${yesNoMasterList.yesnoId}" value="0"
																				onchange="vissibledive(${yesNoMasterList.yesnoId},0)"
																				checked> NO</td>
	
																			<td><div id="resps${yesNoMasterList.yesnoId}"
																					style="display: none;">
																					<div class="col-sm-3">
																						${yesNoMasterList.yesnoResponseTitle}</div>
																					<div class="col-sm-5">
																						<c:choose>
																							<c:when
																								test="${yesNoMasterList.yesnoResponseType==0}">
																								<input type="text" maxlength="50"
																									class="form-control"
																									id="respnsevalue${yesNoMasterList.yesnoId}"
																									name="respnsevalue${yesNoMasterList.yesnoId}"
																									placeholder="Enter value">
																							</c:when>
																							<c:when
																								test="${yesNoMasterList.yesnoResponseType==1}">
																								<select
																									id="respnsevalue${yesNoMasterList.yesnoId}"
																									name="respnsevalue${yesNoMasterList.yesnoId}"
																									class="form-control">
																									<c:forEach items="${yesNoMasterList.jsonArray}"
																										var="jsonArray">
																										<option value="${jsonArray}">${jsonArray}</option>
																									</c:forEach>
																								</select>
																							</c:when>
																							<c:otherwise>
																								<input type="hidden"
																									id="respnsevalue${yesNoMasterList.yesnoId}"
																									name="respnsevalue${yesNoMasterList.yesnoId}"
																									value="-">
																							</c:otherwise>
	
																						</c:choose>
	
																					</div>
																				</div></td>
																		</tr>
	
																	</c:if>
	
	
																</c:if>
															</c:forEach>
	
	
	
														</c:forEach>
	
														<tr>
															<td><strong>Others</strong></td>
															<td></td>
															<td><a data-toggle="modal" href="#myModal">
																	<button type="button" class="btn btn-info">
																		<i class="${sessionScope.addIcon}" aria-hidden="true"></i>Add
																	</button>
															</a></td>
															<td></td>
														</tr>
	
														<c:set var="sr" value="0"></c:set>
														<c:forEach items="${instituteYesNoList}"
															var="instituteYesNoList" varStatus="count">
															<c:if test="${instituteYesNoList.yesnoId==0}">
																<c:set var="sr" value="${count.index}"></c:set>
	
																<tr class="act_tr">
																	<td>&nbsp;&nbsp;&nbsp;&nbsp;${instituteYesNoList.yesnoDynamicTitle}</td>
																	<td></td>
																	<td><input type="radio"
																		id="dynamicprevyesno${instituteYesNoList.instYesnoId}"
																		name="dynamicprevyesno${instituteYesNoList.instYesnoId}"
																		value="1"
																		onchange="otherpreviousvissibledive(${instituteYesNoList.instYesnoId},1)"
																		checked> YES <input type="radio"
																		id="dynamicprevyesno${instituteYesNoList.instYesnoId}"
																		name="dynamicprevyesno${instituteYesNoList.instYesnoId}"
																		value="0"
																		onchange="otherpreviousvissibledive(${instituteYesNoList.instYesnoId},0)">
																		NO</td>
	
																	<td><div
																			id="otherprevresps${instituteYesNoList.instYesnoId}">
																			<div class="col-sm-3">Enter Value</div>
																			<div class="col-sm-5">
																				<input type="text" maxlength="50"
																					class="form-control"
																					id="dynamicprevyesnovalue${instituteYesNoList.instYesnoId}"
																					name="dynamicprevyesnovalue${instituteYesNoList.instYesnoId}"
																					placeholder="Enter value"
																					value="${instituteYesNoList.instYesnoResponse}">
																			</div>
																		</div></td>
																</tr>
	
	
	
															</c:if>
														</c:forEach>
	
													</tbody>
	
												</table>
											</div>
	
											<input type="hidden" id="srindex" name="srindex" value="0">
											<!-- </div>
	
	
											</div> -->
											<br>
											<div class="form-group">
												<div class="col-sm-offset-6 col-sm-6">
													<!-- <input type="submit" class="btn btn-primary" value="Submit"> -->
	
													<button type="submit" id="sub_button"
														class="btn btn-primary">
														<i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save
													</button>
													<!-- 	<button type="reset" class="btn btn-default">Reset</button> -->
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
	
		<div aria-hidden="true" role="dialog" tabindex="-1" id="myModal"
			class="modal fade" style="display: none;">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button aria-hidden="true" data-dismiss="modal" class="close"
							type="button">×</button>
						<h4 class="modal-title">Others</h4>
					</div>
					<div class="modal-body">
						<%-- <form role="form"
							action="${pageContext.request.contextPath}/showModuleForm"
							method="get"> --%>
	
	
						<div class="form-group">
	
							<label class="control-label col-sm-6" for="othertitle">Title</label>
							<!-- <div class="col-sm-3"> -->
							<input type="text" class="form-control" id="othertitle"
								name="othertitle" placeholder="Title">
							<!-- </div> -->
						</div>
	
	
						<button type="button" class="btn btn-primary"
							onclick="addDynamicYesNo()">ADD</button>
						<!-- 	</form> -->
					</div>
				</div>
			</div>
		</div>
		<!-- END CONTAINER -->
		<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->
	
		<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
	
	
	
		<script type="text/javascript">
			function vissibledive(yesnoId,value) {
				 
				if(value==1){
					
					document.getElementById("resps"+yesnoId).style = "visible";
					
				}else{
					document.getElementById("resps"+yesnoId).style = "display:none";
				}
	
			}
		</script>
	
		<script type="text/javascript">
			function othervissibledive(yesnoId,value) {
				 
				if(value==1){
					
					document.getElementById("otherresps"+yesnoId).style = "visible";
					
				}else{
					document.getElementById("otherresps"+yesnoId).style = "display:none";
				}
	
			}
			function otherpreviousvissibledive(yesnoId,value) {
				 
				if(value==1){
					
					document.getElementById("otherprevresps"+yesnoId).style = "visible";
					
				}else{
					document.getElementById("otherprevresps"+yesnoId).style = "display:none";
				}
	
			}
		</script>
		<script type="text/javascript">
			function addDynamicYesNo() {
				 
				var othertitle = document.getElementById("othertitle").value; 
				var srindex = parseInt(document.getElementById("srindex").value);
				
				if(othertitle=="" || othertitle==null){
					
					alert("Enter Title");
					
				}else{
					
					srindex=srindex+1;
					
					var acButton = '<input type="radio" name="dynamicyesno'+srindex+'" value="1" onchange="othervissibledive('+srindex+',1)" checked>'+
					'YES <input type="radio" id="dynamicyesno'+srindex+'" name="dynamicyesno'+srindex+'" value="0" onchange="othervissibledive('+srindex+',0)"> NO'
					
					var div = '<div id="otherresps'+srindex+'"> <div class="col-sm-3">Enter Value</div> <div class="col-sm-5"> <input type="text" maxlength="50"'+
					' class="form-control" id="dynamicyesnovalue'+srindex+'" name="dynamicyesnovalue'+srindex+'" placeholder="Enter value" > </div> </div>'
					
					
					var tr = $('<tr class="act_tr" ></tr>'); 
				  	tr.append($('<td  ></td>').html('&nbsp;&nbsp;&nbsp;&nbsp;<input type="hidden" id="otherTitleName'+srindex+'" name="otherTitleName'+srindex+'" value="'+othertitle+'">'+othertitle));
				  	tr.append($('<td  ></td>').html(''));
				  	tr.append($('<td  ></td>').html(acButton)); 
				  	tr.append($('<td   ></td>').html(div)); 
					$('#table1 tbody').append(tr); 
					
					 document.getElementById("srindex").value= srindex; 
					 $('#myModal').modal('hide');
				}
				
	
			}
		</script>
	
	
	</body>
	</html>
	
	
	
