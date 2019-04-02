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
<body class=" " onload="showIsReg()">
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

				<!-- 	<div class="col-xs-12">
					<div class="page-title">

						<div class="pull-left">
							PAGE HEADING TAG - START
							<h1 class="title">HOD Registration</h1>
							PAGE HEADING TAG - END
						</div>


					</div>
				</div> -->


				<div class="clearfix"></div>
				<!-- MAIN CONTENT AREA STARTS -->

				<div class="col-lg-12">
					<section class="box ">

						<header class="panel_header">
							<h2 class="title pull-left">${title}</h2>

							<div class="actions panel_actions pull-right">

								<a href="${pageContext.request.contextPath}/showAccList"><button
										type="button" class="btn btn-info">Back</button></a>
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertAccOff"
										method="post" name="form_sample_2" id="form_sample_2">

										<div class="form-group">
											<label class="control-label col-sm-3" for="page_name">Name
												<span class="text-danger">*</span>
											</label>
											<div class="col-sm-9">
												<input type="text" class="form-control" id="acc_off_name"
													maxlength="100" value="${accOff.accOfficerName}" onchange="trim(this)"
													name="acc_off_name" placeholder="Account Officer Name"
													>
													<span
															class="error_form text-danger" id="acc_off_name_field"
															style="display: none;">Please enter account officer name</span>
											</div>
										</div>

										<div class="form-group">
											<label class="control-label col-sm-3" for="smallheading">Contact
												No.<span class="text-danger">*</span>
											</label>
											<div class="col-sm-9">
												<input type="text" maxlength="10"  onchange="trim(this)" class="form-control"
													id="acc_off_mob" value="${accOff.contactNo}"
													name="acc_off_mob"
													oninput="checkUnique(this.value,1)"
													placeholder="Account Officer Mobile Number">
													<span
															class="error_form text-danger" id="acc_off_mob_field"
															style="display: none;">Please enter account officer mobile no.</span>
											</div>
										</div>

										<div class="form-group">
											<label class="control-label col-sm-3" for="smallheading">Email
												ID <span class="text-danger">*</span>
											</label>
											<div class="col-sm-9">
												<input type="text" class="form-control"  onchange="trim(this)" id="acc_off_email"
													oninput="checkUnique(this.value,2)" name="acc_off_email"
													placeholder="Official Email Id of Account Officer" value="${accOff.email}">
													<span class="error_form text-danger" id="acc_off_email_field"
															style="display: none;">Please enter account officer email id</span>
											</div>
										</div>
										<div class="form-group">
											<label class="control-label col-sm-3" for="status">
												Qualification <span class="text-danger">*</span>
											</label>
											<div class="col-sm-9">
												<select id="acc_quolf" name="acc_quolf" class="form-control"
													required>
													<c:forEach items="${quolfList}" var="quolf">
														<c:choose>
															<c:when
																test="${accOff.qualificationId==quolf.qualificationId}">
																<option selected value="${quolf.qualificationId}">${quolf.qualificationName}</option>

															</c:when>
															<c:otherwise>

																<option value="${quolf.qualificationId}">${quolf.qualificationName}</option>

															</c:otherwise>

														</c:choose>

													</c:forEach>

												</select>
												<span class="error_form text-danger" id="acc_quolf_field"
															style="display: none;">Please select account officer qualification</span>
											</div>
										</div>

										<div class="form-group">
											<label class="control-label col-sm-3" for="page_order">Joining
												Date <span class="text-danger">*</span>
											</label>
											<div class="col-sm-3">
												<input type="text" class="form-control datepicker"
													id="acc_off_joinDate" value="${accOff.joiningDate}"
													name="acc_off_joinDate" placeholder="Joining Date">
													<span class="error_form text-danger" id="acc_off_joinDate_field"
															style="display: none;">Please select joining date</span>
											</div>
											<label class="control-label col-sm-3" for="planning"
												style="text-align: left;">Is Currently Working<span
												class="text-danger">*</span>
											</label>
											<div class="col-sm-2">
												<c:choose>
													<c:when test="${accOff.officerId==0}">

														<input type="radio" id="is_registration"
															name="is_registration" value="1" checked
															onclick="setDate(this.value)">Yes 
																<input type="radio" id="is_registration"
															name="is_registration" value="0"
															onclick="setDate(this.value)">No 
															
															</c:when>
													<c:otherwise>

														<c:choose>
															<c:when test="${empty accOff.realivingDate}">
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
													<span class="error_form text-danger" id="is_registration_field"
															style="display: none;">Please select account officer work status</span>

											</div>
										</div>
										<div class="form-group">

											<div id="abc" style="display: none">
												<label class="control-label col-sm-3" for="page_order">Relieving
													Date <span class="text-danger">*</span>
												</label>
												<div class="col-sm-3">
													<input type="text" class="form-control datepicker"
														id="acc_off_relDate" value="${accOff.realivingDate}"
														name="acc_off_relDate" placeholder="Relieving Date">
														<span class="error_form text-danger" id="acc_off_relDate_field"
															style="display: none;">Please select relieving date</span>
														
												</div>
											</div>
										</div>


										<div class="form-group">
											<div class="col-sm-offset-3 col-sm-9">
												<input type="submit" id="sub1" class="btn btn-primary"
													onclick="submit_f(1)" value="Save"> <input
													type="submit" id="sub2" class="btn btn-primary"
													onclick="submit_f(0)"
													value="Save &
																		Next">
												<button type="reset" class="btn btn-default">Reset</button>
											</div>
										</div>

										<input type="hidden" id="acc_off_id" name="acc_off_id"
											value="${accOff.officerId}"> <input type="hidden"
											id="is_view" name="is_view" value="0">

										<div class="clearfix"></div>


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



	</div>
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

							$("#form_sample_2")
									.submit(
											function(e) {
												var isError = false;
												var errMsg = "";

												if (!$("#acc_off_name").val()) {
													isError = true;

													$("#acc_off_name").addClass(
															"has-error")
													$("#acc_off_name_field")
															.show()
												} else {
													$("#acc_off_name_field")
															.hide()
												}

												if (!$("#acc_quolf").val()) {
													isError = true;

													$("#acc_quolf").addClass(
															"has-error")
													$("#acc_quolf_field")
															.show()
												} else {
													$("#acc_quolf_field")
															.hide()
												}

												if (!$("#acc_off_joinDate").val()) {
													isError = true;

													$("#acc_off_joinDate").addClass(
															"has-error")
													$("#acc_off_joinDate_field").show()
												} else {
													$("#acc_off_joinDate_field").hide()
												}


												if (!$("#acc_off_mob").val()
														|| !validateMobile($(
																"#acc_off_mob")
																.val())) {
													isError = true;
													$("#acc_off_mob")
															.addClass(
																	"has-error")
													$("#acc_off_mob_field")
															.show()
												} else {
													$("#acc_off_mob_field")
															.hide()
												}

												if (!$("#acc_off_email").val()
														|| !validateEmail($(
																"#acc_off_email")
																.val())) {
													isError = true;
													$("#acc_off_email").addClass(
															"has-error")
													$("#acc_off_email_field")
															.show()
												} else {
													$("#acc_off_email_field")
															.hide()
												}
												
												if (!$("#is_registration").val())
																{
													isError = true;
													$("#is_registration").addClass(
															"has-error")
													$("#is_registration_field")
															.show()
												} else {
													$("#is_registration_field")
															.hide()
												}
												
					var radioValue = $("input[name='is_registration']:checked"). val();
					//alert(radioValue);
					if(radioValue==0){
						if (!$("#acc_off_relDate").val()) {
							isError = true;

							$("#acc_off_relDate").addClass(
									"has-error")
							$("#acc_off_relDate_field").show()
						} else {
							$("#acc_off_relDate_field").hide()
						}
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
		$(function() {

			$('.datepicker').datepicker({
				autoclose : true,
				format : "dd-mm-yyyy",
				changeYear : true,
				changeMonth : true

			});
		});

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
			if (value == 1) {
				document.getElementById("abc").style.display = "none";
			} else {
				document.getElementById("abc").style.display = "block";
			}
		}
	</script>
	<script type="text/javascript">
		function showIsReg() {
			//alert("Hi");
			var x = ${accOff.officerId};

			if (x > 0) {
				//alert("Hi 1")
				var isRel = ${accOff.realivingDate};
				
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
		function checkUnique(inputValue, valueType) {
			//alert(inputValue);
			document.getElementById("sub1").disabled = false;
			document.getElementById("sub2").disabled = false;

			var primaryKey = ${accOff.officerId};
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
								'${checkUniqueField}',
								{

									inputValue : inputValue,
									valueType : valueType,
									primaryKey : primaryKey,
									isEdit : isEdit,
									tableId : 5,

									ajax : 'true',

								},
								function(data) {

									//	alert("Data  " +JSON.stringify(data));
									if (data.error == true) {
										if (valueType == 2) {
											document
													.getElementById("acc_off_email").value = "";

											alert("This email id already exist in system please enter unique");

											document.getElementById("sub1").disabled = true;
											document.getElementById("sub2").disabled = true;

										} else {
											document
													.getElementById("acc_off_mob").value = "";

											alert("This contact no  already exist in system please enter unique");

											document.getElementById("sub1").disabled = true;
											document.getElementById("sub2").disabled = true;

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
					document.getElementById("sub1").disabled = true;
					document.getElementById("sub2").disabled = true;

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