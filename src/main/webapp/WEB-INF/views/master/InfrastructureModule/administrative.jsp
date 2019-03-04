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
<body class=" " onload="hideText()">
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
								<a href="#myModal2"
														data-toggle="modal"><button type="submit"
																class="btn btn-primary">Add</button></a><a
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
											<strong>Administrative</strong>
										</h5>
										<div class="col-xs-12"></div>


										<table class="table table-striped dt-responsive display" id="example-1">
											<thead>
												<tr>
													<th width="5%">Sr No</th>
														<th width="20%">Academic Year</th>
													<th width="20%">Title</th>
													<th width="10%">Area(sqm)</th>
													<th width="20%">Location </th>
												</tr>
											</thead>



											<!-- <tbody>

												<tr>
													<td>1</td>
													<td>Principal Office</td>

													<td><input type="text" class="form-control"
														id="curExp" name="curExp" value="sqm" required readonly></td>
													<td><input type="text" class="form-control"
														id="curExp" name="curExp" value="Location" readonly
														required></td>
												</tr>

											</tbody> -->
										</table>




										<div class="form-group">
											<div class="col-sm-offset-2 col-sm-10">
												<button type="submit" class="btn btn-primary">Submit</button>
												<button type="reset" class="btn btn-default">Reset</button>
											</div>
										</div>
									</div>
								</form>
							</div>
						</div>
					</section>
				</div>






				<!-- MAIN CONTENT AREA ENDS -->
			</section>
		</section>
		<!-- END CONTENT -->





	</div>


	<div aria-hidden="true" role="dialog" tabindex="-1" id="myModal2"
		class="modal fade" style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button aria-hidden="true" data-dismiss="modal" class="close"
						type="button">×</button>
					<h4 class="modal-title">Administrative Detail</h4>
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
							<label for="modalname1" class="form-label">Title</label> <select
								id="qualType" name="salutation" class="form-control"  onchange="showForm()" required>
								<option value="Principal Office">Principal Office</option>
								<option value="Board Room">Board Room</option>
								<option value="Office All Inclusive">Office All Inclusive</option>
								<option value="HOD Office/Cabin">HOD Office/Cabin</option>
								<option value="Faculty Rooms">Faculty Rooms</option>
								<option value="Central Rooms">Central Rooms</option>
								<option value="Maintenance Rooms">Maintenance Rooms</option>
								<option value="Security">Security</option>
								<option value="Housekeeping">Housekeeping</option>
								<option value="Pantry for Staff">Pantry for Staff</option>
								<option value="Examination Control Office">Examination Control Office</option>
								<option value="Placement Office">Placement Office</option>
										<option value="7">Any Other</option>


							</select>
						</div>
						
						<div class="form-group" id="abc">
						
							<label class="control-label col-sm-6" for="page_name">Other Facility
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="otherCourse" placeholder="Other Facility" required
									name="otherCourse" placeholder="Facility" value="${page.pageName}"
									>
							<!-- </div> -->
	</div>
	
						<div class="form-group">
							<label class="control-label col-sm-1" for="page_name">No.
							</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" id="qualName" placeholder="No."
									name="hodName" placeholder="No." value="${page.pageName}"
									required>
							</div>

							<label class="control-label col-sm-2" for="page_name">Location
							</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" id="className" placeholder="Location"
									name="hodName" placeholder="Location" value="${page.pageName}"
									required>
							</div>




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
			var i = parseInt(document.getElementById("index").value);
		var qualType=document.getElementById("qualType").value
		var qualName=document.getElementById("qualName").value
		var className=document.getElementById("className").value
		var otherCourse=document.getElementById("otherCourse").value
		var academicYear = document.getElementById("academicYear").value;
		var temp;
		if (qualType == 7) {

			temp=otherCourse;
			//alert(temp);
		} 
		else{
			temp=qualType;
		}
		var dataTable = $('#example-1')
		.DataTable();
		
		dataTable.row
		.add(
				[
					i+1,
					academicYear,
					temp,
					qualName,
					className
					
					
						 ])
		.draw();
		
		document.getElementById("index").value = i + 1;
		
	}
	

	function showForm() {
		//document.getElementById("abc").style = "display:none"
			var qualType=document.getElementById("qualType").value
			//alert("qualType::"+qualType);
			
			if (qualType == 7) {

				document.getElementById("abc").style = "visible"
				
					
			} 
			else{
				document.getElementById("abc").style = "display:none"
			}
		
		}
	function hideText() {
		//alert("hii");
		document.getElementById("abc").style = "display:none"
			
		
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
