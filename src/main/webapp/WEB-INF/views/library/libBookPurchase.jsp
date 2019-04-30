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
										action="${pageContext.request.contextPath}/insertLibBook"
										method="post" name="form_sample_2" id="form_sample_2">

										<input type="hidden" id="bookId" name="bookId"
											value="${lib.bookPurchaseId}">
											
										<div class="form-group">

											<label class="control-label col-sm-2" for="noOfBook"
												style="text-align: left;">No. of Book Purchase <span
												class="text-danger">*</span>
											</label>
											<div class="col-sm-10">
												<input type="text"  class="form-control"
													onchange="trim(this)"
													onkeypress='return restrictAlphabets(event)' id="noOfBookPrchs"
													name="noOfBookPrchs" onchange="trim(this)" maxlength="7"
													placeholder="No of Copies for Book" autocomplete="off"
													value="${lib.noOfBooks}"> <span
													class="error_form text-danger" id="error_formfield1"
													style="display: none;">Please enter No. of copies
													and value must be greater than 0.</span>
											</div>
										</div>

										<div class="form-group">

											<label class="control-label col-sm-2" for="costOfBook"
												style="text-align: left;">Cost of Book<span
												class="text-danger">*</span>
											</label>
											<div class="col-sm-10">
												<input type="text"  class="form-control"
													onkeypress='return restrictAlphabets(event)'
													onchange="trim(this)" id="costOfBook" name="costOfBook"
													autocomplete="off" maxlength="7"
													placeholder="Cost Of Rare Book" onchange="trim(this)"
													value="${lib.costOfBooks}"> <span
													class="error_form text-danger" id="error_formfield2"
													style="display: none;">Please enter cost of rare
													book and value must be greater than 0.</span>
											</div>
										</div>

										<div class="form-group">

											<label class="control-label col-sm-2" for="noOfBook"
												style="text-align: left;">No. of Journal <span
												class="text-danger">*</span>
											</label>
											<div class="col-sm-10">
												<input type="text"  class="form-control"
													onchange="trim(this)"
													onkeypress='return restrictAlphabets(event)' id="noOfJournal"
													name="noOfJournal" onchange="trim(this)" maxlength="7"
													placeholder="No of Copies for Journal" autocomplete="off"
													value="${lib.noOfJournal}"> <span
													class="error_form text-danger" id="error_formfield3"
													style="display: none;">Please enter No. of copies
													and value must be greater than 0.</span>
											</div>
										</div>

										<div class="form-group">

											<label class="control-label col-sm-2" for="costOfBook"
												style="text-align: left;">Cost of Journal<span
												class="text-danger">*</span>
											</label>
											<div class="col-sm-10">
												<input type="text" class="form-control"
													onkeypress='return restrictAlphabets(event)'
													onchange="trim(this)" id="costOfJournal" name="costOfJournal"
													autocomplete="off" maxlength="7"
													placeholder="Cost Of Journal" onchange="trim(this)"
													value="${lib.costOfJournal}"> <span
													class="error_form text-danger" id="error_formfield4"
													style="display: none;">Please enter cost of rare
													book and value must be greater than 0.</span>
											</div>
										</div>
										
										<div class="form-group">

											<label class="control-label col-sm-2" for="noOfBook"
												style="text-align: left;">No. of E-Journal <span
												class="text-danger">*</span>
											</label>
											<div class="col-sm-10">
												<input type="text"  class="form-control"
													onchange="trim(this)"
													onkeypress='return restrictAlphabets(event)' id="noOfEJournal"
													name="noOfEJournal" onchange="trim(this)" maxlength="7"
													placeholder="No of E-Journal" autocomplete="off"
													value="${lib.noOfEjournal}"> <span
													class="error_form text-danger" id="error_formfield5"
													style="display: none;">Please enter No. of copies
													and value must be greater than 0.</span>
											</div>
										</div>

										<div class="form-group">

											<label class="control-label col-sm-2" for="costOfBook"
												style="text-align: left;">Cost of E-Journal<span
												class="text-danger">*</span>
											</label>
											<div class="col-sm-10">
												<input type="text" class="form-control"
													onkeypress='return restrictAlphabets(event)'
													onchange="trim(this)" id="costOfEJournal" name="costOfEJournal"
													autocomplete="off" maxlength="7"
													placeholder="Cost Of E-Journal" onchange="trim(this)"
													value="${lib.costOfEjournal}"> <span
													class="error_form text-danger" id="error_formfield6"
													style="display: none;">Please enter cost of rare
													book and value must be greater than 0.</span>
											</div>
										</div>

									

										<div class="form-group">
											<div class="col-sm-offset-2 col-sm-10">
												<button type="submit" id="sub1"
													class="btn btn-primary" onclick="submit_f(1)">
													<i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save
												</button>

												<a
													href="${pageContext.request.contextPath}/showlibBookPurchased"><button
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

		$(document).ready(function($) {

			$("#form_sample_2").submit(function(e) {
				//	alert("hi");
				var isError = false;
				var errMsg = "";

				if ($("#noOfBookPrchs").val() <= 0 || !$("#noOfBookPrchs").val()) {
					isError = true;
					$("#noOfBookPrchs").addClass("has-error")
					$("#error_formfield1").show()
				} else {
					$("#error_formfield1").hide()
				}

				if ($("#costOfBook").val() <= 0 || !$("#costOfBook").val()) {
					isError = true;
					$("#costOfBook").addClass("has-error")
					$("#error_formfield2").show()
					//return false;
				} else {
					$("#error_formfield2").hide()
				}
				 
				
				if ($("#noOfJournal").val() <= 0 || !$("#noOfJournal").val()) {
					isError = true;
					$("#noOfJournal").addClass("has-error")
					$("#error_formfield3").show()
					//return false;
				} else {
					$("#error_formfield3").hide()
				}

				if ($("#costOfJournal").val() <= 0 || !$("#costOfJournal").val()) {
					isError = true;					
					$("#costOfJournal").addClass("has-error")
					
					$("#error_formfield4").show()
					//return false;
				} else {
					$("#error_formfield4").hide()
				}

				
				if ($("#noOfEJournal").val() <= 0 || !$("#noOfEJournal").val()) {
					isError = true;
					$("#noOfEJournal").addClass("has-error")
					$("#error_formfield5").show()
					//return false;
				} else {
					$("#error_formfield5").hide()
				}

				if ($("#costOfEJournal").val() <= 0 || !$("#costOfEJournal").val()) {
					isError = true;
					$("#costOfEJournal").addClass("has-error")
					
					$("#error_formfield6").show()
					//return false;
				} else {
					$("#error_formfield6").hide()
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

						document.getElementById("sub1").disabled = true;
						document.getElementById("sub2").disabled = true;
						return true;
					}
				}
				return false;
			});
		});
	</script> -->

	<script type="text/javascript">
		/*code: 48-57 Numbers
		  8  - Backspace,
		  35 - home key, 36 - End key
		  37-40: Arrow keys, 46 - Delete key*/
		function restrictAlphabets(e) {
			var x = e.which || e.keycode;
			if ((x >= 48 && x <= 57) || x == 8 || (x >= 35 && x <= 40)
					|| x == 46)
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
					[ i + 1, year, name, publisher, noOfBook, costOfBook ])
					.draw();
			document.getElementById("index").value = i + 1;
		}

		function submit_f(view) {
			document.getElementById("is_view").value = view;//create this 

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
		function checkBeforeSubmit() {
			if (!wasSubmitted) {
				var x = confirm("Do you really want to submit the form?");
				if (x == true) {
					wasSubmitted = true;
					document.getElementById("sub1").disabled = true;
					document.getElementById("sub2").disabled = true;

					return wasSubmitted;
				}
			}
			return false;
		}
	</script>


</body>
</html>