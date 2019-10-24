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
						<%-- 
						<div class="pull-left">
							<!-- PAGE HEADING TAG - START -->
							<h1 class="title">${title}</h1>
							<!-- PAGE HEADING TAG - END -->
						</div> --%>


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
							<c:if test="${trainnig_type==1}">
								<a href="${pageContext.request.contextPath}/showProfDevelopment"
									data-toggle="modal"><button type="submit"
										class="btn btn-info"><i class="${sessionScope.cancelIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Cancel</button></a></c:if>
										<c:if test="${trainnig_type==2}">
								<a href="${pageContext.request.contextPath}/showAdminDevelopment"
									data-toggle="modal"><button type="submit"
										class="btn btn-info"><i class="${sessionScope.cancelIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Cancel</button></a></c:if>
										
										
							</div> --%>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertTeachTraing"
										method="post" name="form_sample_2" id="form_sample_2">

										<%-- <ul class="nav nav-tabs">
											<li class="active"><a href="#home" data-toggle="tab">
													<i class="fa fa-home"></i> ${title}
											</a></li>

										</ul>
 --%>
										<!-- <div class="tab-content">
											<div class="tab-pane fade in active" id="home"> -->

										<div class="form-group">
											<label class="control-label col-sm-2" for="dev_Prog_title">
												Title of Training Program<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" maxlength="200" class="form-control"
													id="dev_Prog_title" name="dev_Prog_title"
													autocomplete="off" onchange="trim(this)"
													placeholder="Title of Training Program"
													value="${trainning.trainingTitle}"> <span
													class="error_form text-danger" id="dev_Prog_title_field"
													style="display: none;">Please enter training program
													title.</span>
											</div>
										</div>
										
										<div class="form-group">
											<label class="control-label col-sm-2" for="dev_Prog_title"> Financial
												 Support/ Sponsors<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" maxlength="200" class="form-control"
													id="fianance_support" name="fianance_support"
													autocomplete="off" onchange="trim(this)"
													placeholder=" Financial Support/ Sponsors"
													value="${trainning.exVar1}"> <span
													class="error_form text-danger" id="finance_errField"
													style="display: none;">Please enter financial support
													/sponsors.</span>
											</div>
										</div>

										<div class="form-group">
											<label class="control-label col-sm-2" for="no_of_participant">No.
												of Participants <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" maxlength="9" class="form-control"
													min="0" id="no_of_participant" autocomplete="off"
													name="no_of_participant" onchange="trim(this)"
													onkeypress='return restrictAlphabets(event)'
													placeholder="Enter No. of Participants" 
													value="${trainning.trainingPcount}"> <span
													class="error_form text-danger" id="no_of_participant_field"
													style="display: none;">Please enter no of
													participants and value must be greater than 0.</span>
											</div>
										</div>
										<div class="form-group">
											<label class="control-label col-sm-2" for="fromDate">From
												Date <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control datepicker"
													placeholder="Select From Date" autocomplete="off"
													onkeypress='return restrictAlphabets(event)' id="fromDate"
													name="fromDate" value="${trainning.trainingFromdt}">
												<span class="error_form text-danger" id="fromDate_field"
													style="display: none;">Please select from date.</span>
													
												<span
													class="error_form text-danger" id="error_fromToDate"
													style="display: none;">From Date must be smaller than To Date. </span>
											</div>
										</div>
										<div class="form-group">

											<label class="control-label col-sm-2" for="toDate">To
												Date <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control datepicker"
													autocomplete="off" id="toDate" name="toDate"
													onkeypress='return restrictAlphabets(event)'
													placeholder="Select To Date"
													value="${trainning.trainingTodt}"><span
													class="error_form text-danger" id="toDate_field"
													style="display: none;">Please select to date</span>
													
													<span
													class="error_form text-danger" id="error_toToDate"
													style="display: none;">To Date must be greater than From Date. </span>
											</div>
										</div>

										<div class="form-group">
											<div class="col-sm-offset-2 col-sm-10">
												<button type="submit" id="sub1" class="btn btn-primary"
													onclick="submit_f(1)">
													<i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save
												</button>


												<c:if test="${trainnig_type==1}">
													<a
														href="${pageContext.request.contextPath}/showProfDevelopment"><button
															type="button" id="sub2" class="btn btn-primary">
															<i class="${sessionScope.cancelIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Cancel
														</button></a>
												</c:if>
												<c:if test="${trainnig_type==2}">
													<a
														href="${pageContext.request.contextPath}/showAdminDevelopment"><button
															type="button" id="sub2" class="btn btn-primary">
															<i class="${sessionScope.cancelIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Cancel
														</button></a>
												</c:if>



												<!--  <input
													type="submit" id="sub2" class="btn btn-primary" onclick="submit_f(0)"
													value="Save & Next"> -->


												<input type="hidden" name="is_view" id="is_view" value="0">
												<input type="hidden" id="trainnig_type" name="trainnig_type"
													value="${trainnig_type}"> <input type="hidden"
													id="training_id" name="training_id"
													value="${trainning.trainingId}">

											</div>

											<div class="clearfix"></div>
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

	<script>
	$('#no_of_participant').on('input', function() {
		  this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');
		});
	
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

												if (!$("#dev_Prog_title").val()) {
													isError = true;

													$("#dev_Prog_title")
															.addClass(
																	"has-error")
													$("#dev_Prog_title_field")
															.show()
												} else {
													$("#dev_Prog_title_field")
															.hide();
												}
												
												if (!$("#fianance_support").val()) {		
													isError = true;

													$("#fianance_support")
															.addClass(
																	"has-error")
													$("#finance_errField")
															.show()
												} else {
													$("#finance_errField")
															.hide();
												}

												if (!$("#fromDate").val()) {
													isError = true;

													$("#fromDate").addClass(
															"has-error")
													$("#fromDate_field").show();
												} else {
													$("#fromDate_field").hide();
												}

												if (!$("#toDate").val()) {
													isError = true;

													$("#toDate").addClass(
															"has-error")
													$("#toDate_field").show();
												} else {
													$("#toDate_field").hide();
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
						         		       if (to_date<from_date) //   if (from_date > to_date ) 
						         		        {
						         		           /// alert("Invalid Date Range!\nStart Date cannot be after End Date!")
													$("#error_fromToDate").show();
						    					 	$("#error_toToDate").show();
						    					 	$("#fromDate_field").hide();
						    					 	$("#toDate_field").hide();
						         		            return false;
						         		           
						         		        }else {
						         					$("#error_fromToDate").hide();
						         					$("#error_toToDate").hide();
						         				}
						         				

												if ($("#no_of_participant")
														.val() <= 0
														|| !$(
																"#no_of_participant")
																.val()) {

													isError = true;
													errMsg += '<li>Please enter a valid name.</li>';

													$("#no_of_participant")
															.addClass(
																	"has-error")
													$(
															"#no_of_participant_field")
															.show()
													//return false;
												} else {
													$(
															"#no_of_participant_field")
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

	<!--  <script>
  $( function() {
    var dateFormat = "dd-mm-yyyy",
      from = $( "#fromDate" )
        .datepicker({
          defaultDate: "+1w",
          changeMonth: true,
          numberOfMonths: 3
        })
        .on( "change", function() {
          to.datepicker( "option", "minDate", getDate( this ) );
        }),
      to = $( "#toDate" ).datepicker({
        defaultDate: "+1w",
        changeMonth: true,
        numberOfMonths: 1
      })
      .on( "change", function() {
        from.datepicker( "option", "maxDate", getDate( this ) );
      });
 
    function getDate( element ) {
      var date;
      try {
        date = $.datepicker.parseDate( dateFormat, element.value );
      } catch( error ) {
        date = null;
      }
 
      return date;
    }
  } );
  </script> -->
	<script type="text/javascript">
		/* $(function() {
			$('#form_sample_2').submit(
					function() {
						$("input[type='submit']", this).val("Please Wait...")
								.attr('disabled', 'disabled');

						return true;
					});
		}); */

		function trim(el) {
			el.value = el.value.replace(/(^\s*)|(\s*$)/gi, ""). // removes leading and trailing spaces
			replace(/[ ]{2,}/gi, " "). // replaces multiple spaces with one space 
			replace(/\n +/, "\n"); // Removes spaces after newlines
			return;
		}

		function submit_f(view) {
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
					document.getElementById("sub1").disabled = true;
					document.getElementById("sub2").disabled = true;

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
	<!--  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script> -->
</body>
</html>


<!-- Title of administrative training program organized for non-teaching staff -->