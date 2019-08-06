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

	<c:url value="/getInstituteMasterByAisheforPrincipalChange"
		var="getInstituteMasterByAisheforPrincipalChange"></c:url>
	<!-- START TOPBAR -->
	<!-- END TOPBAR -->
	<!-- START CONTAINER -->
	<div class="page-container row-fluid container-fluid">

		<!-- SIDEBAR - START -->

		<!--  SIDEBAR - END -->
		<!-- START CONTENT -->
		<section id="main-content" class=" ">
			<section class="wrapper main-wrapper row" style="">


				<!-- MAIN CONTENT AREA STARTS -->




				<div class="col-lg-12">
					<section class="box ">

						<header class="panel_header extra_head">
							<h2 class="login_head">
								<i class="fa fa-key" aria-hidden="true"></i> PRINCIPAL
								REGISTRATION
							</h2>


						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">


									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertChangePrinciple"
										method="post" name="form_sample_2" id="form_sample_2">

										<input type="hidden" id="inst_id" name="inst_id"
											value="${editInst.instituteId}">

										<div class="row">
											<div class="col-md-12">											
 
												<div class="form-group">
													<label class="control-label col-sm-3" for="page_name">AISHE
														Code <span class="text-danger">*</span>
													</label>
													<div class="col-sm-7">
														<input type="text" maxlength="7" onchange="trim(this)"
															class="form-control" id="aishe_code" autocomplete="off"
															name="aishe_code" value="${aishe}"
															placeholder="All India Survey On Higher Education code">
														<span class="error_form text-danger" id="aishe_code_field"
															style="display: none;">Please enter AISHE code</span>

													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-sm-3" for="page_name">Institute
														Name<span class="text-danger">*</span>
													</label>
													<div class="col-sm-7">
														<input type="text" readonly onchange="trim(this)"
															class="form-control" id="inst_name" autocomplete="off"
															value="${instName}" name="inst_name"
															placeholder="Complete Name of Institute"> <span
															class="error_form text-danger" id="inst_name_field"
															style="display: none;">Please enter institute name</span>

													</div>
												</div>
												



												<div class="form-group">
													<label class="control-label col-sm-3" for="page_order">
														Principal Name <span class="text-danger">*</span>
													</label>
													<div class="col-sm-7">
														<input type="text" maxlength="100" onchange="trim(this)"
															class="form-control" id="princ_name" autocomplete="off"
															value="${editInst.facultyFirstName}" name="princ_name"
															placeholder="Name of Principal"> <span
															class="error_form text-danger" id="princ_name_field"
															style="display: none;">Please enter principal name</span>

													</div>
												</div>

												<div class="form-group">
													<label class="control-label col-sm-3" for="page_order">Mobile
														No <span class="text-danger">*</span>
													</label>
													<div class="col-sm-7">
														<input type="text" maxlength="10" class="form-control"
															id="princ_contact" value="${editInst.contactNo}"
															name="princ_contact" autocomplete="off"
															placeholder="Principal Mobile No"
															onkeypress='return restrictAlphabets(event)'
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
															value="${editInst.email}" autocomplete="off"
															oninput="checkUnique(this.value,2)" name="princ_email"
															placeholder=" Principal Email Id (Official)"> <span
															class="error_form text-danger" id="princ_email_field"
															style="display: none;">Please enter principal
															email id</span>

														<p class="desc font-italic fontsize11">Note:
															Verification mail will be sent on this Email id</p>
													</div>
												</div>
												<input type="hidden" name="instId" id="instId"	value="${editInst.instituteId}">

												 

												<div class="form-group">
													<div class="col-sm-offset-3 col-sm-7">

														<input type="submit" id="sub_button"
															class="btn btn-primary" value="Save" /> <a
															href="${pageContext.request.contextPath}/">
															<button type="button" class="btn btn-info">Cancel</button>
														</a>

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


	<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>

	<script type="text/javascript">
		$('#aishe_code')
				.on(
						'input',
						function() {
							var aishe_code = $('#aishe_code').val();

							document.getElementById("inst_name").value = "";

							if (aishe_code.length == 7) {

								$
										.getJSON(
												'${getInstituteMasterByAisheforPrincipalChange}',
												{
													aishe_code : aishe_code,
													ajax : 'true',

												},
												function(data) {

													document
															.getElementById("inst_name").value = data.instituteName;
													document
															.getElementById("instId").value = data.instituteId;

												});
							}

						});
	</script>
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
														document
																.getElementById("sub_button").disabled = true;
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
		jQuery('.numbersOnly').keyup(function() {
			this.value = this.value.replace(/[^0-9\.]/g, '');
		});
		jQuery('.alphaonly').keyup(function() {
			this.value = this.value.replace(/[^a-zA-Z\s]+$/, '');
		});
		jQuery('.alhanumeric').keyup(function() {
			this.value = this.value.replace(/[^a-zA-Z0-9\-\s]+$/, '');
		});
		jQuery('.dob').keyup(function() {
			this.value = this.value.replace(/[^a-zA-Z0-9\-\s]+$/, '');
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
	</script>
	<script>
		function showIsReg() {
			//alert("Hi")
			var x = $
			{
				isEdit
			}
			;
			//model.addObject("isEdit", 1);
			if (parseInt(x) == 0) {
				//var da = ${editInst.regDate};

				var isReg = $
				{
					editInst.isRegistration
				}
				;

				//alert("Is Reg " +isReg);
				if (isReg == 0) {

					document.getElementById("abc").style.display = "none";
					//document.getElementById("reg_date").removeAttribute(
					//	"required");
					//document.getElementById("reg_date").value = da;

				} else {
					document.getElementById("abc").style.display = "block";
					//reg_date
					//document.getElementById("reg_date").value = da;

				}

			} else {

				var isReg = $
				{
					editInst.isRegistration
				}
				;
				//alert("Is Reg " +isReg);
				if (isReg == 0) {

					document.getElementById("abc").style.display = "none";
					//document.getElementById("reg_date").removeAttribute(
					//	"required");
					//document.getElementById("reg_date").value = da;

				} else {
					document.getElementById("abc").style.display = "block";
					//reg_date
					//document.getElementById("reg_date").value = da;

				}
			}
			//alert("Hi");
		}
	</script>


	<!-- END CONTAINER -->
	<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->
	<script type="text/javascript">
		/* $(function() {

			$('.datepicker').datepicker({
				autoclose : true,
				format : "dd-mm-yyyy",
				changeYear : true,
				changeMonth : true
			});
		}); */

		function checkUnique(inputValue, valueType) {
			var primaryKey = 0;
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