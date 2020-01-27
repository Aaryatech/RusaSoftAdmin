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
						 <c:if test="${addAccess == 0}">  
								<a href="${pageContext.request.contextPath}/showAddStudMentor"
									data-toggle="modal"><button type="submit"
										class="btn btn-info"><i class="${sessionScope.addIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Add</button></a>
									 </c:if>
							</div>

						</header>
						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/delSlectedStudmentr/0"
										method="get" name="form_sample_2" id="form_sample_2">
									<%
										UUID uuid = UUID.randomUUID();
										MessageDigest md = MessageDigest.getInstance("MD5");
										byte[] messageDigest = md.digest(String.valueOf(uuid).getBytes());
										BigInteger number = new BigInteger(1, messageDigest);
										String hashtext = number.toString(16);
										session = request.getSession();
										session.setAttribute("generatedKey", hashtext);
									%>
											<div class="col-xs-12">
													<table id="example-1"
														class="table table-striped dt-responsive display">
														<thead>
															<tr>
															 <th class="check" style="text-align: center; width: 5%;"><input
																type="checkbox" name="selAll" id="selAll"
																onClick="selectedInst(this)" /> Select All</th> 
																<th>Sr No</th>
																<th>Faculty name</th>
																<th>Department</th>
																<th>No. of Students</th>
																<th width="10%">Action</th>
															</tr>
														</thead>
														<tbody>
										<c:forEach items="${studL}"
												var="studL" varStatus="count">
												<tr>
											 <td><input type="checkbox" class="chk" name="menId"
															id="menId${count.index+1}" value="${studL.menId}" /></td> 
												<td style="text-align: center">${count.index+1}</td>
											
											<td style="text-align: center"><c:out
														value="${studL.facultyFirstName}" /></td>
														
											<td style="text-align: center"><c:out
														value="${studL.deptName}" /></td>
														
										
											<td style="text-align: right"><c:out
														value="${studL.menStuCount}" /></td>
										
																							
													
													<td align="center">
													 <c:if test="${editAccess == 0}"> <a
														href="${pageContext.request.contextPath}/editFacultyMentor/${studL.menId}"><span
															class="glyphicon glyphicon-edit"
															data-animate=" animated fadeIn " rel="tooltip"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;
														 </c:if>  
															
												 <c:if test="${deleteAccess == 0}"> <a
														href="${pageContext.request.contextPath}/deleteFacultyMentor/${studL.menId}/<%out.println(hashtext);%>"
														onClick="return confirm('Are you sure want to delete this record');"
														rel="tooltip" data-color-class="danger"
														data-animate=" animated fadeIn " data-toggle="tooltip"
														data-original-title="Delete  record"><span
															class="glyphicon glyphicon-remove"></span></a></c:if></td>
												</tr>
											</c:forEach> 
											
										</tbody>
													</table>
													
												 	<c:if test="${deleteAccess==0}"> -
														<button class="btn btn-primary" value="Delete" id="deleteId"
														onClick="var checkedVals = $('.chk:checkbox:checked').map(function() { return this.value;}).get();checkedVals=checkedVals.join(',');if(checkedVals==''){alert('No Rows Selected');return false;	}else{   return confirm('Are you sure want to delete record');}"
														style="align-content: center; width: 113px; margin-left: 40px;">
														<i class="${sessionScope.deleteIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Delete</button> 
											 		</c:if> 
											<input type="hidden" id="edit_accOff_id" name="edit_accOff_id"
											value="0">
													
												</div>


												<div class="clearfix"></div>


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


	<div aria-hidden="true" role="dialog" tabindex="-1" id="myModal1"
		class="modal fade" style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button aria-hidden="true" data-dismiss="modal" class="close"
						type="button">×</button>
					<h4 class="modal-title">Mentoring to Student</h4>
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
							Year</label> <select id="qualType" name="qualType" class="form-control"
							onchange="showForm()" required>
							<option value="2018-2019">2018-2019</option>
							<option value="2017-2018">2017-2018</option>
							<option value="2016-2017">2016-2017</option>
							<option value="2016-2017">2015-2016</option>

						</select>
					</div>
					<div class="form-group">

						<label class="control-label col-sm-3" for="page_name">No
							of Student </label> <input type="text" class="form-control" id="hodName"
							name="hodName" placeholder="No." value="${page.pageName}"
							required>
					</div>



					<button type="submit" class="btn btn-primary" onclick="getData()">Submit</button>
					<!-- </form> -->
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
	function selectedInst(source) {

		checkboxes = document.getElementsByName('menId');

		for (var i = 0, n = checkboxes.length; i < n; i++) {
			checkboxes[i].checked = source.checked;

		}

	}
	
		function getData() {
			//alert("hii");
			var i = parseInt(document.getElementById("index").value);
			var year = document.getElementById("qualType").value;
			//alert("year"+year);
			var noStud = document.getElementById("hodName").value;
			//alert("noStud"+noStud);
			var dataTable = $('#example-2').DataTable();

			dataTable.row.add([ i + 1, year, noStud ]).draw();

			document.getElementById("index").value = i + 1;

		}
	</script>






</body>
</html>