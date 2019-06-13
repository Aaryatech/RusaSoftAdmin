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
<body class=" " onload="check(${facPerDetail.isAddSame})">
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
								<%-- <a href="${pageContext.request.contextPath}/showPersonalDetails"><button
										type="button" class="btn btn-info">Back</button></a> --%>
							</div>

						</header>
						 <c:if test="${sessionScope.successMsg!=null}">
           						 <div class="col-lg-12">
    						          <div class="alert alert-success alert-dismissible fade in">
            							    <button type="button" class="close" data-dismiss="alert" onclick="removSess()" aria-label="Close"><span aria-hidden="true">×</span></button>
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
										<div class="tab-pane fade in active" id="home">
 -->
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertFacPersonalDetail"
										method="post" name="form_sample_2" id="form_sample_2">
										<div class="col-md-12"></div>

										<div class="form-group">
											<label class="control-label col-sm-3" for="fac_name">
												Name(Full)<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" readonly class="form-control"
													id="fac_name" name="fac_name"
													placeholder="Last Name Middle First Name"
													value="${staff.facultyFirstName}" required>
											</div>
										</div>

										<div class="form-group">
											<label class="control-label col-sm-3" for="fac_address">Address
												<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" onchange="trim(this)" maxlength="200"
													class="form-control" id="fac_address" name="fac_address"
													placeholder="Permanent Address"
													value="${facPerDetail.fAddress}"> <span
													class="error_form text-danger" id="fac_address_field"
													style="display: none;">Please enter permanent
													address.</span>
											</div>
										</div>
										
										<div class="form-group">
											<label class="control-label col-sm-3" for="fac_address">Village
												<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" onchange="trim(this)" maxlength="200"
													class="form-control" id="village" name="village"
													placeholder="Village"
													value="${facPerDetail.fVillage}"> <span
													class="error_form text-danger" id="fac_village_field"
													style="display: none;">Please enter village.</span>
											</div>
										</div>
										
										<div class="form-group">
											<label class="control-label col-sm-3" for="fac_address">Taluka/City
												<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" onchange="trim(this)" maxlength="200"
													class="form-control" id="taluka" name="taluka"
													placeholder="Taluka/City"
													value="${facPerDetail.fTaluka}"> <span
													class="error_form text-danger" id="fac_taluka_field"
													style="display: none;">Please enter taluka.</span>
											</div>
										</div>
										
										<div class="form-group">
											<label class="control-label col-sm-3" for="fac_address">District
												<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" onchange="trim(this)" maxlength="200"
													class="form-control" id="district" name="district"
													placeholder="District"
													value="${facPerDetail.fDistrict}"> <span
													class="error_form text-danger" id="fac_district_field"
													style="display: none;">Please enter district.</span>
											</div>
										</div>
										
										
									
										
										<%-- <div class="form-group">
											<label class="control-label col-sm-3" for="fac_address">City
												<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" onchange="trim(this)" maxlength="200"
													class="form-control" id="city" name="city"
													placeholder="City"
													value="${facPerDetail.fCity}"> <span
													class="error_form text-danger" id="fac_city_field"
													style="display: none;">Please enter city.</span>
											</div>
										</div> --%>
										<div class="form-group">
											<label class="control-label col-sm-3" for="fac_address">State
												<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" onchange="trim(this)" maxlength="200"
													class="form-control" id="state" name="state"
													placeholder="State"
													value="${facPerDetail.fState}"> <span
													class="error_form text-danger" id="fac_state_field"
													style="display: none;">Please enter state.</span>
											</div>
										</div>
										
										<div class="form-group">
											<label class="control-label col-sm-3" for="fac_address">Pin Code
												<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" onchange="trim(this)" maxlength="200"
													class="form-control" id="pincode" name="pincode"
													placeholder="Pin Code"
													value="${facPerDetail.fPincode}"> <span
													class="error_form text-danger" id="fac_pincodc_field"
													style="display: none;">Please enter proper pin code.</span>
											</div>
										</div>
										
										<div class="form-group" >
											
											<label class="control-label col-sm-3" for="is_add_same">Belongs to
												 Same State <span
												class="text-danger">*</span>
											</label>

											<div class="col-sm-3">
												
													Yes <input type="radio" ${facPerDetail.isSame == 1 ? 'checked' : ''} name="is_state_same" id="is_state_same"	 value="1"> 
													No<input type="radio" ${facPerDetail.isSame == 0 ? 'checked' : ''} name="is_state_same" id="is_state_same" value="0">

													
												<span class="error_form text-danger" id="is_state_same_field"
													style="display: none;">Please select
													permanent/correspondence address same or not.</span>

											</div>
										</div>
										
										
										

										<div class="form-group">
											<div class="col-sm-1"></div>
											<label class="control-label col-sm-6" for="is_add_same">Is
												Permanent and Correspondence Address Same <span
												class="text-danger">*</span>
											</label>

											<div class="col-sm-3">
												<c:choose>
													<c:when test="${facPerDetail.isAddSame==1}">
													Yes <input type="radio" name="is_add_same" id="is_add_same"
															checked value="1" onclick="check(this.value)"> No<input
															type="radio" onclick="check(this.value)"
															name="is_add_same" id="is_add_same" value="0">

													</c:when>
													<c:otherwise>
													
													Yes <input type="radio" name="is_add_same" id="is_add_same"
															value="1" onclick="check(this.value)"> No<input
															type="radio" onclick="check(this.value)" checked
															name="is_add_same" id="is_add_same" value="0">

													</c:otherwise>

												</c:choose>
												<span class="error_form text-danger" id="is_add_same_field"
													style="display: none;">Please select
													permanent/correspondence address same or not.</span>

											</div>
										</div>

										<div class="form-group" id="abc" style="display: none">
											<label class="control-label col-sm-3" for="fac_address2">Correspondence
												Address <span class="text-danger">*</span>
											</label>
											<div class="col-sm-9">
												<input type="text" onchange="trim(this)" maxlength="200"
													class="form-control" id="fac_address2"
													value="${facPerDetail.fAddress2}" name="fac_address2"
													placeholder="Correspondence Address"> <span
													class="error_form text-danger" id="fac_address2_field"
													style="display: none;">Please enter correspondence
													address.</span>
											</div>
										</div>

										<div class="form-group">
											<label class="control-label col-sm-3" for="fac_mob">Mobile
												No<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" readonly class="form-control"
													id="fac_mob" name="fac_mob" pattern="^[1-9]{1}[0-9]{9}$"
													maxlength="10"
													title="Phone number with 7-9 and remaing 9 digit with 0-9"
													placeholder="Mobile No" value="${staff.contactNo}">
											</div>
											<div class="col-sm-2"></div>
										</div>
										<div class="form-group">
											<label class="control-label col-sm-3" for="f_phone">Phone
												No<span class="text-danger"></span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control" id="f_phone"
													onchange="trim(this)" name="f_phone" maxlength="15"
													placeholder="Office Landline No"
													value="${facPerDetail.fPhone}">

											</div>
										</div>
										<div class="form-group">
											<label class="control-label col-sm-3" for="f_resident">Resident
												No</label>
											<div class="col-sm-6">
												<input type="text" class="form-control" id="f_resident"
													onchange="trim(this)" name="f_resident" maxlength="15"
													title="Phone number with 7-9 and remaing 9 digit with 0-9"
													placeholder="Resident Phone No"
													value="${facPerDetail.fResident}">
											</div>
											<div class="col-sm-2"></div>
										</div>
										<div class="form-group">
											<label class="control-label col-sm-3" for="f_email">Email
												ID<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" readonly class="form-control"
													id="f_email" name="f_email" placeholder="abc@xyz.com"
													value="${staff.email}">
											</div>
										</div>
										<div class="form-group">
											<label class="control-label col-sm-3" for="f_aadhar">Aadhar
												No<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" maxlength="12" class="form-control"
													id="f_aadhar" name="f_aadhar"
													placeholder="Consecutive 12 digit Aadhar No"
													value="${facPerDetail.fAadhar}"> <span
													class="error_form text-danger" id="f_aadhar_field"
													style="display: none;">Please enter aadhaar No.</span>
											</div>
										</div>
										
										<div class="form-group">
											<label class="control-label col-sm-3" for="fPan">PAN
												No<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" maxlength="12" class="form-control"
													id="f_pan" name="f_pan"
													placeholder="Consecutive 12 digit PAN No"
													value="${facPerDetail.fPan}"> <span
													class="error_form text-danger" id="f_aadhar_field"
													style="display: none;">Please enter PAN No.</span>
											</div>
										</div>
										<div class="form-group">
											<label class="control-label col-sm-3" for="f_designation">
												Designation <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<select id="f_designation" name="f_designation"
													class="form-control" disabled required>
													<c:forEach items="${desigList}" var="des">
														<c:choose>
															<c:when
																test="${staff.currentDesignationId==des.designationId}">
																<option selected value="${des.designationId}">${des.designationName}</option>
															</c:when>
															<c:otherwise>
																<option value="${des.designationId}">${des.designationName}</option>
															</c:otherwise>
														</c:choose>
													</c:forEach>
												</select>
											</div>
											<div class="col-sm-2"></div>
										</div>

										<div class="form-group">
											<label class="control-label col-sm-3" for="f_dob">Date
												of Birth<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control datepicker"  data-end-date="-7000d" data-format="dd-mm-yyyy"
													id="f_dob" name="f_dob" placeholder="Enter Date Of Birth"
													value="${facPerDetail.fDob}"> <span
													class="error_form text-danger" id="f_dob_field"
													style="display: none;">Please select date of birth.</span>

											</div>
										</div>

										<div class="form-group">
											<label class="control-label col-sm-3" for="f_doj">Date
												of Joining <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control datepicker"
													id="f_doj" name="f_doj" placeholder="Date of Joining"
													value="${staff.joiningDate}" readonly disabled>
											</div>
											<div class="col-sm-2"></div>
										</div>
										<div class="form-group">
											<label class="control-label col-sm-3" for="f_prevExp">Previous
												Experience <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="number" min="0" class="form-control"
													id="f_prevExp" name="f_prevExp"
													placeholder="Previous Experience In Months"
													value="${facPerDetail.fPastExp}"> <span
													class="error_form text-danger" id="f_prevExp_field"
													style="display: none;">Please enter previous
													experience.</span>

											</div>
										</div>

										<div class="form-group">
											<label class="control-label col-sm-3" for="f_gender">Gender<span
												class="text-danger">*</span>
											</label>
											<div class="col-sm-9">
												<c:choose>
													<c:when test="${facPerDetail.fGender==0}">
													Male<input type="radio" checked name="f_gender"
															id="f_gender" value="0">&nbsp;&nbsp;&nbsp;
													Female<input type="radio" name="f_gender" id="f_gender"
															value="1">&nbsp;&nbsp;&nbsp; 
															Transgender<input type="radio" name="f_gender"
															id="f_gender" value="2">
													</c:when>

													<c:when test="${facPerDetail.fGender==1}">
													Male<input type="radio" name="f_gender" id="f_gender"
															value="0">&nbsp;&nbsp;&nbsp;Female<input
															type="radio" name="f_gender" id="f_gender" checked
															value="1">&nbsp;&nbsp;&nbsp; 
															Transgender<input type="radio" name="f_gender"
															id="f_gender" value="2">
													</c:when>
													<c:otherwise>Male<input type="radio"
															name="f_gender" id="f_gender" value="0">&nbsp;&nbsp;&nbsp;
													Female<input type="radio" name="f_gender" id="f_gender"
															value="1">&nbsp;&nbsp;&nbsp; 
															Transgender<input type="radio" checked name="f_gender"
															id="f_gender" value="2">
													</c:otherwise>

												</c:choose>
												<span class="error_form text-danger" id="f_gender_field"
													style="display: none;">Please select gender.</span>

											</div>
										</div>

										<div class="form-group">
											<div class="col-sm-offset-3 col-sm-9">
												<button type="submit" id="sub1" class="btn btn-primary"
													onclick="submit_f(1)">
													<i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save
												</button>
												<button
														type="submit" id="sub2" class="btn btn-primary">
														<i class="${sessionScope.forwardIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Next
													</button>
											</div>
											<input type="hidden" id="staff_id" name="staff_id"
												value="${staff.facultyId}"> <input type="hidden"
												id="is_view" name="is_view" value="0"> <input
												type="hidden" id="temp" name="temp" value="${temp}">
										</div>
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
	
	
	/* $('#f_dob').datepicker()
	.on('changeDate', function(ev){                 
	    $('#f_dob').datepicker('hide');
	}); */
	/* $(function () {
		var date = new Date();
		 var today = new Date(date.getFullYear(), date.getMonth(), date.getDate());
        $('.datepickerr').datepicker({
			autoclose: true,
			maxDate: today,
            format: "dd-mm-yyyy",
            changeYear:true,
            changeMonth:true, 
          
           
		});
    });   */
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
		function validateAadhaar(mobile) {
			var mob = /^[0-9]{12}$/;
			if (mob.test($.trim(mobile)) == false) {
				return false;
			}
			return true;
		}
		$(document)
				.ready(
						function($) {

							$("#form_sample_2")
									.submit(
											function(e) {
												var isError = false;
												var errMsg = "";

												if (!$("#fac_address").val()) {
													isError = true;

													$("#fac_address").addClass(
															"has-error")
													$("#fac_address_field")
															.show()
												} else {
													$("#fac_address_field")
															.hide()
												}
												
												
												
												var radioValue = $("input[name='is_add_same']:checked"). val();
												//alert(radioValue);
												if(radioValue==0){
													if (!$("#fac_address2").val()) {
														isError = true;

														$("#fac_address2").addClass(
																"has-error")
														$("#fac_address2_field").show()
													} else {
														$("#fac_address2_field").hide()
													}
												} 	
												
												if (!$("#village").val()) {
													isError = true;

													$("#village").addClass(
															"has-error")
													$("#fac_village_field").show()
												} else {
													$("#fac_village_field").hide()
												}
												
												/*****/
												
												if (!$("#district").val()) {
													isError = true;

													$("#district").addClass(
															"has-error")
													$("#fac_district_field").show()
												} else {
													$("#fac_district_field").hide()
												}
												
												if (!$("#taluka").val()) {
													isError = true;

													$("#taluka").addClass(
															"has-error")
													$("#fac_taluka_field").show()
												} else {
													$("#fac_taluka_field").hide()
												}
												
												/* if (!$("#city").val()) {
													isError = true;

													$("#city").addClass(
															"has-error")
													$("#fac_city_field").show()
												} else {
													$("#fac_city_field").hide()
												} */
												
												if (!$("#state").val()) {
													isError = true;

													$("#state").addClass(
															"has-error")
													$("#fac_state_field").show()
												} else {
													$("#fac_state_field").hide()
												}
												
												if (!$("#pincode").val()) {
													isError = true;

													$("#pincode").addClass(
															"has-error")
													$("#fac_pincode_field").show()
												} else {
													$("#fac_pincode_field").hide()
												}
																									
													if (!$("#f_aadhar").val()
															|| !validateAadhaar($(
																	"#f_aadhar")
																	.val())) {
													isError = true;

													$("#f_aadhar").addClass(
															"has-error")
													$("#f_aadhar_field")
															.show()
												} else {
													$("#f_aadhar_field")
															.hide()
												}

												if (!$("#f_dob").val()) {
													isError = true;

													$("#f_dob").addClass(
															"has-error")
													$("#f_dob_field").show()
												} else {
													$("#f_dob_field").hide()
												}


												if (!$("#f_prevExp").val()&&$("#f_prevExp").val()>=0){
													isError = true;
													$("#f_prevExp")
															.addClass(
																	"has-error")
													$("#f_prevExp_field")
															.show()
												} else {
													$("#f_prevExp_field")
															.hide()
												}

												
												
												if (!$("#f_gender").val()) {
													isError = true;

													$("#f_gender").addClass(
															"has-error")
													$("#f_gender_field").show()
												} else {
													$("#f_gender_field").hide()
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

			if (qualType == 0) {
				document.getElementById("abc").style = "visible"
					
					//document.getElementById("fac_address2").setAttribute("required","true");

			} else if (qualType == 1) {
				document.getElementById("abc").style = "display:none"
				//	document.getElementById("fac_address2").removeAttribute("required");

			}

		}
		function hideText() {
			//alert("hii");
			document.getElementById("abc").style = "display:none"
			document.getElementById("xyz").style = "display:none";
			var x=${facPerDetail.isAddSame};
			//alert("x" +x);
				//check(x);
		}

		function showForm() {
			//document.getElementById("abc").style = "display:none"
			var qualType = document.getElementById("designation").value
			//alert("qualType::"+qualType);

			if (qualType == 5) {

				document.getElementById("xyz").style = "visible"

			} else if (qualType == 1) {
				document.getElementById("xyz").style = "display:none"
			}

		}

		function calExp() {
			
			
			  const monthDifference =  moment(new Date('2019/05/30')).diff(new Date('2019/03/30'), 'months', true);
//alert("monthDifference " +monthDifference)
			var d = new Date();
			
			var a = '2019/03/30';
			var b = '2019/05/30';

			// Months between years.
	//		var months = (b.getFullYear() - a.getFullYear()) * 12;
//alert(//Mo ths " +months);
			var month = d.getMonth() + 1;
			var day = d.getDate();

			var output = d.getFullYear() + '/' + (month < 10 ? '0' : '')
					+ month + '/' + (day < 10 ? '0' : '') + day;

			//alert("date is" + output);

			var joinDate = document.getElementById("f_doj").value;
			//alert("date of join" + joinDate);

			//date1: 24/09/2015 (24th Sept 2015)
			//date2: 09/11/2015 (9th Nov 2015)
			//the difference: 2.5 (months)

			var difference = (date2.getDate() - date1.getDate()) / 30
					+ date2.getMonth() - date1.getMonth()
					+ (12 * (date2.getFullYear() - date1.getFullYear()));
			//alert("difference " +difference);

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
    
    function submit_f(view) {
		document.getElementById("is_view").value = view;//create this 

	}
      
    function trim(el) {
		el.value = el.value.replace(/(^\s*)|(\s*$)/gi, ""). // removes leading and trailing spaces
		replace(/[ ]{2,}/gi, " "). // replaces multiple spaces with one space 
		replace(/\n +/, "\n"); // Removes spaces after newlines
		return;
	}
    
</script>

<script type="text/javascript">
function removSess(){
	localStorage.clear();
}
</script>

</body>
</html>