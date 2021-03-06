<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.UUID"%>
<%@ page import="java.security.MessageDigest"%>
<%@ page import="java.math.BigInteger"%>


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
<body onload="showForm()">

	<c:url value="/getBudgetDataByFinYearId" var="getBudgetDataByFinYearId"></c:url>

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
								<a
									href="${pageContext.request.contextPath}/budgetOnGreenInitiativesAndWasteMngmnt"><button
										type="button" class="btn btn-info">Back</button></a>
							</div> --%>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertWasteMngtBudget"
										method="post" name="form_sample_2" id="form_sample_2">
										
										<%
											UUID uuid = UUID.randomUUID();
											MessageDigest md = MessageDigest.getInstance("MD5");
											byte[] messageDigest = md.digest(String.valueOf(uuid).getBytes());
											BigInteger number = new BigInteger(1, messageDigest);
											String hashtext = number.toString(16);
											session = request.getSession();
											session.setAttribute("generatedKey", hashtext);
										%>
											<input type="hidden" value="<%out.println(hashtext);%>"
													name="token" id="token">

										<div class="form-group">

											<label class="control-label col-sm-2" for="fin_year_id">Financial
												Year<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<select id="fin_year_id" name="fin_year_id"
													class="form-control" onchange="setBudget(this.value)">
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

												</select> <span class="error_form text-danger" id="fin_year_id_field"
													style="display: none;">Please select financial year.</span>
											</div>
										</div>
										
										<div class="form-group">

											<label class="control-label col-sm-2" for="funding_from"> Source of 
												Funding<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<select id="funding_from" name="funding_from"
													class="form-control" onchange="showForm()">
													<option value="Management" ${budget.exVar1 eq 'Management' ? 'selected' : ''}>Management</option>
													<option value="RUSA" ${budget.exVar1 eq 'RUSA' ? 'selected' : '' }>RUSA</option>
													<option value="Any Other Government Agency" ${budget.exVar1 eq 'Any Other Government Agency' ? 'selected' : '' }>Any Other Government Agency</option>
												</select>
											</div>
										</div>
										
											<div class="form-group" id="abc" style="display: none">

											<label class="control-label col-sm-2" for="page_name">Other Source of 
												Funding<span class="text-danger">*</span> </label>
											<div class="col-sm-6">
												<input type="text" class="form-control" id="otherSource"
													autocomplete="off" name="otherSource" onchange="trim(this)"
													placeholder="Name of Other Source Funding" value="${budget.exVar2}"> <span
													class="error_form text-danger" id="error_other"
													style="display: none;">Please enter other  source of funding.</span>
											</div>

										</div>

										<div class="form-group">

											<label class="control-label col-sm-2" for="allocatedAmt">Budget
												Allocated ${budRupees}<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="number" min="0" max="100000000"
													onchange="trim(this)" autocomplete="off" maxlength="9"
													autocomplete="off" class="form-control"
													id="budget_allocated" name="budget_allocated"
													onkeypress='return restrictAlphabets(event)'
													placeholder="Budget Allocated Amount in ${budRupees}"
													value="${budget.budgetAllocated}"> <span
													class="error_form text-danger" id="budget_allocated_field"
													style="display: none;">Please enter allocated budget
													amount.</span>
											</div>
										</div>

										<div class="form-group">

											<label class="control-label col-sm-2" for="utilizedAmt">Budget
												Utilized ${budRupees}<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="number" class="form-control"
													id="budget_utilized" onchange="trim(this)"
													name="budget_utilized" min="0" max="100000000"
													autocomplete="off" maxlength="9"  onFocus="clearDefault(this)"
													onkeypress='return restrictAlphabets(event)'
													placeholder="Budget Utilized Amount in ${budRupees}"
													value="${budget.budgetUtilized}"> <span
													class="error_form text-danger" id="budget_utilized_field"
													style="display: none;">Please enter utilized budget
													amount.</span> <span class="error_form text-danger"
													id="budget_utilized_field2" style="display: none;">Please
													enter budget utilized amount less than or equal to
													allocated amount.</span>
											</div>
										</div>
										
										<div class="form-group">

											<label class="control-label col-sm-2" for="utilizedAmt">Total Expenditure
												for Green Initiatives & Waste Mgnt. ${budRupees}<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="number" class="form-control"
													id="ttl_exp" onchange="trim(this)"  onFocus="clearDefault(this)"
													name="ttl_exp" min="0" max="100000000"
													autocomplete="off" maxlength="9"
													onkeypress='return restrictAlphabets(event)'
													placeholder="Total Expenditure for Green Initiatives & Waste Mgnt. in ${budRupees}"
													value="${budget.exInt1}"> <span
													class="error_form text-danger" id="ttl_exp_field"
													style="display: none;">Please enter total expenditure for green initiative &
													waste management and value must be greater than 0.</span> 
											</div>
										</div>



										<input type="hidden" id="is_view" name="is_view" value="0">
										<div class="form-group">
											<div class="col-sm-offset-2 col-sm-10">
												<button type="submit" id="sub1" class="btn btn-primary"
													onclick="submit_f(1)">
													<i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save
												</button>

												<a
													href="${pageContext.request.contextPath}/budgetOnGreenInitiativesAndWasteMngmnt"><button
														id="sub2" type="button" class="btn btn-primary">
														<i class="${sessionScope.cancelIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Cancel
													</button></a> <input type="hidden" id="is_view" name="is_view" value="0">
											
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

	<script>
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
		
		function validateZeroNo(mobile) {
			var mob = /^[0-9]{1}[0-9]{0,9}$/;
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
												
												var fundSource = $("#funding_from").val();
												if(fundSource == 'Any Other Government Agency'){
													if (!$("#otherSource").val()) {
														isError = true;
	
														$("#otherSource").addClass(
																"has-error")
														$("#error_other").show()
													} else {
														$("#error_other").hide()
													}													
												}

												if (!$("#fin_year_id").val()) {
													isError = true;

													$("#fin_year_id").addClass(
															"has-error")
													$("#fin_year_id_field")
															.show()
												} else {
													$("#fin_year_id_field")
															.hide()
												}
												
												
												
												if (!$("#budget_allocated").val()
														|| !validateZeroNo($(
														"#budget_allocated")
														.val())) {
													isError = true;
													$("#budget_allocated")
															.addClass(
																	"has-error")
		 											$("#budget_allocated_field")
															.show()
												} else {
													$("#budget_allocated_field")
															.hide()
												}

												
												if (!$("#budget_utilized").val() || !validateZeroNo($(
												"#budget_utilized")
												.val())) {
													
													isError = true;
													$("#budget_utilized")
															.addClass(
																	"has-error")
		 											$("#budget_utilized_field")
															.show()
												} else {
													$("#budget_utilized_field")
															.hide()
												}
												
												if (!$("#ttl_exp").val()
														|| !validateZeroNo($(
														"#ttl_exp")
														.val())) {
													
													isError = true;
													$("#ttl_exp")
															.addClass(
																	"has-error")
		 											$("#ttl_exp_field")
															.show()
												} else {
													$("#ttl_exp_field")
															.hide()
												}
												/* if(parseInt($("#budget_utilized").val()) > parseInt($("#budget_allocated").val())){
													isError = true;
													$("#budget_utilized")
													.addClass(
															"has-error")
 											$("#budget_utilized_field2")
													.show()
												}else{
													$("#budget_utilized_field2")
													.hide()
												}
 */
												if (!isError) {
													var x = confirm("Do you really want to submit the form?");
													if (x == true) {
														document.getElementById("sub1").disabled = true;
														document.getElementById("sub2").disabled = true;

														return  true;
													}	
												}
												return false;
											});
						});
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
	function clearDefault(a){
		if(a.defaultValue==0)
		{
			a.value=""
		}
		};
	
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
								document.getElementById("budget_allocated").value="0"
								document.getElementById("budget_utilized").value="0"
								document.getElementById("wasteMngtBudgetId").value="0";
								document.getElementById("ttl_exp").value="0";
								
							}else{
								
								 // alert("Data Exists ");								
									document.getElementById("budget_allocated").value=data.budgetAllocated;
									document.getElementById("budget_utilized").value=data.budgetUtilized;
									document.getElementById("ttl_exp").value=data.exInt1;
									document.getElementById("wasteMngtBudgetId").value=data.wasteMngtBudgetId;
								
							}
							
						});
			 
		 }
	
	</script>


<script type="text/javascript">
		function showForm() {
			
			var selectedValue = document.getElementById("funding_from").value;
			//alert("qualType::"+selectedValue);
		
			if(selectedValue == 'Any Other Government Agency'){
				document.getElementById("abc").style.display = "inline";
			}
			else{
				document.getElementById("abc").style.display = "none";
			}
			
		}
	</script>
</body>
</html>