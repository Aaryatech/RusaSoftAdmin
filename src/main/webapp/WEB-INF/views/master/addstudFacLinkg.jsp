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

							<%-- <div class="actions panel_actions pull-right">
								<a href="${pageContext.request.contextPath}/showOutReachContriList"><button
										type="button" class="btn btn-info">Back</button></a>

							</div> --%>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertStudFacultylinkg"
										method="post" 
										name="form_sample_2" id="form_sample_2">


												<div>

									<input type="hidden" value="${linkage.facultyStudLinkageId}" id="fac_stud_link_id" name="fac_stud_link_id">


													<div class="form-group">
														<label class="control-label col-sm-2" for="smallheading">Linkage Title 
															<span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<input type="text" class="form-control" id="link_title" autocomplete="off" onchange="trim(this)"
															name="link_title" placeholder="Title Of linkage" value="${linkage.linkageTitle}">
														<span class="error_form text-danger" id="error_formfield0" style="display:none;" >Please enter title of linkage.</span>
														</div>


													</div>
													
													<div class="form-group">
														<label class="control-label col-sm-2" for="smallheading">Partnering 
															Institute<span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<input type="text" class="form-control" id="part_inst" autocomplete="off" onchange="trim(this)"
															name="part_inst" placeholder="Name of Partnering Institute" value="${linkage.partneringInstitute}">
														<span class="error_form text-danger" id="error_formfield1" style="display:none;" >Please enter name of partnering institute.</span>
														</div>


													</div>

													<div class="form-group">

														<label class="control-label col-sm-2" for="smallheading">Industry 
															Name<span class="text-danger">*</span>
														</label>

														<div class="col-sm-6">
															<input type="text" class="form-control" id="insustry_name" autocomplete="off"  onchange="trim(this)"
																name="insustry_name" placeholder="Name of Industry" value="${linkage.industryName}">
																<span class="error_form text-danger" id="error_formfield2" style="display:none;" >Please enter name of industry.</span>
														</div>

													</div>
													
												<div class="form-group">
														<label class="control-label col-sm-2" for="year"> Industry 
														 Year<span class="text-danger">*</span>
														</label>

													<div class="col-sm-6">
														<input type="text" class="form-control datepickeryear"
															data-min-view-mode="years" data-start-view="2"
															data-format="yyyy"
															placeholder="Industry Year"
															id="indust_year"
															value="${linkage.industryFromYear}"
															name="indust_year" autocomplete="off"
															onkeypress='return restrictAlphabets(event)'
															onchange="trim(this)"> <span			
															class="error_form text-danger" id="error_formfield3"
															style="display: none;">Please enter year of industry.</span>
													</div>

											</div>
											
											<div class="form-group">

														<label class="control-label col-sm-2" for="smallheading">Research Lab
															Name<span class="text-danger">*</span>
														</label>

														<div class="col-sm-6">
															<input type="text" class="form-control" id="resrch_lab_name" autocomplete="off"  onchange="trim(this)"
																name="resrch_lab_name" placeholder="Name of Research Lab" value="${linkage.researchLabName}">
																<span class="error_form text-danger" id="error_formfield4" style="display:none;" >Please enter research lab.</span>
														</div>

											</div>
											
											<div class="form-group">
														<label class="control-label col-sm-2" for="year"> Research Lab 
														Year<span class="text-danger">*</span>
														</label>

													<div class="col-sm-6">
														<input type="text" class="form-control datepickeryear"
															data-min-view-mode="years" data-start-view="2"
															data-format="yyyy"
															placeholder="Research Lab Year"
															id="resrch_lab_year"
															value="${linkage.labFromYear}"
															name="resrch_lab_year" autocomplete="off"
															onkeypress='return restrictAlphabets(event)'
															onchange="trim(this)"> <span
															class="error_form text-danger" id="error_formfield5"
															style="display: none;">Please enter research lab year.</span>
													</div>

											</div>

													<div class="form-group">


														<label class="control-label col-sm-2" for="smallheading">From Date
															<span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<input type="text" class="form-control datepicker" id="from_date" onkeypress='return restrictAlphabets(event)'
																 onchange="trim(this)" name="from_date" placeholder="dd-mm-yyyy" value="${linkage.durationFrom}" autocomplete="off">
														<span class="error_form text-danger" id="error_fromToDate"	style="display: none;">From Date must be smaller than To Date </span>
														<span class="error_form text-danger" id="error_fromfield" style="display:none;" >Please enter from date.</span>
														</div>
													</div>

													<div class="form-group"> 

														<label class="control-label col-sm-2" for="smallheading">To Date
															<span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<input type="text" class="form-control datepicker" id="to_date" name="to_date" autocomplete="off"
																placeholder="dd-mm-yyyy" value="${linkage.durationTo}"  onchange="trim(this)"
																onkeypress='return restrictAlphabets(event)'>
																
															<span class="error_form text-danger" id="error_toToDate" style="display: none;">To Date must be greater than From Date </span>
															<span class="error_form text-danger" id="error_tofield" style="display:none;" >Please enter to date.</span>
														</div>

													</div>
													
													<div class="form-group">

														<label class="control-label col-sm-2" for="smallheading">Nature Of 
															Linkage<span class="text-danger">*</span>
														</label>

														<div class="col-sm-6">
															<input type="text" class="form-control" id="naturelinkage" autocomplete="off"  onchange="trim(this)"
																name="naturelinkage" placeholder="Nature Of Linkage" value="${linkage.natureOfLinkage}">
																<span class="error_form text-danger" id="error_formfield6" style="display:none;">Please enter nature of linkage.</span>
														</div>

											</div>
											
											<div class="form-group">

														<label class="control-label col-sm-2" for="smallheading">No. Of
															Student Participated<span class="text-danger">*</span>
														</label>

														<div class="col-sm-6">
															<input type="text" class="form-control" id="participate" autocomplete="off"  onchange="trim(this)" onkeypress='return restrictAlphabets(event)'
																name="participate" placeholder="Name of Research Lab" value="${linkage.noStudentParticipated}">
																<span class="error_form text-danger" id="error_formfield7" style="display:none;" >Please enter No. of participate and value must be greater than 0.</span>
														</div>

											</div>

													
													
													

													<div class="form-group">
														<div class="col-sm-offset-2 col-sm-10">
															<button type="submit" id="sub1" class="btn btn-primary" 
																	onclick="submit_f(1)"><i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save</button>
															
															<a href="${pageContext.request.contextPath}/showStudFacultyLinkage"><button
																id="sub2" type="button" class="btn btn-primary"><i class="${sessionScope.cancelIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Cancel</button></a>

															<input type="hidden" id="is_view" name="is_view" value="0">
														</div>
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
<script>
	function trim(el) {
		el.value = el.value.replace(/(^\s*)|(\s*$)/gi, ""). // removes leading and trailing spaces
		replace(/[ ]{2,}/gi, " "). // replaces multiple spaces with one space 
		replace(/\n +/, "\n"); // Removes spaces after newlines
		return;
	}
	
            
            	$(document).ready(function($){
            		
            		$("#form_sample_2").submit(function(e) {
            		
            			var isError=false;
            			 var errMsg=""; 
            			 
            				
            				if(!$("#link_title").val()){
             					 
             				isError=true;
             				
             				$("#link_title").addClass("has-error")
             				$("#error_formfield0").show();
             					//return false;
             				} else {
             					$("#error_formfield0").hide();
             				}
            				
           				if(!$("#part_inst").val()){
            					 
            				isError=true;
            				
            				$("#part_inst").addClass("has-error")
            				$("#error_formfield1").show();
            					//return false;
            				} else {
            					$("#error_formfield1").hide();
            				}
           				if(!$("#insustry_name").val()){ 
        					 
            				isError=true;
            				
            				
            				$("#insustry_name").addClass("has-error")
            				$("#error_formfield2").show();
            					//return false;
            				} else {
            					$("#error_formfield2").hide();
            				}   
           				
            				
            				
               				if(!$("#indust_year").val()){
                					 
                				isError=true;
                				
                				$("#indust_year").addClass("has-error")
                				$("#error_formfield3").show();
                					//return false;
                				} else {
                					$("#error_formfield3").hide();
                				}
               				
               				if(!$("#resrch_lab_name").val()){  
           					 
                				isError=true;
                				
                				$("#resrch_lab_name").addClass("has-error")
                				$("#error_formfield4").show();
                					//return false;
                				} else {
                					$("#error_formfield4").hide();
                				}
               				
               				if(!$("#resrch_lab_year").val()){  
              					 
                				isError=true;
                				
                				$("#resrch_lab_year").addClass("has-error")
                				$("#error_formfield5").show();
                					//return false;
                				} else {
                					$("#error_formfield5").hide();
                				}
               				
               				if(!$("#naturelinkage").val()){  
              					 
                				isError=true;
                				
                				$("#naturelinkage").addClass("has-error")
                				$("#error_formfield6").show();
                					//return false;
                				} else {
                					$("#error_formfield6").hide();
                				}
            			
               				if($("#participate").val() <=0 || !$("#participate").val()){   
            					 
                				isError=true;
                				
                				$("#participate").addClass("has-error")
                				$("#error_formfield7").show();
                					//return false;
                				} else {
                					$("#error_formfield7").hide();  
                				}
                				
	           			var from_date = document.getElementById("from_date").value;
	      				var to_date = document.getElementById("to_date").value;
	      				
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
    					 	$("#error_fromfield").hide();
    					 	$("#error_tofield").hide();
         		            return false;
         		           
         		        }else {
         					$("#error_fromToDate").hide();
         					$("#error_toToDate").hide();
         				}
         				
            				
           				if(!$("#from_date").val()){
       					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#from_date").addClass("has-error")
            				$("#error_fromfield").show();
            					//return false;
            				} else {
            					$("#error_fromfield").hide();
            				}
           				
           				if(!$("#to_date").val()){
          					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#to_date").addClass("has-error")
            				$("#error_tofield").show();
            					//return false;
            				} else {
            					$("#error_tofield").hide();
            				}
           				
           			
			            	 if (!isError) {
			            		 
								var x = confirm("Do you really want to submit the form?");
								if (x == true) {
									
									document.getElementById("sub1").disabled = true;
									document.getElementById("sub2").disabled = true;
									return  true;
								}
							}
            					   return false;
            			});
        });

</script>
	
	
<script type="text/javascript">
		
		function submit_f(view) {
			//alert(view);
			document.getElementById("is_view").value = view;

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

		$(function() {

			$('.datepickeryear').datepicker({
				autoclose : true,
				minViewMode : 2,
				format : 'yyyy'

			});
		});
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
			/*code: 48-57 Numbers
			  8  - Backspace,
			  35 - home key, 36 - End key
			  37-40: Arrow keys, 46 - Delete key*/
			function restrictAlphabets(e){
				var x=e.which||e.keycode;
				if((x>=48 && x<=57) || x==8 ||
					(x>=35 && x<=40)|| x==46)
					return true;
				else
					return false;
			}
		</script>
</body>
</html>