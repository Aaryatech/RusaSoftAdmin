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
										method="post" name="form_sample_2" id="form_sample_2"
										onsubmit="return confirm('Do you really want to submit the form?');">

										<ul class="nav nav-tabs">
											<li class="active"><a href="#home" data-toggle="tab">
													<i class="fa fa-home"></i> Register
											</a></li>

										</ul>

										<div class="tab-content">
											<div class="tab-pane fade in active" id="home">

												<div>
													<div class="col-xs-12">
														<div class="form-group">
															<label class="control-label col-sm-2" for="jouStd">Journal
																Publication <span class="text-danger">*</span>
															</label>

															<c:choose>
																<c:when test="${editJournal.journalStandard==0}">
																	<div class="col-md-2">

																		<input type="radio" name="jouStd" id="jouStd"
																			value="0" checked>National

																	</div>

																	<div class="col-md-2">
																		<input type="radio" name="jouStd" value="1">
																		International
																	</div>
																</c:when>
																<c:when test="${editJournal.journalStandard==1}">
																	<div class="col-md-2">

																		<input type="radio" name="jouStd" id="jouStd"
																			value="0">National

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

															<!-- <div class="col-sm-6">
																National <input type="radio" name="jouStd" id="jouStd"
																	checked value="0"> International<input
																	type="radio" name="jouStd" id="jouStd" value="1">
															</div> -->
														</div>
														<div class="form-group">
															<label class="control-label col-sm-2" for="journalName">Name
																of Journal <span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="text" class="form-control" id="journalName"
																	name="journalName" placeholder="Name of journal"
																	value="${editJournal.journalName}" autocomplete="off"
																	value="" required>
															</div>
														</div>
														<div class="form-group">
															<label class="control-label col-sm-2" for="journalType">Scopus/DIO/UGC
																Recognized <span class="text-danger">*</span>
															</label>




															<c:choose>
																<c:when test="${editJournal.journalType==0}">
																	<div class="col-md-2">

																		<input type="radio" name="journalType"
																			id="journalType" value="0" checked>Yes

																	</div>

																	<div class="col-md-2">
																		<input type="radio" name="journalType" value="1">
																		No
																	</div>
																</c:when>
																<c:when test="${editJournal.journalStandard==1}">
																	<div class="col-md-2">

																		<input type="radio" name="journalType"
																			id="journalType" value="0">Yes
																	</div>
																	<div class="col-md-2">
																		<input type="radio" name="journalType" value="1"
																			checked> No
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



															<!-- 	<div class="col-sm-2">
																Yes <input type="radio" name="journalType"
																	id="journalType" checked value="0"> No<input
																	type="radio" name="journalType" id="journalType"
																	value="1">
															</div> -->
														</div>
														<div class="form-group">
															<label class="control-label col-sm-2" for="journalYear">Year
																of Publication <span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="text" class="form-control datepicker"
																	id="journalYear" name="journalYear"
																	value="${editJournal.journalYear}" autocomplete="off"
																	placeholder="Year of Publication" value="" required>
															</div>

														</div>
														<div class="form-group">
															<label class="control-label col-sm-2" for="issue">Issue
																<span class="text-danger">*</span>
															</label>


															<div class="col-sm-6">
																<input type="text" class="form-control" id="issue"
																	value="${editJournal.journalIssue}" autocomplete="off"
																	name="issue" placeholder="Issue" value="" required>
															</div>

														</div>
														<div class="form-group">
															<label class="control-label col-sm-2" for="volume">Volume
																<span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="number" class="form-control" id="volume"
																	min="0" value="${editJournal.journalVolume}"
																	autocomplete="off"
																	onkeypress="return allowOnlyNumber(event)"
																	name="volume" placeholder="Volume" value="" required>
															</div>


														</div>
														<input type="hidden" id="is_view" name="is_view" value="0">


														<div class="form-group">
															<label class="control-label col-sm-2" for="journalPgFrom">Page
																No <span class="text-danger">*</span>
															</label> <label class="control-label col-sm-1"
																for="journalPgFrom">From <span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-2">
																<input type="number" class="form-control"
																	id="journalPgFrom" name="journalPgFrom"
																	onchange="checkJournalForm()" min="0"
																	onkeypress="return allowOnlyNumber(event)"
																	value="${editJournal.journalPgFrom}" autocomplete="off"
																	placeholder="Page No From" required>
															</div>
															<input type="hidden" value="${editJournal.journalId}"
																name="journalId" id="journalId"> <label
																class="control-label col-sm-1" for="page_order">To
																<span class="text-danger">*</span>
															</label>


															<div class="col-sm-2">
																<input type="number" class="form-control"
																	id="journalPgTo" autocomplete="off" name="journalPgTo"
																	min="0" onchange="checkJournalForm()"
																	placeholder="Page No To"
																	onkeypress="return allowOnlyNumber(event)"
																	value="${editJournal.journalPgTo}" required>
															</div>
														</div>
														<div class="form-group">
															<div class="col-sm-offset-2 col-sm-10">
																<input type="submit" class="btn btn-primary"
																	onclick="submit_f(1)" value="Save"> <input
																	type="submit" class="btn btn-primary"
																	onclick="submit_f(0)"
																	value="Save &
																		Next">
																<button type="reset" class="btn btn-default">Reset</button>
															</div>
														</div>
													</div>


													<div class="clearfix"></div>

												</div>

											</div>
										</div>
									</form>
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
	</script>
	<!-- 	<script type="text/javascript">
		function checkJournalForm() {
			 
			alert("hii");
		 
				var journalPgFrom = document.getElementById("journalPgFrom").value;
				var journalPgTo = document.getElementById("journalPgTo").value;
				
				alert(journalPgFrom);
				alert(journalPgTo);
				if(parseFloat(journalPgFrom) < parseFloat(journalPgTo))
					{
					alert("Please Enter Right Page No ");
					document.getElementById("journalPgFrom").value = "";
					document.getElementById("journalPgTo").value = "";
					}
			 
 
		}
		</script> -->

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



</body>
</html>