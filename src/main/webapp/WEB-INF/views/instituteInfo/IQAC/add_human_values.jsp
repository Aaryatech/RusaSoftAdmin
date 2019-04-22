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
<body class=" " onload="hideText()">
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
								<%-- <a href="${pageContext.request.contextPath}/showHumanValues"><button
										type="button" class="btn btn-info">Back</button></a> --%>

							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertHumanValues"
										method="post" name="formidhere" id="formidhere">


										<div class="form-group">
											<label class="control-label col-sm-2" for="finantialYear">Title Of
											Programme<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control" placeholder="Title Of Programme"
													autocomplete="off" id="ttlProgm" name="ttlProgm"
													value="${editValue.activityName}"> <span
													class="error_form text-danger" id="error_ttlProgm"
													style="display: none;">Please enter title of programme</span>

											</div>
											
											<%-- <div class="col-sm-6">
												<select id="title12" name="title" class="form-control">
													<option value="-1">Select</option>
													<c:forEach items="${distlist}" var="dist">

														<c:choose>
															<c:when test="${editValue.activityName==dist.valMastId}">
																<option value="${dist.valMastId}" selected>${dist.valText}</option>
															</c:when>
															<c:otherwise>
																<option value="${dist.valMastId}">${dist.valText}</option>
															</c:otherwise>
														</c:choose>

													</c:forEach>

												</select> <span class="error_form text-danger" id="error_year"
													style="display: none;">Please Select </span>
											</div> --%>
										</div>

										<div class="form-group">

											<label class="control-label col-sm-2" for="fromDate">From
												Date <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control datepicker" placeholder="dd/mm/yyyy"
													autocomplete="off" id="fromDate" name="fromDate"
													value="${editValue.activityFromdt}"> <span
													class="error_form text-danger" id="error_fromDate"
													style="display: none;">Please enter from date</span>
													
												<span
													class="error_form text-danger" id="error_fromToDate"
													style="display: none;">From Date must be smaller than To Date </span>

											</div>
										</div>

										<div class="form-group">

											<label class="control-label col-sm-2" for="toDate">To
												Date <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control datepicker" placeholder="dd/mm/yyyy"
													autocomplete="off" id="toDate" name="toDate"
													value="${editValue.activityTodt}"> <span
													class="error_form text-danger" id="error_toDate"
													style="display: none;">Please enter to date</span>
													
												<span
													class="error_form text-danger" id="error_toToDate"
													style="display: none;">To Date must be greater than From Date </span>
											</div>
										</div>



										<div class="form-group">
											<label class="control-label col-sm-2" for="participant">No.
												of Participants<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="number" class="form-control" id="participant"
													autocomplete="off" min="0" name="participant"
													placeholder="No. of Participants" onchange="trim(this)"
													onkeypress='return restrictAlphabets(event)'
													value="${editValue.activityPcount}"> <span
													class="error_form text-danger" id="error_participant"
													style="display: none;">Please enter No of
													Participants</span>
											</div>
										</div>
										<input type="hidden" id="valueId" name="valueId"
											value="${editValue.valueId}"> <input type="hidden"
											id="is_view" name="is_view" value="0">
										<div class="form-group">
											<div class="col-sm-offset-3 col-sm-9">


												<button type="submit" id="sub_button"
													class="btn btn-primary" onclick="submit_f(1)">
													<i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save
												</button>

												<a href="${pageContext.request.contextPath}/showHumanValues"><button id="sub_button_cancel"
														type="button" class="btn btn-primary">
														<i class="${sessionScope.cancelIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Cancel
													</button></a>
											</div>
										</div>


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
		$(function() {
			$('#submitForm').submit(
					function() {
						$("input[type='submit']", this).val("Please Wait...")
								.attr('disabled', 'disabled');

						return true;
					});
		});

		function trim(el) {
			el.value = el.value.replace(/(^\s*)|(\s*$)/gi, ""). // removes leading and trailing spaces
			replace(/[ ]{2,}/gi, " "). // replaces multiple spaces with one space 
			replace(/\n +/, "\n"); // Removes spaces after newlines
			return;
		}
	</script>

	<script type="text/javascript">
		function submit_f(view) {
			//alert(view);
			document.getElementById("is_view").value = view;

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

	<script>
		function numbersOnlyNotZero(value) {

			var mob = /^[1-9][0-9]+$/;

			if (mob.test($.trim(value)) == false) {

				//alert("Please enter a valid email address .");
				return false;

			}
			return true;
		}
	</script>



	<script>
		$(document)
				.ready(
						function($) {

							$("#formidhere")
									.submit(
											function(e) {
												var isError = false;
												var errMsg = "";

												if (!$("#ttlProgm").val()) {

													isError = true;
													errMsg += '<li>Please enter a Subject name.</li>';

													$("#ttlProgm").addClass(
															"has-error")
													$("#error_ttlProgm").show();

												} else {
													$("#error_ttlProgm").hide();
												}

												if (!$("#fromDate").val()) {

													isError = true;
													errMsg += '<li>Please enter a Subject name.</li>';

													$("#fromDate").addClass(
															"has-error")
													$("#error_fromDate").show();

												} else {
													$("#error_fromDate").hide();
												}

												if (!$("#toDate").val()) {

													isError = true;
													errMsg += '<li>Please enter a Subject name.</li>';

													$("#toDate").addClass(
															"has-error")
													$("#error_toDate").show();

												} else {
													$("#error_toDate").hide();
												}
												
												if (!$("#participant").val()) {

													isError = true;
													errMsg += '<li>Please enter a Subject name.</li>';

													$("#participant").addClass(
															"has-error")
													$("#error_participant").show();

												} else {
													$("#error_participant").hide();
												}
												
												var from_date = document.getElementById("fromDate").value;
						         				var to_date = document.getElementById("toDate").value;
						         				var x=0;
						         				
						         				
						         		        var fromdate = from_date.split('-');
						         		        from_date = new Date();
						         		        from_date.setFullYear(fromdate[2],fromdate[1]-1,fromdate[0]);
						         		        var todate = to_date.split('-');
						         		        to_date = new Date();
						         		        to_date.setFullYear(todate[2],todate[1]-1,todate[0]);
						         		        if (from_date > to_date ) 
						         		        {
						         		           /// alert("Invalid Date Range!\nStart Date cannot be after End Date!")
													$("#error_fromToDate").show();
						    					 	$("#error_toToDate").show();
						    					 	$("#error_fromDate").hide();
						    					 	$("#error_toDate").hide();
						         		            return false;
						         		           
						         		        }else {
						         					$("#error_fromToDate").hide();
						         					$("#error_toToDate").hide();
						         				}

												if (!isError) {

													var x = confirm("Do you really want to submit the form?");
													if (x == true) {
														document
																.getElementById("sub_button").disabled = true;
														document
																.getElementById("sub_button_cancel").disabled = true;
														return true;

													}

												}
												return false;
											});
						});
	</script>





</body>
</html>