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
	word-wrap: break-word;
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

	<!-- <div aria-hidden="true" role="dialog" tabindex="-1" id="myModal2"
		class="modal fade" style="display: none;">
		<div class="modal-dialog"> -->
	<div class="modal-content">
		<div class="modal-header">
			<button aria-hidden="true" data-dismiss="modal" class="close"
				type="button">×</button>
			<h4 class="modal-title" align="center">New Principal Details
				Confirmation</h4>
		</div>
		<div class="col-md-12" align="center">
			<form role="form"
				action="${pageContext.request.contextPath}/showOtpPageForChangePrinci"
				method="POST">
				 
				 <br><br>
				 <input type="hidden" class="form-control" id="pageId" name="pageId">
					<input type="hidden" readonly="readonly" value="${editInst.contactNo}" id="otp_no" name="otp_no">
					<input type="hidden"  value="0" id="is_back" name="is_back">
				<table class="blueTable">

					<tfoot>
						<tr>

						</tr>
					</tfoot>
					<tbody>
						<tr>
							<td>Institute Name</td>
							<td>${instName}</td>

						</tr>
						<tr>
							<td>AISHE Code</td>
							<td>${aishe}</td>
						</tr>
						<tr>
							<td>Principal Name</td>
							<td>${editInst.facultyFirstName}</td>
						</tr>
						<tr>
							<td>Contact Number</td>
							<td>${editInst.contactNo}</td>
						</tr>
						<tr>
							<td>Email Id</td>
							<td>${editInst.email}</td>
						</tr>
					 

					</tbody>

				</table>
				<br>
				<div align="center">
					<button type="submit" class="btn btn-primary">Confirm</button>
<!-- 					<button type="submit" onclick="setIsBack()" class="btn btn-primary">Cancel</button>
 -->
				</div>
			</form>
		</div>

	</div>




	<!-- 	</div>
	</div>  -->


	 
<script type="text/javascript"> 
		 
		function getOpt() {
			//submit afrer showing details on modal dialogue
			var form = document.getElementById("form_sample_2");
			form.submit();

		}

		function showIsReg() {

			var x = $
			{
				editInst.instituteId
			}
			;

			if (x > 0) {

				var isReg = $
				{
					editInst.isRegistration
				}
				;

				//alert("Is Reg " +isReg);
				if (isReg == 0) {

					document.getElementById("abc").style.display = "none";
					document.getElementById("reg_date").removeAttribute(
							"required");

				} else {
					document.getElementById("abc").style.display = "block";

				}

			}

		}
		function setIsBack() {
			document.getElementById("is_back").value = "1";

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

			var primaryKey = $
			{
				editInst.instituteId
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
											document
													.getElementById("princ_email").value = "";

											alert("This Email Id is Already Exist in Database. Please Login with Your Credential.");

										} else {
											document
													.getElementById("princ_contact").value = "";

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