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
								<a href="${pageContext.request.contextPath}/hodList"><button
										type="button" class="btn btn-info">Back</button></a> <a
									class="box_toggle fa fa-chevron-down"></a>
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertCmsForm"
										method="post" enctype="multipart/form-data"
										name="form_sample_2" id="form_sample_2"
										onsubmit="return confirm('Do you really want to submit the form?');">

										<ul class="nav nav-tabs">
											<li class="active"><a href="#home" data-toggle="tab">
													<i class="fa fa-home"></i> Register
											</a></li>
											
										</ul>

										<div class="tab-content">
											<div class="tab-pane fade in active" id="home">

												<div>


													<div class="col-xs-12">

														<!-- <div class="form-group">
															<label class="control-label col-sm-2" for="status">Select
																Department : <span class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<select id="deptId" name="deptId" class="form-control"
																	required>
																	<option value="0">Information Technology</option>
																	<option value="1">Computer</option>


																</select>
															</div>
														</div> -->


														<h5 class="title pull-left">
															<strong>Personal Details</strong>
														</h5>

														<div class="col-xs-12"></div>





														<div class="form-group">
															<label class="control-label col-sm-2" for="status">Select
																Salutation : <span class="text-danger">*</span>
															</label>
															<div class="col-sm-1">
																<select id="salutation" name="salutation"
																	class="form-control" required>
																	<option value="0">Mr</option>
																	<option value="1">Miss</option>
																	<option value="2">Mrs</option>


																</select>
															</div>

															<label class="control-label col-sm-1" for="page_name">
																Name:<span class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<input type="text" class="form-control" id="firstName"
																	name="firstName" placeholder="First Name"
																	value="${page.pageName}" required>
															</div>
															<div class="col-sm-2">
																<input type="text" class="form-control" id="middleName"
																	name="middleName" placeholder="Middle Name"
																	value="${page.pageName}" required>
															</div>
															<div class="col-sm-3">
																<input type="text" class="form-control" id="lastName"
																	name="lastName" placeholder="Last Name"
																	value="${page.pageName}" required>
															</div>
														</div>

														<div class="form-group">
															<label class="control-label col-sm-2" for="page_name">Address
																:<span class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<input type="text" class="form-control" id="address"
																	name="address" placeholder="Correspondence/Permanent"
																	value="${page.pageName}" required>
															</div>
														</div>

														<div class="form-group">
															<label class="control-label col-sm-2" for="smallheading">Mobile
																No. : <span class="text-danger">*</span>
															</label>
															<div class="col-sm-2">
																<input type="text" class="form-control" id="contactNo"
																	name="contactNo" pattern="[7-9]{1}[0-9]{9}"
																	title="Phone number with 7-9 and remaing 9 digit with 0-9"
																	placeholder="Mobile No" value="" required>
															</div>

															<label class="control-label col-sm-1" for="smallheading">Phone No
																: <span class="text-danger">*</span>
															</label>

															<div class="col-sm-3">
																<input type="text" class="form-control" id="mobileNo"
																	name="mobileNo" 
																	title="Phone number with 7-9 and remaing 9 digit with 0-9"
																	placeholder="Landline No" value="" required>
															</div>

															<label class="control-label col-sm-1" for="smallheading">Resident:
																 </label>

															<div class="col-sm-3">
																<input type="text" class="form-control" id="residentNo"
																	name="residentNo" pattern="[7-9]{1}[0-9]{9}"
																	title="Phone number with 7-9 and remaing 9 digit with 0-9"
																	placeholder="Resident No" value="" required>
															</div>
														</div>

														<div class="form-group">
															<label class="control-label col-sm-2" for="smallheading">Email ID
																: <span class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<input type="email" class="form-control" id="email"
																	name="email" placeholder="abc@gmail.com" value="" required>
															</div>

															<label class="control-label col-sm-1" for="smallheading">PAN
																No : <span class="text-danger">*</span>
															</label>
															<div class="col-sm-2">
																<input type="text" class="form-control" id="panNo"
																	name="panNo" placeholder="PAN No" value="" required>
															</div>

															<label class="control-label col-sm-2" for="smallheading">Aadhar
																No: <span class="text-danger">*</span>
															</label>
															<div class="col-sm-2">
																<input type="Text" class="form-control" id="adharNo"
																	name="adharNo" placeholder="Aadhar No" value=""
																	required>
															</div>
														</div>

														<div class="form-group">
															<label class="control-label col-sm-2" for="status">Select
																Designation : <span class="text-danger">*</span>
															</label>
															<div class="col-sm-2">
																<select id="designation" name="designation"
																	class="form-control" required>
																	<option value="0">Assistant Professor</option>
																	<option value="1">Associate Professor</option>
																	<option value="2">Professor</option>
																	<option value="3">Registrar</option>
																	<option value="4">Any Other</option>
																</select>
															</div>
															<label class="control-label col-sm-2" for="smallheading">Date
																of Birth : <span class="text-danger">*</span>
															</label>
															<div class="col-sm-2">
																<input type="date" class="form-control" id="dob"
																	name="dob" placeholder="DOB" value="" required>
															</div>

															<label class="control-label col-sm-2" for="smallheading">Date
																of Joining : <span class="text-danger">*</span>
															</label>
															<div class="col-sm-2">
																<input type="date" class="form-control" id="doj"
																	name="doj" placeholder="Date of Joining" value=""
																	required>
															</div>
														</div>

														<div class="form-group">

															<label class="control-label col-sm-2" for="smallheading">Previous
																Experience : <span class="text-danger">*</span>
															</label>
															<div class="col-sm-2">
																<input type="text" class="form-control" id="prevExp"
																	name="prevExp" placeholder="Previous Experience"
																	value="" required>
															</div>

															<label class="control-label col-sm-2" for="smallheading">Current
																Experience : <span class="text-danger">*</span>
															</label>
															<div class="col-sm-2">
																<input type="text" class="form-control" id="curExp"
																	name="curExp" placeholder="Current Experience" value=""
																	required>
															</div>
														</div>
														<hr>

														<h5 class="title pull-left">
															<strong>Academic Details</strong>
														</h5>

														<div class="col-xs-12"></div>
														<label class="control-label col-sm-3" for="smallheading">Educational
															Qualifications : <span class="text-danger">*</span>
														</label>
														<div class="col-xs-12">




															<table id="example1"
																class="table table-striped dt-responsive display">
																<thead>
																	<tr>
																		<th width="10%">Sr No</th>
																		<th width="30%">Qualification</th>
																		<th width="30%">Year of Passing</th>
																		<th width="30%">Class</th>
																	</tr>
																</thead>



																<tbody>

																	<tr>
																		<td>1</td>
																		<td>Diploma</td>

																		<td><input type="date" class="form-control"
																			id="curExp" name="curExp" value="" required></td>
																		<td><input type="text" class="form-control"
																			id="curExp" name="curExp" value="0" required></td>
																	</tr>
																	<tr>
																		<td>2</td>
																		<td>Bachelors</td>

																		<td><input type="date" class="form-control"
																			id="curExp" name="curExp" value="" required></td>
																		<td><input type="text" class="form-control"
																			id="curExp" name="curExp" value="0" required></td>
																	</tr>

																	<tr>
																		<td>3</td>
																		<td>Masters</td>

																		<td><input type="date" class="form-control"
																			id="curExp" name="curExp" value="" required></td>
																		<td><input type="text" class="form-control"
																			id="curExp" name="curExp" value="0" required></td>
																	</tr>

																	<tr>
																		<td>4</td>
																		<td>Doctorates</td>

																		<td><input type="date" class="form-control"
																			id="curExp" name="curExp" value="" required></td>
																		<td><input type="text" class="form-control"
																			id="curExp" name="curExp" value="0" required></td>
																	</tr>

																	<tr>
																		<td>5</td>
																		<td>Post Doctorates</td>

																		<td><input type="date" class="form-control"
																			id="curExp" name="curExp" value="" required></td>
																		<td><input type="text" class="form-control"
																			id="curExp" name="curExp" value="0" required></td>
																	</tr>


																	<tr>
																		<td>6</td>
																		<td>M.phill/Ph.Guide</td>

																		<td><input type="date" class="form-control"
																			id="curExp" name="curExp" value="" required></td>
																		<td><input type="text" class="form-control"
																			id="curExp" name="curExp" value="0" required></td>
																	</tr>


																</tbody>
															</table>




														</div>

														<div class="form-group">
															<label class="control-label col-sm-2" for="page_order">M.phill/Ph.D.Guide
																:<span class="text-danger">*</span>
															</label>


															<div class="col-sm-2">
																Yes <input type="radio" name="mPhill" id="mPhill"
																	checked value="0"> No<input type="radio"
																	name="mPhill" id="mPhill" value="1">
															</div>

															<label class="control-label col-sm-2" for="smallheading">Date
																of Recognition : <span class="text-danger">*</span>
															</label>
															<div class="col-sm-2">
																<input type="date" class="form-control" id="dor"
																	name="dor" placeholder="Date of Recognition" value=""
																	required>
															</div>


															<label class="control-label col-sm-2" for="smallheading">Valid
																up to : <span class="text-danger">*</span>
															</label>
															<div class="col-sm-2">
																<input type="date" class="form-control" id="dor"
																	name="dor" placeholder="Valid up to" value="" required>
															</div>


														</div>


														<div class="form-group">
															<label class="control-label col-sm-2" for="page_name">No.
																of Students Guided :<span class="text-danger">*</span>
															</label> <label class="control-label col-sm-1" for="page_name">PG
																:<span class="text-danger">*</span>
															</label>
															<div class="col-sm-2">
																<input type="text" class="form-control"
																	id="qualification" name="qualification"
																	placeholder="PG" value="${page.pageName}" required>
															</div>

															<label class="control-label col-sm-1" for="page_name">M.Phill
																:<span class="text-danger">*</span>
															</label>
															<div class="col-sm-2">
																<input type="text" class="form-control"
																	id="qualification" name="qualification"
																	placeholder="M.Phill" value="${page.pageName}" required>
															</div>



															<label class="control-label col-sm-1" for="page_name">Ph.D.
																:<span class="text-danger">*</span>
															</label>
															<div class="col-sm-2">
																<input type="text" class="form-control" id="phdNo"
																	name="phdNo" placeholder="Ph.D"
																	value="${page.pageName}" required>
															</div>
														</div>
														<div class="form-group">
															<label class="control-label col-sm-4" for="page_order">Use
																of ICT(Information Communication Technology) :<span
																class="text-danger">*</span>
															</label>


															<div class="col-sm-2">
																Yes <input type="radio" name="isReg" id="isReg" checked
																	value="0"> No<input type="radio" name="isReg"
																	id="isReg" value="1">
															</div>
														</div>


														<label class="control-label col-sm-3" for="page_name">Mentoring
															to the Students :<span class="text-danger">*</span>
														</label>


														<div class="col-xs-12">




															<table id="example-2"
																class="table table-striped dt-responsive display">
																<thead>
																	<tr>
																		<th width="10%">Sr No</th>
																		<th width="45%">Year</th>
																		<th width="45%">No of Students</th>
																	</tr>
																</thead>



																<tbody>

																	<tr>
																		<td>1</td>
																		<td>2018-2019</td>

																		<td><input type="text" class="form-control"
																			id="curExp" name="curExp" value="" required></td>

																	</tr>
																	<tr>
																		<td>2</td>
																		<td>2019-2020</td>

																		<td><input type="text" class="form-control"
																			id="curExp" name="curExp" value="" required></td>

																	</tr>
																	<tr>
																		<td>3</td>
																		<td>2020-2021</td>

																		<td><input type="text" class="form-control"
																			id="curExp" name="curExp" value="" required></td>

																	</tr>


																</tbody>
															</table>
															<hr>


															<h5 class="title pull-left">
																<strong>Publication/Presentation Details</strong>
															</h5>

															<div class="col-xs-12"></div>
															<div class="form-group">
																<label class="control-label col-sm-2" for="page_order">Conference
																	Attended :<span class="text-danger">*</span>
																</label>


																<div class="col-sm-3">
																	National <input type="radio" name="conat" id="conat"
																		checked value="0"> International<input
																		type="radio" name="conat" id="conat" value="1">
																</div>

																<label class="control-label col-sm-2" for="smallheading">Date
																	: <span class="text-danger">*</span>
																</label>
																<div class="col-sm-2">
																	<input type="date" class="form-control" id="dob"
																		name="dob" placeholder="DOB" value="" required>
																</div>

																<label class="control-label col-sm-1" for="smallheading">Venue
																	: <span class="text-danger"></span>
																</label>
																<div class="col-sm-2">
																	<input type="text" class="form-control" id="dob"
																		name="dob" placeholder="Venue" value="" required>
																</div>

															</div>

															<div class="form-group">




																<label class="control-label col-sm-2" for="smallheading">Funding
																	From : <span class="text-danger">*</span>
																</label>
																<div class="col-sm-2">
																	<input type="text" class="form-control" id="dob"
																		name="dob" placeholder="Funding from" value=""
																		required>
																</div>

																<label class="control-label col-sm-2" for="smallheading">Amount
																	: <span class="text-danger">*</span>
																</label>
																<div class="col-sm-2">
																	<input type="text" class="form-control" id="dob"
																		name="dob" placeholder="Amount" value="" required>
																</div>

															</div>




															<div class="form-group">
																<label class="control-label col-sm-2" for="page_order">Journal
																	Publication :<span class="text-danger">*</span>
																</label>


																<div class="col-sm-3">
																	National <input type="radio" name="pub" id="pub"
																		checked value="0"> International<input
																		type="radio" name="pub" id="pub" value="1">
																</div>

																<label class="control-label col-sm-2" for="smallheading">Name
																	of Journal : <span class="text-danger">*</span>
																</label>
																<div class="col-sm-2">
																	<input type="text" class="form-control" id="dob"
																		name="dob" placeholder="Name of journal" value=""
																		required>
																</div>

																<label class="control-label col-sm-1" for="page_order">Scopus/DIO
																	:<span class="text-danger">*</span>
																</label>


																<div class="col-sm-2">
																	Yes <input type="radio" name="dio" id="dio" checked
																		value="0"> No<input type="radio" name="dio"
																		id="dio" value="1">
																</div>
															</div>
															<div class="form-group">
																<label class="control-label col-sm-2" for="smallheading">Year
																	of Publication : <span class="text-danger">*</span>
																</label>
																<div class="col-sm-2">
																	<input type="date" class="form-control" id="dob"
																		name="dob" placeholder="Date" value="" required>
																</div>


																<label class="control-label col-sm-2" for="page_order">Issue
																	:<span class="text-danger">*</span>
																</label>


																<div class="col-sm-2">
																	<input type="text" class="form-control" id="issue"
																		name="issue" placeholder="Issue" value="" required>
																</div>


																<label class="control-label col-sm-2" for="smallheading">Volume
																	: <span class="text-danger">*</span>
																</label>
																<div class="col-sm-2">
																	<input type="text" class="form-control" id="dob"
																		name="dob" placeholder="Volume" value="" required>
																</div>


															</div>



															<div class="form-group">



																<label class="control-label col-sm-2" for="smallheading">Page
																	No : <span class="text-danger">*</span>
																</label> <label class="control-label col-sm-1"
																	for="smallheading">From : <span
																	class="text-danger">*</span>
																</label>
																<div class="col-sm-2">
																	<input type="text" class="form-control" id="dob"
																		name="dob" placeholder="From" value="" required>
																</div>


																<label class="control-label col-sm-1" for="page_order">To
																	:<span class="text-danger">*</span>
																</label>


																<div class="col-sm-2">
																	<input type="text" class="form-control" id="issue"
																		name="issue" placeholder="To" value="" required>
																</div>



															</div>



															<div class="form-group">



																<label class="control-label col-sm-2" for="smallheading">Book
																	Publication : <span class="text-danger">*</span>
																</label> <label class="control-label col-sm-2"
																	for="smallheading">Title of Book : <span
																	class="text-danger">*</span>
																</label>
																<div class="col-sm-4">
																	<input type="text" class="form-control" id="dob"
																		name="dob" placeholder="Title of Book" value=""
																		required>
																</div>


																<label class="control-label col-sm-2" for="page_order">Edition
																	:<span class="text-danger">*</span>
																</label>


																<div class="col-sm-2">
																	<input type="text" class="form-control" id="issue"
																		name="issue" placeholder="Edition" value="" required>
																</div>





															</div>

															<div class="form-group">

																<label class="control-label col-sm-2" for="page_order">Name
																	of Authors/Co-Authors :<span class="text-danger">*</span>
																</label>


																<div class="col-sm-6">
																	<input type="text" class="form-control" id="issue"
																		name="issue" placeholder="Name of Authors/Co-Authors "
																		value="" required>
																</div>
																<div class="col-sm-1"></div>

																<div class="col-sm-3">
																	Publication <input type="radio" name="publication"
																		id="publication" checked value="0"> Publisher<input
																		type="radio" name="publication" id="publication"
																		value="1">
																</div>

															</div>

															<div class="form-group">

																<label class="control-label col-sm-2" for="page_order">ISBN
																	No :<span class="text-danger">*</span>
																</label>


																<div class="col-sm-3">
																	<input type="text" class="form-control" id="isbn"
																		name="isbn" placeholder="ISBN No" value="" required>
																</div>


																<label class="control-label col-sm-2" for="smallheading">Year
																	of Publication : <span class="text-danger">*</span>
																</label>
																<div class="col-sm-2">
																	<input type="date" class="form-control" id="dob"
																		name="dob" placeholder="Title of Book" value=""
																		required>
																</div>


															</div>



															<hr>

															<h5 class="title pull-left">
																<strong>Research Project Details</strong>
															</h5>
															<div class="col-sm-12"></div>

															<div class="form-group">
																<label class="control-label col-sm-2" for="smallheading">Name
																	of Project : <span class="text-danger">*</span>
																</label>
																<div class="col-sm-4">
																	<input type="text" class="form-control" id="dob"
																		name="dob" placeholder="Name of Project" value=""
																		required>
																</div>


																<label class="control-label col-sm-3" for="page_order">Sponsoring
																	Authority :<span class="text-danger">*</span>
																</label>


																<div class="col-sm-3">
																	<input type="text" class="form-control" id="issue"
																		name="issue" placeholder="Sponsoring Authority"
																		value="" required>
																</div>



															</div>

															<div class="form-group">
																<label class="control-label col-sm-2" for="smallheading">Name
																	of Principal Investigator : <span class="text-danger">*</span>
																</label>
																<div class="col-sm-4">
																	<input type="text" class="form-control" id="dob"
																		name="dob"
																		placeholder="Name of Principal Investigator" value=""
																		required>
																</div>


																<label class="control-label col-sm-2" for="page_order">Department
																	Name :<span class="text-danger">*</span>
																</label>


																<div class="col-sm-4">
																	<input type="text" class="form-control" id="issue"
																		name="Department" placeholder="Department Name"
																		value="" required>
																</div>



															</div>


															<div class="form-group">
																<label class="control-label col-sm-2" for="smallheading">Name
																	of Co-Principal Investigator : <span
																	class="text-danger">*</span>
																</label>
																<div class="col-sm-4">
																	<input type="text" class="form-control" id="dob"
																		name="dob"
																		placeholder="Name of Co-Principal Investigator"
																		value="" required>
																</div>


																<label class="control-label col-sm-2" for="page_order">Department
																	Name :<span class="text-danger">*</span>
																</label>


																<div class="col-sm-4">
																	<input type="text" class="form-control" id="issue"
																		name="Department" placeholder="Department Name"
																		value="" required>
																</div>



															</div>


															<div class="form-group">
																<label class="control-label col-sm-2" for="smallheading">Grant
																	: <span class="text-danger">*</span>
																</label>
																<div class="col-sm-2">
																	<input type="text" class="form-control" id="dob"
																		name="Grant" placeholder="Grant" value="" required>
																</div>


																<label class="control-label col-sm-2" for="page_order">Total
																	Amount :<span class="text-danger">*</span>
																</label>


																<div class="col-sm-2">
																	<input type="text" class="form-control" id="issue"
																		name="Total Amount" placeholder="Total Amount"
																		value="" required>
																</div>

																<label class="control-label col-sm-2" for="page_order">
																	Amount Received :<span class="text-danger">*</span>
																</label>


																<div class="col-sm-2">
																	<input type="text" class="form-control" id="issue"
																		name="Amount Received" placeholder="Amount Received"
																		value="" required>
																</div>



															</div>


															<div class="form-group">
																<label class="control-label col-sm-2" for="smallheading">Year
																	of Project Sanction : <span class="text-danger">*</span>
																</label>
																<div class="col-sm-2">
																	<input type="text" class="form-control" id="dob"
																		name="Grant" placeholder="Year of Project Sanction"
																		value="" required>
																</div>


																<label class="control-label col-sm-2" for="page_order">From
																	Date :<span class="text-danger">*</span>
																</label>


																<div class="col-sm-2">
																	<input type="date" class="form-control" id="issue"
																		name="Total Amount" placeholder="" value="" required>
																</div>

																<label class="control-label col-sm-2" for="page_order">
																	To Date :<span class="text-danger">*</span>
																</label>


																<div class="col-sm-2">
																	<input type="date" class="form-control" id="issue"
																		name="Amount Received" placeholder="Total Amount"
																		value="" required>
																</div>



															</div>
															<hr>


															<h5 class="title pull-left">
																<strong>Consultancy</strong>
															</h5>
															<div class="col-sm-1"></div>
															<div class="col-sm-2">
																Yes <input type="radio" name="consultancy"
																	id="consultancy" checked value="0"> No<input
																	type="radio" name="consultancy" id="consultancy"
																	value="1">
															</div>
															<div class="col-sm-12"></div>

															<div class="form-group">
																<label class="control-label col-sm-2" for="smallheading">Nature
																	of Consultancy: <span class="text-danger">*</span>
																</label>
																<div class="col-sm-4">
																	<input type="text" class="form-control" id="dob"
																		name="Grant" placeholder="Name of Consultancy"
																		value="" required>
																</div>

																<label class="control-label col-sm-3" for="smallheading">Sponsoring
																	Agency/Industry : <span class="text-danger">*</span>
																</label>
																<div class="col-sm-3">
																	<input type="text" class="form-control" id="dob"
																		name="Grant" placeholder="Sponsoring Agency/Industry"
																		value="" required>
																</div>

															</div>

															<div class="form-group">
																<label class="control-label col-sm-2" for="page_order">Amount
																	of Consultancy :<span class="text-danger">*</span>
																</label>


																<div class="col-sm-2">
																	<input type="text" class="form-control" id="dor"
																		name="dor" placeholder="Amount of Consultancy"
																		value="" required>
																</div>

																<label class="control-label col-sm-2" for="page_order">Consultancy
																	Period :<span class="text-danger">*</span>
																</label>


																<div class="col-sm-2">
																	<input type="text" class="form-control" id="dor"
																		name="dor" placeholder="Consultancy Period" value=""
																		required>
																</div>

																<label class="control-label col-sm-2" for="smallheading">Project
																	Completed : <span class="text-danger">*</span>
																</label>


																<div class="col-sm-2">
																	Yes <input type="radio" name="projectComp"
																		id="projectComp" checked value="0"> No<input
																		type="radio" name="projectComp" id="projectComp"
																		value="1">
																</div>


															</div>
															<hr>

															<h5 class="title pull-left">
																<strong>Patent Details</strong>
															</h5>
															<div class="col-sm-12"></div>



															<div class="form-group">
																<label class="control-label col-sm-2" for="smallheading">Patent
																	Title : <span class="text-danger">*</span>
																</label>
																<div class="col-sm-6">
																	<input type="text" class="form-control" id="dob"
																		name="Grant" placeholder="Patent Title" value=""
																		required>
																</div>

																<label class="control-label col-sm-2" for="smallheading">Patent
																	Filling Date : <span class="text-danger">*</span>
																</label>
																<div class="col-sm-2">
																	<input type="date" class="form-control" id="dob"
																		name="Grant" placeholder="Sponsoring Agency/Industry"
																		value="" required>
																</div>

															</div>

															<div class="form-group">
																<label class="control-label col-sm-2" for="smallheading">Name
																	of Co-assistant/Guide : <span class="text-danger">*</span>
																</label>
																<div class="col-sm-2">
																	<input type="text" class="form-control" id="dob"
																		name="Grant" placeholder="Name of Co-assistant/Guide"
																		value="" required>
																</div>

																<label class="control-label col-sm-2" for="smallheading">Date
																	of Publication : <span class="text-danger">*</span>
																</label>
																<div class="col-sm-2">
																	<input type="date" class="form-control" id="dob"
																		name="Grant" value="" required>
																</div>
																<label class="control-label col-sm-2" for="smallheading">Academic
																	Year : <span class="text-danger">*</span>
																</label>
																<div class="col-sm-2">
																	<input type="text" class="form-control" id="dob"
																		name="Grant" value="" placeholder="Academic Year"
																		required>
																</div>

															</div>
															<hr>

															<h5 class="title pull-left">
																<strong>Award Recognition Details</strong>
															</h5>
															<div class="col-sm-12"></div>
															<div class="form-group">
																<label class="control-label col-sm-2" for="smallheading">Name
																	of Award/Recognition : <span class="text-danger">*</span>
																</label>
																<div class="col-sm-4">
																	<input type="text" class="form-control" id="dob"
																		name="Grant" placeholder="Name of Award/Recognition"
																		value="" required>
																</div>

																<label class="control-label col-sm-2" for="smallheading">Awarding
																	Agency : <span class="text-danger">*</span>
																</label>
																<div class="col-sm-4">
																	<input type="text" class="form-control" id="dob"
																		name="Grant" placeholder="Awarding Agency" value=""
																		required>
																</div>

															</div>


															<div class="form-group">
																<label class="control-label col-sm-2" for="smallheading">Nature
																	of Award/Recognition : <span class="text-danger">*</span>
																</label>
																<div class="col-sm-4">
																	<input type="text" class="form-control" id="dob"
																		name="Grant" placeholder="Nature of Award/Recognition"
																		value="" required>
																</div>

																<label class="control-label col-sm-1" for="smallheading">Date
																	: <span class="text-danger">*</span>
																</label>
																<div class="col-sm-2">
																	<input type="date" class="form-control" id="dob"
																		name="Grant" placeholder="Awarding Agency" value=""
																		required>
																</div>
																<label class="control-label col-sm-1" for="smallheading">Validity
																	: <span class="text-danger">*</span>
																</label>
																<div class="col-sm-2">
																	Duration <input type="radio" name="validity"
																		id="validity" checked value="0"> Lifetime<input
																		type="radio" name="validity" id="validity" value="1">
																</div>

															</div>

															<hr>

															<h5 class="title pull-left">
																<strong>Out Reach Activity</strong>
															</h5>
															<div class="col-sm-12"></div>


															<div class="form-group">
																<label class="control-label col-sm-2" for="status">Select
																	Attended Activity : <span class="text-danger">*</span>
																</label>
																<div class="col-sm-4">
																	<select id="salutation" name="salutation"
																		class="form-control" required>
																		<option value="0">STTP</option>
																		<option value="1">Seminar</option>
																		<option value="2">Workshop</option>
																		<option value="3">Conference</option>
																		<option value="4">FDP</option>
																		<option value="5">Refresher Course</option>

																	</select>
																</div>

																<label class="control-label col-sm-2" for="smallheading">Name
																	of Activity: <span class="text-danger">*</span>
																</label>
																<div class="col-sm-4">
																	<input type="text" class="form-control" id="dob"
																		name="Grant" placeholder="Name of Activity" value=""
																		required>
																</div>

															</div>


															<div class="form-group">

																<label class="control-label col-sm-2" for="status">Select
																	Level: <span class="text-danger">*</span>
																</label>
																<div class="col-sm-4">
																	<select id="salutation" name="salutation"
																		class="form-control" required>
																		<option value="0">International</option>
																		<option value="1">National</option>
																		<option value="2">State</option>
																		<option value="3">Regional</option>


																	</select>
																</div>



																<label class="control-label col-sm-2" for="smallheading">Date
																	of Activity: <span class="text-danger">*</span>
																</label>
																<div class="col-sm-2">
																	<input type="date" class="form-control" id="dob"
																		name="Grant" placeholder="Awarding Agency" value=""
																		required>
																</div>


															</div>


															<hr>

															<h5 class="title pull-left">
																<strong>Organized</strong>
															</h5>
															<div class="col-sm-12"></div>


															<div class="form-group">
																<label class="control-label col-sm-2" for="status">Select
																	: <span class="text-danger">*</span>
																</label>
																<div class="col-sm-4">
																	<select id="salutation" name="salutation"
																		class="form-control" required>
																		<option value="0">STTP</option>
																		<option value="1">Seminar</option>
																		<option value="2">Workshop</option>
																		<option value="3">Conference</option>
																		<option value="4">FDP</option>
																		<option value="5">Refresher Course</option>

																	</select>
																</div>

																<label class="control-label col-sm-2" for="smallheading">Name
																	of Activity: <span class="text-danger">*</span>
																</label>
																<div class="col-sm-4">
																	<input type="text" class="form-control" id="dob"
																		name="Grant" placeholder="Name of Activity" value=""
																		required>
																</div>

															</div>

															<div class="form-group">

																<label class="control-label col-sm-2" for="status">Select
																	Level: <span class="text-danger">*</span>
																</label>
																<div class="col-sm-2">
																	<select id="salutation" name="salutation"
																		class="form-control" required>
																		<option value="0">International</option>
																		<option value="1">National</option>
																		<option value="2">State</option>
																		<option value="3">Regional</option>


																	</select>
																</div>



																<label class="control-label col-sm-2" for="smallheading">Date
																	of Activity: <span class="text-danger">*</span>
																</label>
																<div class="col-sm-2">
																	<input type="date" class="form-control" id="dob"
																		name="Grant" placeholder="Awarding Agency" value=""
																		required>
																</div>

																<label class="control-label col-sm-2" for="smallheading">No
																	of Participants : <span class="text-danger">*</span>
																</label>
																<div class="col-sm-2">
																	<input type="text" class="form-control" id="dob"
																		name="Grant" placeholder="No of Participants" value=""
																		required>
																</div>


															</div>

															<div class="form-group">
																<label class="control-label col-sm-2" for="smallheading">Funded
																	By : <span class="text-danger">*</span>
																</label>
																<div class="col-sm-2">
																	<input type="text" class="form-control" id="dob"
																		name="Grant" placeholder="Funded By" value="" required>
																</div>




																<label class="control-label col-sm-2" for="smallheading">Amount
																	Sanctioned : <span class="text-danger">*</span>
																</label>
																<div class="col-sm-2">
																	<input type="text" class="form-control" id="dob"
																		name="Grant" placeholder="Amount Sanctioned" value=""
																		required>
																</div>

																<label class="control-label col-sm-2" for="smallheading">Amount
																	Utilized : <span class="text-danger">*</span>
																</label>
																<div class="col-sm-2">
																	<input type="text" class="form-control" id="dob"
																		name="Grant" placeholder="Amount Utilized" value=""
																		required>
																</div>


															</div>

															<div class="form-group">

																<label class="control-label col-sm-2" for="smallheading">Are
																	You Member of : <span class="text-danger">*</span>
																</label> <label class="control-label col-sm-1"
																	for="smallheading">BOS : <span
																	class="text-danger">*</span>
																</label>


																<div class="col-sm-2">
																	Yes <input type="radio" name="bos" id="bos" checked
																		value="0"> No<input type="radio" name="bos"
																		id="bos" value="1">
																</div>

																<label class="control-label col-sm-2" for="smallheading">Name
																	of BOS: <span class="text-danger">*</span>
																</label>
																<div class="col-sm-5">
																	<input type="text" class="form-control" id="dob"
																		name="Grant" placeholder="Name of BOS" value=""
																		required>
																</div>


															</div>

															<div class="form-group">

																<label class="control-label col-sm-2" for="smallheading">University
																	: <span class="text-danger">*</span>
																</label>

																<div class="col-sm-4">
																	<input type="text" class="form-control" id="dob"
																		name="Grant" placeholder="University" value=""
																		required>
																</div>


																<label class="control-label col-sm-1" for="smallheading">From
																	: <span class="text-danger">*</span>
																</label>
																<div class="col-sm-2">
																	<input type="text" class="form-control" id="dob"
																		name="Grant" placeholder="From" value="" required>
																</div>

																<label class="control-label col-sm-1" for="smallheading">To
																	: <span class="text-danger">*</span>
																</label>
																<div class="col-sm-2">
																	<input type="text" class="form-control" id="dob"
																		name="to" placeholder="To" value="" required>
																</div>



															</div>

															<div class="form-group">

																<label class="control-label col-sm-2" for="smallheading">Are
																	You Member of : <span class="text-danger">*</span>
																</label> <label class="control-label col-sm-1"
																	for="smallheading">Faculty : <span
																	class="text-danger">*</span>
																</label>


																<div class="col-sm-2">
																	Yes <input type="radio" name="faculty" id="faculty"
																		checked value="0"> No<input type="radio"
																		name="faculty" id="faculty" value="1">
																</div>

																<label class="control-label col-sm-2" for="smallheading">Name
																	of Faculty: <span class="text-danger">*</span>
																</label>
																<div class="col-sm-5">
																	<input type="text" class="form-control" id="dob"
																		name="Grant" placeholder="Name of Faculty" value=""
																		required>
																</div>


															</div>

															<div class="form-group">

																<label class="control-label col-sm-2" for="smallheading">University
																	: <span class="text-danger">*</span>
																</label>

																<div class="col-sm-4">
																	<input type="text" class="form-control" id="dob"
																		name="Grant" placeholder="University" value=""
																		required>
																</div>


																<label class="control-label col-sm-1" for="smallheading">From
																	: <span class="text-danger">*</span>
																</label>
																<div class="col-sm-2">
																	<input type="text" class="form-control" id="dob"
																		name="Grant" placeholder="From" value="" required>
																</div>

																<label class="control-label col-sm-1" for="smallheading">To
																	: <span class="text-danger">*</span>
																</label>
																<div class="col-sm-2">
																	<input type="text" class="form-control" id="dob"
																		name="to" placeholder="To" value="" required>
																</div>



															</div>

															<div class="form-group">

																<label class="control-label col-sm-2" for="smallheading">Are
																	You Member of : <span class="text-danger">*</span>
																</label> <label class="control-label col-sm-2"
																	for="smallheading">Academic Council : <span
																	class="text-danger">*</span>
																</label>


																<div class="col-sm-2">
																	Yes <input type="radio" name="academiCouncil"
																		id="academiCouncil" checked value="0"> No<input
																		type="radio" name="academiCouncil" id="academiCouncil"
																		value="1">
																</div>

																<label class="control-label col-sm-3" for="smallheading">Name
																	of Academic Council: <span class="text-danger">*</span>
																</label>
																<div class="col-sm-3">
																	<input type="text" class="form-control" id="dob"
																		name="Grant" placeholder="Name of Academic Council"
																		value="" required>
																</div>


															</div>

															<div class="form-group">

																<label class="control-label col-sm-2" for="smallheading">University
																	: <span class="text-danger">*</span>
																</label>

																<div class="col-sm-4">
																	<input type="text" class="form-control" id="dob"
																		name="Grant" placeholder="University" value=""
																		required>
																</div>


																<label class="control-label col-sm-1" for="smallheading">From
																	: <span class="text-danger">*</span>
																</label>
																<div class="col-sm-2">
																	<input type="text" class="form-control" id="dob"
																		name="Grant" placeholder="From" value="" required>
																</div>

																<label class="control-label col-sm-1" for="smallheading">To
																	: <span class="text-danger">*</span>
																</label>
																<div class="col-sm-2">
																	<input type="text" class="form-control" id="dob"
																		name="to" placeholder="To" value="" required>
																</div>



															</div>


															<div class="form-group">

																<label class="control-label col-sm-2" for="smallheading">Examination
																	Paper Setting : <span class="text-danger">*</span>
																</label>


																<div class="col-sm-2">
																	Yes <input type="radio" name="examSetting"
																		id="examSetting" checked value="0"> No<input
																		type="radio" name="examSetting" id="examSetting"
																		value="1">
																</div>

																<label class="control-label col-sm-2" for="smallheading">Answer
																	Sheet Evaluation : <span class="text-danger">*</span>
																</label>


																<div class="col-sm-2">
																	Yes <input type="radio" name="ansEvaluation"
																		id="ansEvaluation" checked value="0"> No<input
																		type="radio" name="ansEvaluation" id="ansEvaluation"
																		value="1">
																</div>

																<label class="control-label col-sm-2" for="smallheading">Answer
																	Sheet Moderation : <span class="text-danger">*</span>
																</label>


																<div class="col-sm-2">
																	Yes <input type="radio" name="ansmod" id="ansmod"
																		checked value="0"> No<input type="radio"
																		name="ansmod" id="ansmod" value="1">
																</div>

															</div>



															<h5 class="title pull-left">
																<strong>Subject Details :</strong>
															</h5>
															<div class="col-sm-12"></div>


															<div class="col-xs-12">


																<table id="example1"
																	class="table table-striped dt-responsive display">
																	<thead>
																		<tr>
																			<th width="5%">Sr No</th>
																			<th width="10%">Year</th>
																			<th width="10%">Semester</th>
																			<th width="15%">Subject Taught</th>
																			<th width="20%">Subject
																				Type(Regular/Elective/Any other)</th>
																			<th width="15%">No. of Students Appeared</th>
																			<th width="10%">Passed</th>
																			<th width="10%">% of Result</th>
																			<th width="10%">Course Outcome</th>
																			<th width="5%">Action</th>
																		</tr>
																	</thead>



																	<tbody>

																		<tr>
																			<td>1</td>

																			<td><input type="text" class="form-control"
																				id="curExp" name="curExp" value="" required></td>
																			<td><input type="text" class="form-control"
																				id="curExp" name="curExp" value="" required></td>

																			<td><input type="text" class="form-control"
																				id="curExp" name="curExp" value="" required></td>
																			<td><input type="text" class="form-control"
																				id="curExp" name="curExp" value="" required></td>
																			<td><input type="text" class="form-control"
																				id="curExp" name="curExp" value="" required></td>
																			<td><input type="text" class="form-control"
																				id="curExp" name="curExp" value="" required></td>
																			<td><input type="text" class="form-control"
																				id="curExp" name="curExp" value="" required></td>
																			<td><input type="text" class="form-control"
																				id="curExp" name="curExp" value="" required></td>
																			<td><button type="submit"
																					class="btn btn-primary">Add</button></td>
																		</tr>
																		<tr>
																			<td>2</td>


																			<td><input type="text" class="form-control"
																				id="curExp" name="curExp" value="" required></td>
																			<td><input type="text" class="form-control"
																				id="curExp" name="curExp" value="" required></td>

																			<td><input type="text" class="form-control"
																				id="curExp" name="curExp" value="" required></td>
																			<td><input type="text" class="form-control"
																				id="curExp" name="curExp" value="" required></td>
																			<td><input type="text" class="form-control"
																				id="curExp" name="curExp" value="" required></td>
																			<td><input type="text" class="form-control"
																				id="curExp" name="curExp" value="" required></td>
																			<td><input type="text" class="form-control"
																				id="curExp" name="curExp" value="" required></td>
																			<td><input type="text" class="form-control"
																				id="curExp" name="curExp" value="" required></td>
																			<td><button type="submit"
																					class="btn btn-primary">Add</button></td>
																		</tr>

																		<tr>
																			<td>3</td>


																			<td><input type="text" class="form-control"
																				id="curExp" name="curExp" value="" required></td>
																			<td><input type="text" class="form-control"
																				id="curExp" name="curExp" value="" required></td>

																			<td><input type="text" class="form-control"
																				id="curExp" name="curExp" value="" required></td>
																			<td><input type="text" class="form-control"
																				id="curExp" name="curExp" value="" required></td>
																			<td><input type="text" class="form-control"
																				id="curExp" name="curExp" value="" required></td>
																			<td><input type="text" class="form-control"
																				id="curExp" name="curExp" value="" required></td>
																			<td><input type="text" class="form-control"
																				id="curExp" name="curExp" value="" required></td>
																			<td><input type="text" class="form-control"
																				id="curExp" name="curExp" value="" required></td>
																			<td><button type="submit"
																					class="btn btn-primary">Add</button></td>
																		</tr>

																	</tbody>
																</table>

															</div>

															<hr>

															<h5 class="title pull-left">
																<strong>SWOC for HODs :</strong>
															</h5>
															<div class="col-sm-12"></div>


															<div class="form-group">
																<label class="control-label col-sm-2" for="smallheading">Strength
																	: <span class="text-danger">*</span>
																</label>
																<div class="col-sm-10">
																	<textarea id="off_add" name="off_add"
																		class="form-control" style="width: 100%;" required></textarea>
																</div>
															</div>

															<div class="form-group">

																<label class="control-label col-sm-2" for="smallheading">Weakness
																	: <span class="text-danger">*</span>
																</label>
																<div class="col-sm-10">
																	<textarea id="off_add" name="off_add"
																		class="form-control" style="width: 100%;" required></textarea>
																</div>
															</div>
															<div class="form-group">

																<label class="control-label col-sm-2" for="smallheading">Opportunity
																	: <span class="text-danger">*</span>
																</label>
																<div class="col-sm-10">
																	<textarea id="off_add" name="off_add"
																		class="form-control" style="width: 100%;" required></textarea>
																</div>


															</div>

															<div class="form-group">

																<label class="control-label col-sm-2" for="smallheading">Challenges
																	: <span class="text-danger">*</span>
																</label>
																<div class="col-sm-10">
																	<textarea id="off_add" name="off_add"
																		class="form-control" style="width: 100%;" required></textarea>
																</div>


															</div>

															<h5 class="title pull-left">
																<strong>Ph.D Guidance :</strong>
															</h5>
															<div class="col-sm-12"></div>

															<div class="form-group">

																<label class="control-label col-sm-2" for="smallheading">Ph.D.
																	Guide : <span class="text-danger">*</span>
																</label>


																<div class="col-sm-2">
																	Yes <input type="radio" name="phdGuide" id="phdGuide"
																		checked value="0"> No<input type="radio"
																		name="phdGuide" id="phdGuide" value="1">
																</div>

																<label class="control-label col-sm-1" for="smallheading">Co-Guide
																	: <span class="text-danger">*</span>
																</label>


																<div class="col-sm-2">
																	Yes <input type="radio" name="coGuide" id="coGuide"
																		checked value="0"> No<input type="radio"
																		name="coGuide" id="coGuide" value="1">
																</div>

																<label class="control-label col-sm-2" for="smallheading">Name
																	of Co-Guide: <span class="text-danger">*</span>
																</label>
																<div class="col-sm-3">
																	<input type="text" class="form-control" id="dob"
																		name="Grant" placeholder="Name of Co_Guide" value=""
																		required>
																</div>


															</div>

															<div class="form-group">



																<label class="control-label col-sm-2" for="smallheading">Name
																	of Ph.D. Scholar: <span class="text-danger">*</span>
																</label>
																<div class="col-sm-4">
																	<input type="text" class="form-control" id="dob"
																		name="Grant" placeholder="Name of Ph.D Scholar"
																		value="" required>
																</div>
																<label class="control-label col-sm-2" for="smallheading">Year
																	of Registration: <span class="text-danger">*</span>
																</label>
																<div class="col-sm-1">
																	<input type="text" class="form-control" id="dob"
																		name="Grant" placeholder="Year" value="" required>
																</div>
																<label class="control-label col-sm-2" for="smallheading">Year
																	of Awarded Ph.D: <span class="text-danger">*</span>
																</label>
																<div class="col-sm-1">
																	<input type="text" class="form-control" id="dob"
																		name="Grant" placeholder="Year" value="" required>
																</div>


															</div>

															<div class="form-group">

																<label class="control-label col-sm-2" for="smallheading">Topic/
																	Area of Research : <span class="text-danger">*</span>
																</label>
																<div class="col-sm-10">
																	<textarea id="off_add" name="off_add"
																		class="form-control" style="width: 100%;" required></textarea>
																</div>
															</div>

															<div class="form-group">



																<label class="control-label col-sm-2" for="smallheading">Date
																	of Registration: <span class="text-danger">*</span>
																</label>
																<div class="col-sm-2">
																	<input type="date" class="form-control" id="dob"
																		name="Grant" placeholder="Name of Ph.D Scholar"
																		value="" required>
																</div>

																<label class="control-label col-sm-2" for="smallheading">Valid
																	up : <span class="text-danger">*</span>
																</label>
																<div class="col-sm-2">
																	<input type="date" class="form-control" id="validup"
																		name="validup" placeholder="Valid up" value=""
																		required>
																</div>


																<label class="control-label col-sm-2" for="smallheading">Awarded
																	: <span class="text-danger">*</span>
																</label>


																<div class="col-sm-2">
																	Yes <input type="radio" name="awarded" id="awarded"
																		checked value="0"> No<input type="radio"
																		name="awarded" id="awarded" value="1">
																</div>

															</div>




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


</body>
</html>