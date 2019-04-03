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
								<a href="${pageContext.request.contextPath}/showLibList"><button
										type="button" class="btn btn-info">Back</button></a> 
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
								<!-- 		<div class="alert alert-danger" id="regerror"></div> -->
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertLibrarian"
										method="post"  novalidate="novalidate"
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
															<div class="col-sm-10">
																<input type="text" class="form-control" id="librarian_name" value="${editInst.librarianName}"
																	name="librarian_name" placeholder="Librarian Name"   onchange="trim(this)"
																	>
												 <span class="error_form text-danger" id="error_name" style="display:none;" >Please enter Librarian Name</span>
															</div>
														</div>



														

														<div class="form-group">
															<label class="control-label col-sm-2" for="page_order">Contact
																No <span class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
													<input type="text"  
																	 class="form-control" id="lib_con_num" 	value="${editInst.contactNo}"
																	 name="lib_con_num" placeholder="Mobile No" onchange="checkUnique(this.value,1)" >
											 <span class="error_form text-danger" id="error_contact" style="display:none;" >
											 Please Enter Contact Number</span>
																	 
															</div>
														</div>

														<div class="form-group">
															<label class="control-label col-sm-2" for="page_order">Email ID(Official)
																<span class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<input type="email" class="form-control" id="librarian_email" value="${editInst.email}"
																	 name="librarian_email" placeholder="abc@xyz.com" onchange="checkUnique(this.value,2)" >
													 <span class="error_form text-danger" id="error_email" style="display:none;" >Please Enter Email ID</span>
																	 
															</div>
														</div>

															<div class="form-group">
															<label class="control-label col-sm-2" for="status">Highest
																Qualification : <span class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<select id="lib_quolf" name="lib_quolf" class="form-control" >
														<option value="-1">Select</option>
															
																	
																		<c:forEach items="${quolfList}" var="quolf">
																		<c:choose>
																			<c:when test="${quolf.qualificationId==editInst.qualificationId}">
																			<option selected value="${quolf.qualificationId}">${quolf.qualificationName}</option>

																			</c:when>
																			<c:otherwise>
																			<option value="${quolf.qualificationId}">${quolf.qualificationName}</option>

																			</c:otherwise>

																		</c:choose>

																	</c:forEach>
																</select>
																
																<span class="error_form text-danger" id="error_quol" style="display:none;" >Please Select Qualification</span>
															</div>
														</div>
																
														<div class="form-group">
															<label class="control-label col-sm-2" for="page_order">Joining
																Date <span class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<input type="text" class="form-control datepicker" id="lib_joiningDate" 	value="${jdate}"
																	name="lib_joiningDate" placeholder=" " >
																		<span class="error_form text-danger" id="error_jDate" style="display:none;" >Please Select Qualification</span>
															</div>
														</div>
											
											
											
											
														
															<div class="form-group">
																<label class="control-label col-sm-2" for="page_order">Is Working
																 <span class="text-danger">*</span>
																</label>


																<div class="col-sm-6">
																	Yes <input type="radio" name="conf_type" id="conf_type"
																		checked value="1" onclick="setDate(this.value)">
																		
																	No<input	type="radio" name="conf_type" id="conf_type"
																	 value="0" onclick="setDate(this.value)">
																	 
																
																</div>	
															</div>
													
													
														<div class="form-group" id="abc" style="display: none">
															<label class="control-label col-sm-2" for="page_order">Relieving
																Date <span class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<input type="text" class="form-control datepicker" id="relieving_date" 	value="${ldate}"
																	name="relieving_date"  >
													<span class="error_form text-danger" id="error_rDate" style="display:none;" >Please Select Relieving Date</span>
																	
															</div>
														</div>

													</div>

												</div>

                                             <input type="hidden" id="librarian_id" name="librarian_id" value="${editInst.librarianId}">
                                             	<input type="hidden" id="is_view" name="is_view" value="0">
												
											  <div class="form-group">
															<div class="col-sm-offset-2 col-sm-10">
																<input type="submit" class="btn btn-primary" id="sub1" onclick="submit_f(1)" value="Save">
																<!-- <input type="submit" class="btn btn-primary" onclick="submit_f(0)" value="Save &
																		Next"> -->
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

	<!-- END CONTENT -->
	<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
	<script>
	
	function trim(el) {
		el.value = el.value.replace(/(^\s*)|(\s*$)/gi, ""). // removes leading and trailing spaces
		replace(/[ ]{2,}/gi, " "). // replaces multiple spaces with one space 
		replace(/\n +/, "\n"); // Removes spaces after newlines
		return;
	}
	
	
            //
            function validateEmail(librarian_email) {
          //  alert("hii....validateEmail");
            	var eml = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
            
            	if (eml.test($.trim(librarian_email)) == false) {
            
            
            	return false;
            
            	}
            
            	return true;
            
            }
             function validateMobile(lib_con_num) {
            	 // alert("hii....validateMobile");
            		var mob = /^[1-9]{1}[0-9]{9}$/;
            
            
            		if (mob.test($.trim(lib_con_num)) == false) {
            
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
            				
           
            				if(!$("#librarian_name").val()){
            					 
            				isError=true;
            				errMsg += '<li>Please enter a valid Name.</li>';
            				
            				$("#librarian_name").addClass("has-error")
            				$("#error_name").show()
            					//return false;
            				} else {
            					$("#error_name").hide()
            				}
            				 
            				if(!$("#lib_con_num").val() || !validateMobile($("#lib_con_num").val())){
            
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
            				
            				
            				if(!$("#librarian_email").val() || !validateEmail($("#librarian_email").val())){
            
            				isError=true;
            				errMsg += '<li>Please enter a valid email address.</li>';
            				errMsg_alert += 'Please enter a valid email address. \n';
            				$("#error_email").show()
            					//return fregister_useralse;
            				} else {
            					$("#error_email").hide()
            				}
            				
            				
            				if($("#lib_quolf").val()== -1 ){
            		            
                				isError=true;
                				errMsg += '<li>Please Select Qualification</li>';
                				errMsg_alert += 'Please Select Qualification \n';
                				$("#error_quol").show()
                					//return fregister_useralse;
                				} else {
                					$("#error_quol").hide()
                				}
            				
            				
            				if(!$("#lib_joiningDate").val()){
            		            
                				isError=true;
                				errMsg += '<li>Please Select Date</li>';
                				errMsg_alert += 'Please Select Date \n';
                				$("#error_jDate").show()
                					//return fregister_useralse;
                				} else {
                					$("#error_jDate").hide()
                				}
                
            				

           				 if(($("input[name=conf_type]:checked").val())!=1){
           					 isError=true;
                				errMsg += '<li>Please enter a valid Name.</li>';
                				
                				$("#relieving_date").addClass("has-error")
                				$("#error_rDate").show()
                					//return false;
                				} else {
                					$("#error_rDate").hide()
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
/* function chkDate(){
	
	var date1=document.getElementById("lib_joiningDate").value;
	var date2=document.getElementById("relieving_date").value;
	
	date1 = new Date(date1);
	date2 = new Date(date2);
	
	
	if(date1 > date2){
		alert("invalid");
		
		document.getElementById("relieving_date").value="";
	}
	
}
 */
 
 function setDate(value){
		//alert("Value " +value)
		if(value==1){
		//alert(value)
	
		document.getElementById("abc").style.display = "none";

		//alert(value)
		}else{
			//alert(value)
			
			document.getElementById("abc").style.display = "block";

			//alert(value)

		}
		
	}
 
 
 $(function() {

		$('.datepicker').datepicker({
			autoclose : true,
			format : "dd-mm-yyyy",
			changeYear : true,
			changeMonth : true

		});
	});

function submit_f(view){
		document.getElementById("is_view").value=view;//create this 
		/* var form=document.getElementById("form_sample_2");
	    form.setAttribute("method", "post");

		form.action=("insertLibrarian");
		var x =confirm();
		if(x==true)
		form.submit(); */
		
	}
	
function checkUnique(inputValue,valueType){
	//alert(inputValue);
	trim(el);
	var primaryKey=${editInst.librarianId};
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
		tableId : 4,

		ajax : 'true',

	}, function(data) {
		
	//	alert("Data  " +JSON.stringify(data));
		if(data.error==true){
			if(valueType==2){
			
			
			alert("This email id already exist in system please enter unique");
			$('#librarian_email').val('');
			//document.getElementById("stud_contact_no").value=" ";
			
			}
			else{
				
				
				alert("This contact no  already exist in system please enter unique");
				$('#lib_con_num').val('');
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