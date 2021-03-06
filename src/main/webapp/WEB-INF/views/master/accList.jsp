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

				<!--   <div class='col-xs-12'>
        <div class="page-title">

            <div class="pull-left">
                PAGE HEADING TAG - START<h1 class="title">HOD List</h1>PAGE HEADING TAG - END                            </div>
			 
                                
        </div>
    </div> -->
				<div class="clearfix"></div>
				<!-- MAIN CONTENT AREA STARTS -->



				<div class="col-lg-12">
					<section class="box ">
						<header class="panel_header">
							<h2 class="title pull-left">${title}</h2>
							<div class="actions panel_actions pull-right">
								<c:if test="${addAccess==0}">
									<a href="${pageContext.request.contextPath}/showRegAcc"><button
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
							action="${pageContext.request.contextPath}/deleteaccOff/0/<%out.println(hashtext);%>"
							method="get" id="insListForm">
							<div class="content-body">
								<div class="row">
									<%-- <c:if test="${sessionScope.successMsg!=null}">
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
													<th class="check" style="text-align: center; width: 5%;"><input
														type="checkbox" name="selAll" id="selAll"
														onClick="selectedInst(this)" /> Select All</th>
													<th>Sr No</th>
													<th>Name</th>
													<th>Department</th>
													<th>Qualification</th>

													<th>Joining Date</th>
													<th>Contact No</th>
													<th>Email</th>
													<th width="10%">Action</th>
												</tr>
											</thead>

											<tbody>
												<c:forEach items="${accOffList}" var="accOff"
													varStatus="count">
													<tr>
														<td align="center"><input type="checkbox" class="chk"
															name="accOffIds" id="accOffIds${count.index+1}"
															value="${accOff.facultyId}" /></td>
														<td align="center">${count.index+1}</td>
														<td>${accOff.facultyFirstName}</td>
														<td>${accOff.deptName}</td>
														<td>${accOff.qualificationName}</td>
														<td align="center">${accOff.joiningDate}</td>
														<td align="center">${accOff.contactNo}</td>
														<td>${accOff.email}</td>
														<td align="center"><c:if test="${editAccess==0}">
																<a
																	href="${pageContext.request.contextPath}/showEditaccOff/${accOff.facultyId}"
																	href="#"><span class="glyphicon glyphicon-edit"
																	title="Edit" data-animate=" animated fadeIn "
																	rel="tooltip"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;
															</c:if> <c:if test="${deleteAccess==0}">
																<a
																	href="${pageContext.request.contextPath}/deleteaccOff/${accOff.facultyId}/<%out.println(hashtext);%>"
																	onClick="return confirm('Are you sure want to delete this record');"
																	rel="tooltip" data-color-class="danger" title="Delete"
																	data-animate=" animated fadeIn " data-toggle="tooltip"
																	data-original-title="Delete  record"><span
																	class="glyphicon glyphicon-remove"></span></a>
																	
																	 &nbsp;&nbsp;
																<a href="#" onclick="blockUser(${accOff.facultyId})"
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
										<c:if test="${deleteAccess==0}">
											<button type="submit" title="delete checked records"
												id="deleteId" class="btn btn-primary"
												onClick="var checkedVals = $('.chk:checkbox:checked').map(function() { return this.value;}).get();checkedVals=checkedVals.join(',');if(checkedVals==''){alert('No Rows Selected');return false;	}else{   return confirm('Are you sure want to delete record');}">
												<i class="${sessionScope.deleteIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Delete
											</button>

										</c:if>
										<input type="hidden" id="edit_accOff_id" name="edit_accOff_id"
											value="0"> <input type="hidden" id="listMapping"
											name="listMapping" value="${listMapping}"> <input
											type="hidden" id="userId" name="userId" value="0"> <input
											type="hidden" value="<%out.println(hashtext);%>" name="token"
											id="token">


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

			checkboxes = document.getElementsByName('accOffIds');

			for (var i = 0, n = checkboxes.length; i < n; i++) {
				checkboxes[i].checked = source.checked;

			}

		}

		function showEditaccOff(accOffId) {
			document.getElementById("edit_accOff_id").value = accOffId;//create this 
			var form = document.getElementById("insListForm");
			form.setAttribute("method", "post");

			form.action = ("showEditaccOff");
			form.submit();

		}
		
		function blockUser(userId) {
			document.getElementById("userId").value = userId;//create this 
			var form = document.getElementById("insListForm");
			form.setAttribute("method", "post");

			form.action = ("blockUser");
			form.submit();

		}
	</script>


</body>
</html>
