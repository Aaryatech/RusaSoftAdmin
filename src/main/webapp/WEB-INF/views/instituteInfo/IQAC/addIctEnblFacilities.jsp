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
<body class=" " onload="hideText()">
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

				<div class="col-lg-12">
					<section class="box ">

						<header class="panel_header">
							<h2 class="title pull-left">${title}</h2>

							<%-- <div class="actions panel_actions pull-right">
								<a href="${pageContext.request.contextPath}/showGenderEquity"><button
										type="button" class="btn btn-info">Back</button></a>
							</div> --%>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertIctEnblFac"
										method="post" name="form_sample_2" id="form_sample_2">

										<input type="hidden" id="ict_id"
											name="ict_id" value="${ictEnbFac.ictEnbFacId}">
										<div class="form-group">
											<label class="control-label col-sm-2" for="title">No. of 
												Classrooms<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control" id="classroom" onfocus="this.value=''"
													autocomplete="off" name="classroom" onchange="trim(this)"
													placeholder="No. of Classrooms" onkeypress='return restrictAlphabets(event)'
													value="${ictEnbFac.noOfClassroom}"> <span
													class="error_form text-danger" id="error_formfield1"
													style="display: none;">Please enter No. of classrooms
													 and value must be greater than 0.</span>
											</div>
										</div>

										<div class="form-group">

											<label class="control-label col-sm-2" for="lcd">No. of 
												Classrooms with LCD/LED Projector<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control" onfocus="this.value=''"
													autocomplete="off" id="lcd" name="lcd" onkeypress='return restrictAlphabets(event)'
													placeholder="No. of Classrooms with LCD" value="${ictEnbFac.noClassromLcd}">
												<span class="error_form text-danger" id="error_formfield2"
													style="display: none;">Please enter No. of classrooms with LCD
													and value must be greater than 0.</span>

											</div>
										</div>

										<div class="form-group">

											<label class="control-label col-sm-2" for="toDate">No. of 
												Classrooms with Wifi/LAN<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control" onfocus="this.value=''"
													autocomplete="off" id="wifi" name="wifi" onkeypress='return restrictAlphabets(event)'
													placeholder="No. of Classrooms with Wifi/LAN" value="${ictEnbFac.noClassroomWifi}">
												<span class="error_form text-danger" id="error_formfield3"
													style="display: none;">Please enter No. of classrooms with wifi/LAN 
													and value must be greater than 0..</span> 
											</div>
										</div>



										<div class="form-group">
											<label class="control-label col-sm-2" for="participant">No. of 
												Seminar Halls<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control" id="seminar_hall"
													autocomplete="off" name="seminar_hall" maxlength="7" 
													onkeypress='return restrictAlphabets(event)' onfocus="this.value=''"
													onchange="trim(this)" placeholder="No. of Seminar Halls"
													value="${ictEnbFac.seminarHall}"> <span
													class="error_form text-danger" id="error_formfield4"
													style="display: none;">Please enter No. of seminar halls
													and value must be greater than 0.</span>
											</div>
										</div>
										
										<div class="form-group">
											<label class="control-label col-sm-2" for="participant">No. of Seminar
												 Halls with ICT <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control" id="ict_seminar"
													autocomplete="off" name="ict_seminar" maxlength="7" 
													onkeypress='return restrictAlphabets(event)' onfocus="this.value=''"
													onchange="trim(this)" placeholder="No. of Seminar Halls with ICT "
													value="${ictEnbFac.ictSeminarHall}"> <span
													class="error_form text-danger" id="error_formfield5"
													style="display: none;">Please enter No. of seminar halls
													with ICT and value must be greater than 0.</span>
											</div>
										</div>
										
										<div class="form-group">
											<label class="control-label col-sm-2" for="participant">LCD/LED
												 Projectors<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control" id="lcd_led"
													autocomplete="off" name="lcd_led" maxlength="7" onkeypress='return restrictAlphabets(event)'
													onkeypress='return restrictAlphabets(event)' onfocus="this.value=''"
													onchange="trim(this)" placeholder="LCD/LED Projectors"
													value="${ictEnbFac.exInt1}"> <span
													class="error_form text-danger" id="error_formfield6"
													style="display: none;">Please enter LCD/LED projectors
													and value must be greater than 0.</span>
											</div>
										</div>

										<div class="form-group">
											<div class="col-sm-offset-2 col-sm-10">
												<button type="submit" id="sub_button"
													class="btn btn-primary" onclick="submit_f(1)">
													<i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save
												</button>

												<a
													href="${pageContext.request.contextPath}/showICTEnblFaclities"><button
														id="sub2" type="button" class="btn btn-primary">
														<i class="${sessionScope.cancelIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Cancel
													</button></a> <input type="hidden" id="is_view" name="is_view" value="0">
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



	</div>
	<!-- END CONTAINER -->
	<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->
	<div class="modal fade col-xs-12" id="myModal1" tabindex="-1"
		role="dialog" aria-hidden="true">
		<div class="modal-dialog" style="width: 65%">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Internal Quality Initiative</h4>
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
            		//	alert("hi");
            			var isError=false;
            			 var errMsg="";
            			   			
            			
            			 if($("#classroom").val()<=0 || !$("#classroom").val()){
        					 
             				isError=true;
             				errMsg += '<li>Please enter a valid name.</li>';
             				
             				$("#classroom").addClass("has-error")
             				$("#error_formfield1").show()
             					//return false;
             				} else {
             					$("#error_formfield1").hide()
             				} 
           				
           				if($("#lcd").val()<=0 || !$("#lcd").val()){
       					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#lcd").addClass("has-error")
            				$("#error_formfield2").show();
            					//return false;
            				} else {
            					$("#error_formfield2").hide();
            				} 
           				
           				if($("#wifi").val()<=0 || !$("#wifi").val()){
       					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#wifi").addClass("has-error")
            				$("#error_formfield3").show()
            					//return false;
            				} else {
            					$("#error_formfield3").hide();
            				} 
           				
           				
           			
           				
           				if($("#seminar_hall").val()==0  || !$("#seminar_hall").val()){
          					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#seminar_hall").addClass("has-error")
            				$("#error_formfield4").show()
            					//return false;
            				} else {
            					$("#error_formfield4").hide()
            				} 
           				
           			 	if($("#ict_seminar").val()<=0 ||  !$("#ict_seminar").val()){
          					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#ict_seminar").addClass("has-error")
            				$("#error_formfield5").show()
            					//return false;
            				} else {
            					$("#error_formfield5").hide()
            				} 
            				
            				if($("#lcd_led").val()<=0 ||  !$("#lcd_led").val()){
             					 
                				isError=true;
                				errMsg += '<li>Please enter a valid name.</li>';
                				
                				$("#lcd_led").addClass("has-error")
                				$("#error_formfield6").show()
                					//return false;
                				} else {
                					$("#error_formfield6").hide()
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
		function getData() {
			//alert("hii");
			var i = parseInt(document.getElementById("index").value);

			var academicYear = document.getElementById("academicYear").value;
			var initiativeName = document.getElementById("initiativeName").value;
			var conductionDate = document.getElementById("conductionDate").value;
			var fromDate = document.getElementById("fromDate").value;
			var toDate = document.getElementById("toDate").value;
			var participant = document.getElementById("participant").value;
			var otherQual = document.getElementById("otherQual").value;
			//alert("noStud"+noStud);
			var temp;
			if (initiativeName == 7) {

				temp = otherQual;
				//alert(temp);
			} else {
				temp = initiativeName;
			}

			var dataTable = $('#example-1').DataTable();

			dataTable.row.add(
					[ i + 1, academicYear, temp, conductionDate, fromDate,
							toDate, participant ]).draw();
			document.getElementById("index").value = i + 1;
		}

		function showForm() {
			//document.getElementById("abc").style = "display:none"
			var qualType = document.getElementById("initiativeName").value
			//alert("qualType::"+qualType);

			if (qualType == 7) {

				document.getElementById("abc").style = "visible"

			} else {
				document.getElementById("abc").style = "display:none"
			}

		}
		function hideText() {
			//alert("hii");
			document.getElementById("abc").style = "display:none"

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
		function submit_f(view) {
			//alert(view);
			document.getElementById("is_view").value = view;

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