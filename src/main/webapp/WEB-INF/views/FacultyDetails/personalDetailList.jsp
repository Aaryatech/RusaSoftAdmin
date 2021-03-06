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
							<%-- <c:if test="${addAccess == 0}">
						<a href="${pageContext.request.contextPath}/addPersonalDetails"><button
										type="button" class="btn btn-success">Add Faculty Personal Details</button></a></c:if>  --%>
							
							</div>

						</header>
						<div class="content-body">
							<div class="row">
							<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertPublicationDetail"
										method="post" 
										name="form_sample_2" id="form_sample_2"
										onsubmit="return confirm('Do you really want to submit the form?');">



								<div class="col-xs-12"></div>
								<div class="col-xs-12">

									<table class="table table-striped dt-responsive display" id="example-1">
										<thead>
											<tr>
												<th >Sr.No.</th>
												<th>Name of Faculty</th>
												<th>Contact No.</th>
												<th>Email ID</th>
												<th>Designation</th>
												<th>Total Experience</th>
												<th>Action</th>
											</tr>
											
											<tbody>
											<c:forEach items="${facPerDetList}" var="staffList" varStatus="count">
												<tr>
												<%-- <td><input type="checkbox" class="chk" name="staffIds"
															id="staffIds${count.index+1}" value="${staffList.facultyId}" /></td> --%>
											<td style="text-align: center">${count.index+1}</td>
											
											<td style="text-align: left"><c:out
														value="${staffList.facultyName}" /></td>
														
											<td style="text-align: center"><c:out
														value="${staffList.contactNo}" /></td>
														
											<td style="text-align: left"><c:out
														value="${staffList.email}" /></td>
														
														<td style="text-align: left"><c:out
														value="${staffList.designationName}" /></td>
														
											<td style="text-align: right"><c:out
														value="${staffList.currentExperience+staffList.fPastExp}" /></td>
														
											
												<td align="center"> <%-- <a
											href="${pageContext.request.contextPath}/showFacultyDetails" title="Add Student"
											 rel="tooltip" data-color-class = "detail" data-animate=" animated fadeIn " data-toggle="tooltip" data-original-title="Add HOD"><span
												class="glyphicon glyphicon-list"></span></a> --%>
											<c:if test="${editAccess == 0}">
											<a
																						href="#" onclick="showEdit(${staffList.facultyId})"
																						title="Edit" rel="tooltip"
																						data-color-class="detail"
																						data-animate=" animated fadeIn "
																						data-toggle="tooltip" data-original-title="Edit"><span
																						class="glyphicon glyphicon-edit"></span></a></c:if> 
												
											<%-- <c:if test="${deleteAccess == 0}">	<a
											href="${pageContext.request.contextPath}/deleteFaculity/${staffList.facultyId}" title="Block"
											 rel="tooltip" data-color-class = "detail" data-animate=" animated fadeIn " data-toggle="tooltip" data-original-title="Block"><span
												class="glyphicon glyphicon-remove"></span></a></c:if> --%>
												</td>
											
											
											
											
											</tr>
											</c:forEach>
										</tbody>

									</table>
								</div>
																		<input type="hidden" name="add_fac_detail_id" id="add_fac_detail_id" value="0">
																		<input type="hidden" name="title" id="title" value="Edit Faculty Personal Details">
								
</form>
							</div>
							
						</div>
					</section>
				</div>

				<!-- MAIN CONTENT AREA ENDS -->
			</section>
		</section>
		<!-- END CONTENT -->





	</div>
	
	
	
<%-- 
<div aria-hidden="true" role="dialog" tabindex="-1" id="myModal"
		class="modal fade" style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button aria-hidden="true" data-dismiss="modal" class="close"
						type="button">×</button>
					<h4 class="modal-title">Training Details</h4>
				</div>
				<div class="modal-body">
					<form role="form"
						action="${pageContext.request.contextPath}/showModuleForm"
						method="get">
						<input type="hidden" class="form-control" id="pageId"
							name="pageId" >
							
							<input type="hidden" class="form-control" id="index"
							name="index" value="0">
							
								<div class="form-group">
						<label class="control-label col-sm-6" for="academicYear">Academic
							Year</label> <select id="academicYear" name="academicYear"
							class="form-control" required>
							<option value="2018-2019">2018-2019</option>
							<option value="2017-2018">2017-2018</option>
							<option value="2016-2017">2016-2017</option>
								<option value="2015-2016">2015-2016</option>

						</select>
					</div>
					
									
						
						<div class="form-group">
						
							<label class="control-label col-sm-6" for="page_name">Name of Employer
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="empName" placeholder="Name of Employer"
									name="subTaut" value="${page.pageName}"
									required>
							<!-- </div> -->
	</div>
	
	
<div class="form-group">
						<label class="control-label col-sm-6" for="academicYear">Program
							Type</label> <select id="progType" name="progType"
							class="form-control" required>
							<option value="CERTIFICATE">CERTIFICATE</option>
							<option value="DIPLOMA">DIPLOMA</option>
							<option value="UG">UG</option>
								<option value="PG">PG</option>

						</select>
					</div>
	
		<div class="form-group">
						
							<label class="control-label col-sm-6" for="page_name">Name of Program
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="progName"  placeholder="Name of Program"
									name="subTaut" value="${page.pageName}"
									required>
							<!-- </div> -->
	</div>
							
									
						
						<div class="form-group">
						
							<label class="control-label col-sm-6" for="page_name">No. of Students Placed
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="stud"  placeholder="No. of Students Placed"
									name="subTaut" value="${page.pageName}"
									required>
							<!-- </div> -->
	</div>
						
						
						
						<div class="form-group">
						
							<label class="control-label col-sm-6" for="page_name">Address of Employer
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="address"  placeholder="Address of Employer"
									name="subTaut" value="${page.pageName}"
									required>
							<!-- </div> -->
	</div>
						
						<div class="form-group">
						
							<label class="control-label col-sm-6" for="page_name">Contact Details
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="contact"  placeholder="Contact Details"
									name="subTaut" value="${page.pageName}"
									required>
							<!-- </div> -->
	</div>
						
						<div class="form-group">
						
							<label class="control-label col-sm-6" for="page_name">Package Offered
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="package"  placeholder="Package Offered"
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
			var i = parseInt(document.getElementById("index").value);
			var academicYear = document.getElementById("academicYear").value;
		var empName=document.getElementById("empName").value
		var progName=document.getElementById("progName").value
		var stud=document.getElementById("stud").value
		var address=document.getElementById("address").value
		var contact=document.getElementById("contact").value
		var package1=document.getElementById("package").value
	
		var progType=document.getElementById("progType").value
		
		//alert(stud);
		
		var dataTable = $('#example-1')
		.DataTable();
		
		dataTable.row
		.add(
				[
					i+1,
					academicYear,
					empName,
					progType,
					progName,
					stud,
					address,
					contact,
					package1
					
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
	
	<script type="text/javascript">
	
	function showEdit(facId){
		//alert("facId" +facId);
		document.getElementById("add_fac_detail_id").value=facId;//create this 
		var form=document.getElementById("form_sample_2");
	    form.setAttribute("method", "post");

		form.action=("addPersonalDetails");
		form.submit();
		
	}
	
	</script>
</body>
</html>
