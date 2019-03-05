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
							<h1 class="title">${title}</h1>
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
								<a href="${pageContext.request.contextPath}/hodList"><button
										type="button" class="btn btn-info"><< Back</button></a> <a
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
													<i class="fa fa-home"></i> ${title}
											</a></li>

										</ul>

										<div class="tab-content">
											<div class="tab-pane fade in active" id="home">

												<div>


													<div class="col-xs-12">

														
													

														  <h4>Evaluation Process & Reforms</h4>
														
														<div class="form-group">
															<label class="control-label col-sm-3" for="planning"
																style="text-align: left;">Internal Assessment is transparent 	 : <span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<input type="radio" id="internalAsses" name="internalAsses"
																	value="1" checked>Yes <input type="radio"
																	id="planning" name="planning" value="0">No
															</div>
															<div class="col-sm-2"> Frequency of assessment:</div>
															<div class="col-sm-3">

																<input type="text" class="form-control" id="assesFreq"
																	name="assesFreq" placeholder=" Frequency of assessment"
																	value="" required>

															</div>
														</div>
														
															
														<div class="form-group">
															<label class="control-label col-sm-3" for="planning"
																style="text-align: left;">Institute follows academic calendar :

                                                                   <span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<input type="radio" id="copoMapping" name="copoMapping"
																	value="1" checked>Yes <input type="radio"
																	id="planning" name="planning" value="0">No
															</div>
															
															
															
															<label class="control-label col-sm-3" for="planning"
																style="text-align: left;">Institute adheres to academic calendar :  

                                                                   <span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<input type="radio" id="copoMapping" name="copoMapping"
																	value="1" checked>Yes <input type="radio"
																	id="planning" name="planning" value="0">No
															</div>
														</div>
														
														
														
														<h4>Program Outcomes (POs) , PRogram Specific Outcomes (PSOs), Course Outcomes (COs)</h4>
														
														<div class="form-group">
															<label class="control-label col-sm-8" for="planning"
																style="text-align: left;">Program Outcomes (POs) , PRogram Specific Outcomes (PSOs), Course Outcomes (COs) 
evaluated by Institute:
 <span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<input type="radio" id="poEval" name="poEval"
																	value="1" checked>Yes <input type="radio"
																	id="planning" name="planning" value="0">No
															</div>
															
														</div>
														
														
														
														<div class="form-group">
															<label class="control-label col-sm-3" for="planning"
																style="text-align: left;">Mapping of CO-PO done

                                                                   <span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<input type="radio" id="copoMapping" name="copoMapping"
																	value="1" checked>Yes <input type="radio"
																	id="planning" name="planning" value="0">No
															</div>
															<label class="control-label col-sm-3" for="planning"
																style="text-align: left;">Mapping of CO-PSO done

                                                                   <span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<input type="radio" id="copsoMapping" name="copsoMapping"
																	value="1" checked>Yes <input type="radio"
																	id="planning" name="planning" value="0">No
															</div>
														</div>
														
														
															
														
														
															
														<div class="form-group">
															<label class="control-label col-sm-6" for="planning"
																style="text-align: left;">Teaching Plan / Laboratory Plan available :

                                                                   <span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<input type="radio" id="teachingPlan" name="teachingPlan"
																	value="1" checked>Yes <input type="radio"
																	id="planning" name="planning" value="0">No
															</div>
															
														</div>
														
														
																<h4> Human Values and Professional Ethics</h4>
														
														<div class="form-group">
															<label class="control-label col-sm-3" for="planning"
																style="text-align: left;">Code of conduct for students

 <span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<input type="radio" id="poEval" name="poEval"
																	value="1" checked>Yes <input type="radio"
																	id="planning" name="planning" value="0">No
															</div>
															
															<label class="control-label col-sm-3" for="planning"
																style="text-align: left;">Code of conduct for staff

 <span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<input type="radio" id="poEval" name="poEval"
																	value="1" checked>Yes <input type="radio"
																	id="planning" name="planning" value="0">No
															</div>
															
														</div>
														
														
														
														<div class="form-group">
															<label class="control-label col-sm-3" for="planning"
																style="text-align: left;">Code of conduct for governing body

                                                                   <span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<input type="radio" id="copoMapping" name="copoMapping"
																	value="1" checked>Yes <input type="radio"
																	id="planning" name="planning" value="0">No
															</div>
															<label class="control-label col-sm-3" for="planning"
																style="text-align: left;">Code of conduct for Administration

                                                                   <span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<input type="radio" id="copsoMapping" name="copsoMapping"
																	value="1" checked>Yes <input type="radio"
																	id="planning" name="planning" value="0">No
															</div>
														</div>
														
															
														<div class="form-group">
															<label class="control-label col-sm-3" for="planning"
																style="text-align: left;">Display of core values on websites and in the
                                                      Institute for the stake holders 


                                                                   <span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<input type="radio" id="copoMapping" name="copoMapping"
																	value="1" checked>Yes <input type="radio"
																	id="planning" name="planning" value="0">No
															</div>
															
															<label class="control-label col-sm-3" for="planning"
																style="text-align: left;">Organization of national festivals

 <span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<input type="radio" id="poEval" name="poEval"
																	value="1" checked>Yes <input type="radio"
																	id="planning" name="planning" value="0">No
															</div>
															
															
															
														</div>
														
														<div class="form-group">
															<div class="col-sm-offset-2 col-sm-10">
																<button type="submit" class="btn btn-primary">Submit</button>
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
		function showDiv(value) {

			var div = document.getElementById("divshow");

			if (value == 1) {
				div.style.display = "block";
			} else {
				div.style.display = "none";
			}
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