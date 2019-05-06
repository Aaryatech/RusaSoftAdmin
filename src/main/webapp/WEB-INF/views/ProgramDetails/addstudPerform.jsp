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
<body class=" " onload="getProgramTypeByProgram()">
<c:url value="/getProgramTypeByProgramId" var="getProgramTypeByProgramId"></c:url>
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
								<%-- <a href="${pageContext.request.contextPath}/showProgramList"><button
										type="button" class="btn btn-info">Back</button></a> --%>
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertStudPerformInFinalYr"
										method="post" name="form_sample_2" id="form_sample_2">




										<div class="form-group">
										<input type="hidden" name="stud_perform_id" name="stud_perform_id" value="${studPer.studPerformId}">
										
											<label class="control-label col-sm-2" for="programType">Program
												 <span class="text-danger">*</span>
											</label>
											<div class="col-sm-10">
												<select id="programType" name="programType" onchange="getProgramTypeByProgram()"
													class="form-control" required>
													<c:forEach items="${progTypeList}" var="progTypeList">
														<c:choose>
															<c:when
																test="${progTypeList.programId==studPer.progName}">
																<option selected value="${progTypeList.programId}">${progTypeList.programName}</option>
															</c:when>
															<c:otherwise>
																<option value="${progTypeList.programId}">${progTypeList.programName}</option>

															</c:otherwise>

														</c:choose>

													</c:forEach>
												</select>
												<span class="error_form text-danger" id="error_formfield1" style="display:none;" >
												Please select Program.</span>
											</div>
										</div>


										<div class="form-group">
											<label class="control-label col-sm-2" for="monthDuration">Program
												Type<span class="text-danger">*</span>
											</label>
											<div class="col-sm-10">
											<select id="programTypeId" name="programTypeId"
													class="form-control"  required>

												</select>
													<span class="error_form text-danger" id="error_formfield2" style="display:none;" >
													Please select program type.</span>
											</div>
										</div>

										<div class="form-group">
											<label class="control-label col-sm-2" for="nameOfProgram">No. of  
												Students Appeared<span class="text-danger">*</span>
											</label>
											<div class="col-sm-10">
												<input type="text" class="form-control" id="stud_appeared"
													value="${studPer.noStudAppear}" name="stud_appeared"
													placeholder="No of Students Appeared in Final Year Exam ">
													<span class="error_form text-danger" id="error_formfield3" style="display:none;" >
													Please enter No of students appeared in final year exam and value must be greater than 0.</span>
											</div>
										</div>

										<div class="form-group">
											<label class="control-label col-sm-2" for="intake">No. of 
												Students Passed <span class="text-danger">*</span>
											</label>
											<div class="col-sm-10">
												<input type="text" class="form-control" id="stud_passed"
													value="${studPer.noStudPass}" name="stud_passed"
													placeholder="No of Students Passed in Final Year Exam"  pattern="\d*" onblur="getPercent()">
											<span class="error_form text-danger" id="error_formfield4" style="display:none;" >
											Please enter No of students passed in final year exam and value must be greater than 0.</span>
											</div>
										</div>


										<div class="form-group">
											<label class="control-label col-sm-2" for="date">Student 
												Passing % <span class="text-danger">*</span>
											</label>
											<div class="col-sm-10">
												<input type="text" class="form-control" id="stud_pass_per"
													value="${studPer.passingPer}" name="stud_pass_per" readonly
													placeholder="Student Passing %">
											<!-- <span class="error_form text-danger" id="error_formfield4" style="display:none;" >
											Please enter date of introduction.</span> -->
											</div>
										</div>
										
									
									<div class="form-group">
											<div class="col-sm-offset-3 col-sm-9">


<button type="submit" id="sub_button" class="btn btn-primary"
													onclick="submit_f(1)"><i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save</button>
														
<a href="${pageContext.request.contextPath}/showStudPerformInFinlYr"><button
										type="button" class="btn btn-primary"><i class="${sessionScope.cancelIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Cancel</button></a>													</div>
												</div>

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
	
<script>
	function trim(el) {
		el.value = el.value.replace(/(^\s*)|(\s*$)/gi, ""). // removes leading and trailing spaces
		replace(/[ ]{2,}/gi, " "). // replaces multiple spaces with one space 
		replace(/\n +/, "\n"); // Removes spaces after newlines
		return;
	}
	
               
            	$(document).ready(function($){  						  
            		
            		$("#form_sample_2").submit(function(e) {
            		
            			var isError=false;
            			 var errMsg="";
            				
           				if(!$("#programTypeId").val()){
            					 
            				isError=true;
            				
            				
            				$("#programTypeId").addClass("has-error")
            				$("#error_formfield1").show()
            					//return false;
            				} else {
            					$("#error_formfield1").hide()
            				}
            				
            				            				
            				if(!$("#programTypeId").val()){
             					 
                				isError=true;
                			
                				
                				$("#programTypeId").addClass("has-error")
                				$("#error_formfield2").show()
                					//return false;
                				} else {
                					$("#error_formfield2").hide()
                				}
            				 
            				
            				
            				if($("#stud_appeared").val()<=0 || !$("#stud_appeared").val()){
             					 
                				isError=true;
                			
                				
                				$("#stud_appeared").addClass("has-error")
                				$("#error_formfield3").show()
                					//return false;
                				} else {
                					$("#error_formfield3").hide()
                				}
            				
            				if($("#stud_pass_per").val()<=0 || !$("#stud_pass_per").val()){
             					 
                				isError=true;
                			
                				
                				$("#stud_pass_per").addClass("has-error")
                				$("#error_formfield4").show()
                					//return false;
                				} else {
                					$("#error_formfield4").hide()
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
	<script type="text/javascript">
		function submit_f(view) {
			document.getElementById("is_view").value = view;//create this 

		}
	</script>

	<script type="text/javascript">
	
	function getPercent(){
		
		var appear = $("#stud_appeared").val();
		var passed = $("#stud_passed").val();
		var passPer = (passed*100)/appear;
		//alert(passPer);
		document.getElementById("stud_pass_per").value=passPer;
		
	}
	
	
		function showExtraField() {

			var qualType = document.getElementById("approvedBy").value
			if (qualType == 7) {

				document.getElementById("abc").style = "visible"
				document.getElementById("otherApprovedBy").required = true;

			} else {
				document.getElementById("abc").style = "display:none"
				document.getElementById("otherApprovedBy").required = false;
			}

		}

		function hideText() {
			var findOtherName = document.getElementById("findOtherName").value;

			if (findOtherName == 1) {
				document.getElementById("abc").style = "visible"
				document.getElementById("otherApprovedBy").required = true;
			} else {

				document.getElementById("abc").style = "display:none"
				document.getElementById("otherApprovedBy").required = false;
			}

		}
	</script>

<script type="text/javascript">
$(function () {
	 
    $('.datepicker').datepicker({
		autoclose: true,
        format: "dd-mm-yyyy",
        changeYear:true,
        changeMonth:true

	});
});
</script>

<script type="text/javascript">
		function getProgramTypeByProgram() {
			
			
			var programType = document.getElementById("programType").value;
			//alert("programType" + programType);

			var valid = true;

			if (programType == null || programType == "") {
				valid = false;
				alert("Please Select Program");
			}

			if (valid == true) {

				$.getJSON('${getProgramTypeByProgramId}', {
					programType : programType,
					ajax : 'true',
				},

				function(data) {
					//alert(data);
					var html;
					var len = data.length;
					for (var i = 0; i < len; i++) {

						html += '<option value="' + data[i].programId + '">'
								+ data[i].nameOfProgram + '</option>';

					}
					html += '</option>';

					$('#programTypeId').html(html);
					$("#programTypeId").trigger("chosen:updated");
					getStudAdmByProgType();
				});
			}//end of if
			
		}
		
		
	</script>
	
	<!-- END CONTAINER -->
	<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->

	



</body>
</html>