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
<c:url var="clearSessionAttribute" value="/clearSessionAttribute" />
</head>
<!-- END HEAD -->

<!-- BEGIN BODY -->
<body class=" " onload="clearSessionAttribute()">
	<!-- START TOPBAR -->
	<jsp:include page="/WEB-INF/views/include/topbar.jsp"></jsp:include>
	<!-- END TOPBAR -->
	<!-- START CONTAINER -->
	<div class="page-container row-fluid container-fluid">

		<!-- SIDEBAR - START -->

		<jsp:include page="/WEB-INF/views/include/left.jsp"></jsp:include>
		<!--  SIDEBAR - END -->
		<!-- START CONTENT -->
		<!-- START CONTENT -->
		<section id="main-content" class=" ">
			<section class="wrapper main-wrapper row" style=''>
				<%-- 
				<div class='col-xs-12'>
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



				<div class="col-lg-12">
					<section class="box ">
						<header class="panel_header">
							<h2 class="title pull-left">${title}</h2>
							<div class="actions panel_actions pull-right">
								<c:if test="${addAccess == 0}">


									<a title="Add"
										href="${pageContext.request.contextPath}/showRegLib"><button
											type="button" class="btn btn-success">
											<i class="${sessionScope.addIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Add
										</button></a>
								</c:if>

								<!--  <a class="box_setting fa fa-cog" data-toggle="modal" href="#section-settings"></a>
                    <a class="box_close fa fa-times"></a> -->

							</div>

						</header>

						<%
							UUID uuid = UUID.randomUUID();
							MessageDigest md = MessageDigest.getInstance("MD5");
							byte[] messageDigest = md.digest(String.valueOf(uuid).getBytes());
							BigInteger number = new BigInteger(1, messageDigest);
							String hashtext = number.toString(16);
							session = request.getSession();
							session.setAttribute("generatedKey", hashtext);
						%>
						<form
							action="${pageContext.request.contextPath}/deleteLibrarians/0/<%out.println(hashtext);%>"
							method="get" id="libListForm">
							<div class="content-body">
								<div class="row">
									<%-- 	<c:if test="${sessionScope.successMsg!=null}">
										<div class="col-lg-12">
											<div class="alert alert-success alert-dismissible fade in">
												<button type="button" class="close" data-dismiss="alert"
													aria-label="Close">
													<span aria-hidden="true">×</span>
												</button>
												<strong>Success : </strong> ${sessionScope.successMsg}
											</div>
										</div>
									</c:if> --%>

									<div class="col-xs-12">


										<table id="example-1"
											class="table table-striped dt-responsive display">
											<thead>
												<tr>
													<!-- 	<th class="check" style="text-align: center; width: 5%;"><input
														type="checkbox" name="selAll" id="selAll"
														onClick="selectedInst(this)" /> Select All</th> -->
													<th style="text-align: center; width: 5%">Sr No</th>
													<th style="text-align: center;">Librarian Name</th>

													<th style="text-align: center;">Department</th>
													<th style="text-align: center;">Contact No</th>
													<th style="text-align: center;">Email</th>

													<th style="text-align: center;">Date of Joining</th>
													<th style="text-align: center;">Date of Relieving</th>
													<th style="text-align: center; width: 10%">Action</th>
												</tr>
											</thead>



											<tbody>
												<c:forEach items="${libtList}" var="institute"
													varStatus="count">
													<tr>
														<%-- 	<td>input type="checkbox" class="chk" name="studIds"
															id="studIds${count.index+1}"
															value="${institute.librarianId}" /></td> --%>

														<td style="text-align: center; width: 5%">${count.index+1}</td>
														<td>${institute.facultyFirstName}</td>
														<td>${institute.deptName}</td>
														<td style="text-align: center;">${institute.contactNo}</td>
														<td>${institute.email}</td>
														<td style="text-align: center;">${institute.joiningDate}</td>
														<td style="text-align: center;">${institute.realivingDate}</td>

														<td align="center"><c:if test="${editAccess == 0}">

																<a
																	href="${pageContext.request.contextPath}/showEditLibrarian/${institute.facultyId}"
																	rel="tooltip" data-color-class="danger" title="Edit"
																	data-animate=" animated fadeIn " data-toggle="tooltip"><span
																	class="glyphicon glyphicon-edit"></span></a>

															</c:if>&nbsp;&nbsp;&nbsp;&nbsp; <c:if
																test="${deleteAccess == 0}">
																<a
																	href="${pageContext.request.contextPath}/deleteLibrarians/${institute.facultyId}/<%out.println(hashtext);%>"
																	onClick="return confirm('Are you sure want to delete this record');"
																	rel="tooltip" data-color-class="danger" title="Delete"
																	data-original-title="Delete"
																	data-animate=" animated fadeIn " data-toggle="tooltip"
																	data-original-title="Delete  record"><span
																	class="glyphicon glyphicon-remove"></span></a>
																	
																&nbsp;&nbsp;
																	<a href="#" onclick="blockUser(${institute.facultyId})"
																	onClick="return confirm('Are you sure want to block this user');"
																	rel="tooltip" data-color-class="danger"
																	title="Block user" data-animate=" animated fadeIn "
																	data-toggle="tooltip" data-original-title="Block user"><span
																	class="glyphicon glyphicon-ban-circle"></span></a>
															</c:if></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
										<input type="hidden" id="listMapping" name="listMapping"
											value="${listMapping}"> <input type="hidden"
											id="userId" name="userId" value="0"> <input
											type="hidden" value="<%out.println(hashtext);%>" name="token"
											id="token">
										<div class="col-lg-1"></div>

									</div>
								</div>
							</div>
						</form>
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
		function clearSessionAttribute() {

			$.getJSON('${clearSessionAttribute}', {

				ajax : 'true',

			}, function(data) {

			});

		}

		function selectedInst(source) {

			checkboxes = document.getElementsByName('instIds');

			for (var i = 0, n = checkboxes.length; i < n; i++) {
				checkboxes[i].checked = source.checked;

			}

		}
		function showEditLibrarian(instId) {
			document.getElementById("edit_lib_id").value = instId;//create this 
			var form = document.getElementById("libListForm");
			form.setAttribute("method", "post");

			form.action = ("showEditLibrarian");
			form.submit();

		}
		
		function blockUser(userId) {
			document.getElementById("userId").value = userId;//create this 
			var form = document.getElementById("libListForm");
			form.setAttribute("method", "post");

			form.action = ("blockUser");
			form.submit();

		}
	</script>
</body>
</html>
