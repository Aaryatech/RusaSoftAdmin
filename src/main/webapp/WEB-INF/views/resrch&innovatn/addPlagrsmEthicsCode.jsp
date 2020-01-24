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

<body onload="hideText()">
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
										action="${pageContext.request.contextPath}/insertPlagCodeEthic"
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

														
														<input type="hidden" id="soft_id" name="soft_id" value="${plagrsm.plagCodeEthcId}">
														<div class="form-group">

															<label class="control-label col-sm-2" for="smallheading">Whether Plagiarism Detecting 
																Software Available<span class="text-danger">*</span>
															</label>


															<div class="col-sm-4">
														
															Yes <input type="radio" name="is_soft_avalbl" id="is_soft_avalbl" ${plagrsm.isSoftwrAvabl == 1 ? 'checked' : '' } onclick="checkPhdGuide(this.value)" value="1" >
															
															No<input type="radio" name="is_soft_avalbl" id="is_soft_avalbl" ${plagrsm.isSoftwrAvabl == 0 ? 'checked' : '' } onclick="checkPhdGuide(this.value)" value="0">
													
														</div>
													</div>

														
							<!-- Container Start --><div class="form-group" id="ihide" style="display: none;">
														
														<div class="form-group">
															<label class="control-label col-sm-2" for="smallheading">Name
																of Software<span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="text" class="form-control" id="software_name" onchange="trim(this)"
																	name="software_name" placeholder="Name of Software"
																	value="${plagrsm.nameOfSoftwr}" autocomplete="off">
																	<span class="error_form text-danger" id="soft_errfield" style="display:none;" >Please enter name of software.</span>
															</div>
														</div>
														
														<div class="form-group">
															<label class="control-label col-sm-2" for="smallheading">Mechanism for 
																 Detecting Plagiarism<span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="text" class="form-control" id="mechnism" onchange="trim(this)"
																	name="mechnism" placeholder="Mechanism for Detecting Plagiarism"
																	value="${plagrsm.mechDetectPlag}" autocomplete="off">
																	<span class="error_form text-danger" id="mech_errfield" style="display:none;" >Please enter mechanism for 
																	detecting plagiarism.</span>
															</div>
														</div>
														
														<div class="form-group">
															<label class="control-label col-sm-2" for="smallheading">URL Link For 
																Plagiarism Detection <span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="text" class="form-control" id="url" onchange="trim(this)"
																	name="url" placeholder="URL Link For Plagiarism Detection"
																	value="${plagrsm.urlLink}" autocomplete="off">
																	<span class="error_form text-danger" id="url_errfield" style="display:none;" >
																	Please enter URL Link For plagiarism detection.</span>
																</div>
															</div>
														</div><!-- Container End -->
														<div class="form-group">
															<div class="col-sm-offset-2 col-sm-10">
															<button type="submit" id="sub_button" class="btn btn-primary" 
																	onclick="submit_f(1)"><i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save</button>
															
																<a href="${pageContext.request.contextPath}/showPlagiarismCodeEthics"><button
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
     					
     					if(!$("#software_name").val()){ 	
       					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#software_name").addClass("has-error")
            				$("#soft_errfield").show()
            					//return false;
            				} else {
            					$("#soft_errfield").hide()
            				}  
            			
     				
           				if(!$("#mechnism").val()){	
        					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#mechnism").addClass("has-error")
            				$("#mech_errfield").show()
            					//return false;
            				} else {
            					$("#mech_errfield").hide()
            				}  
            				
            				
           				if(!$("#url").val()){
       					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#url").addClass("has-error")
            				$("#url_errfield").show()
            					//return false;
            				} else {
            					$("#url_errfield").hide()  
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
			
			//alert("hii");
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
			
			
			
		function hideText() {
		
		var res = $("input[name=is_soft_avalbl]:checked").val();
		
		
			if(res == 1){
				document.getElementById("ihide").style.display = "block";
				
		 }else{
			 document.getElementById("ihide").style.display = "none";
		 }

			} 
			
			 
		</script>

	</div>
	<!-- END CONTAINER -->
	<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->




</body>
</html>