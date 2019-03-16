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

				<%-- <div class="col-xs-12">
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

				<div class="col-lg-12"></div>



				<div class="col-lg-12">
					<section class="box ">

						<header class="panel_header">
							<h2 class="title pull-left">${title}</h2>

							<div class="actions panel_actions pull-right">

								<a
									href="${pageContext.request.contextPath}/showConsultancyList"><button
										type="button" class="btn btn-info">Back</button></a>

							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/submitFacultyConsultancy"
										method="post" name="form_sample_2" id="form_sample_2"
										onsubmit="return confirm('Do you really want to submit the form?');">

										<ul class="nav nav-tabs">
											<li class="active"><a href="#home" data-toggle="tab">
													<i class="fa fa-home"></i> Consultancy
											</a></li>

										</ul>

										<div class="tab-content">
											<div class="tab-pane fade in active" id="home">

												<!-- <div class="form-group">
													<label class="control-label col-sm-2" for="smallheading">
														Consultancy <span class="text-danger">*</span>
													</label>
													<div class="col-sm-2">
														Yes <input type="radio" name="consultancy"
															onclick="setConsultancy(this.value)" id="consultancy"
															checked value="0"> No<input
															onclick="setConsultancy(this.value)" type="radio"
															name="consultancy" id="consultancy" value="1">
													</div>
												</div> -->
												<div id="abc">


													<div class="form-group">

														<label class="control-label col-sm-2" for="nature">Nature
															of Consultancy <span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<input type="text" class="form-control" id="nature"
																placeholder="Nature of Consultancy" name="nature"
																placeholder="" value="${page.pageName}" required>

														</div>
													</div>

													<div class="form-group">

														<label class="control-label col-sm-2" for="sponser">Sponsoring
															Agency/Industry <span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<input type="text" class="form-control" id="sponser"
																placeholder="Sponsoring Agency/Industry" name="sponser"
																placeholder="" value="${page.pageName}" required>
															<!-- </div> -->
														</div>
													</div>

													<div class="form-group">

														<label class="control-label col-sm-2" for="amount">Amount
															of Consultancy <span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<input type="text" class="form-control" id="amount"
																placeholder="Amount of Consultancy" name="amount"
																placeholder="" value="${page.pageName}" required>
															<!-- </div> -->
														</div>
													</div>
													<div class="form-group">

														<label class="control-label col-sm-2" for="conPeriod">Consultancy
															Period<span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<input type="text" class="form-control" id="conPeriod"
																placeholder="Consultancy Period" name="conPeriod"
																placeholder="" value="${page.pageName}" required>
														</div>

													</div>

													<div class="form-group">
														<label class="control-label col-sm-2" for="projComp">Project
															Completed<span class="text-danger">*</span>
														</label>

														<div class="col-sm-2">
															Yes <input type="radio" name="projComp" id="projComp"
																checked value="1"> No<input type="radio"
																name="projComp" id="projComp" value="0">
														</div>
													</div>

													<input type="hidden" id="is_view" name="is_view" value="0">
													<c:choose>
														<c:when test="${editProgramActivity.studentActivityId>0}">
															<input type="hidden" id="consId" name="consId"
																value="${editProgramActivity.studentActivityId}">
														</c:when>
														<c:otherwise>
															<input type="hidden" id="consId" name="consId"
																value="0">
														</c:otherwise>
													</c:choose>
													<div class="form-group">
														<div class="col-sm-offset-2 col-sm-10">
															<input type="submit" class="btn btn-primary"
																onclick="submit_f(1)" value="Save"> <input
																type="submit" class="btn btn-primary"
																onclick="submit_f(0)"
																value="Save &
																		Next">
															<button type="reset" class="btn btn-default">Reset</button>
														</div>
													</div>

													<div class="clearfix"></div>

												</div>


											</div>
										</div>
									</form>
								</div>

							</div>
						</div>
					</section>
				</div>
			</section>
		</section>
	</div>



	<!-- END CONTAINER -->
	<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->

	<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>


	<div aria-hidden="true" role="dialog" tabindex="-1" id="myModal"
		class="modal fade" style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button aria-hidden="true" data-dismiss="modal" class="close"
						type="button">×</button>
					<h4 class="modal-title">Consultancy Details</h4>
				</div>
				<div class="modal-body">
					<%-- <form role="form"
						action="${pageContext.request.contextPath}/showModuleForm"
						method="get"> --%>
					<input type="hidden" class="form-control" id="pageId" name="pageId">


					<input type="hidden" class="form-control" id="index" name="index"
						value="0">
					<div class="form-group">
						<label class="control-label col-sm-6" for="page_name">Academic
							Year</label> <select id="academicYear" name="qualType"
							class="form-control" onchange="showForm()" required>
							<option value="2018-2019">2018-2019</option>
							<option value="2017-2018">2017-2018</option>
							<option value="2016-2017">2016-2017</option>
							<option value="2016-2017">2015-2016</option>

						</select>
					</div>


					<button type="submit" class="btn btn-primary" onclick="getData()">Submit</button>
					<!-- 	</form> -->
				</div>
			</div>
		</div>
	</div>



	<script type="text/javascript">
		function submit_f(view) {
			//alert(view);
			document.getElementById("is_view").value = view;

		}
	</script>

	<script type="text/javascript">
		function getData() {
			//alert("hii");
			var i = parseInt(document.getElementById("index").value);
			var nature = document.getElementById("nature").value
			var sponser = document.getElementById("sponser").value
			//alert(qualName);
			var amount = document.getElementById("amount").value
			var academicYear = document.getElementById("academicYear").value
			var conPeriod = document.getElementById("conPeriod").value
			var consultancy = document.getElementById("consultancy").value
			var temp;
			if (consultancy == 1) {
				temp = "Yes";
			} else {
				temp = "No";
			}
			var dataTable = $('#example-1').DataTable();

			dataTable.row.add(
					[ i + 1, academicYear, nature, sponser, amount, conPeriod,
							temp

					]).draw();

			document.getElementById("index").value = i + 1;

		}
	</script>

	<script type="text/javascript">
		function setConsultancy(value) {

			if (value == 1) {

				document.getElementById("abc").style.display = "none";

			} else {

				document.getElementById("abc").style.display = "block";

			}

		}
	</script>




</body>
</html>



