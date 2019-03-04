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

							<div class="actions panel_actions pull-right">
								<<%-- a href="${pageContext.request.contextPath}/publicationList"><button
										type="button" class="btn btn-info">Back</button></a> --%> <a
									class="box_toggle fa fa-chevron-down"></a>
							</div>

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
													<i class="fa fa-home"></i> Locational Advantages
											</a></li>
											<li><a href="#weak" data-toggle="tab"> <i
													class="fa fa-home"></i> Locational Disadvantages
											</a></li>
										
											

										</ul>

										<div class="tab-content">
										<!-- 1 -->
											<div class="tab-pane fade in active " id="strength">

												<div>


<!-- 
													<h5 class="title pull-left">
														<strong> Gender:</strong>
													</h5> -->
													<div class="col-xs-12">


														<div class="form-group">
															<label class="control-label col-sm-6" for="isReform"
																style="text-align: left;">  Locational Advantages:

 <span
																class="text-danger">*</span>
															</label>
														
															
															<div class="col-sm-1">
															<a href="#myModal1" data-toggle="modal"><button
										type="submit" class="btn btn-info" id="btn1">Add</button></a> 
										</div>
															
														</div>
													</div>
													
													
													<div class="col-xs-12">
													
															<div class="col-xs-12">

															<table id="example7"
																class="table table-striped dt-responsive display">
																<thead>
																	<tr>
																		<th width="10%">Sr No</th>
																		<th>No</th>
																		<th>Year</th>
																		
																	</tr>
																
																</thead>



																<tbody>


																</tbody>
															</table>

														</div>
														

													</div>

													<div class="clearfix"></div>
												</div>

											</div>
					<div class="tab-pane fade in  " id="weak">

												<div>


<!-- 
													<h5 class="title pull-left">
														<strong> Gender:</strong>
													</h5> -->
													<div class="col-xs-12">


														<div class="form-group">
															<label class="control-label col-sm-6" for="isReform"
																style="text-align: left;">  Locational Disadvantages:

 <span
																class="text-danger">*</span>
															</label>
														
															
															<div class="col-sm-1">
															<a href="#myModal2" data-toggle="modal"><button
										type="submit" class="btn btn-info" id="btn1">Add</button></a> 
										</div>
															
														</div>
													</div>
													
													
													<div class="col-xs-12">
													
															<div class="col-xs-12">

															<table id="example8"
																class="table table-striped dt-responsive display">
																<thead>
																	<tr>
																		<th width="10%">Sr No</th>
																		<th>No</th>
																		<th>Year</th>
																		
																	</tr>
																
																</thead>



																<tbody>


																</tbody>
															</table>

														</div>
														

													</div>

													<div class="clearfix"></div>
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


	



	<div class="modal fade col-xs-12" id="myModal1" tabindex="-1"
		role="dialog" aria-hidden="true">
		<div class="modal-dialog" style="width: 65%">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Gender

</h4>
				</div>
				<div class="modal-body">
				
					
				<div class="form-group">

						<label class="control-label col-sm-3" for="Subject/Course">Number
						</label> <input type="text" class="form-control"
							id="subName" name="subName"
							placeholder="Number"
							value="${page.pageName}" required>
					</div>
				
				<div class="form-group">
						<label class="control-label col-sm-3" for="academicYear">Academic
							Year</label> <select id="academicYear" name="academicYear"
							class="form-control" required>
							<option value="2018-2019">2018-2019</option>
							<option value="2017-2018">2017-2018</option>
							<option value="2016-2017">2016-2017</option>

						</select>
					</div>
				
				
				
			
			
					<!-- Link on Website for Activity Report -->

					<div class="form-group" style="text-align: center;">

						<button type="submit" class="btn btn-primary" onclick="getData()">Submit</button>
					</div>


				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
					<input type="hidden" id="index" name="index" value="0">
				</div>
			</div>
		</div>
	</div>
	
	
	
	<div class="modal fade col-xs-12" id="myModal2" tabindex="-1"
		role="dialog" aria-hidden="true">
		<div class="modal-dialog" style="width: 65%">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Environment and Sustainability

</h4>
				</div>
				<div class="modal-body">
				
				<div class="form-group">

						<label class="control-label col-sm-3" for="Subject/Course">Number
						</label> <input type="text" class="form-control"
							id="subName1" name="subName1"
							placeholder="Number"
							value="${page.pageName}" required>
					</div>
				
				<div class="form-group">
						<label class="control-label col-sm-3" for="academicYear">Academic
							Year</label> <select id="academicYear1" name="academicYear1"
							class="form-control" required>
							<option value="2018-2019">2018-2019</option>
							<option value="2017-2018">2017-2018</option>
							<option value="2016-2017">2016-2017</option>
								<option value="2015-2016">2015-2016</option>

						</select>
					</div>
				
					
				
				
			
			
					<!-- Link on Website for Activity Report -->

					<div class="form-group" style="text-align: center;">

						<button type="submit" class="btn btn-primary" onclick="getData1()">Submit</button>
					</div>


				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
					<input type="hidden" id="index" name="index" value="0">
				</div>
			</div>
		</div>
	</div>
	
	
	<div class="modal fade col-xs-12" id="myModal3" tabindex="-1"
		role="dialog" aria-hidden="true">
		<div class="modal-dialog" style="width: 65%">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Human Values and Professional Ethics

</h4>
				</div>
				<div class="modal-body">
				
				<div class="form-group">
						<label class="control-label col-sm-3" for="academicYear">Academic
							Year</label> <select id="academicYear2" name="academicYear2"
							class="form-control" required>
							<option value="2018-2019">2018-2019</option>
							<option value="2017-2018">2017-2018</option>
							<option value="2016-2017">2016-2017</option>
								<option value="2015-2016">2015-2016</option>

						</select>
					</div>
				
					
				<div class="form-group">

						<label class="control-label col-sm-3" for="Subject/Course">Subject/Course
						</label> <input type="text" class="form-control"
							id="subName2" name="subName2"
							placeholder="Subject/Course"
							value="${page.pageName}" required>
					</div>
				
			
			
					<!-- Link on Website for Activity Report -->

					<div class="form-group" style="text-align: center;">

						<button type="submit" class="btn btn-primary" onclick="getData2()">Submit</button>
					</div>


				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
					<input type="hidden" id="index" name="index" value="0">
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	function getData() {
		//alert("hii");
		var i = parseInt(document.getElementById("index").value);
		var academicYear = document.getElementById("academicYear").value;
		var subName = document.getElementById("subName").value;
	
	
	
		var dataTable = $('#example7').DataTable();

		dataTable.row.add(
				[ i + 1,subName,academicYear ])
				.draw();
		document.getElementById("index").value = i + 1;
	}
	
	function getData1() {
		//alert("hii");
		var i = parseInt(document.getElementById("index").value);
		var academicYear = document.getElementById("academicYear1").value;
		var subName = document.getElementById("subName1").value;
	
	
	
		var dataTable = $('#example8').DataTable();

		dataTable.row.add(
				[ i + 1,academicYear,subName ])
				.draw();
		document.getElementById("index").value = i + 1;
	}
	
	
	function getData2() {
		//alert("hii");
		var i = parseInt(document.getElementById("index").value);
		var academicYear = document.getElementById("academicYear2").value;
		var subName = document.getElementById("subName2").value;
	
	
	
		var dataTable = $('#example9').DataTable();

		dataTable.row.add(
				[ i + 1,subName,academicYear ])
				.draw();
		document.getElementById("index").value = i + 1;
	}
	
	

		function showReforms(temp) {
			//alert("hii");
			//var remark = document.getElementById("isReform").value;
			//alert(temp);
		if(temp ==1){
			document.getElementById("btn1").style ="visible"
				document.getElementById("example7").style = "visible"
		}
		else{
			
			document.getElementById("btn1").style = "display:none"
				document.getElementById("example7").style = "display:none"
		}
		
			
		}
		
		
		function showReforms1(temp) {
			//alert("hii");
			//var remark = document.getElementById("isReform").value;
			//alert(temp);
		if(temp ==1){
			document.getElementById("btn2").style ="visible"
				document.getElementById("example8").style = "visible"
		}
		else{
			
			document.getElementById("btn2").style = "display:none"
				document.getElementById("example8").style = "display:none"
		}
		
			
		}
		

		function showReforms2(temp) {
			//alert("hii");
			//var remark = document.getElementById("isReform").value;
			//alert(temp);
		if(temp ==1){
			document.getElementById("btn3").style ="visible"
				document.getElementById("example9").style = "visible"
		}
		else{
			
			document.getElementById("btn3").style = "display:none"
				document.getElementById("example9").style = "display:none"
		}
		
			
		}
	</script>


</body>
</html>