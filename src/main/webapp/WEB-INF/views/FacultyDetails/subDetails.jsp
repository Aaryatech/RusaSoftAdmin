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
								<%-- 	<a href="${pageContext.request.contextPath}/showAddSubDetails"><button
										type="button" class="btn btn-success">Add</button></a> --%>

								<c:if test="${addAccess==0}">
									<a title="Add"
										href="${pageContext.request.contextPath}/showAddSubDetails"><button
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


										<table id="example-1"
											class="table table-striped dt-responsive display">
											<thead>
												<tr>
													<th>Sr No</th>
													<th>Semester</th>
													<th>Subject</th>
													<th>Subject Type</th>
													<th>Faculty</th>
													<th>Program</th>
													<th>Year of Implementation</th>
													<th>Result %</th>
													<th>Action</th>
												</tr>
											</thead>

											<tbody>
												<c:forEach items="${subList}" var="subject"
													varStatus="count">
													<tr>

														<td style="text-align: center">${count.index+1}</td>

														<td style="text-align: center"><c:out
																value="${subject.subSem}" /></td>



														<td style="text-align: left"><c:out
																value="${subject.subCode}-${subject.subName}" /></td>


														<c:set var="subType"></c:set>
														<c:choose>
															<c:when test="${subject.subType==0}">
																<c:set var="subType" value="Regular"></c:set>
															</c:when>
															<c:when test="${subject.subType==1}">
																<c:set var="subType" value="Elective"></c:set>
															</c:when>
															<c:when test="${subject.subType==2}">
																<c:set var="subType" value="Other"></c:set>
															</c:when>
														</c:choose>
														<td style="text-align: left"><c:out
																value="${subType}" /></td>


														<td style="text-align: left;">${subject.facultyFirstName}-${subject.deptName}</td>

														<td style="text-align: left"><c:out
																value="${subject.nameOfProgram}" /></td>

														<td style="text-align: left"><c:out
																value="${subject.exVar1}" /></td>



														<td style="text-align: right"><c:out
																value="${subject.subPassPer}" /></td>



														<td align="center"><c:if test="${editAccess == 0}">

																<a
																	href="${pageContext.request.contextPath}/showAddCo/${subject.subId}"
																	title="Add Course Outcome (CO)" rel="tooltip"
																	data-color-class="detail"
																	data-animate=" animated fadeIn " data-toggle="tooltip"
																	data-original-title="Block"><span
																	class="glyphicon glyphicon-list"></span></a>&nbsp;&nbsp;
																<a
																	href="${pageContext.request.contextPath}/editSubject/${subject.subId}"
																	title="Edit" rel="tooltip" data-color-class="detail"
																	data-animate=" animated fadeIn " data-toggle="tooltip"
																	data-original-title="Edit  "><span
																	class="glyphicon glyphicon-edit"></span></a>&nbsp;&nbsp;
															</c:if> <c:if test="${deleteAccess == 0}">
																<a
																	href="${pageContext.request.contextPath}/deleteSubject/${subject.subId}/<%out.println(hashtext);%>"
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
					<h4 class="modal-title">Subject Details</h4>
				</div>
				<div class="modal-body">
					<%-- <form role="form"
						action="${pageContext.request.contextPath}/showModuleForm"
						method="get"> --%>
					<input type="hidden" class="form-control" id="pageId" name="pageId">
					<input type="hidden" class="form-control" id="index" name="index"
						value="0">
					<div class="form-group">
						<label class="control-label col-sm-6" for="page_name">Academic
							Year</label> <select id="academicYear" name="qualType"
							class="form-control" onchange="showForm()" required>
							<option value="2018-2019">2018-2019</option>
							<option value="2017-2018">2017-2018</option>
							<option value="2016-2017">2016-2017</option>
							<option value="2016-2017">2015-2016</option>

						</select>
					</div>


					<div class="form-group">

						<label class="control-label col-sm-6" for="page_name">Subject
							Code Taught </label>
						<!-- <div class="col-sm-3"> -->
						<input type="text" class="form-control" id="code"
							placeholder="Subject Code Taught" name="code"
							value="${page.pageName}" required>
						<!-- </div> -->
					</div>


					<div class="form-group">
						<label class="control-label col-sm-6" for="page_name">Semester</label>
						<select id="sem" name="sem" class="form-control"
							onchange="showForm()" required>
							<option value="0">I</option>
							<option value="1">II</option>
							<option value="3">III</option>
							<option value="4">IV</option>




						</select>
					</div>


					<div class="form-group">

						<label class="control-label col-sm-6" for="page_name">Subject
							Taught </label>
						<!-- <div class="col-sm-3"> -->
						<input type="text" class="form-control" id="subTaut"
							placeholder="Subject Taught" name="subTaut"
							value="${page.pageName}" required>
						<!-- </div> -->
					</div>


					<div class="form-group">
						<label class="control-label col-sm-6" for="page_name">Subject
							Type</label> <select id="subType" name="subType" class="form-control"
							onchange="showForm()" required>
							<option value="0">Regular</option>
							<option value="1">Elective</option>
							<option value="3">Other</option>

						</select>
					</div>



					<div class="form-group">
						<label class="control-label col-sm-6" for="page_name">Is
							CBCS</label>
						<div class="col-sm-2">
							Yes <input type="radio" name="consultancy" id="consultancy"
								checked value="0"> No<input type="radio"
								name="consultancy" id="consultancy" value="1">
						</div>
					</div>
					<div class="form-group">

						<label class="control-label col-sm-6" for="page_name">No.
							of Student Appeared </label>
						<!-- <div class="col-sm-3"> -->
						<input type="text" class="form-control" id="noStud"
							placeholder="No. of Student Appeared" name="noStud"
							value="${page.pageName}" required>
						<!-- </div> -->
					</div>
					<div class="form-group">

						<label class="control-label col-sm-6" for="page_name">Passed
						</label>
						<!-- <div class="col-sm-3"> -->
						<input type="text" class="form-control" id="pass"
							placeholder="Passed" onchange=" calResult()" name="pass"
							value="${page.pageName}" required>
						<!-- </div> -->
					</div>


					<div class="form-group">

						<label class="control-label col-sm-6" for="page_name">% of
							Result </label>
						<!-- <div class="col-sm-3"> -->
						<input type="text" class="form-control" id="rslt"
							placeholder="% of Result" name="rslt" value="${page.pageName}"
							required>
						<!-- </div> -->
					</div>



					<div class="form-group">

						<label class="control-label col-sm-6" for="page_name">Course
							Outcome </label>
						<!-- <div class="col-sm-3"> -->
						<input type="text" class="form-control" id="course"
							placeholder="Course Outcome" name="course"
							value="${page.pageName}" required>
						<!-- </div> -->
					</div>




					<button type="submit" class="btn btn-primary" onclick="getData()">Submit</button>
					<!-- </form> -->
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		function getData() {
			//alert("hii");
			var i = parseInt(document.getElementById("index").value);

			var sem = document.getElementById("sem").value
			var code = document.getElementById("code").value
			var academicYear = document.getElementById("academicYear").value

			var subTaut = document.getElementById("subTaut").value
			var subType = document.getElementById("subType").value

			var noStud = document.getElementById("noStud").value

			var pass = document.getElementById("pass").value
			var rslt = document.getElementById("rslt").value
			var course = document.getElementById("course").value
			var isCBCS = document.getElementById("consultancy").value
			var t = "-";

			var dataTable = $('#example-1').DataTable();

			dataTable.row.add([ i + 1, academicYear,

			sem, code, subTaut, subType, isCBCS, course, noStud, pass, rslt,

			t ]).draw();

			document.getElementById("index").value = i + 1;

		}

		function calResult() {
			var noStud = parseFloat(document.getElementById("noStud").value);

			var pass = parseFloat(document.getElementById("pass").value);

			var x = (pass / noStud) * 100;
			document.getElementById("rslt").value = x;

		}
	</script>
	<script type="text/javascript">
	function allowOnlyNumber(evt){
		
		var charCode = (evt.which) ? evt.which : event.keyCode
	    if (charCode == 46){
	        var inputValue = $("#floor").val();
	        var count = (inputValue.match(/'.'/g) || []).length;
	        
	        if(count<1){
	            if (inputValue.indexOf('.') < 1){
	                return true;
	            }
	            return false;
	        }else{
	            return false;
	        }
	    }
	    if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)){
	        return false;
	    }
	    return true;
	}
	</script>





</body>
</html>