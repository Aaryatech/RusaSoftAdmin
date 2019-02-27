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
									<strong>Migration to Higher Education</strong>
								</h5>
								<div class="col-xs-12"></div>
								<div class="col-xs-12">

									<table class="table table-striped dt-responsive display" id="example1">
										<thead>
											<tr>
												<th width="5%">Sr.No.</th>
												<th width="15%">Program Type</th>
												<th width="15%">Proceeding To</th>
												<th width="15%">No. of Students</th>
											</tr>


										</thead>


<!-- 
										<tbody>
											<tr>
												<td>1</td>
												<td>UG</td>
												<td>PG</td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
											</tr>
											<tr>
												<td>2</td>
												<td>PG</td>
												<td>Ph.D.</td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
											</tr>
											<tr>
												<td>3</td>
												<td>M.Phill</td>
												<td>Ph.D.</td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
											</tr>

											<tr>
												<td>4</td>

												<td>Ph.D.</td>
												<td>Post Doct.</td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
											</tr>

											<tr>
												<td>5</td>
												<td>Post Doct.</td>
												<td>pls.Specify</td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
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
					<h4 class="modal-title">Higher Education Details</h4>
				</div>
				<div class="modal-body">
					<%-- <form role="form"
						action="${pageContext.request.contextPath}/showModuleForm"
						method="get"> --%>
						<input type="hidden" class="form-control" id="pageId"
							name="pageId">
							
							
							<input type="hidden" class="form-control" id="index"
							name="index" value="0">
							
								<div class="form-group">
						<label class="control-label col-sm-6" for="academicYear">Academic
							Year</label> <select id="academicYear" name="academicYear"
							class="form-control" required>
							<option value="2018-2019">2018-2019</option>
							<option value="2017-2018">2017-2018</option>
							<option value="2016-2017">2016-2017</option>
								<option value="2015-2016">2015-2016</option>

						</select>
					</div>
					
						<div class="form-group">
							<label for="modalname1" class="form-label">Program Type</label> <select
								id="qualType" name="salutation" class="form-control" required>
								<option value="UG">UG</option>
								<option value="PG">PG</option>
								<option value="M.Phill">M.Phill</option>
								<option value="Ph.D.">Ph.D.</option>
								<option value="Post Doct.">Post Doct.</option>
								



							</select>
						</div>
						
						
							<div class="form-group">
							<label for="modalname1" class="form-label">Proceeding To </label> <select
								id="qualName" name="salutation" class="form-control" required>
								
								<option value="PG">PG</option>
									<option value="Ph.D.">Ph.D.</option>
										<option value="Ph.D.">Ph.D.</option>
								
							
								<option value="Post Doct.">Post Doct.</option>
									<option value="-">Pls. Specify</option>
								



							</select>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-3" for="page_name">No. of Student
							</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" id="className"
									name="hodName" placeholder="No." value="${page.pageName}"
									required>
									<input type="hidden" id="index" name="index" value="0">
							</div>




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
			var academicYear = document.getElementById("academicYear").value;

		var qualType=document.getElementById("qualType").value
		var qualName=document.getElementById("qualName").value
		var className=document.getElementById("className").value

		var dataTable = $('#example1')
		.DataTable();
		
		dataTable.row
		.add(
				[
					i+1,
					academicYear,
					qualType,
					qualName,
					className
					
					
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
