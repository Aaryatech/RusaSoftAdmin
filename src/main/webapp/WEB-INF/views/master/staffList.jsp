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
								   <c:if test="${addAccess == 0}"> 
								<a href="${pageContext.request.contextPath}/showRegisterStaff"><button
										type="button" class="btn btn-success">Register Staff</button></a>
										</c:if>
								<a class="box_toggle fa fa-chevron-down"></a>
								<!--  <a class="box_setting fa fa-cog" data-toggle="modal" href="#section-settings"></a>
                    <a class="box_close fa fa-times"></a> -->

							</div>

						</header>
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
											<th class="check" style="text-align: center; width: 5%;"><input
														type="checkbox" name="selAll" id="selAll"
														onClick="selectedInst(this)" /> Select All</th>
												<th width="5%">Sr No</th>
												<th>Faculty Name</th>
												<th>Qualification</th>
												<th>Department</th>
												<th>Joining Date</th>
												<th>Relieving Date</th>
												<th>Contact No</th>
												<th>Email</th>
												<th width="10%">Action</th>
											</tr>
										</thead>

										

										<tbody>
											<c:forEach items="${staffList}" var="staffList" varStatus="count">
												<tr>
												<td><input type="checkbox" class="chk" name="staffIds"
															id="staffIds${count.index+1}" value="${staffList.facultyId}" /></td>
											<td style="text-align: center">${count.index+1}</td>
											
											<td style="text-align: left"><c:out
														value="${staffList.facultyName}" /></td>
														
											<td style="text-align: left"><c:out
														value="${staffList.qualificationName}" /></td>
											
											<td style="text-align: left"><c:out
														value="${staffList.deptName}" /></td>
														
											<td style="text-align: left"><c:out
														value="${staffList.joiningDate}" /></td>
														
											<td style="text-align: left"><c:out
														value="${staffList.realivingDate}" /></td>
														
											<td style="text-align: left"><c:out
														value="${staffList.contactNo}" /></td>
														
											<td style="text-align: left"><c:out
														value="${staffList.email}" /></td>
														
											
												<td><%--  <a
											href="${pageContext.request.contextPath}/showFacultyDetails" title="Add Student"
											 rel="tooltip" data-color-class = "detail" data-animate=" animated fadeIn " data-toggle="tooltip" data-original-title="Add HOD"><span
												class="glyphicon glyphicon-list"></span></a> --%>
											<c:if test="${editAccess == 0}">	<a
											href="${pageContext.request.contextPath}/editFaculity/${staffList.facultyId}" title="Edit"
											 rel="tooltip" data-color-class = "detail" data-animate=" animated fadeIn " data-toggle="tooltip" data-original-title="Edit"><span
												class="glyphicon glyphicon-edit"></span></a></c:if> | 
												
											<c:if test="${deleteAccess == 0}">	<a
											href="${pageContext.request.contextPath}/deleteFaculity/${staffList.facultyId}" 
											onClick="return confirm('Are you sure want to delete this record');" title="Block"
											 rel="tooltip" data-color-class = "detail" data-animate=" animated fadeIn " data-toggle="tooltip" data-original-title="Block"><span
												class="glyphicon glyphicon-remove"></span></a></c:if>
												</td>
											
											
											
											
											</tr>
											</c:forEach>
										</tbody>
									</table>
									<div class="form-group">
															<div class="col-sm-offset-2 col-sm-10">

								<button type="button" class="btn btn-primary" onclick="exportToExcel();"  id="expExcel" 
									style="align-content: center; width: 200px; margin-left: 80px; background-color: #272c33;">
									Export To Excel</button>
									
									<button type="button" class="btn btn-primary"
										onclick="genPdf()" id="PDFButton"
										style="align-content: center; width: 100px; margin-left: 80px; background-color: #272c33;">
										PDF</button>
							</div>
							</div>

									<c:if test="${deleteAccess==0}">
											<input type="submit" class="btn btn-primary" value="Delete"
												id="deleteId"
												onClick="var checkedVals = $('.chk:checkbox:checked').map(function() { return this.value;}).get();checkedVals=checkedVals.join(',');if(checkedVals==''){alert('No Rows Selected');return false;	}else{   return confirm('Are you sure want to delete record');}"
												style="align-content: center; width: 113px; margin-left: 40px;">
										</c:if>
										<input type="hidden" id="edit_accOff_id" name="edit_accOff_id"
											value="0">
											
											
						
							

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
	<script type="text/javascript">
		function exportToExcel() {

			window.open("${pageContext.request.contextPath}/exportToExcel");
			document.getElementById("expExcel").disabled = true;
		}
	
		function genPdf() {
			//alert("hiii");
			
			window.open("${pageContext.request.contextPath}/showFacultyPdf");
			document.getElementById("expExcel").disabled = true;

		}
	</script>
	<script>
		function clearSessionAttribute() {

			$.getJSON('${clearSessionAttribute}', {

				ajax : 'true',

			}, function(data) {

			});

		}

		function selectedInst(source) {

			checkboxes = document.getElementsByName('staffIds');

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
