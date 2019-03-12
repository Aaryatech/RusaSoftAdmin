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
		<!-- START CONTENT -->
		<section id="main-content" class=" ">
			<section class="wrapper main-wrapper row" style=''>

				<div class='col-xs-12'>
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



				<div class="col-lg-12">
					<section class="box ">
						<header class="panel_header">
							<h2 class="title pull-left">${title}</h2>
						
							<div class="actions panel_actions pull-right">
									<a href="${pageContext.request.contextPath}/showAddAlumini"><button type="submit"
									class="btn btn-success">Add Alumini Detail</button></a>
							

							</div>

						</header>
						<div class="content-body">
							<div class="row">


					



								<h5 class="title pull-left">
									<strong>Alumni Association/Contribution</strong>
								</h5>
								<div class="col-xs-12"></div>
								<div class="col-xs-12">

									<table class="table table-striped dt-responsive display" id="example-1">
										<thead>
											<tr>
												<th>Sr.No.</th>
										
												<th>Name of Alumni</th>
												<th>Passing Year</th>
												<th>Nature of Contribution</th>
												<th>Year of Contribution</th>
												<th>Benefit To</th>
											</tr>

										</thead>



									</table>
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
	
	<div aria-hidden="true" role="dialog" tabindex="-1" id="myModal"
		class="modal fade" style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button aria-hidden="true" data-dismiss="modal" class="close"
						type="button">×</button>
					<h4 class="modal-title">Alumini Details</h4>
				</div>
				<div class="modal-body">
					<%-- <form role="form"
						action="${pageContext.request.contextPath}/showModuleForm"
						method="get"> --%>
						<input type="hidden" class="form-control" id="pageId"
							name="pageId" >
							
								<input type="hidden" class="form-control" id="index"
							name="index" value="0">
							
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
						
							<label class="control-label col-sm-6" for="page_name">Name of Alumini
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="name"
									name="subTaut" value="${page.pageName}" placeholder="Name of Alumini"
									required>
							<!-- </div> -->
	</div>		
						
					
								
		<div class="form-group">
						<label class="control-label col-sm-6" for="academicYear">Passing
							Year</label> <select id="passYear" name="passYear"
							class="form-control" required>
							<option value="2018-2019">2018-2019</option>
							<option value="2017-2018">2017-2018</option>
							<option value="2016-2017">2016-2017</option>
								<option value="2015-2016">2015-2016</option>

						</select>
					</div>
	
	<div class="form-group">
						<label class="control-label col-sm-6" for="page_name">Nature of Contribution</label> <select
								id="nature" name="sem" class="form-control" onchange="showForm()" required>
								<option value="Financial">Financial</option>
												<option value="Non Financial">Non Financial</option>
								
								
							</select>
						</div>
			
									
						
											
		<div class="form-group">
						
							<label class="control-label col-sm-3" for="page_name">Year of Contribution
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="date" class="form-control" id="year1"
									name="subTaut" value="${page.pageName}"
									required>
							<!-- </div> -->
	</div>
	
						
					
									<div class="form-group">
						<label class="control-label col-sm-6" for="page_name">Benefit To</label> <select
								id="level" name="sem" class="form-control" onchange="showForm()" required>
								<option value="Students">Students</option>
															<option value="Staff">Staff</option>
															<option value="Faculty">Faculty</option>
															<option value="Department">Department</option>
															<option value="Institute">Institute</option>
															<option value="Society">Society</option>
															<option value="7">Any Other</option>
								
								
							</select>
						</div>
						
						
		<div class="form-group" id="abc">
						
							<label class="control-label col-sm-6" for="page_name">Benefit To Other
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="otherScheme"
									name="otherScheme" value="${page.pageName}" placeholder="Benefit To Other"
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
	//alert("hii");
			var i = parseInt(document.getElementById("index").value);
		var name=document.getElementById("name").value
		var academicYear=document.getElementById("academicYear").value
		var year=document.getElementById("passYear").value
		var nature=document.getElementById("nature").value
		var year1=document.getElementById("year1").value
		
		
		var package1=document.getElementById("level").value
		
		var otherScheme=document.getElementById("otherScheme").value
		//alert(stud);
		var temp;
		if (package1 == 7) {

			temp=otherScheme;
			//alert(temp);
		} 
		else{
			temp=package1;
		}
		
		
		//alert(stud);
		
		var dataTable = $('#example-1')
		.DataTable();
		
		dataTable.row
		.add(
				[
					i+1,
					academicYear,
					name,
					year,
					nature,
					year1,
					temp
					
						 ])
		.draw();
		
		
		document.getElementById("index").value = i + 1;
	}
	
	function showForm() {
		//document.getElementById("abc").style = "display:none"
			var qualType=document.getElementById("level").value
			//alert("qualType::"+qualType);
			
			if (qualType == 7) {

				document.getElementById("abc").style = "visible"
				
					
			} 
			else{
				document.getElementById("abc").style = "display:none"
			}
		
		}
	function hideText() {
		//alert("hii");
		document.getElementById("abc").style = "display:none"
			
		
		}

	</script>
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
