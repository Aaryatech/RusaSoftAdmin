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
<body class=" " onload="showForm()">
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

							<%-- <div class="actions panel_actions pull-right">
								<a
									href="${pageContext.request.contextPath}/showInternalQualityInitiative"><button
										type="button" class="btn btn-info"><i class="${sessionScope.cancelIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Cancel</button></a>

							</div> --%>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertInstQuaInitiative"
										method="post" name="form_sample_2" id="form_sample_2">



										<div class="form-group">
											<label class="control-label col-sm-2" for="qualityInitId">
												Quality Initiative<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<select id="qualityInitId" name="qualityInitId" onchange="showForm()"
													class="form-control" title="Select Quality Initiative">
													<c:choose>
													<c:when test="${editQuality.qualityInitiativeId==0}">
																																								<option selected disabled value="-1">Select</option>
														
														<c:forEach items="${qualInintList}" var="quInit">
																<option  value="${quInit.qualityInitiativeId}">${quInit.qualityInitiativeName}</option>
													</c:forEach>
													<option selected value="0">Any other quality based certification</option>
													
													</c:when>
													<c:otherwise>
													
													<c:forEach items="${qualInintList}" var="quInit">
													
														<c:choose>
															<c:when
																test="${quInit.qualityInitiativeId==editQuality.qualityInitiativeId}">
																<option selected value="${quInit.qualityInitiativeId}">${quInit.qualityInitiativeName}</option>
															</c:when>
															<c:otherwise>
																<option value="${quInit.qualityInitiativeId}">${quInit.qualityInitiativeName}</option>
															</c:otherwise>
														</c:choose>
													</c:forEach>
													<option disabled value="-1">Select</option>
													
													<option  value="0">Any other quality based certification</option>
													
													</c:otherwise>
													
													</c:choose>
													
													
												</select> <span class="error_form text-danger"
													id="qualityInitId_field" style="display: none;">Please
													select quality initiative name</span>


											</div>
										</div>
										
										<div class="form-group" id="abc" style="display: none">

											<label class="control-label col-sm-2" for="page_name">Name of 
												Quality Initiative<span class="text-danger">*</span> </label>
											<div class="col-sm-6">
												<input type="text" class="form-control" id="qltyInitiative"
													autocomplete="off" name="qltyInitiative" onchange="trim(this)"
													placeholder="Name of Quality Initiative" value="${editQuality.exVar1}"> <span
													class="error_form text-danger" id="error_other"
													style="display: none;">Please enter name of quality initiative.</span>
											</div>

										</div>

										<div class="form-group">
											<label class="control-label col-sm-2" for="no_of_participant">No.
												of Participant <span class="text-danger"></span>
											</label>
											<div class="col-sm-6">
												<input type="text" maxlength="9" onFocus="clearDefault(this)"
													onkeypress='return restrictAlphabets(event)'
													onchange="trim(this)" class="form-control"
													id="no_of_participant" min="0" autocomplete="off"
													name="no_of_participant"
													placeholder="Enter  No. of Participants"
													value="${editQuality.qualityPcount}"> <span
													class="error_form text-danger" id="no_of_participant_field"
													style="display: none;">Please enter no of
													participants.</span>

											</div>
										</div>
										<div class="form-group">
											<label class="control-label col-sm-2" for="fromDate">From
												Date <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control datepicker"
													placeholder="Select From Date" autocomplete="off"
													data-end-date="0d" data-format="dd-mm-yyyy"
													id="fromDate" name="fromDate"
													value="${editQuality.qualityFromdt}"> <span
													class="error_form text-danger" id="fromDate_field"
													style="display: none;">Please select from date.</span>
													
													<span
													class="error_form text-danger" id="error_fromToDate"
													style="display: none;">from date must be smaller than to date.</span>

											</div>
										</div>
										<div class="form-group">

											<label class="control-label col-sm-2" for="toDate">To
												Date <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control datepicker"
													autocomplete="off" id="toDate" name="toDate"
													placeholder="Select To Date" data-format="dd-mm-yyyy"
													value="${editQuality.qualityTodt}"> <span
													class="error_form text-danger" id="toDate_field"
													style="display: none;">Please select to date.</span>
													
												<span
													class="error_form text-danger" id="error_toToDate"
													style="display: none;">to date must be greater than from date.</span>

											</div>
										</div>

										<div class="form-group">
											<div class="col-sm-offset-2 col-sm-10">
												<button type="submit" id="sub1" class="btn btn-primary"
													onclick="submit_f(1)">
													<i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save
												</button>
												<a
													href="${pageContext.request.contextPath}/showInternalQualityInitiative"><button
														id="sub2" type="button" class="btn btn-primary">
														<i class="${sessionScope.cancelIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Cancel
													</button></a> <input type="hidden" name="is_view" id="is_view" value="0">

												<input type="hidden" id="qualityId" name="qualityId"
													value="${editQuality.qualityId}">

											</div>

											<div class="clearfix"></div>
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
		$(document)
				.ready(
						function($) {

							$("#form_sample_2")
									.submit(
											function(e) {
												var isError = false;
												var errMsg = "";

												if ($("#qualityInitId").val() == null || $("#qualityInitId").val()==-1) {
													isError = true;

													$("#qualityInitId").addClass(
															"has-error")
													$("#qualityInitId_field")
															.show()
												} else {
													$("#qualityInitId_field")
															.hide()
												}
												
												 var from_date = document.getElementById("fromDate").value;
							         				var to_date = document.getElementById("toDate").value;
							         				var x=0;
							         				
							         				
							         		        var fromdate = from_date.split('-');
							         		        from_date = new Date();
							         		        from_date.setFullYear(fromdate[2],fromdate[1]-1,fromdate[0]);
							         		        var todate = to_date.split('-');
							         		        to_date = new Date();
							         		        to_date.setFullYear(todate[2],todate[1]-1,todate[0]);
							         		        if (from_date > to_date ) 
							         		        {
							         		           /// alert("Invalid Date Range!\nStart Date cannot be after End Date!")
														$("#error_fromToDate").show();
							    					 	$("#error_toToDate").show();
							    					 	$("#fromDate_field").hide();
							    					 	$("#toDate_field").hide();
							         		            return false;
							         		           
							         		        }else {
							         					$("#error_fromToDate").hide();
							         					$("#error_toToDate").hide();
							         				}
												
												if (!$("#fromDate").val()) {
													isError = true;

													$("#fromDate").addClass(
															"has-error")
													$("#fromDate_field").show();
												} else {
													$("#fromDate_field").hide();
												}
												
												if (!$("#toDate").val()) {
													isError = true;

													$("#toDate").addClass(
															"has-error")
													$("#toDate_field").show();
												} else {
													$("#toDate_field").hide();
												}
												
												
												var res = $("#qualityInitId").val();
												if(res==0){
													
													if (!$("#qltyInitiative").val()) {
														isError = true;
	
														$("#qltyInitiative").addClass(
																"has-error")
														$("#error_other").show();
													} else {
														$("#error_other").hide();
													}
												}
												
												/* if ($("#no_of_participant")
														.val() == 0
														|| !$(
																"#no_of_participant")
																.val()) {

													isError = true;
													errMsg += '<li>Please enter a valid name.</li>';

													$("#no_of_participant")
															.addClass(
																	"has-error")
													$(
															"#no_of_participant_field")
															.show()
													//return false;
												} else {
													$(
															"#no_of_participant_field")
															.hide()
												} */


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


	<!-- 	<div class="modal fade col-xs-12" id="myModal1" tabindex="-1"
		role="dialog" aria-hidden="true">
		<div class="modal-dialog" style="width: 65%">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Internal Quality Initiative</h4>
				</div>
				<div class="modal-body">





					Link on Website for Activity Report

					<div class="form-group" style="text-align: center;">

						<button type="submit" class="btn btn-primary" onclick="getData()">Submit</button>
					</div>


				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
					<input type="hidden" id="index" name="index" value="0">
				</div>
			</div>
		</div>
	</div>


	<script type="text/javascript">
		function getData() {
			//alert("hii");
			var i = parseInt(document.getElementById("index").value);

			var academicYear = document.getElementById("academicYear").value;
			var initiativeName = document.getElementById("initiativeName").value;
			var conductionDate = document.getElementById("conductionDate").value;
			var fromDate = document.getElementById("fromDate").value;
			var toDate = document.getElementById("toDate").value;
			var participant = document.getElementById("participant").value;
			var otherQual = document.getElementById("otherQual").value;
			//alert("noStud"+noStud);
			var temp;
			if (initiativeName == 7) {

				temp = otherQual;
				//alert(temp);
			} else {
				temp = initiativeName;
			}

			var dataTable = $('#example-1').DataTable();

			dataTable.row.add(
					[ i + 1, academicYear, temp, conductionDate, fromDate,
							toDate, participant ]).draw();
			document.getElementById("index").value = i + 1;
		}

		function showForm() {
			//document.getElementById("abc").style = "display:none"
			var qualType = document.getElementById("initiativeName").value
			//alert("qualType::"+qualType);

			if (qualType == 7) {

				document.getElementById("abc").style = "visible"

			} else {
				document.getElementById("abc").style = "display:none"
			}

		}
		function hideText() {
			//alert("hii");
			document.getElementById("abc").style = "display:none"

		}
	</script>
 -->

	<script type="text/javascript">
		/* $(function() {

			$('.datepicker').datepicker({
				autoclose : true,
				format : "dd-mm-yyyy",
				changeYear : true,
				changeMonth : true

			});
		}); */
		
		function hideText() {
			var value = document.getElementById("qualityInitId").value
			//alert("hii");
			/* var qualType = document.getElementById("qltyInitiative").value
			alert("hii"+qualType);
			document.getElementById("abc").style.display = (qualType==null) ? "none" : "inline"; */
	if(value==0){
		document.getElementById("abc").style = "visible"
		
	}else{
		document.getElementById("abc").style = "display:none"

	}
			
				
		}
		
		function showForm() {
			//document.getElementById("abc").style = "display:none"
			var value = document.getElementById("qualityInitId").value
			var editValue = value;
			//alert("qualType::"+value);

			//document.getElementById("abc").style.display = (value == editValue) ? "inline" : "none";
			
			if (value == 0) {
				//alert("called");
				document.getElementById("abc").style = "visible"

			} else {
				document.getElementById("abc").style = "display:none"
			} 

		}
	</script>
	<script type="text/javascript">

		function trim(el) {
			el.value = el.value.replace(/(^\s*)|(\s*$)/gi, ""). // removes leading and trailing spaces
			replace(/[ ]{2,}/gi, " "). // replaces multiple spaces with one space 
			replace(/\n +/, "\n"); // Removes spaces after newlines
			return;
		}
	</script>
	<script type="text/javascript">
		function submit_f(view) {
			document.getElementById("is_view").value = view;
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


	<script type="text/javascript">
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


</body>
</html>