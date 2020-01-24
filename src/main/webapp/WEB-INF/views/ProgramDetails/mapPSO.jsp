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
								<!-- <a href="#myModal2"	data-toggle="modal"><button type="submit"
																class="btn btn-info">Add</button></a> -->
								<%-- <a href="${pageContext.request.contextPath}/sectionTreeList"><button
										type="button" class="btn btn-success">Add CMS Content</button></a> --%>

								<!--  <a class="box_setting fa fa-cog" data-toggle="modal" href="#section-settings"></a>
                    <a class="box_close fa fa-times"></a> -->

							</div>

						</header>

						<form action="${pageContext.request.contextPath}/popsomapped"
							method="get" id="libListForm"
							onsubmit="return checkBeforeSubmit()">
 
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

							<div class="content-body">

								<input type="hidden" id="prgId" name="prgId" value="${prgId}">
 
								<div class="row">


									<div class="col-xs-12">

										<%-- <div class="form-group">
										<label class="control-label col-sm-2" for="status">
											Duration:</label> <label class="control-label col-sm-10" for="status">
											<strong>${programDetail.monthDuration}</strong>
										</label>
									</div>
									 --%>
										<div class="form-group">

											<label class="control-label col-sm-2" for="status">
												Program Name:</label> <label class="control-label col-sm-10"
												for="status"> <strong>${progDetail.nameOfProgram}
											</strong></label>
										</div>

										<div class="form-group">

											<label class="control-label col-sm-2" for="status">
												Duration In(months):</label> <label class="control-label col-sm-10"
												for="status"> <strong>${progDetail.monthDuration}</strong></label>
										</div>
										<div class="form-group">

											<label class="control-label col-sm-2" for="status">
												Sanctioned Intake:</label> <label class="control-label col-sm-10"
												for="status"> <strong>${progDetail.sanctionalIntake}</strong>
											</label>

										</div>
										<div class="form-group">

											<label class="control-label col-sm-2" for="status">
												Program Outcome:</label> <label class="control-label col-sm-10"
												for="status"> <strong> ${poDetail.poText}</strong></label>

										</div>
									</div>

									<div class="col-xs-12">


										<div class="table-responsive">
											<table class="table table-bordered" id="table1">
												<thead>
													<tr>
														<th style="text-align: center;" width="3%">Sr No</th>
														<th class="check" style="text-align: center; width: 3%;"><input
															type="checkbox" name="selAll" id="selAll"
															onClick="selectedInst(this)" /> Select All</th>
														<th style="text-align: center; width: 3%;">Program
															Specific Outcomes</th>


													</tr>

												</thead>

												<tbody>
													<c:forEach items="${psoDetail}" var="pso" varStatus="count">
														<tr>
															<td style="text-align: center;">${count.index+1}</td>
															<td style="text-align: center;"><c:set var="find"
																	value="0"></c:set> <c:forEach items="${items}"
																	var="items">
																	<c:if test="${pso.psoId==items}">
																		<input type="checkbox" checked="checked" class="chk"
																			name="psoIds" id="psoIds${count.index+1}"
																			value="${pso.psoId}" />
																		<c:set var="find" value="1"></c:set>
																	</c:if>

																</c:forEach> <c:if test="${find==0}">
																	<input type="checkbox" class="chk" name="psoIds"
																		id="psoIds${count.index+1}" value="${pso.psoId}" />
																</c:if></td>



															<td>${pso.psoText}</td>



														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>


									</div>



									<div class="form-group">
										<label class="control-label col-sm-2" for="status">Satisfying
											Value <span class="text-danger">*</span>
										</label>
										<div class="col-sm-6">

											<select id="satValue" name="satValue" class="form-control">

												<!-- 	<option value="1">1</option>
												<option value="2">2</option>
												<option value="3">3</option>
												<option value="4">-</option>
												
												 -->
												<c:choose>
													<c:when test="${poDetail.psoMapSatisfyingValue=='1'}">

														<option selected value="1">1</option>
														<option value="2">2</option>
														<option value="3">3</option>
														<option value="4">-</option>

													</c:when>
													<c:when test="${poDetail.psoMapSatisfyingValue=='2'}">

														<option value="1">1</option>
														<option selected value="2">2</option>
														<option value="3">3</option>
														<option value="4">-</option>

													</c:when>
													<c:when test="${poDetail.psoMapSatisfyingValue =='3'}">

														<option value="1">1</option>
														<option value="2">2</option>
														<option selected value="3">3</option>
														<option value="4">-</option>

													</c:when>
													<c:when test="${poDetail.psoMapSatisfyingValue=='4'}">

														<option value="1">1</option>
														<option value="2">2</option>
														<option value="3">3</option>
														<option selected value="4">-</option>

													</c:when>
													<c:otherwise>
														<option value="1">1</option>
														<option value="2">2</option>
														<option value="3">3</option>
														<option value="4">-</option>
													</c:otherwise>

												</c:choose>

											</select>


										</div>
									</div>
									<input type="hidden" name="poId1" id="poId1" value="${poId1}" />
									<div class="col-xs-12"></div>
									<div class="col-xs-12"></div>
									<div class="col-xs-12"></div>
									<div class="col-xs-12"></div>
									<div class="col-xs-12"></div>
									<div class="col-xs-12"></div>
									<div class="col-xs-12"></div>

									<div class="form-group">
										<div class="col-sm-offset-2 col-sm-10">
											<button type="submit" id="sub1" class="btn btn-primary"
												onClick="var checkedVals = $('.chk:checkbox:checked').map(function() 
													{ return this.value;}).get();checkedVals=checkedVals.join(',');if(checkedVals=='')
													{alert('No Rows Selected');return false;	}
											else{   return confirm('Are you sure want to Map the  record');}">Submit</button>
											<button type="reset" class="btn btn-default">Reset</button>
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

	<script type="text/javascript">
		var wasSubmitted = false;
		function checkBeforeSubmit() {
			if (!wasSubmitted) {
				var x = confirm("Do you really want to submit the form?");
				if (x == true) {
					wasSubmitted = true;
					document.getElementById("sub1").disabled = true;
					//document.getElementById("sub2").disabled=true;

					return wasSubmitted;
				}
			}
			return false;
		}
	</script>




	<script type="text/javascript">
		function selectedInst(source) {

			checkboxes = document.getElementsByName('psoIds');

			for (var i = 0, n = checkboxes.length; i < n; i++) {
				checkboxes[i].checked = source.checked;

			}

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

