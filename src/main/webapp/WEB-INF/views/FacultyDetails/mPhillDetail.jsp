<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
								<%-- 	<a href="${pageContext.request.contextPath}/showMphillDetails"><button
										type="button" class="btn btn-info">Back</button></a> --%>
							</div>

						</header>
						<c:if test="${sessionScope.alert!=null}">
							<div class="col-lg-12">
								<div class="alert alert-success alert-dismissible fade in">
									<button type="button" class="close" data-dismiss="alert"
										aria-label="Close">
										<span aria-hidden="true">×</span>
									</button>
									<strong>Success : </strong> ${alert}
								</div>
							</div>
							<%
								session = request.getSession();
									session.removeAttribute("alert");
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
										<div class="tab-pane fade in active" id="home"> -->
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertFacPhdDetail"
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

										<div class="col-md-12"></div>

										<div class="col-xs-12">
											<div class="form-group">
												<label class="control-label col-sm-3" for="isPhdGuide">M.Phil./Ph.D.
													Guide<span class="text-danger">*</span>
												</label>
												<div class="col-sm-6">

													<c:choose>
														<c:when test="${facPhdDetail.isPhdGuide==0}">
															Yes <input type="radio" name="isPhdGuide" id="isPhdGuide"
																value="1"> No<input type="radio" checked
																name="isPhdGuide" id="isPhdGuide" value="0">
														</c:when>

														<c:otherwise>
															Yes <input type="radio" checked name="isPhdGuide"
																id="isPhdGuide" value="1"> No<input type="radio"
																name="isPhdGuide" id="isPhdGuide" value="0">
														</c:otherwise>

													</c:choose>
													<span class="error_form text-danger" id="isPhdGuide_field"
														style="display: none;">Please select yes</span>

												</div>
											</div>

											<div id="form_input">
												<div class="form-group">
													<label class="control-label col-sm-3"
														for="phdRecognitionDt">Date of Recognition<span
														class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="text" class="form-control datepicker"
															data-end-date="0d" data-format="dd-mm-yyyy"
															id="phdRecognitionDt" name="phdRecognitionDt" readonly
															placeholder="Select Date of Recognition"
															value="${facPhdDetail.phdRecognitionDt}"> <span
															class="error_form text-danger"
															id="phdRecognitionDt_field" style="display: none;">Please
															select date of recognition</span>
													</div>
												</div>

												<div class="form-group">
													<label class="control-label col-sm-3" for="phdValidDt">Valid
														up to <span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="text" class="form-control datepicker"
															id="phdValidDt" readonly data-format="dd-mm-yyyy"
															name="phdValidDt" placeholder="Valid up to Date"
															value="${facPhdDetail.phdValidDt}"> <span
															class="error_form text-danger" id="phdValidDt_field"
															style="display: none;">Please select date of
															validity</span>

													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-sm-3" for="ii">No.
														of Students Guided <span class="text-danger">*</span>

													</label> <label class="control-label col-sm-1" for="phdStuPg">PG
														<span class="text-danger">*</span>
													</label>
													<div class="col-sm-2">
														<input type="number" max="99999" min="0"
															class="form-control" id="phdStuPg" name="phdStuPg"
															placeholder="PG" value="${facPhdDetail.phdStuPg}">
														<span class="error_form text-danger" id="phdStuPg_field"
															style="display: none;">Enter no of PG students</span>
													</div>

													<label style="white-space: nowrap;"
														class="control-label col-sm-1" for="phdStuMphill">M.Phil.
														<span class="text-danger">*</span>
													</label>
													<div class="col-sm-2">
														<input type="number" max="99999" min="0"
															class="form-control" id="phdStuMphill"
															name="phdStuMphill" placeholder="M.Phil."
															value="${facPhdDetail.phdStuMphill}"> <span
															class="error_form text-danger" id="phdStuMphill_field"
															style="display: none;">Enter no of M.Phil.
															students</span>
													</div>
													<label class="control-label col-sm-1" for="phdStuPhd">Ph.D.
														<span class="text-danger">*</span>
													</label>
													<div class="col-sm-2">
														<input type="number" max="99999" min="0"
															class="form-control" id="phdStuPhd" name="phdStuPhd"
															placeholder="Ph.D" value="${facPhdDetail.phdStuPhd}">
														<span class="error_form text-danger" id="phdStuPhd_field"
															style="display: none;">Enter number of Ph.D
															students</span>
													</div>
												</div>


												<div class="form-group">

													<label class="control-label col-sm-3" for="isIctUsed">Use
														of ICT<br>
													<span style="font-size: 10px">(Information
															Communication Technology)</span> <span class="text-danger">*</span>
													</label>


													<div class="col-sm-2">
														<c:choose>
															<c:when test="${facPhdDetail.isIctUsed==1}">
															Yes <input type="radio" name="isIctUsed" id="isIctUsed"
																	checked value="1"> No<input type="radio"
																	name="isIctUsed" id="isIctUsed" value="0">
															</c:when>
															<c:otherwise>
																Yes <input type="radio" name="isIctUsed" id="isIctUsed"
																	value="1"> No<input type="radio"
																	name="isIctUsed" id="isIctUsed" checked value="0">

															</c:otherwise>
														</c:choose>
														<span class="error_form text-danger" id="isIctUsed_field"
															style="display: none;">Please select use of ICT</span>

													</div>
												</div>
											</div>

											<div class="form-group">
												<div class="col-sm-offset-3 col-sm-10">
													<button type="submit" id="sub1" class="btn btn-primary"
														onclick="submit_f(1)">
														<i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save
													</button>
													<a
														href="${pageContext.request.contextPath}/showEditFacAcademic"><button
															type="button" id="sub2" class="btn btn-primary">
															<i class="${sessionScope.cancelIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Cancel
														</button></a>
												</div>
												<input type="hidden" id="staff_id" name="staff_id"
													value="${facPhdDetail.facultyId}"> <input
													type="hidden" id="is_view" name="is_view" value="0">
												<input type="hidden" id="temp1" name="temp1"
													value="${temp1}">
											</div>
										</div>
										<div class="clearfix"></div>
									</form>
									<p class="desc text-danger fontsize11">Notice : * Fields
										are mandatory.</p>
								</div>

								<!-- </div>
								</div> -->
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
		$(document).ready(function($) {
			//alert("Hi")
			$("#form_sample_2").submit(function(e) {
				var isError = false;
				var errMsg = "";
				//alert("Hi")

				var radioValue = $("input[name='isPhdGuide']:checked").val();
				//alert(radioValue);
				if (radioValue == 0) {
					$("#sub2").click();
					isError = true;

					$("#isPhdGuide").addClass("has-error")
					$("#isPhdGuide_field").show()
				} else {
					$("#isPhdGuide_field").hide()

				}

				if (!$("#phdRecognitionDt").val()) {
					isError = true;

					$("#phdRecognitionDt").addClass("has-error")
					$("#phdRecognitionDt_field").show()
				} else {
					$("#phdRecognitionDt_field").hide()
				}

				if (!$("#phdValidDt").val()) {
					isError = true;

					$("#phdValidDt").addClass("has-error")
					$("#phdValidDt_field").show()
				} else {
					$("#phdValidDt_field").hide()
				}

				if (!$("#phdStuPg").val()) {
					isError = true;

					$("#phdStuPg").addClass("has-error")
					$("#phdStuPg_field").show()
				} else {
					$("#phdStuPg_field").hide()
				}

				if (!$("#phdStuMphill").val()) {
					isError = true;

					$("#phdStuMphill").addClass("has-error")
					$("#phdStuMphill_field").show()
				} else {
					$("#phdStuMphill_field").hide()
				}

				if (!$("#phdStuPhd").val()) {
					isError = true;

					$("#phdStuPhd").addClass("has-error")
					$("#phdStuPhd_field").show()
				} else {
					$("#phdStuPhd_field").hide()
				}

				if (!$("#isIctUsed").val()) {
					isError = true;

					$("#isIctUsed").addClass("has-error")
					$("#isIctUsed_field").show()
				} else {
					$("#isIctUsed_field").hide()
				}

				if (!isError) {
					var x = confirm("Do you really want to submit the form?");
					if (x == true) {
						document.getElementById("sub1").disabled = true;
						document.getElementById("sub2").disabled = true;

						return true;
					}
				}
				return false;
			});
		});

		$("input[name='isPhdGuide']").change(function() {
			var radioValue = $("input[name='isPhdGuide']:checked").val();
			if (radioValue == 0) {
				document.getElementById("form_input").style.display = "none";
			} else {
				document.getElementById("form_input").style.display = "block";
			}
		});
	</script>

	<%-- <div aria-hidden="true" role="dialog" tabindex="-1" id="myModal"
		class="modal fade" style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button aria-hidden="true" data-dismiss="modal" class="close"
						type="button">×</button>
					<h4 class="modal-title">Academic Details</h4>
				</div>
				<div class="modal-body">
					<form role="form"
						action="${pageContext.request.contextPath}/showModuleForm"
						method="get">
						<input type="hidden" class="form-control" id="pageId"
							name="pageId">

						<div class="form-group">
							<label for="modalname1" class="form-label">Qualification</label>
							<select id="qualType" name="qualType" class="form-control"
								onchange="showForm()" required>
								<option value="0">Diploma</option>
								<option value="1">Bachelors</option>
								<option value="3">Masters</option>
								<option value="4">Doctorates</option>
								<option value="5">Post Doctorates</option>
								<option value="6">M.Phill/Ph.D. Guide</option>

								<option value="7">Any Other</option>



							</select>
						</div>


						<div class="form-group">

							<label class="control-label col-sm-3" for="page_name">Other
								Qualification </label>
							<!-- <div class="col-sm-3"> -->
							<input type="text" class="form-control" id="qualName"
								name="qualName" placeholder="No." value="${page.pageName}"
								required>
							<!-- </div> -->
						</div>

						<div class="form-group">

							<label class="control-label col-sm-1" for="page_name">Class
							</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" id="hodName"
									name="hodName" placeholder="No." value="${page.pageName}"
									required>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="page_name">Year
								of Passing </label>
							<div class="col-sm-3">
								<input type="date" class="form-control" id="curExp"
									name="curExp" value="" required>
							</div>

						</div>





						<button type="submit" id="sub1" class="btn btn-primary">Submit</button>
					</form>
				</div>
			</div>
		</div>
	</div>
 --%>


	<script>
		function showForm() {
			document.getElementById("abc").style = "display:none"
			var index = document.getElementById("qualType").value

			if (index == 6) {

				document.getElementById("abc").style = "visible"

			}

		}
		function showForm1() {
			document.getElementById("abc").style = "display:none"

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
					return wasSubmitted;
				}
			}
			return false;
		}

		function submit_f(view) {
			document.getElementById("is_view").value = view;//create this 

		}
		/* $(function () {
			 
		    $('.datepicker').datepicker({
				autoclose: true,
		        format: "dd-mm-yyyy",
		        changeYear:true,
		        changeMonth:true
			});
		}); */
	</script>




</body>
</html>