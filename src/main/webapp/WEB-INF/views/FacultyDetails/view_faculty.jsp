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
							<%-- 	<h1 class="title">${title}</h1> --%>
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
								<%-- a
								href="${pageContext.request.contextPath}/publicationList"><button
								type="button" class="btn btn-info">Back</button></a> --%>
								<!-- <a
								class="box_toggle fa fa-chevron-down"></a></div> -->
							</div>

						</header>

						<form class="form-horizontal"
							action="${pageContext.request.contextPath}/insertFacAcademic"
							method="post" name="form_sample_2" id="form_sample_2">
							<div class="content-body">
								<div class="row">

									<ul class="nav nav-tabs">

										<li class="active"><a href="#strength" data-toggle="tab">
												<i class="fa fa-home"></i> Faculty M.Phil./Ph.D. Details
										</a></li>
										<li><a href="#oppt" data-toggle="tab"> <i
												class="fa fa-home"></i> Faculty Academic Details
										</a></li>

										<li><a href="#weak" data-toggle="tab"> <i
												class="fa fa-home"></i> Faculty Personal Details
										</a></li>




									</ul>


									<div class="tab-content">
										<div class="tab-pane fade in active" id="strength">

											<div class="col-xs-12">
												<div class="form-group">
													<label class="control-label col-sm-3" for="isPhdGuide">M.Phil./Ph.D.
														Guide<span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">

														<c:choose>
															<c:when test="${facPhdDetail.isPhdGuide==0}">
															Yes <input type="radio" name="isPhdGuide" id="isPhdGuide"
																	value="1"> No<input type="radio" checked
																	name="isPhdGuide" id="isPhdGuide" value="0">
															</c:when>

															<c:otherwise>
															Yes <input type="radio" checked name="isPhdGuide"
																	id="isPhdGuide" value="1"> No<input
																	type="radio" name="isPhdGuide" id="isPhdGuide"
																	value="0">
															</c:otherwise>

														</c:choose>
														<span class="error_form text-danger" id="isPhdGuide_field"
															style="display: none;">Please select yes</span>

													</div>
												</div>


												<div class="form-group">
													<label class="control-label col-sm-3"
														for="phdRecognitionDt">Date of Recognition<span
														class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="text" class="form-control datepicker"
															id="phdRecognitionDt" name="phdRecognitionDt" readonly
															placeholder="Select Date of Recognition"
															value="${facPhdDetail.phdRecognitionDt}"> <span
															class="error_form text-danger"
															id="phdRecognitionDt_field" style="display: none;">Please
															select date of recognition</span>
													</div>
												</div>

												<div class="form-group">
													<label class="control-label col-sm-3" for="phdValidDt">Valid
														up to <span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="text" class="form-control datepicker"
															id="phdValidDt" readonly name="phdValidDt"
															placeholder="Valid up to Date"
															value="${facPhdDetail.phdValidDt}"> <span
															class="error_form text-danger" id="phdValidDt_field"
															style="display: none;">Please select date of
															validity</span>

													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-sm-3" for="ii">No.
														of Students Guided <span class="text-danger">*</span>

													</label> <label class="control-label col-sm-1" for="phdStuPg">PG
														<span class="text-danger">*</span>
													</label>
													<div class="col-sm-2">
														<input type="number" max="99999" min="0"
															class="form-control" id="phdStuPg" name="phdStuPg"
															placeholder="PG" value="${facPhdDetail.phdStuPg}">
														<span class="error_form text-danger" id="phdStuPg_field"
															style="display: none;">Enter no of PG students</span>
													</div>

													<label style="white-space: nowrap;"
														class="control-label col-sm-1" for="phdStuMphill">M.Phil.
														<span class="text-danger">*</span>
													</label>
													<div class="col-sm-2">
														<input type="number" max="99999" min="0"
															class="form-control" id="phdStuMphill"
															name="phdStuMphill" placeholder="M.Phil."
															value="${facPhdDetail.phdStuMphill}"> <span
															class="error_form text-danger" id="phdStuMphill_field"
															style="display: none;">Enter no of M.Phil.
															students</span>
													</div>
													<label class="control-label col-sm-1" for="phdStuPhd">Ph.D.
														<span class="text-danger">*</span>
													</label>
													<div class="col-sm-2">
														<input type="number" max="99999" min="0"
															class="form-control" id="phdStuPhd" name="phdStuPhd"
															placeholder="Ph.D" value="${facPhdDetail.phdStuPhd}">
														<span class="error_form text-danger" id="phdStuPhd_field"
															style="display: none;">Enter number of Ph.D
															students</span>
													</div>
												</div>


												<div class="form-group">

													<label class="control-label col-sm-3" for="isIctUsed">Use
														of ICT<br> <span style="font-size: 10px">(Information
															Communication Technology)</span> <span class="text-danger">*</span>
													</label>


													<div class="col-sm-2">
														<c:choose>
															<c:when test="${facPhdDetail.isIctUsed==1}">
															Yes <input type="radio" name="isIctUsed" id="isIctUsed"
																	checked value="1"> No<input type="radio"
																	name="isIctUsed" id="isIctUsed" value="0">
															</c:when>
															<c:otherwise>
																Yes <input type="radio" name="isIctUsed" id="isIctUsed"
																	value="1"> No<input type="radio"
																	name="isIctUsed" id="isIctUsed" checked value="0">

															</c:otherwise>
														</c:choose>
														<span class="error_form text-danger" id="isIctUsed_field"
															style="display: none;">Please select use of ICT</span>

													</div>
												</div>


											</div>
											<div class="clearfix"></div>
										</div>

										<div class="tab-pane fade in" id="oppt">



											<div class="col-md-12"></div>
											<div class="col-xs-12">
												<div class="col-xs-12"></div>

												<div class="form-group">
													<label class="control-label col-sm-2"
														for="fQualificationId"> Qualification<span
														class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<select id="fQualificationId" name="fQualificationId"
															class="form-control">
															<c:forEach items="${quolfList}" var="quolf">
																<c:choose>
																	<c:when
																		test="${quolf.qualificationId==editFacAcad.fQualificationId}">
																		<option selected value="${quolf.qualificationId}">${quolf.qualificationName}</option>

																	</c:when>
																	<c:otherwise>
																		<option value="${quolf.qualificationId}">${quolf.qualificationName}</option>

																	</c:otherwise>


																</c:choose>

															</c:forEach>

														</select> <span class="error_form text-danger"
															id="fQualificationId_field" style="display: none;">Please
															select qualification</span>

													</div>

												</div>


												<div class="form-group">
													<label class="control-label col-sm-2" for="fClass">
														Class <span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<select id="fClass" name="fClass" class="form-control">

															<c:choose>
																<c:when test="${editFacAcad.fClass eq 'Distinction'}">
																	<option selected value="Distinction">Distinction</option>
																	<option value="First">First</option>
																	<option value="Second">Second</option>
																	<option value="Pass">Pass</option>
																</c:when>
																<c:when test="${editFacAcad.fClass eq 'First'}">
																	<option value="Distinction">Distinction</option>
																	<option selected value="First">First</option>
																	<option value="Second">Second</option>
																	<option value="Pass">Pass</option>
																</c:when>
																<c:when test="${editFacAcad.fClass eq 'Second'}">
																	<option value="Distinction">Distinction</option>
																	<option value="First">First</option>
																	<option selected value="Second">Second</option>
																	<option value="Pass">Pass</option>
																</c:when>
																<c:when test="${editFacAcad.fClass eq 'Pass'}">
																	<option value="Distinction">Distinction</option>
																	<option value="First">First</option>
																	<option value="Second">Second</option>
																	<option selected value="Pass">Pass</option>
																</c:when>
																<c:otherwise>
																	<option value="Distinction">Distinction</option>
																	<option value="First">First</option>
																	<option value="Second">Second</option>
																	<option value="Pass">Pass</option>
																</c:otherwise>

															</c:choose>
														</select> <span class="error_form text-danger" id="fClass_field"
															style="display: none;">Please select class
															achieved</span>

													</div>
													<div class="col-sm-2"></div>
												</div>

												<div class="form-group">
													<label class="control-label col-sm-2" for="fUniversity">University
														<span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="text" maxlength="200" onchange="trim(this)"
															class="form-control" id="fUniversity" name="fUniversity"
															placeholder="Enter University Name"
															value="${editFacAcad.fUniversity}"> <span
															class="error_form text-danger" id="fUniversity_field"
															style="display: none;">Please enter university
															name</span>

													</div>


												</div>



												<div class="form-group">
													<label class="control-label col-sm-2" for="fPassingYear">Year
														of Passing<span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="text" class="form-control datepickeryear"
															data-min-view-mode="years" data-start-view="2"
															data-format="yyyy" id="fPassingYear" name="fPassingYear"
															placeholder="Select Passing Year" autocomplete="off"
															value="${editFacAcad.fPassingYear}"> <span
															class="error_form text-danger" id="fPassingYear_field"
															style="display: none;">Please select year of
															passing</span>

													</div>

												</div>



											</div>


											<div class="clearfix"></div>


										</div>

										<div class="tab-pane fade in" id="weak">


											<div class="col-md-12"></div>

											<div class="form-group">
												<label class="control-label col-sm-3" for="fac_name">
													Name<span class="text-danger">*</span>
												</label>
												<div class="col-sm-6">
													<input type="text" readonly class="form-control"
														id="fac_name" name="fac_name"
														placeholder="Last Name Middle First Name"
														value="${staff.facultyFirstName}" required>
												</div>
											</div>

											<div class="form-group">
												<label class="control-label col-sm-3" for="fac_address">Address
													<span class="text-danger">*</span>
												</label>
												<div class="col-sm-6">
													<input type="text" onchange="trim(this)" maxlength="200"
														class="form-control" id="fac_address" name="fac_address"
														placeholder="Permanent Address"
														value="${facPerDetail.fAddress}"> <span
														class="error_form text-danger" id="fac_address_field"
														style="display: none;">Please enter permanent
														address</span>
												</div>
											</div>

											<div class="form-group">
												<div class="col-sm-1"></div>
												<label class="control-label col-sm-6" for="is_add_same">Is
													Permanent and Correspondence Address Same <span
													class="text-danger">*</span>
												</label>

												<div class="col-sm-3">
													<c:choose>
														<c:when test="${facPerDetail.isAddSame==1}">
													Yes <input type="radio" name="is_add_same" id="is_add_same"
																checked value="1" onclick="check(this.value)"> No<input
																type="radio" onclick="check(this.value)"
																name="is_add_same" id="is_add_same" value="0">

														</c:when>
														<c:otherwise>
													
													Yes <input type="radio" name="is_add_same" id="is_add_same"
																value="1" onclick="check(this.value)"> No<input
																type="radio" onclick="check(this.value)" checked
																name="is_add_same" id="is_add_same" value="0">

														</c:otherwise>

													</c:choose>
													<span class="error_form text-danger" id="is_add_same_field"
														style="display: none;">Please select
														permanent/correspondence address same or not</span>

												</div>
											</div>

											<div class="form-group" id="abc" style="display: none">
												<label class="control-label col-sm-3" for="fac_address2">Correspondence
													Address <span class="text-danger">*</span>
												</label>
												<div class="col-sm-9">
													<input type="text" onchange="trim(this)" maxlength="200"
														class="form-control" id="fac_address2"
														value="${facPerDetail.fAddress2}" name="fac_address2"
														placeholder="Correspondence Address"> <span
														class="error_form text-danger" id="fac_address2_field"
														style="display: none;">Please enter correspondence
														address</span>
												</div>
											</div>

											<div class="form-group">
												<label class="control-label col-sm-3" for="fac_mob">Mobile
													No<span class="text-danger">*</span>
												</label>
												<div class="col-sm-6">
													<input type="text" readonly class="form-control"
														id="fac_mob" name="fac_mob" pattern="^[1-9]{1}[0-9]{9}$"
														maxlength="10"
														title="Phone number with 7-9 and remaing 9 digit with 0-9"
														placeholder="Mobile No" value="${staff.contactNo}">
												</div>
												<div class="col-sm-2"></div>
											</div>
											<div class="form-group">
												<label class="control-label col-sm-3" for="f_phone">Phone
													No<span class="text-danger"></span>
												</label>
												<div class="col-sm-6">
													<input type="text" class="form-control" id="f_phone"
														onchange="trim(this)" name="f_phone" maxlength="15"
														placeholder="Office Landline No"
														value="${facPerDetail.fPhone}">

												</div>
											</div>
											<div class="form-group">
												<label class="control-label col-sm-3" for="f_resident">Resident
													No</label>
												<div class="col-sm-6">
													<input type="text" class="form-control" id="f_resident"
														onchange="trim(this)" name="f_resident" maxlength="15"
														title="Phone number with 7-9 and remaing 9 digit with 0-9"
														placeholder="Resident Phone No"
														value="${facPerDetail.fResident}">
												</div>
												<div class="col-sm-2"></div>
											</div>
											<div class="form-group">
												<label class="control-label col-sm-3" for="f_email">Email
													ID<span class="text-danger">*</span>
												</label>
												<div class="col-sm-6">
													<input type="text" readonly class="form-control"
														id="f_email" name="f_email" placeholder="abc@xyz.com"
														value="${staff.email}">
												</div>
											</div>
											<div class="form-group">
												<label class="control-label col-sm-3" for="f_aadhar">Aadhar
													No<span class="text-danger">*</span>
												</label>
												<div class="col-sm-6">
													<input type="text" maxlength="12" class="form-control"
														id="f_aadhar" name="f_aadhar"
														placeholder="Consecutive 12 digit Aadhar No"
														value="${facPerDetail.fAadhar}"> <span
														class="error_form text-danger" id="f_aadhar_field"
														style="display: none;">Please enter aadhaar no</span>
												</div>
											</div>
											<div class="form-group">
												<label class="control-label col-sm-3" for="f_designation">
													Designation <span class="text-danger">*</span>
												</label>
												<div class="col-sm-6">
													<select id="f_designation" name="f_designation"
														class="form-control" disabled required>
														<c:forEach items="${desigList}" var="des">
															<c:choose>
																<c:when
																	test="${staff.currentDesignationId==des.designationId}">
																	<option selected value="${des.designationId}">${des.designationName}</option>
																</c:when>
																<c:otherwise>
																	<option value="${des.designationId}">${des.designationName}</option>
																</c:otherwise>
															</c:choose>
														</c:forEach>
													</select>
												</div>
												<div class="col-sm-2"></div>
											</div>

											<div class="form-group">
												<label class="control-label col-sm-3" for="f_dob">Date
													of Birth<span class="text-danger">*</span>
												</label>
												<div class="col-sm-6">
													<input type="text" class="form-control datepicker"
														id="f_dob" name="f_dob" placeholder="Enter Date Of Birth"
														value="${facPerDetail.fDob}"> <span
														class="error_form text-danger" id="f_dob_field"
														style="display: none;">Please select date of birth</span>

												</div>
											</div>

											<div class="form-group">
												<label class="control-label col-sm-3" for="f_doj">Date
													of Joining <span class="text-danger">*</span>
												</label>
												<div class="col-sm-6">
													<input type="text" class="form-control datepicker"
														id="f_doj" name="f_doj" placeholder="Date of Joining"
														value="${staff.joiningDate}" readonly disabled>
												</div>
												<div class="col-sm-2"></div>
											</div>
											<div class="form-group">
												<label class="control-label col-sm-3" for="f_prevExp">Previous
													Experience <span class="text-danger">*</span>
												</label>
												<div class="col-sm-6">
													<input type="number" min="0" class="form-control"
														id="f_prevExp" name="f_prevExp"
														placeholder="Previous Experience In Months"
														value="${facPerDetail.fPastExp}"> <span
														class="error_form text-danger" id="f_prevExp_field"
														style="display: none;">Please enter previous
														experience</span>

												</div>
											</div>

											<div class="form-group">
												<label class="control-label col-sm-3" for="f_gender">Gender<span
													class="text-danger">*</span>
												</label>
												<div class="col-sm-9">
													<c:choose>
														<c:when test="${facPerDetail.fGender==0}">
													Male<input type="radio" checked name="f_gender"
																id="f_gender" value="0">&nbsp;&nbsp;&nbsp;
													Female<input type="radio" name="f_gender" id="f_gender"
																value="1">&nbsp;&nbsp;&nbsp; 
															Transgender<input type="radio" name="f_gender"
																id="f_gender" value="2">
														</c:when>

														<c:when test="${facPerDetail.fGender==1}">
													Male<input type="radio" name="f_gender" id="f_gender"
																value="0">&nbsp;&nbsp;&nbsp;Female<input
																type="radio" name="f_gender" id="f_gender" checked
																value="1">&nbsp;&nbsp;&nbsp; 
															Transgender<input type="radio" name="f_gender"
																id="f_gender" value="2">
														</c:when>
														<c:otherwise>Male<input type="radio"
																name="f_gender" id="f_gender" value="0">&nbsp;&nbsp;&nbsp;
													Female<input type="radio" name="f_gender" id="f_gender"
																value="1">&nbsp;&nbsp;&nbsp; 
															Transgender<input type="radio" checked name="f_gender"
																id="f_gender" value="2">
														</c:otherwise>

													</c:choose>
													<span class="error_form text-danger" id="f_gender_field"
														style="display: none;">Please select gender</span>

												</div>
											</div>


										</div>

									</div>
								</div>
							</div>
						</form>
					</section>
				</div>


				<!-- MAIN CONTENT 
				
				AREA ENDS -->
			</section>
		</section>
	</div>

	<!-- END CONTENT -->




	<!-- END CONTAINER -->
	<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->

	<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>


</body>
</html>