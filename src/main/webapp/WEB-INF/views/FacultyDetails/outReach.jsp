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
								<a
									href="${pageContext.request.contextPath}/showOutReachDetailsList"><button
										type="button" class="btn btn-info">Back</button></a>
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertOutReachActivity"
										method="post" 
										name="form_sample_2" id="form_sample_2"
										onsubmit="return confirm('Do you really want to submit the form?');">

										<!-- <ul class="nav nav-tabs">
											<li class="active"><a href="#home" data-toggle="tab">
													<i class="fa fa-home"></i> Register
											</a></li>

										</ul> -->
<!-- 
										<div class="tab-content">
											<div class="tab-pane fade in active" id="home"> -->

												<div>

													<div class="col-sm-12"></div>


													<div class="form-group">
														<label class="control-label col-sm-2" for="status">
														Outreach Type <span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<!-- <select id="act_attended" onchange="showForm()"
																name="act_attended" class="form-control" required>
																<option value="STTP">STTP</option>
																<option value="Seminar">Seminar</option>
																<option value="Workshop">Workshop</option>
																<option value="Conference">Conference</option>
																<option value="FDP">FDP</option>
																<option value="Refresher Course">Refresher Course</option>
																<option value="6">Any Other</option>

															</select> -->
															<select id="activity_type" name="activity_type" class="form-control" required>
															
																	
																		<c:forEach items="${facultyOutreachTypeList}" var="outtype">
																		<c:choose>
																			<c:when test="${outtype.typeId==editInst.outreachType}">
																			<option  selected value="${outtype.typeId}">${outtype.typeName}</option>


																			</c:when>
																			<c:otherwise>
																			<option value="${outtype.typeId}">${outtype.typeName}</option>


																			</c:otherwise>

																		</c:choose>

																	</c:forEach>
																</select>
														</div>
															</div>
													<!-- 	<div class="form-group" id="abc" style = "display:none">
															<label class="control-label col-sm-2" for="smallheading">Other
																Activity <span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="text" class="form-control" id="other_act"
																	name="other_act" placeholder="Other Designation" value=""
																	required>
															</div>
														</div>
 -->

													</div>

													<div class="form-group">

														<label class="control-label col-sm-2" for="smallheading">Outreach Name
															<!-- Name of Activity  --><span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<input type="text" class="form-control" id="act_name"
																name="act_name" placeholder="Name of Activity" value="${editInst.outreachName}"
																required>
														</div>

													</div>


<%-- 
											<div class="form-group">

														<label class="control-label col-sm-2" for="status">Activity Type
															 <span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<select id="activity_type" name="activity_type" class="form-control" required>
															
																	
																		<c:forEach items="${facultyOutreachTypeList}" var="outtype">
																	
																			<option value="${outtype.typeId}">${outtype.typeName}</option>

																			

																	</c:forEach>
																</select>
														</div>
													</div>
 --%>

													<div class="form-group">

														<label class="control-label col-sm-2" for="status">Outreach Level
															 <span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<select id="act_level" name="act_level"
																class="form-control" required>
																<!-- <option value="International">International</option>
																<option value="National">National</option>
																<option value="State">State</option>
																<option value="Regional">Regional</option> -->
																
																<c:choose>
																			<c:when test="${'International'==editInst.outreachLevel}">
																			
																<option  selected value="International">International</option>
																<option value="National">National</option>
																<option value="State">State</option>
																<option value="Regional">Regional</option>

																			</c:when>
																			<c:when test="${'National'==editInst.outreachLevel}">
																			
																<option   value="International">International</option>
																<option selected value="National">National</option>
																<option value="State">State</option>
																<option value="Regional">Regional</option>

																			</c:when>
																			
																			<c:when test="${'State'==editInst.outreachLevel}">
																			
																<option   value="International">International</option>
																<option  value="National">National</option>
																<option selected value="State">State</option>
																<option value="Regional">Regional</option>

																			</c:when>
																			
																			<c:when test="${'Regional'==editInst.outreachLevel}">
																			
																<option   value="International">International</option>
																<option  value="National">National</option>
																<option value="State">State</option>
																<option  selected value="Regional">Regional</option>

																			</c:when>
																			
																	<c:otherwise>
																<option value="International">International</option>
																<option value="National">National</option>
																<option value="State">State</option>
																<option value="Regional">Regional</option>


																			</c:otherwise>

																		</c:choose>


															</select>
														</div>
													</div>

												<div class="form-group">
															<label class="control-label col-sm-2" for="page_order">
																Outreach Date <span class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<input type="text" class="form-control datepicker" id="act_date" value="${date}"
																	name="act_date" placeholder="dd/MM/yyyy " required>
															</div>
														</div>
														
														
											 <input type="hidden" id="outreach_id"  name="outreach_id"   value="${editInst.outreachId}">
                                             	<input type="hidden" id="is_view" name="is_view" value="0">
												
												
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


													<div class="clearfix"></div>

												<!-- </div> -->

										<!-- 	</div> -->
									
									</form>
										<p class="desc text-danger fontsize11">Notice : * Field are  Mandatory.</p>
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
		function showForm() {
			//document.getElementById("abc").style = "display:none"
			var qualType = document.getElementById("salutation").value
			//alert("qualType::"+qualType);

			if (qualType == 6) {

				document.getElementById("abc").style = "visible"

			} else {
				document.getElementById("abc").style = "display:none"
			}

		}
		/* function hideText() {
			//alert("hii");
			document.getElementById("abc").style = "display:none"

		} */
		
		function submit_f(view){
			document.getElementById("is_view").value=view;//create this 
			/* var form=document.getElementById("form_sample_2");
		    form.setAttribute("method", "post");

			form.action=("insertLibrarian");
			var x =confirm();
			if(x==true)
			form.submit(); */
			
		}
		
	</script>
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

</body>
</html>