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

				<div class="col-lg-12">
					<section class="box ">

						<header class="panel_header">

							<h2 class="title pull-left">${title}</h2>

							<%-- <div class="actions panel_actions pull-right">
								<a href="${pageContext.request.contextPath}/showLibraryBasicInfo"><button
										type="button" class="btn btn-info">Back</button></a>
							</div> --%>

						</header>

						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertLibInfo"
										method="post" name="form_sample_2" id="form_sample_2">
										<div>
											<div class="col-xs-12">

												<input type="hidden" value="${libInfo.libInfoId}"
													name="libInfoId">

												<div id="divshow">
													<div class="form-group">
														<label class="control-label col-sm-3" for="swName"
															style="text-align: left;">Name of Library
															Software<span class="text-danger">*</span>
														</label>
														<div class="col-sm-9">
															<input type="text" class="form-control" id="swName"
																name="swName" placeholder="Name of Software"
																value="${libInfo.softName}" onchange="trim(this)"
																autocomplete="off"> <span
																class="error_form text-danger" id="error_formfield1"
																style="display: none;">Please enter name of
																software.</span>
														</div>
													</div>

													<div class="form-group">
														<label class="control-label col-sm-3" for="version"
															style="text-align: left;">Software Version<span
															class="text-danger">*</span>
														</label>
														<div class="col-sm-9">
															<input type="text" class="form-control" id="version"
																name="version" placeholder="Software Version"
																value="${libInfo.softVersion}" onchange="trim(this)"
																autocomplete="off"> <span
																class="error_form text-danger" id="error_formfield2"
																style="display: none;">Please enter software
																version.</span>
														</div>
													</div>

													<div class="form-group">
														<label class="control-label col-sm-3" for="version"
															style="text-align: left;">Total Number of LMS
															Users <span class="text-danger">*</span>
														</label>
														<div class="col-sm-9">
															<input type="number" class="form-control" id="userLms"
																min="0" name="userLms" placeholder="" maxlength="7"
																onkeypress='return restrictAlphabets(event)'
																value="${libInfo.usersOfLms}" onchange="trim(this)"
																autocomplete="off"> <span
																class="error_form text-danger" id="error_formfield3"
																style="display: none;">Please enter total number
																of users of LMS and value must be greater than 0.</span>
														</div>
													</div>


													<div class="form-group">
														<label class="control-label col-sm-3" for="date"
															style="text-align: left;"> Date of
															Purchase/Automation Lib. <span class="text-danger">*</span>
														</label>
														<div class="col-sm-9">
															<input type="text" class="form-control datepicker"
																id="purchaseDate" name="purchaseDate"
																onkeypress='return restrictAlphabets(event)'
																onchange="trim(this)" autocomplete="off"
																placeholder="dd/mm/yyyy"
																value="${libInfo.dateOfPurchaseAutomation}"> <span
																class="error_form text-danger" id="error_formfield4"
																style="display: none;">Please enter date of
																purchase/automation Lib. </span>
														</div>
													</div>


													<div class="form-group">
														<label class="control-label col-sm-3" for="noOfComp"
															style="text-align: left;">No. of Comp in LAN with
															LMS <span class="text-danger">*</span>
														</label>
														<div class="col-sm-9">
															<input type="number" class="form-control" id="noOfComp"
																name="noOfComp" min="0"
																onkeypress='return restrictAlphabets(event)'
																placeholder="" value="${libInfo.noCompLan}"
																maxlength="7" onchange="trim(this)" autocomplete="off">
															<span class="error_form text-danger"
																id="error_formfield5" style="display: none;">Please
																enter No. of Comp in LAN and value must be greater than
																0. </span>
														</div>
													</div>

													<div class="form-group">
														<label class="control-label col-sm-3" for="bandwidth"
															style="text-align: left;">Bandwidth for Accessing
															E-Resources <span class="text-danger">*</span>
														</label>
														<div class="col-sm-9">
															<input type="text" class="form-control" id="bandwidth"
																name="bandwidth"
																placeholder="Bandwidth for Accessing E-Resources"
																value="${libInfo.bandwidthForAccessingEresources}"
																onchange="trim(this)" autocomplete="off"> <span
																class="error_form text-danger" id="error_formfield6"
																style="display: none;">Please enter bandwidth for
																accessing E-resources.</span>
														</div>
													</div>

													<div class="form-group">
														<label class="control-label col-sm-3" for="usingremot"
															style="text-align: left;"> E-Resources are
															remotely Accessible <span class="text-danger">*</span>
														</label>
														<div class="col-sm-9">

															<c:choose>
																<c:when test="${libInfo.isEresourceRemotly == 1}">
																	<input type="radio" id="usingremot" name="usingremot"
																		value="1" checked>Yes <input type="radio"
																		id="usingremot" name="usingremot" value="0">No
																</c:when>

																<c:when test="${libInfo.isEresourceRemotly == 0}">
																	<input type="radio" id="usingremot" name="usingremot"
																		value="1">Yes <input type="radio"
																		id="usingremot" name="usingremot" checked value="0">No
																</c:when>
															</c:choose>
														</div>
													</div>
												</div>

												<!-- 
														<div class="form-group">
															<label class="control-label col-sm-3" for="usingremot"
																style="text-align: left;"> e-Journals : <span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-2">
																<input type="radio" id="eJournals" name="eJournals"
																	value="1" checked>Yes <input type="radio"
																	id="eJournals" name="eJournals" value="0">No
															</div>
															<div class="col-sm-2">Membership Details</div>
															<div class="col-sm-3">

																<input type="text" class="form-control" id="bandwidth"
																	name="bandwidth" placeholder="Membership Details"
																	value="" required>

															</div>
														</div>

														<div class="form-group">
															<label class="control-label col-sm-3" for="usingremot"
																style="text-align: left;"> e-Shodh Sindhu : <span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-2">
																<input type="radio" id="shodhsindhu" name="shodhsindhu"
																	value="1" checked>Yes <input type="radio"
																	id="shodhsindhu" name="shodhsindhu" value="0">No
															</div>
															<div class="col-sm-2">Membership Details</div>
															<div class="col-sm-3">

																<input type="text" class="form-control" id="bandwidth"
																	name="bandwidth" placeholder="Membership Details"
																	value="" required>

															</div>
														</div>


														<div class="form-group">
															<label class="control-label col-sm-3" for="usingremot"
																style="text-align: left;"> Shodh Ganga
																Membership : <span class="text-danger">*</span>
															</label>
															<div class="col-sm-2">
																<input type="radio" id="gangaMembership"
																	name="gangaMembership" value="1" checked>Yes <input
																	type="radio" id="gangaMembership"
																	name="gangaMembership" value="0">No
															</div>
															<div class="col-sm-2">Membership Details</div>
															<div class="col-sm-3">

																<input type="text" class="form-control" id="bandwidth"
																	name="bandwidth" placeholder="Membership Details"
																	value="" required>

															</div>
														</div>

														<div class="form-group">
															<label class="control-label col-sm-3" for="usingremot"
																style="text-align: left;"> e-Books : <span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-2">
																<input type="radio" id="eBooks" name="eBooks" value="1"
																	checked>Yes <input type="radio" id="eBooks"
																	name="eBooks" value="0">No
															</div>
															<div class="col-sm-2">No. of e-Books</div>
															<div class="col-sm-3">

																<input type="text" class="form-control" id="bandwidth"
																	name="bandwidth" placeholder="No. of e-Books"
																	value="" required>

															</div>
														</div>

														<div class="form-group">
															<label class="control-label col-sm-3" for="usingremot"
																style="text-align: left;"> Database : <span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-2">
																<input type="radio" id="database" name="database"
																	value="1" checked>Yes <input type="radio"
																	id="database" name="database" value="0">No
															</div>
															<div class="col-sm-2">No. of Database</div>
															<div class="col-sm-3">

																<input type="text" class="form-control" id="bandwidth"
																	name="bandwidth" placeholder="No. of Database"
																	value="" required>

															</div>
														</div>
														 -->
												<div class="form-group">
													<label class="control-label col-sm-4" for="noOfComp"
														style="text-align: left;">Avg. No.of Teachers
														Using Library resources per day<span class="text-danger">*</span>
													</label>
													<div class="col-sm-8">
														<input type="number" class="form-control" id="avgTeacher"
															maxlength="4" name="avgTeacher" placeholder=""
															value="${libInfo.avgTeacher}" onchange="trim(this)"
															autocomplete="off" min="0"
															onkeypress='return restrictAlphabets(event)'> <span
															class="error_form text-danger" id="error_formfield7"
															style="display: none;">Please enter Avg. No.of
															teachers using library resources per day.</span>
													</div>
												</div>

												<div class="form-group">
													<label class="control-label col-sm-4" for="noOfComp"
														style="text-align: left;"> Avg. No. of Student
														Using Library resources per day<span class="text-danger">*</span>
													</label>
													<div class="col-sm-8">
														<input type="number" class="form-control" id="avgStud"
															name="avgStud" min="0"
															placeholder="Avg. No. of Student Using Library resources per day"
															value="${libInfo.avgTeacher}" maxlength="4"
															onchange="trim(this)" autocomplete="off"
															onkeypress='return restrictAlphabets(event)'> <span
															class="error_form text-danger" id="error_formfield8"
															style="display: none;">Please enter Avg. No. of
															student using library resources per day.</span>
													</div>
												</div>


												<!--  <h4>e-Content Facilities</h4>
															
														 
														<div class="form-group">
															<label class="control-label col-sm-3" for="mediacenter"
																style="text-align: left;"> Media Center Available  <span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<input type="radio" id="mediacenter" name="mediacenter"
																	value="1" checked>Yes <input type="radio"
																	id="mediacenter" name="mediacenter" value="0">No
															</div>
															<div class="col-sm-2">Year of Establishment</div>
															<div class="col-sm-3">

																<input type="text" class="form-control datepicker" id="yearMediacenter"
																	name="yearMediacenter" placeholder="Membership Details"
																	value="" pattern="^(?!\s*$).+" autocomplete="off"
																	  onkeypress='return restrictAlphabets(event)' required>

															</div>
														</div>
														 -->

												<!-- <div class="form-group">
															<label class="control-label col-sm-3" for="recordingFacility"
																style="text-align: left;"> Recording Facility Available <span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<input type="radio" id="recordingFacility" name="recordingFacility"
																	value="1" checked>Yes <input type="radio"
																	id="recordingFacility" name="recordingFacility" value="0" pattern="^(?!\s*$).+" autocomplete="off"  required>No
															</div>
															<div class="col-sm-2">Year of Establishment </div>
															<div class="col-sm-3">

																<input type="text" class="form-control datepicker" id="yearRecordingFacility"
																	name="yearRecordingFacility" placeholder="Membership Details"
																	value="" pattern="^(?!\s*$).+" autocomplete="off"
																	onkeypress='return restrictAlphabets(event)'  required>

															</div>
														</div>
														<div class="form-group">
															<label class="control-label col-sm-3" for="lectuteCap"
																style="text-align: left;"> Lecture Capturing System  <span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<input type="radio" id="lectuteCap" name="lectuteCap"
																	value="1" checked>Yes <input type="radio"
																	id="lectuteCap" name="lectuteCap" value="0">No
															</div>
															<div class="col-sm-2">Year of Establishment </div>
															<div class="col-sm-3">

																<input type="text" class="form-control datepicker" id="yearLectuteCap"
																	name="yearLectuteCap" placeholder="Membership Details"
																	value="" pattern="^(?!\s*$).+" autocomplete="off"
																	onkeypress='return restrictAlphabets(event)'  required>

															</div>
														</div>
														<div class="form-group">
															<label class="control-label col-sm-3" for="mediacenter"
																style="text-align: left;"> Any other e-Content Development Facility 
(Please Specify) 
  <span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<input type="radio" id="eContentFacility" name="eContentFacility"
																	value="1" checked>Yes <input type="radio"
																	id="eContentFacility" name="eContentFacility" value="0">No
															</div>
															<div class="col-sm-2">Year of Establishment </div>
															<div class="col-sm-3">

																<input type="text" class="form-control datepicker" id="yearEContentFacility"
																	name="yearEContentFacility" placeholder="Membership Details"
																	value="" pattern="^(?!\s*$).+" autocomplete="off" 
																	onkeypress='return restrictAlphabets(event)' required>

															</div>
														</div> -->


												<!--   <h4>e-Governance Facilities</h4>
														
														<div class="form-group">
															<label class="control-label col-sm-3" for="planning"
																style="text-align: left;">Planning	  <span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<input type="radio" id="planning" name="planning"
																	value="1" checked>Yes <input type="radio"
																	id="planning" name="planning" value="0">No
															</div>
															<div class="col-sm-2">Year of Establishment </div>
															<div class="col-sm-3">

																<input type="text" class="form-control datepicker" id="yearPlanning"
																	name="yearPlanning" placeholder="Membership Details"
																	value="" pattern="^(?!\s*$).+" autocomplete="off" 
																	onkeypress='return restrictAlphabets(event)' required>

															</div>
														</div>
														
														
														<div class="form-group">
															<label class="control-label col-sm-3" for="recordingFacility"
																style="text-align: left;"> Administration  <span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<input type="radio" id="administration" name="administration"
																	value="1" checked>Yes <input type="radio"
																	id="recordingFacility" name="recordingFacility" value="0">No
															</div>
															<div class="col-sm-2">Year of Establishment </div>
															<div class="col-sm-3">

																<input type="text" class="form-control datepicker" id="yearAdministration"
																	name="yearAdministration" placeholder="Membership Details" 
																	value="" pattern="^(?!\s*$).+" autocomplete="off" 
																	onkeypress='return restrictAlphabets(event)' required>

															</div>
														</div>
														<div class="form-group">
															<label class="control-label col-sm-3" for="lectuteCap"
																style="text-align: left;">Finance & Accounts  <span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<input type="radio" id="finance" name="finance"
																	value="1" checked>Yes <input type="radio"
																	id="lectuteCap" name="lectuteCap" value="0">No
															</div>
															<div class="col-sm-2">Year of Establishment </div>
															<div class="col-sm-3">

																<input type="text" class="form-control datepicker" id="yearFinance"
																	name="yearFinance" placeholder="Membership Details"
																	value="" pattern="^(?!\s*$).+" autocomplete="off" 
																	onkeypress='return restrictAlphabets(event)' required>

															</div>
														</div>
														<div class="form-group">
															<label class="control-label col-sm-3" for="studAddmission"
																style="text-align: left;"> Student Admission & Support 
  <span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<input type="radio" id="studAddmission" name="studAddmission"
																	value="1" checked>Yes <input type="radio"
																	id="studAddmission" name="studAddmission" value="0">No
															</div>
															<div class="col-sm-2">Year of Establishment </div>
															<div class="col-sm-3">

																<input type="text" class="form-control datepicker" id="yearStudAddmission"
																	name="yearStudAddmission" placeholder="Membership Details"
																	value="" pattern="^(?!\s*$).+" autocomplete="off"  
																	onkeypress='return restrictAlphabets(event)' required>

															</div>
														</div>


														<div class="form-group">
															<label class="control-label col-sm-3" for="examinations"
																style="text-align: left;"> Examinations  
 																 <span class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<input type="radio" id="examinations" name="examinations"
																	value="1" checked>Yes <input type="radio"
																	id="eContentFacility" name="eContentFacility" value="0">No
															</div>
															<div class="col-sm-2">Year of Establishment </div>
															<div class="col-sm-3">

																<input type="text" class="form-control datepicker" id="yearExaminations"
																	name="yearExaminations" placeholder="Membership Details"
																	value="" pattern="^(?!\s*$).+" autocomplete="off"
																	onkeypress='return restrictAlphabets(event)'  required>

															</div>
														</div> -->



												<div class="form-group">
													<div class="col-sm-offset-4 col-sm-8">
														<button type="submit" id="sub_button"
															class="btn btn-primary" onclick="submit_f(1)">
															<i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save
														</button>

														<a
															href="${pageContext.request.contextPath}/showLibraryBasicInfo"><button
																id="sub2" type="button" class="btn btn-primary">
																<i class="${sessionScope.cancelIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Cancel
															</button></a> <input type="hidden" id="is_view" name="is_view"
															value="0">
													</div>
												</div>

											</div>


											<div class="clearfix"></div>

										</div>

									</form>
									<p class="desc text-danger fontsize11">Notice : * Fields
										are mandatory.</p>
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
		function trim(el) {
			el.value = el.value.replace(/(^\s*)|(\s*$)/gi, ""). // removes leading and trailing spaces
			replace(/[ ]{2,}/gi, " "). // replaces multiple spaces with one space 
			replace(/\n +/, "\n"); // Removes spaces after newlines
			return;
		}

		$(document).ready(function($) {

			$("#form_sample_2").submit(function(e) {
				//	alert("hi");
				var isError = false;
				var errMsg = "";

				if (!$("#swName").val()) {
					isError = true;

					$("#swName").addClass("has-error")
					$("#error_formfield1").show()
				} else {
					$("#error_formfield1").hide()
				}

				if (!$("#version").val()) {

					isError = true;
					errMsg += '<li>Please enter a valid name.</li>';

					$("#version").addClass("has-error")
					$("#error_formfield2").show()
					//return false;
				} else {
					$("#error_formfield2").hide()
				}

				//alert($("#userLms").val());
				if ($("#userLms").val() == 0) {

					isError = true;
					errMsg += '<li>Please enter a valid name.</li>';

					$("#userLms").addClass("has-error")
					$("#error_formfield3").show()
					//return false;
				} else {
					$("#error_formfield3").hide()
				}

				if (!$("#purchaseDate").val()) {

					isError = true;
					errMsg += '<li>Please enter a valid name.</li>';

					$("#purchaseDate").addClass("has-error")
					$("#error_formfield4").show()
					//return false;
				} else {
					$("#error_formfield4").hide()
				}

				if ($("#noOfComp").val() == 0) {

					isError = true;
					errMsg += '<li>Please enter a valid name.</li>';

					$("#noOfComp").addClass("has-error")
					$("#error_formfield5").show()
					//return false;
				} else {
					$("#error_formfield5").hide()
				}

				if (!$("#bandwidth").val()) {

					isError = true;
					errMsg += '<li>Please enter a valid name.</li>';

					$("#noOfComp").addClass("has-error")
					$("#error_formfield6").show()
					//return false;
				} else {
					$("#error_formfield6").hide()
				}

				if ($("#avgTeacher").val() == 0) {

					isError = true;
					errMsg += '<li>Please enter a valid name.</li>';

					$("#avgTeacher").addClass("has-error")
					$("#error_formfield7").show()
					//return false;
				} else {
					$("#error_formfield7").hide()
				}

				if ($("#avgStud").val() == 0) {

					isError = true;
					errMsg += '<li>Please enter a valid name.</li>';

					$("#noOfComp").addClass("has-error")
					$("#error_formfield8").show()
					//return false;
				} else {
					$("#error_formfield8").hide()
				}

				if (!isError) {

					var x = confirm("Do you really want to submit the form?");
					if (x == true) {

						document.getElementById("sub_button").disabled = true;
						document.getElementById("sub2").disabled = true;
						return true;
					}
				}
				return false;
			});
		});
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

		function submit_f(view) {
			//alert(view);
			document.getElementById("is_view").value = view;

		}
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
		/* function showDiv(value) {

			var div = document.getElementById("divshow");

			if (value == 1) {
				div.style.display = "block";
			} else {
				div.style.display = "none";
			}
		} */
	</script>
	<script type="text/javascript">
		jQuery(document).ready(
				function($) {

					// sub_menu
					$('#sub_menu1').click(function(e) {
						//ajax send this to php page
						var def = 1;
						if ($("#sub_menu").prop('checked') == true) {
							$('#main_menu').prop('checked', true);

						} else {

						}
					});

					//Example 2

					$(document).on('click', '#close-preview', function() {
						$('.image-preview').popover('hide');
						// Hover befor close the preview
						$('.image-preview').hover(function() {
							$('.image-preview').popover('show');
						}, function() {
							$('.image-preview').popover('hide');
						});
					});

					$(function() {
						// Create the close button
						var closebtn = $('<button/>', {
							type : "button",
							text : 'x',
							id : 'close-preview',
							style : 'font-size: initial;',
						});
						closebtn.attr("class", "close pull-right");
						// Set the popover default content
						$('.image-preview').popover(
								{
									trigger : 'manual',
									html : true,
									title : "<strong>Preview</strong>"
											+ $(closebtn)[0].outerHTML,
									content : "There's no image",
									placement : 'bottom'
								});
						// Clear event
						$('.image-preview-clear').click(function() {
							//$('.image-preview').attr("data-content","").popover('hide');
							$('.image-preview-filename').val("");
							$('.image-preview-clear').hide();
							$('#header_image input:file').val("");
							$(".image-preview-input-title").text("Browse");
							$("#thumbkishore").attr("src", '');
							$(".thumbkishorediv").hide();
						});
						// Create the preview image
						$("#header_image").change(
								function() {
									var img = $('<img/>', {
										id : 'dynamic',
										width : 250,
										height : 200,
									});

									var file = this.files[0];
									var reader = new FileReader();
									// Set preview image into the popover data-content
									reader.onload = function(e) {

										$(".image-preview-input-title").text(
												"Change");
										$(".image-preview-clear").show();
										$(".image-preview-filename").val(
												file.name);
										img.attr('src', e.target.result);
										//alert(e.target.result);
										///alert($(img)[0].outerHTML);
										//$(".image-preview").attr("data-content",$(img)[0].outerHTML).popover("show");
										//
										//alert(e.target.result);
										$("#thumbkishore").attr("src",
												e.target.result);

										$(".thumbkishorediv").show();
									}
									reader.readAsDataURL(file);
								});

						// Clear event
						$('.image-preview-clear2').click(function() {
							//$('.image-preview').attr("data-content","").popover('hide');
							$('.image-preview-filename2').val("");
							$('.image-preview-clear2').hide();
							$('.image-preview-input2 input:file').val("");
							$(".image-preview-input-title2").text("Browse");
							$("#thumbkishor2e").attr("src", '');
							$(".thumbkishore2div").hide();
						});
						// Create the preview image
						$("#images").change(
								function() {
									var img = $('<img/>', {
										id : 'dynamic',
										width : 250,
										height : 200,
									});

									var file = this.files[0];
									var reader = new FileReader();
									// Set preview image into the popover data-content
									reader.onload = function(e) {

										$(".image-preview-input-title2").text(
												"Change");
										$(".image-preview-clear2").show();
										$(".image-preview-filename2").val(
												file.name);
										img.attr('src', e.target.result);
										//alert(e.target.result);
										///alert($(img)[0].outerHTML);
										//$(".image-preview").attr("data-content",$(img)[0].outerHTML).popover("show");
										//
										//alert(e.target.result);
										$("#thumbkishore2").attr("src",
												e.target.result);

										$(".thumbkishore2div").show();
									}
									reader.readAsDataURL(file);
								});

					});

				});
	</script>

</body>
</html>