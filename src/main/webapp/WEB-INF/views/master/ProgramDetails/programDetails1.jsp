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

									<table class="table table-striped dt-responsive display" id="example8">
										<thead>
											<tr>
												<th width="5%">Sr No</th>
												<th >Vision</th>
												<th >Mission</th>
												<th >Program Outcome (as per NBA Manual)</th>
												<th>UG/PG</th>
												
													<th>Program Specific Outcomes(Define By the Program )</th>
												<th >UG/PG &nbsp; &nbsp;<a href="#myModal"	data-toggle="modal"><button type="submit"
																class="btn btn-info">Add</button></a></th>
											</tr>

                                               </thead>
														</table>
</div>
									<div class="form-group">

									<table class="table table-striped dt-responsive display" id="example10">
										<thead>
											<tr>
												<th width="5%">Sr No</th>
												
												<th >Program Outcome (as per NBA Manual)</th>
											
												
													<th >PSO(Define By the Program )</th>
													<th >Mapping(Yes/No)</th>
												<th >Satisfying Value&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<a href="#myModal2"	data-toggle="modal"><button type="submit"
																class="btn btn-info">Add</button></a></th>
											</tr>

                                               </thead>
														</table>
														</div>

<div class="form-group">
									<table class="table table-striped dt-responsive display" id="example9">
										<thead>
											<tr>
												<th width="5%">Sr No</th>
												<th >Program Type</th>
												<th >Duration Months</th>
												<th >Name of Program</th>
												<!-- <th width="30%" style="text-align: center;" colspan="2">No.
													of Students Admitted</th> -->
												<th >Date/Year of Introduction</th>
												<th >Approved by &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#myModal1"	data-toggle="modal"><button type="submit"
																class="btn btn-info">Add</button></a></th>
												
											</tr>
<!-- 
											<tr>
												<th width="5%"></th>
												<th width="10%"></th>
												<th width="10%"></th>
												<th width="20%"></th>
												<th width="15%">Sanctioned Intake</th>
												<th width="15%">Admitted Student</th>
												<th width="10%"></th>
												<th width="15%"></th>
											</tr> -->
										</thead>
</table>

<!-- 
										<tbody>

											<tr>
												<td>1</td>
												<td>Certificate</td>

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
												<td><div class="col-sm-12">
														<select id="salutation" name="salutation"
															class="form-control" required>
															<option value="0">BOS/AC</option>
															<option value="1">Industry</option>
															<option value="2">AICTE</option>
															<option value="3">NCTE</option>
															<option value="4">MCI</option>
															<option value="5">DCI</option>
															<option value="6">PCI</option>
															<option value="7">Any Other</option>


														</select>
													</div></td>

											</tr>
											<tr>
												<td>2</td>
												<td>UG</td>

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
												<td><div class="col-sm-12">
														<select id="salutation" name="salutation"
															class="form-control" required>
															<option value="0">BOS/AC</option>
															<option value="1">Industry</option>
															<option value="2">AICTE</option>
															<option value="3">NCTE</option>
															<option value="4">MCI</option>
															<option value="5">DCI</option>
															<option value="6">PCI</option>
															<option value="7">Any Other</option>


														</select>
													</div></td>

											</tr>

											<tr>
												<td>3</td>
												<td>PG</td>

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
												<td><div class="col-sm-12">
														<select id="salutation" name="salutation"
															class="form-control" required>
															<option value="0">BOS/AC</option>
															<option value="1">Industry</option>
															<option value="2">AICTE</option>
															<option value="3">NCTE</option>
															<option value="4">MCI</option>
															<option value="5">DCI</option>
															<option value="6">PCI</option>
															<option value="7">Any Other</option>


														</select>
													</div></td>

											</tr>

											<tr>
												<td>4</td>
												<td>Diploma</td>

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
												<td><div class="col-sm-12">
														<select id="salutation" name="salutation"
															class="form-control" required>
															<option value="0">BOS/AC</option>
															<option value="1">Industry</option>
															<option value="2">AICTE</option>
															<option value="3">NCTE</option>
															<option value="4">MCI</option>
															<option value="5">DCI</option>
															<option value="6">PCI</option>
															<option value="7">Any Other</option>


														</select>
													</div></td>

											</tr>

											<tr>
												<td>5</td>
												<td>Ph.D.</td>

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
												<td><div class="col-sm-12">
														<select id="salutation" name="salutation"
															class="form-control" required>
															<option value="0">BOS/AC</option>
															<option value="1">Industry</option>
															<option value="2">AICTE</option>
															<option value="3">NCTE</option>
															<option value="4">MCI</option>
															<option value="5">DCI</option>
															<option value="6">PCI</option>
															<option value="7">Any Other</option>


														</select>
													</div></td>

											</tr>



											<tr>
												<td>6</td>
												<td>Post Doc.</td>

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
												<td><div class="col-sm-12">
														<select id="salutation" name="salutation"
															class="form-control" required>
															<option value="0">BOS/AC</option>
															<option value="1">Industry</option>
															<option value="2">AICTE</option>
															<option value="3">NCTE</option>
															<option value="4">MCI</option>
															<option value="5">DCI</option>
															<option value="6">PCI</option>
															<option value="7">Any Other</option>


														</select>
													</div></td>

											</tr>

											<tr>
												<td>7</td>
												<td>Any Other</td>

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
												<td><div class="col-sm-12">
														<select id="salutation" name="salutation"
															class="form-control" required>
															<option value="0">BOS/AC</option>
															<option value="1">Industry</option>
															<option value="2">AICTE</option>
															<option value="3">NCTE</option>
															<option value="4">MCI</option>
															<option value="5">DCI</option>
															<option value="6">PCI</option>
															<option value="7">Any Other</option>


														</select>
													</div></td>

											</tr>
											<tr>
												<td></td>
												<td><button type="submit" class="btn btn-primary">Add</button></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td><button type="submit" class="btn btn-primary">Add</button></td>
											</tr>
										</tbody> -->
									
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
							
								
						<div class="form-group">
						
							<label class="control-label col-sm-3" for="page_name">Vision
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="vision"
									name="code" value="${page.pageName}"
									required>
							<!-- </div> -->
	</div>
								
						<div class="form-group">
						
							<label class="control-label col-sm-3" for="page_name">Mission
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="mission"
									name="code" value="${page.pageName}"
									required>
							<!-- </div> -->
	</div>
			
						<div class="form-group">
						
							<label class="control-label col-sm-10" for="page_name">Program Outcome (as per NBA Manual)
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="po"
									name="code" value="${page.pageName}"
									required>
							<!-- </div> -->
	</div>
						
				
									<div class="form-group">
						<label class="control-label col-sm-2" for="page_name">UG/PG</label> <select
								id="ug" name="ug" class="form-control" onchange="showForm()" required>
								<option value="UG">UG</option>
								<option value="PG">PG</option>
								
							
							</select>
						</div>
						
						
						<div class="form-group">
						
							<label class="control-label col-sm-10" for="page_name">Program Specific Outcomes(Define By the Program )
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="pso"
									name="subTaut" value="${page.pageName}"
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
					

						<button type="submit" class="btn btn-primary" onclick="getData()">Submit</button>
					<!-- </form> -->
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
					<h4 class="modal-title">Program Details</h4>
				</div>
				<div class="modal-body">
					<%-- <form role="form"
						action="${pageContext.request.contextPath}/showModuleForm"
						method="get"> --%>
						<input type="hidden" class="form-control" id="pageId"
							name="pageId" >
							
							<div class="form-group">
						<label class="control-label col-sm-6" for="page_name">Program Type</label> <select
								id="type" name="ug" class="form-control" required>
								
								<option value="Certificate">Certificate</option>
								<option value="UG">UG</option>
								<option value="PG">PG</option>
								<option value="Diploma">Diploma</option>
								<option value="Ph.D.">Ph.D.</option>
								<option value="Post Doc.">Post Doc.</option>
								<option value="-">Any Other</option>
								
							
							</select>
						</div>
						
								
						<div class="form-group">
						
							<label class="control-label col-sm-6" for="page_name">Duration(in months)
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="duration"
									name="code" value="${page.pageName}"
									required>
							<!-- </div> -->
	</div>
								
						<div class="form-group">
						
							<label class="control-label col-sm-6" for="page_name">Name of Program
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="name"
									name="code" value="${page.pageName}"
									required>
							<!-- </div> -->
	</div>
			
						<div class="form-group">
						
							<label class="control-label col-sm-8" for="page_name">Date/Year of Introduction
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="date"
									name="code" value="${page.pageName}"
									required>
							<!-- </div> -->
	</div>
						
				
						
						
							<div class="form-group">
						<label class="control-label col-sm-6" for="page_name">Approved By</label> <select
								id="approve" name="approve" class="form-control" onchange="showForm()" required>
								
								<option value="BOS/AC">BOS/AC</option>
															<option value="Industry">Industry</option>
															<option value="AICTE">AICTE</option>
															<option value="NCTE">NCTE</option>
															<option value="MCI">MCI</option>
															<option value="DCI">DCI</option>
															<option value="PCI">PCI</option>
															<option value="-">Any Other</option>
						
								
							
							</select>
						</div>
						
					
					

						<button type="submit" class="btn btn-primary" onclick="getData1()">Submit</button>
					<!-- </form> -->
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
					<h4 class="modal-title">Program Details</h4>
				</div>
				<div class="modal-body">
					<%-- <form role="form"
						action="${pageContext.request.contextPath}/showModuleForm"
						method="get"> --%>
						<input type="hidden" class="form-control" id="pageId"
							name="pageId" >
							
						
									<div class="form-group">
						<label class="control-label col-sm-6" for="page_name">Program Outcome (as per NBA)</label> <select
								id="nba" name="ug" class="form-control" onchange="showForm()" required>
								<option value="Good">Good</option>
								<option value="Better">Better</option>
								<option value="Best">Best</option>
								
							
							</select>
						</div>
						
						
						
						
		
									<div class="form-group">
						<label class="control-label col-sm-6" for="page_name">PSO(Define By Program)</label> <select
								id="ps" name="ps" class="form-control"  required>
								<option value="Good">Good</option>
								<option value="Better">Better</option>
								<option value="Best">Best</option>
							
							</select>
						</div>
						
						
						<div class="form-group">
						<label class="control-label col-sm-6" for="page_name">Satisfying
							Value</label> <select id="val" name="val" class="form-control"
							onchange="showForm()" required>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="-">-</option>

						</select>
					</div>
					<div class="form-group">

						<label class="control-label col-sm-6" for="page_order">Mapping(Yes/No)
							 :<span class="text-danger">*</span>
						</label>
						<div class="col-sm-6">
							Yes <input type="radio" name="stu" id="stu" checked value="yes">
							No<input type="radio" name="stu" id="stu" value="1">
						</div>
					</div>
					
					
					

					

						<button type="submit" class="btn btn-primary" onclick="getData3()">Submit</button>
					<!-- </form> -->
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
	
	

	function getData3() {
		
		var i=0;
		var nba=document.getElementById("nba").value
		//alert(vision);
		
		var ps=document.getElementById("ps").value
		//alert(mission);
		var stu=document.getElementById("stu").value
		//alert(po);
		
		var val=document.getElementById("val").value
		
		
	
		var dataTable = $('#example10')
		.DataTable();
		
		dataTable.row
		.add(
				[
					i+1,
					nba,
					ps,
					stu,
					val
					
					
						 ])
		.draw();
		
		
		
	}

	function getData() {
	
		var i=0;
		var vision=document.getElementById("vision").value
		//alert(vision);
		
		var mission=document.getElementById("mission").value
		//alert(mission);
		var po=document.getElementById("po").value
		//alert(po);
		
		var ug=document.getElementById("ug").value
		//alert(ug);
		var pso=document.getElementById("pso").value
		//alert(pso);
		
		
		var pg=document.getElementById("pg").value
		//alert(pg);
		//alert("hii");
		var dataTable = $('#example8')
		.DataTable();
		
		dataTable.row
		.add(
				[
					i+1,
					vision,
					mission,
					po,
					ug,
					pso,
					pg
					
						 ])
		.draw();
		
		
		
	}
	
	
	function getData1() {
		
		var i=0;
		var type=document.getElementById("type").value
		//alert(vision);
		
		var duration=document.getElementById("duration").value
		//alert(mission);
		var name=document.getElementById("name").value
		//alert(po);
		
		var date=document.getElementById("date").value
		//alert(ug);
		var approve=document.getElementById("approve").value
		//alert(approve);
		
		
	
		var dataTable = $('#example9')
		.DataTable();
		
		dataTable.row
		.add(
				[
					i+1,
					type,
					duration,
					name,
					date,
					approve
					
						 ])
		.draw();
		
		
		
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

<!-- <select id="salutation" name="salutation"
															class="form-control" required>
															<option value="0">State</option>
															<option value="1">Other State</option>
															<option value="2">NRI</option>
															<option value="3">PIO</option>
															<option value="4">Foreign</option>
															<option value="5">PWD</option>
														</select> -->
