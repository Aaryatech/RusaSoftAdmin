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
<body class=" " onload="check(${facPerDetail.isAddSame})">
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
				<%-- 
				<div class="col-xs-12">
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
								<a href="${pageContext.request.contextPath}/showPersonalDetails"><button
										type="button" class="btn btn-info">Back</button></a>
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">

									<ul class="nav nav-tabs">
										<li class="active"><a href="#home" data-toggle="tab">
												<i class="fa fa-home"></i> Register
										</a></li>

									</ul>

									<div class="tab-content">
										<div class="tab-pane fade in active" id="home">

											<form class="form-horizontal"
												action="${pageContext.request.contextPath}/insertFacPersonalDetail"
												method="post" name="form_sample_2" id="form_sample_2"
												onsubmit="return checkBeforeSubmit()">
												<div class="col-md-12"></div>

												<div class="form-group">
													<label class="control-label col-sm-3" for="fac_name">
														Name<span class="text-danger">*</span>
													</label>
													<div class="col-sm-9">
														<input type="text" class="form-control" id="fac_name"
															name="fac_name" placeholder="Last Name Middle First Name"
															value="${staff.facultyFirstName}" required>
													</div>
												</div>

												<div class="form-group">
													<label class="control-label col-sm-3" for="fac_address">Address
														<span class="text-danger">*</span>
													</label>
													<div class="col-sm-9">
														<input type="text" class="form-control" id="fac_address"
															name="fac_address" placeholder="Permanent Address"
															required value="${facPerDetail.fAddress}">
													</div>
												</div>

												<div class="form-group">
													<div class="col-sm-2"></div>
													<label class="control-label col-sm-6" for="is_add_same">Is
														Permanent and Correspondence Address Same <span
														class="text-danger">*</span>
													</label>

													<div class="col-sm-3">
													<c:choose>
													<c:when test="${facPerDetail.isAddSame==1}">
													Yes <input type="radio" name="is_add_same"
															id="is_add_same" checked value="1"
															onclick="check(this.value)"> No<input
															type="radio" onclick="check(this.value)"
															name="is_add_same" id="is_add_same" value="0">
													
													</c:when>
													<c:otherwise>
													
													Yes <input type="radio" name="is_add_same"
															id="is_add_same"  value="1"
															onclick="check(this.value)"> No<input
															type="radio" onclick="check(this.value)" checked
															name="is_add_same" id="is_add_same" value="0">
													
													</c:otherwise>
													
													</c:choose>
														
													</div>
												</div>

												<div class="form-group" id="abc" style="display:none">
													<label class="control-label col-sm-3" for="fac_address2">Correspondence
														Address <span class="text-danger">*</span>
													</label>
													<div class="col-sm-9">
														<input type="text" class="form-control" id="fac_address2" value="${facPerDetail.fAddress2}"
															name="fac_address2" placeholder="Correspondence Address">
													</div>
												</div>

												<div class="form-group">
													<label class="control-label col-sm-3" for="fac_mob">Mobile
														No<span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="fac_mob"
															name="fac_mob"pattern="^[1-9]{1}[0-9]{9}$"
																	maxlength="10"
															title="Phone number with 7-9 and remaing 9 digit with 0-9"
															placeholder="Mobile No" value="${staff.contactNo}"
															required>
													</div>
													<div class="col-sm-2"></div>
												</div>
												<div class="form-group">
													<label class="control-label col-sm-3" for="f_phone">Phone
														No<span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="f_phone"
															name="f_phone"
															
															placeholder="Office Landline No" value="${facPerDetail.fPhone}" required>
													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-sm-3" for="f_resident">Resident
														No</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="f_resident"
															name="f_resident"  
															title="Phone number with 7-9 and remaing 9 digit with 0-9"
															placeholder="Resident Phone No"value="${facPerDetail.fResident}" required>
													</div>
													<div class="col-sm-2"></div>
												</div>
												<div class="form-group">
													<label class="control-label col-sm-3" for="f_email">Email
														ID<span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="f_email"
															name="f_email"
															
															placeholder="abc@xyz.com" value="${staff.email}" required>
													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-sm-3" for="f_aadhar">Aadhar
														No<span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="Text" class="form-control" id="f_aadhar"
															name="f_aadhar" placeholder="Aadhar No" value="${facPerDetail.fAadhar}" required>
													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-sm-3" for="f_designation">Select
														Designation <span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<select id="f_designation" name="f_designation"
															 class="form-control" required>
															<c:forEach items="${desigList}" var="des">
																<c:choose>
																	<c:when
																		test="${staff.currentDesignationId==des.designationId}">
																		<option selected value="${des.designationId}">${des.designationName}</option>
																	</c:when>
																	<c:otherwise>
																		<option value="${des.designationId}">${des.designationName}</option>
																	</c:otherwise>
																</c:choose>
															</c:forEach>
														</select>
													</div>
													<div class="col-sm-2"></div>
												</div>
												<!-- <div class="form-group" id="xyz">
													<label class="control-label col-sm-2" for="smallheading">Other
														Designation<span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="prevExp"
															name="prevExp" placeholder="Other Designation" value=""
															>
													</div>
												</div>
 -->
												<div class="form-group">
													<label class="control-label col-sm-3" for="f_dob">Date
														of Birth<span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="text" class="form-control datepicker"
															id="f_dob" name="f_dob" placeholder="Enter Date Of Birth"  value="${facPerDetail.fDob}"
															required>
													</div>
												</div>

												<div class="form-group">
													<label class="control-label col-sm-3" for="f_doj">Date
														of Joining <span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="text" class="form-control datepicker"
															id="f_doj" name="f_doj"
															
															placeholder="Date of Joining" value="${staff.joiningDate}" required>
													</div>
													<div class="col-sm-2"></div>
												</div>
												<div class="form-group">
													<label class="control-label col-sm-3" for="f_prevExp">Previous
														Experience <span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="f_prevExp"
															name="f_prevExp" placeholder="Previous Experience"
															onchange="calExp()" value="${facPerDetail.fPastExp}" required>
													</div>
												</div>
												
												<div class="form-group">
													<label class="control-label col-sm-3" for="f_gender">Gender<span class="text-danger">*</span>
													</label>
													<div class="col-sm-9">
													<c:choose>
													<c:when test="${facPerDetail.fGender==0}">
													Female<input type="radio" name="f_gender"
															id="f_gender"  value="1"
															>&nbsp;&nbsp;&nbsp; Male<input
															type="radio" checked
															name="f_gender" id="f_gender" value="0">&nbsp;&nbsp;&nbsp;
															Transgender<input
															type="radio"
															name="f_gender" id="f_gender" value="2">
													</c:when>
													
													<c:when test="${facPerDetail.fGender==1}">
													Female<input type="radio" name="f_gender"
															id="f_gender" checked value="1"
															>&nbsp;&nbsp;&nbsp; Male<input
															type="radio"  
															name="f_gender" id="f_gender" value="0">&nbsp;&nbsp;&nbsp;
															Transgender<input
															type="radio"
															name="f_gender" id="f_gender" value="2">
													</c:when>
													<c:otherwise>
													Female<input type="radio" name="f_gender"
															id="f_gender"  value="1"
															>&nbsp;&nbsp;&nbsp; Male<input
															type="radio" 
															name="f_gender" id="f_gender" value="0">&nbsp;&nbsp;&nbsp;
															Transgender<input
															type="radio" checked
															name="f_gender" id="f_gender" value="2">
													</c:otherwise>
													
													</c:choose>
														
													</div>
												</div>
												<!-- 
												<div class="form-group">
													<label class="control-label col-sm-3" for="smallheading">Total
														Experience <span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
															<input type="text" class="form-control" id="curExp"
																	name="curExp" placeholder="Current Experience" value=""
																	required>
														<label class="control-label col-sm-2" for="smallheading">10
														</label>
													</div>
												</div> -->
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
													<input type="hidden" id="staff_id" name="staff_id"
														value="${staff.facultyId}"> <input
														type="hidden" id="is_view" name="is_view" value="0">
												</div>
											</form>
										</div>
										<div class="clearfix"></div>
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

	<!-- END CONTAINER -->
	<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->

	<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
	<script type="text/javascript">
		function check(qualType) {

			if (qualType == 0) {
				document.getElementById("abc").style = "visible"
					
					document.getElementById("fac_address2").setAttribute("required","true");

			} else if (qualType == 1) {
				document.getElementById("abc").style = "display:none"
					document.getElementById("fac_address2").removeAttribute("required");

			}

		}
		function hideText() {
			//alert("hii");
			document.getElementById("abc").style = "display:none"
			document.getElementById("xyz").style = "display:none";
			var x=${facPerDetail.isAddSame};
			//alert("x" +x);
				//check(x);
		}

		function showForm() {
			//document.getElementById("abc").style = "display:none"
			var qualType = document.getElementById("designation").value
			//alert("qualType::"+qualType);

			if (qualType == 5) {

				document.getElementById("xyz").style = "visible"

			} else if (qualType == 1) {
				document.getElementById("xyz").style = "display:none"
			}

		}

		function calExp() {
			
			
			  const monthDifference =  moment(new Date('2019/05/30')).diff(new Date('2019/03/30'), 'months', true);
alert("monthDifference " +monthDifference)
			var d = new Date();
			
			var a = '2019/03/30';
			var b = '2019/05/30';

			// Months between years.
	//		var months = (b.getFullYear() - a.getFullYear()) * 12;
//alert(//Mo ths " +months);
			var month = d.getMonth() + 1;
			var day = d.getDate();

			var output = d.getFullYear() + '/' + (month < 10 ? '0' : '')
					+ month + '/' + (day < 10 ? '0' : '') + day;

			alert("date is" + output);

			var joinDate = document.getElementById("f_doj").value;
			alert("date of join" + joinDate);

			//date1: 24/09/2015 (24th Sept 2015)
			//date2: 09/11/2015 (9th Nov 2015)
			//the difference: 2.5 (months)

			var difference = (date2.getDate() - date1.getDate()) / 30
					+ date2.getMonth() - date1.getMonth()
					+ (12 * (date2.getFullYear() - date1.getFullYear()));
			alert("difference " +difference);

		}
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
    
    function submit_f(view) {
		document.getElementById("is_view").value = view;//create this 

	}
    $(function () {
		 
        $('.datepicker').datepicker({
			autoclose: true,
            format: "dd-mm-yyyy",
            changeYear:true,
            changeMonth:true
		});
    });
    
</script>


</body>
</html>