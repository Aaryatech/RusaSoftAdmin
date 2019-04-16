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
								<%-- <a
									href="${pageContext.request.contextPath}/showCollaborationLinkages"><button
										type="button" class="btn btn-info">Back</button></a> --%>

							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertColLinkages"
										method="post" name="form_sample_2" id="form_sample_2">

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



												<select id="colName" name="colName" class="form-control">
													<option value="-1">Select</option>
													<c:forEach items="${colList}" var="colList">
														<c:choose>
															<c:when test="${colList.linknameId==editInst.linknameId}">
																<option selected value="${colList.linknameId}">${colList.linknameText}</option>

															</c:when>
															<c:otherwise>
																<option value="${colList.linknameId}">${colList.linknameText}</option>

															</c:otherwise>

														</c:choose>

													</c:forEach>
												</select> <span class="error_form text-danger" id="error_name"
													style="display: none;">Please Select Collaboration /
													Linkage Name</span>

											</div>
										</div>
										<div class="form-group">

											<label class="control-label col-sm-2" for="agency">Collaboration
												Linkage with Agency <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control" id="col_agency"
													name="col_agency" autocomplete="off"
													onchange="return trim(this)"
													placeholder="Collaboration Linkage with Agency"
													value="${editInst.linkAgency}"> <span
													class="error_form text-danger" id="error_agency"
													style="display: none;">Please enter Collaboration
													Linkage with Agency</span>
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
													value="${editInst.linkNature}"> <span
													class="error_form text-danger" id="error_nature"
													style="display: none;">Please enter Nature of
													Linkage Collaboration</span>
											</div>
										</div>



										<div class="form-group">

											<label class="control-label col-sm-2" for="beneficiaryMOU">Beneficiary of 
											Collaboration/ Linkage <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">

												<select id="beneficiaryMOU" name="beneficiaryMOU"
													class="form-control">
													<!-- <option value="-1">Select</option>
															<option value="Students">Students</option>
															<option value="Staff">Staff</option>
															<option value="Students And Staff">Students And Staff</option> -->
													<option value="-1">Select</option>


													<c:choose>
														<c:when test="${'Students'== editInst.linkBeneficiary}">

															<option selected value="Students">Students</option>
															<option value="Faculty">Faculty</option>
															<option value="Students And Faculty">Students And
																Faculty</option>
														</c:when>
														<c:when test="${'Faculty'==editInst.linkBeneficiary}">

															<option value="Students">Students</option>
															<option selected value="Faculty">Faculty</option>
															<option value="Students And Faculty">Students And
																Faculty</option>
														</c:when>

														<c:when
															test="${'Students And Faculty'==editInst.linkBeneficiary}">


															<option value="Students">Students</option>
															<option value="Faculty">Faculty</option>
															<option selected value="Faculty">Students And
																Faculty</option>

														</c:when>


														<c:otherwise>

															<option value="Students">Students</option>
															<option value="Faculty">Faculty</option>
															<option value="Students And Faculty">Students And
																Faculty</option>

														</c:otherwise>

													</c:choose>

												</select> <span class="error_form text-danger" id="error_benf"
													style="display: none;">Please select Beneficiary of
													MoU</span>
											</div>
										</div>

										<div class="form-group">

											<label class="control-label col-sm-2" for="totalParticipants">No.
												of Participants / Beneficiary<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="number" min="0" class="form-control"
													autocomplete="off" id="totalParticipants"
													onkeypress='return restrictAlphabets(event)'
													name="totalParticipants" onchange="return trim(this)"
													placeholder="No. of Participants / Beneficiary"
													value="${editInst.linkBeneficiaryNos}"> <span
													class="error_form text-danger" id="error_part"
													style="display: none;">Please enter No. of
													Participants </span>
											</div>
										</div>



										<input type="hidden" id="link_id" name="link_id"
											value="${editInst.linkId}"> <input type="hidden"
											id="is_view" name="is_view" value="0">
										<div class="form-group">
											<div class="col-sm-offset-3 col-sm-9">


												<button type="submit" id="sub_button"
													class="btn btn-primary" onclick="submit_f(1)">
													<i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save
												</button>

												<a
													href="${pageContext.request.contextPath}/showCollaborationLinkages"><button
														type="button" class="btn btn-primary">
														<i class="${sessionScope.cancelIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Cancel
													</button></a>
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
	<script>
		function numbersOnlyNotZero(id_number) {

			var mob = /^[1-9][0-9]+$/;

			if (mob.test($.trim(id_number)) == false) {

				//alert("Please enter a valid email address .");
				return false;

			}
			return true;
		}

		$(document)
				.ready(
						function($) {
							//  alert("hii....");
							$("#form_sample_2")
									.submit(
											function(e) {
												var isError = false;
												var errMsg = "";

												if (!$("#col_agency").val()) {

													isError = true;

													$("#col_agency").addClass(
															"has-error")
													$("#error_agency").show()
													//return false;
												} else {
													$("#error_agency").hide()
												}

												if (!$("#linkageNature").val()) {

													isError = true;

													$("#linkageNature")
															.addClass(
																	"has-error")
													$("#error_nature").show()
													//return false;
												} else {
													$("#error_nature").hide()
												}

												if ($("#colName").val() == -1) {

													isError = true;

													$("#error_name").show()
													//return fregister_useralse;
												} else {
													$("#error_name").hide()
												}

												if ($("#beneficiaryMOU").val() == -1) {

													isError = true;

													$("#error_benf").show()
													//return fregister_useralse;
												} else {
													$("#error_benf").hide()
												}

												if ($("#totalParticipants")
														.val() == 0
														|| !$(
																"#totalParticipants")
																.val()) {

													isError = true;
													errMsg += '<li>Please enter a valid name.</li>';

													$("#totalParticipants")
															.addClass(
																	"has-error")
													$("#error_part").show()
													//return false;
												} else {
													$("#error_part").hide()
												}

												if (!isError) {
													var x = confirm("Do you really want to submit the form?");
													if (x == true) {
														return true;
														document
																.getElementById("sub1").disabled = true;
														document
																.getElementById("sub2").disabled = true;
													}
												}

												return false;
											});
						});
		//
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
			document.getElementById("is_view").value = view;//create this 
			/* var form=document.getElementById("form_sample_2");
			form.setAttribute("method", "post");

			form.action=("insertLibrarian");
			var x =confirm();
			if(x==true)
			form.submit(); */

		}

		var wasSubmitted = false;
		function checkBeforeSubmit() {
			if (!wasSubmitted) {
				var x = confirm("Do you really want to submit the form?");
				if (x == true) {
					wasSubmitted = true;
					document.getElementById("sub1").disabled = true;
					document.getElementById("sub2").disabled = true;

					return wasSubmitted;
				}
			}
			return false;
		}
	</script>

	<script type="text/javascript">
		/*code: 48-57 Numbers
		  8  - Backspace,
		  35 - home key, 36 - End key
		  37-40: Arrow keys, 46 - Delete key*/
		function restrictAlphabets(e) {
			var x = e.which || e.keycode;
			if ((x >= 48 && x <= 57) || x == 8 || (x >= 35 && x <= 40)
					|| x == 46)
				return true;
			else
				return false;
		}
	</script>

</body>
</html>