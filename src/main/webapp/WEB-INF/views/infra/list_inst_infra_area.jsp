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
<body class=" " onload="showAreaName()">
	<c:url value="/getInfraAreaNameListByInfraAreaTypeId"
		var="getInfraAreaNameListByInfraAreaTypeId"></c:url>
	<c:url value="/findByDelStatusAndIsActiveAndInstIdAndInfraAreaId"
		var="findByDelStatusAndIsActiveAndInstIdAndInfraAreaId"></c:url>


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
								<c:if test="${addAccess == 0}"> 
								<a href="${pageContext.request.contextPath}/showAddInstInfraAreawise"><button
										type="button" class="btn btn-success"><i class="${sessionScope.addIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Add</button></a></c:if>

							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<%-- <form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertInstInfraArea"
										method="post" name="form_sample_2" id="form_sample_2">


										<div class="form-group">
											<label class="control-label col-sm-2" for="page_order">Area
												Type <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<select id="area_type" name="area_type" class="form-control"
													onchange="showAreaName()">

													<c:forEach items="${areaTypeList}" var="areaTypes">
														<c:choose>
															<c:when
																test="${areaTypes.designationId == editFaculty.currentDesignationId}">
																<option value="${makeList.designationId}"
																	selected="selected">${makeList.designationName}</option>
															</c:when>
															<c:otherwise>
														<option value="${areaTypes.infraAreaTypeId}">${areaTypes.areaTypeName}</option>
															</c:otherwise>
														</c:choose>
													</c:forEach>

												</select> <span class="error_form text-danger" id="area_type_field"
													style="display: none;">Please select area type</span>
											</div>
										</div>

										<div class="form-group">
											<label class="control-label col-sm-2" for="page_order">Area
												Name <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<select id="area_name" name="area_name" class="form-control"
													onchange="showDiv()">

													<c:forEach items="${areaTypeList}" var="areaTypes">
														<c:choose>
															<c:when
																test="${areaTypes.designationId == editFaculty.currentDesignationId}">
																<option value="${makeList.designationId}"
																	selected="selected">${makeList.designationName}</option>
															</c:when>
															<c:otherwise>
																<option value="${areaTypes.infraAreaTypeId}">${areaTypes.areaTypeName}</option>
															</c:otherwise>
														</c:choose>
													</c:forEach>

												</select> <span class="error_form text-danger" id="area_name_field"
													style="display: none;">Please select Area Type</span>
											</div>
										</div>

										<div class="form-group" style="display: none"
											id="area_name_div">
											<label class="control-label col-sm-2" for="other_area">Other
												Area Name <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control" maxlength="200"
													onchange="trim(this)" id="other_area" name="other_area"
													autocomplete="off" placeholder="Enter Area Location">
												<span class="error_form text-danger" id="other_area_field"
													style="display: none;">Please enter other area name</span>

											</div>
										</div>


										<div class="form-group">
											<label class="control-label col-sm-2" for="loc_of_area">Location
												Of Area <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control" maxlength="200"
													onchange="trim(this)" id="loc_of_area" name="loc_of_area"
													autocomplete="off" placeholder="Enter Area Location">
												<span class="error_form text-danger" id="loc_of_area_field"
													style="display: none;">Please enter location of area</span>

											</div>
										</div>


										<div class="form-group">
											<label class="control-label col-sm-2"
												for="quality_initiative_name">Area in Sq. M. <span
												class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control" maxlength="200"
													onchange="trim(this)" id="area_in_sqm" name="area_in_sqm"
													autocomplete="off" placeholder="Enter Area In Square Meter">
												<span class="error_form text-danger" id="area_in_sqm_field"
													style="display: none;">Please enter area in square
													meter</span>

											</div>
										</div>

										<div class="form-group">
											<div class="col-sm-offset-2 col-sm-10">
												<button type="submit" id="sub1" class="btn btn-primary"
													onclick="submit_f(1)">
													<i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save
												</button>
												<!-- <input
													type="submit" class="btn btn-primary" onclick="submit_f(0)"
													value="Save &
																		Next"> -->

											</div>
										</div>
										<input type="hidden" id="instInfraAreaId"
											name="instInfraAreaId" value="0"> <input
											type="hidden" id="is_view" name="is_view" value="0">
									</form>
 --%>

									<div class="form-group">
										<form
											action="${pageContext.request.contextPath}/deleteInfraArea/0"
											method="get" id="insListForm">
											<input type="hidden" value="0" id="instInfraAreaId" name="instInfraAreaId">

											<table class="table table-striped dt-responsive display"
												id="example-1">
												<thead>

													<tr>
														<th class="check" style="text-align: center; width: 5%;"><input
															type="checkbox" name="selAll" id="selAll"
															onClick="selectedInst(this)" /> Select All</th>
														<th>Sr No</th>
														<th>Area Type</th>
														<th>Area Name</th>
														<th>Name of Room/Room No.</th>
														<th>Area In Sq. M.</th>
														<th>Action</th>

													</tr>

												</thead>
												<tbody>
													<c:forEach items="${instInfraAreaList}" var="instInfraArea"
														varStatus="count">
														<tr>
															<td align="center"><input type="checkbox"
																class="chk" name="accOffIds"
																id="accOffIds${count.index+1}"
																value="${instInfraArea.instInfraAreaId}" /></td>
															<td align="center">${count.index+1}</td>
															<td>${instInfraArea.areaTypeName}</td>
															<td>${instInfraArea.areaName}</td>

															<td>${instInfraArea.areaLoc}</td>

															<td>${instInfraArea.areaSqMtr}</td>

															<td align="center"><c:if test="${editAccess==0}">
																	<a onclick="showEdit(${instInfraArea.instInfraAreaId})"
																		href="#"><span class="glyphicon glyphicon-edit"
																		title="Edit" data-animate=" animated fadeIn "
																		rel="tooltip"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;
															</c:if> <c:if test="${deleteAccess==0}">
																	<a
																		href="${pageContext.request.contextPath}/deleteInfraArea/${instInfraArea.instInfraAreaId}"
																		onClick="return confirm('Are you sure want to delete this record');"
																		rel="tooltip" data-color-class="danger" title="Delete"
																		data-animate=" animated fadeIn " data-toggle="tooltip"
																		data-original-title="Delete  record"><span
																		class="glyphicon glyphicon-remove"></span></a>
																</c:if></td>


														</tr>

													</c:forEach>


												</tbody>
											</table>

											<c:if test="${deleteAccess==0}">
												<button type="submit" class="btn btn-primary" value="Delete"
													id="deleteId"
													onClick="var checkedVals = $('.chk:checkbox:checked').map(function() { return this.value;}).get();checkedVals=checkedVals.join(',');if(checkedVals==''){alert('No Rows Selected');return false;	}else{   return confirm('Are you sure want to delete record');}"
													style="align-content: center; width: 113px; margin-left: 40px;">
													<i class="${sessionScope.deleteIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Delete
												</button>
											</c:if>
											
										</form>
									</div>
									<p class="desc text-danger fontsize11">Notice : * Fields
										are mandatory.</p>

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
	
	function showEdit(instInfraAreaId){
		
		document.getElementById("instInfraAreaId").value=instInfraAreaId;//create this 
		var form=document.getElementById("insListForm");
	    form.setAttribute("method", "post");

		form.action=("showEditInstInfraArea");
		form.submit();
		
	}
	
	</script>


	<script>
		function validateEmail(email) {
			var eml = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
			if (eml.test($.trim(email)) == false) {
				return false;
			}
			return true;
		}
		function validateNo(mobile) {
			var mob = /^[1-9]{1}[0-9]{0,9}$/;
			if (mob.test($.trim(mobile)) == false) {
				return false;
			}
			return true;
		}
		$(document)
				.ready(
						function($) {

							$("#form_sample_2")
									.submit(
											function(e) {
												var isError = false;
												var errMsg = "";

												if (!$("#area_type").val()) {
													isError = true;
													$("#area_type").addClass(
															"has-error")
													$("#area_type_field")
															.show()
												} else {
													$("#area_type_field")
															.hide()
												}
												
												if (!$("#area_name").val()) {
													isError = true;
													$("#area_name").addClass(
															"has-error")
													$("#area_name_field")
															.show()
												} else {
													$("#area_name_field")
															.hide()
												}
												
												
												
												if (!$("#loc_of_area").val()) {
													isError = true;
													$("#loc_of_area").addClass(
															"has-error")
													$("#loc_of_area_field")
															.show()
												} else {
													$("#loc_of_area_field")
															.hide()
												}
												
												
												
												if (!$("#area_in_sqm").val()) {
													isError = true;
													$("#area_in_sqm").addClass(
															"has-error")
													$("#area_in_sqm_field")
															.show()
												} else {
													$("#area_in_sqm_field")
															.hide()
												}
												
									            var areaId = document.getElementById("area_name").value;

												if(areaId==0){
													
													if (!$("#other_area").val()) {
														isError = true;
														$("#other_area").addClass(
																"has-error")
														$("#other_area_field")
																.show()
													} else {
														$("#other_area_field")
																.hide()
													}
												}

												if (!isError) {
													var x = confirm("Do you really want to submit the form?");
													if (x == true) {
														document.getElementById("sub1").disabled = true;
														return  true;
													}	
												}
												return false;
											});
						});
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

		function trim(el) {
			el.value = el.value.replace(/(^\s*)|(\s*$)/gi, ""). // removes leading and trailing spaces
			replace(/[ ]{2,}/gi, " "). // replaces multiple spaces with one space 
			replace(/\n +/, "\n"); // Removes spaces after newlines
			return;
		}
		function submit_f(view) {
			document.getElementById("is_view").value = view;
		}
		
		function selectedInst(source) {

			checkboxes = document.getElementsByName('accOffIds');

			for (var i = 0, n = checkboxes.length; i < n; i++) {
				checkboxes[i].checked = source.checked;

			}

		}
		
		function resetId(){
		 document.getElementById("qual_inti_id").value=0;
		}
		
	</script>

	<script type="text/javascript">
  var wasSubmitted = false;    
    function checkBeforeSubmit(){
      if(!wasSubmitted) {
    	  var x=confirm("Do you really want to submit the form?");
    	  if(x==true){
        	wasSubmitted = true;
    	  document.getElementById("sub1").disabled=true;

        return wasSubmitted;
    	  }
      }
      return false;
    }    
</script>


	<script type="text/javascript">
        function showAreaName(){
            //alert("Item Id ")
            var infraAreaTypeId = document.getElementById("area_type").value;
            var valid = true;

            if (valid == true) {

                $.getJSON('${getInfraAreaNameListByInfraAreaTypeId}', {
                	infraAreaTypeId : infraAreaTypeId,
                    ajax : 'true',
                },

                function(data) {
                    var html;
                    var len = data.length;
                    for (var i = 0; i < len; i++) {

                        html += '<option value="' + data[i].infraAreaId + '">'
                                + data[i].infraAreaName + '</option>';

                    }
                    
                    var x=0;
                    var y="Any Other";
                    html += '<option value="'+x+'">'
                    +y+'</option>';
                    html += '</option>';

                    $('#area_name').html(html);
                    $("#area_name").trigger("chosen:updated");
                  
                });
            }//end of if

        }
        
        function showDiv(){
            var areaId = document.getElementById("area_name").value;
			if(areaId==0){
				$("#area_name_div")
				.show()
				document.getElementById("instInfraAreaId").value="0";
				document.getElementById("area_in_sqm").value="";
            	document.getElementById("loc_of_area").value="";
			}else{
				$("#area_name_div")
				.hide()
                $.getJSON('${findByDelStatusAndIsActiveAndInstIdAndInfraAreaId}', {
                	areaId : areaId,
                    ajax : 'true',
                },

                function(data) {
                	
                	if(!data==""){
                	document.getElementById("area_in_sqm").value=data.areaSqMtr;
                	document.getElementById("loc_of_area").value=data.areaLoc;
                	document.getElementById("instInfraAreaId").value=data.instInfraAreaId;
                	}else{
                		document.getElementById("area_in_sqm").value="";
                    	document.getElementById("loc_of_area").value="";
                    	document.getElementById("instInfraAreaId").value="0";
                	}
                	
                });
				//
			}
        }
    </script>



</body>
</html>