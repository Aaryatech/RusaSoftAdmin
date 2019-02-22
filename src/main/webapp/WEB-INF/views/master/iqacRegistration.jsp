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
							<h1 class="title">IQAC Registration</h1>
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
								<%-- <a href="${pageContext.request.contextPath}/iqacList"><button
										type="button" class="btn btn-info">< Back</button></a --%>> <a
									class="box_toggle fa fa-chevron-down"></a>
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertCmsForm"
										method="post" enctype="multipart/form-data"
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
																:<span class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<input type="text" class="form-control" id="iqacName"
																	name="iqacName" placeholder="Name"
																	value="${page.pageName}" required>
															</div>
														</div>


														<div class="form-group">
															<label class="control-label col-sm-2" for="status">Select
																Designation : <span class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<select id="designation" name="designation"
																	class="form-control" required>
																	<option value="Assistant Professor">Assistant Professor</option>
																	<option value="Associate Professor">Associate Professor</option>
																	<option value="Professor">Professor</option>
																	<option value="Registrar">Registrar</option>
																	<option value="-">Any Other</option>
																</select>
															</div>
														</div>

														<div class="form-group">
															<label class="control-label col-sm-2" for="heading1">Date
																Of Joining : <span class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<input type="date" class="form-control" id="dateOfJoin"
																	name="dateOfJoin" placeholder="Date Of Joining"
																	value="" required>
															</div>
														</div>

														<div class="form-group">
															<label class="control-label col-sm-2" for="smallheading">Contact
																No. : <span class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<input type="text" class="form-control" id="contactNo"
																	name="contactNo" pattern="[7-9]{1}[0-9]{9}"
																	title="Phone number with 7-9 and remaing 9 digit with 0-9"
																	placeholder="Mobile Number" value="" required>
															</div>
														</div>

														<div class="form-group">
															<label class="control-label col-sm-2" for="smallheading">Email ID
																: <span class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<input type="email" class="form-control" id="email"
																	name="email" placeholder="abc@xyz.com" value="" required>
															</div>
														</div>

														<div class="form-group">
															<div class="col-sm-offset-2 col-sm-10">
															
															<a href="#myModal2"
														data-toggle="modal"><button type="submit" onclick="getCOPO()"
																class="btn btn-info">Confirm</button></a>
																<button type="submit" class="btn btn-primary">Submit</button>
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
					<%-- <form role="form"
						action="${pageContext.request.contextPath}/showModuleForm"
						method="get"> --%>
						<input type="hidden" class="form-control" id="pageId"
							name="pageId" >
						
								
								
						<div class="form-group">
						
							<label class="control-label col-sm-3" for="page_name">IQAC I/C Name
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="iqacName1"
									name="iqacName1" placeholder="" value="${page.pageName}" readonly
									>
							<!-- </div> -->
	</div>
					
					<div class="form-group">
						
							<label class="control-label col-sm-3" for="page_name">Designation : 
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="designation1" readonly
									name="designation1" placeholder="" value="${page.pageName}"
									>
							<!-- </div> -->
	</div>
					
					<div class="form-group">
						
							<label class="control-label col-sm-3" for="page_name">Date
				              Of Joining : : 
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="dateOfJoin1" readonly
									name="dateOfJoin1" placeholder="" value="${page.pageName}"
									>
							<!-- </div> -->
	</div>
	
	
	
	
					<div class="form-group">
						
							<label class="control-label col-sm-3" for="page_name">Contact 
									No :
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="contactNo1" readonly
									name="contactNo1" placeholder="" value="${page.pageName}"
									>
							<!-- </div> -->
	</div>
	
	<div class="form-group">
						
							<label class="control-label col-sm-3" for="page_name">Email
									
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="email1" readonly
									name="email1" placeholder="" value="${page.pageName}"
									>
							<!-- </div> -->
	</div>
					</div>
	
	
						<!-- <button type="submit" class="btn btn-primary" onclick="getOpt()">Confirm</button> -->
				<!-- 	</form> -->
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
			
			
			document.getElementById("iqacName1").value=iqacName;
			
			document.getElementById("designation1").value=designation;
			document.getElementById("dateOfJoin1").value=dateOfJoin;
			document.getElementById("contactNo1").value=contactNo;
			document.getElementById("email1").value=email;
			
			
			
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