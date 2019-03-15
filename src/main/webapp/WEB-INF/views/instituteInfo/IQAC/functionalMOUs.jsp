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
								<a
									href="${pageContext.request.contextPath}/showAddFunctionalMOUs"
									data-toggle="modal"><button type="submit"
										class="btn btn-success">Add</button></a>
								<%-- <a
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
											<li class="active"><a href="#home" data-toggle="tab">
													<i class="fa fa-home"></i> ${title}
											</a></li>

										</ul>

										<div class="tab-content">
											<div class="tab-pane fade in active" id="home">

												<div>


													<div class="col-xs-12">

														<div class="col-xs-12">

															<table id="example-1"
																class="table table-striped dt-responsive display">
																<thead>
																	<tr>
																		<th width="10%">Sr No</th>
																		<th>Title of MoU</th>
																		<th>MoU Duration / Period</th>
																		<th>Functional MoU with Agency</th>
																		<th>Academic Year</th>
																		<th>Beneficiary of MoU</th>
																		<th>No. of Participants / Beneficiary</th>


																	</tr>

																</thead>



																<tbody>


																</tbody>
															</table>

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
					<h4 class="modal-title">Functional MoUs</h4>
				</div>
				<div class="modal-body">


					<div class="form-group">

						<label class="control-label col-sm-3" for="title">Title of
							MoU </label> <input type="text" class="form-control" id="title"
							name="title" placeholder="Title of MoU" value="${page.pageName}"
							required>
					</div>
					<div class="form-group">

						<label class="control-label col-sm-6" for="mouDuration">MoU
							Duration / Period </label> <input type="text" class="form-control"
							id="mouDuration" name="mouDuration"
							placeholder="MoU Duration / Period" value="${page.pageName}"
							required>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-6" for="functionalMOU">Functional
							MoU with Agency </label> <select id="functionalMOU" name="functionalMOU"
							onchange="showForm()" class="form-control" required>
							<option value="IIT">IIT</option>
							<option value="NIT">NIT</option>
							<option value="IIIT">IIIT</option>
							<option value="University">University</option>
							<option value="Industries">Industries</option>
							<option value="Corporate Houses">Corporate Houses</option>
							<option value="7">Any other Institute of
								International/National/State Importance</option>

						</select>
					</div>

					<div class="form-group" id="abc">

						<label class="control-label col-sm-6" for="page_name">Other
							Course </label>
						<!-- <div class="col-sm-3"> -->
						<input type="text" class="form-control" id="otherCourse" required
							name="otherCourse" placeholder="" value="${page.pageName}">
						<!-- </div> -->
					</div>

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

						<label class="control-label col-sm-6" for="beneficiaryMOU">Beneficiary
							of MOU </label> <input type="text" class="form-control"
							id="beneficiaryMOU" name="beneficiaryMOU"
							placeholder="Beneficiary of MOU" value="${page.pageName}"
							required>
					</div>



					<div class="form-group">

						<label class="control-label col-sm-6" for="totalParticipants">No.
							of Participants / Beneficiary </label> <input type="text"
							class="form-control" id="totalParticipants"
							name="totalParticipants"
							placeholder="No. of Participants / Beneficiary"
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


	<script type="text/javascript">
		function getData() {
			//alert("hii");
			var i = parseInt(document.getElementById("index").value);
			var title = document.getElementById("title").value;
			var mouDuration = document.getElementById("mouDuration").value;
			var academicYear = document.getElementById("academicYear").value;
			var functionalMOU = document.getElementById("functionalMOU").value;
			var beneficiaryMOU = document.getElementById("beneficiaryMOU").value;
			var otherCourse = document.getElementById("otherCourse").value;

			var totalParticipants = document
					.getElementById("totalParticipants").value;
			var temp;
			if (functionalMOU == 7) {

				temp = otherCourse;
				//alert(temp);
			} else {
				temp = functionalMOU;
			}

			var dataTable = $('#example-1').DataTable();

			dataTable.row.add(
					[ i + 1, title, mouDuration, temp, academicYear,
							beneficiaryMOU, totalParticipants ]).draw();
			document.getElementById("index").value = i + 1;
		}

		function showForm() {
			//document.getElementById("abc").style = "display:none"
			var qualType = document.getElementById("functionalMOU").value
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






</body>
</html>