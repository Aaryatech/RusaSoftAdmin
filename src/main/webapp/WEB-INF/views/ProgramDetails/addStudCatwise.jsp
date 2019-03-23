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
/* .txt {
				background-color: #FEFFB0;
				font-weight: bold;
				text-align: right;
			} */
</style>


<!-- BEGIN BODY -->
<body class=" " onload="calculateSum()()">
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
								<a href="${pageContext.request.contextPath}/showStudAddmit"><button
										type="button" class="btn btn-info">Back</button></a>
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									

									<!-- <ul class="nav nav-tabs">
										<li class="active"><a href="#home" data-toggle="tab">
												<i class="fa fa-home"></i> Register Form
										</a></li>


									</ul>

									<div class="tab-content">
										<div class="tab-pane fade in active" id="home"> -->


											<form class="form-horizontal"
												action="${pageContext.request.contextPath}/insertStudAdmCatwise"
												method="post" name="form_sample_2" id="form_sample_2"
												onsubmit="return checkBeforeSubmit()">

												<div class="row">
													<div class="col-md-12">
														<table class="table table-striped dt-responsive display">
															<thead>
																<tr>
																	<th width="10%">Sr No</th>
																	<th width="30%">Category</th>
																	<th width="60%" style="text-align: center;" colspan="3">No.
																		of Students</th>

																</tr>
																<tr>
																	<th width="10%"></th>
																	<th width="30%"></th>
																	<th width="20%">Male</th>
																	<th width="20%">Female</th>
																	<th width="20%">Transgender</th>


																</tr>
															</thead>
															<tbody>
																<c:choose>
															<c:when test="${isEdit==0}">
																<c:forEach items="${castList}" var="cast"
																	varStatus="count">
																	<tr>
																		<td>${count.index+1 }</td>
																		<td>${cast.castName}</td>

																		<td><input type="number" min="0" max="99999" onkeyup="calculateSum()"
																			class="txt" id="cast_m${cast.castId}"
																			name="cast_m${cast.castId}" value="0"   required></td>
																		<td><input type="number" min="0" max="99999" onkeyup="calculateSum()"
																			class="txt" id="cast_f${cast.castId}"
																			name="cast_f${cast.castId}" value="0" required></td>
																		<td><input type="number" min="0" max="99999"  onkeyup="calculateSum()"
																			class="txt" id="cast_t${cast.castId}"
																			name="cast_t${cast.castId}" value="0" required></td>
																	</tr>
																</c:forEach>
																</c:when>
																<c:otherwise>
																
																<c:forEach items="${studAdmCastList}" var="cast"
																	varStatus="count">
																	<tr>
																		<td>${count.index+1 }</td>
																		<td>${cast.castName}</td>

																		<td><input type="number" min="0" max="99999" onkeyup="calculateSum()"
																			class="txt" id="cast_m${cast.studentCatId}"
																			name="cast_m${cast.studentCatId}" value="${cast.maleStudent}" required></td>
																		<td><input type="number" min="0" max="99999"  onkeyup="calculateSum()"
																			class="txt" id="cast_f${cast.studentCatId}"
																			name="cast_f${cast.studentCatId}"  value="${cast.femaleStudent}" required></td>
																		<td><input type="number" min="0" max="99999" onkeyup="calculateSum()"
																			class="txt" id="cast_t${cast.studentCatId}"
																			name="cast_t${cast.studentCatId}" value="${cast.transStudent}" required></td>
																			
																			
																	</tr>
																</c:forEach>
																</c:otherwise>
																</c:choose>
															</tbody>
														</table>
													</div>

													<input type="hidden" id="isEdit" name="isEdit"
														value="${isEdit}"> <input type="hidden" id="is_view"
														name="is_view" value="0">

													<div class="form-group">
														<div class="col-sm-offset-2 col-sm-10">
															<input type="submit" class="btn btn-primary" id="sub1"
																onclick="submit_f(0)" value="Save"> <input
																type="submit" class="btn btn-primary" id="sub2"
																onclick="submit_f(1)" value="Save And Next">
																
																 
															<button type="reset" class="btn btn-default">Reset</button>
															
															<label class="control-label" for="fPassingYear"><b>Total Students</b><span class="text-danger"></span>
														</label>
															<input
																type="text"   style="color:black; border-radius: 15px; align-items: center;" readonly placeholder="Total Student" id="total_stud"
																>
														</div>
													</div>

													<div class="clearfix"></div>



												</div>
											</form>
										<!-- </div>

									</div>
 -->
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
		function checkUnique(inputValue, valueType) {
			//alert(inputValue);

			var primaryKey = $
			{
				editInst.librarianId
			}
			;
			//alert("Primary key"+primaryKey);
			var isEdit = 0;
			if (primaryKey > 0) {
				isEdit = 1;
			}
			//alert("Is Edit " +isEdit);

			var valid = false;
			if (valueType == 1) {
				//alert("Its Mob no");
				if (inputValue.length == 10) {
					valid = true;
					//alert("Len 10")
				} else {
					//alert("Not 10");
				}
			} else if (valueType == 2) {
				//alert("Its Email " );

				var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
				if (inputValue.match(mailformat)) {
					valid = true;
					//alert("Valid Email Id");
				} else {
					valid = false;
					//alert("InValid Email Id");
				}
			}
			if (valid == true)
				$
						.getJSON(
								'${checkUniqueField}',
								{

									inputValue : inputValue,
									valueType : valueType,
									primaryKey : primaryKey,
									isEdit : isEdit,
									tableId : 4,

									ajax : 'true',

								},
								function(data) {

									//	alert("Data  " +JSON.stringify(data));
									if (data.error == true) {
										if (valueType == 2) {

											alert("This email id already exist in system please enter unique");
											$('#librarian_email').val('');
											//document.getElementById("stud_contact_no").value=" ";

										} else {

											alert("This contact no  already exist in system please enter unique");
											$('#lib_con_num').val('');
											//document.getElementById("student_email").value=" ";
										}
									}
								});
		}
	</script>

	<script type="text/javascript">
		function showExtraField() {
			//alert("hii");
			//document.getElementById("abc").style = "display:none"
			var qualType = document.getElementById("approveValue").value
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
		function submit_f(view) {
			
			document.getElementById("is_view").value = view;//create this 
		}
	</script>

	
	<script type="text/javascript">

$(document).ready(function(){

	$(".txt").each(function() {
		$(this).keyup(function(){
			calculateSum();
		});
	});

});

function calculateSum() {
	var sum = 0;
	$(".txt").each(function() {

		if(!isNaN(this.value) && this.value.length!=0) {
			sum += parseFloat(this.value);
		}

	});
	document.getElementById("total_stud").value=sum;
}

    </script>


	<!-- END CONTAINER -->
	<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->

	<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>



</body>
</html>