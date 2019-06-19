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


<!-- BEGIN BODY --><!-- onload="showIsReg(${alumni.exInt1})" -->
<body class=" " onload="checkPhdGuide(${facultyEmpowr.financialSupport})">
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
										action="${pageContext.request.contextPath}/insertFacEmpower"
										method="post" name="form_sample_2" id="form_sample_2">

										<div class="row">
											<div class="col-md-12">

												<div class="form-group">
													<label class="control-label col-sm-3" for="status">Name of
														 Activity <span class="text-danger">*</span>
													</label>
													<div class="col-sm-9">
														<select id="actvity_name" name="actvity_name" class="form-control">
															
																	<option value="Conference" ${facultyEmpowr.nameOfAcitvity eq 'Conference' ? 'Selected' : ''}>Conference</option>
																	<option value="Workshop" ${facultyEmpowr.nameOfAcitvity eq 'Workshop' ? 'Selected' : ''}>Workshop</option>
																	<option value="Professional Membership" ${facultyEmpowr.nameOfAcitvity eq 'Professional Membership' ? 'Selected' : ''}>Professional Membership</option>
																	<option value="FDP" ${facultyEmpowr.nameOfAcitvity eq 'FDP' ? 'Selected' : ''}>FDP</option>
																	<option value="STTP" ${facultyEmpowr.nameOfAcitvity eq 'STTP' ? 'Selected' : ''}>STTP</option>
																	<option value="Seminar" ${facultyEmpowr.nameOfAcitvity eq 'Seminar' ? 'Selected' : ''}>Seminar</option>
																	<option value="Refresher Course" ${facultyEmpowr.nameOfAcitvity eq 'Refresher Course' ? 'Selected' : ''}>Refresher Course</option>
														</select>
													</div>
												</div>
												
												<div class="form-group">
													<label class="control-label col-sm-3" for="page_name">Title
														 <span class="text-danger">*</span>
													</label>
													<div class="col-sm-9">
														<input type="text" class="form-control" onchange="trim(this)"
															placeholder=" Enter Tille" id="title"
															value="${facultyEmpowr.title}" name="title" autocomplete="off">
															<span class="error_form text-danger" id="title_errfield"
															style="display: none;">Please enter title.</span>
													</div>
												</div>

												<div class="form-group">
													<label class="control-label col-sm-3" for="status">Financial 
														Support <span class="text-danger">*</span>
													</label>
													<div class="col-sm-9">
														<input type="radio" ${facultyEmpowr.financialSupport == 1 ? 'checked' : ''} name="financial_suprt" id="financial_suprt"  value="1" onclick="checkPhdGuide(1)">Yes<br>
														<input type="radio" ${facultyEmpowr.financialSupport == 0 ? 'checked' : ''} name="financial_suprt"  id="financial_suprt" value="0" onclick="checkPhdGuide(0)">No<br>
														<span class="error_form text-danger" id="financ_errfield"
															style="display: none;">Please select financial support</span>


													</div>
												</div>
												
												<div class="form-group" id="ihide" style="display: none;">
														<div class="form-group">
															<label class="control-label col-sm-3" for="smallheading">Amount 
																Received From<span class="text-danger">*</span>
															</label>
															<div  class="col-sm-9">
																<select id="amt_rcvd_frm" name="amt_rcvd_frm" class="form-control">
																	<!-- <option > Financial Support </option> -->
																	<option value="Institute" ${facultyEmpowr.amt_recvd_from eq 'Institute'? 'Selected' : ''}>Institute</option>
																	<option value="Individual" ${facultyEmpowr.amt_recvd_from eq'Individual'? 'Selected' : ''}>Individual</option>
																	<option value="Government" ${facultyEmpowr.amt_recvd_from eq 'Government'? 'Selected' : ''}>Government</option>
																	<option value="Non Government" ${facultyEmpowr.amt_recvd_from eq 'Non Government'? 'Selected' : ''}>Non Government</option>
																	<option value="Philanthropers" ${facultyEmpowr.amt_recvd_from eq 'Philanthropers'? 'Selected' : ''}>Philanthropers</option>
																	
															</select>
															</div>
														</div>
														
														<div class="form-group">
													<label class="control-label col-sm-3" for="page_name">Amount(Rs.)
														 <span class="text-danger">*</span>
													</label>
													<div class="col-sm-9">
														<input type="text" class="form-control" onchange="trim(this)"
															placeholder=" Amount(Rs.)" id="amount"
															value="${facultyEmpowr.exVar1}" name="amount" autocomplete="off">
															<span class="error_form text-danger" id="amt_errfield"
															style="display: none;">Please enter Amount(Rs.).</span>
													</div>
												</div>
													</div>



												<div class="form-group">
													<label class="control-label col-sm-3" for="page_name"> From
														Date<span class="text-danger">*</span>
													</label>
													<div class="col-sm-9">
														<input type="text" class="form-control datepicker"
													placeholder="dd-mm-yyyy" autocomplete="off" id="fromDate"
													name="fromDate" value="${facultyEmpowr.fromDate}"> <span
													class="error_form text-danger" id="error_fdate"
													style="display: none;">Please Enter From Date.</span>
													
													<span
													class="error_form text-danger" id="error_fromToDate"
													style="display: none;">From Date must be smaller than To Date. </span>
													</div>
												</div>



												<div class="form-group">
													<label class="control-label col-sm-3" for="status">To
														Date <span class="text-danger">*</span>
													</label>
													<div class="col-sm-9">
														<input type="text" class="form-control datepicker"
													autocomplete="off" id="toDate" name="toDate"
													placeholder="dd-mm-yyyy" value="${facultyEmpowr.toDate}"> <span
													class="error_form text-danger" id="error_tdate"
													style="display: none;">Please Enter To Date.</span>
													
													<span
													class="error_form text-danger" id="error_toToDate"
													style="display: none;">To Date must be greater than From Date. </span>

													</div>
												</div>

												<div class="form-group">
													<div class="col-sm-offset-3 col-sm-9">


<button type="submit" id="sub1" class="btn btn-primary"
													onclick="submit_f(1)"><i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save</button>
														
<a href="${pageContext.request.contextPath}/showFacultyEmpowerment"><button id="sub2"
										type="button" class="btn btn-primary"><i class="${sessionScope.cancelIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Cancel</button></a>													</div>
												</div>

											</div>
											<input type="hidden" id="fac_empwr_id" name="fac_empwr_id"
												value="${facultyEmpowr.facultyEmpwrmntId}"> 

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
												
												if (!$("#title").val()) {
													isError = true;

													$("#title").addClass(
															"has-error")
													$("#title_errfield")
															.show()
													} else {
														$("#title_errfield")
																.hide()
													}
												
												
												if (!$("#fromDate").val()) {

													isError = true;

													$("#fromDate").addClass("has-error")
													$("#error_fdate").show()
													//return false;
												} else {
													$("#error_fdate").hide();
												}

												if (!$("#toDate").val()) {

													isError = true;

													$("#toDate").addClass("has-error")
													$("#error_tdate").show()
													//return false;
												} else {
													$("#error_tdate").hide();
												}
												
												
												
												
												
												var from_date = document.getElementById("fromDate").value;
								 				var to_date = document.getElementById("toDate").value;
								 				
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
												 	$("#error_fdate").hide();
												 	$("#error_tdate").hide();
								 		            return false;
								 		           
								 		        }else {
								 					$("#error_fromToDate").hide();
								 					$("#error_toToDate").hide();
								 				}

												 
												
								 		       var radioValue = $("input[name='financial_suprt']:checked").val();
								 		       //alert(radioValue);
								 		        
								 		        if(radioValue==1){
								 		        	
								 		        	if (!$("#amount").val()  || $("#amount").val() <= 0) {
														isError = true;

														$("#amount").addClass(
																"has-error")
														$("#amt_errfield")
																.show()
														} else {
															$("#amt_errfield")
																	.hide()
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
		function showExtraField() {
			//alert("hii");
			//document.getElementById("abc").style = "display:none"
			var qualType = document.getElementById("benif_to").value
			//alert("qualType::"+qualType);

			if (qualType == 7) {

				document.getElementById("abc").style = "visible"
				//document.getElementById("other_benif").setAttribute("required",
						//"true");

			} else {
				document.getElementById("abc").style = "display:none"
				//document.getElementById("other_benif").removeAttribute(
						//"required");

			}

		}
	</script>
	<script type="text/javascript">
		function hideText() {
			//alert("hii");
			var qualType = document.getElementById("benif_to").value
			// alert("x " +qualType);
			if (qualType == 7) {
				//alert("In If " +x);
				document.getElementById("abc").style = "visible";
				//document.getElementById("other_benif").setAttribute("required",
						//"true");

			} else {
				document.getElementById("abc").style = "display:none"
			}

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
	
	
	
	<!-- END CONTAINER -->
	<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->




</body>
</html>