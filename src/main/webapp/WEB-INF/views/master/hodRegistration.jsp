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

				<!-- 	<div class="col-xs-12">
					<div class="page-title">

						<div class="pull-left">
							PAGE HEADING TAG - START
							<h1 class="title">HOD Registration</h1>
							PAGE HEADING TAG - END
						</div>


					</div>
				</div> -->


				<div class="clearfix"></div>
				<!-- MAIN CONTENT AREA STARTS -->




				<div class="col-lg-12">
					<section class="box ">

						<header class="panel_header">

							<h2 class="title pull-left">${title}</h2>
							<br />
<!-- 							<h3 class="title pull-left">Institute Name:abc</h3>
 -->

							<div class="actions panel_actions pull-right">

								
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									

										<!-- <ul class="nav nav-tabs">
											<li class="active"><a href="#home" data-toggle="tab">
													<i class="fa fa-home"></i> Register HOD
											</a></li>

										</ul>

										<div class="tab-content">
											<div class="tab-pane fade in active" id="home"> -->
<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertHod"
										method="post" name="form_sample_2" id="form_sample_2">
													<div class="row">
													<div class="col-md-12">

														<div class="form-group">
															<label class="control-label col-sm-3" for="page_name">
																Department<span class="text-danger">*</span>
															</label>
															<div class="col-sm-9">
																<select id="hod_dept_id" name="hod_dept_id"
																	class="form-control">
																	<c:forEach items="${deptList}" var="dept">
																		<c:choose>
																			<c:when test="${hod.deptId==dept.deptId}">
																				<option selected value="${dept.deptId}">${dept.deptName}</option>

																			</c:when>
																			<c:otherwise>
																				<option value="${dept.deptId}">${dept.deptName}</option>

																			</c:otherwise>

																		</c:choose>

																	</c:forEach>

																</select>
																<span
															class="error_form text-danger" id="hod_dept_id_field"
															style="display: none;">Please select department
															name</span>
															</div>
														</div>


														<div class="form-group">
															<label class="control-label col-sm-3" for="page_name">HOD
																Name<span class="text-danger">*</span>
															</label>
															<div class="col-sm-9">
																<input type="text" class="form-control" id="hod_name" onchange="trim(this)" maxlength="100"
																	value="${hod.hodName}" name="hod_name"
																	placeholder="Head of Department Name">
																	<span
															class="error_form text-danger" id="hod_name_field"
															style="display: none;">Please enter hod
															name</span>
															</div>
														</div>



														<div class="form-group">
															<label class="control-label col-sm-3" for="status">Highest
																Qualification<span class="text-danger">*</span>
															</label>
															<div class="col-sm-9">
																<select id="hod_quolf" name="hod_quolf"
																	class="form-control">
																	<c:forEach items="${quolfList}" var="quolf">
																		<c:choose>
																			<c:when test="${hod.highestQualificationId==quolf.qualificationId}">
																				<option selected value="${quolf.qualificationId}">${quolf.qualificationName}</option>

																			</c:when>
																			<c:otherwise>

																				<option value="${quolf.qualificationId}">${quolf.qualificationName}</option>

																			</c:otherwise>

																		</c:choose>

																	</c:forEach>

																</select>
																<span
															class="error_form text-danger" id="hod_quolf_field"
															style="display: none;">Please select
															highest qualification</span>
															</div>
														</div>

														

														<div class="form-group">
															<label class="control-label col-sm-3" for="smallheading">Contact
																No.<span class="text-danger">*</span>
															</label>
															<div class="col-sm-9">
																<input type="text" maxlength="10" class="form-control" id="hod_mob" value="${hod.contactNo}"
																	name="hod_mob"  oninput="checkUnique(this.value,1)"
																	placeholder="Mobile Number">
																	<span
															class="error_form text-danger" id="hod_mob_field"
															style="display: none;">Please enter hod mobile no</span>
															</div>
														</div>

														<div class="form-group">
															<label class="control-label col-sm-3" for="hod_email">Email
																ID(Official) : <span class="text-danger">*</span>
															</label>
															<div class="col-sm-9">
																<input type="text"  maxlength="50" class="form-control" id="hod_email"  oninput="checkUnique(this.value,2)"
																	name="hod_email" placeholder="abc@xyz.com" value="${hod.email}"
																	>
																	<span
															class="error_form text-danger" id="hod_email_field"
															style="display: none;">Please enter hod email id</span>
															</div>
														</div>

														<div class="form-group">
															<div class="col-sm-offset-3 col-sm-9">
															
															
															<button type="submit" id="sub1" class="btn btn-primary" onclick="submit_f(1)"><i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save</button>
														<a href="${pageContext.request.contextPath}/hodList"><button type="button" class="btn btn-primary"><i class="${sessionScope.cancelIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Cancel</button></a>


															<%-- 	<input type="submit" id="sub1" class="btn btn-primary" onclick="submit_f(1)" value="Save">
																<a href="${pageContext.request.contextPath}/hodList"><button
										type="button" id="sub2" class="btn btn-info">Cancel</button></a> --%>
																<!-- <input type="submit" style="display: none"  id="sub2" class="btn btn-primary" onclick="submit_f(0)" value="Save &
																		Next"> -->
																<%-- <a href="${pageContext.request.contextPath}/hodList"><button
																		type="button" class="btn btn-primary">S</button></a> --%>
															</div>
														</div>

													</div>
													<input type="hidden" id="hod_id" name="hod_id" value="${hod.hodId}">
													<input type="hidden" id="is_view" name="is_view" value="0">
													
													


													<div class="clearfix"></div>

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
						
<!-- 
					</section>

				</div>
 -->

				<!-- MAIN CONTENT AREA ENDS -->
		<!-- 	</section>
		</section> -->
		<!-- END CONTENT -->



	<!-- </div> -->
	<!-- END CONTAINER -->
	<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->

	<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
	
	<script>
		function validateEmail(email) {
			var eml = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
			if (eml.test($.trim(email)) == false) {
				return false;
			}
			return true;
		}
		function validateMobile(mobile) {
			var mob = /^[1-9]{1}[0-9]{9}$/;
			if (mob.test($.trim(mobile)) == false) {
				return false;
			}
			return true;
		}
		$(document)
				.ready(
						function($) {

							$("#form_sample_2")
									.submit(
											function(e) {
												var isError = false;
												var errMsg = "";

												if (!$("#hod_dept_id").val()) {
													isError = true;

													$("#hod_dept_id").addClass(
															"has-error")
													$("#hod_dept_id_field")
															.show()
												} else {
													$("#hod_dept_id_field")
															.hide()
												}

												if (!$("#hod_name").val()) {
													isError = true;

													$("#hod_name").addClass(
															"has-error")
													$("#hod_name_field")
															.show()
												} else {
													$("#hod_name_field")
															.hide()
												}

												if (!$("#hod_quolf").val()) {
													isError = true;

													$("#hod_quolf").addClass(
															"has-error")
													$("#hod_quolf_field").show()
												} else {
													$("#hod_quolf_field").hide()
												}

												if (!$("#hod_mob").val()
														|| !validateMobile($(
																"#hod_mob")
																.val())) {
													isError = true;
													$("#hod_mob")
															.addClass(
																	"has-error")
													$("#hod_mob_field")
															.show()
												} else {
													$("#hod_mob_field")
															.hide()
												}

												if (!$("#hod_email").val()
														|| !validateEmail($(
																"#hod_email")
																.val())) {
													isError = true;
													$("#hod_email").addClass(
															"has-error")
													$("#hod_email_field")
															.show()
												} else {
													$("#hod_email_field")
															.hide()
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
	
	function checkUnique(inputValue,valueType){
    	//alert(inputValue);
    	
    	var primaryKey=${hod.hodId};
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
    		tableId : 2,

			ajax : 'true',

		}, function(data) {
			
		//	alert("Data  " +JSON.stringify(data));
			if(data.error==true){
				if(valueType==2){
					document.getElementById("hod_email").value="";

				alert("This email id already exist in system please enter unique");
				}
				else{
					document.getElementById("hod_mob").value="";

					alert("This contact no  already exist in system please enter unique");
				}
			}
		});
    }
	
	function submit_f(view){
		document.getElementById("is_view").value=view;//create this 
		/* var form=document.getElementById("form_sample_2");
	    form.setAttribute("method", "post");

		form.action=("insertHod");
		var x =confirm("Do you really want to submit the form?");
		if(x==true)
		form.submit(); */
		
	}
	
	
	/* function showForm() {
		//document.getElementById("abc").style = "display:none"
			var qualType=document.getElementById("qualification").value
		//alert("qualType::"+qualType);
			
			if (qualType == 5) {

				document.getElementById("abc").style = "visible"
				
					
			} 
			else{
				document.getElementById("abc").style = "display:none"
			}
		
		} */
	/* function hideText() {
		//alert("hii");
		document.getElementById("abc").style = "display:none"
			
		
		} */
		
	</script>
	 <script type="text/javascript">
  var wasSubmitted = false;    
    function checkBeforeSubmit(){
      if(!wasSubmitted) {
    	  var x=confirm("Do you really want to submit the form?");
    	  if(x==true){
        wasSubmitted = true;
    	  document.getElementById("sub1").disabled=true;
    	  document.getElementById("sub2").disabled=true;

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

</body>
</html>