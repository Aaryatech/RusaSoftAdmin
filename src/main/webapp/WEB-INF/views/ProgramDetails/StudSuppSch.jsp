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
							<a href="#myModal"	data-toggle="modal"><button type="submit"
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



								<h5 class="title pull-left">
									<strong>Students Support Scheme</strong>
								</h5>
							
								<div class="col-xs-12">

									<table class="table table-striped dt-responsive display" id="example-1">
										<thead>
											<tr>
												<th width="5%">Sr.No.</th>
												<th width="10%">Year</th>
												<th width="10%">Name of Scheme</th>
												<th width="10%">Level</th>
												<th width="30%" >Type</th>
												<th width="10%">No. of  Benefited Students</th>
												<th width="10%">Year of Implementation</th>
												<th width="15%">Name of Support Agency</th>
											</tr>

										</thead>

</table>
								</div>

<!-- 
										<tbody>

											<tr>
												<td>1</td>
												<td>Capability Enhancement</td>

												<td><div class="col-sm-12">
														<select id="salutation" name="salutation"
															class="form-control" required>
															<option value="0">National</option>
															<option value="1">International</option>
															<option value="2">State</option>
															<option value="3">Regional</option>

														</select>
													</div></td>

												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>


												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>


											</tr>
											<tr>
												<td>2</td>
												<td>Career Counseling</td>

												<td><div class="col-sm-12">
														<select id="salutation" name="salutation"
															class="form-control" required>
															<option value="0">National</option>
															<option value="1">International</option>
															<option value="2">State</option>
															<option value="3">Regional</option>

														</select>
													</div></td>

												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>


												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>


											</tr>

											<tr>
												<td>3</td>
												<td>Competitive Exams(MPSC,UPSC,PSU,RRB,etc)</td>

												<td><div class="col-sm-12">
														<select id="salutation" name="salutation"
															class="form-control" required>
															<option value="0">National</option>
															<option value="1">International</option>
															<option value="2">State</option>
															<option value="3">Regional</option>

														</select>
													</div></td>

												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>


												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>


											</tr>
											<tr>
												<td>3</td>
												<td>Higher Education Entrance Exams(GATE,MAT,GPAT,CAT
													etc)</td>

												<td><div class="col-sm-12">
														<select id="salutation" name="salutation"
															class="form-control" required>
															<option value="0">National</option>
															<option value="1">International</option>
															<option value="2">State</option>
															<option value="3">Regional</option>

														</select>
													</div></td>

												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>


												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>


											</tr>

											<tr>
												<td>4</td>
												<td>Vocational Education Training</td>

												<td><div class="col-sm-12">
														<select id="salutation" name="salutation"
															class="form-control" required>
															<option value="0">National</option>
															<option value="1">International</option>
															<option value="2">State</option>
															<option value="3">Regional</option>

														</select>
													</div></td>

												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>


												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>


											</tr>

											<tr>
												<td>6</td>
												<td>Any other</td>

												<td><div class="col-sm-12">
														<select id="salutation" name="salutation"
															class="form-control" required>
															<option value="0">National</option>
															<option value="1">International</option>
															<option value="2">State</option>
															<option value="3">Regional</option>

														</select>
													</div></td>

												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>


												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>
												<td><input type="text" class="form-control" id="curExp"
													name="curExp" value="" required></td>

											</tr>

											<tr>
												<td></td>
												<td><button type="submit" class="btn btn-primary">Add</button></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
											</tr>
										</tbody> -->
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
					<h4 class="modal-title">Support Scheme Details </h4>
				</div>
				<div class="modal-body">
				<%-- 	<form role="form"
						action="${pageContext.request.contextPath}/showModuleForm"
						method="get"> --%>
						<input type="hidden" class="form-control" id="pageId"
							name="pageId" >
							
								<input type="hidden" class="form-control" id="index"
							name="index" value="0" >
							
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
							<label class="control-label col-sm-2" for="page_name">Scheme Name</label><select
								id="cat" name="sem" class="form-control" onchange="showForm()" required>
								<option value="Capability Enhancement">Capability Enhancement</option>
								<option value="Competitive Exams(MPSC,UPSC,PSU,RRB,etc)">Competitive Exams(MPSC,UPSC,PSU,RRB,etc)</option>
								<option value="Higher Education Entrance Exams(GATE,MAT,GPAT,CAT
													etc)">Higher Education Entrance Exams(GATE,MAT,GPAT,CAT
													etc)</option>
								<option value="Vocational Education Training">Vocational Education Training</option>
								<option value="7">Any Other</option>
								
							
							


							</select>
						</div>
						<div class="form-group" id="abc">
						
							<label class="control-label col-sm-6" for="page_name">Other Scheme
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="otherScheme"
									name="otherScheme" value="${page.pageName}" placeholder="Other Scheme"
									required>
							<!-- </div> -->
	</div>
						
						
									<div class="form-group">
						<label class="control-label col-sm-2" for="page_name">Level</label> <select
								id="level" name="sem" class="form-control" onchange="showForm()" required>
								<option value="0">National</option>
															<option value="International">International</option>
															<option value="State">State</option>
															<option value="Regional">Regional</option>

								

							</select>
						</div>
						
						
									<div class="form-group">
						<label class="control-label col-sm-2" for="page_name">Type</label> <select
								id="type" name="sem" class="form-control" onchange="showForm()" required>
								
															<option value="Govt">Govt.</option>
															<option value="Non Govt.">Non Govt.</option>
														

								

							</select>
						</div>
						
						<div class="form-group">
						
							<label class="control-label col-sm-6" for="page_name">No. of Students Benefited 
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="gen"
									name="subTaut" value="${page.pageName}" placeholder="No. of Students Benefited"
									required>
							<!-- </div> -->
	</div>
							
						<div class="form-group">
						
							<label class="control-label col-sm-6" for="page_name">Name of Support Agency 
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="stud" placeholder="Name of Support Agency"
									name="subTaut" value="${page.pageName}"
									required>
									
										<input type="hidden" id="index" name="index" value="0">
							<!-- </div> -->
	</div>
		
		
							<div class="form-group">
						<label class="control-label col-sm-6" for="year">Year of Implementation
						</label> <select id="year" name="year"
							class="form-control" required>
							<option value="2018-2019">2018-2019</option>
							<option value="2017-2018">2017-2018</option>
							<option value="2016-2017">2016-2017</option>
	<option value="2015-2016">2015-2016</option>
						</select>
					</div>



						<button type="button" class="btn btn-primary" onclick="getData()">Submit</button>
					<!-- </form> -->
				</div>
			</div>
		</div>
	</div>  
	
	<script type="text/javascript">
	function getData() {
	//alert("hii");
		var i = parseInt(document.getElementById("index").value);
		var year1=document.getElementById("academicYear").value
		var cat=document.getElementById("cat").value
		var level=document.getElementById("level").value
		var type=document.getElementById("type").value
		var gen=document.getElementById("gen").value
		var stud=document.getElementById("stud").value
		var year=document.getElementById("year").value
		
		var otherScheme=document.getElementById("otherScheme").value
		//alert(stud);
		var temp;
		if (cat == 7) {

			temp=otherScheme;
			//alert(temp);
		} 
		else{
			temp=cat;
		}
		var dataTable = $('#example-1')
		.DataTable();
		
		dataTable.row
		.add(
				[
					i+1,
					year1,
					temp,
					level,
					type,
					gen,
					stud,
					year
					
						 ])
		.draw();
		
		
		document.getElementById("index").value = i + 1;
	}
	function showForm() {
		//document.getElementById("abc").style = "display:none"
			var qualType=document.getElementById("cat").value
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
 
	 
</body>
</html>