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
										action="${pageContext.request.contextPath}/insertOutReachContri"
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

									<input type="hidden" value="${facContri.conId}" id="fac_contriId" name="fac_contriId">


													<div class="form-group">

														<label class="control-label col-sm-2" for="status">Select
															Level <span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<select id="level" name="level"
																class="form-control" required>
																<c:choose>
																	<c:when test="${facContri.conLevel eq 'BOS'}">
																		<option selected value="BOS">BOS</option>
																		<option value="Faculty">Faculty</option>
																		<option value="Academic Council">Academic Council</option>
																	
																	</c:when>
																	
																	<c:when test="${facContri.conLevel eq 'Faculty'}">
																		<option value="BOS">BOS</option>
																		<option selected value="Faculty">Faculty</option>
																		<option value="Academic Council">Academic Council</option>
																	
																	</c:when>
																	<c:when test="${facContri.conLevel eq 'Academic Council'}">
																		<option value="BOS">BOS</option>
																		<option value="Faculty">Faculty</option>
																		<option selected value="Academic Council">Academic Council</option>
																	
																	</c:when>
																	
																	<c:otherwise>
																		<option value="BOS">BOS</option>
																		<option value="Faculty">Faculty</option>
																		<option value="Academic Council">Academic Council</option>
																	</c:otherwise>
																
																</c:choose>
																
															</select>
														</div>



													</div>
													<div class="form-group">
														<label class="control-label col-sm-2" for="smallheading">Name
															<span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<input type="text" class="form-control" id="con_name" autocomplete="off" onchange="trim(this)"
															name="con_name" placeholder="Name " value="${facContri.conName}">
														<span class="error_form text-danger" id="error_formfield1" style="display:none;" >Please enter name.</span>
														</div>


													</div>

													<div class="form-group">

														<label class="control-label col-sm-2" for="smallheading">University
															<span class="text-danger">*</span>
														</label>

														<div class="col-sm-6">
															<input type="text" class="form-control" id="university" autocomplete="off"  onchange="trim(this)"
																name="university" placeholder="University" value="${facContri.conUniversity}">
																<span class="error_form text-danger" id="error_formfield2" style="display:none;" >Please enter university.</span>
														</div>

													</div>

													<div class="form-group">


														<label class="control-label col-sm-2" for="smallheading">From Date
															<span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<input type="text" class="form-control datepicker" id="from_date" onkeypress='return restrictAlphabets(event)'
																 onchange="trim(this)" name="from_date" placeholder="From" value="${facContri.conFrom}" autocomplete="off">
														<span class="error_form text-danger" id="error_fromToDate"	style="display: none;">From Date must be smaller than To Date </span>
														<span class="error_form text-danger" id="error_formfield3" style="display:none;" >Please enter from date.</span>
														</div>
													</div>

													<div class="form-group"> 

														<label class="control-label col-sm-2" for="smallheading">To Date
															<span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<input type="text" class="form-control datepicker" id="to_date" name="to_date" autocomplete="off"
																placeholder="To" value="${facContri.conTo}"  onchange="trim(this)"
																onkeypress='return restrictAlphabets(event)'>
																
															<span class="error_form text-danger" id="error_toToDate" style="display: none;">To Date must be greater than From Date </span>
															<span class="error_form text-danger" id="error_formfield4" style="display:none;" >Please enter to date.</span>
														</div>

													</div>

													<div class="form-group">


														<label class="control-label col-sm-2" for="smallheading">Examination
															Paper Setting <span class="text-danger">*</span>
														</label>
														<div class="col-sm-2">
														<c:choose>
															<c:when test="${facContri.conExamSetting==1}">
															Yes <input type="radio" name="examSetting"
																id="examSetting" checked value="1">
															No<input type="radio" name="examSetting"
															 id="examSetting" value="0">
															 </c:when>
															
															 <c:when test="${facContri.conExamSetting==0}">
															Yes <input type="radio" name="examSetting"
																id="examSetting"  value="1">
															No<input type="radio" name="examSetting"
															 id="examSetting"  checked value="0">
															 </c:when>
															 <c:otherwise>
															 Yes <input type="radio" name="examSetting"
																id="examSetting" checked value="1">
															No<input type="radio" name="examSetting" id="examSetting" value="0">
															 </c:otherwise>
															 </c:choose>
														</div>
													</div>

													<div class="form-group">

														<label class="control-label col-sm-2" for="smallheading">Answer
															Sheet Evaluation <span class="text-danger">*</span>
														</label>
														<div class="col-sm-2">
														<c:choose>
															<c:when test="${facContri.conAsEvaluation==1}">
																Yes <input type="radio" name="ansEvaluation" id="ansEvaluation" checked value="1"> 
																No<input type="radio" name="ansEvaluation" id="ansEvaluation" value="0">
															</c:when>
															
															<c:when test="${facContri.conAsEvaluation==0}">
																Yes <input type="radio" name="ansEvaluation" id="ansEvaluation"  value="1"> 
																No<input type="radio" name="ansEvaluation" id="ansEvaluation" checked value="0">
															</c:when>
															<c:otherwise>
																Yes <input type="radio" name="ansEvaluation" id="ansEvaluation" checked value="1"> 
																No<input type="radio" name="ansEvaluation" id="ansEvaluation" value="0">
															</c:otherwise>
														</c:choose>
														
														</div>
													</div>

													<div class="form-group">

														<label class="control-label col-sm-2" for="smallheading">Answer
															Sheet Moderation <span class="text-danger">*</span>
														</label>
														<div class="col-sm-2">
														<c:choose>
															<c:when test="${facContri.conAsModeration==1}">
															Yes <input type="radio" name="ansmod" id="ansmod" checked value="1">
															
															No<input type="radio" name="ansmod"	id="ansmod" value="0">
														</c:when>
														<c:when test="${facContri.conAsModeration==0}">
															Yes <input type="radio" name="ansmod" id="ansmod"  value="1">
															
															No<input type="radio" name="ansmod"	id="ansmod" checked value="0">
														</c:when>
														<c:otherwise>
														Yes <input type="radio" name="ansmod" id="ansmod" checked value="1">
														No<input type="radio" name="ansmod"	id="ansmod" value="0">
														</c:otherwise>
														
														</c:choose>
														
														</div>

													</div>

													<div class="form-group">
														<div class="col-sm-offset-2 col-sm-10">
															<button type="submit" id="sub_button" class="btn btn-primary" 
																	onclick="submit_f(1)"><i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save</button>
															
															<a href="${pageContext.request.contextPath}/showOutReachContriList"><button
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
            				
           				if(!$("#con_name").val()){
            					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#con_name").addClass("has-error")
            				$("#error_formfield1").show()
            					//return false;
            				} else {
            					$("#error_formfield1").hide()
            				}
           				if(!$("#university").val()){
        					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#conf_date").addClass("has-error")
            				$("#error_formfield2").show()
            					//return false;
            				} else {
            					$("#error_formfield2").hide()
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
    					 	$("#error_formfield3").hide()
    					 	$("#error_formfield4").hide()
         		            return false;
         		           
         		        }else {
         					$("#error_fromToDate").hide();
         					$("#error_toToDate").hide();
         				}
         				
            				
           				if(!$("#from_date").val()){
       					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#from_date").addClass("has-error")
            				$("#error_formfield3").show()
            					//return false;
            				} else {
            					$("#error_formfield3").hide()
            				}
           				
           				if(!$("#to_date").val()){
          					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#to_date").addClass("has-error")
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
</body>
</html>