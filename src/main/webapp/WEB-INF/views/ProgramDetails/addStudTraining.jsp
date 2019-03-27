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
								<a href="${pageContext.request.contextPath}/showStudTran"><button
										type="button" class="btn btn-info">Back</button></a>
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">




									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertTrainPlace"
										method="post" name="form_sample_2" id="form_sample_2"
										onsubmit="return checkBeforeSubmit()">

										<div class="row">
											<div class="col-md-12">


												<div class="form-group">
													<label class="control-label col-sm-3" for="page_name">
														Name of Employer<span class="text-danger">*</span>
													</label>
													<div class="col-sm-9">
														<input type="text" class="form-control" id="employer_name"
															value="${trainPlace.empyrName}" name="employer_name"
															placeholder="Full Name of Employer" required>
													</div>
												</div>



												<div class="form-group">
													<label class="control-label col-sm-3" for="status">Program
														Type<span class="text-danger">*</span>
													</label>
													<div class="col-sm-9">
														<select id="prog_type" name="prog_type"
															class="form-control" required>

															<c:forEach items="${progTypeList}" var="progType">
																<c:choose>
																	<c:when
																		test="${trainPlace.programType==progType.programId}">
																		<option selected value="${progType.programId}">${progType.programName}</option>

																	</c:when>
																	<c:otherwise>

																		<option value="${progType.programId}">${progType.programName}</option>

																	</c:otherwise>

																</c:choose>

															</c:forEach>


														</select>
													</div>
												</div>

												<div class="form-group">
													<label class="control-label col-sm-3" for="page_order">
														Name of Program<span class="text-danger">*</span>
													</label>
													<div class="col-sm-9">
														<input type="text" class="form-control" id="prog_name"
															value="${trainPlace.programName}" name="prog_name"
															placeholder="Name of Program " required>
													</div>
												</div>



												<div class="form-group">
													<label class="control-label col-sm-3" for="page_order">
														No. of Students Placed<span class="text-danger">*</span>
													</label>
													<div class="col-sm-9">
														<input type="number" maxlength="5" min="0"
															class="form-control" id="no_stud_placed"
															value="${trainPlace.noStudentPlaced}"
															name="no_stud_placed"
															placeholder="Total Students Placed " required>
													</div>
												</div>



												<div class="form-group">
													<label class="control-label col-sm-3" for="page_order">
														Address of Employer<span class="text-danger">*</span>
													</label>
													<div class="col-sm-9">
														<input type="text" class="form-control"
															id="employer_address" value="${trainPlace.empyrAdd}"
															name="employer_address" required>
													</div>
												</div>



												<div class="form-group">
													<label class="control-label col-sm-3" for="page_order">
														Contact Details<span class="text-danger">*</span>
													</label>
													<div class="col-sm-9">
														<input type="text" class="form-control"
															id="contact_detail" value="${trainPlace.contactDetail}"
															name="contact_detail"
															placeholder="Employer Contact Information" required>
													</div>
												</div>


												<div class="form-group">
													<label class="control-label col-sm-3" for="page_order">
														Support Agency Name<span class="text-danger">*</span>
													</label>
													<div class="col-sm-9">
														<input type="text" class="form-control"
															id="sup_agency_name"
															value="${trainPlace.supportAgencyName}"
															name="sup_agency_name"
															placeholder="Enter Supprt Agency Name" required>
													</div>
												</div>



												<div class="form-group">
													<label class="control-label col-sm-3" for="page_order">
														Package Offered<span class="text-danger">*</span>
													</label>
													<div class="col-sm-9">
														<input type="number" min="0" maxlength="10" class="form-control"
															id="package_offered" value="${trainPlace.pakageOfferd}"
															name="package_offered"
															placeholder="Package Offered by Employer to Students  "
															required>
													</div>
												</div>

												<div class="form-group">
													<div class="col-sm-offset-3 col-sm-9">
														<input type="submit" id="sub1" class="btn btn-primary"
															onclick="submit_f(1)" value="Save"> <input
															type="submit" id="sub2" class="btn btn-primary"
															onclick="submit_f(0)"
															value="Save &
																		Next">
														<button type="reset" class="btn btn-default">Reset</button>
													</div>
												</div>
												<input type="hidden" id="place_id" name="place_id"
													value="${trainPlace.placementId}"> <input
													type="hidden" id="is_view" name="is_view" value="0">

											</div>

										</div>

										<div class="clearfix"></div>
									</form>

									<p class="desc text-danger fontsize11">Notice : * Fields
										are mandatory.</p>



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
			/* var form=document.getElementById("form_sample_2");
			form.setAttribute("method", "post");

			form.action=("insertLibrarian");
			var x =confirm();
			if(x==true)
			form.submit(); */

		}

		function checkUnique(inputValue, valueType) {
			//alert(inputValue);

			var primaryKey = $
			{
				editInst.librarianId
			}
			;
			//alert("Primary key"+primaryKey);
			var isEdit = 0;
			if (primaryKey > 0) {
				isEdit = 1;
			}
			//alert("Is Edit " +isEdit);

			var valid = false;
			if (valueType == 1) {
				//alert("Its Mob no");
				if (inputValue.length == 10) {
					valid = true;
					//alert("Len 10")
				} else {
					//alert("Not 10");
				}
			} else if (valueType == 2) {
				//alert("Its Email " );

				var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
				if (inputValue.match(mailformat)) {
					valid = true;
					//alert("Valid Email Id");
				} else {
					valid = false;
					//alert("InValid Email Id");
				}
			}
			if (valid == true)
				$
						.getJSON(
								'${checkUniqueField}',
								{

									inputValue : inputValue,
									valueType : valueType,
									primaryKey : primaryKey,
									isEdit : isEdit,
									tableId : 4,

									ajax : 'true',

								},
								function(data) {

									//	alert("Data  " +JSON.stringify(data));
									if (data.error == true) {
										if (valueType == 2) {

											alert("This email id already exist in system please enter unique");
											$('#librarian_email').val('');
											//document.getElementById("stud_contact_no").value=" ";

										} else {

											alert("This contact no  already exist in system please enter unique");
											$('#lib_con_num').val('');
											//document.getElementById("student_email").value=" ";
										}
									}
								});
		}
	</script>

	<script type="text/javascript">
		function showExtraField() {
			//alert("hii");
			//document.getElementById("abc").style = "display:none"
			var qualType = document.getElementById("approveValue").value
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
	<script type="text/javascript">
		function submit_f(view) {
			document.getElementById("is_view").value = view;//create this 

		}
	</script>

	<script type="text/javascript">
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



	<!-- END CONTAINER -->
	<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->

	<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>



</body>
</html>