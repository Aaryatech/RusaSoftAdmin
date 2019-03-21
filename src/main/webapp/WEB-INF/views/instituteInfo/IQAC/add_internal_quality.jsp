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

							<div class="actions panel_actions pull-right">
								<a
									href="${pageContext.request.contextPath}/showInternalQualityInitiative"><button
										type="button" class="btn btn-info">Back</button></a>

							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertPublicationDetail"
										method="post" enctype="multipart/form-data"
										name="form_sample_2" id="form_sample_2"
										onsubmit="return confirm('Do you really want to submit the form?');">

										<%-- <ul class="nav nav-tabs">
											<li class="active"><a href="#home" data-toggle="tab">
													<i class="fa fa-home"></i> ${title}
											</a></li>

										</ul> --%>

										<!-- <div class="tab-content">
											<div class="tab-pane fade in active" id="home"> -->




										<div class="form-group">
											<label class="control-label col-sm-2" for="initiativeName">Name
												of Quality Initiative<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<select id="initiativeName" name="initiativeName"
													onchange="showForm()" class="form-control" required>
													<!-- <option value=" ">Select</option>
													<option value="AQAR prepared or submitted">AQAR
														prepared or submitted</option>
													<option value="Academic – Administrative Audit Conducted">Academic
														– Administrative Audit Conducted</option>

													<option value="AQAR prepared or submitted">AQAR
														prepared or submitted</option>



													<option value="Institute Participated in NIRF">Institute
														Participated in NIRF</option>
													<option value="ISO Certification obtained">ISO
														Certification obtained</option>

													<option value="NBA Certification received">NBA
														Certification received</option>
													<option value="7">Any other quality based
														certification</option> -->

												</select>
											</div>
										</div>

										<div class="form-group" id="abc">

											<label class="control-label col-sm-2" for="page_name">Any
												other quality based certification <span class="text-danger">*</span>
											</label>
											<div class="col-sm-3">
												<input type="text" class="form-control" id="otherQual"
													required name="otherQual"
													placeholder="Any other quality based certification"
													value="${page.pageName}">
											</div>
										</div>



										<div class="form-group">

											<label class="control-label col-sm-2" for="fromDate">From
												Date <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control datepicker"
													autocomplete="off" id="fromDate" name="fromDate"
													value="${page.pageName}" required>

											</div>
										</div>

										<div class="form-group">

											<label class="control-label col-sm-2" for="toDate">To
												Date <span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control datepicker"
													autocomplete="off" id="toDate" name="toDate"
													value="${page.pageName}" required>
											</div>
										</div>



										<div class="form-group">
											<label class="control-label col-sm-2" for="participant">No.
												of Participants<span class="text-danger">*</span>
											</label>
											<div class="col-sm-6">
												<input type="number" class="form-control" id="participant"
													autocomplete="off" name="participant"
													onkeypress="return allowOnlyNumber(event)" min="0"
													placeholder="No. of Participants" value="${page.pageName}"
													required>
											</div>
										</div>
										<input type="hidden" id="is_view" name="is_view" value="0">

										<div class="form-group">
											<div class="col-sm-offset-2 col-sm-10">
												<input type="submit" class="btn btn-primary"
													onclick="submit_f(1)" value="Save"> <input
													type="submit" class="btn btn-primary" onclick="submit_f(0)"
													value="Save &
																		Next">
												<button type="reset" class="btn btn-default">Reset</button>
											</div>
											<!-- 	</div>
											</div> -->


											<div class="clearfix"></div>
										</div>

									</form>
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



</body>
</html>