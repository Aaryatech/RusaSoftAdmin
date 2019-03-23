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


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/submitfullyDinamicYesNo"
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

													<tr>
														<td><strong>Evaluation Process And Reforms</strong></td>
														<td></td>
														<td><a data-toggle="modal" href="#myModal">
																<button type="button" onclick="settype(1);" class="btn btn-info">Add</button>
														</a></td>
														<td></td>
													</tr>

													<c:set var="sr" value="0"></c:set>
													<c:forEach items="${instituteYesNoTab1List}"
														var="instituteYesNoList" varStatus="count">
														<c:if test="${instituteYesNoList.yesnoId==0}">
															<c:set var="sr" value="${count.index}"></c:set>

															<tr class="act_tr">
																<td>&nbsp;&nbsp;&nbsp;&nbsp;${instituteYesNoList.yesnoDynamicTitle}</td>
																<td></td>
																<td><input type="radio"
																	id="tab1prevyesno${instituteYesNoList.instYesnoId}"
																	name="tab1prevyesno${instituteYesNoList.instYesnoId}"
																	value="1"
																	onchange="tab1previousvissibledive(${instituteYesNoList.instYesnoId},1)"
																	checked> YES <input type="radio"
																	id="tab1prevyesno${instituteYesNoList.instYesnoId}"
																	name="tab1prevyesno${instituteYesNoList.instYesnoId}"
																	value="0"
																	onchange="tab1previousvissibledive(${instituteYesNoList.instYesnoId},0)">
																	NO</td>

																<td><div
																		id="tab1otherprevresps${instituteYesNoList.instYesnoId}">
																		<div class="col-sm-3">Enter Value</div>
																		<div class="col-sm-5">
																			<input type="text" maxlength="50"
																				class="form-control"
																				id="tab1dynamicprevyesnovalue${instituteYesNoList.instYesnoId}"
																				name="tab1dynamicprevyesnovalue${instituteYesNoList.instYesnoId}"
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

										<div class="table-responsive">
											<table id="table2" style="width: 100%; padding-bottom: 50px;">
												<thead>
													<tr>
														<th width="40%"></th>
														<th></th>
														<th></th>
														<th width="40%"></th>
													</tr>
												</thead>

												<tbody>

													<tr>
														<td><strong>Mechanism of Examination related
																Grievances</strong></td>
														<td></td>
														<td><a data-toggle="modal" href="#myModal">
																<button type="button" onclick="settype(2);" class="btn btn-info">Add</button>
														</a></td>
														<td></td>
													</tr>

													<c:set var="sr" value="0"></c:set>
													<c:forEach items="${instituteYesNoTab2List}"
														var="instituteYesNoList" varStatus="count">
														<c:if test="${instituteYesNoList.yesnoId==0}">
															<c:set var="sr" value="${count.index}"></c:set>

															<tr class="act_tr">
																<td>&nbsp;&nbsp;&nbsp;&nbsp;${instituteYesNoList.yesnoDynamicTitle}</td>
																<td></td>
																<td><input type="radio"
																	id="tab2prevyesno${instituteYesNoList.instYesnoId}"
																	name="tab2prevyesno${instituteYesNoList.instYesnoId}"
																	value="1"
																	onchange="tab2previousvissibledive(${instituteYesNoList.instYesnoId},1)"
																	checked> YES <input type="radio"
																	id="tab2prevyesno${instituteYesNoList.instYesnoId}"
																	name="tab2prevyesno${instituteYesNoList.instYesnoId}"
																	value="0"
																	onchange="tab2previousvissibledive(${instituteYesNoList.instYesnoId},0)">
																	NO</td>

																<td><div
																		id="tab2otherprevresps${instituteYesNoList.instYesnoId}">
																		<div class="col-sm-3">Enter Value</div>
																		<div class="col-sm-5">
																			<input type="text" maxlength="50"
																				class="form-control"
																				id="tab2dynamicprevyesnovalue${instituteYesNoList.instYesnoId}"
																				name="tab2dynamicprevyesnovalue${instituteYesNoList.instYesnoId}"
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

										<input type="hidden" id="tab1index" name="tab1index" value="0">

										<input type="hidden" id="tab2index" name="tab2index" value="0">

										<!-- </div>


										</div> -->
										<br>
										<div class="form-group">
											<div class="col-sm-offset-2 col-sm-10">
												<input type="submit" class="btn btn-primary" value="Add">
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
					<input type="hidden" id="tableTab" name="tableTab" value="0">

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
		 
		function tab1previousvissibledive(yesnoId,value) {
			
			if(value==1){
				
				document.getElementById("tab1otherprevresps"+yesnoId).style = "visible";
				
			}else{
				document.getElementById("tab1otherprevresps"+yesnoId).style = "display:none";
			}
			 
		}
		
		function tab2previousvissibledive(yesnoId,value) {
			 
			if(value==1){
				
				document.getElementById("tab2otherprevresps"+yesnoId).style = "visible";
				
			}else{
				document.getElementById("tab2otherprevresps"+yesnoId).style = "display:none";
			}

		}
	</script>
	<script type="text/javascript">
	
	function settype(tabtype) {
		 
		 document.getElementById("tableTab").value= tabtype;
		 
	}
		function addDynamicYesNo() {
			 
			var othertitle = document.getElementById("othertitle").value;  
			var tableTab = parseInt(document.getElementById("tableTab").value);
			
			if(othertitle=="" || othertitle==null){
				
				alert("Enter Title");
				
			}else{
				
				if(tableTab==1){
					appendtab1();
				}
				else if(tableTab==2){
					appendtab2();
				}
			}
			

		}
		function appendtab1() {
			 
			var srindex = parseInt(document.getElementById("tab1index").value);
			srindex=srindex+1;
			var othertitle = document.getElementById("othertitle").value;
			var acButton = '<input type="radio" name="tab1dynamicyesno'+srindex+'" value="1" onchange="tab1othervissibledive('+srindex+',1)" checked>'+
			'YES <input type="radio" id="tab1dynamicyesno'+srindex+'" name="tab1dynamicyesno'+srindex+'" value="0" onchange="tab1othervissibledive('+srindex+',0)"> NO'
			
			var div = '<div id="tab1otherresps'+srindex+'"> <div class="col-sm-3">Enter Value</div> <div class="col-sm-5"> <input type="text" maxlength="50"'+
			' class="form-control" id="tab1dynamicyesnovalue'+srindex+'" name="tab1dynamicyesnovalue'+srindex+'" placeholder="Enter value" > </div> </div>'
			
			
			var tr = $('<tr class="act_tr" ></tr>'); 
		  	tr.append($('<td  ></td>').html('&nbsp;&nbsp;&nbsp;&nbsp;<input type="hidden" id="tab1otherTitleName'+srindex+'" name="tab1otherTitleName'+srindex+'" value="'+othertitle+'">'+othertitle));
		  	tr.append($('<td  ></td>').html(''));
		  	tr.append($('<td  ></td>').html(acButton)); 
		  	tr.append($('<td   ></td>').html(div)); 
			$('#table1 tbody').append(tr); 
			
			 document.getElementById("tab1index").value= srindex;
			 document.getElementById("othertitle").value= "";

		}
		function appendtab2() {
			 
			var srindex = parseInt(document.getElementById("tab2index").value);
			srindex=srindex+1;
			var othertitle = document.getElementById("othertitle").value;
			var acButton = '<input type="radio" name="tab2dynamicyesno'+srindex+'" value="1" onchange="tab2othervissibledive('+srindex+',1)" checked>'+
			'YES <input type="radio" id="tab2dynamicyesno'+srindex+'" name="tab2dynamicyesno'+srindex+'" value="0" onchange="tab2othervissibledive('+srindex+',0)"> NO'
			
			var div = '<div id="tab2otherresps'+srindex+'"> <div class="col-sm-3">Enter Value</div> <div class="col-sm-5"> <input type="text" maxlength="50"'+
			' class="form-control" id="tab2dynamicyesnovalue'+srindex+'" name="tab2dynamicyesnovalue'+srindex+'" placeholder="Enter value" > </div> </div>'
			
			
			var tr = $('<tr class="act_tr" ></tr>'); 
		  	tr.append($('<td  ></td>').html('&nbsp;&nbsp;&nbsp;&nbsp;<input type="hidden" id="tab2otherTitleName'+srindex+'" name="tab2otherTitleName'+srindex+'" value="'+othertitle+'">'+othertitle));
		  	tr.append($('<td  ></td>').html(''));
		  	tr.append($('<td  ></td>').html(acButton)); 
		  	tr.append($('<td   ></td>').html(div)); 
			$('#table2 tbody').append(tr); 
			
			 document.getElementById("tab2index").value= srindex;
			 document.getElementById("othertitle").value= "";
		}
	</script>

<script type="text/javascript">
		 
		function tab1othervissibledive(yesnoId,value) {
			
			if(value==1){
				
				document.getElementById("tab1otherresps"+yesnoId).style = "visible";
				
			}else{
				document.getElementById("tab1otherresps"+yesnoId).style = "display:none";
			}
			 
		}
		
		function tab2othervissibledive(yesnoId,value) {
			 
			if(value==1){
				
				document.getElementById("tab2otherresps"+yesnoId).style = "visible";
				
			}else{
				document.getElementById("tab2otherresps"+yesnoId).style = "display:none";
			}

		}
	</script>

</body>
</html>



