<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html class=" ">
<head>

<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<!-- CORE CSS TEMPLATE - END -->
<c:url var="clearSessionAttribute" value="/clearSessionAttribute" />
</head>
<!-- END HEAD -->

<!-- BEGIN BODY -->
<body class=" " onload="clearSessionAttribute()">
	<!-- START TOPBAR -->
	<jsp:include page="/WEB-INF/views/include/topbar.jsp"></jsp:include>
	<!-- END TOPBAR -->
	<!-- START CONTAINER -->
	<div class="page-container row-fluid container-fluid">

		<!-- SIDEBAR - START -->

		<jsp:include page="/WEB-INF/views/include/left.jsp"></jsp:include>
		<!--  SIDEBAR - END -->
		<!-- START CONTENT -->
		<!-- START CONTENT -->
		<section id="main-content" class=" ">
			<section class="wrapper main-wrapper row" style=''>

				<div class='col-xs-12'>
					<div class="page-title">

						<div class="pull-left">
							<!-- PAGE HEADING TAG - START -->
							<h1 class="title">${title}</h1>
							<!-- PAGE HEADING TAG - END -->
						</div>


					</div>
				</div>
				<div class="clearfix"></div>
				<!-- MAIN CONTENT AREA STARTS -->



				<div class="col-lg-12">
					<section class="box ">
						<header class="panel_header">
							<h2 class="title pull-left">${title}</h2>
							<div class="actions panel_actions pull-right">
								<a href="${pageContext.request.contextPath}/sectionTreeList"><button
										type="button" class="btn btn-success">Add CMS Content</button></a>
								<a class="box_toggle fa fa-chevron-down"></a>
								<!--  <a class="box_setting fa fa-cog" data-toggle="modal" href="#section-settings"></a>
                    <a class="box_close fa fa-times"></a> -->

							</div>

						</header>
						<div class="content-body">
							<div class="row">


								

								<h5 class="title pull-left">
									<strong>No. of Students Admitted</strong>
								</h5>

								<div class="col-xs-12"></div>
								<div class="col-xs-12">
									<table class="table table-striped dt-responsive display">
										<thead>
											<tr>
												<th width="5%">Sr No</th>
												<th width="15%">Category</th>
												<th width="30%" style="text-align: center;" colspan="2">No.
													of Students</th>
												<th width="20%">Location</th>
												<th width="30%" style="text-align: center;" colspan="2">No.
													of Students</th>
											</tr>
											<tr>
												<th width="5%"></th>
												<th width="15%"></th>
												<th width="15%">Male</th>
												<th width="15%">Female</th>
												<th width="20%"></th>
												<th width="15%">Male</th>
												<th width="15%">Female</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>1</td>
												<td>SC</td>

												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>

												<td><div class="col-sm-12">
														<select id="salutation" name="salutation"
															class="form-control" required>
															<option value="0">State</option>
															<option value="1">Other State</option>
															<option value="2">NRI</option>
															<option value="3">PIO</option>
															<option value="4">Foreign</option>
															<option value="5">PWD</option>
														</select>
													</div></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>


											</tr>
											<tr>
												<td>2</td>
												<td>ST</td>

												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>

												<td><div class="col-sm-12">
														<select id="salutation" name="salutation"
															class="form-control" required>
															<option value="0">State</option>
															<option value="1">Other State</option>
															<option value="2">NRI</option>
															<option value="3">PIO</option>
															<option value="4">Foreign</option>
															<option value="5">PWD</option>
														</select>
													</div></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>


											</tr>

											<tr>
												<td>3</td>
												<td>OBC</td>

												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>

												<td><div class="col-sm-12">
														<select id="salutation" name="salutation"
															class="form-control" required>
															<option value="0">State</option>
															<option value="1">Other State</option>
															<option value="2">NRI</option>
															<option value="3">PIO</option>
															<option value="4">Foreign</option>
															<option value="5">PWD</option>
														</select>
													</div></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>


											</tr>

											<tr>
												<td>4</td>
												<td>VJ</td>

												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>

												<td><div class="col-sm-12">
														<select id="salutation" name="salutation"
															class="form-control" required>
															<option value="0">State</option>
															<option value="1">Other State</option>
															<option value="2">NRI</option>
															<option value="3">PIO</option>
															<option value="4">Foreign</option>
															<option value="5">PWD</option>
														</select>
													</div></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>


											</tr>

											<tr>
												<td>5</td>
												<td>NT-I,NT-II,NT-III</td>

												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>

												<td><div class="col-sm-12">
														<select id="salutation" name="salutation"
															class="form-control" required>
															<option value="0">State</option>
															<option value="1">Other State</option>
															<option value="2">NRI</option>
															<option value="3">PIO</option>
															<option value="4">Foreign</option>
															<option value="5">PWD</option>
														</select>
													</div></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>


											</tr>

											<tr>
												<td>1</td>
												<td>General</td>

												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>

												<td><div class="col-sm-12">
														<select id="salutation" name="salutation"
															class="form-control" required>
															<option value="0">State</option>
															<option value="1">Other State</option>
															<option value="2">NRI</option>
															<option value="3">PIO</option>
															<option value="4">Foreign</option>
															<option value="5">PWD</option>
														</select>
													</div></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>


											</tr>

										</tbody>
									</table>

								</div>
								




							</div>
						</div>
					</section>
				</div>

				<!-- MAIN CONTENT AREA ENDS -->
			</section>
		</section>
		</div>
		<!-- END CONTENT -->





	<!-- END CONTAINER -->
	<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->

	<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
	<script>
		function clearSessionAttribute() {

			$.getJSON('${clearSessionAttribute}', {

				ajax : 'true',

			}, function(data) {

			});

		}
	</script>
</body>
</html>
