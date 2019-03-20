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
								<a
									href="${pageContext.request.contextPath}/showCollaborationLinkages"><button
										type="button" class="btn btn-info">Back</button></a>
								<%-- <a
									href="${pageContext.request.contextPath}/publicationList"><button
										type="button" class="btn btn-info">Back</button></a> <!-- <a
									class="box_toggle fa fa-chevron-down"></a> --> --%>
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertPublicationDetail"
										method="post" name="form_sample_2" id="form_sample_2"
										onsubmit="return confirm('Do you really want to submit the form?');">

										<ul class="nav nav-tabs">
											<li class="active"><a href="#home" data-toggle="tab">
													<i class="fa fa-home"></i> ${title}
											</a></li>

										</ul>

										<div class="tab-content">
											<div class="tab-pane fade in active" id="home">

												<div class="form-group">
													<label class="control-label col-sm-2" for="colName">Name
														of Collaboration / Linkage<span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<select id="colName" name="colName" class="form-control"
															required><option value="-1">Select</option>
															<option value="Faculty Exchange">Faculty
																Exchange</option>
															<option value="Student Exchange">Student
																Exchange</option>
															<option value="Internship">Internship</option>
															<option value="Fieldtrip">Fieldtrip</option>
															<option value="Research">Research</option>
															<option value="On job paining">On job paining</option>

														</select>
													</div>
												</div>
												<div class="form-group">

													<label class="control-label col-sm-2" for="agency">Collaboration
														Linkage with Agency<span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="agency"
															name="agency" autocomplete="off"
															onchange="return trim(this)"
															placeholder="Collaboration Linkage with Agency"
															value="${page.pageName}" required>
													</div>
												</div>




												<div class="form-group">

													<label class="control-label col-sm-2" for="linkageNature">Nature
														of Linkage Collaboration <span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="linkageNature"
															name="linkageNature" autocomplete="off"
															onchange="return trim(this)"
															placeholder="Nature of Linkage Collaboration"
															value="${page.pageName}" required>
													</div>
												</div>

												<div class="form-group">

													<label class="control-label col-sm-2" for="beneficiary">Beneficiary
														of Collaboration / Linkage<span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<select id="beneficiary" name="beneficiary"
															class="form-control" required>
															<option value="-1">Select</option>
															<option value="1">Students</option>
															<option value="2">Staff</option>
															<option value="3">Students And Staff</option>
														</select>
													</div>
												</div>



												<div class="form-group">

													<label class="control-label col-sm-2"
														for="totalParticipants">No. of Participants /
														Beneficiary<span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" autocomplete="off"
															id="totalParticipants" name="totalParticipants"
															placeholder="No. of Participants / Beneficiary"
															value="${page.pageName}" required>
													</div>
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
					<h4 class="modal-title">Collaboration & Linkages</h4>
				</div>
				<div class="modal-body">









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
			var colName = document.getElementById("colName").value;
			var agency = document.getElementById("agency").value;
			var academicYear = document.getElementById("academicYear").value;

			var linkageNature = document.getElementById("linkageNature").value;
			var beneficiary = document.getElementById("beneficiary").value;
			var totalParticipants = document
					.getElementById("totalParticipants").value;

			var dataTable = $('#example-1').DataTable();

			dataTable.row.add(
					[ i + 1, colName, agency, academicYear, linkageNature,
							beneficiary, totalParticipants ]).draw();
			document.getElementById("index").value = i + 1;
		}
	</script>



	<script type="text/javascript">
		$(function() {
			$('#submitForm').submit(
					function() {
						$("input[type='submit']", this).val("Please Wait...")
								.attr('disabled', 'disabled');

						return true;
					});
		});

		function trim(el) {
			el.value = el.value.replace(/(^\s*)|(\s*$)/gi, ""). // removes leading and trailing spaces
			replace(/[ ]{2,}/gi, " "). // replaces multiple spaces with one space 
			replace(/\n +/, "\n"); // Removes spaces after newlines
			return;
		}
	</script>


</body>
</html>