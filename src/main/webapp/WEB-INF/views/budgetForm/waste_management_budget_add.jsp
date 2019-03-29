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

<c:url value="/getBudgetDataByFinYearId"  var="getBudgetDataByFinYearId"></c:url>

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
									href="${pageContext.request.contextPath}/budgetOnGreenInitiativesAndWasteMngmnt"><button
										type="button" class="btn btn-info">Back</button></a>
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertWasteMngtBudget"
										method="post" name="form_sample_2" id="form_sample_2"
										onsubmit="return checkBeforeSubmit()">


										<div class="form-group">

											<label class="control-label col-sm-2" for="fin_year_id">Financial
												Year<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<select id="fin_year_id" name="fin_year_id"
													class="form-control" onchange="setBudget(this.value)" required>
													<c:forEach items="${finYearList}" var="finYear">
														<c:choose>
															<c:when test="${finYear.finYearId==budget.finYearId}">
																<option selected value="${finYear.finYearId}">${finYear.finYear}</option>
															</c:when>
															<c:otherwise>
																<option value="${finYear.finYearId}">${finYear.finYear}</option>
															</c:otherwise>
														</c:choose>
													</c:forEach>

												</select>
											</div>
										</div>

										<div class="form-group">

											<label class="control-label col-sm-2" for="allocatedAmt">
												Budget Allocated<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="number"  min="0" max="100000000" maxlength="9" class="form-control"
													onkeypress="return allowOnlyNumber(this)" 
													id="budget_allocated" name="budget_allocated"
													placeholder="Total Budget Allocated Amount in Rupees"
													value="${budget.budgetAllocated}" autocomplete="off"
													required>
											</div>
										</div>

										<div class="form-group">

											<label class="control-label col-sm-2" for="utilizedAmt">
												Budget Utilized<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="number"  min="0" max="100000000"  class="form-control" maxlength="9"
													id="budget_utilized" name="budget_utilized"
													onkeypress="return allowOnlyNumber(this)"
													placeholder="Total Budget Utilized Amount in Rupees"
													value="${budget.budgetUtilized}" autocomplete="off"
													required>
											</div>
										</div>

										<input type="hidden" id="is_view" name="is_view" value="0">
										<div class="form-group">
											<div class="col-sm-offset-2 col-sm-10">
												<input type="submit" id="sub1" class="btn btn-primary"
													onclick="submit_f(1)" value="Save"> <input
													type="submit" id="sub2" class="btn btn-primary"
													onclick="submit_f(0)" value="Save &
																		Next">
												<button type="reset" class="btn btn-default">Reset</button>
												<input type="hidden" id="is_view" name="is_view" value="0">
												<input type="hidden" id="wasteMngtBudgetId"
													name="wasteMngtBudgetId"
													value="${budget.wasteMngtBudgetId}">

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
	        	 if (inputValue.indexOf('.')>0){
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
	 function onlyNumeric(evt){
		 alert(evt);
		 var valid=true;
	  var charCode = (evt.which) ? evt.which : event.keyCode
	  if (charCode > 31 && charCode==46 && (charCode < 48 || charCode > 57)){
		  valid=false;
	  }
	  return valid;
	} 
	 
	 $('.form-control').bind("cut paste",function(e) {
         e.preventDefault();
     });
	 
	 function setBudget(finYearId){
			// alert(finYearId);
			 $.getJSON(
						'${getBudgetDataByFinYearId}',
						{

							finYearId: finYearId,
							tableId : 3,
							ajax : 'true',

						},
						function(data) {
							//alert("Data " +JSON.stringify(data));
							
							if(data==0){
								
								//alert("zero ");
								document.getElementById("budget_allocated").value=""
								document.getElementById("budget_utilized").value=""
								document.getElementById("wasteMngtBudgetId").value="0";
								
							}else{
								
								 //  alert("Data Exists ");
								
									document.getElementById("budget_allocated").value=data.budgetAllocated;
									document.getElementById("budget_utilized").value=data.budgetUtilized;
									document.getElementById("wasteMngtBudgetId").value=data.wasteMngtBudgetId;
								
							}
							
						});
			 
		 }
	
	</script>



</body>
</html>