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
								<c:if test="${addAccess == 0}">
									<%-- <a href="${pageContext.request.contextPath}/showOutReachDetails"><button
										type="button" class="btn btn-success">Add</button></a> --%>

									<a title="Add"
										href="${pageContext.request.contextPath}/showOutReachDetails"><button
											type="button" class="btn btn-success">
											<i class="${sessionScope.addIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Add
										</button></a>
								</c:if>
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertPublicationDetail"
										method="post" name="form_sample_2" id="form_sample_2"
										onsubmit="return confirm('Do you really want to submit the form?');">

										<!-- <ul class="nav nav-tabs">
											<li class="active"><a href="#home" data-toggle="tab">
													<i class="fa fa-home"></i> Register
											</a></li>

										</ul> -->

										<!-- 	<div class="tab-content">
											<div class="tab-pane fade in active" id="home"> -->

										<div>

											<div class="col-xs-12">


												<div class="col-xs-12">


													<table id="example-1"
														class="table table-striped dt-responsive display">
														<thead>
															<tr>
																<th style="text-align: center;">Sr No</th>
																<th style="text-align: center;">Outreach Type</th>
																<th style="text-align: center;">Outreach Name</th>
																<th style="text-align: center;">Faculty Name</th>
																<th style="text-align: center;">Department</th>
																<th style="text-align: center;">Outreach Level</th>
																<th style="text-align: center;">Outreach Date</th>

																<th>Action</th>
															</tr>
														</thead>
														<tbody>
															<c:forEach items="${facultyOutreachList}" var="outList"
																varStatus="count">
																<tr>

																	<td style="text-align: center;">${count.index+1}</td>
																	<td>${outList.typeName}</td>
																	<td>${outList.outreachName}</td>
																	<td>${outList.facultyFirstName}</td>
																	<td>${outList.deptName}</td>
																	<td>${outList.outreachLevel}</td>
																	<td style="text-align: center;">${outList.outreachDate}</td>



																	<td align="center"><c:if test="${editAccess == 0}">
																			<a href="#"
																				onclick="showEditLibrarian(${outList.outreachId})"><span
																				class="glyphicon glyphicon-edit" title="Edit"
																				data-original-title="Edit"
																				data-animate=" animated fadeIn " rel="tooltip"></span></a>
																		</c:if>&nbsp;&nbsp;&nbsp;&nbsp; <c:if
																			test="${deleteAccess == 0}">
																			<a
																				href="${pageContext.request.contextPath}/deleteFacOutReach/${outList.outreachId}"
																				onClick="return confirm('Are you sure want to delete this record');"
																				rel="tooltip" data-color-class="danger"
																				title="Delete" data-original-title="Delete"
																				data-animate=" animated fadeIn "
																				data-toggle="tooltip"
																				data-original-title="Delete  record"><span
																				class="glyphicon glyphicon-remove"></span></a>
																		</c:if></td>
																</tr>
															</c:forEach>
														</tbody>

													</table>
													<input type="hidden" id="e_outreach_id"
														name="e_outreach_id" value="0">

												</div>

											</div>


											<div class="clearfix"></div>

										</div>
										<!-- 
											</div>
										</div> -->
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
	function showEditLibrarian(outId){
	
		document.getElementById("e_outreach_id").value=outId;//create this 
		
		var form=document.getElementById("form_sample_2");
	    form.setAttribute("method", "post");	
	   

		//form.action=("showEditFacOutReach");
		form.action=("showEditFacOutReach");
		form.submit();
		
	}
	</script>



</body>
</html>


