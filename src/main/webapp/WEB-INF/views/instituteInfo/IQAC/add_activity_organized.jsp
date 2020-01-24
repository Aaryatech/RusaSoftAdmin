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

							<%-- <div class="actions panel_actions pull-right">
								<a href="${pageContext.request.contextPath}/showActivityOrganized"><button
										type="button" class="btn btn-info">Back</button></a>
							</div> --%>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertInstituteActivity"
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
										<%-- <ul class="nav nav-tabs">
											<li class="active"><a href="#home" data-toggle="tab">
													<i class="fa fa-home"></i> ${title}
											</a></li>

										</ul>

										<div class="tab-content">
											<div class="tab-pane fade in active" id="home"> --%>

										<div class="form-group">
											<input type="hidden" id="activityId" name="activityId"
												value="${instAct.instActivityId}"> <label
												class="control-label col-sm-2" for="activityType">Type
												of Activity <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">

												<select id="activityType" name="activityType"
													class="form-control" required>
													<c:choose>
														<c:when test="${instAct.instActivityType eq 'Sports'}">
															<option selected value="Sports">Sports</option>
															<option value="Cultural">Cultural</option>
														</c:when>
														<c:when test="${instAct.instActivityType eq 'Cultural'}">
															<option value="Sports">Sports</option>
															<option selected value="Cultural">Cultural</option>
														</c:when>
														<c:otherwise>
															<option value="Sports">Sports</option>
															<option value="Cultural">Cultural</option>
														</c:otherwise>

													</c:choose>

												</select>
											</div>
										</div>

										<div class="form-group">
											<label class="control-label col-sm-2" for="activityLevel">Level
												of Activity <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<select id="activityLevel" name="activityLevel"
													class="form-control" required>

													<c:choose>
														<c:when test="${instAct.instActivityLevel eq 'State'}">
															<option selected value="State">State</option>
															<option value="National">National</option>
															<option value="International">International</option>
 															<option value="University">University</option>
														</c:when>

														<c:when test="${instAct.instActivityLevel eq 'National'}">
															<option value="State">State</option>
															<option selected value="National">National</option>
															<option value="International">International</option>
 															<option value="University">University</option>
														</c:when>

														<c:when
															test="${instAct.instActivityLevel eq 'International'}">
															<option value="State">State</option>
															<option value="National">National</option>
															<option selected value="International">International</option>
 															<option value="University">University</option>
														</c:when>
 
														<c:when
															test="${instAct.instActivityLevel eq 'University'}">
															<option value="State">State</option>
															<option value="National">National</option>
															<option  value="International">International</option>
 															<option selected value="University">University</option>
														</c:when>

														<c:otherwise>
															<option value="State">State</option>
															<option value="National">National</option>
															<option value="International">International</option>
 															<option value="University">University</option>
														</c:otherwise>

													</c:choose>
												</select>
											</div>
										</div>

										<div class="form-group">

											<label class="control-label col-sm-2" for="activityName">Name
												of Activity <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control" id="activityName"
													name="activityName" placeholder="Name of Activity"
													onchange="trim(this)" autocomplete="off"
													value="${instAct.instActivityName}"> <span
													class="error_form text-danger" id="error_formfield1"
													style="display: none;">Please enter name of
													activity.</span>
											</div>
										</div>



										<div class="form-group">

											<label class="control-label col-sm-2" for="fromDate">From
												Date <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control datepicker"
													onchange="trim(this)" placeholder="From Date"
													data-end-date="0d" data-format="dd-mm-yyyy"
													autocomplete="off" id="fromDate" name="fromDate"
													value="${instAct.instActivityFromdt}"> <span
													class="error_form text-danger" id="error_formfield2"
													style="display: none;">Please enter from date.</span> <span
													class="error_form text-danger" id="error_fromToDate"
													style="display: none;">from date must be smaller
													than to date </span>
											</div>
										</div>

										<div class="form-group">

											<label class="control-label col-sm-2" for="toDate">To
												Date <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control datepicker"
													data-format="dd-mm-yyyy" onchange="trim(this)"
													placeholder="To Date" autocomplete="off" id="toDate"
													name="toDate" value="${instAct.instActivityTodt}">
												<span class="error_form text-danger" id="error_formfield3"
													style="display: none;">Please enter to date.</span> <span
													class="error_form text-danger" id="error_toToDate"
													style="display: none;">To Date must be greater than
													From Date </span>
											</div>
										</div>



										<div class="form-group">
											<label class="control-label col-sm-2"
												for="inst_activity_participation">No. of
												Participants <span class="text-danger">*</span>
											</label>

											<div class="col-sm-6">
												<input type="text" class="form-control" maxlength="7"
													min="0" id="inst_activity_participation" autocomplete="off"
													onchange="trim(this)" name="inst_activity_participation"
													placeholder="No.of Participants"
													onFocus="clearDefault(this)"
													value="${instAct.instActivityParticipation}"> <span
													class="error_form text-danger" id="error_formfield4"
													style="display: none;">Please enter No. of
													participants and value must be greater than 0.</span>
											</div>
										</div>

										<div class="form-group">
											<div class="col-sm-offset-2 col-sm-10">
												<button type="submit" id="sub_button"
													class="btn btn-primary" onclick="submit_f(1)">
													<i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save
												</button>

												<a
													href="${pageContext.request.contextPath}/showActivityOrganized"><button
														id="sub2" type="button" class="btn btn-primary">
														<i class="${sessionScope.cancelIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Cancel
													</button></a> <input type="hidden" id="is_view" name="is_view" value="0">
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


				<!-- MAIN CONTENT AREA ENDS -->
			</section>
		</section>
		<!-- END CONTENT -->



	</div>
	<!-- END CONTAINER -->
	<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->


	<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
	<script>
		$('#inst_activity_participation').on(
				'input',
				function() {
					this.value = this.value.replace(/[^0-9]/g, '').replace(
							/(\..*)\./g, '$1');
				});

		$('#toDate').on(
				'input',
				function() {
					this.value = this.value.replace(/[^0-9]/g, '').replace(
							/(\..*)\./g, '$1');
				});

		$('#fromDate').on(
				'input',
				function() {
					this.value = this.value.replace(/[^0-9]/g, '').replace(
							/(\..*)\./g, '$1');
				});

		function trim(el) {
			el.value = el.value.replace(/(^\s*)|(\s*$)/gi, ""). // removes leading and trailing spaces
			replace(/[ ]{2,}/gi, " "). // replaces multiple spaces with one space 
			replace(/\n +/, "\n"); // Removes spaces after newlines
			return;
		}

		$(document)
				.ready(
						function($) {

							$("#form_sample_2")
									.submit(
											function(e) {
												//	alert("hi");
												var isError = false;
												var errMsg = "";

												if (!$("#activityName").val()) {

													isError = true;
													errMsg += '<li>Please enter a valid name.</li>';

													$("#activityName")
															.addClass(
																	"has-error")
													$("#error_formfield1")
															.show()
													//return false;
												} else {
													$("#error_formfield1")
															.hide()
												}

												if (!$("#fromDate").val()) {

													isError = true;
													errMsg += '<li>Please enter a valid name.</li>';

													$("#fromDate").addClass(
															"has-error")
													$("#error_formfield2")
															.show()
													//return false;
												} else {
													$("#error_formfield2")
															.hide()
												}

												if (!$("#toDate").val()) {

													isError = true;
													errMsg += '<li>Please enter a valid name.</li>';

													$("#toDate").addClass(
															"has-error")
													$("#error_formfield3")
															.show()
													//return false;
												} else {
													$("#error_formfield3")
															.hide()
												}

												if ($(
														"#inst_activity_participation")
														.val() <= 0
														|| !$(
																"#inst_activity_participation")
																.val()) {

													isError = true;
													errMsg += '<li>Please enter a valid name.</li>';

													$(
															"#inst_activity_participation")
															.addClass(
																	"has-error")
													$("#error_formfield4")
															.show()
													//return false;
												} else {
													$("#error_formfield4")
															.hide()
												}

												/* if(!$("#").val()){
												 
												isError=true;
												errMsg += '<li>Please enter a valid name.</li>';
												
												$("#").addClass("has-error")
												$("#error_formfield6").show()
													//return false;
												} else {
													$("#error_formfield6").hide()
												} */

												var from_date = document
														.getElementById("fromDate").value;
												var to_date = document
														.getElementById("toDate").value;
												var x = 0;

												var fromdate = from_date
														.split('-');
												from_date = new Date();
												from_date.setFullYear(
														fromdate[2],
														fromdate[1] - 1,
														fromdate[0]);
												var todate = to_date.split('-');
												to_date = new Date();
												to_date.setFullYear(todate[2],
														todate[1] - 1,
														todate[0]);
												if (from_date >to_date) {
													/// alert("Invalid Date Range!\nStart Date cannot be after End Date!")
													$("#error_fromToDate")
															.show();
													$("#error_toToDate").show();
													$("#error_formfield2")
															.hide();
													$("#error_formfield3")
															.hide();
													return false;

												} else {
													$("#error_fromToDate")
															.hide();
													$("#error_toToDate").hide();
												}

												if (!isError) {

													var x = confirm("Do you really want to submit the form?");
													if (x == true) {

														document
																.getElementById("sub_button").disabled = true;
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
		function getData() {
			//alert("hii");
			var i = parseInt(document.getElementById("index").value);
			var activityName = document.getElementById("activityName").value;
			var academicYear = document.getElementById("academicYear").value;

			var totalParticipants = document
					.getElementById("totalParticipants").value;
			var activityLevel = document.getElementById("activityLevel").value;
			var fromDate = document.getElementById("fromDate").value;
			var toDate = document.getElementById("toDate").value;
			var activityType = document.getElementById("activityType").value;
			//alert("noStud"+noStud);
			var dataTable = $('#example-1').DataTable();

			dataTable.row.add(
					[ i + 1, activityName, academicYear, fromDate, toDate,
							totalParticipants, activityLevel, activityType ])
					.draw();
			document.getElementById("index").value = i + 1;
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
		});
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
	</script>


	<script type="text/javascript">
		function clearDefault(a) {
			if (a.defaultValue == 0) {
				a.value = ""
			}
		};
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





</body>
</html>