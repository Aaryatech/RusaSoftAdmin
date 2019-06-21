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
<body class=" " onload="showIsReg()">
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
										action="${pageContext.request.contextPath}/insertStudGrievance"
										method="post" name="form_sample_2" id="form_sample_2">

										<div class="row">
											<div class="col-md-12">
											
											<input type="hidden" id="stud_griev_id" name="stud_griev_id"
												value="${redressed.redrsStudGrvncId}"> 
											

												<div class="form-group">
													<label class="control-label col-sm-3" for="status">Student 
														 Grievances <span class="text-danger">*</span>
													</label>
													<div class="col-sm-9">
														<select id="stud_griev" name="stud_griev" class="form-control">
															
															<option value="">Select</option>
															<option value="Exam Grievances" ${redressed.studGrievnce == 'Exam Grievances' ? 'selected' : ''} >Exam Grievances</option>
															<option value="Sexual Harassment" ${redressed.studGrievnce == 'Sexual Harassment' ? 'selected' : ''} >Sexual Harassment</option>
															<option value="Ragging Cases" ${redressed.studGrievnce  == 'Ragging Cases' ? 'selected' : ''} >Ragging Cases</option>
															<option value="Any Other" ${redressed.studGrievnce  == 'Any Other' ? 'selected' : ''} >Any Other</option>
														</select>
														<span class="error_form text-danger" id="stud_griev_errfield"
															style="display: none;">Please select student grievances.</span>
													</div>
												</div>
												
											<div class="form-group">
													<label class="control-label col-sm-3" for="status">Transparent 
														<span class="text-danger">*</span>
													</label>
													<div class="col-sm-9">
														<input type="radio" ${redressed.isTransparent == 1 ? 'checked' : ''} name="isTrans" value="1" onclick="studGriev0(1)">Yes<br>
														<input type="radio" ${redressed.isTransparent == 0 ? 'checked' : ''} name="isTrans" value="0" onclick="studGriev0(0)">No<br>
														<span class="error_form text-danger" id="tranc_errfield"
															style="display: none;">Please select transparent.</span>


													</div>
												</div>
												
												<div class="form-group" id="ihide0" style="display: none;">
														<div class="form-group">
															<label class="control-label col-sm-3" for="page_name"> No. of Grievances
														 Appealed<span class="text-danger">*</span>
													</label>
													<div class="col-sm-9">
														<input type="text" class="form-control" placeholder="No. of Grievances Appealed" autocomplete="off" id="trans_grivnc"
													name="trans_grivnc" value="${redressed.noTrnsprntGrievnceAppeld}" onFocus="clearDefault(this)">
													 <span class="error_form text-danger" id="error_trancGrievnc" style="display: none;">Please enter Transparent - No. of grievances.</span>
													
												</div>
												</div>
													
													<div class="form-group">
													<label class="control-label col-sm-3" for="page_name"> No. of 
														Redressed <span class="text-danger">*</span>
													</label>
													<div class="col-sm-9">
														<input type="text" class="form-control" placeholder="No. of Redressed Appealed" autocomplete="off" id="trans_redressed"
													name="trans_redressed" value="${redressed.noTrnsprntRedrsed}" onFocus="clearDefault(this)"> 
													<span class="error_form text-danger" id="error_transReAdress" style="display: none;">Please Enter Transparent - No. of redressed appealed.</span>
													
															</div>
														</div>
													</div><!-- // -->
													
													
													<!-- // -->	<div class="form-group">
													<label class="control-label col-sm-3" for="status">Time  
														Bound<span class="text-danger">*</span>
													</label>
													<div class="col-sm-9">
														<input type="radio" ${redressed.isTimeBound == 1 ? 'checked' : ''} name="isTime" value="1" onclick="studGriev1(1)">Yes<br>
														<input type="radio" ${redressed.isTimeBound == 0 ? 'checked' : ''} name="isTime" value="0" onclick="studGriev1(0)">No<br>
														<span class="error_form text-danger" id="isTime_errfield"
															style="display: none;">Please select time bound.</span>


													</div>
												</div>
												
												<div class="form-group" id="ihide1" style="display: none;">
														<div class="form-group">
															<label class="control-label col-sm-3" for="page_name"> No. of Grievances
														 Appealed<span class="text-danger">*</span>
													</label>
													<div class="col-sm-9">
														<input type="text" class="form-control" placeholder="No. of Grievances" autocomplete="off" id="time_griev"
													name="time_griev" value="${redressed.noTimeGrievnceAppeld}" onFocus="clearDefault(this)" onkeypress='return restrictAlphabets(event)'>
													 <span class="error_form text-danger" id="error_timeGriev"	style="display: none;">Please Enter Time Bound -  No. of grievances appealed.</span>
													 
												</div>
												</div>
													
													<div class="form-group">
													<label class="control-label col-sm-3" for="page_name"> No. of 
														Redressed<span class="text-danger">*</span>
													</label>
													<div class="col-sm-9">
														<input type="text" class="form-control" placeholder="No. of Redressed" autocomplete="off" id="time_redress"
													name="time_redress" value="${redressed.noTimeRedrsed}" onFocus="clearDefault(this)"> 
													<span class="error_form text-danger" id="error_timeRedres"	style="display: none;">Please Enter Time Bound - No. of redressed appealed.</span>
													
															</div>
														</div>
													</div>
													
													
													<div class="form-group">
													<label class="control-label col-sm-3" for="status">Efficient 
														<span class="text-danger">*</span>
													</label>
													<div class="col-sm-9">
														<input type="radio" ${redressed.isEfficient == 1 ? 'checked' : ''} name="isEfcint" value="1" onclick="studGriev2(1)">Yes<br>
														<input type="radio" ${redressed.isEfficient == 0 ? 'checked' : ''} name="isEfcint" value="0" onclick="studGriev2(0)">No<br>
														<span class="error_form text-danger" id="financ_errfield"
															style="display: none;">Please select financial support.</span>


													</div>
												</div>
												
												<div class="form-group" id="ihide2" style="display: none;">
														<div class="form-group">
															<label class="control-label col-sm-3" for="page_name"> No. of Grievances
														 Appealed<span class="text-danger">*</span>
													</label>
													<div class="col-sm-9">
														<input type="text" class="form-control" placeholder="No. of Grievances Appealed" autocomplete="off" id="effGriev"
													name="effGriev" value="${redressed.noEfficntGrievnceAppeld}" onFocus="clearDefault(this)">
													 <span class="error_form text-danger" id="error_effecintGriev"	style="display: none;">Please enter Efficient - No. of grievances appealed.</span>
													
												</div>
												</div>
													
													<div class="form-group">
													<label class="control-label col-sm-3" for="page_name"> No. of 
														Redressed<span class="text-danger">*</span>
													</label>
													<div class="col-sm-9">
														<input type="text" class="form-control" placeholder="dd-mm-yyyy" autocomplete="off" id="eff_redress"
													name="eff_redress" value="${redressed.noEfficntRedrsed}" onFocus="clearDefault(this)" onkeypress='return restrictAlphabets(event)'> 
													<span class="error_form text-danger" id="error_effcint_Redress"	style="display: none;">Please enter Efficient - No. of redressed.</span>
													
															</div>
														</div>
													</div><!-- // -->
												
												
												
												<div class="form-group">
													<div class="col-sm-offset-3 col-sm-9">


<button type="submit" id="sub1" class="btn btn-primary"
													onclick="submit_f(1)"><i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save</button>
														
<a href="${pageContext.request.contextPath}/showRedressdeStudGrievnce"><button id="sub2"
										type="button" class="btn btn-primary"><i class="${sessionScope.cancelIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Cancel</button></a>													</div>
												</div>

											</div>
											
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
	function clearDefault(a){
	if(a.defaultValue==0)
	{
		a.value=""
	}
	};

	  $('#trans_grivnc').on('input', function() {
		  this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');
		});
	
	 $('#trans_redressed').on('input', function() {
		  this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');
		});
	 
	 $('#time_griev').on('input', function() {
		  this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');
		});
	 
	 $('#time_redress').on('input', function() {
		  this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');
		});
	 
	 $('#effGriev').on('input', function() {
		  this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');
		});
	 
	 $('#eff_redress').on('input', function() {
		  this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');
		});
</script>
	<script type="text/javascript">
	
	
	function studGriev0(activity) {
		
		//alert(activity);
					if (activity == 1) {
						
						document.getElementById("ihide0").style = "visible"
						

					} else  {
						document.getElementById("ihide0").style = "display:none"
						
					}

				}
	
function studGriev1(activity) {
		
			//alert(activity);
					if (activity == 1) {
						
						document.getElementById("ihide1").style = "visible"
						

					} else  {
						document.getElementById("ihide1").style = "display:none"
						
					}

				}
				
				
				
function studGriev2(activity) {
	
	//alert(activity);
				if (activity == 1) {
					
					document.getElementById("ihide2").style = "visible"
					

				} else  {
					document.getElementById("ihide2").style = "display:none"
					
				}

			}
	
	
	
	
		
		function showIsReg(){
			
			var isTrans = ${redressed.isTransparent};
				if(isTrans > 0 ){
					document.getElementById("ihide0").style.display = "block";
				}else{
					document.getElementById("ihide0").style.display = "none";
				}
				
			var noTrans = ${redressed.isTimeBound};
			if(noTrans > 0 ){
					document.getElementById("ihide1").style.display = "block";
				}else{
					document.getElementById("ihide1").style.display = "none";
				}
				
			var isEffcnt= ${redressed.isEfficient};
				if(isEffcnt > 0 ){
					document.getElementById("ihide2").style.display = "block";
				}else{
					document.getElementById("ihide2").style.display = "none";
				}
				
				
				/* if(isActivity > 0 ){
					document.getElementById("ihide0").style.display = "block";
					document.getElementById("ihide1").style.display = "block";
					document.getElementById("ihide2").style.display = "block";
					
				} else{
					document.getElementById("ihide0").style.display = "none";
					document.getElementById("ihide1").style.display = "none";
					document.getElementById("ihide2").style.display = "none";
				}  */
				
			}
			
		</script>
	
	
		<script>
	//	var radioValue = $("input[name='gender']:checked"). val();
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
												
												if (!$("#stud_griev").val()) {
													isError = true;

													$("#stud_griev").addClass(
															"has-error")
													$("#stud_griev_errfield")
															.show()
													} else {
														$("#stud_griev_errfield")
																.hide()
													} 
												
													if (!$("#trans_grivnc").val()) {
														isError = true;

														$("#trans_grivnc").addClass(
																"has-error")
														$("#error_trancGrievnc")
																.show()
														} else {
															$("#error_trancGrievnc")
																	.hide()
														}
													
													if (!$("#trans_redressed").val()) {
														isError = true;

														$("#trans_redressed").addClass(
																"has-error")
														$("#error_transReAdress")
																.show()
														} else {
															$("#error_transReAdress")
																	.hide()
														}													
														
														

														if (!$("#time_griev").val()) {
															isError = true;

															$("#time_griev").addClass(
																	"has-error")
															$("#error_timeGriev")
																	.show()
															} else {
																$("#error_timeGriev")
																		.hide()
															}
														
														if (!$("#time_redress").val()) {
															isError = true;

															$("#time_griev").addClass(
																	"has-error")
															$("#error_timeRedres")
																	.show()
															} else {
																$("#error_timeRedres")
																		.hide()
															}
															
															if (!$("#effGriev").val()) {
																isError = true;

																$("#effGriev").addClass(
																		"has-error")
																$("#error_effecintGriev")
																		.show()
																} else {
																	$("#error_effecintGriev")
																			.hide()
																}
															
															if (!$("#eff_redress").val()) {
																isError = true;

																$("#eff_redress").addClass(
																		"has-error")
																$("#error_effcint_Redress")
																		.show()
																} else {
																	$("#error_effcint_Redress")
																			.hide()
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