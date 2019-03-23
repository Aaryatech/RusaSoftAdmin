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
								<%-- <a
									href="${pageContext.request.contextPath}/showIntellectualProperty"><button
										type="button" class="btn btn-info">Back</button></a> --%>

							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
								
								  <c:if test="${addAccess == 0}"> 
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertLinkageMaster"
										method="post" name="form_sample_2" id="form_sample_2"
										onsubmit="return confirm('Do you really want to submit the form?');">



										<div class="form-group">
											<label class="control-label col-sm-2" for="linkname_text">Linkage
												Name <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control" id="linkname_text"
													required name="linkname_text" autocomplete="off"
													placeholder="Linkage Name" value="${editInst.linknameText}">
											</div>
										</div>

										<div class="form-group">
											<label class="control-label col-sm-2" for="linkname_remarks">
												Linkage Remarks <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control"
													id="linkname_remarks" required name="linkname_remarks"
													autocomplete="off" placeholder="Linkage Remarks"
													value="${editInst.linknameRemarks}">
											</div>
										</div>

										<input type="hidden" id="linkage_id" name="linkage_id" value="${editInst.linknameId}" >
										
										<div class="form-group">
											<div class="col-sm-offset-2 col-sm-10">
												<input type="submit" class="btn btn-primary"
													onclick="submit_f(1)" value="Save"> 
												<button type="reset" class="btn btn-default">Reset</button>
											</div>

										</div>
									</form>
									
									</c:if>
									
									

									<div class="form-group">
						<form action="${pageContext.request.contextPath}/deleteLinkages/0"
							method="get" id="libListForm">
										<table class="table table-striped dt-responsive display"
											id="example-1">
											<thead>

												<tr>
												<th class="check" style="text-align: center; width: 5%;"><input
														type="checkbox" name="selAll" id="selAll"
														onClick="selectedInst(this)" /> Select All</th>
													<th>Sr No</th>
													<th>Linkage Name</th>
													<th>Linkage Remarks</th>
													<th>Action</th>

												</tr>


											</thead>
											
								
											<tbody>
											<c:forEach items="${colList}" var="colList"
														varStatus="count">
														<tr>
															<td><input type="checkbox" class="chk" name="linknameIds"
																id="linknameIds${count.index+1}" value="${colList.linknameId}" /></td>
															<td>${count.index+1}</td>
															<td>${colList.linknameText}</td>
															<td>${colList.linknameRemarks}</td>
															


															<td style="text-align: center;">
																  <c:if test="${editAccess == 0}">  <a
																href="#" onclick="showEditLinkage(${colList.linknameId})"><span
																	class="glyphicon glyphicon-edit"  title="Edit" data-original-title="Edit"
																	data-animate=" animated fadeIn " rel="tooltip"></span></a>
																</c:if>    <c:if test="${deleteAccess == 0}">
																&nbsp;&nbsp;&nbsp;&nbsp; <a
																href="${pageContext.request.contextPath}/deleteLinkages/${colList.linknameId}"
																onClick="return confirm('Are you sure want to delete this record');"
																rel="tooltip" data-color-class="danger" title="Delete" data-original-title="Delete"
																data-animate=" animated fadeIn " data-toggle="tooltip"
																data-original-title="Delete  record"><span
																	class="glyphicon glyphicon-remove"></span></a> 
																		</c:if>
															</td>
														</tr>
													</c:forEach>
											</tbody>
										</table>
  								<c:if test="${deleteAccess == 0}">
										<input type="submit" class="btn btn-primary" value="Delete"
												id="deleteId"
												onClick="var checkedVals = $('.chk:checkbox:checked').map(function() { return this.value;}).get();checkedVals=checkedVals.join(',');if(checkedVals==''){alert('No Rows Selected');return false;	}else{   return confirm('Are you sure want to delete record');}"
												style="align-content: center; width: 113px; margin-left: 40px;"></c:if>
											<input type="hidden" id="edit_linkage_id" name="edit_linkage_id"
												value="0">
</form>

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

	<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>




<script type="text/javascript">
		function selectedInst(source) {

			checkboxes = document.getElementsByName('linknameIds');

			for (var i = 0, n = checkboxes.length; i < n; i++) {
				checkboxes[i].checked = source.checked;

			}

		}
		function showEditLinkage(studId){
			alert("edit_linkage_id"+studId);
			document.getElementById("edit_linkage_id").value=studId;//create this 
			var form=document.getElementById("libListForm");
		    form.setAttribute("method", "post");

			form.action=("showEditLinkage");
			form.submit();
			
		}
		</script>

	<script type="text/javascript">
		$(function() {

			$('.datepicker').datepicker({
				autoclose : true,
				format : "dd-mm-yyyy",
				changeYear : true,
				changeMonth : true

			});
		});
	</script>

	<script type="text/javascript">
		$(function() {
			$('#submitForm').submit(
					function() {
						$("input[type='submit']", this).val("Please Wait...")
								.attr('disabled', 'disabled');

						return true;
					});
		});

		function trim(el) {
			el.value = el.value.replace(/(^\s*)|(\s*$)/gi, ""). // removes leading and trailing spaces
			replace(/[ ]{2,}/gi, " "). // replaces multiple spaces with one space 
			replace(/\n +/, "\n"); // Removes spaces after newlines
			return;
		}
	</script>
	<script type="text/javascript">
		function submit_f(view) {
			//alert(view);
			document.getElementById("is_view").value = view;

		}
	</script>



</body>
</html>