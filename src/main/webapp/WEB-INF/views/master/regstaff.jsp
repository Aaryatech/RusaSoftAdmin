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
<body class=" " onload="showIsReg()">
	<c:url value="/getUserInfo" var="getUserInfo"></c:url>
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
							<!-- <h4>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Institute Name:abc&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							Department Name:xyz</h4> -->
							<h2 class="title pull-left">${title}</h2>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/addFaculty"
										method="post" name="form_sample_2" id="form_sample_2">



										<input type="hidden" id="faculty_id" name="faculty_id"
											value="${staff.facultyId}"> <input type="hidden"
											id="addEdit" name="addEdit" value="${addEdit}">

										<div class="form-group">
											<label class="control-label col-sm-2" for="page_name">Faculty
												Name <span class="text-danger">*</span>
											</label>
											<div class="col-sm-10">
												<input type="text" class="form-control"
													id="faculty_first_name" onchange="trim(this)"
													name="faculty_first_name"
													placeholder="First Name			Middle Name			 Last Name"
													value="${staff.facultyFirstName}" autocomplete="off">
												<span class="error_form text-danger" id="error_formfield1"
													style="display: none;">Please enter faculty name.</span>
											</div>
										</div>


										<div class="form-group">
											<label class="control-label col-sm-2" for="status">Department
											 <span class="text-danger">*</span>
											</label>
											<div class="col-sm-10">
												<select id="dept" name="dept" class="form-control">
													<c:forEach items="${deptList}" var="dList">
														<c:choose>
															<c:when test="${dList.deptId==staff.deptId}">
																<option selected value="${dList.deptId}">${dList.deptName}</option>

															</c:when>
															<c:otherwise>

																<option value="${dList.deptId}">${dList.deptName}</option>

															</c:otherwise>

														</c:choose>

													</c:forEach>

												</select>
											</div>
										</div>


										<div class="form-group">
											<label class="control-label col-sm-2" for="status">Highest Qualification
											<span class="text-danger">*</span>
											</label>
											<div class="col-sm-10">
												<select id="hod_quolf" name="hod_quolf" class="form-control">
													<c:forEach items="${quolfList}" var="quolf">
														<c:choose>
															<c:when
																test="${staff.highestQualification==quolf.qualificationId}">
																<option selected value="${quolf.qualificationId}">${quolf.qualificationName}</option>

															</c:when>
															<c:otherwise>

																<option value="${quolf.qualificationId}">${quolf.qualificationName}</option>

															</c:otherwise>

														</c:choose>

													</c:forEach>

												</select>
											</div>
										</div>

										<div class="form-group">
											<label class="control-label col-sm-2" for="year"
												style="text-align: left;">
												Qualification Year<span class="text-danger">*</span>
											</label>

											<div class="col-sm-10">
												<input type="text" class="form-control datepickeryear"
													data-min-view-mode="years" data-start-view="2"
													data-format="yyyy"
													placeholder="Year of highest Qualification Acquired"
													id="yr_highest_qualification_acqrd"
													value="${staff.hightestQualificationYear}"
													name="yr_highest_qualification_acqrd" autocomplete="off"
													onkeypress='return restrictAlphabets(event)'
													onchange="trim(this)"> <span
													class="error_form text-danger" id="error_formfield0"
													style="display: none;">Please enter year of highest
													qualification.</span>
											</div>

										</div>

										<%-- <div class="form-group">
											<label class="control-label col-sm-2" for="page_order">Year
												of highest Qualification Acquired <span class="text-danger">*</span>
											</label>

											<div class="col-sm-10">
												<select id="yr_highest_qualification_acqrd"
													name="yr_highest_qualification_acqrd" class="form-control">

													<c:forEach items="${acaYearList}" var="acaYearList">
														<c:choose>
															<c:when
																test="${acaYearList.yearId==staff.hightestQualificationYear}">
																<option selected value="${acaYearList.yearId}">${acaYearList.academicYear}</option>

															</c:when>
															<c:otherwise>
																<option value="${acaYearList.yearId}">${acaYearList.academicYear}</option>

															</c:otherwise>

														</c:choose>

													</c:forEach>

												</select>
											</div>
										</div> --%>


										<div class="form-group">
											<label class="control-label col-sm-2" for="status">Current
												Designation <span class="text-danger">*</span>
											</label>
											<div class="col-sm-10">
												<select id="designation" name="designation"
													class="form-control">
													<c:forEach items="${desigList}" var="makeList">
														<c:choose>
															<c:when
																test="${makeList.designationId == staff.currentDesignationId}">
																<option value="${makeList.designationId}"
																	selected="selected">${makeList.designationName}</option>
															</c:when>
															<c:otherwise>
																<option value="${makeList.designationId}">${makeList.designationName}</option>
															</c:otherwise>
														</c:choose>
													</c:forEach>

												</select>
											</div>
										</div>


										<div class="form-group">
											<label class="control-label col-sm-2" for="page_order">Joining
												Date <span class="text-danger">*</span>
											</label>
											<div class="col-sm-3">
												<input type="text" class="form-control datepicker"
													id="dateOfJoin" data-end-date="0d" data-format="dd-mm-yyyy"
													onkeypress='return restrictAlphabets(event)'
													onchange="trim(this)" name="join_date" autocomplete="off"
													placeholder="dd/mm/yyyy" value="${staff.joiningDate}">
												<span class="error_form text-danger" id="error_formfield2"
													style="display: none;">Please enter joining date.</span>
											</div>

											<label class="control-label col-sm-2" for="page_order">Is
												Working Today<span class="text-danger">*</span>
											</label>

											<div class="col-sm-2">
												<c:choose>
													<c:when test="${staff.facultyId==0}">

														<input type="radio" id="is_registration1"
															name="is_registration" value="1" checked
															onclick="setDate(this.value)">Yes 
																<input type="radio" id="is_registration2"
															name="is_registration" value="0"
															onclick="setDate(this.value)">No 
															
															</c:when>
													<c:otherwise>

														<c:choose>
															<c:when test="${empty staff.realivingDate}">
																<input type="radio" id="is_registration"
																	name="is_registration" value="1" checked
																	onclick="setDate(this.value)">Yes  
																<input type="radio" id="is_registration"
																	name="is_registration" value="0"
																	onclick="setDate(this.value)">No 
															
																
																</c:when>
															<c:otherwise>
																<input type="radio" id="is_registration"
																	name="is_registration" value="1"
																	onclick="setDate(this.value)">Yes
																<input type="radio" id="is_registration" checked
																	name="is_registration" value="0"
																	onclick="setDate(this.value)">No
															
																
																</c:otherwise>

														</c:choose>

													</c:otherwise>


												</c:choose>
											</div>

										</div>

										<div class="form-group">

											<div id="abc" style="display: none">
												<label class="control-label col-sm-2" for="page_order">Relieving
													Date <span class="text-danger">*</span>
												</label>
												<div class="col-sm-3">
													<input type="text" class="form-control datepicker"
														id="relDate" value="${staff.realivingDate}"
														onchange="trim(this)" data-end-date="0d" data-format="dd-mm-yyyy"
														onkeypress='return restrictAlphabets(event)'
														name="acc_off_relDate" autocomplete="off"
														placeholder="dd/mm/yyyy"> <span
														class="error_form text-danger" id="error_formfield3"
														style="display: none;">Please enter relieving date.</span>
												</div>
											</div>
										</div>

										<div class="form-group">
											<label class="control-label col-sm-2" for="status">Teaching
												to <span class="text-danger">*</span>
											</label>
											<div class="col-sm-10">
												<select id="teachTo" name="teachTo" class="form-control">
													<c:forEach items="${teachingList}" var="teach">
														<c:choose>
															<c:when test="${staff.teachingTo==teach.qualificationId}">
																<option selected value="${teach.qualificationId}">${teach.qualificationName}</option>

															</c:when>
															<c:otherwise>

																<option value="${teach.qualificationId}">${teach.qualificationName}</option>

															</c:otherwise>

														</c:choose>

													</c:forEach>

												</select>
											</div>
										</div>

										<div class="form-group">
											<label class="control-label col-sm-2" for="is_add_same">Belongs to 
												 Same State <span
												class="text-danger">*</span>
											</label>
											<div class="col-sm-3">
													<c:if test="${staff.facultyId>0}">
													Yes <input type="radio" ${staff.isSame == 1 ? 'checked' : ''} name="is_state_same" id="is_state_same" value="1"> 
													No<input type="radio" ${staff.isSame == 0 ? 'checked' : ''} name="is_state_same" id="is_state_same" value="0">
													</c:if>
													
													<c:if test="${staff.facultyId==0}">
													Yes <input type="radio" checked name="is_state_same" id="is_state_same"	 value="1"> 
													No<input type="radio"  name="is_state_same" id="is_state_same" value="0">
													</c:if>
													
												<span class="error_form text-danger" id="is_state_same_field"
													style="display: none;">Please select
													permanent/correspondence address same or not.</span>

											</div>
										</div>
										<div class="form-group">
											<label class="control-label col-sm-2" for="page_order">Contact
												No <span class="text-danger">*</span>
											</label>
											<div class="col-sm-10">
												<input type="text" onchange="checkUnique(this.value,1)"
													onchange="trim(this)" maxlength="10" class="form-control"
													id="contact_no"
													onkeypress='return restrictAlphabets(event)'
													name="contact_no" placeholder="Mobile Number"
													value="${staff.contactNo}" autocomplete="off"> <span
													class="error_form text-danger" id="error_formfield4"
													style="display: none;">Please enter valid contact
													No.</span>
												<p class="desc text-danger fontsize11">Note: OTP will be
													sent on this mobile number for verification</p>
											</div>
										</div>

										<div class="form-group">
											<label class="control-label col-sm-2" for="page_order">Email
												ID (Official) <span class="text-danger">*</span>
											</label>
											<div class="col-sm-10">
												<input type="email" class="form-control" id="email"
													onchange="checkUnique(this.value,2)" name="email"
													placeholder="abc@xyz.com" value="${staff.email}"
													onchange="trim(this)" autocomplete="off"> <span
													class="error_form text-danger" id="error_formfield5"
													style="display: none;">Please enter valid email.</span>
												<p class="desc font-italic fontsize11">Note:
													Verification mail will be sent on this Email id</p>
											</div>
										</div>

										<div class="form-group">
											<div class="col-sm-offset-2 col-sm-10">
												<button type="submit" id="sub_button"
													class="btn btn-primary" onclick="submit_f(1)">
													<i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save
												</button>


												<a href="${pageContext.request.contextPath}/showStaffList"><button
														id="sub2" type="button" class="btn btn-primary">
														<i class="${sessionScope.cancelIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Cancel
													</button></a>
											</div>
										</div>

										<input type="hidden" id="acc_off_id" name="acc_off_id"
											value="${staff.facultyId}"> <input type="hidden"
											id="is_view" name="is_view" value="0">

										<div class="clearfix"></div>

									</form>
									<p class="desc text-danger fontsize11">Notice : * Fields
										are mandatory.</p>
								</div>

							</div>

						</div>
					</section>
				</div>

			</section>
		</section>

	</div>
	<!-- MAIN CONTENT AREA ENDS -->

	<!-- END CONTENT -->
	<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
	<script>
		function trim(el) {
			el.value = el.value.replace(/(^\s*)|(\s*$)/gi, ""). // removes leading and trailing spaces
			replace(/[ ]{2,}/gi, " "). // replaces multiple spaces with one space 
			replace(/\n +/, "\n"); // Removes spaces after newlines
			return;
		}

		function validateEmail(email) {

			var eml = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;

			if (eml.test($.trim(email)) == false) {

				return false;

			}

			return true;

		}
		function validateMobile(mobile) {
			var mob = /^[1-9]{1}[0-9]{9}$/;

			if (mob.test($.trim(mobile)) == false) {

				//alert("Please enter a valid email address .");
				return false;

			}
			return true;

		}
		$(document)
				.ready(
						function($) {

							$("#form_sample_2")
									.submit(
											function(e) {

												var isError = false;
												var errMsg = "";

												if (!$("#faculty_first_name")
														.val()) {

													isError = true;
													errMsg += '<li>Please enter a valid name.</li>';

													$("#faculty_first_name")
															.addClass(
																	"has-error")
													$("#error_formfield1")
															.show()
													//return false;
												} else {
													$("#error_formfield1")
															.hide()
												}

												if (!$("#dateOfJoin").val()) {

													isError = true;
													errMsg += '<li>Please enter a valid name.</li>';

													$("#dateOfJoin").addClass(
															"has-error")
													$("#error_formfield2")
															.show()
													//return false;
												} else {
													$("#error_formfield2")
															.hide()
												}

												var radioValue = $(
														"input[name='is_registration']:checked")
														.val();
												//alert(radioValue);
												if (radioValue == 0) {

													if (!$("#relDate").val()) {
														isError = true;
														errMsg += '<li>Please enter a valid name.</li>';

														$("#relDate").addClass(
																"has-error1")
														$("#error_formfield3")
																.show()
														//return false;
													} else {
														$("#error_formfield3")
																.hide()
													}
												}
												/* if ( $("#radioValue").is(":checked")) {
													$("#error_formfield3").hide()
												} */

												if (!$("#contact_no").val()
														|| !validateMobile($(
																"#contact_no")
																.val())) {

													isError = true;
													errMsg += '<li>Please enter a valid email address.</li>';
													errMsg_alert = 'Please enter a valid mobile number.';
													$("#error_formfield4")
															.html(errMsg_alert);
													$("#error_formfield4")
															.show();
													//alert();
													//return false;
												} else {
													$("#error_formfield4")
															.html(
																	"Please enter mobile")
													$("#error_formfield4")
															.hide()
												}

												if (!$("#email").val()
														|| !validateEmail($(
																"#email").val())) {

													isError = true;
													errMsg += '<li>Please enter a valid email address.</li>';
													errMsg_alert += 'Please enter a valid email address. \n';
													$("#error_formfield5")
															.show()
													//return fregister_useralse;
												} else {
													$("#error_formfield5")
															.hide()
												}

												if (!$(
														"#yr_highest_qualification_acqrd")
														.val()) {

													isError = true;
													errMsg += '<li>Please enter a valid name.</li>';

													$(
															"#yr_highest_qualification_acqrd")
															.addClass(
																	"has-error")
													$("#error_formfield0")
															.show()
													//return false;
												} else {
													$("#error_formfield0")
															.hide()
												}

												if (!isError) {

													var x = confirm("Do you really want to submit the form?");
													if (x == true) {

														document
																.getElementById("sub_button").disabled = true;
														document
																.getElementById("sub2").disabled = true;
														return true;
													}
												}
												return false;
											});
						});
	</script>

	<script type="text/javascript">
		/*code: 48-57 Numbers
		  8  - Backspace,
		  35 - home key, 36 - End key
		  37-40: Arrow keys, 46 - Delete key*/
		function restrictAlphabets(e) {
			var x = e.which || e.keycode;
			if ((x >= 48 && x <= 57) || x == 8 || (x >= 35 && x <= 40)
					|| x == 46)
				return true;
			else
				return false;
		}
	</script>

	<script type="text/javascript">
		var wasSubmitted = false;
		function checkBeforeSubmit() {
			if (!wasSubmitted) {
				var x = confirm("Do you really want to submit the form?");
				if (x == true) {
					wasSubmitted = true;
					document.getElementById("sub1").disabled = true;
					document.getElementById("sub2").disabled = true;

					return wasSubmitted;
				}
			}
			return false;
		}
	</script>


	<script type="text/javascript">
		/* $(function() {

			$('.datepicker').datepicker({
				autoclose : true,
				format : "dd-mm-yyyy",
				changeYear : true,
				changeMonth : true

			});
		}); */

		$(function() {

			$('.datepickeryear').datepicker({
				autoclose : true,
				minViewMode : 2,
				format : 'yyyy'

			});
		});
	</script>

	<script>
		function showDiv(value) {

			if (value == 0) {
				//alert(value);
				document.getElementById("abc").style.display = "block";
			} else {
				//alert(value);
				document.getElementById("abc").style.display = "none";
			}
		}

		function submit_f(view) {
			document.getElementById("is_view").value = view;//create this 

		}
	</script>
	<script type="text/javascript">
		function checkUnique(inputValue, valueType) {
			//alert("hi");

			document.getElementById("sub2").disabled = false;

			var valid = false;
			if (valueType == 1) {
				//alert("Its Mob no");
				if (inputValue.length == 10) {
					valid = true;
					//alert("Len 10")
				} else {
					//alert("Not 10");
				}
			} else if (valueType == 2) {
				//alert("Its Email " );

				var mailformat = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
				if (inputValue.match(mailformat)) {
					valid = true;
					//alert("Valid Email Id");
				} else {
					valid = false;
					//alert("InValid Email Id");
				}
			}
			if (valid == true)

				$
						.getJSON(
								'${getUserInfo}',
								{
									inputValue : inputValue,
									valueType : valueType,
									ajax : 'true',

								},
								function(data) {
									//alert("data" + data);

									//alert("Data  " +JSON.stringify(data));
									if(data.facultyId>0){
										document.getElementById("faculty_id").value = data.facultyId;
										document.getElementById("faculty_first_name").value = data.facultyFirstName;
										document.getElementById("yr_highest_qualification_acqrd").value = data.hightestQualificationYear;
										
										
										document.getElementById("dateOfJoin").value = data.joiningDate;
										document.getElementById("relDate").value = data.realivingDate;
										document.getElementById("email").value = data.email;
										document.getElementById("contactNo").value = data.contactNo;
										
									/* document.getElementById("hod_quolf").value=data.highestQualification;
									$("#hod_quolf").trigger("chosen:updated");
										
									document.getElementById("designation").options.selectedIndex = data.currentDesignationId;
									$("#designation").trigger("chosen:updated");
									var temp = new Array();

									temp = (data.deptId).split(",");
									//alert(temp);
									$("#dept").val(temp);
									$("#dept").trigger("chosen:updated"); */
								
									document.getElementById("designation").value=data.currentDesignationId;
									$("#designation").trigger("chosen:updated");
									//single select
									document.getElementById("hod_quolf").value=data.highestQualification;
									$("#hod_quolf").trigger("chosen:updated");
									//multiple select
								 	var temp = new Array();
								 	temp = (data.deptId).split(",");
									  $('#dept').val(temp);
									  $('#dept').trigger('change'); // Notify any JS components that the value changed
}else{
	/* document.getElementById("faculty_id").value = 0;
	document.getElementById("faculty_first_name").value = "";
	document.getElementById("yr_highest_qualification_acqrd").value = "";
	document.getElementById("dateOfJoin").value = "";
	document.getElementById("email").value = "";
	document.getElementById("contactNo").value = "";
	document.getElementById("dept").value = 0;
	document.getElementById("hod_quolf").value = 0;
	document.getElementById("designation").value = 0; */
	
}

								});
		}

		function submit_f(view) {
			document.getElementById("is_view").value = view;//create this 
			/* var form=document.getElementById("form_sample_2");
			form.setAttribute("method", "post");

			form.action=("insertHod");
			var x =confirm("Do you really want to submit the form?");
			if(x==true)
			form.submit(); */

		}

		/* function showForm() {
			//document.getElementById("abc").style = "display:none"
				var qualType=document.getElementById("qualification").value
			//alert("qualType::"+qualType);
				
				if (qualType == 5) {

					document.getElementById("abc").style = "visible"
					
						
				} 
				else{
					document.getElementById("abc").style = "display:none"
				}
			
			} */
		/* function hideText() {
			//alert("hii");
			document.getElementById("abc").style = "display:none"
				
			
			} */
	</script>
	<script type="text/javascript">
		function setDate(value) {
			///alert("Value " +value)
			if (value == 1) {
				//alert(value)
				//document.getElementById("relDate").removeAttribute("required");
				document.getElementById("abc").style.display = "none";

				//alert(value)
			} else {
				//alert(value)
				//document.getElementById("relDate").setAttribute("required","true");
				document.getElementById("abc").style.display = "block";

				//alert(value)

			}

		}
	</script>
	<script type="text/javascript">
		function showReforms3(b) {

			if (b == 1) {
				document.getElementById("rel_date1").style = "display:none"

			} else {

				document.getElementById("rel_date1").style = "visible"
				//document.getElementById("rel_date").required = true;

			}
		}

		function showForm() {
			//document.getElementById("abc").style = "display:none"
			var qualType = document.getElementById("teachTo").value
			//alert("qualType::"+qualType);

			if (qualType == 5) {

				document.getElementById("abc").style = "visible"

			} else {
				document.getElementById("abc").style = "display:none"
			}

		}

		function showForm1() {
			//document.getElementById("abc").style = "display:none"
			var qualType = document.getElementById("deptId").value
			//alert("qualType::"+qualType);

			if (qualType == 3) {

				document.getElementById("pqr").style = "visible"

			} else {
				document.getElementById("pqr").style = "display:none"
			}

		}
		function hideText() {
			//alert("hii");
			document.getElementById("abc").style = "display:none"

			document.getElementById("pqr").style = "display:none"

		}
	</script>

	<script type="text/javascript">
		function showIsReg() {
			//alert("Hi");
			var x = $
			{
				staff.facultyId
			}
			;

			if (x > 0) {
				//alert("Hi 1")
				var isRel = $
				{
					staff.realivingDate
				}
				;
				//alert("Is Reg " +isReg);
				if (isRel == null) {
					//alert("Hi 2")
					document.getElementById("abc").style.display = "none";

				} else {
					//alert("Hi es")
					document.getElementById("abc").style.display = "block";

				}

			}

			var isTaxInc = $("input[name=is_registration]:checked").val()

			if (isTaxInc == 1) {
				//alert("Hi 2")
				document.getElementById("abc").style.display = "none";

			} else {
				//alert("Hi es")
				document.getElementById("abc").style.display = "block";

			}

		}
	</script>

</body>
</html>