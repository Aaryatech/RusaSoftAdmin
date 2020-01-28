<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.UUID"%>
<%@ page import="java.security.MessageDigest"%>
<%@ page import="java.math.BigInteger"%>

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
				<%-- 
				<div class='col-xs-12'>
					<div class="page-title">

						<div class="pull-left">
							<!-- PAGE HEADING TAG - START -->
							<h1 class="title">${title}</h1>
							<!-- PAGE HEADING TAG - END -->
						</div>


					</div>
				</div> --%>
				<div class="clearfix"></div>
				<!-- MAIN CONTENT AREA STARTS -->



				<div class="col-lg-12">
					<section class="box ">
						<header class="panel_header">
							<h2 class="title pull-left">${title}</h2>
							<div class="actions panel_actions pull-right">

								<c:if test="${isAdd==1}">
									<%-- <a href="${pageContext.request.contextPath}/showAddProgram"><button
											type="button" class="btn btn-success">Add</button></a> --%>
											
				  <a title="Add"
											href="${pageContext.request.contextPath}/addStudPerfromancInFinalYear"><button
												type="button" class="btn btn-success"><i class="${sessionScope.addIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Add</button></a>
								</c:if>


							</div>
						</header>
						<div class="content-body">
							<div class="row">

								<div class="col-xs-12">

									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/deleteSeldata/0"
										method="get" name="form_sample_2" id="form_sample_2">
										<%
		UUID uuid = UUID.randomUUID();
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] messageDigest = md.digest(String.valueOf(uuid).getBytes());
		BigInteger number = new BigInteger(1, messageDigest);
		String hashtext = number.toString(16);
		session = request.getSession();
		session.setAttribute("generatedKey", hashtext);
	%>
	<input type="hidden" value="<%out.println(hashtext);%>"
				name="token" id="token">
									<div class="form-group">
										<table class="table table-striped dt-responsive display"
											id="example-1">
											<thead>
												<tr>
												<th class="check" style="text-align: center; width: 5%;"><input
																	type="checkbox" name="selAll" id="selAll"
																	onClick="selectedInst(this)" /> Select All</th>
													<th>Sr No</th>
													<th>Program Type</th>
													<th>No. of Student Appeared</th>
													<th>No. of Student Passed</th>
													<th>Student Passing %</th>
													<th>Action</th>
												</tr>
											</thead>

											<c:forEach items="${passedStudList}" var="list" varStatus="count">
												<tr>
												<td align="center"><input type="checkbox" class="chk"
																		name="studInfo" id="studInfos${count.index+1}"
																		value="${list.studPerformId}" /></td>
													<td style="text-align: center; ">${count.index+1}</td>
													<td>${list.progName} - ${list.progType}</td>
													<td style="text-align: right; ">${list.noStudAppear}</td>
													<td style="text-align: right; ">${list.noStudPass}</td>
													<td style="text-align: center; ">${list.passingPer}</td>
													

													<td style="text-align: center; ">
															
													 <c:if test="${isEdit==1}">
															<a
																href="${pageContext.request.contextPath}/editStudPerform/${list.studPerformId}"><span
																class="glyphicon glyphicon-edit" title="Edit"
																data-animate=" animated fadeIn " rel="tooltip"></span></a>&nbsp;&nbsp;
															</c:if> <c:if test="${isDelete==1}">
															<a
																href="${pageContext.request.contextPath}/deleteStudPerform/${list.studPerformId}/<%out.println(hashtext);%>"
																onClick="return confirm('Are you sure want to delete this record');"
																rel="tooltip" data-color-class="danger" title="Delete"
																data-animate=" animated fadeIn " data-toggle="tooltip"
																data-original-title="Delete  record"><span
																class="glyphicon glyphicon-remove"></span></a>

														</c:if></td>
												</tr>
											</c:forEach>

										</table>
										 <c:if test="${isDelete==1}">
														<button class="btn btn-primary" id="deleteId"
															onClick="var checkedVals = $('.chk:checkbox:checked').map(function() { return this.value;}).get();checkedVals=checkedVals.join(',');if(checkedVals==''){alert('No Rows Selected');return false;	}else{   return confirm('Are you sure want to delete record');}"
															style="align-content: center; width: 113px; margin-left: 40px;">
															<i class="${sessionScope.deleteIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Delete
														</button>
													</c:if>

									</div>
								</form>
								</div>
								<!-- <div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										<button type="submit" class="btn btn-primary">Submit</button>
										<button type="reset" class="btn btn-default">Reset</button>
									</div>
								</div> -->




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
		function selectedInst(source) {

			checkboxes = document.getElementsByName('studInfo');

			for (var i = 0, n = checkboxes.length; i < n; i++) {
				checkboxes[i].checked = source.checked;

			}

		}

	
	</script>
	<script type="text/javascript">
		function getData1() {

			var i = parseInt(document.getElementById("count").value);
			var year = document.getElementById("academicYear2").value;
			var type = document.getElementById("type").value
			var sancIntake = document.getElementById("sancIntake").value
			//var admitStud=document.getElementById("admitStud").value
			//alert(vision);

			var duration = document.getElementById("duration").value
			//alert(mission);
			var name = document.getElementById("name").value
			//alert(po);

			var date = document.getElementById("date").value
			//alert(ug);
			var approve = document.getElementById("approve").value
			var otherApproval = document.getElementById("otherApproval").value
			var otherType = document.getElementById("otherApproval").value
			//alert(approve);

			var temp;
			if (type == 7) {

				temp = otherType;
				//alert(temp);
			} else {
				temp = type;
			}
			var x = temp.append()
			var temp1;
			if (approve == 7) {

				temp1 = otherApproval;
				//alert(temp);
			} else {
				temp1 = approve;
			}

			var dataTable = $('#example-1').DataTable();

			dataTable.row.add(
					[ i + 1, year, temp(name), duration, sancIntake, date,
							temp1

					]).draw();

			document.getElementById("count").value = i + 1;
		}

		function showForm() {
			//document.getElementById("abc").style = "display:none"
			var qualType = document.getElementById("approve").value
			//alert("qualType::"+qualType);

			if (qualType == 7) {

				document.getElementById("abc").style = "visible"

			} else {
				document.getElementById("abc").style = "display:none"
			}

		}

		function showForm1() {
			//document.getElementById("abc").style = "display:none"
			var qualType = document.getElementById("type").value
			//alert("qualType::"+qualType);

			if (qualType == 7) {

				document.getElementById("xyz").style = "visible"

			} else {
				document.getElementById("xyz").style = "display:none"
			}

		}
		function hideText() {
			//alert("hii");
			document.getElementById("abc").style = "display:none"
			document.getElementById("xyz").style = "display:none"

		}
	</script>

	
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
