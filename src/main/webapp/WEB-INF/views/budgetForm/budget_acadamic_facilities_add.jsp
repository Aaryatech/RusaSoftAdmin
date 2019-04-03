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
	<c:url value="/getBudgetByFinYearId" var="getBudgetByFinYearId"></c:url>
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

							<div class="actions panel_actions pull-right">
								<a
									href="${pageContext.request.contextPath}/budgetOnAcadamicSupportFacilities"><button
										type="button" class="btn btn-info">Back</button></a>
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertAcademicBudget"
										method="post" name="formidhere" id="formidhere">


										<div class="form-group">

											<label class="control-label col-sm-2" for="fin_year_id">Financial
												Year<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<select id="fin_year_id" name="fin_year_id"
													onchange="setBudget(this.value)" class="form-control">
													<option value="-1">Select</option>

													<c:forEach items="${finYearList}" var="finYear">
														<c:choose>
															<c:when test="${finYear.finYearId==editBudget.finYearId}">
																<option selected value="${finYear.finYearId}">${finYear.finYear}</option>
															</c:when>
															<c:otherwise>
																<option value="${finYear.finYearId}">${finYear.finYear}</option>
															</c:otherwise>
														</c:choose>
													</c:forEach>

												</select> <span class="error_form text-danger" id="error_year"
													style="display: none;">Please Select Financial Year</span>
											</div>
										</div>
										<div class="form-group">

											<label class="control-label col-sm-2"
												for="infra_budget_title">Budget Academic Title <span
												class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control" autocomplete="off"
													id="infra_budget_title" name="infra_budget_title"
													value="${editBudget.academicBudgetTitle}"
													placeholder="Title of Academic Facility Budget"> <span
													class="error_form text-danger"
													id="error_infra_budget_title" style="display: none;">Please
													enter Budget Academic Title </span>
											</div>
										</div>
										<div class="form-group">

											<label class="control-label col-sm-2" for="allocatedAmt">Budget
												Allocated Amount <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="number" class="form-control"
													id="budget_allocated" name="budget_allocated"
													autocomplete="off" min="0"
													placeholder="Budget Allocated Amount in Rupees"
													value="${editBudget.budgetUtilized}"> <span
													class="error_form text-danger" id="error_budget_allocated"
													style="display: none;">Please enter Budget Allocated
													Amount </span>
											</div>
										</div>

										<div class="form-group">

											<label class="control-label col-sm-2" for="utilizedAmt">Budget
												Utilized Amount<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="number" class="form-control"
													id="budget_utilized" name="budget_utilized"
													autocomplete="off" min="0"
													placeholder="Budget Utilized Amount in Rupees"
													value="${editBudget.budgetAllocated}"> <span
													class="error_form text-danger" id="error_budget_utilized"
													style="display: none;">Please enter Budget Utilized
													Amount </span>
											</div>
										</div>
										<input type="hidden" id="academicBudgetId"
											name="academicBudgetId"
											value="${editBudget.academicBudgetId}"> <input
											type="hidden" id="is_view" name="is_view" value="0">
										<div class="form-group">
											<div class="col-sm-offset-2 col-sm-10">
												<input type="submit" class="btn btn-primary"
													onclick="submit_f(1)" value="Save" id="sub_button">
												<input type="submit" id="sub2" class="btn btn-primary"
													onclick="submit_f(0)" value="Save & Next"
													id="sub_button_next">
												<button type="reset" class="btn btn-default">Reset</button>
												<input type="hidden" id="is_view" name="is_view" value="0">
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
		$(function() {

			$('.datepickeryear').datepicker({
				autoclose : true,
				minViewMode : 2,
				format : 'yyyy'

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
 function setBudget(finYearId){
			// alert(finYearId);
			 $.getJSON(
						'${getBudgetByFinYearId}',
						{

							finYearId: finYearId,
							tableId : 2,
							ajax : 'true',

						},
						function(data) {
							//alert("Data " +JSON.stringify(data));
							
							if(data==0){
								
								//alert("zero ");
								document.getElementById("infra_budget_title").value=""
								document.getElementById("budget_allocated").value=""
								document.getElementById("budget_utilized").value=""
								document.getElementById("academicBudgetId").value="0"
								
							}else{
								
								 //  alert("Data Exists ");
								
								    document.getElementById("infra_budget_title").value=data.academicBudgetTitle;
									document.getElementById("budget_allocated").value=data.budgetAllocated;
									document.getElementById("budget_utilized").value=data.budgetUtilized;
									document.getElementById("academicBudgetId").value=data.academicBudgetId;
								
							}
							
						});
			 
		 }
	</script>


	<script>
	   
            	$(document).ready(function($){
            	 
            		$("#formidhere").submit(function(e) {
            			 var isError=false;
            			 var errMsg="";
            			 
            			 if ($("#fin_year_id").val() == -1) {

								isError = true;
								errMsg += '<li>Please Select Finacial Year</li>';
								errMsg_alert += 'Please Select Finacial Year \n';
								$("#error_year").show()
								//return fregister_useralse;
							} else {
								$("#error_year").hide()
							}
            				
           
            				if(!$("#infra_budget_title").val()){
            					 
            				isError=true;
            				errMsg += '<li>Please enter title.</li>';
            				
            				$("#infra_budget_title").addClass("has-error")
            				$("#error_infra_budget_title").show()
            					 
            				} else {
            					$("#error_infra_budget_title").hide()
            				}
            				
            				
            				if(!$("#budget_allocated").val() || !numbersOnlyNotZero($("#budget_allocated").val())){
            					 
            				isError=true;
            				errMsg += '<li>Please enter a valid Budget Allocated.</li>';
            				
            				$("#budget_allocated").addClass("has-error")
            				$("#error_budget_allocated").show()
            					 
            				} else {
            					$("#error_budget_allocated").hide()
            				}
            				
            				 
            				
            				
            				if(!$("#budget_utilized").val() || !numbersOnlyNotZero($("#budget_utilized").val())){
            					 
            				isError=true;
            				errMsg += '<li>Please enter Budget Utilized Amount.</li>';
            				
            				$("#budget_utilized").addClass("has-error")
            				$("#error_budget_utilized").show()
            					 
            				} else {
            					$("#error_budget_utilized").hide()
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
	<script type="text/javascript">
	 
	 function numbersOnlyNotZero(value) {

	        
	        var mob = /^[1-9][0-9]+$/;


	        if (mob.test($.trim(value)) == false) {

	            //alert("Please enter a valid email address .");
	            return false;

	        }
	        return true;
	    }
	</script>



</body>
</html>