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
		<section id="main-content" class=" ">
			<section class="wrapper main-wrapper row" style="">

				<div class="col-xs-12">
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

				<div class="col-lg-12"></div>



				<div class="col-lg-12">
					<section class="box ">

						<header class="panel_header">
							<h2 class="title pull-left">${title}</h2>

							<div class="actions panel_actions pull-right">
								<c:if test="${addAccess==0}">
								<a
									href="${pageContext.request.contextPath}/showAddInternalQualityInitiative"><button
										type="button" class="btn btn-success"><i class="${sessionScope.addIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Add</button></a></c:if>
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/deleteInstiQuality/0"
										method="get"
										name="form_sample_2" id="form_sample_2">



										<div id="example-4_wrapper"
											class="dataTables_wrapper form-inline">

											<table id="example-4" class="display dataTable" role="grid"
												aria-describedby="example-4_info" style="width: 100%;">
												<thead>

													<tr>
													<th rowspan="2" class="check" style="text-align: center; width: 5%;"><input
														type="checkbox" name="selAll" id="selAll"
														onClick="selectedInst(this)" /> Select All</th>
														<th rowspan="2">Sr No</th>
														<th rowspan="2">Name of Quality Initiative</th>
														<th style="text-align: center;" colspan="2">Date of
															Conduction</th>
														<th rowspan="2">No. of Participants</th>
														<th rowspan="2">Action</th>

													</tr>
													<tr>

														<th>From Date</th>
														<th>To Date</th>

													</tr>

												</thead>
												<tbody>
												
												<c:forEach items="${instQualList}" var="insQuality"
													varStatus="count">
													<tr>
														 <td><input type="checkbox" class="chk"
															name="qualityId" id="qualityIds${count.index+1}"
															value="${insQuality.qualityId}" /></td> 
														<td align="center">${count.index+1}</td>
														<td>${insQuality.qualityInitiativeName}</td>
														<td align="center">${insQuality.qualityFromdt}</td>
														<td align="center">${insQuality.qualityTodt}</td>
														<td align="right">${insQuality.qualityPcount}</td>
														<td align="center"><c:if test="${editAccess==0}">
																<a onclick="showEdit(${insQuality.qualityId})"
																	href="#"><span class="glyphicon glyphicon-edit"  title="Edit"
																	data-animate=" animated fadeIn " rel="tooltip"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;
															</c:if><c:if test="${deleteAccess==0}">
																<a
																	href="${pageContext.request.contextPath}/deleteInstiQuality/${insQuality.qualityId}"
																	onClick="return confirm('Are you sure want to delete this record');"
																	rel="tooltip" data-color-class="danger"  title="Delete"
																	data-animate=" animated fadeIn " data-toggle="tooltip"
																	data-original-title="Delete  record"><span
																	class="glyphicon glyphicon-remove"></span></a>
															</c:if></td>
													</tr>
												</c:forEach>

												</tbody>
											</table>
											<c:if test="${deleteAccess==0}">

											<!-- <input type="submit" class="btn btn-primary" value="Delete"
												id="deleteId"
												onClick="var checkedVals = $('.chk:checkbox:checked').map(function() { return this.value;}).get();checkedVals=checkedVals.join(',');if(checkedVals==''){alert('No Rows Selected');return false;	}else{   return confirm('Are you sure want to delete record');}"
												style="align-content: center; width: 113px; margin-left: 40px;"> -->
												
												
										<div class="form-group">

											<div class="col-sm-5">
											<div class="col-sm-1">



															<button type="submit" title="delete checked records" id="deleteId" class="btn btn-primary" onClick="var checkedVals = $('.chk:checkbox:checked').map(function() { return this.value;}).get();checkedVals=checkedVals.join(',');if(checkedVals==''){alert('No Rows Selected');return false;	}else{   return confirm('Are you sure want to delete record');}"><i class="${sessionScope.deleteIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Delete</button>

												<!-- <button type="submit" title="Delete Multiple Records" class="btn1"
													id="deleteId"
													onClick="var checkedVals = $('.chk:checkbox:checked').map(function() { return this.value;}).get();checkedVals=checkedVals.join(',');if(checkedVals==''){alert('No Rows Selected');return false;	}else{   return confirm('Are you sure want to delete record');}"
													style="align-content: left;">
													<i class="fa fa-trash"
														style="font-size: 25px; background-color: black"></i>
												</button> --></div>
												<!-- <div class="col-sm-4">
													<h5 style="text-align: left;">Delete Records</h5>
												</div> -->
											</div>


											

										</div>
												
												
												
										</c:if>
		
										</div>
										<input type="hidden"
													id="qualityId" name="qualityId"
													value="0">
										
	
										<div class="clearfix"></div>

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




	<%-- <div class="modal fade col-xs-12" id="myModal1" tabindex="-1"
		role="dialog" aria-hidden="true">
		<div class="modal-dialog" style="width: 65%">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Internal Quality Initiative</h4>
				</div>
				<div class="modal-body">


					<div class="form-group">
						<label class="control-label col-sm-3" for="finantialYear">Academic
							Year</label> <select id="academicYear" name="academicYear"
							class="form-control" required>
							<option value="2018-2019">2018-2019</option>
							<option value="2017-2018">2017-2018</option>
							<option value="2016-2017">2016-2017</option>
							<option value="2016-2017">2015-2016</option>

						</select>
					</div>


					<div class="form-group">
						<label class="control-label col-sm-3" for="initiativeName">Name
							of Quality Initiative </label> <select id="initiativeName"
							name="initiativeName" onchange="showForm()" class="form-control"
							required>
							<option value="AQAR prepared or submitted">AQAR prepared
								or submitted</option>
							<option value="Academic – Administrative Audit Conducted">Academic
								– Administrative Audit Conducted</option>

							<option value="AQAR prepared or submitted">AQAR prepared
								or submitted</option>



							<option value="Institute Participated in NIRF">Institute
								Participated in NIRF</option>
							<option value="ISO Certification obtained">ISO
								Certification obtained</option>

							<option value="NBA Certification received">NBA
								Certification received</option>
							<option value="7">Any other quality based certification
							</option>

						</select>
					</div>

					<div class="form-group" id="abc">

						<label class="control-label col-sm-6" for="page_name">Any
							other quality based certification </label>
						<!-- <div class="col-sm-3"> -->
						<input type="text" class="form-control" id="otherQual" required
							name="otherQual" placeholder="" value="${page.pageName}">
						<!-- </div> -->
					</div>

					<div class="form-group">

						<label class="control-label col-sm-3" for="conductionDate">Date
							of Conduction </label> <input type="date" class="form-control"
							id="conductionDate" name="conductionDate"
							value="${page.pageName}" required>
					</div>

					<div class="form-group">

						<label class="control-label col-sm-3" for="fromDate">From
							Date </label> <input type="date" class="form-control" id="fromDate"
							name="fromDate" value="${page.pageName}" required> <label
							class="control-label col-sm-3" for="toDate">To Date </label> <input
							type="date" class="form-control" id="toDate" name="toDate"
							value="${page.pageName}" required>
					</div>



					<div class="form-group">

						<label class="control-label col-sm-3" for="participant">No.
							of Participants </label> <input type="text" class="form-control"
							id="participant" name="participant"
							placeholder="No. of Participants" value="${page.pageName}"
							required>
					</div>








					<!-- Link on Website for Activity Report -->

					<div class="form-group" style="text-align: center;">

						<button type="submit" class="btn btn-primary" onclick="getData()">Submit</button>
					</div>


				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
					<input type="hidden" id="index" name="index" value="0">
				</div>
			</div>
		</div>
	</div>


	<script type="text/javascript">
		function getData() {
			//alert("hii");
			var i = parseInt(document.getElementById("index").value);

			var academicYear = document.getElementById("academicYear").value;
			var initiativeName = document.getElementById("initiativeName").value;
			var conductionDate = document.getElementById("conductionDate").value;
			var fromDate = document.getElementById("fromDate").value;
			var toDate = document.getElementById("toDate").value;
			var participant = document.getElementById("participant").value;
			var otherQual = document.getElementById("otherQual").value;
			//alert("noStud"+noStud);
			var temp;
			if (initiativeName == 7) {

				temp = otherQual;
				//alert(temp);
			} else {
				temp = initiativeName;
			}

			var dataTable = $('#example-1').DataTable();

			dataTable.row.add(
					[ i + 1, academicYear, temp, conductionDate, fromDate,
							toDate, participant ]).draw();
			document.getElementById("index").value = i + 1;
		}

		function showForm() {
			//document.getElementById("abc").style = "display:none"
			var qualType = document.getElementById("initiativeName").value
			//alert("qualType::"+qualType);

			if (qualType == 7) {

				document.getElementById("abc").style = "visible"

			} else {
				document.getElementById("abc").style = "display:none"
			}

		}
		function hideText() {
			//alert("hii");
			document.getElementById("abc").style = "display:none"

		}
	</script>
 --%>
<script type="text/javascript">
function selectedInst(source) {

	checkboxes = document.getElementsByName('qualityId');

	for (var i = 0, n = checkboxes.length; i < n; i++) {
		checkboxes[i].checked = source.checked;

	}

} 

function showEdit(qualityId){
	document.getElementById("qualityId").value=qualityId;
	
	var form=document.getElementById("form_sample_2");
    form.setAttribute("method", "post");

	form.action=("showEditInstQuality");
	form.submit();
}

</script>







</body>
</html>