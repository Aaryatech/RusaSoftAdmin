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
							<a href="#myModal3"	data-toggle="modal"><button type="submit"
																class="btn btn-primary">Add</button></a>
							 <a
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
											<strong>Amenities</strong>
										</h5>
										<div class="col-xs-12"></div>


										<table class="table table-striped dt-responsive display" id="example1">
											<thead>
												<tr>
													<th width="5%">Sr No</th>
													<th width="20%">Title</th>
													<th width="10%">sqm</th>
													<th width="20%">Location </th>
												</tr>
											</thead>



										<!-- 	<tbody>

												<tr>
													<td>1</td>
													<td>Toilets Ladies/Gents</td>

													<td><input type="text" class="form-control"
														id="curExp" name="curExp" value="Sqm" required readonly></td>
													<td><input type="text" class="form-control"
														id="curExp" name="curExp" value="Location" required
														readonly></td>
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

	


	<div aria-hidden="true" role="dialog" tabindex="-1" id="myModal3"
		class="modal fade" style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button aria-hidden="true" data-dismiss="modal" class="close"
						type="button">×</button>
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body">
					<%-- <form role="form"
						action="${pageContext.request.contextPath}/showModuleForm"
						method="get"> --%>
						<input type="hidden" class="form-control" id="pageId"
							name="pageId">
						<div class="form-group">
							<label for="modalname1" class="form-label">Title</label> <select
								id="qualType" name="salutation" class="form-control" required>
								<option value="Toilets Ladies/Gents">Toilets Ladies/Gents</option>
								<option value="Boys Common Room">Boys Common Room</option>
								<option value="Girls Common Room">Girls Common Room</option>
								<option value="Cafeteria">Cafeteria</option>
								<option value="Reprographic Facilities">Reprographic Facilities</option>
								<option value="First Aid cus Sick Room">First Aid cus Sick Room</option>
								<option value="Principals Quarter">Principals Quarter</option>
								<option value="Guest House">Guest House</option>
								<option value="Sports Club/Gym">Sports Club/Gym</option>
								<option value="Auditorium/Amphitheatre">Auditorium/Amphitheatre</option>
								<option value="Boys Hostel">Boys Hostel</option>
								<option value="Girls Hostel">Girls Hostel</option>



							</select>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-3" for="page_name">Area(in sqm)
							</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" id="qualName"
									name="hodName" placeholder="No." value="${page.pageName}"
									required>
							</div>

							<label class="control-label col-sm-2" for="page_name">Location
							</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" id="className"
									name="hodName" placeholder="Location" value="${page.pageName}"
									required>
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
		var i=0;

		var qualType=document.getElementById("qualType").value
		var qualName=document.getElementById("qualName").value
		var className=document.getElementById("className").value

		var dataTable = $('#example1')
		.DataTable();
		
		dataTable.row
		.add(
				[
					i+1,
					qualType,
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
