<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 
<%@ page import="java.util.UUID"%>
<%@ page import="java.security.MessageDigest"%>
<%@ page import="java.math.BigInteger"%>
 

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
<%-- <c:url value="/getDateValidation" var="getDateValidation"></c:url> --%>
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
								<%-- <a
									href="${pageContext.request.contextPath}/showResearchDetailsList"><button
										type="button" class="btn btn-info">Back</button></a> --%>
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertResearchProject"
										method="post" name="formidhere" id="formidhere">


<%
												UUID uuid = UUID.randomUUID();
													MessageDigest md = MessageDigest.getInstance("MD5");
													byte[] messageDigest = md.digest(String.valueOf(uuid).getBytes());
													BigInteger number = new BigInteger(1, messageDigest);
													String hashtext = number.toString(16);
													session = request.getSession();
													session.setAttribute("generatedKey", hashtext);
											%>
											<input type="hidden" value="<%out.println(hashtext);%>"
												name="token" id="token">

										<div class="form-group">
											<label class="control-label col-sm-2" for="smallheading">Name
												of Project <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control" id="projName"
													name="projName" autocomplete="off"
													value="${editProject.projName}"
													placeholder="Name of Project"> <span
													class="error_form text-danger" id="error_projName"
													style="display: none;">Please enter Name of Project</span>
											</div>
										</div>


										<div class="form-group">
											<label class="control-label col-sm-2" for="smallheading">Year
												of Project Sanction <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">

												<input type="text" class="form-control datepickeryear"
													data-min-view-mode="years" data-start-view="2"
													value="${editProject.projYear}" autocomplete="off"
													placeholder="Year of Project Sanction" name="yearOfPS"
													id="yearOfPS" data-format="yyyy"> <span
													class="error_form text-danger" id="error_yearOfPS"
													style="display: none;">Please enter Year of Project
													Sanction</span>
											</div>

										</div>
										<input type="hidden" id="is_view" name="is_view" value="0">
										<div class="form-group">


											<label class="control-label col-sm-2" for="page_order">Sponsoring
												Authority <span class="text-danger">*</span>
											</label>


											<div class="col-sm-6">
												<input type="text" class="form-control" id="spoAuth"
													name="spoAuth" placeholder=" Sponsoring Authority including Goverment/Non Govt"
													autocomplete="off" value="${editProject.projSponsor}">
												<span class="error_form text-danger" id="error_spoAuth"
													style="display: none;">Please enter Sponsoring
													Authority</span>
											</div>
										</div>

										<div class="form-group">
											<label class="control-label col-sm-2" for="smallheading">Name
												of Principal Investigator <span class="text-danger"></span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control" id="PIName"
													name="PIName" placeholder="Name of Principal Investigator"
													autocomplete="off" value="${editProject.projInvName}">
													<!-- <span
													class="error_form text-danger" id="error_PIName"
													style="display: none;">Please enter Name of
													Principal Investigator Authority</span> -->

											</div>

										</div>


										

										<div class="form-group">
											<label class="control-label col-sm-2" for="smallheading">Name
												of Co-Principal Investigator <span class="text-danger"></span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control" id="coPrincipal"
													name="coPrincipal"
													placeholder="Name of Co-Principal Investigator"
													autocomplete="off" value="${editProject.projInvName2}">


											</div>

										</div>
										<div class="form-group">
											<label class="control-label col-sm-2" for="page_order">Department
												Name <span class="text-danger"></span>
											</label>


											<div class="col-sm-6">
												<input type="text" class="form-control" id="deptCoName"
													name="deptCoName" placeholder="Department Name"
													autocomplete="off" value="${editProject.projInvDept2}">
											</div>



										</div>


										<div class="form-group">
											<label class="control-label col-sm-2" for="smallheading">Grant Sanctioned (Rs.) 
												<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="number" class="form-control" id="grant" min="0"
													name="grant" placeholder="Grant Sanctioned in Rs." autocomplete="off"
													value="${editProject.projGrant}" maxlength="6"> <span
													class="error_form text-danger" id="error_grant"
													style="display: none;">Please enter Grant Sanctioned</span>

											</div>
										</div>
										
										<div class="form-group">

											<label class="control-label col-sm-2" for="page_order">
												Grant Received (Rs.)<span class="text-danger">*</span>
											</label>


											<div class="col-sm-6">
												<input type="number" class="form-control" id="amtRec" onblur="compare()"
													min="0" name="amtRec" placeholder="Grant Received in Rs."
													autocomplete="off" value="${editProject.projAmtRec}" maxlength="6">
												<span class="error_form text-danger" id="error_amtRec"
													style="display: none;">Please enter Grant Received</span>
													
													<span class="error_form text-danger" id="error_amtRec_amt"
													style="display: none;">Grant received amount can not be greater than sanctioned</span>
													
													
											</div>



										</div>

										<input type="hidden" value="${editProject.projId}"
											name="projId" id="projId">


										<div class="form-group">


											<label class="control-label col-sm-2" for="page_order">From
												Date <span class="text-danger">*</span>
											</label>


											<div class="col-sm-6">
												<input type="text" class="form-control datepicker"
													id="fromDate" name="fromDate" placeholder="From Date"
													autocomplete="off" value="${editProject.projFrdt}">
												<span class="error_form text-danger" id="error_fromDate"
													style="display: none;">Please enter from date </span>
													
												<span
													class="error_form text-danger" id="error_fromToDate"
													style="display: none;">From Date must be smaller than To Date </span>
											</div>
										</div>
										<div class="form-group">

											<label class="control-label col-sm-2" for="page_order">
												To Date <span class="text-danger">*</span>
											</label>


											<div class="col-sm-6">
												<input type="text" class="form-control datepicker"
												 id="toDate" name="toDate"
													placeholder="To Date" autocomplete="off"
													value="${editProject.projTodt}"> <span
													class="error_form text-danger" id="error_toDate"
													style="display: none;">Please enter to Date </span>
													
													<span
													class="error_form text-danger" id="error_toToDate"
													style="display: none;">To Date must be greater than From Date </span>
											</div>



										</div>

										<div class="form-group">
													<div class="col-sm-offset-3 col-sm-9">


<button type="submit" id="sub_button" class="btn btn-primary"
													onclick="submit_f(1)"><i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save</button>
														
<a href="${pageContext.request.contextPath}/showResearchDetailsList"><button id="sub_button_cancel"
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


				<!-- MAIN CONTENT AREA ENDS -->
			</section>
		</section>
		<!-- END CONTENT -->



	</div>
	<!-- END CONTAINER -->
	<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->

	<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>

	<script type="text/javascript">
		$(function() {

			$('.datepicker').datepicker({
				autoclose : true,
				format : "dd-mm-yyyy",
				changeYear : true,
				changeMonth : true

			});
		});
	</script>
	<script type="text/javascript">
	function allowOnlyNumber(evt){
		
		var charCode = (evt.which) ? evt.which : event.keyCode
	    if (charCode == 46){
	        var inputValue = $("#floor").val();
	        var count = (inputValue.match(/'.'/g) || []).length;
	        
	        if(count<1){
	            if (inputValue.indexOf('.') < 1){
	                return true;
	            }
	            return false;
	        }else{
	            return false;
	        }
	    }
	    if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)){
	        return false;
	    }
	    return true;
	}
	</script>

	<script type="text/javascript">
function submit_f(view){
	//alert(view);
		document.getElementById("is_view").value=view; 
		
	}
	function numbersOnlyNotZero(value) {

        
        var mob = /^[1-9][0-9]+$/;


        if (mob.test($.trim(value)) == false) {

            //alert("Please enter a valid email address .");
            return false;

        }
        return true;
    }
	</script>

	<script type="text/javascript">
	
	$('#grant').on('input', function() {
		  this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');
		});
	
	$('#amtRec').on('input', function() {
		  this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');
		});
	
    $(function () {
	 
        $('.datepickeryear').datepicker({
			autoclose: true,
			minViewMode: 2,
	         format: 'yyyy'

		});
    });
</script>

	 <script>
		function checkDate1(fromDate,toDate) {
			var res=false;
			var valid = true;
			if (valid == true)
				$
						.getJSON(
								'${getDateValidation}',
								{
									fromDate : fromDate,
									toDate : toDate,
									ajax : 'true',

								},
								function(data) {
									res=data.isError;
									alert("Data  " +JSON.stringify(data));
									if (data==0) {
										res=0;
											//alert("To Date must be greate than to date");
									} else{
										res=1;
									}
								});
			return res;
		}

	</script> 


	<script>
	   
	function compare(){
		
	}
	
            	$(document).ready(function($){
            	 
            		$("#formidhere").submit(function(e) {
            			
            			
            		
            			 var isError=false;
            			 var errMsg="";
            				
            			var from_date = document.getElementById("fromDate").value;
         				var to_date = document.getElementById("toDate").value;
         				var x=0;
         				
         				
         		        var fromdate = from_date.split('-');
         		        from_date = new Date();
         		        from_date.setFullYear(fromdate[2],fromdate[1]-1,fromdate[0]);
         		        var todate = to_date.split('-');
         		        to_date = new Date();
         		        to_date.setFullYear(todate[2],todate[1]-1,todate[0]);
         		        if (from_date > to_date ) 
         		        {
         		           /// alert("Invalid Date Range!\nStart Date cannot be after End Date!")
							$("#error_fromToDate").show();
    					 	$("#error_toToDate").show();
    					 	$("#error_fromDate").hide();
    					 	$("#error_toDate").hide();
         		            return false;
         		           
         		        }else {
         					$("#error_fromToDate").hide();
         					$("#error_toToDate").hide();
         				}
         				
         				
         				/* x=checkDate1(fromDate,toDate);
         				if(x==0){
        					 alert("Hi x==0")
             				isError=true;
             				$("#fromDate").addClass("has-error")
             				$("#toDate").addClass("has-error")
             				$("#error_fromToDate").show();
     					 	$("#error_toToDate").show();
         				}
             				else {
             					$("#error_fromToDate").hide();
             					$("#error_toToDate").hide();
             				}
         				if(x==1){
         					alert("Hi x===1")
         					$("#error_fromToDate").hide();
         					$("#error_toToDate").hide();
         				} */
           
            				if(!$("#projName").val()){
            					 
            				isError=true;
            				errMsg += '<li>Please enter Project Name.</li>';
            				
            				$("#projName").addClass("has-error")
            				$("#error_projName").show()
            					 
            				} else {
            					$("#error_projName").hide();
            				}
            				
            				
            				if(!$("#yearOfPS").val()){
            					 
            				isError=true;
            				errMsg += '<li>Please enter Year of Transction.</li>';
            				
            				$("#yearOfPS").addClass("has-error")
            				$("#error_yearOfPS").show();
            					 
            				} else {
            					$("#error_yearOfPS").hide();
            				}
            				
            				
            				if(!$("#spoAuth").val()){
           					 
                				isError=true;
                				errMsg += '<li>Please enter Sponsoring Authority.</li>';
                				
                				$("#spoAuth").addClass("has-error")
                				$("#error_spoAuth").show();
                					 
                				} else {
                					$("#error_spoAuth").hide();
                				}
            				
            				
            				/* if(!$("#PIName").val()){
              					 
                				isError=true;
                				errMsg += '<li>Please enter Principal Investigator.</li>';
                				
                				$("#PIName").addClass("has-error")
                				$("#error_PIName").show();
                					 
                				} else {
                					$("#error_PIName").hide();
                				} */
            				
            				
            				
            				
            				/* if(!$("#deptName").val()){
              					 
                				isError=true;
                				errMsg += '<li>Please enter Department.</li>';
                				
                				$("#deptName").addClass("has-error")
                				$("#error_deptName").show();
                					 
                				} else {
                					$("#error_deptName").hide();
                				} */
            				
            				
            				
            				if(!$("#grant").val()){
              					 
                				isError=true;
                				errMsg += '<li>Please enter Grant</li>';
                				
                				$("#grant").addClass("has-error")
                				$("#error_grant").show();
                					 
                				} else {
                					$("#error_grant").hide();
                				}
            				
            				//error_spoAuth
            				
            				/* if(!$("#totalAmt").val() || !($("#totalAmt").val()>0)){
            					 
            				isError=true;
            				errMsg += '<li>Please enter Total Amount</li>';
            				
            				$("#totalAmt").addClass("has-error")
            				$("#error_totalAmt").show();
            					 
            				} else {
            					$("#error_totalAmt").hide();
            				} */
            				
            				
            				if(!$("#amtRec").val() || !($("#amtRec").val()>0)){
            					 
            				isError=true;
            				errMsg += '<li>Please enter Amount Received.</li>';
            				
            				$("#amtRec").addClass("has-error")
            				$("#error_amtRec").show();
            					 
            				} else {
            					$("#error_amtRec").hide();
            				}
            				
            				//error_fromDate
            				
            				/* var grantSac=0;
            				var grantRec=0;
            				grantRec=$("#amtRec").val();
            				grantSac=$("#grant").val();
            				alert("grantRec " +grantRec);
            				alert("grantSac " +grantSac);
            				
            				if(grantRec<=grantSac){
            					$("#amtRec").addClass("has-error")
                				$("#error_amtRec_amt").show();
            				}else{
            					$("#error_amtRec_amt").hide();
            				} */
            			
            				
            				
            				 if(!$("#fromDate").val()){
           					 
                				isError=true;
                				errMsg += '<li>Please enter FromDate.</li>';
                				
                				$("#fromDate").addClass("has-error")
                				$("#error_fromDate").show();
                					 
                				} else {
                					$("#error_fromDate").hide();
                				}
                				
            				
            				if(!$("#toDate").val()){
              					 
                				isError=true;
                				errMsg += '<li>Please enter to Date.</li>';
                				
                				$("#toDate").addClass("has-error")
                				$("#error_toDate").show();
                				
                					 
                				} else {
                					$("#error_toDate").hide();
                				}
            				var a = document.getElementById('amtRec').value;
              			 			 a = parseFloat(a);
              			 	
              				var b = document.getElementById('grant').value;
              						  b = parseFloat(b);
              			
              			  if(a > b) {
              				isError=true;
              				  $("#amtRec").addClass("has-error")
              					$("#error_amtRec_amt").show();
              				
              				  } else{
              					  $("#error_amtRec_amt").hide();
              					  	
              				  }
            			 
            				if(!isError) {
            					
            					var x = confirm("Do you really want to submit the form?");
								if (x == true) {
									document.getElementById("sub_button").disabled = true;
									document.getElementById("sub_button_cancel").disabled = true;
									return  true;
								
								}
            					 		  
            					   } 
            					   return false;
            				} );
            	});
		  
        </script>
<!-- 	function checkUnique(inputValue, valueType) {
			//alert(inputValue);

			var primaryKey = $
			{
				editInst.librarianId
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
								'${checkUniqueField}',
								{

									inputValue : inputValue,
									valueType : valueType,
									primaryKey : primaryKey,
									isEdit : isEdit,
									tableId : 4,

									ajax : 'true',

								},
								function(data) {

									//	alert("Data  " +JSON.stringify(data));
									if (data.error == true) {
										if (valueType == 2) {

											alert("This email id already exist in system please enter unique");
											$('#librarian_email').val('');
											//document.getElementById("stud_contact_no").value=" ";

										} else {

											alert("This contact no  already exist in system please enter unique");
											$('#lib_con_num').val('');
											//document.getElementById("student_email").value=" ";
										}
									}
								});
		}
	</script>
 -->
</body>
</html>