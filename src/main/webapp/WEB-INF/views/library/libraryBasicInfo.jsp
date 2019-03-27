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
								<a href="${pageContext.request.contextPath}/showLibraryBasicInfo"><button
										type="button" class="btn btn-info">Back</button></a>
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertLibInfo"
										method="post" 
										name="form_sample_2" id="form_sample_2"
											onsubmit="return checkBeforeSubmit()">

									
												<div>


													<div class="col-xs-12">

													<input type="hidden" value="${libInfo.libInfoId}" name="libInfoId">

														<!-- <div class="form-group">
															<label class="control-label col-sm-5" for="status"
																style="text-align: left;">Library is Automated
																using Integrated Library Management System  <span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
															
																<input type="radio" id="isUsingSoft" name="isUsingSoft"
																	onclick="showDiv(1)" value="1" checked>Yes <input
																	type="radio" id="isUsingSoft" name="isUsingSoft"
																	onclick="showDiv(0)" value="0">No
															</div>
														</div> -->

														<div id="divshow">
															<div class="form-group">
																<label class="control-label col-sm-3" for="swName"
																	style="text-align: left;">Name Of Software <span
																	class="text-danger">*</span>
																</label>
																<div class="col-sm-9">
																	<input type="text" class="form-control" id="swName"
																		name="swName" placeholder=""
																		value="${libInfo.softName}" pattern="^(?!\s*$).+" autocomplete="off"  required>
																</div>
															</div>

															<div class="form-group">
																<label class="control-label col-sm-3" for="version"
																	style="text-align: left;">Software Version<span
																	class="text-danger">*</span>
																</label>
																<div class="col-sm-9">
																	<input type="text" class="form-control" id="version"
																		name="version" placeholder=""
																		value="${libInfo.softVersion}" pattern="^(?!\s*$).+" autocomplete="off"  required>
																</div>
															</div>
															
															<div class="form-group">
																<label class="control-label col-sm-3" for="version"
																	style="text-align: left;">Total Number of Users of LMS <span
																	class="text-danger">*</span>
																</label>
																<div class="col-sm-9">
																	<input type="number" class="form-control" id="userLms"
																		name="userLms" placeholder="" 
																		onkeypress='return restrictAlphabets(event)' min="1" max="999999"
																		value="${libInfo.usersOfLms}" pattern="^(?!\s*$).+" autocomplete="off"  required>
																</div>
															</div>


															<div class="form-group">
																<label class="control-label col-sm-3" for="date"
																	style="text-align: left;"> Date Of
																	Purchase/Automation Lib.  <span class="text-danger">*</span>
																</label>
																<div class="col-sm-9">
																	<input type="text" class="form-control datepicker" id="purchaseDate"
																		name="purchaseDate" onkeypress='return restrictAlphabets(event)'
																		pattern="^(?!\s*$).+" autocomplete="off" 
																		placeholder="dd/mm/yyyy"
																		value="${libInfo.dateOfPurchaseAutomation}" required>
																</div>
															</div>


															<div class="form-group">
																<label class="control-label col-sm-3" for="noOfComp"
																	style="text-align: left;">No. of Comp in LAN
																	with LMS  <span class="text-danger">*</span>
																</label>
																<div class="col-sm-9">
																	<input type="number" min="1" max="99999" class="form-control" id="noOfComp"
																		name="noOfComp" onkeypress='return restrictAlphabets(event)'
																		placeholder="" value="${libInfo.noCompLan}"
																		pattern="^(?!\s*$).+" autocomplete="off"  required>
																</div>
															</div>

															<div class="form-group">
																<label class="control-label col-sm-3" for="bandwidth"
																	style="text-align: left;">Bandwidth for
																	accessing e-resources  <span class="text-danger">*</span>
																</label>
																<div class="col-sm-9">
																	<input type="text" class="form-control" id="bandwidth"
																		name="bandwidth"
																		placeholder=""
																		value="${libInfo.bandwidthForAccessingEresources}" pattern="^(?!\s*$).+" autocomplete="off"  required>
																</div>
															</div>

															<div class="form-group">
																<label class="control-label col-sm-3" for="usingremot"
																	style="text-align: left;"> e-Resources are
																	remotely Accessible  <span class="text-danger">*</span>
																</label>
																<div class="col-sm-9">
																
																<c:choose>
																<c:when test="${libInfo.isEresourceRemotly == 1}">
																<input type="radio" id="usingremot" name="usingremot"
																		value="1" checked>Yes <input type="radio"
																		id="usingremot" name="usingremot" value="0">No
																</c:when>
																
																	<c:when test="${libInfo.isEresourceRemotly == 0}">
																<input type="radio" id="usingremot" name="usingremot"
																		value="1" >Yes <input type="radio"
																		id="usingremot" name="usingremot" checked value="0">No
																</c:when>
																		</c:choose>
																</div>
															</div>
														</div>

<!-- 
														<div class="form-group">
															<label class="control-label col-sm-3" for="usingremot"
																style="text-align: left;"> e-Journals : <span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-2">
																<input type="radio" id="eJournals" name="eJournals"
																	value="1" checked>Yes <input type="radio"
																	id="eJournals" name="eJournals" value="0">No
															</div>
															<div class="col-sm-2">Membership Details</div>
															<div class="col-sm-3">

																<input type="text" class="form-control" id="bandwidth"
																	name="bandwidth" placeholder="Membership Details"
																	value="" required>

															</div>
														</div>

														<div class="form-group">
															<label class="control-label col-sm-3" for="usingremot"
																style="text-align: left;"> e-Shodh Sindhu : <span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-2">
																<input type="radio" id="shodhsindhu" name="shodhsindhu"
																	value="1" checked>Yes <input type="radio"
																	id="shodhsindhu" name="shodhsindhu" value="0">No
															</div>
															<div class="col-sm-2">Membership Details</div>
															<div class="col-sm-3">

																<input type="text" class="form-control" id="bandwidth"
																	name="bandwidth" placeholder="Membership Details"
																	value="" required>

															</div>
														</div>


														<div class="form-group">
															<label class="control-label col-sm-3" for="usingremot"
																style="text-align: left;"> Shodh Ganga
																Membership : <span class="text-danger">*</span>
															</label>
															<div class="col-sm-2">
																<input type="radio" id="gangaMembership"
																	name="gangaMembership" value="1" checked>Yes <input
																	type="radio" id="gangaMembership"
																	name="gangaMembership" value="0">No
															</div>
															<div class="col-sm-2">Membership Details</div>
															<div class="col-sm-3">

																<input type="text" class="form-control" id="bandwidth"
																	name="bandwidth" placeholder="Membership Details"
																	value="" required>

															</div>
														</div>

														<div class="form-group">
															<label class="control-label col-sm-3" for="usingremot"
																style="text-align: left;"> e-Books : <span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-2">
																<input type="radio" id="eBooks" name="eBooks" value="1"
																	checked>Yes <input type="radio" id="eBooks"
																	name="eBooks" value="0">No
															</div>
															<div class="col-sm-2">No. of e-Books</div>
															<div class="col-sm-3">

																<input type="text" class="form-control" id="bandwidth"
																	name="bandwidth" placeholder="No. of e-Books"
																	value="" required>

															</div>
														</div>

														<div class="form-group">
															<label class="control-label col-sm-3" for="usingremot"
																style="text-align: left;"> Database : <span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-2">
																<input type="radio" id="database" name="database"
																	value="1" checked>Yes <input type="radio"
																	id="database" name="database" value="0">No
															</div>
															<div class="col-sm-2">No. of Database</div>
															<div class="col-sm-3">

																<input type="text" class="form-control" id="bandwidth"
																	name="bandwidth" placeholder="No. of Database"
																	value="" required>

															</div>
														</div>
														 -->
														<div class="form-group">
																<label class="control-label col-sm-4" for="noOfComp"
																	style="text-align: left;"> Avg. No.  of Teachers Using Library resources per day  <span class="text-danger">*</span>
																</label>
																<div class="col-sm-8">
																	<input type="text" class="form-control" id="avgTeacher"
																		name="avgTeacher" placeholder="" value="${libInfo.avgTeacher}"
																		pattern="^(?!\s*$).+" autocomplete="off" onkeypress='return restrictAlphabets(event)' required>
																</div>
															</div>
															
														<div class="form-group">
																<label class="control-label col-sm-4" for="noOfComp"
																	style="text-align: left;"> Avg. No. of Student Using Library resources per day  <span class="text-danger">*</span>
																</label>
																<div class="col-sm-8">
																	<input type="text" class="form-control" id="avgStud"
																		name="avgStud"
																		placeholder="" value="${libInfo.avgTeacher}"
																		pattern="^(?!\s*$).+" autocomplete="off" onkeypress='return restrictAlphabets(event)' required>
																</div>
															</div>


                                             <!--  <h4>e-Content Facilities</h4>
															
														 
														<div class="form-group">
															<label class="control-label col-sm-3" for="mediacenter"
																style="text-align: left;"> Media Center Available  <span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<input type="radio" id="mediacenter" name="mediacenter"
																	value="1" checked>Yes <input type="radio"
																	id="mediacenter" name="mediacenter" value="0">No
															</div>
															<div class="col-sm-2">Year of Establishment</div>
															<div class="col-sm-3">

																<input type="text" class="form-control datepicker" id="yearMediacenter"
																	name="yearMediacenter" placeholder="Membership Details"
																	value="" pattern="^(?!\s*$).+" autocomplete="off"
																	  onkeypress='return restrictAlphabets(event)' required>

															</div>
														</div>
														 -->
														
														<!-- <div class="form-group">
															<label class="control-label col-sm-3" for="recordingFacility"
																style="text-align: left;"> Recording Facility Available <span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<input type="radio" id="recordingFacility" name="recordingFacility"
																	value="1" checked>Yes <input type="radio"
																	id="recordingFacility" name="recordingFacility" value="0" pattern="^(?!\s*$).+" autocomplete="off"  required>No
															</div>
															<div class="col-sm-2">Year of Establishment </div>
															<div class="col-sm-3">

																<input type="text" class="form-control datepicker" id="yearRecordingFacility"
																	name="yearRecordingFacility" placeholder="Membership Details"
																	value="" pattern="^(?!\s*$).+" autocomplete="off"
																	onkeypress='return restrictAlphabets(event)'  required>

															</div>
														</div>
														<div class="form-group">
															<label class="control-label col-sm-3" for="lectuteCap"
																style="text-align: left;"> Lecture Capturing System  <span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<input type="radio" id="lectuteCap" name="lectuteCap"
																	value="1" checked>Yes <input type="radio"
																	id="lectuteCap" name="lectuteCap" value="0">No
															</div>
															<div class="col-sm-2">Year of Establishment </div>
															<div class="col-sm-3">

																<input type="text" class="form-control datepicker" id="yearLectuteCap"
																	name="yearLectuteCap" placeholder="Membership Details"
																	value="" pattern="^(?!\s*$).+" autocomplete="off"
																	onkeypress='return restrictAlphabets(event)'  required>

															</div>
														</div>
														<div class="form-group">
															<label class="control-label col-sm-3" for="mediacenter"
																style="text-align: left;"> Any other e-Content Development Facility 
(Please Specify) 
  <span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<input type="radio" id="eContentFacility" name="eContentFacility"
																	value="1" checked>Yes <input type="radio"
																	id="eContentFacility" name="eContentFacility" value="0">No
															</div>
															<div class="col-sm-2">Year of Establishment </div>
															<div class="col-sm-3">

																<input type="text" class="form-control datepicker" id="yearEContentFacility"
																	name="yearEContentFacility" placeholder="Membership Details"
																	value="" pattern="^(?!\s*$).+" autocomplete="off" 
																	onkeypress='return restrictAlphabets(event)' required>

															</div>
														</div> -->

														
														<!--   <h4>e-Governance Facilities</h4>
														
														<div class="form-group">
															<label class="control-label col-sm-3" for="planning"
																style="text-align: left;">Planning	  <span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<input type="radio" id="planning" name="planning"
																	value="1" checked>Yes <input type="radio"
																	id="planning" name="planning" value="0">No
															</div>
															<div class="col-sm-2">Year of Establishment </div>
															<div class="col-sm-3">

																<input type="text" class="form-control datepicker" id="yearPlanning"
																	name="yearPlanning" placeholder="Membership Details"
																	value="" pattern="^(?!\s*$).+" autocomplete="off" 
																	onkeypress='return restrictAlphabets(event)' required>

															</div>
														</div>
														
														
														<div class="form-group">
															<label class="control-label col-sm-3" for="recordingFacility"
																style="text-align: left;"> Administration  <span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<input type="radio" id="administration" name="administration"
																	value="1" checked>Yes <input type="radio"
																	id="recordingFacility" name="recordingFacility" value="0">No
															</div>
															<div class="col-sm-2">Year of Establishment </div>
															<div class="col-sm-3">

																<input type="text" class="form-control datepicker" id="yearAdministration"
																	name="yearAdministration" placeholder="Membership Details" 
																	value="" pattern="^(?!\s*$).+" autocomplete="off" 
																	onkeypress='return restrictAlphabets(event)' required>

															</div>
														</div>
														<div class="form-group">
															<label class="control-label col-sm-3" for="lectuteCap"
																style="text-align: left;">Finance & Accounts  <span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<input type="radio" id="finance" name="finance"
																	value="1" checked>Yes <input type="radio"
																	id="lectuteCap" name="lectuteCap" value="0">No
															</div>
															<div class="col-sm-2">Year of Establishment </div>
															<div class="col-sm-3">

																<input type="text" class="form-control datepicker" id="yearFinance"
																	name="yearFinance" placeholder="Membership Details"
																	value="" pattern="^(?!\s*$).+" autocomplete="off" 
																	onkeypress='return restrictAlphabets(event)' required>

															</div>
														</div>
														<div class="form-group">
															<label class="control-label col-sm-3" for="studAddmission"
																style="text-align: left;"> Student Admission & Support 
  <span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<input type="radio" id="studAddmission" name="studAddmission"
																	value="1" checked>Yes <input type="radio"
																	id="studAddmission" name="studAddmission" value="0">No
															</div>
															<div class="col-sm-2">Year of Establishment </div>
															<div class="col-sm-3">

																<input type="text" class="form-control datepicker" id="yearStudAddmission"
																	name="yearStudAddmission" placeholder="Membership Details"
																	value="" pattern="^(?!\s*$).+" autocomplete="off"  
																	onkeypress='return restrictAlphabets(event)' required>

															</div>
														</div>


														<div class="form-group">
															<label class="control-label col-sm-3" for="examinations"
																style="text-align: left;"> Examinations  
 																 <span class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<input type="radio" id="examinations" name="examinations"
																	value="1" checked>Yes <input type="radio"
																	id="eContentFacility" name="eContentFacility" value="0">No
															</div>
															<div class="col-sm-2">Year of Establishment </div>
															<div class="col-sm-3">

																<input type="text" class="form-control datepicker" id="yearExaminations"
																	name="yearExaminations" placeholder="Membership Details"
																	value="" pattern="^(?!\s*$).+" autocomplete="off"
																	onkeypress='return restrictAlphabets(event)'  required>

															</div>
														</div> -->


												
														<div class="form-group">
															<div class="col-sm-offset-2 col-sm-10">
																<input type="submit" id="sub1" class="btn btn-primary" onclick="submit_f(1)" value="Save">
																<input type="submit" id="sub2" class="btn btn-primary" onclick="submit_f(0)" value="Save &
																		Next">
																<button type="reset" class="btn btn-default">Reset</button>
																<input type="hidden" id="is_view" name="is_view" value="0">
															</div>
														</div>

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
	<script type="text/javascript">
		$(function() {

			$('.datepicker').datepicker({
				autoclose : true,
				format : "dd-mm-yyyy",
				changeYear : true,
				changeMonth : true

			});
		});
		
		function submit_f(view) {
			//alert(view);
			document.getElementById("is_view").value = view;

		}
	</script>
	<script type="text/javascript">
			/*code: 48-57 Numbers
			  8  - Backspace,
			  35 - home key, 36 - End key
			  37-40: Arrow keys, 46 - Delete key*/
			function restrictAlphabets(e){
				var x=e.which||e.keycode;
				if((x>=48 && x<=57) || x==8 ||
					(x>=35 && x<=40)|| x==46)
					return true;
				else
					return false;
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
		/* function showDiv(value) {

			var div = document.getElementById("divshow");

			if (value == 1) {
				div.style.display = "block";
			} else {
				div.style.display = "none";
			}
		} */
	</script>
	<script type="text/javascript">
		jQuery(document).ready(
				function($) {

					// sub_menu
					$('#sub_menu1').click(function(e) {
						//ajax send this to php page
						var def = 1;
						if ($("#sub_menu").prop('checked') == true) {
							$('#main_menu').prop('checked', true);

						} else {

						}
					});

					//Example 2

					$(document).on('click', '#close-preview', function() {
						$('.image-preview').popover('hide');
						// Hover befor close the preview
						$('.image-preview').hover(function() {
							$('.image-preview').popover('show');
						}, function() {
							$('.image-preview').popover('hide');
						});
					});

					$(function() {
						// Create the close button
						var closebtn = $('<button/>', {
							type : "button",
							text : 'x',
							id : 'close-preview',
							style : 'font-size: initial;',
						});
						closebtn.attr("class", "close pull-right");
						// Set the popover default content
						$('.image-preview').popover(
								{
									trigger : 'manual',
									html : true,
									title : "<strong>Preview</strong>"
											+ $(closebtn)[0].outerHTML,
									content : "There's no image",
									placement : 'bottom'
								});
						// Clear event
						$('.image-preview-clear').click(function() {
							//$('.image-preview').attr("data-content","").popover('hide');
							$('.image-preview-filename').val("");
							$('.image-preview-clear').hide();
							$('#header_image input:file').val("");
							$(".image-preview-input-title").text("Browse");
							$("#thumbkishore").attr("src", '');
							$(".thumbkishorediv").hide();
						});
						// Create the preview image
						$("#header_image").change(
								function() {
									var img = $('<img/>', {
										id : 'dynamic',
										width : 250,
										height : 200,
									});

									var file = this.files[0];
									var reader = new FileReader();
									// Set preview image into the popover data-content
									reader.onload = function(e) {

										$(".image-preview-input-title").text(
												"Change");
										$(".image-preview-clear").show();
										$(".image-preview-filename").val(
												file.name);
										img.attr('src', e.target.result);
										//alert(e.target.result);
										///alert($(img)[0].outerHTML);
										//$(".image-preview").attr("data-content",$(img)[0].outerHTML).popover("show");
										//
										//alert(e.target.result);
										$("#thumbkishore").attr("src",
												e.target.result);

										$(".thumbkishorediv").show();
									}
									reader.readAsDataURL(file);
								});

						// Clear event
						$('.image-preview-clear2').click(function() {
							//$('.image-preview').attr("data-content","").popover('hide');
							$('.image-preview-filename2').val("");
							$('.image-preview-clear2').hide();
							$('.image-preview-input2 input:file').val("");
							$(".image-preview-input-title2").text("Browse");
							$("#thumbkishor2e").attr("src", '');
							$(".thumbkishore2div").hide();
						});
						// Create the preview image
						$("#images").change(
								function() {
									var img = $('<img/>', {
										id : 'dynamic',
										width : 250,
										height : 200,
									});

									var file = this.files[0];
									var reader = new FileReader();
									// Set preview image into the popover data-content
									reader.onload = function(e) {

										$(".image-preview-input-title2").text(
												"Change");
										$(".image-preview-clear2").show();
										$(".image-preview-filename2").val(
												file.name);
										img.attr('src', e.target.result);
										//alert(e.target.result);
										///alert($(img)[0].outerHTML);
										//$(".image-preview").attr("data-content",$(img)[0].outerHTML).popover("show");
										//
										//alert(e.target.result);
										$("#thumbkishore2").attr("src",
												e.target.result);

										$(".thumbkishore2div").show();
									}
									reader.readAsDataURL(file);
								});

					});

				});
	</script>

</body>
</html>