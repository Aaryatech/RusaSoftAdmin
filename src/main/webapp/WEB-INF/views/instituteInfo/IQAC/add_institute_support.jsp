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

							<%-- <div class="actions panel_actions pull-right">
								<a href="${pageContext.request.contextPath}/showInstituteSupport"><button
										type="button" class="btn btn-info">Back</button></a>
							</div> --%>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertInstituteSupport"
										method="post" name="form_sample_2" id="form_sample_2">


										<div id="abc">
											<input type="hidden" id="inst_scheme_id"
												name="inst_scheme_id" placeholder=""
												value="${instSpprt.instSchemeId}">
											<div class="form-group">

												<label class="control-label col-sm-3" for="studBenifited">Name
													of Scheme <span class="text-danger">*</span>
												</label>
												<div class="col-sm-6">
													<input type="text" class="form-control"
														id="inst_scheme_name" autocomplete="off"
														onchange="return trim(this)" name="inst_scheme_name"
														placeholder="Name of Scheme"
														value="${instSpprt.instSchemeName}"> <span
														class="error_form text-danger" id="error_formfield1"
														style="display: none;">Please enter name of
														schemes.</span>
												</div>
											</div>


											<div class="form-group">

												<label class="control-label col-sm-3"
													for="inst_students_benefited">No. of Students
													Benefited <span class="text-danger">*</span>
												</label>
												<div class="col-sm-6">
													<input type="text" class="form-control"
														autocomplete="off" id="inst_students_benefited"
														onchange="trim(this)" onFocus="clearDefault(this)"
														name="inst_students_benefited"
														placeholder="No. of Students Benefited" maxlength="7"
														value="${instSpprt.instStudentsBenefited}"> <span
														class="error_form text-danger" id="error_formfield2"
														style="display: none;">Please enter No. of students
														benefited and value must be greater than 0.</span>
												</div>
											</div>

											<div class="form-group">

												<label class="control-label col-sm-3"
													for="inst_schme_offeredby">Scheme/Support offered
													By <span class="text-danger">*</span>
												</label>
												<div class="col-sm-6">
													<input type="text" class="form-control" autocomplete="off"
														id="inst_schme_offeredby" name="inst_schme_offeredby"
														placeholder="Scheme / Support offered By"
														onchange="trim(this)"
														value="${instSpprt.instSchmeOfferedby}"> <span
														class="error_form text-danger" id="error_formfield3"
														style="display: none;">Please enter scheme/support
														offered by.</span>
												</div>
											</div>
											
											<div class="form-group">

												<label class="control-label col-sm-3"
													for="inst_schme_offeredby">Amount (Rs.)<span class="text-danger"></span>
												</label>
												<div class="col-sm-6">
													<input type="text" class="form-control" autocomplete="off"
														id="amount" name="amount" placeholder="Amount in Rs." 
														onchange="trim(this)" onFocus="clearDefault(this)"
														value="${instSpprt.exVar1}">
												</div>
											</div>
											


											<div class="form-group">
												<div class="col-sm-offset-3 col-sm-10">
													<button type="submit" id="sub_button"
														class="btn btn-primary" onclick="submit_f(1)">
														<i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save
													</button>

													<a
														href="${pageContext.request.contextPath}/showInstituteSupport"><button
															id="sub2" type="button" class="btn btn-primary">
															<i class="${sessionScope.cancelIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Cancel
														</button></a> <input type="hidden" id="is_view" name="is_view"
														value="0">
												</div>
											</div>
										</div>


										<div class="clearfix"></div>
										<!-- 	</div>

											</div> -->

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
	<script>
	function trim(el) {
		el.value = el.value.replace(/(^\s*)|(\s*$)/gi, ""). // removes leading and trailing spaces
		replace(/[ ]{2,}/gi, " "). // replaces multiple spaces with one space 
		replace(/\n +/, "\n"); // Removes spaces after newlines
		return;
	}
	
            
            	$(document).ready(function($){
            		
            		$("#form_sample_2").submit(function(e) {
            		//	alert("hi");
            			var isError=false;
            			 var errMsg="";
            			
            			
            			 if(!$("#inst_scheme_name").val()){
        					 
             				isError=true;
             				errMsg += '<li>Please enter a valid name.</li>';
             				
             				$("#inst_scheme_name").addClass("has-error")
             				$("#error_formfield1").show()
             					//return false;
             				} else {
             					$("#error_formfield1").hide()
             				} 
           				
           				if($("#inst_students_benefited").val()<=0 || !$("#inst_students_benefited").val()){
       					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#inst_students_benefited").addClass("has-error")
            				$("#error_formfield2").show()
            					//return false;
            				} else {
            					$("#error_formfield2").hide()
            				} 
           				
           				if(!$("#inst_schme_offeredby").val()){
       					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#inst_schme_offeredby").addClass("has-error")
            				$("#error_formfield3").show()
            					//return false;
            				} else {
            					$("#error_formfield3").hide()
            				} 
           				
           				/* if(!$("#").val()){
        					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#").addClass("has-error")
            				$("#error_formfield6").show()
            					//return false;
            				} else {
            					$("#error_formfield6").hide()
            				} */
           			
            				
			            	 if (!isError) {
			            		 
								var x = confirm("Do you really want to submit the form?");
								if (x == true) {
									
									document.getElementById("sub_button").disabled = true;
									document.getElementById("sub2").disabled = true;
									return  true;
								}
							}
            					   return false;
            			});
        });

</script>

	<script type="text/javascript">
	function submit_f(view){
		document.getElementById("is_view").value=view;//create this 
		/* var form=document.getElementById("form_sample_2");
	    form.setAttribute("method", "post");

		form.action=("insertHod");
		var x =confirm("Do you really want to submit the form?");
		if(x==true)
		form.submit(); */
		
	}
		function getData() {
			//alert("hii");
			var i = parseInt(document.getElementById("index").value);
			var schemeName = document.getElementById("schemeName").value;
			var academicYear = document.getElementById("academicYear").value;

			var studBenifited = document.getElementById("studBenifited").value;

			var schemeSupportBy = document.getElementById("schemeSupportBy").value;
			//alert("noStud"+noStud);
			var dataTable = $('#example-1').DataTable();

			dataTable.row.add(
					[ i + 1, schemeName, academicYear, studBenifited,
							schemeSupportBy ]).draw();
			document.getElementById("index").value = i + 1;
		}
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
		function setGovernmentValue(value) {

			if (value == 1) {

				document.getElementById("abc").style.display = "none";

			} else {

				document.getElementById("abc").style.display = "block";

			}

		}
	</script>


	<script type="text/javascript">
		$(function() {
			$('#submitForm').submit(
					function() {
						$("input[type='submit']", this).val("Please Wait...")
								.attr('disabled', 'disabled');

						return true;
					});
		});

		function trim(el) {
			el.value = el.value.replace(/(^\s*)|(\s*$)/gi, ""). // removes leading and trailing spaces
			replace(/[ ]{2,}/gi, " "). // replaces multiple spaces with one space 
			replace(/\n +/, "\n"); // Removes spaces after newlines
			return;
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
</script>
	<script type="text/javascript">
	function clearDefault(a){
		if(a.defaultValue==0)
		{
			a.value=""
		}
		};
		  $('#inst_students_benefited').on('input', function() {
			  this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');
			});
		  $('#amount').on('input', function() {
			  this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');
			});
	</script>
</body>
</html>