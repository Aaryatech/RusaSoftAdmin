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
										action="${pageContext.request.contextPath}/insertActConduct"
										method="post" name="form_sample_2" id="form_sample_2">

										<%-- <ul class="nav nav-tabs">
											<li class="active"><a href="#home" data-toggle="tab">
													<i class="fa fa-home"></i> ${title}
											</a></li>

										</ul>

										<div class="tab-content">
											<div class="tab-pane fade in active" id="home"> --%>

										<div class="form-group">
											<input type="hidden" id="activityId" name="activityId"
												value="${actCndct.actCndctId}"> <label
												class="control-label col-sm-2" for="activityType">Title of 
												Programme/ Activity <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">

												<select id="prg_title" name="prg_title"
													class="form-control" required>
														<option value="Truth" ${actCndct.ttleProgrmAct eq 'Truth' ? 'selected' : '' }>Truth</option>
														<option value="Righteous Conduct" ${actCndct.ttleProgrmAct eq 'Righteous Conduct' ? 'selected' : '' }>Righteous Conduct</option>
														<option value="Love" ${actCndct.ttleProgrmAct eq 'Love' ? 'selected' : '' }>Love</option>
														<option value="Non Violence and Peace" ${actCndct.ttleProgrmAct eq 'Non Violence and Peace' ? 'selected' : '' }>Non Violence and Peace</option>
														<option value="National Values" ${actCndct.ttleProgrmAct eq 'National Values' ? 'selected' : '' }>National Values</option>
														<option value="Human Values" ${actCndct.ttleProgrmAct eq 'Human Values' ? 'selected' : '' }>Human Values</option>
														<option value="National Integration" ${actCndct.ttleProgrmAct eq 'National Integration' ? 'selected' : '' }>National Integration</option>
														<option value="Communal Harmony" ${actCndct.ttleProgrmAct eq 'Communal Harmony' ? 'selected' : '' }>Communal Harmony</option>
														<option value="Social Cohesion" ${actCndct.ttleProgrmAct eq 'Social Cohesion' ? 'selected' : '' }>Social Cohesion</option>
														<option value="Fundamental Duties" ${actCndct.ttleProgrmAct eq 'Fundamental Duties' ? 'selected' : '' }>Fundamental Duties</option>
													</select>
											</div>
										</div>

										
										<div class="form-group">

											<label class="control-label col-sm-2" for="fromDate">From
												Date <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control datepicker"
													onchange="trim(this)" placeholder="From Date"
													autocomplete="off" id="fromDate" name="fromDate"
													value="${actCndct.fromDate}" onFocus="clearDefault(this)"> <span
													class="error_form text-danger" id="error_formfield2"
													style="display: none;">Please enter from date.</span>
													
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
												<input type="text" class="form-control datepicker"
													onchange="trim(this)" placeholder="To Date"
													autocomplete="off" id="toDate" name="toDate"
													value="${actCndct.toDate}" onFocus="clearDefault(this)"> <span
													class="error_form text-danger" id="error_formfield3"
													style="display: none;">Please enter to date.</span> 
													<span
													class="error_form text-danger" id="error_toToDate"
													style="display: none;">To Date must be greater than From Date </span>
											</div>
										</div>



										<div class="form-group">
											<label class="control-label col-sm-2"
												for="inst_activity_participation">No. of
												Participants <span class="text-danger">*</span>
											</label>

											<div class="col-sm-6">
												<input type="text" class="form-control" maxlength="7"
													 id="no_participant" autocomplete="off"
													onchange="trim(this)" name="no_participant"
													placeholder="No.of Participants"
													value="${actCndct.noOfParticipant}" onFocus="clearDefault(this)"> <span
													class="error_form text-danger" id="participant_errfield"
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
													href="${pageContext.request.contextPath}/showActCondctPromotUnivrslValus"><button
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

		$(document)
				.ready(
						function($) {

							$("#form_sample_2")
									.submit(
											function(e) {
												//	alert("hi");
												var isError = false;
												var errMsg = "";

												

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
														"#no_participant")
														.val() <= 0
														|| !$(
																"#no_participant")
																.val()) {

													isError = true;
													errMsg += '<li>Please enter a valid name.</li>';

													$(
															"#no_participant")
															.addClass(
																	"has-error")
													$("#participant_errfield")
															.show()
													//return false;
												} else {
													$("#participant_errfield")
															.hide()
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
						    					 	$("#error_formfield2").hide();
						    					 	$("#error_formfield3").hide();
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
	/showActivityOrganized

	<script type="text/javascript">
		$(function() {

			$('.datepicker').datepicker({
				autoclose : true,
				format : "dd-mm-yyyy",
				changeYear : true,
				changeMonth : true

			});
		});

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
	 $('#fromDate').on('input', function() {
		  this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');
		});
	 
	 $('#toDate').on('input', function() {
		  this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');
		});
	 
	 $('#no_participant').on('input', function() {
		  this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');
		});
	 
	 function clearDefault(a){
			if(a.defaultValue==0)
			{
				a.value=""
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