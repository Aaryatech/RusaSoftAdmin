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
								<a href="#myModal1" data-toggle="modal"><button
										type="submit" class="btn btn-info">Add Vision </button></a>
									<a href="#myModal2" data-toggle="modal"><button
										type="submit" class="btn btn-info">Add Mission </button></a>	
										
										 <a
									href="${pageContext.request.contextPath}/publicationList"><button
										type="button" class="btn btn-info">Back</button></a> <a
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
													<i class="fa fa-home"></i> ${title}
											</a></li>

										</ul>

										<div class="tab-content">
											<div class="tab-pane fade in active" id="home">

												<div>


													<div class="col-xs-12">
 
														<div class="col-xs-12">

															<table id="example-1"
																class="table table-striped dt-responsive display">
																<thead>
																	<tr>
																		<th width="10%">Sr No</th>
																		<th>Vision                            </th>
																		
																	</tr>
																
																</thead>



																<tbody>


																</tbody>
															</table>

														</div>
														
														
														
														<div class="col-xs-12">

															<table id="example-1"
																class="table table-striped dt-responsive display">
																<thead>
																	<tr>
																		<th width="10%">Sr No</th>
																		<th>Mission                         </th>
																		
																	</tr>
																
																</thead>



																<tbody>


																</tbody>
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





<div aria-hidden="true" role="dialog" tabindex="-1" id="myModal1"
		class="modal fade" style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button aria-hidden="true" data-dismiss="modal" class="close"
						type="button">×</button>
					<h4 class="modal-title">Institute Vision</h4>
				</div>
				<div class="modal-body">
					<%-- <form role="form"
						action="${pageContext.request.contextPath}/showModuleForm"
						method="get"> --%>
					<input type="hidden" class="form-control" id="pageId" name="pageId">


					<div class="form-group">

						<label class="control-label col-sm-3" for="page_name">Vision
						</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="vision" name="vision"
								placeholder="vision" value="${page.pageName}" required>
						</div>
					</div>

					<button type="submit" class="btn btn-primary" onclick="getVision()">Submit</button>
					<!-- 	</form> -->
				</div>
			</div>
		</div>
	</div>



<div aria-hidden="true" role="dialog" tabindex="-1" id="myModal2"
		class="modal fade" style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button aria-hidden="true" data-dismiss="modal" class="close"
						type="button">×</button>
					<h4 class="modal-title">Institute Mission</h4>
				</div>
				<div class="modal-body">
					<%-- <form role="form"
						action="${pageContext.request.contextPath}/showModuleForm"
						method="get"> --%>
					<input type="hidden" class="form-control" id="pageId" name="pageId">


					<div class="form-group">

						<label class="control-label col-sm-3" for="page_name">Mission
						</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="mission" name="mission"
								placeholder="mission" value="${page.pageName}" required>
						</div>
					</div>

					<button type="submit" class="btn btn-primary" onclick="getMission()">Submit</button>
					<!-- 	</form> -->
				</div>
			</div>
		</div>
	</div>




	<script type="text/javascript">
		function getVision() {
			//alert("hii");
			var i = parseInt(document.getElementById("index").value);
			
			var vision = document.getElementById("vision").value;
			
			//alert("noStud"+noStud);
			var dataTable = $('#example-1').DataTable();

			dataTable.row.add(
					[ i + 1,vision ])
					.draw();
			document.getElementById("index").value = i + 1;
		}
	</script>




<script type="text/javascript">
		function getMision() {
			//alert("hii");
			var i = parseInt(document.getElementById("index").value);
			
			var mission = document.getElementById("mission").value;
			
			//alert("noStud"+noStud);
			var dataTable = $('#example-2').DataTable();

			dataTable.row.add(
					[ i + 1,mission ])
					.draw();
			document.getElementById("index").value = i + 1;
		}
	</script>


</body>
</html>