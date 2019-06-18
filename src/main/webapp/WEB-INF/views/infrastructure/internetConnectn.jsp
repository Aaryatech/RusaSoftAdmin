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
										action="${pageContext.request.contextPath}/inserteInternetConnecInfo"
										method="post" name="form_sample_2" id="form_sample_2">

										<%-- <ul class="nav nav-tabs">
											<li class="active"><a href="#home" data-toggle="tab">
													<i class="fa fa-home"></i> ${title}
											</a></li>

										</ul>

										<div class="tab-content">
											<div class="tab-pane fade in active" id="home"> --%>
										<input type="hidden" id="internetId" name="internetId"
												value="${interConnec.instInternetInfoId}">
	
										<div class="form-group">

											<label class="control-label col-sm-2" for="activityName">Computers with
												  Internet Access<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control" id="internet_access"
													name="internet_access" placeholder="No. of Computers with Internet Access" onkeypress='return restrictAlphabets(event)'
													onchange="trim(this)" autocomplete="off" onFocus="clearDefault(this)"
													value="${interConnec.noOfCompWithInternetAccess}"> <span
													class="error_form text-danger" id="error_formfield1"
													style="display: none;">Please enter No. of computers with internet access and 
													value must be greater than 0.</span>
											</div>
										</div>
										

										
										
										<div class="form-group">
											<label class="control-label col-sm-2"
												for="inst_activity_participation">Leased Line
											 Bandwidth <span class="text-danger">*</span>
											</label>

											<div class="col-sm-6">
												<input type="text" class="form-control"  id="bandwidth"
													 autocomplete="off"	onchange="trim(this)" name="bandwidth"
													placeholder="Leased Line Bandwidth"
													value="${interConnec.leasedLineBandwidth}"> <span
													class="error_form text-danger" id="error_formfield2"
													style="display: none;">Please enter leased line bandwidth.</span>
											</div>
										</div>
										<div class="form-group">
											<label class="control-label col-sm-2" for="inst_activity_participation">LAN Configuration  
											Speed<span class="text-danger">*</span>
											</label>

											<div class="col-sm-6">
												<input type="text" class="form-control"  id="lan_conf"
													 autocomplete="off"	onchange="trim(this)" name="lan_conf"
													placeholder="LAN Configuration Speed" 
													value="${interConnec.lanConfSpeed}"> <span
													class="error_form text-danger" id="error_formfield3"
													style="display: none;">Please enter lan configuration speed.</span>
											</div>
										</div>
										
									<div class="form-group">
											<label class="control-label col-sm-2" for="inst_activity_participation">ISP  
											Name<span class="text-danger">*</span>
											</label>

											<div class="col-sm-6">
												<input type="text" class="form-control"  id="isp_name" maxlength="150"
													 autocomplete="off"	onchange="trim(this)" name="isp_name"
													placeholder="ISP (Internet Service Provider) Name" 
													value="${interConnec.ispName}"> <span
													class="error_form text-danger" id="error_formfield4"
													style="display: none;">Please enter ISP name.</span>
											</div>
										</div>
										
										<div class="form-group">

											<label class="control-label col-sm-2" for="activityName">Date
												 of Purchase<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control datepicker" id="purchase_date"
													name="purchase_date" placeholder="dd-mm-yyyy" data-format="dd-mm-yyyy"
													data-end-date="0d" onkeypress='return restrictAlphabets(event)'
													onchange="trim(this)" autocomplete="off"
													value="${interConnec.purchaseDate}"> <span
													class="error_form text-danger" id="error_formfield5"
													style="display: none;">Please enter date of purchase.</span>
											</div>
										</div>
										
										<div class="form-group">
											<div class="col-sm-offset-2 col-sm-10">
												<button type="submit" id="sub1"
													class="btn btn-primary" onclick="submit_f(1)">
													<i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save
												</button>

												<a
													href="${pageContext.request.contextPath}/showItInfrastructure"><button
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
		function trim(el) {
			el.value = el.value.replace(/(^\s*)|(\s*$)/gi, ""). // removes leading and trailing spaces
			replace(/[ ]{2,}/gi, " "). // replaces multiple spaces with one space 
			replace(/\n +/, "\n"); // Removes spaces after newlines
			return;
		}
		
		/* $(document).ready(function(){
			
			  $('#internet_access').bind("cut copy paste",function(e) {
			
			      e.preventDefault();
			
			  });
			
			}); */

		$(document)
				.ready(
						function($) {

							$("#form_sample_2")
									.submit(
											function(e) {
												//	alert("hi");
												var isError = false;
												var errMsg = "";   				
														 				  
												if ($("#internet_access").val() <= 0 || !$("#internet_access").val()) {

													isError = true;
													
													$("#internet_access")
															.addClass(
																	"has-error")
													$("#error_formfield1")
															.show()
													//return false;
												} else {
													$("#error_formfield1")
															.hide()
												}

												if (!$("#bandwidth").val()) {

													isError = true;
													
													$("#bandwidth").addClass(
															"has-error")
													$("#error_formfield2")
															.show()
													//return false;
												} else {
													$("#error_formfield2")
															.hide()
												}
												
												if (!$("#lan_conf").val()) {

													isError = true;
													
													$("#lan_conf").addClass(
															"has-error")
													$("#error_formfield3")
															.show()
													//return false;
												} else {
													$("#error_formfield3")
															.hide()
												}
												
												if (!$("#isp_name").val()) {

													isError = true;
													

													$("#isp_name").addClass(
															"has-error")
													$("#error_formfield4")
															.show()
													//return false;
												} else {
													$("#error_formfield4")
															.hide()
												}
												
												if (!$("#purchase_date").val()) {

													isError = true;
													

													$("#purchase_date").addClass(
															"has-error")
													$("#error_formfield5")
															.show()
													//return false;
												} else {
													$("#error_formfield5")
															.hide()
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
	</script>  -->

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
	/showActivityOrganized

	<script type="text/javascript">
		/* $(function() {

			$('.datepicker').datepicker({
				autoclose : true,
				format : "dd-mm-yyyy",
				changeYear : true,
				changeMonth : true

			});
		}); */

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
	function clearDefault(a){
		if(a.defaultValue==0)
		{
			a.value=""
		}
		};
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