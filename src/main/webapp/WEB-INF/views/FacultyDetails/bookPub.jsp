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
								<a href="${pageContext.request.contextPath}/showBookPubList"><button
										type="button" class="btn btn-info">Back</button></a> 
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertFacultyBook"
										method="post" 
										name="form_sample_2" id="form_sample_2"
										onsubmit="return checkBeforeSubmit()">

										<ul class="nav nav-tabs">
											<li class="active"><a href="#home" data-toggle="tab">
													<i class="fa fa-home"></i> Register
											</a></li>
											
										</ul>

										<div class="tab-content">
											<div class="tab-pane fade in active" id="home">

												<div>


													<div class="col-xs-12">
														<input type="hidden"  id="book_id" name="book_id"  value="${book.bookId}">
														<div class="form-group">


 																<label class="control-label col-sm-2"
																	for="smallheading">Title of Book  <span
																	class="text-danger">*</span>
																</label>
																<div class="col-sm-6">
																	<input type="text" class="form-control" id="book_title" pattern="^(?!\s*$).+"
																		name="book_title" placeholder="Title of Book" value="${book.bookTitle }"
																		required>
																</div>

															</div>

															<div class="form-group">


																<label class="control-label col-sm-2" for="page_order">Edition
																	<span class="text-danger">*</span>
																</label>


																<div class="col-sm-6">
																	<input type="text" class="form-control" id="book_edition" pattern="^(?!\s*$).+"
																		name="book_edition" placeholder="Edition" value="${book.bookEdition }" required>
																</div>





															</div>

															<div class="form-group">

																<label class="control-label col-sm-2" for="page_order">Name
																	of Author<span class="text-danger">*</span>
																</label>


																<div class="col-sm-6">
																	<input type="text" class="form-control" id="author" pattern="^(?!\s*$).+"
																		name="author" placeholder="Name of Authors/Co-Authors "
																		value="${book.bookAuthor }" required>
																</div>


															</div>

															<div class="form-group">


												<label class="control-label col-sm-2" for="page_order">Name
																	of Co-Author 1<span class="text-danger">*</span>
																</label>


																<div class="col-sm-6">
																	<input type="text" class="form-control" id="co_author1" pattern="^(?!\s*$).+"
																		name="co_author1" placeholder="Name of Co-Authors "
																		value="${book.bookCoauther1}" required>
																</div>
															</div>
															
																
																<div class="form-group">

																<label class="control-label col-sm-2" for="page_order">Name
																	of Co-Author 2
																</label>


																<div class="col-sm-6">
																	<input type="text" class="form-control" id="co_author2"
																		name="co_author2" placeholder="Name of Co-Authors "
																		value="${book.bookCoauther2}">
																</div>


															</div>

															<div class="form-group">

													<label class="control-label col-sm-2" for="page_order">Name
																	of Co-Author 3
																</label>


																<div class="col-sm-6">
																	<input type="text" class="form-control" id="co_author3"
																		name="co_author3" placeholder="Name of Co-Authors "
																		value="${book.bookCoauther3}">
																</div>
													
															</div>
															
																
															<div class="form-group">

																<label class="control-label col-sm-2" for="page_order">Name
																	of Publication/Publisher<span class="text-danger">*</span>
																</label>


																<div class="col-sm-6">
																	<input type="text" class="form-control" id="publisher" pattern="^(?!\s*$).+"
																		name="publisher" placeholder="Name of Publication/Publisher  "
																		value="${book.bookPublisher}" required>
																</div>

															</div>
															

															<div class="form-group">

																<label class="control-label col-sm-2" for="page_order">ISBN
																	No <span class="text-danger">*</span>
																</label>


																<div class="col-sm-6">
																	<input type="text" class="form-control" id="isbn" pattern="^(?!\s*$).+"
																		name="isbn" placeholder="ISBN No" value="${book.bookIsbn }" required>
																</div>

															</div>

															<div class="form-group">


																<label class="control-label col-sm-2" for="smallheading">Year
																	of Publication  <span class="text-danger">*</span>
																</label>
																<div class="col-sm-6">
																	<input type="text" class="form-control datepicker" id="year_publication" pattern="^(?!\s*$).+"
																		name="year_publication" placeholder="dd/mm/yyyy" value="${book.bookPubYear }"
																		required>
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



	</div>
	<!-- END CONTAINER -->
	<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->

	<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>

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

</body>
</html>