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
								<%-- 	<a
									href="${pageContext.request.contextPath}/showInstituteInfoList"><button
										type="button" class="btn btn-info">Back</button></a>  --%>
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertInstituteInfo"
										method="post" name="form_sample_2" novalidate="novalidate"
										id="form_sample_2">
										<!-- 
										<ul class="nav nav-tabs">
											<li class="active"><a href="#home" data-toggle="tab">
													<i class="fa fa-home"></i> Information
											</a></li>


										</ul>
 -->
										<!-- 	<div class="tab-content">
											<div class="tab-pane fade in active" id="home"> -->

										<div>



											<div class="col-xs-12">


												<div class="form-group">
													<label class="control-label col-sm-4" for="page_name">No.
														of Full Time Faculty <span class="text-danger">*</span>
													</label>
													<div class="col-sm-8">
														<input type="text" class="form-control" min="0"
															id="no_fullTime_Faculty" maxlength="8"															
															onchange="trim(this)"
															value="${editInstInfo.noOfFulltimeFaculty}"
															name="no_fullTime_Faculty"
															placeholder="No. of Full Time Faculty in Institute">

														<span class="error_form text-danger" id="error_full"
															style="display: none;">Please enter No. of full
															time faculty and value must be greater than 0.</span>
													</div>
												</div>
												
												<div class="form-group">
													<label class="control-label col-sm-4" for="page_name">No.
														of Clock Hour Basis Faculty <span class="text-danger"></span>
													</label>
													<div class="col-sm-8">
														<input type="text" class="form-control" min="0"
															id="no_clockhr_Faculty" maxlength="8"															
															onchange="trim(this)"
															value="${editInstInfo.exInt2}"
															name="no_clockhr_Faculty"
															placeholder="No. of of Clock Hour Basis Faculty">

													</div>
												</div>
												
												<div class="form-group">
													<label class="control-label col-sm-4" for="page_name">No.
													of Sanctioned Post <span class="text-danger">*</span>
													</label>
													<div class="col-sm-8">
														<input type="text" class="form-control" min="0"
															id="no_sanction_post"															
															onchange="trim(this)" maxlength="8"
															value="${editInstInfo.exInt1}"
															name="no_sanction_post"
															placeholder="No. of Sanctioned Post">

														<span class="error_form text-danger" id="error_sanc"
															style="display: none;">Please enter No. of post
															sanction and value must be greater than 0.</span>
													</div>
												</div>



												<div class="form-group">
													<label class="control-label col-sm-4" for="page_order">No.
														of Full Time Non-Teaching Faculty <span class="text-danger">*</span>
													</label>
													<div class="col-sm-8">
														<input type="text" class="form-control" min="0"
															id="no_nonTeaching_faculty"
															value="${editInstInfo.noNonteachingIncludingOfficeStaff}"
															name="no_nonTeaching_faculty" maxlength="8"															
															onchange="trim(this)"
															placeholder=" No. of Full Time Non-Teaching Faculty">
														<span class="error_form text-danger" id="error_non"
															style="display: none;">Please enter No. of full
															time non-teaching faculty and value must be greater than 0.</span>
													</div>
												</div>



												<div class="form-group">
													<label class="control-label col-sm-4" for="page_order">No.
														of Support Staff <span class="text-danger">*</span>
													</label>
													<div class="col-sm-8">
														<input type="text" class="form-control" min="0"															
															onchange="trim(this)" id="no_suppStaff"
															value="${editInstInfo.noSupportStaff}" maxlength="8"
															name="no_suppStaff" placeholder="No. of Support Staff">
														<span class="error_form text-danger" id="error_supp"
															style="display: none;">Please enter No. of support
															staff and value must be greater than 0.</span>
													</div>
												</div>

												<div class="form-group">
													<label class="control-label col-sm-4" for="page_order">No.
														of Current Admitted Students <span class="text-danger">*</span>
													</label>
													<div class="col-sm-8">
														<input type="text" class="form-control" min="0"
															id="no_currAdmitted_Student"															
															onchange="trim(this)" maxlength="8"
															value="${editInstInfo.noCurrentAdmitedStnt}"
															name="no_currAdmitted_Student"
															placeholder="No. of Current Admitted Students"> <span
															class="error_form text-danger" id="error_curr"
															style="display: none;">Please enter admitted
															students and value must be greater than 0.</span>
													</div>
												</div>

<div class="form-group">
											<label class="control-label col-sm-4" for="is_add_same">Autonomous Status<span
												class="text-danger">*</span>
											</label>
											<div class="col-sm-8">
												<c:choose>
												<c:when test="${editInstInfo.infoDetailId>0}">
													Yes<input type="radio" ${editInstInfo.autonStatus == 1 ? 'checked' : ''} name="auton_status" id="auton_status" value="1"> 
													No<input type="radio" ${editInstInfo.autonStatus == 0 ? 'checked' : ''} name="auton_status" id="auton_status" value="0">
													
													</c:when>
													
													<c:otherwise>
													Yes <input  type="radio" name="auton_status" id="auton_status" value="1"> 
													No<input type="radio" checked name="auton_status" id="auton_status" value="0">
													</c:otherwise>
													
													</c:choose>
													
												</div>
										</div>



											</div>

										</div>

										<c:choose>
											<c:when test="${editInstInfo.infoDetailId!=0}">
												<input type="hidden" id="inst_info_id" name="inst_info_id"
													value="${editInstInfo.infoDetailId}">
											</c:when>
											<c:otherwise>
												<input type="hidden" id="inst_info_id" name="inst_info_id"
													value="0">
											</c:otherwise>

										</c:choose>





										<input type="hidden" id="is_view" name="is_view" value="0">

										<div class="form-group">
											<div class="col-sm-offset-3 col-sm-9">


												<button type="submit" id="sub1" class="btn btn-primary"
													onclick="submit_f(1)">
													<i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save
												</button>

												<a
													href="${pageContext.request.contextPath}/showInstituteInfoList"><button
														id="sub2" type="button" class="btn btn-primary">
														<i class="${sessionScope.cancelIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Cancel
													</button></a>
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
	<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>

	<!-- END CONTENT -->
	<script>
		function numbersOnlyNotZero(id_number) {

			var mob = /^[1-9][0-9]+$/;

			if (mob.test($.trim(id_number)) == false) {

				//alert("Please enter a valid email address .");
				return false;

			}
			return true;
		}

		$(document)
				.ready(
						function($) {
							//  alert("hii....");
							$("#form_sample_2")
									.submit(
											function(e) {
												var isError = false;
												var errMsg = "";

												if ($("#academic_year").val() == -1) {

													isError = true;

													$("#error_year").show()
													//return fregister_useralse;
												} else {
													$("#error_year").hide()
												}
												
												if ($("#no_sanction_post")
														.val() <= 0
														|| !$(
																"#no_sanction_post")
																.val()) {

													isError = true;
													errMsg += '<li>Please enter a valid name.</li>';

													$("#no_sanction_post")
															.addClass(
																	"has-error")
													$("#error_sanc").show()
													//return false;
												} else {
													$("#error_sanc").hide()
												}
												
												

												if ($("#no_fullTime_Faculty")
														.val() <= 0
														|| !$(
																"#no_fullTime_Faculty")
																.val()) {

													isError = true;
													errMsg += '<li>Please enter a valid name.</li>';

													$("#no_fullTime_Faculty")
															.addClass(
																	"has-error")
													$("#error_full").show()
													//return false;
												} else {
													$("#error_full").hide()
												}

												if ($("#no_nonTeaching_faculty")
														.val() <= 0
														|| !$(
																"#no_nonTeaching_faculty")
																.val()) {

													isError = true;
													errMsg += '<li>Please enter a valid name.</li>';

													$("#no_nonTeaching_faculty")
															.addClass(
																	"has-error")
													$("#error_non").show()
													//return false;
												} else {
													$("#error_non").hide()
												}

												if ($("#no_suppStaff").val() <= 0
														|| !$("#no_suppStaff")
																.val()) {

													isError = true;
													errMsg += '<li>Please enter a valid name.</li>';

													$("#no_suppStaff")
															.addClass(
																	"has-error")
													$("#error_supp").show()
													//return false;
												} else {
													$("#error_supp").hide()
												}

												if ($(
														"#no_currAdmitted_Student")
														.val() <= 0
														|| !$(
																"#no_currAdmitted_Student")
																.val()) {

													isError = true;
													errMsg += '<li>Please enter a valid name.</li>';

													$(
															"#no_currAdmitted_Student")
															.addClass(
																	"has-error")
													$("#error_curr").show()
													//return false;
												} else {
													$("#error_curr").hide()
												}

												if (!isError) {
													var x = confirm("Do you really want to submit the form?");
													if (x == true) {
														return true;
														document
																.getElementById("sub1").disabled = true;
														document
																.getElementById("sub2").disabled = true;
													}
												}

												return false;
											});
						});
		//
	</script>

	<script>
		function clearSessionAttribute() {

			$.getJSON('${clearSessionAttribute}', {

				ajax : 'true',

			}, function(data) {

			});

		}

		function selectedInst(source) {

			checkboxes = document.getElementsByName('instIds');

			for (var i = 0, n = checkboxes.length; i < n; i++) {
				checkboxes[i].checked = source.checked;

			}

		}
		function showEditStudent(studId) {
			document.getElementById("edit_stud_id").value = studId;//create this 
			var form = document.getElementById("libListForm");
			form.setAttribute("method", "post");

			form.action = ("showEditStudent");
			form.submit();

		}

		function disableButton() {

			confirm('Do you really want to submit the form?');
			document.getElementById("submitButton").disabled = true;
		}

		/* 
		onsubmit="return confirm('Do you really want to submit the form?');" */
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
	</script>

	<script type="text/javascript">
			  
		  $('.form-control').on('input', function() {
			  this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');
			});
		
	</script>

	<script type="text/javascript">
		function trim(el) {
			el.value = el.value.replace(/(^\s*)|(\s*$)/gi, ""). // removes leading and trailing spaces
			replace(/[ ]{2,}/gi, " "). // replaces multiple spaces with one space 
			replace(/\n +/, "\n"); // Removes spaces after newlines
			return;
		}
	</script>


	<!-- END CONTAINER -->
	<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->




</body>
</html>