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
								<a href="${pageContext.request.contextPath}/sectionTreeList"><button
										type="button" class="btn btn-success">Add</button></a> <a
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
												of the Campus and area in </label>
											<div class="col-sm-3">
												<input type="text" class="form-control" id="hodName"
													name="hodName" placeholder=" " value="${page.pageName}"
													required>
											</div>
											<label class="control-label col-sm-1" for="page_name">sq.mts.

											</label>
										</div>



										<table class="table table-striped dt-responsive display">
											<thead>
												<tr>
													<th width="5%">Sr No</th>
													<th width="20%">Facility</th>
													<th width="10%">No.</th>
													<th width="20%">Associated with</th>
												</tr>
											</thead>



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



											</tbody>
										</table>
									</div>
									<div class="col-xs-12">


										<div class="form-group">
											<label class="control-label col-sm-2" for="page_name">Physical
												Facilities :<span class="text-danger">*</span>
											</label>
											<div class="col-sm-10">
												<input type="text" class="form-control" id="hodName"
													name="hodName" placeholder="Physical Facilities"
													value="${page.pageName}" required>
											</div>
										</div>


										<div class="form-group">
											<label class="control-label col-sm-2" for="page_name">Provision
												for lift :<span class="text-danger">*</span>
											</label>
											<div class="col-sm-2">
												Yes <input type="radio" name="lift" id="lift" checked
													value="0"> No<input type="radio" name="lift"
													id="lift" value="1">
											</div>
											<label class="control-label col-sm-2" for="page_name">Ramp/Rails
												:<span class="text-danger">*</span>
											</label>
											<div class="col-sm-2">
												Yes <input type="radio" name="ramp" id="ramp" checked
													value="0"> No<input type="radio" name="ramp"
													id="ramp" value="1">
											</div>
											<label class="control-label col-sm-2" for="page_name">Rest
												rooms :<span class="text-danger">*</span>
											</label>
											<div class="col-sm-2">
												Yes <input type="radio" name="restRooms" id="restRooms"
													checked value="0"> No<input type="radio"
													name="restRooms" id="restRooms" value="1">
											</div>
										</div>

										<div class="form-group">
											<label class="control-label col-sm-2" for="page_name">Braille
												Software Facilities :<span class="text-danger">*</span>
											</label>
											<div class="col-sm-2">
												Yes <input type="radio" name="brallie" id="brallie" checked
													value="0"> No<input type="radio" name="brallie"
													id="brallie" value="1">
											</div>
											<label class="control-label col-sm-3" for="page_name">Scribes
												for Examination :<span class="text-danger">*</span>
											</label>
											<div class="col-sm-2">
												Yes <input type="radio" name="scribes" id="scribes" checked
													value="0"> No<input type="radio" name="scribes"
													id="scribes" value="1">
											</div>



										</div>

										<div class="form-group">
											<label class="control-label col-sm-5" for="page_name">Special
												Skill Development for differently abled Students :<span
												class="text-danger">*</span>
											</label>
											<div class="col-sm-2">
												Yes <input type="radio" name="Skill" id="Skill" checked
													value="0"> No<input type="radio" name="Skill"
													id="Skill" value="1">
											</div>

										</div>


										<div class="form-group">
											<label class="control-label col-sm-2" for="page_name">Any
												Other Similar Facility :<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control" id="hodName"
													name="hodName" placeholder="Any Other Similar Facility"
													value="${page.pageName}" required>
											</div>
											<div class="col-sm-2">
												<button type="submit" class="btn btn-primary">Add</button>
											</div>

										</div>

										<h5 class="title pull-left">
											<strong>Institutional</strong>
										</h5>
										<div class="col-xs-12"></div>


										<table class="table table-striped dt-responsive display">
											<thead>
												<tr>
													<th width="5%">Sr No</th>
													<th width="20%">Title</th>
													<th width="10%">sqm</th>
													<th width="20%">Location <a href="#myModal"
														data-toggle="modal"><button type="submit"
																class="btn btn-primary">Add</button></a></th>
												</tr>
											</thead>



											<tbody>

												<tr>
													<td>1</td>
													<td>Classrooms</td>

													<td><input type="text" class="form-control"
														id="curExp" name="curExp" value="sqm" readonly required></td>
													<td><input type="text" class="form-control"
														id="curExp" name="curExp" value="Location" readonly
														required></td>
												</tr>

											</tbody>
										</table>



										<h5 class="title pull-left">
											<strong>Administrative</strong>
										</h5>
										<div class="col-xs-12"></div>


										<table class="table table-striped dt-responsive display">
											<thead>
												<tr>
													<th width="5%">Sr No</th>
													<th width="20%">Title</th>
													<th width="10%">sqm</th>
													<th width="20%">Location <a href="#myModal2"
														data-toggle="modal"><button type="submit"
																class="btn btn-primary">Add</button></a></th>
												</tr>
											</thead>



											<tbody>

												<tr>
													<td>1</td>
													<td>Principal Office</td>

													<td><input type="text" class="form-control"
														id="curExp" name="curExp" value="sqm" required readonly></td>
													<td><input type="text" class="form-control"
														id="curExp" name="curExp" value="Location" readonly
														required></td>
												</tr>

											</tbody>
										</table>

										<h5 class="title pull-left">
											<strong>Amenities</strong>
										</h5>
										<div class="col-xs-12"></div>


										<table class="table table-striped dt-responsive display">
											<thead>
												<tr>
													<th width="5%">Sr No</th>
													<th width="20%">Title</th>
													<th width="10%">sqm</th>
													<th width="20%">Location <a href="#myModal3"
														data-toggle="modal"><button type="submit"
																class="btn btn-primary">Add</button></a></th>
												</tr>
											</thead>



											<tbody>

												<tr>
													<td>1</td>
													<td>Toilets Ladies/Gents</td>

													<td><input type="text" class="form-control"
														id="curExp" name="curExp" value="Sqm" required readonly></td>
													<td><input type="text" class="form-control"
														id="curExp" name="curExp" value="Location" required
														readonly></td>
												</tr>

											</tbody>
										</table>

										<h5 class="title pull-left">
											<strong>IT Infrastructure Developed Institute</strong>
										</h5>
										<div class="col-xs-12"></div>

										<div class="form-group">



											<label class="control-label col-sm-6" for="page_name">No.
												of Computers for Students in working Condition :<span
												class="text-danger">*</span>
											</label>
											<div class="col-sm-2">
												<input type="text" class="form-control" id="hodName"
													name="hodName" placeholder="" value="${page.pageName}"
													required>
											</div>

										</div>

										<table class="table table-striped dt-responsive display">
											<thead>
												<tr>
													<th width="5%">Sr No</th>
													<th width="10%">No. of Computers</th>

													<th width="10%">Date of Purchase</th>
													<th width="10%">Amount</th>
													<th width="10%">Total Students utilizing Computer</th>
												</tr>
											</thead>



											<tbody>

												<tr>
													<td>1</td>
													<td><input type="text" class="form-control"
														id="curExp" name="curExp" value="" required></td>

													<td><input type="date" class="form-control"
														id="curExp" name="curExp" value="" required></td>
													<td><input type="text" class="form-control"
														id="curExp" name="curExp" value="" required></td>
													<td><input type="text" class="form-control"
														id="curExp" name="curExp" value="" required></td>
												</tr>

											</tbody>
										</table>

										<div class="form-group">
											<label class="control-label col-sm-4" for="page_name">Total
												No. of Students :<span class="text-danger">*</span>
											</label>
											<div class="col-sm-2">
												<input type="text" class="form-control" id="hodName"
													name="hodName" placeholder="" value="${page.pageName}"
													required>
											</div>
										</div>








										<h5 class="title pull-left">
											<strong>Internet Connection Bandwidth</strong>
										</h5>
										<div class="col-xs-12"></div>

										<div class="form-group">



											<label class="control-label col-sm-6" for="page_name">No.
												of Computers with access to Internet :<span
												class="text-danger">*</span>
											</label>
											<div class="col-sm-2">
												<input type="text" class="form-control" id="hodName"
													name="hodName" placeholder="" value="${page.pageName}"
													required>
											</div>

										</div>

										<%-- <div class="form-group">



											<label class="control-label col-sm-6" for="page_name">Bandwidth
												of leased line connection :<span class="text-danger">*</span>
											</label>
											<div class="col-sm-2">
												<input type="text" class="form-control" id="hodName"
													name="hodName" placeholder="" value="${page.pageName}"
													required>
											</div>
											<label class="control-label col-sm-4" for="page_name">LAN
												Configuration And Speed :<span class="text-danger">*</span>
											</label>
											<div class="col-sm-2">
												<input type="text" class="form-control" id="hodName"
													name="hodName" placeholder="" value="${page.pageName}"
													required>
											</div>

										</div> --%>

										<table class="table table-striped dt-responsive display">
											<thead>
												<tr>
													<th width="5%">Sr No</th>
													<th width="10%">ISP</th>

													<th width="10%">Date</th>
													<th width="10%">Bandwidth</th>

												</tr>
											</thead>



											<tbody>

												<tr>
													<td>1</td>
													<td><input type="text" class="form-control"
														id="curExp" name="curExp" value="" required></td>

													<td><input type="date" class="form-control"
														id="curExp" name="curExp" value="" required></td>
													<td><input type="text" class="form-control"
														id="curExp" name="curExp" value="" required></td>

												</tr>

											</tbody>
										</table>




										<div class="form-group">



											<label class="control-label col-sm-6" for="page_name">Bandwidth
												of leased line connection :<span class="text-danger">*</span>
											</label>
											<div class="col-sm-2">
												<input type="text" class="form-control" id="hodName"
													name="hodName" placeholder="" value="${page.pageName}"
													required>
											</div>


										</div>

										<table class="table table-striped dt-responsive display">
											<thead>
												<tr>
													<th width="5%">Sr No</th>
													<th width="10%">ISP</th>

													<th width="10%">Date</th>
													<th width="10%">Bandwidth</th>

												</tr>
											</thead>



											<tbody>

												<tr>
													<td>1</td>
													<td><input type="text" class="form-control"
														id="curExp" name="curExp" value="" required></td>

													<td><input type="date" class="form-control"
														id="curExp" name="curExp" value="" required></td>
													<td><input type="text" class="form-control"
														id="curExp" name="curExp" value="" required></td>

												</tr>

											</tbody>
										</table>

<h5 class="title pull-left">
											<strong>e-Content Development Facilities</strong>
										</h5>
										<div class="col-xs-12"></div>

										<div class="form-group">



											<label class="control-label col-sm-6" for="page_name">Name 
												of the e-content development facilities:<span
												class="text-danger">*</span>
											</label>
											<div class="col-sm-2">
												<input type="text" class="form-control" id="hodName"
													name="hodName" placeholder="" value="${page.pageName}"
													required>
											</div>
									</div>
											<table class="table table-striped dt-responsive display">
											<thead></thead>
											<tr>
													
													<td>Number of Computer with LAN </td>

														<td><input type="text" class="form-control"
														id="curExp" name="curExp" value="" required></td>
														
												</tr>
												
										</table>
										<div class="form-group">
											
											<label class="control-label col-sm-6" for="page_name">Link to videos of media centre
												and recording facility:<span
												class="text-danger">*</span>
											</label>
											<div class="col-sm-2">
												<input type="text" class="form-control" id="hodName"
													name="hodName" placeholder="" value="${page.pageName}"
													required>
											</div>
												</div>
											
											<table class="table table-striped dt-responsive display">
											<thead></thead>
											<tr>
													
													<td>Computer with Internet Facility</td>

														<td><input type="text" class="form-control"
														id="curExp" name="curExp" value="" required></td>
														
												</tr>
												
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

	<div aria-hidden="true" role="dialog" tabindex="-1" id="myModal"
		class="modal fade" style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button aria-hidden="true" data-dismiss="modal" class="close"
						type="button">×</button>
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body">
					<form role="form"
						action="${pageContext.request.contextPath}/showModuleForm"
						method="get">
						<input type="hidden" class="form-control" id="pageId"
							name="pageId">
						<div class="form-group">
							<label for="modalname1" class="form-label">Title</label> <select
								id="salutation" name="salutation" class="form-control" required>
								<option value="0">Classrooms</option>
								<option value="1">Tutorial Rooms</option>
								<option value="2">Computer Laboratory</option>
								<option value="2">Computer Center</option>
								<option value="2">Seminar Hall</option>
								<option value="2">Library</option>
								<option value="2">Laboratories</option>
								<option value="2">Studio/Workshops</option>
								<option value="2">Language Lab</option>
								<option value="2">Any Other</option>



							</select>
						</div>

						<div class="form-group">
							<label class="control-label col-sm-1" for="page_name">No.
							</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" id="hodName"
									name="hodName" placeholder="No." value="${page.pageName}"
									required>
							</div>

							<label class="control-label col-sm-2" for="page_name">Location
							</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" id="hodName"
									name="hodName" placeholder="Location" value="${page.pageName}"
									required>
							</div>




						</div>



						<button type="submit" class="btn btn-primary">Submit</button>
					</form>
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
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body">
					<form role="form"
						action="${pageContext.request.contextPath}/showModuleForm"
						method="get">
						<input type="hidden" class="form-control" id="pageId"
							name="pageId">
						<div class="form-group">
							<label for="modalname1" class="form-label">Title</label> <select
								id="salutation" name="salutation" class="form-control" required>
								<option value="0">Principal Office</option>
								<option value="1">Board Room</option>
								<option value="2">Office All Inclusive</option>
								<option value="2">HOD Office/Cabin</option>
								<option value="2">Faculty Rooms</option>
								<option value="2">Central Rooms</option>
								<option value="2">Maintenance Rooms</option>
								<option value="2">Security</option>
								<option value="2">Housekeeping</option>
								<option value="2">Pantry for Staff</option>
								<option value="2">Examination Control Office</option>
								<option value="2">Placement Office</option>



							</select>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-1" for="page_name">No.
							</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" id="hodName"
									name="hodName" placeholder="No." value="${page.pageName}"
									required>
							</div>

							<label class="control-label col-sm-2" for="page_name">Location
							</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" id="hodName"
									name="hodName" placeholder="Location" value="${page.pageName}"
									required>
							</div>




						</div>


						<button type="submit" class="btn btn-primary">Submit</button>
					</form>
				</div>
			</div>
		</div>
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
					<form role="form"
						action="${pageContext.request.contextPath}/showModuleForm"
						method="get">
						<input type="hidden" class="form-control" id="pageId"
							name="pageId">
						<div class="form-group">
							<label for="modalname1" class="form-label">Title</label> <select
								id="salutation" name="salutation" class="form-control" required>
								<option value="0">Toilets Ladies/Gents</option>
								<option value="1">Boys Common Room</option>
								<option value="2">Girls Common Room</option>
								<option value="2">Cafeteria</option>
								<option value="2">Reprographic Facilities</option>
								<option value="2">First Aid cus Sick Room</option>
								<option value="2">Principals Quarter</option>
								<option value="2">Guest House</option>
								<option value="2">Sports Club/Gym</option>
								<option value="2">Auditorium/Amphitheatre</option>
								<option value="2">Boys Hostel</option>
								<option value="2">Girls Hostel</option>



							</select>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-1" for="page_name">No.
							</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" id="hodName"
									name="hodName" placeholder="No." value="${page.pageName}"
									required>
							</div>

							<label class="control-label col-sm-2" for="page_name">Location
							</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" id="hodName"
									name="hodName" placeholder="Location" value="${page.pageName}"
									required>
							</div>




						</div>


						<button type="submit" class="btn btn-primary">Submit</button>
					</form>
				</div>
			</div>
		</div>
	</div>
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
