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
							<%-- <h1 class="title">${title}</h1> --%>
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

								<a href="${pageContext.request.contextPath}/budgetAddOnLibrary"><button
										type="button" class="btn btn-success">
										<i class="${sessionScope.addIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Add
									</button></a>

							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/deleteLibBudget/0"
										method="get" name="form_sample_2" id="form_sample_2">


										<table id="example-1"
											class="table table-striped dt-responsive display">
											<thead>
												<tr>
													<!-- <th class="check" style="text-align: center; width: 5%;"><input
														type="checkbox" name="selAll" id="selAll"
														onClick="selectedInst(this)" /> Select All</th> -->
													<th width="10%">Sr No</th>
													<th>Financial Year</th>
													<th width="28%">Title</th>
													<th>Source of Funding</th>
													<th>Allocated Amount ${budRupees}</th>
													<th>Utilized Amount ${budRupees}</th>
													<th>Budget Allocated for Knowledge Resource Center(Library)
													 ${budRupees}</th>
													<th>Action</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${budgetList}" var="budget"
													varStatus="count">
													<tr>
														<%-- <td align="center"><input type="checkbox" class="chk"
															name="libBudgetId" id="libBudgetIds${count.index+1}"
															value="${budget.libBudgetId}" /></td>  --%>
														<td align="center">${count.index+1}</td>
														<td align="center">${budget.finYear}</td>
														<td align="center">${budget.libBudgetTitle}</td>
														<td align="center">${budget.exVar1}</td>
														<td align="right">${budget.budgetAllocated}</td>
														<td align="right">${budget.budgetUtilized}</td>
														<td align="right">${budget.exInt1}</td>
														<td align="center"><c:if test="${editAccess==0}">
																<a onclick="showEdit(${budget.libBudgetId})" href="#"><span
																	class="glyphicon glyphicon-edit" title="Edit"
																	data-animate=" animated fadeIn " rel="tooltip"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;
															</c:if> <c:if test="${deleteAccess==0}">
																<a
																	href="${pageContext.request.contextPath}/deleteLibBudget/${budget.libBudgetId}"
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
										<c:if test="${deleteAccess==0}">

											<!-- <input type="submit" class="btn btn-primary" value="Delete"
												id="deleteId"
												onClick="var checkedVals = $('.chk:checkbox:checked').map(function() { return this.value;}).get();checkedVals=checkedVals.join(',');if(checkedVals==''){alert('No Rows Selected');return false;	}else{   return confirm('Are you sure want to delete record');}"
												style="align-content: center; width: 113px; margin-left: 40px;"> -->


											<div class="form-group">

												<div class="col-sm-5">
													<div class="col-sm-1">



														<%-- <button type="submit" title="delete checked records" id="deleteId" class="btn btn-primary" onClick="var checkedVals = $('.chk:checkbox:checked').map(function() { return this.value;}).get();checkedVals=checkedVals.join(',');if(checkedVals==''){alert('No Rows Selected');return false;	}else{   return confirm('Are you sure want to delete record');}"><i class="${sessionScope.deleteIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Delete</button> --%>

														<!-- <button type="submit" title="Delete Multiple Records" class="btn1"
													id="deleteId"
													onClick="var checkedVals = $('.chk:checkbox:checked').map(function() { return this.value;}).get();checkedVals=checkedVals.join(',');if(checkedVals==''){alert('No Rows Selected');return false;	}else{   return confirm('Are you sure want to delete record');}"
													style="align-content: left;">
													<i class="fa fa-trash"
														style="font-size: 25px; background-color: black"></i>
												</button> -->
													</div>
													<!-- <div class="col-sm-4">
													<h5 style="text-align: left;">Delete Records</h5>
												</div> -->
												</div>




											</div>



										</c:if>
										<input type="hidden" id="libBudgetId" name="libBudgetId"
											value="0">


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
							of Library Facility</label> <input type="text" class="form-control"
							id="facilityTitle" name="facilityTitle"
							placeholder="Title of Library Facility" required>
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


	<script type="text/javascript">
		function getData() {
			//alert("hii");
			var i = parseInt(document.getElementById("index").value);
			var year = document.getElementById("finantialYear").value;
			var facilityTitle = document.getElementById("facilityTitle").value;
			var allocatedAmt = document.getElementById("allocatedAmt").value;
			var utilizedAmt = document.getElementById("utilizedAmt").value;
			//alert("noStud"+noStud);
			var dataTable = $('#example-1').DataTable();

			dataTable.row.add(
					[ i + 1, year, facilityTitle, allocatedAmt, utilizedAmt ])
					.draw();
			document.getElementById("index").value = i + 1;
		}
		
		
	</script>


	<script type="text/javascript">
		function showEdit(libBudgetId){
			document.getElementById("libBudgetId").value=libBudgetId;//create this 
			var form=document.getElementById("form_sample_2");
		    form.setAttribute("method", "post");

			form.action=("showEditLibBudget");
			form.submit();
			
		}
	</script>


	<script>
function selectedInst(source) {

	checkboxes = document.getElementsByName('libBudgetId');

	for (var i = 0, n = checkboxes.length; i < n; i++) {
		checkboxes[i].checked = source.checked;

	}

}
</script>



</body>
</html>