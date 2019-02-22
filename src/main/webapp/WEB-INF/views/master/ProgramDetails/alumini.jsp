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
									<strong>Alumni Association/Contribution</strong>
								</h5>
								<div class="col-xs-12"></div>
								<div class="col-xs-12">

									<table class="table table-striped dt-responsive display" id="example10">
										<thead>
											<tr>
												<th width="5%">Sr.No.</th>
												<th width="15%">Name of Alumni</th>
												<th width="10%">Passing Year</th>
												<th width="15%">Nature of Contribution</th>
												<th width="10%">Year of Contribution</th>
												<th width="10%">Benefit To</th>
											</tr>

										</thead>


<!-- 
										<tbody>

											<tr>
												<td>1</td>

												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>


												<td><div class="col-sm-12">
														<select id="salutation" name="salutation"
															class="form-control" required>
															<option value="0">Financial</option>
															<option value="1">Non Financial</option>
														</select>
													</div></td>

												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>

												<td><div class="col-sm-12">
														<select id="salutation" name="salutation"
															class="form-control" required>
															<option value="0">Students</option>
															<option value="1">Staff</option>
															<option value="2">Faculty</option>
															<option value="3">Department</option>
															<option value="4">Institute</option>
															<option value="5">Society</option>
															<option value="6">Any Other</option>

														</select>
													</div></td>
											</tr>


										</tbody> -->
									</table>
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
					<h4 class="modal-title">Alumini Details</h4>
				</div>
				<div class="modal-body">
					<%-- <form role="form"
						action="${pageContext.request.contextPath}/showModuleForm"
						method="get"> --%>
						<input type="hidden" class="form-control" id="pageId"
							name="pageId" >
							
								
							
						<div class="form-group">
						
							<label class="control-label col-sm-6" for="page_name">Name of Alumini
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="name"
									name="subTaut" value="${page.pageName}"
									required>
							<!-- </div> -->
	</div>		
						
					
								
		<div class="form-group">
						
							<label class="control-label col-sm-3" for="page_name">Passing Year
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="date" class="form-control" id="year"
									name="subTaut" value="${page.pageName}"
									required>
							<!-- </div> -->
	</div>
	
	<div class="form-group">
						<label class="control-label col-sm-6" for="page_name">Nature of Contribution</label> <select
								id="nature" name="sem" class="form-control" onchange="showForm()" required>
								<option value="Financial">Financial</option>
												<option value="Non Financial">Non Financial</option>
								
								
							</select>
						</div>
			
									
						
											
		<div class="form-group">
						
							<label class="control-label col-sm-3" for="page_name">Year of Contribution
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="date" class="form-control" id="year1"
									name="subTaut" value="${page.pageName}"
									required>
							<!-- </div> -->
	</div>
	
						
					
									<div class="form-group">
						<label class="control-label col-sm-6" for="page_name">Benefit To</label> <select
								id="level" name="sem" class="form-control" onchange="showForm()" required>
								<option value="Students">Students</option>
															<option value="Staff">Staff</option>
															<option value="Faculty">Faculty</option>
															<option value="Department">Department</option>
															<option value="Institute">Institute</option>
															<option value="Society">Society</option>
															<option value="-">Any Other</option>
								
								
							</select>
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
		var name=document.getElementById("name").value
	
		var year=document.getElementById("year").value
		var nature=document.getElementById("nature").value
		var year1=document.getElementById("year1").value
		
		
		var package1=document.getElementById("level").value
		
	
		
		
		//alert(stud);
		
		var dataTable = $('#example10')
		.DataTable();
		
		dataTable.row
		.add(
				[
					i+1,
					name,
					year,
					nature,
					year1,
					package1
					
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
