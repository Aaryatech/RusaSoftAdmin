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
<body onload="showIsReg()">
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
										action="${pageContext.request.contextPath}/insertPhdGuide"
										method="post"
										name="form_sample_2" id="form_sample_2">

										<div>

											<div class="col-xs-12">

														
														<input type="hidden" id="phdGiudeId" name="phdGiudeId" value="${phd.phdId}">
														<div class="form-group">

															<label class="control-label col-sm-2" for="smallheading">Ph.D.
																Guide <span class="text-danger">*</span>
															</label>


															<div class="col-sm-4">
														
															Yes <input type="radio" name="phdGuide" id="phdGuide" onclick="checkPhdGuide(this.value)"
															value="1"  ${phd.isPhdGuide == 1 ? 'checked' : '' }>
															
															No<input type="radio" name="phdGuide" id="phdGuide" value="0" onclick="checkPhdGuide(this.value)"
															${phd.isPhdGuide == 0 ? 'checked' : '' } >
													
																													</div>
														</div>

														
												<div class="form-group" id="ihide" style="display: none;">
														
														
														
														
														<div class="form-group">
															<label class="control-label col-sm-2" for="smallheading">Name
																of Ph.D. Scholar <span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="text" class="form-control" id="phd_scholar" onchange="trim(this)"
																	name="phd_scholar" placeholder="Name of Ph.D Scholar"
																	value="${phd.phdScholarName}" autocomplete="off">
																	<span class="error_form text-danger" id="error_formfield2" style="display:none;" >Please enter name of Ph.D. scholar.</span>
															</div>
														</div>
														
														<div class="form-group">
															<label class="control-label col-sm-2" for="smallheading">Aadhaar No.
																of Ph.D. Scholar <span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="text" class="form-control" id="aadhar" onchange="trim(this)"
																	name="aadhar" placeholder="Aadhaar No." maxlength="15"
																	value="${phd.aadhaarNo}" autocomplete="off">
																	<span class="error_form text-danger" id="error_field_adhar" style="display:none;" >
																	Please enter aadhaar No.</span>
															</div>
														</div>
														
														<div class="form-group">
															<label class="control-label col-sm-2" for="smallheading">Place/Name of 
																Research Center<span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="text" class="form-control" id="place_work" onchange="trim(this)"
																	name="place_work" placeholder="Place/Name of Research Center"
																	value="${phd.placeOfWork}" autocomplete="off">
																	<span class="error_form text-danger" id="error_field_place_work" style="display:none;" >
																	Please enter place/name of research center.</span>
															</div>
														</div>
														
														<div class="form-group">
															<label class="control-label col-sm-2" for="smallheading">University
																 <span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="text" class="form-control" id="university" onchange="trim(this)"
																	name="university" placeholder="University"
																	value="${phd.university}" autocomplete="off">
																	<span class="error_form text-danger" id="error_field_univrsity" style="display:none;" >Please enter university.</span>
															</div>
														</div>
														
														<div class="form-group">
															<label class="control-label col-sm-2" for="smallheading">Department Of
																Scholar <span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="text" class="form-control" id="phd_scholar_depart" onchange="trim(this)"
																	name="phd_scholar_depart" placeholder="Department of Scholar"
																	value="${phd.exVar1}" autocomplete="off">
																	<span class="error_form text-danger" id="error_formfield0" style="display:none;" >Please enter department of scholar.</span>
															</div>
														</div>

														<div class="form-group">

															<label class="control-label col-sm-2" for="smallheading">Year
																of Registration <span class="text-danger">*</span>
															</label>
															<%-- <input type="text" class="form-control datepickeryear"
																	required data-min-view-mode="years" data-start-view="2"
																	value="${phd.phdRegYear}" autocomplete="off"
																	placeholder="Year of Project Sanction" name="phd_year_reg"
																	id="phd_year_reg" data-format="yyyy">
																
															</div> --%>

													<div class="col-sm-6">
														<select id="phd_year_reg" name="phd_year_reg" class="form-control">
																																	
															<c:forEach items="${acaYearList}" var="acaYearList">
																		<c:choose>
																			<c:when test="${acaYearList.yearId==phd.phdRegYear}">
																			<option selected value="${acaYearList.yearId}">${acaYearList.academicYear}</option>

																			</c:when>
																			<c:otherwise>
																				<option value="${acaYearList.yearId}">${acaYearList.academicYear}</option>

																			</c:otherwise>

																		</c:choose>

																	</c:forEach>

													</select>
                                      				 </div>

														</div>

														<div class="form-group">

															<label class="control-label col-sm-2" for="smallheading">Title  
																of Thesis <span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<textarea id="phd_topic" name="phd_topic" onchange="trim(this)"
																	class="form-control" style="width: 100%;">${phd.phdTopic}</textarea>
																<span class="error_form text-danger" id="error_formfield3" style="display:none;" >Please enter title of thesis.</span>
															</div>
														</div>

														<div class="form-group">


															<!-- 
																<label class="control-label col-sm-2" for="smallheading">Date
																	of Registration: <span class="text-danger">*</span>
																</label>
																<div class="col-sm-2">
																	<input type="date" class="form-control" id="dob"
																		name="Grant" placeholder="Name of Ph.D Scholar"
																		value="" required>
																</div>
 -->

															<label class="control-label col-sm-2" for="smallheading">Awarded
																 <span class="text-danger">*</span>
															</label>


															<div class="col-sm-2">
														
															Yes <input type="radio" name="awarded" id="awarded" ${phd.isPhdAwarded == 1 ? 'checked' : '' }
																	 value="1" onclick="check(this.value)">
																	
															No<input type="radio" name="awarded" id="awarded" ${phd.isPhdAwarded == 0 ? 'checked' : '' }
																	 value="0" onclick="check(this.value)">
															
															</div>
														</div>

														<div class="form-group" id="abc" style="display: none;">

															<label class="control-label col-sm-2" 
																for="smallheading">Year of Awarded Ph.D.<span
																class="text-danger">*</span>
															</label>
															<%-- <div class="col-sm-6">
																<input type="text" class="form-control datepicker" id="phd_year_awarded" onkeypress='return restrictAlphabets(event)'
																	name="phd_year_awarded" placeholder="dd/mm/yyyy" value="${phd.phdAwardedYear}" autocomplete="off">
															</div> --%>
															<div class="col-sm-6">
														<select id="phd_year_awarded" name="phd_year_awarded" class="form-control" required>
																																	
															<c:forEach items="${acaYearList}" var="acaYearList">
																		<c:choose>
																			<c:when test="${acaYearList.yearId==phd.phdAwardedYear}">
																			<option selected value="${acaYearList.yearId}">${acaYearList.academicYear}</option>

																			</c:when>
																			<c:otherwise>
																				<option value="${acaYearList.yearId}">${acaYearList.academicYear}</option>

																			</c:otherwise>

																		</c:choose>

																	</c:forEach>

													</select>
                                      				 </div>

														</div>

													<div class="form-group">
															<label class="control-label col-sm-2" for="smallheading">Co-Guide
																 <span class="text-danger">*</span>
															</label>


															<div class="col-sm-4">
															Yes <input type="radio" name="coGuide" id="coGuide" ${phd.isCoGuide == 1 ? 'checked' : ''}
																	 value="1" onclick="checkCoGuide(this.value)">
																	
																	 No<input type="radio" onclick="checkCoGuide(this.value)" ${phd.isCoGuide == 0 ? 'checked' : ''}
																	  name="coGuide" id="coGuide" value="0">
															
															
															</div>
														</div>

														<div class="form-group" id="cogid" style="display: none;">

															<label class="control-label col-sm-2"   for="smallheading">Name
																of Co-Guide <span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="text" class="form-control " id="co_guide_name" autocomplete="off"  onchange="trim(this)"	 
																	name="co_guide_name" placeholder="Name of Co_Guide" value="${phd.coGuideName}">
																<span class="error_form text-danger" id="error_formfield1" style="display:none;" >Please enter name of co-guide.</span>
															</div>


														</div>
													</div>
														<div class="form-group">
															<div class="col-sm-offset-2 col-sm-10">
															<button type="submit" id="sub_button" class="btn btn-primary" 
																	onclick="submit_f(1)"><i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save</button>
															
																<a href="${pageContext.request.contextPath}/showPhdGuideList"><button
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
	$('#aadhar').on('keypress change blur oninput', function () {
	    $(this).val(function (index, value) {
	        return value.replace(/[^0-9]+/gi, '').replace(/(.{4})/g, '$1 ');
	    });
	});
	
            
            	$(document).ready(function($){
            		
            		$("#form_sample_2").submit(function(e) {
            		
            			var isError=false;
            			 var errMsg="";
            				
            			
            			 var radioValue = $("input[name='coGuide']:checked"). val();
     					//alert(radioValue);
     					if(radioValue==1){
     						if (!$("#co_guide_name").val()) {
     							isError = true;

     							$("#co_guide_name").addClass(
     									"has-error")
     							$("#error_formfield1").show()
     						} else {
     							$("#error_formfield1").hide()
     						}
     					}
     					
     					if(!$("#university").val()){
       					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#university").addClass("has-error")
            				$("#error_field_univrsity").show()
            					//return false;
            				} else {
            					$("#error_field_univrsity").hide()
            				}  
            			
     					/* if ( $("#is_registration1").is(":checked")) {
        					$("#error_acc_off_relDate").hide()
        				} */
           				if(!$("#phd_scholar").val()){
        					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#phd_scholar").addClass("has-error")
            				$("#error_formfield2").show()
            					//return false;
            				} else {
            					$("#error_formfield2").hide()
            				}  
            				
            				
           				if(!$("#phd_topic").val()){
       					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#phd_topic").addClass("has-error")
            				$("#error_formfield3").show()
            					//return false;
            				} else {
            					$("#error_formfield3").hide()
            				}
           				
           				if(!$("#phd_scholar_depart").val()){
          					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#phd_scholar_depart").addClass("has-error")
            				$("#error_formfield0").show()
            					//return false;
            				} else {
            					$("#error_formfield0").hide()
            				}
           				
           				if(!$("#aadhar").val()|| $("#aadhar").val().length<15) {
         					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#aadhar").addClass("has-error")
            				$("#error_field_adhar").show()
            					//return false;
            				} else {
            					$("#error_field_adhar").hide()
            				}
           				
           				if(!$("#place_work").val()){
        					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#place_work").addClass("has-error")
            				$("#error_field_place_work").show()
            					//return false;
            				} else {
            					$("#error_field_place_work").hide()
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
		function checkPhdGuide(phdguid) {
			

			if (phdguid == 1) {
				
				document.getElementById("ihide").style = "visible"
				

			} else if (phdguid == 0) {
				document.getElementById("ihide").style = "display:none"
				
			}

		}
		
		
		
			function check(qualType) {
				

				if (qualType == 1) {
					
					document.getElementById("abc").style = "visible"
					document.getElementById("phd_year_awarded").style = "visible"
					document.getElementById("phd_year_awarded").setAttribute("required","true");

					

				} else if (qualType == 0) {
					document.getElementById("abc").style = "display:none"
					document.getElementById("phd_year_awarded").style = "display:none"
					document.getElementById("phd_year_awarded").removeAttribute("required");
				}

			}
			
			function checkCoGuide(guide) {
				

				if (guide == 1) {

					document.getElementById("cogid").style = "visible"
					document.getElementById("co_guide_name").style = "visible"
					//document.getElementById("co_guide_name").setAttribute("required","true");

				} else if (guide == 0) {
					document.getElementById("cogid").style = "display:none"
					document.getElementById("co_guide_name").style = "display:none"
					//document.getElementById("co_guide_name").removeAttribute("required");
				}

			}
			
			/* function hideText() {
				//alert("hii");
				
				document.getElementById("abc").style = "visible"
				document.getElementById("phd_year_awarded").style = "visible"
				document.getElementById("phd_year_awarded").setAttribute("required","true");
				
				document.getElementById("cogid").style = "visible"
				document.getElementById("co_guide_name").style = "visible"
				document.getElementById("co_guide_name").setAttribute("required","true");

			} */
			
			
			function showIsReg(){
				
				var x = ${phd.phdId};
				//var a = ${phd.isCoGuide};
				//alert("Hi"+a);
				if(x>0){
					var isRel = ${phd.isCoGuide};
					//alert("Hi"+isRel);
					if(isRel == null){
						document.getElementById("cogid").style.display = "none";
					}else{
						document.getElementById("cogid").style.display = "block";
					}
				}
				 
				var isTaxInc = $("input[name=coGuide]:checked").val();
				
				if(isTaxInc == 1){
					document.getElementById("cogid").style.display = "block";
				} else{
					document.getElementById("cogid").style.display = "none";
				} 
				//*************************************************************//
				 var val = ${phd.isPhdAwarded}
				// alert("Hi"+val);
				if(val > 0 || x > 0){
					
					var isAwrd = ${phd.isPhdAwarded};
					if(isAwrd == 0){
						document.getElementById("abc").style.display = "none";
					}else{
						document.getElementById("abc").style.display = "block";
					}
					
				}
				
				var isTest = $("input[name=awarded]:checked").val();
				
				if(isTest == 1){
					document.getElementById("abc").style.display = "block";
				} else{
					document.getElementById("abc").style.display = "none";
				} 
				
				var isPhdGuid = $("input[name=phdGuide]:checked").val();
				
				if(isPhdGuid == 1){
					document.getElementById("ihide").style.display = "block";
				} else{
					document.getElementById("ihide").style.display = "none";
				} 
			}
			
		</script>

	</div>
	<!-- END CONTAINER -->
	<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->




</body>
</html>