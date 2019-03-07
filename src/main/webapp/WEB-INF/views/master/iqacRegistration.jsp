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
						<!-- 	<h1 class="title">IQAC Registration</h1> -->
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
						<h4>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Institute Name:abc</h4>
							<h2 class="title pull-left">IQAC Registration Form</h2>

							<div class="actions panel_actions pull-right">
								<a href="${pageContext.request.contextPath}/showIqacList"><button
										type="button" class="btn btn-info">< Back</button></a><a
									class="box_toggle fa fa-chevron-down"></a>
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/#"
										method="post"
										name="form_sample_2" id="form_sample_2"
										onsubmit="return confirm('Do you really want to submit the form?');">

										<ul class="nav nav-tabs">
											<li class="active"><a href="#home" data-toggle="tab">
													<i class="fa fa-home"></i> Register IQAC 
											</a></li>

										</ul>

										<div class="tab-content">
											<div class="tab-pane fade in active" id="home">

												<div>
																								
														<div class="form-group">
															<label class="control-label col-sm-2" for="page_name">IQAC I/C Name
																<span class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<input type="text" class="form-control" id="iqacName"
																	name="iqacName" placeholder="Name"
																	value="${miqc.iqacName}" required>
															</div>
														</div>


														<div class="form-group">
															<label class="control-label col-sm-2" for="status">
																Designation <span class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<select id="designation" name="designation"
																	class="form-control"  onchange="showForm()" required>
																
																<c:forEach items="${desigList}" var="makeList"> 
																	<c:choose>
																			<c:when test="${makeList.designationId == miqc.desgntnId}">
																				<option value="${makeList.designationId}" selected="selected">${makeList.designationName}</option>
																			</c:when>
																		<c:otherwise><option value="${makeList.designationId}">${makeList.designationName}</option></c:otherwise>
																	</c:choose>
																 </c:forEach>
																
																
																
																	<!-- <option value="1">Assistant Professor</option>
																	<option value="2">Associate Professor</option>
																	<option value="3">Professor</option>
																	<option value="4">Registrar</option>
																	<option value="7">Any Other</option> -->
																</select>
															</div>
														</div>
														
														
														<div class="form-group" id="abc">
															<label class="control-label col-sm-2" for="smallheading">Other Designation
														  <span class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<input type="text" class="form-control" id="desn"
																	name="desn" 
																	
																	placeholder="Other Designation" value="" required>
															</div>
														</div>
														

														<div class="form-group">
															<label class="control-label col-sm-2" for="heading1">Date
																Of Joining  <span class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<input type="date" class="form-control" id="dateOfJoin"
																	name="dateOfJoin" placeholder="Date Of Joining"
																	value="${miqc.joiningDate}" required>
															</div>
														</div>

														<div class="form-group">
															<label class="control-label col-sm-2" for="smallheading">Contact
																No.  <span class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<input type="text" class="form-control" id="contactNo"
																	name="contactNo" pattern="[7-9]{1}[0-9]{9}"
																	title="Phone number with 7-9 and remaing 9 digit with 0-9"
																	placeholder="Mobile Number" value="${miqc.contactNo}" required>
															</div>
														</div>

														<div class="form-group">
															<label class="control-label col-sm-2" for="smallheading">Email ID(Official)
																 <span class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<input type="email" class="form-control" id="email"
																	name="email" placeholder="abc@xyz.com" value="${miqc.email}" required>
															</div>
														</div>

														<div class="form-group">
															<div class="col-sm-offset-2 col-sm-10">
															
															<!-- 
															<button type="submit" 
																class="btn btn-info" onclick="getCOPO()">Submit</button>
															 -->
															 <a href="#myModal2"
														data-toggle="modal"><button type="submit" onclick="getCOPO()"
																class="btn btn-info">Submit</button></a>
															
																<button type="reset" class="btn btn-default">Reset</button>
															</div>
														</div>

												


													

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
	
	
	
	<div aria-hidden="true" role="dialog" tabindex="-1" id="myModal2"
		class="modal fade" style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button aria-hidden="true" data-dismiss="modal" class="close"
						type="button">×</button>
					<h4 class="modal-title">IQAC Detail Comfirmation</h4>
				</div>
				<div class="modal-body">
				<form role="form"
						action="${pageContext.request.contextPath}/iqacNewRegistration"
						method="post">
						<input type="hidden" class="form-control" id="pageId"
							name="pageId" >
								
									<!-- <div class="form-group">
						
							<label class="control-label col-sm-6" for="page_name">Contact No :
							</label>
							<label  id="trustCon" for="page_name"> 
							</label>
						</div>	 -->
						
						<input type="hidden" class="form-control" id="iqac_id"
								name="iqac_id" value="${miqc.iqacId}">
						
				
				
						<div class="form-group">
						
							<label class="control-label col-sm-6" for="page_name">IQAC I/C Name:
							</label>
						
								<label  id="iqacName1" for="page_name">
								
							</label>
								 <input type="hidden" id="iqacName2" name="iqacName2">
							</div>
					
					<div class="form-group">
						
							<label class="control-label col-sm-6" for="page_name">Designation : 
							</label>
							
							<label  id="designtion1" for="page_name"> 
							
							</label>
							<input type="hidden" id="designtion2" name="designtion2">
	</div>
					
					<div class="form-group">
						
							<label class="control-label col-sm-6" for="page_name">Date
				              Of Joining : 
							</label>
							<label  id="date1" for="page_name"> 
							
							</label>
							<input type="hidden" id="date2" name="date2">
	</div>
	
	
	
	
					<div class="form-group">
						
							<label class="control-label col-sm-6" for="page_name">Contact 
									No :
							</label>
							<label  id="conNumber1" for="page_name"> 
							
							</label>
							<input type="hidden" id="conNumber2" name="conNumber2">
	</div>
	
	<div class="form-group">
						
							<label class="control-label col-sm-6" for="page_name">Email ID(Official)
									
							</label>
							<label  id="emailId1" for="page_name"> 
	
							</label>
							<input type="hidden" id="emailId2" name="emailId2">
	</div>
					
	
	
				 <button type="submit" class="btn btn-primary" onclick="getOpt()">Confirm</button>
				 	</form>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
	
	<script type="text/javascript">
	
	function getCOPO() {
		//alert("hii");
			
			var iqacName=document.getElementById("iqacName").value
			var designation=document.getElementById("designation").value
			var dateOfJoin=document.getElementById("dateOfJoin").value
			var contactNo=document.getElementById("contactNo").value
			var email=document.getElementById("email").value
			
				
			$('#designtion1').html(designation);
			document.getElementById("designtion2").value=designation;
			
			$('#iqacName1').html(iqacName);
			document.getElementById("iqacName2").value=iqacName;
			
			$('#date1').html(dateOfJoin);
			document.getElementById("date2").value=dateOfJoin;
			
			$('#conNumber1').html(contactNo);
			document.getElementById("conNumber2").value=contactNo;
			
			$('#emailId1').html(email);
			document.getElementById("emailId2").value=email;
			
		}

	
	
	
	function showForm() {
		//document.getElementById("abc").style = "display:none"
			var qualType=document.getElementById("designation").value
			//alert("qualType::"+qualType);
			
			if (qualType == 7) {

				document.getElementById("abc").style = "visible"
				
					
			} 
			else{
				document.getElementById("abc").style = "display:none"
			}
		
		}
	function hideText() {
		//alert("hii");
		document.getElementById("abc").style = "display:none"
			
		
		}
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