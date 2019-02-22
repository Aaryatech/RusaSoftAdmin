<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html class=" ">
<head>

<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<!-- CORE CSS TEMPLATE - END -->
<c:url var="clearSessionAttribute" value="/clearSessionAttribute" />
</head>
<!-- END HEAD -->

<!-- BEGIN BODY -->
<body class=" " onload="clearSessionAttribute()">
	<!-- START TOPBAR -->
	<jsp:include page="/WEB-INF/views/include/topbar.jsp"></jsp:include>
	<!-- END TOPBAR -->
	<!-- START CONTAINER -->
	<div class="page-container row-fluid container-fluid">

		<!-- SIDEBAR - START -->

		<jsp:include page="/WEB-INF/views/include/left.jsp"></jsp:include>
		<!--  SIDEBAR - END -->
		<!-- START CONTENT -->
		<!-- START CONTENT -->
		<section id="main-content" class=" ">
			<section class="wrapper main-wrapper row" style=''>

				<div class='col-xs-12'>
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



				<div class="col-lg-12">
					<section class="box ">
						<header class="panel_header">
							<h2 class="title pull-left">${title}</h2>
							<div class="actions panel_actions pull-right">
							<a href="#myModal"	data-toggle="modal"><button type="submit"
																class="btn btn-info">Add</button></a>
								<%-- <a href="${pageContext.request.contextPath}/sectionTreeList"><button
										type="button" class="btn btn-success">Add CMS Content</button></a> --%>
								<a class="box_toggle fa fa-chevron-down"></a>
								<!--  <a class="box_setting fa fa-cog" data-toggle="modal" href="#section-settings"></a>
                    <a class="box_close fa fa-times"></a> -->

							</div>

						</header>
						<div class="content-body">
							<div class="row">


								

								<h5 class="title pull-left">
									<strong>No. of Students Admitted Category wise</strong>
								</h5>

								
								<div class="col-xs-12">
									<table class="table table-striped dt-responsive display" id="example10">
										<thead>
											<tr>
												<th width="25%">Sr No</th>
												<th width="25%">Category</th>
												<th width="25%">Gender</th>
												<th width="25%" >No.
													of Students</th>
											</tr>
											
											
										</thead>
										
									</table>

								
								
	<div class="form-group">
														<div class="col-sm-offset-2 col-sm-10">
															<button type="submit" class="btn btn-primary">Submit</button>
															<button type="reset" class="btn btn-default">Reset</button>
														</div>
													</div>
													</div>




							</div>
						</div>
					</section>
				</div>

				<!-- MAIN CONTENT AREA ENDS -->
			</section>
		</section>
		</div>
		<!-- END CONTENT -->



<div aria-hidden="true" role="dialog" tabindex="-1" id="myModal"
		class="modal fade" style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button aria-hidden="true" data-dismiss="modal" class="close"
						type="button">×</button>
					<h4 class="modal-title">Subject Details</h4>
				</div>
				<div class="modal-body">
					<%-- <form role="form"
						action="${pageContext.request.contextPath}/showModuleForm"
						method="get"> --%>
						<input type="hidden" class="form-control" id="pageId"
							name="pageId" >
							
								
						
									<div class="form-group">
						<label class="control-label col-sm-2" for="page_name">Category</label> <select
								id="cat" name="sem" class="form-control" onchange="showForm()" required>
								<option value="SC">SC</option>
								<option value="ST">ST</option>
								<option value="OBC">OBC</option>
								<option value="VJ">VJ</option>
								<option value="NT">NT-I,NT-II,NT-III</option>
								<option value="General">General</option>
							
							


							</select>
						</div>
						
						
						
									<div class="form-group">
						<label class="control-label col-sm-2" for="page_name">Gender</label> <select
								id="gen" name="sem" class="form-control" onchange="showForm()" required>
								<option value="Male">Male</option>
								<option value="Female">Female</option>
								

							</select>
						</div>
						
						<div class="form-group">
						
							<label class="control-label col-sm-3" for="page_name">No. of Students
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="stud"
									name="subTaut" value="${page.pageName}"
									required>
							<!-- </div> -->
	</div>
						
		

						<button type="submit" class="btn btn-primary" onclick="getData()">Submit</button>
					<!-- </form> -->
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
	function getData() {
	//alert("hii");
		var i=0;
		var cat=document.getElementById("cat").value
		var gen=document.getElementById("gen").value
		var stud=document.getElementById("stud").value
		
		//alert(stud);
		
		var dataTable = $('#example10')
		.DataTable();
		
		dataTable.row
		.add(
				[
					i+1,
					cat,
					gen,
					stud,
					
						 ])
		.draw();
		
		
		
	}

	</script>
	
	

	<!-- END CONTAINER -->
	<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->

	<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
	<script>
		function clearSessionAttribute() {

			$.getJSON('${clearSessionAttribute}', {

				ajax : 'true',

			}, function(data) {

			});

		}
	</script>
</body>
</html>
