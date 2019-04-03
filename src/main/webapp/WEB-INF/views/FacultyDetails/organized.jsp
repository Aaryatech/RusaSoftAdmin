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
								<a href="${pageContext.request.contextPath}/showOrganizedList"><button
										type="button" class="btn btn-info">Back</button></a> 
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertFacultyActivity"
										method="post" 
										name="form_sample_2" id="form_sample_2"	>
									

										
												<div>


													<div class="col-xs-12">


															<!-- <h5 class="title pull-left">
																<strong>Publication/Presentation Details</strong>
															</h5> -->

															<div class="col-xs-12"></div>
															
															<input type="hidden"  id="activity_id"
																		name="activity_id" value="${activity.activityId}">
																		
															<div class="form-group">
													<label class="control-label col-sm-2" for="status">Select
													Course	<span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<select id="activity_type" name="activity_type"
															class="form-control" >
															<option value="-1">Select</option>		
															<c:forEach items="${facultyOutreachTypeList}" var="outtype">
																	<c:choose>
																	<c:when test="${outtype.typeId == activity.activityType}">
																	<option selected value="${outtype.typeId}">${outtype.typeName}</option>
																	</c:when>
																	<c:otherwise>
																			<option value="${outtype.typeId}">${outtype.typeName}</option>
																	</c:otherwise>
																	</c:choose>
																	</c:forEach>
														</select>
		<span class="error_form text-danger" id="error_type" style="display:none;" >Please Select Outreach Type  </span>
	
													</div>
												</div>
															
												<div class="form-group">

													<label class="control-label col-sm-2" for="smallheading">Name
														of Activity <span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="activity_name" autocomplete="off" onchange="trim(this)"
															name="activity_name" placeholder="Name of Activity" value="${activity.activityName}"
															>
	<span class="error_form text-danger" id="error_name" style="display:none;" >Please Enter Outreach Name  </span>
													</div>

												</div>
															
															
												<div class="form-group">

													<label class="control-label col-sm-2" for="status">Select
														Level<span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<select id="activity_level" name="activity_level"
															class="form-control" >
															<option value="-1">Select</option>	
															<c:choose>
															<c:when test="${activity.activityLevel eq 'International' }">
																<option selected  value="International">International</option>
																<option value="National">National</option>
																<option value="State">State</option>
																<option value="Regional">Regional</option>
															</c:when>
															
															
															<c:when test="${activity.activityLevel eq 'National' }">
																<option  value="International">International</option>
																<option selected value="National">National</option>
																<option value="State">State</option>
																<option value="Regional">Regional</option>
															</c:when>
															
															
															<c:when test="${activity.activityLevel eq 'State' }">
																<option value="International">International</option>
																<option value="National">National</option>
																<option selected value="State">State</option>
																<option value="Regional">Regional</option>
															</c:when>
															
															
															<c:when test="${activity.activityLevel eq 'Regional' }">
																<option  value="International">International</option>
																<option value="National">National</option>
																<option value="State">State</option>
																<option selected value="Regional">Regional</option>
															</c:when>
															
															
															
															<c:otherwise>
															<option value="International">International</option>
															<option value="National">National</option>
															<option value="State">State</option>
															<option value="Regional">Regional</option>
															
															</c:otherwise>
															</c:choose>
															

														</select>
<span class="error_form text-danger" id="error_level" style="display:none;" >Please Select Outreach Level  </span>

													</div>

												</div>

															<div class="form-group">
															<label class="control-label col-sm-2" for="page_order">Date of
																Activity <span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="text" class="form-control datepicker" id="activity_date" onchange="trim(this)"
																 	value="${activity.activityDate}" name="activity_date"  autocomplete="off"
																 	 placeholder="dd/mm/yyyy"  >
						<span class="error_form text-danger" id="error_date" style="display:none;" >Please Select Outreach Date  </span>
															</div>
														</div>

															<div class="form-group">

													<label class="control-label col-sm-2" for="smallheading">No
														of Participants <span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="text"  class="form-control" id="activity_part"  onchange="trim(this)"
															name="activity_part" placeholder="No of Participants" value="${activity.activityParticipants}"
														autocomplete="off"	>
						<span class="error_form text-danger" id="error_part" style="display:none;" >Please Select Enter No of Participants </span>

													</div>
												</div>



												<div class="form-group">
													<label class="control-label col-sm-2" for="smallheading">Funded
														By <span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="activity_found" autocomplete="off" onchange="trim(this)"
															name="activity_found" placeholder="Funded By" value="${activity.activityFundedBy}" >
		<span class="error_form text-danger" id="error_funded" style="display:none;" >Please Select Enter Funded By </span>
					
													</div>



												</div>
												<div class="form-group">

													<label class="control-label col-sm-2" for="smallheading">Amount
														Sanctioned <span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="text"  class="form-control" id="amt_sanc"   onchange="trim(this)"
															name="amt_sanc" placeholder="Amount Sanctioned" value="${activity.activityAmountSanctioned}"
															autocomplete="off" >
			<span class="error_form text-danger" id="error_amt" style="display:none;" >Please Select Enter Amount Sanctioned </span>
	
													</div>
												</div>



												<div class="form-group">

													<label class="control-label col-sm-2" for="smallheading">Amount
														Utilized <span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="amt_utilise"  onchange="chkSncAmt()"
															name="amt_utilise" placeholder="Amount Utilized" value="${activity.activityAmountUtilised}"
															autocomplete="off"  >
				<span class="error_form text-danger" id="error_utilized" style="display:none;" >Please Enter Amount Utilized </span>
	
													</div>


												</div>






														<div class="form-group">
															<div class="col-sm-offset-2 col-sm-10">
															<input type="submit" id="sub1" class="btn btn-primary" onclick="submit_f(1)" value="Save">
																<input type="submit"  id="sub2" class="btn btn-primary" onclick="submit_f(0)" value="Save &
																		Next">
																<%-- <a href="${pageContext.request.contextPath}/hodList"><button
																		type="button" class="btn btn-primary">S</button></a> --%>
																<button type="reset" class="btn btn-default">Reset</button>
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
           
             function numbersOnlyNotZero(id_number) {

                 
                 var mob = /^[1-9][0-9]+$/;

                  
                 if (mob.test($.trim(id_number)) == false) {

                	// alert("asdf")
                     //alert("Please enter a valid email address .");
                     return false;

                 }
                 return true;
             }



             
            
            	$(document).ready(function($){
          //  alert("hii....");
            		$("#form_sample_2").submit(function(e) {
            			 var isError=false;
            			 var errMsg="";
            			 
            			 
            			 if($("#activity_type").val()== -1 ){
          		            
              				isError=true;
              				
              				$("#error_type").show()
              					//return fregister_useralse;
              				} else {
              					$("#error_type").hide()
              				}
          				
          				
            				
            			 if(!$("#activity_name").val()){
        					 
             				isError=true;
             				
             				
             				$("#activity_name").addClass("has-error")
             				$("#error_name").show()
             					//return false;
             				} else {
             					$("#error_name").hide()
             				}
            			 
            			 if($("#activity_level").val()== -1 ){
         		            
             				isError=true;
             				
             				$("#error_level").show()
             					//return fregister_useralse;
             				} else {
             					$("#error_level").hide()
             				}

            			
            			 if(!$("#activity_date").val()){
        					 
              				isError=true;
              				
              				
              				$("#activity_date").addClass("has-error")
              				$("#error_date").show()
              					//return false;
              				} else {
              					$("#error_date").hide()
              				}
            			 
            			 var prt = parseInt($("#activity_part").val());
            			 
            			// alert(numbersOnlyNotZero(prt));

         				if(!$("#activity_part").val() || !numbersOnlyNotZero($("#activity_part").val())){
         
         					isError=true;
         				$("#error_part").show()
         					
         				} else {
         					$("#error_part").hide()
         				}

            			 if(!$("#activity_found").val()){
        					 
              				isError=true;
              				
              				
              				$("#activity_found").addClass("has-error")
              				$("#error_funded").show()
              					//return false;
              				} else {
              					$("#error_funded").hide()
              				}
            			 
            			 
            			 
            			 //alert("amt :"+$("#activity_part").val());
            			 
            			// alert("amt :"+$("#amt_sanc").val());
          				if(!$("#amt_sanc").val() || !numbersOnlyNotZero($("#amt_sanc").val())){
          			      
          					isError=true;
           				
          				$("#error_amt").show()
          					//return fregister_useralse;
          				} else {
          					$("#error_amt").hide()
          				}
          				
          		
          		
          				if(!$("#amt_utilise").val() || !numbersOnlyNotZero($("#amt_utilise").val())){
          		          
          					isError=true;
              				$("#error_utilized").show()
              					//return fregister_useralse;
              				} else {
              					$("#error_utilized").hide()
              				}
          			

							if (!isError) {
								var x = confirm("Do you really want to submit the form?");
								if (x == true) {
									return  true;
									 document.getElementById("sub1").disabled=true;
           						  document.getElementById("sub2").disabled=true;
								}
							}
            
            			  
            						 
            					   return false;
            				} );
            	});
			//
			
			    
          
        </script>
<script type="text/javascript">
		function submit_f(view) {
			//alert(view);
			document.getElementById("is_view").value = view;

		}
		
		function chkSncAmt(){
			
			var sancAmt = parseFloat(document.getElementById("amt_sanc").value); 
			var utlAmt = parseFloat(document.getElementById("amt_utilise").value); 
			
			
			if(utlAmt>sancAmt){
				document.getElementById("amt_utilise").value = "";
				alert("Utilized amount is more than Sanctioned amount!");
				return false;
				
			}
		}
</script>	
	
<script type="text/javascript">
 
    
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

