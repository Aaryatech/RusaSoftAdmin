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
									href="${pageContext.request.contextPath}/showEfficienttGrievance"><button
										type="button" class="btn btn-info">Back</button></a>
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



												<div class="form-group">
													<label class="control-label col-sm-2" for="isReform">
														Efficient : <span class="text-danger">*</span>
													</label>
													<div class="col-sm-3">
														<input type="radio" onclick="showReforms(this.value)"
															id="istransparent" name="istransparent" value="1" checked>Yes
														<input type="radio" id="istransparent"
															onclick="showReforms(this.value)" name="istransparent"
															value="0">No
													</div>



												</div>


												<div id="transGr">
													<div class="form-group">

														<label class="control-label col-sm-2" for="Grievances">Grievances
															<span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<input type="text" class="form-control" id="grievances"
																name="grievances" placeholder="Grievances"
																value="${page.pageName}" required>
														</div>
													</div>



													<div class="form-group">

														<label class="control-label col-sm-2" for="remark">Remark
															<span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<input type="text" class="form-control" id="remark"
																name="remark" placeholder="Remark"
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

			</section>
		</section>
	</div>



	<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>




	<div class="modal fade col-xs-12" id="myModal1" tabindex="-1"
		role="dialog" aria-hidden="true">
		<div class="modal-dialog" style="width: 65%">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Mechanism of Examination related
						Grievances</h4>
				</div>
				<div class="modal-body">






					<!-- Link on Website for Activity Report -->

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
				</div>


			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
				<input type="hidden" id="index" name="index" value="0">
			</div>
		</div>
	</div>



	<script type="text/javascript">
		function getData() {
			//alert("hii");
			var i = parseInt(document.getElementById("index").value);
			var academicYear = document.getElementById("academicYear").value;
			var grievances = document.getElementById("grievances").value;
			var remark = document.getElementById("remark").value;

			var dataTable = $('#example-1').DataTable();

			dataTable.row.add([ i + 1, academicYear, grievances, remark ])
					.draw();
			document.getElementById("index").value = i + 1;
		}

		function showReforms(temp) {

			if (temp == 1) {
				document.getElementById("transGr").style = "visible"

			} else {

				document.getElementById("transGr").style = "display:none"

			}

		}
	</script>

</body>
</html>