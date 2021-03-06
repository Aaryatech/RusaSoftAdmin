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
<body class=" " onload="selcState()">
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


				<div class="clearfix"></div>
				<!-- MAIN CONTENT AREA STARTS -->

				<div class="col-lg-12">
					<section class="box ">

						<header class="panel_header">
							<h2 class="title pull-left">${title}</h2>

							<div class="actions panel_actions pull-right">
								<%-- <a href="${pageContext.request.contextPath}/showIqacList"><button
										type="button" class="btn btn-info">Back</button></a> --%>
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertAccOff"
										method="post" name="formidhere" id="formidhere">



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
											name="token" id="token"> <input type="hidden"
											id="acc_id" name="acc_id" value="${editFaculty.facultyId}">
										<input type="hidden" id="addEdit" name="addEdit"
											value="${addEdit}">
										<div class="form-group">
											<label class="control-label col-sm-2" for="page_name">
												Name<span class="text-danger">*</span>
											</label>
											<div class="col-sm-10">
												<input type="text" class="form-control" id="accName"
													onchange="trim(this)" autocomplete="off"
													value="${editFaculty.facultyFirstName}" name="accName"
													placeholder="Account Officer Name"> <span
													class="error_form text-danger" id="error_formfield1"
													style="display: none;">Please enter Account Officer
													name</span>
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
																test="${makeList.designationId == editFaculty.currentDesignationId}">
																<option value="${makeList.designationId}"
																	selected="selected">${makeList.designationName}</option>
															</c:when>
															<c:otherwise>
																<option value="${makeList.designationId}">${makeList.designationName}</option>
															</c:otherwise>
														</c:choose>
													</c:forEach>

												</select> <span class="error_form text-danger" id="error_formfield2"
													style="display: none;">Please select designation</span>
											</div>
										</div>

										<div class="form-group">
											<label class="control-label col-sm-2" for="page_name">
												Department<span class="text-danger">*</span>
											</label>
											<div class="col-sm-10">
												<select id="dept_id" name="dept_id" class="" multiple>
													<option>Select Department</option>
													<c:forEach items="${deptList}" var="dept">
														<c:set var="flag" value="0"></c:set>
														<c:forEach items="${deptIdList}" var="selFr"
															varStatus="count2">
															<c:choose>
																<c:when test="${selFr==dept.deptId}">
																	<option selected value="${dept.deptId}"><c:out
																			value="${dept.deptName}" /></option>
																	<c:set var="flag" value="1"></c:set>
																</c:when>
																<c:otherwise>

																</c:otherwise>
															</c:choose>
														</c:forEach>
														<c:choose>
															<c:when test="${flag==0}">
																<option value="${dept.deptId}"><c:out
																		value="${dept.deptName}" /></option>
															</c:when>
														</c:choose>
													</c:forEach>
													<%-- <c:forEach items="${deptList}" var="dept">
														<c:choose>
															<c:when test="${editFaculty.deptId==dept.deptId}">
																<option selected value="${dept.deptId}">${dept.deptName}</option>

															</c:when>
															<c:otherwise>
																<option value="${dept.deptId}">${dept.deptName}</option>

															</c:otherwise>

														</c:choose>

													</c:forEach> --%>
													<!-- <option value="0">NA</option> -->


												</select> <span class="error_form text-danger" id="dept_id_field"
													style="display: none;">Please select department name</span>
											</div>
										</div>


										<div class="form-group">
											<label class="control-label col-sm-2" for="status">Highest
												Qualification<span class="text-danger">*</span>
											</label>
											<div class="col-sm-10">
												<select id="quolif" name="quolif" class="form-control">
													<c:forEach items="${quolfList}" var="quolf">
														<c:choose>
															<c:when
																test="${editFaculty.highestQualification==quolf.qualificationId}">
																<option selected value="${quolf.qualificationId}">${quolf.qualificationName}</option>

															</c:when>
															<c:otherwise>

																<option value="${quolf.qualificationId}">${quolf.qualificationName}</option>

															</c:otherwise>

														</c:choose>

													</c:forEach>

												</select> <span class="error_form text-danger" id="quolf_field"
													style="display: none;">Please select highest
													qualification</span>
											</div>
										</div>


										<div class="form-group">
											<label class="control-label col-sm-2" for="page_order">Joining
												Date <span class="text-danger">*</span>
											</label>
											<div class="col-sm-3">
												<input type="text" class="form-control datepicker"
													id="dateOfJoin" onchange="trim(this)" data-end-date="0d"
													data-format="dd-mm-yyyy"
													onkeypress='return restrictAlphabets(event)'
													value="${editFaculty.joiningDate}" name="dateOfJoin"
													autocomplete="off" placeholder="dd/mm/yyyy"> <span
													class="error_form text-danger" id="error_formfield3"
													style="display: none;">Please select joining date</span>
											</div>
										</div>

										<div class="form-group">
											<label class="control-label col-sm-2" for="jouStd">Is
												Currently Working <span class="text-danger">*</span>
											</label>

											<c:choose>
												<c:when test="${editFaculty.isWorking==0}">
													<div class="col-md-2">

														<input type="radio" name="isWorking" id="isWorking"
															value="0" onchange="chengeDate(this.value)" checked>No

													</div>

													<div class="col-md-2">
														<input type="radio" name="isWorking"
															onchange="chengeDate(this.value)" value="1"> Yes
													</div>
												</c:when>
												<c:when test="${editFaculty.isWorking==1}">
													<div class="col-md-2">

														<input type="radio" name="isWorking" id="isWorking"
															onchange="chengeDate(this.value)" value="0">No

													</div>

													<div class="col-md-2">
														<input type="radio" name="isWorking"
															onchange="chengeDate(this.value)" value="1" checked>
														Yes
													</div>
												</c:when>
												<c:otherwise>

													<div class="col-md-2">

														<input type="radio" name="isWorking" id="isWorking"
															onchange="chengeDate(this.value)" value="0">No

													</div>

													<div class="col-md-2">
														<input type="radio" name="isWorking" value="1" checked
															onchange="chengeDate(this.value)"> Yes
													</div>


												</c:otherwise>
											</c:choose>

										</div>

										<div class="form-group" id="realivingDate"
											style="display: none">
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
													value="${editFaculty.realivingDate}"> <span
													class="error_form text-danger" id="error_acc_off_relDate"
													style="display: none;">Please enter relieving date</span>
											</div>
										</div>

										<div class="form-group">

											<label class="control-label col-sm-2" for="is_add_same">Belongs
												to MH State<span class="text-danger">*</span>
											</label>

											<div class="col-sm-3">

												<c:if test="${editFaculty.facultyId>0}">
													Yes <input type="radio"
														${editFaculty.isSame == 1 ? 'checked' : ''}
														name="is_state_same" id="is_state_same" value="1"
														onclick="selcState()"> 
													No<input type="radio"
														${editFaculty.isSame == 0 ? 'checked' : ''}
														name="is_state_same" id="is_state_same" value="0"
														onclick="selcState()">
												</c:if>

												<c:if test="${editFaculty.facultyId==0}">
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
																test="${editFaculty.facultyMiddelName == state.stateId}">
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
												No. <span class="text-danger">*</span>
											</label>
											<div class="col-sm-10">
												<input type="text" class="form-control" id="contactNo"
													onchange="trim(this)" name="contactNo"
													onkeypress='return restrictAlphabets(event)'
													autocomplete="off" maxlength="10"
													title="Phone number with 7-9 and remaing 9 digit with 0-9"
													placeholder="Mobile Number"
													value="${editFaculty.contactNo}"> <span
													class="error_form text-danger" id="error_formfield4"
													style="display: none;">Please enter contact no</span>

												<p class="desc text-danger fontsize11">Note: OTP will be
													sent on this mobile number for verification</p>

											</div>
										</div>


										<div class="form-group">
											<label class="control-label col-sm-2" for="smallheading">Email
												ID (Official) <span class="text-danger">*</span>
											</label>
											<div class="col-sm-10">
												<input type="email" class="form-control" id="email"
													autocomplete="off" onchange="checkUnique(this.value,2)"
													onchange="trim(this)" name="email"
													placeholder="abc@xyz.com" value="${editFaculty.email}">
												<span class="error_form text-danger" id="error_formfield5"
													style="display: none;">Please enter email id</span>
												<p class="desc font-italic fontsize11">Note:
													Verification mail will be sent on this Email id</p>

											</div>

										</div>
										<%-- <div class="form-group">
											<label class="control-label col-sm-2" for="smallheading">Roles
												<span class="text-danger">*</span>
											</label>
											<div class="col-sm-10">
												<input type="checkbox" name="isAccOff" value="1" ${editFaculty.isAccOff==1 ? 'checked' : ''}>Account
												Officer
											</div>
										</div> --%>

										<div class="form-group">
											<div class="col-sm-offset-2 col-sm-10">
												<button type="submit" id="sub1" class="btn btn-primary"
													onclick="submit_f(1)">
													<i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save
												</button>

												<a href="${pageContext.request.contextPath}/showAccList"><button
														id="sub2" type="button" class="btn btn-primary">
														<i class="${sessionScope.cancelIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Cancel
													</button></a>
												<!-- <button type="reset" class="btn btn-default">Reset</button> -->
												<input type="hidden" id="is_view" name="is_view" value="0">
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
	<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>

	<script type="text/javascript">
		function selcState() {
			// alert("Hi");
			var isSamState = $("input[name='is_state_same']:checked").val();
			// alert(isSamState);

			if (isSamState == 0) {
				document.getElementById("state").style.display = "block";
			} else {
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
		$(document)
				.ready(
						function($) {

							$("#formidhere")
									.submit(
											function(e) {
												var isError = false;
												var errMsg = "";

												if (!$("#accName").val()) {

													isError = true;
													errMsg += '<li>Please enter a valid name.</li>';

													$("#accName").addClass(
															"has-error")
													$("#error_formfield1")
															.show()
													//return false;
												} else {
													$("#error_formfield1")
															.hide()
												}

												if (!$("#designation").val()) {

													isError = true;
													errMsg += '<li>Please enter a valid name.</li>';

													$("#designation").addClass(
															"has-error")
													$("#error_formfield2")
															.show()
													//return false;
												} else {
													$("#error_formfield2")
															.hide()
												}

												if (!$("#dateOfJoin").val()) {

													isError = true;
													errMsg += '<li>Please enter a valid name.</li>';

													$("#dateOfJoin").addClass(
															"has-error")
													$("#error_formfield3")
															.show()
													//return false;
												} else {
													$("#error_formfield3")
															.hide()
												}

												if (!$("#dept_id").val()) {
													isError = true;

													$("#dept_id").addClass(
															"has-error")
													$("#dept_id_field").show()
												} else {
													$("#dept_id_field").hide()
												}

												if (!$("#quolif").val()) {
													isError = true;
													$("#quolif").addClass(
															"has-error")
													$("#quolf_field").show()
													//return false;
												} else {
													$("#quolf_field").hide()
												}
												if (!$("#contactNo").val()
														|| !validateMobile($(
																"#contactNo")
																.val())) {

													isError = true;
													errMsg += '<li>Please enter a valid email address.</li>';
													errMsg_alert = 'Please enter a valid mobile number.';
													$("#error_formfield4")
															.html(errMsg_alert);
													$("#error_formfield4")
															.show();
													//alert();
													//return false;
												} else {
													$("#error_formfield4")
															.html(
																	"Please enter mobile")
													$("#error_formfield4")
															.hide()
												}
												if (!$("#email").val()
														|| !validateEmail($(
																"#email").val())) {

													isError = true;
													errMsg += '<li>Please enter a valid email address.</li>';
													errMsg_alert += 'Please enter a valid email address. \n';
													$("#error_formfield5")
															.show()
													//return fregister_useralse;
												} else {
													$("#error_formfield5")
															.hide()
												}

												if (!isError) {

													var x = confirm("Do you really want to submit the form?");
													if (x == true) {

														document
																.getElementById("sub1").disabled = true;
														document
																.getElementById("sub2").disabled = true;
														return true;
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
		function restrictAlphabets(e) {
			var x = e.which || e.keycode;
			if ((x >= 48 && x <= 57) || x == 8 || (x >= 35 && x <= 40)
					|| x == 46)
				return true;
			else
				return false;
		}
	</script>

	<script type="text/javascript">
		/* $(function() {

			$('.datepicker').datepicker({
				autoclose : true,
				format : "dd-mm-yyyy",
				changeYear : true,
				changeMonth : true

			});
		}); */

		/* function showForm() {
			//document.getElementById("abc").style = "display:none"
				var qualType=document.getElementById("qualification").value
			//alert("qualType::"+qualType);
				
				if (qualType == 5) {

					document.getElementById("abc").style = "visible"
					
						
				} 
				else{
					document.getElementById("abc").style = "display:none"
				}
			
			} */
		/* function hideText() {
			//alert("hii");
			document.getElementById("abc").style = "display:none"
				
			
			} */

		function trim(el) {
			el.value = el.value.replace(/(^\s*)|(\s*$)/gi, ""). // removes leading and trailing spaces
			replace(/[ ]{2,}/gi, " "). // replaces multiple spaces with one space 
			replace(/\n +/, "\n"); // Removes spaces after newlines
			return;
		}
	</script>

	<script type="text/javascript">
		function showDiv(value) {

			if (value == 0) {
				//alert(value);
				document.getElementById("abc").style.display = "block";
			} else {
				//alert(value);
				document.getElementById("abc").style.display = "none";
			}
		}

		function submit_f(view) {
			document.getElementById("is_view").value = view;//create this 

		}
	</script>
	<script type="text/javascript">
		function setDate(value) {
			//alert("Value " +value)
			if (value == 1) {
				//alert(value)
				document.getElementById("acc_off_relDate").removeAttribute(
						"required");
				document.getElementById("abc").style.display = "none";

				//alert(value)
			} else {
				//alert(value)
				document.getElementById("acc_off_relDate").setAttribute(
						"required", "true");
				document.getElementById("abc").style.display = "block";

				//alert(value)

			}

		}
	</script>
	<script type="text/javascript">
		function showIsReg() {
			//alert("Hi");
			var x = $
			{
				accOff.officerId
			}

			if (x > 0) {
				//alert("Hi 1")
				var isRel = $
				{
					accOff.realivingDate
				}
				;
				//alert("Is Reg " +isReg);
				if (isRel == null) {
					//alert("Hi 2")
					document.getElementById("abc").style.display = "none";

				} else {
					//alert("Hi es")
					document.getElementById("abc").style.display = "block";

				}

			}

		}
	</script>
	<script type="text/javascript">
		/* function checkUnique(inputValue, valueType) {

			document.getElementById("sub1").disabled = false;
			document.getElementById("sub2").disabled = false;

			//alert(inputValue+" "+valueType);

			var primaryKey = $
			{
				miqc.iqacId
			}
			;
			//alert("Primary key"+primaryKey);
			var isEdit = 0;
			if (primaryKey > 0) {
				isEdit = 1;
			}
			//alert("Is Edit " +isEdit);

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

				var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
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
								'${chkFields}',
								{

									inputValue : inputValue,
									valueType : valueType,
									primaryKey : primaryKey,
									isEdit : isEdit,
									tableId : 1,

									ajax : 'true',

								},
								function(data) {

									//alert("Data  " +JSON.stringify(data));
									if (data.error == true) {
										if (valueType == 2) {
											document.getElementById("email").value = "";
											alert("This email id already exist in system please enter unique");
										} else {
											document
													.getElementById("contactNo").value = "";

											alert("This contact no  already exist in system please enter unique");
										}
									}
								});
		}
		 */
		function submit_f(view) {
			document.getElementById("is_view").value = view;//create this 
			/* var form=document.getElementById("form_sample_2");
			form.setAttribute("method", "post");

			form.action=("insertHod");
			var x =confirm("Do you really want to submit the form?");
			if(x==true)
			form.submit(); */

		}

		/* function showForm() {
			//document.getElementById("abc").style = "display:none"
				var qualType=document.getElementById("qualification").value
			//alert("qualType::"+qualType);
				
				if (qualType == 5) {

					document.getElementById("abc").style = "visible"
					
						
				} 
				else{
					document.getElementById("abc").style = "display:none"
				}
			
			} */
		/* function hideText() {
			//alert("hii");
			document.getElementById("abc").style = "display:none"
				
			
			} */
	</script>
	<!-- <script type="text/javascript">
	
	
	function checkUnique(inputValue,valueType){
    	//alert(inputValue);
    				document.getElementById("sub1").disabled=false;
					document.getElementById("sub2").disabled=false;
    	
    	var primaryKey=${accOff.officerId};
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
    		tableId : 5,

			ajax : 'true',

		}, function(data) {
			
		//	alert("Data  " +JSON.stringify(data));
			if(data.error==true){
				if(valueType==2){
					document.getElementById("acc_off_email").value="";

				alert("This email id already exist in system please enter unique");
				
					document.getElementById("sub1").disabled=true;
					document.getElementById("sub2").disabled=true;
				
				}
				else{
					document.getElementById("acc_off_mob").value="";

					alert("This contact no  already exist in system please enter unique");
					
					document.getElementById("sub1").disabled=true;
					document.getElementById("sub2").disabled=true;
					
				}
			}
		});
    }
	
	</script>
 -->
	<script type="text/javascript">
		var wasSubmitted = false;
		function checkBeforeSubmit() {
			if (!wasSubmitted) {
				var x = confirm("Do you really want to submit the form?");
				if (x == true) {
					wasSubmitted = true;
					document.getElementById("sub1").disabled = true;
					document.getElementById("sub2").disabled = true;

					return wasSubmitted;
				}
			}
			return false;
		}

		$("#dept_id").select2({
			allowClear : true
		}).on(
				'select2-open',
				function() {
					// Adding Custom Scrollbar
					$(this).data('select2').results.addClass('overflow-hidden')
							.perfectScrollbar();
				});
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

									if (data.facultyId > 0) {
										//alert("Data not null");
										document.getElementById("email").value = data.email;
										document.getElementById("contactNo").value = data.contactNo;
										document.getElementById("accName").value = data.facultyFirstName;
										document.getElementById("dateOfJoin").value = data.joiningDate;
										document
												.getElementById("acc_off_relDate").value = data.realivingDate;
										document.getElementById("acc_id").value = data.facultyId;

										//Mahendra
										//single select
										document.getElementById("designation").value = data.currentDesignationId;
										$("#designation").trigger(
												"chosen:updated");
										//single select
										document.getElementById("quolif").value = data.highestQualification;
										$("#quolif").trigger("chosen:updated");

										//multiple select
										var temp = new Array();
										temp = (data.deptId).split(",");
										$('#dept_id').val(temp);
										$('#dept_id').trigger('change'); // Notify any JS components that the value changed
									} else {
										//document.getElementById("email").value = "";
										//document.getElementById("contactNo").value = "";
										/* document.getElementById("accName").value = "";
										document.getElementById("dateOfJoin").value = "";
										document.getElementById("acc_off_relDate").value = "";
										document.getElementById("acc_id").value = 0;
										document.getElementById("dept_id").value = 0;
										document.getElementById("quolif").value = 0;
										document.getElementById("designation").value = 0; */
									}

								});
		}
	</script>


	<script>
		function chengeDate(temp) {
			if (temp == 1) {
				document.getElementById("realivingDate").style = "display:none"
			} else {
				document.getElementById("realivingDate").style = "visible"

			}

		}
	</script>


</body>
</html>