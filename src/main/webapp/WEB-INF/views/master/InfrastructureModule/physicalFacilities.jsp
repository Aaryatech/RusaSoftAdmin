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
										type="button" class="btn btn-success">Add</button></a> <a
									class="box_toggle fa fa-chevron-down"></a>
								<!--  <a class="box_setting fa fa-cog" data-toggle="modal" href="#section-settings"></a>
                    <a class="box_close fa fa-times"></a> -->

							</div>

						</header>
						<div class="content-body">
							<div class="row">
								<form class="form-horizontal"
									action="${pageContext.request.contextPath}/insertCmsForm"
									method="post" enctype="multipart/form-data"
									name="form_sample_2" id="form_sample_2"
									onsubmit="return confirm('Do you really want to submit the form?');">
									
									<div class="col-xs-12">


										<div class="form-group">
											<label class="control-label col-sm-2" for="page_name">Physical
												Facilities :<span class="text-danger">*</span>
											</label>
											<div class="col-sm-10">
												<input type="text" class="form-control" id="hodName"
													name="hodName" placeholder="Physical Facilities"
													value="${page.pageName}" required>
											</div>
										</div>


										<div class="form-group">
											<label class="control-label col-sm-2" for="page_name">Provision
												for lift :<span class="text-danger">*</span>
											</label>
											<div class="col-sm-2">
												Yes <input type="radio" name="lift" id="lift" checked
													value="0"> No<input type="radio" name="lift"
													id="lift" value="1">
											</div>
											<label class="control-label col-sm-2" for="page_name">Ramp/Rails
												:<span class="text-danger">*</span>
											</label>
											<div class="col-sm-2">
												Yes <input type="radio" name="ramp" id="ramp" checked
													value="0"> No<input type="radio" name="ramp"
													id="ramp" value="1">
											</div>
											<label class="control-label col-sm-2" for="page_name">Rest
												rooms :<span class="text-danger">*</span>
											</label>
											<div class="col-sm-2">
												Yes <input type="radio" name="restRooms" id="restRooms"
													checked value="0"> No<input type="radio"
													name="restRooms" id="restRooms" value="1">
											</div>
										</div>

										<div class="form-group">
											<label class="control-label col-sm-2" for="page_name">Braille
												Software Facilities :<span class="text-danger">*</span>
											</label>
											<div class="col-sm-2">
												Yes <input type="radio" name="brallie" id="brallie" checked
													value="0"> No<input type="radio" name="brallie"
													id="brallie" value="1">
											</div>
											<label class="control-label col-sm-3" for="page_name">Scribes
												for Examination :<span class="text-danger">*</span>
											</label>
											<div class="col-sm-2">
												Yes <input type="radio" name="scribes" id="scribes" checked
													value="0"> No<input type="radio" name="scribes"
													id="scribes" value="1">
											</div>



										</div>

										<div class="form-group">
											<label class="control-label col-sm-5" for="page_name">Special
												Skill Development for differently abled Students :<span
												class="text-danger">*</span>
											</label>
											<div class="col-sm-2">
												Yes <input type="radio" name="Skill" id="Skill" checked
													value="0"> No<input type="radio" name="Skill"
													id="Skill" value="1">
											</div>

										</div>


										<div class="form-group">
											<label class="control-label col-sm-2" for="page_name">Any
												Other Similar Facility :<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control" id="hodName"
													name="hodName" placeholder="Any Other Similar Facility"
													value="${page.pageName}" required>
											</div>
											<div class="col-sm-2">
												<button type="submit" class="btn btn-primary">Add</button>
											</div>

										</div>

										</div>


						<button type="submit" class="btn btn-primary">Submit</button>
					</form>
				</div>
			</div>
		</div>
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
