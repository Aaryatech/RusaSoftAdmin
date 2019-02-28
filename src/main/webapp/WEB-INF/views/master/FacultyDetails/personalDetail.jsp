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
							<%-- 	<a href="${pageContext.request.contextPath}/publicationList"><button
										type="button" class="btn btn-info">Back</button></a>  --%><a
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

														<h5 class="title pull-left">
															<strong>Personal Details</strong>
														</h5>

														<div class="col-xs-12"></div>





														<div class="form-group">
															<label class="control-label col-sm-2" for="status">Select
																Salutation : <span class="text-danger">*</span>
															</label>
															<div class="col-sm-1">
																<select id="salutation" name="salutation"
																	class="form-control" required>
																	<option value="0">Mr</option>
																	<option value="1">Miss</option>
																	<option value="2">Mrs</option>


																</select>
															</div>

															<label class="control-label col-sm-1" for="page_name">
																Name:<span class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<input type="text" class="form-control" id="firstName"
																	name="firstName" placeholder="First Name"
																	value="${page.pageName}" required>
															</div>
															<div class="col-sm-2">
																<input type="text" class="form-control" id="middleName"
																	name="middleName" placeholder="Middle Name"
																	value="${page.pageName}" required>
															</div>
															<div class="col-sm-3">
																<input type="text" class="form-control" id="lastName"
																	name="lastName" placeholder="Last Name"
																	value="${page.pageName}" required>
															</div>
														</div>

														<div class="form-group">
															<label class="control-label col-sm-2" for="page_name">Address
																:<span class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<input type="text" class="form-control" id="address"
																	name="address" placeholder="Permanent Address"
																	value="${page.pageName}" required>
															</div>
														</div>
														
														
															<div class="form-group">
																<label class="control-label col-sm-6" for="page_order">Is 
																	Permanent and Correspondence Address Same :<span class="text-danger">*</span>
																</label>


																<div class="col-sm-3">
																	Yes <input type="radio" name="cat" id="cat"
																		checked value="1" onclick="check(this.value)"> No<input
																		type="radio"  onclick="check(this.value)" name="cat" id="cat" value="0">
																</div>
</div>


	<div class="form-group" id="abc">
															<label class="control-label col-sm-2" for="page_name">Correspondence Address
																:<span class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<input type="text" class="form-control" id="address"
																	name="address" placeholder="Correspondence Address"
																	value="${page.pageName}" required>
															</div>
														</div>
														
														<div class="form-group">
															<label class="control-label col-sm-2" for="smallheading">Mobile
																No. : <span class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<input type="text" class="form-control" id="contactNo"
																	name="contactNo" pattern="[7-9]{1}[0-9]{9}"
																	title="Phone number with 7-9 and remaing 9 digit with 0-9"
																	placeholder="Mobile No" value="" required>
															</div>
															<div class="col-sm-2">
															</div>

															<label class="control-label col-sm-2" for="smallheading">Phone No
																: <span class="text-danger">*</span>
															</label>

															<div class="col-sm-3">
																<input type="text" class="form-control" id="mobileNo"
																	name="mobileNo" 
																	title="Phone number with 7-9 and remaing 9 digit with 0-9"
																	placeholder="Landline No" value="" required>
															</div>


														</div>

														<div class="form-group">
														
														
															<label class="control-label col-sm-2" for="smallheading">Resident:
																 </label>
														
															<div class="col-sm-3">
																<input type="text" class="form-control" id="residentNo"
																	name="residentNo" pattern="[7-9]{1}[0-9]{9}"
																	title="Phone number with 7-9 and remaing 9 digit with 0-9"
																	placeholder="Resident No" value="" required>
															</div>
															<div class="col-sm-2">
															</div>
															
															<label class="control-label col-sm-2" for="smallheading">Email ID:
																: <span class="text-danger">*</span>
															</label>

															<div class="col-sm-3">
																<input type="text" class="form-control" id="mobileNo"
																	name="mobileNo" 
																	title="Phone number with 7-9 and remaing 9 digit with 0-9"
																	placeholder="abc@xyz.com" value="" required>
															</div>


															
															
														</div>

														<div class="form-group">
														
														<label class="control-label col-sm-2" for="smallheading">PAN
																No : <span class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<input type="text" class="form-control" id="panNo"
																	name="panNo" placeholder="PAN No" value="" required>
															</div>
														<div class="col-sm-2">
															</div>
														
														<label class="control-label col-sm-2" for="smallheading">Aadhar
																No: <span class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<input type="Text" class="form-control" id="adharNo"
																	name="adharNo" placeholder="Aadhar No" value=""
																	required>
															</div>
															</div>
															
															<div class="form-group">
															<label class="control-label col-sm-2" for="status">Select
																Designation : <span class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<select id="designation" name="designation"
																	class="form-control" required>
																	<option value="0">Assistant Professor</option>
																	<option value="1">Associate Professor</option>
																	<option value="2">Professor</option>
																	<option value="3">Registrar</option>
																		<option value="4">Reader</option>
																	<option value="5">Any Other</option>
																</select>
															</div>
															<div class="col-sm-2">
															</div>
															
															<label class="control-label col-sm-2" for="smallheading">Date
																of Birth : <span class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<input type="date" class="form-control" id="dob"
																	name="dob" placeholder="DOB" value="" required>
															</div>

															
														</div>

														<div class="form-group">
														<label class="control-label col-sm-2" for="smallheading">Date
																of Joining : <span class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<input type="date" class="form-control" id="doj" onchange="calExp()"
																	name="doj" placeholder="Date of Joining" value=""
																	required>
															</div>
<div class="col-sm-2">
															</div>

															<label class="control-label col-sm-2" for="smallheading">Previous
																Experience : <span class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<input type="text" class="form-control" id="prevExp"
																	name="prevExp" placeholder="Previous Experience"  onchange="calExp()"
																	value="" required>
															</div>

															
																</div>
															
															
														<div class="form-group">
														
														<label class="control-label col-sm-2" for="smallheading">Total 
																Experience : <span class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
															<!-- 	<input type="text" class="form-control" id="curExp"
																	name="curExp" placeholder="Current Experience" value=""
																	required> -->
																		<label class="control-label col-sm-2" for="smallheading">10 
																
															</label>
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
		
	
	}
	
	
	
function calExp(){
	var d = new Date();

	var month = d.getMonth()+1;
	var day = d.getDate();

	var output = d.getFullYear() + '/' +
	    (month<10 ? '0' : '') + month + '/' +
	    (day<10 ? '0' : '') + day;
	
	//alert("date is"+output);
	
	var joinDate=document.getElementById("doj").value;
	////alert("date of join"+joinDate);
	
}



</script>

</body>
</html>