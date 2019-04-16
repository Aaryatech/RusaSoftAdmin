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
									<a
										href="${pageContext.request.contextPath}/showAddStudAddmitLocWise"><button
											type="button" class="btn btn-success"><i class="${sessionScope.addIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Add</button></a>
								</c:if>
								<%-- <a href="${pageContext.request.contextPath}/sectionTreeList"><button
										type="button" class="btn btn-success">Add CMS Content</button></a> --%>

								<!--  <a class="box_setting fa fa-cog" data-toggle="modal" href="#section-settings"></a>
                    <a class="box_close fa fa-times"></a> -->

							</div>

						</header>
						<div class="content-body">
							<div class="row">

								<div class="col-xs-12">
									<table class="table table-striped dt-responsive display"
										id="example-1">
										<thead>
											<tr>
												<th>Sr No</th>
												<th>Location Name</th>
												<th>Male Students</th>
												<th>Female Students</th>
												<th>Transgender Students</th>
												<th>Total Students</th>
												<!-- <th>Action</th> -->
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${locAdmList}" var="studAdm"
												varStatus="count">
												<tr>
													<td align="center">${count.index+1}</td>
													<td align="left">${studAdm.locationName}</td>
													<td align="right">${studAdm.maleStudent}</td>
													<td align="right">${studAdm.femaleStudent}</td>
													<td align="right">${studAdm.transStudent}</td>
													<td align="right">${studAdm.maleStudent+studAdm.femaleStudent+studAdm.transStudent}</td>

													<%-- <td align="center">
														<c:if test="${editAccess==0}"> <a href="#"
														onclick="showEditAlum(${studAdm.studentLocId})"><span
															class="glyphicon glyphicon-edit" title="Edit"
															data-animate=" animated fadeIn " rel="tooltip"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
														<c:if test="${deleteAccess==0}"> <a
														href="${pageContext.request.contextPath}/deleteAlum/${studAdm.studentLocId}"
														onClick="return confirm('Are you sure want to delete this record');"
														rel="tooltip" data-color-class="danger" title="Delete"
														data-animate=" animated fadeIn " data-toggle="tooltip"
														data-original-title="Delete  record"><span
															class="glyphicon glyphicon-remove"></span></a> </c:if>
													</td> --%>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									<!-- <div class="form-group">
										<div class="col-sm-offset-2 col-sm-10">
											<button type="submit" class="btn btn-primary">Submit</button>
											<button type="reset" class="btn btn-default">Reset</button>
										</div>
									</div> -->
								</div>

							</div>
						</div>
					</section>
				</div>

				<!-- MAIN CONTENT AREA ENDS -->
			</section>
		</section>
	</div>
	<!-- END CONTENT -->

	<script type="text/javascript">
		function getData() {
			//alert("hii");
			//var i=0;
			var i = parseInt(document.getElementById("index").value);
			//alert(i);
			var cat = document.getElementById("cat").value
			var gen = document.getElementById("gen").value
			var stud = document.getElementById("stud").value
			var dataTable = $('#example-1').DataTable();
			dataTable.row.add([ i + 1, cat, gen, stud, ]).draw();
			document.getElementById("index").value = i + 1;
		}
	</script>
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
	</script>
</body>
</html>
