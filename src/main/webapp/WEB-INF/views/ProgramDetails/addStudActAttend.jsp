<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 
<%@ page import="java.util.UUID"%>
<%@ page import="java.security.MessageDigest"%>
<%@ page import="java.math.BigInteger"%>

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
<body class=" " onload="hideText()">
	<c:url value="/checkUniqueField" var="checkUniqueField"></c:url>
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

				<%-- 	<div class="col-xs-12">
					<div class="page-title">

						<div class="pull-left">
							<!-- PAGE HEADING TAG - START -->
							<h1 class="title">${title}</h1>
							<!-- PAGE HEADING TAG - END -->
						</div>


					</div>
				</div> --%>
				<div class="clearfix"></div>
				<!-- MAIN CONTENT AREA STARTS -->

				<div class="col-lg-12"></div>



				<div class="col-lg-12">
					<section class="box ">

						<header class="panel_header">
							<h2 class="title pull-left">${title}</h2>

							<div class="actions panel_actions pull-right">
							<%-- 	<a
									href="${pageContext.request.contextPath}/showStudAttendActivity"><button
										type="button" class="btn btn-info">Back</button></a> --%>
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/submitAttendedActivity"
										method="post" name="submitAcitivity" 
										name="form_sample_2" id="form_sample_2">



<%
												UUID uuid = UUID.randomUUID();
													MessageDigest md = MessageDigest.getInstance("MD5");
													byte[] messageDigest = md.digest(String.valueOf(uuid).getBytes());
													BigInteger number = new BigInteger(1, messageDigest);
													String hashtext = number.toString(16);
													session = request.getSession();
													session.setAttribute("generatedKey", hashtext);
											%>
											<input type="hidden" value="<%out.println(hashtext);%>"
												name="token" id="token">
										<!-- <ul class="nav nav-tabs">
											<li class="active"><a href="#home" data-toggle="tab">
													<i class="fa fa-home"></i> Activity
											</a></li>


										</ul> -->

										<!-- <div class="tab-content">
											<div class="tab-pane fade in active" id="home"> -->

										<div>

											<c:set value="0" var="chooseOther"></c:set>

											<div class="col-xs-12">



												<div class="form-group">
													<label class="control-label col-sm-2" for="activityName">Type
														of Activity <span class="text-danger">*</span>
													</label>
													<div class="col-sm-10">

														<c:choose>
															<c:when test="${editProgramActivity.studentActivityId>0}">

																<select id="activityName" name="activityName"
																	class="form-control" onchange="showExtraField()"
																	required>

																	<c:choose>
																		<c:when
																			test="${editProgramActivity.activityName eq 'Sports'}">

																			<option value="Sports" selected>Sports</option>
																			<option value="Cultural">Cultural</option>
																			<option value="Curricular">Curricular</option>
																			<option value="Co-curricular">Co-curricular</option>
																			<option value="Extra Curricular ">Extra
																				Curricular</option>
																			<option value="7">Other Competition</option>

																		</c:when>
																		<c:when
																			test="${editProgramActivity.activityName eq 'Cultural'}">

																			<option value="Sports">Sports</option>
																			<option value="Cultural" selected>Cultural</option>
																			<option value="Curricular">Curricular</option>
																			<option value="Co-curricular">Co-curricular</option>
																			<option value="Extra Curricular ">Extra
																				Curricular</option>
																			<option value="7">Other Competition</option>

																		</c:when>
																		<c:when
																			test="${editProgramActivity.activityName eq 'Curricular'}">

																			<option value="Sports">Sports</option>
																			<option value="Cultural">Cultural</option>
																			<option value="Curricular" selected>Curricular</option>
																			<option value="Co-curricular">Co-curricular</option>
																			<option value="Extra Curricular ">Extra
																				Curricular</option>
																			<option value="7">Other Competition</option>

																		</c:when>
																		<c:when
																			test="${editProgramActivity.activityName eq 'Co-curricular'}">

																			<option value="Sports">Sports</option>
																			<option value="Cultural">Cultural</option>
																			<option value="Curricular">Curricular</option>
																			<option value="Co-curricular" selected>Co-curricular</option>
																			<option value="Extra Curricular ">Extra
																				Curricular</option>
																			<option value="7">Other Competition</option>

																		</c:when>
																		<c:when
																			test="${editProgramActivity.activityName eq 'Extra Curricular'}">

																			<option value="Sports">Sports</option>
																			<option value="Cultural">Cultural</option>
																			<option value="Curricular">Curricular</option>
																			<option value="Co-curricular">Co-curricular</option>
																			<option value="Extra Curricular" selected>Extra
																				Curricular</option>
																			<option value="7">Other Competition</option>

																		</c:when>
																		<c:otherwise>

																			<option value="Sports">Sports</option>
																			<option value="Cultural">Cultural</option>
																			<option value="Curricular">Curricular</option>
																			<option value="Co-curricular">Co-curricular</option>
																			<option value="Extra Curricular">Extra
																				Curricular</option>
																			<option value="7" selected>Other Competition</option>
																			<c:set value="1" var="chooseOther"></c:set>
																		</c:otherwise>
																	</c:choose>



																</select>

															</c:when>
															
															
															<c:otherwise>
																<select id="activityName" name="activityName"
																	class="form-control" onchange="showExtraField()"
																	required>

																	<option value="Sports">Sports</option>
																	<option value="Cultural">Cultural</option>
																	<option value="Curricular">Curricular</option>
																	<option value="Co-curricular">Co-curricular</option>
																	<option value="Extra Curricular ">Extra
																		Curricular</option>
																	<option value="7">Other Competition</option>
																	<c:set value="0" var="chooseOther"></c:set>
																</select>

															</c:otherwise>
														</c:choose>



													</div>
												</div>


												<c:choose>
													<c:when test="${chooseOther==1}">

														<div class="form-group" id="abc">
															<label class="control-label col-sm-2"
																for="otherActivityName"> Another Activity <span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<input type="text" maxlength="50" class="form-control"
																	id="otherActivityName"
																	value="${editProgramActivity.activityName}"
																	name="otherActivityName" onchange="return trim(this)"
																	placeholder="Another Scheme Name"
																	onchange="return trim(this)" required>
															</div>
														</div>

													</c:when>
													<c:otherwise>
														<div class="form-group" id="abc">
															<label class="control-label col-sm-2"
																for="otherActivityName"> Another Activity <span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<input type="text" maxlength="50" class="form-control"
																	id="otherActivityName" onchange="return trim(this)"
																	value="${editProgramActivity.activityName}"
																	name="otherActivityName"
																	placeholder="Another Scheme Name"
																	onchange="return trim(this)">
															</div>
														</div>
													</c:otherwise>
												</c:choose>

												<div class="form-group">
													<label class="control-label col-sm-2" for="branch">Name
														of Activity<span class="text-danger"></span>
													</label>
													<div class="col-sm-10">
														<input type="text" maxlength="200" class="form-control"
															id="activity_name" value="${editProgramActivity.rawActivityName}"
															name="activity_name" placeholder="Name of Activity" 
															onchange="return trim(this)" autocomplete="off">
															
													
													</div>
												</div>

												<div class="form-group">
													<label class="control-label col-sm-2" for="date">Date
														<span class="text-danger">*</span>
													</label>
													<div class="col-sm-10">
														<input type="text" class="form-control datepicker" onchange="return trim(this)"
														data-format="dd-mm-yyyy"	id="date" value="${editProgramActivity.date}"
															placeholder="dd-mm-yyyy" name="date"  onkeypress='return restrictAlphabets(event)'
															autocomplete="off">
															<span
														class="error_form text-danger" id="error_formfield1"
														style="display: none;">Please enter date.</span>
													</div>
												</div>

											<div class="form-group">
													<label class="control-label col-sm-2" for="year">Venue
														<span class="text-danger">*</span>
													</label>
													<div class="col-sm-10">
														<input type="text" class="form-control" onchange="return trim(this)"
															id="venue" value="${editProgramActivity.venue}" name="venue"
															placeholder="Venue" autocomplete="off">
															<span
														class="error_form text-danger" id="error_formfield_venue"
														style="display: none;">Please enter venue.</span>
													</div>
												</div> 
												
											<div class="form-group">
													<label class="control-label col-sm-2" for="year">Year
														<span class="text-danger">*</span>
													</label>
													<div class="col-sm-10">
														<select id="year" name="year" class="form-control">
																
							<option  ${editProgramActivity.year == 'First Year'  ? 'Selected': '' }>First Year</option>
							<option  ${editProgramActivity.year == 'Second Year' ? 'Selected': '' }>Second Year</option>
							<option  ${editProgramActivity.year == 'Third Year'  ? 'Selected': '' }>Third Year</option>
							<option  ${editProgramActivity.year == 'Fourth Year' ? 'Selected': '' }>Fourth Year</option>
							<option  ${editProgramActivity.year == 'Fifth Year'  ? 'Selected': '' }>Fifth Year</option>
																
																
														</select>
														<span class="error_form text-danger" id="prog_type_field"
															style="display: none;">Please select program type</span>
													</div>
												</div>									
												
												
												
												


												<div class="form-group">
													<label class="control-label col-sm-2" for="branch">Branch
														(Discipline)<span class="text-danger">*</span>
													</label>
													<div class="col-sm-10">
														<input type="text" maxlength="30" class="form-control"
															id="branch" value="${editProgramActivity.branch}"
															name="branch" placeholder="Branch (Discipline)"
															onchange="return trim(this)" autocomplete="off">
															<span
														class="error_form text-danger" id="error_formfield3"
														style="display: none;">Please enter branch.</span>
													</div>
												</div>

												<div class="form-group">
													<label class="control-label col-sm-2" for="noStudent">
														No. of Students Attended <span class="text-danger">*</span>
													</label>
													<div class="col-sm-10">
														<input type="text" class="form-control" id="noStudent"
															value="${editProgramActivity.participatedStudent}"
															name="noStudent" onkeypress='return restrictAlphabets(event)'
															placeholder="No. of Students Attended" pattern="\d*"
															 maxlength="6" autocomplete="off">
															<span
														class="error_form text-danger" id="error_formfield4"
														style="display: none;">Please enter No. of student attended.</span>
													</div>
												</div>



												<div class="form-group">
													<label class="control-label col-sm-2" for="level">Level
														of Activity <span class="text-danger">*</span>
													</label>
													<div class="col-sm-10">
														<select id="level" name="level" class="form-control"
															onchange="showTextField()" >
															<option disabled value="">Select Level</option>
															<option value="International" ${editProgramActivity.level eq 'International' ? 'selected' : ''}>International</option>
															<option value="National" ${editProgramActivity.level eq 'National' ? 'selected' : ''}>National</option>
															<option value="Regional" ${editProgramActivity.level eq 'Regional' ? 'selected' : ''}>Regional</option>
															<option value="State" ${editProgramActivity.level eq 'State' ? 'selected' : ''}>State</option>
													</select>

										<span class="error_form text-danger" id="error_level"
														style="display: none;">Please select level.</span>
													</div>
												</div>
												
												<div class="form-group" id="txtHid" style="display: none;">
													<div class="form-group">
															<label class="control-label col-sm-2" for="otherActivityName"> Name of Student <span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<input type="text" maxlength="50" class="form-control"	id="stud_name"	value="${editProgramActivity.exVar1}"
																	name="stud_name" placeholder="Name of Student" onchange="return trim(this)" autocomplete="off">
															
															<span class="error_form text-danger" id="error_studName"
																		style="display: none;">Please enter name of student.</span>
															</div>
													</div>
													
													<div class="form-group">
															<label class="control-label col-sm-2" for="otherActivityName"> Aadhaar No./ Student ID No. <span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<input type="text" maxlength="12" class="form-control"	id="adhar_no"	value="${editProgramActivity.exVar2}"
																	name="adhar_no" placeholder="Aadhaar No./ Student ID No." onchange="return trim(this)" autocomplete="off">
															
															<span class="error_form text-danger" id="error_aadhaarNo"
																			style="display: none;">Please Aadhaar No./ Student ID No.</span>
															</div>
													</div>
													
													<div class="form-group">
															<label class="control-label col-sm-2" for="otherActivityName"> Name of Award/Medal <span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<input type="text" maxlength="50" class="form-control"	id="name_award"	value="${editProgramActivity.aadharNo}"
																	name="name_award" placeholder="Name of Award/Medal" onchange="return trim(this)" autocomplete="off">
															
															<span class="error_form text-danger" id="error_madel"
																		style="display: none;">Please enter name of award/medal.</span>
															</div>
													</div>
											</div>

											</div>

										</div>




										<c:choose>
											<c:when test="${editProgramActivity.studentActivityId>0}">
												<input type="hidden" id="activityId" name="activityId"
													value="${editProgramActivity.studentActivityId}">
											</c:when>
											<c:otherwise>
												<input type="hidden" id="activityId" name="activityId"
													value="0">
											</c:otherwise>
										</c:choose>
										<input type="hidden" id="is_view" name="is_view" value="0">
										<input type="hidden" id="isOther" name="isOther"
											value="${chooseOther}">
											
										<div class="form-group">
													<div class="col-sm-offset-3 col-sm-9">


<button type="submit" id="sub_button" class="btn btn-primary"
													onclick="submit_f(1)"><i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save</button>
														
<a href="${pageContext.request.contextPath}/showStudAttendActivity"><button id="sub2"
										type="button" class="btn btn-primary"><i class="${sessionScope.cancelIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Cancel</button></a>													</div>
												</div>


										<div class="clearfix"></div>

										<!-- </div>

										</div> -->

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
	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
	<script type="text/javascript" src="./javascript.js"></script>
	<script
		src="http://maps.googleapis.com/maps/api/js?key=YOUR_APIKEY&sensor=false"></script>
		
		<script type="text/javascript">
		$('#noStudent').on('input', function() {
			  this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');
			});
		
		$('#date').on('input', function() {
			  this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');
			});
		
function clearDefault(a){
	if(a.defaultValue==0)
	{
		a.value=""
	}
	};
</script>
	<script type="text/javascript">
			/*code: 48-57 Numbers
			  8  - Backspace,
			  35 - home key, 36 - End key
			  37-40: Arrow keys, 46 - Delete key*/
			function restrictAlphabets(e){
				var x=e.which||e.keycode;
				if((x>=48 && x<=57) || x==8 ||
					(x>=35 && x<=40)|| x==46)
					return true;
				else
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
	
		<script type="text/javascript">
		function submit_f(view) {
			//alert(view);
			document.getElementById("is_view").value = view;

		}
	</script>

	<script type="text/javascript">
	function showTextField(){
		
		var field = document.getElementById("level").value
		
		if(field == 'International' || field == 'National'){
			
		document.getElementById("txtHid").style = "visible";
	
	} else {
		document.getElementById("txtHid").style = "display:none";
		
	}
	}
	
		function showExtraField() {
			//alert("hii");
			//document.getElementById("abc").style = "display:none"
			var qualType = document.getElementById("activityName").value
			//alert("qualType::"+qualType);

			if (qualType == 7) {

				document.getElementById("abc").style = "visible";
				document.getElementById("otherActivityName").required = true;

			} else {
				document.getElementById("abc").style = "display:none";
				document.getElementById("otherActivityName").required = false;
			}

		}

		function hideText() {
			showTextField();
			var isOther = document.getElementById("isOther").value;

			if (isOther == 1) {
				document.getElementById("abc").style = "visible";
			} else {
				document.getElementById("abc").style = "display:none"
			}

		}
	</script>

	<script type="text/javascript">
		$(function() {

			$('.datepickeryear').datepicker({
				autoclose : true,
				minViewMode : 2,
				format : 'yyyy'

			});
		});
		function trim(el) {
			el.value = el.value.replace(/(^\s*)|(\s*$)/gi, ""). // removes leading and trailing spaces
			replace(/[ ]{2,}/gi, " "). // replaces multiple spaces with one space 
			replace(/\n +/, "\n"); // Removes spaces after newlines
			return;
		}
	</script>
	<script type="text/javascript">
		var wasSubmitted = false;
		function checkBeforeSubmit() {
			if (!wasSubmitted) {
				var x = confirm("Do you really want to submit the form?");
				if (x == true) {
					wasSubmitted = true;
					document.getElementById("svebtn").disabled = true;
					document.getElementById("svebtnnext").disabled = true;
					return wasSubmitted;
				}
			}
			return false;
		}
	</script>

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

												if (!$("#date")
														.val()) {

													isError = true;
													errMsg += '<li>Please enter a valid name.</li>';

													$("#date")
															.addClass(
																	"has-error")
													$("#error_formfield1")
															.show()
													//return false;
												} else {
													$("#error_formfield1")
															.hide()
												}
												if (!$("#venue")
														.val()) {

													isError = true;
													errMsg += '<li>Please enter a valid name.</li>';

													$("#venue")
															.addClass(
																	"has-error")
													$("#error_formfield_venue")
															.show()
													//return false;
												} else {
													$("#error_formfield_venue")
															.hide()
												}

												if (!$("#year").val()) {

													isError = true;
													errMsg += '<li>Please enter a valid name.</li>';

													$("#year").addClass(
															"has-error")
													$("#error_formfield2")
															.show()
													//return false;
												} else {
													$("#error_formfield2")
															.hide()
												}

												 
												if (!$(
														"#branch")
														.val()) {

													isError = true;
													errMsg += '<li>Please enter a valid name.</li>';

													$(
															"#branch")
															.addClass(
																	"has-error")
													$("#error_formfield3")
															.show()
													//return false;
												} else {
													$("#error_formfield3")
															.hide()
												}

												if (!$(
												"#noStudent")
												.val()) {

											isError = true;
											errMsg += '<li>Please enter a valid name.</li>';

											$(
													"#noStudent")
													.addClass(
															"has-error")
											$("#error_formfield4")
													.show()
											//return false;
										} else {
											$("#error_formfield4")
													.hide()
										}
												
												
												if (!$(
												"#level")
												.val()) {

											isError = true;
											errMsg += '<li>Please enter a valid name.</li>';

											$(
													"#level")
													.addClass(
															"has-error")
											$("#error_level")
													.show()
											//return false;
										} else {
											$("#error_level")
													.hide()
										}
												
										var lvl = document.getElementById("level").value
									
										if(lvl == 'International' || lvl == 'National'){
											if (!$(
											"#stud_name")
											.val()) {

										isError = true;
										

										$(
												"#stud_name")
												.addClass(
														"has-error")
										$("#error_studName")
												.show()
										//return false;
									} else {
										$("#error_studName")
												.hide()
									}
											
											
											if (!$(
											"#adhar_no")
											.val()) {

										isError = true;
										

										$(
												"#adhar_no")
												.addClass(
														"has-error")
										$("#error_aadhaarNo")
												.show()
										//return false;
									} else {
										$("#error_aadhaarNo")
												.hide()
									}
										
											
											
											if (!$(
											"#name_award")
											.val()) {

										isError = true;
										

										$(
												"#name_award")
												.addClass(
														"has-error")
										$("#error_madel")
												.show()
										//return false;
									} else {
										$("#error_madel")
												.hide()
									}
											
											
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
	

	<!-- END CONTAINER -->
	<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->

	<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>



</body>
</html>