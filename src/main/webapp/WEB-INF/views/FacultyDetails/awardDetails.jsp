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
<body class=" " onload="checkCoGuide(${award.exInt1})">
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
								<%-- <a
									href="${pageContext.request.contextPath}/showAwardDetailsList"><button
										type="button" class="btn btn-info">Back</button></a> --%>
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertAwardDetail"
										method="post" name="form_sample_2" id="form_sample_2">

										<!-- <ul class="nav nav-tabs">
											<li class="active"><a href="#home" data-toggle="tab">
													<i class="fa fa-home"></i> Register
											</a></li>

										</ul> -->

										<!-- <div class="tab-content">
											<div class="tab-pane fade in active" id="home"> -->

												<div>


													<input type="hidden" id="award_id" name="awardId"
														value="${award.awardId}">

													<div class="form-group">
														<label class="control-label col-sm-2" for="name">Name
															of Award/Recognition <span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<input type="text" class="form-control" id="name"
																autocomplete="off" name="name"
																placeholder="Name of Award/Recognition"
																value="${award.awardName}"  onchange="trim(this)">
																<span class="error_form text-danger" id="error_formfield1" style="display:none;" >Please enter name of award/recognition.</span>
														</div>
													</div>
													<div class="form-group">

														<label class="control-label col-sm-2" for="agency">Awarding
															Agency/Authority <span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<input type="text" class="form-control" id="agency"
																name="agency" placeholder="Awarding Agency"
																autocomplete="off" value="${award.awardAuthority}" onchange="trim(this)">
														<span class="error_form text-danger" id="error_formfield2" style="display:none;" >Please enter award agency.</span>
														</div>

													</div>


													<div class="form-group">
														<label class="control-label col-sm-2" for="nature">Nature
															of Award/Recognition <span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<input type="text" class="form-control" id="nature"
																autocomplete="off" name="nature"
																placeholder="Nature of Award/Recognition"
																value="${award.awardNature}" onchange="trim(this)">
																<span class="error_form text-danger" id="error_formfield3" style="display:none;" >Please enter nature of award/recognition.</span>
														</div>
													</div>
													
													<div class="form-group">
															<label class="control-label col-sm-2" for="smallheading">Incentive against Award-Recognition
																 <span class="text-danger">*</span>
															</label>


															<div class="col-sm-4">
															Yes <input type="radio" name="award_recog" id="award_recog"  ${award.exInt1 == 1 ? 'checked' : ''} 
																	 value="1" onclick="checkCoGuide(this.value)">
																	
																	 No<input type="radio" onclick="checkCoGuide(this.value)" ${award.exInt1 == 0 ? 'checked' : ''} 
																	  name="award_recog" id="award_recog" value="0">
															
															</div>
														</div>

														<div class="form-group" id="instv_form" style="display: none;">

															<label class="control-label col-sm-2"   for="smallheading">Name
																of Incentive <span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="text" class="form-control " id="incentive" autocomplete="off"  onchange="trim(this)"	 
																	name="incentive" placeholder="Name of Incentive" value="${award.exVar1}">
																<span class="error_form text-danger" id="error_formfield4" style="display:none;" >Please enter name of incentive.</span>
															</div>


														</div>


													<div class="form-group">

														<label class="control-label col-sm-2" for="date">Date
															<span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<input type="text" class="form-control datepicker"
																id="date" name="date" placeholder="dd/mm/yyyy" onkeypress='return restrictAlphabets(event)'
																autocomplete="off" value="${award.awardDate}">
																<span class="error_form text-danger" id="error_formfield5" style="display:none;" >Please enter date.</span>
														</div>

													</div>
													<div class="form-group">
														<label class="control-label col-sm-2" for="validity">Validity
															<span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<c:choose>
																<c:when test="${award.awardValidity==1}">
														Duration <input type="radio" name="validity" id="validity"
																		value="0" onclick="check1()">
															Lifetime<input onclick="check()" type="radio"
																		name="validity" id="validity" value="1" checked>
																</c:when>
																<c:otherwise>
														Duration <input type="radio" name="validity" id="validity"
																		checked value="0" onclick="check1()">
															Lifetime<input onclick="check()" type="radio"
																		name="validity" id="validity" value="1">
																</c:otherwise>
															</c:choose>


														</div>
													</div>
													<input type="hidden" id="is_view" name="is_view" value="0">

													<c:choose>
														<c:when test="${award.awardValidity==1}">
															<div id="abc" style="display: none;">
																<div class="form-group">


																	<label class="control-label col-sm-2"  
																		for="fromDate">From <span
																		class="text-danger"></span>
																	</label>
																	<div class="col-sm-6">
																		<input type="text" class="form-control datepicker"
																			autocomplete="off" placeholder="dd/mm/yyyy"
																			id="fromDate" name="fromDate" onkeypress='return restrictAlphabets(event)'
																			value="${award.awardValidityFrom}">
																			<span  class="error_form text-danger" id="error_fromToDate"	style="display: none;">From Date must be smaller than To Date </span>
																			<span class="error_form text-danger" id="error_formfield6" style="display:none;" >Please enter from date.</span>
																	</div>
																</div>

																<div class="form-group">
																	<label class="control-label col-sm-2" 
																		for="toDate">To <span class="text-danger"></span>
																	</label>
																	<div class="col-sm-6">
																		<input type="text" class="form-control datepicker"
																			autocomplete="off" id="toDate" name="toDate"
																			placeholder="dd/mm/yyyy" onkeypress='return restrictAlphabets(event)'
																			value="${award.awardValidityTo}">
																				<span class="error_form text-danger" id="error_toToDate" style="display: none;">To Date must be greater than From Date </span>
																			<span class="error_form text-danger" id="error_formfield7" style="display:none;" >Please enter to date.</span>
																	</div>

																</div>
															</div>
														</c:when>
														<c:otherwise>
															<div id="abc">
																<div class="form-group">


																	<label class="control-label col-sm-2" 
																		for="fromDate">From <span
																		class="text-danger"></span>
																	</label>
																	<div class="col-sm-6">
																		<input type="text" class="form-control datepicker"
																			autocomplete="off" placeholder="dd/mm/yyyy"
																			id="fromDate" name="fromDate" onkeypress='return restrictAlphabets(event)'
																			value="${award.awardValidityFrom}">
																				<span  class="error_form text-danger" id="error_fromToDate"	style="display: none;">From Date must be smaller than To Date </span>
																			<span class="error_form text-danger" id="error_formfield6" style="display:none;" >Please enter from date.</span>
																	</div>
																</div>

																<div class="form-group">
																	<label class="control-label col-sm-2"
																		for="toDate">To <span class="text-danger"></span>
																	</label>
																	<div class="col-sm-6">
																		<input type="text" class="form-control datepicker"
																			autocomplete="off" id="toDate" name="toDate"
																			placeholder="dd/mm/yyyy" onkeypress='return restrictAlphabets(event)'
																			value="${award.awardValidityTo}">
																			<span class="error_form text-danger" id="error_toToDate" style="display: none;">To Date must be greater than From Date </span>
																			<span class="error_form text-danger" id="error_formfield7" style="display:none;" >Please enter to date.</span>
																	</div>

																</div>
															</div>

														</c:otherwise>
													</c:choose>
	<div class="form-group">
													<div class="col-sm-offset-3 col-sm-9">


<button type="submit" id="sub1" class="btn btn-primary"
													onclick="submit_f(1)"><i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save</button>
														
<a href="${pageContext.request.contextPath}/showAwardDetailsList"><button id="sub2"
										type="button" class="btn btn-primary"><i class="${sessionScope.cancelIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Cancel</button></a>													</div>
												</div>

 

													<div class="clearfix"></div>

												</div>
											<!-- </div>
										</div> -->
									</form>
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
	
            
            	$(document).ready(function($){
            		
            		$("#form_sample_2").submit(function(e) {
            		
            			var isError=false;
            			 var errMsg="";
            				
            			
            			 if(!$("#name").val()){
        					             				
             				$("#name").addClass("has-error")
             				$("#error_formfield1").show()
             					//return false;
             				} else {
             					$("#error_formfield1").hide()
             				}
            			 if(!$("#agency").val()){
	             				
              				$("#agency").addClass("has-error")
              				$("#error_formfield2").show()
              					//return false;
              				} else {
              					$("#error_formfield2").hide()
              				}
            			 
            			 if(!$("#nature").val()){
	             				
              				$("#nature").addClass("has-error")
              				$("#error_formfield3").show()
              					//return false;
              				} else {
              					$("#error_formfield3").hide()
              				}
            			 
            			 var radioValue = $("input[name='award_recog']:checked"). val();
      					//alert(radioValue);
      					if(radioValue==1){
      						if (!$("#incentive").val()) {
      							isError = true;

      							$("#incentive").addClass(
      									"has-error")
      							$("#error_formfield4").show()
      						} else {
      							$("#error_formfield4").hide()
      						}
      					}
            			 
            			 if(!$("#date").val()){
	             				
              				$("#date").addClass("has-error")
              				$("#error_formfield5").show()
              					//return false;
              				} else {
              					$("#error_formfield5").hide()
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
     					 	$("#error_fromDate").hide()
     					 	$("#error_toDate").hide()
          		            return false;
          		           
          		        }else {
          					$("#error_fromToDate").hide();
          					$("#error_toToDate").hide();
          				}
          				
            			 
            			 var radioFrom = $("input[name='validity']:checked"). val();
       					//alert(radioValue);
       					if(radioFrom==0){
       						if (!$("#fromDate").val()) {
       							isError = true;

       							$("#fromDate").addClass(
       									"has-error")
       							$("#error_formfield6").show()
       						} else {
       							$("#error_formfield6").hide()
       						}
       						
       						if (!$("#toDate").val()) {
       							isError = true;

       							$("#toDate").addClass(
       									"has-error")
       							$("#error_formfield7").show()
       						} else {
       							$("#error_formfield7").hide()
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

	function checkCoGuide(award_recog) {
		
		if (award_recog == 1) {
//alert("Hi");
			document.getElementById("instv_form").style = "visible"
			
		} else if (award_recog == 0) {
			document.getElementById("instv_form").style = "display:none"
			
		}

	}
	
	
		function check() {

			document.getElementById("abc").style = "display:none";

			document.getElementById("fromDate").required = false;
			document.getElementById("toDate").required = false;
		}
		function check1() {
			//alert("hii");

			document.getElementById("abc").style = "visible";
			document.getElementById("fromDate").required = true;
			document.getElementById("toDate").required = true;

		}
	</script>
	<script type="text/javascript">
		function submit_f(view) {
			//alert(view);
			document.getElementById("is_view").value = view;

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