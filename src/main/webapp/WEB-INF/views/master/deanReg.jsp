<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



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
<body onload="setDate1(1)">
	<c:url value="/getUserInfo" var="getUserInfo"></c:url>
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
								<%-- <a href="${pageContext.request.contextPath}/showDeanList"><button
										type="button" class="btn btn-info">Back</button></a> --%>
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertNewDean"
										method="post" name="form_sample_2" id="form_sample_2">



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

											<input type="hidden" class="form-control" id="dean_id"
												name="dean_id" value="${dean.facultyId}"> <input
												type="hidden" id="addEdit" name="addEdit" value="${addEdit}">

											<div class="col-xs-12">

												<div class="form-group">

													<label class="control-label col-sm-2" for="page_name">
														Name<span class="text-danger">*</span>
													</label>
													<div class="col-sm-10">
														<input type="text" class="form-control" id="dean_name"
															name="dean_name" placeholder="Dean R&D Name"
															onchange="trim(this)" autocomplete="off"
															value="${dean.facultyFirstName}"> <span
															class="error_form text-danger" id="error_formfield1"
															style="display: none;">Please enter dean name</span>
													</div>
												</div>

												<div class="form-group">
													<label class="control-label col-sm-2" for="page_order">Designation
														<span class="text-danger">*</span>
													</label>
													<div class="col-sm-10">
														<select id="designation" name="designation"
															class="form-control" onchange="showForm()">

															<c:forEach items="${desigList}" var="makeList">
																<c:choose>
																	<c:when
																		test="${makeList.designationId == dean.currentDesignationId}">
																		<option value="${makeList.designationId}"
																			selected="selected">${makeList.designationName}</option>
																	</c:when>
																	<c:otherwise>
																		<option value="${makeList.designationId}">${makeList.designationName}</option>
																	</c:otherwise>
																</c:choose>
															</c:forEach>

														</select>

													</div>
												</div>

												<div class="form-group">
													<label class="control-label col-sm-2" for="status">Department
														<span class="text-danger">*</span>
													</label>
													<div class="col-sm-10">
														<select id="depart" name="depart" class="form-control">
															<c:forEach items="${deptList}" var="dept">
																<c:choose>
																	<c:when test="${dept.deptId==dean.deptId}">
																		<option selected value="${dept.deptId}">${dept.deptName}</option>

																	</c:when>
																	<c:otherwise>
																		<option value="${dept.deptId}">${dept.deptName}</option>

																	</c:otherwise>

																</c:choose>

															</c:forEach>
															<!-- <option value="0">NA</option> -->
														</select>
													</div>
												</div>



												<div class="form-group">
													<label class="control-label col-sm-2" for="status">Highest
														Qualification <span class="text-danger">*</span>
													</label>
													<div class="col-sm-10">
														<select id="hod_quolf" name="quolif" class="form-control">
															<c:forEach items="${quolfList}" var="quolf">
																<c:choose>
																	<c:when
																		test="${dean.highestQualification==quolf.qualificationId}">
																		<option selected value="${quolf.qualificationId}">${quolf.qualificationName}</option>

																	</c:when>
																	<c:otherwise>

																		<option value="${quolf.qualificationId}">${quolf.qualificationName}</option>

																	</c:otherwise>

																</c:choose>

															</c:forEach>

														</select>
													</div>
												</div>


												<div class="form-group">
													<label class="control-label col-sm-2" for="page_order">Joining
														Date <span class="text-danger">*</span>
													</label>
													<div class="col-sm-3">
														<input type="text" class="form-control datepicker"
															data-end-date="0d" id="join_date" autocomplete="off"
															onchange="trim(this)"
															onkeypress='return restrictAlphabets(event)'
															data-format="dd-mm-yyyy" name="join_date"
															placeholder="dd/mm/yyyy" value="${dean.joiningDate}">
														<span class="error_form text-danger" id="error_formfield4"
															style="display: none;">Please enter joining date</span>
													</div>
													<label class="control-label col-sm-3" for="planning"
														style="text-align: left;">Is Currently Working<span
														class="text-danger">*</span>
													</label>
													<div class="col-sm-2">

														<%-- <input type="radio" id="is_registration1"
															${dean.isWorking==1 ? 'checked' : ''}
															name="is_registration" value="1"
															onclick="setDate(this.value)">Yes <input
															type="radio" id="is_registration2"
															${dean.isWorking<1 ? 'checked' : ''}
															name="is_registration" value="0"
															onclick="setDate(this.value)">No
															 --%>
														<c:choose>
															<c:when test="${dean.facultyId>0}">
																<c:choose>
																	<c:when test="${dean.isWorking==1}">
																		<input type="radio" id="is_registration" checked
																			name="is_registration" value="1"
																			onclick="setDate(this.value)">Yes<input
																			type="radio" id="is_registration"
																			name="is_registration" value="0"
																			onclick="setDate(this.value)">No
															</c:when>
																	<c:otherwise>
																		<input type="radio" id="is_registration"
																			name="is_registration" value="1"
																			onclick="setDate(this.value)">Yes<input
																			type="radio" checked id="is_registration"
																			name="is_registration" value="0"
																			onclick="setDate(this.value)">No
															</c:otherwise>
																</c:choose>
															</c:when>


															<c:otherwise>

																<input type="radio" id="is_registration" checked
																	name="is_registration" value="1"
																	onclick="setDate(this.value)">Yes<input
																	type="radio" id="is_registration"
																	name="is_registration" value="0"
																	onclick="setDate(this.value)">No
															
															</c:otherwise>


														</c:choose>


														<%-- <c:choose>
															<c:when test="${dean.facultyId==0}">

																<input type="radio" id="is_registration1"
																	name="is_registration" value="1" checked
																	onclick="setDate(this.value)">Yes 
																<input type="radio" id="is_registration2"
																	name="is_registration" value="0" 
																	onclick="setDate(this.value)">No 
															
															</c:when>
															<c:otherwise>

																<c:choose>
																	<c:when test="${empty dean.realivingDate}">
																		<input type="radio" id="is_registration1"
																			name="is_registration" value="1" checked
																			onclick="setDate(this.value)">Yes  
																<input type="radio" id="is_registration2"
																			name="is_registration" value="0"
																			onclick="setDate(this.value)">No 
															
																
																</c:when>
																	<c:otherwise>
																		<input type="radio" id="is_registration1"
																			name="is_registration" value="1"
																			onclick="setDate(this.value)">Yes
																<input type="radio" id="is_registration2" checked
																			name="is_registration" value="0"
																			onclick="setDate(this.value)">No
															
																
																</c:otherwise>

																</c:choose>

															</c:otherwise>


														</c:choose>
 --%>
													</div>
												</div>
												<div class="form-group" id="abc" style="display: none">
													<label class="control-label col-sm-2" for="page_order">Relieving
														Date <span class="text-danger">*</span>
													</label>
													<div class="col-sm-3">
														<input type="text" class="form-control datepicker"
															id="acc_off_relDate" data-end-date="0d"
															data-format="dd-mm-yyyy"
															onkeypress='return restrictAlphabets(event)'
															autocomplete="off" name="acc_off_relDate"
															placeholder="Relieving Date"
															value="${dean.realivingDate}"> <span
															class="error_form text-danger" id="error_acc_off_relDate"
															style="display: none;">Please enter relieving date</span>
													</div>
												</div>

												<div class="form-group">

													<label class="control-label col-sm-2" for="is_add_same">Belongs
														to MH State<span class="text-danger">*</span>
													</label>

													<div class="col-sm-3">

														<c:if test="${dean.facultyId>0}">
													Yes <input type="radio"
																${dean.isSame == 1 ? 'checked' : ''}
																name="is_state_same" id="is_state_same" value="1"
																onclick="selcState()"> 
													No<input type="radio" ${dean.isSame == 0 ? 'checked' : ''}
																name="is_state_same" id="is_state_same" value="0"
																onclick="selcState()">
														</c:if>

														<c:if test="${dean.facultyId==0}">
													Yes <input type="radio" checked name="is_state_same"
																id="is_state_same" value="1" onclick="selcState()"> 
													No<input type="radio" name="is_state_same"
																id="is_state_same" value="0" onclick="selcState()">
														</c:if>

														<span class="error_form text-danger"
															id="is_state_same_field" style="display: none;">Please
															select permanent/correspondence address same or not.</span>

													</div>
												</div>

												<div class="form-group" style="display: none;" id="state">

													<label class="control-label col-sm-2" for="state_id">State
														<span class="text-danger">*</span>
													</label>
													<div class="col-sm-10">
														<select id="state_id" name="state_id" class="form-control">

															<c:forEach items="${sessionScope.stateList}" var="state">
																<c:choose>
																	<c:when
																		test="${dean.facultyMiddelName == state.stateId}">
																		<option selected value="${state.stateId}">${state.stateName}</option>
																	</c:when>

																	<c:otherwise>
																		<option value="${state.stateId}">${state.stateName}</option>
																	</c:otherwise>
																</c:choose>
															</c:forEach>


														</select> <span class="error_form text-danger" id="quolf_field"
															style="display: none;">Please select highest
															qualification</span>
													</div>
												</div>

												<div class="form-group">
													<label class="control-label col-sm-2" for="page_order">Contact
														No <span class="text-danger">*</span>
													</label>
													<div class="col-sm-10">
														<input type="text" maxlength="10" class="form-control"
															id="contact_no" name="contact_no" placeholder="Mobile No"
															autocomplete="off" value="${dean.contactNo}"
															onchange="trim(this)"
															onkeypress='return restrictAlphabets(event)'> <span
															class="error_form text-danger" id="error_formfield2"
															style="display: none;">Please enter dean name</span>
														<p class="desc text-danger fontsize11">Note: OTP will
															be sent on this mobile number for verification</p>
													</div>
												</div>

												<div class="form-group">
													<label class="control-label col-sm-2" for="page_order">Email
														ID (Official) <span class="text-danger">*</span>
													</label>
													<div class="col-sm-10">
														<input type="email" class="form-control" id="email"
															onchange="checkUnique(this.value,2)"
															onchange="trim(this)" name="email" autocomplete="off"
															placeholder="abc@xyz.com" value="${dean.email}">
														<span class="error_form text-danger" id="error_formfield3"
															style="display: none;">Please enter email</span>
														<p class="desc font-italic fontsize11">Note:
															Verification mail will be sent on this Email id</p>
													</div>
												</div>


												<%-- <div class="form-group">
													<label class="control-label col-sm-2" for="smallheading">Roles
														<span class="text-danger">*</span>
													</label>
													<div class="col-sm-10">
														<input type="checkbox" name="isDean" value="1"
															${dean.isDean==1 ? 'checked' : ''}>&nbsp;&nbsp;&nbsp;Dean</div>
												</div> --%>

											</div>

										</div>


										<div class="form-group">
											<div class="col-sm-offset-2 col-sm-10">
												<button type="submit" id="sub_button"
													class="btn btn-primary" onclick="submit_f(1)">
													<i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save
												</button>

												<a href="${pageContext.request.contextPath}/showDeanList"><button
														id="sub2" type="button" class="btn btn-primary">
														<i class="${sessionScope.cancelIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Cancel
													</button></a>
											</div>

										</div>

										<input type="hidden" id="is_view" name="is_view" value="0">

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

	<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>

	<script type="text/javascript"> 
 function selcState() {
	// alert("Hi");
	 var isSamState = $("input[name='is_state_same']:checked"). val();
	// alert(isSamState);
	 
	 if(isSamState==0){
		 document.getElementById("state").style.display = "block";
	 }else{
		 document.getElementById("state").style.display = "none";
	 }
}
 
 </script>

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
            				
           				if(!$("#dean_name").val()){
            					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#dean_name").addClass("has-error")
            				$("#error_formfield1").show()
            					//return false;
            				} else {
            					$("#error_formfield1").hide()
            				}
            				
            				if(!$("#contact_no").val() || !validateMobile($("#contact_no").val())){
            		            
                				isError=true;
                				errMsg += '<li>Please enter a valid email address.</li>';
                				errMsg_alert = 'Please enter a valid mobile number.';
                				$("#error_formfield2").html(errMsg_alert);
                				$("#error_formfield2").show();
                				//alert();
                					//return false;
                				} else {
                					$("#error_formfield2").html("Please enter mobile")
                					$("#error_formfield2").hide()
                				} 
            				
            				if(!$("#email").val() || !validateEmail($("#email").val())){
            		            
                				isError=true;
                				errMsg += '<li>Please enter a valid email address.</li>';
                				errMsg_alert += 'Please enter a valid email address. \n';
                				$("#error_formfield3").show()
                					//return fregister_useralse;
                				} else {
                					$("#error_formfield3").hide()
                				} 
            				
            				if(!$("#join_date").val()){
             					 
                				isError=true;
                				errMsg += '<li>Please enter a valid name.</li>';
                				
                				$("#join_date").addClass("has-error")
                				$("#error_formfield4").show()
                					//return false;
                				} else {
                					$("#error_formfield4").hide()
                				}
            				 
            				if ( $("#is_registration2").is(":checked")) {
            					if(!$("#acc_off_relDate").val()){
            					isError=true;
                				errMsg += '<li>Please enter a valid name.</li>';
                				
                				$("#acc_off_relDate").addClass("has-error1")
                				$("#error_acc_off_relDate").show()
                					//return false;
                				} else {
                					$("#error_acc_off_relDate").hide()
                				}
            		          }
            				if ( $("#is_registration1").is(":checked")) {
            					$("#error_acc_off_relDate").hide()
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

	<script type="text/javascript">
/* $(function () {
	 
    $('.datepicker').datepicker({
		autoclose: true,
        format: "dd-mm-yyyy",
        changeYear:true,
        changeMonth:true

	});
}); */

	function showDiv(value) {
	//alert(value);
		if (value == 0) {
			//alert(value);
			document.getElementById("abc").style.display = "block";
		} else {
			//alert(value);
			document.getElementById("abc").style.display = "none";
		}
	}

	
	
	function submit_f(view){
		document.getElementById("is_view").value=view;//create this 
		
	}
	
	

	</script>
	<script type="text/javascript">
	 

	function setDate1(value){
	//alert("Value " +value);
	var x=0;
	x=${dean.facultyId}
	if(x>0){
	var isWork=0;
	isWork=${dean.isWorking}
	if(isWork==1){
		
		//alert(value)
		//document.getElementById("acc_off_relDate").removeAttribute("required");
		document.getElementById("abc").style = "display:none"

		//alert(value)
		}else{
			//alert(value)
			//document.getElementById("acc_off_relDate").setAttribute("required","true");
			document.getElementById("abc").style.display = "block";

			//alert(value)
		}
	}
	selcState();
	}
	
	</script>
	<script type="text/javascript">
	
	function setDate(value){
		//alert("Value " +value);
	
		if(value==1){
			
			//alert(value)
			//document.getElementById("acc_off_relDate").removeAttribute("required");
			document.getElementById("abc").style = "display:none"

			//alert(value)
			}else{
				//alert(value)
				//document.getElementById("acc_off_relDate").setAttribute("required","true");
				document.getElementById("abc").style.display = "block";

				//alert(value)
			}
		}
			
	
	
	</script>
	<script type="text/javascript">
		function checkUnique(inputValue, valueType) {
			//alert("hi");

			document.getElementById("sub2").disabled = false;

			var valid = false;
			if (valueType == 1) {
				//alert("Its Mob no");
				if (inputValue.length == 10) {
					valid = true;
					//alert("Len 10")
				} else {
					//alert("Not 10");
				}
			} else if (valueType == 2) {
				//alert("Its Email " );

				var mailformat = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
				if (inputValue.match(mailformat)) {
					valid = true;
					//alert("Valid Email Id");
				} else {
					valid = false;
					//alert("InValid Email Id");
				}
			}
			if (valid == true)

				$
						.getJSON(
								'${getUserInfo}',
								{
									inputValue : inputValue,
									valueType : valueType,
									ajax : 'true',

								},
								function(data) {
									//alert("data" + data);

								//	alert("Data  " +JSON.stringify(data));

								 if(data.facultyId>0){

										document.getElementById("email").value = data.email;
										document.getElementById("contact_no").value = data.contactNo;
										document.getElementById("dean_name").value = data.facultyFirstName;
										document.getElementById("join_date").value = data.joiningDate;
										document.getElementById("dean_id").value = data.facultyId;
										document.getElementById("acc_off_relDate").value = data.realivingDate;

										/* document.getElementById("designation").options.selectedIndex = data.currentDesignationId;
										$("#designation").trigger("chosen:updated");
										var temp = new Array();

										temp = (data.deptId).split(",");
										//alert(temp);
										$("#dept_id").val(temp);
										$("#dept_id").trigger("chosen:updated"); */
										
										//Mahendra
										//single select
										document.getElementById("designation").value=data.currentDesignationId;
										$("#designation").trigger("chosen:updated");
										//single select
										document.getElementById("quolif").value=data.highestQualification;
										$("#quolif").trigger("chosen:updated");
										//multiple select
									 	var temp = new Array();
									 	temp = (data.deptId).split(",");
										  $('#dept_id').val(temp);
										  $('#dept_id').trigger('change'); // Notify any JS components that the value changed
								 }else{
										/* document.getElementById("email").value = "";
									//	document.getElementById("contact_no").value = "";
										document.getElementById("dean_name").value = "";
										document.getElementById("join_date").value = "";
										document.getElementById("dept_id").value = 0;
										document.getElementById("quolif").value = 0;
										document.getElementById("designation").value = 0;
										document.getElementById("acc_off_relDate").value = ""; */
								 }

								});
		}
	</script>
	<script>
		
	$("#contactNo").on("keypress keyup blur",function (event) {
        //this.value = this.value.replace(/[^0-9\.]/g,'');
 $(this).val($(this).val().replace(/[^0-9\.]/g,''));
        if ((event.which != 46 || $(this).val().indexOf('.') != -1) && (event.which < 48 || event.which > 57)) {
            event.preventDefault();
        }
    });
	
	$("#dateOfJoin").on("keypress keyup blur",function (event) {
        //this.value = this.value.replace(/[^0-9\.]/g,'');
 $(this).val($(this).val().replace(/[^0-9\.]/g,''));
        if ((event.which != 46 || $(this).val().indexOf('.') != -1) && (event.which < 48 || event.which > 57)) {
            event.preventDefault();
        }
    });
	</script>

	<script type="text/javascript">
	
	function showIsReg(){
		//alert("Hi");
		var x=${dean.facultyId};
	
		if(x>0){
			//alert("Hi 1")
		var isRel=${dean.realivingDate};
		//alert("Is Reg " +isReg);
		if(isRel==null){
			//alert("Hi 2")
			document.getElementById("abc").style.display = "none";

		}else{
			//alert("Hi es")
			document.getElementById("abc").style.display = "block";
			
		}
			
		}
		
		 var isTaxInc = $("input[name=is_registration]:checked").val()
		 
		 if(isTaxInc==1){
				//alert("Hi 2")
				document.getElementById("abc").style.display = "none";

			}else{
				//alert("Hi es")
				document.getElementById("abc").style.display = "block";
				
			}
		 
		 
	}
</script>


</body>
</html>