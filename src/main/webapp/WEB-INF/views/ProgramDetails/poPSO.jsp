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
<body class=" " >
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
						<!-- 	<a href="#myModal2"	data-toggle="modal"><button type="submit"
																class="btn btn-info">Add</button></a> -->
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
									<label class="control-label col-sm-2" for="status"> Program Name:</label>
										<label class="control-label col-sm-10" for="status"><strong>${progDetail.nameOfProgram} </strong></label>				
									</div>
										
									<div class="form-group">
									<label class="control-label col-sm-2" for="status"> Duration:</label>
										<label class="control-label col-sm-10" for="status"><strong> ${progDetail.monthDuration}</strong></label>				
									</div>
									<div class="form-group">
									<label class="control-label col-sm-2" for="status"> Sanctioned Intake:</label>
										<label class="control-label col-sm-10" for="status"><strong>${progDetail.sanctionalIntake}</strong> </label>				
								
									</div>
									</div>			
								<div class="col-xs-12">

									<div class="form-group">

									<table class="table table-striped dt-responsive display" id="example-1">
										<thead>
											<tr>
												<th>Sr No</th>
											
												<th>Program Outcomes (PO as per Accreditation Manual)</th>
												
												<th>Is Mapped(Yes/No)</th>
													<th>Satisfying Value</th>
												<th>Action</th>
																							</tr>

                                               </thead>
                                               
                                               
                                               
                                               <c:forEach items="${poList}" var="poList"
													varStatus="count">
													<tr>
										
														<td>${count.index+1}</td>
														<td>${poList.poText}</td>
														
													<c:choose>
   														 <c:when test="${poList.psoMapping=='1'}">
  															 <td>Yes</td>
   															 </c:when>    
   														 <c:otherwise>
    													   <td>No</td>
   																	 </c:otherwise>
														</c:choose>
														
														<c:choose>
   														 <c:when test="${poList.psoMapping=='4'}">
  															 <td>-</td>
   															 </c:when>    
   														 <c:otherwise>
    													 <td>${poList.psoMapSatisfyingValue}</td>
   																	 </c:otherwise>
														</c:choose>
														 
														
													
														
													<td> 
												     	<a href="${pageContext.request.contextPath}/showMapPOPSO/${poList.poId}" title="Add PSO"
											            rel="tooltip" data-color-class = "detail" data-animate=" animated fadeIn " data-toggle="tooltip" data-original-title="Block"><span
												         class="glyphicon glyphicon-list"></span></a>
											       </td>

<%-- 
														<td  align="center">
													    <c:if test="${editAccess == 0}"> 
							                       	  	<a
															href="#" onclick="showEditLibrarian(${institute.librarianId})"><span
																class="glyphicon glyphicon-edit"
																data-animate=" animated fadeIn " rel="tooltip"></span></a>
			                	                       </c:if>&nbsp;&nbsp;&nbsp;&nbsp;
														
												  <c:if test="${deleteAccess == 0}"> 
															<a
															href="${pageContext.request.contextPath}/showMapPOPSO/${institute.librarianId}" title="Add PSO"
															onClick="return confirm('Are you sure want to delete this record');"
															rel="tooltip" data-color-class="danger"
															data-animate=" animated fadeIn " data-toggle="tooltip"
															data-original-title="Delete  record"><span
																class="glyphicon glyphicon-remove"></span></a>
																</c:if>
																
														</td> --%>
													</tr>
												</c:forEach>
                                               
                                          <%--     <tbody>
<tr>
												<td>1</td>
												<td>xyz</td> <!-- format to display program name is program Type-Program Name -->
												<td>No</td>
											  
											<td>1</td>
												<td> <a
											href="${pageContext.request.contextPath}/showMapPOPSO" title="Add PSO"
											 rel="tooltip" data-color-class = "detail" data-animate=" animated fadeIn " data-toggle="tooltip" data-original-title="Block"><span
												class="glyphicon glyphicon-list"></span></a></td>
												
											</tr>

</tbody> --%>
														</table>
														</div>
														
														
									
							
</div>
								
							<!-- 
														<div class="form-group">
															<div class="col-sm-offset-2 col-sm-10">
																<button type="submit" class="btn btn-primary">Submit</button>
																<button type="reset" class="btn btn-default">Reset</button>
															</div>
														</div>
 -->



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
						<label class="control-label col-sm-6" for="page_name">Program Outcomes (PO as per Accreditation Manual)</label> <select
								id="nba" name="ug" class="form-control" onchange="showForm()" required>
								<option value="Good">Good</option>
								<option value="Better">Better</option>
								<option value="Best">Best</option>
								
							
							</select>
						</div>
						
						
						
						
		
									<div class="form-group">
						<label class="control-label col-sm-6" for="page_name">Program Specific Outcomes (PSOs  Define By the Program)</label> <select
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
							<label class="control-label col-sm-6" for="page_order">Mapping((Yes/No)
							 :<span class="text-danger">*</span>
						</label>
						<div class="col-sm-2">
							Yes <input type="radio" name="stu" id="stu" checked value="yes">
							No<input type="radio" name="stu" id="stu1" value="no">
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
		
		var i = parseInt(document.getElementById("index1").value);
		var year = document.getElementById("academicYear1").value;
		var nba=document.getElementById("nba").value
		//alert(vision);
		
		var ps=document.getElementById("ps").value
		//alert(mission);
		//var stu=document.getElementById("stu").value
		//alert(po);
		
		var rate_value;
		if (document.getElementById('stu').checked) {
		
			  rate_value = document.getElementById('stu').value;
				//alert("::"+rate_value);
			}
		else if(document.getElementById('stu1').checked){
			 rate_value = document.getElementById('stu1').value;
			// alert("::"+rate_value);
		}
		
		var val=document.getElementById("val").value
		
		
	
		var dataTable = $('#example-1')
		.DataTable();
		
		dataTable.row
		.add(
				[
					i+1,
					year,
					nba,
					ps,
					rate_value,
					val
					
					
						 ])
		.draw();
		
		document.getElementById("index1").value = i + 1;
		
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

