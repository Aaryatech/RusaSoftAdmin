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
								<%-- <a
									href="${pageContext.request.contextPath}/showResearchDetailsList"><button
										type="button" class="btn btn-info">Back</button></a> --%>
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertResearchProject"
										method="post" name="formidhere" id="formidhere">



										<div class="form-group">
											<label class="control-label col-sm-2" for="smallheading">Name
												of Project <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control" id="projName"
													name="projName" autocomplete="off"
													value="${editProject.projName}"
													placeholder="Name of Project"> <span
													class="error_form text-danger" id="error_projName"
													style="display: none;">Please enter Name of Project</span>
											</div>
										</div>


										<div class="form-group">
											<label class="control-label col-sm-2" for="smallheading">Year
												of Project Sanction <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">

												<input type="text" class="form-control datepickeryear"
													data-min-view-mode="years" data-start-view="2"
													value="${editProject.projYear}" autocomplete="off"
													placeholder="Year of Project Sanction" name="yearOfPS"
													id="yearOfPS" data-format="yyyy"> <span
													class="error_form text-danger" id="error_yearOfPS"
													style="display: none;">Please enter Year of Project
													Sanction</span>
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
													autocomplete="off" value="${editProject.projSponsor}">
												<span class="error_form text-danger" id="error_spoAuth"
													style="display: none;">Please enter Sponsoring
													Authority</span>
											</div>
										</div>

										<div class="form-group">
											<label class="control-label col-sm-2" for="smallheading">Name
												of Principal Investigator <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control" id="PIName"
													name="PIName" placeholder="Name of Principal Investigator"
													autocomplete="off" value="${editProject.projInvName}"><span
													class="error_form text-danger" id="error_PIName"
													style="display: none;">Please enter Name of
													Principal Investigator Authority</span>

											</div>

										</div>


										<div class="form-group">

											<label class="control-label col-sm-2" for="page_order">Department
												Name <span class="text-danger">*</span>
											</label>


											<div class="col-sm-6">
												<input type="text" class="form-control" id="deptName"
													name="deptName" placeholder="Department Name"
													autocomplete="off" value="${editProject.projInvDept}">
												<span class="error_form text-danger" id="error_deptName"
													style="display: none;">Please enter Name of
													Department</span>

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
													value="${editProject.projGrant}"> <span
													class="error_form text-danger" id="error_grant"
													style="display: none;">Please enter Grant</span>

											</div>
										</div>
										<div class="form-group">

											<label class="control-label col-sm-2" for="page_order">Total
												Amount <span class="text-danger">*</span>
											</label>


											<div class="col-sm-6">
												<input type="number" class="form-control" id="totalAmt"
													name="totalAmt" placeholder="Total Amount" min="0"
													autocomplete="off" value="${editProject.projTotalAmt}">
												<span class="error_form text-danger" id="error_totalAmt"
													style="display: none;">Please enter total Amount </span>
											</div>
										</div>


										<div class="form-group">

											<label class="control-label col-sm-2" for="page_order">
												Amount Received <span class="text-danger">*</span>
											</label>


											<div class="col-sm-6">
												<input type="number" class="form-control" id="amtRec"
													min="0" name="amtRec" placeholder="Amount Received"
													autocomplete="off" value="${editProject.projAmtRec}">
												<span class="error_form text-danger" id="error_amtRec"
													style="display: none;">Please enter Amount Received</span>
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
													autocomplete="off" value="${editProject.projFrdt}">
												<span class="error_form text-danger" id="error_fromDate"
													style="display: none;">Please enter from date </span>
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
													value="${editProject.projTodt}"> <span
													class="error_form text-danger" id="error_toDate"
													style="display: none;">Please enter to Date </span>
											</div>



										</div>

										<div class="form-group">
													<div class="col-sm-offset-3 col-sm-9">


<button type="submit" id="sub_button" class="btn btn-primary"
													onclick="submit_f(1)"><i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save</button>
														
<a href="${pageContext.request.contextPath}/showResearchDetailsList"><button
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
	function numbersOnlyNotZero(value) {

        
        var mob = /^[1-9][0-9]+$/;


        if (mob.test($.trim(value)) == false) {

            //alert("Please enter a valid email address .");
            return false;

        }
        return true;
    }
 
</script>
	<!-- <script>
		function checkDate() {
 
			var fromDate = document.getElementById("fromDate").value;
			var toDate = document.getElementById("toDate").value;
	 
			if (  fromDate > toDate  ) {
				alert("From Date Should be Small");
				document.getElementById("toDate").value="";
 
			} 

			 

		}
	</script> -->
	<script type="text/javascript">
        $(function () {
		 
            $('.datepickeryear').datepicker({
				autoclose: true,
				minViewMode: 2,
		         format: 'yyyy'

			});
        });
    </script>



	<script>
	   
            	$(document).ready(function($){
            	 
            		$("#formidhere").submit(function(e) {
            			 var isError=false;
            			 var errMsg="";
            				
           
            				if(!$("#projName").val()){
            					 
            				isError=true;
            				errMsg += '<li>Please enter Project Name.</li>';
            				
            				$("#projName").addClass("has-error")
            				$("#error_projName").show()
            					 
            				} else {
            					$("#error_projName").hide()
            				}
            				
            				
            				if(!$("#yearOfPS").val()){
            					 
            				isError=true;
            				errMsg += '<li>Please enter Year of Transction.</li>';
            				
            				$("#yearOfPS").addClass("has-error")
            				$("#error_yearOfPS").show()
            					 
            				} else {
            					$("#error_yearOfPS").hide()
            				}
            				
            				
            				if(!$("#spoAuth").val()){
           					 
                				isError=true;
                				errMsg += '<li>Please enter Sponsoring Authority.</li>';
                				
                				$("#spoAuth").addClass("has-error")
                				$("#error_spoAuth").show()
                					 
                				} else {
                					$("#error_spoAuth").hide()
                				}
            				
            				
            				if(!$("#PIName").val()){
              					 
                				isError=true;
                				errMsg += '<li>Please enter Principal Investigator.</li>';
                				
                				$("#PIName").addClass("has-error")
                				$("#error_PIName").show()
                					 
                				} else {
                					$("#error_PIName").hide()
                				}
            				
            				
            				
            				
            				
            				if(!$("#deptName").val()){
              					 
                				isError=true;
                				errMsg += '<li>Please enter Department.</li>';
                				
                				$("#deptName").addClass("has-error")
                				$("#error_deptName").show()
                					 
                				} else {
                					$("#error_deptName").hide()
                				}
            				
            				
            				
            				if(!$("#grant").val()){
              					 
                				isError=true;
                				errMsg += '<li>Please enter Grant</li>';
                				
                				$("#grant").addClass("has-error")
                				$("#error_grant").show()
                					 
                				} else {
                					$("#error_grant").hide()
                				}
            				
            				//error_spoAuth
            				
            				if(!$("#totalAmt").val() || !numbersOnlyNotZero($("#totalAmt").val())){
            					 
            				isError=true;
            				errMsg += '<li>Please enter Total Amount</li>';
            				
            				$("#totalAmt").addClass("has-error")
            				$("#error_totalAmt").show()
            					 
            				} else {
            					$("#error_totalAmt").hide()
            				}
            				
            				
            				if(!$("#amtRec").val() || !numbersOnlyNotZero($("#amtRec").val())){
            					 
            				isError=true;
            				errMsg += '<li>Please enter Amount Received.</li>';
            				
            				$("#amtRec").addClass("has-error")
            				$("#error_amtRec").show()
            					 
            				} else {
            					$("#error_amtRec").hide()
            				}
            				
            				//error_fromDate
            				
            				
            				
            				if(!$("#fromDate").val()){
           					 
                				isError=true;
                				errMsg += '<li>Please enter FromDate.</li>';
                				
                				$("#fromDate").addClass("has-error")
                				$("#error_fromDate").show()
                					 
                				} else {
                					$("#error_fromDate").hide()
                				}
                				
            				
            				if(!$("#toDate").val()){
              					 
                				isError=true;
                				errMsg += '<li>Please enter to Date.</li>';
                				
                				$("#toDate").addClass("has-error")
                				$("#error_toDate").show()
                					 
                				} else {
                					$("#error_toDate").hide()
                				}
                				
            			 
            				if(!isError) {
            					
            					var x = confirm("Do you really want to submit the form?");
								if (x == true) {
									document.getElementById("sub_button").disabled = true;
									document.getElementById("sub_button_next").disabled = true;
									return  true;
								
								}
            					 		  
            					   } 
            					   return false;
            				} );
            	});
		  
        </script>

</body>
</html>