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
							<%-- <h1 class="title">${title}</h1> --%>
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

							<%-- <div class="actions panel_actions pull-right">
								<a href="${pageContext.request.contextPath}/showAddPublicationDetailsList"><button
										type="button" class="btn btn-info">Back</button></a>
							</div> --%>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertFacultyConf"
										method="post" name="form_sample_2" id="form_sample_2">

										<ul class="nav nav-tabs">
											<!-- <li class="active"><a href="#home" data-toggle="tab">
													<i class="fa fa-home"></i> Register
											</a></li> -->

										</ul>

										<div class="tab-content">
											<div class="tab-pane fade in active" id="home">

												<div>


													<div class="col-xs-12">


														<!-- <h5 class="title pull-left">
																<strong>Publication/Presentation Details</strong>
															</h5> -->

														<div class="col-xs-12"></div>

														<input type="hidden" class="form-control" id="conf_id"
															name="conf_id" value="${facConf.confId}">

														<div class="form-group">

															<label class="control-label col-sm-2" for="smallheading">Title
																of Publication <span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="text" class="form-control" id="conf_name"
																	onchange="trim(this)" name="conf_name"
																	placeholder="Title of Publication" autocomplete="off"
																	value="${facConf.confName}" >
																	<span class="error_form text-danger" id="error_formfield1" style="display:none;" >Please enter publication title.</span>
															</div>


														</div>

														<div class="form-group">
															<label class="control-label col-sm-2" for="page_order">Conference
																Attended <span class="text-danger">*</span>
															</label>


															<div class="col-sm-6">
																<c:choose>
																	<c:when test="${facConf.confType eq 'national'}">
																
																	National <input type="radio" name="conf_type"
																			id="conf_type" checked value="national">&nbsp;&nbsp;&nbsp; 
																		
																	International <input type="radio" name="conf_type"
																			id="conf_type" value="international">&nbsp;&nbsp;&nbsp;
																	 
																	State <input type="radio" name="conf_type"
																			id="conf_type" value="state">
																	</c:when>
																	<c:when test="${facConf.confType eq 'international'}">
																
																	National <input type="radio" name="conf_type"
																			id="conf_type" value="national">&nbsp;&nbsp;&nbsp; 
																		
																	International <input type="radio" name="conf_type"
																			id="conf_type" checked value="international">&nbsp;&nbsp;&nbsp;
																	 
																	State <input type="radio" name="conf_type"
																			id="conf_type" value="state">
																	</c:when>
																	<c:when test="${facConf.confType eq 'state'}">
																
																	National <input type="radio" name="conf_type"
																			id="conf_type" value="national">&nbsp;&nbsp;&nbsp; 
																		
																	International <input type="radio" name="conf_type"
																			id="conf_type" value="international">&nbsp;&nbsp;&nbsp;
																	 
																	State <input type="radio" name="conf_type"
																			id="conf_type" checked value="state">
																	</c:when>

																	<c:otherwise>
																	National <input type="radio" name="conf_type"
																			id="conf_type" checked value="national">&nbsp;&nbsp;&nbsp; 
																		
																	International <input type="radio" name="conf_type"
																			id="conf_type" value="international">&nbsp;&nbsp;&nbsp;
																	 
																	State <input type="radio" name="conf_type"
																			id="conf_type" value="state">
																	</c:otherwise>
																</c:choose>
															</div>
														</div>

														<div class="form-group">



															<label class="control-label col-sm-2" for="smallheading">Date
																<span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="text" class="form-control datepicker"
																	id="conf_date" autocomplete="off"
																	onkeypress='return restrictAlphabets(event)'
																	name="conf_date" placeholder="dd/mm/yyyy"
																	value="${facConf.confDate}" onchange="trim(this)">
																	<span class="error_form text-danger" id="error_formfield2" style="display:none;" >Please enter valid date.</span>
															</div>
														</div>

														<div class="form-group">
															<label class="control-label col-sm-2" for="smallheading">Venue
																<span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="text" class="form-control" id="conf_venue"
																	onchange="trim(this)" name="conf_venue" autocomplete="off"
																	placeholder="Venue" value="${facConf.confVenue}">
																	<span class="error_form text-danger" id="error_formfield3" style="display:none;" >Please enter venue.</span>
															</div>



														</div>

														<div class="form-group">

															<label class="control-label col-sm-2" for="smallheading">Funding
																From <span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="text" class="form-control" id="conf_fund"
																	onchange="trim(this)" name="conf_fund"
																	placeholder="Funding from" autocomplete="off"
																	value="${facConf.confFundFrom}">
																	<span class="error_form text-danger" id="error_formfield4" style="display:none;" >Please enter funding from.</span>
															</div>
														</div>

														<div class="form-group">

															<label class="control-label col-sm-2" for="smallheading">Amount
																<span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="text" min="0" class="form-control"
																	id="conf_amt" autocomplete="off"
																	onkeypress='return restrictAlphabets(event)'
																	name="conf_amt" placeholder="Amount"
																	value="${facConf.confFundAmt}" onchange="trim(this)">
																<span class="error_form text-danger" id="error_formfield5" style="display:none;" >Please enter amount and value must be greater than 0.</span>
															</div>

														</div>


														<input type="hidden" id="is_view" name="is_view" value="0">


														<div class="form-group">
															<div class="col-sm-offset-2 col-sm-10">
																<input type="submit" id="sub1" class="btn btn-primary"
																	onclick="submit_f(1)" value="Save"> 
																<a href="${pageContext.request.contextPath}/showAddPublicationDetailsList"><button
																id="sub2" type="button" class="btn btn-primary">Cancel</button></a>
															</div>
														</div>

													</div>


													<div class="clearfix"></div>

												</div>

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
            	$(document).ready(function($){
            		
            		$("#form_sample_2").submit(function(e) {
            		
            			var isError=false;
            			 var errMsg="";
            				
           				if(!$("#conf_name").val()){
            					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#conf_name").addClass("has-error")
            				$("#error_formfield1").show()
            					//return false;
            				} else {
            					$("#error_formfield1").hide()
            				}
           				if(!$("#conf_date").val()){
        					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#conf_date").addClass("has-error")
            				$("#error_formfield2").show()
            					//return false;
            				} else {
            					$("#error_formfield2").hide()
            				}
            				
            				
           				if(!$("#conf_venue").val()){
       					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#conf_date").addClass("has-error")
            				$("#error_formfield3").show()
            					//return false;
            				} else {
            					$("#error_formfield3").hide()
            				}
           				
           				if(!$("#conf_fund").val()){
          					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#conf_date").addClass("has-error")
            				$("#error_formfield4").show()
            					//return false;
            				} else {
            					$("#error_formfield4").hide()
            				}
           				
           				if($("#conf_amt").val()==0 || !$("#conf_amt").val()){
         					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#conf_fund").addClass("has-error")
            				$("#error_formfield5").show()
            					//return false;
            				} else {
            					$("#error_formfield5").hide()
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
		function submit_f(view) {
			//alert(view);
			document.getElementById("is_view").value = view;

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
</body>
</html>