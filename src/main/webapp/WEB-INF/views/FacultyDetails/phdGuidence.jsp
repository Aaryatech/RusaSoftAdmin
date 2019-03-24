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
<body onload="checkCoGuide(${phd.isCoGuide})">
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

							<div class="actions panel_actions pull-right">
								<a href="${pageContext.request.contextPath}/showPhdGuideList"><button
										type="button" class="btn btn-info">Back</button></a>

							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertPhdGuide"
										method="post"
										name="form_sample_2" id="form_sample_2"
										onsubmit="return checkBeforeSubmit()">

										<ul class="nav nav-tabs">
											<!-- <li class="active"><a href="#home" data-toggle="tab">
													<i class="fa fa-home"></i> Register
											</a></li>
 -->
										</ul>

										<div class="tab-content">
											<div class="tab-pane fade in active" id="home">

												<div>


													<div class="col-xs-12">

														<div class="col-sm-12"></div>
														<input type="hidden" id="phdGiudeId" name="phdGiudeId" value="${phd.phdId}">
														<div class="form-group">

															<label class="control-label col-sm-2" for="smallheading">Ph.D.
																Guide <span class="text-danger">*</span>
															</label>


															<div class="col-sm-4">
															<c:choose>
																	<c:when test="${phd.isPhdGuide == 1}">
															
																Yes <input type="radio" name="phdGuide" id="phdGuide"
																	checked value="1"> No<input type="radio"
																	name="phdGuide" id="phdGuide" value="0">
																	</c:when>
																	<c:when test="${phd.isPhdGuide == 0}">
															
																Yes <input type="radio" name="phdGuide" id="phdGuide"
																	 value="1"> No<input type="radio" checked
																	name="phdGuide" id="phdGuide" value="0">
																	</c:when>
																	
																	</c:choose>
															</div>
														</div>

														<div class="form-group">
															<label class="control-label col-sm-2" for="smallheading">Co-Guide
																 <span class="text-danger">*</span>
															</label>


															<div class="col-sm-4">
															<c:choose>
																	<c:when test="${phd.isCoGuide == 1}">
															
																Yes <input type="radio" name="coGuide" id="coGuide"
																	checked value="1" onclick="checkCoGuide(this.value)">
																	
																 No<input type="radio" onclick="checkCoGuide(this.value)"
																	name="coGuide" id="coGuide" value="0">
															</c:when>
															<c:when test="${phd.isPhdGuide == 0}">
															
																Yes <input type="radio" name="coGuide" id="coGuide"
																	checked value="1" onclick="checkCoGuide(this.value)">
																	
																 No<input type="radio" onclick="checkCoGuide(this.value)"
																	name="coGuide" id="coGuide" value="0" checked>
															</c:when>
															<c:otherwise>Yes <input type="radio" name="coGuide" id="coGuide"
																	checked value="1" onclick="checkCoGuide(this.value)">
																	
																 No<input type="radio" onclick="checkCoGuide(this.value)"
																	name="coGuide" id="coGuide" value="0" checked></c:otherwise>
															</c:choose>
															
															</div>
														</div>

														<div class="form-group" id="cogid" style="display: none;">

															<label class="control-label col-sm-2"   for="smallheading">Name
																of Co-Guide <span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="text" class="form-control " id="co_guide_name" autocomplete="off"
																	name="co_guide_name" placeholder="Name of Co_Guide" value="${phd.coGuideName}">
															</div>


														</div>

														<div class="form-group">



															<label class="control-label col-sm-2" for="smallheading">Name
																of Ph.D. Scholar <span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="text" class="form-control" id="phd_scholar"
																	name="phd_scholar" placeholder="Name of Ph.D Scholar"
																	value="${phd.phdScholarName}" autocomplete="off" required>
															</div>
														</div>

														<div class="form-group">

															<label class="control-label col-sm-2" for="smallheading">Year
																of Registration <span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="text" class="form-control datepicker" id="phd_year_reg" autocomplete="off"
																	name="phd_year_reg" placeholder="dd/mm/yyyy" value="${phd.phdRegYear}" required>
															</div>



														</div>

														<div class="form-group">

															<label class="control-label col-sm-2" for="smallheading">Topic/
																Area of Research <span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<textarea id="phd_topic" name="phd_topic"
																	class="form-control" style="width: 100%;" required>${phd.phdTopic}</textarea>
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
															<c:choose>
																	<c:when test="${phd.isPhdAwarded == 1}">
																Yes <input type="radio" name="awarded" id="awarded"
																	checked value="1" onclick="check(this.value)">
																No<input type="radio" name="awarded" id="awarded"
																	value="0" onclick="check(this.value)">
															</c:when>
															
															<c:when test="${phd.isPhdAwarded == 0}">
																Yes <input type="radio" name="awarded" id="awarded"
																	 value="1" onclick="check(this.value)">
																No<input type="radio" name="awarded" id="awarded"
																	value="0" onclick="check(this.value)" checked>
															</c:when>
															
															
															</c:choose>
															
															
															</div>
														</div>

														<div class="form-group" id="abc" style="display: none;">

															<label class="control-label col-sm-2" 
																for="smallheading">Year of Awarded Ph.D<span
																class="text-danger">*</span>
															</label>
															<%-- <div class="col-sm-6">
																<input type="text" class="form-control datepicker" id="phd_year_awarded" onkeypress='return restrictAlphabets(event)'
																	name="phd_year_awarded" placeholder="dd/mm/yyyy" value="${phd.phdAwardedYear}" autocomplete="off">
															</div> --%>
															<div class="col-sm-10">
														<select id="phd_year_awarded" name="phd_year_awarded" class="form-control" required>
																																	
															<c:forEach items="${acaYearList}" var="acaYearList">
																		<c:choose>
																			<c:when test="${acaYearList.yearId==staff.hightestQualificationYear}">
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
															<div class="col-sm-offset-2 col-sm-10">
															<input type="submit" id="sub1" class="btn btn-primary" onclick="submit_f(1)" value="Save">
																<input type="submit"  id="sub2" class="btn btn-primary" onclick="submit_f(0)" value="Save &
																		Next">
																<%-- <a href="${pageContext.request.contextPath}/hodList"><button
																		type="button" class="btn btn-primary">S</button></a> --%>
																<button type="reset" class="btn btn-default">Reset</button>
															</div>
														</div>

													</div>


													<div class="clearfix"></div>

												</div>

											</div>
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
			function check(qualType) {
				//document.getElementById("abc").style = "display:none"
				//var qualType=document.getElementById("cat").value
				//alert("qualType::"+qualType);

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
				//document.getElementById("abc").style = "display:none"
				//var qualType=document.getElementById("cat").value
				//alert("qualType::"+qualType);

				if (guide == 1) {

					document.getElementById("cogid").style = "visible"
					document.getElementById("co_guide_name").style = "visible"
					document.getElementById("co_guide_name").setAttribute("required","true");

				} else if (guide == 0) {
					document.getElementById("cogid").style = "display:none"
					document.getElementById("co_guide_name").style = "display:none"
					document.getElementById("co_guide_name").removeAttribute("required");
				}

			}
			
			function hideText() {
				//alert("hii");
				
				document.getElementById("abc").style = "visible"
				document.getElementById("phd_year_awarded").style = "visible"
				document.getElementById("phd_year_awarded").setAttribute("required","true");
				
				document.getElementById("cogid").style = "visible"
				document.getElementById("co_guide_name").style = "visible"
				document.getElementById("co_guide_name").setAttribute("required","true");

			}
		</script>

	</div>
	<!-- END CONTAINER -->
	<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->

	<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>


</body>
</html>