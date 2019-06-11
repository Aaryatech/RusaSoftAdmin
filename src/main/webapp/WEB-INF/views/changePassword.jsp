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
<body class=" " onload="hideText()">
	<!-- START TOPBAR -->
	<%-- 	<jsp:include page="/WEB-INF/views/include/topbar.jsp"></jsp:include> --%>
	<!-- END TOPBAR -->
	<!-- START CONTAINER -->
	<div class="page-container row-fluid container-fluid">

		<!-- SIDEBAR - START -->
		<%-- 
		<jsp:include page="/WEB-INF/views/include/left.jsp"></jsp:include> --%>
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

							<h2 class="title pull-left">Change Password</h2>
							<br />




						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/changePassForm"
										method="post" name="form_sample_2" id="form_sample_2"
										onsubmit="return confirm('Do you really want to Change the Password?');">
<input type="hidden" id="uid" name="uid" value="${userId}">
										<!-- <ul class="nav nav-tabs">
											<li class="active"><a href="#home" data-toggle="tab">
													<i class="fa fa-home"></i> Change Password
											</a></li>

										</ul> -->

										<!-- <div class="tab-content">
											<div class="tab-pane fade in active" id="home"> -->

										<div>
											<div class="col-xs-12">
												<div style="text: align center;">
													<div class="form-group">
														<label class="control-label col-sm-2" for="page_name">
															New Password <span class="text-danger">*</span>
														</label>
														<div class="col-sm-3">
															<input type="password" pattern="(?=^.{8,14}$)(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&amp;*()_+}{&quot;:;'?/&gt;.&lt;,])(?!.*\s).*$"  class="form-control"
																id="newPassword" name="newPassword" maxlength="14"
																placeholder="Enter New Password" onkeyup="trim(this);"
																required>
														</div>
														<!-- <span
id="strength">Type Password: Must contain at least one number(0-9) and one uppercase(A-Z) and lowercase(a-z) letter and one special character, and at least 8 and maximum 14  characters </span> <span
class="validation-invalid-label" id="error_password"
style="display: none;">This field is required.</span>
<input type="hidden" id="allowPass" name="allowPass" value="0"> -->
													</div>
													
													
														<div class="form-group">
														<label class="control-label col-sm-2" for="page_name">
															Password format
														</label>
														<div class="col-sm-3">
															<span
id="strength">Must contain at least <b>one number(0-9)</b> and <b>one upper case(A-Z)</b> and <b> one lower case(a-z)</b> letter, and <b>at least 8 and one special character</b> and <b>maximum 14  characters</b> </span> 
														</div>
													</div>
													

													
													<div class="form-group">
														<label class="control-label col-sm-2" for="page_name">
															Confirm Password <span class="text-danger">*</span>
														</label>
														<div class="col-sm-3">
															<input type="password" class="form-control"
																id="conPassword" name="conPassword" maxlength="14"
																placeholder="Enter Confirm Password"
																onkeyup="trim(this)" required>
														</div>
													</div>
													
													


													<div class="form-group">
														<div class="col-sm-2">
															<input type="checkbox" onclick="viewPassword()">Show
															Password
														</div>
													</div>

													<div class="form-group">
														<div class="col-sm-2">
															<button type="submit" onclick="validatePassword()"
																class="btn btn-primary">Change</button>
															<button type="reset" class="btn btn-default">Reset</button>
														</div>
													</div>

												</div>
											</div>


											<div class="clearfix"></div>

										</div>

										<!-- </div>

										</div> -->
									</form>
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

	<script type="text/javascript">
		 function validatePassword() {
			var pass = document.getElementById("newPassword").value;
			var conpass = document.getElementById("conPassword").value;
			// alert(pass+" "+conpass);

			if (pass != conpass) {

				document.getElementById("conPassword").value = "";
				alert("Confirm password not matched!");
				return false;
			}
		}
 
		function viewPassword() {
			var pass1 = document.getElementById("newPassword");
			var pass2 = document.getElementById("conPassword");

			if (pass1.type == "password" && pass2.type == "password") {
				pass1.type = "text";
				pass2.type = "text";
			} else {
				pass1.type = "password";
				pass2.type = "password";
			}
		}

		function trim(el) {
			el.value = el.value.replace(/(^\s*)|(\s*$)/gi, ""). // removes leading and trailing spaces
			replace(/[ ]{2,}/gi, " "). // replaces multiple spaces with one space 
			replace(/\n +/, "\n"); // Removes spaces after newlines
			return;
		}
		
		
	</script>
	
	<script>
function passwordChanged() {
var strength = document.getElementById("strength");
$("#error_password").hide();
var strongRegex = new RegExp(
"^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])([$&+,:;=?@#|'<>.^*()%!-](?=.*\\W).*$",
"g");
var mediumRegex = new RegExp(
"^(?=.{6,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$",
"g");
var enoughRegex = new RegExp("(?=.{6,}).*", "g");
var pwd = document.getElementById("newPassword").value;

if (pwd.length == 0) {
document.getElementById("strength").innerHTML = "Type Password";
document.getElementById("allowPass").value=0;
} else if (false == enoughRegex.test(pwd)) {
document.getElementById("strength").innerHTML = "More Characters";
document.getElementById("allowPass").value=0;
} else if (strongRegex.test(pwd)) {
document.getElementById("strength").innerHTML = "<span style='color:green'>Strong!</span>";
document.getElementById("allowPass").value=1;
} else if (mediumRegex.test(pwd)) {
document.getElementById("strength").innerHTML = "<span style='color:orange'>Medium!</span>";
document.getElementById("allowPass").value=1;
} else {
document.getElementById("strength").innerHTML = "<span style='color:red'>Weak!</span>";
document.getElementById("allowPass").value=0;
}
}
</script>

</body>
</html>