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

				<%-- <div class='col-xs-12'>
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

								<c:if test="${addAccess == 0}">
									<%-- 	<a
									href="${pageContext.request.contextPath}/showAddFunctionalMOUs"><button
										type="button" class="btn btn-success">Add</button></a>  --%>
									<a title="Add"
										href="${pageContext.request.contextPath}/showAddFunctionalMOUs"><button
											type="button" class="btn btn-success">
											<i class="${sessionScope.addIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Add
										</button></a>

								</c:if>


							</div>

						</header>
						<form action="${pageContext.request.contextPath}/deleteMous/0"
							method="get" id="libListForm">
							<div class="content-body">
								<div class="row">
									<c:if test="${sessionScope.successMsg!=null}">
										<div class="col-lg-12">
											<div class="alert alert-success alert-dismissible fade in">
												<button type="button" class="close" data-dismiss="alert"
													aria-label="Close">
													<span aria-hidden="true">×</span>
												</button>
												<strong>Success : </strong> ${sessionScope.successMsg}
											</div>
										</div>
									</c:if>

									<div class="col-xs-12">


										<div id="example-4_wrapper"
											class="dataTables_wrapper form-inline">

											<table id="example-4" class="display dataTable" role="grid"
												aria-describedby="example-4_info" style="width: 100%;">
												<thead>

													<tr>
														<th class="check" style="text-align: center; width: 5%;"
															rowspan="2"><input type="checkbox" name="selAll"
															id="selAll" onClick="selectedInst(this)" /> Select All</th>
														<th rowspan="2">Sr No</th>
														<th rowspan="2">Title of MoU</th>
														<th style="text-align: center;" colspan="2">Duration</th>
														<th rowspan="2">Institute of MoU</th>

														<th rowspan="2">No. of Participants</th>
														<th rowspan="2">Action</th>

													</tr>
													<tr>
														<th>From Date</th>
														<th>To Date</th>


													</tr>

												</thead>




												<tbody>
													<c:forEach items="${mouList}" var="mouList"
														varStatus="count">
														<tr>
															<td style="text-align: center;"><input
																type="checkbox" class="chk" name="mouIds"
																id="mouIds${count.index+1}" value="${mouList.mouId}" /></td>
															<td style="text-align: center;">${count.index+1}</td>
															<td style="text-align: left;">${mouList.mouTitle}</td>
															<td style="text-align: center;">${mouList.mouFromdt}</td>
															<td style="text-align: center;">${mouList.mouTodt}</td>
															<td>${mouList.mouInstitute}</td>
															<td style="text-align: right;">${mouList.mouBeneficiaryNos}</td>



															<td style="text-align: center;"><c:if
																	test="${editAccess == 0}">
																	<a href="#" onclick="showEditMou(${mouList.mouId})"><span
																		class="glyphicon glyphicon-edit" title="Edit"
																		data-original-title="Edit"
																		data-animate=" animated fadeIn " rel="tooltip"></span></a>
																</c:if> <c:if test="${deleteAccess == 0}">
																&nbsp;&nbsp;&nbsp;&nbsp; <a
																		href="${pageContext.request.contextPath}/deleteMous/${mouList.mouId}"
																		onClick="return confirm('Are you sure want to delete this record');"
																		rel="tooltip" data-color-class="danger" title="Delete"
																		data-original-title="Delete"
																		data-animate=" animated fadeIn " data-toggle="tooltip"
																		data-original-title="Delete  record"><span
																		class="glyphicon glyphicon-remove"></span></a>
																</c:if></td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
										<div class="col-lg-1">
											<c:if test="${deleteAccess==0}">
												<button class="btn btn-primary" id="deleteId"
													onClick="var checkedVals = $('.chk:checkbox:checked').map(function() { return this.value;}).get();checkedVals=checkedVals.join(',');if(checkedVals==''){alert('No Rows Selected');return false;	}else{   return confirm('Are you sure want to delete record');}"
													style="align-content: center; width: 113px; margin-left: 40px;">
													<i class="${sessionScope.deleteIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Delete
												</button>
											</c:if>
											<input type="hidden" id="edit_mou_id" name="edit_mou_id"
												value="0">


										</div>


									</div>
								</div>
							</div>
						</form>
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

	<script>
		function clearSessionAttribute() {

			$.getJSON('${clearSessionAttribute}', {

				ajax : 'true',

			}, function(data) {

			});

		}

		function selectedInst(source) {

			checkboxes = document.getElementsByName('mouIds');

			for (var i = 0, n = checkboxes.length; i < n; i++) {
				checkboxes[i].checked = source.checked;

			}

		}
		function showEditMou(studId){
			document.getElementById("edit_mou_id").value=studId;//create this 
			var form=document.getElementById("libListForm");
		    form.setAttribute("method", "post");

			form.action=("showEditMou");
			form.submit();
			
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




	<script type="text/javascript">
		function showExtraField() {
			//alert("hii");
			//document.getElementById("abc").style = "display:none"
			var qualType = document.getElementById("MOU_agency").value
			//alert("qualType::"+qualType);

			if (qualType == 7) {

				document.getElementById("abc").style = "visible"
					document.getElementById("otherCourse").setAttribute("required","true");

			} else {
				document.getElementById("abc").style = "display:none"
					document.getElementById("other_benif").removeAttribute("required");

			}

		}

	</script>
	<script type="text/javascript">
	function hideText() {
		//alert("hii");
		var qualType = document.getElementById("MOU_agency").value
		// alert("x " +qualType);
			if(qualType == 7){
				//alert("In If " +x);
				document.getElementById("abc").style = "visible";
			}else{ 
		document.getElementById("abc").style = "display:none"
			}
		
	}
	</script>

</body>
</html>
