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
								<a href="${pageContext.request.contextPath}/showJournalPubList"><button
										type="button" class="btn btn-info">Back</button></a>
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertJournal"
										method="post" name="formidhere" id="formidhere">



										<div class="form-group">
											<label class="control-label col-sm-2" for="jouStd">Journal
												Publication <span class="text-danger">*</span>
											</label>

											<c:choose>
												<c:when test="${editJournal.journalStandard==0}">
													<div class="col-md-2">

														<input type="radio" name="jouStd" id="jouStd" value="0"
															checked>National

													</div>

													<div class="col-md-2">
														<input type="radio" name="jouStd" value="1">
														International
													</div>
												</c:when>
												<c:when test="${editJournal.journalStandard==1}">
													<div class="col-md-2">

														<input type="radio" name="jouStd" id="jouStd" value="0">National

													</div>

													<div class="col-md-2">
														<input type="radio" name="jouStd" value="1" checked>
														International
													</div>
												</c:when>
												<c:otherwise>

													<div class="col-md-2">

														<input type="radio" name="jouStd" id="jouStd" checked
															value="0">National

													</div>

													<div class="col-md-2">
														<input type="radio" name="jouStd" value="1">
														International
													</div>


												</c:otherwise>
											</c:choose>

										</div>
										<div class="form-group">
											<label class="control-label col-sm-2" for="journalName">Name
												of Journal <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control" id="journalName"
													name="journalName" placeholder="Name of journal"
													value="${editJournal.journalName}" autocomplete="off">
												<span class="error_form text-danger" id="error_jName"
													style="display: none;">Please enter Journal Name</span>
											</div>
										</div>
										<div class="form-group">
											<label class="control-label col-sm-2" for="journalType">Scopus/DIO/UGC
												Recognized <span class="text-danger">*</span>
											</label>




											<c:choose>
												<c:when test="${editJournal.journalType==0}">
													<div class="col-md-2">

														<input type="radio" name="journalType" id="journalType"
															value="0" checked>Yes

													</div>

													<div class="col-md-2">
														<input type="radio" name="journalType" value="1">
														No
													</div>
												</c:when>
												<c:when test="${editJournal.journalStandard==1}">
													<div class="col-md-2">

														<input type="radio" name="journalType" id="journalType"
															value="0">Yes
													</div>
													<div class="col-md-2">
														<input type="radio" name="journalType" value="1" checked>
														No
													</div>
												</c:when>
												<c:otherwise>
													<div class="col-md-2">
														<input type="radio" name="journalType" checked
															id="journalType" value="0">Yes
													</div>

													<div class="col-md-2">
														<input type="radio" name="journalType" value="1">
														No
													</div>

												</c:otherwise>
											</c:choose>

										</div>
										<div class="form-group">
											<label class="control-label col-sm-2" for="journalYear">Year
												of Publication <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control datepickeryear"
													data-min-view-mode="years" data-start-view="2"
													value="${editJournal.journalYear}" autocomplete="off"
													placeholder="Year of Publication" name="journalYear"
													id="journalYear" data-format="yyyy">

											</div>

										</div>
										<div class="form-group">
											<label class="control-label col-sm-2" for="issue">Issue
												<span class="text-danger">*</span>
											</label>


											<div class="col-sm-6">
												<input type="text" class="form-control" id="issue"
													value="${editJournal.journalIssue}" autocomplete="off"
													name="issue" placeholder="Issue"> <span
													class="error_form text-danger" id="error_issue"
													style="display: none;">Please enter Journal Issue</span>
											</div>

										</div>
										<div class="form-group">
											<label class="control-label col-sm-2" for="volume">Volume
												<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="number" class="form-control" id="volume"
													min="0" value="${editJournal.journalVolume}"
													autocomplete="off" name="volume" placeholder="Volume">
												<span class="error_form text-danger" id="error_volume"
													style="display: none;">Please enter Journal Volume</span>
											</div>


										</div>
										<input type="hidden" id="is_view" name="is_view" value="0">


										<div class="form-group">
											<label class="control-label col-sm-2" for="journalPgFrom">Page
												No <span class="text-danger">*</span>
											</label> <label class="control-label col-sm-1" for="journalPgFrom">From
												<span class="text-danger">*</span>
											</label>
											<div class="col-sm-2">
												<input type="number" class="form-control" id="journalPgFrom"
													name="journalPgFrom" min="0"
													value="${editJournal.journalPgFrom}" autocomplete="off"
													placeholder="Page No From"> <span
													class="error_form text-danger" id="error_journalPgFrom"
													style="display: none;">Please enter Journal Page
													From</span>
											</div>
											<input type="hidden" value="${editJournal.journalId}"
												name="journalId" id="journalId"> <label
												class="control-label col-sm-1" for="page_order">To <span
												class="text-danger">*</span>
											</label>


											<div class="col-sm-2">
												<input type="number" class="form-control" id="journalPgTo"
													autocomplete="off" name="journalPgTo" min="0"
													placeholder="Page No To" value="${editJournal.journalPgTo}">
												<span class="error_form text-danger" id="error_journalPgTo"
													style="display: none;">Please enter Journal Page To</span>
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-offset-2 col-sm-10">
												<input type="submit" class="btn btn-primary"
													onclick="submit_f(1)" value="Save" id="sub_button">
												<input type="submit" class="btn btn-primary"
													id="sub_button_next" onclick="submit_f(0)"
													value="Save & Next">
												<button type="reset" class="btn btn-default">Reset</button>
											</div>
										</div>



										<div class="clearfix"></div>

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
        $(function () {
		 
            $('.datepickeryear').datepicker({
				autoclose: true,
				minViewMode: 2,
		         format: 'yyyy'

			});
        });
    </script>


	<script type="text/javascript">
function submit_f(view){
	//alert(view);
		document.getElementById("is_view").value=view; 
		
	}
 
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
	 function numbersOnlyNotZero(value) {

	        
	        var mob = /^[1-9][0-9]+$/;


	        if (mob.test($.trim(value)) == false) {

	            //alert("Please enter a valid email address .");
	            return false;

	        }
	        return true;
	    }
	</script>


	<script>
		function checkJournalForm() {

			var journalPgFrom = document.getElementById("journalPgFrom").value;
			var journalPgTo = document.getElementById("journalPgTo").value;
		 
			var len= journalPgTo.length;
			  
			
			var valid = true;
			
			if(len!=0){
			if (  parseFloat(journalPgFrom) > parseFloat(journalPgTo)  ) {

				valid = false;
			} 

			if (valid == false) {
				
				alert("Enter Journal From   less than Journal To ");
				//document.getElementById("pmin_stock").value="";
				document.getElementById("journalPgTo").value="";
			}
		}

		}
	</script>

	<script>
	   
            	$(document).ready(function($){
            	 
            		$("#formidhere").submit(function(e) {
            			 var isError=false;
            			 var errMsg="";
            				
           
            				if(!$("#journalName").val()){
            					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#journalName").addClass("has-error")
            				$("#error_jName").show()
            					 
            				} else {
            					$("#error_jName").hide()
            				}
            				
            				
            				if(!$("#issue").val()){
            					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#issue").addClass("has-error")
            				$("#error_issue").show()
            					 
            				} else {
            					$("#error_issue").hide()
            				}
            				
            				if(!$("#volume").val() || !numbersOnlyNotZero($("#volume").val())){
            					 
            				isError=true;
            				errMsg += '<li>Please enter Volume.</li>';
            				
            				$("#volume").addClass("has-error")
            				$("#error_volume").show()
            					 
            				} else {
            					$("#error_volume").hide()
            				}
            				
            				
            				if(!$("#journalPgFrom").val() || !numbersOnlyNotZero($("#journalPgFrom").val())){
            					 
            				isError=true;
            				errMsg += '<li>Please enter Journal Page From .</li>';
            				
            				$("#journalPgFrom").addClass("has-error")
            				$("#error_journalPgFrom").show()
            					 
            				} else {
            					$("#error_journalPgFrom").hide()
            				}
            				
            				
            				
            				if(!$("#journalPgTo").val() || !numbersOnlyNotZero($("#journalPgTo").val())){
            					 
            				isError=true;
            				errMsg += '<li>Please enter Journal Page To.</li>';
            				
            				$("#journalPgTo").addClass("has-error")
            				$("#error_journalPgTo").show()
            					 
            				} else {
            					$("#error_journalPgTo").hide()
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