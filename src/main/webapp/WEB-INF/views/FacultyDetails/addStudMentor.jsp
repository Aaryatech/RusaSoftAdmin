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
							<%-- <h1 class="title">${title}</h1> --%>
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
								<a href="${pageContext.request.contextPath}/showStudMentor"
									data-toggle="modal"><button type="submit"
										class="btn btn-info">Back</button></a>
								<%-- <a href="${pageContext.request.contextPath}/publicationList"><button
										type="button" class="btn btn-info">Back</button></a> --%>
								<!-- <a class="box_toggle fa fa-chevron-down"></a> -->
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/addStudMentor"
										method="post" name="formidhere" id="formidhere">
										<!-- onsubmit="return checkBeforeSubmit()" -->

										<!-- <div class="form-group">

													<label class="control-label col-sm-3" for="page_name">Mentoring
														to the Students <span class="text-danger">*</span>
													</label>
												</div> -->
										<input type="hidden" id="menId" name="menId"
											value="${stud.menId}">
										<div class="form-group">

											<label class="control-label col-sm-2" for="page_name">No
												of Student <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" 
													class="form-control numbersOnly" id="stud_no" 
													name="stud_no" placeholder="No." maxlength="5"
													value="${stud.menStuCount}">
											<span class="error_form text-danger" id="error_formfield1" style="display:none;" >Please enter No. of student and value must be greater than 0.</span>
											
											</div>
										</div>

										<input type="hidden" id="is_view" name="is_view" value="0">

										<div class="form-group">
											<div class="col-sm-offset-2 col-sm-10">
												<input type="submit" id="sub1" class="btn btn-primary"
													onclick="submit_f(1)" value="Save"> <input
													type="submit" id="sub2" class="btn btn-primary"
													onclick="submit_f(0)" value="Save &
																		Next">
												<button type="reset" class="btn btn-default">Reset</button>
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


				<!-- MAIN CONTENT AREA ENDS -->
			</section>
		</section>
		<!-- END CONTENT -->



	</div>
	<!-- END CONTAINER -->
	<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->

	<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>





	<div aria-hidden="true" role="dialog" tabindex="-1" id="myModal1"
		class="modal fade" style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button aria-hidden="true" data-dismiss="modal" class="close"
						type="button">×</button>
					<h4 class="modal-title">Mentoring to Student</h4>
				</div>
				<div class="modal-body">
					<%-- <form role="form"
						action="${pageContext.request.contextPath}/showModuleForm"
						method="get"> --%>
					<input type="hidden" class="form-control" id="pageId" name="pageId">

					<input type="hidden" class="form-control" id="index" name="index"
						value="0">



					<div class="form-group">
						<label class="control-label col-sm-6" for="page_name">Academic
							Year</label> <select id="qualType" name="qualType" class="form-control"
							onchange="showForm()" required>
							<option value="2018-2019">2018-2019</option>
							<option value="2017-2018">2017-2018</option>
							<option value="2016-2017">2016-2017</option>
							<option value="2016-2017">2015-2016</option>

						</select>
					</div>




					<button type="submit" class="btn btn-primary" onclick="getData()">Submit</button>
					<!-- </form> -->
				</div>
			</div>
		</div>
	</div>
	
<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
	

	<script type="text/javascript">
		function getData() {
			//alert("hii");
			var i = parseInt(document.getElementById("index").value);
			var year = document.getElementById("qualType").value;
			//alert("year"+year);
			var noStud = document.getElementById("hodName").value;
			//alert("noStud"+noStud);
			var dataTable = $('#example-2').DataTable();

			dataTable.row.add([ i + 1, year, noStud ]).draw();

			document.getElementById("index").value = i + 1;

		}
	</script>
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

		function submit_f(view) {

			document.getElementById("is_view").value = view;//create this 

		}
	</script>

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
	
		function onlyno(e) {
			 alert(e)
			var numbers = /^[1-9]+$/;
		      if(e.test($.trim(numbers)) == false)
		      {
		      
		      return false;
		      } 
			
				return true;
		}
	
		$(document).ready(function($){
        	
    		$("#formidhere").submit(function(e) {
    			 var isError=false;
    			 var errMsg="";
    				
    				if($("#stud_no").val()==0 || !$("#stud_no").val()){
    					 
    					isError=true;
    					 
    					errMsg += '<li>Please enter value greater than 0  and less than 99999</li>';
    				$("#stud_no").addClass("has-error")
    				$("#error_formfield1").show()
    					//return false;
    				} else {
    					$("#error_formfield1").hide()
    				}
    				
    				  
    				 
    				/* if(!$("#mobile").val() || !validateMobile($("#mobile").val())){
    
    				isError=true;
    				errMsg += '<li>Please enter a valid email address.</li>';
    				errMsg_alert = 'Please enter a valid mobile number.';
    				$("#error_mobile").html(errMsg_alert);
    				$("#error_mobile").show();
    				//alert();
    					//return false;
    				} else {
    					$("#error_mobile").html("Please enter mobile")
    					$("#error_mobile").hide()
    				}
    				if(!$("#email").val() || !validateEmail($("#email").val())){
    
    				isError=true;
    				errMsg += '<li>Please enter a valid email address.</li>';
    				errMsg_alert += 'Please enter a valid email address. \n';
    				$("#error_email").show()
    					//return fregister_useralse;
    				} else {
    					$("#error_email").hide()
    				} */
    
    		 
    
    
    			  /*  if ($('#termcondition').is(':checked')) {
    				} else {
    				isError=true;
    					errMsg += '<li>You must agree to our Term & Conditions.</li>';
    					errMsg_alert = 'You must agree to our Term & Conditions. \n';
    					alert(errMsg_alert);
    					return false;
    				} */
     
    
    
    				if(!isError) {
    					//dataString = $("#regform").serialize();
    					// alert(weregister_userb_site_url);
    						/* $("#regerror").html("");
    					    $("#regerror").hide();
     */
    								 //ajax send this to php page
    						//$("#formidhere").submit();
    								//end ajax send this to php page
    					   } else {
    					   $("#regerror").html(errMsg);
    					    $("#regerror").show();
    					   }
    					   return false;
    				} );
    	});
	</script>
	<script type="text/javascript">
		function submit_f(view) {
			//alert(view);
			document.getElementById("is_view").value = view;

		}
	</script>

</body>
</html>