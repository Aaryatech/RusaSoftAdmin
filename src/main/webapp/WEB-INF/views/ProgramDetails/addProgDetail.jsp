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
	<c:url value="/checkUniqueField" var="checkUniqueField"></c:url>
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

				<%-- 	<div class="col-xs-12">
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

				<div class="col-lg-12"></div>



				<div class="col-lg-12">
					<section class="box ">

						<header class="panel_header">
							<h2 class="title pull-left">${title}</h2>

							<div class="actions panel_actions pull-right">
								<a href="${pageContext.request.contextPath}/showProgramList"><button
										type="button" class="btn btn-info">Back</button></a>
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/submitAddProgram"
										method="post" name="form_sample_2" id="form_sample_2"
										onsubmit="return confirm('Do you really want to submit the form?');">

										<ul class="nav nav-tabs">
											<li class="active"><a href="#home" data-toggle="tab">
													<i class="fa fa-home"></i> Program
											</a></li>


										</ul>

										<div class="tab-content">
											<div class="tab-pane fade in active" id="home">

												<div>

													<div class="col-xs-12">

														<div class="form-group">
															<label class="control-label col-sm-2" for="programType">Program
																Type <span class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<select id="programType" name="programType"
																	class="form-control" required>
																	<c:forEach items="${progTypeList}" var="progTypeList">
																		<c:choose>
																			<c:when
																				test="${progTypeList.programId==editProgram.programType}">
																				<option selected value="${progTypeList.programId}">${progTypeList.programName}</option>
																			</c:when>
																			<c:otherwise>
																				<option value="${progTypeList.programId}">${progTypeList.programName}</option>

																			</c:otherwise>

																		</c:choose>

																	</c:forEach>
																</select>


															</div>
														</div>


														<div class="form-group">
															<label class="control-label col-sm-2" for="monthDuration">
																Duration(in months)<span class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<input type="number" class="form-control"
																	id="monthDuration" value="${editProgram.monthDuration}"
																	name="monthDuration" placeholder="Duration(in months)"
																	required>
															</div>
														</div>

														<div class="form-group">
															<label class="control-label col-sm-2" for="nameOfProgram">
																Name of Program <span class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<input type="text" class="form-control"
																	id="nameOfProgram" value="${editProgram.nameOfProgram}"
																	name="nameOfProgram" placeholder="Name of Program "
																	required>
															</div>
														</div>

														<div class="form-group">
															<label class="control-label col-sm-2" for="intake">Sanctioned
																Intake <span class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<input type="text" class="form-control " id="intake"
																	value="${editProgram.sanctionalIntake}" name="intake"
																	placeholder=" Sanctioned Intake" required>
															</div>
														</div>


														<div class="form-group">
															<label class="control-label col-sm-2" for="date">Date/Year
																of Introduction <span class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<input type="text" class="form-control datepicker"
																	id="date" value="${editProgram.dateOfIntroduction}"
																	name="date" placeholder="Date/Year of Introduction"
																	required>
															</div>
														</div>
														<c:set var="findOther" value="0"></c:set>
														
														<div class="form-group">
															<label class="control-label col-sm-2" for="approvedBy">Approved
																By <span class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<select id="approvedBy" name="approvedBy"
																	class="form-control" onchange="showExtraField()"
																	required>

																	<c:choose>
																		<c:when test="${editProgram.programId>0}">

																			<c:choose>
																				<c:when test="${editProgram.approvedBy eq 'BOS/AC'}">
																					<option value="BOS/AC" selected>BOS/AC</option>
																					<option value="Industry">Industry</option>
																					<option value="AICTE">AICTE</option>
																					<option value="NCTE">NCTE</option>
																					<option value="MCI">MCI</option>
																					<option value="DCI">DCI</option>
																					<option value="PCI">PCI</option>
																					<option value="7">Any Other</option>
																				</c:when>
																				<c:when
																					test="${editProgram.approvedBy eq 'Industry'}">
																					<option value="BOS/AC">BOS/AC</option>
																					<option value="Industry" selected>Industry</option>
																					<option value="AICTE">AICTE</option>
																					<option value="NCTE">NCTE</option>
																					<option value="MCI">MCI</option>
																					<option value="DCI">DCI</option>
																					<option value="PCI">PCI</option>
																					<option value="7">Any Other</option>
																				</c:when>
																				<c:when test="${editProgram.approvedBy eq 'AICTE'}">
																					<option value="BOS/AC">BOS/AC</option>
																					<option value="Industry">Industry</option>
																					<option value="AICTE" selected>AICTE</option>
																					<option value="NCTE">NCTE</option>
																					<option value="MCI">MCI</option>
																					<option value="DCI">DCI</option>
																					<option value="PCI">PCI</option>
																					<option value="7">Any Other</option>
																				</c:when>
																				<c:when test="${editProgram.approvedBy eq 'NCTE'}">
																					<option value="BOS/AC">BOS/AC</option>
																					<option value="Industry">Industry</option>
																					<option value="AICTE">AICTE</option>
																					<option value="NCTE" selected>NCTE</option>
																					<option value="MCI">MCI</option>
																					<option value="DCI">DCI</option>
																					<option value="PCI">PCI</option>
																					<option value="7">Any Other</option>
																				</c:when>
																				<c:when test="${editProgram.approvedBy eq 'MCI'}">
																					<option value="BOS/AC">BOS/AC</option>
																					<option value="Industry">Industry</option>
																					<option value="AICTE">AICTE</option>
																					<option value="NCTE">NCTE</option>
																					<option value="MCI" selected>MCI</option>
																					<option value="DCI">DCI</option>
																					<option value="PCI">PCI</option>
																					<option value="7">Any Other</option>
																				</c:when>
																				<c:when test="${editProgram.approvedBy eq 'DCI'}">
																					<option value="BOS/AC">BOS/AC</option>
																					<option value="Industry">Industry</option>
																					<option value="AICTE">AICTE</option>
																					<option value="NCTE">NCTE</option>
																					<option value="MCI">MCI</option>
																					<option value="DCI" selected>DCI</option>
																					<option value="PCI">PCI</option>
																					<option value="7">Any Other</option>
																				</c:when>
																				<c:when test="${editProgram.approvedBy eq 'PCI'}">
																					<option value="BOS/AC">BOS/AC</option>
																					<option value="Industry">Industry</option>
																					<option value="AICTE">AICTE</option>
																					<option value="NCTE">NCTE</option>
																					<option value="MCI">MCI</option>
																					<option value="DCI">DCI</option>
																					<option value="PCI" selected>PCI</option>
																					<option value="7">Any Other</option>
																				</c:when>
																				<c:otherwise>

																					<option value="BOS/AC">BOS/AC</option>
																					<option value="Industry">Industry</option>
																					<option value="AICTE">AICTE</option>
																					<option value="NCTE">NCTE</option>
																					<option value="MCI">MCI</option>
																					<option value="DCI">DCI</option>
																					<option value="PCI">PCI</option>
																					<option value="7" selected>Any Other</option>
																					<c:set var="findOther" value="1"></c:set>

																				</c:otherwise>

																			</c:choose>
																		</c:when>
																		<c:otherwise>
																			<option value="BOS/AC">BOS/AC</option>
																			<option value="Industry">Industry</option>
																			<option value="AICTE">AICTE</option>
																			<option value="NCTE">NCTE</option>
																			<option value="MCI">MCI</option>
																			<option value="DCI">DCI</option>
																			<option value="PCI">PCI</option>
																			<option value="7">Any Other</option>
																			 
																		</c:otherwise>
																	</c:choose>
																</select>


															</div>
														</div>

														<div class="form-group" id="abc">
															<label class="control-label col-sm-2"
																for="otherApprovedBy"> Approved By <span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<input type="text" class="form-control"
																	id="otherApprovedBy" value="${editInst.contactNo}"
																	name="otherApprovedBy" placeholder="Approved By ">
															</div>
														</div>

														<c:choose>
															<c:when test="${editProgram.programId>0}">
																<input type="hidden" id="programId" name="programId"
																	value="${editProgram.programId}">
															</c:when>
															<c:otherwise>
																<input type="hidden" id="programId" name="programId"
																	value="0">
															</c:otherwise>
														</c:choose>

														<input type="hidden" id="is_view" name="is_view" value="0">
														
														<input type="hidden" id="findOtherName" name="findOtherName" value="${findOther}">
														
														<div class="form-group">
															<div class="col-sm-offset-2 col-sm-10">
																<input type="submit" class="btn btn-primary"
																	onclick="submit_f(1)" value="Add"> <input
																	type="submit" class="btn btn-primary"
																	onclick="submit_f(0)"
																	value="Save &
																		Next">
																<button type="reset" class="btn btn-default">Reset</button>
															</div>
														</div>




													</div>
													<div class="clearfix"></div>
												</div>

											</div>

										</div>

									</form>
								</div>

							</div>

						</div>
					</section>
				</div>

			</section>
		</section>

	</div>
	<!-- MAIN CONTENT AREA ENDS -->

	<!-- END CONTENT -->
	<script type="text/javascript">
		function submit_f(view) {
			document.getElementById("is_view").value = view;//create this 

		}
	</script>

	<script type="text/javascript">
		function showExtraField() {

			var qualType = document.getElementById("approvedBy").value
			if (qualType == 7) {

				document.getElementById("abc").style = "visible"

			} else {
				document.getElementById("abc").style = "display:none"
			}

		}

		function hideText() {
			var findOtherName = document.getElementById("findOtherName").value;
			
			if(findOtherName==1){
				document.getElementById("abc").style = "visible"
			}else{
				
				document.getElementById("abc").style = "display:none"
			}
			

		}
	</script>




	<!-- END CONTAINER -->
	<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->

	<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>



</body>
</html>