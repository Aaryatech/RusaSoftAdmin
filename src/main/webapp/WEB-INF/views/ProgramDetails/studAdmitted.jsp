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

			<%-- 	<div class='col-xs-12'>
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
								<a href="${pageContext.request.contextPath}/showAddStudAdmitCatWise"><button
										type="button" class="btn btn-success">Add Student Categorywise</button></a>
								<%-- <a href="${pageContext.request.contextPath}/sectionTreeList"><button
										type="button" class="btn btn-success">Add CMS Content</button></a> --%>
								
								<!--  <a class="box_setting fa fa-cog" data-toggle="modal" href="#section-settings"></a>
                    <a class="box_close fa fa-times"></a> -->

							</div>
						</header>
						<div class="content-body">
							<div class="row">
								<div class="col-xs-12">
									<table class="table table-striped dt-responsive display" id="example-1">
										<thead>
											<tr>
												<th >Sr No</th>
												<th >Category</th>
												<th >Male Students</th>
												<th >Female Students</th>
												<th >Transgender Students</th>
												<!-- <th >Action</th> -->
											</tr>
										</thead>
										<tbody>
										<c:forEach items="${studAdmCastList}" var="studAdm"
											varStatus="count">
											<tr>
												<td>${count.index+1}</td>
												<td>${studAdm.castName}</td>
												<td>${studAdm.maleStudent}</td>
												<td>${studAdm.femaleStudent}</td>
												<td>${studAdm.transStudent}</td>

												<%-- <td align="center">
													<c:if test="${editAccess==0}"> <a href="#"
													onclick="showEditAlum(${studAdm.studentCatId})"><span
														class="glyphicon glyphicon-edit" title="Edit"
														data-animate=" animated fadeIn " rel="tooltip"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
													<c:if test="${deleteAccess==0}"> <a
													href="${pageContext.request.contextPath}/deleteAlum/${studAdm.studentCatId}"
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

<%-- <div aria-hidden="true" role="dialog" tabindex="-1" id="myModal"
		class="modal fade" style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button aria-hidden="true" data-dismiss="modal" class="close"
						type="button">×</button>
					<h4 class="modal-title">Student Details</h4>
				</div>
				<div class="modal-body">
					<form role="form"
						action="${pageContext.request.contextPath}/showModuleForm"
						method="get">
						<input type="hidden" class="form-control" id="pageId"
							name="pageId" >
							
								
						
									<div class="form-group">
						<label class="control-label col-sm-2" for="page_name">Category</label> <select
								id="cat" name="sem" class="form-control" onchange="showForm()" required>
								<option value="SC">SC</option>
								<option value="ST">ST</option>
								<option value="OBC">OBC</option>
								<option value="VJ">VJ</option>
								<option value="NT">NT-I,NT-II,NT-III</option>
								<option value="General">General</option>
							
							


							</select>
						</div>
						
						
						
									<div class="form-group">
						<label class="control-label col-sm-2" for="page_name">Gender</label> <select
								id="gen" name="sem" class="form-control" onchange="showForm()" required>
								<option value="Male">Male</option>
								<option value="Female">Female</option>
								

							</select>
						</div>
						
						<div class="form-group">
						
							<label class="control-label col-sm-6" for="page_name">No. of Students
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="stud"
									name="subTaut" value="${page.pageName}"
									required>
									
										<input type="hidden" id="index" name="index" value="0">
							<!-- </div> -->
	</div>
						
		

						<button type="submit" class="btn btn-primary" onclick="getData()">Submit</button>
					<!-- </form> -->
				</div>
			</div>
		</div>
	</div> --%>
	
	<script type="text/javascript">
	function getData() {
	//alert("hii");
		//var i=0;
		var i = parseInt(document.getElementById("index").value);
			
		//alert(i);
		var cat=document.getElementById("cat").value
		var gen=document.getElementById("gen").value
		var stud=document.getElementById("stud").value
	
		
		var dataTable = $('#example-1')
		.DataTable();
		
		dataTable.row
		.add(
				[
					i+1,
					cat,
					gen,
					stud,
					
						 ])
		.draw();
		
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
