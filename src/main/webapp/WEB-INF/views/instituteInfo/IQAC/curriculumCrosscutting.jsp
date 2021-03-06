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
								<<%-- a
								href="${pageContext.request.contextPath}/publicationList"><button
								type="button" class="btn btn-info">Back</button></a> --%>
								<!-- <a
									class="box_toggle fa fa-chevron-down"></a> -->
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

										<ul class="nav nav-tabs">
											<li class="active"><a href="#strength" data-toggle="tab">
													<i class="fa fa-home"></i> Gender
											</a></li>
											<li><a href="#weak" data-toggle="tab"> <i
													class="fa fa-home"></i> Environment and Sustainability
											</a></li>
											<li><a href="#oppt" data-toggle="tab"> <i
													class="fa fa-home"></i> Human Values and Professional
											</a></li>


										</ul>

										<div class="tab-content">
											<!-- 1 -->
											<div class="tab-pane fade in active " id="strength">

												<div class="form-group">
													<label class="control-label col-sm-2" for="isReform"
														style="text-align: left;"> Gender: <span
														class="text-danger">*</span>
													</label>
													<div class="col-sm-2">
														<input type="radio" onclick="showReforms(this.value)"
															id="istransparent" name="istransparent" value="1" checked>Yes
														<input type="radio" id="istransparent"
															onclick="showReforms(this.value)" name="istransparent"
															value="0">No
													</div>
												</div>
												<div id="abc">
													<div class="form-group">

														<label class="control-label col-sm-2" for="Subject/Course">Subject/Course
															<span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<input type="text" class="form-control" id="subName1"
																name="subName1" placeholder="Subject/Course"
																value="${page.pageName}" required>
														</div>
													</div>
												</div>

												<table id="example7"
													class="table table-striped dt-responsive display">
													<thead>
														<tr>
															<th width="10%">Sr No</th>
															<th>Subject/Course</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
												</table>
											</div>
											<div class="tab-pane fade in  " id="weak">


												<div class="form-group">
													<label class="control-label col-sm-3" for="isReform"
														style="text-align: left;"> Environment and
														Sustainability: <span class="text-danger">*</span>
													</label>
													<div class="col-sm-3">
														<input type="radio" onclick="showReforms1(this.value)"
															id="isSustain" name="isSustain" value="1" checked>Yes
														<input type="radio" id="isSustain"
															onclick="showReforms1(this.value)" name="isSustain"
															value="0">No
													</div>

												</div>


												<div id="xyz">
													<div class="form-group">

														<label class="control-label col-sm-2" for="Subject/Course">Subject/Course
															<span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<input type="text" class="form-control" id="subName2"
																name="subName2" placeholder="Subject/Course"
																value="${page.pageName}" required>
														</div>
													</div>
												</div>

												<table id="example8"
													class="table table-striped dt-responsive display">
													<thead>
														<tr>
															<th width="10%">Sr No</th>
															<th>Subject/Course</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
												</table>
											</div>

											<div class="tab-pane  " id="oppt">


												<div class="form-group">
													<label class="control-label col-sm-3" for="isReform"
														style="text-align: left;"> Human Values and
														Professional Ethics <span class="text-danger">*</span>
													</label>
													<div class="col-sm-1">
														<input type="radio" onclick="showReforms2(this.value)"
															id="isEthics" name="isEthics" value="1" checked>Yes
														<input type="radio" id="isEthics"
															onclick="showReforms2(this.value)" name="isEthics"
															value="0">No
													</div>
												</div>


												<div id="pqr">
													<div class="form-group">

														<label class="control-label col-sm-2" for="Subject/Course">Subject/Course
															<span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<input type="text" class="form-control" id="subName3"
																name="subName3" placeholder="Subject/Course"
																value="${page.pageName}" required>
														</div>
													</div>
												</div>
												<table id="example9"
													class="table table-striped dt-responsive display">
													<thead>
														<tr>
															<th width="10%">Sr No</th>
															<th>Subject/Course</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
												</table>
											</div>

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

										<!--  -->
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
					<h4 class="modal-title">Gender</h4>
				</div>
				<div class="modal-body">

					<div class="form-group">
						<label class="control-label col-sm-3" for="academicYear">Academic
							Year</label> <select id="academicYear" name="academicYear"
							class="form-control" required>
							<option value="2018-2019">2018-2019</option>
							<option value="2017-2018">2017-2018</option>
							<option value="2016-2017">2016-2017</option>
							<option value="2015-2016">2015-2016</option>

						</select>
					</div>


					<div class="form-group">

						<label class="control-label col-sm-3" for="Subject/Course">Subject/Course
						</label> <input type="text" class="form-control" id="subName"
							name="subName" placeholder="Subject/Course"
							value="${page.pageName}" required>
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



	<div class="modal fade col-xs-12" id="myModal2" tabindex="-1"
		role="dialog" aria-hidden="true">
		<div class="modal-dialog" style="width: 65%">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Environment and Sustainability</h4>
				</div>
				<div class="modal-body">

					<div class="form-group">
						<label class="control-label col-sm-3" for="academicYear">Academic
							Year</label> <select id="academicYear1" name="academicYear1"
							class="form-control" required>
							<option value="2018-2019">2018-2019</option>
							<option value="2017-2018">2017-2018</option>
							<option value="2016-2017">2016-2017</option>
							<option value="2015-2016">2015-2016</option>

						</select>
					</div>



					<!-- Link on Website for Activity Report -->

					<div class="form-group" style="text-align: center;">

						<button type="submit" class="btn btn-primary" onclick="getData1()">Submit</button>
					</div>


				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
					<input type="hidden" id="index" name="index" value="0">
				</div>
			</div>
		</div>
	</div>


	<div class="modal fade col-xs-12" id="myModal3" tabindex="-1"
		role="dialog" aria-hidden="true">
		<div class="modal-dialog" style="width: 65%">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Human Values and Professional Ethics</h4>
				</div>
				<div class="modal-body">

					<div class="form-group">
						<label class="control-label col-sm-3" for="academicYear">Academic
							Year</label> <select id="academicYear2" name="academicYear2"
							class="form-control" required>
							<option value="2018-2019">2018-2019</option>
							<option value="2017-2018">2017-2018</option>
							<option value="2016-2017">2016-2017</option>
							<option value="2015-2016">2015-2016</option>

						</select>
					</div>

					<!-- Link on Website for Activity Report -->

					<div class="form-group" style="text-align: center;">

						<button type="submit" class="btn btn-primary" onclick="getData2()">Submit</button>
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
			var academicYear = document.getElementById("academicYear").value;
			var subName = document.getElementById("subName").value;

			var dataTable = $('#example7').DataTable();

			dataTable.row.add([ i + 1, academicYear, subName ]).draw();
			document.getElementById("index").value = i + 1;
		}

		function getData1() {
			//alert("hii");
			var i = parseInt(document.getElementById("index").value);
			var academicYear = document.getElementById("academicYear1").value;
			var subName = document.getElementById("subName1").value;

			var dataTable = $('#example8').DataTable();

			dataTable.row.add([ i + 1, academicYear, subName ]).draw();
			document.getElementById("index").value = i + 1;
		}

		function getData2() {
			//alert("hii");
			var i = parseInt(document.getElementById("index").value);
			var academicYear = document.getElementById("academicYear2").value;
			var subName = document.getElementById("subName2").value;

			var dataTable = $('#example9').DataTable();

			dataTable.row.add([ i + 1, academicYear, subName ]).draw();
			document.getElementById("index").value = i + 1;
		}

		function showReforms(temp) {

			if (temp == 1) {
				document.getElementById("abc").style = "visible"
			} else {
				document.getElementById("abc").style = "display:none"
			}
		}

		function showReforms1(temp) {

			if (temp == 1) {
				document.getElementById("xyz").style = "visible"
			} else {

				document.getElementById("xyz").style = "display:none"

			}

		}

		function showReforms2(temp) {
			//alert("hii");
			//var remark = document.getElementById("isReform").value;
			//alert(temp);
			if (temp == 1) {
				document.getElementById("pqr").style = "visible"

			} else {

				document.getElementById("pqr").style = "display:none"

			}

		}
	</script>


</body>
</html>