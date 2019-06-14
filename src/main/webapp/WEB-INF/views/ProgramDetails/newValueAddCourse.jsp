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


<!-- BEGIN BODY --><!-- onload="hideText()" -->
<body class=" " onload="showIsReg(${alumni.exInt2})">
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
							<%-- 	<a href="${pageContext.request.contextPath}/showAlumini"><button
										type="button" class="btn btn-info">Back</button></a> --%>
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">




									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertValueAddedCourse"
										method="post" name="form_sample_2" id="form_sample_2">

										<div class="row">
											<div class="col-md-12">

												<div class="form-group">
													<label class="control-label col-sm-3" for="page_name">Name of Value Added 
														Courses (More than 30 hrs)<span class="text-danger">*</span>
													</label>
													<div class="col-sm-9">
														<input type="text" class="form-control" onchange="trim(this)"
															placeholder="Name of Value Added Courses (More than 30 hrs)" 
															id="course_name" autocomplete="off"
															value="${value.valueAddedCourseName}" name="course_name">
															<span class="error_form text-danger" id="course_name_field"
															style="display: none;">Please enter name of value added courses (More than 30 hrs)</span>
													</div>
												</div>
												
												<div class="form-group">
													<label class="control-label col-sm-3" for="page_name">Course 
														 Code<span class="text-danger">*</span>
													</label>
													<div class="col-sm-9">
														<input type="text" class="form-control" onchange="trim(this)"
															placeholder="Course Code" id="course_code"
															value="${value.courseCode}" name="course_code" autocomplete="off">
															<span class="error_form text-danger" id="course_code_field"
															style="display: none;">Please enter course code</span>
													</div>
												</div>


												<div class="form-group">
													<label class="control-label col-sm-3" for="page_name">Year of
														 Offering <span class="text-danger">*</span>
													</label>
													<div class="col-sm-9">
														<input type="text" class="form-control datepickeryear"
															data-min-view-mode="years" data-start-view="2"
															data-format="yyyy" placeholder="Year of Offering"
															id="year_of_offering" value="${value.yearOfOffering}"
															name="year_of_offering" autocomplete="off">
															<span class="error_form text-danger" id="year_of_offering_field"
															style="display: none;">Please select year of offering</span>
													</div>
												</div>


												<div class="form-group">
													<label class="control-label col-sm-3" for="page_name">No. of Times 
														 Offer (In Same Year) <span class="text-danger">*</span>
													</label>
													<div class="col-sm-9">
														<input type="number" class="form-control" onchange="trim(this)" onFocus="clearDefault(this)"
															placeholder="No.of Times Offer (In Same Year)" id="no_times_offer"
															value="${value.noOfTimesOffer}" name="no_times_offer" autocomplete="off">
															<span class="error_form text-danger" id="time_offer_field"
															style="display: none;">Please enter No. of times offer (In Same Year)</span>
													</div>
												</div>
												
												<div class="form-group">
													<label class="control-label col-sm-3" for="page_name">Year of
														 Discontinuation <span class="text-danger">*</span>
													</label>
													<div class="col-sm-9">
														<input type="text" class="form-control datepickeryear"
															data-min-view-mode="years" data-start-view="2"
															data-format="yyyy" placeholder="Year of Discontinuation"
															id="year_of_discontinue" value="${value.yearOfDiscontinuation}"
															name="year_of_discontinue" autocomplete="off">
															<span class="error_form text-danger" id="year_of_discontinue_field"
															style="display: none;">Please select year of discontinuation</span>
													</div>
												</div>



												<div class="form-group">
													<label class="control-label col-sm-3" for="page_name">No. of 
														 Students Enrolled<span class="text-danger">*</span>
													</label>
													<div class="col-sm-9">
														<input type="number" class="form-control" onchange="trim(this)" onFocus="clearDefault(this)"
															placeholder="No.of Times Offer(In Same Year)" id="no_student_enrolled"
															value="${value.noOfStudentsEnrolled}" name="no_student_enrolled" autocomplete="off">
															<span class="error_form text-danger" id="student_enrolled_field"
															style="display: none;">Please enter No. of students enrolled</span>
													</div>
												</div>



												<div class="form-group">
													<label class="control-label col-sm-3" for="page_name">No. of Students
													 Completed Course<span class="text-danger">*</span>
													</label>
													<div class="col-sm-9">
														<input type="number" class="form-control" onchange="trim(this)" onFocus="clearDefault(this)"
															placeholder="No of Students Completed Course" id="no_student_completed_course"
															value="${value.noOfStudentsCompletedCourse}" name="no_student_completed_course" autocomplete="off">
															<span class="error_form text-danger" id="student_course_completed_field"
															style="display: none;">Please enter No. of students completed course</span>
															
															<span class="error_form text-danger" id="batch"
															style="display: none;">No. of students completed course can not be greater than no of appeared students</span>
													</div>
												</div>

											<div class="form-group">
													<div class="col-sm-offset-3 col-sm-9">


<button type="submit" id="sub1" class="btn btn-primary"
													onclick="submit_f(1)"><i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save</button>
														
<a href="${pageContext.request.contextPath}/showValueAddedCourses"><button id="sub2"
										type="button" class="btn btn-primary"><i class="${sessionScope.cancelIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Cancel</button></a>													</div>
												</div>

											</div>
											<input type="hidden" id="course_id" name="alumni_id"
												value="${value.valueAddedCourseId}"> <input
												type="hidden" id="is_view" name="is_view" value="0">

										</div>
									</form>


								</div>
								<p class="desc text-danger fontsize11">Notice : * Fields are
									mandatory.</p>

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
		function clearDefault(a){
			if(a.defaultValue==0)
			{
				a.value=""
			}
			};
		$(document)
				.ready(
						function($) {

							$("#form_sample_2")
									.submit(
											function(e) {
												var isError = false;
												var errMsg = "";
												
												  
												if (!$("#course_name").val()) {
													isError = true;

													$("#course_name").addClass(
															"has-error")
													$("#course_name_field")
															.show()
													} else {
														$("#course_name_field")
																.hide()
													}
												
												  
												if (!$("#course_code").val()) {
													isError = true;

													$("#course_code").addClass(
															"has-error")
													$("#course_code_field")
															.show()
												} else {
													$("#course_code_field")
															.hide()
												}		
												   
												if (!$("#year_of_offering").val()) {
													isError = true;

													$("#year_of_offering").addClass(
															"has-error")
													$("#year_of_offering_field")
															.show()
												} else {
													$("#year_of_offering_field")
															.hide()
												}

												if ($("#no_times_offer").val()<=0 || !$("#no_times_offer").val()) {
													isError = true;

													$("#no_times_offer").addClass(
															"has-error")
													$("#time_offer_field")
															.show()
												} else {
													$("#time_offer_field")
															.hide()
												}	

												if (!$("#year_of_discontinue").val()) {
													isError = true;

													$("#year_of_discontinue").addClass(
															"has-error")
													$("#year_of_discontinue_field").show()
												} else {
													$("#year_of_discontinue_field").hide()
												}


												if ($("#no_student_enrolled").val()<=0 || !$("#no_student_enrolled").val()) {
													isError = true;

													$("#no_student_enrolled").addClass(
															"has-error")
													$("#student_enrolled_field").show()
												} else {
													$("#student_enrolled_field").hide()
												}

												if ($("#no_student_completed_course").val()<=0 || !$("#no_student_completed_course").val()) {	
													isError = true;

													$("#no_student_completed_course").addClass(
															"has-error")
													$("#student_course_completed_field").show()
												} else {
													$("#student_course_completed_field").hide()
												}

												//new 
												var x=$("#no_student_completed_course").val();
												var y=$("#no_student_enrolled").val();
												if (parseInt(x)>parseInt(y)) {	
													isError = true;

													$("#no_student_completed_course").addClass(
															"has-error")
													$("#batch").show()
												} else {
													$("#batch").hide()
												}



												
												
				

												 
												
												if (!isError) {
													var x = confirm("Do you really want to submit the form?");
													if (x == true) {
														document.getElementById("sub1").disabled = true;
														document.getElementById("sub2").disabled = true;
														return  true;
													}	
												}
												return false;
											});
						});
	</script>
	<script type="text/javascript">
		/* function checkUnique(inputValue,valueType){
		 //alert(inputValue);
		
		 var primaryKey=${editInst.librarianId};
		 //alert("Primary key"+primaryKey);
		 var isEdit=0;
		 if(primaryKey>0){
		 isEdit=1;
		 }
		 //alert("Is Edit " +isEdit);
		
		 var valid=false;
		 if(valueType==1){
		 //alert("Its Mob no");
		 if(inputValue.length==10){
		 valid=true;
		 //alert("Len 10")
		 }else{
		 //alert("Not 10");
		 }
		 }
		 else if(valueType==2){
		 //alert("Its Email " );
		
		 var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
		 if(inputValue.match(mailformat))
		 {
		 valid=true;
		 //alert("Valid Email Id");
		 }
		 else
		 {
		 valid=false;
		 //alert("InValid Email Id");
		 }
		 }
		 if(valid==true)
		 $.getJSON('${checkUniqueField}', {
		
		 inputValue : inputValue,
		 valueType  : valueType,
		 primaryKey : primaryKey,
		 isEdit     : isEdit,
		 tableId : 4,

		 ajax : 'true',

		 }, function(data) {
		
		 //	alert("Data  " +JSON.stringify(data));
		 if(data.error==true){
		 if(valueType==2){
		
		
		 alert("This email id already exist in system please enter unique");
		 $('#librarian_email').val('');
		 //document.getElementById("stud_contact_no").value=" ";
		
		 }
		 else{
		
		
		 alert("This contact no  already exist in system please enter unique");
		 $('#lib_con_num').val('');
		 //document.getElementById("student_email").value=" ";
		 }
		 }
		 });
		 }
		 */
	</script>

	

	<script type="text/javascript">
		
		$(function() {

			$('.datepickeryear').datepicker({
				autoclose : true,
				minViewMode : 2,
				format : 'yyyy'

			});
		});
		
		
	</script>
	
	<script type="text/javascript">
	
	function trim(el) {
		el.value = el.value.replace(/(^\s*)|(\s*$)/gi, ""). // removes leading and trailing spaces
		replace(/[ ]{2,}/gi, " "). // replaces multiple spaces with one space 
		replace(/\n +/, "\n"); // Removes spaces after newlines
		return;
	}
	
	</script>
	<!-- 	<script type="text/javascript">
	 $(function(){
		 
	        $('.datepickeryear').datepicker({
				autoclose: true,
				minViewMode: 2,
		         format: 'yyyy'

			});
	    });
    </script> -->

	<!-- <script
		src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
	<script type="text/javascript" src="./javascript.js"></script>
	<script
		src="http://maps.googleapis.com/maps/api/js?key=YOUR_APIKEY&sensor=false">
		
	</script> -->

	<!-- END CONTAINER -->
	<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->
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



</body>
</html>