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

				<div class="col-xs-12">
					<div class="page-title">

						<div class="pull-left">
							<!-- PAGE HEADING TAG - START -->
							<%-- <h1 class="title">${title}</h1> --%>
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
								<%-- <a
									href="${pageContext.request.contextPath}/showPatentDetailsList"><button
										type="button" class="btn btn-info">Back</button></a> --%>

							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertPatentDetail"
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
										<!-- <ul class="nav nav-tabs">
											<li class="active"><a href="#home" data-toggle="tab">
													<i class="fa fa-home"></i> Register
											</a></li>

										</ul> -->

										<!-- <div class="tab-content">
											<div class="tab-pane fade in active" id="home"> -->

										<div>

											<div class="col-sm-12"></div>

											<input type="hidden" id="patent_id" name="patentId"
												value="${patent.patentId}">

											<div class="form-group">
												<label class="control-label col-sm-2" for="patentNo">Patent
													File No. <span class="text-danger">*</span>
												</label>
												<div class="col-sm-6">
													<input type="text" class="form-control" id="patentNo"
														autocomplete="off" name="patentNo"
														value="${patent.patentFileNo}" placeholder="Patent Number"
														onchange="trim(this)"> <span
														class="error_form text-danger" id="error_field1"
														style="display: none;">Please enter patent file No.
													</span>
												</div>
											</div>

											<div class="form-group">
												<label class="control-label col-sm-2" for="parent_title">Patent
													Title <span class="text-danger">*</span>
												</label>
												<div class="col-sm-6">
													<input type="text" class="form-control" id="parent_title"
														autocomplete="off" name="parentTitle"
														placeholder="Patent Title" value="${patent.patentTitle}"
														onchange="trim(this)"> <span
														class="error_form text-danger" id="error_field2"
														style="display: none;">Please enter patent title. </span>
												</div>
											</div>

											<div class="form-group">

												<label class="control-label col-sm-2" for="filling_date">Patent
													Filing Date <span class="text-danger">*</span>
												</label>
												<div class="col-sm-6">
													<input type="text" class="form-control datepicker"
														onchange="trim(this)" autocomplete="off" id="filling_date"
														name="fillingDate" placeholder="Patent Filing Date"
														onkeypress='return restrictAlphabets(event)'
														value="${patent.patentFilingDate}"> <span
														class="error_form text-danger" id="error_field3"
														style="display: none;">Please enter patent filing
														date. </span>
												</div>

											</div>

											<div class="form-group">
												<label class="control-label col-sm-2" for="guide_name">Name
													of Co-Assistant/Guide </label>
												<div class="col-sm-6">
													<input type="text" class="form-control" id="guide_name"
														autocomplete="off" name="guideName" onchange="trim(this)"
														placeholder="Name of Co-Assistant/Guide"
														value="${patent.patentGuideName}">
													<!-- <span
													class="error_form text-danger" id="error_field4"
													style="display: none;">Please enter co-assistant/guide name. </span> -->
												</div>
											</div>
											<input type="hidden" id="is_view" name="is_view" value="0">
											<div class="form-group">

												<label class="control-label col-sm-2" for="pub_date">Date
													of Publication <span class="text-danger">*</span>
												</label>
												<div class="col-sm-6">
													<input type="text" class="form-control datepicker"
														onkeypress='return restrictAlphabets(event)'
														autocomplete="off" placeholder="Date of Publication"
														id="pub_date" name="pubDate" onchange="trim(this)"
														value="${patent.patentPubDate}"> <span
														class="error_form text-danger" id="error_field5"
														style="display: none;">Please enter date of
														publication. </span>
												</div>

											</div>

											<div class="form-group">
												<div class="col-sm-offset-2 col-sm-10">


													<button type="submit" id="sub1" class="btn btn-primary"
														onclick="submit_f(1)">
														<i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save
													</button>

													<a
														href="${pageContext.request.contextPath}/showPatentDetailsList"><button
															id="sub2" type="button" class="btn btn-primary">
															<i class="${sessionScope.cancelIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Cancel
														</button></a>
												</div>
											</div>

											<div class="clearfix"></div>

										</div>

										<!-- </div>
										</div> -->
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
		function trim(el) {
			el.value = el.value.replace(/(^\s*)|(\s*$)/gi, ""). // removes leading and trailing spaces
			replace(/[ ]{2,}/gi, " "). // replaces multiple spaces with one space 
			replace(/\n +/, "\n"); // Removes spaces after newlines
			return;
		}

		$(document).ready(function($) {

			$("#form_sample_2").submit(function(e) {
				var isError = false;
				var errMsg = "";
				if (!$("#patentNo").val()) {
					isError = true;

					$("#patentNo").addClass("has-error")
					$("#error_field1").show();
				} else {
					$("#error_field1").hide();
				}

				if (!$("#parent_title").val()) {
					isError = true;

					$("#parent_title").addClass("has-error")
					$("#error_field2").show();
				} else {
					$("#error_field2").hide();
				}
				if (!$("#filling_date").val()) {
					isError = true;

					$("#filling_date").addClass("has-error")
					$("#error_field3").show();
				} else {
					$("#error_field3").hide();
				}
				/* 	if (!$("#guide_name").val()) {
						isError = true;

						$("#guide_name").addClass(
								"has-error")
						$("#error_field4").show();
					} else {
						$("#error_field4").hide();
					} */

				if (!$("#pub_date").val()) {
					isError = true;

					$("#pub_date").addClass("has-error")
					$("#error_field5").show();
				} else {
					$("#error_field5").hide();
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
		function submit_f(view) {
			//alert(view);
			document.getElementById("is_view").value = view;

		}
	</script>

	<script type="text/javascript">
		var wasSubmitted = false;
		function checkBeforeSubmit() {
			if (!wasSubmitted) {
				var x = confirm("Do you really want to submit the form?");
				if (x == true) {
					wasSubmitted = true;
					document.getElementById("svebtn").disabled = true;
					document.getElementById("svebtnnext").disabled = true;
					return wasSubmitted;
				}
			}
			return false;
		}
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
</body>
</html>