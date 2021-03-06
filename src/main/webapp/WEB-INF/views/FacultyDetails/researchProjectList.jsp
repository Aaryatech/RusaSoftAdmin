<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="java.util.UUID"%>
<%@ page import="java.security.MessageDigest"%>
<%@ page import="java.math.BigInteger"%>

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

								<%-- 	<a href="${pageContext.request.contextPath}/showResearchDetails"><button
										type="button" class="btn btn-success">Add</button></a>
 --%>

								<c:if test="${addAccess==1}">
									<a title="Add"
										href="${pageContext.request.contextPath}/showResearchDetails"><button
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
										method="post" enctype="multipart/form-data"
										name="form_sample_2" id="form_sample_2"
										onsubmit="return confirm('Do you really want to submit the form?');">
										
										<%
											UUID uuid = UUID.randomUUID();
											MessageDigest md = MessageDigest.getInstance("MD5");
											byte[] messageDigest = md.digest(String.valueOf(uuid).getBytes());
											BigInteger number = new BigInteger(1, messageDigest);
											String hashtext = number.toString(16);
											session = request.getSession();
											session.setAttribute("generatedKey", hashtext);
										%>

										<!-- <label class="control-label col-sm-3" for="smallheading">Educational
															Qualifications : <span class="text-danger">*</span>
														</label> -->
										<div class="col-xs-12">


											<table id="example-1"
												class="table table-striped dt-responsive display">
												<thead>
													<tr>
														<th>Sr No</th>
														<th>Project Name</th>
														<th>Faculty Name</th>
														<th>Department</th>
														<th>Project Sanction Year</th>
														<th>Sponsoring Authority</th>
														<th>Grant Sanctioned</th>
														<th>Grant Received</th>
														<th>Action</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${jouList}" var="journal"
														varStatus="count">
														<tr>

															<td style="text-align: center">${count.index+1}</td>

															<td style="text-align: left"><c:out
																	value="${journal.projName}" /></td>

															<td style="text-align: left"><c:out
																	value="${journal.facultyFirstName}" /></td>

															<td style="text-align: left"><c:out
																	value="${journal.deptName}" /></td>



															<td style="text-align: center"><c:out
																	value="${journal.projYear}" /></td>

															<td style="text-align: left"><c:out
																	value="${journal.projSponsor}" /></td>

															<td style="text-align: right"><c:out
																	value="${journal.projGrant}" /></td>

															<td style="text-align: right"><c:out
																	value="${journal.projAmtRec}" /></td>



															<td align="center"><c:if test="${editAccess == 1}">
																	<a
																		href="${pageContext.request.contextPath}/editProject/${journal.projId}"
																		title="Edit Project" rel="tooltip"
																		data-color-class="detail"
																		data-animate=" animated fadeIn " data-toggle="tooltip"
																		data-original-title="Edit Journal"><span
																		class="glyphicon glyphicon-edit"></span></a> &nbsp;&nbsp;&nbsp;&nbsp;</c:if>
																<c:if test="${deleteAccess == 1}">
																	<a
																		href="${pageContext.request.contextPath}/deleteProject/${journal.projId}/<%out.println(hashtext);%>"
																		onClick="return confirm('Are you sure want to delete this record');"
																		rel="tooltip" data-color-class="danger" title="Delete"
																		data-animate=" animated fadeIn " data-toggle="tooltip"
																		data-original-title="Delete  record"><span
																		class="glyphicon glyphicon-remove"></span></a>

																</c:if></td>




														</tr>
													</c:forEach>
												</tbody>

											</table>

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

	<%-- 	
	
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
							
							
							<input type="hidden" class="form-control" id="index"
							name="index" value="0">
							
										<div class="form-group">
						<label class="control-label col-sm-6" for="academicYear">Academic
							Year</label> <select id="academicYear" name="academicYear"
							class="form-control" required>
							<option value="2018-2019">2018-2019</option>
							<option value="2017-2018">2017-2018</option>
							<option value="2016-2017">2016-2017</option>
								<option value="2015-2016">2015-2016</option>

						</select>
					</div>
						
									<div class="form-group" id="ex1">
							<label class="control-label col-sm-3" for="page_name">Qualification</label> <select
								id="qualType" name="qualType" class="form-control" onchange="showForm()" required>
								<option value="Diploma">Diploma</option>
								<option value="Bachelors">Bachelors</option>
								<option value="Masters">Masters</option>
								<option value="Doctorate">Doctorate</option>
							<option value="Post Doctorate">Post Doctorate</option>
								<option value="M.Phill">M.Phill</option>
								
								<option value="7">Any Other</option>



							</select>
						</div>
						
						
						<div class="form-group" id="abc">
						
							<label class="control-label col-sm-6" for="page_name">Other Qualification
							</label>
							<!-- <div class="col-sm-"> -->
								<input type="text" class="form-control" id="qualName" required
									name="qualName" placeholder="" value="${page.pageName}"
									>
							<!-- </div> -->
	</div>
						
						<div class="form-group">
						
							<label class="control-label col-sm-1" for="page_name">Class
							</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" id="className" required
									name="className" placeholder="" value="${page.pageName}"
									required>
							</div>
	</div>
	
	
										<div class="form-group">
						<label class="control-label col-sm-6" for="passClass">Class
							Year</label> <select id="passClass" name="passClass"
							class="form-control" required>
							<option value="First">First</option>
							<option value="Second">Second</option>
							<option value="Pass">Pass </option>
								<option value="Class">Class</option>

						</select>
					</div>
					
					<div class="form-group">

						<label class="control-label col-sm-6" for="beneficiaryMOU">University
						</label> <input type="text" class="form-control"
							id="university" name="university"
							placeholder="University"
							value="${page.pageName}" required>
					</div>
				
	
					
<div class="form-group">

						<label class="control-label col-sm-6" for="totalParticipants">City
						</label> <input type="text" class="form-control"
							id="city" name="city"
							placeholder="City"
							value="${page.pageName}" required>
					</div>

<div class="form-group">

						<label class="control-label col-sm-6" for="totalParticipants">Year of Passing
						</label> <input type="date" class="form-control"
							id="year" name="totalParticipants"
							placeholder="Year of Passing"
							value="${page.pageName}" required>
					</div>

				
						<button type="submit" class="btn btn-primary" onclick="getData()">Submit</button>
				<!-- 	</form> -->
				</div>
			</div>
		</div>
	</div>
	
	
	 --%>


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
			//alert("hii");
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



<!-- 
														<h5 class="title pull-left">
																<strong>Consultancy</strong>
															</h5>
															<div class="col-sm-1"></div>
															<div class="col-sm-2">
																Yes <input type="radio" name="consultancy"
																	id="consultancy" checked value="0"> No<input
																	type="radio" name="consultancy" id="consultancy"
																	value="1">
															</div> -->