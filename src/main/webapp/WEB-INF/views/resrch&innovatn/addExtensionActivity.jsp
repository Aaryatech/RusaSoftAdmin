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
<!-- onload="check(${phd.isPhdAwarded})" onload="checkCoGuide(${phd.isCoGuide})" -->
<body onload="showIsReg(${tExtAct.exInt1})">
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
				<div class="col-lg-12">
					<section class="box ">

						<header class="panel_header">
							<h2 class="title pull-left">${title}</h2>

							<%-- <div class="actions panel_actions pull-right">
								<a href="${pageContext.request.contextPath}/showPhdGuideList"><button
										type="button" class="btn btn-info">Back</button></a>
							</div> --%>

						</header>
						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertExstensionActivity"
										method="post"
										name="form_sample_2" id="form_sample_2">

										<div>

											<div class="col-xs-12">

														
														<input type="hidden" id="inst_extension_act_id" name="inst_extension_act_id"
														 value="${tExtAct.instExtensionActId}">
														
														
													<div class="form-group">
											<label class="control-label col-sm-2" for="status">Select  
												Activity: <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<select id="activity_id" name="activity_id" class="form-control" onclick="checkPhdGuide(this.value)">
													<c:choose>
													<c:when test="${tExtAct.exInt1==1}">
														<option selected value="0">Any Other</option>
														<c:forEach items="${mExtList}" var="extList">
																<option value="${extList.extensionActivityId}">${extList.activityTitle}</option>
													</c:forEach>
													</c:when>
													<c:otherwise>
													
													
													<c:forEach items="${mExtList}" var="extList">
														 <c:choose>
															<c:when test="${extList.extensionActivityId==tExtAct.extensionActivityId}">
																<option selected value="${extList.extensionActivityId}">${extList.activityTitle}</option>

															</c:when>
															<c:otherwise> 
																<option value="${extList.extensionActivityId}">${extList.activityTitle}</option>

															 </c:otherwise>

														</c:choose> 

													</c:forEach>
													
													
														<option value="0">Any Other</option>
														</c:otherwise>
														</c:choose>
												</select>
											</div>
										</div>

													
														
												<div class="form-group" id="ihide" style="display: none;">
														<div class="form-group">
															<label class="control-label col-sm-2" for="smallheading">Activity
																Title<span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="text" class="form-control" id="activity_name" onchange="trim(this)"
																	name="activity_name" placeholder="Name of Activity"
																	value="${tExtAct.tActivityTitle}" autocomplete="off">
																	<span class="error_form text-danger" id="error_formfield1" style="display:none;" >Please enter name of activity.</span>
															</div>
														</div>
													</div>
														
														<div class="form-group">
															<label class="control-label col-sm-2" for="smallheading">No. of
																Student Participated<span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="text" class="form-control" id="no_student_part" onchange="trim(this)" onFocus="clearDefault(this)"
																	name="no_student_part" placeholder="No. of Student Participated" maxlength="6"
																	value="${tExtAct.noOfStudParticipated}" autocomplete="off">
																	<span class="error_form text-danger" id="error_formfield2" style="display:none;" >Please enter No.of student participated and value must be greater than 0.</span>
															</div>
														</div>

														
														<div class="form-group">
															<label class="control-label col-sm-2" for="smallheading">No. Of
																Student in Institute <span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="text" class="form-control" id="student_in_institute" onchange="trim(this)" onFocus="clearDefault(this)"
																	name="student_in_institute" placeholder="No. of Student in Institute" maxlength="6"
																	value="${tExtAct.noOfStudInInst}" autocomplete="off">
																	<span class="error_form text-danger" id="error_formfield3" style="display:none;" >Please enter No. of student in institute and value must be greater than 0.</span>
															</div>
														</div>

														<div class="form-group">
															<label class="control-label col-sm-2" for="smallheading">No. Of
																Faculty Participated <span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="text" class="form-control" id="no_faculty" onchange="trim(this)" onFocus="clearDefault(this)"
																	name="no_faculty" placeholder="No. of Faculty Participated" maxlength="6"
																	value="${tExtAct.noOfFacultyParticipated}" autocomplete="off">
																	<span class="error_form text-danger" id="error_formfield4" style="display:none;" >Please enter No. of faculty participated and value must be greater than 0.</span>
															</div>
														</div>

														<div class="form-group">
															<label class="control-label col-sm-2" for="smallheading">No. of
																Faculty in Institute <span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="text" class="form-control" id="faculty_in_inst" onchange="trim(this)" onFocus="clearDefault(this)"
																		name="faculty_in_inst" placeholder="No. of Faculty in Institute" maxlength="6"
																	value="${tExtAct.noOfFacultyInInst}" autocomplete="off">
																	<span class="error_form text-danger" id="error_formfield5" style="display:none;" >Please enter No. of faculty in institute and value must be greater than 0.</span>
															</div>
														</div>
													
														<div class="form-group">
															<div class="col-sm-offset-2 col-sm-10">
															<button type="submit" id="sub_button" class="btn btn-primary" 
																	onclick="submit_f(1)"><i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save</button>
															
																<a href="${pageContext.request.contextPath}/showExtensionActivity"><button
																   id="sub2" type="button" class="btn btn-primary"><i class="${sessionScope.cancelIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Cancel</button></a>
															<input type="hidden" id="is_view" name="is_view" value="0">
															</div>
														</div>

													</div>


													<div class="clearfix"></div>

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
            				
     					
            			 var radioValue = $("#activity_id").val(); /* $("input[name='activity_id']:checked"). val(); */
      					//alert(radioValue);
      					if(radioValue==0){
     						if (!$("#activity_name").val()) {
     							isError = true;

     							$("#activity_name").addClass(
     									"has-error")
     							$("#error_formfield1").show()
     						} else {
     							$("#error_formfield1").hide()
     						}
      					}
     				
     						 
           				if($("#no_student_part").val() <= 0 || !$("#no_student_part").val()){
        					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#no_student").addClass("has-error")
            				$("#error_formfield2").show()
            					//return false;
            				} else {
            					$("#error_formfield2").hide()
            				}  
            				
            				
           				if($("#student_in_institute").val() <= 0 || !$("#student_in_institute").val()){
       					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#student_in_institute").addClass("has-error")
            				$("#error_formfield3").show()
            					//return false;
            				} else {
            					$("#error_formfield3").hide()
            				}
           				
           						
           				
           				if($("#no_faculty").val() <= 0 || !$("#no_faculty").val()){
         					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#no_faculty").addClass("has-error")
            				$("#error_formfield4").show()
            					//return false;
            				} else {
            					$("#error_formfield4").hide()
            				}
           				
           				if($("#faculty_in_inst").val() <= 0 || !$("#faculty_in_inst").val()){
         					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#ttl_faculty").addClass("has-error")
            				$("#error_formfield5").show()
            					//return false;
            				} else {
            					$("#error_formfield5").hide()
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
    
    $(function () {
		 
        $('.datepicker').datepicker({
			autoclose: true,
            format: "dd-mm-yyyy",
            changeYear:true,
            changeMonth:true

		});
    });
    
</script>
<script type="text/javascript">
function clearDefault(a){
	if(a.defaultValue==0)
	{
		a.value=""
	}
	};

	$('#no_student_part').on('input', function() {
		  this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');
		});
	
	$('#no_faculty').on('input', function() {
		  this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');
		});
	
	$('#faculty_in_inst').on('input', function() {
		  this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');
		});
	
	$('#beneficiaryMOUNo').on('input', function() {
		  this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');
		});
		</script>
		<script type="text/javascript">
		function checkPhdGuide(activity) {
			
//alert(activity);
			if (activity == 0) {
				
				document.getElementById("ihide").style = "visible"
				

			} else  {
				document.getElementById("ihide").style = "display:none"
				
			}

		}
		
		function showIsReg(act){
			
				//alert(act);
				var isActivity = act; //$("input[name=isActivity]:checked").val();
				
				if(isActivity == 1){
					document.getElementById("ihide").style.display = "block";
				} else{
					document.getElementById("ihide").style.display = "none";
				} 
				
			}
			
		</script>

	</div>
	<!-- END CONTAINER -->
	<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->




</body>
</html>