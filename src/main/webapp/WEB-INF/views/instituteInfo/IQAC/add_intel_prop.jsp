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
				<!-- MAIN CONTENT AREA STARTS -->

				<div class="col-lg-12"></div>



				<div class="col-lg-12">
					<section class="box ">

						<header class="panel_header">
							<h2 class="title pull-left">${title}</h2>

							<%-- <div class="actions panel_actions pull-right">
								<a href="${pageContext.request.contextPath}/showIntellectualProperty"><button
										type="button" class="btn btn-info">Back</button></a>

							</div> --%>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertPropIntelRight"
										method="post" name="form_sample_2" id="form_sample_2">

										<input type="hidden" id="intel_id" name="intel_id"
											placeholder="" value="${intelProp.conId}">

										<div class="form-group">
											<label class="control-label col-sm-3" for="initiativeName">
												Title of IPR-Industry-Academic Initiative Practice<span
												class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control" id="ipr_title"
													name="ipr_title" autocomplete="off" onchange="trim(this)"
													placeholder="Title of IPR-Industry-Academic Initiative  "
													value="${intelProp.conName}"> <span
													class="error_form text-danger" id="error_formfield1"
													style="display: none;">Please enter title of
													IPR-Industry-Academic Initiative Practice.</span>
											</div>
										</div>
										
											<div class="form-group">
												<label class="control-label col-sm-3" for="reportLink">
													Link to the Activity Reports on the Website<span
													class="text-danger">*</span>
												</label>
												<div class="col-sm-6">
													<input type="text" class="form-control" id="reports_link"
														name="reports_link" autocomplete="off" onchange="trim(this)"
														placeholder="Link to the Activity Reports on the Website"
														value="${intelProp.reportLink}"> <span
														class="error_form text-danger" id="error_linkfield"
														style="display: none;">Please enter
														link to the activity reports on the website.</span>
												</div>
										</div>
										
											<div class="form-group">
											<label class="control-label col-sm-3" for="establishedDate">
												IPR Establishment Date<span
												class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control datepicker" id="estb_date"
													name="estb_date" autocomplete="off" onchange="trim(this)"
													placeholder="IPR Establishment Date" onkeypress='return restrictAlphabets(event)'
													value="${intelProp.establishDate}"> <span
													class="error_form text-danger" id="error_estbDate"
													style="display: none;">Please enter IPR establishment date.</span>
											</div>
										</div>




										<div class="form-group">

											<label class="control-label col-sm-3" for="fromDate">From
												Date <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control datepicker"
													placeholder="From Date" autocomplete="off" id="fromDate"
													name="fromDate"
													onkeypress='return restrictAlphabets(event)'
													value="${intelProp.conFromdt}" onchange="trim(this)">
												<span class="error_form text-danger" id="error_formfield2"
													style="display: none;">Please enter from date.</span>
												
												<span
													class="error_form text-danger" id="error_fromToDate"
													style="display: none;">From Date must be smaller than To Date </span>
											</div>
										</div>

										<div class="form-group">

											<label class="control-label col-sm-3" for="toDate">To
												Date <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control datepicker"
													onchange="trim(this)" placeholder="To Date"
													autocomplete="off" id="toDate" name="toDate"
													onkeypress='return restrictAlphabets(event)'
													value="${intelProp.conTodt}"> <span
													class="error_form text-danger" id="error_formfield3"
													style="display: none;">Please enter to date.</span> 
													<span
													class="error_form text-danger" id="error_formfield0"
													style="display: none;">to date must be greater than
													from date.</span>
													
													<span
													class="error_form text-danger" id="error_toToDate"
													style="display: none;">To Date must be greater than From Date </span>
											</div>
										</div>



										<div class="form-group">
											<label class="control-label col-sm-3" for="participant">No.
												of Participants<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="number" class="form-control" id="participant"
													maxlength="7" autocomplete="off" name="participant"
													onchange="trim(this)" min="0" onFocus="clearDefault(this)"
													onkeypress='return restrictAlphabets(event)'
													placeholder="No. of Participants"
													value="${intelProp.conPcount}"> <span
													class="error_form text-danger" id="error_formfield4"
													style="display: none;">Please enter No. of
													participants and value must be greater than 0.</span>
											</div>
										</div>

										<div class="form-group">
											<div class="col-sm-offset-3 col-sm-10">
												<button type="submit" id="sub_button"
													class="btn btn-primary" onclick="submit_f(1)">
													<i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save
												</button>

												<a
													href="${pageContext.request.contextPath}/showIntellectualProperty"><button
														id="sub2" type="button" class="btn btn-primary">
														<i class="${sessionScope.cancelIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Cancel
													</button></a> <input type="hidden" id="is_view" name="is_view" value="0">
											</div>

										</div>
										<input type="hidden" id="is_view" name="is_view" value="0">
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
            		//	alert("hi");
            			var isError=false;
            			 var errMsg="";
            			
            			
            			 if(!$("#ipr_title").val()){
        					 
             				isError=true;
             				errMsg += '<li>Please enter a valid name.</li>';
             				
             				$("#ipr_title").addClass("has-error")
             				$("#error_formfield1").show();
             					//return false;
             				} else {
             					$("#error_formfield1").hide();
             				} 
           				
           				if(!$("#fromDate").val()){
       					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#fromDate").addClass("has-error")
            				$("#error_formfield2").show();
            					//return false;
            				} else {
            					$("#error_formfield2").hide();
            				} 
           				
           				if(!$("#toDate").val()){
       					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#toDate").addClass("has-error")
            				$("#error_formfield3").show();
            					//return false;
            				} else {
            					$("#error_formfield3").hide()
            				} 
           				if($("#participant").val()==0  || !$("#participant").val()){
          					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#participant").addClass("has-error")
            				$("#error_formfield4").show();
            					//return false;
            				} else {
            					$("#error_formfield4").hide();
            				} 
           				
           			 	var from_date = document.getElementById("fromDate").value;
      					var to_date = document.getElementById("toDate").value;
      					
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
	 					 	$("#error_formfield1").hide();
	 					 	$("#error_formfield3").hide();
	      		            return false;
	      		           
	      		        }else {
	      					$("#error_fromToDate").hide();
	      					$("#error_toToDate").hide();
	      				}
	      		     
           				if(!$("#reports_link").val()){
        					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#reports_link").addClass("has-error")
            				$("#error_linkfield").show()
            					//return false;
            				} else {
            					$("#error_linkfield").hide()
            				} 
	      		      
	      		    if(!$("#estb_date").val()){
   					 
        				isError=true;
        				       				
        				$("#estb_date").addClass("has-error")
        				$("#error_estbDate").show()
        					//return false;
        				} else {
        					$("#error_estbDate").hide()
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
	
			function clearDefault(a){
				if(a.defaultValue==0)
				{
					a.value=""
				}
				};
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