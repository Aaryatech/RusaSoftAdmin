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
								<%-- <a href="${pageContext.request.contextPath}/showBestPractice"><button
										type="button" class="btn btn-info">Back</button></a> --%>

							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertInstituteBestPract"
										method="post" name="form_sample_2" id="form_sample_2"
										>




										<div class="form-group">

											<label class="control-label col-sm-2" for="participant">Name of Best Practices
												<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control" id="bestPrac"
													autocomplete="off" name="bestPrac" onchange="trim(this)"
													placeholder="Name of Best Practices"
													value="${editInst.practicesName}" >
	<span class="error_form text-danger" id="error_name" style="display:none;" >Please Enter Name of Best Practices</span>	

											</div>
										</div>


										<div class="form-group">

											<label class="control-label col-sm-2" for="benificiary">Name of Best Beneficiaries
												<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control" id="benificiary"
													autocomplete="off" name="benificiary" onchange="trim(this)"
													placeholder="Name of Best Beneficiaries"
													value="${editInst.practicesBeneficiary}" >
			<span class="error_form text-danger" id="error_bef" style="display:none;" >Please Enter Name of Best Beneficiaries</span>	
		
											</div>
										</div>





										<div class="form-group">
											<label class="control-label col-sm-2"
												for="practices_effective_from">Effective From <span
												class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control datepicker"
													autocomplete="off" id="practices_effective_from" placeholder="dd-mm-yyyy"
													name="practices_effective_from" value="${date}"
													>
													
<span class="error_form text-danger" id="error_eff" style="display:none;" >Please Select Effective From</span>	

											</div>
										</div>

										 <input type="hidden" id="prac_id" name="prac_id" value="${editInst.practicesId}">
                                      	<input type="hidden" id="is_view" name="is_view" value="0">
                                             	
                                             	
												<div class="form-group">
													<div class="col-sm-offset-3 col-sm-9">


<button type="submit" id="sub_button" class="btn btn-primary"
													onclick="submit_f(1)"><i class="${sessionScope.addIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save</button>
														
<a href="${pageContext.request.contextPath}/showBestPractice"><button
										type="button" class="btn btn-primary"><i class="${sessionScope.cancelIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Cancel</button></a>													</div>
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
           
         
            
            	$(document).ready(function($){
          //  alert("hii....");
            		$("#form_sample_2").submit(function(e) {
            			 var isError=false;
            			 var errMsg="";
            				
            			 if(!$("#bestPrac").val()){
        					 
             				isError=true;
             				
             				
             				$("#bestPrac").addClass("has-error")
             				$("#error_name").show()
             					//return false;
             				} else {
             					$("#error_name").hide()
             				}
            			 
            			 if(!$("#benificiary").val()){
        					 
              				isError=true;
              				
              				
              				$("#benificiary").addClass("has-error")
              				$("#error_bef").show()
              					//return false;
              				} else {
              					$("#error_bef").hide()
              				}
            			 
            			 if(!$("#practices_effective_from").val()){
        					 
               				isError=true;
               				
               				
               				$("#practices_effective_from").addClass("has-error")
               				$("#error_eff").show()
               					//return false;
               				} else {
               					$("#error_eff").hide()
               				}
             			 
             			 

							if (!isError) {
								var x = confirm("Do you really want to submit the form?");
								if (x == true) {
									return  true;
									 document.getElementById("sub1").disabled=true;
           						  document.getElementById("sub2").disabled=true;
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