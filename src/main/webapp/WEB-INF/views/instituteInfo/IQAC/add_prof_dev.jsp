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

							<div class="actions panel_actions pull-right">
							<c:if test="${trainnig_type==1}">
								<a href="${pageContext.request.contextPath}/showProfDevelopment"
									data-toggle="modal"><button type="submit"
										class="btn btn-info">Back</button></a></c:if>
										<c:if test="${trainnig_type==2}">
								<a href="${pageContext.request.contextPath}/showAdminDevelopment"
									data-toggle="modal"><button type="submit"
										class="btn btn-info">Back</button></a></c:if>
										
										
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertTeachTraing"
										method="post" name="form_sample_2" id="form_sample_2"
										onsubmit="return checkBeforeSubmit()">

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
												Title<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" maxlength="200" class="form-control" id="dev_Prog_title"
													name="dev_Prog_title" autocomplete="off"
													onchange="trim(this)"
													placeholder="Title of Professional Development Program for Teaching Staff"
													value="${trainning.trainingTitle}" required>
											</div>
										</div>

										<div class="form-group">
											<label class="control-label col-sm-2" for="no_of_participant">No.
												of Participants <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="number" maxlength="5" min="1" max="99999" class="form-control"
													id="no_of_participant" autocomplete="off"
													name="no_of_participant"
													placeholder="Enter No. of Participants"
													value="${trainning.trainingPcount}" required>
											</div>
										</div>
										<div class="form-group">
											<label class="control-label col-sm-2" for="fromDate">From
												Date <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control datepicker"
													placeholder="Select From Date" autocomplete="off"
													id="fromDate" name="fromDate" value="${trainning.trainingFromdt}"
													required>
											</div>
										</div>
										<div class="form-group">

											<label class="control-label col-sm-2" for="toDate">To
												Date <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control datepicker"
													autocomplete="off" id="toDate" name="toDate"
													placeholder="Select To Date" value="${trainning.trainingTodt}"
													required>
											</div>
										</div>

										<div class="form-group">
											<div class="col-sm-offset-2 col-sm-10">
												<input type="submit" id="sub1" class="btn btn-primary"
													onclick="submit_f(1)" value="Save"> <input
													type="submit" id="sub2" class="btn btn-primary" onclick="submit_f(0)"
													value="Save & Next">
												<button type="reset" class="btn btn-default">Reset</button>
												<input type="hidden" name="is_view" id="is_view" value="0">
												<input type="hidden" id="trainnig_type" name="trainnig_type"
													value="${trainnig_type}"> 
													<input type="hidden"
													id="training_id" name="training_id"
													value="${trainning.trainingId}">

											</div>

											<div class="clearfix"></div>
										</div>
									</form>
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
    function checkBeforeSubmit(){
      if(!wasSubmitted) {
    	  var x=confirm("Do you really want to submit the form?");
    	  if(x==true){
        wasSubmitted = true;
    	  document.getElementById("sub1").disabled=true;
    	  document.getElementById("sub2").disabled=true;

        return wasSubmitted;
    	  }
      }
      return false;
    }    
</script>

 <!--  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script> -->
</body>
</html>


<!-- Title of administrative training program organized for non-teaching staff -->