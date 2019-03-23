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
								<a
									href="${pageContext.request.contextPath}/showResearchDetailsList"><button
										type="button" class="btn btn-info">Back</button></a>
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertResearchProject"
										method="post" name="form_sample_2" id="form_sample_2"
										onsubmit="return confirm('Do you really want to submit the form?');">



										<div class="form-group">
											<label class="control-label col-sm-2" for="smallheading">Name
												of Project <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control" id="projName"
													name="projName" autocomplete="off"
													value="${editProject.projName}"
													placeholder="Name of Project" required>
											</div>
										</div>


										<div class="form-group">
											<label class="control-label col-sm-2" for="smallheading">Year
												of Project Sanction <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">

												<input type="text" class="form-control datepickeryear"
													required data-min-view-mode="years" data-start-view="2"
													value="${editProject.projYear}" autocomplete="off"
													placeholder="Year of Project Sanction" name="yearOfPS"
													id="journalYear" data-format="yyyy">
												<%-- <input type="text" class="form-control datepicker"
																	id="yearOfPS" autocomplete="off"
																	value="${editProject.projYear}" name="yearOfPS"
																 value=""
																	required> --%>
											</div>

										</div>
										<input type="hidden" id="is_view" name="is_view" value="0">
										<div class="form-group">


											<label class="control-label col-sm-2" for="page_order">Sponsoring
												Authority <span class="text-danger">*</span>
											</label>


											<div class="col-sm-6">
												<input type="text" class="form-control" id="spoAuth"
													name="spoAuth" placeholder="Sponsoring Authority"
													autocomplete="off" value="${editProject.projSponsor}"
													value="" required>
											</div>
										</div>

										<div class="form-group">
											<label class="control-label col-sm-2" for="smallheading">Name
												of Principal Investigator <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control" id="PIName"
													name="PIName" placeholder="Name of Principal Investigator"
													autocomplete="off" value="${editProject.projInvName}"
													required>
											</div>

										</div>


										<div class="form-group">

											<label class="control-label col-sm-2" for="page_order">Department
												Name <span class="text-danger">*</span>
											</label>


											<div class="col-sm-6">
												<input type="text" class="form-control" id="deptName"
													name="deptName" placeholder="Department Name"
													autocomplete="off" value="${editProject.projInvDept}"
													required>
											</div>



										</div>


										<div class="form-group">
											<label class="control-label col-sm-2" for="smallheading">Name
												of Co-Principal Investigator <span class="text-danger"></span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control" id="coPrincipal"
													name="coPrincipal"
													placeholder="Name of Co-Principal Investigator"
													autocomplete="off" value="${editProject.projInvName2}">
											</div>

										</div>
										<div class="form-group">
											<label class="control-label col-sm-2" for="page_order">Department
												Name <span class="text-danger"></span>
											</label>


											<div class="col-sm-6">
												<input type="text" class="form-control" id="deptCoName"
													name="deptCoName" placeholder="Department Name"
													autocomplete="off" value="${editProject.projInvDept2}">
											</div>



										</div>


										<div class="form-group">
											<label class="control-label col-sm-2" for="smallheading">Grant
												<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control" id="grant"
													name="grant" placeholder="Grant" autocomplete="off"
													value="${editProject.projGrant}" required>
											</div>
										</div>
										<div class="form-group">

											<label class="control-label col-sm-2" for="page_order">Total
												Amount <span class="text-danger">*</span>
											</label>


											<div class="col-sm-6">
												<input type="number" class="form-control" id="totalAmt"
													name="totalAmt" placeholder="Total Amount" min="0"
													onkeypress="return allowOnlyNumber(event)"
													autocomplete="off" value="${editProject.projTotalAmt}"
													required>
											</div>
										</div>


										<div class="form-group">

											<label class="control-label col-sm-2" for="page_order">
												Amount Received <span class="text-danger">*</span>
											</label>


											<div class="col-sm-6">
												<input type="number" class="form-control" id="amtRec"
													min="0" onkeypress="return allowOnlyNumber(event)"
													name="amtRec" placeholder="Amount Received"
													autocomplete="off" value="${editProject.projAmtRec}"
													required>
											</div>



										</div>

										<input type="hidden" value="${editProject.projId}"
											name="projId" id="projId">


										<div class="form-group">


											<label class="control-label col-sm-2" for="page_order">From
												Date <span class="text-danger">*</span>
											</label>


											<div class="col-sm-6">
												<input type="text" class="form-control datepicker"
													id="fromDate" name="fromDate" placeholder="From Date"
													autocomplete="off" value="${editProject.projFrdt}" required>
											</div>
										</div>
										<div class="form-group">

											<label class="control-label col-sm-2" for="page_order">
												To Date <span class="text-danger">*</span>
											</label>


											<div class="col-sm-6">
												<input type="text" class="form-control datepicker"
													onchange="checkDate()" id="toDate" name="toDate"
													placeholder="To Date" autocomplete="off"
													value="${editProject.projTodt}" required>
											</div>



										</div>

										<div class="form-group">
											<div class="col-sm-offset-2 col-sm-10">
												<input type="submit" class="btn btn-primary"
													onclick="submit_f(1)" value="Save"> <input
													type="submit" class="btn btn-primary" onclick="submit_f(0)"
													value="Save &
																		Next">
												<button type="reset" class="btn btn-default">Reset</button>
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

	<script type="text/javascript">
function submit_f(view){
	//alert(view);
		document.getElementById("is_view").value=view; 
		
	}
 
</script>
	<script>
		function checkDate() {
 
			var fromDate = document.getElementById("fromDate").value;
			var toDate = document.getElementById("toDate").value;
	 
			if (  fromDate > toDate  ) {
				alert("From Date Should be Small");
				document.getElementById("toDate").value="";
 
			} 

			 

		}
	</script>
	<script type="text/javascript">
        $(function () {
		 
            $('.datepickeryear').datepicker({
				autoclose: true,
				minViewMode: 2,
		         format: 'yyyy'

			});
        });
    </script>

</body>
</html>