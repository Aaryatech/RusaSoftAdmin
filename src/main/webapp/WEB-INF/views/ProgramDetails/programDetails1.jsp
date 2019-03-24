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

								<c:if test="${isAdd==1}">
									<a href="${pageContext.request.contextPath}/showAddProgram"><button
											type="button" class="btn btn-success">Add</button></a>
								</c:if>


							</div>
						</header>
						<div class="content-body">
							<div class="row">

								<div class="col-xs-12">


									<div class="form-group">
										<table class="table table-striped dt-responsive display"
											id="example-1">
											<thead>
												<tr>
													<th>Sr No</th>
													<th>Name of Program</th>
													<th>Duration Months</th>
													<th>Sanctioned Intake</th>
													<th>Date/Year of Introduction</th>
													<th>Approved by</th>
													<th>Action</th>
												</tr>
											</thead>

											<c:forEach items="${list}" var="list" varStatus="count">
												<tr>

													<td>${count.index+1}</td>
													<td>${list.nameOfProgram}</td>
													<td>${list.monthDuration}</td>
													<td>${list.sanctionalIntake}</td>
													<td>${list.dateOfIntroduction}</td>
													<td>${list.approvedBy}</td>

													<td><c:if test="${isAdd==1}">
															<a
																href="${pageContext.request.contextPath}/addProgramDetail/${list.programId}"
																title="Add Details" rel="tooltip"
																data-color-class="detail"
																data-animate=" animated fadeIn " data-toggle="tooltip"
																data-original-title="Block"><span
																class="glyphicon glyphicon-list"></span></a>&nbsp;&nbsp;
														</c:if> <c:if test="${isEdit==1}">
															<a
																href="${pageContext.request.contextPath}/editProgram/${list.programId}"><span
																class="glyphicon glyphicon-edit" title="Edit"
																data-animate=" animated fadeIn " rel="tooltip"></span></a>&nbsp;&nbsp;
															</c:if> <c:if test="${isDelete==1}">
															<a
																href="${pageContext.request.contextPath}/deleteProgram/${list.programId}"
																onClick="return confirm('Are you sure want to delete this record');"
																rel="tooltip" data-color-class="danger" title="Delete"
																data-animate=" animated fadeIn " data-toggle="tooltip"
																data-original-title="Delete  record"><span
																class="glyphicon glyphicon-remove"></span></a>

														</c:if></td>
												</tr>
											</c:forEach>

										</table>

									</div>
								</div>
								<!-- <div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										<button type="submit" class="btn btn-primary">Submit</button>
										<button type="reset" class="btn btn-default">Reset</button>
									</div>
								</div> -->




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



	<%-- 	
	<div aria-hidden="true" role="dialog" tabindex="-1" id="myModal1"
		class="modal fade" style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button aria-hidden="true" data-dismiss="modal" class="close"
						type="button">×</button>
					<h4 class="modal-title">Program Details</h4>
				</div>
				<div class="modal-body">
					<form role="form"
						action="${pageContext.request.contextPath}/showModuleForm"
						method="get">
						<input type="hidden" class="form-control" id="pageId"
							name="pageId" >
							
							
								<input type="hidden" class="form-control" id="count"
							name="count"  value="0">
								
							<div class="form-group">
						<label class="control-label col-sm-3" for="finantialYear">Academic
							Year</label> <select id="academicYear2" name="academicYear2"
							class="form-control" required>
							<option value="2018-2019">2018-2019</option>
							<option value="2017-2018">2017-2018</option>
							<option value="2016-2017">2016-2017</option>
	<option value="2015-2016">2015-2016</option>
						</select>
					</div>
							<div class="form-group">
						<label class="control-label col-sm-6" for="page_name">Program Type</label> <select
								id="type" name="ug" class="form-control"  onchange="showForm1()" required>
								
								<option value="Certificate">Certificate</option>
								<option value="UG">UG</option>
								<option value="PG">PG</option>
								<option value="Diploma">Diploma</option>
								<option value="Ph.D.">Ph.D.</option>
								<option value="Post Doc.">Post Doc.</option>
								<option value="7">Any Other</option>
								
							
							</select>
						</div>
						
								<div class="form-group" id="xyz">
						
							<label class="control-label col-sm-6" for="page_name">Other Type
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="otherType"
									name="otherType" value="${page.pageName}" placeholder="Other Type"
									required>
							<!-- </div> -->
	</div>
						<div class="form-group">
						
							<label class="control-label col-sm-6" for="page_name">Duration(in months)
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="duration" placeholder="Duration(in months)"
									name="code" value="${page.pageName}"
									required>
							<!-- </div> -->
	</div>
								
						<div class="form-group">
						
							<label class="control-label col-sm-6" for="page_name">Name of Program
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="name" placeholder="Name of Program"
									name="code" value="${page.pageName}"
									required>
							<!-- </div> -->
	</div>
	
	<div class="form-group">
						
							<label class="control-label col-sm-6" for="page_name">Sanctioned Intake
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="sancIntake" placeholder="Sanctioned Intake"
									name="sancIntake" value="${page.pageName}"
									required>
							<!-- </div> -->
	</div>
	
	
	<div class="form-group">
						
							<label class="control-label col-sm-6" for="page_name">Admitted Student
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="admitStud" placeholder="Admitted Student"
									name="admitStud" value="${page.pageName}"
									required>
							<!-- </div> -->
	</div>
			
						<div class="form-group">
						
							<label class="control-label col-sm-8" for="page_name">Date/Year of Introduction
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="date" class="form-control" id="date" placeholder="Date/Year of Introduction"
									name="code" value="${page.pageName}"
									required>
							<!-- </div> -->
	</div>
						
				
						
						
							<div class="form-group">
						<label class="control-label col-sm-6" for="page_name">Approved By</label> <select
								id="approve" name="approve" class="form-control" onchange="showForm()" required>
								
								<option value="BOS/AC">BOS/AC</option>
															<option value="Industry">Industry</option>
															<option value="AICTE">AICTE</option>
															<option value="NCTE">NCTE</option>
															<option value="MCI">MCI</option>
															<option value="DCI">DCI</option>
															<option value="PCI">PCI</option>
															<option value="7">Any Other</option>
						
								
							
							</select>
						</div>
						<div class="form-group" id="abc">
						
							<label class="control-label col-sm-6" for="page_name">Other Approval
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="otherApproval"
									name="otherApproval" value="${page.pageName}" placeholder="Other Approval"
									required>
							<!-- </div> -->
	</div>
					
					

						<button type="submit" class="btn btn-primary" onclick="getData1()">Submit</button>
					<!-- </form> -->
				</div>
			</div>
		</div>
	</div>
	 --%>
	<script type="text/javascript">
		function getData1() {

			var i = parseInt(document.getElementById("count").value);
			var year = document.getElementById("academicYear2").value;
			var type = document.getElementById("type").value
			var sancIntake = document.getElementById("sancIntake").value
			//var admitStud=document.getElementById("admitStud").value
			//alert(vision);

			var duration = document.getElementById("duration").value
			//alert(mission);
			var name = document.getElementById("name").value
			//alert(po);

			var date = document.getElementById("date").value
			//alert(ug);
			var approve = document.getElementById("approve").value
			var otherApproval = document.getElementById("otherApproval").value
			var otherType = document.getElementById("otherApproval").value
			//alert(approve);

			var temp;
			if (type == 7) {

				temp = otherType;
				//alert(temp);
			} else {
				temp = type;
			}
			var x = temp.append()
			var temp1;
			if (approve == 7) {

				temp1 = otherApproval;
				//alert(temp);
			} else {
				temp1 = approve;
			}

			var dataTable = $('#example-1').DataTable();

			dataTable.row.add(
					[ i + 1, year, temp(name), duration, sancIntake, date,
							temp1

					]).draw();

			document.getElementById("count").value = i + 1;
		}

		function showForm() {
			//document.getElementById("abc").style = "display:none"
			var qualType = document.getElementById("approve").value
			//alert("qualType::"+qualType);

			if (qualType == 7) {

				document.getElementById("abc").style = "visible"

			} else {
				document.getElementById("abc").style = "display:none"
			}

		}

		function showForm1() {
			//document.getElementById("abc").style = "display:none"
			var qualType = document.getElementById("type").value
			//alert("qualType::"+qualType);

			if (qualType == 7) {

				document.getElementById("xyz").style = "visible"

			} else {
				document.getElementById("xyz").style = "display:none"
			}

		}
		function hideText() {
			//alert("hii");
			document.getElementById("abc").style = "display:none"
			document.getElementById("xyz").style = "display:none"

		}
	</script>

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
<!-- Title of administrative training program organized for non-teaching staff -->
