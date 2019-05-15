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
<body class=" " onload="hideText()">
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
								<%-- <a href="${pageContext.request.contextPath}/showAMC"><button
										type="button" class="btn btn-info">Back</button></a> --%>



							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertInstituteAMC"
										method="post" name="form_sample_2" id="form_sample_2">

										<div class="form-group">

											<label class="control-label col-sm-2" for="amc_title">Title
												of Maintenance <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control" id="amc_title"
													autocomplete="off" name="amc_title" onchange="trim(this)"
													placeholder="Title of Maintenance"
													value="${editInst.amcTitle}"> <span
													class="error_form text-danger" id="error_name"
													style="display: none;">Please Enter Title </span>

											</div>
										</div>
										<div class="form-group">

											<label class="control-label col-sm-2" for="amc_expenditure">AMC
												Expenditure (Rs.)<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="number" class="form-control" min="0"
													onchange="return trim(this)" id="amc_expenditure"
													onkeypress='return restrictAlphabets(event)' onFocus="clearDefault(this)"
													name="amc_expenditure" placeholder="AMC Expenditure(Rs.)"
													autocomplete="off" value="${editInst.amcExpenditure}">
												<span class="error_form text-danger" id="error_exp"
													style="display: none;">Please Enter AMC Expenditure
												</span>

											</div>
										</div>


										<div class="form-group">

											<label class="control-label col-sm-2" for="amc_company">Name
												of Company<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control" id="amc_company"
													name="amc_company" placeholder="Name of Company"
													onchange="trim(this)" autocomplete="off"
													value="${editInst.amcCompany}"> <span
													class="error_form text-danger" id="error_comp"
													style="display: none;">Please Enter Company Name </span>

											</div>
										</div>

										<div class="form-group">

											<label class="control-label col-sm-2" for="amc_remark">
												Scope of AMC<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control" id="amc_remark"
													name="amc_remark" placeholder="Scope of AMC"
													onchange="trim(this)" autocomplete="off"
													value="${editInst.amcRemarks}"> <span
													class="error_form text-danger" id="error_amc"
													style="display: none;">Please Enter Scope of AMC </span>

											</div>
										</div>


										<input type="hidden" id="is_view" name="is_view" value="0">
										<input type="hidden" id="amc_id" name="amc_id"
											value="${editInst.amcId}">


										<div class="form-group">
											<div class="col-sm-offset-3 col-sm-9">


												<button type="submit" id="sub1"
													class="btn btn-primary" onclick="submit_f(1)">
													<i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save
												</button>

												<a href="${pageContext.request.contextPath}/showAMC"><button
														type="button" class="btn btn-primary" id="sub2">
														<i class="${sessionScope.cancelIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Cancel
													</button></a>
											</div>
										</div>

									</form>
									<p class="desc text-danger fontsize11">Notice : * Fields
										are Mandatory.</p>

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
           
             function numbersOnlyNotZero(id_number) {

                 
                 var mob = /^[1-9][0-9]+$/;


                 if (mob.test($.trim(id_number)) == false) {

                     //alert("Please enter a valid email address .");
                     return false;

                 }
                 return true;
             }



             
            
            	$(document).ready(function($){
          //  alert("hii....");
            		$("#form_sample_2").submit(function(e) {
            			 var isError=false;
            			 var errMsg="";
            				
            			 if(!$("#amc_title").val()){
        					 
             				isError=true;
             				
             				
             				$("#amc_title").addClass("has-error")
             				$("#error_name").show()
             					//return false;
             				} else {
             					$("#error_name").hide()
             				}
            			 
            			 
            				if($("#amc_expenditure").val()==0 || !$("#amc_expenditure").val()){
              					 
                				isError=true;
                				errMsg += '<li>Please enter a valid name.</li>';
                				
                				$("#amc_expenditure").addClass("has-error")
                				$("#error_exp").show()
                					//return false;
                				} else {
                					$("#error_exp").hide()
                				}  
            			 if(!$("#amc_company").val()){
        					 
              				isError=true;
              				
              				
              				$("#amc_company").addClass("has-error")
              				$("#error_comp").show()
              					//return false;
              				} else {
              					$("#error_comp").hide()
              				}
            			 
            			 

            			 if(!$("#amc_remark").val()){
        					 
              				isError=true;
              				
              				
              				$("#amc_remark").addClass("has-error")
              				$("#error_amc").show()
              					//return false;
              				} else {
              					$("#error_amc").hide()
              				}
            			 

							if (!isError) {
								var x = confirm("Do you really want to submit the form?");
								if (x == true) {
									
									 document.getElementById("sub1").disabled=true;
           						 	 document.getElementById("sub2").disabled=true;
           							return  true;
								}
							}
            
            			  
            						 
            					   return false;
            				} );
            	});
			//
			
			    
          
        </script>
	<script type="text/javascript">
function submit_f(view){
		document.getElementById("is_view").value=view;//create this 
		/* var form=document.getElementById("form_sample_2");
	    form.setAttribute("method", "post");

		form.action=("insertLibrarian");
		var x =confirm();
		if(x==true)
		form.submit(); */
		
	}
	
	
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
  
   
	</script>

	<script type="text/javascript">
	function clearDefault(a){
		if(a.defaultValue==0)
		{
			a.value=""
		}
		};

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
		$(function() {
			$('#submitForm').submit(
					function() {
						$("input[type='submit']", this).val("Please Wait...")
								.attr('disabled', 'disabled');

						return true;
					});
		});

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

</body>
</html>