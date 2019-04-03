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

						<div class="pull-left"></div>


					</div>
				</div>
				<div class="clearfix"></div>
				<!-- MAIN CONTENT AREA STARTS -->

				<div class="col-lg-12"></div>



				<div class="col-lg-12">
					<section class="box ">

						<header class="panel_header">
							<h2 class="title pull-left">${title}</h2>

							<div class="actions panel_actions pull-right"></div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertValueMaster"
										method="post" name="formidhere" id="formidhere">



										<div class="form-group">
											<label class="control-label col-sm-2" for="valText">Activity
												Name<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control" id="valText"
													name="valText" autocomplete="off"
													onchange="return trim(this)" placeholder="Activity Name"
													value="${editValue.valText}"> <span
													class="error_form text-danger" id="error_valText"
													style="display: none;">Please enter Activity</span>
											</div>
										</div>


										<input type="hidden" id="is_view" name="is_view" value="0">
										<input type="hidden" id="valMastId" name="valMastId"
											value="${editValue.valMastId}">
										<div class="form-group">
											<div class="col-sm-offset-2 col-sm-10">
												<input type="submit" class="btn btn-primary" id="sub_button"
													value="Save">
												<button type="reset" class="btn btn-default">Reset</button>
											</div>

										</div>
									</form>


									<div class="form-group">

										<table class="table table-striped dt-responsive display"
											id="example-1">
											<thead>

												<tr>
													<th width="10%">Sr No</th>
													<th>Values</th>
													<th width="10%">Action</th>

												</tr>


											</thead>
											<tbody>
												<c:forEach items="${distlist}" var="value" varStatus="count">
													<tr>

														<td style="text-align: center">${count.index+1}</td>

														<td style="text-align: left"><c:out
																value="${value.valText}" /></td>

														<td align="center"><a
															href="${pageContext.request.contextPath}/editValuesMaster/${value.valMastId}"
															title="Edit" rel="tooltip" data-color-class="detail"
															data-animate=" animated fadeIn " data-toggle="tooltip"
															data-original-title="Edit"><span
																class="glyphicon glyphicon-edit"></span></a>
															&nbsp;&nbsp;&nbsp;&nbsp;<a
															href="${pageContext.request.contextPath}/deleteValuesMaster/${value.valMastId}"
															onClick="return confirm('Are you sure want to delete this record');"
															rel="tooltip" data-color-class="danger" title="Delete"
															data-animate=" animated fadeIn " data-toggle="tooltip"
															data-original-title="Delete  record"><span
																class="glyphicon glyphicon-remove"></span></a></td>

													</tr>
												</c:forEach>
											</tbody>
										</table>


									</div>
									<p class="desc text-danger fontsize11">Notice : * Fields
										are Mandatory.</p>
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


	<script>
		$(document).ready(function($) {

			$("#formidhere").submit(function(e) {
				var isError = false;
				var errMsg = "";

				if (!$("#valText").val()) {

					isError = true;
					errMsg += '<li>Please enter Title.</li>';

					$("#valText").addClass("has-error")
					$("#error_valText").show()

				} else {
					$("#error_valText").hide()
				}

				if (!isError) {

					var x = confirm("Do you really want to submit the form?");
					if (x == true) {
						document.getElementById("sub_button").disabled = true;

						return true;

					}

				}
				return false;
			});
		});
	</script>




</body>
</html>