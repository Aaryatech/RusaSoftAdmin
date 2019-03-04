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
							<a href="#myModal1"	data-toggle="modal"><button type="submit"
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

														<h4>No. of Students Admitted:</h4>

<div class="form-group">
									<table class="table table-striped dt-responsive display" id="example-1">
										<thead>
											<tr>
												<th width="5%">Sr No</th>
												<th width="10%">Year</th>
												<th width="10%" >Program Type</th>
												<th  width="5%">Duration Months</th>
												<th  width="10%">Name of Program</th>
												 <th width="20%" style="text-align: center;" colspan="2">No.
													of Students Admitted</th> 
													
												<th width="20%">Date/Year of Introduction</th>
												<th width="20%" >Approved by &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												</th>
												
											</tr>
</thead>
<tbody>
											<tr>
												<td width="5%"></td>
												<td width="10%"></td>
												<td width="10%"></td>
												<td width="5%"></td>
												<td width="10%"></td>
												<td width="10%">Sanctioned Intake</td>
												<td width="10%">Admitted Student</td>
												<td width="20%"></td>
												<td width="20%"></td>
											</tr> 
										</tbody>
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
							
								<input type="hidden" class="form-control" id="index1"
							name="index1"  value="0">
								
							<div class="form-group">
						<label class="control-label col-sm-3" for="finantialYear">Academic
							Year</label> <select id="academicYear1" name="academicYear1"
							class="form-control" required>
							<option value="2018-2019">2018-2019</option>
							<option value="2017-2018">2017-2018</option>
							<option value="2016-2017">2016-2017</option>
	<option value="2015-2016">2015-2016</option>
						</select>
					</div>
						
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
							
							
								<input type="hidden" class="form-control" id="count"
							name="count"  value="0">
								
							<div class="form-group">
						<label class="control-label col-sm-3" for="finantialYear">Academic
							Year</label> <select id="academicYear2" name="academicYear2"
							class="form-control" required>
							<option value="2018-2019">2018-2019</option>
							<option value="2017-2018">2017-2018</option>
							<option value="2016-2017">2016-2017</option>
	<option value="2015-2016">2015-2016</option>
						</select>
					</div>
							<div class="form-group">
						<label class="control-label col-sm-6" for="page_name">Program Type</label> <select
								id="type" name="ug" class="form-control"  onchange="showForm1()" required>
								
								<option value="Certificate">Certificate</option>
								<option value="UG">UG</option>
								<option value="PG">PG</option>
								<option value="Diploma">Diploma</option>
								<option value="Ph.D.">Ph.D.</option>
								<option value="Post Doc.">Post Doc.</option>
								<option value="7">Any Other</option>
								
							
							</select>
						</div>
						
								<div class="form-group" id="xyz">
						
							<label class="control-label col-sm-6" for="page_name">Other Type
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="otherType"
									name="otherType" value="${page.pageName}" placeholder="Other Type"
									required>
							<!-- </div> -->
	</div>
						<div class="form-group">
						
							<label class="control-label col-sm-6" for="page_name">Duration(in months)
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="duration" placeholder="Duration(in months)"
									name="code" value="${page.pageName}"
									required>
							<!-- </div> -->
	</div>
								
						<div class="form-group">
						
							<label class="control-label col-sm-6" for="page_name">Name of Program
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="name" placeholder="Name of Program"
									name="code" value="${page.pageName}"
									required>
							<!-- </div> -->
	</div>
	
	<div class="form-group">
						
							<label class="control-label col-sm-6" for="page_name">Sanctioned Intake
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="sancIntake" placeholder="Sanctioned Intake"
									name="sancIntake" value="${page.pageName}"
									required>
							<!-- </div> -->
	</div>
	
	
	<div class="form-group">
						
							<label class="control-label col-sm-6" for="page_name">Admitted Student
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="admitStud" placeholder="Admitted Student"
									name="admitStud" value="${page.pageName}"
									required>
							<!-- </div> -->
	</div>
			
						<div class="form-group">
						
							<label class="control-label col-sm-8" for="page_name">Date/Year of Introduction
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="date" class="form-control" id="date" placeholder="Date/Year of Introduction"
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
															<option value="7">Any Other</option>
						
								
							
							</select>
						</div>
						<div class="form-group" id="abc">
						
							<label class="control-label col-sm-6" for="page_name">Other Approval
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="otherApproval"
									name="otherApproval" value="${page.pageName}" placeholder="Other Approval"
									required>
							<!-- </div> -->
	</div>
					
					

						<button type="submit" class="btn btn-primary" onclick="getData1()">Submit</button>
					<!-- </form> -->
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
function getData1() {
		
		var i = parseInt(document.getElementById("count").value);
		var year = document.getElementById("academicYear2").value;
		var type=document.getElementById("type").value
		var sancIntake=document.getElementById("sancIntake").value
		var admitStud=document.getElementById("admitStud").value
		//alert(vision);
		
		var duration=document.getElementById("duration").value
		//alert(mission);
		var name=document.getElementById("name").value
		//alert(po);
		
		var date=document.getElementById("date").value
		//alert(ug);
		var approve=document.getElementById("approve").value
		var otherApproval=document.getElementById("otherApproval").value
		var otherType=document.getElementById("otherApproval").value
		//alert(approve);
		
		var temp;
		if (type == 7) {

			temp=otherType;
			//alert(temp);
		} 
		else{
			temp=type;
		}
		
		var temp1;
		if (approve == 7) {

			temp1=otherApproval;
			//alert(temp);
		} 
		else{
			temp1=approve;
		}
	
		var dataTable = $('#example-1')
		.DataTable();
		
		dataTable.row
		.add(
				[
					i+1,
					year,
					temp,
					duration,
					name,
					sancIntake,
					admitStud,
					date,
					temp1
					
						 ])
		.draw();
		
		
		document.getElementById("count").value = i + 1;
	}

	
function showForm() {
	//document.getElementById("abc").style = "display:none"
		var qualType=document.getElementById("approve").value
		//alert("qualType::"+qualType);
		
		if (qualType == 7) {

			document.getElementById("abc").style = "visible"
			
				
		} 
		else{
			document.getElementById("abc").style = "display:none"
		}
	
	}
	
function showForm1() {
	//document.getElementById("abc").style = "display:none"
		var qualType=document.getElementById("type").value
		//alert("qualType::"+qualType);
		
		if (qualType == 7) {

			document.getElementById("xyz").style = "visible"
			
				
		} 
		else{
			document.getElementById("xyz").style = "display:none"
		}
	
	}
function hideText() {
	//alert("hii");
	document.getElementById("abc").style = "display:none"
		document.getElementById("xyz").style = "display:none"
		
	
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
<!-- Title of administrative training program organized for non-teaching staff -->
