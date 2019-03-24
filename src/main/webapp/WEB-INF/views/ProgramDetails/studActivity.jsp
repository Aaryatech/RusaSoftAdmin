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
									<a
										href="${pageContext.request.contextPath}/showAddStudentOrgnizedActivity"><button
											type="submit" class="btn btn-success">Add</button></a>
								</c:if>

							</div>
						</header>
						<div class="content-body">
							<div class="row">




								<div class="col-xs-12"></div>
								<div class="col-xs-12">

									<table class="table table-striped dt-responsive display"
										id="example-1">
										<thead>
											<tr>
												<th>Sr.No.</th>
												<th>Name of Activity</th>
												<th>Date</th>
												<th>Year</th>
												<th>Branch</th>
												<th>No. of Students Participated</th>
												<th>Level of Activity</th>
												<th>Action</th>
											</tr>

										</thead>
										<tbody>
											<c:forEach items="${list}" var="list" varStatus="count">
												<tr>

													<td>${count.index+1}</td>
													<td>${list.activityName}</td>
													<td>${list.date}</td>
													<td>${list.yearName}</td>
													<td>${list.branch}</td>
													<td>${list.participatedStudent}</td>
													<td>${list.level}</td>

													<td><c:if test="${isEdit==1}">
															<a
																href="${pageContext.request.contextPath}/editStudentOrgnizedActivity/${list.studentActivityId}"><span
																class="glyphicon glyphicon-edit" title="Edit"
																data-animate=" animated fadeIn " rel="tooltip"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;
															</c:if> <c:if test="${isDelete==1}">
															<a
																href="${pageContext.request.contextPath}/deleteStudentOrgnizedActivity/${list.studentActivityId}"
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

	<%-- <div aria-hidden="true" role="dialog" tabindex="-1" id="myModal"
		class="modal fade" style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button aria-hidden="true" data-dismiss="modal" class="close"
						type="button">×</button>
					<h4 class="modal-title">Student Activity Details</h4>
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
						<label class="control-label col-sm-6" for="page_name">Name of Activity</label> <select
								id="cat" name="cat" onchange="showForm()" class="form-control"  required>
								<option value="Sports">Sports</option>
								<option value="Cultural">Cultural</option>
								<option value="Curricular">Curricular</option>
								<option value=" Co-curricular"> Co-curricular</option>
								<option value="Extra Curricular ">Extra Curricular </option>
								<option value="7">Other Competition</option>
							
							</select>
						</div>
						
						<div class="form-group" id="abc">
						
							<label class="control-label col-sm-6" for="page_name">Other Competition
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="otherScheme"
									name="otherScheme" value="${page.pageName}" placeholder="Other Competition"
									required>
							<!-- </div> -->
	</div>
	
		<div class="form-group">
						
							<label class="control-label col-sm-3" for="page_name">Date
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="date" class="form-control" id="date"
									name="subTaut" value="${page.pageName}"
									required>
							<!-- </div> -->
	</div>
	
	<div class="form-group">
						<label class="control-label col-sm-6" for="academicYear">
							Year</label> <select id="year" name="year"
							class="form-control" required>
							<option value="2018-2019">2018-2019</option>
							<option value="2017-2018">2017-2018</option>
							<option value="2016-2017">2016-2017</option>
								<option value="2015-2016">2015-2016</option>

						</select>
					</div>
							
									
						
						<div class="form-group">
						
							<label class="control-label col-sm-3" for="page_name">Branch
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="branchName"
									name="subTaut" value="${page.pageName}" placeholder="Branch"
									required>
							<!-- </div> -->
	</div>
						
						
						
						<div class="form-group">
						
							<label class="control-label col-sm-6" for="page_name">No. of Students Participated
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="stud"
									name="subTaut" value="${page.pageName}"  placeholder="No. of Students Participated"
									required>
									<input type="hidden" id="index" name="index" value="0">
							<!-- </div> -->
	</div>
						
									<div class="form-group">
						<label class="control-label col-sm-6" for="page_name">Level of Activity</label> <select
								id="level" name="sem" class="form-control" onchange="showForm()" required>
								<option value="International">International</option>
								<option value="National">National</option>
								<option value="Regional">Regional</option>
								<option value="State">State</option>
								
								
							</select>
						</div>
						
						
		

						<button type="submit" class="btn btn-primary" onclick="getData()">Submit</button>
					<!-- </form> -->
				</div>
			</div>
		</div>
	</div>
	 --%>
	<script type="text/javascript">
		function getData() {
			//alert("hii");
			var i = parseInt(document.getElementById("index").value);
			var academicYear = document.getElementById("academicYear").value
			var year = document.getElementById("year").value
			var cat = document.getElementById("cat").value //act name
			var date = document.getElementById("date").value
			var branch = document.getElementById("branchName").value
			//alert(branch);
			var stud = document.getElementById("stud").value
			var level = document.getElementById("level").value

			var otherScheme = document.getElementById("otherScheme").value
			//alert(stud);
			var temp;
			if (cat == 7) {

				temp = otherScheme;
				//alert(temp);
			} else {
				temp = cat;
			}

			//alert(stud);

			var dataTable = $('#example-1').DataTable();

			dataTable.row.add(
					[ i + 1, academicYear, temp, date, year, branch, stud,
							level

					]).draw();

			document.getElementById("index").value = i + 1;

		}

		function showForm() {
			//document.getElementById("abc").style = "display:none"
			var qualType = document.getElementById("cat").value
			//alert("qualType::"+qualType);

			if (qualType == 7) {

				document.getElementById("abc").style = "visible"

			} else {
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
	</script>
</body>
</html>
