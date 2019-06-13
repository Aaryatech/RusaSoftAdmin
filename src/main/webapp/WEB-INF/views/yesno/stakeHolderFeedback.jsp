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
table, th, td {
	padding: 5px !important;
}

.act_tr:hover {
	background: #F1F1F2;
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

				<%-- <div class="col-xs-12">
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

								<c:if test="${isAdd==1}">
									<a href="${pageContext.request.contextPath}/showAddConsultancy"><button
											type="button" class="btn btn-info">Add</button></a>
								</c:if>


							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertInstStakeholder"
										method="post" name="form_sample_2" id="form_sample_2"
										onsubmit="return confirm('Do you really want to submit the form?');">
										<div class="table-responsive">
											<table id="table1" style="width: 100%; padding-bottom: 50px;">
												<thead>
													<tr>
														<th width="20%"></th>
														<th width="10%"></th>
														<th width="30%"></th>
														<th width="40%"></th>
													</tr>
												</thead>

												<tbody>

													<c:choose>
														<c:when test="${isEdit == 1}">
															<c:forEach items="${tempFb}" var="tempFb">

																<tr class="act_tr">
																	<td>&nbsp;&nbsp;&nbsp;&nbsp;${tempFb.exVar1}</td>

																	<td><c:choose>
																			<c:when test="${tempFb.fbYesno==0}">
																				<input type="radio" name="yesNo${tempFb.fbFromId}"
																					value="1" id="yesNo${tempFb.fbFromId}"
																					onchange="vissibledive(${tempFb.fbFromId},1)"> YES <input
																					type="radio" id="yesNo${tempFb.fbFromId}"
																					name="yesNo${tempFb.fbFromId}" value="0"
																					onchange="vissibledive(${tempFb.fbFromId},0)"
																					checked>
																		NO
																		</c:when>
																			<c:otherwise>

																				<input type="radio" name="yesNo${tempFb.fbFromId}"
																					value="1" id="yesNo${tempFb.fbFromId}"
																					onchange="vissibledive(${tempFb.fbFromId},1)"
																					checked> YES <input type="radio"
																					id="yesNo${tempFb.fbFromId}"
																					name="yesNo${tempFb.fbFromId}" value="0"
																					onchange="vissibledive(${tempFb.fbFromId},0)">
																		NO
																	 
																	</c:otherwise>
																		</c:choose></td>

																	<td>
																		<div id="otherprevresps${tempFb.fbFromId}">
																			<div class="form-group">
																				<label class="control-label col-sm-3" for="status">Select
																					Year <span class="text-danger">*</span>
																				</label>
																				<div class="col-sm-5">
																					<select id="hod_quolf"
																						name="quolif${tempFb.fbFromId}"
																						class="form-control">
																						<option ${tempFb.yrSem == '1'  ? 'Selected': '' }
																							value="1">Yearwise</option>
																						<option ${tempFb.yrSem == '2' ? 'Selected': '' }
																							value="2">Semwise</option>
																						<c:if test="${tempFb.fbYesno==0}">
																							<option selected value="0">NA</option>
																						</c:if>

																					</select>
																				</div>
																			</div>
																		</div>
																	</td>
																	<td>
																		<div id="resps${tempFb.fbFromId}">
																			<div class="form-group">
																				<label class="control-label col-sm-5" for="status">Select
																					Feedback Process <span class="text-danger">*</span>
																				</label>
																				<div class="col-sm-7">
																					<select id="hod_quolf"
																						name="fbProcess${tempFb.fbFromId}"
																						class="form-control">

																						<option
																							${tempFb.fbProcess eq 'A'  ? 'Selected': '' }
																							value="A">Feedback Collected,analyzed
																							and action taken and feedback available on
																							websites</option>
																						<option
																							${tempFb.fbProcess eq 'B' ? 'Selected': '' }
																							value="B">Collected,analyzed and action
																							has been taken</option>
																						<option
																							${tempFb.fbProcess eq 'C'  ? 'Selected': '' }
																							value="C">Feedback Collected and analyzed
																							</option>
																						<option
																							${tempFb.fbProcess eq 'D' ? 'Selected': '' }
																							value="D">Feedback Collected</option>

																						<c:if test="${tempFb.fbYesno==0}">
																							<option selected value="0">NA</option>
																						</c:if>

																					</select>
																				</div>
																			</div>
																		</div>
																	</td>


																</tr>
																<c:if test="${isEdit==0}">

																	<input type="hidden" name="fbFromId"
																		value="${tempFb.fbFromId}">

																	<input type="hidden" name="stakFbId${tempFb.fbFromId}"
																		value="${tempFb.stakFbId}">

																	<input type="hidden" name="isEdit" value="${isEdit}">


																</c:if>

															</c:forEach>

														</c:when>
														<c:otherwise>

															<c:forEach items="${stakeFblist}" var="stakeFblist">

																<tr class="act_tr">
																	<td>&nbsp;&nbsp;&nbsp;&nbsp;${stakeFblist.feedbackFrom}</td>

																	<td><input type="radio"
																		name="yesNo${stakeFblist.feedbackId}" value="1"
																		id="yesNo${stakeFblist.feedbackId}"
																		onchange="vissibledive(${stakeFblist.feedbackId},1)"
																		checked> YES <input type="radio"
																		id="yesNo${stakeFblist.feedbackId}"
																		name="yesNo${stakeFblist.feedbackId}" value="0"
																		onchange="vissibledive(${stakeFblist.feedbackId},0)">
																		NO</td>

																	<td>
																		<div id="otherprevresps${stakeFblist.feedbackId}">
																			<div class="form-group">
																				<label class="control-label col-sm-3" for="status">Select
																					Year <span class="text-danger">*</span>
																				</label>
																				<div class="col-sm-5">
																					<select id="hod_quolf"
																						name="quolif${stakeFblist.feedbackId}"
																						class="form-control">
																						<option value="1">Yearwise</option>
																						<option value="2">Semwise</option>
																					</select>
																				</div>
																			</div>
																		</div>
																	</td>
																	<td>
																		<div id="resps${stakeFblist.feedbackId}">
																			<div class="form-group">
																				<label class="control-label col-sm-5" for="status">Select
																					Feedback Process <span class="text-danger">*</span>
																				</label>
																				<div class="col-sm-7">
																					<select id="hod_quolf"
																						name="fbProcess${stakeFblist.feedbackId}"
																						class="form-control">
																						<option value="A">Feedback
																							Collected,analyzed and action taken and feedback
																							available on websites</option>
																						<option value="B">Feedback
																							Collected,analyzed and action has been taken</option>
																						<option value="C">Feedback Collected and
																							analyzed</option>
																						<option value="D">Feedback Collected</option>
																					</select>
																				</div>
																			</div>
																		</div>
																	</td>


																</tr>
																<input type="hidden" name="fbId"
																	value="${stakeFblist.feedbackId}">
															</c:forEach>
														</c:otherwise>
													</c:choose>


												</tbody>

											</table>
										</div>

										<!-- 										<input type="hidden" id="srindex" name="srindex" value="0">
 -->
										<br>
										<div class="form-group">
											<div class="col-sm-offset-2 col-sm-10">
												<!-- 	<input type="submit" class="btn btn-primary" value="Submit"> -->
												<button type="submit" id="sub_button"
													class="btn btn-primary">
													<i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save
												</button>



											</div>
										</div>
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



	<script type="text/javascript">
		function vissibledive(yesnoId, value) {
			//alert("hiii...");

			if (value == 1) {

				document.getElementById("resps" + yesnoId).style = "visible";
				document.getElementById("otherprevresps" + yesnoId).style = "visible";


			} else {
				document.getElementById("resps" + yesnoId).style = "display:none";
				document.getElementById("otherprevresps" + yesnoId).style = "display:none";
			}

		}
	</script>

	<script type="text/javascript">
		function othervissibledive(yesnoId, value) {

			if (value == 1) {

				document.getElementById("otherresps" + yesnoId).style = "visible";

			} else {
				document.getElementById("otherresps" + yesnoId).style = "display:none";
			}

		}
		function otherpreviousvissibledive(yesnoId, value) {

			if (value == 1) {

				document.getElementById("otherprevresps" + yesnoId).style = "visible";

			} else {
				document.getElementById("otherprevresps" + yesnoId).style = "display:none";
			}

		}
	</script>
	<script type="text/javascript">
		function addDynamicYesNo() {

			var othertitle = document.getElementById("othertitle").value;
			var srindex = parseInt(document.getElementById("srindex").value);

			if (othertitle == "" || othertitle == null) {

				alert("Enter Title");

			} else {

				srindex = srindex + 1;

				var acButton = '<input type="radio" name="dynamicyesno'
						+ srindex + '" value="1" onchange="othervissibledive('
						+ srindex + ',1)" checked>'
						+ 'YES <input type="radio" id="dynamicyesno' + srindex
						+ '" name="dynamicyesno' + srindex
						+ '" value="0" onchange="othervissibledive(' + srindex
						+ ',0)"> NO'

				var div = '<div id="otherresps'+srindex+'"> <div class="col-sm-3">Enter Value</div> <div class="col-sm-5"> <input type="text" maxlength="50"'+
				' class="form-control" id="dynamicyesnovalue'+srindex+'" name="dynamicyesnovalue'+srindex+'" placeholder="Enter value" > </div> </div>'

				var tr = $('<tr class="act_tr" ></tr>');
				tr
						.append($('<td  ></td>')
								.html(
										'&nbsp;&nbsp;&nbsp;&nbsp;<input type="hidden" id="otherTitleName'+srindex+'" name="otherTitleName'+srindex+'" value="'+othertitle+'">'
												+ othertitle));
				tr.append($('<td  ></td>').html(''));
				tr.append($('<td  ></td>').html(acButton));
				tr.append($('<td   ></td>').html(div));
				$('#table1 tbody').append(tr);

				document.getElementById("srindex").value = srindex;
			}

		}
	</script>


</body>
</html>


