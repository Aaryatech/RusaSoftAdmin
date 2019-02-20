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
<body class=" " >
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
								<a href="${pageContext.request.contextPath}/publicationList"><button
										type="button" class="btn btn-info">Back</button></a> <a
									class="box_toggle fa fa-chevron-down"></a>
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertPublicationDetail"
										method="post" enctype="multipart/form-data"
										name="form_sample_2" id="form_sample_2"
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
															<label class="control-label col-sm-2" for="page_order">M.phill/Ph.D.Guide
																:
															</label>


															<div class="col-sm-2">
																Yes <input type="radio" name="mPhill" id="mPhill"
																	checked value="0"> No<input type="radio"
																	name="mPhill" id="mPhill" value="1">
															</div>
                                                           </div>


														<div class="form-group">
															<label class="control-label col-sm-2" for="smallheading">Date
																of Recognition : <span class="text-danger">*</span>
															</label>
															<div class="col-sm-2">
																<input type="date" class="form-control" id="dor"
																	name="dor" placeholder="Date of Recognition" value=""
																	required>
															</div>


															<label class="control-label col-sm-2" for="smallheading">Valid
																up to : <span class="text-danger">*</span>
															</label>
															<div class="col-sm-2">
																<input type="date" class="form-control" id="dor"
																	name="dor" placeholder="Valid up to" value="" required>
															</div>


														</div>


														<div class="form-group">
															<label class="control-label col-sm-2" for="page_name">No.
																of Students Guided :<span class="text-danger">*</span>
																
																
															</label> <label class="control-label col-sm-1" for="page_name">PG
																:<span class="text-danger">*</span>
															</label>
															<div class="col-sm-2">
																<input type="text" class="form-control"
																	id="qualification" name="qualification"
																	placeholder="PG" value="${page.pageName}" required>
															</div>

															<label class="control-label col-sm-2" for="page_name">M.Phill
																:<span class="text-danger">*</span>
															</label>
															<div class="col-sm-2">
																<input type="text" class="form-control"
																	id="qualification" name="qualification"
																	placeholder="M.Phill" value="${page.pageName}" required>
															</div>



															<label class="control-label col-sm-1" for="page_name">Ph.D.
																:<span class="text-danger">*</span>
															</label>
															<div class="col-sm-2">
																<input type="text" class="form-control" id="phdNo"
																	name="phdNo" placeholder="Ph.D"
																	value="${page.pageName}" required>
															</div>
														</div>
														
														
														
														
														<div class="form-group">
															<label class="control-label col-sm-4" for="page_order">Use
																of ICT(Information Communication Technology) :<span
																class="text-danger">*</span>
															</label>


															<div class="col-sm-2">
																Yes <input type="radio" name="isReg" id="isReg" checked
																	value="0"> No<input type="radio" name="isReg"
																	id="isReg" value="1">
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
	
	
	
<div aria-hidden="true" role="dialog" tabindex="-1" id="myModal"
		class="modal fade" style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button aria-hidden="true" data-dismiss="modal" class="close"
						type="button">×</button>
					<h4 class="modal-title">Academic Details</h4>
				</div>
				<div class="modal-body">
					<form role="form"
						action="${pageContext.request.contextPath}/showModuleForm"
						method="get">
						<input type="hidden" class="form-control" id="pageId"
							name="pageId" >
							
									<div class="form-group">
							<label for="modalname1" class="form-label">Qualification</label> <select
								id="qualType" name="qualType" class="form-control" onchange="showForm()" required>
								<option value="0">Diploma</option>
								<option value="1">Bachelors</option>
								<option value="3">Masters</option>
								<option value="4">Doctorates</option>
							<option value="5">Post Doctorates</option>
								<option value="6">M.Phill/Ph.D. Guide</option>
								
								<option value="7">Any Other</option>



							</select>
						</div>
						
						
						<div class="form-group">
						
							<label class="control-label col-sm-3" for="page_name">Other Qualification
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="qualName"
									name="qualName" placeholder="No." value="${page.pageName}"
									required>
							<!-- </div> -->
	</div>
						
						<div class="form-group">
						
							<label class="control-label col-sm-1" for="page_name">Class
							</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" id="hodName"
									name="hodName" placeholder="No." value="${page.pageName}"
									required>
							</div>
	</div>
	<div class="form-group">
							<label class="control-label col-sm-2" for="page_name">Year of Passing
							</label>
							<div class="col-sm-3">
								<input type="date" class="form-control"
								id="curExp" name="curExp" value="" required>
							</div>

</div>


					


						<button type="submit" class="btn btn-primary">Submit</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	


	<script>
	function showForm() {
	document.getElementById("abc").style = "display:none"
		var index=document.getElementById("qualType").value
		

		if (index == 6) {

			document.getElementById("abc").style = "visible"

		} 
	
	}
	function showForm1() {
		document.getElementById("abc").style = "display:none"
			
		
		}
	
	</script>
	
	
	

</body>
</html>