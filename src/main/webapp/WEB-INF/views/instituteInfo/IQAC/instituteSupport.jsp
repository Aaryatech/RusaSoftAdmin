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
								<a
									href="${pageContext.request.contextPath}/showAddInstituteSupport"><button
										type="button" class="btn btn-success">Add</button></a>
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertPublicationDetail"
										method="post" enctype="multipart/form-data"
										name="form_sample_2" id="form_sample_2"
										onsubmit="return confirm('Do you really want to submit the form?');">






										<!-- <div class="form-group">

													<label class="control-label col-sm-10" for="smallheading">Institute
														Support Financially by Awarding Scholarship/Freeships like
														Schemes <b>other than Government Schemes </b> : <span
														class="text-danger">*</span>
													</label>


													<div class="col-sm-2">
														Yes <input type="radio" name="isSchemes" id="isSchemes"
															checked value="0"> No<input type="radio"
															name="isSchemes" id="isSchemes" value="1">
													</div>
												</div> -->
										<div class="col-xs-12">
											<h5 class="title pull-left">
												Institute Support Financially by Awarding
												Scholarship/Freeships like schemes <Strong>other
													than Government Schemes</Strong>
											</h5>
											<div class="col-xs-12"></div>

											<table id="example-1"
												class="table table-striped dt-responsive display">
												<thead>
													<tr>
													<!-- <th class="check" style="text-align: center; width: 5%;"><input
														type="checkbox" name="selAll" id="selAll"
														onClick="selectedInst(this)" /> Select All</th> -->
														<th width="10%">Sr No</th>
														<th>Name of Scheme</th>
														<th>No. of Students Benefited</th>
														<th>Scheme/Support offered By</th>
														<th>Action</th>
													</tr>

												</thead>

												
												<tbody>
												<c:forEach items="${schemeList}" var="schmList"
													varStatus="count">
													<tr>
														<%-- <td><input type="checkbox" class="chk"
															name="schmIds" id="schmIds${count.index+1}"
															value="${bList.instSchemeId}" /></td> --%>
														<td align="center">${count.index+1}</td>
														<td>${schmList.instSchemeName}</td>
														<td>${schmList.instStudentsBenefited}</td>
														<td>${schmList.instSchmeOfferedby}</td>
														
														<td align="center"><%-- <c:if test="${editAccess==0}"> --%>
																<a 
																	href="${pageContext.request.contextPath}/editInstituteScheme/${schmList.instSchemeId}"><span class="glyphicon glyphicon-edit"  title="Edit"
																	data-animate=" animated fadeIn " rel="tooltip"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;
															<%-- </c:if><c:if test="${deleteAccess==0}"> --%>
																<a
																	href="${pageContext.request.contextPath}/deleteInstituteScheme/${schmList.instSchemeId}"
																	onClick="return confirm('Are you sure want to delete this record');"
																	rel="tooltip" data-color-class="danger"  title="Delete"
																	data-animate=" animated fadeIn " data-toggle="tooltip"
																	data-original-title="Delete  record"><span
																	class="glyphicon glyphicon-remove"></span></a>
															<%-- </c:if> --%></td>
													</tr>
												</c:forEach>

											</tbody>

											</table>

											<div class="form-group">
															<div class="col-sm-offset-2 col-sm-10">

								<button type="button" class="btn btn-primary" onclick="exportToExcel();"  id="expExcel" 
									style="align-content: center; width: 200px; margin-left: 80px; background-color: #03A9F4;">
									Export To Excel</button>
									
									<button type="button" class="btn btn-primary"
										onclick="genPdf()" id="PDFButton"
										style="align-content: center; width: 100px; margin-left: 80px; background-color: #03A9F4;">
										PDF</button>
							</div>
							</div>
										</div>
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
					<h4 class="modal-title">Institute Support Details</h4>
				</div>
				<div class="modal-body">

					<div class="form-group">

						<label class="control-label col-sm-3" for="studBenifited">Name
							of Schemes </label> <input type="text" class="form-control"
							id="schemeName" name="schemeName" placeholder="Name of Schemes"
							value="${page.pageName}" required>
					</div>




					<div class="form-group">
						<label class="control-label col-sm-3" for="academicYear">Academic
							Year</label> <select id="academicYear" name="academicYear"
							class="form-control" required>
							<option value="2018-2019">2018-2019</option>
							<option value="2017-2018">2017-2018</option>
							<option value="2016-2017">2016-2017</option>

						</select>
					</div>



					<div class="form-group">

						<label class="control-label col-sm-3" for="studBenifited">No.
							of Students Benefited </label> <input type="text" class="form-control"
							id="studBenifited" name="studBenifited"
							placeholder="No. of Students Benefited" value="${page.pageName}"
							required>
					</div>

					<div class="form-group">

						<label class="control-label col-sm-3" for="schemeSupportBy">Scheme/Support
							offered By </label> <input type="text" class="form-control"
							id="schemeSupportBy" name="schemeSupportBy"
							placeholder="Scheme/Support offered By" value="${page.pageName}"
							required>
					</div>



					<!-- Link on Website for Activity Report -->

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
	
	function selectedInst(source) {

		checkboxes = document.getElementsByName('schmIds');

		for (var i = 0, n = checkboxes.length; i < n; i++) {
			checkboxes[i].checked = source.checked;

		}

	}
	
	
		function getData() {
			//alert("hii");
			var i = parseInt(document.getElementById("index").value);
			var schemeName = document.getElementById("schemeName").value;
			var academicYear = document.getElementById("academicYear").value;

			var studBenifited = document.getElementById("studBenifited").value;

			var schemeSupportBy = document.getElementById("schemeSupportBy").value;
			//alert("noStud"+noStud);
			var dataTable = $('#example-1').DataTable();

			dataTable.row.add(
					[ i + 1, schemeName, academicYear, studBenifited,
							schemeSupportBy ]).draw();
			document.getElementById("index").value = i + 1;
		}
	</script>

<script type="text/javascript">
		function exportToExcel() {

			window.open("${pageContext.request.contextPath}/exportToExcel");
			document.getElementById("expExcel").disabled = true;
		}
	
		function genPdf() {
			//alert("hiii");
			
			window.open("${pageContext.request.contextPath}/instituteSchemePdf");
			document.getElementById("expExcel").disabled = true;

		}
	</script>




</body>
</html>