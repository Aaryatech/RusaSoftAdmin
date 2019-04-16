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
<body onload="hideText()">
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
							<%-- 	<a href="${pageContext.request.contextPath}/showStudSupp"><button
										type="button" class="btn btn-info">Back</button></a> --%>
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertStudentSuppurtScheme"
										method="post" name="form_sample_2" id="form_sample_2">

										<div>

											<div class="col-xs-12">



												<input type="hidden" name="stud_suprt_schm"
													value="${stud.sprtSchmId}">
													
												<div class="form-group">
													<label class="control-label col-sm-2" for="status">Level
														<span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<select id="approveValue1" name="level"
															class="form-control">
															<option value="-1">Select</option>
															<c:choose>
																<c:when test="${stud.level eq 'International'}">
																	<option selected value="International">International</option>
																	<option value="State">State</option>
																	<option value="Regional">Regional</option>
																</c:when>

																<c:when test="${stud.level eq 'State'}">
																	<option value="International">International</option>
																	<option selected value="State">State</option>
																	<option value="Regional">Regional</option>
																</c:when>

																<c:when test="${stud.level eq 'Regional'}">
																	<option value="International">International</option>
																	<option value="State">State</option>
																	<option selected value="Regional">Regional</option>
																</c:when>

																<c:otherwise>
																	<option value="International">International</option>
																	<option value="State">State</option>
																	<option value="Regional">Regional</option>
																</c:otherwise>
															</c:choose>
														</select> <span class="error_form text-danger" id="error_level"
															style="display: none;">Please Select Level.</span>
													</div>
												</div>
												
												<div class="form-group">
													<label class="control-label col-sm-2" for="status">Type
														<span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<select id="approveValue" name="type" class="form-control">
															<option value="-1">Select</option>
																<option  value="Govt." ${stud.type == 'Govt.' ? 'selected' : '' }>Govt.</option>
																<option  value="Non Govt." ${stud.type == 'Non Govt.' ? 'selected' : '' }>Non Govt.</option>
															<%-- <c:choose>
																<c:when test="${stud.type eq 'Govt.'}">
																	<option selected value="Govt">Govt.</option>
																	<option value="Non Govt.">Non Govt.</option>

																</c:when>
																<c:when test="${stud.type eq 'Non Govt.'}">
																	<option value="Govt">Govt.</option>
																	<option selected value="Non Govt.">Non Govt.</option>

																</c:when>
																<c:otherwise>
																	<option value="Govt">Govt.</option>
																	<option value="Non Govt.">Non Govt.</option>
																</c:otherwise>

															</c:choose> --%>
														</select> <span class="error_form text-danger" id="error_type"
															style="display: none;">Please Select Type.</span>


													</div>
												</div>
												
												

												<div class="form-group">
													<label class="control-label col-sm-2" for="status">Scheme
														Name <span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<select id="schemeName" name="schemeName"
															class="form-control" onchange="showExtraField()">
															<option value="-1">Select</option>
															<option value="Capability Enhancement" ${stud.schemeName == 'Capability Enhancement' ? 'selected' : ''} >Capability	Enhancement</option>
															<option value="Competitive Exams(MPSC,UPSC,PSU,RRB,etc)" ${stud.schemeName  == 'Competitive Exams(MPSC,UPSC,PSU,RRB,etc)' ? 'selected' : ''} >Competitive Exams(MPSC,UPSC,PSU,RRB,etc)</option>
															<option	value="Higher Education Entrance Exams(GATE,MAT,GPAT,CAT etc)" ${stud.schemeName  == 'Higher Education Entrance Exams(GATE,MAT,GPAT,CAT etc)' ? 'selected' : ''}>Higher Education Entrance Exams(GATE,MAT,GPAT,CAT etc)</option>
															<option value="Vocational Education Training" ${stud.schemeName  == 'Vocational Education Training' ? 'selected' : ''}>Vocational Education Training</option>
															<option value="7">Any Other</option>
															<%-- <c:choose>
															<c:when test="${stud.schemeName  eq 'Capability Enhancement'}">
															<option value="-1">Select</option>
															<option selected value="Capability Enhancement">Capability	Enhancement</option>
															<option value="Competitive Exams(MPSC,UPSC,PSU,RRB,etc)" >Competitive Exams(MPSC,UPSC,PSU,RRB,etc)</option>
															<option	value="Higher Education Entrance Exams(GATE,MAT,GPAT,CAT etc)">Higher Education Entrance Exams(GATE,MAT,GPAT,CAT etc)</option>
															<option value="Vocational Education Training">Vocational Education Training</option>
															<option value="7">Any Other</option>
															</c:when>
															
															<c:when test="${stud.schemeName  eq 'Competitive Exams(MPSC,UPSC,PSU,RRB,etc)'}">
															<option value="-1">Select</option>
														    <option value="Capability Enhancement">Capability Enhancement</option>
															<option selected value="Competitive Exams(MPSC,UPSC,PSU,RRB,etc)" >Competitive Exams(MPSC,UPSC,PSU,RRB,etc)</option>
															<option	value="Higher Education Entrance Exams(GATE,MAT,GPAT,CAT etc)">Higher Education Entrance Exams(GATE,MAT,GPAT,CAT etc)</option>
															<option value="Vocational Education Training">Vocational Education Training</option>
															<option value="7">Any Other</option>
															</c:when>
															
															<c:when test="${stud.schemeName  eq 'Higher Education Entrance Exams(GATE,MAT,GPAT,CAT etc)'}">
															<option value="-1">Select</option>
															<option value="Capability Enhancement">Capability	Enhancement</option>
															<option value="Competitive Exams(MPSC,UPSC,PSU,RRB,etc)" >Competitive Exams(MPSC,UPSC,PSU,RRB,etc)</option>
															<option	selected value="Higher Education Entrance Exams(GATE,MAT,GPAT,CAT etc)">Higher Education Entrance Exams(GATE,MAT,GPAT,CAT etc)</option>
															<option value="Vocational Education Training">Vocational Education Training</option>
															<option value="7">Any Other</option>
															</c:when>
															
															<c:when test="${stud.schemeName  eq 'Vocational Education Training'}">
															<option value="-1">Select</option>
															<option value="Capability Enhancement">Capability Enhancement</option>
															<option value="Competitive Exams(MPSC,UPSC,PSU,RRB,etc)" >Competitive Exams(MPSC,UPSC,PSU,RRB,etc)</option>
															<option	value="Higher Education Entrance Exams(GATE,MAT,GPAT,CAT etc)">Higher Education Entrance Exams(GATE,MAT,GPAT,CAT etc)</option>
															<option selected value="Vocational Education Training">Vocational Education Training</option>
															<option value="7">Any Other</option>
															</c:when>
														
															<c:otherwise>
															<option value="-1">Select</option>
															<option selected value="Capability Enhancement">Capability Enhancement</option>
															<option value="Competitive Exams(MPSC,UPSC,PSU,RRB,etc)" >Competitive Exams(MPSC,UPSC,PSU,RRB,etc)</option>
															<option	value="Higher Education Entrance Exams(GATE,MAT,GPAT,CAT etc)">Higher Education Entrance Exams(GATE,MAT,GPAT,CAT etc)</option>
															<option value="Vocational Education Training">Vocational Education Training</option>
															<option selected value="7">Any Other</option>
														</c:otherwise>
														
														</c:choose> --%>
														
														
									
														</select> <span class="error_form text-danger" id="error_scheme"
															style="display: none;">Please Select Scheme.</span>


													</div>
												</div>



												<div class="form-group" id="abc">
													<label class="control-label col-sm-2" for="page_order">
														Another Scheme Name <span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="text" autocomplete="off" class="form-control"
															id="anotherScheme" value="${stud.schemeName}"
															name="anotherScheme" placeholder="Another Scheme"
															onchange="trim(this)"> <span
															class="error_form text-danger" id="error_oth"
															style="display: none;">Please Enter Other Scheme.</span>
													</div>
												</div>

											<div class="form-group">
													<label class="control-label col-sm-2" for="page_order">
														Name of Support Agency <span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="supportAgency" onchange="trim(this)"
															value="${stud.supportAgencyName}" autocomplete="off"
															name="supportAgency" placeholder="Support Agency"
															> <span
															class="error_form text-danger" id="error_agency"
															style="display: none;">Please Enter Name of
															Support Agency. </span>

													</div>
												</div>




												<div class="form-group">
													<label class="control-label col-sm-2" for="page_order">Date
														of Implementation <span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="text" class="form-control datepicker"
															id="yearofIntro" placeholder="dd-MM-YYYY"
															 autocomplete="off" value="${stud.implementationYear}" name="yearofIntro">
															<span class="error_form text-danger" id="error_date" style="display:none;" >Please enter date of implementation .  </span>

													</div>
												</div>
												
												

												<div class="form-group">
													<label class="control-label col-sm-2" for="page_name">
														No. of Students Benefited <span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="text"  class="form-control"
															id="studBenifit" value="${stud.noStudentBenifited}"
															name="studBenifit" placeholder="Students Benifited"
															autocomplete="off"> <span
															class="error_form text-danger" id="error_part"
															style="display: none;">Please Enter No. of
															Students Benefited and value should be gerater than 0.</span>
													</div>
												</div>





												

											</div>

										</div>


										<input type="hidden" id="librarian_id" name="librarian_id"
											value="${stud.sprtSchmId}"> <input type="hidden"
											id="is_view" name="is_view" value="0">


										<div class="form-group">
													<div class="col-sm-offset-3 col-sm-9">


<button type="submit" id="sub_button" class="btn btn-primary"
													onclick="submit_f(1)"><i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save</button>
														
<a href="${pageContext.request.contextPath}/showStudSupp"><button
										type="button" class="btn btn-primary"><i class="${sessionScope.cancelIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Cancel</button></a>													</div>
												</div>

										<div class="clearfix"></div>
										<!-- 
											</div>

										</div>
 -->
									</form>
									<p class="desc text-danger fontsize11">Notice : * Fields are
										Mandatory.</p>
								</div>

							</div>

						</div>
					</section>
				</div>

			</section>
		</section>

	</div>                            
	<!-- MAIN CONTENT AREA ENDS -->
	<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>

	<!-- END CONTENT -->


	<script>
	function trim(el) {
		el.value = el.value.replace(/(^\s*)|(\s*$)/gi, ""). // removes leading and trailing spaces
		replace(/[ ]{2,}/gi, " "). // replaces multiple spaces with one space 
		replace(/\n +/, "\n"); // Removes spaces after newlines
		return;
	}  
	
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

if (!$("#yearofIntro").val()){
						        					 //alert("Hi")
						              				isError=true;
						              				
						              				
						              				$("#yearofIntro").addClass("has-error")
						              				$("#error_date").show()
						              					//return false;
						              				} else {
						              					$("#error_date").hide()
						              				}
											

												if ($("#schemeName").val() == -1) {

													isError = true;

													$("#error_scheme").show()
													//return fregister_useralse;
												} else {
													$("#error_scheme").hide()
												}

												if ($("#approveValue").val() == -1) {

													isError = true;

													$("#error_type").show()
													//return fregister_useralse;
												} else {
													$("#error_type").hide()
												}

												if ($("#approveValue1").val() == -1) {

													isError = true;

													$("#error_level").show()
													//return fregister_useralse;
												} else {
													$("#error_level").hide()
												}

												if ($("#schemeName").val() == 7) {
													if (!$("#anotherScheme")
															.val()) {

														isError = true;

														$("#anotherScheme")
																.addClass(
																		"has-error")
														$("#error_oth").show()
														//return false;
													} else {
														$("#error_oth").hide()
													}

												}

												if (!$("#studBenifit").val()
														|| !numbersOnlyNotZero($(
																"#studBenifit")
																.val())) {

													isError = true;

													$("#error_part").show()
													//return fregister_useralse;
												} else {
													$("#error_part").hide()
												}

												if (!$("#supportAgency").val()) {

													isError = true;

													$("#supportAgency")
															.addClass(
																	"has-error")
													$("#error_agency").show()
													//return false;
												} else {
													$("#error_agency").hide()
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

			$('.datepicker').datepicker({
				autoclose : true,
				format : "dd-mm-yyyy",
				changeYear : true,
				changeMonth : true

			});
		});
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

		function submit_f(view) {

			//alert("id is"+view);
			document.getElementById("is_view").value = view;//create this 
			/* var form=document.getElementById("form_sample_2");
			form.setAttribute("method", "post");

			form.action=("insertLibrarian");
			var x =confirm();
			if(x==true)
			form.submit(); */

		}
	</script>

	<script type="text/javascript">
		function showExtraField() {
			//alert("hii");
			//document.getElementById("abc").style = "display:none"
			var qualType = document.getElementById("schemeName").value;
			//alert("qualType::"+qualType);

			if (qualType == 7) {

				document.getElementById("abc").style = "visible"
				//document.getElementById("anotherScheme").setAttribute("required","true");

			} else {
				document.getElementById("abc").style = "display:none"
			}

		}

		function hideText() {
			var x = ${stud.sprtSchmId};
			//alert("hii"+x);
			var qualType = document.getElementById("schemeName").value;
			//alert("qualType::"+qualType);

			if (x == 7) {

				document.getElementById("abc").style = "visible"
				//document.getElementById("anotherScheme").setAttribute("required","true");

			} else {
				document.getElementById("abc").style = "display:none"
			}
			//	document.getElementById("abc").style = "display:none"
		}
	</script>
	<script type="text/javascript">
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




	<!-- END CONTAINER -->
	<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->




</body>
</html>