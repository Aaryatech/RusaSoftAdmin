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
				<!-- MAIN CONTENT AREA STARTS -->

				<div class="col-lg-12"></div>



				<div class="col-lg-12">
					<section class="box ">

						<header class="panel_header">
							<h2 class="title pull-left">${title}</h2>

							<div class="actions panel_actions pull-right">
								<a href="${pageContext.request.contextPath}/showRareBookInfo"><button
										type="button" class="btn btn-info">Back</button></a>
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertRareBookInfo"
										method="post" 
										name="form_sample_2" id="form_sample_2"
										onsubmit="return checkBeforeSubmit()">

										
					<input type="text"
							id="bookId" name="bookId"
								 value="${rareBook.rareBookInfoId}">					
					<div class="form-group">
													
						<label class="control-label col-sm-3" for="bookName"
						style="text-align: left;"> Name Of Rare Book<span
						class="text-danger">*</span> </label> 
						<div class="col-sm-9">
								<input type="text"
								class="form-control" id="bookName" name="bookName" pattern="^(?!\s*$).+"
								placeholder="" value="${rareBook.rareBookname}" required>
						</div>
					</div>
					<div class="form-group">

							<label class="control-label col-sm-3" for="publisher"
						style="text-align: left;">Publisher<span
						class="text-danger">*</span> </label> 
						<div class="col-sm-9">
								 <input type="text" class="form-control"
								id="publisher" name="publisher"
								placeholder="Publisher" pattern="^(?!\s*$).+"
								value="${rareBook.publisher}" required>
						</div>
					</div>

					<div class="form-group">

							<label class="control-label col-sm-3" for="noOfBook"
						style="text-align: left;">No. of Copies of Book <span
						class="text-danger">*</span> </label>
						<div class="col-sm-9">
								 <input type="number" min="1" max="99999" class="form-control"
									id="noOfBook" name="noOfBook" pattern="^(?!\s*$).+"
									placeholder="No of Copies for Book"
									value="${rareBook.bookCopies}" required>
						</div>
					</div>
					
					<div class="form-group">

						<label class="control-label col-sm-3" for="costOfBook"
						style="text-align: left;">Cost Of Rare Book<span
						class="text-danger">*</span> </label>
						<div class="col-sm-9">
							 <input type="number" min="1" max="99999" class="form-control"
								id="costOfBook" name="costOfBook" 
								placeholder="Cost Of Rare Book" pattern="^(?!\s*$).+"
								value="${rareBook.costOfBook}" required>
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
															onkeypress='return restrictAlphabets(event)' required>
													</div>
						
					</div>
					
														<div class="form-group">
															<div class="col-sm-offset-2 col-sm-10">
																<input type="submit" id="sub1" class="btn btn-primary" onclick="submit_f(1)" value="Save">
																<input type="submit" id="sub2" class="btn btn-primary" onclick="submit_f(0)" value="Save &
																		Next">
																<button type="reset" class="btn btn-default">Reset</button>
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




	<div class="modal fade col-xs-12" id="myModal1" tabindex="-1"
		role="dialog" aria-hidden="true">
		<div class="modal-dialog" style="width: 65%">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Budget on Infrastructure Facility</h4>
				</div>
				<div class="modal-body">

					<div class="form-group">
						<label class="control-label col-sm-3" for="finantialYear">Financial
							Year</label> <select id="finantialYear" name="finantialYear"
							class="form-control" required>
							<option value="2018-2019">2018-2019</option>
							<option value="2017-2018">2017-2018</option>
							<option value="2016-2017">2016-2017</option>

						</select>
					</div>
					<div class="form-group">

						<label class="control-label col-sm-4 for="facilityTitle">Title
							of Academic Support Activity</label> <input type="text"
							class="form-control" id="facilityTitle" name="facilityTitle"
							placeholder="Title of Academic Support Activity" required>
					</div>
					<div class="form-group">

						<label class="control-label col-sm-3" for="allocatedAmt">Budget
							Allocated Amount in Rs. </label> <input type="text" class="form-control"
							id="allocatedAmt" name="allocatedAmt"
							placeholder="Budget Allocated Amount in Rs"
							value="${page.pageName}" required>
					</div>

					<div class="form-group">

						<label class="control-label col-sm-3" for="utilizedAmt">Budget
							Utilized Amount in Rs. </label> <input type="text" class="form-control"
							id="utilizedAmt" name="utilizedAmt"
							placeholder="Budget Utilized Amount in Rs."
							value="${page.pageName}" required>
					</div>

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



</body>
</html>