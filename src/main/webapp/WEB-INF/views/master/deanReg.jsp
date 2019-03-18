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
<body class=" " onload="showIsReg()">
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
								<a href="${pageContext.request.contextPath}/showDeanList"><button
										type="button" class="btn btn-info">Back</button></a> <a
									class="box_toggle fa fa-chevron-down"></a>
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertNewDean"
										method="post"
										name="form_sample_2" id="form_sample_2"
										onsubmit="return checkBeforeSubmit()">

										<ul class="nav nav-tabs">
											<li class="active"><a href="#home" data-toggle="tab">
													<i class="fa fa-home"></i> Dean Register Form
											</a></li>


										</ul>

										<div class="tab-content">
											<div class="tab-pane fade in active" id="home">

												<div>

												<input type="hidden" class="form-control" id="dean_id"
																	name="dean_id" placeholder="Dean R&D Name"
																	value="${dean.deanId}">

													<div class="col-xs-12">
													
														<div class="form-group">
														
															<label class="control-label col-sm-2" for="page_name">
																Name<span class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<input type="text" class="form-control" id="dean_name"
																	name="dean_name" placeholder="Dean R&D Name"
																	value="${dean.deanName}">
															</div>
														</div>



														

														<div class="form-group">
															<label class="control-label col-sm-2" for="page_order">Contact
																No <span class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<input type="text" pattern="^[1-9]{1}[0-9]{9}$" 
																	maxlength="10" class="form-control" id="contact_no" onchange="checkUnique(this.value,1)"
																	name="contact_no" placeholder="Mobile No" required value="${dean.contactNo}">
																	<p class="desc text-danger fontsize11">Note: OTP
																	will be sent on this mobile number for verification</p>
															</div>
														</div>

														<div class="form-group">
															<label class="control-label col-sm-2" for="page_order">Email ID(Official)
																<span class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<input type="email" class="form-control" id="email" onchange="checkUnique(this.value,2)"
																	name="email" placeholder="abc@xyz.com" required value="${dean.email}">
																	<p class="desc font-italic fontsize11">Note:
																		Verification mail will be sent on this Email id</p>
															</div>
														</div>

													<div class="form-group">
															<label class="control-label col-sm-2" for="status">Qualification : 
															<span class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<select id="hod_quolf" name="hod_quolf"
																	class="form-control" required>
																	<c:forEach items="${quolfList}" var="quolf">
																		<c:choose>
																			<c:when test="${dean.qualificationId==quolf.qualificationId}">
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

														
																
														<div class="form-group">
															<label class="control-label col-sm-2" for="page_order">Joining
																Date <span class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<input type="text" class="form-control datepicker" id="join_date"
																	name="join_date" placeholder="Joining Date" required value="${dean.joiningDate}">
															</div>
															<label class="control-label col-sm-3" for="planning"
																style="text-align: left;">Is Currently Working<span class="text-danger">*</span>
															</label>
															<div class="col-sm-2">
																<c:choose>
																	<c:when test="${dean.deanId==0}">

																		<input type="radio" id="is_registration"
																			name="is_registration" value="1" checked
																			onclick="setDate(this.value)">Yes 
																<input type="radio" id="is_registration"
																			name="is_registration" value="0"
																			onclick="setDate(this.value)">No 
															
															</c:when>
																	<c:otherwise>

																		<c:choose>
																			<c:when test="${empty dean.realivingDate}">
																				<input type="radio" id="is_registration"
																					name="is_registration" value="1" checked
																					onclick="setDate(this.value)">Yes  
																<input type="radio" id="is_registration"
																					name="is_registration" value="0"
																					onclick="setDate(this.value)">No 
															
																
																</c:when>
																			<c:otherwise>
																				<input type="radio" id="is_registration"
																					name="is_registration" value="1" 
																					onclick="setDate(this.value)">Yes
																<input type="radio" id="is_registration" checked
																					name="is_registration" value="0"
																					onclick="setDate(this.value)">No
															
																
																</c:otherwise>

																		</c:choose>

																	</c:otherwise>


																</c:choose>

															</div>
														</div>
													
													
														<div class="form-group" id="abc" style="display: none">
															<label class="control-label col-sm-2" for="page_order">Relieving
																Date <span class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<input type="text" class="form-control datepicker" id="acc_off_relDate"
																	name="acc_off_relDate" placeholder="Relieving Date" value="${dean.realivingDate}">
															</div>
														</div>

													</div>

												</div>


											  <div class="form-group">
											  <div class="col-sm-offset-2 col-sm-10">
																<input type="submit" id="sub1" class="btn btn-primary" onclick="submit_f(1)" value="Save">
																<input type="submit" id="sub2" class="btn btn-primary" onclick="submit_f(0)" value="Save &
																		Next">
																<button type="reset" class="btn btn-default">Reset</button>
															</div>
															<%-- <div class="col-sm-offset-2 col-sm-10">
																<button type="submit" class="btn btn-primary">Add</button>
																	<a href="${pageContext.request.contextPath}/showDeanList"><button
										                              type="button" class="btn btn-primary">Save & Next</button></a>
																<button type="reset" class="btn btn-default">Reset</button>
															</div> --%>
														</div>
													<input type="hidden" id="acc_off_id" name="acc_off_id" value="${dean.deanId}">
													<input type="hidden" id="is_view" name="is_view" value="0">
													
													



												<div class="clearfix"></div>

											</div>

										</div>

									</form>
									<p class="desc text-danger fontsize11">Notice : * Fields
										are mendatory.</p>
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

	<!-- END CONTAINER -->
	<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->

	<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
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
<script type="text/javascript">
$(function () {
	 
    $('.datepicker').datepicker({
		autoclose: true,
        format: "dd-mm-yyyy",
        changeYear:true,
        changeMonth:true

	});
});

	function showDiv(value) {

		if (value == 0) {
			//alert(value);
			document.getElementById("abc").style.display = "block";
		} else {
			//alert(value);
			document.getElementById("abc").style.display = "none";
		}
	}

	
	
	function submit_f(view){
		document.getElementById("is_view").value=view;//create this 
		
	}
	
	

	</script>
<script type="text/javascript">
	function setDate(value){
		//alert("Value " +value)
		if(value==1){
		//alert(value)
		document.getElementById("acc_off_relDate").removeAttribute("required");
		document.getElementById("abc").style.display = "none";

		//alert(value)
		}else{
			//alert(value)
			document.getElementById("acc_off_relDate").setAttribute("required","true");
			document.getElementById("abc").style.display = "block";

			//alert(value)

		}
		
	}
	
	</script>
<script type="text/javascript">

	function checkUnique(inputValue,valueType){
		document.getElementById("sub1").disabled=false;
		document.getElementById("sub2").disabled=false;

		
		//alert(inputValue+" "+valueType);
    	
    	var primaryKey=${dean.deanId};
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
    	$.getJSON('${chkFields}', {
    		
    		inputValue : inputValue,
    		valueType  : valueType,
    		primaryKey : primaryKey,
    		isEdit     : isEdit,
    		tableId : 3,

			ajax : 'true',

		}, function(data) {
			
		alert("Data  " +JSON.stringify(data));
			if(data.error==true){
				if(valueType==2){
				alert("This email id already exist in system please enter unique");
				}
				else{
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
	
		<script>
		
	$("#contactNo").on("keypress keyup blur",function (event) {
        //this.value = this.value.replace(/[^0-9\.]/g,'');
 $(this).val($(this).val().replace(/[^0-9\.]/g,''));
        if ((event.which != 46 || $(this).val().indexOf('.') != -1) && (event.which < 48 || event.which > 57)) {
            event.preventDefault();
        }
    });
	
	$("#dateOfJoin").on("keypress keyup blur",function (event) {
        //this.value = this.value.replace(/[^0-9\.]/g,'');
 $(this).val($(this).val().replace(/[^0-9\.]/g,''));
        if ((event.which != 46 || $(this).val().indexOf('.') != -1) && (event.which < 48 || event.which > 57)) {
            event.preventDefault();
        }
    });
	</script>
	
	<script type="text/javascript">
	
	function showIsReg(){
		//alert("Hi");
		var x=${dean.deanId}
	
		if(x>0){
			//alert("Hi 1")
		var isRel=${dean.realivingDate};
		//alert("Is Reg " +isReg);
		if(isRel==null){
			//alert("Hi 2")
			document.getElementById("abc").style.display = "none";

		}else{
			//alert("Hi es")
			document.getElementById("abc").style.display = "block";
			
		}
			
		}
	
	}
	</script>


</body>
</html>