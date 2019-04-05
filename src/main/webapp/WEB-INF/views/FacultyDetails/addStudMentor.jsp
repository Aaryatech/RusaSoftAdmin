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
						</header>
						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/addStudMentor"
										method="post" name="formidhere" id="formidhere">
									
									<input type="hidden" id="menId" name="menId" value="${stud.menId}">
										<div class="form-group">

											<label class="control-label col-sm-2" for="page_name">No
												of Students <span class="text-danger">*</span>
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
												<button type="submit" id="sub_button" class="btn btn-primary" 
													onclick="submit_f(1)"><i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save</button>
											
											<a href="${pageContext.request.contextPath}/showStudMentor"><button
													id="sub2" type="button" class="btn btn-primary">
													<i class="${sessionScope.cancelIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Cancel</button></a>
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
    				
    	
    				 if (!isError) {
	            		 
							var x = confirm("Do you really want to submit the form?");
							if (x == true) {
								
								document.getElementById("sub_button").disabled = true;
								document.getElementById("sub2").disabled = true;
								return  true;
							}
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