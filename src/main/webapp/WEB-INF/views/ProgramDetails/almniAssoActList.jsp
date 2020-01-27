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
		<!-- START CONTENT -->
		<section id="main-content" class=" ">
			<section class="wrapper main-wrapper row" style=''>

				<%-- <div class='col-xs-12'>
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
								<c:if test="${addAccess==0}">
								<%-- 	<a href="${pageContext.request.contextPath}/showAddAlumini"><button
											type="submit" class="btn btn-success">Add</button></a> --%>
											
											
	 					 <a title="Add"
											href="${pageContext.request.contextPath}/addAlumniAssociationActivity"><button
												type="button" class="btn btn-success"><i class="${sessionScope.addIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Add</button></a>
								</c:if>


							</div>

						</header>
						<form action="${pageContext.request.contextPath}/deleteSelectedAlum/0"
							method="get" id="insListForm">
							<%
		UUID uuid = UUID.randomUUID();
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] messageDigest = md.digest(String.valueOf(uuid).getBytes());
		BigInteger number = new BigInteger(1, messageDigest);
		String hashtext = number.toString(16);
		session = request.getSession();
		session.setAttribute("generatedKey", hashtext);
	%>
							<div class="content-body">
								<div class="row">

									<div class="col-xs-12"></div>
									<div class="col-xs-12">

										<table class="table table-striped dt-responsive display"
											id="example-1">
											<thead>
												<tr>
												<th class="check" style="text-align: center; width: 5%;"><input
														type="checkbox" name="selAll" id="selAll"
														onClick="selectedInst(this)" /> Select All</th>
													<th>Sr.No.</th>
													<th>Alumni Meeting Agenda</th>
													<th>Date of Meeting</th>
													<th>Name of Alumni Association</th>
													<th>Alumni Registration No.</th>
													<th>Date of Alumni Registration</th>
													<th>No. of Alumni Register</th>
													<th>No. of Member Attended</th>
													<th>Total No. of Alumni Enrolled</th>
													<th>Action</th>
												</tr>

											</thead>
											<tbody>
												<c:forEach items="${alumList}" var="alum" varStatus="count">
													<tr>
													<td align="center"><input type="checkbox" class="chk" name="alumni"
														id="alumnis${count.index+1}" value="${alum.almAssocActId}"/></td> 
														<td align="center">${count.index+1}</td>
														<td align="left">${alum.alumniMeetngAgnda}</td>
														<td align="center">${alum.dateOfMeeting}</td>
														<td align="center">${alum.nameAlumniAssoc}</td>
														<td align="center">${alum.alumniRegNo}</td>
														<td align="center">${alum.dateAlumniReg}</td>
														<td align="center">${alum.noAlumniReg}</td>	
														<td align="center">${alum.noMemberAttended}</td>
														<td align="left">${alum.ttlNoAlumniEnrolled}</td>

														<td align="center"><c:if test="${editAccess==0}">
																<a href="${pageContext.request.contextPath}/editAlumAlumniAssocAct/${alum.almAssocActId}"><span
																	class="glyphicon glyphicon-edit" title="Edit"
																	data-animate=" animated fadeIn " rel="tooltip"></span></a>&nbsp;&nbsp;&nbsp;&nbsp; </c:if>
															<c:if test="${deleteAccess==0}">
																<a
																	href="${pageContext.request.contextPath}/deleteAlumniAssocAct/${alum.almAssocActId}/<%out.println(hashtext);%>"
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
										<c:if test="${deleteAccess==0}">
														<button class="btn btn-primary" id="deleteId"
															onClick="var checkedVals = $('.chk:checkbox:checked').map(function() { return this.value;}).get();checkedVals=checkedVals.join(',');if(checkedVals==''){alert('No Rows Selected');return false;	}else{   return confirm('Are you sure want to delete record');}"
															style="align-content: center; width: 113px; margin-left: 40px;">
															<i class="${sessionScope.deleteIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Delete
														</button>
													</c:if>
									</div>
									<input type="hidden" id="edit_alum_id" name="edit_alum_id"
										value="0">


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
	<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
 <script>

   function selectedInst(source) {

		checkboxes = document.getElementsByName('alumni');

		for (var i = 0, n = checkboxes.length; i < n; i++) {
			checkboxes[i].checked = source.checked;

		}

	}
   </script>
	<script>
		function clearSessionAttribute() {

			$.getJSON('${clearSessionAttribute}', {

				ajax : 'true',

			}, function(data) {

			});

		}
	</script>
	
</body>
</html>
