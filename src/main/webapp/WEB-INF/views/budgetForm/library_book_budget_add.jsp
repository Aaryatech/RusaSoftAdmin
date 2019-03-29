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
								<a href="${pageContext.request.contextPath}/budgetOnLibrary"><button
										type="button" class="btn btn-info">Back</button></a>
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertLibBookBudget"
										method="post" name="form_sample_2" id="form_sample_2"
										onsubmit="return checkBeforeSubmit()">


										<div class="form-group">

											<label class="control-label col-sm-2" for="fin_year_id">Financial
												Year<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<select id="fin_year_id" name="fin_year_id"
													class="form-control" onchange="setBudget(this.value)"
													required>
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

											<label class="control-label col-sm-2"
												for="infra_budget_title">Expenditures on purchase of
												Books <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="number" class="form-control" min="0"
													id=expenditure_on_book_purchase autocomplete="off"
													name="expenditure_on_book_purchase"
													value="${budget.expenditureOnBookPurchase}"
													onkeypress="return allowOnlyNumber(event)"
													placeholder="Expenditures on purchase of Books" required>
											</div>
										</div>
										<div class="form-group">

											<label class="control-label col-sm-2" for="allocatedAmt">
												Expenditures on purchase of Journals <span
												class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="number" class="form-control" min="0"
													id="expenditure_on_journals_purchase" autocomplete="off"
													name="expenditure_on_journals_purchase"
													onkeypress="return allowOnlyNumber(event)"
													placeholder="Expenditures on purchase of Journals"
													value="${budget.expenditureOnJournalsPurchase}" required>
											</div>
										</div>

										<div class="form-group">

											<label class="control-label col-sm-2" for="utilizedAmt">
												Expenditures on e-Journals<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="number" class="form-control" min="0"
													id="expenditure_on_ejournals_purchase" autocomplete="off"
													name="expenditure_on_ejournals_purchase"
													placeholder="Expenditures on e-Journals"
													onkeypress="return allowOnlyNumber(event)"
													value="${budget.expenditureOnEjournalsPurchase}" required>
											</div>
										</div>


										<div class="form-group">

											<label class="control-label col-sm-2" for="utilizedAmt">
												Expenditures on e-Resources<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="number" class="form-control" min="0"
													id="expenditure_on_eresources_purchase"
													name="expenditure_on_eresources_purchase"
													autocomplete="off"
													onkeypress="return allowOnlyNumber(event)"
													placeholder="Expenditures on e-Resources"
													value="${budget.expenditureOnEresourcesPurchase}" required>
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
												<input type="hidden" id="budget_id" name="budget_id"
													value="${budget.libraryBookBudgetId}">
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
			var x = confirm("
													Do you really want to submit the form?");
			if (x==
													true) {
				wasSubmitted=true;
													document.getElementById("sub1").disabled=true;
													document.getElementById("sub2").disabled=true; return
													wasSubmitted;
			}
		}
		return false;
	}

		function
													trim(el) {
			el.value=el.value.replace(/(^\s*)|(\s*$)/gi,
													""). // removes leading and trailing spaces
													replace(/[ ]{2,}/gi, " "). // replaces multiple spaces with
													one space replace(/\n +/, "\n"); // Removes spaces afternewlinesreturn;
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
							tableId : 3,
							ajax : 'true',

						},
						function(data) {
							//alert("Data " +JSON.stringify(data));
							
							if(data==0){
								
								//alert("zero ");
								document.getElementById("expenditure_on_book_purchase").value=""
									document.getElementById("expenditure_on_journals_purchase").value=""
								document.getElementById("expenditure_on_ejournals_purchase").value=""
								document.getElementById("expenditure_on_eresources_purchase").value=""
								document.getElementById("budget_id").value="0"
								
							}else{
								
								 //  alert("Data Exists ");
								
								    document.getElementById("expenditure_on_book_purchase").value=data.expenditureOnBookPurchase;
								    document.getElementById("expenditure_on_journals_purchase").value=data.expenditureOnJournalsPurchase;
									document.getElementById("expenditure_on_ejournals_purchase").value=data.expenditureOnEjournalsPurchase;
									document.getElementById("expenditure_on_eresources_purchase").value=data.expenditureOnEresourcesPurchase;
									document.getElementById("budget_id").value=data.libBudgetId;
								
							}
							
						});
			 
		 }
	</script>
</body>
</html>