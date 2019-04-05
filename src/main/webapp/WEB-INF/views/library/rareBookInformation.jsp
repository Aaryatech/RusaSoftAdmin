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
								<a href="${pageContext.request.contextPath}/showRareBookInfo"><button
										type="button" class="btn btn-info">Back</button></a>
							</div> --%>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertRareBookInfo"
										method="post" name="form_sample_2" id="form_sample_2">
	
					<input type="hidden" id="bookId" name="bookId" value="${rareBook.rareBookInfoId}">	
									
					<div class="form-group">
													
						<label class="control-label col-sm-3" for="bookName"
						style="text-align: left;"> Title Of Rare Book<span
						class="text-danger">*</span> </label> 
						<div class="col-sm-9">
								<input type="text" autocomplete="off"
								class="form-control" id="bookName" name="bookName" onchange="trim(this)"
								placeholder="Title of Book" value="${rareBook.rareBookname}">
								<span class="error_form text-danger" id="error_formfield1" style="display:none;" >Please enter title of rare book.</span>
						</div>
					</div>
					<div class="form-group">

							<label class="control-label col-sm-3" for="publisher"
						style="text-align: left;">Publisher<span
						class="text-danger">*</span> </label> 
						<div class="col-sm-9"> 
								 <input type="text" class="form-control"
								id="publisher" name="publisher" autocomplete="off"
								placeholder="Publisher" onchange="trim(this)"
								value="${rareBook.publisher}">
								<span class="error_form text-danger" id="error_formfield2" style="display:none;" >Please enter name of Publisher.</span>
						</div>
					</div>

					<div class="form-group">

							<label class="control-label col-sm-3" for="noOfBook"
						style="text-align: left;">No. of Copies <span
						class="text-danger">*</span> </label>
						<div class="col-sm-9">
								 <input type="text" class="form-control" onkeypress='return restrictAlphabets(event)'
									id="noOfBook" name="noOfBook"onchange="trim(this)" maxlength="7"
									placeholder="No of Copies for Book" autocomplete="off"
									value="${rareBook.bookCopies}"> 
									<span class="error_form text-danger" id="error_formfield3" style="display:none;" >Please enter No. of copies and value must be greater than 0.</span>
						</div>
					</div>
					
					<div class="form-group">

						<label class="control-label col-sm-3" for="costOfBook"
						style="text-align: left;">Cost Of Rare Book<span
						class="text-danger">*</span> </label>
						<div class="col-sm-9"> 
							 <input type="text" class="form-control" onkeypress='return restrictAlphabets(event)'
								id="costOfBook" name="costOfBook"  autocomplete="off" maxlength="7"
								placeholder="Cost Of Rare Book" onchange="trim(this)"
								value="${rareBook.costOfBook}">
								<span class="error_form text-danger" id="error_formfield4" style="display:none;" >Please enter cost of rare book and value must be greater than 0.</span>
						</div>		
					</div>
					
					  <div class="form-group"> 
						<label class="control-label col-sm-3" for="year"
						style="text-align: left;"> Year of Publication <span
						class="text-danger">*</span> </label>
						<%-- <div class="col-sm-9">
								 <input type="text" class="form-control"
									id="year" name="year"
									placeholder="Year of Publication"
									value="${rareBook.publicationYear}" required>
						</div> --%>
						<div class="col-sm-9">
							<input type="text" class="form-control datepickeryear"
								data-min-view-mode="years" data-start-view="2"
								data-format="yyyy" placeholder="Year Of Published"
								id="year" value="${rareBook.publicationYear}"
								name="year" autocomplete="off"
								onkeypress='return restrictAlphabets(event)' onchange="trim(this)">
								<span class="error_form text-danger" id="error_formfield5" style="display:none;" >Please enter year of publication.</span>					
						</div>
						
					</div>
					
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<button type="submit" id="sub_button" class="btn btn-primary" 
										onclick="submit_f(1)"><i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save</button>
							
									<a href="${pageContext.request.contextPath}/showRareBookInfo"><button
										id="sub2" type="button" class="btn btn-primary">
											<i class="${sessionScope.cancelIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Cancel</button></a>
									
									<input type="hidden" id="is_view" name="is_view" value="0">
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

	<div class="modal fade col-xs-12" id="myModal1" tabindex="-1"
		role="dialog" aria-hidden="true">
		<div class="modal-dialog" style="width: 65%">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Library</h4>
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
					<input type="hidden" id="index" name="index" value="0">
				</div>
			</div>
		</div>
	</div>
	
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
            			
            			
     						if (!$("#bookName").val()) {
     							isError = true;

     							$("#bookName").addClass(
     									"has-error")
     							$("#error_formfield1").show()
     						} else {
     							$("#error_formfield1").hide()
     						}
     					
     						   
     						if(!$("#publisher").val()){
        					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#publisher").addClass("has-error")
            				$("#error_formfield2").show()
            					//return false;
            				} else {
            					$("#error_formfield2").hide()
            				}  
        				
            				//alert($("#userLms").val());
           				if($("#noOfBook").val()==0){
       					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#noOfBook").addClass("has-error")
            				$("#error_formfield3").show()
            					//return false;
            				} else {
            					$("#error_formfield3").hide()
            				}
           				
           				if($("#costOfBook").val()==0){
          					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#costOfBook").addClass("has-error")
            				$("#error_formfield4").show()
            					//return false;
            				} else {
            					$("#error_formfield4").hide()
            				}
           				
           				if(!$("#year").val()){
         					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#year").addClass("has-error")
            				$("#error_formfield5").show()
            					//return false;
            				} else {
            					$("#error_formfield5").hide()
            				}
           				
           				/* if(!$("#").val()){
        					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#").addClass("has-error")
            				$("#error_formfield6").show()
            					//return false;
            				} else {
            					$("#error_formfield6").hide()
            				} */
           			
            				
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
	$(function() {

		$('.datepickeryear').datepicker({
			autoclose : true,
			minViewMode : 2,
			format : 'yyyy'

		});
	});
	
	
		function getData() {
			//alert("hii");name  publisher  noOfBook costOfBook year
			var i = parseInt(document.getElementById("index").value);
			var name = document.getElementById("name").value;
			var publisher = document.getElementById("publisher").value;
			var noOfBook = document.getElementById("noOfBook").value;
			var costOfBook = document.getElementById("costOfBook").value;
			var year = document.getElementById("year").value;
			//alert("noStud"+noStud);
			var dataTable = $('#example-1').DataTable();

			dataTable.row.add(
					[ i + 1, year, name, publisher, noOfBook,costOfBook ])
					.draw();
			document.getElementById("index").value = i + 1;
		}
		

		function submit_f(view){
			document.getElementById("is_view").value=view;//create this 
			
		}
	</script>


	<script type="text/javascript">
		function showDiv(value) {

			var div = document.getElementById("divshow");

			if (value == 1) {
				div.style.display = "block";
			} else {
				div.style.display = "none";
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
</script>


</body>
</html>