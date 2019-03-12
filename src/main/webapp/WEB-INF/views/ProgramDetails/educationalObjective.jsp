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
<body class=" "  onload="hideText()">
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
							<%-- <h1 class="title">${title}</h1> --%>
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
							<a href="#myModal" data-toggle="modal"><button type="submit"
																class="btn btn-info">Add</button></a>
								<%-- <a href="${pageContext.request.contextPath}/sectionTreeList"><button
										type="button" class="btn btn-success">Add CMS Content</button></a> --%>
								<a class="box_toggle fa fa-chevron-down"></a>
								<!--  <a class="box_setting fa fa-cog" data-toggle="modal" href="#section-settings"></a>
                    <a class="box_close fa fa-times"></a> -->

							</div>

						</header>
						<div class="content-body">
							<div class="row">




								<div class="col-xs-12">
<div class="form-group">

									<table class="table table-striped dt-responsive display" id="example-1">
										<thead>
											<tr>
												<th width="5%">Sr No</th>
												<th >Year</th>
												<th >Vision</th>
												<th >Mission</th>
												
												<th >Program Educational Objectives</th>
												<th>UG/PG</th>
												<th >Program Outcome (as per Accreditation Manual)</th>
											
													<th width="20%">Program Specific Outcomes(Define By the Program )</th>
												
											</tr>

                                               </thead>
														</table>
</div>
								
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
	
	
	<div aria-hidden="true" role="dialog" tabindex="-1" id="myModal"
		class="modal fade" style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button aria-hidden="true" data-dismiss="modal" class="close"
						type="button">×</button>
					<h4 class="modal-title">Program Details</h4>
				</div>
				<div class="modal-body">
					<%-- <form role="form"
						action="${pageContext.request.contextPath}/showModuleForm"
						method="get"> --%>
						<input type="hidden" class="form-control" id="pageId"
							name="pageId" >
							<input type="hidden" class="form-control" id="index"
							name="index"  value="0">
							
							
							<div class="form-group">
						<label class="control-label col-sm-3" for="finantialYear">Academic
							Year</label> <select id="academicYear" name="academicYear"
							class="form-control" required>
							<option value="2018-2019">2018-2019</option>
							<option value="2017-2018">2017-2018</option>
							<option value="2016-2017">2016-2017</option>
	<option value="2015-2016">2015-2016</option>
						</select>
					</div>
								
						<div class="form-group">
						
							<label class="control-label col-sm-3" for="page_name">Vision
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="vision"  placeholder="Vision"
									name="code" value="${page.pageName}"
									required>
							<!-- </div> -->
	</div>
								
						<div class="form-group">
						
							<label class="control-label col-sm-3" for="page_name">Mission
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="mission"  placeholder="Mission"
									name="code" value="${page.pageName}"
									required>
							<!-- </div> -->
	</div>
	
	
	<div class="form-group">
						
							<label class="control-label col-sm-6" for="page_name">Program Educational Objective
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="objective" placeholder="Program Educational Objective"
									name="objectives" value="${page.pageName}"
									required>
							<!-- </div> -->
	</div>
				<div class="form-group">
						<label class="control-label col-sm-2" for="page_name">UG/PG</label> <select
								id="pg" name="pg" class="form-control"  required>
								<option value="UG">UG</option>
								<option value="PG">PG</option>
								
							
							</select>
						</div>
						<div class="form-group">
						
							<label class="control-label col-sm-10" for="page_name">Program Outcome (as per Accreditation Manual)
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="po" placeholder="Program Outcome (as per Accreditation Manual)"
									name="code" value="${page.pageName}"
									required>
							<!-- </div> -->
	</div>
						
				
						
						<div class="form-group">
						
							<label class="control-label col-sm-10" for="page_name">Program Specific Outcomes(Define By the Program )
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="pso" placeholder="Program Specific Outcomes(Define By the Program )"
									name="subTaut" value="${page.pageName}"
									required>
							<!-- </div> -->
	</div>
						
		
								
					

						<button type="submit" class="btn btn-primary" onclick="getData()">Submit</button>
					<!-- </form> -->
				</div>
			</div>
		</div>
	</div>
	
	
	
	
	
	
	<script type="text/javascript">

	

	function getData() {
	
		var i = parseInt(document.getElementById("index").value);
		var objective=document.getElementById("objective").value
		var vision=document.getElementById("vision").value
		//alert(vision);
		
		var mission=document.getElementById("mission").value
		//alert(mission);
		var po=document.getElementById("po").value
		//alert(po);
		
		//var ug=document.getElementById("ug").value
		//alert(ug);
		var pso=document.getElementById("pso").value
		//alert(pso);
		
		var year = document.getElementById("academicYear").value;
		var pg=document.getElementById("pg").value
		//alert(pg);
		//alert("hii");
		var dataTable = $('#example-1')
		.DataTable();
		
		dataTable.row
		.add(
				[
					i+1,
					year,
					vision,
					mission,
					objective,
					pg,
					po,
					pso
					
					
						 ])
		.draw();
		
		document.getElementById("index").value = i + 1;
		
	}
	
	
	
	
	</script>

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

