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

							<%-- <div class="actions panel_actions pull-right">
								<a href="${pageContext.request.contextPath}/showBookPubList"><button
										type="button" class="btn btn-info">Back</button></a> 
							</div> --%>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertFacultyBook"
										method="post" 
										name="form_sample_2" id="form_sample_2">

										

												<div>


													<div class="col-xs-12">
														<input type="hidden"  id="book_id" name="book_id"  value="${book.bookId}">
														<div class="form-group">


 																<label class="control-label col-sm-2"
																	for="smallheading">Title of Book  <span
																	class="text-danger">*</span>
																</label>
																<div class="col-sm-6">
																	<input type="text" class="form-control" id="book_title" onchange="trim(this)" autocomplete="off"
																		name="book_title" placeholder="Title of Book,Chapter in edited Volume/proceeding" value="${book.bookTitle }">
																<span class="error_form text-danger" id="error_formfield1" style="display:none;" >Please enter title of book.</span>
																</div>

															</div>

															<div class="form-group">


																<label class="control-label col-sm-2" for="page_order">Edition
																	<span class="text-danger">*</span>
																</label>


																<div class="col-sm-6">
																	<input type="text" class="form-control" id="book_edition" onchange="trim(this)" autocomplete="off"
																		name="book_edition" placeholder="Edition" value="${book.bookEdition }">
																		<span class="error_form text-danger" id="error_formfield2" style="display:none;" >Please enter edition of book.</span>
																</div>


															</div>

															<%-- <div class="form-group">

																<label class="control-label col-sm-2" for="page_order">Name
																	of Author<span class="text-danger">*</span>
																</label>


																<div class="col-sm-6">
																	<input type="text" class="form-control" id="author" onchange="trim(this)" autocomplete="off"
																		name="author" placeholder="Name of Authors/Co-Authors "
																		value="${book.bookAuthor }">
																		<span class="error_form text-danger" id="error_formfield3" style="display:none;" >Please enter name of author.</span>
																</div> 


															</div>--%>

															<div class="form-group">


												<label class="control-label col-sm-2" for="page_order">Name
																	of Co-Author 1<span class="text-danger"></span>
																</label>


																<div class="col-sm-6">
																	<input type="text" class="form-control" id="co_author1" onchange="trim(this)" autocomplete="off"
																		name="co_author1" placeholder="Name of Co-Authors "
																		value="${book.bookCoauther1}">
																	
																</div>
															</div>
															
																
																<div class="form-group">

																<label class="control-label col-sm-2" for="page_order">Name
																	of Co-Author 2
																</label>


																<div class="col-sm-6">
																	<input type="text" class="form-control" id="co_author2" onchange="trim(this)" autocomplete="off"
																	 name="co_author2" placeholder="Name of Co-Authors "
																		value="${book.bookCoauther2}">
																</div>


															</div>

															<div class="form-group">

													<label class="control-label col-sm-2" for="page_order">Name
																	of Co-Author 3
																</label>


																<div class="col-sm-6">
																	<input type="text" class="form-control" id="co_author3" onchange="trim(this)" autocomplete="off"
																	 name="co_author3" placeholder="Name of Co-Authors "
																		value="${book.bookCoauther3}">
																</div>
													
															</div>
															
																
															<div class="form-group">

																<label class="control-label col-sm-2" for="page_order">Name
																	of Publication/Publisher<span class="text-danger">*</span>
																</label>


																<div class="col-sm-6">
																	<input type="text" class="form-control" id="publisher" onchange="trim(this)" autocomplete="off"
																	 name="publisher" placeholder="Name of Publication/Publisher "
																		value="${book.bookPublisher}">
																		<span class="error_form text-danger" id="error_formfield4" style="display:none;" >Please enter name of publication/publisher. </span>
																</div>

															</div>
															

															<div class="form-group">

																<label class="control-label col-sm-2" for="page_order">ISSN/ISBN
																	No. <span class="text-danger">*</span>
																</label>


																<div class="col-sm-6">
																	<input type="text" class="form-control" id="isbn" onchange="trim(this)" autocomplete="off"
																	 name="isbn" placeholder="ISSN/ISBN No." value="${book.bookIsbn }">
																	<span class="error_form text-danger" id="error_formfield5" style="display:none;" >Please enter ISSN/ISBN
																	No. </span>
																</div>

															</div>

															
																  <div class="form-group"> 
																	<label class="control-label col-sm-2" for="year"> Year of 
																		Publication <span
																	class="text-danger">*</span> </label>
																		<div class="col-sm-6">
																		<input type="text" class="form-control datepickeryear"
																			data-min-view-mode="years" data-start-view="2"
																			data-format="yyyy" placeholder="Year Of Published"
																			id="year_publication" value="${book.bookPubYear }"
																			name="year_publication" autocomplete="off" maxlength="4"
																			onkeypress='return restrictAlphabets(event)' onchange="trim(this)">
																			<span class="error_form text-danger" id="error_formfield6" style="display:none;" >Please enter year of publication.</span>					
																	</div>
																	
																</div>
				
															<%-- <div class="form-group">


																<label class="control-label col-sm-2" for="smallheading">Year
																	of Publication  <span class="text-danger">*</span>
																</label>
																<div class="col-sm-6">
																	<input type="text" class="form-control datepicker" id="year_publication" onchange="trim(this)" autocomplete="off" 
																	 name="year_publication" placeholder="dd/mm/yyyy" value="${book.bookPubYear }">
																<span class="error_form text-danger" id="error_formfield6" style="display:none;" >Please enter year
																	of publication. </span>
																</div>


															</div>
 --%>														

														<div class="form-group">
															<div class="col-sm-offset-2 col-sm-10">
																<button type="submit" id="sub_button" class="btn btn-primary" 
																	onclick="submit_f(1)"><i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save</button>
																	
																	<a href="${pageContext.request.contextPath}/showBookPubList"><button
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



	</div>
	<!-- END CONTAINER -->
	<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->

	<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
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
<script>
	function trim(el) {
		el.value = el.value.replace(/(^\s*)|(\s*$)/gi, ""). // removes leading and trailing spaces
		replace(/[ ]{2,}/gi, " "). // replaces multiple spaces with one space 
		replace(/\n +/, "\n"); // Removes spaces after newlines
		return;
	}
	
              function validateEmail(email) {
            
            	var eml = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
            
            	if (eml.test($.trim(email)) == false) {
            
            
            	return false;
            
            	}
            
            	return true;
            
            }
             function validateMobile(mobile) {
            		var mob = /^[1-9]{1}[0-9]{9}$/;
            
            
            		if (mob.test($.trim(mobile)) == false) {
            
            		//alert("Please enter a valid email address .");
            		return false;
            
            		}
            		return true;
            
             }  
            	$(document).ready(function($){
            		
            		$("#form_sample_2").submit(function(e) {
            		
            			var isError=false;
            			 var errMsg="";
            				
           				if(!$("#book_title").val()){
            					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#book_title").addClass("has-error")
            				$("#error_formfield1").show()
            					//return false;
            				} else {
            					$("#error_formfield1").hide()
            				}
           				if(!$("#book_edition").val()){
        					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#book_edition").addClass("has-error")
            				$("#error_formfield2").show()
            					//return false;
            				} else {
            					$("#error_formfield2").hide()
            				}
            				
            				
           				/* if(!$("#author").val()){
       					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#conf_date").addClass("has-error")
            				$("#error_formfield3").show()
            					//return false;
            				} else {
            					$("#error_formfield3").hide()
            				} */
           				
           				if(!$("#publisher").val()){
          					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#publisher").addClass("has-error")
            				$("#error_formfield4").show()
            					//return false;
            				} else {
            					$("#error_formfield4").hide()
            				}
           				
           				if(!$("#isbn").val()){
         					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#isbn").addClass("has-error")
            				$("#error_formfield5").show()
            					//return false;
            				} else {
            					$("#error_formfield5").hide()
            				}
           				
           				if(!$("#year_publication").val()){
        					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#year_publication").addClass("has-error")
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
    
    
    $(function() {

		$('.datepickeryear').datepicker({
			autoclose : true,
			minViewMode : 2,
			format : 'yyyy'

		});
	});
</script>

<script type="text/javascript">
		function submit_f(view) {
			//alert(view);
			document.getElementById("is_view").value = view;

		}
</script>

</body>
</html>