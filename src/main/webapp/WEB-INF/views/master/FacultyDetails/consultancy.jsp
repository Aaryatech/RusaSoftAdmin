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
<body class=" " >
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
							<h1 class="title">${title}</h1>
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
							<a href="#myModal"
														data-toggle="modal"><button type="submit"
																class="btn btn-info">Add</button></a>
								<%-- <a href="${pageContext.request.contextPath}/publicationList"><button
										type="button" class="btn btn-info">Back</button></a>  --%>
										<a
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
											<li class="active"><a href="#home" data-toggle="tab">
													<i class="fa fa-home"></i> Register
											</a></li>
											
										</ul>

										<div class="tab-content">
											<div class="tab-pane fade in active" id="home">

												<div>


													<div class="col-xs-12">

														<h5 class="title pull-left">
															<strong>Consultancy Details</strong>
														</h5>

														<div class="col-xs-12"></div>
														 <label class="control-label col-sm-3" for="smallheading">
														Consultancy <span class="text-danger">*</span>
														</label> 
														<div class="col-sm-2">
																Yes <input type="radio" name="consultancy"
																	id="consultancy" checked value="0"> No<input
																	type="radio" name="consultancy" id="consultancy"
																	value="1">
															</div> 
														<div class="col-xs-12">




															<table id="example1"
																class="table table-striped dt-responsive display">
																<thead>
																	<tr>
																		<th width="10%">Sr No</th>
																		<th width="30%">Nature of Consultancy</th>
																		<th width="30%">Sponsoring Industry</th>
																		<th width="30%">Consultancy Amount</th>
																		<th width="30%">Consultancy Period</th>
																		<th width="30%">Project Completed</th>
																	</tr>
																</thead>



															</table>




														</div>

														
														<div class="form-group">
															<div class="col-sm-offset-2 col-sm-10">
																<button type="submit" class="btn btn-primary">Submit</button>
																<button type="reset" class="btn btn-default">Reset</button>
															</div>
														</div>

													</div>


													<div class="clearfix"></div>

												</div>

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
	
	
<div aria-hidden="true" role="dialog" tabindex="-1" id="myModal"
		class="modal fade" style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button aria-hidden="true" data-dismiss="modal" class="close"
						type="button">×</button>
					<h4 class="modal-title">Consultancy Details</h4>
				</div>
				<div class="modal-body">
					<%-- <form role="form"
						action="${pageContext.request.contextPath}/showModuleForm"
						method="get"> --%>
						<input type="hidden" class="form-control" id="pageId"
							name="pageId" >
							
									
						
						
						<div class="form-group">
						
							<label class="control-label col-sm-3" for="page_name">Nature of Consultancy
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="nature"
									name="nature" placeholder="" value="${page.pageName}"
									>
							<!-- </div> -->
	                    </div>
	                    
	                    	<div class="form-group">
						
							<label class="control-label col-sm-3" for="page_name">Sponsoring Agency/Industry
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="sponser"
									name="sponser" placeholder="" value="${page.pageName}"
									>
							<!-- </div> -->
	                    </div>
	                    
	                    <div class="form-group">
						
							<label class="control-label col-sm-3" for="page_name">Amount of Consultancy
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="amount"
									name="amount" placeholder="" value="${page.pageName}"
									>
							<!-- </div> -->
	                    </div>
	                    <div class="form-group">
						
							<label class="control-label col-sm-3" for="page_name">Consultancy Period
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="conPeriod"
									name="conPeriod" placeholder="" value="${page.pageName}"
									>
							<!-- </div> -->
	                    </div>
					
<label class="control-label col-sm-3" for="page_name">Project Completed
							</label>
                                                                <div class="col-sm-1"></div>
															<div class="col-sm-2">
																Yes <input type="radio" name="consultancy"
																	id="consultancy" checked value="Yes"> No<input
																	type="radio" name="consultancy" id="consultancy"
																	value="No">
															</div> 
						<button type="submit" class="btn btn-primary" onclick="getData()">Submit</button>
				<!-- 	</form> -->
				</div>
			</div>
		</div>
	</div>
	
	


	
	<script type="text/javascript">
	function getData() {
	//alert("hii");
		var i=0;
		var nature=document.getElementById("nature").value
		var sponser=document.getElementById("sponser").value
		//alert(qualName);
		var amount=document.getElementById("amount").value
		var conPeriod=document.getElementById("conPeriod").value
		var consultancy=document.getElementById("consultancy").value
		
		var dataTable = $('#example1')
		.DataTable();
		
		dataTable.row
		.add(
				[
					i+1,
					nature,
					sponser,
					amount,
					conPeriod,
					consultancy
					
						 ])
		.draw();
		
		
		
	}

	</script>
	
	

</body>
</html>



<!-- 
														<h5 class="title pull-left">
																<strong>Consultancy</strong>
															</h5>
															<div class="col-sm-1"></div>
															<div class="col-sm-2">
																Yes <input type="radio" name="consultancy"
																	id="consultancy" checked value="0"> No<input
																	type="radio" name="consultancy" id="consultancy"
																	value="1">
															</div> -->