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
							<%-- <h1 class="title">${title}</h1> --%>
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
																class="btn btn-info">Add</button></a> <a
									class="box_toggle fa fa-chevron-down"></a>
								<!--  <a class="box_setting fa fa-cog" data-toggle="modal" href="#section-settings"></a>
                    <a class="box_close fa fa-times"></a> -->

							</div>

						</header>
						<div class="content-body">
							<div class="row">
								<form class="form-horizontal"
									action="${pageContext.request.contextPath}/insertCmsForm"
									method="post" enctype="multipart/form-data"
									name="form_sample_2" id="form_sample_2"
									onsubmit="return confirm('Do you really want to submit the form?');">
									<div class="col-xs-12">



										<h5 class="title pull-left">
											<strong>Facilities</strong>
										</h5>
										<div class="col-xs-12"></div>

										<div class="form-group">
											<label class="control-label col-sm-3" for="page_name">Location
												of the Campus  </label>
											<div class="col-sm-3">
												<input type="text" class="form-control" id="hodName"  placeholder="Location"
													name="hodName" placeholder=" " value="${page.pageName}"
													required>
											</div>
											<label class="control-label col-sm-3" for="page_name">Area of Campus in sq.mts.

											</label>
											<div class="col-sm-3">
											<input type="text" class="form-control" id="hodName" placeholder="Area"
													name="hodName" placeholder=" " value="${page.pageName}" 
													required>
											</div>
										</div>



										<table class="table table-striped dt-responsive display" id="example-1">
											<thead>
												<tr>
													<th width="5%">Sr No</th>
													<th width="20%">Facility</th>
														<th width="20%">Yes/No</th>
													<th width="10%">No.</th>
													<th width="20%">Associated with</th>
												</tr>
											</thead>


<!-- 
											<tbody>

												<tr>
													<td>1</td>
													<td>Classrooms</td>

													<td><input type="text" class="form-control"
														id="curExp" name="curExp" value="" required></td>
													<td><input type="text" class="form-control"
														id="curExp" name="curExp" value="" required></td>
												</tr>
												<tr>
													<td>2</td>
													<td>Seminar Hall</td>

													<td><input type="text" class="form-control"
														id="curExp" name="curExp" value="" required></td>
													<td><input type="text" class="form-control"
														id="curExp" name="curExp" value="" required></td>
												</tr>

												<tr>
													<td>3</td>
													<td>ICT Rooms</td>

													<td><input type="text" class="form-control"
														id="curExp" name="curExp" value="" required></td>
													<td><input type="text" class="form-control"
														id="curExp" name="curExp" value="" required></td>
												</tr>

												<tr>
													<td>4</td>
													<td>Smartboard</td>

													<td><input type="text" class="form-control"
														id="curExp" name="curExp" value="" required></td>
													<td><input type="text" class="form-control"
														id="curExp" name="curExp" value="" required></td>
												</tr>

												<tr>
													<td>5</td>
													<td>Computers</td>

													<td><input type="text" class="form-control"
														id="curExp" name="curExp" value="" required></td>
													<td><input type="text" class="form-control"
														id="curExp" name="curExp" value="" required></td>
												</tr>

												<tr>
													<td>6</td>
													<td>Mooc Courses</td>

													<td><input type="text" class="form-control"
														id="curExp" name="curExp" value="" required></td>
													<td><input type="text" class="form-control"
														id="curExp" name="curExp" value="" required></td>
												</tr>

												<tr>
													<td>7</td>
													<td>Virtual Lab</td>

													<td><input type="text" class="form-control"
														id="curExp" name="curExp" value="" required></td>
													<td><input type="text" class="form-control"
														id="curExp" name="curExp" value="" required></td>
												</tr>
												<tr>
													<td>8</td>
													<td>Nptel</td>

													<td><input type="text" class="form-control"
														id="curExp" name="curExp" value="" required></td>
													<td><input type="text" class="form-control"
														id="curExp" name="curExp" value="" required></td>
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
									

						
					</form>
				</div>
			</div>
			</section>
		</div>
		</section>
		</section>
	</div>
	
	
	<div aria-hidden="true" role="dialog" tabindex="-1" id="myModal"
		class="modal fade" style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button aria-hidden="true" data-dismiss="modal" class="close"
						type="button">×</button>
					<h4 class="modal-title">Facility Details</h4>
				</div>
				<div class="modal-body">
					<%-- <form role="form"
						action="${pageContext.request.contextPath}/showModuleForm"
						method="get"> --%>
						<input type="hidden" class="form-control" id="pageId"
							name="pageId" >
							
									<div class="form-group">
							<label class="control-label col-sm-3" for="page_name">Facility</label> <select
								id="qualType" name="qualType" class="form-control" onchange="showForm()" required>
								<option value="Classroom">Classroom</option>
								<option value="Seminar Halls">Seminar Halls</option>
								<option value="ICT Rooms">ICT Rooms</option>
								<option value="Smartboard">Smartboard</option>
							<option value="Computers">Computers</option>
								<option value="Mooc Courses">Mooc Courses</option>
									<option value="Virtual Lab">Virtual Lab</option>
									<option value="Nptel">Nptel</option>
							
							</select>
						</div>
						
							<div class="form-group">
							<label class="control-label col-sm-6" for="page_order">Is
							 :<span class="text-danger">*</span>
						</label>
						<div class="col-sm-2">
							 <input type="radio" name="stu" id="stu" checked value="yes">Yes
							<input type="radio" name="stu" id="stu1" value="no">No
						</div>
					</div>
						
						
						<div class="form-group">
						
							<label class="control-label col-sm-6" for="page_name">No. of Facilities
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="qualName" placeholder="No. of Facilities"
									name="qualName" placeholder="" value="${page.pageName}"
									>
							<!-- </div> -->
	</div>

	
	<div class="form-group">
						
							<label class="control-label col-sm-6" for="page_name">Total Area(in sqm)
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="className" placeholder="Total Area(in sqm)"
									name="qualName" placeholder="" value="${page.pageName}" 
									>
							<!-- </div> -->
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

		var qualType=document.getElementById("qualType").value
		var qualName=document.getElementById("qualName").value
		var className=document.getElementById("className").value
		
		var rate_value;
		if (document.getElementById('stu').checked) {
		
			  rate_value = document.getElementById('stu').value;
				//alert("::"+rate_value);
			}
		else if(document.getElementById('stu1').checked){
			 rate_value = document.getElementById('stu1').value;
			 //alert("::"+rate_value);
		}
	//	var stu = document.getElementById("stu").value
		var dataTable = $('#example-1')
		.DataTable();
		
		dataTable.row
		.add(
				[
					i+1,
					qualType,
					rate_value,
					qualName,
					className
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
