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
							
						
								<%-- <a href="${pageContext.request.contextPath}/showInstProf"><button
										type="button" class="btn btn-success">Add</button></a> --%>
							
					 <a title="Add"
											href="${pageContext.request.contextPath}/showInstProf"><button
												type="button" class="btn btn-success"><i class="${sessionScope.addIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Add</button></a>		
								
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
										
												<th style="text-align: center; width:10%">Sr No</th>
												<th style="text-align: center; "> Date of Estabilishment</th>
												<th style="text-align: center; ">Name</th>
											    <th style="text-align: center; ">Fax No.</th>
												<th style="text-align: center; ">Mobile No.</th>
												<th style="text-align: center; ">Email</th>
												<th style="text-align: center; width:10%">Action</th>
											</tr>
										</thead>

										
										
											<tbody>
									
													<tr>
															<c:if test="${instProfRes!=null}">
														<td style="text-align: center; ">1</td>
														<td style="text-align: center; ">${date}</td>
														<td>${instProfRes.iqacAltName}</td>
														<td style="text-align: center; ">${instProfRes.iqacAltFax}</td>
														
														<td style="text-align: center; ">${instProfRes.iqacAltMobile}</td>
														<td>${instProfRes.iqacAltEmail1}</td>


														<td style="text-align: center; ">
													  <c:if test="${editAccess == 0}"> 
														<a
															href="${pageContext.request.contextPath}/showEditInstProf"><span
																class="glyphicon glyphicon-edit" title="Edit"  title="Edit" data-original-title="Edit"
																data-animate=" animated fadeIn " ></span></a>
																</c:if> &nbsp;&nbsp;&nbsp;&nbsp;
																  <%-- <c:if test="${deleteAccess == 0}"> &nbsp;&nbsp;&nbsp;&nbsp;
															<a
															href="${pageContext.request.contextPath}/deleteInstProf/${instProfRes.iqacInfoId}"
															onClick="return confirm('Are you sure want to delete this record');"
															rel="tooltip" data-color-class="danger"
															data-animate=" animated fadeIn " data-toggle="tooltip"
															data-original-title="Delete  record"><span
																class="glyphicon glyphicon-remove"></span></a> --%>
															</td>
																</c:if>
													</tr>
										
											</tbody>
									</table>
					
	                              <div class="col-lg-1">
<%--   <c:if test="${deleteAccess == 0}">
											<input type="submit" class="btn btn-primary" value="Delete"
												id="deleteId"
												onClick="var checkedVals = $('.chk:checkbox:checked').map(function() { return this.value;}).get();checkedVals=checkedVals.join(',');if(checkedVals==''){alert('No Rows Selected');return false;	}else{   return confirm('Are you sure want to delete record');}"
												style="align-content: center; width: 113px; margin-left: 40px;"></c:if> --%>
													<input type="hidden" id="edit_stud_id"   name="edit_stud_id" value="0">


										</div>


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
	
	<script>
		function clearSessionAttribute() {

			$.getJSON('${clearSessionAttribute}', {

				ajax : 'true',

			}, function(data) {

			});

		}

		function selectedInst(source) {

			checkboxes = document.getElementsByName('studIds');

			for (var i = 0, n = checkboxes.length; i < n; i++) {
				checkboxes[i].checked = source.checked;

			}

		}
		function showEditStudent(studId){
			document.getElementById("edit_stud_id").value=studId;//create this 
			var form=document.getElementById("libListForm");
		    form.setAttribute("method", "post");

			form.action=("showEditStudent");
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
