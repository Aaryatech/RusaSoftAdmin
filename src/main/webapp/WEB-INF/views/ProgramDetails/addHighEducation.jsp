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
<body class=" " onload="setProceedProgType()">
	<c:url value="/getProgTypeByProgId" var="getProgTypeByProgId"></c:url>
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
<%-- 							<h2 class="title pull-left">${title}</h2>
 --%>
							<div class="actions panel_actions pull-right">
								<a href="${pageContext.request.contextPath}/showHighEdu"><button
										type="button" class="btn btn-info">Back</button></a>
							</div>
						</header>
						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<ul class="nav nav-tabs">
										<li class="active"><a href="#home" data-toggle="tab">
												<i class="fa fa-home"></i> Register Form
										</a></li>
									</ul>
									<div class="tab-content">
										<div class="tab-pane fade in active" id="home">

											<form class="form-horizontal"
												action="${pageContext.request.contextPath}/insertHigherEduDetail"
												method="post" name="form_sample_2" id="form_sample_2"
												onsubmit="return checkBeforeSubmit()">

												<div class="row">
													<div class="col-md-12">
														<div class="form-group">
															<label class="control-label col-sm-2" for="status">Program
																Type<span class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<!--  fetch it from m_prog_type -->
																<select id="prog_type" name="prog_type"
																	class="form-control" onchange="setProceedProgType()"
																	required>
																	<c:forEach items="${progTypeList}" var="progType">
																		<c:choose>
																			<c:when
																				test="${highEduDet.programType==progType.programId}">
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
															<label class="control-label col-sm-2" for="status">Proceeding
																To<span class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<!--  fetch it from m_prog_type but the program higher than above selected prog only -->
																<select id="proceed_prog_type" name="proceed_prog_type"
																	class="form-control" required>
																</select>
															</div>
														</div>

														<div class="form-group">
															<label class="control-label col-sm-2" for="page_name">
																No. of Student<span class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<input type="number"  min="0" class="form-control"
																	id="no_of_student" value="${highEduDet.noStudent}"
																	name="no_of_student"
																	placeholder="Number Of Students Opted for Higher Education"
																	required>
															</div>
														</div>

														<div class="form-group">
															<div class="col-sm-offset-2 col-sm-10">
																<input type="submit" id="sub1" class="btn btn-primary"
																	onclick="submit_f(1)" value="Save"> <input
																	type="submit" id="sub2" class="btn btn-primary"
																	onclick="submit_f(0)"
																	value="Save &
																		Next">
																<button type="reset" class="btn btn-default">Reset</button>
															</div>
														</div>
													</div>
													<input type="hidden" id="high_edu_id" name="high_edu_id"
														value="${highEduDet.educationDetailId}"> <input
														type="hidden" id="is_view" name="is_view" value="0">
													<div class="clearfix"></div>
												</div>
											</form>
										</div>
									</div>
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

		/* function checkUnique(inputValue,valueType){
		 //alert(inputValue);
		
		 var primaryKey=${editInst.librarianId};
		 //alert("Primary key"+primaryKey);
		 var isEdit=0;
		 if(primaryKey>0){
		 isEdit=1;
		 }
		 //alert("Is Edit " +isEdit);
		
		 var valid=false;
		 if(valueType==1){
		 //alert("Its Mob no");
		 if(inputValue.length==10){
		 valid=true;
		 //alert("Len 10")
		 }else{
		 //alert("Not 10");
		 }
		 }
		 else if(valueType==2){
		 //alert("Its Email " );
		
		 var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
		 if(inputValue.match(mailformat))
		 {
		 valid=true;
		 //alert("Valid Email Id");
		 }
		 else
		 {
		 valid=false;
		 //alert("InValid Email Id");
		 }
		 }
		 if(valid==true)
		 $.getJSON('${checkUniqueField}', {
		
		 inputValue : inputValue,
		 valueType  : valueType,
		 primaryKey : primaryKey,
		 isEdit     : isEdit,
		 tableId : 4,

		 ajax : 'true',

		 }, function(data) {
		
		 //	alert("Data  " +JSON.stringify(data));
		 if(data.error==true){
		 if(valueType==2){
		
		
		 alert("This email id already exist in system please enter unique");
		 $('#librarian_email').val('');
		 //document.getElementById("stud_contact_no").value=" ";
		
		 }
		 else{
		
		
		 alert("This contact no  already exist in system please enter unique");
		 $('#lib_con_num').val('');
		 //document.getElementById("student_email").value=" ";
		 }
		 }
		 });
		 }

		 */
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

		function setProceedProgType() {
			//alert("progId" +progId);
			var progId = document.getElementById("prog_type").value;
			$.getJSON('${getProgTypeByProgId}', {
				progId : progId,
				ajax : 'true',
			}, function(data) {
				var html;
				var len = data.length;
				var x=${highEduDet.proceedingTo};
				for (var i = 0; i < len; i++) {
					if(data[i].programId==x){
						html += '<option selected value="' + data[i].programId + '">'
						+ data[i].programName + '</option>';
						
					}else{
					html += '<option value="' + data[i].programId + '">'
							+ data[i].programName + '</option>';
					}
				}
				html += '</option>';
				$('#proceed_prog_type').html(html);
				$("#proceed_prog_type").trigger("chosen:updated");
			});
			
			
			
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