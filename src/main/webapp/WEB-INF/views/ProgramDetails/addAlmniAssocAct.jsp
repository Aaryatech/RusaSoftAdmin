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
										action="${pageContext.request.contextPath}/insertAlumniAssocAct"
										method="post" name="form_sample_2" id="form_sample_2">

										<div class="row">
											<div class="col-md-12">

												<div class="form-group">
													<label class="control-label col-sm-3" for="page_name">Alumni 
														Meeting Agenda<span class="text-danger">*</span>
													</label>
													<div class="col-sm-9">
														<input type="text" class="form-control" onchange="trim(this)" autocomplete="off"
															placeholder="Alumni Meeting Agenda" id="alum_meet_agnd"
															value="${alumni.alumniMeetngAgnda}" name="alum_meet_agnd">
															<span class="error_form text-danger" id="alum_agenda_field"
															style="display: none;">Please enter alumni meeting agenda</span>
													</div>
												</div>


												<div class="form-group">
													<label class="control-label col-sm-3" for="page_name">Date
														of Meeting <span class="text-danger">*</span>
													</label>
													<div class="col-sm-9">
														<input type="text" class="form-control datepicker"
															 placeholder="dd-mm-yyyy" autocomplete="off"
															id="date_meet" value="${alumni.dateOfMeeting}"
															name="date_meet" autocomplete="off">
															<span class="error_form text-danger" id="date_meet_errfield"
															style="display: none;">Please enter date of meeting</span>
													</div>
												</div>

												
												<div class="form-group">
														<div class="form-group">
															<label class="control-label col-sm-3" for="smallheading">Name of 
																Alumni Association<span class="text-danger">*</span>
															</label>
															<div  class="col-sm-9">
																<input type="text" class="form-control" id="name_alumini_assoc" onchange="trim(this)"
																	name="name_alumini_assoc" placeholder="Name of Alumni Association" autocomplete="off"
																	value="${alumni.nameAlumniAssoc}" autocomplete="off">
																	<span class="error_form text-danger" id="name_alumini_assoc_errfield" style="display:none;" >
																	Please enter name of alumni association.</span>
															</div>
														</div>
													</div>



												<div class="form-group">
													<label class="control-label col-sm-3" for="page_name"> Alumni 
														Registration No<span class="text-danger">*</span>
													</label>
													<div class="col-sm-9">
														<input type="text" autocomplete="off"
															class="form-control" onfocus="this.value=''"
															placeholder="Alumni Registration No" id="almni_reg_no"
															value="${alumni.alumniRegNo}" name="almni_reg_no"
															><span class="error_form text-danger" id="almni_reg_no_errfield"
															style="display: none;">Please alumni registration No 
															and value must be greater than0.</span>
													</div>
												</div>

												<div class="form-group">
													<label class="control-label col-sm-3" for="page_order">Date of 
														Alumni Registration <span class="text-danger">*</span>
													</label>
													<div class="col-sm-9">
														<input type="text" onchange="trim(this)" class="form-control datepicker" id="date_of_almni_reg"
															value="${alumni.dateAlumniReg}" name="date_of_almni_reg" autocomplete="off"
															placeholder="dd-mm-yyyy">
															<span class="error_form text-danger" id="date_of_almni_reg_errfield"
															style="display: none;">Please enter date of alumni registration</span>
													</div>
												</div>

												<div class="form-group">
													<label class="control-label col-sm-3" for="page_name"> No. of Alumni 	
														Register<span class="text-danger">*</span>
													</label>
													<div class="col-sm-9">
														<input type="text" autocomplete="off"
															class="form-control" onfocus="this.value=''"
															placeholder="No. of Alumni Register" id="registred_almni_no"
															value="${alumni.noAlumniReg}" name="registred_almni_no"
															><span class="error_form text-danger" id="registred_almni_no_err_field"
															style="display: none;">Please enter No. of alumni register 
															and value must be greater than 0.</span>
													</div>
												</div>
												
												<div class="form-group">
													<label class="control-label col-sm-3" for="page_name"> No. of Member 	
														Attended<span class="text-danger">*</span>
													</label>
													<div class="col-sm-9">
														<input type="text" autocomplete="off"
															class="form-control" onfocus="this.value=''"
															placeholder="No. of Member Attended" id="no_member_attnd"
															value="${alumni.noMemberAttended}" name="no_member_attnd"
															><span class="error_form text-danger" id="no_member_attnd_errfield"
															style="display: none;">Please enter  No. of member attended 
															and value must be greater than 0.</span>
													</div>
												</div>
												
												<div class="form-group">
													<label class="control-label col-sm-3" for="page_name">Total No. of 	
														Alumni Enrolled<span class="text-danger">*</span>
													</label>
													<div class="col-sm-9">
														<input type="text" autocomplete="off"
															class="form-control" onfocus="this.value=''"
															placeholder="No. of Member Attended" id="ttl_no_almni_enrolled"
															value="${alumni.ttlNoAlumniEnrolled}" name="ttl_no_almni_enrolled">
															<span class="error_form text-danger" id="ttl_no_almni_enrolled_errfield"
															style="display: none;">Please enter total No. of alumni enrolled 
															and value must be greater than 0.</span>
													</div>
												</div>


												<div class="form-group">
													<div class="col-sm-offset-3 col-sm-9">


<button type="submit" id="sub1" class="btn btn-primary"
													onclick="submit_f(1)"><i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save</button>
														
<a href="${pageContext.request.contextPath}/showAlumniAssociationActivity"><button id="sub2"
										type="button" class="btn btn-primary"><i class="${sessionScope.cancelIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Cancel</button></a>													</div>
												</div>

											</div>
											<input type="hidden" id="alumni_assoc_act_id" name="alumni_assoc_act_id"
												value="${alumni.almAssocActId}"> 

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
	<script type="text/javascript">
		function checkPhdGuide(activity) {
			
//alert(activity);
			if (activity == 1) {
				
				document.getElementById("ihide").style = "visible"
				

			} else  {
				document.getElementById("ihide").style = "display:none"
				
			}

		}
		
		function showIsReg(act){
			
				//alert(act);
				hideText();
				var isActivity = act; //$("input[name=isActivity]:checked").val();
				
				if(isActivity == 1){
					document.getElementById("ihide").style.display = "block";
				} else{
					document.getElementById("ihide").style.display = "none";
				} 
				
			}
			
		</script>
	
 <script>
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
												
												
												if (!$("#alum_meet_agnd").val()) {
													isError = true;

													$("#alum_meet_agnd").addClass(
															"has-error")
													$("#alum_agenda_field")
															.show()
													} else {
														$("#alum_agenda_field")
																.hide()
													}
												

												if (!$("#date_meet").val()) {  
													isError = true;

													$("#date_meet").addClass(
															"has-error")
													$("#date_meet_errfield")
															.show()
												} else {
													$("#date_meet_errfield")
															.hide()
												}
																						
												if (!$("#name_alumini_assoc").val()) {
													isError = true;

													$("#name_alumini_assoc").addClass(
															"has-error")
													$("#name_alumini_assoc_errfield")
															.show()
												} else {
													$("#name_alumini_assoc_errfield")
															.hide()
												}

												if ($("#almni_reg_no").val()<=0 || !$("#almni_reg_no").val()) {	
													isError = true;

													$("#almni_reg_no").addClass(
															"has-error")
													$("#almni_reg_no_errfield").show()
												} else {
													$("#almni_reg_no_errfield").hide()
												}
												
												if (!$("#date_of_almni_reg").val()) { 		
													isError = true;

													$("#date_of_almni_reg").addClass(
															"has-error")
													$("#date_of_almni_reg_errfield").show()
												} else {
													$("#date_of_almni_reg_errfield").hide()
												}
												
												if ($("#registred_almni_no").val()<=0 || !$("#registred_almni_no").val()) {		
													isError = true;

													$("#registred_almni_no").addClass(
															"has-error")
													$("#registred_almni_no_err_field").show()
												} else {
													$("#registred_almni_no_err_field").hide()
												}
												
												if ($("#registred_almni_no").val()<=0 || !$("#no_member_attnd").val()) {	
													isError = true;

													$("#no_member_attnd").addClass(
															"has-error")
													$("#no_member_attnd_errfield").show()
												} else {
													$("#no_member_attnd_errfield").hide()
												}
												if ($("#ttl_no_almni_enrolled").val()<=0 || !$("#ttl_no_almni_enrolled").val()) {  
													isError = true;

													$("#ttl_no_almni_enrolled").addClass(
															"has-error")
													$("#ttl_no_almni_enrolled_errfield").show()
												} else {
													$("#ttl_no_almni_enrolled_errfield").hide()
												}

												

												/* if (!$("#").val()) {
													isError = true;

													$("#").addClass(
															"has-error")
													$("#").show()
												} else {
													$("#").hide()
												} */


												
												
					var radioValue = $("#benif_to").val()
					//alert(radioValue);
					if(radioValue==7){
						if (!$("#other_benif").val()) {
							isError = true;

							$("#other_benif").addClass(
									"has-error")
							$("#other_benif_field").show()
						} else {
							$("#other_benif_field").hide()
						}
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

		function submit_f(view) {
			document.getElementById("is_view").value = view;//create this 

		}
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




</body>
</html>