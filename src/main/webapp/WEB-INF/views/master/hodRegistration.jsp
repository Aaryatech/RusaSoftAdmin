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

							<h2 class="title pull-left">HOD Registration</h2>
							<br />
<!-- 							<h3 class="title pull-left">Institute Name:abc</h3>
 -->

							<div class="actions panel_actions pull-right">

								<a href="${pageContext.request.contextPath}/hodList"><button
										type="button" class="btn btn-info">Back</button></a>
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									

										<ul class="nav nav-tabs">
											<li class="active"><a href="#home" data-toggle="tab">
													<i class="fa fa-home"></i> Register HOD
											</a></li>

										</ul>

										<div class="tab-content">
											<div class="tab-pane fade in active" id="home">
<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertHod"
										method="post" name="form_sample_2" id="form_sample_2"
										onsubmit="return checkBeforeSubmit()">
													<div class="row">
													<div class="col-md-12">


												

														<!-- <div class="form-group">
															<label class="control-label col-sm-2" for="status">Select
																Department : <span class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<select id="deptId" name="deptId" class="form-control"
																	required>
																	<option value="0">Information Technology</option>
																	<option value="1">Computer</option>


																</select>
															</div>
														</div> -->




														<div class="form-group">
															<label class="control-label col-sm-3" for="page_name">
																Department :<span class="text-danger">*</span>
															</label>
															<div class="col-sm-9">
																<select id="hod_dept_id" name="hod_dept_id"
																	class="form-control" required>
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
															</div>
														</div>


														<div class="form-group">
															<label class="control-label col-sm-3" for="page_name">HOD
																Name :<span class="text-danger">*</span>
															</label>
															<div class="col-sm-9">
																<input type="text" class="form-control" id="hod_name" maxlength="100"
																	value="${hod.hodName}" name="hod_name"
																	placeholder="HOD Name" required>
															</div>
														</div>



														<div class="form-group">
															<label class="control-label col-sm-3" for="status">Highest
																Qualification : <span class="text-danger">*</span>
															</label>
															<div class="col-sm-9">
																<select id="hod_quolf" name="hod_quolf"
																	class="form-control" required>
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
															</div>
														</div>

														<!-- <div class="form-group" id="abc">
															<label class="control-label col-sm-2" for="smallheading">Other
																Course <span class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<input type="text" class="form-control" id="desn"
																	name="desn" placeholder="Other Designation" value=""
																	>
															</div>
														</div> -->

														<div class="form-group">
															<label class="control-label col-sm-3" for="smallheading">Contact
																No. : <span class="text-danger">*</span>
															</label>
															<div class="col-sm-9">
																<input type="text" maxlength="10" class="form-control" id="hod_mob" value="${hod.contactNo}"
																	name="hod_mob" pattern="^[1-9]{1}[0-9]{9}$" oninput="checkUnique(this.value,1)"
																	
																	placeholder="Mobile Number" value="" required>
															</div>
														</div>

														<div class="form-group">
															<label class="control-label col-sm-3 for="smallheading">Email
																ID(Official) : <span class="text-danger">*</span>
															</label>
															<div class="col-sm-9">
																<input type="email" class="form-control" id="hod_email"  oninput="checkUnique(this.value,2)"
																	name="hod_email" placeholder="abc@xyz.com" value="${hod.email}"
																	required>
															</div>
														</div>

														<div class="form-group">
															<div class="col-sm-offset-3 col-sm-9">
																<input type="submit" id="sub1" class="btn btn-primary" onclick="submit_f(1)" value="Save">
																<input type="submit"  id="sub2" class="btn btn-primary" onclick="submit_f(0)" value="Save &
																		Next">
																<%-- <a href="${pageContext.request.contextPath}/hodList"><button
																		type="button" class="btn btn-primary">S</button></a> --%>
																<button type="reset" class="btn btn-default">Reset</button>
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
</script>

</body>
</html>