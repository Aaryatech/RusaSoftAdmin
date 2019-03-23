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
<body class=" ">
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

				<div class='col-xs-12'>
					<div class="page-title">

						<div class="pull-left">
							<!-- PAGE HEADING TAG - START -->
							<%-- 	<h1 class="title">${title}</h1> --%>
							<!-- PAGE HEADING TAG - END -->
						</div>


					</div>
				</div>
				<div class="clearfix"></div>
				<!-- MAIN CONTENT AREA STARTS -->



				<div class="col-lg-12">
					<section class="box ">
						<header class="panel_header">
							<h2 class="title pull-left">${title}</h2>
							<div class="actions panel_actions pull-right">
								 
								 <a href="${pageContext.request.contextPath}/showSubDetailsList"><button
										type="button" class="btn btn-info">Back</button></a>  
								<a class="box_toggle fa fa-chevron-down"></a> 
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<form class="form-horizontal"
									action="${pageContext.request.contextPath}/submitSubjectCo"
									method="post" name="submitProgramMission"
									id="submitProgramMission"
									onsubmit="return confirm('Do you really want to add CO?');">
									<div class="col-xs-12">

										<div class="form-group">
											<label class="control-label col-sm-2" for="status">
												Program Name:</label>
											<div class="col-sm-6" for="status">
												<strong> ${programDetail.nameOfProgram} </strong>
											</div>
										</div>

										<div class="form-group">
											<label class="control-label col-sm-2" for="status">
												Duration:</label>
											<div class="col-sm-6" for="status">
												<strong>${programDetail.monthDuration}</strong>
											</div>
										</div>
										<div class="form-group">
											<label class="control-label col-sm-2" for="status">
												Sanctioned Intake:</label>
											<div class="col-sm-6" for="status">
												<strong>${programDetail.sanctionalIntake}</strong>
											</div>


										</div>
										<div class="form-group"></div>
									</div>
									<div class="col-xs-12">

										<div class="form-group">
											<label class="control-label col-sm-2" for="coName">CO
												<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control" id=coName
													name="coName" placeholder="CO NAME"
													value="${editSubjectCo.coName}" value="" required>
											</div>
											<div class="col-sm-4">

												<input type="submit" class="btn btn-info" value="Add">
												<input type="hidden" id="subId" name="subId"
													value="${subId}">
												<c:choose>
													<c:when test="${editSubjectCo.coId>0}">
														<input type="hidden" id="coId" name="coId"
															value="${editSubjectCo.coId}">
													</c:when>
													<c:otherwise>
													<input type="hidden" id="coId" name="coId"
															value="0">
													</c:otherwise>
												</c:choose>
											</div>
										</div>

										<div class="form-group"></div>
									</div>
								</form>

								<div class="col-xs-12">
									<div class="table-responsive">
										<table class="table table-bordered" id="table1">
											<thead>
												<tr>
													<th width="10%">Sr No</th>

													<th>CO</th>

													<!-- <th>Is Mapped(Yes/No)</th> -->
													<!-- <th>Satisfying Value</th> -->
													<th width="10%" style="text-align: center;">PO Mapping</th>
													<th width="10%" style="text-align: center;">PSO Mapping</th>
													<th width="10%" style="text-align: center;">Action</th>
												</tr>

											</thead>

											<tbody>

												<c:forEach items="${subjectCoList}" var="subjectCoList" varStatus="count">
													<tr>

														<td>${count.index+1}</td>

														<td><c:out value="${subjectCoList.coName}" /></td>


														<%-- 


														<td><c:out value="${subjectCoList.coPoMap}" /></td>

														<td ><c:out
																value="${subjectCoList.coPsoMap}" /></td>
  --%>
														<td style="text-align: center;"><a
															href="${pageContext.request.contextPath}/mapPoCo?coId=${subjectCoList.coId}&subId=${subId}&programId=${programDetail.programId}"
															title="Map PO" rel="tooltip" data-color-class="detail"
															data-animate=" animated fadeIn " data-toggle="tooltip"
															data-original-title="Block"><span
																class="glyphicon glyphicon-list"></span></a></td>

														<td style="text-align: center;"><a
															href="${pageContext.request.contextPath}/mapPsoCo?coId=${subjectCoList.coId}&subId=${subId}&programId=${programDetail.programId}"
															title="Map PSO" rel="tooltip" data-color-class="detail"
															data-animate=" animated fadeIn " data-toggle="tooltip"
															data-original-title="Block"><span
																class="glyphicon glyphicon-list"></span></a></td>
														<td style="text-align: center;"><a
															href="${pageContext.request.contextPath}/editSubjectCo/${subjectCoList.coId}/${subId}"><span
																class="glyphicon glyphicon-edit" title="Edit"
																data-animate=" animated fadeIn " rel="tooltip"></span></a>&nbsp;&nbsp;

															<a
															href="${pageContext.request.contextPath}/deleteSubjectCo/${subjectCoList.coId}/${subId}"
															onClick="return confirm('Are you sure want to delete this record');"
															rel="tooltip" data-color-class="danger" title="Delete"
															data-animate=" animated fadeIn " data-toggle="tooltip"
															data-original-title="Delete  record"><span
																class="glyphicon glyphicon-remove"></span></a></td>





													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>


								<!-- <div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										<input type="submit" class="btn btn-primary"
											onclick="submit_f(1)" value="Save"> <input
											type="submit" class="btn btn-primary" onclick="submit_f(0)"
											value="Save &
																		Next">
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




	<div aria-hidden="true" role="dialog" tabindex="-1" id="myModal2"
		class="modal fade" style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button aria-hidden="true" data-dismiss="modal" class="close"
						type="button">×</button>
					<h4 class="modal-title">Program Details</h4>
				</div>
				<div class="modal-body">
					<%-- <form role="form"
						action="${pageContext.request.contextPath}/showModuleForm"
						method="get"> --%>
					<input type="hidden" class="form-control" id="pageId" name="pageId">

					<input type="hidden" class="form-control" id="index1" name="index1"
						value="0">

					<div class="form-group">
						<label class="control-label col-sm-3" for="finantialYear">Academic
							Year</label> <select id="academicYear1" name="academicYear1"
							class="form-control" required>
							<option value="2018-2019">2018-2019</option>
							<option value="2017-2018">2017-2018</option>
							<option value="2016-2017">2016-2017</option>
							<option value="2015-2016">2015-2016</option>
						</select>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-6" for="page_name">Program
							Outcomes (PO as per Accreditation Manual)</label> <select id="nba"
							name="ug" class="form-control" onchange="showForm()" required>
							<option value="Good">Good</option>
							<option value="Better">Better</option>
							<option value="Best">Best</option>


						</select>
					</div>





					<div class="form-group">
						<label class="control-label col-sm-6" for="page_name">Program
							Specific Outcomes (PSOs Define By the Program)</label> <select id="ps"
							name="ps" class="form-control" required>
							<option value="Good">Good</option>
							<option value="Better">Better</option>
							<option value="Best">Best</option>

						</select>
					</div>


					<div class="form-group">
						<label class="control-label col-sm-6" for="page_name">Satisfying
							Value</label> <select id="val" name="val" class="form-control"
							onchange="showForm()" required>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="-">-</option>

						</select>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-6" for="page_order">Mapping((Yes/No)
							:<span class="text-danger">*</span>
						</label>
						<div class="col-sm-2">
							Yes <input type="radio" name="stu" id="stu" checked value="yes">
							No<input type="radio" name="stu" id="stu1" value="no">
						</div>
					</div>







					<button type="submit" class="btn btn-primary" onclick="getData3()">Submit</button>
					<!-- </form> -->
				</div>
			</div>
		</div>
	</div>






	<script type="text/javascript">
		function getData3() {

			var i = parseInt(document.getElementById("index1").value);
			var year = document.getElementById("academicYear1").value;
			var nba = document.getElementById("nba").value
			//alert(vision);

			var ps = document.getElementById("ps").value
			//alert(mission);
			//var stu=document.getElementById("stu").value
			//alert(po);

			var rate_value;
			if (document.getElementById('stu').checked) {

				rate_value = document.getElementById('stu').value;
				//alert("::"+rate_value);
			} else if (document.getElementById('stu1').checked) {
				rate_value = document.getElementById('stu1').value;
				// alert("::"+rate_value);
			}

			var val = document.getElementById("val").value

			var dataTable = $('#example-1').DataTable();

			dataTable.row.add([ i + 1, year, nba, ps, rate_value, val

			]).draw();

			document.getElementById("index1").value = i + 1;

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

