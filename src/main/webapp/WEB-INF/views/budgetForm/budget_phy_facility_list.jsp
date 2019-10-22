<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<!DOCTYPE html>
<html class=" ">
<head>

<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<!-- CORE CSS TEMPLATE - END -->
</head>
<!-- END HEAD -->

<style>
.image-preview-input {
	position: relative;
	overflow: hidden;
	margin: 0px;
	color: #333;
	background-color: #fff;
	border-color: #ccc;
}

.image-preview-input input[type=file] {
	position: absolute;
	top: 0;
	right: 0;
	margin: 0;
	padding: 0;
	font-size: 20px;
	cursor: pointer;
	opacity: 0;
	filter: alpha(opacity = 0);
}

.image-preview-input-title {
	margin-left: 2px;
}
</style>


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
		<section id="main-content" class=" ">
			<section class="wrapper main-wrapper row" style="">

				<div class="col-xs-12">
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

				<div class="col-lg-12"></div>

				<div class="col-lg-12">
					<section class="box ">

						<header class="panel_header">
							<h2 class="title pull-left">${title}</h2>

							<div class="actions panel_actions pull-right">
								<c:if test="${isAdd==1}">
									<a title="Add"
										href="${pageContext.request.contextPath}/budgetAddPhysicalFacility"><button
											type="button" class="btn btn-success">
											<i class="${sessionScope.addIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Add
										</button></a>
								</c:if>
							</div>
						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertPublicationDetail"
										method="post" name="form_sample_2" id="form_sample_2"
										onsubmit="return confirm('Do you really want to submit the form?');">


										<table id="example-1"
											class="table table-striped dt-responsive display">
											<thead>
												<tr>
													<th width="10%">Sr No</th>
													<th>Financial Year</th>
													<th width="40%">Title of Physical Facilities</th>
													<th>Source of Funding</th>
													<th>Allocated Amount ${budRupees}</th>
													<th>Utilized Amount ${budRupees}</th>
													<th>Total Expenditure for Physical Facility
														${budRupees}</th>
													<th>Action</th>
												</tr>
											</thead>

											<tbody>
												<c:forEach items="${budgetList}" var="budget"
													varStatus="count">
													<tr>
														<%-- <td align="center"><input type="checkbox" class="chk"
															name="accOffIds" id="accOffIds${count.index+1}"
															value="${accOff.officerId}" /></td> --%>
														<td align="center">${count.index+1}</td>
														<td align="center">${budget.finYear}</td>
														<td align="center">${budget.physicalFacilityBudgetTitle}</td>

														<c:if
															test="${budget.exVar1!='Any Other Government Agency'}">
															<td align="center">${budget.exVar1}</td>
														</c:if>

														<c:if
															test="${budget.exVar1=='Any Other Government Agency'}">
															<td align="center">${budget.exVar2}</td>
														</c:if>
														<%-- 	<td align="center">${budget.exVar1}</td> --%>


														<td align="right">${budget.budgetAllocated}</td>
														<td align="right">${budget.budgetUtilized}</td>
														<td align="right">${budget.exInt1}</td>
														<td align="center"><c:if test="${editAccess==0}">
																<a
																	href="${pageContext.request.contextPath}/editPhysicalBudget/${budget.physicalFacilityBudgetId}"><span
																	class="glyphicon glyphicon-edit" title="Edit"
																	data-animate=" animated fadeIn " rel="tooltip"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;
															</c:if> <c:if test="${deleteAccess==0}">
																<a
																	href="${pageContext.request.contextPath}/deletePhyBudget/${budget.physicalFacilityBudgetId}"
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

									</form>
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




	<div class="modal fade col-xs-12" id="myModal1" tabindex="-1"
		role="dialog" aria-hidden="true">
		<div class="modal-dialog" style="width: 65%">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Budget on Infrastructure Facility</h4>
				</div>
				<div class="modal-body">

					<div class="form-group">
						<label class="control-label col-sm-3" for="finantialYear">Financial
							Year</label> <select id="finantialYear" name="finantialYear"
							class="form-control" required>
							<option value="2018-2019">2018-2019</option>
							<option value="2017-2018">2017-2018</option>
							<option value="2016-2017">2016-2017</option>

						</select>
					</div>
					<div class="form-group">

						<label class="control-label col-sm-4 for="facilityTitle">Title
							of Physical Facilities</label> <input type="text" class="form-control"
							id="facilityTitle" name="facilityTitle"
							placeholder="Title of Physical Facilities" required>
					</div>
					<div class="form-group">

						<label class="control-label col-sm-3" for="allocatedAmt">Budget
							Allocated Amount in Rs. </label> <input type="text" class="form-control"
							id="allocatedAmt" name="allocatedAmt"
							placeholder="Budget Allocated Amount in Rs"
							value="${page.pageName}" required>
					</div>

					<div class="form-group">

						<label class="control-label col-sm-3" for="utilizedAmt">Budget
							Utilized Amount in Rs. </label> <input type="text" class="form-control"
							id="utilizedAmt" name="utilizedAmt"
							placeholder="Budget Utilized Amount in Rs."
							value="${page.pageName}" required>
					</div>

					<div class="form-group" style="text-align: center;">

						<button type="submit" class="btn btn-primary" onclick="getData()">Submit</button>
					</div>


				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
					<input type="hidden" id="index" name="index" value="0">
				</div>
			</div>
		</div>
	</div>





</body>
</html>