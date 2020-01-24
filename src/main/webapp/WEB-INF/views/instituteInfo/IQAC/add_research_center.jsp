<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


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
								<%-- <a href="${pageContext.request.contextPath}/showResearchCenter"><button
										type="button" class="btn btn-info">Back</button></a>
 --%>
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertResearchCenter"
										method="post" name="formidhere" id="formidhere">



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
										<div class="form-group">

											<label class="control-label col-sm-2" for="rc_subject_name">Subject
												Name<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control" id="rc_subject_name"
													autocomplete="off" name="rc_subject_name"
													placeholder="Subject Name" onchange="return trim(this)"
													value="${editValue.rcSubjectName}"> <span
													class="error_form text-danger" id="error_rc_subject_name"
													style="display: none;">Please enter subject name.</span>
											</div>
										</div>

										<div class="form-group">

											<label class="control-label col-sm-2" for="rc_faculty_name">Faculty
												Name <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control" id="rc_faculty_name"
													autocomplete="off" name="rc_faculty_name"
													onchange="return trim(this)" placeholder="Faculty Name"
													value="${editValue.rcFacultyName}"> <span
													class="error_form text-danger" id="error_rc_faculty_name"
													style="display: none;">Please enter faculty name.</span>
											</div>

										</div>

										<div class="form-group">
											<label class="control-label col-sm-2" for="rc_guide_count">No.
												of Affiliated Guides<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control"
													onchange="trim(this)" id="rc_guide_count"
													name="rc_guide_count" maxlength="8"
													placeholder="No. of Affiliated Guides" autocomplete="off"
													value="${editValue.rcGuideCount}"> <span
													class="error_form text-danger" id="error_rc_guide_count"
													style="display: none;">Please enter of affiliated
													guides.</span>
											</div>
										</div>


										<div class="form-group">
											<label class="control-label col-sm-2" for="participant">No.
												Students Registered<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control" maxlength="8"
													onchange="trim(this)" id="rc_student_count"
													name="rc_student_count"
													placeholder="No. of Students Registered" autocomplete="off"
													value="${editValue.rcStudentCount}"> <span
													class="error_form text-danger" id="error_rc_student_count"
													style="display: none;">Please enter No. of students
													registered.</span>
											</div>
										</div>


										<div class="form-group">

											<label class="control-label col-sm-2" for="fromDate">From
												Date <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control datepicker"
													id="fromDate" name="fromDate" placeholder="dd-mm-yyyy"
													value="${editValue.rcValidityFromdt}" autocomplete="off">
												<span class="error_form text-danger" id="error_fromDate"
													style="display: none;">Please enter From date.</span> <span
													class="error_form text-danger" id="error_fromToDate"
													style="display: none;">from Date must be smaller
													than to date.</span>

											</div>
										</div>

										<div class="form-group">

											<label class="control-label col-sm-2" for="toDate">To
												Date <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control datepicker"
													autocomplete="off" id="toDate" name="toDate"
													placeholder="dd-mm-yyyy"
													value="${editValue.rcValidityTodt}"> <span
													class="error_form text-danger" id="error_toDate"
													style="display: none;">Please enter to date.</span> <span
													class="error_form text-danger" id="error_toToDate"
													style="display: none;">to date must be greater than
													from date.</span>
											</div>
										</div>

										<input type="hidden" id="rcId" name="rcId"
											value="${editValue.rcId}"> <input type="hidden"
											id="is_view" name="is_view" value="0">

										<div class="form-group">
											<div class="col-sm-offset-3 col-sm-9">


												<button type="submit" id="sub_button"
													class="btn btn-primary" onclick="submit_f(1)">
													<i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save
												</button>

												<a
													href="${pageContext.request.contextPath}/showResearchCenter"><button
														type="button" class="btn btn-primary">
														<i class="${sessionScope.cancelIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Cancel
													</button></a>
											</div>
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





	<script type="text/javascript">
	$('#rc_guide_count').on('input', function() {
		  this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');
		});
	
	$('#rc_student_count').on('input', function() {
		  this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');
		});
	
	$('#fromDate').on('input', function() {
		  this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');
		});
	
	$('#toDate').on('input', function() {
		  this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');
		});
	
		$(function() {

			$('.datepicker').datepicker({
				autoclose : true,
				format : "dd-mm-yyyy",
				changeYear : true,
				changeMonth : true

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

	<script type="text/javascript">
		function submit_f(view) {
			//alert(view);
			document.getElementById("is_view").value = view;

		}
	</script>
	<script type="text/javascript">
	function allowOnlyNumber(evt){
		
		var charCode = (evt.which) ? evt.which : event.keyCode
	    if (charCode == 46){
	        var inputValue = $("#floor").val();
	        var count = (inputValue.match(/'.'/g) || []).length;
	        
	        if(count<1){
	            if (inputValue.indexOf('.') < 1){
	                return true;
	            }
	            return false;
	        }else{
	            return false;
	        }
	    }
	    if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)){
	        return false;
	    }
	    return true;
	}
	</script>

	<script>
		function numbersOnlyNotZero(value) {

			var mob = /^[1-9][0-9]+$/;

			if (mob.test($.trim(value)) == false) {

				//alert("Please enter a valid email address .");
				return false;

			}
			return true;
		}
	</script>



	<script>
		$(document)
				.ready(
						function($) {

							$("#formidhere")
									.submit(
											function(e) {
												var isError = false;
												var errMsg = "";
												
												if (!$("#rc_subject_name").val()) {

													isError = true;
													errMsg += '<li>Please enter Subject Name.</li>';

													$("#rc_subject_name").addClass(
															"has-error")
													$("#error_rc_subject_name").show();

												} else {
													$("#error_rc_subject_name").hide();
												}

												
												
												
												if (!$("#rc_faculty_name").val()) {

													isError = true;
													errMsg += '<li>Please enter faculty Name </li>';

													$("#rc_faculty_name").addClass(
															"has-error")
													$("#error_rc_faculty_name").show();

												} else {
													$("#error_rc_faculty_name").hide();
												}
												
												
												if ($("#rc_guide_count")
														.val() == 0
														|| !$(
																"#rc_guide_count")
																.val()) {

													isError = true;
													errMsg += '<li>Please enter a valid name.</li>';

													$("#rc_guide_count")
															.addClass(
																	"has-error")
													$(
															"#error_rc_guide_count")
															.show();
													//return false;
												} else {
													$(
															"#error_rc_guide_count")
															.hide();
												}
												
												
												
												if ($("#rc_student_count")
														.val() == 0
														|| !$(
																"#rc_student_count")
																.val()) {

													isError = true;
													errMsg += '<li>Please enter a valid name.</li>';

													$("#rc_student_count")
															.addClass(
																	"has-error")
													$(
															"#error_rc_student_count")
															.show();
													//return false;
												} else {
													$(
															"#error_rc_student_count")
															.hide();
												} 
											 
											 
												if (!$("#fromDate").val()) {

													isError = true;
													errMsg += '<li>Please enter a Subject name.</li>';

													$("#fromDate").addClass(
															"has-error")
													$("#error_fromDate").show();

												} else {
													$("#error_fromDate").hide();
												}
												
												if (!$("#toDate").val()) {

													isError = true;
													errMsg += '<li>Please enter a Subject name.</li>';

													$("#toDate").addClass(
															"has-error")
													$("#error_toDate").show();

												} else {
													$("#error_toDate").hide();
												}

												var from_date = document.getElementById("fromDate").value;
						         				var to_date = document.getElementById("toDate").value;
						         				var x=0;
						         				
						         				
						         		        var fromdate = from_date.split('-');
						         		        from_date = new Date();
						         		        from_date.setFullYear(fromdate[2],fromdate[1]-1,fromdate[0]);
						         		        var todate = to_date.split('-');
						         		        to_date = new Date();
						         		        to_date.setFullYear(todate[2],todate[1]-1,todate[0]);
						         		        if (from_date > to_date ) 
						         		        {
						         		           /// alert("Invalid Date Range!\nStart Date cannot be after End Date!")
													$("#error_fromToDate").show();
						    					 	$("#error_toToDate").show();
						    					 	$("#error_fromDate").hide();
						    					 	$("#error_toDate").hide();
						         		            return false;
						         		           
						         		        }else {
						         					$("#error_fromToDate").hide();
						         					$("#error_toToDate").hide();
						         				}

												if (!isError) {

													var x = confirm("Do you really want to submit the form?");
													if (x == true) {
														document
																.getElementById("sub_button").disabled = true;
														document
																.getElementById("sub_button_next").disabled = true;
														return true;

													}

												}
												return false;
											});
						});
	</script>
</body>
</html>