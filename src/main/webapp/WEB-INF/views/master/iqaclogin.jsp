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
								<a
									href="${pageContext.request.contextPath}/showInstituteInfoList"><button
										type="button" class="btn btn-info">Back</button></a> 
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertInstituteInfo"
										method="post"  name="form_sample_2" novalidate="novalidate"
										id="form_sample_2">
<!-- 
										<ul class="nav nav-tabs">
											<li class="active"><a href="#home" data-toggle="tab">
													<i class="fa fa-home"></i> Information
											</a></li>


										</ul>
 -->
									<!-- 	<div class="tab-content">
											<div class="tab-pane fade in active" id="home"> -->

												<div>



													<div class="col-xs-12">



														<div class="form-group">
															<label class="control-label col-sm-4" for="page_name"
															>Year <span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-8">
																<select id="academic_year" name="academic_year"
																	class="form-control" required>
																	<%-- <c:forEach items="${acaYearList}" var="acaYear">
																		<option value="${acaYear.yearId}">${acaYear.academicYear}</option>
																	
																	</c:forEach> --%>
<option value="-1">Select</option>
																	<c:forEach items="${acaYearList}" var="acaYearList">
																		<c:choose>
																			<c:when
																				test="${acaYearList.yearId==editInstInfo.yearId}">
																				<option selected value="${acaYearList.yearId}">${acaYearList.academicYear}</option>

																			</c:when>
																			<c:otherwise>
																				<option value="${acaYearList.yearId}">${acaYearList.academicYear}</option>

																			</c:otherwise>

																		</c:choose>

																	</c:forEach>

																</select>
<span class="error_form text-danger" id="error_year" style="display:none;" >Please enter Student Name</span>
															</div>
														</div>

														<div class="form-group">
															<label class="control-label col-sm-4" for="page_name"
																>No. of Full Time Faculty
																<span class="text-danger">*</span>
															</label>
															<div class="col-sm-8">
																<input type="number" min="0" class="form-control"
																	id="no_fullTime_Faculty"
																	value="${editInstInfo.noOfFulltimeFaculty}"
																	name="no_fullTime_Faculty"
																	placeholder="No. of Full Time Faculty in Institute"
																	>
																	
										<span class="error_form text-danger" id="error_full" style="display:none;" >Please enter No. of Full Time Faculty</span>
															</div>
														</div>



														<div class="form-group">
															<label class="control-label col-sm-4" for="page_order"
														>No. of Full Time 	Nonteaching Faculty
															<span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-8">
																<input type="number" min="0" class="form-control"
																	id="no_nonTeaching_faculty"
																	value="${editInstInfo.noNonteachingIncludingOfficeStaff}"
																	name="no_nonTeaching_faculty"
																	placeholder="No. of Full Time Nonteaching Including Office Staff"
																	>
						<span class="error_form text-danger" id="error_non" style="display:none;" >Please enter No. of Full Time 	Nonteaching Faculty</span>
															</div>
														</div>



														<div class="form-group">
															<label class="control-label col-sm-4" for="page_order"
															>No. of Support Staff <span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-8">
																<input type="number" min="0" class="form-control"
																	id="no_suppStaff"
																	value="${editInstInfo.noSupportStaff}"
																	name="no_suppStaff" placeholder="No. of Support Staff"
																	>
							<span class="error_form text-danger" id="error_supp" style="display:none;" >Please enter No. of Support Staff</span>
															</div>
														</div>

														<div class="form-group">
															<label class="control-label col-sm-4" for="page_order"
																>No. of current
																admitted student <span class="text-danger">*</span>
															</label>
															<div class="col-sm-8">
																<input type="number" min="0" class="form-control"
																	id="no_currAdmitted_Student"
																	value="${editInstInfo.noCurrentAdmitedStnt}"
																	name="no_currAdmitted_Student"
																	placeholder="No. of current admitted student" >
																	
												<span class="error_form text-danger" id="error_curr" style="display:none;" >Please enter admitted student</span>
															</div>
														</div>

														<div class="form-group">
															<label class="control-label col-sm-4" for="page_order"
														> State Treasury Code <span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-8">
																<input type="number" min="0" class="form-control"
																	id="treasury_code" value="${editInstInfo.treasuryCode}"
																	name="treasury_code" placeholder="State Treasury Code"
																	>
																	
							<span class="error_form text-danger" id="error_code" style="display:none;" >Please enter State Treasury Code</span>
															</div>
														</div>

														<div class="form-group">
															<label class="control-label col-sm-4" for="page_order"
															> RUSA ID No. <span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-8">
																<input type="text" class="form-control" id="rusa_idNo"
																	value="${editInstInfo.rusaIdNo}" name="rusa_idNo"
																	placeholder="Rusa ID No." >
							<span class="error_form text-danger" id="error_rusa" style="display:none;" >Please enter Rusa ID</span>
															</div>
														</div>



													</div>

												</div>

												<c:choose>
													<c:when test="${editInstInfo.infoDetailId!=0}">
														<input type="hidden" id="inst_info_id" name="inst_info_id"
															value="${editInstInfo.infoDetailId}">
													</c:when>
													<c:otherwise>
														<input type="hidden" id="inst_info_id"  name="inst_info_id"
															value="0">
													</c:otherwise>

												</c:choose>





												<input type="hidden" id="is_view" name="is_view" value="0">

												<div class="form-group">
													<div class="col-sm-offset-2 col-sm-10">
														<input type="submit" 
															id="sub1" class="btn btn-primary"
															onclick="submit_f(1)" value="Save"> 
															<input
															type="submit" id="sub2"  class="btn btn-primary"
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

			</section>
		</section>

	</div>
	<!-- MAIN CONTENT AREA ENDS -->
	<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>

	<!-- END CONTENT -->
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
            				
            				
            				if($("#academic_year").val()== -1 ){
            		            
                				isError=true;
                				
                				$("#error_year").show()
                					//return fregister_useralse;
                				} else {
                					$("#error_year").hide()
                				}
            				

            				if(!$("#no_fullTime_Faculty").val() || !numbersOnlyNotZero($("#no_fullTime_Faculty").val())){
            
            				isError=true;
            				
            				$("#error_full").show()
            					//return fregister_useralse;
            				} else {
            					$("#error_full").hide()
            				}
            				
            				if(!$("#no_nonTeaching_faculty").val() || !numbersOnlyNotZero($("#no_nonTeaching_faculty").val())){
            		            
                				isError=true;
                				
                				$("#error_non").show()
                					//return fregister_useralse;
                				} else {
                					$("#error_non").hide()
                				}
            				if(!$("#no_suppStaff").val() || !numbersOnlyNotZero($("#no_suppStaff").val())){
            		            
                				isError=true;
                				
                				$("#error_supp").show()
                					//return fregister_useralse;
                				} else {
                					$("#error_supp").hide()
                				}
            				if(!$("#no_currAdmitted_Student").val() || !numbersOnlyNotZero($("#no_currAdmitted_Student").val())){
            		            
                				isError=true;
                				
                				$("#error_curr").show()
                					//return fregister_useralse;
                				} else {
                					$("#error_curr").hide()
                				}
            				if(!$("#treasury_code").val() || !numbersOnlyNotZero($("#treasury_code").val())){
            		            
                				isError=true;
                				
                				$("#error_code").show()
                					//return fregister_useralse;
                				} else {
                					$("#error_code").hide()
                				}
            				
							if(!$("#rusa_idNo").val() || !numbersOnlyNotZero($("#rusa_idNo").val())){
            		            
                				isError=true;
                				
                				$("#error_rusa").show()
                					//return fregister_useralse;
                				} else {
                					$("#error_rusa").hide()
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

	<script>
		function clearSessionAttribute() {

			$.getJSON('${clearSessionAttribute}', {

				ajax : 'true',

			}, function(data) {

			});

		}

		function selectedInst(source) {

			checkboxes = document.getElementsByName('instIds');

			for (var i = 0, n = checkboxes.length; i < n; i++) {
				checkboxes[i].checked = source.checked;

			}

		}
		function showEditStudent(studId) {
			document.getElementById("edit_stud_id").value = studId;//create this 
			var form = document.getElementById("libListForm");
			form.setAttribute("method", "post");

			form.action = ("showEditStudent");
			form.submit();

		}

		function disableButton() {

			confirm('Do you really want to submit the form?');
			document.getElementById("submitButton").disabled = true;
		}
		
		

		/* 
		onsubmit="return confirm('Do you really want to submit the form?');" */
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



	<!-- END CONTAINER -->
	<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->




</body>
</html>