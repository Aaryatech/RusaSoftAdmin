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
							<h2 class="title pull-left">Department List</h2>
							<div class="actions panel_actions pull-right">
								<c:if test="${addAccess==0}">
									<a href="${pageContext.request.contextPath}/addFaculty"><button
											type="button" class="btn btn-success"><i class="${sessionScope.addIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Add</button></a>
									
								</c:if>
								<!--  <a class="box_setting fa fa-cog" data-toggle="modal" href="#section-settings"></a>
                    <a class="box_close fa fa-times"></a> -->

							</div>

						</header>
						<form
							action="${pageContext.request.contextPath}/deleteInstitutes/0"
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
													<th width="5%">Sr No</th>
													<th>Department</th>
													<th width="20%">Action</th>
												</tr>
											</thead>

											<!--  <tfoot>
                    <tr>
                    		<th width="5%">Sr No</th>
                        <th>Department</th> 
                        <th width="20%">Action</th>            
                          </tr>
                </tfoot> -->

											<tbody>
												<%-- <c:forEach items="${getPagesModuleList}" var="getPagesModuleList" varStatus="count">
									<tr  >
										<td>${count.index+1}</td>
										<td>${getPagesModuleList.pageName} (${getPagesModuleList.secctionName})</td>
										 
										<td>${getPagesModuleList.content}</td>  
										<td><a
											href="${pageContext.request.contextPath}/editCmsContent/${getPagesModuleList.primaryKeyId}"><span
												class="glyphicon glyphicon-edit" data-animate=" animated fadeIn "
												rel="tooltip" ></span></a> | <a
											href="${pageContext.request.contextPath}/deleteCmsContent/${getPagesModuleList.primaryKeyId}"
											onClick="return confirm('Are you sure want to delete this record');" rel="tooltip" data-color-class = "danger" data-animate=" animated fadeIn " data-toggle="tooltip" data-original-title="Delete  record"><span
												class="glyphicon glyphicon-remove"></span></a></td>
									</tr>
								</c:forEach>   --%>
												<c:forEach items="${deptList}" var="dept" varStatus="count">
													<tr>
														<td align="center">${count.index+1}</td>
														<td>${dept.deptName}</td>

														<td align="center"><c:if test="${editAccess==0}">
																<a href="#" onclick="showEditDept(${dept.deptId})"><span
																	class="glyphicon glyphicon-edit" title="Edit"
																	data-animate=" animated fadeIn " rel="tooltip"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
															<c:if test="${deleteAccess==0}">
																<a
																	href="${pageContext.request.contextPath}/deleteDepts/${dept.deptId}/<%out.println(hashtext);%>"
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



										<input type="hidden" id="edit_dept_id" name="edit_dept_id"
											value="0">

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



function showEditDept(deptId){
	document.getElementById("edit_dept_id").value=deptId;//create this 
	var form=document.getElementById("insListForm");
    form.setAttribute("method", "post");

	form.action=("showEditDept");
	form.submit();
	
}
 </script>
</body>
</html>
