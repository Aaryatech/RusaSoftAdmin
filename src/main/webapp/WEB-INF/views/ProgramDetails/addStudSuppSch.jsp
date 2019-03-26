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
<body   onload="hideText()">
<c:url value="/checkUniqueField" var="checkUniqueField"></c:url>
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

			<%-- 	<div class="col-xs-12">
					<div class="page-title">

						<div class="pull-left">
							<!-- PAGE HEADING TAG - START -->
							<h1 class="title">${title}</h1>
							<!-- PAGE HEADING TAG - END -->
						</div>


					</div>
				</div> --%>
				<div class="clearfix"></div>
				<!-- MAIN CONTENT AREA STARTS -->

				<div class="col-lg-12"></div>



				<div class="col-lg-12">
					<section class="box ">

						<header class="panel_header">
							<h2 class="title pull-left">${title}</h2>

							<div class="actions panel_actions pull-right">
								<a href="${pageContext.request.contextPath}/showStudSupp"><button
										type="button" class="btn btn-info">Back</button></a> 
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertStudentSuppurtScheme"
										method="post" 
										name="form_sample_2" id="form_sample_2"
									onsubmit="return checkBeforeSubmit()">
<!-- 
										<ul class="nav nav-tabs">
											<li class="active"><a href="#home" data-toggle="tab">
													<i class="fa fa-home"></i> Register Form
											</a></li>


										</ul>
 -->
										<!-- <div class="tab-content">
											<div class="tab-pane fade in active" id="home">
 -->
												<div>

													<div class="col-xs-12">
													
													
													
													<input type="hidden" name="stud_suprt_schm" value="${stud.sprtSchmId}">
													
														<div class="form-group">
															<label class="control-label col-sm-2" for="status">Scheme Name 
															 <span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<select id="approveValue" name="schemeName" class="form-control"  onchange="showExtraField()"  required>
												 		<option value="Capability Enhancement">Capability Enhancement</option>
								<option value="Competitive Exams(MPSC,UPSC,PSU,RRB,etc)">Competitive Exams(MPSC,UPSC,PSU,RRB,etc)</option>
								<option value="Higher Education Entrance Exams(GATE,MAT,GPAT,CAT
													etc)">Higher Education Entrance Exams(GATE,MAT,GPAT,CAT
													etc)</option>
								<option value="Vocational Education Training">Vocational Education Training</option>
								<option value="7">Any Other</option>
												 		
												 			<%-- <c:choose>
												 			<c:when test="${stud.schemeName eq 'Capability Enhancement'}">
																<option selected value="Capability Enhancement">Capability Enhancement</option>
																<option value="Competitive Exams(MPSC,UPSC,PSU,RRB,etc)">Competitive Exams(MPSC,UPSC,PSU,RRB,etc)</option>
																<option value="Higher Education Entrance Exams(GATE,MAT,GPAT,CAT
																							etc)">Higher Education Entrance Exams(GATE,MAT,GPAT,CAT
																							etc)</option>
																<option value="Vocational Education Training">Vocational Education Training</option>
																<option value="7">Any Other</option>
															</c:when> 
															
															<c:when test="${stud.schemeName eq 'Competitive Exams(MPSC,UPSC,PSU,RRB,etc)'}">
																<option value="Capability Enhancement">Capability Enhancement</option>
																<option selected value="Competitive Exams(MPSC,UPSC,PSU,RRB,etc)">Competitive Exams(MPSC,UPSC,PSU,RRB,etc)</option>
																<option value="Higher Education Entrance Exams(GATE,MAT,GPAT,CAT
																							etc)">Higher Education Entrance Exams(GATE,MAT,GPAT,CAT
																							etc)</option>
																<option value="Vocational Education Training">Vocational Education Training</option>
																<option value="7">Any Other</option>
															</c:when> 
															
															<c:when test="${stud.schemeName eq 'Higher Education Entrance Exams(GATE,MAT,GPAT,CAT
																							etc)'}">
																<option value="Capability Enhancement">Capability Enhancement</option>
																<option value="Competitive Exams(MPSC,UPSC,PSU,RRB,etc)">Competitive Exams(MPSC,UPSC,PSU,RRB,etc)</option>
																<option selected value="Higher Education Entrance Exams(GATE,MAT,GPAT,CAT
																							etc)">Higher Education Entrance Exams(GATE,MAT,GPAT,CAT
																							etc)</option>
																<option value="Vocational Education Training">Vocational Education Training</option>
																<option value="7">Any Other</option>
															</c:when> 
															
															<c:when test="${stud.schemeName eq 'Vocational Education Training'}">
																<option value="Capability Enhancement">Capability Enhancement</option>
																<option value="Competitive Exams(MPSC,UPSC,PSU,RRB,etc)">Competitive Exams(MPSC,UPSC,PSU,RRB,etc)</option>
																<option value="Higher Education Entrance Exams(GATE,MAT,GPAT,CAT
																							etc)">Higher Education Entrance Exams(GATE,MAT,GPAT,CAT
																							etc)</option>
																<option selected value="Vocational Education Training">Vocational Education Training</option>
																<option value="7">Any Other</option>
															</c:when> 
															
														 <c:when test="${stud.schemeName eq '7'}">
																<option value="Capability Enhancement">Capability Enhancement</option>
																<option value="Competitive Exams(MPSC,UPSC,PSU,RRB,etc)">Competitive Exams(MPSC,UPSC,PSU,RRB,etc)</option>
																<option value="Higher Education Entrance Exams(GATE,MAT,GPAT,CAT
																							etc)">Higher Education Entrance Exams(GATE,MAT,GPAT,CAT
																							etc)</option>
																<option value="Vocational Education Training">Vocational Education Training</option>
																<option selected value="7">Any Other</option>
															</c:when> 
															 
															
															
															
															<c:otherwise> 
							<option value="Capability Enhancement">Capability Enhancement</option>
								<option value="Competitive Exams(MPSC,UPSC,PSU,RRB,etc)">Competitive Exams(MPSC,UPSC,PSU,RRB,etc)</option>
								<option value="Higher Education Entrance Exams(GATE,MAT,GPAT,CAT
													etc)">Higher Education Entrance Exams(GATE,MAT,GPAT,CAT
													etc)</option>
								<option value="Vocational Education Training">Vocational Education Training</option>
								<option value="7">Any Other</option>
							
								 </c:otherwise>
									</c:choose>  --%>
																</select>
																
																
															</div>
														</div>
														
														
														
												<div class="form-group" id="abc">
													<label class="control-label col-sm-2" for="page_order">
														Another Scheme Name <span class="text-danger">*</span>
													</label>
														<div class="col-sm-6">
															<input type="text"  autocomplete="off" 
																 class="form-control" id="anotherScheme" value="${stud.schemeName}" pattern="^(?!\s*$).+"
																	 name="anotherScheme" placeholder="Another Scheme" onchange="checkUnique(this.value,1)">
														</div>
											</div>
														
														
											<div class="form-group">
														<label class="control-label col-sm-2" for="status">Level
															 <span class="text-danger">*</span>
														</label>
														<div class="col-sm-6">
															<select id="approveValue" name="level"class="form-control"  onchange="showExtraField()"  required>
																
																 <c:choose>
															 <c:when test="${stud.level eq 'International'}">
																<option selected value="International">International</option>
																<option value="State">State</option>
																<option value="Regional">Regional</option>
															</c:when> 
															
															<c:when test="${stud.level eq 'State'}">
																<option value="International">International</option>
																<option selected value="State">State</option>
																<option value="Regional">Regional</option>
															</c:when> 
															
															<c:when test="${stud.level eq 'Regional'}">
																<option value="International">International</option>
																<option value="State">State</option>
																<option selected value="Regional">Regional</option>
															</c:when> 
																
															<c:otherwise> 
																<option value="International">International</option>
																<option value="State">State</option>
																<option value="Regional">Regional</option>
															 </c:otherwise>
																</c:choose> 	
																</select>
																
																
														</div>
														</div>
														
													<div class="form-group">
															<label class="control-label col-sm-2" for="status">Type
															 <span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<select id="approveValue" name="type"class="form-control"  onchange="showExtraField()"  required>
														 <c:choose>
															 <c:when test="${stud.type eq 'Govt.'}">
																<option selected value="Govt">Govt.</option>
																<option value="Non Govt.">Non Govt.</option>
															
															</c:when>
															<c:when test="${stud.type eq 'Non Govt.'}">
																<option value="Govt">Govt.</option>
																<option selected value="Non Govt.">Non Govt.</option>
															
															</c:when>
															  <c:otherwise>
																<option value="Govt">Govt.</option>
																<option value="Non Govt.">Non Govt.</option>
															 </c:otherwise>
															 	
															</c:choose> 
																</select>
																
																
															</div>
														</div>
														
														
														
														<div class="form-group">
															<label class="control-label col-sm-2" for="page_name">
														No. of Students Benefited <span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="number" min="0" class="form-control" id="studBenifit" 	value="${stud.noStudentBenifited}"
																	name="studBenifit" placeholder="Students Benifited" autocomplete="off"  required>
															</div>
														</div>



														

														<div class="form-group">
															<label class="control-label col-sm-2" for="page_order">
														Name of Support Agency  <span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="text" 
																	 class="form-control" id="supportAgency" 	value="${stud.supportAgencyName}" pattern="^(?!\s*$).+" autocomplete="off" 
																	 name="supportAgency" placeholder="Support Agency" onchange="checkUnique(this.value,1)" required>
															</div>
														</div>
																
														
													
													
														<div class="form-group">
															<label class="control-label col-sm-2" for="page_order">Date/Year of Introduction
															 <span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="text" class="form-control datepicker" id=" yearofIntro"  placeholder="dd-MM-YYYY" 	value="${ydate}"
																	autocomplete="off"  name="yearofIntro"  required>
															</div>
														</div>
														
													</div>

												</div>
												

                                             <input type="hidden" id="librarian_id" name="librarian_id" value="${stud.sprtSchmId}">
                                             	<input type="hidden" id="is_view" name="is_view" value="0">
												
										
															<div class="form-group">
															<div class="col-sm-offset-2 col-sm-10">
																<input type="submit" class="btn btn-primary" id="sub1" onclick="submit_f(1)" value="Save">
																<input type="submit" class="btn btn-primary" id="sub2" onclick="submit_f(0)" value="Save &
																		Next">
																<button type="reset" class="btn btn-default">Reset</button>
															</div>
														</div>

												<div class="clearfix"></div>
<!-- 
											</div>

										</div>
 -->
									</form>
									<p class="desc text-danger fontsize11">Notice : * Field are  Mandatory.</p>
								</div>

							</div>

						</div>
					</section>
				</div>

			</section>
		</section>

	</div>
	<!-- MAIN CONTENT AREA ENDS -->

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

function submit_f(view){
	
	//alert("id is"+view);
		document.getElementById("is_view").value=view;//create this 
		/* var form=document.getElementById("form_sample_2");
	    form.setAttribute("method", "post");

		form.action=("insertLibrarian");
		var x =confirm();
		if(x==true)
		form.submit(); */
		
	}
	



</script>

<script type="text/javascript">

function showExtraField() {
	//alert("hii");
	//document.getElementById("abc").style = "display:none"
		var qualType = document.getElementById("approveValue").value;
		//alert("qualType::"+qualType);
		
		if (qualType == 7) {

			document.getElementById("abc").style = "visible"
			document.getElementById("anotherScheme").setAttribute("required","true");
			
				
		} 
		else{
			document.getElementById("abc").style = "display:none"
		}
	
	}
	

function hideText() {
	//alert("hii");
	document.getElementById("abc").style = "display:none"
	
	
	}

</script>
<script type="text/javascript">

function checkUnique(inputValue,valueType){
	//alert(inputValue);
	
	var primaryKey=${editInst.librarianId};
	//alert("Primary key"+primaryKey);
	var isEdit=0;
	if(primaryKey>0){
		isEdit=1;
	}
	//alert("Is Edit " +isEdit);
	
	var valid=false;
	if(valueType==1){
		//alert("Its Mob no");
		if(inputValue.length==10){
			valid=true;
			//alert("Len 10")
		}else{
			//alert("Not 10");
		}
	}
	else if(valueType==2){
		//alert("Its Email " );
		
		var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
		if(inputValue.match(mailformat))
		{
			valid=true;
			//alert("Valid Email Id");
		}
		else
		{
			valid=false;
			//alert("InValid Email Id");
		}
	}
	if(valid==true)
	$.getJSON('${checkUniqueField}', {
		
		inputValue : inputValue,
		valueType  : valueType,
		primaryKey : primaryKey,
		isEdit     : isEdit,
		tableId : 4,

		ajax : 'true',

	}, function(data) {
		
	//	alert("Data  " +JSON.stringify(data));
		if(data.error==true){
			if(valueType==2){
			
			
			alert("This email id already exist in system please enter unique");
			$('#librarian_email').val('');
			//document.getElementById("stud_contact_no").value=" ";
			
			}
			else{
				
				
				alert("This contact no  already exist in system please enter unique");
				$('#lib_con_num').val('');
				//document.getElementById("student_email").value=" ";
			}
		}
	});
}


</script>




	<!-- END CONTAINER -->
	<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->

	<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>



</body>
</html>