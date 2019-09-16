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
	<c:url value="/getProgramTypeByProgram" var="getProgramTypeByProgram"></c:url>
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

							<%-- <div class="actions panel_actions pull-right">
								<a href="${pageContext.request.contextPath}/showStudTran"><button
										type="button" class="btn btn-info">Back</button></a>
							</div> --%>

						</header>

						<div class="content-body">
							<div class="row">
								<div class="col-md-12">

									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertNewCourseInfo"
										method="post" name="form_sample_2" id="form_sample_2">
										
										<div class="row">
											<div class="col-md-12">
											
											
												<div class="form-group">
													<label class="control-label col-sm-3" for="status">Program
														Name<span class="text-danger">*</span>
													</label>
													<div class="col-sm-9">
														<select id="prog_type" name="prog_type"
															class="form-control" onchange="getProgramTypeByProgram()">

															<c:forEach items="${progTypeList}" var="progType">
																<c:choose>
																	<c:when
																		test="${newCourse.progType==progType.programId}">
																		<option selected value="${progType.programId}">${progType.programName}</option>
																	</c:when>
																	<c:otherwise>
																		<option value="${progType.programId}">${progType.programName}</option>
																	</c:otherwise>
																</c:choose>
															</c:forEach>
														</select>
														<!-- <span class="error_form text-danger" id="prog_type_field"
															style="display: none;">Please select program type</span> -->
													</div>
												</div>
												
											<div class="form-group">
													<label class="control-label col-sm-3" for="page_order">
														Type of Program<span class="text-danger">*</span>
													</label>
													<div class="col-sm-9">

													<select id="prog_name" name="prog_name"
													class="form-control" required>
												</select>													
													<!-- <span class="error_form text-danger" id="prog_name_field"
															style="display: none;">Please enter program name</span> -->
													</div>
											</div>

												
												<div class="form-group">
													<label class="control-label col-sm-3" for="status">Applicable
													Year <span class="text-danger">*</span>
												</label>
												<div class="col-sm-9">
												<select id="applicabl_year" name="applicabl_year" class="form-control">
																
														<option value="First Year" ${newCourse.applicableYear eq 'First Year' ? 'selected' : '' }>First Year</option>
														<option value="Second Year" ${newCourse.applicableYear eq 'Second Year' ? 'selected' : '' }>Second Year</option>
														<option value="Third Year" ${newCourse.applicableYear eq 'Third Year' ? 'selected' : '' }>Third Year</option>
														<option value="Fourth Year" ${newCourse.applicableYear eq 'Fourth Year' ? 'selected' : '' }>Fourth Year</option>
														<option value="Fifth Year" ${newCourse.applicableYear eq 'Fifth Year' ? 'selected' : '' }>Fifth Year</option>
														
													
													</select>
													<!--  <span class="error_form text-danger" id="error_year" style="display:none;" >Please Select Academic Year</span> -->
                                       </div>
										</div>	

												<div class="form-group">
													<label class="control-label col-sm-3" for="page_name">Name of the 
														New Course<span class="text-danger">*</span>
													</label>
													<div class="col-sm-9">
														<input type="text" class="form-control" onchange="trim(this)" id="courseName"
															value="${newCourse.courseName}" name="courseName"
															placeholder="Name of the New Course" maxlength="100">
															<span class="error_form text-danger" id="course_name_field"
															style="display: none;">Please enter name of the new course.</span>
													</div>
												</div>



												<div class="form-group">
													<label class="control-label col-sm-3" for="page_order">Course
														 Code<span class="text-danger">*</span>
													</label>
													<div class="col-sm-9">
														<input type="text" class="form-control" onFocus="clearDefault(this)"
															id="courseCode" value="${newCourse.courseCode}"
															name="courseCode" onchange="trim(this)" maxlength="10"
															placeholder="Course Code">
															<span class="error_form text-danger" id="course_code_field"
															style="display: none;">Please enter course code.</span>
													</div>
												</div>
												
													<div class="form-group">

															<label class="control-label col-sm-3" for="smallheading">Introduced
																From <span class="text-danger">*</span>
															</label>
														<div class="col-sm-9">
														<select id="acadYear" name="acadYear" class="form-control">
																																	
															<c:forEach items="${acaYearList}" var="acaYearList">
																		<c:choose>
																			<c:when test="${acaYearList.yearId==newCourse.introduceFrom}">
																			<option selected value="${acaYearList.yearId}">${acaYearList.academicYear}</option>

																			</c:when>
																			<c:otherwise>
																				<option value="${acaYearList.yearId}">${acaYearList.academicYear}</option>

																			</c:otherwise>

																		</c:choose>

																	</c:forEach>

													</select>
                                      				 </div>

														</div>

												<div class="form-group">
													<label class="control-label col-sm-3" for="page_order">Link of Relevant 
														Documents<span class="text-danger"></span>
													</label>
													<div class="col-sm-9">
														<input type="text" class="form-control"
															id="document" onchange="trim(this)"
															value="${newCourse.document}"
															name="document" maxlength="100"
															placeholder="Link of Relevant Document">
															<!-- <span class="error_form text-danger" id="document_field"
															style="display: none;">Please enter link of relevant documents.</span> -->
													</div>
												</div>

										<input type="hidden" id="progTypeId" name="progTypeId"
													value="${newCourse.progName}">

												<div class="form-group">
													<div class="col-sm-offset-3 col-sm-9">
														<button type="submit" id="sub1" class="btn btn-primary" onclick="submit_f(1)"><i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save</button>
														<a href="${pageContext.request.contextPath}/showNewCourseInfo"><button id="sub2" type="button" class="btn btn-primary"><i class="${sessionScope.cancelIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Cancel</button></a>
													</div>
												</div>
												<input type="hidden" id="course_id" name="course_id"
													value="${newCourse.courseId}"> <input
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
	
	<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>

	<script type="text/javascript">
	
	$('#courseCode').on('input', function() {
		  this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');
		});
	
		function getProgramTypeByProgram() {

			var programType = document.getElementById("prog_type").value;
			var progTypeId=document.getElementById("progTypeId").value;
			//alert("programType" + programType);
			
			var valid = true;

			if (programType == null || programType == "") {
				valid = false;
				alert("Please Select Program");
			}

			if (valid == true) {

				$.getJSON('${getProgramTypeByProgram}', {
					programType : programType,
					ajax : 'true',
				},

				function(data) {
					//alert(data);
				
					var html;
					var len = data.length;
					for (var i = 0; i < len; i++) {
						if(progTypeId==data[i].programId){
							html += '<option  selected value="' + data[i].programId + '">'
								+ data[i].nameOfProgram + '</option>';
							}else{
						html += '<option value="' + data[i].programId + '">'
								+ data[i].nameOfProgram + '</option>';
							}
					}
					html += '</option>';

					$('#prog_name').html(html);
					$("#prog_name").trigger("chosen:updated");

				});
			}//end of if

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
						function($) {		// 			

							$("#form_sample_2")
									.submit(
											function(e) {
												var isError = false;
												var errMsg = "";

												if (!$("#courseName").val()) {
													isError = true;

													$("#courseName").addClass(
															"has-error")
													$("#course_name_field")
															.show()
												} else {
													$("#course_name_field")
															.hide()
												}

												if (($("#courseCode").val()<=0 || !$("#courseCode").val())) {
													isError = true;
													$("#courseCode")
															.addClass(
																	"has-error")
													$("#course_code_field")
															.show()
												} else {
													$("#course_code_field")
															.hide()
												}

												/* if (!$("#document").val()) {
													isError = true;

													$("#document").addClass(
															"has-error")
													$("#document_field").show()
												} else {
													$("#document_field").hide()
												} */
												 
												
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
			getProgramTypeByProgram();
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
		
		function trim(el) {
			el.value = el.value.replace(/(^\s*)|(\s*$)/gi, ""). // removes leading and trailing spaces
			replace(/[ ]{2,}/gi, " "). // replaces multiple spaces with one space 
			replace(/\n +/, "\n"); // Removes spaces after newlines
			return;
		}
	</script>
<script type="text/javascript">
function clearDefault(a){
	if(a.defaultValue==0)
	{
		a.value=""
	}
	};
			/*code: 48-57 Numbers
			  8  - Backspace,
			  35 - home key, 36 - End key
			  37-40: Arrow keys, 46 - Delete key*/
			function restrictAlphabets(e){
				var x=e.which||e.keycode;
				if((x>=48 && x<=57) || x==8 ||
					(x>=35 && x<=40)|| x==46)
					return true;
				else
					return false;
			}
		</script>


	<!-- END CONTAINER -->
	<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->

	

</body>
</html>