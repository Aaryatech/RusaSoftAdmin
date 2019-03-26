







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
										name="form_sample_2" id="form_sample_2"
										onsubmit="return checkBeforeSubmit()">

										
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
															class="form-control" required>
															
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
													</div>
												</div>
															
												<div class="form-group">

													<label class="control-label col-sm-2" for="smallheading">Name
														of Activity <span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="activity_name" autocomplete="off"
															name="activity_name" placeholder="Name of Activity" value="${activity.activityName}"
															required>
													</div>

												</div>
															
															
												<div class="form-group">

													<label class="control-label col-sm-2" for="status">Select
														Level<span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<select id="activity_level" name="activity_level"
															class="form-control" required>
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
													</div>

												</div>

															<div class="form-group">
															<label class="control-label col-sm-2" for="page_order">Date of
																Activity <span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="text" class="form-control datepicker" id="activity_date" onkeypress='return restrictAlphabets(event)'
																 	value="${activity.activityDate}" name="activity_date"  autocomplete="off"
																 	 placeholder="dd/mm/yyyy" pattern="^(?!\s*$).+" required>
															</div>
														</div>

															<div class="form-group">

													<label class="control-label col-sm-2" for="smallheading">No
														of Participants <span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="activity_part"  onkeypress='return restrictAlphabets(event)'
															name="activity_part" placeholder="No of Participants" value="${activity.activityParticipants}"
														autocomplete="off"	required>
													</div>
												</div>



												<div class="form-group">
													<label class="control-label col-sm-2" for="smallheading">Funded
														By <span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="activity_found" autocomplete="off"
															name="activity_found" placeholder="Funded By" value="${activity.activityFundedBy}" required>
													</div>



												</div>
												<div class="form-group">

													<label class="control-label col-sm-2" for="smallheading">Amount
														Sanctioned <span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="number" min="0"  class="form-control" id="amt_sanc"  onkeypress='return restrictAlphabets(event)'
															name="amt_sanc" placeholder="Amount Sanctioned" value="${activity.activityAmountSanctioned}"
															autocomplete="off" required>
													</div>
												</div>



												<div class="form-group">

													<label class="control-label col-sm-2" for="smallheading">Amount
														Utilized <span class="text-danger">*</span>
													</label>
													<div class="col-sm-6">
														<input type="number" min="0" class="form-control" id="amt_utilise"  onkeypress='return restrictAlphabets(event)'
															name="amt_utilise" placeholder="Amount Utilized" value="${activity.activityAmountUtilised}"
															autocomplete="off" required onchange="chkSncAmt()">
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
<script type="text/javascript">
		function submit_f(view) {
			//alert(view);
			document.getElementById("is_view").value = view;

		}
		
		function chkSncAmt(){
			
			var sancAmt = document.getElementById("amt_sanc").value; 
			var utlAmt = document.getElementById("amt_utilise").value; 
			
			
			if(utlAmt>sancAmt){
				document.getElementById("amt_utilise").value = "";
				alert("Utilized amount is more than Sanctioned amount!");
				return false;
				
			}
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

