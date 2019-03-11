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
								<a href="${pageContext.request.contextPath}/showStudList"><button
										type="button" class="btn btn-info">Back</button></a> <a
									class="box_toggle fa fa-chevron-down"></a>
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertStudent"
										method="post" 
										name="form_sample_2" id="form_sample_2"
										onsubmit="return confirm('Do you really want to submit the form?');">

										<ul class="nav nav-tabs">
											<li class="active"><a href="#home" data-toggle="tab">
													<i class="fa fa-home"></i> Register Form
											</a></li>


										</ul>

										<div class="tab-content">
											<div class="tab-pane fade in active" id="home">

												<div>



													<div class="col-xs-12">
														<div class="form-group">
															<label class="control-label col-sm-2" for="page_name">
																Name<span class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<input type="text" class="form-control" id="student_name"
																value="${editStudent.studentName}"
																	name="student_name" placeholder="Student Name"
																	>
															</div>
														</div>


												<div class="form-group">
													<label class="control-label col-sm-2" for="status">
													Year  <span class="text-danger">*</span>
												</label>
												<div class="col-sm-2">
													<select id="academic_year" name="academic_year" class="form-control" required>
																<%-- <c:forEach items="${acaYearList}" var="acaYear">
																		<option value="${acaYear.yearId}">${acaYear.academicYear}</option>
																	
																	</c:forEach> --%>
																	
																		<c:forEach items="${acaYearList}" var="acaYearList">
																		<c:choose>
																			<c:when test="${acaYearList.yearId==editStudent.acadamicYear}">
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
													<label class="control-label col-sm-2" for="status">
													Department  <span class="text-danger">*</span>
												</label>
												<div class="col-sm-2">
													<select id="stud_branch" name="stud_branch" class="form-control" required>
														
																	
																		<c:forEach items="${deptList}" var="dept">
																		<c:choose>
																			<c:when test="${dept.deptId==editStudent.deptId}">
																			<option selected value="${dept.deptId}">${dept.deptName}</option>

																			</c:when>
																			<c:otherwise>
																			<option value="${dept.deptId}">${dept.deptName}</option>

																			</c:otherwise>

																		</c:choose>

																	</c:forEach>

													</select>
                                       </div>
										</div>		
															
														<div class="form-group">
															<label class="control-label col-sm-2" for="page_order">ID No.
																 <span class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<input type="text" 
																	maxlength="10" class="form-control" id="id_number"
																	value="${editStudent.idNo}"
																	name="id_number" placeholder="ID Number" required>
															</div>
														</div>

														<div class="form-group">
															<label class="control-label col-sm-2" for="page_order">Contact
																No <span class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<input type="text" pattern="^[1-9]{1}[0-9]{9}$"
																	maxlength="10" class="form-control" id="stud_contact_no"
																	value="${editStudent.contactNo}" onchange="checkUnique(this.value,1)"
																	name="stud_contact_no" placeholder="Mobile No" required>
															</div>
														</div>

														<div class="form-group">
															<label class="control-label col-sm-2" for="page_order">Email ID(Official)
																<span class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<input type="email" class="form-control" id="student_email"
																value="${editStudent.email}" onchange="checkUnique(this.value,2)"
																	name="student_email" placeholder="abc@xyz.com" required>
															</div>
														</div>

											

													</div>

												</div>

                                              <input type="hidden" id="student_id" name="student_id" value="${editStudent.studentId}" >
                                             	<input type="hidden" id="is_view" name="is_view" value="0">
												
											  <div class="form-group">
															<div class="col-sm-offset-2 col-sm-10">
																<input type="submit" class="btn btn-primary" onclick="submit_f(1)" value="Add">
																<input type="submit" class="btn btn-primary" onclick="submit_f(0)" value="Save &
																		Next">
																<button type="reset" class="btn btn-default">Reset</button>
															</div>
														</div>


												<div class="clearfix"></div>

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
function submit_f(view){
		document.getElementById("is_view").value=view;//create this 
		/* var form=document.getElementById("form_sample_2");
	    form.setAttribute("method", "post");

		form.action=("insertLibrarian");
		var x =confirm();
		if(x==true)
		form.submit(); */
		
	}
	
function checkUnique(inputValue,valueType){
	//alert("hii");
	
	var primaryKey=${editStudent.studentId};
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
		tableId : 3,

		ajax : 'true',

	}, function(data) {
		
	//	alert("Data  " +JSON.stringify(data));
		if(data.error==true){
			if(valueType==2){
			
			
			alert("This email id already exist in system please enter unique");
			$('#student_email').val('');
			//document.getElementById("stud_contact_no").value=" ";
			
			}
			else{
				
				
				alert("This contact no  already exist in system please enter unique");
				$('#stud_contact_no').val('');
				//document.getElementById("student_email").value=" ";
			}
		}
	});
}

</script>





	<!-- END CONTAINER -->
	<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->

	<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>



</body>
</html>