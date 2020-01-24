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
<!-- onload="check(${phd.isPhdAwarded})" onload="checkCoGuide(${phd.isCoGuide})" -->
<body onload="showIsReg(${tExtAct.exInt1})">
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
										action="${pageContext.request.contextPath}/insertawrdRecgAgnstExtAxt"
										method="post"
										name="form_sample_2" id="form_sample_2">
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

										<div>

											<div class="col-xs-12">

														
														<input type="hidden" id="award_recg_id" name="award_recg_id"
														 value="${araea.awrdRecgAgnstExtActId}">
											
														
														<div class="form-group">
															<label class="control-label col-sm-2" for="smallheading">Name of 
																Activity<span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="text" class="form-control" id="name_act" onchange="trim(this)"
																	name="name_act" placeholder="Name of Activity" 
																	value="${araea.actName}" autocomplete="off">
																	<span class="error_form text-danger" id="error_formfield1" style="display:none;" >
																	Please enter name of activity.</span>
															</div>
														</div>

														
														<div class="form-group">
															<label class="control-label col-sm-2" for="smallheading">Name of 
																Award/Recognition<span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="text" class="form-control" id="name_awrd_recg" onchange="trim(this)"
																	name="name_awrd_recg" placeholder="Name of Award/Recognition" 
																	value="${araea.awardRecogName}" autocomplete="off">
																	<span class="error_form text-danger" id="error_formfield2" style="display:none;" >
																	Please enter name of award/recognition.</span>
															</div>
														</div>

														<div class="form-group">
															<label class="control-label col-sm-2" for="smallheading">Name of 
																Awarding body<span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="text" class="form-control" id="name_awrd_body" onchange="trim(this)"
																	name="name_awrd_body" placeholder="Name of Awarding body" 
																	value="${araea.nameAwardingBody}" autocomplete="off">
																	<span class="error_form text-danger" id="error_formfield3" style="display:none;" >
																	Please enter name of awarding body .</span>
															</div>
														</div>

														<div class="form-group">
															<label class="control-label col-sm-2" for="smallheading">Year of 
																Award<span class="text-danger">*</span>
															</label>
																<div class="col-sm-6">
																	<input type="text" class="form-control datepickeryear"
																		data-min-view-mode="years" data-start-view="2"
																		data-format="yyyy"
																		placeholder="Year of Award"
																		id="awrd_year"
																		value="${araea.awardYear}"
																		name="awrd_year" autocomplete="off"
																		onkeypress='return restrictAlphabets(event)'
																		onchange="trim(this)"> <span
																		class="error_form text-danger" id="error_formfield4"
																		style="display: none;">Please enter year of award
																		qualification.</span>
																</div>
														</div>
													
														<div class="form-group">
															<div class="col-sm-offset-2 col-sm-10">
															<button type="submit" id="sub_button" class="btn btn-primary" 
																	onclick="submit_f(1)"><i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save</button>
															
																<a href="${pageContext.request.contextPath}/awrdRecogAgnstExtAct"><button
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
      					
            			 	    	
           				if(!$("#name_act").val()){
        					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#name_act").addClass("has-error")
            				$("#error_formfield1").show()
            					//return false;
            				} else {
            					$("#error_formfield1").hide()
            				}  
            				
            				
           				if(!$("#name_awrd_recg").val()){
       					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#name_awrd_recg").addClass("has-error")
            				$("#error_formfield2").show()
            					//return false;
            				} else {
            					$("#error_formfield2").hide()
            				}
           				
           						
           				
           				if(!$("#name_awrd_body").val()){
         					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#name_awrd_body").addClass("has-error")
            				$("#error_formfield3").show()
            					//return false;
            				} else {
            					$("#error_formfield3").hide()
            				}
           				
           				if(!$("#awrd_year").val()){
         					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#awrd_year").addClass("has-error")
            				$("#error_formfield4").show()
            					//return false;
            				} else {
            					$("#error_formfield4").hide()
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