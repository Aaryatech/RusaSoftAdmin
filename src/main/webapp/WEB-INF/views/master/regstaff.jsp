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
	<c:url value="/chkFields" var="chkFields"></c:url>
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

							<div class="actions panel_actions pull-right">
								<a href="${pageContext.request.contextPath}/showStaffList"><button
										type="button" class="btn btn-info">Back</button></a>
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/addFaculty"
										method="post" name="form_sample_2" id="form_sample_2"
										onsubmit="return checkBeforeSubmit()">



										<input type="hidden" id="faculty_id" name="faculty_id"
											value="${staff.facultyId}">

										<div class="form-group">
											<label class="control-label col-sm-2" for="page_name">Faculty
												Name <span class="text-danger">*</span>
											</label>
											<div class="col-sm-10">
												<input type="text" class="form-control"
													id="faculty_first_name" pattern="^(?!\s*$).+"
													name="faculty_first_name"
													placeholder="First Name			Middle Name			 Last Name"
													value="${staff.facultyFirstName}" required="required"
													autocomplete="off">
											</div>
										</div>


										<div class="form-group">
											<label class="control-label col-sm-2" for="status">Department
												: <span class="text-danger">*</span>
											</label>
											<div class="col-sm-10">
												<select id="dept" name="dept" class="form-control" required>
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
											<label class="control-label col-sm-2" for="status">Qualification
												: <span class="text-danger">*</span>
											</label>
											<div class="col-sm-10">
												<select id="hod_quolf" name="hod_quolf" class="form-control"
													required>
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
											<label class="control-label col-sm-2" for="page_order">Year
												of highest Qualification Acquired <span class="text-danger">*</span>
											</label>

											<div class="col-sm-10">
												<select id="yr_highest_qualification_acqrd"
													name="yr_highest_qualification_acqrd" class="form-control"
													required>

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
										</div>

										<div class="form-group">
											<label class="control-label col-sm-2" for="status">Current
												Designation <span class="text-danger">*</span>
											</label>
											<div class="col-sm-10">
												<select id="designation" name="designation"
													class="form-control" required>
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
													id="dateOfJoin"
													onkeypress='return restrictAlphabets(event)'
													pattern="^(?!\s*$).+" name="join_date" autocomplete="off"
													placeholder="dd/mm/yyyy" value="${staff.joiningDate}"
													required>
											</div>

											<label class="control-label col-sm-2" for="page_order">Is
												Working Today<span class="text-danger">*</span>
											</label>

											<div class="col-sm-2">
												<c:choose>
													<c:when test="${staff.facultyId==0}">

														<input type="radio" id="is_registration"
															name="is_registration" value="1" checked
															onclick="setDate(this.value)">Yes 
																<input type="radio" id="is_registration"
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
														pattern="^(?!\s*$).+"
														onkeypress='return restrictAlphabets(event)'
														name="acc_off_relDate" autocomplete="off"
														placeholder="dd/mm/yyyy">
												</div>
											</div>
										</div>
										<!-- <div class="form-group" id="rel_date1">
															<label class="control-label col-sm-2" for="page_order">Relieving
																Date <span class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<input type="date" class="form-control" id="rel_date"
																	name="rel_date">
															</div>
														</div> -->

										<div class="form-group">
											<label class="control-label col-sm-2" for="status">Teaching
												to <span class="text-danger">*</span>
											</label>
											<div class="col-sm-10">
												<select id="teachTo" name="teachTo" class="form-control"
													required>
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

										<!-- <div class="form-group" id="abc">
															<label class="control-label col-sm-2" for="smallheading">Other Course
														  <span class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<input type="text" class="form-control" id="other_course"
																	name="other_course" 
																	
																	placeholder="Other Designation" value="">
															</div>
														</div> -->

										<div class="form-group">
											<label class="control-label col-sm-2" for="page_order">Contact
												No <span class="text-danger">*</span>
											</label>
											<div class="col-sm-10">
												<input type="text" pattern="^[1-9]{1}[0-9]{9}$"
													onchange="checkUnique(this.value,1)" pattern="^(?!\s*$).+"
													maxlength="10" class="form-control" id="contact_no"
													onkeypress='return restrictAlphabets(event)'
													name="contact_no" placeholder="Mobile Number"
													value="${staff.contactNo}" autocomplete="off" required>
												<p class="desc text-danger fontsize11">Note: OTP will be
													sent on this mobile number for verification</p>
											</div>
										</div>

										<div class="form-group">
											<label class="control-label col-sm-2" for="page_order">Email
												ID(Official) <span class="text-danger">*</span>
											</label>
											<div class="col-sm-10">
												<input type="email" class="form-control" id="email"
													onchange="checkUnique(this.value,2)" name="email"
													placeholder="abc@xyz.com" value="${staff.email}"
													pattern="^(?!\s*$).+" autocomplete="off" required>
												<p class="desc font-italic fontsize11">Note:
													Verification mail will be sent on this Email id</p>
											</div>
										</div>





										<%-- <div class="form-group">
															<div class="col-sm-offset-2 col-sm-10">
																<input type="submit" class="btn btn-primary" value="Add">
																	<a href="${pageContext.request.contextPath}/showStaffList"><button
										                              type="button" class="btn btn-primary">Save & Next</button></a>
																<button type="reset" class="btn btn-default">Reset</button>
															</div>
														</div> --%>

										<div class="form-group">
											<div class="col-sm-offset-2 col-sm-10">
												<input type="submit" id="sub1" class="btn btn-primary"
													onclick="submit_f(1)" value="Save"> <input
													type="submit" id="sub2" class="btn btn-primary"
													onclick="submit_f(0)" value="Save &
																		Next">
												<button type="reset" class="btn btn-default">Reset</button>
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
		$(function() {

			$('.datepicker').datepicker({
				autoclose : true,
				format : "dd-mm-yyyy",
				changeYear : true,
				changeMonth : true

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
			document.getElementById("sub1").disabled = false;
			document.getElementById("sub2").disabled = false;

			//alert(inputValue+" "+valueType);

			var primaryKey = ${staff.facultyId};
			
			//alert("Primary key"+primaryKey);
			var isEdit = 0;
			if (primaryKey > 0) {
				isEdit = 1;
			}
			//alert("Is Edit " +isEdit);

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

				var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
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
								'${chkFields}',
								{

									inputValue : inputValue,
									valueType : valueType,
									primaryKey : primaryKey,
									isEdit : isEdit,
									tableId : 2,

									ajax : 'true',

								},
								function(data) {

									//alert("Data  " +JSON.stringify(data));
									if (data.error == true) {
										if (valueType == 2) {
											document.getElementById("email").value = "";
											alert("This email id already exist in system please enter unique");
										} else {
											document
													.getElementById("contact_no").value = "";
											alert("This contact no  already exist in system please enter unique");
										}
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
				document.getElementById("relDate").removeAttribute("required");
				document.getElementById("abc").style.display = "none";

				//alert(value)
			} else {
				//alert(value)
				document.getElementById("relDate").setAttribute("required",
						"true");
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
				document.getElementById("rel_date").required = true;

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
			var x = ${staff.facultyId};

			if (x > 0) {
				//alert("Hi 1")
				var isRel = ${staff.realivingDate};
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
			 
			 if(isTaxInc==1){
					//alert("Hi 2")
					document.getElementById("abc").style.display = "none";

				}else{
					//alert("Hi es")
					document.getElementById("abc").style.display = "block";
					
				}

		}
	</script>



	<!-- END CONTAINER -->
	<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->

	<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>



</body>
</html>