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
								<a
									href="${pageContext.request.contextPath}/showInstituteSupport"><button
										type="button" class="btn btn-info">Back</button></a>
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertInstituteSupport"
										method="post" onsubmit="return checkBeforeSubmit()"
										name="form_sample_2" id="form_sample_2" >

										<%-- 	<ul class="nav nav-tabs">
											<li class="active"><a href="#home" data-toggle="tab">
													<i class="fa fa-home"></i> ${title}
											</a></li>

										</ul> --%>

										<!-- <div class="tab-content">
											<div class="tab-pane fade in active" id="home">

												<div>


													<div class="col-xs-12">
 -->

										<!-- <div class="form-group">

															<label class="control-label col-sm-8" for="smallheading">Institute
																Support Financially by Awarding Scholarship/Freeships
																like Schemes <b>other than Government Schemes </b> : <span
																class="text-danger">*</span>
															</label>


															<div class="col-sm-2">
																Yes <input type="radio" name="isSchemes" id="isSchemes"
																	onclick="setGovernmentValue(this.value)" checked
																	value="0"> No<input type="radio"
																	name="isSchemes" id="isSchemes" value="1"
																	onclick="setGovernmentValue(this.value)">
															</div>
														</div> -->
										<%-- <div class="form-group">
													<label class="control-label col-sm-3" for="status">
													Year  <span class="text-danger">*</span>
												</label>
												<div class="col-sm-6">
													<select id="academic_year" name="academic_year" class="form-control" required>
																<c:forEach items="${acaYearList}" var="acaYear">
																		<option value="${acaYear.yearId}">${acaYear.academicYear}</option>
																	
																	</c:forEach>
																	
																		<c:forEach items="${acaYearList}" var="acaYearList">
																		<c:choose>
																			<c:when test="${acaYearList.yearId==editStudent.acadamicYear}">
																			<option selected value="${acaYearList.yearId}">${acaYearList.academicYear}</option>

																			</c:when>
																			<c:otherwise>
																				<option value="${acaYearList.yearId}">${acaYearList.academicYear}</option>

																			</c:otherwise>

																		</c:choose>

																	</c:forEach>

													</select>
                                       </div>
										</div> --%>	
										<div id="abc">
												<input type="hidden" id="inst_scheme_id"  name="inst_scheme_id"
														placeholder="" value="${instSpprt.instSchemeId}">
											<div class="form-group">

												<label class="control-label col-sm-3" for="studBenifited">Name
													of Schemes <span class="text-danger">*</span>
												</label>
												<div class="col-sm-6">
													<input type="text" class="form-control" pattern="^(?!\s*$).+"
														id="inst_scheme_name" autocomplete="off"
														onchange="return trim(this)" name="inst_scheme_name"
														placeholder="" value="${instSpprt.instSchemeName}"
														required>
												</div>
											</div>




											<div class="form-group">

												<label class="control-label col-sm-3"
													for="inst_students_benefited">No. of Students
													Benefited <span class="text-danger">*</span>
												</label>
												<div class="col-sm-6">
													<input type="number" min="0" class="form-control" autocomplete="off"
														id="inst_students_benefited" pattern="^(?!\s*$).+"
														name="inst_students_benefited" onkeypress="return allowOnlyNumber(event)" 
														placeholder=""
														value="${instSpprt.instStudentsBenefited}" required>
												</div>
											</div>

											<div class="form-group">

												<label class="control-label col-sm-3"
													for="inst_schme_offeredby">Scheme/Support offered
													By <span class="text-danger">*</span>
												</label>
												<div class="col-sm-6">
													<input type="text" class="form-control" autocomplete="off"
														id="inst_schme_offeredby" name="inst_schme_offeredby"
														placeholder="" pattern="^(?!\s*$).+"
														value="${instSpprt.instSchmeOfferedby}" required>
												</div>
											</div>


											<div class="form-group">
												<div class="col-sm-offset-3 col-sm-10">
														<input type="submit" id="sub1" class="btn btn-primary" onclick="submit_f(1)" value="Save">
																<input type="submit" id="sub2" class="btn btn-primary" onclick="submit_f(0)" value="Save &
																		Next">
																<button type="reset" class="btn btn-default">Reset</button>
																<input type="hidden" id="is_view" name="is_view" value="0">
												</div>
											</div>
										</div>


										<div class="clearfix"></div>
										<!-- 	</div>

											</div> -->

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




	<div class="modal fade col-xs-12" id="myModal1" tabindex="-1"
		role="dialog" aria-hidden="true">
		<div class="modal-dialog" style="width: 65%">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Institute Support Details</h4>
				</div>
				<div class="modal-body">



					<!-- Link on Website for Activity Report -->

					<div class="form-group" style="text-align: center;">

						<button type="submit" class="btn btn-primary" onclick="getData()">Submit</button>
					</div>


				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
					<input type="hidden" id="index" name="index" value="0">
				</div>
			</div>
		</div>
	</div>


	<script type="text/javascript">
	function submit_f(view){
		document.getElementById("is_view").value=view;//create this 
		/* var form=document.getElementById("form_sample_2");
	    form.setAttribute("method", "post");

		form.action=("insertHod");
		var x =confirm("Do you really want to submit the form?");
		if(x==true)
		form.submit(); */
		
	}
		function getData() {
			//alert("hii");
			var i = parseInt(document.getElementById("index").value);
			var schemeName = document.getElementById("schemeName").value;
			var academicYear = document.getElementById("academicYear").value;

			var studBenifited = document.getElementById("studBenifited").value;

			var schemeSupportBy = document.getElementById("schemeSupportBy").value;
			//alert("noStud"+noStud);
			var dataTable = $('#example-1').DataTable();

			dataTable.row.add(
					[ i + 1, schemeName, academicYear, studBenifited,
							schemeSupportBy ]).draw();
			document.getElementById("index").value = i + 1;
		}
	</script>

<script type="text/javascript">
	function allowOnlyNumber(evt){
		
		var charCode = (evt.which) ? evt.which : event.keyCode
	    if (charCode == 46){
	        var inputValue = $("#floor").val();
	        var count = (inputValue.match(/'.'/g) || []).length;
	        
	        if(count<1){
	            if (inputValue.indexOf('.') < 1){
	                return true;
	            }
	            return false;
	        }else{
	            return false;
	        }
	    }
	    if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)){
	        return false;
	    }
	    return true;
	}
	</script>
	<script type="text/javascript">
		function setGovernmentValue(value) {

			if (value == 1) {

				document.getElementById("abc").style.display = "none";

			} else {

				document.getElementById("abc").style.display = "block";

			}

		}
	</script>


	<script type="text/javascript">
		$(function() {
			$('#submitForm').submit(
					function() {
						$("input[type='submit']", this).val("Please Wait...")
								.attr('disabled', 'disabled');

						return true;
					});
		});

		function trim(el) {
			el.value = el.value.replace(/(^\s*)|(\s*$)/gi, ""). // removes leading and trailing spaces
			replace(/[ ]{2,}/gi, " "). // replaces multiple spaces with one space 
			replace(/\n +/, "\n"); // Removes spaces after newlines
			return;
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

</body>
</html>