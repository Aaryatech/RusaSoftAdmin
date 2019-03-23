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
								<%-- <c:if test="${addAccess==0}">
									<a
										href="${pageContext.request.contextPath}/showAddAcademicDetails"><button
											type="button" class="btn btn-success">Add Academic
											Details</button></a>
								</c:if>
 --%>
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
														<!-- <label class="control-label col-sm-3" for="smallheading">Educational
															Qualifications : <span class="text-danger">*</span>
														</label> -->
														<div class="col-xs-12">




															<table id="example-1"
																class="table table-striped dt-responsive display">
																<thead>
																	<tr>
																		<th>Sr No</th>
																		<th>Faculty Name</th>
																		<th>Qualification</th>
																		<th>Class</th>
																		<th>University Name</th>
																		<th>Year of Passing</th>
																		<th>Action</th>

																	</tr>
																</thead>
																<tbody>
																	<c:forEach items="${facAcadList}" var="staffList"
																		varStatus="count">
																		<tr>
																			<%-- <td><input type="checkbox" class="chk" name="staffIds"
															id="staffIds${count.index+1}" value="${staffList.facultyId}" /></td> --%>
																			<td style="text-align: center">${count.index+1}</td>

																			<td style="text-align: left"><c:out
																					value="${staffList.facultyName}" /></td>
																			<td style="text-align: left"><c:out
																					value="${staffList.qualificationName}" /></td>

																			<td style="text-align: center;"><c:out
																					value="${staffList.fClass}" /></td>

																			<td style="text-align: left"><c:out
																					value="${staffList.fUniversity}" /></td>

																			<td style="text-align: center;"><c:out
																					value="${staffList.fPassingYear}" /></td>

																			<td align="center"><c:if
																					test="${editAccess == 0}">
																					<a
																						href="${pageContext.request.contextPath}/showEditFacAcademic/${staffList.fAcaId}"
																						title="Edit" rel="tooltip"
																						data-color-class="detail"
																						data-animate=" animated fadeIn "
																						data-toggle="tooltip" data-original-title="Edit"><span
																						class="glyphicon glyphicon-edit"></span></a>
																				</c:if></td>

																		</tr>
																	</c:forEach>
																</tbody>

															</table>

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



	<script>
		function showForm() {
			//document.getElementById("abc").style = "display:none"
			var qualType = document.getElementById("qualType").value
			//alert("qualType::"+qualType);

			if (qualType == 7) {

				document.getElementById("abc").style = "visible"

				//document.getElementById("ex1").style = "display:none"
				// $("#ex1").prop("disabled", true);

				//$('#ex1').attr('disabled', true);
			} else {
				document.getElementById("abc").style = "display:none"
			}

		}
		function hideText() {
			//alert("hii");
			document.getElementById("abc").style = "display:none"

		}
	</script>

	<script type="text/javascript">
		function getData() {
			var i = parseInt(document.getElementById("index").value);
			var academicYear = document.getElementById("academicYear").value
			var qualType = document.getElementById("qualType").value
			var qualName = document.getElementById("qualName").value
			//alert(qualName);
			var passClass = document.getElementById("passClass").value
			var year = document.getElementById("year").value
			var university = document.getElementById("university").value
			var city = document.getElementById("city").value
			var temp;
			if (qualType == 7) {

				temp = qualName;
				//alert(temp);
			} else {
				temp = qualType;
			}

			var dataTable = $('#example-1').DataTable();

			dataTable.row.add(
					[ i + 1, academicYear, temp, passClass, university, city,
							year

					]).draw();

			document.getElementById("index").value = i + 1;

		}
	</script>



</body>
</html>



