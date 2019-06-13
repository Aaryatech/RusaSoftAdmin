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
	<c:url value="/getQualityInitiativeById" var="getQualityInitiativeById"></c:url>
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
						
							 <c:if test="${sessionScope.successMsg!=null}">
           						 <div class="col-lg-12">
    						          <div class="alert alert-success alert-dismissible fade in">
            							    <button type="button" class="close" data-dismiss="alert" onclick="removSess()" aria-label="Close"><span aria-hidden="true">×</span></button>
             						   <strong>Success : </strong> ${sessionScope.successMsg}</div>
        	                     </div> 
        	                     <%session=request.getSession();
        	                    
        	                     session.removeAttribute("successMsg");
        	                     %>
            			</c:if>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertQualityInitiative"
										method="post" name="form_sample_2" id="form_sample_2">
										

										<div class="form-group">
											<label class="control-label col-sm-3"
												for="quality_initiative_name"> Quality Initiative
												Name<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control" maxlength="200"
													onchange="trim(this)" id="quality_initiative_name"
													name="quality_initiative_name" autocomplete="off"
													placeholder="Enter Quality Initiative Name">
													<span class="error_form text-danger" id="quality_initiative_name_field"
															style="display: none;">Please enter quality initiative name</span>

											</div>
										</div>

										<div class="form-group">
											<div class="col-sm-offset-3 col-sm-10">
												<button type="submit" id="sub1" class="btn btn-primary" onclick="submit_f(1)">
												<i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save</button>
												<!-- <input
													type="submit" class="btn btn-primary" onclick="submit_f(0)"
													value="Save &
																		Next"> -->
												<button type="reset" onclick="resetId()" id="sub2"
													class="btn btn-primary">
													<i class="${sessionScope.cancelIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Cancel</button>
											</div>
										</div>
										<input type="hidden" id="qual_inti_id" name="qual_inti_id"
											value="0"> <input type="hidden" id="is_view"
											name="is_view" value="0">
									</form>


									<div class="form-group">
										<form
											action="${pageContext.request.contextPath}/deleteQualiInit/0"
											method="get" id="insListForm">

											<table class="table table-striped dt-responsive display"
												id="example-1">
												<thead>

													<tr>
														<th class="check" style="text-align: center; width: 5%;"><input
															type="checkbox" name="selAll" id="selAll"
															onClick="selectedInst(this)" /> Select All</th>
														<th>Sr No</th>
														<th>Quality Initiative Name</th>
														<th>Action</th>

													</tr>

												</thead>
												<tbody>
													<c:forEach items="${qualInintList}" var="quality"
														varStatus="count">
														<tr>
															<td align="center"><input type="checkbox"
																class="chk" name="accOffIds"
																id="accOffIds${count.index+1}"
																value="${quality.qualityInitiativeId}" /></td>
															<td align="center">${count.index+1}</td>
															<td>${quality.qualityInitiativeName}</td>
															<td align="center"><c:if test="${editAccess==0}">
																	<a onclick="showEdit(${quality.qualityInitiativeId})"
																		href="#"><span class="glyphicon glyphicon-edit"
																		title="Edit" data-animate=" animated fadeIn "
																		rel="tooltip"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;
															</c:if> <c:if test="${deleteAccess==0}">
																	<a
																		href="${pageContext.request.contextPath}/deleteQualiInit/${quality.qualityInitiativeId}"
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
													id="deleteId" onClick="var checkedVals = $('.chk:checkbox:checked').map(function() { return this.value;}).get();checkedVals=checkedVals.join(',');if(checkedVals==''){alert('No Rows Selected');return false;	}else{   return confirm('Are you sure want to delete record');}"
													style="align-content: center; width: 113px; margin-left: 40px;"><i class="${sessionScope.deleteIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Delete</button>
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

												if (!$("#quality_initiative_name").val()) {
													isError = true;

													$("#quality_initiative_name").addClass(
															"has-error")
													$("#quality_initiative_name_field")
															.show()
												} else {
													$("#quality_initiative_name_field")
															.hide()
												}

												if (!isError) {
													var x = confirm("Do you really want to submit the form?");
													if (x == true) {
														document.getElementById("sub1").disabled = true;
														document.getElementById("sub2").disabled = true;
														return  true;
													}	
												}
												return false;
											});
						});
	</script>
	

	<!-- <script type="text/javascript">
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
 -->

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
		
		function showEdit(qualityInitiativeId){
			document.getElementById("qual_inti_id").value=qualityInitiativeId;//create this 
			
			$.getJSON('${getQualityInitiativeById}', {
				qualityInitiativeId : qualityInitiativeId,
				ajax : 'true',

			}, function(data) { 
				 document.getElementById("quality_initiative_name").value=data.qualityInitiativeName;
				 
			});
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


</body>
</html>