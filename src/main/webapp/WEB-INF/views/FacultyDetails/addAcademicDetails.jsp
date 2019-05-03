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
				<%-- 
				<div class="col-xs-12">
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
								<%-- <a href="${pageContext.request.contextPath}/showAcademicDetails"><button
										type="button" class="btn btn-info">Back</button></a> --%>
							</div>

						</header>
						<c:if test="${sessionScope.successMsg!=null}">
           						 <div class="col-lg-12">
    						          <div class="alert alert-success alert-dismissible fade in">
            							    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
             						   <strong>Success : </strong> ${sessionScope.successMsg}</div>
        	                     </div> 
        	                     <%session=request.getSession();
        	                     session.removeAttribute("successMsg");
        	                     %>
            			</c:if>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">


									<!-- <ul class="nav nav-tabs">
										<li class="active"><a href="#home" data-toggle="tab">
												<i class="fa fa-home"></i> Register
										</a></li>

									</ul>

									<div class="tab-content">
										<div class="tab-pane fade in active" id="home"> -->

											<form class="form-horizontal"
												action="${pageContext.request.contextPath}/insertFacAcademic"
												method="post" name="form_sample_2" id="form_sample_2">
												
												<div class="col-md-12"></div>
												<div class="col-xs-12">
													<div class="col-xs-12"></div>

													<div class="form-group">
														<label class="control-label col-sm-2"
															for="fQualificationId">Highest Qualification<span
															class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<select id="fQualificationId" name="fQualificationId"
																class="form-control">
																<c:forEach items="${quolfList}" var="quolf">
																	<c:choose>
																		<c:when
																			test="${quolf.qualificationId==editFacAcad.fQualificationId}">
																			<option selected value="${quolf.qualificationId}">${quolf.qualificationName}</option>

																		</c:when>
																		<c:otherwise>
																			<option value="${quolf.qualificationId}">${quolf.qualificationName}</option>

																		</c:otherwise>


																	</c:choose>

																</c:forEach>

															</select>
															<span class="error_form text-danger" id="fQualificationId_field"
															style="display: none;">Please select qualification</span>
															
														</div>

													</div>


													<div class="form-group">
														<label class="control-label col-sm-2" for="fClass">
															Class <span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<select id="fClass" name="fClass" class="form-control">
																
																<c:choose>
																<c:when test="${editFacAcad.fClass eq 'Distinction'}">
																<option selected value="Distinction">Distinction</option>
																<option value="First">First</option>
																<option value="Second">Second</option>
																<option value="Pass">Pass </option>
																</c:when>
																<c:when test="${editFacAcad.fClass eq 'First'}">
																<option  value="Distinction">Distinction</option>
																<option selected value="First">First</option>
																<option value="Second">Second</option>
																<option value="Pass">Pass </option>
																</c:when>
																<c:when test="${editFacAcad.fClass eq 'Second'}">
																<option  value="Distinction">Distinction</option>
																<option  value="First">First</option>
																<option  selected value="Second">Second</option>
																<option value="Pass">Pass </option>
																</c:when>
																<c:when test="${editFacAcad.fClass eq 'Pass'}">
																<option  value="Distinction">Distinction</option>
																<option  value="First">First</option>
																<option   value="Second">Second</option>
																<option  selected value="Pass">Pass </option>
																</c:when>
																<c:otherwise>
																<option value="Distinction">Distinction</option>
																<option value="First">First</option>
																<option value="Second">Second</option>
																<option value="Pass">Pass</option>
																</c:otherwise>
																
																</c:choose>
															</select>
															<span class="error_form text-danger" id="fClass_field"
															style="display: none;">Please select class achieved</span>
															
														</div>
														<div class="col-sm-2"></div>
													</div>

													<div class="form-group">
														<label class="control-label col-sm-2" for="fUniversity">University/Board
															<span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<input type="text" maxlength="200" onchange="trim(this)" class="form-control" id="fUniversity" 
																name="fUniversity" placeholder="Enter University Name"
																value="${editFacAcad.fUniversity}">
																<span class="error_form text-danger" id="fUniversity_field"
															style="display: none;">Please enter university name</span>
																
														</div>


													</div>



													<div class="form-group">
														<label class="control-label col-sm-2" for="fPassingYear">Year
															of Passing<span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<input type="text" class="form-control datepickeryear"
																data-min-view-mode="years" data-start-view="2" 
																data-format="yyyy" id="fPassingYear" name="fPassingYear"
																placeholder="Select Passing Year"  autocomplete="off"
																value="${editFacAcad.fPassingYear}">
																<span class="error_form text-danger" id="fPassingYear_field"
															style="display: none;">Please select year of passing</span>
																
														</div>

													</div>

													<div class="form-group">

														<div class="col-sm-offset-2 col-sm-10">
															<button type="submit" id="sub1" class="btn btn-primary" onclick="submit_f(1)"><i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save</button>
 														<a href="${pageContext.request.contextPath}/showAddMphillDetails"><button type="button"  id="sub2" class="btn btn-primary"><i class="${sessionScope.cancelIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Next</button></a>
 															</div>

														<input type="hidden" id="fac_aca_id" name="fac_aca_id"
															value="${editFacAcad.fAcaId}"> <input
															type="hidden" id="is_view" name="is_view" value="0">
														<input type="hidden" id="fac_id" name="fac_id" value="${facultyId}">
															<input type="hidden" id="temp2"
											name="temp2" value="${temp2}"> 
													</div>

												</div>


												<div class="clearfix"></div>
												
											</form>
											<p class="desc text-danger fontsize11">Notice : * Fields
										are mandatory.</p>
										
								</div>
							</div>

						</div>
					</section>
				</div>

			</section>
		</section>
	</div>


	<!-- MAIN CONTENT AREA ENDS -->
	<!-- END CONTENT -->



	<!-- END CONTAINER -->
	<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->

	<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
	
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
							//alert("Hi")
							$("#form_sample_2")
									.submit(
											function(e) {
												var isError = false;
												var errMsg = "";

												 if (!$("#fQualificationId").val()) {
													isError = true;

													$("#fQualificationId").addClass(
															"has-error")
													$("#fQualificationId_field")
															.show()
												} else {
													$("#fQualificationId_field")
															.hide()
												}

												if (!$("#fClass").val()) {
													isError = true;

													$("#fClass").addClass(
															"has-error")
													$("#fClass_field")
															.show()
												} else {
													$("#fClass_field")
															.hide()
												}

												if (!$("#fUniversity").val()) {
													isError = true;

													$("#fUniversity").addClass(
															"has-error")
													$("#fUniversity_field")
															.show()
												} else {
													$("#fUniversity_field")
															.hide()
												}

												if (!$("#fPassingYear").val()) {
													isError = true;

													$("#fPassingYear").addClass(
															"has-error")
													$("#fPassingYear_field")
															.show()
												} else {
													$("#fPassingYear_field")
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


function check(qualType) {
	//document.getElementById("abc").style = "display:none"
		//var qualType=document.getElementById("cat").value
		//alert("qualType::"+qualType);
		
		if (qualType == 0) {

			document.getElementById("abc").style = "visible"
			
				
		} 
		else if(qualType == 1){
			document.getElementById("abc").style = "display:none"
		}
	
	}
function hideText() {
	//alert("hii");
	document.getElementById("abc").style = "display:none"
		document.getElementById("xyz").style = "display:none"
		
	
	}
	
function showForm() {
	//document.getElementById("abc").style = "display:none"
		var qualType=document.getElementById("designation").value
		//alert("qualType::"+qualType);
		
		if (qualType == 5) {

			document.getElementById("xyz").style = "visible"
			
				
		} 
		else if(qualType == 1){
			document.getElementById("xyz").style = "display:none"
		}
	
	}
	
function calExp(){
	var d = new Date();

	var month = d.getMonth()+1;
	var day = d.getDate();

	var output = d.getFullYear() + '/' +
	    (month<10 ? '0' : '') + month + '/' +
	    (day<10 ? '0' : '') + day;
	
	alert("date is"+output);
	
	var joinDate=document.getElementById("doj").value;
	alert("date of join"+joinDate);
	
	//date1: 24/09/2015 (24th Sept 2015)
	//date2: 09/11/2015 (9th Nov 2015)
	//the difference: 2.5 (months)
	
	
	var difference = (date2.getDate() - date1.getDate()) / 30 +
    date2.getMonth() - date1.getMonth() +
    (12 * (date2.getFullYear() - date1.getFullYear()));
	
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
       // document.getElementById("sub2").disabled=true;

  	  return wasSubmitted;
    	  }
      }
      return false;
    }   
    
    function submit_f(view) {
		document.getElementById("is_view").value = view;//create this 

	}
    
    function trim(el) {
		el.value = el.value.replace(/(^\s*)|(\s*$)/gi, ""). // removes leading and trailing spaces
		replace(/[ ]{2,}/gi, " "). // replaces multiple spaces with one space 
		replace(/\n +/, "\n"); // Removes spaces after newlines
		return;
	}
    $(function () {
		 
        $('.datepicker').datepicker({
			autoclose: true,
            format: "dd-mm-yyyy",
            changeYear:true,
            changeMonth:true
		});
    });
    
    $(function () {
	 
        $('.datepickeryear').datepicker({
			autoclose: true,
			minViewMode: 2,
	         format: 'yyyy'

		});
    });
</script>

	

</body>
</html>