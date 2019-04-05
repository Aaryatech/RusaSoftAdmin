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
								<%-- <a href="${pageContext.request.contextPath}/showProgDistinctive"><button
										type="button" class="btn btn-info">Back</button></a> --%>

							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertDist"
										method="post" name="formidhere" id="formidhere">

										<div class="form-group">

											<label class="control-label col-sm-2" for="title">Title
												of Distinctiveness <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control" id="title"
													maxlength="200" autocomplete="off" name="title"
													onchange="return trim(this)"
													placeholder="Title of Distinctiveness"
													value="${editDist.distName}"> <span
													class="error_form text-danger" id="error_title"
													style="display: none;">Please enter Title</span>
											</div>
										</div>
										<div class="form-group">
											<label class="control-label col-sm-2" for="academicYear">Applicable
												From <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control datepicker" id="date"
													name="date" autocomplete="off"
													value="${editDist.distApplicableFrom}"> <span
													class="error_form text-danger" id="error_date"
													style="display: none;">Please enter Date</span>
											</div>
										</div>

										<div class="form-group">

											<label class="control-label col-sm-2" for="befStake">Beneficiary
												Stake Holders <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control" id="befStake"
													onchange="return trim(this)" maxlength="200"
													name="befStake" placeholder="Beneficiary Stake Holders"
													autocomplete="off" value="${editDist.distBeneficiary}">
												<span class="error_form text-danger" id="error_befStake"
													style="display: none;">Please enter Beneficiary
													Stake Holders</span>
											</div>
										</div>
										<input type="hidden" id="distId" name="distId"
											value="${editDist.distId}"> <input type="hidden"
											id="is_view" name="is_view" value="0">
										<div class="form-group">
													<div class="col-sm-offset-3 col-sm-9">


<button type="submit" id="sub_button" class="btn btn-primary"
													onclick="submit_f(1)"><i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save</button>
														
<a href="${pageContext.request.contextPath}/showProgDistinctive"><button
										type="button" class="btn btn-primary"><i class="${sessionScope.cancelIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Cancel</button></a>													</div>
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
	function allowOnlyNumber(evt){
		
		var charCode = (evt.which) ? evt.which : event.keyCode
	    if (charCode == 46){
	        var inputValue = $("#floor").val();
	        var count = (inputValue.match(/'.'/g) || []).length;
	        
	        if(count<1){
	            if (inputValue.indexOf('.') < 1){
	                return true;
	            }
	            return false;
	        }else{
	            return false;
	        }
	    }
	    if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)){
	        return false;
	    }
	    return true;
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
												
												if (!$("#title").val()) {

													isError = true;
													errMsg += '<li>Please enter Title.</li>';

													$("#title").addClass(
															"has-error")
													$("#error_title").show()

												} else {
													$("#error_title").hide()
												}

												
												
												
												if (!$("#date").val()) {

													isError = true;
													errMsg += '<li>Please enter Date </li>';

													$("#date").addClass(
															"has-error")
													$("#error_date").show()

												} else {
													$("#error_date").hide()
												}
												
												
												
											 
												
												
												if (!$("#befStake").val()  ) {

													isError = true;
													errMsg += '<li>Please enter No. of guides</li>';

													$("#befStake").addClass(
															"has-error")
													$("#error_befStake")
															.show()

												} else {
													$("#error_befStake")
															.hide()
												}
  
											  
												

												if (!isError) {

													var x = confirm("Do you really want to submit the form?");
													if (x == true) {
														document
																.getElementById("sub_button").disabled = true;
														document
																.getElementById("sub_button_next").disabled = true;
														return true;

													}

												}
												return false;
											});
						});
	</script>







</body>
</html>