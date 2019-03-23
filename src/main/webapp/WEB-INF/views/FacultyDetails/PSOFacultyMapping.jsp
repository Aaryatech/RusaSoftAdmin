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
								<!-- <a href="#myModal2"	data-toggle="modal"><button type="submit"
																class="btn btn-info">Add</button></a> -->
								  <a href="${pageContext.request.contextPath}/showAddCo/${subId}"><button
										type="button" class="btn btn-info">Back</button></a> 

								<!--  <a class="box_setting fa fa-cog" data-toggle="modal" href="#section-settings"></a>
                    <a class="box_close fa fa-times"></a> -->

							</div>

						</header>



						<div class="col-xs-12">

							<div class="form-group">
								<label class="control-label col-sm-2" for="status">
									Program Name:</label><strong> ${programDetail.nameOfProgram} </strong>
								<%-- <div class="col-sm-6" for="status">
												<strong> ${programDetail.nameOfProgram} </strong>
											</div> --%>
							</div>

							<div class="form-group">
								<label class="control-label col-sm-2" for="status">
									Duration:</label> <strong>${programDetail.monthDuration}</strong>
								<%-- <div class="col-sm-6" for="status">
												<strong>${programDetail.monthDuration}</strong>
											</div> --%>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-2" for="status">
									Sanctioned Intake:</label><strong>${programDetail.sanctionalIntake}</strong>
								<%-- <div class="col-sm-6" for="status">
												<strong>${programDetail.sanctionalIntake}</strong>
											</div> --%>


							</div>
							<div class="form-group"></div>
						</div>


						<br /> <br /> <br />
						<div class="content-body">
							<div class="row">


								<form class="form-horizontal"
									action="${pageContext.request.contextPath}/submitPsoCoMapping"
									method="post" name="submitProgramMission"
									id="submitProgramMission"
									onsubmit="return confirm('Do you really want to Submit?');">
									<div class="col-xs-12">
										<div class="table-responsive">
											<table class="table table-bordered" id="table1">
												<thead>

													<tr>
														<th>Sr No <!-- <input type="checkbox" id="checkAll"
															onClick="toggle(this)" /> --><INPUT type="checkbox" onchange="checkAll(this)" name="chk[]" /></th>
														<th>Program Outcomes (PO as per Accreditation Manual)</th>
													</tr>

												</thead>
												<tbody>
													<!-- <tr>
													<td>1</td>
													<td><input type="checkbox" class="chk" name="studIds">
													</td>
													<td>xyz</td>
													format to display program name is program Type-Program Name




												</tr> -->

													<c:forEach items="${programSpeceficOutcomeList}" var="list"
														varStatus="count">
														<tr>
															<c:set var="find" value="0"></c:set>
															<td>${count.index+1}<c:forEach items="${psoarry}"
																	var="poarry">
																	<c:if test="${list.psoId==poarry}">
																		<c:set var="find" value="1"></c:set>
																	</c:if>
																</c:forEach> <c:choose>
																	<c:when test="${find==1}">
																		<input type="checkbox" class="chk" name="psoIds"
																			value="${list.psoId}" checked>
																	</c:when>
																	<c:otherwise>
																		<input type="checkbox" class="chk" name="psoIds"
																			value="${list.psoId}">
																	</c:otherwise>
																</c:choose>

															</td>
															<td>${list.psoText}</td>

														</tr>
													</c:forEach>

												</tbody>
											</table>
										</div>
									</div>



									<div class="form-group">
										<label class="control-label col-sm-2" for="satisfyingValue">Satisfying
											Value <span class="text-danger">*</span>
										</label>
										<div class="col-sm-6">

											<select id="satisfyingValue" name="satisfyingValue"
												class="form-control" required>

												   <c:choose>
													<c:when test="${subjectCo.coPsoSatisfyingValue == 0}">
														<option value="0" selected>1</option>
														<option value="1">2</option>
														<option value="2">3</option>
														<option value="3">-</option>
													</c:when>
													<c:when test="${subjectCo.coPsoSatisfyingValue == 1}">
														<option value="0">1</option>
														<option value="1" selected>2</option>
														<option value="2">3</option>
														<option value="3">-</option>
													</c:when>
													<c:when test="${subjectCo.coPsoSatisfyingValue == 2}">
														<option value="0">1</option>
														<option value="1">2</option>
														<option value="2" selected>3</option>
														<option value="3">-</option>
													</c:when>
													<c:when test="${subjectCo.coPsoSatisfyingValue == 3}">
														<option value="0">1</option>
														<option value="1">2</option>
														<option value="2">3</option>
														<option value="3" selected>-</option>
													</c:when>
													<c:otherwise>
														<option value="0">1</option>
														<option value="1">2</option>
														<option value="2">3</option>
														<option value="3">-</option> 
													</c:otherwise>
												</c:choose>  
											</select>


										</div>
									</div>


									<input type="hidden" id="coId" name="coId" value="${coId}">
									<input type="hidden" id="subId" name="subId" value="${subId}">
									<input type="hidden" id="programId" name="programId"
										value="${programId}">
									<div class="form-group">
										<div class="col-sm-offset-2 col-sm-10">
											<button type="submit" class="btn btn-primary">Submit</button>
											<!-- <button type="reset" class="btn btn-default">Reset</button> -->
										</div>
									</div>

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







					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<input type="submit" class="btn btn-primary"
								onclick="submit_f(1)" value="Save"> <input type="submit"
								class="btn btn-primary" onclick="submit_f(0)"
								value="Save &
																		Next">
							<button type="reset" class="btn btn-default">Reset</button>
						</div>
					</div>
					<!-- </form> -->
				</div>
			</div>
		</div>
	</div>






	<script>
	function checkAll(ele) {
	     var checkboxes = document.getElementsByTagName('input');
	     if (ele.checked) {
	         for (var i = 0; i < checkboxes.length; i++) {
	             if (checkboxes[i].type == 'checkbox') {
	                 checkboxes[i].checked = true;
	             }
	         }
	     } else {
	         for (var i = 0; i < checkboxes.length; i++) {
	             console.log(i)
	             if (checkboxes[i].type == 'checkbox') {
	                 checkboxes[i].checked = false;
	             }
	         }
	     }
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

