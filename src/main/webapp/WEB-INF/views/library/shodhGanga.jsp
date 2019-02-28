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
								<a href="#myModal"	data-toggle="modal"><button type="submit"
																class="btn btn-info">Add</button></a> <a
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


<!-- 
										<h5 class="title pull-left">
											<strong>Facilities</strong>
										</h5> -->
										<div class="col-xs-12"></div>

										

										<table class="table table-striped dt-responsive display" id="example1">
											<thead>
												<tr>
													<th width="5%">Sr No</th>
												
														<th width="20%">Yes/No</th>
													
													<th width="30%">Membership Details</th>
												</tr>
											</thead>


<!-- 
											<tbody>

												<tr>
													<td>1</td>
													<td>Classrooms</td>

													<td><input type="text" class="form-control"
														id="curExp" name="curExp" value="" required></td>
													<td><input type="text" class="form-control"
														id="curExp" name="curExp" value="" required></td>
												</tr>
												<tr>
													<td>2</td>
													<td>Seminar Hall</td>

													<td><input type="text" class="form-control"
														id="curExp" name="curExp" value="" required></td>
													<td><input type="text" class="form-control"
														id="curExp" name="curExp" value="" required></td>
												</tr>

												<tr>
													<td>3</td>
													<td>ICT Rooms</td>

													<td><input type="text" class="form-control"
														id="curExp" name="curExp" value="" required></td>
													<td><input type="text" class="form-control"
														id="curExp" name="curExp" value="" required></td>
												</tr>

												<tr>
													<td>4</td>
													<td>Smartboard</td>

													<td><input type="text" class="form-control"
														id="curExp" name="curExp" value="" required></td>
													<td><input type="text" class="form-control"
														id="curExp" name="curExp" value="" required></td>
												</tr>

												<tr>
													<td>5</td>
													<td>Computers</td>

													<td><input type="text" class="form-control"
														id="curExp" name="curExp" value="" required></td>
													<td><input type="text" class="form-control"
														id="curExp" name="curExp" value="" required></td>
												</tr>

												<tr>
													<td>6</td>
													<td>Mooc Courses</td>

													<td><input type="text" class="form-control"
														id="curExp" name="curExp" value="" required></td>
													<td><input type="text" class="form-control"
														id="curExp" name="curExp" value="" required></td>
												</tr>

												<tr>
													<td>7</td>
													<td>Virtual Lab</td>

													<td><input type="text" class="form-control"
														id="curExp" name="curExp" value="" required></td>
													<td><input type="text" class="form-control"
														id="curExp" name="curExp" value="" required></td>
												</tr>
												<tr>
													<td>8</td>
													<td>Nptel</td>

													<td><input type="text" class="form-control"
														id="curExp" name="curExp" value="" required></td>
													<td><input type="text" class="form-control"
														id="curExp" name="curExp" value="" required></td>
												</tr>



											</tbody> -->
										</table>
									</div>
									<div class="form-group">
															<div class="col-sm-offset-2 col-sm-10">
																<button type="submit" class="btn btn-primary">Submit</button>
																<button type="reset" class="btn btn-default">Reset</button>
															</div>
														</div>
									

						
					</form>
				</div>
			</div>
			</section>
		</div>
		</section>
		</section>
	</div>
	
	
	<div aria-hidden="true" role="dialog" tabindex="-1" id="myModal"
		class="modal fade" style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button aria-hidden="true" data-dismiss="modal" class="close"
						type="button">×</button>
					<h4 class="modal-title">e Shodh Ganga</h4>
				</div>
				<div class="modal-body">
					<%-- <form role="form"
						action="${pageContext.request.contextPath}/showModuleForm"
						method="get"> --%>
						<input type="hidden" class="form-control" id="pageId"
							name="pageId" >
							
								<input type="hidden" id="index" name="index" value="0">
								
							<div class="form-group">
							<label class="control-label col-sm-6" for="page_order">Is e Shodh Ganga
							 :<span class="text-danger">*</span>
						</label>
						<div class="col-sm-2">
							 <input type="radio" name="stu" id="stu" checked value="1" onclick="showForm(this.value)">Yes
							<input type="radio" name="stu" id="stu1" value="0" onclick="showForm(this.value)">No
						</div>
					</div>
						
						
						<div class="form-group" id="abc">
						
							<label class="control-label col-sm-6" for="page_name">Membership Details
							</label>
							<!-- <div class="col-sm-3"> -->
								<input type="text" class="form-control" id="mem" placeholder="Membership Details"
									name="qualName" placeholder="" value="${page.pageName}"
									>
							<!-- </div> -->
	</div>

	
	
	
						<button type="submit" class="btn btn-primary" onclick="getData()">Submit</button>
				<!-- 	</form> -->
				
			</div>
		</div>
	</div>
	
	</div>
	


	
	<script type="text/javascript">
	function getData() {
	//alert("hii");
		var i = parseInt(document.getElementById("index").value);

		
		var mem=document.getElementById("mem").value
		
		var rate_value;
		if (document.getElementById('stu').checked) {
		
			  rate_value = document.getElementById('stu').value;
				//alert("::"+rate_value);
			}
		else if(document.getElementById('stu1').checked){
			 rate_value = document.getElementById('stu1').value;
			 //alert("::"+rate_value);
		}
		
		//var stu = document.getElementById("stu").value
		var temp;
		var temp1;
		
		if(rate_value == 1){
			//alert("in yes");
			//alert(mem);
			temp="Yes";
			temp1=mem;
		}
		else{
			//alert("in no");
			//alert(stu);
			temp="No";
			temp1="-";
		}
		var dataTable = $('#example1')
		.DataTable();
		
		dataTable.row
		.add(
				[
					i+1,
					temp,
					temp1
						 ])
		.draw();
		
		document.getElementById("index").value = i + 1;
		document.getElementById("qualName").value='-';
	}
	
	function showForm(qualType) {
		//document.getElementById("abc").style = "display:none"
			//var qualType=document.getElementById("functionalMOU").value
			//alert("qualType::"+qualType);
			
			if (qualType == 1) {

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
