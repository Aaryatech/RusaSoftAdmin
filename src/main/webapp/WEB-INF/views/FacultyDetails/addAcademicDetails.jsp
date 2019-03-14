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
<%-- 
				<div class="col-xs-12">
					<div class="page-title">

						<div class="pull-left">
							<!-- PAGE HEADING TAG - START -->
							<h1 class="title">${title}</h1>
							<!-- PAGE HEADING TAG - END -->
						</div>


					</div>
				</div> --%>
				<div class="clearfix"></div>
				<!-- MAIN CONTENT AREA STARTS -->

				<div class="col-lg-12"></div>



				<div class="col-lg-12">
					<section class="box ">

						<header class="panel_header">
							<h2 class="title pull-left">${title}</h2>

							<div class="actions panel_actions pull-right">
								<a href="${pageContext.request.contextPath}/showAcademicDetails"><button
										type="button" class="btn btn-info">Back</button></a> 
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

														

														<div class="col-xs-12"></div>





													
															<div class="form-group">
															<label class="control-label col-sm-2" for="status">Select
																Qualification  <span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<select id="designation" name="designation" 
																	class="form-control" required>
																	
																</select>
															</div>
															<div class="col-sm-2">
															</div>
															</div>
															
															
															<div class="form-group">
															<label class="control-label col-sm-2" for="status">Select
																Class  <span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<select id="designation" name="designation" 
																	class="form-control" required>
																	<option value="Distinction">Distinction</option>
																	<option value="First">First</option>
							                                           <option value="Second">Second</option>
						                                           	<option value="Pass">Pass </option>
																</select>
															</div>
															<div class="col-sm-2">
															</div>
															</div>
															
															
													
														
														<div class="form-group">
															<label class="control-label col-sm-2" for="smallheading">University
																  <span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="text" class="form-control" id="prevExp"
																	name="prevExp" placeholder="Previous Experience"  onchange="calExp()"
																	value="" required>
															</div>

															
																</div>
																
																
																<div class="form-group">
															<label class="control-label col-sm-2" for="smallheading">City
																  <span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="text" class="form-control" id="prevExp"
																	name="prevExp" placeholder="Previous Experience"  onchange="calExp()"
																	value="" required>
															</div>

															
																</div>
															
															
																
																<div class="form-group">
															<label class="control-label col-sm-2" for="smallheading">Year of Passing
																  <span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="text" class="form-control" id="prevExp"
																	name="prevExp" placeholder="Previous Experience"  onchange="calExp()"
																	value="" required>
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


function check(qualType) {
	//document.getElementById("abc").style = "display:none"
		//var qualType=document.getElementById("cat").value
		//alert("qualType::"+qualType);
		
		if (qualType == 0) {

			document.getElementById("abc").style = "visible"
			
				
		} 
		else if(qualType == 1){
			document.getElementById("abc").style = "display:none"
		}
	
	}
function hideText() {
	//alert("hii");
	document.getElementById("abc").style = "display:none"
		document.getElementById("xyz").style = "display:none"
		
	
	}
	
function showForm() {
	//document.getElementById("abc").style = "display:none"
		var qualType=document.getElementById("designation").value
		//alert("qualType::"+qualType);
		
		if (qualType == 5) {

			document.getElementById("xyz").style = "visible"
			
				
		} 
		else if(qualType == 1){
			document.getElementById("xyz").style = "display:none"
		}
	
	}
	
function calExp(){
	var d = new Date();

	var month = d.getMonth()+1;
	var day = d.getDate();

	var output = d.getFullYear() + '/' +
	    (month<10 ? '0' : '') + month + '/' +
	    (day<10 ? '0' : '') + day;
	
	alert("date is"+output);
	
	var joinDate=document.getElementById("doj").value;
	alert("date of join"+joinDate);
	
	//date1: 24/09/2015 (24th Sept 2015)
	//date2: 09/11/2015 (9th Nov 2015)
	//the difference: 2.5 (months)
	
	
	var difference = (date2.getDate() - date1.getDate()) / 30 +
    date2.getMonth() - date1.getMonth() +
    (12 * (date2.getFullYear() - date1.getFullYear()));
	
}



</script>

</body>
</html>