<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.UUID"%>
<%@ page import="java.security.MessageDigest"%>
<%@ page import="java.math.BigInteger"%>
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
<body onload="showIsReg()">
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
										action="${pageContext.request.contextPath}/insertneighbrhdCommActvity"
										method="post"
										name="form_sample_2" id="form_sample_2">
										<%
											UUID uuid = UUID.randomUUID();
											MessageDigest md = MessageDigest.getInstance("MD5");
											byte[] messageDigest = md.digest(String.valueOf(uuid).getBytes());
											BigInteger number = new BigInteger(1, messageDigest);
											String hashtext = number.toString(16);
											session = request.getSession();
											session.setAttribute("generatedKey", hashtext);
										%>
											<input type="hidden" value="<%out.println(hashtext);%>"
													name="token" id="token">
										<div>

											<div class="col-xs-12">

														
														<input type="hidden" id="neghbh_comm_act_id" name="neghbh_comm_act_id" value="${neighbourCommAct.instNeighbourhoodCommActId}">
														<div class="form-group">

															<label class="control-label col-sm-2" for="smallheading">Neighbourhood  
																Community Activities <span class="text-danger">*</span>
															</label>


															<div class="col-sm-4">
														
															Yes <input type="radio" name="isActivity" id="isActivity" onclick="checkPhdGuide(this.value)"
															value="1"  ${neighbourCommAct.exInt1 == 1 ? 'checked' : '' }>
															
															No<input type="radio" name="isActivity" id="isActivity" value="0" onclick="checkPhdGuide(this.value)"
															${neighbourCommAct.exInt1 == 0 ? 'checked' : '' } >
													
														
														</div>
														</div>

														
												<div class="form-group" id="ihide" style="display: none;">
														<div class="form-group">
															<label class="control-label col-sm-2" for="smallheading">Name
																of Activity <span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="text" class="form-control" id="activity_name" onchange="trim(this)"
																	name="activity_name" placeholder="Name of Activity"
																	value="${neighbourCommAct.activityName}" autocomplete="off">
																	<span class="error_form text-danger" id="error_formfield1" style="display:none;" >Please enter name of activity.</span>
															</div>
														</div>
														
														<div class="form-group">
															<label class="control-label col-sm-2" for="smallheading">No. of
																Student <span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="text" class="form-control" id="no_student" onchange="trim(this)" onFocus="clearDefault(this)"
																	name="no_student" placeholder="No. of Student" 
																	value="${neighbourCommAct.noOfStud}" autocomplete="off">
																	<span class="error_form text-danger" id="error_formfield2" style="display:none;" >Please enter No.of student and value must be greater than 0.</span>
															</div>
														</div>

														
														<div class="form-group">
															<label class="control-label col-sm-2" for="smallheading">Total
																Student <span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="text" class="form-control" id="ttl_student" onchange="trim(this)" onFocus="clearDefault(this)"
																	name="ttl_student" placeholder="Total No. of Student" 
																	value="${neighbourCommAct.totalFaculty}" autocomplete="off">
																	<span class="error_form text-danger" id="error_formfield3" style="display:none;" >Please enter total student and value must be greater than 0.</span>
															</div>
														</div>

														<div class="form-group">
															<label class="control-label col-sm-2" for="smallheading">No. of
																Faculty <span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="text" class="form-control" id="no_faculty" onchange="trim(this)" onFocus="clearDefault(this)"
																	name="no_faculty" placeholder="No. of Faculty" 
																	value="${neighbourCommAct.noOfFaculty}" autocomplete="off">
																	<span class="error_form text-danger" id="error_formfield4" style="display:none;" >Please enter No. of faculty and value must be greater than 0.</span>
															</div>
														</div>

														<div class="form-group">
															<label class="control-label col-sm-2" for="smallheading">Total
																Faculty <span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="text" class="form-control" id="ttl_faculty" onchange="trim(this)" onFocus="clearDefault(this)"
																	name="ttl_faculty" placeholder="Total No. of Faculty"
																	value="${neighbourCommAct.totalFaculty}" autocomplete="off">
																	<span class="error_form text-danger" id="error_formfield5" style="display:none;" >Please enter total faculty and value must be greater than 0.</span>
															</div>
														</div>

														<div class="form-group">
															<label class="control-label col-sm-2" for="smallheading">Association
																with<span class="text-danger">*</span>
															</label>
															
														<div class="col-sm-6">
															<select id="association" name="association" class="form-control" onchange="showForm()">
																<option value="Government Organization" ${neighbourCommAct.associationWith eq 'Government Organization' ? 'selected' : ''}>Government Organization</option>
																<option value="Non Government Organization" ${neighbourCommAct.associationWith eq 'Non Government Organization' ? 'selected' : ''}>Non Government Organization</option>
																<option value="Industry" ${neighbourCommAct.associationWith eq 'Industry' ? 'selected' : ''}>Industry</option>
																<option value="Any Other" ${neighbourCommAct.associationWith eq 'Any Other' ? 'selected' : ''}>Any Other</option>
														</select>
                                      				 </div>

														</div>
													
													<div class="form-group" id="abc" style="display: none">

														<label class="control-label col-sm-2" for="page_name">Other  
															Associate<span class="text-danger">*</span> </label>
														<div class="col-sm-6">
															<input type="text" class="form-control" id="otherSource"
																autocomplete="off" name="otherSource" onchange="trim(this)"
																placeholder="Name of Other Source Funding" value="${neighbourCommAct.exVar1}"> <span
																class="error_form text-danger" id="error_other"
																style="display: none;">Please enter other associate.</span>
														</div>

												</div>
														
													</div>
														<div class="form-group">
															<div class="col-sm-offset-2 col-sm-10">
															<button type="submit" id="sub_button" class="btn btn-primary" 
																	onclick="submit_f(1)"><i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save</button>
															
																<a href="${pageContext.request.contextPath}/showNeighbourhoodCommActivities"><button
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
$('#no_student').on('input', function() {
	this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');
	});
	
$('#ttl_student').on('input', function() {
	this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');
	});
	
$('#no_faculty').on('input', function() {
	  this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');
	});
	
$('#ttl_faculty').on('input', function() {
	  this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');
	});
	
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
            				
            			 var fundSource = $("#association").val();
							if(fundSource == 'Any Other'){
								if (!$("#otherSource").val()) {
									isError = true;

									$("#otherSource").addClass(
											"has-error")
									$("#error_other").show()
								} else {
									$("#error_other").hide()
								}													
							}
     					
     						if (!$("#activity_name").val()) {
     							isError = true;

     							$("#activity_name").addClass(
     									"has-error")
     							$("#error_formfield1").show()
     						} else {
     							$("#error_formfield1").hide()
     						}
     				
     						 
           				if($("#no_student").val() <= 0 || !$("#no_student").val()){
        					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#no_student").addClass("has-error")
            				$("#error_formfield2").show()
            					//return false;
            				} else {
            					$("#error_formfield2").hide()
            				}  
            				
            				
           				if($("#ttl_student").val() <= 0 || !$("#ttl_student").val()){
       					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#ttl_student").addClass("has-error")
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
           				
           				if($("#ttl_faculty").val() <= 0 || !$("#ttl_faculty").val()){
         					 
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
			
		</script>
		<script type="text/javascript">
		function checkPhdGuide(activity) {
			

			if (activity == 1) {
				
				document.getElementById("ihide").style = "visible"
				

			} else if (activity == 0) {
				document.getElementById("ihide").style = "display:none"
				
			}

		}
		
		function showIsReg(){
				
				var isActivity = $("input[name=isActivity]:checked").val();
				
				if(isActivity == 1){
					document.getElementById("ihide").style.display = "block";
				} else{
					document.getElementById("ihide").style.display = "none";
				} 
				showForm();
			}
		function showForm() {
			
			var selectedValue = document.getElementById("association").value;
			//alert("qualType::"+selectedValue);
		
			if(selectedValue == 'Any Other'){
				document.getElementById("abc").style.display = "inline";
			}
			else{
				document.getElementById("abc").style.display = "none";
			}
			
		}
		</script>

	</div>
	<!-- END CONTAINER -->
	<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->




</body>
</html>