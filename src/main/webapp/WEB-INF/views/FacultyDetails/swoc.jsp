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

							<div class="actions panel_actions pull-right"><<%-- a
								href="${pageContext.request.contextPath}/publicationList"><button
								type="button" class="btn btn-info">Back</button></a> --%> <a
								class="box_toggle fa fa-chevron-down"></a></div>

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
													<i class="fa fa-home"></i> Strength
											</a></li>
											<li><a href="#weak" data-toggle="tab"> <i
													class="fa fa-home"></i> Weakness
											</a></li>
											<li><a href="#oppt" data-toggle="tab"> <i
													class="fa fa-home"></i> Opportunity
											</a></li>
											<li><a href="#challenge" data-toggle="tab"> <i
													class="fa fa-home"></i> Challenges
											</a></li>
											<li><a href="#copo" data-toggle="tab"> <i
													class="fa fa-home"></i> CO-PO Mapping
											</a></li>

										</ul>

										<div class="tab-content">
											<!-- 1 -->
											<div class="tab-pane fade in active " id="strength">





												<div class="col-xs-12">

													<div class="row">

														<label class="col-sm-2 text-left" for="hh">
															Strength </label>
														<div class="col-sm-6">
															<input type="text" maxlength="10" class="form-control"
																id="hh" name="hh" placeholder="Strength" required>
														</div>

														<div class="col-sm-4">

															<input type="button" class="btn btn-info" value="Add">
														</div>
													</div>


													<table id="example1"
														class="table table-striped dt-responsive display">
														<thead>
															<tr>
																<th width="10%">Sr No</th>
																<th width="30%">Strength</th>

															</tr>
														</thead>



													</table>

												</div>

												<div class="clearfix"></div>


											</div>
											<div class="tab-pane fade in " id="weak">






												<div class="col-xs-12">

													<div class="row">

														<label class="col-sm-2 text-left" for="weak">
															Weakness </label>
														<div class="col-sm-6">
															<input type="text" maxlength="10" class="form-control"
																id="weak" name="weak" placeholder="Weak" required>
														</div>

														<div class="col-sm-4">

															<input type="button" class="btn btn-info" value="Add">
														</div>
													</div>

													<table id="example7"
														class="table table-striped dt-responsive display">
														<thead>
															<tr>
																<th width="10%">Sr No</th>
																<th width="30%">Weakness</th>

															</tr>
														</thead>



													</table>

												</div>

												<div class="clearfix"></div>
											</div>


											<div class="tab-pane " id="oppt">


												<div class="col-sm-12">

													<div class="row">

														<label class="col-sm-2 text-left" for="Opportunity">
															Opportunity </label>
														<div class="col-sm-6">
															<input type="text" maxlength="10" class="form-control"
																id="Opportunity" name="Opportunity"
																placeholder="Opportunity" required>
														</div>

														<div class="col-sm-4">

															<input type="button" class="btn btn-info" value="Add">
														</div>
													</div>


													<div class="form-group">
														<table id="example3"
															class="table table-striped dt-responsive display">
															<thead>
																<tr>
																	<th width="10%">Sr No</th>
																	<th width="30%">Opportunity</th>

																</tr>
															</thead>



														</table>
													</div>


												</div>
												<div class="clearfix"></div>

											</div>

											<div class="tab-pane " id="challenge">
												<div class="col-sm-12">
													<label class="col-sm-2 text-left" for="hh">
														Challenge </label>
													<div class="col-sm-6">
														<input type="text" maxlength="10" class="form-control"
															id="Challenge" name="Challenge" placeholder="Challenge"
															required>
													</div>

													<div class="col-sm-4">

														<input type="button" class="btn btn-info" value="Add">
													</div>


													<div class="form-group">
														<table id="example4"
															class="table table-striped dt-responsive display">
															<thead>
																<tr>
																	<th width="10%">Sr No</th>
																	<th width="30%">Challenges</th>

																</tr>
															</thead>



														</table>
													</div>


												</div>

												<div class="clearfix"></div>
											</div>



											<div class="tab-pane " id="copo">




												<div class="col-sm-12">

													<div class="form-group">
														<label class="control-label col-sm-2" for="page_name">Satisfying
															Value</label>
														<div class="col-sm-4">
															<select id="val" name="val" class="form-control"
																onchange="showForm()" required>
																<option value="1">1</option>
																<option value="2">2</option>
																<option value="3">3</option>
																<option value="-">-</option>

															</select>
														</div>
													</div>


													<div class="form-group">
														<label class="control-label col-sm-2" for="page_name">Course
															Outcome</label>
														<div class="col-sm-4">
															<select id="co" name="co" class="form-control"
																onchange="showForm()" required>
																<option value="Good">Good</option>
																<option value="Better">Better</option>
																<option value="Best">Best</option>

															</select>
														</div>
													</div>


													<div class="form-group">
														<label class="control-label col-sm-2" for="page_name">Program
															Outcome</label>
														<div class="col-sm-4">
															<select id="po" name="po" class="form-control"
																onchange="showForm()" required>
																<option value="Good">Good</option>
																<option value="Better">Better</option>
																<option value="Best">Best</option>

															</select>
														</div>
													</div>
													<div class="form-group">

														<label class="control-label col-sm-2" for="page_order">Is
															CO/PO Mapped :<span class="text-danger">*</span>
														</label>
														<div class="col-sm-2">
															Yes <input type="radio" name="stu" id="stu" checked
																value="yes"> No<input type="radio" name="stu"
																id="stu" value="1">
														</div>

														<div class="col-sm-4">

															<input type="button" class="btn btn-info" value="Add">
														</div>

													</div>
													<div class="form-group">
														<table id="example5"
															class="table table-striped dt-responsive display">
															<thead>
																<tr>
																	<th width="10%">Sr No</th>
																	<th width="10%">Course Outcome</th>
																	<th width="10%">Program Outcome</th>
																	<th width="10%">Is CO/PO Mapped</th>
																	<th width="30%">Satisfying Value</th>

																</tr>
															</thead>



														</table>
													</div>
													<div class="clearfix"></div>
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


	<div aria-hidden="true" role="dialog" tabindex="-1" id="myModal"
		class="modal fade" style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button aria-hidden="true" data-dismiss="modal" class="close"
						type="button">×</button>
					<h4 class="modal-title">Strength</h4>
				</div>
				<div class="modal-body">
					<%-- <form role="form"
						action="${pageContext.request.contextPath}/showModuleForm"
						method="get"> --%>
					<input type="hidden" class="form-control" id="pageId" name="pageId">


					<div class="form-group">

						<label class="control-label col-sm-3" for="page_name">Strength
						</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="abc" name="abc"
								placeholder="" value="${page.pageName}" required>
						</div>
					</div>

					<button type="submit" class="btn btn-primary" onclick="getinfo()">Submit</button>
					<!-- 	</form> -->
				</div>
			</div>
		</div>
	</div>







	<div aria-hidden="true" role="dialog" tabindex="-1" id="myModal1"
		class="modal fade" style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button aria-hidden="true" data-dismiss="modal" class="close"
						type="button">×</button>
					<h4 class="modal-title">Weakness</h4>
				</div>
				<div class="modal-body">
					<%-- <form role="form"
						action="${pageContext.request.contextPath}/showModuleForm"
						method="get"> --%>
					<input type="hidden" class="form-control" id="pageId" name="pageId">


					<div class="form-group">

						<label class="control-label col-sm-3" for="page_name">Weakness
						</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="xyz" name="xyz"
								placeholder="" value="${page.pageName}" required>
						</div>
					</div>

					<button type="submit" class="btn btn-primary" onclick="getWeak()">Submit</button>
					<!-- 	</form> -->
				</div>
			</div>
		</div>
	</div>





	<div aria-hidden="true" role="dialog" tabindex="-1" id="myModal2"
		class="modal fade" style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button aria-hidden="true" data-dismiss="modal" class="close"
						type="button">×</button>
					<h4 class="modal-title">Opportunity</h4>
				</div>
				<div class="modal-body">
					<%-- <form role="form"
						action="${pageContext.request.contextPath}/showModuleForm"
						method="get"> --%>
					<input type="hidden" class="form-control" id="pageId" name="pageId">


					<div class="form-group">

						<label class="control-label col-sm-3" for="page_name">Opportunity
						</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="opt" name="opt"
								placeholder="" value="${page.pageName}" required>
						</div>
					</div>

					<button type="submit" class="btn btn-primary" onclick="getOpt()">Submit</button>
					<!-- 	</form> -->
				</div>
			</div>
		</div>
	</div>



	<div aria-hidden="true" role="dialog" tabindex="-1" id="myModal3"
		class="modal fade" style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button aria-hidden="true" data-dismiss="modal" class="close"
						type="button">×</button>
					<h4 class="modal-title">Challenges</h4>
				</div>
				<div class="modal-body">
					<%-- <form role="form"
						action="${pageContext.request.contextPath}/showModuleForm"
						method="get"> --%>
					<input type="hidden" class="form-control" id="pageId" name="pageId">


					<div class="form-group">

						<label class="control-label col-sm-3" for="page_name">Challenges
						</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="chl" name="chl"
								placeholder="" value="${page.pageName}" required>
						</div>
					</div>

					<button type="submit" class="btn btn-primary" onclick="getData3()">Submit</button>
					<!-- 	</form> -->
				</div>
			</div>
		</div>
	</div>

	<div aria-hidden="true" role="dialog" tabindex="-1" id="myModalcopo"
		class="modal fade" style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button aria-hidden="true" data-dismiss="modal" class="close"
						type="button">×</button>
					<h4 class="modal-title">COPO Mapping</h4>
				</div>
				<div class="modal-body">
					<%-- <form role="form"
						action="${pageContext.request.contextPath}/showModuleForm"
						method="get"> --%>
					<input type="hidden" class="form-control" id="pageId" name="pageId">










					<button type="submit" class="btn btn-primary" onclick="getCOPO()">Submit</button>
					<!-- 	</form> -->
				</div>
			</div>
		</div>
	</div>


	<script type="text/javascript">
		function getinfo() {
			//alert("hii");
			var i = 0;
			var abc = document.getElementById("abc").value

			var dataTable = $('#example1').DataTable();

			dataTable.row.add([ i + 1, abc

			]).draw();

		}
		function getWeak() {
			alert("hii");
			var i = 0;
			var xyz = document.getElementById("xyz").value
			alert(xyz);

			var dataTable = $('#example7').DataTable();

			dataTable.row.add([ i + 1, xyz

			]).draw();

		}
		function getOpt() {
			//alert("hii");
			var i = 0;
			var oppt = document.getElementById("opt").value
			//alert(oppt);

			var dataTable = $('#example3').DataTable();

			dataTable.row.add([ i + 1, oppt

			]).draw();

		}
		function getData3() {
			//alert("hii");
			var i = 0;
			var chl = document.getElementById("chl").value

			var dataTable = $('#example4').DataTable();

			dataTable.row.add([ i + 1, chl

			]).draw();

		}
		function getCOPO() {
			//alert("hii");
			var i = 0;
			var co = document.getElementById("co").value
			var po = document.getElementById("po").value
			//alert(qualName);
			var stu = document.getElementById("stu").value
			var val = document.getElementById("val").value

			var dataTable = $('#example5').DataTable();

			dataTable.row.add([ i + 1, co, po, stu, val ]).draw();

		}
	</script>


</body>
</html>