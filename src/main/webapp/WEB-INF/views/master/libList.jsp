<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


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
								  <a href="${pageContext.request.contextPath}/showRegLib">
								  <button type="button" class="btn btn-success">Register Librarian 
								  </button></a> 
			                	  </c:if>
								
								<!--  <a class="box_setting fa fa-cog" data-toggle="modal" href="#section-settings"></a>
                    <a class="box_close fa fa-times"></a> -->

							</div>

						</header>
						<form action="${pageContext.request.contextPath}/deleteLibrarians/0"
							method="get" id="libListForm">
							<div class="content-body">
								<div class="row">
									<c:if test="${sessionScope.successMsg!=null}">
										<div class="col-lg-12">
											<div class="alert alert-success alert-dismissible fade in">
												<button type="button" class="close" data-dismiss="alert"
													aria-label="Close">
													<span aria-hidden="true">×</span>
												</button>
												<strong>Success : </strong> ${sessionScope.successMsg}
											</div>
										</div>
									</c:if>

									<div class="col-xs-12">


										<table id="example-1"
											class="table table-striped dt-responsive display">
											<thead>
												<tr>
												<!-- 	<th class="check" style="text-align: center; width: 5%;"><input
														type="checkbox" name="selAll" id="selAll"
														onClick="selectedInst(this)" /> Select All</th> -->
													<th style="text-align: center; width:5%">Sr No</th>
													<th style="text-align: center; ">Librarian Name</th>
													<th style="text-align: center; ">Contact No</th>
													<th style="text-align: center; ">Email</th>
													
														<th style="text-align: center; ">Date of Joining</th>
														<th style="text-align: center; ">Date of Relieving</th>
													<th style="text-align: center; width:10%">Action</th>
												</tr>
											</thead>

											

											<tbody>
												<c:forEach items="${libtList}" var="institute"
													varStatus="count">
													<tr>
										<%-- 	<td>input type="checkbox" class="chk" name="studIds"
															id="studIds${count.index+1}"
															value="${institute.librarianId}" /></td> --%>
														
														<td>${count.index+1}</td>
														<td>${institute.librarianName}</td>
														<td style="text-align: center; ">${institute.contactNo}</td>
														<td >${institute.email}</td>
															<td style="text-align: center; ">${institute.joiningDate}</td>
															<td style="text-align: center; ">${institute.realivingDate}</td>


														<td  align="center">
													    <c:if test="${editAccess == 0}"> 
							                       	  	<a
															href="#" onclick="showEditLibrarian(${institute.librarianId})"><span
																class="glyphicon glyphicon-edit"
																data-animate=" animated fadeIn " rel="tooltip"></span></a>
			                	                       </c:if>&nbsp;&nbsp;&nbsp;&nbsp;
														
												  <c:if test="${deleteAccess == 0}"> 
															<a
															href="${pageContext.request.contextPath}/deleteLibrarians/${institute.librarianId}"
															onClick="return confirm('Are you sure want to delete this record');"
															rel="tooltip" data-color-class="danger"
															data-animate=" animated fadeIn " data-toggle="tooltip"
															data-original-title="Delete  record"><span
																class="glyphicon glyphicon-remove"></span></a>
																</c:if>
																
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
										<div class="col-lg-1">

                                                  <c:if test="${deleteAccess == 0}"> 
											<input type="submit" class="btn btn-primary" value="Delete"
												id="deleteId"
												onClick="var checkedVals = $('.chk:checkbox:checked').map(function() { return this.value;}).get();checkedVals=checkedVals.join(',');if(checkedVals==''){alert('No Rows Selected');return false;	}else{   return confirm('Are you sure want to delete record');}"
												style="align-content: center; width: 113px; margin-left: 40px;"></c:if>
													<input type="hidden" id="edit_lib_id"   name="edit_lib_id" value="0">


										</div>

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
		function showEditLibrarian(instId){
			document.getElementById("edit_lib_id").value=instId;//create this 
			var form=document.getElementById("libListForm");
		    form.setAttribute("method", "post");

			form.action=("showEditLibrarian");
			form.submit();
			
		}
	</script>
</body>
</html>
