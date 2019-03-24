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
	<c:url value="/chkFields" var="chkFields"></c:url>
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
								<a href="${pageContext.request.contextPath}/showIqacList"><button
										type="button" class="btn btn-info">Back</button></a>
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/iqacNewRegistration"
										method="post" name="form_sample_2" id="form_sample_2"
										onsubmit="return checkBeforeSubmit()">



										<input type="hidden" id="iqac_id" name="iqac_id"
											value="${miqc.iqacId}">
										<div class="form-group">
											<label class="control-label col-sm-2" for="page_name">
												Name<span class="text-danger">*</span>
											</label>
											<div class="col-sm-10">
												<input type="text" class="form-control" id="iqacName"
													autocomplete="off" value="${miqc.iqacName}" name="iqacName"
													pattern="^(?!\s*$).+"
													placeholder="Internal Quality Assurance Cell (IQAC)"
													required>
											</div>
										</div>





										<div class="form-group">
											<label class="control-label col-sm-2" for="page_order">Designation
												<span class="text-danger">*</span>
											</label>
											<div class="col-sm-10">
												<select id="designation" name="designation"
													class="form-control" onchange="showForm()" required>

													<c:forEach items="${desigList}" var="makeList">
														<c:choose>
															<c:when
																test="${makeList.designationId == miqc.desgntnId}">
																<option value="${makeList.designationId}"
																	selected="selected">${makeList.designationName}</option>
															</c:when>
															<c:otherwise>
																<option value="${makeList.designationId}">${makeList.designationName}</option>
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
												<input type="text" class="form-control datepicker"
													id="dateOfJoin"
													onkeypress='return restrictAlphabets(event)'
													value="${miqc.joiningDate}" name="dateOfJoin"
													autocomplete="off" placeholder="dd/mm/yyyy"
													pattern="^(?!\s*$).+" required>
											</div>
										</div>

										<div class="form-group">
											<label class="control-label col-sm-2" for="page_order">Contact
												No. <span class="text-danger">*</span>
											</label>
											<div class="col-sm-10">
												<input type="text" class="form-control" id="contactNo"
													onchange="checkUnique(this.value,1)" pattern="^(?!\s*$).+"
													name="contactNo" pattern="[7-9]{1}[0-9]{9}" maxlength="10"
													onkeypress='return restrictAlphabets(event)'
													autocomplete="off"
													title="Phone number with 7-9 and remaing 9 digit with 0-9"
													placeholder="Mobile Number" value="${miqc.contactNo}"
													required>
												<p class="desc text-danger fontsize11">Note: OTP will be
													sent on this mobile number for verification</p>
											</div>
										</div>


										<div class="form-group">
											<label class="control-label col-sm-2" for="smallheading">Email
												ID(Official) <span class="text-danger">*</span>
											</label>
											<div class="col-sm-10">
												<input type="email" class="form-control" id="email"
													autocomplete="off" onchange="checkUnique(this.value,2)"
													pattern="^(?!\s*$).+" name="email"
													placeholder="abc@xyz.com" value="${miqc.email}" required>
												<p class="desc font-italic fontsize11">Note:
													Verification mail will be sent on this Email id</p>
											</div>

										</div>



										<div class="form-group">
											<div class="col-sm-offset-2 col-sm-10">
												<input type="submit" id="sub1" class="btn btn-primary"
													onclick="submit_f(1)" value="Save"> <input
													type="submit" id="sub2" class="btn btn-primary"
													onclick="submit_f(0)" value="Save &
																		Next">
												<button type="reset" class="btn btn-default">Reset</button>
												<input type="hidden" id="is_view" name="is_view" value="0">
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

	<script type="text/javascript">
		$(function() {

			$('.datepicker').datepicker({
				autoclose : true,
				format : "dd-mm-yyyy",
				changeYear : true,
				changeMonth : true

			});
		});

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
		function showDiv(value) {

			if (value == 0) {
				//alert(value);
				document.getElementById("abc").style.display = "block";
			} else {
				//alert(value);
				document.getElementById("abc").style.display = "none";
			}
		}

		function submit_f(view) {
			document.getElementById("is_view").value = view;//create this 

		}
	</script>
	<script type="text/javascript">
		function setDate(value) {
			//alert("Value " +value)
			if (value == 1) {
				//alert(value)
				document.getElementById("acc_off_relDate").removeAttribute(
						"required");
				document.getElementById("abc").style.display = "none";

				//alert(value)
			} else {
				//alert(value)
				document.getElementById("acc_off_relDate").setAttribute(
						"required", "true");
				document.getElementById("abc").style.display = "block";

				//alert(value)

			}

		}
	</script>
	<script type="text/javascript">
		function showIsReg() {
			//alert("Hi");
			var x = $
			{
				accOff.officerId
			}

			if (x > 0) {
				//alert("Hi 1")
				var isRel = $
				{
					accOff.realivingDate
				}
				;
				//alert("Is Reg " +isReg);
				if (isRel == null) {
					//alert("Hi 2")
					document.getElementById("abc").style.display = "none";

				} else {
					//alert("Hi es")
					document.getElementById("abc").style.display = "block";

				}

			}

		}
	</script>
	<script type="text/javascript">
		function checkUnique(inputValue, valueType) {
			
			document.getElementById("sub1").disabled = false;
			document.getElementById("sub2").disabled = false;

			//alert(inputValue+" "+valueType);

			var primaryKey = ${miqc.iqacId}	;
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
								'${chkFields}',
								{

									inputValue : inputValue,
									valueType : valueType,
									primaryKey : primaryKey,
									isEdit : isEdit,
									tableId : 1,

									ajax : 'true',

								},
								function(data) {

									//alert("Data  " +JSON.stringify(data));
									if (data.error == true) {
										if (valueType == 2) {
											document.getElementById("email").value = "";
											alert("This email id already exist in system please enter unique");
										} else {
											document
													.getElementById("contactNo").value = "";

											alert("This contact no  already exist in system please enter unique");
										}
									}
								});
		}

		function submit_f(view) {
			document.getElementById("is_view").value = view;//create this 
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
	<!-- <script type="text/javascript">
	
	
	function checkUnique(inputValue,valueType){
    	//alert(inputValue);
    				document.getElementById("sub1").disabled=false;
					document.getElementById("sub2").disabled=false;
    	
    	var primaryKey=${accOff.officerId};
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
    		tableId : 5,

			ajax : 'true',

		}, function(data) {
			
		//	alert("Data  " +JSON.stringify(data));
			if(data.error==true){
				if(valueType==2){
					document.getElementById("acc_off_email").value="";

				alert("This email id already exist in system please enter unique");
				
					document.getElementById("sub1").disabled=true;
					document.getElementById("sub2").disabled=true;
				
				}
				else{
					document.getElementById("acc_off_mob").value="";

					alert("This contact no  already exist in system please enter unique");
					
					document.getElementById("sub1").disabled=true;
					document.getElementById("sub2").disabled=true;
					
				}
			}
		});
    }
	
	</script>
 -->
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



	<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>



</body>
</html>