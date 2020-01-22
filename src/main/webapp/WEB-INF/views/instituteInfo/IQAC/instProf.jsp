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
				<!-- MAIN CONTENT AREA STARTS -->

				<div class="col-lg-12"></div>



				<div class="col-lg-12">
					<section class="box ">

						<header class="panel_header">
							<h2 class="title pull-left">${title}</h2>

							<div class="actions panel_actions pull-right">
							<%-- 	<a href="${pageContext.request.contextPath}/showInstProfList"><button
										type="button" class="btn btn-info">Back</button></a> --%>
								<!-- <a
									class="box_toggle fa fa-chevron-down"></a> -->
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertIqacBasicInfo"
										method="post" name="form_sample_2" id="form_sample_2"><!--   novalidate="novalidate" -->

												<div>

													<div class="form-group">
														<label class="control-label col-sm-2" for="page_name">
															IQAC Establishment Date <span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<input type="text" class="form-control datepicker"
																id="estb_date" name="estb_date" placeholder="dd-MM-YYYY"
																placeholder="Date of Establishment of IQAC " 
																value="${date}">
												 <span class="error_form text-danger" id="error_eDate" style="display:none;" >Please Select Establishment Date  </span>
																
														</div>
													</div>



													<div class="form-group">
														<label class="control-label col-sm-2" for="page_order">
															Alternate Faculty Name <span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<input type="text" class="form-control"
																id="alt_faculty_name" name="alt_faculty_name" onchange="trim(this)"
																placeholder="Name of alternate Faculty with IQAC"
																 value="${instRes.iqacAltName}"
																>
															 <span class="error_form text-danger" id="error_name" style="display:none;" >Please Enter Name  </span>
														</div>
													</div>
													
											
											<div class="form-group">
											<label class="control-label col-sm-2" for="page_order">Designation
												<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<select id="designation" name="designation"
													class="form-control" onchange="showForm()">

													<c:forEach items="${desigList}" var="makeList">
														<c:choose>
															<c:when
																test="${makeList.designationId == instRes.exVar1}"> <!-- instRes.exVar1 == makeList.designationId -->
																<option value="${makeList.designationId}"
																	selected="selected">${makeList.designationName}</option>
															</c:when>
															<c:otherwise>
																<option value="${makeList.designationId}">${makeList.designationName}</option>
															</c:otherwise>
														</c:choose>
													</c:forEach>

												</select>
												<span class="error_form text-danger" id="error_formfield2" style="display:none;" >Please select designation</span>
											</div>
										</div>

													<div class="form-group">
														<label class="control-label col-sm-2" for="page_order">Mobile
															No <span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<input type="text" class="form-control"
																id="alt_fac_contact" 
																maxlength="10" name="alt_fac_contact"
																placeholder="Mobile No of Alternate Faculty Associated"
																value="${instRes.iqacAltMobile}" >
																
													 <span class="error_form text-danger" id="error_contact" style="display:none;" >Please Enter Mobile Number  </span>
														</div>
													</div>

													<div class="form-group">
														<label class="control-label col-sm-2" for="page_order">Phone
															No<span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<input type="text" class="form-control" id="phone_no"
															 maxlength="10"
																name="phone_no"
																placeholder="Phone No of Alternate Faculty"
																value="${instRes.iqacAltPhone}" >
											 <span class="error_form text-danger" id="error_contact1" style="display:none;" >Please Enter Phone Number   </span>
														</div>
													</div>

													<div class="form-group">
														<label class="control-label col-sm-2" for="page_order">FAX
															No.<span class="text-danger"></span>
														</label>
														<div class="col-sm-6">
															<input type="text"
																 maxlength="10" class="form-control"
																id="fax_no" value="${instRes.iqacAltFax}" name="fax_no"
																placeholder="FAX No of Alternate Faculty Associated"
																>
									 <span class="error_form text-danger" id="error_fax_no" style="display:none;" >Please Enter Fax Number Properly </span>
														</div>
													</div>

													<div class="form-group">
														<label class="control-label col-sm-2" for="page_order">
															Registered Email <span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<input type="email" class="form-control"
																id="registered_email" 
																 value="${instRes.iqacAltEmail1}"
																name="registered_email" placeholder="abc@xyz.com" 
																>
										 <span class="error_form text-danger" id="error_rEmail" style="display:none;" >Please Enter Email  </span>		
					
														</div>
													</div>

													<div class="form-group">
														<label class="control-label col-sm-2" for="page_order">Alternate
															Email <span class="text-danger"></span>
														</label>
														<div class="col-sm-6">
															<input type="email" class="form-control" id="alt_email" 
																value="${instRes.iqacAltEmail2}" name="alt_email"
																placeholder="abc@xyz.com " >
																
								 <span class="error_form text-danger" id="error_aEmail" style="display:none;" >Please Enter Email Id Properly </span>
														</div>
													</div>



													<c:choose>
														<c:when test="${instRes.iqacInfoId!=0}">
															<input type="hidden" id="iqac_info_id"
																name="iqac_info_id" value="${instRes.iqacInfoId}">
														</c:when>
														<c:otherwise>
															<input type="hidden" id="iqac_info_id"
																name="iqac_info_id" value="0">
														</c:otherwise>

													</c:choose>

												<div class="form-group">
													<div class="col-sm-offset-3 col-sm-9">


												<button type="submit" id="sub_button" class="btn btn-primary"
												onclick="submit_f(1)"><i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save</button>
														
												<a href="${pageContext.request.contextPath}/showInstProfList"><button id="sub2"
													type="button" class="btn btn-primary"><i class="${sessionScope.cancelIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Cancel</button></a>													</div>
												</div>


<!-- 
												</div>
											</div> -->


											<div class="clearfix"></div>


										</div>
									</form>
									
									<p class="desc text-danger fontsize11">Notice : * Fields
										are Mandatory.</p>
								</div>

							</div>

						</div>
					</section>
				</div>

			</section>
		</section>

	</div>
	<!-- MAIN CONTENT AREA ENDS -->
	<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>

	<!-- END CONTENT -->
<script>
$('#alt_fac_contact').on(
		'input',
		function() {
			this.value = this.value.replace(/[^0-9]/g, '').replace(
					/(\..*)\./g, '$1');
		});
		
$('#phone_no').on(
		'input',
		function() {
			this.value = this.value.replace(/[^0-9]/g, '').replace(
					/(\..*)\./g, '$1');
		});
		
$('#fax_no').on(
		'input',
		function() {
			this.value = this.value.replace(/[^0-9]/g, '').replace(
					/(\..*)\./g, '$1');
		});

function trim(el) {
	el.value = el.value.replace(/(^\s*)|(\s*$)/gi, ""). // removes leading and trailing spaces
	replace(/[ ]{2,}/gi, " "). // replaces multiple spaces with one space 
	replace(/\n +/, "\n"); // Removes spaces after newlines
	return;
}  
            //
            function validateEmail(registered_email) {
          //  alert("hii....validateEmail");
            	var eml = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
            
            	if (eml.test($.trim(registered_email)) == false) {
            
            
            	return false;
            
            	}
            
            	return true;
            
            }
            function validateEmail(alt_email) {
                //  alert("hii....validateEmail");
                  	var eml = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
                  
                  	if (eml.test($.trim(alt_email)) == false) {
                  
                  
                  	return false;
                  
                  	}
                  
                  	return true;
                  
                  }
             function validateMobile(phone_no) {
            	 // alert("hii....validateMobile");
            		var mob = /^[1-9]{1}[0-9]{9}$/;
            
            
            		if (mob.test($.trim(phone_no)) == false) {
            
            		//alert("Please enter a valid email address .");
            		return false;
            
            		}
            		return true;
            
             }
             
             function validateMobile(alt_fac_contact) {
            	 // alert("hii....validateMobile");
            		var mob = /^[1-9]{1}[0-9]{9}$/;
            
            
            		if (mob.test($.trim(alt_fac_contact)) == false) {
            
            		//alert("Please enter a valid email address .");
            		return false;
            
            		}
            		return true;
            
             }
function numbersOnlyNotZero(fax_no) {

                 
                 var mob = /^[1-9][0-9]+$/;


                 if (mob.test($.trim(fax_no)) == false) {

                     //alert("Please enter a valid email address .");
                     return false;

                 }
                 return true;
             }



            
            	$(document).ready(function($){
            //	alert("hii....");
            		$("#form_sample_2").submit(function(e) {
            			 var isError=false;
            			 var errMsg="";
            				
           
            				if(!$("#alt_faculty_name").val()){
            					 
            				isError=true;
            				errMsg += '<li>Please enter a valid Name.</li>';
            				
            				$("#alt_faculty_name").addClass("has-error")
            				$("#error_name").show()
            					//return false;
            				} else {
            					$("#error_name").hide()
            				}
            				
            				if(!$("#estb_date").val()){
           					 
                				isError=true;
                				errMsg += '<li>Please enter a valid Name.</li>';
                				
                				$("#estb_date").addClass("has-error")
                				$("#error_eDate").show()
                					//return false;
                				} else {
                					$("#error_eDate").hide()
                				}
                				
            				
            				 
            				if(!$("#alt_fac_contact").val() || !validateMobile($("#alt_fac_contact").val())){
            
            				isError=true;
            				errMsg += '<li>Please enter a valid email address.</li>';
            				errMsg_alert = 'Please enter a valid mobile number.';
            				$("#error_contact").html(errMsg_alert);
            				$("#error_contact").show();
            				//alert();
            					//return false;
            				} else {
            				
            					$("#error_contact").hide()
            				}
            				
            				
            				 
            				if( !validateMobile($("#phone_no").val())){
            
            				isError=true;
            				errMsg += '<li>Please enter a valid email address.</li>';
            				errMsg_alert = 'Please enter a valid mobile number.';
            				$("#error_contact1").html(errMsg_alert);
            				$("#error_contact1").show();
            				//alert();
            					//return false;
            				} else {
            				
            					$("#error_contact1").hide()
            				}
            				
            				
            				if(!$("#registered_email").val() || !validateEmail($("#registered_email").val())){
            
            				isError=true;
            				errMsg += '<li>Please enter a valid email address.</li>';
            				errMsg_alert += 'Please enter a valid email address. \n';
            				$("#error_rEmail").show()
            					//return fregister_useralse;
            				} else {
            					$("#error_rEmail").hide()
            				}
            				
            				

            				if( !validateEmail($("#alt_email").val())){
            
            				isError=true;
            				errMsg += '<li>Please enter a valid email address.</li>';
            				errMsg_alert += 'Please enter a valid email address. \n';
            				$("#error_aEmail").show()
            					//return fregister_useralse;
            				} else {
            					$("#error_aEmail").hide()
            				}
            				
            				if(!$("#fax_no").val() || !numbersOnlyNotZero($("#fax_no").val())){
            		            
                				isError=true;
                				errMsg += '<li>Please enter a valid email address.</li>';
                				errMsg_alert += 'Please enter a valid email address. \n';
                				$("#error_fax_no").show()
                				} else {
                					$("#error_fax_no").hide()
                				}
                				

            				 if (!isError) {
			            		 
 								var x = confirm("Do you really want to submit the form?");
 								if (x == true) {
 									
 									document.getElementById("sub_button").disabled = true;
 									document.getElementById("sub2").disabled = true;
 									return  true;
 								}
 							}
            					   return false;
            				} );
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
	</script>

	<script type="text/javascript">
		function submit_f(view) {
			document.getElementById("is_view").value = view;//create this 
			/* var form=document.getElementById("form_sample_2");
			form.setAttribute("method", "post");

			form.action=("insertLibrarian");
			var x =confirm();
			if(x==true)
			form.submit(); */

		}

	/* 	var wasSubmitted = false;
		function checkBeforeSubmit() {
			if (!wasSubmitted) {
				var x = confirm("Do you really want to submit the form?");
				if (x == true) {
					wasSubmitted = true;
					document.getElementById("sub1").disabled = true;
					//  document.getElementById("sub2").disabled=true;

					return wasSubmitted;
				}
			}
			return false;
		} */
	</script>



	<!-- END CONTAINER -->
	<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->




</body>
</html>