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


								<div class="col-xs-12">


									<table class="table table-striped dt-responsive display">
										<thead>
											<tr>
												<th width="5%">Sr No</th>
												<th width="10%">Feedback</th>
												<th width="10%">Y/N</th>
												<th width="20%">Sem.wise/Yearwise</th>
												
											</tr>

										</thead>



										<tbody>

										
											<tr>
												<td>1</td>
												<td>Structured Feedback Collected</td>

												<td><div class="col-sm-12">
														<select id="salutation" name="salutation"
															class="form-control" required>
															<option value="0">Yes</option>
															<option value="1">No</option>
															

														</select>
													</div></td>
												
												<td><div class="col-sm-12">
														<select id="salutation" name="salutation"
															class="form-control" required>
															<option value="0">Semwise</option>
															<option value="1">Yr.wise</option>
															

														</select>
													</div></td>

											</tr>
											
											
											
											<tr>
												<td>2</td>
												<td>Feedback form Students</td>

												<td><div class="col-sm-12">
														<select id="salutation" name="salutation"
															class="form-control" required>
															<option value="0">Yes</option>
															<option value="1">No</option>
															

														</select>
													</div></td>
												
												<td><div class="col-sm-12">
														<select id="salutation" name="salutation"
															class="form-control" required>
															<option value="0">Semwise</option>
															<option value="1">Yr.wise</option>
															

														</select>
													</div></td>

											</tr>
											
											
												
											<tr>
												<td>3</td>
												<td>Feedback form Teachers</td>

												<td><div class="col-sm-12">
														<select id="salutation" name="salutation"
															class="form-control" required>
															<option value="0">Yes</option>
															<option value="1">No</option>
															

														</select>
													</div></td>
												
												<td><div class="col-sm-12">
														<select id="salutation" name="salutation"
															class="form-control" required>
															<option value="0">Semwise</option>
															<option value="1">Yr.wise</option>
															

														</select>
													</div></td>

											</tr>
											
												
											<tr>
												<td>4</td>
												<td>Feedback form Employees</td>

												<td><div class="col-sm-12">
														<select id="salutation" name="salutation"
															class="form-control" required>
															<option value="0">Yes</option>
															<option value="1">No</option>
															

														</select>
													</div></td>
												
												<td><div class="col-sm-12">
														<select id="salutation" name="salutation"
															class="form-control" required>
															<option value="0">Semwise</option>
															<option value="1">Yr.wise</option>
															

														</select>
													</div></td>

											</tr>
											
											
											<tr>
												<td>5</td>
												<td>Feedback form Aluminis</td>

												<td><div class="col-sm-12">
														<select id="salutation" name="salutation"
															class="form-control" required>
															<option value="0">Yes</option>
															<option value="1">No</option>
															

														</select>
													</div></td>
												
												<td><div class="col-sm-12">
														<select id="salutation" name="salutation"
															class="form-control" required>
															<option value="0">Semwise</option>
															<option value="1">Yr.wise</option>
															

														</select>
													</div></td>

											</tr>
											
											<tr>
												<td>5</td>
												<td>Feedback form Parent</td>

												<td><div class="col-sm-12">
														<select id="salutation" name="salutation"
															class="form-control" required>
															<option value="0">Yes</option>
															<option value="1">No</option>
															

														</select>
													</div></td>
												
												<td><div class="col-sm-12">
														<select id="salutation" name="salutation"
															class="form-control" required>
															<option value="0">Semwise</option>
															<option value="1">Yr.wise</option>
															

														</select>
													</div></td>

											</tr>
											
											
										</tbody>
									</table>
								</div>

 <div class="form-group">
															<div class="col-sm-offset-2 col-sm-10">
																<button type="submit" class="btn btn-primary">Submit</button>
																<button type="reset" class="btn btn-default">Reset</button>
															</div>
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
