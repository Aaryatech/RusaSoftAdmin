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
<!-- onload="check(${phd.isPhdAwarded})" onload="checkCoGuide(${phd.isCoGuide})" -->
<body onload="showIsReg(${tMous.exInt1})">
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
				<div class="col-lg-12">
					<section class="box ">

						<header class="panel_header">
							<h2 class="title pull-left">${title}</h2>

							<%-- <div class="actions panel_actions pull-right">
								<a href="${pageContext.request.contextPath}/showPhdGuideList"><button
										type="button" class="btn btn-info">Back</button></a>
							</div> --%>

						</header>
						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertResrchDevMou"
										method="post"
										name="form_sample_2" id="form_sample_2">

										<div>

											<div class="col-xs-12">

														
														<input type="hidden" id="inst_reasearch_dev_mouId" name="inst_reasearch_dev_mouId"
														 value="${tMous.instReasearchDevMouId}">
														
														
													<div class="form-group">
											<label class="control-label col-sm-2" for="status">Select  Research Development
												MoU<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<select id="activity_id" name="activity_id" class="form-control" onclick="checkPhdGuide(this.value)">
													<c:choose>
													<c:when test="${tMous.exInt1==1}">
														<option selected value="0">Any Other</option>
														<c:forEach items="${mouList}" var="mouList">
																<option value="${mouList.researchDevMouId}">${mouList.mouTitle}</option>
													</c:forEach>
													</c:when>
													
													<c:otherwise>
														<c:forEach items="${mouList}" var="mouList">
															 <c:choose>
																<c:when test="${mouList.researchDevMouId==tMous.researchDevMouId}">
																	<option selected value="${mouList.researchDevMouId}">${mouList.mouTitle}</option>
	
																</c:when>
																<c:otherwise> 
																	<option value="${mouList.researchDevMouId}">${mouList.mouTitle}</option>
	
																 </c:otherwise>
	
															</c:choose> 
	
														</c:forEach>
													
													
														<option value="0">Any Other</option>
														</c:otherwise>
														</c:choose>
												</select>
											</div>
										</div>

													
														
												<div class="form-group" id="ihide" style="display: none;">
														<div class="form-group">
															<label class="control-label col-sm-2" for="smallheading">MoU
																Title<span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="text" class="form-control" id="mou_name" onchange="trim(this)"
																	name="mou_name" placeholder="Title MOU"
																	value="${tMous.mouTitle}" autocomplete="off">
																	<span class="error_form text-danger" id="error_formfield1" style="display:none;" >Please enter title of mou.</span>
															</div>
														</div>
													</div>
														
														<div class="form-group">
															<label class="control-label col-sm-2" for="smallheading">Organization
																Name<span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="text" class="form-control" id="org_name" onchange="trim(this)"
																	name="org_name" placeholder="Organization Name"
																	value="${tMous.orgName}" autocomplete="off">
																	<span class="error_form text-danger" id="error_formfield2" style="display:none;" >Please enter name organization.</span>
															</div>
														</div>

														
														
														<div class="form-group">
											<label class="control-label col-sm-2" for="year"
												style="text-align: left;">
												MoU Signed Year<span class="text-danger">*</span>
											</label>

											<div class="col-sm-6">
												<input type="text" class="form-control datepickeryear"
													data-min-view-mode="years" data-start-view="2"
													data-format="yyyy"
													placeholder="Year of MoU Signed"
													id="mou_signed_year"
													value="${tMous.mouSignedYear}"
													name="mou_signed_year" autocomplete="off"
													onkeypress='return restrictAlphabets(event)'
													onchange="trim(this)"> <span
													class="error_form text-danger" id="error_formfield3"
													style="display: none;">Please enter mou signed year.</span>
											</div>

										</div>
													
														<div class="form-group">
															<label class="control-label col-sm-2" for="smallheading">From Date
																 <span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">  
																<input type="text" class="form-control datepicker" id="from_date" onchange="trim(this)"
																	name="from_date" placeholder="dd/mm/yyyy" onkeypress='return restrictAlphabets(event)'
																	value="${tMous.durFromdt}" autocomplete="off">
																	<span class="error_form text-danger" id="error_fdate" style="display:none;" >Please enter from date.</span>
																	<span class="error_form text-danger" id="error_fromToDate"	style="display: none;">From Date must be smaller than To Date </span>
															</div>
														</div>

														<div class="form-group">
															<label class="control-label col-sm-2" for="smallheading">To Date
															 <span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="text" class="form-control datepicker" id="to_date" onchange="trim(this)"
																		name="to_date" placeholder="dd/mm/yyyy" onkeypress='return restrictAlphabets(event)'
																	value="${tMous.durTodt}" autocomplete="off">
																	<span class="error_form text-danger" id="error_tdate" style="display:none;" >Please enter to date.</span>
																<span class="error_form text-danger" id="error_toToDate" style="display: none;">To Date must be greater than From Date </span>
															</div>
														</div>
														
														<div class="form-group">
															<label class="control-label col-sm-2" for="smallheading"> MoU Activities 
															<span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="text" class="form-control" id="mou_avtivity" onchange="trim(this)"
																		name="mou_avtivity" placeholder="MoU Activity" 
																	value="${tMous.activitiesMou}" autocomplete="off">
																	<span class="error_form text-danger" id="error_formfield4" style="display:none;" >Please enter mou activity.</span>
															</div>
														</div>
														
														<div class="form-group">
															<label class="control-label col-sm-2" for="smallheading">No of 
															Student Benefited<span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="text" class="form-control" id="no_stud" onchange="trim(this)" onFocus="clearDefault(this)"
																		name="no_stud" placeholder="No. of Student Benefited" onkeypress='return restrictAlphabets(event)'
																	value="${tMous.noOfStudBenif}" autocomplete="off">
																	<span class="error_form text-danger" id="error_formfield5" style="display:none;" >Please enter No. of student benefited and value must be greater than 0.</span>
															</div>
														</div>
														
														<div class="form-group">
															<label class="control-label col-sm-2" for="smallheading">No of 
															Staff Benefited <span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="text" class="form-control" id="no_faculty" onchange="trim(this)"onFocus="clearDefault(this)"
																		name="no_faculty" placeholder="No. of Faculty Benefited" onkeypress='return restrictAlphabets(event)'
																	value="${tMous.noOfStaffBenif}" autocomplete="off">
																	<span class="error_form text-danger" id="error_formfield6" style="display:none;" >Please enter No. of faculty benefited and value must be greater than 0.</span>
															</div>
														</div>
													
														<div class="form-group">
															<div class="col-sm-offset-2 col-sm-10">
															<button type="submit" id="sub_button" class="btn btn-primary" 
																	onclick="submit_f(1)"><i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save</button>
															
																<a href="${pageContext.request.contextPath}/showInstResrchDevMous"><button
																   id="sub2" type="button" class="btn btn-primary"><i class="${sessionScope.cancelIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Cancel</button></a>
															<input type="hidden" id="is_view" name="is_view" value="0">
															</div>
														</div>

													</div>


													<div class="clearfix"></div>

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
            				
     					
            			 var radioValue = $("#activity_id").val(); /* $("input[name='activity_id']:checked"). val(); */
      					//alert(radioValue);
      					if(radioValue==0){
     						if (!$("#mou_name").val()) {
     							isError = true;

     							$("#mou_name").addClass(
     									"has-error")
     							$("#error_formfield1").show()
     						} else {
     							$("#error_formfield1").hide()
     						}
      					}
     				
     						 
           				if(!$("#org_name").val()){
        					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#org_name").addClass("has-error")
            				$("#error_formfield2").show()
            					//return false;
            				} else {
            					$("#error_formfield2").hide()
            				}  
            				
            				
           				if( !$("#mou_signed_year").val()){
       					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#mou_signed_year").addClass("has-error")
            				$("#error_formfield3").show()
            					//return false;
            				} else {
            					$("#error_formfield3").hide()
            				}
           				
           						
           				
           				if( !$("#mou_avtivity").val()){
         					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#mou_avtivity").addClass("has-error")
            				$("#error_formfield4").show()
            					//return false;
            				} else {
            					$("#error_formfield4").hide()
            				}
           				
           				if($("#no_stud").val() <= 0 || !$("#no_stud").val()){
         					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#ttl_faculty").addClass("has-error")
            				$("#error_formfield5").show()
            					//return false;
            				} else {
            					$("#error_formfield5").hide()
            				}
           				
           				if($("#no_faculty").val() <= 0 || !$("#no_faculty").val()){
        					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#ttl_faculty").addClass("has-error")
            				$("#error_formfield6").show()
            					//return false;
            				} else {
            					$("#error_formfield6").hide()
            				}
           				
           				if (!$("#from_date").val()) {

        					isError = true;

        					$("#from_date").addClass("has-error")
        					$("#error_fdate").show()
        					//return false;
        				} else {
        					$("#error_fdate").hide();
        				}

        				if (!$("#to_date").val()) {

        					isError = true;

        					$("#to_date").addClass("has-error")
        					$("#error_tdate").show()
        					//return false;
        				} else {
        					$("#error_tdate").hide();
        				}
        				
        				var from_date = document.getElementById("from_date").value;
         				var to_date = document.getElementById("to_date").value;
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
        				 	$("#error_fdate").hide();
        				 	$("#error_tdate").hide();
         		            return false;
         		           
         		        }else {
         					$("#error_fromToDate").hide();
         					$("#error_toToDate").hide();
         				}
         				

        				if ($("#beneficiaryMOU").val() == -1) {

        					isError = true;

        					$("#error_benf").show()
        					//return fregister_useralse;
        				} else {
        					$("#error_benfmou").hide()
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
    
    $(function () {
		 
        $('.datepicker').datepicker({
			autoclose: true,
            format: "dd-mm-yyyy",
            changeYear:true,
            changeMonth:true

		});
    });
    
</script>
<script type="text/javascript">
function clearDefault(a){
	if(a.defaultValue==0)
	{
		a.value=""
	}
	};
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
		<script type="text/javascript">
		function checkPhdGuide(activity) {
			
//alert(activity);
			if (activity == 0) {
				
				document.getElementById("ihide").style = "visible"
				

			} else  {
				document.getElementById("ihide").style = "display:none"
				
			}

		}
		
		function showIsReg(act){
			
				//alert(act);
				var isActivity = act; //$("input[name=isActivity]:checked").val();
				
				if(isActivity == 1){
					document.getElementById("ihide").style.display = "block";
				} else{
					document.getElementById("ihide").style.display = "none";
				} 
				
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

	</div>
	<!-- END CONTAINER -->
	<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->




</body>
</html>