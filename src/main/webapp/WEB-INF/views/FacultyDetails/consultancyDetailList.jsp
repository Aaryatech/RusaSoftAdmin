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

				<%-- <div class="col-xs-12">
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

								<%-- <a href="${pageContext.request.contextPath}/showConsultancyList"><button
										type="button" class="btn btn-info">Back</button></a> --%>



							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/submitFacultyConsultancy"
										method="post" name="form_sample_2" id="form_sample_2">





										<div id="abc">


											<div class="form-group">

												<label class="control-label col-sm-2" for="nature">Nature
													of Consultancy <span class="text-danger">*</span>
												</label>
												<div class="col-sm-6">
													<input type="text" class="form-control" id="nature" autocomplete="off"
														placeholder="Nature of Consultancy" name="nature"
														value="${editConsultancy.consNature}" onchange="trim(this)">
												<span
													class="error_form text-danger" id="error_field1"
													style="display: none;">Please enter nature of consultancy. </span>

												</div>
											</div>

											<div class="form-group">

												<label class="control-label col-sm-2" for="sponser">Sponsoring
													Agency/Industry <span class="text-danger">*</span>
												</label>
												<div class="col-sm-6">
													<input type="text" class="form-control" id="sponser" autocomplete="off"
														placeholder="Sponsoring Agency/Industry" name="sponser"
														value="${editConsultancy.consSponsor}" onchange="trim(this)">
														<span
													class="error_form text-danger" id="error_field2"
													style="display: none;">Please enter sponsoring agency industry.</span>
													<!-- </div> -->
												</div>
											</div>

											<div class="form-group">

												<label class="control-label col-sm-2" for="amount">Amount
													of Consultancy <span class="text-danger">*</span>
												</label>
												<div class="col-sm-6">
													<input type="text" class="form-control" autocomplete="off"
														id="amount" placeholder="Amount of Consultancy" onkeypress='return restrictAlphabets(event)'
														name="amount" value="${editConsultancy.consAmount}" onchange="trim(this)">
											<span
													class="error_form text-danger" id="error_field3"
													style="display: none;">Please enter amount of consultancy and value must be greater than 0. </span>
												</div>
											</div>
											<div class="form-group">

												<label class="control-label col-sm-2" for="conPeriod">Consultancy
													Period<span class="text-danger">*</span>
												</label>
												<div class="col-sm-6">
													<input type="text" class="form-control" autocomplete="off"
														id="conPeriod" placeholder="Consultancy Period"
														name="conPeriod" value="${editConsultancy.consPeriod}" onchange="trim(this)">
												<span
													class="error_form text-danger" id="error_field4"
													style="display: none;">Please enter consultancy period. </span>
												</div>

											</div>

											<div class="form-group">
												<label class="control-label col-sm-2" for="projComp">Project
													Completed<span class="text-danger">*</span>
												</label>

												<div class="col-sm-2">

													<c:choose>
														<c:when test="${editConsultancy.isConsCompleted==0}">
															Yes <input type="radio" name="projComp" id="projComp"
																value="1"> No<input type="radio" name="projComp"
																id="projComp" value="0" checked>
														</c:when>
														<c:otherwise>
															Yes <input type="radio" name="projComp" id="projComp"
																checked value="1"> No<input type="radio"
																name="projComp" id="projComp" value="0">
														</c:otherwise>
													</c:choose>

												</div>
											</div>

											<input type="hidden" id="is_view" name="is_view" value="0">
											<c:choose>
												<c:when test="${editConsultancy.consId>0}">
													<input type="hidden" id="consId" name="consId"
														value="${editConsultancy.consId}">
												</c:when>
												<c:otherwise>
													<input type="hidden" id="consId" name="consId" value="0">
												</c:otherwise>
											</c:choose>
											<div class="form-group">
												<div class="col-sm-offset-2 col-sm-10">


													<button type="submit" id="sub1"
														class="btn btn-primary" onclick="submit_f(1)">
														<i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save
													</button>

													<a
														href="${pageContext.request.contextPath}/showConsultancyList"><button
															type="button" id="sub2" class="btn btn-primary">
															<i class="${sessionScope.cancelIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Cancel
														</button></a>
												</div>
											</div>

											<div class="clearfix"></div>

										</div>



									</form>
								</div>

							</div>
						</div>
					</section>
				</div>
			</section>
		</section>
	</div>



	<!-- END CONTAINER -->
	<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->

	<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>


	<div aria-hidden="true" role="dialog" tabindex="-1" id="myModal"
		class="modal fade" style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button aria-hidden="true" data-dismiss="modal" class="close"
						type="button">×</button>
					<h4 class="modal-title">Consultancy Details</h4>
				</div>
				<div class="modal-body">
					<%-- <form role="form"
						action="${pageContext.request.contextPath}/showModuleForm"
						method="get"> --%>
					<input type="hidden" class="form-control" id="pageId" name="pageId">


					<input type="hidden" class="form-control" id="index" name="index"
						value="0">
					<div class="form-group">
						<label class="control-label col-sm-6" for="page_name">Academic
							Year</label> <select id="academicYear" name="qualType"
							class="form-control" onchange="showForm()" >
							<option value="2018-2019">2018-2019</option>
							<option value="2017-2018">2017-2018</option>
							<option value="2016-2017">2016-2017</option>
							<option value="2016-2017">2015-2016</option>

						</select>
					</div>


					<button type="submit" class="btn btn-primary" onclick="getData()">Submit</button>
					<!-- 	</form> -->
				</div>
			</div>
		</div>
	</div>

<script>

function trim(el) {
	el.value = el.value.replace(/(^\s*)|(\s*$)/gi, ""). // removes leading and trailing spaces
	replace(/[ ]{2,}/gi, " "). // replaces multiple spaces with one space 
	replace(/\n +/, "\n"); // Removes spaces after newlines
	return;
}

		function validateEmail(email) {
			var eml = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
			if (eml.test($.trim(email)) == false) {
				return false;
			}
			return true;
		}
		function validateNo(mobile) {
			var mob = /^[1-9]{1}[0-9]{0,9}$/;
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
												if (!$("#nature").val()) { amount
													isError = true;

													$("#nature").addClass(
															"has-error")
													$("#error_field1").show();
												} else {
													$("#error_field1").hide();
												}
												   
												if (!$("#sponser").val()) {
													isError = true;

													$("#sponser").addClass(
															"has-error")
													$("#error_field2").show();
												} else {
													$("#error_field2").hide();
												}

												if ($("#amount").val()<=0 || !$("#amount").val()) {
													isError = true;

													$("#amount").addClass(
															"has-error")
													$("#error_field3").show();
												} else {
													$("#error_field3").hide();
												}
												if (!$("#conPeriod").val()) {
													isError = true;

													$("#conPeriod").addClass(
															"has-error")
													$("#error_field4").show();
												} else {
													$("#error_field4").hide();
												}
												

												if (!isError) {
													var x = confirm("Do you really want to submit the form?");
													if (x == true) {
														document
																.getElementById("sub1").disabled = true;
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
		function submit_f(view) {
			//alert(view);
			document.getElementById("is_view").value = view;

		}
	</script>
	<script type="text/javascript">
		$(function() {

			$('.datepickeryear').datepicker({
				autoclose : true,
				minViewMode : 2,
				format : 'yyyy'

			});
		});
	</script>
	<script type="text/javascript">
		function getData() {
			//alert("hii");
			var i = parseInt(document.getElementById("index").value);
			var nature = document.getElementById("nature").value
			var sponser = document.getElementById("sponser").value
			//alert(qualName);
			var amount = document.getElementById("amount").value
			var academicYear = document.getElementById("academicYear").value
			var conPeriod = document.getElementById("conPeriod").value
			var consultancy = document.getElementById("consultancy").value
			var temp;
			if (consultancy == 1) {
				temp = "Yes";
			} else {
				temp = "No";
			}
			var dataTable = $('#example-1').DataTable();

			dataTable.row.add(
					[ i + 1, academicYear, nature, sponser, amount, conPeriod,
							temp

					]).draw();

			document.getElementById("index").value = i + 1;

		}
	</script>

	<script type="text/javascript">
		function setConsultancy(value) {

			if (value == 1) {

				document.getElementById("abc").style.display = "none";

			} else {

				document.getElementById("abc").style.display = "block";

			}

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



