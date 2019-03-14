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
								<a href="${pageContext.request.contextPath}/showAddAlumini"><button
										type="submit" class="btn btn-success">Add Alumini
										Detail</button></a>


							</div>

						</header>
						<form
							action="${pageContext.request.contextPath}/deleteAlum/0"
							method="get" id="insListForm">
						<div class="content-body">
							<div class="row">

								<div class="col-xs-12"></div>
								<div class="col-xs-12">

									<table class="table table-striped dt-responsive display"
										id="example-1">
										<thead>
											<tr>
												<th>Sr.No.</th>
												<th>Name of Alumni</th>
												<th>Passing Year</th>
												<th>Nature of Contribution</th>
												<th>Year of Contribution</th>
												<th>Benefit To</th>
												<th>Action</th>
											</tr>

										</thead>
										<tbody>
											<c:forEach items="${alumList}" var="alum" varStatus="count">
												<tr>
													<td>${count.index+1}</td>
													<td>${alum.alumniName}</td>
													<td>${alum.passingYear}</td>
													<c:if test="${alum.contributionType==1}">
														<td>Financial</td>
													</c:if>
													<c:if test="${alum.contributionType==0}">
														<td>Non Financial</td>
													</c:if>

													<td>${alum.contributionYear}</td>
													<td>${alum.benefitTo}</td>

													<td align="center"><%-- <c:if test="${editAccess==0}"> --%>
															<a href="#"
																onclick="showEditAlum(${alum.alumniDetailId})"><span
																class="glyphicon glyphicon-edit" title="Edit"
																data-animate=" animated fadeIn " rel="tooltip"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;<%-- </c:if> --%>
														<%-- <c:if test="${deleteAccess==0}"> --%>
															<a
																href="${pageContext.request.contextPath}/deleteAlum/${alum.alumniDetailId}"
																onClick="return confirm('Are you sure want to delete this record');"
																rel="tooltip" data-color-class="danger" title="Delete"
																data-animate=" animated fadeIn " data-toggle="tooltip"
																data-original-title="Delete  record"><span
																class="glyphicon glyphicon-remove"></span></a>
														<%-- </c:if> --%></td>

												</tr>
											</c:forEach>

										</tbody>

									</table>
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
	
	<script type="text/javascript">
	function getData() {
	//alert("hii");
			var i = parseInt(document.getElementById("index").value);
		var name=document.getElementById("name").value
		var academicYear=document.getElementById("academicYear").value
		var year=document.getElementById("passYear").value
		var nature=document.getElementById("nature").value
		var year1=document.getElementById("year1").value
		
		
		var package1=document.getElementById("level").value
		
		var otherScheme=document.getElementById("otherScheme").value
		//alert(stud);
		var temp;
		if (package1 == 7) {

			temp=otherScheme;
			//alert(temp);
		} 
		else{
			temp=package1;
		}
		
		
		//alert(stud);
		
		var dataTable = $('#example-1')
		.DataTable();
		
		dataTable.row
		.add(
				[
					i+1,
					academicYear,
					name,
					year,
					nature,
					year1,
					temp
					
						 ])
		.draw();
		
		
		document.getElementById("index").value = i + 1;
	}
	
	function showForm() {
		//document.getElementById("abc").style = "display:none"
			var qualType=document.getElementById("level").value
			//alert("qualType::"+qualType);
			
			if (qualType == 7) {
				document.getElementById("abc").style = "visible"
			} 
			else{
				document.getElementById("abc").style = "display:none"
			}
		}
	function hideText() {
		//alert("hii");
		document.getElementById("abc").style = "display:none"
			
		
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
		
		function showEditAlum(almId){
			document.getElementById("edit_alum_id").value=almId;//create this 
			var form=document.getElementById("insListForm");
		    form.setAttribute("method", "post");

			form.action=("showEditAlum");
			form.submit();
			
		}
	</script>
</body>
</html>
