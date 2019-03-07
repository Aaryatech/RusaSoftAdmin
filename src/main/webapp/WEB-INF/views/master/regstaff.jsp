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
							<h4>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Institute Name:abc&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							Department Name:xyz</h4>
							<h2 class="title pull-left">${title}</h2>

							<div class="actions panel_actions pull-right">
								<a href="${pageContext.request.contextPath}/showStaffList"><button
										type="button" class="btn btn-info">Back</button></a> <a
									class="box_toggle fa fa-chevron-down"></a>
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/addFaculty"
										method="post" 
										name="form_sample_2" id="form_sample_2">

										<ul class="nav nav-tabs">
											<li class="active"><a href="#home" data-toggle="tab">
													<i class="fa fa-home"></i> Register Faculty
											</a></li>


										</ul>

										<div class="tab-content">
											<div class="tab-pane fade in active" id="home">

												<div>



													<div class="col-xs-12">
													
													<%-- <div class="form-group">
													<label class="control-label col-sm-2" for="status">
													Year : <span class="text-danger">*</span>
												</label>
												<div class="col-sm-2">
													<select id="salutation" name="salutation"
														class="form-control" required>
														<option value="0">2018-2019</option>
														<option value="1">2017-2018</option>
														<option value="2">2016-2017</option>
														<option value="3">2015-2016</option>


													</select>
                                       </div>
										</div>			
													
													<div class="form-group">
															<label class="control-label col-sm-2" for="page_name">No. of Faculty
																Required <span class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<input type="text" class="form-control" id="page_name"
																	name="page_name" placeholder="No. of Faculty Required "
																
																	value="${page.pageName}">
															</div>
														</div>
														
														<div class="form-group">
															<label class="control-label col-sm-2" for="page_name">No. of Faculty
																Available<span class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<input type="text" class="form-control" id="page_name"
																	name="page_name" placeholder="No. of Faculty Available"
																
																	value="${page.pageName}">
															</div>
														</div>
														
														<div class="form-group">
															<label class="control-label col-sm-6" for="page_name">Total Student Strength as per Sanction Intake
																(second year/third year/last year/including direct second year(if applicable) )<span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="text" class="form-control" id="page_name"
																	name="page_name" placeholder="Student Strength"
																	value="${page.pageName}">
															</div>
														</div>
													
													 --%>
														<div class="form-group">
															<label class="control-label col-sm-2" for="page_name">Faculty
																Member Name <span class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<input type="text" class="form-control" id="faculty_member_name"
																	name="faculty_member_name" placeholder="Faculty Member Name"
																	value="${staff.facultyName}">
															</div>
														</div>



														<div class="form-group">
															<label class="control-label col-sm-2" for="status">Highest
																Qualification  <span class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<select id="highest_qualification" name="highest_qualification"  onchange="showForm1()" class="form-control"
																	required>
																	<%-- <c:forEach items="${}" var="makeList"> 
																	<c:choose>
																			<c:when test="${makeList.designationId == miqc.desgntnId}">
																				<option value="${makeList.designationId}" selected="selected">${makeList.designationName}</option>
																			</c:when>
																		<c:otherwise><option value="${makeList.designationId}">${makeList.designationName}</option></c:otherwise>
																	</c:choose>
																 </c:forEach> --%>
																	
																	<option value="1">UG</option>
																	<option value="2">PG</option>
																	<option value="4">M.phill</option>
																	<option value="5">Ph.D.</option>
																	<option value="6">Post Docterate</option>
																	
																	<option value="3">Any Other Course</option>
																</select>
															</div>
														</div>


	
														<div class="form-group" id="pqr">
															<label class="control-label col-sm-2" for="smallheading">Other Qualification
														  <span class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<input type="text" class="form-control" id="other_qualification"
																	name="other_qualification" 
																	
																	placeholder="Other Qualification" value="">
															</div>
														</div>
														
														
														<div class="form-group">
															<label class="control-label col-sm-2" for="page_order">Year
																of highest Qualification Acquired <span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<input type="text" class="form-control" id="yr_highest_qualification_acqrd"
																	name="yr_highest_qualification_acqrd"
																	placeholder="Year of highest Qualification Required"
																	value="${staff.hightestQualificationYear}" required>
															</div>
														</div>

														<div class="form-group">
															<label class="control-label col-sm-2" for="status">Current
																Designation  <span class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<select id="designation" name="designation"
																	class="form-control" required>
																	<c:forEach items="${desigList}" var="makeList"> 
																	<c:choose>
																			<c:when test="${makeList.designationId == staff.currentDesignationId}">
																				<option value="${makeList.designationId}" selected="selected">${makeList.designationName}</option>
																			</c:when>
																		<c:otherwise><option value="${makeList.designationId}">${makeList.designationName}</option></c:otherwise>
																	</c:choose>
																 </c:forEach>

																</select>
															</div>
														</div>


														<div class="form-group">
															<label class="control-label col-sm-2" for="page_order">Joining
																Date <span class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<input type="date" class="form-control" id="join_date"
																	name="join_date" placeholder="Contact No" value="${staff.joiningDate}" required>
															</div>
														</div>

														<div class="form-group">
															<label class="control-label col-sm-2" for="page_order">Is
																Working Today<span class="text-danger">*</span>
															</label>


															<div class="col-sm-10">
																Yes <input type="radio" name="isReg" id="isReg"  onclick="showReforms3(this.value)"
																	checked value="0">
																No<input type="radio" name="isReg" id="isReg"  onclick="showReforms3(this.value)"
																	value="1">
															</div>


														</div>

														<div class="form-group" id="rel_date1">
															<label class="control-label col-sm-2" for="page_order">Relieving
																Date <span class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<input type="date" class="form-control" id="rel_date"
																	name="rel_date">
															</div>
														</div>

															<div class="form-group">
															<label class="control-label col-sm-2" for="status">Teaching
																to  <span class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<select id="teachTo" name="teachTo"  class="form-control"
																	required>
																	<option value="0">UG</option>
																	<option value="1">PG</option>
																	<option value="2">UG & PG</option>
																	<option value="5">Any Other Course</option>


																</select>
															</div>
														</div>
															
                                                    <div class="form-group" id="abc">
															<label class="control-label col-sm-2" for="smallheading">Other Course
														  <span class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<input type="text" class="form-control" id="other_course"
																	name="other_course" 
																	
																	placeholder="Other Designation" value="">
															</div>
														</div>

														<div class="form-group">
															<label class="control-label col-sm-2" for="page_order">Contact
																No <span class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<input type="text" pattern="^[1-9]{1}[0-9]{9}$"
																	maxlength="10" class="form-control" id="contact_no"
																	name="contact_no" placeholder="Mobile Number" value="${staff.contactNo}" required>
															</div>
														</div>

														<div class="form-group">
															<label class="control-label col-sm-2" for="page_order">Email ID(Official)
																<span class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<input type="email" class="form-control" id="email"
																	name="email" placeholder="abc@xyz.com" value="${staff.email}" required>
															</div>
														</div>

														
													</div>

												</div>


												<div class="form-group">
															<div class="col-sm-offset-2 col-sm-10">
																<input type="submit" class="btn btn-primary" value="Add">
																	<a href="${pageContext.request.contextPath}/showStaffList"><button
										                              type="button" class="btn btn-primary">Save & Next</button></a>
																<button type="reset" class="btn btn-default">Reset</button>
															</div>
														</div>



												<div class="clearfix"></div>

											</div>

										</div>

									</form>
								</div>

							</div>

						</div>
					</section>
				</div>

			</section>
		</section>

	</div>
	<!-- MAIN CONTENT AREA ENDS -->

	<!-- END CONTENT -->



	<script type="text/javascript">
	 function showReforms3(b) {
			
		if(b ==1){
			document.getElementById("rel_date1").style = "display:none"
				
		}
		else{
			
			
				document.getElementById("rel_date1").style ="visible"
			
		}
		}
				
	 function showForm() {
			//document.getElementById("abc").style = "display:none"
				var qualType=document.getElementById("teachTo").value
			//alert("qualType::"+qualType);
				
				if (qualType == 5) {

					document.getElementById("abc").style = "visible"
					
						
				} 
				else{
					document.getElementById("abc").style = "display:none"
				}
			
			}
	 
	 function showForm1() {
			//document.getElementById("abc").style = "display:none"
				var qualType=document.getElementById("deptId").value
			//alert("qualType::"+qualType);
				
				if (qualType == 3) {

					document.getElementById("pqr").style = "visible"
					
						
				} 
				else{
					document.getElementById("pqr").style = "display:none"
				}
			
			}
		function hideText() {
			//alert("hii");
			document.getElementById("abc").style = "display:none"
				
				document.getElementById("pqr").style = "display:none"

			}
		
	</script>





	<!-- END CONTAINER -->
	<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->

	<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>



</body>
</html>