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
								<%-- 	<a href="${pageContext.request.contextPath}/budgetOnLibraryBooks"><button
										type="button" class="btn btn-info">Back</button></a> --%>
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertLibBookBudget"
										method="post" name="form_sample_2" id="form_sample_2">


										<div class="form-group">

											<label class="control-label col-sm-3" for="fin_year_id">Financial
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
												</select> <span class="error_form text-danger" id="error_year"
													style="display: none;">Please Select Year</span>

											</div>
										</div>


										<div class="form-group">

											<label class="control-label col-sm-3"
												for="infra_budget_title">Expenditures on purchase of
												Books ${budRupees}<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="number" class="form-control" min="0"
													onchange="trim(this)" id="expenditure_on_book_purchase"
													autocomplete="off"
													onkeypress='return restrictAlphabets(event)'
													name="expenditure_on_book_purchase"
													value="${budget.expenditureOnBookPurchase}"
													placeholder="Expenditures on purchase of Books ${budRupees}"> <span
													class="error_form text-danger" id="error_pbook"
													style="display: none;">Please Enter Expenditures on
													purchase of Books.</span>

											</div>
										</div>
										<div class="form-group">

											<label class="control-label col-sm-3" for="allocatedAmt">
												Expenditures on purchase of Journals ${budRupees} <span
												class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="number" class="form-control" min="0"
													onchange="trim(this)" id="expenditure_on_journals_purchase"
													autocomplete="off"
													onkeypress='return restrictAlphabets(event)'
													name="expenditure_on_journals_purchase"
													placeholder="Expenditures on purchase of Journals ${budRupees}"
													value="${budget.expenditureOnJournalsPurchase}"> <span
													class="error_form text-danger" id="error_pjournal"
													style="display: none;">Please Enter Expenditures on
													purchase of Journals.</span>
											</div>
										</div>

										<div class="form-group">

											<label class="control-label col-sm-3" for="utilizedAmt">
												Expenditures on e-Journals ${budRupees}<span
												class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="number" class="form-control" min="0"
													onchange="trim(this)"
													id="expenditure_on_ejournals_purchase" autocomplete="off"
													onkeypress='return restrictAlphabets(event)'
													name="expenditure_on_ejournals_purchase"
													placeholder="Expenditures on e-Journals ${budRupees}"
													value="${budget.expenditureOnEjournalsPurchase}"> <span
													class="error_form text-danger" id="error_ejournal"
													style="display: none;">Please Enter Expenditures on
													e-Journals.</span>

											</div>
										</div>


										<div class="form-group">

											<label class="control-label col-sm-3" for="utilizedAmt">
												Expenditures on e-Resources ${budRupees}<span
												class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="number" class="form-control" min="0"
													onchange="trim(this)"
													id="expenditure_on_eresources_purchase"
													name="expenditure_on_eresources_purchase"
													autocomplete="off"
													onkeypress='return restrictAlphabets(event)'
													placeholder="Expenditures on e-Resources ${budRupees}"
													value="${budget.expenditureOnEresourcesPurchase}">
												<span class="error_form text-danger" id="error_eresources"
													style="display: none;">Please Enter Expenditures on
													e-Resources.</span>

											</div>
										</div>
										
										<div class="form-group">

											<label class="control-label col-sm-3" for="utilizedAmt">Total Expenditure
												for Library Book ${budRupees}<span
												class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="number" class="form-control" min="0"
													onchange="trim(this)"
													id="ttl_exp" name="ttl_exp"
													autocomplete="off"
													onkeypress='return restrictAlphabets(event)'
													placeholder="Expenditures on e-Resources ${budRupees}"
													value="${budget.exInt1}">
												<span class="error_form text-danger" id="ttl_exp_field"
													style="display: none;">Please enter total expenditures for
													library book and value must be greater than 0.</span>

											</div>
										</div>
										<input type="hidden" id="budget_id" value="${budget.libraryBookBudgetId}" name="budget_id">

										<input type="hidden" id="is_view" name="is_view" value="0">
										<div class="form-group">
											<div class="col-sm-offset-3 col-sm-9">


												<button type="submit" id="sub1" class="btn btn-primary"
													onclick="submit_f(1)">
													<i class="${sessionScope.addIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save
												</button>

												<a
													href="${pageContext.request.contextPath}/budgetOnLibraryBooks"><button
														type="button" class="btn btn-primary">
														<i class="${sessionScope.cancelIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Cancel
													</button></a>
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
             function numbersOnlyNotZero(id_number) {

                 
                 var mob = /^[1-9][0-9]+$/;


                 if (mob.test($.trim(id_number)) == false) {

                     //alert("Please enter a valid email address .");
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


             
            
            	$(document).ready(function($){
          		//  alert("hii....");
            		$("#form_sample_2").submit(function(e) {
            			 var isError=false;
            			 var errMsg="";
            				
            				
            				if($("#fin_year_id").val()== -1 ){
            		            
                				
                				$("#error_year").show()
                					//return fregister_useralse;
                				} else {
                					$("#error_year").hide()
                				}
            				
            				
            				if (!$("#expenditure_on_book_purchase").val()
									|| !validateZeroNo($(
											"#expenditure_on_book_purchase")
											.val())) {
								
								isError = true;
								$("#expenditure_on_book_purchase")
										.addClass(
												"has-error")
									$("#error_pbook")
										.show()
							} else {
								$("#error_pbook")
										.hide()
							}
            				
            				
            				if (!$("#expenditure_on_journals_purchase").val()
									|| !validateZeroNo($(
											"#expenditure_on_journals_purchase")
											.val())) {
								
								isError = true;
								$("#expenditure_on_journals_purchase")
										.addClass(
												"has-error")
									$("#error_pjournal")
										.show()
							} else {
								$("#error_pjournal")
										.hide()
							}
            				
            				
            				if (!$("#expenditure_on_ejournals_purchase").val()
									|| !validateZeroNo($(
											"#expenditure_on_ejournals_purchase")
											.val())) {
								
								isError = true;
								$("#expenditure_on_ejournals_purchase")
										.addClass(
												"has-error")
									$("#error_ejournal")
										.show()
							} else {
								$("#error_ejournal")
										.hide()
							}
            				
            				
            				if (!$("#expenditure_on_eresources_purchase").val()
									|| !validateZeroNo($(
											"#expenditure_on_eresources_purchase")
											.val())) {
								
								isError = true;
								$("#expenditure_on_eresources_purchase")
										.addClass(
												"has-error")
									$("#error_eresources")
										.show()
							} else {
								$("#error_eresources")
										.hide()
							}


            				if ($("#ttl_exp").val()<=0
									|| !$("#ttl_exp").val()) {
								
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

            			  
            				 

							if (!isError) {
								var x = confirm("Do you really want to submit the form?");
								if (x == true) {
									return  true;
									 document.getElementById("sub1").disabled=true;
           						 	  
								}
							}
            
            			  
            						 
            					   return false;
            				} );
            	});
			//
			
			    
          
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

</body>
</html>