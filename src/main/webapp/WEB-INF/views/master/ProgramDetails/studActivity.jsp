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
								<a class="box_toggle fa fa-chevron-down"></a>
								<!--  <a class="box_setting fa fa-cog" data-toggle="modal" href="#section-settings"></a>
                    <a class="box_close fa fa-times"></a> -->

							</div>

						</header>
						<div class="content-body">
							<div class="row">


					



								<h5 class="title pull-left">
									<strong>Students Activity(Organized)</strong>
								</h5>
								
								<div class="col-xs-12"></div>
								<div class="col-xs-12">

									<table class="table table-striped dt-responsive display" id="example10">
										<thead>
											<tr>
												<th width="5%">Sr.No.</th>
												<th width="15%">Name of Activity</th>
												<th width="15%">Date</th>
												<th width="10%">Year</th>
												<th width="10%">Branch</th>
												<th width="10%">No. of Students</th>
												<th width="10%">Level of Activity</th>
											</tr>

										</thead>



										<!-- <tbody>

											<tr>
												<td>1</td>
												<td>Sports</td>
												<td><input type="date" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>


												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>

												<td><div class="col-sm-12">
														<select id="salutation" name="salutation"
															class="form-control" required>
															<option value="0">National</option>
															<option value="1">International</option>
															<option value="2">State</option>
															<option value="3">Regional</option>

														</select>
													</div></td>
											</tr>

											<tr>
												<td>2</td>
												<td>Cultural</td>
												<td><input type="date" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>


												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>

												<td><div class="col-sm-12">
														<select id="salutation" name="salutation"
															class="form-control" required>
															<option value="0">National</option>
															<option value="1">International</option>
															<option value="2">State</option>
															<option value="3">Regional</option>

														</select>
													</div></td>
											</tr>


											<tr>
												<td>3</td>
												<td>Other Competition</td>
												<td><input type="date" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>


												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>

												<td><div class="col-sm-12">
														<select id="salutation" name="salutation"
															class="form-control" required>
															<option value="0">National</option>
															<option value="1">International</option>
															<option value="2">State</option>
															<option value="3">Regional</option>

														</select>
													</div></td>
											</tr>

										</tbody> -->
									</table>
								</div>




<div class="form-group">
														<div class="col-sm-offset-2 col-sm-10">
															<button type="submit" class="btn btn-primary">Submit</button>
															<button type="reset" class="btn btn-default">Reset</button>
														</div>
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
					<h4 class="modal-title">Student Activity Details</h4>
				</div>
				<div class="modal-body">
					<%-- <form role="form"
						action="${pageContext.request.contextPath}/showModuleForm"
						method="get"> --%>
						<input type="hidden" class="form-control" id="pageId"
							name="pageId" >
							
								
									
						
					
									<div class="form-group">
						<label class="control-label col-sm-6" for="page_name">Name of Activity</label> <select
								id="cat" name="sem" class="form-control"  required>
								<option value="Sports">Sports</option>
								<option value="Cultural">Cultural</option>
								<option value="Other Competition">Other Competition</option>
								
							
							


							</select>
						</div>
	
		<div class="form-group">
						
							<label class="control-label col-sm-3" for="page_name">Date
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="date" class="form-control" id="date"
									name="subTaut" value="${page.pageName}"
									required>
							<!-- </div> -->
	</div>
	
	<div class="form-group">
						
							<label class="control-label col-sm-3" for="page_name">Year
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="year"
									name="subTaut" value="${page.pageName}"
									required>
							<!-- </div> -->
	</div>
							
									
						
						<div class="form-group">
						
							<label class="control-label col-sm-3" for="page_name">Branch
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="branch"
									name="subTaut" value="${page.pageName}"
									required>
							<!-- </div> -->
	</div>
						
						
						
						<div class="form-group">
						
							<label class="control-label col-sm-6" for="page_name">No. of Students
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="stud"
									name="subTaut" value="${page.pageName}"
									required>
									<input type="hidden" id="index" name="index" value="0">
							<!-- </div> -->
	</div>
						
									<div class="form-group">
						<label class="control-label col-sm-6" for="page_name">Level of Activity</label> <select
								id="level" name="sem" class="form-control" onchange="showForm()" required>
								<option value="International">International</option>
								<option value="National">National</option>
								<option value="Regional">Regional</option>
								<option value="State">State</option>
								
								
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
		var i = parseInt(document.getElementById("index").value);
		var empName=document.getElementById("cat").value
		var progName=document.getElementById("date").value
		var address=document.getElementById("year").value
		var contact=document.getElementById("branch").value
		
		var stud=document.getElementById("stud").value
		var package1=document.getElementById("level").value
		
	
		
		
		//alert(stud);
		
		var dataTable = $('#example10')
		.DataTable();
		
		dataTable.row
		.add(
				[
					i+1,
					empName,
					progName,
					address,
					contact,
					stud,
					package1
					
						 ])
		.draw();
		
		document.getElementById("index").value = i + 1;
		
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
