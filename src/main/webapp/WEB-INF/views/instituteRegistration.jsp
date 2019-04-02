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
#main-content section {
	position: absolute;
	top: 0;
	bottom: 0;
	left: 0;
	right: 0;
	margin: auto;
}
</style>


<!-- BEGIN BODY -->
<body onload="showIsReg()" class=" ">
	<c:url value="/checkUniqueField" var="checkUniqueField"></c:url>
	<!-- START TOPBAR -->
	<!-- END TOPBAR -->
	<!-- START CONTAINER -->
	<div class="page-container row-fluid container-fluid">

		<!-- SIDEBAR - START -->

		<!--  SIDEBAR - END -->
		<!-- START CONTENT -->
		<section id="main-content" class=" ">
			<section class="wrapper main-wrapper row" style="">

				<%-- <div class="col-xs-12">
					<div class="page-title">

						<div class="pull-left">
							<!-- PAGE HEADING TAG - START -->
							<h1 class="title">${title}</h1>
							<!-- PAGE HEADING TAG - END -->
						</div>


					</div>
				</div> --%>

				<!-- MAIN CONTENT AREA STARTS -->




				<div class="col-lg-12">
					<section class="box ">

						<header class="panel_header">
							<h2 align="center">INSTITUTE REGISTRATION</h2>

							<div class="actions panel_actions pull-right">
								<a href="${pageContext.request.contextPath}/"><button
										type="button" class="btn btn-info">Back</button></a>
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">


									<!-- 	<ul class="nav nav-tabs">
										<li class="active"><a href="#home" data-toggle="tab">
												<i class="fa fa-home"></i> Register Form
										</a></li>


									</ul>

									<div class="tab-content">
										<div class="tab-pane fade in active" id="home">
 -->
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertInstituteDemo"
										method="post" name="form_sample_2" id="form_sample_2">

										<input type="hidden" id="inst_id" name="inst_id"
											value="${editInst.instituteId}">

										<div class="row">
											<div class="col-md-12">
												<div class="col-sm-2"></div>

												<p class="desc text-danger fontsize11">Notice : This
													form strictly need to be filled by Institutes coming under
													RUSA Maharashtra Only. You can access RUSA portal only
													after authorisation done by RUSA officials.</p>

												<div class="form-group">
													<label class="control-label col-sm-3" for="page_name">Institute
														Name<span class="text-danger">*</span>
													</label>
													<div class="col-sm-7">
														<input type="text" maxlength="100" onchange="trim(this)"
															class="form-control" id="inst_name"
															value="${editInst.instituteName}" name="inst_name"
															placeholder="Complete Name of Institute"> <span
															class="error_form text-danger" id="inst_name_field"
															style="display: none;">Please enter institute
															name</span>

													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-sm-3" for="page_name">AISHE
														Code <span class="text-danger">*</span>
													</label>
													<div class="col-sm-7">
														<input type="text" maxlength="50" onchange="trim(this)"
															class="form-control" id="aishe_code"
															value="${editInst.aisheCode}" name="aishe_code"
															placeholder="All India Survey On Higher Education code">
														<span class="error_form text-danger" id="aishe_code_field"
															style="display: none;">Please enter AISHE code</span>

													</div>
												</div>




												<div class="form-group">
													<label class="control-label col-sm-3" for="page_order">Institute
														Address<span class="text-danger">*</span>
													</label>
													<div class="col-sm-7">
														<input type="text" onchange="trim(this)" maxlength="200"
															class="form-control" id="inst_add"
															value="${editInst.instituteAdd}" name="inst_add"
															placeholder="Complete Institute Address"> <span
															class="error_form text-danger" id="inst_add_field"
															style="display: none;">Please enter institute
															address</span>

													</div>
												</div>


												<div class="form-group">
													<label class="control-label col-sm-3" for="planning">2F/12B
														Registration <span class="text-danger">*</span>
													</label>
													<div class="col-sm-7">

														<c:choose>
															<c:when test="${editInst.instituteId==0}">

																<input type="radio" id="is_registration"
																	name="is_registration" value="1" checked
																	onclick="setDate(this.value)">Yes
																<input type="radio" id="is_registration"
																	name="is_registration" value="0"
																	onclick="setDate(this.value)">No
															
															</c:when>
															<c:otherwise>

																<c:choose>
																	<c:when test="${editInst.isRegistration==1}">
																		<input type="radio" id="is_registration"
																			name="is_registration" value="1" checked
																			onclick="setDate(this.value)">Yes
																<input type="radio" id="is_registration"
																			name="is_registration" value="0"
																			onclick="setDate(this.value)">No
															
																
																</c:when>
																	<c:otherwise>
																		<input type="radio" id="is_registration"
																			name="is_registration" value="1"
																			onclick="setDate(this.value)">Yes
																<input type="radio" id="is_registration" checked
																			name="is_registration" value="0"
																			onclick="setDate(this.value)">No
															
																
																</c:otherwise>

																</c:choose>

															</c:otherwise>


														</c:choose>
														<span class="error_form text-danger"
															id="is_registration_field" style="display: none;">Please
															select yes/no</span>


													</div>

												</div>
												<div class="form-group" id="abc">
													<label class="control-label col-sm-3" for="page_order">Date
														of Registration <span class="text-danger">*</span>
													</label>


													<div class="col-sm-7">
														<input type="text" class="form-control datepicker"
															autocomplete="off" id="reg_date"
															value="${editInst.regDate}" name="reg_date"
															placeholder="Date of Registration"> <span
															class="error_form text-danger" id="reg_date_field"
															style="display: none;">Please select date of
															registration</span>

													</div>
												</div>

												<div class="form-group">
													<label class="control-label col-sm-3" for="page_order">Trust/Society
														Name <span class="text-danger">*</span>
													</label>
													<div class="col-sm-7">
														<input type="text" onchange="trim(this)" maxlength="100"
															class="form-control" id="trusty_name"
															value="${editInst.trustName}" name="trusty_name"
															placeholder="Trust/Society Name"> <span
															class="error_form text-danger" id="trusty_name_field"
															style="display: none;">Please enter trust/society
															name</span>

													</div>
												</div>

												<div class="form-group">
													<label class="control-label col-sm-3" for="page_order">Trust/Society
														Address <span class="text-danger">*</span>
													</label>
													<div class="col-sm-7">
														<input type="text" onchange="trim(this)" maxlength="200"
															class="form-control" id="trusty_add"
															value="${editInst.trustAdd}" name="trusty_add"
															placeholder="Trust/Society Address" value=""> <span
															class="error_form text-danger" id="trusty_add_field"
															style="display: none;">Please enter trust/society
															address</span>
													</div>
												</div>

												<div class="form-group">
													<label class="control-label col-sm-3" for="page_order">Official
														Contact No <span class="text-danger">*</span>
													</label>
													<div class="col-sm-7">
														<input type="text" value="${editInst.trustContactNo}"
															onchange="trim(this)" maxlength="15" class="form-control"
															id="trusty_con_no" name="trusty_con_no"
															placeholder="Trust/Society Official Contact No">
														<span class="error_form text-danger"
															id="trusty_con_no_field" style="display: none;">Please
															enter trust/society official contact no</span>
													</div>
												</div>

												<div class="form-group">
													<label class="control-label col-sm-3" for="page_order">
														Chairman/President Name<span class="text-danger">*</span>
													</label>
													<div class="col-sm-7">
														<input type="text" onchange="trim(this)" maxlength="200"
															class="form-control" id="pres_name"
															value="${editInst.presidentName}" name="pres_name"
															placeholder="Name of Chairman/President(Board of Governance)">
														<span class="error_form text-danger" id="pres_name_field"
															style="display: none;">Please enter
															chairman/president (board of governance) name</span>
													</div>
												</div>

												<div class="form-group">
													<label class="control-label col-sm-3" for="page_order">Chairman
														Contact No <span class="text-danger">*</span>
													</label>
													<div class="col-sm-7">
														<input type="text" value="${editInst.presidenContact}"
															maxlength="15" class="form-control" id="pres_contact"
															name="pres_contact"
															placeholder="Chairman/President Contact No"> <span
															class="error_form text-danger" id="pres_contact_field"
															style="display: none;">Please enter
															chairman/president contact no</span>

													</div>
												</div>

												<div class="form-group">
													<label class="control-label col-sm-3" for="page_order">Email
														ID <span class="text-danger">*</span>
													</label>
													<div class="col-sm-7">
														<input type="text" onchange="trim(this)"
															class="form-control" id="pres_email"
															value="${editInst.presidentEmail}" name="pres_email"
															placeholder="Chairman/President Email Id">
														<span class="error_form text-danger" id="pres_email_field"
															style="display: none;">Please enter
															chairman/president email id</span>

													</div>
												</div>




												<div class="form-group">
													<label class="control-label col-sm-3" for="page_order">
														Principal Name <span class="text-danger">*</span>
													</label>
													<div class="col-sm-7">
														<input type="text" maxlength="100" onchange="trim(this)"
															class="form-control" id="princ_name"
															value="${editInst.principalName}" name="princ_name"
															placeholder="Name of Principal"> <span
															class="error_form text-danger" id="princ_name_field"
															style="display: none;">Please enter principal
															name</span>

													</div>
												</div>

												<div class="form-group">
													<label class="control-label col-sm-3" for="page_order">Mobile
														No <span class="text-danger">*</span>
													</label>
													<div class="col-sm-7">
														<input type="text" 
															maxlength="10" class="form-control" id="princ_contact"
															value="${editInst.contactNo}" name="princ_contact"
															placeholder="Principal Mobile No"
															oninput="checkUnique(this.value,1)"> <span
															class="error_form text-danger" id="princ_contact_field"
															style="display: none;">Please enter principal
															mobile no</span>

														<p class="desc font-italic fontsize11">Note: OTP will
															be sent on this mobile number for verification</p>
													</div>

												</div>

												<div class="form-group">
													<label class="control-label col-sm-3" for="page_order">Email
														ID <span class="text-danger">*</span>
													</label>
													<div class="col-sm-7">
														<input type="text" onchange="trim(this)"
															class="form-control" id="princ_email"
															value="${editInst.email}"
															oninput="checkUnique(this.value,2)" name="princ_email"
															placeholder=" Principal Email Id (Official)"> <span
															class="error_form text-danger" id="princ_email_field"
															style="display: none;">Please enter principal
															email id</span>

														<p class="desc font-italic fontsize11">Note:
															Verification mail will be sent on this Email id</p>
													</div>
												</div>


												<div class="form-group">
													<div class="col-sm-offset-3 col-sm-7">

														<input type="submit" id="sub_button"
															class="btn btn-primary" value="Submit" />
														<button type="reset" class="btn btn-default">Reset</button>
													</div>
												</div>



											</div>
										</div>

									</form>
								</div>
								<div class="clearfix"></div>
							</div>
							<div class="col-sm-3"></div>

							<p class="desc text-danger fontsize11">Notice : * Fields are
								mandatory.</p>
						</div>
					</section>
				</div>
			</section>
		</section>
	</div>

	<!-- MAIN CONTENT AREA ENDS -->

	<!-- END CONTENT -->

	<%-- 	<div aria-hidden="true" role="dialog" tabindex="-1" id="myModal2"
		class="modal fade" style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button aria-hidden="true" data-dismiss="modal" class="close"
						type="button">×</button>
					<h4 class="modal-title">Institute Details Confirmation</h4>
				</div>
				<div class="modal-body">
					<form role="form"
						action="${pageContext.request.contextPath}/showModuleForm"
						method="get">
					<input type="hidden" class="form-control" id="pageId" name="pageId">



					<div class="form-group">

						<label class="control-label col-sm-6" for="page_name">Institute
							Name: </label> <label id="inst_Name1" for="page_name"> </label>
					</div>


					<div class="form-group">

						<label class="control-label col-sm-6" for="page_name">AISHE
							Code: </label> <label id="aishe_code1" for="page_name"> </label>
					</div>



					<div class="form-group">

						<label class="control-label col-sm-6" for="page_name">Institute
							Address: </label> <label id="inst_Add1" for="page_name"> </label>
					</div>

					<div class="form-group">

						<label class="control-label col-sm-6" for="page_name">Is
							Registered: </label> <label id="is_reg1" for="page_name"> </label>
					</div>

					<div class="form-group">

						<label class="control-label col-sm-6" for="page_name">Registration
							Date: </label> <label id="reg_date1" for="page_name"> </label>
					</div>

					<div class="form-group">

						<label class="control-label col-sm-6" for="page_name">Trust/Society
							Name : </label> <label id="trust_Name1" for="page_name"> </label>
					</div>

					<div class="form-group">

						<label class="control-label col-sm-6" for="page_name">Trust/Society
							Address : </label> <label id="trust_Add1" for="page_name"> </label>
					</div>

					<div class="form-group">

						<label class="control-label col-sm-6" for="page_name">Contact
							No : </label> <label id="trust_Con1" for="page_name"> </label>
					</div>

					<div class="form-group">

						<label class="control-label col-sm-6" for="page_name">Chairman/President
							Name: </label> <label id="chairman_Name1" for="page_name"> </label>
					</div>

					<div class="form-group">

						<label class="control-label col-sm-6" for="page_name">Contact
							No : </label> <label id="Chair_Con1" for="page_name"> </label>
					</div>

					<div class="form-group">

						<label class="control-label col-sm-6" for="page_name">Email
							ID(Official): </label> <label id="chair_Email1" for="page_name">
						</label>
					</div>

					<div class="form-group">

						<label class="control-label col-sm-6" for="page_name">Principal
							Name : </label> <label id="princi_Name1" for="page_name"> </label>
					</div>
					<!-- 	-->
					<div class="form-group">

						<label class="control-label col-sm-6" for="page_name">Contact
							No : </label> <label id="princi_Con1" for="page_name"> </label>
					</div>

					<div class="form-group">

						<label class="control-label col-sm-6" for="page_name">Email
							ID(Official): </label> <label id="princi_Email1" for="page_name">
						</label>
					</div>


					<button type="submit" class="btn btn-primary" onclick="getOpt()">Confirm</button>
					<!-- 	</form> -->
				</div>
			</div>
		</div>
	</div> --%>
<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>

	<script>
		//
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

							$("#form_sample_2")
									.submit(
											function(e) {
												var isError = false;
												var errMsg = "";

												if (!$("#inst_name").val()) {
													isError = true;

													$("#inst_name").addClass(
															"has-error")
													$("#inst_name_field")
															.show()
												} else {
													$("#inst_name_field")
															.hide()
												}

												if (!$("#aishe_code").val()) {
													isError = true;

													$("#aishe_code").addClass(
															"has-error")
													$("#aishe_code_field")
															.show()
												} else {
													$("#aishe_code_field")
															.hide()
												}

												if (!$("#inst_add").val()) {
													isError = true;

													$("#inst_add").addClass(
															"has-error")
													$("#inst_add_field").show()
												} else {
													$("#inst_add_field").hide()
												}

												if (!$("#is_registration")
														.val()) {
													isError = true;

													$("#is_registration")
															.addClass(
																	"has-error")
													$("#is_registration_field")
															.show()
												} else {
													$("#is_registration_field")
															.hide()
												}

												var x = $("#is_registration")
														.val();
												if (x == 1)
													if (!$("#reg_date").val()) {
														isError = true;

														$("#reg_date")
																.addClass(
																		"has-error")
														$("#reg_date_field")
																.show()
													} else {
														$("#reg_date_field")
																.hide()
													}

												if (!$("#trusty_name").val()) {
													isError = true;

													$("#trusty_name").addClass(
															"has-error")
													$("#trusty_name_field")
															.show()
												} else {
													$("#trusty_name_field")
															.hide()
												}

												if (!$("#trusty_add").val()) {
													isError = true;

													$("#trusty_add").addClass(
															"has-error")
													$("#trusty_add_field")
															.show()
												} else {
													$("#trusty_add_field")
															.hide()
												}

												if (!$("#trusty_con_no").val()) {
													isError = true;

													$("#trusty_con_no")
															.addClass(
																	"has-error")
													$("#trusty_con_no_field")
															.show()
												} else {
													$("#trusty_con_no_field")
															.hide()
												}

												if (!$("#pres_name").val()) {
													isError = true;
													$("#pres_name").addClass(
															"has-error")
													$("#pres_name_field")
															.show()
												} else {
													$("#pres_name_field")
															.hide()
												}

												if (!$("#pres_contact").val()) {
													isError = true;
													$("#pres_contact")
															.addClass(
																	"has-error")
													$("#pres_contact_field")
															.show()
												} else {
													$("#pres_contact_field")
															.hide()
												}

												if (!$("#pres_email").val()
														|| !validateEmail($(
																"#pres_email")
																.val())) {
													isError = true;
													$("#pres_email").addClass(
															"has-error")
													$("#pres_email_field")
															.show()
												} else {
													$("#pres_email_field")
															.hide()
												}
												
												if (!$("#princ_name").val()) {
													isError = true;
													$("#princ_name").addClass(
															"has-error")
													$("#princ_name_field")
															.show()
												} else {
													$("#princ_name_field")
															.hide()
												}

												if (!$("#princ_contact").val()
														|| !validateMobile($(
																"#princ_contact")
																.val())) {
													isError = true;
													$("#princ_contact")
															.addClass(
																	"has-error")
													$("#princ_contact_field")
															.show()
												} else {
													$("#princ_contact_field")
															.hide()
												}

												if (!$("#princ_email").val()
														|| !validateEmail($(
																"#princ_email")
																.val())) {
													isError = true;
													$("#princ_email").addClass(
															"has-error")
													$("#princ_email_field")
															.show()
												} else {
													$("#princ_email_field")
															.hide()
												}
												
												if (!isError) {
													var x = confirm("Do you really want to submit the form?");
													if (x == true) {
														document.getElementById("sub_button").disabled = true;
														return  true;
													}
												}
												return false;
											});
						});
	</script>
	<script type="text/javascript">
	jQuery('.numbersOnly').keyup(function () { 
        this.value = this.value.replace(/[^0-9\.]/g,'');
    });
    jQuery('.alphaonly').keyup(function () { 
        this.value = this.value.replace(/[^a-zA-Z\s]+$/,'');
    });
    jQuery('.alhanumeric').keyup(function () { 
        this.value = this.value.replace(/[^a-zA-Z0-9\-\s]+$/,'');
    });
    jQuery('.dob').keyup(function () { 
        this.value = this.value.replace(/[^a-zA-Z0-9\-\s]+$/,'');
    });
		function showDiv(value) {
			if (value == 1) {
				//alert(value);
				document.getElementById("abc").style.display = "block";
			} else {
				//alert(value);
				document.getElementById("abc").style.display = "none";
			}
		}

		function setDate(value) {
			//alert("Value " +value)
			if (value == 0) {
				//alert(value)
				//document.getElementById("reg_date").removeAttribute("required");
				document.getElementById("abc").style.display = "none";

				//alert(value)
			} else {
				//alert(value)
				//document.getElementById("reg_date").setAttribute("required",
				//"true");
				document.getElementById("abc").style.display = "block";

				//alert(value)

			}

		}

		/* function getCOPO() {
			//alert("hii");

			var iqacName = document.getElementById("inst_name").value
			var aishe_code = document.getElementById("aishe_code").value
			var inst_add = document.getElementById("inst_add").value
			var is_registration = document.getElementById("is_registration").value
			var reg_date = document.getElementById("reg_date").value
			var trusty_name = document.getElementById("trusty_name").value
			var trusty_add = document.getElementById("trusty_add").value
			var trusty_con_no = document.getElementById("trusty_con_no").value
			var pres_name = document.getElementById("pres_name").value
			var pres_contact = document.getElementById("pres_contact").value
			var pres_email = document.getElementById("pres_email").value
			var princ_name = document.getElementById("princ_name").value
			var princ_contact = document.getElementById("princ_contact").value
			var princ_email = document.getElementById("princ_email").value
			var temp;
			var temp1;

			if (is_registration == 1) {
				//alert(reg_date);
				temp = "Yes";
				$('#reg_date1').html(reg_date);

			} else {
				alert("no...");
				temp = "No";
				temp1 = "-";
				$('#reg_date1').html(temp1);

			}

			$('#inst_Name1').html(iqacName);
			$('#aishe_code1').html(aishe_code);
			$('#inst_Add1').html(inst_add);
			$('#is_reg1').html(temp);

			$('#trust_Name1').html(trusty_name);
			$('#trust_Add1').html(trusty_add);
			$('#trust_Con1').html(trusty_con_no);

			$('#chairman_Name1').html(pres_name);
			$('#Chair_Con1').html(pres_contact);
			$('#chair_Email1').html(pres_email);
			$('#princi_Name1').html(princ_name);
			$('#princi_Con1').html(princ_contact);
			$('#princi_Email1').html(princ_email);

		}
		function getOpt() {
			//submit afrer showing details on modal dialogue
			var form = document.getElementById("form_sample_2");
			form.submit();

		}
		 */
	</script>
	<script>
		function showIsReg() {

			var x = ${editInst.instituteId};

			if (x > 0) {
				var da = ${editInst.regDate};
				
				var isReg = ${editInst.isRegistration};
				

				//alert("Is Reg " +isReg);
				if (isReg == 0) {

					document.getElementById("abc").style.display = "none";
					document.getElementById("reg_date").removeAttribute(
							"required");
					document.getElementById("reg_date").value = da;

				} else {
					document.getElementById("abc").style.display = "block";
					reg_date
					document.getElementById("reg_date").value = da;

				}

			}

		}
	</script>


	<!-- END CONTAINER -->
	<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->
	<script type="text/javascript">
		$(function() {

			$('.datepicker').datepicker({
				autoclose : true,
				format : "dd-mm-yyyy",
				changeYear : true,
				changeMonth : true
			});
		});

		function checkUnique(inputValue, valueType) {
			//alert(inputValue);

			var primaryKey = $
			{
				editInst.instituteId
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
					valid = false;
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
								'${checkUniqueField}',
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
											document
													.getElementById("princ_email").value = "";

											alert("This Email Id is Already Exist in Database. Please Login with Your Credential.");

										} else {
											document
													.getElementById("princ_contact").value = "";

											alert("This Mobile No is Already Exist in Database. Please Login with Your Credential.");
										}
									}
								});
		}
	</script>

	<script type="text/javascript">
		var wasSubmitted = false;
		function checkBeforeSubmit() {
			if (!wasSubmitted) {
				var x = confirm("Do you really want to submit the form?");
				if (x == true) {
					wasSubmitted = true;
					document.getElementById("sub_button").disabled = true;
					return wasSubmitted;
				}
			}
			return false;
		}
		function trim(el) {
			el.value = el.value.replace(/(^\s*)|(\s*$)/gi, ""). // removes leading and trailing spaces
			replace(/[ ]{2,}/gi, " "). // replaces multiple spaces with one space 
			replace(/\n +/, "\n"); // Removes spaces after newlines
			return;
		}
	</script>

	

</body>
</html>