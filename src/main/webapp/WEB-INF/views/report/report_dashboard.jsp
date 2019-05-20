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
		<c:url value="/getProgramTypeByProgram" var="getProgramTypeByProgram"></c:url>
	
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

						<!-- 	<div class="pull-left">
							PAGE HEADING TAG - START
							<h1 class="title">Add Department</h1>
							PAGE HEADING TAG - END
						</div> -->
					</div>
				</div>
				<div class="clearfix"></div>
				<!-- MAIN CONTENT AREA STARTS -->
				<div class="col-lg-12">
					<section class="box ">
						<header class="panel_header">
							<h2 class="title pull-left">${title}</h2>
							<div class="actions panel_actions pull-right">
								<%-- 	<a href="${pageContext.request.contextPath}/showDeptList"><button
										type="button" class="btn btn-info">Back</button></a> --%>
							</div>
						</header>
						<div class="content-body">
						
							<div class="row" style="padding-bottom: 0px;">
							
							<div class="form-group">
													<label class="control-label col-sm-1" for="status">Academic Year
														<span class="text-danger"></span>
													</label>
													<div class="col-sm-2">
														<select id="ac_year" name="ac_year"
															class="form-control">

															<c:forEach items="${acaYearList}" var="acYear">
															<option value="${acYear.yearId}">${acYear.academicYear}</option>
															</c:forEach>
															
														</select>
														<span class="error_form text-danger" id="prog_type_field"
															style="display: none;">Please select program type</span>
													</div>
												
													<label class="control-label col-sm-1" for="status">Program
														<span class="text-danger"></span>
													</label>
													<div class="col-sm-3">
														<select id="prog_type" name="prog_type"
															class="form-control" onchange="getProgramTypeByProgram()">

															<c:forEach items="${progTypeList}" var="progType">
															<option value="${progType.programId}">${progType.programName}</option>
															</c:forEach>
															
														</select>
														<span class="error_form text-danger" id="prog_type_field"
															style="display: none;">Please select program type</span>
													</div>
												
													<label class="control-label col-sm-1" for="page_order">
														Program Type<span class="text-danger">*</span>
													</label>
													<div class="col-sm-3">

													<select id="prog_name" name="prog_name"
													class="form-control" required>

												</select>
													
														<%-- <input type="text" class="form-control" id="prog_name"
															value="${trainPlace.programName}" onchange="trim(this)" name="prog_name"
															placeholder="Name of Program" maxlength="100"> --%>
															<span class="error_form text-danger" id="prog_name_field"
															style="display: none;">Please enter program name</span>
													</div>
												</div>
							</div>
							<br/>
							<div class="row" style="padding-bottom: 0px;">
								<div class="col-lg-12">
								<form id="reportForm">

									<div class="panel-group primary" id="accordion-2"
										role="tablist" aria-multiselectable="true">
										<div class="panel panel-default">
											<div class="panel-heading" role="tab" id="headingOne2">
												<h4 class="panel-title">
													<a data-toggle="collapse" data-parent="#accordion-2"
														href="#collapseOne-2" aria-expanded="true"
														aria-controls="collapseOne-2"> <i class='fa fa-check'></i>
														Criteria 1 : Curricular Aspects
													</a>
												</h4>
											</div>
											<div id="collapseOne-2" class="panel-collapse collapse "
												role="tabpanel" aria-labelledby="headingOne2">
												<div class="panel-body">
													<div class="col-lg-10">1] No. of Certificate/Diploma
														Programs</div>
													<div class="col-lg-2">
														<a href="#" onclick="getProgReport(0)"><i
															class="fa fa-file-excel-o" style="color: green;"
															aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
															href="#" onclick="getProgReport(1)"><i class="fa fa-file-pdf-o"
															style="color: red;" aria-hidden="true"></i>&nbsp;PDF</a>
													</div>
												</div>
												<div class="panel-body">
													<div class="col-lg-10">2] Percentage(%) of
														Participation in various University Bodies</div>
													<div class="col-lg-2">
														<a href="#" onclick="getReport2(0)"><i
															class="fa fa-file-excel-o" style="color: green;"
															aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
															href="#" onclick="getReport2(1)"><i class="fa fa-file-pdf-o"
															style="color: red;" aria-hidden="true"></i>&nbsp;PDF</a>
													</div>
												</div>
											</div>

										</div>

										<div class="panel panel-default">
											<div class="panel-heading" role="tab" id="headingTwo2">
												<h4 class="panel-title">
													<a class="collapsed" data-toggle="collapse"
														data-parent="#accordion-2" href="#collapseTwo-2"
														aria-expanded="false" aria-controls="collapseTwo-2"> <i
														class='fa fa-check'></i>Criteria 2 : Teaching,Learning and
														Evaluation
													</a>
												</h4>
											</div>
											<div id="collapseTwo-2" class="panel-collapse collapse"
												role="tabpanel" aria-labelledby="headingTwo2">
												<div class="panel-body">
													<div class="col-lg-10">1] Average % of Students from
														other States/Countries – Yearwise</div>
													<div class="col-lg-2">
														<a href="#" onclick="hi()"><i
															class="fa fa-file-excel-o" style="color: green;"
															aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
															href="#" onclick="hi()"><i class="fa fa-file-pdf-o"
															style="color: red;" aria-hidden="true"></i>&nbsp;PDF</a>
													</div>
												</div>
												<div class="panel-body">
													<div class="col-lg-10">2] Average Enrollment
														Percentage</div>
													<div class="col-lg-2">
														<a href="#" onclick="hi()"><i
															class="fa fa-file-excel-o" style="color: green;"
															aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
															href="#" onclick="hi()"><i class="fa fa-file-pdf-o"
															style="color: red;" aria-hidden="true"></i>&nbsp;PDF</a>
													</div>
												</div>
											</div>
										</div>

										<div class="panel panel-default">
											<div class="panel-heading" role="tab" id="headingOne3">
												<h4 class="panel-title">
													<a data-toggle="collapse" data-parent="#accordion-2"
														href="#collapseOne-3" aria-expanded="true"
														aria-controls="collapseOne-3"> <i class='fa fa-check'></i>
														Criteria 3 : Research, Innovation and Extension
													</a>
												</h4>
											</div>
											<div id="collapseOne-3" class="panel-collapse collapse "
												role="tabpanel" aria-labelledby="headingOne3">
												<div class="panel-body">
													<div class="col-lg-10">1] No of Recognition/Awards</div>
													<div class="col-lg-2">
														<a href="#" onclick="hi()"><i
															class="fa fa-file-excel-o" style="color: green;"
															aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
															href="#" onclick="hi()"><i class="fa fa-file-pdf-o"
															style="color: red;" aria-hidden="true"></i>&nbsp;PDF</a>
													</div>
												</div>
												<div class="panel-body">
													<div class="col-lg-10">2] Teachers Recognition/Awards
														and Incentives Information</div>
													<div class="col-lg-2">
														<a href="#" onclick="hi()"><i
															class="fa fa-file-excel-o" style="color: green;"
															aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
															href="#" onclick="hi()"><i class="fa fa-file-pdf-o"
															style="color: red;" aria-hidden="true"></i>&nbsp;PDF</a>
													</div>
												</div>
											</div>
										</div>

										<div class="panel panel-default">
											<div class="panel-heading" role="tab" id="headingOne4">
												<h4 class="panel-title">
													<a data-toggle="collapse" data-parent="#accordion-2"
														href="#collapseOne-4" aria-expanded="true"
														aria-controls="collapseOne-4"> <i class='fa fa-check'></i>
														Criteria 4 : Infrastructure and Learning Resources
													</a>
												</h4>
											</div>
											<div id="collapseOne-4" class="panel-collapse collapse "
												role="tabpanel" aria-labelledby="headingOne4">
												<div class="panel-body">
													<div class="col-lg-10">1] Budget on Infrastructure
														augmentation</div>
													<div class="col-lg-2">
														<a href="#" onclick="hi()"><i
															class="fa fa-file-excel-o" style="color: green;"
															aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
															href="#" onclick="hi()"><i class="fa fa-file-pdf-o"
															style="color: red;" aria-hidden="true"></i>&nbsp;PDF</a>
													</div>
												</div>
												<div class="panel-body">
													<div class="col-lg-10">2] Library Automation and ILMS
														Information</div>
													<div class="col-lg-2">
														<a href="#" onclick="getProgReport(0)"><i
															class="fa fa-file-excel-o" style="color: green;"
															aria-hidden="true"></i>&nbsp;Excel</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
															href="#" onclick="getProgReport(1)"><i class="fa fa-file-pdf-o"
															style="color: red;" aria-hidden="true"></i>&nbsp;PDF</a>
													</div>
												</div>
											</div>
										</div>
									</div>
									<input type="hidden" id="p" name="p" value="0">
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
<script type="text/javascript">
		function getProgReport(prm) {
			if(prm==1){
				document.getElementById("p").value="1";
			}
			var form = document.getElementById("reportForm");

			form.setAttribute("target", "_blank");
			form.setAttribute("method", "post");

			form.action = ("${pageContext.request.contextPath}/showProgReport/");

			form.submit();
		}
		
		function getReport2(prm){
			if(prm==1){
				document.getElementById("p").value="1";
			}
			var form = document.getElementById("reportForm");

			form.setAttribute("target", "_blank");
			form.setAttribute("method", "post");

			form.action = ("${pageContext.request.contextPath}/showFacPartiVarBodies/");

			form.submit();
		}
	</script>
	
		<script type="text/javascript">
		function getProgramTypeByProgram() {

			var programType = document.getElementById("prog_type").value;
			//alert("programType" + programType);
			
			var valid = true;

			/* if (programType == null || programType == "") {
				valid = false;
				alert("Please Select Program");
			} */

			
				$.getJSON('${getProgramTypeByProgram}', {
					programType : programType,
					ajax : 'true',
				},

				function(data) {
					//alert(data);
				
					var html;
					var len = data.length;
					for (var i = 0; i < len; i++) {

						html += '<option value="' + data[i].programId + '">'
								+ data[i].nameOfProgram + '</option>';

					}
					html += '</option>';

					$('#prog_name').html(html);
					$("#prog_name").trigger("chosen:updated");

				});
			//alert("Hi")
		}
	</script>

</body>
</html>