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
										type="button" class="btn btn-success">Add CMS Content</button></a>
								<a class="box_toggle fa fa-chevron-down"></a>
								<!--  <a class="box_setting fa fa-cog" data-toggle="modal" href="#section-settings"></a>
                    <a class="box_close fa fa-times"></a> -->

							</div>

						</header>
						<div class="content-body">
							<div class="row">


								<div class="col-xs-12">


									<table class="table table-striped dt-responsive display">
										<thead>
											<tr>
												<th width="5%">Sr No</th>
												<th width="10%">Program Type</th>
												<th width="10%">Duration Months</th>
												<th width="20%">Name of Program</th>
												<th width="30%" style="text-align: center;" colspan="2">No.
													of Students Admitted</th>
												<th width="10%">Date/Year of Introduction</th>
												<th width="15%">Approved by</th>
											</tr>

											<tr>
												<th width="5%"></th>
												<th width="10%"></th>
												<th width="10%"></th>
												<th width="20%"></th>
												<th width="15%">Sanctioned Intake</th>
												<th width="15%">Admitted Student</th>
												<th width="10%"></th>
												<th width="15%"></th>
											</tr>
										</thead>



										<tbody>

											<tr>
												<td>1</td>
												<td>Certificate</td>

												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
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
															<option value="0">BOS/AC</option>
															<option value="1">Industry</option>
															<option value="2">AICTE</option>
															<option value="3">NCTE</option>
															<option value="4">MCI</option>
															<option value="5">DCI</option>
															<option value="6">PCI</option>
															<option value="7">Any Other</option>


														</select>
													</div></td>

											</tr>
											<tr>
												<td>2</td>
												<td>UG</td>

												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
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
															<option value="0">BOS/AC</option>
															<option value="1">Industry</option>
															<option value="2">AICTE</option>
															<option value="3">NCTE</option>
															<option value="4">MCI</option>
															<option value="5">DCI</option>
															<option value="6">PCI</option>
															<option value="7">Any Other</option>


														</select>
													</div></td>

											</tr>

											<tr>
												<td>3</td>
												<td>PG</td>

												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
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
															<option value="0">BOS/AC</option>
															<option value="1">Industry</option>
															<option value="2">AICTE</option>
															<option value="3">NCTE</option>
															<option value="4">MCI</option>
															<option value="5">DCI</option>
															<option value="6">PCI</option>
															<option value="7">Any Other</option>


														</select>
													</div></td>

											</tr>

											<tr>
												<td>4</td>
												<td>Diploma</td>

												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
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
															<option value="0">BOS/AC</option>
															<option value="1">Industry</option>
															<option value="2">AICTE</option>
															<option value="3">NCTE</option>
															<option value="4">MCI</option>
															<option value="5">DCI</option>
															<option value="6">PCI</option>
															<option value="7">Any Other</option>


														</select>
													</div></td>

											</tr>

											<tr>
												<td>5</td>
												<td>Ph.D.</td>

												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
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
															<option value="0">BOS/AC</option>
															<option value="1">Industry</option>
															<option value="2">AICTE</option>
															<option value="3">NCTE</option>
															<option value="4">MCI</option>
															<option value="5">DCI</option>
															<option value="6">PCI</option>
															<option value="7">Any Other</option>


														</select>
													</div></td>

											</tr>



											<tr>
												<td>6</td>
												<td>Post Doc.</td>

												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
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
															<option value="0">BOS/AC</option>
															<option value="1">Industry</option>
															<option value="2">AICTE</option>
															<option value="3">NCTE</option>
															<option value="4">MCI</option>
															<option value="5">DCI</option>
															<option value="6">PCI</option>
															<option value="7">Any Other</option>


														</select>
													</div></td>

											</tr>

											<tr>
												<td>7</td>
												<td>Any Other</td>

												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
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
															<option value="0">BOS/AC</option>
															<option value="1">Industry</option>
															<option value="2">AICTE</option>
															<option value="3">NCTE</option>
															<option value="4">MCI</option>
															<option value="5">DCI</option>
															<option value="6">PCI</option>
															<option value="7">Any Other</option>


														</select>
													</div></td>

											</tr>
											<tr>
												<td></td>
												<td><button type="submit" class="btn btn-primary">Add</button></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td><button type="submit" class="btn btn-primary">Add</button></td>
											</tr>
										</tbody>
									</table>
								</div>

								<h5 class="title pull-left">
									<strong>No. of Students Admitted</strong>
								</h5>

								<div class="col-xs-12"></div>
								<div class="col-xs-12">
									<table class="table table-striped dt-responsive display">
										<thead>
											<tr>
												<th width="5%">Sr No</th>
												<th width="15%">Category</th>
												<th width="30%" style="text-align: center;" colspan="2">No.
													of Students</th>
												<th width="20%">Location</th>
												<th width="30%" style="text-align: center;" colspan="2">No.
													of Students</th>
											</tr>
											<tr>
												<th width="5%"></th>
												<th width="15%"></th>
												<th width="15%">Male</th>
												<th width="15%">Female</th>
												<th width="20%"></th>
												<th width="15%">Male</th>
												<th width="15%">Female</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>1</td>
												<td>SC</td>

												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>

												<td><div class="col-sm-12">
														<select id="salutation" name="salutation"
															class="form-control" required>
															<option value="0">State</option>
															<option value="1">Other State</option>
															<option value="2">NRI</option>
															<option value="3">PIO</option>
															<option value="4">Foreign</option>
															<option value="5">PWD</option>
														</select>
													</div></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>


											</tr>
											<tr>
												<td>2</td>
												<td>ST</td>

												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>

												<td><div class="col-sm-12">
														<select id="salutation" name="salutation"
															class="form-control" required>
															<option value="0">State</option>
															<option value="1">Other State</option>
															<option value="2">NRI</option>
															<option value="3">PIO</option>
															<option value="4">Foreign</option>
															<option value="5">PWD</option>
														</select>
													</div></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>


											</tr>

											<tr>
												<td>3</td>
												<td>OBC</td>

												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>

												<td><div class="col-sm-12">
														<select id="salutation" name="salutation"
															class="form-control" required>
															<option value="0">State</option>
															<option value="1">Other State</option>
															<option value="2">NRI</option>
															<option value="3">PIO</option>
															<option value="4">Foreign</option>
															<option value="5">PWD</option>
														</select>
													</div></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>


											</tr>

											<tr>
												<td>4</td>
												<td>VJ</td>

												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>

												<td><div class="col-sm-12">
														<select id="salutation" name="salutation"
															class="form-control" required>
															<option value="0">State</option>
															<option value="1">Other State</option>
															<option value="2">NRI</option>
															<option value="3">PIO</option>
															<option value="4">Foreign</option>
															<option value="5">PWD</option>
														</select>
													</div></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>


											</tr>

											<tr>
												<td>5</td>
												<td>NT-I,NT-II,NT-III</td>

												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>

												<td><div class="col-sm-12">
														<select id="salutation" name="salutation"
															class="form-control" required>
															<option value="0">State</option>
															<option value="1">Other State</option>
															<option value="2">NRI</option>
															<option value="3">PIO</option>
															<option value="4">Foreign</option>
															<option value="5">PWD</option>
														</select>
													</div></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>


											</tr>

											<tr>
												<td>1</td>
												<td>General</td>

												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>

												<td><div class="col-sm-12">
														<select id="salutation" name="salutation"
															class="form-control" required>
															<option value="0">State</option>
															<option value="1">Other State</option>
															<option value="2">NRI</option>
															<option value="3">PIO</option>
															<option value="4">Foreign</option>
															<option value="5">PWD</option>
														</select>
													</div></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>


											</tr>

										</tbody>
									</table>

								</div>
								<h5 class="title pull-left">
									<strong>Students Support Scheme</strong>
								</h5>
								<div class="col-xs-12"></div>
								<div class="col-xs-12">

									<table class="table table-striped dt-responsive display">
										<thead>
											<tr>
												<th width="5%">Sr.No.</th>
												<th width="15%">Name of Scheme</th>
												<th width="15%">Level</th>
												<th width="30%" style="text-align: center;" colspan="2">Type</th>
												<th width="10%">No. of Students Benefited</th>
												<th width="10%">Year of Implementation</th>
												<th width="15%">Name of Support Agency</th>
											</tr>

											<tr>
												<th width="5%"></th>
												<th width="15%"></th>
												<th width="15%"></th>
												<th width="15%">Government</th>
												<th width="15%">Non Govt.</th>
												<th width="10%"></th>
												<th width="10%"></th>
												<th width="15%"></th>
											</tr>
										</thead>



										<tbody>

											<tr>
												<td>1</td>
												<td>Capability Enhancement</td>

												<td><div class="col-sm-12">
														<select id="salutation" name="salutation"
															class="form-control" required>
															<option value="0">National</option>
															<option value="1">International</option>
															<option value="2">State</option>
															<option value="3">Regional</option>

														</select>
													</div></td>

												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>


												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>


											</tr>
											<tr>
												<td>2</td>
												<td>Career Counseling</td>

												<td><div class="col-sm-12">
														<select id="salutation" name="salutation"
															class="form-control" required>
															<option value="0">National</option>
															<option value="1">International</option>
															<option value="2">State</option>
															<option value="3">Regional</option>

														</select>
													</div></td>

												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>


												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>


											</tr>

											<tr>
												<td>3</td>
												<td>Competitive Exams(MPSC,UPSC,PSU,RRB,etc)</td>

												<td><div class="col-sm-12">
														<select id="salutation" name="salutation"
															class="form-control" required>
															<option value="0">National</option>
															<option value="1">International</option>
															<option value="2">State</option>
															<option value="3">Regional</option>

														</select>
													</div></td>

												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>


												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>


											</tr>
											<tr>
												<td>3</td>
												<td>Higher Education Entrance Exams(GATE,MAT,GPAT,CAT
													etc)</td>

												<td><div class="col-sm-12">
														<select id="salutation" name="salutation"
															class="form-control" required>
															<option value="0">National</option>
															<option value="1">International</option>
															<option value="2">State</option>
															<option value="3">Regional</option>

														</select>
													</div></td>

												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>


												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>


											</tr>

											<tr>
												<td>4</td>
												<td>Vocational Education Training</td>

												<td><div class="col-sm-12">
														<select id="salutation" name="salutation"
															class="form-control" required>
															<option value="0">National</option>
															<option value="1">International</option>
															<option value="2">State</option>
															<option value="3">Regional</option>

														</select>
													</div></td>

												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>


												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>


											</tr>

											<tr>
												<td>6</td>
												<td>Any other</td>

												<td><div class="col-sm-12">
														<select id="salutation" name="salutation"
															class="form-control" required>
															<option value="0">National</option>
															<option value="1">International</option>
															<option value="2">State</option>
															<option value="3">Regional</option>

														</select>
													</div></td>

												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>


												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>

											</tr>

											<tr>
												<td></td>
												<td><button type="submit" class="btn btn-primary">Add</button></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
											</tr>
										</tbody>
									</table>
								</div>


								<h5 class="title pull-left">
									<strong>Training Placement</strong>
								</h5>
								<div class="col-xs-12"></div>
								<div class="col-xs-12">

									<table class="table table-striped dt-responsive display">
										<thead>
											<tr>
												<th width="5%">Sr.No.</th>
												<th width="15%">Name of Program</th>
												<th width="15%">No. of Students Placed</th>
												<th width="10%">Name of
													Employer(Company/Industry/Organization)</th>
												<th width="10%">Address of Employer</th>
												<th width="10%">Contact Details</th>
												<th width="15%">Package Offered</th>
											</tr>


										</thead>



										<tbody>
											<tr>
												<td>1</td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>

												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>


												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>

											</tr>



										</tbody>
									</table>
								</div>

								<h5 class="title pull-left">
									<strong>Higher Education</strong>
								</h5>
								<div class="col-xs-12"></div>
								<div class="col-xs-12">

									<table class="table table-striped dt-responsive display">
										<thead>
											<tr>
												<th width="5%">Sr.No.</th>
												<th width="15%">Program Type</th>
												<th width="15%">Proceeding To</th>
												<th width="15%">No. of Students</th>
											</tr>


										</thead>



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



										</tbody>
									</table>
								</div>





								<h5 class="title pull-left">
									<strong>Students Activity</strong>
								</h5>
								<div class="col-xs-12"></div>
								<div class="col-xs-12">

									<table class="table table-striped dt-responsive display">
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



										<tbody>

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

										</tbody>
									</table>
								</div>







								<h5 class="title pull-left">
									<strong>Alumni Association/Contribution</strong>
								</h5>
								<div class="col-xs-12"></div>
								<div class="col-xs-12">

									<table class="table table-striped dt-responsive display">
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


										</tbody>
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
