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
										action="${pageContext.request.contextPath}/insertStudQualifyExamDtl"
										method="post" name="submitAcitivity" name="form_sample_2"
										id="form_sample_2">

										<!-- <ul class="nav nav-tabs">
											<li class="active"><a href="#home" data-toggle="tab">
													<i class="fa fa-home"></i> Activity
											</a></li>


										</ul> -->

										<!-- <div class="tab-content">
											<div class="tab-pane fade in active" id="home"> -->

										<div>

											

											<div class="col-xs-12">
											
											<input type="hidden" id="studExmId" name="studExmId" value="${studQlifyExam.studExmId }">

												<div class="form-group">
													<label class="control-label col-sm-2" for="activityName">Name
														of Qualifying Exam <span class="text-danger">*</span>
													</label>
													<div class="col-sm-10">
														<select id="qualify_exam" name="qualify_exam" class="form-control" >
															<option value="NET" ${studQlifyExam.nameQualifExam eq 'NET' ? 'selected' : ''}>NET</option>
															<option value="SLET" ${studQlifyExam.nameQualifExam eq 'SLET' ? 'selected' : ''}>SLET</option>
															<option value="GATE" ${studQlifyExam.nameQualifExam eq 'GATE' ? 'selected' : ''}>GATE</option>
															<option value="GMAT" ${studQlifyExam.nameQualifExam eq 'GMAT' ? 'selected' : ''}>GMAT</option>
															<option value="CAT" ${studQlifyExam.nameQualifExam eq 'CAT' ? 'selected' : ''}>CAT</option>
															<option value="GRE" ${studQlifyExam.nameQualifExam eq 'GRE' ? 'selected' : ''}>GRE</option>
															<option value="TOEFL" ${studQlifyExam.nameQualifExam eq 'TOEFL' ? 'selected' : ''}>TOEFL</option>
															<option value="Civil Services" ${studQlifyExam.nameQualifExam eq 'Civil Services' ? 'selected' : ''}>Civil Services</option>
															<option value="State Government Exams" ${studQlifyExam.nameQualifExam eq 'State Government Exams' ? 'selected' : ''}>State Government Exams</option>
														</select>
													</div>
												</div>

												<div class="form-group">
													<label class="control-label col-sm-2" for="level">Level
														of Exam <span class="text-danger">*</span>
													</label>
													<div class="col-sm-10">
														<select id="exam_level" name="exam_level" class="form-control">
															<option value="State" ${studQlifyExam.levelExam eq 'State' ? 'selected' : ''}>State</option>															
															<option value="National" ${studQlifyExam.levelExam eq 'National' ? 'selected' : ''}>National</option>
															<option value="International" ${studQlifyExam.levelExam eq 'International' ? 'selected' : ''}>International</option>
														</select>
													</div>
												</div>

												<div class="form-group">
													<label class="control-label col-sm-2" for="no_stud_appear">No. of Students 
														Appeared<span class="text-danger">*</span>
													</label>
													<div class="col-sm-10">
														<input type="text" class="form-control" id="no_stud_appear" onblur="cal()"
															value="${studQlifyExam.noStudAppeared}" onfocus="this.value=''"
															placeholder="No. of Students Appeared" name="no_stud_appear"
															onkeypress='return restrictAlphabets(event)'
															autocomplete="off"> <span
															class="error_form text-danger" id="studAppear_errfield"
															style="display: none;">Please enter No. of student appeared
															and value must be greater than 0.</span>
													</div>
												</div>
												
												<div class="form-group">
													<label class="control-label col-sm-2" for="no_stud_qualify">No. of Students 
														Qualified<span class="text-danger">*</span>
													</label>
													<div class="col-sm-10">
														<input type="text" maxlength="30" class="form-control" onblur="cal()"
															id="no_stud_qualify" value="${studQlifyExam.noStudQualified}"
															name="no_stud_qualify" placeholder="No. of Students Qualified" 
															onkeypress='return restrictAlphabets(event)' onfocus="this.value=''"
															onchange="return trim(this)" autocomplete="off">
													<span class="error_form text-danger" id="studQualify_errfield"
															style="display: none;">Please enter No. student qualified and value must be greater than 0.</span>
															
													<span class="error_form text-danger" id="studQualify_errfield0"
															style="display: none;">No. students qualified cannot exceed No. of students appeared.</span>
													</div>
												</div>


											</div>

										</div>

										<div class="form-group">
											<div class="col-sm-offset-3 col-sm-9">


												<button type="submit" id="sub_button"
													class="btn btn-primary" onclick="submit_f(1)">
													<i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save
												</button>

												<a
													href="${pageContext.request.contextPath}/showStudentsQualifyingExamDetails"><button
														type="button" class="btn btn-primary" id="sub2">
														<i class="${sessionScope.cancelIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Cancel
													</button></a>
											</div>
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
		$(function() {

			$('.datepicker').datepicker({
				autoclose : true,
				format : "dd-mm-yyyy",
				changeYear : true,
				changeMonth : true

			});
		});

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
		var res = 0;
		function cal(){
			var appear = parseFloat($("#no_stud_appear").val());
			var qualify = parseFloat($("#no_stud_qualify").val());
			
			if(appear < qualify){
				res = 0;
			}else{
				res = 1;
			}
		}
		
		$(document)
				.ready(
						function($) {

							$("#form_sample_2")
									.submit(
											function(e) {

												var isError = false;
												var errMsg = "";

												if ($("#no_stud_appear")
														.val()<=0 || !$("#no_stud_appear")
														.val()) {		

													isError = true;
													errMsg += '<li>Please enter a valid name.</li>';

													$("#no_stud_appear")
															.addClass(
																	"has-error")
													$("#studAppear_errfield")
															.show()
													//return false;
												} else {
													$("#studAppear_errfield")
															.hide()
												}

												if ($("#no_stud_qualify").val()<=0 || !$("#no_stud_qualify").val()) {

													isError = true;
													errMsg += '<li>Please enter a valid name.</li>';

													$("#no_stud_qualify").addClass(
															"has-error")
													$("#studQualify_errfield")
															.show()
													//return false;
												} else {
													$("#studQualify_errfield")
															.hide()
												}		
	
												if (res == 0) {

													isError = true;
													
													$("#no_stud_qualify").addClass(
															"has-error")
													$("#studQualify_errfield0")
															.show()
													//return false;
												} else {
													$("#studQualify_errfield0")
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


	<!-- END CONTAINER -->
	<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->

	<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>



</body>
</html>