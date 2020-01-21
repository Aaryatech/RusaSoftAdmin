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
			<section class="wrapper main-wrapper row">

				<%-- <div class="col-xs-12">
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
							<%-- 	<a href="${pageContext.request.contextPath}/showStudList"><button
										type="button" class="btn btn-info">Back</button></a>  --%>
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertStudent"
										method="post" novalidate="novalidate"
										name="form_sample_2" id="form_sample_2">

										<!-- <ul class="nav nav-tabs">
											<li class="active"><a href="#home" data-toggle="tab">
													<i class="fa fa-home"></i> Register Form
											</a></li>


										</ul> -->

										<!-- <div class="tab-content">
											<div class="tab-pane fade in active" id="home">
 -->
												<div>



													<div class="col-xs-12">
														<div class="form-group">
															<label class="control-label col-sm-2" for="page_name">
																Name<span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="text" class="form-control" id="student_name" 
																value="${editStudent.studentName}"  
																	name="student_name" placeholder="Student Name" onchange="trim(this)"
																	>
											 <span class="error_form text-danger" id="error_name" style="display:none;" >Please enter Student Name</span>
																	
															</div>
														</div>


												<div class="form-group">
													<label class="control-label col-sm-2" for="status">
													Year <span class="text-danger">*</span>
												</label>
												<div class="col-sm-6">
												<select id="academic_year" name="academic_year" class="form-control">
																
														<option value="First Year" ${editStudent.acadamicYear eq 'First Year' ? 'selected' : '' }>First Year</option>
														<option value="Second Year" ${editStudent.acadamicYear eq 'Second Year' ? 'selected' : '' }>Second Year</option>
														<option value="Third Year" ${editStudent.acadamicYear eq 'Third Year' ? 'selected' : '' }>Third Year</option>
														<option value="Fourth Year" ${editStudent.acadamicYear eq 'Fourth Year' ? 'selected' : '' }>Fourth Year</option>
														<option value="Fifth Year" ${editStudent.acadamicYear eq 'Fifth Year' ? 'selected' : '' }>Fifth Year</option>
														
													
													</select>
													 <span class="error_form text-danger" id="error_year" style="display:none;" >Please Select Academic Year</span>
                                       </div>
										</div>		
													
												<div class="form-group">
													<label class="control-label col-sm-2" for="status">
													Department  <span class="text-danger">*</span>
												</label>
												<div class="col-sm-6">
													<select id="stud_branch" name="stud_branch" class="form-control" >
														
																		<option value="-1">Select</option>
																		<c:forEach items="${deptList}" var="dept">
																		<c:choose>
																			<c:when test="${dept.deptId==editStudent.deptId}">
																			<option selected value="${dept.deptId}">${dept.deptName}</option>

																			</c:when>
																			<c:otherwise>
																			<option value="${dept.deptId}">${dept.deptName}</option>

																			</c:otherwise>

																		</c:choose>

																	</c:forEach>

													</select>
							 <span class="error_form text-danger" id="error_dept" style="display:none;" >Please Select Department</span>
                                       </div>
										</div>		
															
														<div class="form-group">
															<label class="control-label col-sm-2" for="page_order">ID No.
																 <span class="text-danger"></span>
															</label>
															<div class="col-sm-6">
																<input type="text" 
																	maxlength="10" class="form-control" id="id_number" 
																	value="${editStudent.idNo}"  
																	name="id_number" placeholder="ID Number     (Optional)" >
													<!-- <span class="error_form text-danger" id="error_Id" style="display:none;" >Please Enter ID Properly</span> -->
															</div>
														</div>

														<div class="form-group">
															<label class="control-label col-sm-2" for="page_order">Contact
																No <span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="text" 
																	maxlength="10" class="form-control" id="stud_contact_no"
																	value="${editStudent.contactNo}" onchange="checkUnique(this.value,1)"
																	name="stud_contact_no" placeholder="Mobile No" onkeypress='return restrictAlphabets(event)'>
																	
														 <span class="error_form text-danger" id="error_contact" style="display:none;" >Enter Contact Number</span>
															</div>
														</div>

														<div class="form-group">
															<label class="control-label col-sm-2" for="page_order">Email ID
																<span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="email" class="form-control" id="student_email"
																value="${editStudent.email}" onchange="checkUnique(this.value,2)"
																	name="student_email" placeholder="abc@xyz.com" >
									<span class="error_form text-danger" id="error_email" style="display:none;" >Enter Proper Email Id </span>
															</div>
														</div>

											

													</div>

												</div>

                                              <input type="hidden" id="student_id" name="student_id" value="${editStudent.studentId}" >
                                             	<input type="hidden" id="is_view" name="is_view" value="0">
												
											<div class="form-group">
													<div class="col-sm-offset-3 col-sm-9">


<button type="submit" id="sub1" class="btn btn-primary"
													onclick="submit_f(1)"><i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save</button>
														
<a href="${pageContext.request.contextPath}/showStudList"><button id="sub2"
										type="button" class="btn btn-primary"><i class="${sessionScope.cancelIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Cancel</button></a>													</div>
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
	

function trim(el) {
	el.value = el.value.replace(/(^\s*)|(\s*$)/gi, ""). // removes leading and trailing spaces
	replace(/[ ]{2,}/gi, " "). // replaces multiple spaces with one space 
	replace(/\n +/, "\n"); // Removes spaces after newlines
	return;
}     //
            function validateEmail(student_email) {
          //  alert("hii....validateEmail");
            	var eml = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
            
            	if (eml.test($.trim(student_email)) == false) {
            
            
            	return false;
            
            	}
            
            	return true;
            
            }
             function validateMobile(stud_contact_no) {
            	 // alert("hii....validateMobile");
            		var mob = /^[1-9]{1}[0-9]{9}$/;
            
            
            		if (mob.test($.trim(stud_contact_no)) == false) {
            
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
            				
           
            				if(!$("#student_name").val()){
            					 
            				isError=true;
            				errMsg += '<li>Please enter a valid Name.</li>';
            				
            				$("#student_name").addClass("has-error")
            				$("#error_name").show()
            					//return false;
            				} else {
            					$("#error_name").hide()
            				}
            				 
            				if(!$("#stud_contact_no").val() || !validateMobile($("#stud_contact_no").val())){
            
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
            				
            				
            				if(!$("#student_email").val() || !validateEmail($("#student_email").val())){
            
            				isError=true;
            			
            				$("#error_email").show()
            					//return fregister_useralse;
            				} else {
            					$("#error_email").hide()
            				}
            				
            				
            				if($("#academic_year").val()== -1 ){
            		            
                				isError=true;
                				
                				$("#error_year").show()
                					//return fregister_useralse;
                				} else {
                					$("#error_year").hide()
                				}
            				


							if($("#stud_branch").val()== -1 ){
            		            
                				isError=true;
                				
                				$("#error_dept").show()
                					//return fregister_useralse;
                				} else {
                					$("#error_dept").hide()
                				}
            				
            				
							if (!isError) {
								var x = confirm("Do you really want to submit the form?");
								if (x == true) {
									
									 document.getElementById("sub1").disabled=true;
           						 	 document.getElementById("sub2").disabled=true;
           							 return  true;
								}
							}
            
            			  
            						 
            					   return false;
            				} );
            	});
		
		 
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
	
/* 	
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
}     */
	</script>
	
	
	
	
	<script>
function checkUnique(inputValue,valueType){
	//alert("hii");
	
	var primaryKey=${editStudent.studentId};
	//alert("Primary key"+primaryKey);
	var isEdit=0;
	if(primaryKey>0){
		isEdit=1;
	}
	//alert("Is Edit " +isEdit);
	
	var valid=false;
	if(valueType==1){
		//alert("Its Mob no");
		if(inputValue.length==10){
			valid=true;
			//alert("Len 10")
		}else{
			//alert("Not 10");
		}
	}
	else if(valueType==2){
		//alert("Its Email " );
		
		var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
		if(inputValue.match(mailformat))
		{
			valid=true;
			//alert("Valid Email Id");
		}
		else
		{
			valid=false;
			//alert("InValid Email Id");
		}
	}
	if(valid==true)
	$.getJSON('${checkUniqueField}', {
		
		inputValue : inputValue,
		valueType  : valueType,
		primaryKey : primaryKey,
		isEdit     : isEdit,
		tableId : 3,

		ajax : 'true',

	}, function(data) {
		
	//	alert("Data  " +JSON.stringify(data));
		if(data.error==true){
			if(valueType==2){
			
			
			alert("This Email Id  is Already Exist.");
			$('#student_email').val('');
			//document.getElementById("stud_contact_no").value=" ";
			
			}
			else{
				
				
				alert("This Contact No. is Already Exist.																																																																			");
				$('#stud_contact_no').val('');
				//document.getElementById("student_email").value=" ";
			}
		}
	});
}

</script>





	<!-- END CONTAINER -->
	<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->

	



</body>
</html>