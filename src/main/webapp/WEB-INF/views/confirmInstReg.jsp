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
#main-content section {
	position: absolute;
	top: 0;
	bottom: 0;
	left: 0;
	right: 0;
	margin: auto;
}

table.blueTable {
	border: 1px solid #1C6EA4;
	background-color: #EEEEEE;
	width: 80%;
	border-collapse: collapse;
	table-layout: fixed;
	
	/* margin-left: 25%;
	margin-right: 25%;
	margin-top: 25%;
	margin-bottom: 25%; */
	
}

table.blueTable td, table.blueTable th {
	border: 1px solid #AAAAAA;
	padding: 3px 2px;
	
}

table.blueTable tbody td {
	font-size: 13px;
	text-align: left;
	word-wrap:break-word;
}

table.blueTable tr:nth-child(even) {
	background: #D0E4F5;
}

table.blueTable thead {
	background: #1C6EA4;
	background: -moz-linear-gradient(top, #5592bb 0%, #327cad 66%, #1C6EA4 100%);
	background: -webkit-linear-gradient(top, #5592bb 0%, #327cad 66%, #1C6EA4 100%);
	background: linear-gradient(to bottom, #5592bb 0%, #327cad 66%, #1C6EA4 100%);
	border-bottom: 2px solid #444444;
}

table.blueTable thead th {
	font-size: 15px;
	font-weight: bold;
	color: #FFFFFF;
	border-left: 2px solid #D0E4F5;
}

table.blueTable thead th:first-child {
	border-left: none;
}
</style>


<!-- BEGIN BODY -->
<body onload="showIsReg()" class=" ">
	<c:url value="/checkUniqueField" var="checkUniqueField"></c:url>
	<!-- START TOPBAR -->
	<!-- END TOPBAR -->
	<!-- START CONTAINER -->
	<%-- <div class="page-container row-fluid container-fluid">

		<!-- SIDEBAR - START -->

		<!--  SIDEBAR - END -->
		<!-- START CONTENT -->
		<section id="main-content" class=" ">
			<section class="wrapper main-wrapper row" style="">

				<div class="col-xs-12">
					<div class="page-title">

						<div class="pull-left">
							<!-- PAGE HEADING TAG - START -->
							<h1 class="title">${title}</h1>
							<!-- PAGE HEADING TAG - END -->
						</div>


					</div>
				</div>

				<!-- MAIN CONTENT AREA STARTS -->




				<div class="col-lg-12">
					<section class="box ">

						<header class="panel_header">
							<h2 align="center">INSTITUTE REGISTRATION</h2>

							<div class="actions panel_actions pull-right">
								<a href="${pageContext.request.contextPath}/"><button
										type="button" class="btn btn-info">Back</button></a>
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">


									<!-- 	<ul class="nav nav-tabs">
										<li class="active"><a href="#home" data-toggle="tab">
												<i class="fa fa-home"></i> Register Form
										</a></li>


									</ul>

									<div class="tab-content">
										<div class="tab-pane fade in active" id="home">
 -->
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertInstitute"
										method="post" name="form_sample_2" id="form_sample_2"
										onsubmit="return checkBeforeSubmit()">

										<input type="hidden" id="inst_id" name="inst_id"
											value="${editInst.instituteId}">

										<div class="row">
											<div class="col-md-12">
												<div class="col-sm-2"></div>

												<p class="desc text-danger fontsize11">Notice : This
													form strictly need to be filled by Institutes coming under
													RUSA Maharashtra Only. You can access RUSA portal only
													after authorisation done by RUSA officials.</p>

												<div class="form-group">
													<label class="control-label col-sm-3" for="page_name">Institute
														Name<span class="text-danger">*</span>
													</label>
													<div class="col-sm-7">
														<input type="text" maxlength="100" onchange="trim(this)"
															class="form-control" id="inst_name"
															value="${editInst.instituteName}" name="inst_name"
															placeholder="Complete Name of Institute" required>
													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-sm-3" for="page_name">AISHE
														Code <span class="text-danger">*</span>
													</label>
													<div class="col-sm-7">
														<input type="text" maxlength="50" onchange="trim(this)"
															class="form-control" id="aishe_code"
															value="${editInst.aisheCode}" name="aishe_code"
															placeholder="All India Survey On Higher Education code"
															required>
													</div>
												</div>




												<div class="form-group">
													<label class="control-label col-sm-3" for="page_order">Institute
														Address<span class="text-danger">*</span>
													</label>
													<div class="col-sm-7">
														<input type="text" onchange="trim(this)" maxlength="200"
															class="form-control" id="inst_add"
															value="${editInst.instituteAdd}" name="inst_add"
															placeholder="Complete Institute Address" required>
													</div>
												</div>


												<div class="form-group">
													<label class="control-label col-sm-3" for="planning">2F/12B
														Registration <span class="text-danger">*</span>
													</label>
													<div class="col-sm-7">

														<c:choose>
															<c:when test="${editInst.instituteId==0}">

																<input type="radio" id="is_registration"
																	name="is_registration" value="1" checked
																	onclick="setDate(this.value)">Yes
																<input type="radio" id="is_registration"
																	name="is_registration" value="0"
																	onclick="setDate(this.value)">No
															
															</c:when>
															<c:otherwise>

																<c:choose>
																	<c:when test="${editInst.isRegistration==1}">
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
												<div class="form-group" id="abc">
													<label class="control-label col-sm-3" for="page_order">Date
														of Registration <span class="text-danger">*</span>
													</label>


													<div class="col-sm-7">
														<input type="text" class="form-control datepicker" autocomplete="off"
															id="reg_date" value="${editInst.regDate}" name="reg_date"
															placeholder="Date of Registration" required>
													</div>
												</div>

												<div class="form-group">
													<label class="control-label col-sm-3" for="page_order">Trust/Society
														Name <span class="text-danger">*</span>
													</label>
													<div class="col-sm-7">
														<input type="text" onchange="trim(this)" maxlength="100"
															class="form-control" id="trusty_name"
															value="${editInst.trustName}" name="trusty_name"
															placeholder="Trust/Society Name"  required>
													</div>
												</div>

												<div class="form-group">
													<label class="control-label col-sm-3" for="page_order">Trust/Society
														Address <span class="text-danger">*</span>
													</label>
													<div class="col-sm-7">
														<input type="text" onchange="trim(this)" maxlength="200"
															class="form-control" id="trusty_add"
															value="${editInst.trustAdd}" name="trusty_add"
															placeholder="Trust/Society Address" value="" required>
													</div>
												</div>

												<div class="form-group">
													<label class="control-label col-sm-3" for="page_order">Official
														Contact No <span class="text-danger">*</span>
													</label>
													<div class="col-sm-7">
														<input type="text" value="${editInst.trustContactNo}" onchange="trim(this)"
															maxlength="15" class="form-control" id="trusty_con_no"
															name="trusty_con_no"
															placeholder="Trust/Society Official Contact No" required>
													</div>
												</div>

												<div class="form-group">
													<label class="control-label col-sm-3" for="page_order">
														Chairman/President Name<span class="text-danger">*</span>
													</label>
													<div class="col-sm-7">
														<input type="text" onchange="trim(this)" maxlength="200"
															class="form-control" id="pres_name"
															value="${editInst.presidentName}" name="pres_name"
															placeholder="Name of Chairman/President(Board of Governance)"
															required>
													</div>
												</div>

												<div class="form-group">
													<label class="control-label col-sm-3" for="page_order">Chairman
														Contact No <span class="text-danger">*</span>
													</label>
													<div class="col-sm-7">
														<input type="text" value="${editInst.presidenContact}"
															maxlength="15" class="form-control" id="pres_contact"
															name="pres_contact"
															placeholder="Chairman/President Contact No" required>
													</div>
												</div>

												<div class="form-group">
													<label class="control-label col-sm-3" for="page_order">Email
														ID <span class="text-danger">*</span>
													</label>
													<div class="col-sm-7">
														<input type="email" onchange="trim(this)"
															class="form-control" id="pres_email"
															value="${editInst.presidentEmail}" name="pres_email"
															placeholder="Chairman/President Email Id"
															required>
													</div>
												</div>




												<div class="form-group">
													<label class="control-label col-sm-3" for="page_order">
														Principal Name <span class="text-danger">*</span>
													</label>
													<div class="col-sm-7"> 
														<input type="text" maxlength="100" onchange="trim(this)"
															class="form-control" id="princ_name"
															value="${editInst.principalName}" name="princ_name"
															placeholder="Name of Principal" required>
													</div>
												</div>

												<div class="form-group">
													<label class="control-label col-sm-3" for="page_order">Mobile
														No <span class="text-danger">*</span>
													</label>
													<div class="col-sm-7">
														<input type="text" pattern="^[1-9]{1}[0-9]{9}$"
															maxlength="10" class="form-control" id="princ_contact"
															value="${editInst.contactNo}" name="princ_contact"
															placeholder="Principal Mobile No"
															oninput="checkUnique(this.value,1)" required>
														<p class="desc font-italic fontsize11">Note: OTP will
															be sent on this mobile number for verification</p>
													</div>

												</div>

												<div class="form-group">
													<label class="control-label col-sm-3" for="page_order">Email
														ID <span class="text-danger">*</span>
													</label>
													<div class="col-sm-7">
														<input type="email" onchange="trim(this)" 
															class="form-control" id="princ_email"
															value="${editInst.email}"
															oninput="checkUnique(this.value,2)" name="princ_email"
															placeholder=" Principal Email Id (Official)" required>
														<p class="desc font-italic fontsize11">Note:
															Verification mail will be sent on this Email id</p>
													</div>
												</div>


												<div class="form-group">
													<div class="col-sm-offset-3 col-sm-7">

														<input type="submit" id="sub_button"
															class="btn btn-primary" value="Submit" />
														<button type="reset" class="btn btn-default">Reset</button>
													</div>
												</div>



											</div>
										</div>

									</form>
								</div>
								<div class="clearfix"></div>
							</div>
							<div class="col-sm-3"></div>

							<p class="desc text-danger fontsize11">Notice : * Fields are
								mandatory.</p>
						</div>
					</section>



				</div>
			</section>

		</section>
	</div>

 --%>

	<!-- MAIN CONTENT AREA ENDS -->

	<!-- END CONTENT -->

	<!-- <div aria-hidden="true" role="dialog" tabindex="-1" id="myModal2"
		class="modal fade" style="display: none;">
		<div class="modal-dialog"> -->
	<div class="modal-content">
		<div class="modal-header">
			<button aria-hidden="true" data-dismiss="modal" class="close"
				type="button">×</button>
			<h4 class="modal-title" align="center">Institute Details
				Confirmation</h4>
		</div>
			<div class="col-md-12" align="center">
				<form role="form" onsubmit="callme()"
					action="${pageContext.request.contextPath}/showOtpPage"
					method="POST">
					<input type="hidden" class="form-control" id="pageId" name="pageId">
					<input type="hidden" readonly="readonly" value="${editInst.contactNo}" id="otp_no" name="otp_no">
					<input type="hidden"  value="0" id="is_back" name="is_back">
					

					<%-- 				<div class="col-sm-12">
					<div class="row">

						<div class="form-group">

							<label class="control-label col-sm-12" for="page_name">Institute
								Name:${editInst.instituteName} </label> <label class="control-label-left col-sm-9" id="inst_Name1"
								for="page_name"> ${editInst.instituteName} </label>
							 
						</div>


						<div class="form-group">

							<label class="control-label col-sm-12" for="page_name">AISHE
								Code:${editInst.aisheCode} </label> <label class="control-label-left col-sm-9" id="aishe_code1"
								for="page_name">${editInst.aisheCode}</label>
						</div>



						<div class="form-group">

							<label class="control-label col-sm-12" for="page_name">Institute
								Address:${editInst.instituteAdd} </label> <label class="control-label col-sm-12" id="inst_Add1"
								for="page_name"> ${editInst.instituteAdd} </label>
						</div>

						<div class="form-group">

							<label class="control-label col-sm-12" for="page_name">Is
								Registered:<c:if
									test="${editInst.isRegistration==1}">Yes</c:if> <c:if
									test="${editInst.isRegistration==0}">No</c:if>  </label> <label class="control-label col-sm-6" id="is_reg1"
								for="page_name"><c:if
									test="${editInst.isRegistration==1}">Yes</c:if> <c:if
									test="${editInst.isRegistration==0}">No</c:if> </label>
						</div>

						<div class="form-group">

							<label class="control-label col-sm-12" for="page_name">Registration
								Date:${editInst.regDate} </label> <label class="control-label col-sm-6" id="reg_date1"
								for="page_name">${editInst.regDate}</label>
						</div>

						<div class="form-group">

							<label class="control-label col-sm-12" for="page_name">Trust/Society
								Name :${editInst.trustName} </label> <label class="control-label col-sm-6" id="trust_Name1"
								for="page_name"> ${editInst.trustName} </label>
						</div>

						<div class="form-group">

							<label class="control-label col-sm-12" for="page_name">Trust/Society
								Address :${editInst.trustAdd} </label> <label class="control-label col-sm-6" id="trust_Add1"
								for="page_name">${editInst.trustAdd} </label>
						</div>

						<div class="form-group">

							<label class="control-label col-sm-12" for="page_name">Contact
								No :${editInst.trustContactNo} </label> <label class="control-label col-sm-6" id="trust_Con1"
								for="page_name"> ${editInst.trustContactNo} </label>
						</div>

						<div class="form-group">

							<label class="control-label col-sm-12" for="page_name">Chairman/President
								Name:${editInst.presidentName}</label> <label class="control-label col-sm-6" id="chairman_Name1"
								for="page_name">${editInst.presidentName} </label>
						</div>

						<div class="form-group">

							<label class="control-label col-sm-12" for="page_name">Contact
								No :${editInst.presidenContact}  </label> <label class="control-label col-sm-6" id="Chair_Con1"
								for="page_name"> ${editInst.presidenContact} </label>
						</div>

						<div class="form-group">

							<label class="control-label col-sm-12" for="page_name">Email
								ID(Official):${editInst.presidentEmail} </label> <label class="control-label col-sm-6"
								id="chair_Email1" for="page_name">${editInst.presidentEmail}
							</label>
						</div>

						<div class="form-group">

							<label class="control-label col-sm-12" for="page_name">Principal
								Name :${editInst.principalName} </label> <label class="control-label col-sm-6" id="princi_Name1"
								for="page_name">${editInst.principalName} </label>
						</div>
						<!-- 	-->
						<div class="form-group">

							<label class="control-label col-sm-12" for="page_name">Contact
								No : ${editInst.contactNo} </label> <label class="control-label col-sm-6" id="princi_Con1"
								for="page_name">${editInst.contactNo} </label>
						</div>

						<div class="form-group">

							<label class="control-label col-sm-12" for="page_name">Email
								ID(Official):${editInst.email} </label> <label class="control-label col-sm-6"
								id="princi_Email1" for="page_name">${editInst.email} </label>
						</div>
						
					</div>
					
				</div> --%>
	<br></br>
					<table class="blueTable" >

						<tfoot>
							<tr>

							</tr>
						</tfoot>
						<tbody>
							<tr>
								<td>Institute Name : ${editInst.instituteName}</td>
								<td>AISHE Code : ${editInst.aisheCode}</td>
							</tr>
							<tr>
								<td>Institute Address : ${editInst.instituteAdd}</td>
								<td>Village : ${editInst.village}</td>
							</tr>
							
							<tr>
								<td>Taluka : ${editInst.taluka}</td>
								<td>District : ${editInst.district}</td>
								
							</tr>
							<tr>
								<td>State : ${editInst.state}</td>
								<td>Pincode : ${editInst.pincode}</td>
								
							</tr>
							
							<tr>
								<td>Registration Date : ${editInst.regDate}</td>
								 <td>
								 	Is Registered For 2F/12B:
								 	<c:if test="${editInst.isRegistration==1}">Yes</c:if>
								  	<c:if test="${editInst.isRegistration==0}">No	</c:if>
								</td> 
								
							</tr>
							<tr>
								<td>Trust/Society Name :${editInst.trustName}</td>
								<td>Trust/Society Address : ${editInst.trustAdd}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									Contact No : ${editInst.trustContactNo}
								</td>
								<%-- <td>Contact No : ${editInst.trustContactNo}</td> --%>
							</tr>
							<tr>
								<td>Chairman/President Name : ${editInst.presidentName}</td>
								<td>Contact No : ${editInst.presidenContact}</td>
							</tr>
							<tr>
								<td>Email ID :  ${editInst.presidentEmail}</td>
								<td>Principal/Director/University Authority : ${editInst.principalName}</td>
							</tr>
							<tr>
								<td>Contact No : ${editInst.contactNo}</td>
								<td>Email ID : ${editInst.email}</td>
							</tr>
							<tr>
								  <td>
								 	Autonomy :
								 	<c:if test="${editInst.exVar2==1}">Yes</c:if>
								  	<c:if test="${editInst.exVar2==0}">No</c:if>
								</td>
								
								<td>Institute Type : 
									<c:if test="${editInst.exVar1==1}">Government</c:if>
									<c:if test="${editInst.exVar1==2}">Aided</c:if>
									<c:if test="${editInst.exVar1==3}">Non-Aided</c:if>								
								</td>
							</tr>
						</tbody>

					</table>
					<br>
					<div align="center">
						<button type="submit" id="btnId" class="btn btn-primary">Confirm</button>
						<button type="submit" onclick="setIsBack()" class="btn btn-primary">Cancel</button>
						
					</div>
				</form>
			</div>
		
	</div>




	<!-- 	</div>
	</div>  -->


	<script type="text/javascript">
	function callme(){
		document.getElementById("btnId").disabled=true;
		//alert("HHH")
		 return true;
	}
	
		function showDiv(value) {

			if (value == 1) {
				//alert(value);
				document.getElementById("abc").style.display = "block";
			} else {
				//alert(value);
				document.getElementById("abc").style.display = "none";
			}
		}

		function setDate(value) {
			//alert("Value " +value)
			if (value == 0) {
				//alert(value)
				document.getElementById("reg_date").removeAttribute("required");
				document.getElementById("abc").style.display = "none";

				//alert(value)
			} else {
				//alert(value)
				document.getElementById("reg_date").setAttribute("required",
						"true");
				document.getElementById("abc").style.display = "block";

				//alert(value)

			}

		}

		function getCOPO() {
			//alert("hii");

			var iqacName = document.getElementById("inst_name").value
			var aishe_code = document.getElementById("aishe_code").value
			var inst_add = document.getElementById("inst_add").value
			var is_registration = document.getElementById("is_registration").value
			var reg_date = document.getElementById("reg_date").value
			var trusty_name = document.getElementById("trusty_name").value
			var trusty_add = document.getElementById("trusty_add").value
			var trusty_con_no = document.getElementById("trusty_con_no").value
			var pres_name = document.getElementById("pres_name").value
			var pres_contact = document.getElementById("pres_contact").value
			var pres_email = document.getElementById("pres_email").value
			var princ_name = document.getElementById("princ_name").value
			var princ_contact = document.getElementById("princ_contact").value
			var princ_email = document.getElementById("princ_email").value
			var temp;
			var temp1;

			if (is_registration == 1) {
				alert(reg_date);
				temp = "Yes";
				$('#reg_date1').html(reg_date);

			} else {
				alert("no...");
				temp = "No";
				temp1 = "-";$('#reg_date1').html(temp1);

			}

			$('#inst_Name1').html(iqacName);
			$('#aishe_code1').html(aishe_code);
			$('#inst_Add1').html(inst_add);
			$('#is_reg1').html(temp);

			$('#trust_Name1').html(trusty_name);
			$('#trust_Add1').html(trusty_add);
			$('#trust_Con1').html(trusty_con_no);

			$('#chairman_Name1').html(pres_name);
			$('#Chair_Con1').html(pres_contact);
			$('#chair_Email1').html(pres_email);
			$('#princi_Name1').html(princ_name);
			$('#princi_Con1').html(princ_contact);
			$('#princi_Email1').html(princ_email);

		}
		function getOpt() {
			//submit afrer showing details on modal dialogue
			var form = document.getElementById("form_sample_2");
			form.submit();

		}

		function showIsReg() {

			var x = ${editInst.instituteId};

			if (x > 0) {

				var isReg = ${editInst.isRegistration};

				//alert("Is Reg " +isReg);
				if (isReg == 0) {

					document.getElementById("abc").style.display = "none";
					document.getElementById("reg_date").removeAttribute("required");

				} else {
					document.getElementById("abc").style.display = "block";

				}

			}

		}
		function setIsBack(){
			document.getElementById("is_back").value="1";
			
		}
	</script>


	<!-- END CONTAINER -->
	<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->
	<script type="text/javascript">
		$(function() {

			$('.datepicker').datepicker({
				autoclose : true,
				format : "dd-mm-yyyy",
				changeYear : true,
				changeMonth : true
			});
		});

		function checkUnique(inputValue, valueType) {
			//alert(inputValue);

			var primaryKey = ${editInst.instituteId};
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
					valid = false;
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
									tableId : 1,
									ajax : 'true',

								},
								function(data) {

									//alert("Data  " +JSON.stringify(data));
									if (data.error == true) {
										if (valueType == 2) {
											document.getElementById("princ_email").value = "";

											alert("This Email Id is Already Exist in Database. Please Login with Your Credential.");

										} else {
											document.getElementById("princ_contact").value = "";

											alert("This Mobile No is Already Exist in Database. Please Login with Your Credential.");
										}
									}
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
					document.getElementById("sub_button").disabled = true;
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
	<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
</body>
</html>