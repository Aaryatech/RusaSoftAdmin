<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="java.util.UUID"%>
<%@ page import="java.security.MessageDigest"%>
<%@ page import="java.math.BigInteger"%>

<!DOCTYPE html>
<html class=" ">
<head>

<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<!-- CORE CSS TEMPLATE - END -->
</head>
<!-- END HEAD -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

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

								<c:if test="${addAccess==0}">
									<%-- <a href="${pageContext.request.contextPath}/budgetAddOnLibraryBooks"><button
											type="button" class="btn btn-success">Add</button></a> --%>

									<a title="Add"
										href="${pageContext.request.contextPath}/budgetAddOnLibraryBooks"><button
											type="button" class="btn btn-success">
											<i class="${sessionScope.addIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Add
										</button></a>

								</c:if>
							</div>

						</header>

						<form
							action="${pageContext.request.contextPath}/deleteLibBookBudget/0"
							method="get" id="libListForm">
							
							<%
		UUID uuid = UUID.randomUUID();
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] messageDigest = md.digest(String.valueOf(uuid).getBytes());
		BigInteger number = new BigInteger(1, messageDigest);
		String hashtext = number.toString(16);
		session = request.getSession();
		session.setAttribute("generatedKey", hashtext);
	%>
	
							<div class="content-body">
								<div class="row">
									<div class="col-md-12">
										<table id="example-1"
											class="table table-striped dt-responsive display">
											<thead>
												<tr>
													<!-- <th class="check" style="text-align: center; width: 5%;"><input
														type="checkbox" name="selAll" id="selAll"
														onClick="selectedInst(this)" /> Select All</th> -->
													<th width="10%">Sr No</th>


													<th>Financial Year</th>
													<th>Source of Funding</th>
													<th style="text-align: right;">Expenditures on
														purchase of Books ${budRupees}</th>
													<th style="text-align: right;">Expenditures on
														purchase of Journals ${budRupees}</th>
													<th style="text-align: right;">Expenditures on
														e-Journals ${budRupees}</th>
													<th style="text-align: right;">Expenditures on
														e-Resources ${budRupees}</th>
														
													<th style="text-align: right;">Expenditures for
													Library Book ${budRupees}</th>
													<th style="text-align: center;">Action</th>

												</tr>
											</thead>


											<tbody>
												<c:forEach items="${budgetList}" var="budget"
													varStatus="count">
													<tr>

														<%-- <td style="text-align: center;"><input
															type="checkbox" class="chk" name="bookIds"
															id="bookIds${count.index+1}"
															value="${budget.libraryBookBudgetId}" /></td> --%>

														<td align="center">${count.index+1}</td>
														<td style="text-align: center;">${budget.finYear}</td>
														<%-- <td style="text-align: center;">${budget.exVar1}</td> --%>
														
														<c:if test = "${budget.exVar1!='Any Other Government Agency'}">
																	<td align="center">${budget.exVar1}</td>
														</c:if>
														
														<c:if test = "${budget.exVar1=='Any Other Government Agency'}">
																	<td align="center">${budget.exVar2}</td>
														</c:if>
														
														<td style="text-align: right;">${budget.expenditureOnBookPurchase}</td>
														<td style="text-align: right;">${budget.expenditureOnJournalsPurchase}</td>
														<td style="text-align: right;">${budget.expenditureOnEjournalsPurchase}</td>
														<td style="text-align: right;">${budget.expenditureOnEresourcesPurchase}</td>
														<td style="text-align: right;">${budget.exInt1}</td>
														<td align="center"><c:if test="${editAccess==0}">
																<a
																	onclick="showEditBookBudget(${budget.libraryBookBudgetId})"
																	href="#"><span class="glyphicon glyphicon-edit"
																	title="Edit" data-animate=" animated fadeIn "
																	rel="tooltip"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;
															</c:if> <c:if test="${deleteAccess==0}">
																<a
																	href="${pageContext.request.contextPath}/deleteLibBookBudget/${budget.libraryBookBudgetId}/<%out.println(hashtext);%>"
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


										<div class="form-group">

											<c:if test="${deleteAccess==0}">
												<%-- <button class="btn btn-primary" id="deleteId"
													onClick="var checkedVals = $('.chk:checkbox:checked').map(function() { return this.value;}).get();checkedVals=checkedVals.join(',');if(checkedVals==''){alert('No Rows Selected');return false;	}else{   return confirm('Are you sure want to delete record');}"
													style="align-content: center; width: 113px; margin-left: 40px;">
													<i class="${sessionScope.deleteIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Delete
												</button> --%>
											</c:if>


											<!-- <div class="col-sm-5">
											<div class="col-sm-1">

												<button type="submit" title="Delete Multiple Records" class="btn1"
													id="deleteId"
													onClick="var checkedVals = $('.chk:checkbox:checked').map(function() { return this.value;}).get();checkedVals=checkedVals.join(',');if(checkedVals==''){alert('No Rows Selected');return false;	}else{   return confirm('Are you sure want to delete record');}"
													style="align-content: left;">
													<i class="fa fa-trash"
														style="font-size: 25px; background-color: black"></i>
												</button></div>
												<div class="col-sm-4">
													<h5 style="text-align: left;">Delete Records</h5>
												</div>
											</div> -->


											<input type="hidden" id="libBookId" name="libBookId"
												value="0">


										</div>


									</div>

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
	<!-- END CONTAINER -->
	<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->

	<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>



	<script type="text/javascript">
		function showEditBookBudget(libBudgetId){
			///alert(libBudgetId);
			document.getElementById("libBookId").value=libBudgetId;//create this 
			var form=document.getElementById("libListForm");
		    form.setAttribute("method", "post");

			form.action=("showEditLibBookBudget");
			form.submit();
			
		}
		
		function selectedInst(source) {

			checkboxes = document.getElementsByName('bookIds');

			for (var i = 0, n = checkboxes.length; i < n; i++) {
				checkboxes[i].checked = source.checked;

			}

		}

	</script>


	<script type="text/javascript">
		function getData() {

			var i = parseInt(document.getElementById("index").value);
			var year = document.getElementById("finantialYear").value;

			var purchaseBooks = document.getElementById("purchaseBooks").value;
			var purchaseJrnls = document.getElementById("purchaseJrnls").value;
			var purchaseEJrnls = document.getElementById("purchaseEJrnls").value;
			var purchaseEResources = document
					.getElementById("purchaseEResources").value;
			//alert("noStud"+noStud);
			var dataTable = $('#example-1').DataTable();

			dataTable.row.add(
					[ i + 1, year, purchaseBooks, purchaseJrnls,
							purchaseEJrnls, purchaseEResources ]).draw();
			document.getElementById("index").value = i + 1;
		}
	</script>






</body>
</html>