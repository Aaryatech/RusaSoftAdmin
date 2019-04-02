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
								<a href="${pageContext.request.contextPath}/showFunctionalMOUs"
									data-toggle="modal"><button type="submit"
										class="btn btn-info">Back</button></a>
								<%-- <a
									href="${pageContext.request.contextPath}/publicationList"><button
										type="button" class="btn btn-info">Back</button></a> --%>
								<!-- <a
									class="box_toggle fa fa-chevron-down"></a> -->
							</div>


						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertFunctionalMOU"
										method="post" novalidate="novalidate"
										name="form_sample_2" id="form_sample_2"
										>

									<%-- 	<ul class="nav nav-tabs">
											<li class="active"><a href="#home" data-toggle="tab">
													<i class="fa fa-home"></i> ${title}
											</a></li>

										</ul>
 --%>
										<!-- <div class="tab-content">
											<div class="tab-pane fade in active" id="home">

 -->

												<div class="form-group">

													<label class="control-label col-sm-2" for="title">Title of MoU 
														<span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="title"
															autocomplete="off" name="title"
															placeholder="Title of MoU" value="${editInst.mouTitle}"
															>
															
					<span class="error_form text-danger" id="error_name" style="display:none;" >Please Enter Title of MoU </span>
													</div>
												</div>

												<div class="form-group">
													<label class="control-label col-sm-2" for="functionalMOU">Functional MoU with Agency
													 <span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<select id="MOU_agency" name="MOU_agency"
															onchange="showForm()" class="form-control" >
															<option value="-1">Select</option>	
															
													<c:choose>
															<c:when test="${'IIT'== editInst.mouAgency}">
															<option selected value="IIT">IIT</option>
															<option value="NIT">NIT</option>
															<option value="IIIT">IIIT</option>
															<option value="University">University</option>
															<option value="Industries">Industries</option>
															<option value="Corporate Houses">Corporate
																Houses</option>
															<option  value="7">Any other Institute of
																International/National/State Importance</option>
															</c:when>
															<c:when test="${'NIT'== editInst.mouAgency}">
															<option value="IIT">IIT</option>
															<option selected value="NIT">NIT</option>
															<option value="IIIT">IIIT</option>
															<option value="University">University</option>
															<option value="Industries">Industries</option>
															<option value="Corporate Houses">Corporate
																Houses</option>
															<option  value="7">Any other Institute of
																International/National/State Importance</option>
															</c:when>
															<c:when test="${'IIIT'== editInst.mouAgency}">
															<option value="IIT">IIT</option>
															<option value="NIT">NIT</option>
															<option selected value="IIIT">IIIT</option>
															<option value="University">University</option>
															<option value="Industries">Industries</option>
															<option value="Corporate Houses">Corporate
																Houses</option>
															<option  value="7">Any other Institute of
																International/National/State Importance</option>
															</c:when>
															<c:when test="${'University'== editInst.mouAgency}">
															<option value="IIT">IIT</option>
															<option value="NIT">NIT</option>
															<option value="IIIT">IIIT</option>
															<option selected value="University">University</option>
															<option value="Industries">Industries</option>
															<option value="Corporate Houses">Corporate
																Houses</option>
															<option  value="7">Any other Institute of
																International/National/State Importance</option>
															</c:when>
															<c:when test="${'Industries'== editInst.mouAgency}">
															<option value="IIT">IIT</option>
															<option value="NIT">NIT</option>
															<option value="IIIT">IIIT</option>
															<option value="University">University</option>
															<option selected value="Industries">Industries</option>
															<option value="Corporate Houses">Corporate
																Houses</option>
															<option  value="7">Any other Institute of
																International/National/State Importance</option>
															</c:when>
															<c:when test="${'Corporate Houses'== editInst.mouAgency}">
															<option value="IIT">IIT</option>
															<option value="NIT">NIT</option>
															<option value="IIIT">IIIT</option>
															<option value="University">University</option>
															<option  value="Industries">Industries</option>
															<option selected value="Corporate Houses">Corporate Houses
																</option>
															<option  value="7">Any other Institute of
																International/National/State Importance</option>
															</c:when>
															
															<c:otherwise>
														
															<option value="IIT">IIT</option>
															<option value="NIT">NIT</option>
															<option value="IIIT">IIIT</option>
															<option value="University">University</option>
															<option value="Industries">Industries</option>
															<option value="Corporate Houses">Corporate
																Houses</option>
															<option selected value="7">Any other Institute of
																International/National/State Importance</option>
																</c:otherwise>
												</c:choose>
														</select>
		<span class="error_form text-danger" id="error_mou" style="display:none;" >Please Select Functional MoU with Agency</span>						
													</div>
												</div>


												<div class="form-group" id="abc" style = "display:none">

													<label class="control-label col-sm-2" for="page_name">Other
														Course
													</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="otherCourse"
															autocomplete="off"  name="otherCourse"
															placeholder="" value="${editInst.mouAgency}">
					<span class="error_form text-danger" id="error_other" style="display:none;" >Please Enter Other Course</span>	
													</div>
								
												</div>



												<div class="form-group">

													<label class="control-label col-sm-2" for="fromDate">From
														Date <span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="text" class="form-control datepicker"
															autocomplete="off" id="fromDate" name="fromDate"
															value="${fdate}" >
					<span class="error_form text-danger" id="error_fdate" style="display:none;" >Please Enter From Date</span>	

													</div>
												</div>

												<div class="form-group">

													<label class="control-label col-sm-2" for="toDate">To
														Date <span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="text" class="form-control datepicker"
															autocomplete="off" id="toDate" name="toDate"
															value="${tdate}" >
		<span class="error_form text-danger" id="error_tdate" style="display:none;" >Please Enter To Date</span>	
		
													</div>
												</div>





												<div class="form-group">

													<label class="control-label col-sm-2" for="beneficiaryMOU">Beneficiary 	of MoU
													<span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">

														<select id="beneficiaryMOU" name="beneficiaryMOU"
															class="form-control" >
															<!-- <option value="-1">Select</option>
															<option value="Students">Students</option>
															<option value="Staff">Staff</option>
															<option value="Students And Staff">Students And Staff</option> -->

															<option value="-1">Select</option>

																<c:choose>
															<c:when test="${'Students'== editInst.mouBeneficiary}">
															<option value="-1">Select</option>		
															<option selected value="Students">Students</option>
															<option value="Staff">Staff</option>
															<option value="Students And Staff">Students And Staff</option>
																			</c:when>
																			<c:when test="${'Staff'==editInst.mouBeneficiary}">
																	<option value="-1">Select</option>			        
															<option value="Students">Students</option>
															<option selected value="Staff">Staff</option>
															<option value="Students And Staff">Students And Staff</option>
																			</c:when>
																			
															<c:when test="${'Students And Staff'==editInst.mouBeneficiary}">
															<option value="-1">Select</option>			
															<option value="Students">Students</option>
															<option value="Staff">Staff</option>
															<option selected value="Staff">Students And Staff</option>

																			</c:when>
																			
																			
																	<c:otherwise>
																		<option selected value="-1">Select</option>
															<option value="Students">Students</option>
															<option value="Staff">Staff</option>
															<option value="Students And Staff">Students And Staff</option>

																			</c:otherwise>

																		</c:choose>

														</select>
		<span class="error_form text-danger" id="error_benfmou" style="display:none;" >Please Select Beneficiary of MoU</span>						

													</div>
												</div>

												<div class="form-group">

													<label class="control-label col-sm-2" for="beneficiaryMOU">No. of Beneficiary Participants
														<span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="number" min="0" class="form-control"
															id="beneficiaryMOUNo" name="beneficiaryMOUNo"
															placeholder="No. of Beneficiary Participants "
															value="${editInst.mouBeneficiaryNos}" >
		<span class="error_form text-danger" id="error_part" style="display:none;" >Please Enter No. of Beneficiary Participants</span>						

													</div>
												</div>


												<div class="form-group">

													<label class="control-label col-sm-2" for="instituteOfMoU">Institute of MoU
														of MoU <span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="text" class="form-control"
															id="instituteOfMoU" autocomplete="off"
															name="instituteOfMoU" placeholder="Institute of MoU"
															value="${editInst.mouInstitute}" >
				<span class="error_form text-danger" id="error_inst" style="display:none;" >Please Institute of MoU</span>						
		
													</div>
												</div>
												

      										 <input type="hidden" id="mou_id" name="mou_id"value="${editInst.mouId}">
                                             	<input type="hidden" id="is_view" name="is_view" value="0">
                                             	
                                             	
												<div class="form-group">
													<div class="col-sm-offset-2 col-sm-10">
														<input type="submit" id="sub1" class="btn btn-primary"
															onclick="submit_f(1)"  id="sub2" value="Save"> <input
															type="submit" class="btn btn-primary"
															onclick="submit_f(0)"
															value="Save &
																		Next">
														<button type="reset" class="btn btn-default">Reset</button>
													</div>
												</div>



												<div class="clearfix"></div>



<!-- 
											</div>
										</div> -->
									</form>
									<p class="desc text-danger fontsize11">Notice : * Fields
										are Mandatory.</p>
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
           
             function numbersOnlyNotZero(id_number) {

                 
                 var mob = /^[1-9][0-9]+$/;


                 if (mob.test($.trim(id_number)) == false) {

                     //alert("Please enter a valid email address .");
                     return false;

                 }
                 return true;
             }



             
            
            	$(document).ready(function($){
          //  alert("hii....");
            		$("#form_sample_2").submit(function(e) {
            			 var isError=false;
            			 var errMsg="";
            				
            			 if(!$("#title").val()){
        					 
             				isError=true;
             				
             				
             				$("#title").addClass("has-error")
             				$("#error_name").show()
             					//return false;
             				} else {
             					$("#error_name").hide()
             				}
            			 
            			 if($("#MOU_agency").val()== -1 ){
         		            
             				isError=true;
             				
             				$("#error_mou").show()
             					//return fregister_useralse;
             				} else {
             					$("#error_mou").hide()
             				}
         				
            			 
            			 
            			 
            			 if($("#MOU_agency").val()== 7 ){
            			 if(!$("#otherCourse").val()){
        					 
              				isError=true;
              				
              				
              				$("#otherCourse").addClass("has-error")
              				$("#error_other").show()
              					//return false;
              				} else {
              					$("#error_other").hide()
              				}
            			 
            			 }

            			 if(!$("#fromDate").val()){
        					 
              				isError=true;
              				
              				
              				$("#fromDate").addClass("has-error")
              				$("#error_fdate").show()
              					//return false;
              				} else {
              					$("#error_fdate").hide()
              				}
            			 
            			 if(!$("#toDate").val()){
        					 
               				isError=true;
               				
               				
               				$("#toDate").addClass("has-error")
               				$("#error_tdate").show()
               					//return false;
               				} else {
               					$("#error_tdate").hide()
               				}
            			 
            			 

            				if($("#beneficiaryMOU").val()== -1 ){
            		            
                				isError=true;
                				
                				$("#error_benf").show()
                					//return fregister_useralse;
                				} else {
                					$("#error_benfmou").hide()
                				}
            				
            				
            				
            				

            				if(!$("#beneficiaryMOUNo").val() || !numbersOnlyNotZero($("#beneficiaryMOUNo").val())){
            
            				isError=true;
            				
            				$("#error_part").show()
            					//return fregister_useralse;
            				} else {
            					$("#error_part").hide()
            				}
            				
            				
            				if(!$("#instituteOfMoU").val()){
           					 
                  				isError=true;
                  				
                  				
                  				$("#instituteOfMoU").addClass("has-error")
                  				$("#error_inst").show()
                  					//return false;
                  				} else {
                  					$("#error_inst").hide()
                  				}

            				

							if (!isError) {
								var x = confirm("Do you really want to submit the form?");
								if (x == true) {
									return  true;
									 document.getElementById("sub1").disabled=true;
           						  document.getElementById("sub2").disabled=true;
								}
							}
            
            			  
            						 
            					   return false;
            				} );
            	});
			//
			
			    
          
        </script>


	<script type="text/javascript">
		$(function() {
			$('#submitForm').submit(
					function() {
						$("input[type='submit']", this).val("Please Wait...")
								.attr('disabled', 'disabled');

						return true;
					});
		});

		function trim(el) {
			el.value = el.value.replace(/(^\s*)|(\s*$)/gi, ""). // removes leading and trailing spaces
			replace(/[ ]{2,}/gi, " "). // replaces multiple spaces with one space 
			replace(/\n +/, "\n"); // Removes spaces after newlines
			return;
		}
	</script>

<!-- 	<script type="text/javascript">
		
		function showForm() {
			//document.getElementById("abc").style = "display:none"
			var qualType = document.getElementById("functionalMOU").value
			//alert("qualType::"+qualType);

			if (qualType == 7) {

				document.getElementById("abc").style = "visible"

			} else {
				document.getElementById("abc").style = "display:none"
			}

		}
	
	</script> -->
	
	<script type="text/javascript">
		function showForm() {
			//alert("hii");
			//document.getElementById("abc").style = "display:none"
			var qualType = document.getElementById("MOU_agency").value
			//alert("qualType::"+qualType);

			if (qualType == 7) {

				document.getElementById("abc").style = "visible"
				//	document.getElementById("otherCourse").setAttribute("required","true");

			} else {
				document.getElementById("abc").style = "display:none"
				//	document.getElementById("otherCourse").removeAttribute("required");

			}

		}

	</script>
	<script type="text/javascript">
	function hideText() {
		//alert("hii");
		var qualType = document.getElementById("MOU_agency").value
		 //alert("x " +qualType);
			if(qualType == 7){
				//alert("In If " +x);
				document.getElementById("abc").style = "visible";
				document.getElementById("otherCourse").setAttribute("required","true");
			}else{ 
		document.getElementById("abc").style = "display:none"                         
			}
		
	}
	</script>


<script type="text/javascript">
function submit_f(view){
		document.getElementById("is_view").value=view;//create this 
		/* var form=document.getElementById("form_sample_2");
	    form.setAttribute("method", "post");

		form.action=("insertLibrarian");
		var x =confirm();
		if(x==true)
		form.submit(); */
		
	}
</script>
	<script type="text/javascript">
var wasSubmitted = false;    
function checkBeforeSubmit(){
  if(!wasSubmitted) {
	  var x=confirm("Do you really want to submit the form?");
	  if(x==true){
    wasSubmitted = true;
	  document.getElementById("sub1").disabled=true;
	  document.getElementById("sub2").disabled=true;

    return wasSubmitted;
	  }
  }
  return false;
}    
	</script>
	
	
												


</body>
</html>