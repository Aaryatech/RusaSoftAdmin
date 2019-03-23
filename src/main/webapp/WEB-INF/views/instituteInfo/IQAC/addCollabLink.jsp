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
<body class=" ">
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
								<a
									href="${pageContext.request.contextPath}/showCollaborationLinkages"><button
										type="button" class="btn btn-info">Back</button></a>
								<%-- <a
									href="${pageContext.request.contextPath}/publicationList"><button
										type="button" class="btn btn-info">Back</button></a> <!-- <a
									class="box_toggle fa fa-chevron-down"></a> --> --%>
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertColLinkages"
										method="post" name="form_sample_2" id="form_sample_2"
										onsubmit="return checkBeforeSubmit()">

										<%-- <ul class="nav nav-tabs">
											<li class="active"><a href="#home" data-toggle="tab">
													<i class="fa fa-home"></i> ${title}
											</a></li>

										</ul> --%>

										<!-- <div class="tab-content">
											<div class="tab-pane fade in active" id="home">
 -->
												<div class="form-group">
													<label class="control-label col-sm-2" for="colName">Name
														of Collaboration / Linkage<span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														
														
														
														<select id="colName" name="colName" class="form-control" required>
															
																		<c:forEach items="${colList}" var="colList">
																		<c:choose>
																			<c:when test="${colList.linknameId==editInst.linknameId}">
																			<option selected value="${colList.linknameId}">${colList.linknameText}</option>

																			</c:when>
																			<c:otherwise>
																			<option  value="${colList.linknameId}">${colList.linknameText}</option>

																			</c:otherwise>

																		</c:choose>

																	</c:forEach>
																</select>
																
													</div>
												</div>
												<div class="form-group">

													<label class="control-label col-sm-2" for="agency">Collaboration
														Linkage with Agency<span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="col_agency"
															name="col_agency" autocomplete="off"
															onchange="return trim(this)"
															placeholder="Collaboration Linkage with Agency"
															value="${editInst.linkAgency}" required>
													</div>
												</div>




												<div class="form-group">

													<label class="control-label col-sm-2" for="linkageNature">Nature
														of Linkage Collaboration <span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="linkageNature"
															name="linkageNature" autocomplete="off"
															onchange="return trim(this)"
															placeholder="Nature of Linkage Collaboration"
															value="${editInst.linkNature}" required>
													</div>
												</div>

												

												<div class="form-group">

													<label class="control-label col-sm-2" for="beneficiaryMOU">Beneficiary
														of MoU<span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">

														<select id="beneficiaryMOU" name="beneficiaryMOU"
															class="form-control" required>
															<!-- <option value="-1">Select</option>
															<option value="Students">Students</option>
															<option value="Staff">Staff</option>
															<option value="Students And Staff">Students And Staff</option> -->



																<c:choose>
															<c:when test="${'Students'== editInst.linkBeneficiary}">
}">
														
															<option selected value="Students">Students</option>
															<option value="Staff">Staff</option>
															<option value="Students And Staff">Students And Staff</option>
																			</c:when>
																			<c:when test="${'Staff'==editInst.linkBeneficiary}">
																	        
															<option value="Students">Students</option>
															<option selected value="Staff">Staff</option>
															<option value="Students And Staff">Students And Staff</option>
																			</c:when>
																			
															<c:when test="${'Students And Staff'==editInst.linkBeneficiary}">
}">
																	
															<option value="Students">Students</option>
															<option value="Staff">Staff</option>
															<option selected value="Staff">Students And Staff</option>

																			</c:when>
																			
																			
																	<c:otherwise>
																		
															<option value="Students">Students</option>
															<option value="Staff">Staff</option>
															<option value="Students And Staff">Students And Staff</option>

																			</c:otherwise>

																		</c:choose>

														</select>

													</div>
												</div>

												<div class="form-group">

													<label class="control-label col-sm-2"
														for="totalParticipants">No. of Participants /
														Beneficiary<span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="number" min="0" class="form-control" autocomplete="off"
															id="totalParticipants" name="totalParticipants"
															placeholder="No. of Participants / Beneficiary"
															value="${editInst.linkBeneficiaryNos}" required>
													</div>
												</div>



      										 <input type="hidden" id="link_id" name="link_id" value="${editInst.linkId}">
                                             	<input type="hidden" id="is_view" name="is_view" value="0">
												<div class="form-group">
													<div class="col-sm-offset-2 col-sm-10">
														<input type="submit"  id="sub1" class="btn btn-primary"
															onclick="submit_f(1)" value="Save"> <input
															type="submit" id="sub2" class="btn btn-primary"
															onclick="submit_f(0)"
															value="Save &
																		Next">
														<button type="reset" class="btn btn-default">Reset</button>
													</div>
												</div>
												<div class="clearfix"></div>
											<!-- </div>
										</div> -->
									</form>
									<p class="desc text-danger fontsize11">Notice : * Fields
										are Mandatory.</p>
									
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
function submit_f(view){
		document.getElementById("is_view").value=view;//create this 
		/* var form=document.getElementById("form_sample_2");
	    form.setAttribute("method", "post");

		form.action=("insertLibrarian");
		var x =confirm();
		if(x==true)
		form.submit(); */
		
	}
	
	
var wasSubmitted = false;    
function checkBeforeSubmit(){
  if(!wasSubmitted) {
	  var x=confirm("Do you really want to submit the form?");
	  if(x==true){
    wasSubmitted = true;
	  document.getElementById("sub1").disabled=true;
	  document.getElementById("sub2").disabled=true;

    return wasSubmitted;
	  }
  }
  return false;
}    
	</script>

</body>
</html>